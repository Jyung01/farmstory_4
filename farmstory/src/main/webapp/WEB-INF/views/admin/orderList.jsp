<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_주문목록</title>
        <link rel="stylesheet" href="../css/index.css" />
        
        <script>
	        document.addEventListener('DOMContentLoaded', function() {
	            // 1. 전체 선택/해제 기능 구현
	            const thCheckbox = document.querySelector('table tr th input[type="checkbox"]');
	            const tdCheckboxes = document.querySelectorAll('table tr td input[name="itemNo"]');
	            
	            if (thCheckbox) {
	                thCheckbox.addEventListener('change', function() {
	                    tdCheckboxes.forEach(cb => {
	                        cb.checked = thCheckbox.checked;
	                    });
	                });
	            }
	
	            // 2. 폼 전송(제출) 시 유효성 검사 및 확인창 띄우기
	            const deleteForm = document.querySelector('form[action="/farmstory/admin/orderDelete.do"]');
	            if (deleteForm) {
	                deleteForm.addEventListener('submit', function(e) {
	                    // 체크된 항목 개수 확인
	                    const checkedCount = document.querySelectorAll('input[name="itemNo"]:checked').length;
	                    
	                    if (checkedCount === 0) {
	                        alert('삭제할 주문 항목을 하나 이상 선택해 주세요.');
	                        e.preventDefault(); // 서버 전송(Submit)을 강제로 중단
	                        return false;
	                    }
	                    
	                    if (!confirm('선택한 ' + checkedCount + '개의 주문 항목을 삭제하시겠습니까?')) {
	                        e.preventDefault(); // 취소 누르면 전송 중단
	                        return false;
	                    }
	                });
	            }
	        });
        </script>
    </head>
    
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_admin_header.jsp" %>

            <main>
                <aside>
                    <div><p>주요 기능</p></div>
                    <ul>
                        <li>
                            <span>상품관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/prodList.do">L <span>상품목록</span></a>
                                </li>
                                <li>
                                    <a href="/farmstory/admin/register.do">L <span>상품등록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>주문관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/orderList.do" class="active">L <span>주문목록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>회원관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/userList.do">L <span>회원목록</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </aside>

                <section class="admin_main orderList">
                    <h2>주문목록</h2>
                    
					<form action="/farmstory/admin/orderDelete.do" method="post">
	                    <section>
	                        <table>
	                            <tr>
	                                <th><input type="checkbox" id="checkAll" /></th>
	                                <th>주문번호</th>
	                                <th>상품명</th>
	                                <th>판매가격</th>
	                                <th>수량</th>
	                                <th>배송비</th>
	                                <th>합계</th>
	                                <th>주문자</th>
	                                <th>주문일</th>
	                                <th>확인</th>
	                            </tr>
	                            <c:forEach var="order" items="${dtoList}">
	                            	<tr>
		                                <td><input type="checkbox" name="itemNo" value="${order.itemNo}" /></td>
		                                <td>${order.orderNo}</td>
		                                <td>${order.prodName}</td>
		                                <td>${order.price}원</td>
		                                <td>${order.count}</td>
		                                <td>${order.delivery}원</td>
		                                <td>${order.totalPrice}원</td>
		                                <td>${order.name}</td>
		                                <td>${order.regDate}</td>
		                                <td><a href="/farmstory/admin/orderDetail.do?orderNo=${order.orderNo}&prodNo=${order.prodNo}">[상세확인]</a></td>
	                            	</tr>
	                            </c:forEach>
	                           
	                        </table>
	                        
	                       	<c:if test="${empty dtoList}">
							    <p style="text-align:center; padding:20px 0;">주문 목록이 없습니다.</p>
							</c:if>
	                    </section>
	
	                    <div class="buttons">
	                        <input type="submit" value="선택삭제">
	                    </div>
                    </form>

                    <!-- 페이지네이션 -->
                    <div class="pagination_wrapper">
                        <ul class="pagination">
                        	<c:if test="${pageGroupDTO.start > 1}">
		                    	<a href="/farmstory/admin/orderList.do?page=${pageGroupDTO.start - 1}"><</a>
		                    </c:if>
		                    
                        	<c:forEach var="i" begin="${pageGroupDTO.start}" end="${pageGroupDTO.end}">
                        		<li><a href="/farmstory/admin/orderList.do?page=${i}" class="${currentPage == i ? 'page_active' : ''}">[${i}]</a></li>
                        	</c:forEach>
                        
                        	<c:if test="${pageGroupDTO.end < lastPage}">
		                    	<a href="/farmstory/admin/orderList.do?page=${pageGroupDTO.end + 1}">></a>
		                    </c:if>
                        </ul>
                    </div>
                </section>
            </main>

            <%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
