<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::상품상세</title>
        <link rel="stylesheet" href="${path}/css/main.css" />
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_header.jsp" %>

            <div id="sub">
                <div><img src="${path}/images/sub_top_tit2.png" alt="MARKET" /></div>
                <section class="market">
                    <aside>
                        <img src="${path}/images/sub_aside_cate2_tit.png" alt="장보기" />
                        <ul class="lnb">
                            <li class="on"><a href="${path}/market/list.do">장보기</a></li>
                        </ul>
                    </aside>
                    
                    <article class="view">
                        <nav>
                            <img src="${path}/images/sub_nav_tit_cate2_tit1.png" alt="장보기" />
                            <p>HOME > 장보기 > <em>장보기</em></p>
                        </nav>

                        <h3>기본정보</h3>
                        <div class="basic">
                            <img src="${path}${product.infoImg}" alt="${product.prodName}" class="thumbnail" />

                            <table border="0">
                                <tr>
                                    <td>상품명</td>
                                    <td><strong>${product.prodName}</strong></td>
                                </tr>
                                <tr>
                                    <td>상품코드</td>
                                    <td>${product.prodNo}</td>
                                </tr>
                                <tr>
                                    <td>배송비</td>
                                    <td>
                                        <span><fmt:formatNumber value="${product.delivery}" type="number"/></span>원
                                        <c:choose>
                                            <c:when test="${product.delivery == 0}">
                                                <em>무료배송 상품입니다.</em>
                                            </c:when>
                                            <c:otherwise>
                                                <em>3만원 이상 무료배송</em>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>판매가격</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${product.discount > 0}">
                                                <del><fmt:formatNumber value="${product.price}" type="number"/>원</del> 
                                                <span class="discount" style="color:red; font-weight:bold;">[${product.discount}% 할인]</span><br/>
                                                <strong style="font-size:1.2em; color:#ff4500;">
                                                    <fmt:formatNumber value="${product.price - (product.price * product.discount / 100)}" type="number"/>원
                                                </strong>
                                            </c:when>
                                            <c:otherwise>
                                                <strong><fmt:formatNumber value="${product.price}" type="number"/>원</strong>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>구매수량</td>
                                    <td>
                                        <input type="number" name="count" id="productCount" min="1" value="1" 
                                               data-price="${product.price - (product.price * product.discount / 100)}" />
                                        <input type="hidden" id="productStock" value="${product.stock}" />
                                        <input type="hidden" id="productNo" value="${product.prodNo}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>합계</td>
                                    <td class="total">
                                        <strong id="totalPrice">
                                            <fmt:formatNumber value="${product.price - (product.price * product.discount / 100)}" type="number"/>원
                                        </strong>
                                    </td>
                                </tr>
                            </table>
							
							<input type="hidden" id="loginCheck" value="${empty sessionScope.sessUser}">
							<input type="hidden" id="Path" value="${path}">
							
                            <div style="float: right; margin-top: 10px">
							    <a href="#" id="btnCart" class="btn btnCart">장바구니</a>
							    <a href="#" id="btnOrder" class="btn btnOrder">바로구매</a>
							</div>
                        </div>

                        <h3>상품설명</h3>
                        <div class="detail">
                            <img src="${path}${product.detailImg}" alt="상품 상세 설명" />
                        </div>

                        <h3>배송정보</h3>
                        <div class="delivery">
                            <p>입금하신 이후 택배송장번호는 SMS(문자서비스)를 통해 고객님께 안내해드립니다.</p>
                        </div>

                        <h3>교환/반품</h3>
                        <div class="exchange">
                            <table border="0">
                                <tr>
                                    <td>교환 반품이 가능한 경우</td>
                                    <td>
                                        <ul>
                                            <li>팜스토리 상품에 하자가 있거나 불량인 경우</li>
                                            <li>채소, 과일, 양곡등의 식품은 만1일 이내</li>
                                            <li>기타 상품은 수령일로부터 영업일 기준 일주일 이내</li>
                                            <li>받으신 상품이 표시사항과 다른 경우에는 받으신 날로부터 일주일 이내</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>교환 반품이 불가능한 경우</td>
                                    <td>
                                        <ul>
                                            <li>신선 식품의 경우 단순히 마음에 들지 않는 경우</li>
                                            <li>단순 변심으로 상품이 가치가 훼손돼서 판매가 어려운 경우</li>
                                        </ul>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </article>
                </section>
            </div>
			<script src="${path}/js/market.js"></script>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>