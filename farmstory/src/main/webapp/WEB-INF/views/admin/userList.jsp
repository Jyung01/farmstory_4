<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_회원목록</title>
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
                                    <a href="/farmstory/admin/orderList.do">L <span>주문목록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>회원관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/userList.do" class="active">L <span>회원목록</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </aside>

                <section class="admin_main userList">
                    <h2>회원목록</h2>

					<form action="/farmstory/admin/userDelete.do" method="post">
	                    <section>
	                        <table>
	                            <tr>
	                                <th><input type="checkbox" /></th>
	                                <th>아이디</th>
	                                <th>이름</th>
	                                <th>별명</th>
	                                <th>이메일</th>
	                                <th>휴대폰</th>
	                                <th>등급</th>
	                                <th>가입일</th>
	                                <th>확인</th>
	                            </tr>
	                            
	                            <c:forEach var="user" items="${dtoList}">
	                            	<tr>
		                                <td><input type="checkbox" name="userid" value="${user.userid}"/></td>
		                                <td>${user.userid}</td>
		                                <td>${user.name}</td>
		                                <td>${user.nick}</td>
		                                <td>${user.email}</td>
		                                <td>${user.hp}</td>
		                                <c:choose>
										    <c:when test="${user.role eq 'admin'}">
										        <td>1</td>
										    </c:when>
										    <c:when test="${user.role eq 'member'}">
										        <td>2</td>
										    </c:when>
										    <c:otherwise>
										        <td>3</td>
										    </c:otherwise>
										</c:choose>
										
		                                <td>${user.regDate}</td>
		                                <td><a href="#">[상세확인]</a></td>
		                            </tr>
	                            
	                            </c:forEach>
	                        </table>
	                        
	                        <c:if test="${empty dtoList}">
							    <p style="text-align:center; padding:20px 0;">회원 목록이 없습니다.</p>
							</c:if>
							
	                    </section>
                    	
                    </form>

                    <!-- 페이지네이션 -->
                    <div class="pagination_wrapper">
                        <ul class="pagination">
                        	<c:if test="${pageGroupDTO.start > 1}">
		                    	<a href="/farmstory/admin/userList.do?page=${pageGroupDTO.start - 1}"><</a>
		                    </c:if>
		                    
                        	<c:forEach var="i" begin="${pageGroupDTO.start}" end="${pageGroupDTO.end}">
                        		<li><a href="/farmstory/admin/userList.do?page=${i}" class="${currentPage == i ? 'page_active' : ''}">[${i}]</a></li>
                        	</c:forEach>
                        
                        	<c:if test="${pageGroupDTO.end < lastPage}">
		                    	<a href="/farmstory/admin/userList.do?page=${pageGroupDTO.end + 1}">></a>
		                    </c:if>
                        </ul>
                    </div>
                </section>
            </main>

            <%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
