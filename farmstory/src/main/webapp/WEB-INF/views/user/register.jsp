<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
    <head>
    <script src="${path}/js/register.js"></script>
        <meta charset="UTF-8" />
        <title>팜스토리::회원가입</title>
        <link rel="stylesheet" href="${path}/css/register.css" />
    </head>
    <body>
        <div id="container">
            <header>
                <a href="${path}/index.jsp" class="logo"><img src="${path}/images/logo.png" alt="로고" /></a>
                <p>
                    <a href="${path}/index.jsp">HOME</a>| 
                    <a href="${path}/user/login.do">로그인</a>| 
                    <a href="${path}/user/terms.do">회원가입</a>|
                    <a href="#">나의정보 |</a>
                    <a href="#">로그아웃 |</a>
                    <a href="#">관리자</a>|
                    <a href="#">고객센터</a>
                </p>
                <img src="${path}/images/head_txt_img.png" alt="3만원 이상 무료배송" />
                <ul class="gnb">
                    <li><a href="#">팜스토리소개</a></li>
                    <li><a href="#"><img src="${path}/images/head_menu_badge.png" alt="30%" />장보기</a></li>
                    <li><a href="#">농작물이야기</a></li>
                    <li><a href="#">이벤트</a></li>
                    <li><a href="#">커뮤니티</a></li>
                </ul>
            </header>
            <div id="user">
                <section class="register">
                    <form action="${path}/user/register.do" method="post">
                        <h2 class="tit">사이트 이용정보 입력</h2>
                        <table border="1">
                            <tr>
                                <td>아이디</td>
                                <td>
                                    <input type="text" name="uid" placeholder="아이디 입력" />
                                    <button type="button" onclick="checkUserid()"><img src="${path}/images/user/chk_id.gif" alt="중복확인" /></button>
                                    <span class="uidResult"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>비밀번호</td>
                                <td><input type="password" name="pass1" placeholder="비밀번호 입력" /></td>
                            </tr>
                            <tr>
                                <td>비밀번호 확인</td>
                                <td><input type="password" name="pass2" placeholder="비밀번호 입력 확인"  onkeyup="checkPass()"/>
                                <span class="passResult"></span></td>
                            </tr>
                        </table>
                        <h2 class="tit">개인정보 입력</h2>
                        <table border="1">
                            <tr>
                                <td>이름</td>
                                <td><input type="text" name="name" placeholder="이름 입력" /></td>
                            </tr>
                            <tr>
                                <td>별명</td>
                                <td>
                                    <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                    <input type="text" name="nick" placeholder="별명 입력" />
                                    <button type="button" onclick="checkNick()"><img src="${path}/images/user/chk_id.gif" alt="중복확인" /></button>
                                    <span class="nickResult"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>이메일</td>
                                <td>
                                    <input type="email" name="email" placeholder="이메일 입력" />
                                    <button type="button">
                                        <img src="${path}/images/user/chk_auth.gif" alt="인증번호 받기" />
                                    </button>
                                    <div class="auth">
                                        <input type="text" name="auth" placeholder="인증번호 입력" />
                                        <button type="button">
                                            <img src="${path}/images/user/chk_confirm.gif" alt="확인" />
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>휴대폰</td>
                                <td><input type="text" name="hp" placeholder="휴대폰 입력" /></td>
                            </tr>
                            <tr>
                                <td>주소</td>
                                <td>
                                    <input type="text" name="zip" placeholder="우편번호" />
                                    <button type="button">
                                        <img src="${path}/images/user/chk_post.gif" alt="우편번호찾기" />
                                    </button>
                                    <input type="text" name="addr1" placeholder="주소 검색" />
                                    <input type="text" name="addr2" placeholder="상세주소 입력" />
                                </td>
                            </tr>
                        </table>
                        <div>
                            <a href="${path}/user/login.do" class="btn btnCancel">취소</a>
                            <input type="submit" value="회원가입" class="btn btnRegister" />
                        </div>
                    </form>
                </section>
            </div>
            <footer>
                <img src="${path}/images/footer_logo.png" alt="로고" />
                <p>
                    (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-부산진구-123호<br />
                    등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동<br />
                    대표 : 김철학 / 이메일 : chhak0503@gmail.com / 전화 : 01) 234-5678<br />
                    <span class="falo">copyrightⓒ 김철학(개발에반하다.) All rights reserved.</span>
                    <span class="version">farmstory ver1.0.1</span>
                </p>
            </footer>
        </div>
    </body>
     <script src="${path}/js/register.js"></script>
</html>