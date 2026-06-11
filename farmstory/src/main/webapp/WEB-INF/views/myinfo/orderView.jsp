<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>주문 상세 내역</title>
    <link rel="stylesheet" href="${path}/css/orderView.css" />
</head>
<body>
    <div class="popup-container">
        <h2>주문 상세 확인</h2>
        
        <h3>📌 주문 정보</h3>
        <table class="popup-table">
            <tr>
                <th>주문번호</th>
                <td>${order.orderNo}</td>
                <th>주문일자</th>
                <td>${order.regDate}</td>
            </tr>
            <tr>
                <th>주문자</th>
                <td>${order.orderer}</td>
                <th>결제수단</th>
                <td>${order.payment}</td>
            </tr>
        </table>

        <h3>🍎 주문 상품 목록</h3>
        <table class="popup-table">
            <thead>
                <tr>
                    <th>상품명</th>
                    <th>판매가격</th>
                    <th>수량</th>
                    <th>소계</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${orderItemList}">
                    <tr>
                        <td class="left">${item.prodName}</td>
                        <td><fmt:formatNumber value="${item.price/item.count}" type="number"/>원</td>
                        <td>${item.count}개</td>
                        <td class="bold">
                            <fmt:formatNumber value="${item.price}" type="number"/>원
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h3>💳 결제 및 배송 정보</h3>
        <table class="popup-table">
            <tr>
                <th>배송비</th>
                <td>
                    <c:choose>
                        <c:when test="${order.delivery == 0}">무료 배송</c:when>
                        <c:otherwise><fmt:formatNumber value="${order.delivery}" type="number"/>원</c:otherwise>
                    </c:choose>
                </td>
                <th>주문 상태</th>
                <td class="bold" style="color: #2980b9;">${order.status}</td>
            </tr>
            <tr>
                <th>사용한 포인트</th>
                <td><fmt:formatNumber value="${order.usedPoint}" type="number"/> P</td>
                <th>적립 포인트</th>
                <td><fmt:formatNumber value="${order.savePoint}" type="number"/> P</td>
            </tr>
            <tr>
                <th style="background-color: #e8f4fd;">최종 총 결제금액</th>
                <td colspan="3" class="bold" style="font-size: 16px; text-align: right; padding-right: 20px;">
                    <fmt:formatNumber value="${order.totalPrice}" type="number"/>원
                </td>
            </tr>
        </table>

        <div class="btn-area">
            <button class="btn-close" onclick="window.close();">창 닫기</button>
        </div>
    </div>
</body>
</html>