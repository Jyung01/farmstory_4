<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_주문상세</title>
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
                    <h2>주문 상세정보</h2>
                    
					<table border="0">
				        <tr>
				            <th>주문번호</th>
				            <td>${order.orderNo}</td>
				            <th>주문일</th>
				            <td>${order.regDate}</td>
				        </tr>
				        <tr>
				            <th>주문상태</th>
				            <td>${order.status}</td>
				            <th>결제방법</th>
				            <td>${order.payment}</td>
				        </tr>
				        <tr>
				            <th>주문자</th>
				            <td>${order.orderer}</td>
				            <th>연락처</th>
				            <td>${order.hp}</td>
				        </tr>
				        <tr>
				            <th>수령인</th>
				            <td>${order.receiver}</td>
				            <th>수령인 연락처</th>
				            <td>${order.receiverHp}</td>
				        </tr>
				        <tr>
				            <th>배송지</th>
				            <td colspan="3">[${order.zip}] ${order.addr1} ${order.addr2}</td>
				        </tr>
				        <tr>
				            <th>배송메모</th>
				            <td colspan="3">${order.memo}</td>
				        </tr>
				    </table>


					<h2>주문 상품</h2>
					<table border="0">
				        <tr>
				            <th>이미지</th>
				            <th>상품명</th>
				            <th>종류</th>
				            <th>수량</th>
				            <th>가격</th>
				            <th>합계</th>
				        </tr>
				
				        <c:forEach var="item" items="${itemList}">
				            <tr>
				                <td>
				                    <img src="${path}/upload/${item.thumb}" class="thumb">
				                </td>
				                <td>${item.prodName}</td>
				                <td>${item.cate}</td>
				                <td>${item.count}</td>
				                <td>${item.price}원</td>
				                <td>${item.price * item.count}원</td>
				            </tr>
				        </c:forEach>
				    </table>
				
				    <h2>결제 정보</h2>
				
				    <table border="0">
				        <tr>
				            <th>사용포인트</th>
				            <td>${order.usedPoint}P</td>
				        </tr>
				        <tr>
				            <th>적립포인트</th>
				            <td>${order.savePoint}P</td>
				        </tr>
				        <tr>
				            <th>배송비</th>
				            <td>${order.delivery}원</td>
				        </tr>
				        <tr>
				            <th>최종 결제금액</th>
				            <td><strong>${order.totalPrice}원</strong></td>
				        </tr>
				    </table>
				
				    <div class="btnArea">
				        <a href="${path}/admin/orderlist.do" class="btn">목록</a>
				    </div>
                    
                </section>
            </main>

            <%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
