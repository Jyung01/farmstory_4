<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<header>
    <a href="/farmstory/admin/main.do" class="logo">
        <img src="${path}/images/admin/admin_logo.jpg" alt="로고" />
    </a>
    <ul>
        <li><a href="${path}/index.jsp">HOME |</a></li>
        <li><a href="${path}/user/logout.do">로그아웃 |</a></li>
        <li><a href="${path}/article/list.do?groupName=community&cate=qna">고객센터</a></li>
    </ul>
</header>