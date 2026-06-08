<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<header>
    <a href="${path}/index.jsp" class="logo"><img src="${path}/images/logo.png" alt="로고" /></a>
    <p>
        <a href="./index.jsp">HOME |</a>
        <a href="./user/login.html">로그인 |</a>
        <a href="./user/terms.html">회원가입 |</a>
        <a href="./myinfo/cart.html">나의정보 |</a>
        <a href="#">로그아웃 |</a>
        <a href="./admin/">관리자 |</a>
        <a href="./community/qna.html">고객센터</a>
    </p>
    <img src="${path}/images/head_txt_img.png" alt="3만원 이상 무료배송" />

    <ul class="gnb">
        <li><a href="./introduction/hello.html">팜스토리소개</a></li>
        <li>
            <a href="./market/list.html"><img src="${path}/images/head_menu_badge.png" alt="30%" />장보기</a>
        </li>
        <li><a href="${path}/article/list.do?groupName=crop&cate=story">농작물이야기</a></li>
        <li><a href="./event/event.html">이벤트</a></li>
        <li><a href="${path}/article/list.do?groupName=community&cate=notice">커뮤니티</a></li>
    </ul>
</header>