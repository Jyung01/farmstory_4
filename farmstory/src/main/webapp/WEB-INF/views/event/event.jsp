<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
  	<head>
	    <meta charset="UTF-8" />
	    <title>팜스토리::이벤트</title>
	    <link rel="stylesheet" href="${path}/css/crop.css" />
	    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
	    <script>
	    const path = '${path}';
	    const isAdmin = ${not empty sessUser and sessUser.role eq 'admin'};
	
	    const eventData = [
	        <c:forEach var="event" items="${eventList}" varStatus="status">
	        {
	            id:'${event.eventNo}',
	            title:'${event.title}',
	            start:'${event.startDate}',
	            end:'${event.endDate}'
	        }${status.last ? '' : ','}
	        </c:forEach>
	    ];
		</script>
		
		<script src="${path}/js/event.js"></script>
  	</head>
  	<body>
	    <div id="container">
	      	<!-- header import -->
	      	<jsp:include page="/WEB-INF/views/inc/_header.jsp"></jsp:include>
	      	<div id="crop">
	        	<div><img src="${path}/images/sub_top_tit3.png" alt="CROP TALK" /></div>
		        <section class="event">
		          	<aside>
			            <img src="${path}/images/sub_aside_cate4_tit.png" alt="이벤트" />
			            <ul class="lnb">
			              	<li class="on"><a href="#">이벤트</a></li>
			            </ul>
		          	</aside>
		          	<article id="board">
			            <nav>
			              	<img src="${path}/images/sub_nav_tit_cate4_tit1.png" alt="이벤트" />
			              	<p>HOME > 이벤트 > <em>이벤트</em></p>
			            </nav>
		            	<div id="fc-calendar" style="min-height: 500px; margin-top: 20px"></div>
		            	<!-- 관리자가 누르면 보여줄 모달창 -->
		            	<div id="modalBg">
		            		<div id="eventModal" style="display:none;">
							    <form action="${path}/event/write.do" method="post">
							        <h3>이벤트 등록</h3>
							        <p>제목<input type="text" name="title" id="eventTitle"></p>	
							        <p>시작일<input type="date" name="startDate" id="startDate"></p>				
							        <p>종료일<input type="date" name="endDate" id="endDate"></p>			
							        <div class="btnArea">
							            <input type="submit" value="등록">
							            <button type="button" id="modalClose">취소</button>
							        </div>
							    </form>
							</div>
							<div id="modifyModal">
							    <form action="${path}/event/modify.do" method="post">
							
							        <input type="hidden" name="eventNo" id="modifyEventNo">
							
							        <h3>이벤트 수정</h3>
							
							        <p>제목<input type="text" name="title" id="modifyTitle"></p>
							        <p>시작일<input type="date" name="startDate" id="modifyStartDate"></p>
							        <p>종료일<input type="date" name="endDate" id="modifyEndDate">
							        </p>
							        <div class="btnArea">
							            <button type="button" id="btnDeleteEvent">삭제</button>
							            <button type="button" id="modifyClose">취소</button>
							            <input type="submit" value="수정완료">
							        </div>
							
							    </form>
							</div>
		            	</div>
		          	</article>
		        </section>
	      </div>
	      <!-- footer import -->
	      <jsp:include page="/WEB-INF/views/inc/_footer.jsp"></jsp:include>
	    </div>
	  </body>
</html>
