<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_상품목록</title>
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
                                    <a href="/farmstory/admin/prodList.do" class="active">L <span>상품목록</span></a>
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

                <section class="admin_main proList">
                    <h2>상품목록</h2>

					<form action="/farmstory/admin/prodDelete.do" method="post">
	                    <section>
	                        <table>
	                            <tr>
	                                <th><input type="checkbox" /></th>
	                                <th>사진</th>
	                                <th>상품번호</th>
	                                <th>상품명</th>
	                                <th>구분</th>
	                                <th>가격</th>
	                                <th>재고</th>
	                                <th>등록일</th>
	                            </tr>
	                            
	                            <c:forEach var="product" items="${dtoList}">
		                            <tr>
		                                <td><input type="checkbox" name="prodNo" value="${product.prodNo}" /></td>
		                                <td><img src="${path}/upload/${product.thumb}" alt="item1" /></td>
		                                <td>${product.prodNo}</td>
		                                <td>${product.prodName}</td>
		                                <td>${product.cate}</td>
		                                <td>${product.price}원</td>
		                                <td>${product.stock}</td>
		                                <td>${product.regDate}</td>
		                            </tr>
	                            </c:forEach>
	                        </table>
	                        
	                        <c:if test="${empty dtoList}">
							    <p style="text-align:center; padding:20px 0;">상품 목록이 없습니다.</p>
							</c:if>
	                    </section>
	
	                    <div class="buttons">
	                    	<input type="submit" value="선택삭제">
	                        <a href="/farmstory/admin/register.do" class="add_product"><p>상품등록</p></a>
	                    </div>
                    </form>

                    <!-- 페이지네이션 -->
                    <div class="pagination_wrapper">
                        <ul class="pagination">
                        	<c:if test="${pageGroupDTO.start > 1}">
		                    	<a href="/farmstory/admin/prodList.do?page=${pageGroupDTO.start - 1}"><</a>
		                    </c:if>
		                    
                        	<c:forEach var="i" begin="${pageGroupDTO.start}" end="${pageGroupDTO.end}">
                        		<li><a href="/farmstory/admin/prodList.do?page=${i}" class="${currentPage == i ? 'page_active' : ''}">[${i}]</a></li>
                        	</c:forEach>
                        
                        	<c:if test="${pageGroupDTO.end < lastPage}">
		                    	<a href="/farmstory/admin/prodList.do?page=${pageGroupDTO.end + 1}">></a>
		                    </c:if>
                        </ul>
                    </div>
                </section>
            </main>
			<%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
