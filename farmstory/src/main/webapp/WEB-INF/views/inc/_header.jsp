<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<header>
    <a href="${path}/index.jsp" class="logo"><img src="${path}/images/logo.png" alt="로고" /></a>
    <p>
        <a href="${path}/index.jsp">HOME |</a>
<<<<<<< Updated upstream
        <a href="${path}/test/login.do">로그인 |</a>
        <a href="./user/terms.html">회원가입 |</a>
        <a href="./myinfo/cart.html">나의정보 |</a>
        <a href="#">로그아웃 |</a>
        <a href="./admin/">관리자 |</a>
        <a href="./community/qna.html">고객센터</a>
=======
        <c:choose>
            <c:when test="${not empty loginUser}">
                ${loginUser.name}님 반갑습니다! |
                <a href="${path}/market/cart.do">나의정보 |</a> <!-- 주소 변경 -->
                <a href="${path}/user/logout.do">로그아웃 |</a>
            </c:when>
            <c:otherwise>
                <a href="${path}/test/login.do">로그인 |</a> <!-- 로그인 테스트용으로 변경 -->
                <a href="${path}/user/terms.do">회원가입 |</a>
            </c:otherwise>
        </c:choose>
        <a href="${path}/admin/">관리자 |</a>
        <a href="${path}/community/qna.jsp">고객센터</a>
>>>>>>> Stashed changes
    </p>
    <img src="${path}/images/head_txt_img.png" alt="3만원 이상 무료배송" />

    <ul class="gnb">
        <li><a href="./introduction/hello.html">팜스토리소개</a></li>
        <li>
<<<<<<< Updated upstream
            <a href="${path}/market/list.do"><img src="${path}/images/head_menu_badge.png" alt="30%" />장보기</a>
=======
            <a href="${path}/market/list.do"><img src="${path}/images/head_menu_badge.png" alt="30%" />장보기</a>  <!-- 주소 변경 -->
>>>>>>> Stashed changes
        </li>
        <li><a href="./croptalk/story.html">농작물이야기</a></li>
        <li><a href="./event/event.html">이벤트</a></li>
        <li><a href="./community/notice.html">커뮤니티</a></li>
    </ul>
</header>