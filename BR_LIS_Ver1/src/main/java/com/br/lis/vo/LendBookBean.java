package com.br.lis.vo;

public class LendBookBean {
	
	private LendingVo lending;
	private BookInfoVo bookInfo;
	private Possessing_BookVo possessing_Book;
	private String lending_seq;
	private String member_code;
	private String isbn;
	private String title;
	private String publisher;
	private String author;
	private String book_serial;
	
	private String lending_delay;
	private String lending_date;
	private String end_date;
	private String back_date;

	
	
	
	
	public String getLending_seq() {
		return lending_seq;
	}
	public void setLending_seq(String lending_seq) {
		this.lending_seq = lending_seq;
	}
	public String getMember_code() {
		return member_code;
	}
	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBook_serial() {
		return book_serial;
	}
	public void setBook_serial(String book_serial) {
		this.book_serial = book_serial;
	}
	public String getLending_delay() {
		return lending_delay;
	}
	public void setLending_delay(String lending_delay) {
		this.lending_delay = lending_delay;
	}
	public String getLending_date() {
		return lending_date;
	}
	public void setLending_date(String lending_date) {
		this.lending_date = lending_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getBack_date() {
		return back_date;
	}
	public void setBack_date(String back_date) {
		this.back_date = back_date;
	}
	

	@Override
	public String toString() {
		return "LendBookBean [lending_seq=" + lending_seq + ", member_code=" + member_code + ", isbn=" + isbn
				+ ", title=" + title + ", publisher=" + publisher + ", author=" + author + ", book_serial="
				+ book_serial + ", lending_delay=" + lending_delay + ", lending_date=" + lending_date + ", end_date="
				+ end_date + ", back_date=" + back_date + "]";
	}
	
	
	public LendBookBean(String lending_seq, String member_code, String isbn, String title, String publisher,
			String author, String book_serial, String lending_delay, String lending_date, String end_date,
			String back_date) {
		super();
		this.lending_seq = lending_seq;
		this.member_code = member_code;
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.book_serial = book_serial;
		this.lending_delay = lending_delay;
		this.lending_date = lending_date;
		this.end_date = end_date;
		this.back_date = back_date;
	}
	public LendBookBean() {
	}
	
	
	
	
	

}
