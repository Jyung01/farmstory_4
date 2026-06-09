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
                <div><img src="${path}/images/sub_top_tit5.png" alt="${cateTitle}" /></div>
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

                        <section class="write">
                            <h1>글쓰기</h1>
                            <form action="${path}/article/write.do" method="post" enctype="multipart/form-data">
                            	<input type="hidden" name="groupName" value="${groupName}">
    							<input type="hidden" name="cate" value="${cate}">
    								
                                <table border="0">
                                    <tr>
                                        <th>제목</th>
                                        <td><input type="text" name="title" placeholder="제목을 입력하세요." /></td>
                                    </tr>
                                    <tr>
                                        <th>내용</th>
                                        <td>
                                            <textarea name="content"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>파일</th>
                                        <td>
                                            <p>최대 2개 파일 첨부 가능, 각 파일당 최대 10MB까지 가능</p>
                                            <input type="file" name="file" />
                                            <input type="file" name="file" />
                                        </td>
                                    </tr>
                                </table>

                                <div>
                                    <a href="./list.html" class="btn btnCancel">취소</a>
                                    <input type="submit" value="작성완료" class="btn btnComplete" />
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
