<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>관리자_상품목록</title>
        <link rel="stylesheet" href="../css/index.css" />
    </head>
    
    <script>
	// 1. 전체 선택 / 해제 기능
	function toggleSelectAll(selectAllCheckbox) {
	    const checkboxes = document.getElementsByName('prodNo');
	    
	    checkboxes.forEach((checkbox) => {
	        checkbox.checked = selectAllCheckbox.checked;
	    });
	}
	
	// 2. 개별 체크박스 선택 시 전체 선택 체크박스 상태 동기화
	function checkSelectAll() {
	    const selectAll = document.getElementById('selectAll');
	    const checkboxes = document.getElementsByName('prodNo');
	    
	    // 현재 체크된 체크박스만 필터링
	    const checkedBoxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);
	    
	    // 모든 체크박스가 체크되었다면 전체선택도 체크, 하나라도 아니면 해제
	    if (checkboxes.length === checkedBoxes.length) {
	        selectAll.checked = true;
	    } else {
	        selectAll.checked = false;
	    }
	}
	
	// 3. 삭제 확인
	function validateDelete() {
	    const checkboxes = document.getElementsByName('prodNo');
	    
	    // 체크된 체크박스가 하나라도 있는지 확인 (아무것도 안 고르고 삭제 누르는 것 방지)
	    const isAnyChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
	    
	    if (!isAnyChecked) {
	        alert('삭제할 상품을 선택해주세요.');
	        return false; // 폼 제출 중단
	    }
	    
	    // 사용자에게 최종 삭제 여부 확인
	    if (confirm('선택한 상품을 삭제하시겠습니까?')) {
	        return true;
	    } else {
	        return false;
	    }
	}
	</script>

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
                                    <a href="/farmstory/admin/prodList.do" class="active">L <span>상품목록</span></a>
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
                                    <a href="/farmstory/admin/userList.do">L <span>회원목록</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </aside>

                <section class="admin_main proList">
                    <h2>상품목록</h2>

					<form action="/farmstory/admin/prodDelete.do" method="post" onsubmit="return validateDelete()">
	                    <section>
	                        <table>
	                            <tr>
	                                <th><input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)" /></th>
	                                <th>사진</th>
	                                <th>상품번호</th>
	                                <th>상품명</th>
	                                <th>구분</th>
	                                <th>가격</th>
	                                <th>재고</th>
	                                <th>등록일</th>
	                            </tr>
	                            
	                            <c:forEach var="product" items="${dtoList}">
		                            <tr>
		                                <td><input type="checkbox" name="prodNo" value="${product.prodNo}" onclick="checkSelectAll()"/></td>
		                                <td><img src="${path}/upload/${product.thumb}" alt="item1" /></td>
		                                <td>${product.prodNo}</td>
		                                <td>${product.prodName}</td>
		                                <td>${product.cate}</td>
		                                <td>${product.price}원</td>
		                                <td>${product.stock}</td>
		                                <td>${product.regDate}</td>
		                            </tr>
	                            </c:forEach>
	                        </table>
	                        
	                        <c:if test="${empty dtoList}">
							    <p style="text-align:center; padding:20px 0;">상품 목록이 없습니다.</p>
							</c:if>
	                    </section>
	
	                    <div class="buttons">
	                    	<input type="submit" value="선택삭제">
	                        <a href="/farmstory/admin/register.do" class="add_product"><p>상품등록</p></a>
	                    </div>
                    </form>

                    <!-- 페이지네이션 -->
                    <div class="pagination_wrapper">
                        <ul class="pagination">
                        	<c:if test="${pageGroupDTO.start > 1}">
		                    	<a href="/farmstory/admin/prodList.do?page=${pageGroupDTO.start - 1}"><</a>
		                    </c:if>
		                    
                        	<c:forEach var="i" begin="${pageGroupDTO.start}" end="${pageGroupDTO.end}">
                        		<li><a href="/farmstory/admin/prodList.do?page=${i}" class="${currentPage == i ? 'page_active' : ''}">[${i}]</a></li>
                        	</c:forEach>
                        
                        	<c:if test="${pageGroupDTO.end < lastPage}">
		                    	<a href="/farmstory/admin/prodList.do?page=${pageGroupDTO.end + 1}">></a>
		                    </c:if>
                        </ul>
                    </div>
                </section>
            </main>
			<%@ include file="/WEB-INF/views/inc/_admin_footer.jsp" %>
        </div>
    </body>
</html>
