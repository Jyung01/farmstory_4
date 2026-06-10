<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::아이디찾기</title>
        <link rel="stylesheet" href="${path}/css/userid.css" />
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
            <div id="find">
                <section class="userid">
                    <form action="${path}/user/userid.do" method="post">
                        <h2>아이디 찾기</h2>
                        <table border="0">
                            <tr>
                                <td>이름</td>
                                <td><input type="text" name="name" placeholder="이름 입력" /></td>
                            </tr>
                            <tr>
                                <td>이메일</td>
                                <td><input type="email" name="email" placeholder="이메일 입력" /></td>
                            </tr>
                        </table>
                        <c:if test="${not empty error}">
                            <p style="color:red;">${error}</p>
                        </c:if>
                        <div style="text-align: right;">
                            <a href="${path}/user/login.do" class="btn btnCancel">취소</a>
                            <input type="submit" value="다음" class="btn btnNext" />
                        </div>
                    </form>
                </section>
            </div>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    </body>
</html>