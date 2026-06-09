<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::${cateTitle}</title>
        <link rel="stylesheet" href="${path}/css/crop.css" />
    </head>
    <body>
        <div id="container">
        	<!-- header import -->
            <jsp:include page="/WEB-INF/views/inc/_header.jsp"></jsp:include>
            <div id="crop">
                <div><img src="${path}/images/sub_top_tit3.png" alt="${cateTitle}" /></div>
                <!-- groupName에 따라 aside 다르게 출력 -->
                <section class="${groupName eq 'crop' ? 'croptalk' : groupName}">
                    <c:choose>
                    	<c:when test="${groupName eq 'crop'}">
                    		<jsp:include page="/WEB-INF/views/inc/_crop_aside.jsp"></jsp:include>
                    	</c:when>
                    	<c:when test="${groupName eq 'community'}">
                    		<jsp:include page="/WEB-INF/views/inc/_community_aside.jsp"></jsp:include>
                    	</c:when>
                    </c:choose>
                    <article id="board">
                        <nav>
                            <img src="${path}/images/${navImage}" alt="${cateTitle}" />
                            <p>HOME > ${groupTitle} > <em>${cateTitle}</em></p>
                        </nav>

                        <section class="list">
                            <nav>
                                <form action="${path}/article/list.do" method="get">

								    <input type="hidden" name="groupName" value="${groupName}">
								    <input type="hidden" name="cate" value="${cate}">
								
								    <select name="searchType">
								       <option value="title" ${searchType eq 'title' ? 'selected' : ''}>제목</option>
								        <option value="content" ${searchType eq 'content' ? 'selected' : ''}>내용</option>
								        <option value="writer" ${searchType eq 'writer' ? 'selected' : ''}>글쓴이</option>
								    </select>
								    <input type="text" name="keyword" value="${keyword}" placeholder="검색어 입력">
								    <input type="submit"value="검색">
								
								</form>
                            </nav>

                            <h1>글목록</h1>

                            <table border="0">
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>글쓴이</th>
                                    <th>날짜</th>
                                    <th>조회</th>
                                </tr>
                                <c:set var="num" value="${pageStartNum}" />
                                <c:if test="${empty dtoList}">
								    <tr>
								        <td colspan="5">검색 결과가 없습니다.</td>
								    </tr>
								</c:if>
                                <c:forEach var="dto" items="${dtoList}">	
	                                <tr>
	                                    <td>${num}</td>
	                                    <td><a href="${path}/article/view.do?ano=${dto.ano}&groupName=${groupName}&cate=${cate}&page=${currentPage}">${dto.title}
		                                    <c:if test="${dto.commentCount > 0}">
		                                    	[${dto.commentCount}]
		                                    </c:if>
	                                    </a></td>
	                                    <td>${dto.nick}</td>
	                                    <td>${dto.wdate}</td>
	                                    <td>${dto.hit}</td>
	                                </tr>
	                                
	                                <c:set var="num" value="${num - 1}" />
                                </c:forEach>
                            </table>
							<!-- 페이지네이션 -->
                            <div class="page">
                                <c:if test="${pageGroup.start > 1}">
							        <a href="${path}/article/list.do?groupName=${groupName}&cate=${cate}&page=${pageGroup.start - 1}&searchType=${searchType}&keyword=${keyword}" class="prev">이전</a>
							    </c:if>
							    <c:forEach var="i" begin="${pageGroup.start}" end="${pageGroup.end}">
							        <a href="${path}/article/list.do?groupName=${groupName}&cate=${cate}&page=${i}&searchType=${searchType}&keyword=${keyword}"
							           class="num ${currentPage eq i ? 'current' : ''}">
							            ${i}
							        </a>
							    </c:forEach>
							    <c:if test="${pageGroup.end < lastPageNum}">
							        <a href="${path}/article/list.do?groupName=${groupName}&cate=${cate}&page=${pageGroup.end + 1}&searchType=${searchType}&keyword=${keyword}" class="next">다음</a>
							    </c:if>
                            </div>

                            <c:if test="${not empty sessUser}">
							    <a href="${path}/article/write.do?groupName=${groupName}&cate=${cate}" class="btn btnWrite">글쓰기</a>
							</c:if>
                        </section>
                    </article>
                </section>
            </div>
			<!-- footer import -->
            <jsp:include page="/WEB-INF/views/inc/_footer.jsp"></jsp:include>
        </div>
    </body>
</html>
