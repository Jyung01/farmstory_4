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
        <header>
            <a href="${path}/index.jsp" class="logo"><img src="${path}/images/logo.png" alt="로고"/></a>
            <p>
                <a href="${path}/index.jsp">HOME</a>|
                <a href="${path}/user/login.do">로그인</a>|
                <a href="${path}/user/terms.do">회원가입</a>|
                <a href="#">나의정보 |</a>
                <a href="#">로그아웃 |</a>
                <a href="#">관리자</a>|
                <a href="#">고객센터</a>
            </p>
            <img src="${path}/images/head_txt_img.png" alt="3만원 이상 무료배송"/>
            <ul class="gnb">
                <li><a href="#">팜스토리소개</a></li>
                <li><a href="#"><img src="${path}/images/head_menu_badge.png" alt="30%"/>장보기</a></li>
                <li><a href="#">농작물이야기</a></li>
                <li><a href="#">이벤트</a></li>
                <li><a href="#">커뮤니티</a></li>
            </ul>
        </header>
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
            	<				</c:if>
                        	</td>
                        </tr>
                    </table>
                    <input type="submit" value="로그인" class="btnLogin"/>
                </form>
                <div>
                    <h3>회원 로그인 안내</h3>
                    <p>아직 회원이 아니시면 회원으로 가입하세요.</p>
                    <div style="text-align: right;">
                        <a href="${path}/user/userid.do">아이디 |</a>
                        <a href="${path}/user/password.do">비밀번호찾기 |</a>
                        <a href="${path}/user/terms.do">회원가입</a>
                    </div>
                </div>
            </section>
        </div>
        <footer>
            <img src="${path}/images/footer_logo.png" alt="로고"/>
            <p>
                (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-부산진구-123호<br />
                등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동<br />
                대표 : 김철학 / 이메일 : chhak0503@gmail.com / 전화 : 01) 234-5678<br />
                <span class="copyright">copyrightⓒ 김철학(개발에반하다.) All rights reserved.</span>
                <span class="version">farmstory ver1.0.1</span>
            </p>
        </footer>
    </div>
</body>
</html>