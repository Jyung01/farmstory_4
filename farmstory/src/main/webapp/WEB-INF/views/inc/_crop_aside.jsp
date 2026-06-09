<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<aside>
    <img src="${pageContext.request.contextPath}/images/sub_aside_cate3_tit.png" alt="농작물이야기" />
    <ul class="lnb">
        <li class="${cate eq 'story' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=crop&cate=story">농작물이야기</a></li>
        <li class="${cate eq 'grow' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=crop&cate=grow">텃밭가꾸기</a></li>
        <li class="${cate eq 'school' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=crop&cate=school">귀농학교</a></li>
    </ul>
</aside>