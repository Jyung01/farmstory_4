<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리::장바구니</title>
    <link rel="stylesheet" href="${path}/css/cart.css"/>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
        <div id="myinfo">
            <div><img src="${path}/images/myinfo/myinfo_top_tit.png" alt="MY INFO"></div>
            <section>
                <aside>
                    <img src="${path}/images/myinfo/myinfo_menu_tit.png" alt="나의정보"/>
                    <ul class="menu">
                        <li class="on"><a href="${path}/user/cart.do">장바구니</a></li>
                        <li><a href="${path}/user/ordered.do">주문내역</a></li>
                        <li><a href="${path}/user/myinfo.do">정보수정</a></li>
                    </ul>
                </aside>
                <article>
                    <nav>
                        <img src="${path}/images/myinfo/myinfo_nav_tit1.png" alt="장바구니"/>
                        <p>HOME > 나의정보 > <em>장바구니</em></p>
                    </nav>
                    <section class="cart">
                        <p class="sort">
                            <a href="#" class="on">장바구니 전체(10)</a>
                        </p>
                        <table border="0">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" name="all"></th>
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
                                <tr class="empty">
                                    <td colspan="9">장바구니에 상품이 없습니다.</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" name=""></td>
                                    <td><a href="#"><img src="${path}/images/market_item1.jpg" class="thumb" alt="사과 500g"></a></td>
                                    <td>과일</td>
                                    <td><a href="#">사과 500g</a></td>
                                    <td>1</td>
                                    <td>10%</td>
                                    <td>40P</td>
                                    <td>4,000</td>
                                    <td><strong>3,600</strong>원</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" name=""></td>
                                    <td><a href="#"><img src="${path}/images/market_item1.jpg" class="thumb" alt="사과 500g"></a></td>
                                    <td>과일</td>
                                    <td><a href="#">사과 500g</a></td>
                                    <td>1</td>
                                    <td>10%</td>
                                    <td>40P</td>
                                    <td>4,000</td>
                                    <td><strong>3,600</strong>원</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" name=""></td>
                                    <td><a href="#"><img src="${path}/images/market_item1.jpg" class="thumb" alt="사과 500g"></a></td>
                                    <td>과일</td>
                                    <td><a href="#">사과 500g</a></td>
                                    <td>1</td>
                                    <td>10%</td>
                                    <td>40P</td>
                                    <td>4,000</td>
                                    <td><strong>3,600</strong>원</td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="button" name="del" value="선택삭제">
                        <div class="info">
                            <table border="0">
                                <caption>전체합계</caption>
                                <tr><th>상품수</th><td>1</td></tr>
                                <tr><th>상품금액</th><td>27,000</td></tr>
                                <tr><th>할인금액</th><td>5,0000원</td></tr>
                                <tr><th>배송비</th><td class="delivery">5,0000원</td></tr>
                                <tr><th>포인트</th><td>4,000원</td></tr>
                                <tr><th>전체주문금액</th><td class="total">22,000</td></tr>
                            </table>
                            <input type="submit" class="btnOrder" value="주문하기">
                        </div>
                    </section>
                </article>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
    </div>
</body>
</html>