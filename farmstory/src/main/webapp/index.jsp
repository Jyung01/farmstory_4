<!-- 실행시키면 index.do 로 이동  -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.sendRedirect(request.getContextPath() + "/index.do");
%>

