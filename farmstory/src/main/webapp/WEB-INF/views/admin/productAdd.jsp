<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_상품등록</title>
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
                                    <a href="./productAdd.html" class="active">L <span>상품등록</span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span>주문관리</span>
                            <ul>
                                <li>
                                    <a href="./orderList.html">L <span>주문목록</span></a>
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

                <section class="admin_main proAdd">
                    <h2>상품등록</h2>

                    <section>
                        <!-- <table>
                            <tr>
                                <th><input type="checkbox"></th>
                                <th>사진</th>
                                <th>상품번호</th>
                                <th>상품명</th>
                                <th>구분</th>
                                <th>가격</th>
                                <th>재고</th>
                                <th>등록일</th>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></td>
                                <td><img src="../images/market_item1.jpg" alt="item1"></td>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>과일</td>
                                <td>4,000원</td>
                                <td>100</td>
                                <td>2023-01-01</td>
                            </tr>
                        </table> -->

                        <form action="#">
                            <div>
                                <p>상품명</p>
                                <input type="text" />
                            </div>
                            <div>
                                <p>종류</p>
                                <select name="category">
                                    <option value="initial">종류</option>
                                </select>
                            </div>
                            <div>
                                <p>가격</p>
                                <input type="text" />
                            </div>
                            <div>
                                <p>포인트</p>
                                <input type="text" />
                            </div>
                            <div>
                                <p>할인</p>
                                <select name="discount">
                                    <option value="5">5%</option>
                                </select>
                            </div>
                            <div class="delivery">
                                <p>배송비</p>
                                <div>
                                    <label><input type="radio" name="delivery" />2,000원</label>
                                    <label><input type="radio" name="delivery" />3,000원</label>
                                    <label><input type="radio" name="delivery" />5,000원</label>
                                    <label><input type="radio" name="delivery" />무료</label>
                                </div>
                            </div>
                            <div>
                                <p>재고</p>
                                <input type="text" />
                            </div>
                            <div class="proImg">
                                <p>상품이미지</p>
                                <div>
                                    <div>
                                        <span>상품목록 이미지(약 120 x 120)</span>
                                        <input type="file" />
                                    </div>
                                    <div>
                                        <span>상품목록 이미지(약 120 x 120)</span>
                                        <input type="file" />
                                    </div>
                                    <div>
                                        <span>상품목록 이미지(약 120 x 120)</span>
                                        <input type="file" />
                                    </div>
                                </div>
                            </div>
                            <div>
                                <p>기타</p>
                                <textarea name="" id=""></textarea>
                            </div>
                        </form>
                    </section>

                    <div class="buttons">
                        <a href="#" class="cancel">취소</a>
                        <a href="#" class="add_product"><p>상품등록</p></a>
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
