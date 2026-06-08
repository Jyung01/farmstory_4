document.addEventListener('DOMContentLoaded', function() {
    // 1. 합계 계산 로직 (기존 소스 유지)
    const countInput = document.getElementById('productCount');
    const totalPriceEl = document.getElementById('totalPrice');

    if (countInput && totalPriceEl) {
        countInput.addEventListener('input', function() {
            const unitPrice = parseInt(this.dataset.price) || 0;
            let count = parseInt(this.value);
            if (isNaN(count) || count < 1) count = 1;
            
            const total = unitPrice * count;
            totalPriceEl.innerText = total.toLocaleString() + '원';
        });
    }

    // 2. 장바구니 및 바로구매 클릭 시 검증 및 전송 로직
    const btnCart = document.getElementById('btnCart');
    const btnOrder = document.getElementById('btnOrder');
    
    const prodNoInput = document.getElementById('productNo'); // 추가됨
    const stockInput = document.getElementById('productStock');

    if (btnCart && btnOrder && countInput && stockInput && prodNoInput) {
        
        function checkStockAndCount(e) {
            e.preventDefault(); // a 태그 본래의 이동 기능 막기

            const stock = parseInt(stockInput.value) || 0;
            let count = parseInt(countInput.value) || 1;
            const prodNo = prodNoInput.value;

            // [조건 1] 아예 재고가 0 이하로 품절인 경우
            if (stock <= 0) {
                alert('해당 상품은 품절입니다.');
                return false;
            }

            // [조건 2] 선택한 수량이 총 재고보다 많은 경우
            if (count > stock) {
                alert(stock + '개 이상 구매 불가합니다.');
                return false;
            }
            
            // 클릭한 버튼이 [장바구니]일 때 -> Ajax로 doPost 요청 날리기
            if (e.currentTarget.id === 'btnCart') {
                
                // URLSearchParams를 이용해 POST 파라미터 형태로 포장
                const params = new URLSearchParams();
                params.append('prodNo', prodNo);
                params.append('count', count);

                // 현재 컨텍스트 패스를 동적으로 구하기 위해 윈도우 로케이션 활용 가능
                // 안전하게 주소 매핑 처리 (프로젝트 컨텍스트명이 farmstory이므로 맞춰서 전송)
                fetch('/farmstory/market/cart.do', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: params.toString()
                })
                .then(response => {
                    if (response.redirected) {
                        // 컨트롤러에서 대소문자나 세션 필터 등으로 인해 튕겨나간 주소가 있다면 그리로 이동
                        window.location.href = response.url;
                    } else {
                        // 정상 등록 완료 시 목록 페이지(doGet)로 이동
                        window.location.href = '/farmstory/market/cart.do';
                    }
                })
                .catch(err => {
                    console.error('에러 발생:', err);
                    alert('장바구니 담기 중 오류가 발생했습니다.');
                });

            } else if (e.currentTarget.id === 'btnOrder') {
                // 바로구매 버튼 로직은 추후 주문하기 만들 때 여기에 코딩!
                console.log('바로구매 실행');
            }
        }

        // 두 버튼 모두에 검증 함수 연결
        btnCart.addEventListener('click', checkStockAndCount);
        btnOrder.addEventListener('click', checkStockAndCount);
    }
});