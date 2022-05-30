package com.br.lis.vo;

public class CalendarBoardVo {

	private String calendar_seq;
	private String admin_id;
	private String title;
	private String content;
	private String regdate;
	private String start_date;
	private String end_date;
	
	public CalendarBoardVo() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getCalendar_seq() {
		return calendar_seq;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	@Override
	public String toString() {
		return "CalendarBoardVo [calendar_seq=" + calendar_seq + ", admin_id=" + admin_id + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", start_date=" + start_date + ", end_date="
				+ end_date + "]";
	}
	
	

}
