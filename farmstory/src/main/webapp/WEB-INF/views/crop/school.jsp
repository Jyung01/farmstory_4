<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::귀농학교</title>
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
                    <li><a href="./grow.html">농작물이야기</a></li>
                    <li><a href="../event/event.html">이벤트</a></li>
                    <li><a href="../community/notice.html">커뮤니티</a></li>
                </ul>
            </header>
            <div id="crop">
                <div><img src="../images/sub_top_tit3.png" alt="CROP TALK" /></div>
                <section class="croptalk">
                    <aside>
                        <img src="../images/sub_aside_cate3_tit.png" alt="농작물이야기" />
                        <ul class="lnb">
                            <li><a href="./story.html">농작물이야기</a></li>
                            <li><a href="./grow.html">텃밭가꾸기</a></li>
                            <li class="on"><a href="./school.html">귀농학교</a></li>
                        </ul>
                    </aside>
                    <article id="board">
                        <nav>
                            <img src="../images/sub_nav_tit_cate3_tit3.png" alt="텃밭가꾸기" />
                            <p>HOME > 농작물이야기 > <em>귀농학교</em></p>
                        </nav>

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
                                    <td>3</td>
                                    <td><a href="./view.html">귀농학교 게시물입니다.[3]</a></td>
                                    <td>길동이</td>
                                    <td>20-05-12</td>
                                    <td>12</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><a href="./view.html">귀농학교 게시물입니다.[3]</a></td>
                                    <td>길동이</td>
                                    <td>20-05-12</td>
                                    <td>12</td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><a href="./view.html">귀농학교 게시물입니다.[3]</a></td>
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
