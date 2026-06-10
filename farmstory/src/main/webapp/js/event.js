/**
 * 이벤트 처리
 */

document.addEventListener("DOMContentLoaded", function () {

    const calendarEl = document.getElementById("fc-calendar");

    const calendar = new FullCalendar.Calendar(calendarEl, {

        initialView: "dayGridMonth",

        headerToolbar: {
            left: "title",
            right: "today prev,next"
        },

        events: eventData,

        dateClick: function(info) {

            if(!isAdmin) {
                return;
            }

            document.getElementById("eventTitle").value = "";
            document.getElementById("startDate").value = info.dateStr;
            document.getElementById("endDate").value = info.dateStr;
			
			document.getElementById("modalBg").style.display = "block";
            document.getElementById("eventModal").style.display = "block";
        }
    });

    calendar.render();

    document.getElementById("modalClose").addEventListener("click", function() {
		document.getElementById("modalBg").style.display = "none";
        document.getElementById("eventModal").style.display = "none";
    });
});