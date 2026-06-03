<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::나도요리사</title>
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
                            <li class="on"><a href="./chef.html">나도요리사</a></li>
                            <li><a href="./qna.html">1:1고객문의</a></li>
                            <li><a href="./faq.html">자주묻는질문</a></li>
                        </ul>
                    </aside>
                    <article id="board">
                        <nav>
                            <img src="../images/sub_nav_tit_cate5_tit3.png" alt="나도요리사" />
                            <p>HOME > 커뮤니티 > <em>나도요리사</em></p>
                        </nav>

                        <section class="view">
                            <h1>글보기</h1>
                            <table border="0">
                                <tr>
                                    <th>제목</th>
                                    <td><input type="text" name="title" placeholder="제목을 입력하세요." /></td>
                                </tr>
                                <tr>
                                    <th>파일</th>
                                    <td>
                                        <p><a href="#">2021년 상반기 매출현황.xls</a>&nbsp;<span>7</span>회 다운로드</p>
                                        <p><a href="#">교육 운영 관리자료.hwp</a>&nbsp;<span>7</span>회 다운로드</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>내용</th>
                                    <td>
                                        <textarea name="content"></textarea>
                                    </td>
                                </tr>
                            </table>

                            <div>
                                <a href="#" class="btn btnRemove">삭제</a>
                                <a href="./modify.html" class="btn btnModify">수정</a>
                                <a href="./list.html" class="btn btnList">목록</a>
                            </div>

                            <section class="commentList">
                                <h3>댓글목록</h3>

                                <article>
                                    <span class="date">2024-05-20</span>
                                    <span class="nick">길동이</span>
                                    <p class="content">댓글 샘플 입니다.</p>
                                    <div>
                                        <a href="#" class="remove">삭제</a>
                                        <a href="#" class="modify">수정</a>
                                    </div>
                                </article>

                                <p class="empty">등록된 댓글이 없습니다.</p>
                            </section>
                            <section class="commentForm">
                                <h3>댓글쓰기</h3>
                                <form action="#">
                                    <textarea name="content">댓글내용 입력</textarea>
                                    <div>
                                        <a href="#" class="btn btnCancel">취소</a>
                                        <input type="submit" value="작성완료" class="btn btnComplete" />
                                    </div>
                                </form>
                            </section>
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
