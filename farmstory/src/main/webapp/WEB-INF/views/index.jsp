<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::메인</title>
        <link rel="stylesheet" href="${path}/css/main.css" />
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
            <main>
                <div class="slider">
                    <ul>
                        <li><img src="${path}/images/main_slide_img1.jpg" alt="슬라이더1" /></li>
                        <li><img src="${path}/images/main_slide_img2.jpg" alt="슬라이더2" /></li>
                        <li><img src="${path}/images/main_slide_img3.jpg" alt="슬라이더3" /></li>
                    </ul>

                    <img src="${path}/images/main_slide_img_tit.png" alt="사람과 자연을 사랑하는 팜스토리" />

                    <div class="banner">
                        <img src="${path}/images/main_banner_txt.png" alt="GRAND OPEN" />
                        <img src="${path}/images/main_banner_tit.png" alt="팜스토리 오픈기념 30% 할인 이벤트" />
                        <img src="${path}/images/main_banner_img.png" alt="과일" />
                    </div>
                </div>

                <div class="best">
                    <img src="${path}/images/main_market_tit.png" />
                    <section>
                        <article>
                            <a href="#"><img src="${path}/images/market_item1.jpg" class="thumbnail" alt="사과 500g" /></a>
                            <h1>과일</h1>
                            <h2>사과 500g</h2>
                            <p>
                                <del>4,000</del>
                                <span class="discount">10%</span>
                            </p>
                            <h3 class="price">3,600</h3>
                        </article>
                        <article>
                            <a href="#"><img src="${path}/images/market_item2.jpg" class="thumbnail" alt="사과 500g" /></a>
                            <h1>과일</h1>
                            <h2>사과 500g</h2>
                            <p>
                                <del>4,000</del>
                                <span class="discount">10%</span>
                            </p>
                            <h3 class="price">3,600</h3>
                        </article>
                        <article>
                            <a href="#"><img src="${path}/images/market_item3.jpg" class="thumbnail" alt="사과 500g" /></a>
                            <h1>과일</h1>
                            <h2>사과 500g</h2>
                            <p>
                                <del>4,000</del>
                                <span class="discount">10%</span>
                            </p>
                            <h3 class="price">3,600</h3>
                        </article>
                        <article>
                            <a href="#"><img src="${path}/images/market_item4.jpg" class="thumbnail" alt="사과 500g" /></a>
                            <h1>과일</h1>
                            <h2>사과 500g</h2>
                            <p>
                                <del>4,000</del>
                                <span class="discount">10%</span>
                            </p>
                            <h3 class="price">3,600</h3>
                        </article>
                        <article>
                            <a href="#"><img src="${path}/images/market_item5.jpg" class="thumbnail" alt="사과 500g" /></a>
                            <h1>과일</h1>
                            <h2>사과 500g</h2>
                            <p>
                                <del>4,000</del>
                                <span class="discount">10%</span>
                            </p>
                            <h3 class="price">3,600</h3>
                        </article>
                        <article>
                            <a href="#"><img src="${path}/images/market_item6.jpg" class="thumbnail" alt="사과 500g" /></a>
                            <h1>과일</h1>
                            <h2>사과 500g</h2>
                            <p>
                                <del>4,000</del>
                                <span class="discount">10%</span>
                            </p>
                            <h3 class="price">3,600</h3>
                        </article>
                    </section>
                </div>
                <div class="quick">
                    <a href="${path}/article/list.do?groupName=community&cate=menu"><img src="${path}/images/main_banner_sub1_tit.png" alt="오늘의 식단" /></a>
                    <a href="${path}/article/list.do?groupName=community&cate=chef"><img src="${path}/images/main_banner_sub2_tit.png" alt="나도 요리사" /></a>
                </div>

                <div class="latest">
                    <div>
                        <a href="#"><img src="${path}/images/main_latest1_tit.png" alt="텃밭 가꾸기" /></a>
                        <img src="${path}/images/main_latest1_img.jpg" alt="이미지" />
                        <table border="0">
                            <c:forEach var="dto" items="${growList}">
							    <tr>
							        <td>></td>
							        <td>
							            <a href="${path}/article/view.do?ano=${dto.ano}&groupName=${dto.groupName}&cate=${dto.cate}&page=1">
							                ${dto.title}
							            </a>
							        </td>
							        <td>${dto.wdate.substring(0, 8)}</td>
							    </tr>
							</c:forEach>
                        </table>
                    </div>
                    <div>
                        <a href="#"><img src="${path}/images/main_latest2_tit.png" alt="귀농학교" /></a>
                        <img src="${path}/images/main_latest2_img.jpg" alt="이미지" />
                        <table border="0">
                            <c:forEach var="dto" items="${schoolList}">
							    <tr>
							        <td>></td>
							        <td><a href="${path}/article/view.do?ano=${dto.ano}&groupName=${dto.groupName}&cate=${dto.cate}&page=1">${dto.title}</a></td>
							        <td>${dto.wdate.substring(0, 8)}</td>
							    </tr>
							</c:forEach>
                        </table>
                    </div>
                    <div>
                        <a href="#"><img src="${path}/images/main_latest3_tit.png" alt="농작물 이야기" /></a>
                        <img src="${path}/images/main_latest3_img.jpg" alt="이미지" />
                        <table border="0">
                            <c:forEach var="dto" items="${storyList}">
							    <tr>
							        <td>></td>
							        <td><a href="${path}/article/view.do?ano=${dto.ano}&groupName=${dto.groupName}&cate=${dto.cate}&page=1">${dto.title}</a></td>
							        <td>${dto.wdate.substring(0, 8)}</td>
							    </tr>
							</c:forEach>	
                        </table>
                    </div>
                </div>

                <div class="info">
                    <div>
                        <img src="${path}/images/main_sub2_cs_tit.png" class="tit" alt="고객센터 안내" />
                        <div class="tel">
                            <img src="${path}/images/main_sub2_cs_img.png" alt="" />
                            <img src="${path}/images/main_sub2_cs_txt.png" alt="1666-777" />
                            <p class="time">
                                평일: AM 09:00 ~ PM 06:00<br />
                                점심: PM 12:00 ~ PM 01:00<br />
                                토, 일요일, 공휴일 휴무
                            </p>
                        </div>
                        <div class="btns">
                            <a href="${path}/article/list.do?groupName=community&cate=qna"><img src="${path}/images/main_sub2_cs_bt1.png" alt="1:1 고객문의" /></a>
                            <a href="${path}/article/list.do?groupName=community&cate=faq"><img src="${path}/images/main_sub2_cs_bt2.png" alt="자주묻는질문" /></a>
                            <a href="#"><img src="${path}/images/main_sub2_cs_bt3.png" alt="배송조회" /></a>
                        </div>
                    </div>
                    <div>
                        <img src="${path}/images/main_sub2_account_tit.png" class="tit" alt="계좌안내" />
                        <p class="account">
                            기업은행 123-456789-01-01-012<br />
                            국민은행 01-1234-56789<br />
                            우리은행 123-456789-01-01-012<br />
                            하나은행 123-456789-01-01<br />
                            예 금 주 (주)팜스토리
                        </p>
                    </div>
                    <div>
                        <img src="${path}/images/main_sub2_notice_tit.png" class="tit" alt="공지사항" />
                        <table class="notice" border="0">
                            <c:forEach var="dto" items="${noticeList}">
							    <tr>
							        <td>></td>
							        <td><a href="${path}/article/view.do?ano=${dto.ano}&groupName=${dto.groupName}&cate=${dto.cate}&page=1">${dto.title}</a></td>
							        <td>${dto.wdate.substring(0, 8)}</td>
							    </tr>
							</c:forEach>
                        </table>
                    </div>
                </div>
            </main>
            <%@ include file="/WEB-INF/views/inc/_footer.jsp" %>
        </div>
    	<c:if test="${param.leave == 'success'}">
            <script>
                alert('탈퇴가 완료되었습니다.');
            </script>
        </c:if>
    </body>
</html>
