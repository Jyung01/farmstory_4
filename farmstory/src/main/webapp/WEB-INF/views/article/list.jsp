<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::텃밭가꾸기</title>
        <link rel="stylesheet" href="../css/crop.css" />
    </head>
    <body>
        <div id="container">
            <jsp:include page="/WEB-INF/views/inc/_header.jsp"></jsp:include>
            <div id="crop">
                <div><img src="${path}/images/sub_top_tit3.png" alt="${cateTitle}" /></div>
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
                                <form action="#">
                                    <input type="text" name="search" placeholder="제목 키워드, 글쓴이 검색" />
                                    <input type="submit" value="검색" />
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
                                <tr>
                                    <td>3</td>
                                    <td><a href="./view.html">텃밭가꾸기 게시물입니다.[3]</a></td>
                                    <td>길동이</td>
                                    <td>20-05-12</td>
                                    <td>12</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><a href="./view.html">텃밭가꾸기 게시물입니다.[3]</a></td>
                                    <td>길동이</td>
                                    <td>20-05-12</td>
                                    <td>12</td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="./view.html">텃밭가꾸기 게시물입니다.[3]</a></td>
                                    <td>길동이</td>
                                    <td>20-05-12</td>
                                    <td>12</td>
                                </tr>
                            </table>

                            <div class="page">
                                <a href="#" class="prev">이전</a>
                                <a href="#" class="num current">1</a>
                                <a href="#" class="num">2</a>
                                <a href="#" class="num">3</a>
                                <a href="#" class="next">다음</a>
                            </div>

                            <a href="${path}/article/write.do?groupName=${groupName}&cate=${cate}" class="btn btnWrite">글쓰기</a>
                        </section>
                    </article>
                </section>
            </div>

            <footer>
                <img src="../images/footer_logo.png" alt="로고" />
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
