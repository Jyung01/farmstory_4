<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::상품주문</title>
        <link rel="stylesheet" href="${path}/css/main.css" />
        
        <style>
        /* 크롬, 사파리, 엣지용 */
        input[type="number"]::-webkit-outer-spin-button,
        input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* 파이어폭스용 */
        input[type="number"] {
            -moz-appearance: textfield;
        }
    </style>
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
                    
                    <article class="order">
                        <nav>
                            <img src="${path}/images/sub_nav_tit_cate2_tit1.png" alt="장보기" />
                            <p>HOME > 장보기 > <em>장보기</em></p>
                        </nav>

                        <p class="sort">
                            <a href="#" class="on">주문상품 전체(${orderList.size()})</a>
                        </p>
                        
                        <table border="0">
                            <thead>
                                <tr>
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
							    <c:if test="${empty orderList}">
							        <tr class="empty">
							            <td colspan="8" style="text-align: center; padding: 20px;">주문할 상품이 선택되지 않았습니다.</td>
							        </tr>
							    </c:if>
							
							    <c:if test="${not empty orderList}">
							        <c:forEach var="item" items="${orderList}">
							            <tr class="order-item-row" data-cartno="${item.cartNo}" data-prodno="${item.prodNo}">
							                <td>
							                    <a href="${path}/market/view.do?prodNo=${item.prodNo}">
							                        <img src="${path}/upload/${item.thumb}" class="thumb" alt="${item.prodName}" />
							                    </a>
							                </td>
							                <td>${item.cate}</td>
							                <td style="text-align: left; padding-left: 15px;">
							                    <a href="${path}/market/view.do?prodNo=${item.prodNo}">${item.prodName}</a>
							                </td>
							                <td class="item-count" data-count="${item.count}">${item.count}개</td>
							                <td class="item-discount" data-discount="${item.discount}">${item.discount}%</td>
							                <td class="item-point" data-point="${item.point * item.count}">
							                    <span class="row-point"><fmt:formatNumber value="${item.point * item.count}" type="number"/></span>P
							                </td>
							                <td class="item-price" data-price="${item.price}">
							                    <span class="row-price"><fmt:formatNumber value="${item.price}" type="number"/></span>원
							                </td>
							                <td class="item-subtotal">
							                    <strong>
							                        <span class="row-subtotal"><fmt:formatNumber value="${(item.price - (item.price * item.discount / 100)) * item.count}" type="number"/></span>
							                    </strong>원
							                </td>
							            </tr>
							        </c:forEach>
							    </c:if>
							</tbody>
							</table>
							
							
							<div class="final">
							    <table border="0">
							        <caption>최종결제정보</caption>
							        <tr>
							            <th>상품수</th>
							            <td><span id="totalCount">0개</span></td>
							        </tr>
							        <tr>
							            <th>상품금액</th>
							            <td><span id="cartTotalPrice">0원</span></td>
							        </tr>
							        <tr>
							            <th>할인금액</th>
							            <td><span id="totalDiscount">0원</span></td>
							        </tr>
							        <tr>
							            <th>포인트사용</th>
							            <td><span id="displayedUsedPoint">0</span>P</td>
							        </tr>
							        <tr>
							            <th>배송비</th>
							            <td class="delivery"><span id="totalDelivery">0원</span></td>
							        </tr>
							        <tr>
							            <th>포인트적립</th>
							            <td><span id="totalSavePoint">0</span></td>
							        </tr>
							        <tr>
							            <th>전체주문금액</th>
							            <td class="total"><span id="finalOrderPrice">0원</span></td>
							        </tr>
							    </table>
							    <input type="button" class="btnOrder" id="btnPaySubmit" value="결제하기" />
							</div>

                        <h3>주문정보 입력</h3>
                        <div class="shipping">
                            <table>
                                <tr>
                                    <td>주문자</td>
                                    <td><input type="text" id="orderer" value="${orderUser.name}" placeholder="주문자 이름" style="padding: 0 8px; height: 26px;" /></td>
                                </tr>
                                <tr>
                                    <td>휴대폰</td>
                                    <td><input type="text" id="ordererHp" value="${orderUser.hp}" placeholder="010-0000-0000" style="padding: 0 8px; height: 26px;" /></td>
                                </tr>
                                <tr>
                                    <td>포인트사용</td>
                                    <td>
                                        <input type="number" id="inputPoint" value="0" min="0" max="${orderUser.point}" style="text-align: right; width: 100px; padding-right: 5px; height: 26px;" />
                                        <button type="button" id="btnApplyPoint" style="display: inline-block; visibility: visible; opacity: 1; color: #fff; background-color: #333; padding: 4px 10px; font-size: 11px; height: 20px; line-height: 1; border: none; vertical-align: middle; cursor: pointer;" >사용하기</button>
                                        <p class="point">사용가능 <span id="availablePoint"><fmt:formatNumber value="${orderUser.point}" type="number"/></span>P</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>받는분</td>
                                    <td><input type="text" id="receiver" value="${orderUser.name}" placeholder="받는 사람 이름" required style="padding: 0 8px; height: 26px;" /></td>
                                </tr>
                                <tr>
                                    <td>연락처</td>
                                    <td><input type="text" id="receiverHp" value="${orderUser.hp}" placeholder="010-0000-0000" required style="padding: 0 8px; height: 26px;" /></td>
                                </tr>
                                <tr>
								    <td>배송주소</td>
								    <td>
									    <input type="text" id="zip" value="${orderUser.zip}" placeholder="우편번호" style="margin-bottom: 4px;" />
									    
									    <button type="button" id="btnZip" style="display: inline-block; visibility: visible; opacity: 1; color: #fff; background-color: #333; padding: 4px 10px; font-size: 11px; height: 20px; line-height: 1; border: none; vertical-align: middle; cursor: pointer;">우편번호 검색</button>
									    
									    <br>
									    <input type="text" id="addr1" value="${orderUser.addr1}" placeholder="기본주소 검색" style="width: 80%; margin-bottom: 4px;" /><br>
									    <input type="text" id="addr2" value="${orderUser.addr2}" placeholder="상세주소 입력" style="width: 80%;" />
									</td>
								</tr>
                                <tr>
                                    <td>결제방법</td>
                                    <td>
                                        <label><input type="radio" name="payment_view" value="계좌이체" checked />계좌이체</label>
                                        <label><input type="radio" name="payment_view" value="신용카드" />신용카드</label>
                                        <label><input type="radio" name="payment_view" value="체크카드" />체크카드</label>
                                        <label><input type="radio" name="payment_view" value="휴대폰" />휴대폰</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>기타(배송메모)</td>
                                    <td>
                                        <textarea id="memo" placeholder="배송 메시지를 입력해주세요."></textarea>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                    </article>
                </section>
            </div>
            
            <form id="formOrderFinal" action="${path}/market/ordersResult.do" method="POST" style="display:none;"></form>
            <input type="hidden" class="contextPath" value="${path}"/>
            <input type="hidden" id="hiddenUserid" value="${orderUser.userid}"/>
            
            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script src="${path}/js/daumPostcode.js"></script>
            
            <script src="${path}/js/order.js"></script>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>