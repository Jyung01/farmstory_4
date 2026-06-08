<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<aside>
    <img src="${pageContext.request.contextPath}/images/sub_aside_cate5_tit.png" alt="커뮤니티" />

    <ul class="lnb">
        <li class="${cate eq 'notice' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=community&cate=notice">공지사항</a></li>
        <li class="${cate eq 'menu' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=community&cate=menu">오늘의식단</a></li>
        <li class="${cate eq 'chef' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=community&cate=chef">나도요리사</a></li>
        <li class="${cate eq 'qna' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=community&cate=qna">1:1고객문의</a></li>
        <li class="${cate eq 'faq' ? 'on' : ''}"><a href="${pageContext.request.contextPath}/article/list.do?groupName=community&cate=faq">자주묻는질문</a></li>
    </ul>
</aside>