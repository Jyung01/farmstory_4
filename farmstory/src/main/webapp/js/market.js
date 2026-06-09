document.addEventListener('DOMContentLoaded', function() {
    
    // 💡 [테스트] 버튼과 입력창들을 제대로 읽어오고 있는지 확인하는 돋보기 로그
    console.log("1. 장바구니버튼:", document.getElementById('btnCart'));
    console.log("2. 바로구매버튼:", document.getElementById('btnOrder'));
    console.log("3. 상품번호태그:", document.getElementById('productNo'));
    console.log("4. 재고태그:", document.getElementById('productStock'));
    console.log("5. 수량태그:", document.getElementById('productCount'));
    console.log("6. 패스타그:", document.getElementById('Path'));
    console.log("7. 로그인태그:", document.getElementById('loginCheck'));

    //-----------------
    //합계
    //-----------------
    const countInput = document.getElementById('productCount');
    const totalPriceEl = document.getElementById('totalPrice');

    if (countInput && totalPriceEl) {
        countInput.addEventListener('input', function() {
            const unitPrice = parseFloat(this.dataset.price) || 0;
            let count = parseInt(this.value);
            if (isNaN(count) || count < 1) count = 1;
            
            const total = unitPrice * count;
            totalPriceEl.innerText = total.toLocaleString() + '원';
        });
    }

    //-----------------
    // 장바구니
    //-----------------
    const btnCart = document.getElementById('btnCart');
    const btnOrder = document.getElementById('btnOrder');
    
    const prodNoInput = document.getElementById('productNo'); 
    const stockInput = document.getElementById('productStock');
    const pathInput = document.getElementById('Path');       
    const loginInput = document.getElementById('loginCheck'); 

    if (btnCart && btnOrder && countInput && stockInput && prodNoInput && pathInput && loginInput) {
        
        function checkStockAndCount(e) {
            e.preventDefault(); 
            const contextPath = pathInput.value;
            const isNotLoggedIn = loginInput.value === 'true';

            //-------------------
            //로그인체크
            //-------------------
            if (isNotLoggedIn) {
                alert('로그인 후 시도하세요.');
                window.location.href = contextPath + '/test/login.do'; //로그인 구현 후 바꿔야함!!!
                return false;
            }

            //-------------------
            //재고 체크
            //-------------------
            const stock = parseInt(stockInput.value) || 0;
            let count = parseInt(countInput.value) || 1;
            const prodNo = prodNoInput.value;

            if (stock <= 0) {
                alert('해당 상품은 품절입니다.');
                return false;
            }

            if (count > stock) {
                alert(stock + '개 이상 구매 불가합니다.');
                return false;
            }

            if (e.currentTarget.id === 'btnCart') {
                
                console.log("JS 변수 확인 -> prodNo:", prodNo, " / count:", count);
                
                // 처음에는 중복 확인 및 기본 담기 시도 (checkOri=true 파라미터 추가)
                let requestUrl = contextPath + '/market/cart.do?prodNo=' + prodNo + '&count=' + count + '&checkOri=true';

                function sendCartRequest(url) {
                    fetch(url, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    })
                    .then(response => response.json()) // 서버에서 JSON 형태로 응답을 받음
                    .then(data => {
                        if (data.result === 'DUPLICATE') {
                            if (confirm('이미 장바구니에 있는 상품입니다. 추가하시겠습니까?')) {
                                const updateUrl = contextPath + '/market/cart.do?prodNo=' + prodNo + '&count=' + count + '&confirmed=true';
                                sendCartRequest(updateUrl);
                            }
                        } else if (data.result === 'SUCCESS') {
                            alert('장바구니에 상품이 담겼습니다.');
                            window.location.href = contextPath + '/market/cart.do';
                        } else if (data.result === 'SOLDOUT') {
                            alert('해당 상품은 품절되었습니다.');
                        } else if (data.result === 'LACK') {
                            alert('재고가 부족하여 해당 수량만큼 추가 구매가 불가합니다.');
                        }
                    })
                    .catch(err => {
                        console.error('에러 발생:', err);
                        alert('장바구니 담기 중 오류가 발생했습니다.');
                    });
                }

                sendCartRequest(requestUrl);

            } else if (e.currentTarget.id === 'btnOrder') {
                // 💡 괄호가 정상적으로 매칭되어 이제 이 블록이 똑바로 실행됩니다.
                console.log('바로구매 실행');
            }
        }

        btnCart.addEventListener('click', checkStockAndCount);
        btnOrder.addEventListener('click', checkStockAndCount);
    }
});