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
                        <section class="modify">
	                        <h1>글수정</h1>
	                        <c:if test="${not empty msg}">
							    <p style="color:red;">${msg}</p>
							</c:if>
	                        <form action="${path}/article/modify.do" method="post" enctype="multipart/form-data">

							    <input type="hidden" name="ano" value="${dto.ano}">
							    <input type="hidden" name="groupName" value="${groupName}">
							    <input type="hidden" name="cate" value="${cate}">
							    <input type="hidden" name="page" value="${page}">
							
							    <table border="0">
							        <tr>
							            <th>제목</th>
							            <td><input type="text" name="title" value="${dto.title}"></td>
							        </tr>	
							        <tr>
							            <th>내용</th>
							            <td><textarea name="content">${dto.content}</textarea></td>
							        </tr>
							        <tr>
							            <th>기존파일</th>
							            <td>
							                <c:if test="${empty fileList}">
							                    첨부파일이 없습니다.
							                </c:if>
							                <c:forEach var="file" items="${fileList}">
											    <p class="fileItem">
											        <span>${file.ofname}</span>
											
											        <input type="checkbox" id="deleteFile${file.fno}" name="deleteFile" value="${file.fno}">
											        <label for="deleteFile${file.fno}">삭제</label>
											    </p>
											</c:forEach>
							            </td>
							        </tr>
							
							        <tr>
							            <th>새 파일</th>
							            <td>
							                <p>최대 2개 파일 첨부 가능</p>
							                <input type="file" name="file">
							                <input type="file" name="file">
							            </td>
							        </tr>
							    </table>
							
							    <div>
							        <a href="${path}/article/view.do?ano=${dto.ano}&groupName=${groupName}&cate=${cate}&page=${page}" class="btn btnCancel">취소</a>
							        <input type="submit" value="수정완료" class="btn btnComplete">
							    </div>
							</form>
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
