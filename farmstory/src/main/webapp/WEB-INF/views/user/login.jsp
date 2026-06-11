<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리::로그인</title>
    <link rel="stylesheet" href="${path}/css/login.css"/>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
        <div id="user">
            <section class="login">
            	
                <form action="${path}/user/login.do" method="post">
                    <table border="0">
                        <tr>
                            <td><img src="${path}/images/user/login_ico_id.png" alt="아이디"/></td>
                            <td><input type="text" name="uid" placeholder="아이디 입력"/></td>
                        </tr>
                        <tr>
                            <td><img src="${path}/images/user/login_ico_pw.png" alt="비밀번호"/></td>
                            <td><input type="password" name="pass" placeholder="비밀번호 입력"/></td>
                        </tr>
                        <tr>
                        	<td colspan="2">
                        		<c:if test="${not empty error }">
            					<p style="color:red; text-align:center;">${error}</p>
            					</c:if>
                        	</td>
                        </tr>
                    </table>
                    <input type="submit" value="로그인" class="btnLogin"/>
                </form>
                <div>
                    <h3>회원 로그인 안내</h3>
                    <p>아직 회원이 아니시면 회원으로 가입하세요.</p>
                    <div style="text-align: right;">
                        <a href="${path}/user/userid.do">아이디찾기 |</a>
                        <a href="${path}/user/password.do">비밀번호찾기 |</a>
                        <a href="${path}/user/terms.do">회원가입</a>
                    </div>
                </div>
            </section>
        </div>
        <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
    </div>
</body>
</html>