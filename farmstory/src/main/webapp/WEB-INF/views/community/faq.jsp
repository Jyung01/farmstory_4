<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::자주묻는질문</title>
        <link rel="stylesheet" href="../css/crop.css" />
    </head>
    <body>
        <div id="container">
            <header>
                <a href="../index.html" class="logo"><img src="../images/logo.png" alt="로고" /></a>
                <p>
                    <a href="#">HOME |</a>
                    <a href="#">로그인 |</a>
                    <a href="#">회원가입 |</a>
                    <a href="#">나의정보 |</a>
                    <a href="#">로그아웃 |</a>
                    <a href="../admin/index.html">관리자 |</a>
                    <a href="#">고객센터</a>
                </p>
                <img src="../images/head_txt_img.png" alt="3만원 이상 무료배송" />

                <ul class="gnb">
                    <li><a href="#">팜스토리소개</a></li>
                    <li>
                        <a href="#"><img src="../images/head_menu_badge.png" alt="30%" />장바구니</a>
                    </li>
                    <li><a href="../crop/grow.html">농작물이야기</a></li>
                    <li><a href="../event/event.html">이벤트</a></li>
                    <li><a href="./notice.html">커뮤니티</a></li>
                </ul>
            </header>

            <div id="crop">
                <div><img src="../images/sub_top_tit5.png" alt="COMMUNITY" /></div>
                <section class="community">
                    <aside>
                        <img src="../images/sub_aside_cate5_tit.png" alt="커뮤니티" />

                        <ul class="lnb">
                            <li><a href="./notice.html">공지사항</a></li>
                            <li><a href="./menu.html">오늘의식단</a></li>
                            <li><a href="./chef.html">나도요리사</a></li>
                            <li><a href="./qna.html">1:1고객문의</a></li>
                            <li class="on"><a href="./faq.html">자주묻는질문</a></li>
                        </ul>
                    </aside>
                    <article id="board">
                        <nav>
                            <img src="../images/sub_nav_tit_cate5_tit5.png" alt="자주묻는질문" />
                            <p>HOME > 커뮤니티 > <em>자주묻는질문</em></p>
                        </nav>

                        <!-- 게시판 글목록/글쓰기/글보기/글수정 내용 시작 -->
                        <section class="list">
                            <nav>
                                <form action="#">
                                    <input type="text" name="search" placeholder="제목 키워드, 글쓴이 검색" />
                                    <input type="submit" value="검색" />
                                </form>
                            </nav>
                            <h1>글목록</h1>
                            <table border="0">
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>글쓴이</th>
                                    <th>날짜</th>
                                    <th>조회</th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="./view.html">반품은 어떻게 해야하나요?</a></td>
                                    <td>길동이</td>
                                    <td>20-05-12</td>
                                    <td>12</td>
                                </tr>
                            </table>

                            <div class="page">
                                <a href="#" class="prev">이전</a>
                                <a href="#" class="num current">1</a>
                                <a href="#" class="num">2</a>
                                <a href="#" class="num">3</a>
                                <a href="#" class="next">다음</a>
                            </div>

                            <a href="./write.html" class="btn btnWrite">글쓰기</a>
                        </section>
                        <!-- 내용 끝 -->
                    </article>
                </section>
            </div>

            <footer>
                <img src="../images/footer_logo.png" alt="로고" />
                <p>
                    (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-팜스토리구-123호 / 벤처기업확인
                    서울지방중소기업청 제 012345678-9-01234호<br />
                    등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동<br />
                    대표 : 홍길동 / 이메일 : email@mail.mail / 전화 : 01) 234-5678 / 경기도 성남시 잘한다구 신난다동
                    345<br />
                    <em>Copyright(C)홍길동 All rights reserved.</em>
                </p>
            </footer>
        </div>
    </body>
</html>
