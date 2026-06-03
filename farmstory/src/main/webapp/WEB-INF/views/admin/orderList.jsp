<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_주문목록</title>
        <link rel="stylesheet" href="../css/index.css" />
    </head>
    <body>
        <div id="container">
            <header>
                <a href="./index.html" class="logo">
                    <img src="../images/admin/admin_logo.jpg" alt="로고" />
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
                                    <a href="./productList.html">L <span>상품목록</span></a>
                                </li>
                                <li>
                                    <a href="./productAdd.html">L <span>상품등록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>주문관리</span>
                            <ul>
                                <li>
                                    <a href="./orderList.html" class="active">L <span>주문목록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>회원관리</span>
                            <ul>
                                <li>
                                    <a href="./userList.html">L <span>회원목록</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </aside>

                <section class="admin_main orderList">
                    <h2>주문목록</h2>

                    <section>
                        <table>
                            <tr>
                                <th><input type="checkbox" /></th>
                                <th>주문번호</th>
                                <th>상품명</th>
                                <th>판매가격</th>
                                <th>수량</th>
                                <th>배송비</th>
                                <th>합계</th>
                                <th>주문자</th>
                                <th>주문일</th>
                                <th>확인</th>
                            </tr>
                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>1001</td>
                                <td>사과 500g</td>
                                <td>4,000원</td>
                                <td>2</td>
                                <td>3,000원</td>
                                <td>11,000원</td>
                                <td>김유신</td>
                                <td>2023-01-01 13:06:14</td>
                                <td><a href="#">[상세확인]</a></td>
                            </tr>
                        </table>
                    </section>

                    <div class="buttons">
                        <a href="#">선택삭제</a>
                    </div>

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
