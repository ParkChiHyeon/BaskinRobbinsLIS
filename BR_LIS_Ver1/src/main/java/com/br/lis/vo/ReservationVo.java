package com.br.lis.vo;

public class ReservationVo {
	private LendingVo lending;
	private LibMemberVo libMember;
	private BookInfoVo bookInfo;
	private String lending_seq ;
	private String book_serial ;
	private String member_code ;
	private String phone       ;
	private String email       ;
	private String name        ;
	private String lending_date;
	private String reserve_date;
	private String rv;
	
	public String getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}
	

	public ReservationVo(String lending_seq, String book_serial, String member_code, String phone, String email,
			String name, String lending_date, String reserve_date, String rv) {
		super();
		this.lending_seq = lending_seq;
		this.book_serial = book_serial;
		this.member_code = member_code;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.lending_date = lending_date;
		this.reserve_date = reserve_date;
		this.rv = rv;
	}
	
	
	@Override
	public String toString() {
		return "ReservationVo [lending_seq=" + lending_seq + ", book_serial=" + book_serial + ", member_code="
				+ member_code + ", phone=" + phone + ", email=" + email + ", name=" + name + ", lending_date="
				+ lending_date + ", reserve_date=" + reserve_date + ", rv=" + rv + "]";
	}
	
	public ReservationVo() {
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLending_date() {
		return lending_date;
	}
	public void setLending_date(String lending_date) {
		this.lending_date = lending_date;
	}
	public String getRv() {
		return rv;
	}
	public void setRv(String rv) {
		this.rv = rv;
	}
	
	
}

