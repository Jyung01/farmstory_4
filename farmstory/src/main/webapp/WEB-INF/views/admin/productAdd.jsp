<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

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
                                    <a href="/farmstory/admin/register.do" class="active">L <span>상품등록</span></a>
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
                                    <a href="/farmstory/admin/userList.do">L <span>회원목록</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </aside>

                <section class="admin_main proAdd">
                    <h2>상품등록</h2>

                    <section>
                        <form action="/farmstory/admin/register.do" method="post" enctype="multipart/form-data">
                            <div>
                                <p>상품명</p>
                                <input type="text" name="name" />
                            </div>
                            <div>
                                <p>종류</p>
                                <select name="category">
                                    <option value="종류">종류</option>
                                    <option value="과일">과일</option>
                                </select>
                            </div>
                            <div>
                                <p>가격</p>
                                <input type="text" name="price"/>
                            </div>
                            <div>
                                <p>포인트</p>
                                <input type="text" name="point"/>
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
                                    <label><input type="radio" name="delivery" value="2000" />2,000원</label>
                                    <label><input type="radio" name="delivery" value="3000" />3,000원</label>
                                    <label><input type="radio" name="delivery" value="5000" />5,000원</label>
                                    <label><input type="radio" name="delivery"  value="0"/>무료</label>
                                </div>
                            </div>
                            <div>
                                <p>재고</p>
                                <input type="text" name="stock"/>
                            </div>
                            <div class="proImg">
                                <p>상품이미지</p>
                                <div>
                                    <div>
                                        <span>상품목록 이미지(약 120 x 120)</span>
                                        <input type="file" name="file1"/>
                                    </div>
                                    <div>
                                        <span>상품목록 이미지(약 120 x 120)</span>
                                        <input type="file" name="file2" />
                                    </div>
                                    <div>
                                        <span>상품목록 이미지(약 120 x 120)</span>
                                        <input type="file" name="file3"/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <p>기타</p>
                                <textarea name="discript" id=""></textarea>
                            </div>
                            
                            <div class="buttons">
		                        <a href="#" class="cancel">취소</a>
		                        <input type="submit" value="상품등록" class="add_product"/>
		                   	 </div>
                        </form>
                    </section>

                    
                </section>
            </main>

            <footer>
                <p>FARMSTORY ADMINISTRATOR Version 1.0.1</p>
                <p>Copyrightⓒ 김철학(개발에반하다.) All rights reserved.</p>
            </footer>
        </div>
    </body>
</html>
