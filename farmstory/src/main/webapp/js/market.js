document.addEventListener('DOMContentLoaded', function() {

    // ========================================================
    // [1] 상품 상세보기 페이지: 수량 변경 시 실시간 총 상품금액 계산
    // ========================================================
    var countInput = document.getElementById('productCount');
    var detailTotalPriceEl = document.getElementById('totalPrice');

    if (countInput && detailTotalPriceEl) {
        countInput.addEventListener('input', function() {
            var unitPrice = parseFloat(this.dataset.price) || 0;
            var count = parseInt(this.value);
            if (isNaN(count) || count < 1) count = 1;

            var total = unitPrice * count;
            detailTotalPriceEl.innerText = total.toLocaleString() + '원';
        });
    }

	// ========================================================
	    // [2] 상품 상세보기 페이지: 장바구니 담기 및 바로구매 예외 처리
	    // ========================================================
	    var btnCart = document.getElementById('btnCart');
	    var btnOrder = document.getElementById('btnOrder');
	    
	    var countInput = document.getElementById('productCount');
	    var prodNoInput = document.getElementById('productNo'); 
	    var stockInput = document.getElementById('productStock');
	    var pathInput = document.getElementById('Path');       
	    var loginInput = document.getElementById('loginCheck'); 

	    // 장바구니 전용 비동기 요청 함수
	    function sendCartRequest(url) {
	        fetch(url, {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded'
	            }
	        })
	        .then(function(response) { 
	            if (!response.ok) {
	                throw new Error('서버 응답 오류 (Status: ' + response.status + ')');
	            }
	            return response.json(); 
	        })
	        .then(function(data) {
	            if (data.result === 'DUPLICATE') {
	                if (confirm('이미 장바구니에 있는 상품입니다. 추가하시겠습니까?')) {
	                    // 🔥 [해결 포인트] 함수 매개변수에 의존하지 않고, 엘리먼트에서 직접 최신 값을 다시 읽어옵니다.
	                    var currentPath = document.getElementById('Path').value;
	                    var currentProdNo = document.getElementById('productNo').value;
	                    var currentCount = parseInt(document.getElementById('productCount').value) || 1;
	                    
	                    // 주소가 undefined로 쪼개지지 않도록 완벽하게 변수 배치
	                    var updateUrl = currentPath + '/market/cart.do?prodNo=' + currentProdNo + '&count=' + currentCount + '&confirmed=true';
	                    sendCartRequest(updateUrl);
	                }
	            } else if (data.result === 'SUCCESS') {
	                var currentPath = document.getElementById('Path').value;
	                alert('장바구니에 상품이 담겼습니다.');
	                window.location.href = currentPath + '/market/cart.do';
	            } else if (data.result === 'SOLDOUT') {
	                alert('해당 상품은 품절되었습니다.');
	            } else if (data.result === 'LACK') {
	                alert('재고가 부족하여 해당 수량만큼 추가 구매가 불가합니다.');
	            }
	        })
	        .catch(function(err) {
	            console.error('에러 발생:', err);
	            alert('장바구니 담기 중 오류가 발생했습니다.');
	        });
	    }

	    if (btnCart && btnOrder && countInput && stockInput && prodNoInput && pathInput && loginInput) {
	        
	        // 공통 유효성 검사 함수 (로그인, 품절, 재고 체크)
	        function validateSelection() {
	            var contextPath = pathInput.value;
	            var isNotLoggedIn = loginInput.value === 'true';

	            if (isNotLoggedIn) {
	                alert('로그인 후 시도하세요.');
	                window.location.href = contextPath + '/user/login.do';
	                return false;
	            }

	            var stock = parseInt(stockInput.value) || 0;
	            var count = parseInt(countInput.value) || 1;

	            if (stock <= 0) {
	                alert('해당 상품은 품절입니다.');
	                return false;
	            }

	            if (count > stock) {
	                alert(stock + '개 이상 구매 불가합니다.');
	                return false;
	            }
	            return true;
	        }

	        // A. 장바구니 버튼 클릭 이벤트
	        btnCart.addEventListener('click', function(e) {
	            e.preventDefault();
	            if (!validateSelection()) return; 

	            var contextPath = pathInput.value;
	            var prodNo = prodNoInput.value;
	            var count = parseInt(countInput.value) || 1;

	            var requestUrl = contextPath + '/market/cart.do?prodNo=' + prodNo + '&count=' + count + '&checkOri=true';
	            sendCartRequest(requestUrl);
	        });

	        // B. 바로구매 버튼 클릭 이벤트 (장바구니 안 거치고 직접 POST 전송)
	        btnOrder.addEventListener('click', function(e) {
	            e.preventDefault();
	            if (!validateSelection()) return; 

	            var contextPath = pathInput.value;
	            var prodNo = prodNoInput.value;
	            var count = parseInt(countInput.value) || 1;

	            var form = document.createElement('form');
	            form.method = 'POST';
	            form.action = contextPath + '/market/order.do'; 

	            var inputProdNo = document.createElement('input');
	            inputProdNo.type = 'hidden';
	            inputProdNo.name = 'prodNo';
	            inputProdNo.value = prodNo;
	            form.appendChild(inputProdNo);

	            var inputCount = document.createElement('input');
	            inputCount.type = 'hidden';
	            inputCount.name = 'count';
	            inputCount.value = count;
	            form.appendChild(inputCount);

	            var inputMode = document.createElement('input');
	            inputMode.type = 'hidden';
	            inputMode.name = 'buyNow';
	            inputMode.value = 'true';
	            form.appendChild(inputMode);

	            document.body.appendChild(form);
	            form.submit();
	        });
	    }
    // ========================================================
    // [3] 장바구니 페이지: 전체 합계 정산 테이블 연산 기능 (공통 함수)
    // ========================================================
    var cartCheckboxes = document.querySelectorAll('.cart-checkbox:not([disabled])');
    var totalCountEl = document.getElementById('totalCount');
    var cartTotalPriceEl = document.getElementById('cartTotalPrice');
    var totalDiscountEl = document.getElementById('totalDiscount');
    var totalDeliveryEl = document.getElementById('totalDelivery');
    var totalPointEl = document.getElementById('totalPoint');
    var finalOrderPriceEl = document.getElementById('finalOrderPrice');

    function updateCartTotal() {
        var totalCount = 0;
        var totalPrice = 0;
        var totalDiscount = 0;
        var totalPoint = 0;
        var totalDelivery = 0;

        var checkedBoxes = document.querySelectorAll('.cart-checkbox:checked:not([disabled])');

        checkedBoxes.forEach(function(box) {
            var row = box.closest('tr');
            if (!row) return;

            var qtyInput = row.querySelector('.cart-qty-input');
            var count = qtyInput ? (parseInt(qtyInput.value) || 0) : (parseInt(row.querySelector('td:nth-child(5)').innerText.replace(/[^0-9]/g, '')) || 0);

            var price = parseInt(row.querySelector('td:nth-child(8)').innerText.replace(/[^0-9]/g, '')) || 0;
            var discountPercent = parseInt(row.querySelector('td:nth-child(6)').innerText.replace(/[^0-9]/g, '')) || 0;
            var unitPoint = parseInt(row.querySelector('td:nth-child(7)').innerText.replace(/[^0-9]/g, '')) || 0;

            var singlePoint = Math.round(unitPoint / (parseInt(qtyInput.defaultValue) || count));
            if (isNaN(singlePoint) || singlePoint === Infinity) singlePoint = 0;

            totalCount += 1;
            totalPrice += (price * count);
            totalDiscount += (price * (discountPercent / 100) * count);
            totalPoint += (singlePoint * count);
        });

        if (totalPrice >= 30000 || checkedBoxes.length === 0) {
            totalDelivery = 0;
        } else {
            totalDelivery = 3000;
        }

        var finalOrderPrice = totalPrice - totalDiscount + totalDelivery;

        if (totalCountEl) totalCountEl.innerText = checkedBoxes.length + "개";
        if (cartTotalPriceEl) cartTotalPriceEl.innerText = totalPrice.toLocaleString() + '원';
        if (totalDiscountEl) totalDiscountEl.innerText = totalDiscount.toLocaleString() + '원';
        if (totalDeliveryEl) totalDeliveryEl.innerText = totalDelivery.toLocaleString() + '원';
        if (totalPointEl) totalPointEl.innerText = totalPoint.toLocaleString() + '원';
        if (finalOrderPriceEl) finalOrderPriceEl.innerText = finalOrderPrice.toLocaleString() + '원';
    }

    if (cartCheckboxes.length > 0) {
        cartCheckboxes.forEach(function(checkbox) {
            checkbox.addEventListener('change', updateCartTotal);
        });
    }

    // ========================================================
    // [4] 장바구니 페이지: 전체 선택 체크박스 바인딩
    // ========================================================
    var checkAll = document.getElementById('chkAll');
    if (checkAll) {
        checkAll.addEventListener('change', function() {
            var isChecked = this.checked;
            cartCheckboxes.forEach(function(cb) {
                cb.checked = isChecked;
            });
            updateCartTotal();
        });
    }

    // ========================================================
    // [5] 장바구니 페이지: 상품 수량 변경 및 DB 반영 (Ajax)
    // ========================================================
    var btnUpdateQtys = document.querySelectorAll('.btnUpdateQty');

    if (btnUpdateQtys.length > 0) {
        btnUpdateQtys.forEach(function(btn) {
            btn.addEventListener('click', function() {
                var prodNo = this.getAttribute('data-prodno');
                var row = this.closest('tr');
                var qtyInput = row.querySelector('.cart-qty-input');
                var count = parseInt(qtyInput.value) || 0;

                var pathElement = document.querySelector('.contextPath');
                var contextPath = pathElement ? pathElement.value : '';

                if (count < 1) {
                    alert('수량은 1개 이상이어야 합니다.');
                    qtyInput.value = 1;
                    return;
                }

                var maxStock = parseInt(qtyInput.getAttribute('max')) || 0;
                if (count > maxStock) {
                    alert('재고가 부족합니다. (현재 재고: ' + maxStock + '개)');
                    qtyInput.value = qtyInput.defaultValue;
                    return;
                }

                var xhr = new XMLHttpRequest();
                xhr.open('POST', contextPath + '/market/cart.do', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var data = JSON.parse(xhr.responseText);

                        if (data.result === 'SUCCESS') {
                            alert('수량이 변경되었습니다.');
                            updateRowSubtotalAndPoint(row, count);
                            updateCartTotal();
                        } else if (data.result === 'NOT_LOGGED_IN') {
                            alert('로그인이 필요합니다.');
                            window.location.href = contextPath + '/user/login.do';
                        } else if (data.result === 'LACK') {
                            alert('선택하신 수량이 남아있는 상품 재고를 초과했습니다.');
                            qtyInput.value = qtyInput.defaultValue;
                        } else {
                            alert('수량 변경에 실패했습니다.');
                        }
                    }
                };

                xhr.send('prodNo=' + prodNo + '&count=' + count + '&isCartPage=true');
            });
        });
    }

    function updateRowSubtotalAndPoint(row, count) {
        var price = parseInt(row.querySelector('td:nth-child(8)').innerText.replace(/[^0-9]/g, '')) || 0;
        var discountPercent = parseInt(row.querySelector('td:nth-child(6)').innerText.replace(/[^0-9]/g, '')) || 0;
        var pointTd = row.querySelector('td:nth-child(7)');

        if (pointTd) {
            var qtyInput = row.querySelector('.cart-qty-input');
            var currentPoint = parseInt(pointTd.innerText.replace(/[^0-9]/g, '')) || 0;
            var basePoint = Math.round(currentPoint / (parseInt(qtyInput.defaultValue) || count));
            pointTd.innerText = (basePoint * count).toLocaleString() + 'P';
            qtyInput.defaultValue = count;
        }

        var discountPrice = price * (discountPercent / 100);
        var finalPrice = (price - discountPrice) * count;

        var subtotalStrong = row.querySelector('td:nth-child(9) strong');
        if (subtotalStrong) {
            subtotalStrong.innerText = finalPrice.toLocaleString();
        }
    }

    // ========================================================
    // [6] 장바구니 페이지: 선택 상품 삭제 기능 (Ajax)
    // ========================================================
    var btnDeleteSelected = document.getElementById('btnDeleteSelected');

    if (btnDeleteSelected) {
        btnDeleteSelected.addEventListener('click', function() {
            var checkedBoxes = document.querySelectorAll('.cart-checkbox:checked:not([disabled])');

            if (checkedBoxes.length === 0) {
                alert('삭제할 상품을 선택해주세요.');
                return;
            }

            if (!confirm('선택한 상품을 장바구니에서 삭제하시겠습니까?')) {
                return;
            }

            var pathElement = document.querySelector('.contextPath');
            var contextPath = pathElement ? pathElement.value : '';

            var deleteCount = 0;
            var totalToDelete = checkedBoxes.length;

            checkedBoxes.forEach(function(box) {
                var cartNo = box.value;

                fetch(contextPath + '/market/cart.do', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'cartNo=' + cartNo
                })
                    .then(function(res) {
                        return res.text();
                    })
                    .then(function(data) {
                        if (data.trim() === "1") {
                            deleteCount++;
                            if (deleteCount === totalToDelete) {
                                alert('선택한 상품이 삭제되었습니다.');
                                location.reload();
                            }
                        } else {
                            alert('일부 상품 삭제에 실패했습니다.');
                        }
                    })
                    .catch(function(err) {
                        console.error('삭제 중 에러 발생:', err);
                    });
            });
        });
    }

    // ========================================================
    // [7] 장바구니 페이지: 주문하기 전송 (가상 폼 동적 전송 구조)
    // ========================================================
    var btnOrderSubmit = document.getElementById('btnOrderSubmit');

    if (btnOrderSubmit) {
        btnOrderSubmit.addEventListener('click', function() {
            var checkedBoxes = document.querySelectorAll('.cart-checkbox:checked:not([disabled])');

            if (checkedBoxes.length === 0) {
                alert('주문하실 상품을 선택해주세요.');
                return;
            }

            if (!confirm('선택한 상품을 주문하시겠습니까?')) {
                return;
            }

            var form = document.getElementById('formCartOrder');
            if (form) {
                form.innerHTML = '';

                checkedBoxes.forEach(function(box) {
                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'cartNo';
                    input.value = box.value;
                    form.appendChild(input);
                });

                form.submit();
            }
        });
    }

    // ========================================================
    // [8] 주문서 페이지(order.jsp): 금액 및 포인트 자동 연산기
    // ========================================================
    function calculateOrderTotal() {
        var orderRows = document.querySelectorAll('.order-item-row');
        if (orderRows.length === 0) return;

        var totalCount = 0;
        var totalPrice = 0;
        var totalDiscount = 0;
        var totalSavePoint = 0;

        orderRows.forEach(function(row) {
            var countEl = row.querySelector('.item-count');
            var priceEl = row.querySelector('.item-price');
            var discountEl = row.querySelector('.item-discount');
            var pointEl = row.querySelector('.item-point');

            var count = countEl ? (parseInt(countEl.getAttribute('data-count')) || 0) : 0;
            var price = priceEl ? (parseInt(priceEl.getAttribute('data-price')) || 0) : 0;
            var discountPercent = discountEl ? (parseInt(discountEl.getAttribute('data-discount')) || 0) : 0;
            var point = pointEl ? (parseInt(pointEl.getAttribute('data-point')) || 0) : 0;

            totalCount += 1;
            totalPrice += (price * count);
            totalDiscount += (price * (discountPercent / 100) * count);
            totalSavePoint += point;
        });

        var totalDelivery = (totalPrice - totalDiscount >= 30000 || totalCount === 0) ? 0 : 3000;
        var finalOrderPrice = totalPrice - totalDiscount + totalDelivery;

        var elTotalCount = document.getElementById('totalCount');
        var elCartTotalPrice = document.getElementById('cartTotalPrice');
        var elTotalDiscount = document.getElementById('totalDiscount');
        var elTotalDelivery = document.getElementById('totalDelivery');
        var elTotalSavePoint = document.getElementById('totalSavePoint');
        var elFinalOrderPrice = document.getElementById('finalOrderPrice');

        if (elTotalCount) elTotalCount.innerText = orderRows.length + "개";
        if (elCartTotalPrice) elCartTotalPrice.innerText = totalPrice.toLocaleString() + "원";
        if (elTotalDiscount) elTotalDiscount.innerText = totalDiscount.toLocaleString() + "원";
        if (elTotalDelivery) elTotalDelivery.innerText = totalDelivery.toLocaleString() + "원";
        if (elTotalSavePoint) elTotalSavePoint.innerText = totalSavePoint.toLocaleString() + "P";
        if (elFinalOrderPrice) elFinalOrderPrice.innerText = finalOrderPrice.toLocaleString() + "원";
    }

    // ========================================================
    // [9] 주문서 페이지(order.jsp): 다음 우편번호 API 호출 연동
    // ========================================================
    var btnZip = document.getElementById('btnZip');
    if (btnZip) {
        btnZip.addEventListener('click', function() {
            if (typeof daumPostcode === 'function') {
                daumPostcode();
            } else {
                alert('우편번호 서비스 로딩에 실패했습니다.');
            }
        });
    }

    // ========================================================
    // [10] 주문서 페이지(order.jsp): 포인트 검증 및 전체 금액 연동 제어
    // ========================================================
    var btnApplyPoint = document.getElementById('btnApplyPoint');
    if (btnApplyPoint) {
        btnApplyPoint.addEventListener('click', function() {
            var inputPointEl = document.getElementById('inputPoint');
            var displayedUsedPointEl = document.getElementById('displayedUsedPoint');
            var finalOrderPriceEl = document.getElementById('finalOrderPrice');
            var cartTotalPriceEl = document.getElementById('cartTotalPrice');
            var totalDiscountEl = document.getElementById('totalDiscount');
            var totalDeliveryEl = document.getElementById('totalDelivery');

            var maxPoint = parseInt(inputPointEl.getAttribute('max')) || 0;
            var usePoint = parseInt(inputPointEl.value) || 0;

            var price = parseInt(cartTotalPriceEl.innerText.replace(/[^0-9]/g, '')) || 0;
            var discount = parseInt(totalDiscountEl.innerText.replace(/[^0-9]/g, '')) || 0;
            var delivery = parseInt(totalDeliveryEl.innerText.replace(/[^0-9]/g, '')) || 0;
            var netOrderPrice = price - discount + delivery;

            if (usePoint < 0) {
                alert('0P 이상의 포인트만 입력 가능합니다.');
                inputPointEl.value = 0;
                return;
            }

            if (usePoint > maxPoint) {
                alert('포인트가 부족합니다.');
                if (maxPoint > netOrderPrice) {
                    inputPointEl.value = netOrderPrice;
                    usePoint = netOrderPrice;
                } else {
                    inputPointEl.value = maxPoint;
                    usePoint = maxPoint;
                }
            }

            if (usePoint > netOrderPrice) {
                alert('주문금액보다 포인트가 더 많습니다.');
                inputPointEl.value = netOrderPrice;
                usePoint = netOrderPrice;
            }

            var finalPrice = netOrderPrice - usePoint;

            if (displayedUsedPointEl) displayedUsedPointEl.innerText = usePoint.toLocaleString();
            if (finalOrderPriceEl) finalOrderPriceEl.innerText = finalPrice.toLocaleString() + "원";
        });
    }

    // ========================================================
    // [11] 주문서 페이지(order.jsp): 결제하기 최종 데이터 입력 상태 유효성 체크 및 전송
    // ========================================================
    var btnPaySubmit = document.getElementById('btnPaySubmit');

    if (btnPaySubmit) {
        btnPaySubmit.addEventListener('click', function() {
            var form = document.getElementById('formOrderFinal');
            if (!form) return;

            form.innerHTML = '';

            var elHiddenUserid = document.getElementById('hiddenUserid');
            var elOrderer = document.getElementById('orderer');
            var elOrdererHp = document.getElementById('ordererHp');
            var elInputPoint = document.getElementById('inputPoint');
            var elReceiver = document.getElementById('receiver');
            var elReceiverHp = document.getElementById('receiverHp');
            var elZip = document.getElementById('zip');
            var elAddr1 = document.getElementById('addr1');
            var elAddr2 = document.getElementById('addr2');
            var elMemo = document.getElementById('memo');
            var elPayment = document.querySelector('input[name="payment_view"]:checked');
            var elTotalDelivery = document.getElementById('totalDelivery');
            var elTotalSavePoint = document.getElementById('totalSavePoint');
            var elFinalOrderPrice = document.getElementById('finalOrderPrice');
            var hpRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;

            var fields = {
                userid: elHiddenUserid ? elHiddenUserid.value : '',
                orderer: elOrderer ? elOrderer.value.trim() : '',
                ordererHp: elOrdererHp ? elOrdererHp.value.trim() : '',
                usedPoint: elInputPoint ? elInputPoint.value : '0',
                receiver: elReceiver ? elReceiver.value.trim() : '',
                receiverHp: elReceiverHp ? elReceiverHp.value.trim() : '',
                zip: elZip ? elZip.value.trim() : '',
                addr1: elAddr1 ? elAddr1.value.trim() : '',
                addr2: elAddr2 ? elAddr2.value.trim() : '',
                memo: elMemo ? elMemo.value.trim() : '',
                payment: elPayment ? elPayment.value : '계좌이체',
                delivery: elTotalDelivery ? elTotalDelivery.innerText.replace(/[^0-9]/g, '') : '0',
                savePoint: elTotalSavePoint ? elTotalSavePoint.innerText.replace(/[^0-9]/g, '') : '0',
                totalPrice: elFinalOrderPrice ? elFinalOrderPrice.innerText.replace(/[^0-9]/g, '') : '0'
            };

            if (!fields.orderer) { alert('주문자 이름을 입력해주세요.'); elOrderer.focus(); return; }
            if (!fields.ordererHp) { alert('주문자 휴대폰 번호를 입력해주세요.'); elOrdererHp.focus(); return; }
            if (!fields.receiver) { alert('받는 분 이름을 입력해주세요.'); elReceiver.focus(); return; }
            if (!fields.receiverHp) { alert('받는 분 연락처를 입력해주세요.'); elReceiverHp.focus(); return; }
            if (!fields.zip || !fields.addr1) { alert('배송주소 조회를 위해 우편번호 검색을 해주세요.'); return; }
            if (!fields.addr2) { alert('상세주소를 입력해주세요.'); elAddr2.focus(); return; }

            if (!hpRegex.test(fields.ordererHp)) {
                alert('주문자 휴대폰 번호를 다시 입력해 주세요.');
                elOrdererHp.focus();
                return;
            }
            if (!hpRegex.test(fields.receiverHp)) {
                alert('받는 분 연락처(휴대폰 번호)를 다시 입력해 주세요.');
                elReceiverHp.focus();
                return;
            }

            for (var key in fields) {
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = key;
                input.value = fields[key];
                form.appendChild(input);
            }

            var rows = document.querySelectorAll('.order-item-row');
            if (rows.length === 0) {
                alert('주문할 상품 정보가 존재하지 않습니다.');
                return;
            }

            rows.forEach(function(row) {
                var cartNo = row.getAttribute('data-cartno');
                var prodNo = row.getAttribute('data-prodno');

                var countEl = row.querySelector('.item-count');
                var count = countEl ? (countEl.getAttribute('data-count') || '1') : '1';

                var subtotalEl = row.querySelector('.row-subtotal');
                var price = subtotalEl ? subtotalEl.innerText.replace(/[^0-9]/g, '') : '0';


                if (cartNo) {
                    var inputCart = document.createElement('input');
                    inputCart.type = 'hidden';
                    inputCart.name = 'cartNo';
                    inputCart.value = cartNo;
                    form.appendChild(inputCart);
                }

                if (prodNo) {
                    var inputProd = document.createElement('input');
                    inputProd.type = 'hidden';
                    inputProd.name = 'prodNo';
                    inputProd.value = prodNo;
                    form.appendChild(inputProd);
                }

                var inputCount = document.createElement('input');
                inputCount.type = 'hidden';
                inputCount.name = 'count';
                inputCount.value = count;
                form.appendChild(inputCount);

                var inputPrice = document.createElement('input');
                inputPrice.type = 'hidden';
                inputPrice.name = 'price';
                inputPrice.value = price;
                form.appendChild(inputPrice);
            });

            form.submit();
        });
    }
	
	// ========================================================
	// [12] 주문내역 상세보기 팝업창 띄우기
	// ========================================================
	var btnViewOrders = document.querySelectorAll('.btnViewOrder');

	if (btnViewOrders.length > 0) {
	    btnViewOrders.forEach(function(btn) {
	        btn.addEventListener('click', function(e) {
	            e.preventDefault();
	            
	            var requestUrl = this.getAttribute('data-url');
	            
	            var popupWidth = 800;
	            var popupHeight = 600;
	            var popupX = (window.screen.width / 2) - (popupWidth / 2);
	            var popupY = (window.screen.height / 2) - (popupHeight / 2);
	            
	            var popupOptions = 'width=' + popupWidth + 
	                               ',height=' + popupHeight + 
	                               ',left=' + popupX + 
	                               ',top=' + popupY + 
	                               ',scrollbars=yes,resizable=yes';
	            
	            window.open(requestUrl, 'OrderViewPopup', popupOptions);
	        });
	    });
	}
	
	

    // ========================================================
    // [13] ★ 핵심 초기화 통합 통제 엔진 구동부 분기
    // ========================================================
    if (document.querySelector('.order-item-row')) {
        calculateOrderTotal();
    } else if (cartTotalPriceEl) {
        updateCartTotal();
    }
});