<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::주문내역</title>
        <link rel="stylesheet" href="${path}/css/ordered.css" />
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
            <div id="myinfo">
                <div><img src="${path}/images/myinfo/myinfo_top_tit.png" alt="MY INFO" /></div>
                <section>
                    <aside>
                        <img src="${path}/images/myinfo/myinfo_menu_tit.png" alt="나의정보" />
                        <ul class="menu">
                            <li><a href="${path}/user/cart.do">장바구니</a></li>
                            <li class="on"><a href="${path}/user/ordered.do">주문내역</a></li>
                            <li><a href="${path}/user/myinfo.do">정보수정</a></li>
                        </ul>
                    </aside>
                    <article>
                        <nav>
                            <img src="${path}/images/myinfo/myinfo_nav_tit2.png" alt="주문내역" />
                            <p>HOME > 나의정보 > <em>주문내역</em></p>
                        </nav>
                        <section class="ordered">
                            <table border="0">
							    <tr>
							        <th>주문번호</th>
							        <th>이미지</th>
							        <th>상품명</th>
							        <th>수량</th>
							        <th>합계</th>
							        <th>주문자</th>
							        <th>주문일</th>
							        <th>확인</th>
							    </tr>

							    <c:choose>
							        <c:when test="${empty ordersList}">
							            <tr class="empty">
							                <td colspan="8">상품 구매 내역이 없습니다.</td>
							            </tr>
							        </c:when>
							        <c:otherwise>
							            <c:forEach var="order" items="${ordersList}">
							                <tr>
							                    <td>${order.orderNo}</td>
							                    <td>
							                        <img src="${path}/upload/${order.displayThumb}" class="thumb" alt="${order.displayProdName}" />
							                    </td>
							                    <td>${order.displayProdName}</td>
							                    <td>${order.totalCount}개</td>
							                    <td>
							                        <fmt:formatNumber value="${order.totalPrice}" type="number" />원
							                    </td>
							                    <td>${order.orderer}</td>
							                    <td>${order.regDate}</td>
							                    <td>
							                        <a href="#" class="btnViewOrder" data-url="${path}/user/orderView.do?orderNo=${order.orderNo}">[상세확인]</a>
							                    </td>
							                </tr>
							            </c:forEach>
							        </c:otherwise>
							    </c:choose>
							</table>
                            
                            <p class="paging" style="text-align: center; margin-top: 20px;">
							    <c:if test="${not empty pageGroup}">
							    
							        <c:if test="${pageGroup.start > 1}">
							            <a href="${path}/user/ordered.do?page=${pageGroup.start - 1}">&lt;</a>
							        </c:if>
							    
							        <c:forEach var="i" begin="${pageGroup.start}" end="${pageGroup.end}">
							            <a href="${path}/user/ordered.do?page=${i}" class="${currentPage == i ? 'on' : ''}">[${i}]</a>
							        </c:forEach>
							    
							        <c:if test="${pageGroup.end < lastPageNum}">
							            <a href="${path}/user/ordered.do?page=${pageGroup.end + 1}">&gt;</a>
							        </c:if>
							        
							    </c:if>
							</p>
                        </section>
                    </article>
                </section>
            </div>
            <script src="${path}/js/order.js"></script>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>