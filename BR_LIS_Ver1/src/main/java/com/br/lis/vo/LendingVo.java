package com.br.lis.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LendingVo {

	private String lending_seq;
	private String book_serial;
	private String member_code;
	private String lending_delay;
	private String return_status;
	private String lending_date;
//	private String end_date;
//	private String back_date;
	@DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
	private Date end_date;
	@DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
	private Date back_date;
	private String reserve_date;
	
	public String getLending_seq() {
		return lending_seq;
	}
	public void setLending_seq(String lending_seq) {
		this.lending_seq = lending_seq;
	}
	public String getBook_serial() {
		return book_serial;
	}
	public void setBook_serial(String book_serial) {
		this.book_serial = book_serial;
	}
	public String getMember_code() {
		return member_code;
	}
	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}
	public String getLending_delay() {
		return lending_delay;
	}
	public void setLending_delay(String lending_delay) {
		this.lending_delay = lending_delay;
	}
	public String getReturn_status() {
		return return_status;
	}
	public void setReturn_status(String return_status) {
		this.return_status = return_status;
	}
	public String getLending_date() {
		return lending_date;
	}
	public void setLending_date(String lending_date) {
		this.lending_date = lending_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getBack_date() {
		return back_date;
	}
	public void setBack_date(Date back_date) {
		this.back_date = back_date;
	}
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	@Override
	public String toString() {
		return "LendingVo [lending_seq=" + lending_seq + ", book_serial=" + book_serial + ", member_code=" + member_code
				+ ", lending_delay=" + lending_delay + ", return_status=" + return_status + ", lending_date="
				+ lending_date + ", end_date=" + end_date + ", back_date=" + back_date + ", reserve_date="
				+ reserve_date + "]";
	}
	public LendingVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LendingVo(String lending_seq, String book_serial, String member_code, String lending_delay,
			String return_status, String lending_date, Date end_date, Date back_date, String reserve_date) {
		super();
		this.lending_seq = lending_seq;
		this.book_serial = book_serial;
		this.member_code = member_code;
		this.lending_delay = lending_delay;
		this.return_status = return_status;
		this.lending_date = lending_date;
		this.end_date = end_date;
		this.back_date = back_date;
		this.reserve_date = reserve_date;
	}

	

}
