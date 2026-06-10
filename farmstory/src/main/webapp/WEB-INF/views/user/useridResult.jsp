<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::아이디찾기결과</title>
        <link rel="stylesheet" href="${path}/css/useridResult.css" />
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
            <div id="find">
                <section class="useridResult">
                    <h2 class="tit">아이디 찾기 결과</h2>
                    <table border="0">
                        <tr>
                            <td>아이디</td>
                            <td>${userid}</td>
                        </tr>
                    </table>
                    <p>고객님의 정보와 일치하는 아이디 입니다.</p>
                    <div>
                        <a href="${path}/user/login.do" class="btn btnCancel">로그인</a>
                        <a href="${path}/user/password.do" class="btn btnNext">비밀번호 찾기</a>
                    </div>
                </section>
            </div>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>