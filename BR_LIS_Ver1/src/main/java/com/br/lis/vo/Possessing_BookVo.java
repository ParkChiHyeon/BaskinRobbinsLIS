package com.br.lis.vo;

public class Possessing_BookVo {
	private String book_serial;
	private String isbn;
	private String rv;
	
	
	public String getBook_serial() {
		return book_serial;
	}
	public void setBook_serial(String book_serial) {
		this.book_serial = book_serial;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getRv() {
		return rv;
	}
	public void setRv(String rv) {
		this.rv = rv;
	}
	@Override
	public String toString() {
		return "Possessing_Book [book_serial=" + book_serial + ", isbn=" + isbn + ", rv=" + rv + "]";
	}
	public Possessing_BookVo(String book_serial, String isbn, String rv) {
		super();
		this.book_serial = book_serial;
		this.isbn = isbn;
		this.rv = rv;
	}
	public Possessing_BookVo() {
		super();
	}
	
	
	

}
