<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_회원목록</title>
        <link rel="stylesheet" href="../css/index.css" />
    </head>
    <body>
        <div id="container">
            <header>
                <a href="./index.html" class="logo">
                    <img src="${path}/images/admin/admin_logo.jpg" alt="로고" />
                </a>
                <ul>
                    <li><a href="#">HOME |</a></li>
                    <li><a href="#">로그아웃 |</a></li>
                    <li><a href="#">고객센터</a></li>
                </ul>
            </header>

            <main>
                <aside>
                    <div><p>주요 기능</p></div>
                    <ul>
                        <li>
                            <span>상품관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/prodList.do">L <span>상품목록</span></a>
                                </li>
                                <li>
                                    <a href="/farmstory/admin/register.do">L <span>상품등록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>주문관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/orderList.do">L <span>주문목록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>회원관리</span>
                            <ul>
                                <li>
                                    <a href="/farmstory/admin/userList.do" class="active">L <span>회원목록</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </aside>

                <section class="admin_main userList">
                    <h2>회원목록</h2>

                    <section>
                        <table>
                            <tr>
                                <th><input type="checkbox" /></th>
                                <th>아이디</th>
                                <th>이름</th>
                                <th>별명</th>
                                <th>이메일</th>
                                <th>휴대폰</th>
                                <th>등급</th>
                                <th>가입일</th>
                                <th>확인</th>
                            </tr>

                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>a101</td>
                                <td>김유신</td>
                                <td>유신101</td>
                                <td>yusin101@naver.com</td>
                                <td>010-1234-1001</td>
                                <td>
                                    <select name="grade">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                    </select>
                                </td>
                                <td>2023-01-01 13:06:14</td>
                                <td><a href="#">[상세확인]</a></td>
                            </tr>

                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>a102</td>
                                <td>김춘추</td>
                                <td>춘추102</td>
                                <td>chunchu102@naver.com</td>
                                <td>010-1234-1002</td>
                                <td>
                                    <select name="grade">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                    </select>
                                </td>
                                <td>2023-01-01 13:06:14</td>
                                <td><a href="#">[상세확인]</a></td>
                            </tr>

                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>a103</td>
                                <td>장보고</td>
                                <td>보고103</td>
                                <td>bogo103@naver.com</td>
                                <td>010-1234-1003</td>
                                <td>
                                    <select name="grade">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                    </select>
                                </td>
                                <td>2023-01-01 13:06:14</td>
                                <td><a href="#">[상세확인]</a></td>
                            </tr>
                        </table>
                    </section>

                    <!-- 페이지네이션 -->
                    <div class="pagination_wrapper">
                        <ul class="pagination">
                            <li><a href="#"><</a></li>
                            <li><a href="#" class="page_active">[1]</a></li>
                            <li><a href="#">[2]</a></li>
                            <li><a href="#">[3]</a></li>
                            <li><a href="#">[4]</a></li>
                            <li><a href="#">[5]</a></li>
                            <li><a href="#">></a></li>
                        </ul>
                    </div>
                </section>
            </main>

            <footer>
                <p>FARMSTORY ADMINISTRATOR Version 1.0.1</p>
                <p>Copyrightⓒ 김철학(개발에반하다.) All rights reserved.</p>
            </footer>
        </div>
    </body>
</html>
