<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::${cateTitle}</title>
        <link rel="stylesheet" href="${path}/css/crop.css" />
        <script>
			const parent = '${dto.ano}';
			const groupName = '${groupName}';
			const cate = '${cate}';
			const page = '${page}';
			const path = '${path}';
		</script>
		<script src="${path}/js/comment.js" defer></script>
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
                        <section class="view">
						    <h1>글보기</h1>
						    <table border="0">
						        <tr>
						            <th>제목</th>
						            <td>
						                <input type="text" name="title" value="${dto.title}" readonly />
						            </td>
						        </tr>
						
						        <tr>
						            <th>파일</th>
						            <td>
						                <c:if test="${empty fileList}">
						                    첨부파일이 없습니다.
						                </c:if>
						                <c:forEach var="file" items="${fileList}">
						                    <p>
						                        <a href="${path}/article/download.do?fno=${file.fno}">
						                            ${file.ofname}
						                        </a>
						                        &nbsp;<span>${file.download}</span>회 다운로드
						                    </p>
						                </c:forEach>
						            </td>
						        </tr>
						
						        <tr>
						            <th>내용</th>
						            <td>
						                <textarea name="content" readonly>${dto.content}</textarea>
						            </td>
						        </tr>
						    </table>
						
						    <div>
								<c:if test="${not empty sessUser and (sessUser.userid eq dto.writer or sessUser.role eq 'admin')}">						
							        <a href="${path}/article/modify.do?ano=${dto.ano}&groupName=${groupName}&cate=${cate}&page=${page}" class="btn btnModify">수정</a>
							        <a href="${path}/article/delete.do?ano=${dto.ano}&groupName=${groupName}&cate=${cate}&page=${page}" class="btn btnRemove" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
								</c:if>
						        <a href="${path}/article/list.do?groupName=${groupName}&cate=${cate}&page=${page}" class="btn btnList">목록</a>
						    </div>
						
						    <section class="commentList">
						        <h3>댓글목록</h3>
						
						        <c:if test="${empty commentList}">
						            <p class="empty">등록된 댓글이 없습니다.</p>
						        </c:if>
						
						        <c:forEach var="comment" items="${commentList}">
						            <article>
						            	<input type="hidden" class="cno" value="${comment.cno}">
						                <span class="date">${comment.wdate}</span>
						                <span class="nick">${comment.nick}</span>
						                <p class="content">${comment.content}</p>
						
						                <div>
						                    <c:if test="${not empty sessUser and (sessUser.userid eq comment.writer or sessUser.role eq 'admin')}">
						                        <a href="#" class="modify">수정</a> |
						                        <a href="${path}/comment/delete.do?cno=${comment.cno}&parent=${dto.ano}&groupName=${groupName}&cate=${cate}&page=${page}" 
						                        class="remove"
						                        onclick="return confirm('댓글을 삭제하시겠습니까?');">삭제</a>
						                    </c:if>
						                </div>
						            </article>
						        </c:forEach>
						    </section>
							<!-- 로그인 세션 검사 후 댓글입력 창 출력 -->
							<c:choose>
								<c:when test="${not empty sessUser}">
								    <section class="commentForm">
								        <h3>댓글쓰기</h3>
								
								        <form action="${path}/comment/write.do" method="post">
								            <input type="hidden" name="parent" value="${dto.ano}" />
								            <input type="hidden" name="groupName" value="${groupName}" />
								            <input type="hidden" name="cate" value="${cate}" />
								            <input type="hidden" name="page" value="${page}" />
								
								            <textarea name="content" placeholder="댓글내용 입력"></textarea>
								
								            <div>
								                <a href="${path}/article/view.do?ano=${dto.ano}&groupName=${groupName}&cate=${cate}&page=${page}" class="btn btnCancel">취소</a>
								                <input type="submit" value="작성완료" class="btn btnComplete" />
								            </div>
								        </form>
								    </section>
							    </c:when>
							    <c:otherwise>
							        <section class="commentForm">
							            <h3>댓글쓰기</h3>
							            <p>댓글 작성은 로그인 후 이용 가능합니다.</p>
							        </section>
							    </c:otherwise>
						    </c:choose>
						</section>
                        <!-- 내용 끝 -->
                    </article>
                </section>
            </div>

            <!-- footer import -->
            <jsp:include page="/WEB-INF/views/inc/_footer.jsp"></jsp:include>
        </div>
    </body>
</html>
