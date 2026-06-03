<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_메인</title>
        <link rel="stylesheet" href="../css/index.css" />
    </head>
    <body>
        <div id="container">
            <%@ include file="/WEB-INF/views/inc/_admin_header.jsp" %>
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

                <section class="admin_main">
                    <h2>관리자 메인</h2>

                    <!-- 상품현황 -->
                    <section>
                        <div>
                            <p>상품현황</p>
                            <a href="#">+ 더보기</a>
                        </div>
                        <table>
                            <tr>
                                <th>상품번호</th>
                                <th>상품명</th>
                                <th>구분</th>
                                <th>가격</th>
                                <th>재고</th>
                                <th>등록일</th>
                            </tr>
                            <tr>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>과일</td>
                                <td>4,000원</td>
                                <td>100</td>
                                <td>2023-01-01</td>
                            </tr>
                            <tr>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>과일</td>
                                <td>4,000원</td>
                                <td>100</td>
                                <td>2023-01-01</td>
                            </tr>
                            <tr>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>과일</td>
                                <td>4,000원</td>
                                <td>100</td>
                                <td>2023-01-01</td>
                            </tr>
                        </table>
                    </section>

                    <!-- 주문현황 -->
                    <section>
                        <div>
                            <p>주문현황</p>
                            <a href="#">+ 더보기</a>
                        </div>
                        <table>
                            <tr>
                                <th>주문번호</th>
                                <th>상품명</th>
                                <th>판매가격</th>
                                <th>수량</th>
                                <th>배송비</th>
                                <th>합계</th>
                                <th>주문자</th>
                                <th>주문일</th>
                            </tr>
                            <tr>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>4,000원</td>
                                <td>2개</td>
                                <td>3,000원</td>
                                <td>8,000원</td>
                                <td>홍길동</td>
                                <td>2023-01-01</td>
                            </tr>
                            <tr>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>4,000원</td>
                                <td>2개</td>
                                <td>3,000원</td>
                                <td>8,000원</td>
                                <td>홍길동</td>
                                <td>2023-01-01</td>
                            </tr>
                            <tr>
                                <td>1011</td>
                                <td>사과 500g</td>
                                <td>4,000원</td>
                                <td>2개</td>
                                <td>3,000원</td>
                                <td>8,000원</td>
                                <td>홍길동</td>
                                <td>2023-01-01</td>
                            </tr>
                        </table>
                    </section>

                    <!-- 회원현황 -->
                    <section>
                        <div>
                            <p>회원현황</p>
                            <a href="#">+ 더보기</a>
                        </div>
                        <table>
                            <tr>
                                <th>회원아이디</th>
                                <th>이름</th>
                                <th>닉네임</th>
                                <th>휴대폰</th>
                                <th>이메일</th>
                                <th>등급</th>
                                <th>회원가입일</th>
                            </tr>
                            <tr>
                                <td>a101</td>
                                <td>김유신</td>
                                <td>유신123</td>
                                <td>010-1234-1001</td>
                                <td>yusin123@naver.com</td>
                                <td>2</td>
                                <td>2023-01-01</td>
                            </tr>
                            <tr>
                                <td>a101</td>
                                <td>김유신</td>
                                <td>유신123</td>
                                <td>010-1234-1001</td>
                                <td>yusin123@naver.com</td>
                                <td>2</td>
                                <td>2023-01-01</td>
                            </tr>
                            <tr>
                                <td>a101</td>
                                <td>김유신</td>
                                <td>유신123</td>
                                <td>010-1234-1001</td>
                                <td>yusin123@naver.com</td>
                                <td>2</td>
                                <td>2023-01-01</td>
                            </tr>
                        </table>
                    </section>
                </section>
            </main>
			<%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
