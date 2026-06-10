/**
 * 이벤트 처리
 */

document.addEventListener("DOMContentLoaded", function () {

    const calendarEl = document.getElementById("fc-calendar");

    const events = eventData.map(event => {

        let endDate = new Date(event.end);
        endDate.setDate(endDate.getDate() + 1);

        return {
            id: event.id,
            title: event.title,
            start: event.start,
            end: endDate.toISOString().split("T")[0],
			extendedProps: {
	            realEnd: event.end
	        }
        };
    });

    const calendar = new FullCalendar.Calendar(calendarEl, {

        initialView: "dayGridMonth",
        locale: "en",
        height: 600,

        headerToolbar: {
            left: "title",
            right: "today prev,next"
        },

        events: events,
		
		// 달력 클릭시 등록 모달창 출력
        dateClick: function(info) {

            if(!isAdmin) {
                return;
            }

            document.getElementById("eventTitle").value = "";
            document.getElementById("startDate").value = info.dateStr;
            document.getElementById("endDate").value = info.dateStr;

            document.getElementById("modalBg").style.display = "block";
            document.getElementById("eventModal").style.display = "block";
        },
		
		// 등록된 이벤트 클릭 수정 모달창 출력
		eventClick: function(info) {

		    if(!isAdmin) {
		        return;
		    }

		    document.getElementById("modifyEventNo").value = info.event.id;
		    document.getElementById("modifyTitle").value = info.event.title;
		    document.getElementById("modifyStartDate").value = info.event.startStr;
		    document.getElementById("modifyEndDate").value = info.event.extendedProps.realEnd;

		    document.getElementById("modalBg").style.display = "block";
		    document.getElementById("modifyModal").style.display = "block";
		}
    });

    calendar.render();
	
	document.getElementById("btnDeleteEvent").addEventListener("click", function() {

	    const eventNo = document.getElementById("modifyEventNo").value;

	    if(confirm("이벤트를 삭제하시겠습니까?")) {
	        location.href = path + "/event/delete.do?eventNo=" + eventNo;
	    }
	});
	
	function closeModal() {
		document.getElementById("modalBg").style.display = "none";
        document.getElementById("eventModal").style.display = "none";
		document.getElementById("modifyModal").style.display = "none";
	}

    document.getElementById("modalClose").addEventListener("click", closeModal);
    document.getElementById("modifyClose").addEventListener("click", closeModal);
});