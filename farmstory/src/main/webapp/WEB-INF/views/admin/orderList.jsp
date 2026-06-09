<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_주문목록</title>
        <link rel="stylesheet" href="../css/index.css" />
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

                    <section>
                        <table>
                            <tr>
                                <th><input type="checkbox" /></th>
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
	                                <td><input type="checkbox" /></td>
	                                <td>${order.orderNo}</td>
	                                <td>${order.prodName}</td>
	                                <td>${order.price}원</td>
	                                <td>${order.count}</td>
	                                <td>${order.delivery}원</td>
	                                <td>${order.totalPrice}원</td>
	                                <td>${order.name}</td>
	                                <td>${order.regDate}</td>
	                                <td><a href="#">[상세확인]</a></td>
                            	</tr>
                            </c:forEach>
                           
                        </table>
                        
                       	<c:if test="${empty dtoList}">
						    <p style="text-align:center; padding:20px 0;">주문 목록이 없습니다.</p>
						</c:if>
                    </section>

                    <div class="buttons">
                        <a href="#">선택삭제</a>
                    </div>

                    <!-- 페이지네이션 -->
                    <div class="pagination_wrapper">
                        <ul class="pagination">
                            <li><a href="#"><</a></li>
                            <li><a href="#" class="page_active">[1]</a></li>
                            <li><a href="#">[2]</a></li>
                            <li><a href="#">[3]</a></li>
                            <li><a href="#">[4]</a></li>
                            <li><a href="#">[5]</a></li>
                            <li><a href="#">></a></li>
                        </ul>
                    </div>
                </section>
            </main>

            <%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
