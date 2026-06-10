<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
                                    <th>판매가격</th>
                                    <th>수량</th>
                                    <th>합계</th>
                                    <th>주문자</th>
                                    <th>주문일</th>
                                    <th>확인</th>
                                </tr>
                                <tr class="empty">
                                    <td colspan="9">상품 구매 내역이 없습니다.</td>
                                </tr>
                                <tr>
                                    <td>1001</td>
                                    <td><img src="${path}/images/myinfo/sample_item1.jpg" class="thumb" /></td>
                                    <td>사과 500g</td>
                                    <td>4,000원</td>
                                    <td>2</td>
                                    <td>11,000원</td>
                                    <td>김유신</td>
                                    <td>2023-01-01 13:06:14</td>
                                    <td><a href="#">[상세확인]</a></td>
                                </tr>
                            </table>
                            <div class="page">
                                <a href="#" class="prev">이전</a>
                                <a href="#" class="num current">1</a>
                                <a href="#" class="num">2</a>
                                <a href="#" class="num">3</a>
                                <a href="#" class="next">다음</a>
                            </div>
                        </section>
                    </article>
                </section>
            </div>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>