package kr.co.farmstory.dto.event;

public class EventDTO {
	private int eventNo;
	private String title;
	private String startDate;
	private String endDate;
	private String regDate;
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "EventDTO [eventNo=" + eventNo + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", regDate=" + regDate + "]";
	}
	
	
	
}
