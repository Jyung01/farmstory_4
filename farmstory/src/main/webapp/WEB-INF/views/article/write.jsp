<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::오늘의식단</title>
        <link rel="stylesheet" href="../css/crop.css" />
    </head>
    <body>
        <div id="container">
            <jsp:include page="/WEB-INF/views/inc/_header.jsp"></jsp:include>
            <div id="crop">
                <div><img src="${path}/images/sub_top_tit5.png" alt="${cateTitle}" /></div>
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
                                            <input type="file" name="file1" />
                                            <input type="file" name="file2" />
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

            <footer>
                <img src="${path}/images/footer_logo.png" alt="로고" />
                <p>
                    (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-팜스토리구-123호 / 벤처기업확인
                    서울지방중소기업청 제 012345678-9-01234호<br />
                    등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동<br />
                    대표 : 홍길동 / 이메일 : email@mail.mail / 전화 : 01) 234-5678 / 경기도 성남시 잘한다구 신난다동
                    345<br />
                    <em>Copyright(C)홍길동 All rights reserved.</em>
                </p>
            </footer>
        </div>
    </body>
</html>
