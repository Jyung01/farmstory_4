<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_메인</title>
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
                                    <a href="/farmstory/admin/orderList.do">L <span>주문목록</span></a>
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

                <section class="admin_main">
                    <h2>관리자 메인</h2>

                    <!-- 상품현황 -->
                    <section>
                        <div>
                            <p>상품현황</p>
                            <a href="/farmstory/admin/prodList.do">+ 더보기</a>
                        </div>
                        <table>
                            <tr>
                                <th>상품번호</th>
                                <th>상품명</th>
                                <th>구분</th>
                                <th>가격</th>
                                <th>재고</th>
                                <th>등록일</th>
                            </tr>
                            
                            <c:forEach var="product" items="${prodList}">
	                            <tr>
	                                <td>${product.prodNo}</td>
	                                <td>${product.prodName}</td>
	                                <td>${product.cate}</td>
	                                <td>${product.price}원</td>
	                                <td>${product.stock}</td>
	                                <td>${product.regDate}</td>
	                            </tr>
                            </c:forEach>
                        </table>
                        
                        <c:if test="${empty prodList}">
						    <p style="text-align:center; padding:20px 0;">상품 목록이 없습니다.</p>
						</c:if>
                        
                    </section>

                    <!-- 주문현황 -->
                    <section>
                        <div>
                            <p>주문현황</p>
                            <a href="/farmstory/admin/orderList.do">+ 더보기</a>
                        </div>
                        <table>
                            <tr>
                                <th>주문번호</th>
                                <th>상품명</th>
                                <th>판매가격</th>
                                <th>수량</th>
                                <th>배송비</th>
                                <th>합계</th>
                                <th>주문자</th>
                                <th>주문일</th>
                            </tr>
                            
                            <c:forEach var="order" items="${orderList}">
                            	<tr>
 	                                <td>${order.orderNo}</td>
	                                <td>${order.prodName}</td>
	                                <td>${order.price}원</td>
	                                <td>${order.count}</td>
	                                <td>${order.delivery}원</td>
	                                <td>${order.totalPrice}원</td>
	                                <td>${order.name}</td>
	                                <td>${order.regDate}</td>
                            	</tr>
                            </c:forEach>
                        </table>
                        
                        <c:if test="${empty orderList}">
						    <p style="text-align:center; padding:20px 0;">주문 목록이 없습니다.</p>
						</c:if>
                    </section>

                    <!-- 회원현황 -->
                    <section>
                        <div>
                            <p>회원현황</p>
                            <a href="/farmstory/admin/userList.do">+ 더보기</a>
                        </div>
                        <table>
                            <tr>
                                <th>회원아이디</th>
                                <th>이름</th>
                                <th>닉네임</th>
                                <th>휴대폰</th>
                                <th>이메일</th>
                                <th>등급</th>
                                <th>회원가입일</th>
                            </tr>
                            
                            <c:forEach var="user" items="${userList}">
                            	<tr>
 	                                <td>${user.userid}</td>
	                                <td>${user.name}</td>
	                                <td>${user.nick}</td>
	                                <td>${user.email}</td>
	                                <td>${user.hp}</td>
	                                <c:choose>
									    <c:when test="${user.role eq 'admin'}">
									        <td>1</td>
									    </c:when>
									    <c:when test="${user.role eq 'member'}">
									        <td>2</td>
									    </c:when>
									    <c:otherwise>
									        <td>3</td>
									    </c:otherwise>
									</c:choose>
	                                
	                                <!-- 
		                                <td>
		                                    <select name="grade">
		                                        <option value="1" ${user.role eq 'admin' ? 'selected' : ''}>1</option>
		                                        <option value="2" ${user.role eq 'member' ? 'selected' : ''}>2</option>
		                                        <option value="3" ${user.role eq 'guest' ? 'selected' : ''}>3</option>
		                                    </select>
		                                    
		                                </td>
	                                -->
	                                <td>${user.regDate}</td>
	                            </tr>
                            </c:forEach>
                        </table>
                        
                        <c:if test="${empty userList}">
						    <p style="text-align:center; padding:20px 0;">회원 목록이 없습니다.</p>
						</c:if>
                    </section>
                </section>
            </main>
			<%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
