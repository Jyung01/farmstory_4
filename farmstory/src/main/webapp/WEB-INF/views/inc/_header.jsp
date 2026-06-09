<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<header>
    <a href="${path}/index.jsp" class="logo"><img src="${path}/images/logo.png" alt="로고" /></a>
    <p>
        <a href="${path}/index.jsp">HOME |</a>
        <c:choose>
            <c:when test="${not empty sessUser}">
                ${sessUser.name}님 반갑습니다! |
                <a href="${path}/myinfo/cart.jsp">나의정보 |</a>
                <a href="${path}/user/logout.do">로그아웃 |</a>
            </c:when>
            <c:otherwise>
                <a href="${path}/user/login.do">로그인 |</a>
                <a href="${path}/user/terms.do">회원가입 |</a>
            </c:otherwise>
        </c:choose>
        <a href="${path}/admin/">관리자 |</a>
        <a href="${path}/community/qna.jsp">고객센터</a>
    </p>
    <img src="${path}/images/head_txt_img.png" alt="3만원 이상 무료배송" />
    <ul class="gnb">
        <li><a href="${path}/introduction/hello.jsp">팜스토리소개</a></li>
        <li>
            <a href="${path}/market/list.jsp"><img src="${path}/images/head_menu_badge.png" alt="30%" />장보기</a>
        </li>
        <li><a href="${path}/croptalk/story.jsp">농작물이야기</a></li>
        <li><a href="${path}/event/event.jsp">이벤트</a></li>
        <li><a href="${path}/community/notice.jsp">커뮤니티</a></li>
    </ul>
</header>