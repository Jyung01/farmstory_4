document.addEventListener('DOMContentLoaded', function() {

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

            if (!cartTotalPriceEl || !totalDiscountEl || !totalDeliveryEl) return;

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

            // 유효성 검사 분기문들
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

            // 🌟 [추가된 로직] 유효성 통과 후 최하단에서 서브밋 직전 알림창 띄우기
            alert('주문이 완료되었습니다.');
            form.submit();
        });
    }

    // ========================================================
    // [12] 주문내역 상세보기 팝업창 띄우기 (주문완료 페이지 등 고려 유지)
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
    // 핵심 초기화 엔진 구동부 (주문서 전용 격리)
    // ========================================================
    if (document.querySelector('.order-item-row')) {
        calculateOrderTotal();
    }
});