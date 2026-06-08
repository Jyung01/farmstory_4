<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리::장바구니</title>
    <link rel="stylesheet" href="${path}/css/main.css" />
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/views/inc/_header.jsp" %>

        <div id="sub">
            <div><img src="${path}/images/sub_top_tit2.png" alt="MARKET"></div>
            <section class="market">
                <aside>
                    <img src="${path}/images/sub_aside_cate2_tit.png" alt="장보기"/>
                    <ul class="lnb">
                        <li class="on"><a href="${path}/market/list.do">장보기</a></li>
                    </ul>
                </aside>
                
                <article class="cart">
                    <nav>
                        <img src="${path}/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                        <p>HOME > 장보기 > <em>장보기</em></p>
                    </nav>

                    <p class="sort">
                        <a href="#" class="on">장바구니 전체(${cartList.size()})</a>
                    </p>
                    
                    <table border="0">
                        <thead>
                            <tr>
                                <th>
                                    <input type="checkbox" name="all" id="chkAll">
                                </th>
                                <th>이미지</th>
                                <th>종류</th>
                                <th>상품명</th>
                                <th>수량</th>
                                <th>할인</th>
                                <th>포인트</th>
                                <th>가격</th>
                                <th>소계</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty cartList}">
                                <tr class="empty">
                                    <td colspan="9">장바구니에 상품이 없습니다.</td>
                                </tr>
                            </c:if>
                            
                            <c:if test="${not empty cartList}">
                                <c:forEach var="cart" items="${cartList}">
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="cartChk" value="${cart.cartNo}" class="cartChk">
                                        </td>
                                        <td>
                                            <a href="${path}/market/view.do?prodNo=${cart.prodNo}">
                                                <img src="${path}${cart.thumb}" class="thumb" alt="${cart.prodName}">
                                            </a>
                                        </td>
                                        <td>${cart.cate}</td>
                                        <td>
                                            <a href="${path}/market/view.do?prodNo=${cart.prodNo}">${cart.prodName}</a>
                                        </td>
                                        <td>${cart.count}</td>
                                        <td>${cart.discount}%</td>
                                        <td><fmt:formatNumber value="${cart.point * cart.count}" type="number"/>P</td>
                                        <td><fmt:formatNumber value="${cart.price}" type="number"/>원</td>
                                        <td>
                                            <strong>
                                                <fmt:formatNumber value="${(cart.price - (cart.price * cart.discount / 100)) * cart.count}" type="number"/>
                                            </strong>원
                                        </td>    
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>                        
                    </table>
                    <input type="button" id="btnDeleteSelected" value="선택삭제">

                    <div class="info">                        
                        <table border="0">
                            <caption>전체합계</caption>                            
                            <tr>
                                <th>상품수</th>
                                <td id="totalCount">0</td>
                            </tr>
                            <tr>
                                <th>상품금액</th>
                                <td id="totalPrice">0원</td>
                            </tr>
                            <tr>
                                <th>할인금액</th>
                                <td id="totalDiscount">0원</td>
                            </tr>
                            <tr>
                                <th>배송비</th>
                                <td id="totalDelivery" class="delivery">0원</td>
                            </tr>
                            <tr>
                                <th>포인트</th>
                                <td id="totalPoint">0원</td>
                            </tr>
                            <tr>
                                <th>전체주문금액</th>
                                <td id="finalOrderPrice" class="total">0원</td>
                            </tr>
                        </table>                        
                        <input type="submit" class="btnOrder" value="주문하기">
                    </div>
                    </article>
            </section>
        </div>
        
         <script src="${path}/js/cart.js"></script>
        <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
    </div>    
</body>
</html>