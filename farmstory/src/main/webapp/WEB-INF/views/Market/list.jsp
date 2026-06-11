<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::상품목록</title>
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
                    <article class="list">
                        <nav>
                            <img src="${path}/images/sub_nav_tit_cate2_tit1.png" alt="장보기" />
                            <p>HOME > 장보기 > <em>장보기</em></p>
                        </nav>

                        <p class="sort">
						    <a href="${path}/market/list.do" class="${empty param.cate ? 'on' : ''}">전체(${totalCount}) |</a>
						    <a href="${path}/market/list.do?cate=과일" class="${param.cate eq '과일' ? 'on' : ''}">과일 |</a>
						    <a href="${path}/market/list.do?cate=야채" class="${param.cate eq '야채' ? 'on' : ''}">야채 |</a>
						    <a href="${path}/market/list.do?cate=곡류" class="${param.cate eq '곡류' ? 'on' : ''}">곡류</a>
						</p>
                        <table border="0">
                            <thead>
                                <tr>
                                    <th>이미지</th>
                                    <th>종류</th>
                                    <th>상품명</th>
                                    <th>할인</th>
                                    <th>포인트</th>
                                    <th>판매가격</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty products}">
                                        <c:forEach var="product" items="${products}">
                                            <tr>
                                                <td>
                                                    <a href="${path}/market/view.do?prodNo=${product.prodNo}">
                                                        <img src="${path}/upload/${product.thumb}" class="thumbnail" alt="${product.prodName}" />
                                                    </a>
                                                </td>
                                                <td class="type">${product.cate}</td>
                                                <td class="title">
                                                    <a href="${path}/market/view.do?prodNo=${product.prodNo}">${product.prodName}</a>
                                                </td>
                                                <td class="discount">${product.discount}%</td>
                                                <td class="point">
                                                    <fmt:parseNumber value="${product.price / 100}" integerOnly="true"/>P
                                                </td> 
                                                <td class="price">
                                                    <strong>
                                                        <fmt:formatNumber value="${product.price - (product.price * product.discount / 100)}" type="number"/>
                                                    </strong>
                                                    <br>
                                                    <del><fmt:formatNumber value="${product.price}" type="number"/></del>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="6" style="text-align:center; padding:30px; color:#666;">
                                                등록된 상품이 존재하지 않습니다.
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>

                        <p class="paging">
                            <c:if test="${not empty pageGroup}">
                            
                                <c:if test="${pageGroup.start > 1}">
                                    <a href="${path}/market/list.do?page=${pageGroup.start - 1}&cate=${param.cate}">&lt;</a>
                                </c:if>
                            
                                <c:forEach var="i" begin="${pageGroup.start}" end="${pageGroup.end}">
                                    <a href="${path}/market/list.do?page=${i}&cate=${param.cate}" class="${currentPage == i ? 'on' : ''}">[${i}]</a>
                                </c:forEach>
                            
                                <c:if test="${pageGroup.end < lastPageNum}">
                                    <a href="${path}/market/list.do?page=${pageGroup.end + 1}&cate=${param.cate}">&gt;</a>
                                </c:if>
                                
                            </c:if>
                        </p>
                    </article>
                </section>
            </div>

            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>