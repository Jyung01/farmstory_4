<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>팜스토리::정보수정</title>
        <link rel="stylesheet" href="${path}/css/modify.css" />
        <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_header.jsp" %>
                
            

            <div id="myinfo">
                <div><img src="${path}/images/myinfo/myinfo_top_tit.png" alt="MY INFO" /></div>
                <section>
                    <aside>
                        <img src="${path}/images/myinfo/myinfo_menu_tit.png" alt="나의정보" />
                        <ul class="menu">
                            <li><a href="${path}/user/cart.do">장바구니</a></li>
                            <li><a href="${path}/user/ordered.do">주문내역</a></li>
                            <li class="on"><a href="${path}/user/myinfo.do">정보수정</a></li>
                        </ul>
                    </aside>
                    <article>
                        <nav>
                            <img src="${path}/images/myinfo/myinfo_nav_tit3.png" alt="정보수정" />
                            <p>HOME > 나의정보 > <em>정보수정</em></p>
                        </nav>

                        <!-- 내용 시작 -->
                        <section class="modify">
                            <form action="${path}/user/modify.do" method="post">
                                <h2>회원정보 설정</h2>
                                <table border="1">
                                    <tr>
                                        <td>아이디</td>
                                        <td>${sessUser.userid}</td>
                                    </tr>
                                    <tr>
                                        <td>비밀번호</td>
                                        <td>
                                            <input type="password" name="pass1" placeholder="비밀번호 입력" />
                                            <span class="passResult"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>비밀번호 확인</td>
                                        <td>
                                            <input type="password" name="pass2" placeholder="비밀번호 입력 확인" />
                                            <button type="button" class="btnUpdatePass" onclick="updatePass()">비밀번호 수정</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>회원가입날짜</td>
                                        <td>${sessUser.regDate }</td>
                                    </tr>
                                </table>

                                <h2>개인정보 수정</h2>
                                <table border="1">
                                    <tr>
                                        <td>이름</td>
                                        <td>
                                            <input type="text" name="name" value="${sessUser.name}" />
                                            <span class="nameResult"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>별명</td>
                                        <td>
                                            <p class="nickInfo">한글, 영문, 숫자 입력</p>
                                            <input type="text" name="nick" value="${sessUser.nick }"/>
                                            <button type="button" id="btnNickCheck">
                                                <img src="${path}/images/myinfo/chk_id.gif" alt="중복확인" />
                                            </button>
                                            <span class="nickResult"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>이메일</td>
                                        <td>
                                            <input type="email" name="email" value="${sessUser.email }" />
                                            <span class="emailResult"></span>
                                            <button type="button" id="btnEmailAuth" onclick="sendEmail()">
                                                <img src="${path}/images/myinfo/chk_auth.gif" alt="인증번호 받기" />
                                            </button>
                                            <div class="auth">
                                                <input type="text" name="auth" placeholder="인증번호 입력" />
                                                <button type="button" id="btnEmailConfirm">
                                                    <img src="${path}/images/myinfo/chk_confirm.gif" alt="확인" />
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>휴대폰</td>
                                        <td>
                                            <input type="text" name="hp" value="${sessUser.hp }"/>
                                            <span class="hpResult"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>주소</td>
                                        <td>
                                            <input
                                                type="text"
                                                name="zip"
                                                id="zip"
												value="${sessUser.zip }"                                                
                                                readonly="readonly"
                                            />
                                            <button type="button" onclick="searchZip()">
                                                <img src="${path}/images/myinfo/chk_post.gif" alt="우편번호찾기" />
                                            </button>
                                            <input type="text" name="addr1" id="addr1" value="${sessUser.addr1 }"/>
                                            <input type="text" name="addr2" id="addr2"value="${sessUser.addr2 }" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>회원탈퇴</td>
                                        <td>
                                            <a href="${path}/user/leave.do" class="btnWithdraw">탈퇴하기</a>
                                        </td>
                                    </tr>
                                </table>

                                <div>
                                    <a href="${path}/index.jsp" class="btn btnCancel">취소</a>
                                    <input type="submit" value="회원수정" class="btn btnRegister"/>
                                </div>
                            </form>
                        </section>
                        <!-- 내용 끝 -->
                    </article>
                </section>
            </div>

            <footer>
                <img src="${path}/images/footer_logo.png" alt="로고" />
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
        <script src="${path}/js/register.js"></script>
        <script src="${path}/js/modify.js"></script>
    </body>
</html>
