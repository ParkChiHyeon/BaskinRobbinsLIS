package com.br.lis.vo;

public class BookInfoVo {

	private String isbn;
	private String title;
	private String publisher;
	private String author;
	private String total_count;
	private String category;
	private String book_serial;
	private String regdate;
	private String ld;
	private String rv;
	private String dl;
	private String ba;
	private String od;
	private String sp;
	private String dg;
	private String nm;
	private String wh;

	public BookInfoVo() {
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getAuthor() {
		return author;
	}

	public String getTotal_count() {
		return total_count;
	}

	public String getCategory() {
		return category;
	}

	public String getBook_serial() {
		return book_serial;
	}

	public String getRegdate() {
		return regdate;
	}

	public String getLd() {
		return ld;
	}

	public String getRv() {
		return rv;
	}

	public String getDl() {
		return dl;
	}

	public String getBa() {
		return ba;
	}

	public String getOd() {
		return od;
	}

	public String getSp() {
		return sp;
	}

	public String getDg() {
		return dg;
	}

	public String getNm() {
		return nm;
	}

	public String getWh() {
		return wh;
	}

	public void setLd(String ld) {
		this.ld = ld;
	}

	public void setRv(String rv) {
		this.rv = rv;
	}

	public void setDl(String dl) {
		this.dl = dl;
	}

	public void setOd(String od) {
		this.od = od;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public void setDg(String dg) {
		this.dg = dg;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setBook_serial(String book_serial) {
		this.book_serial = book_serial;
	}

	@Override
	public String toString() {
		return "BookInfoVo [isbn=" + isbn + ", title=" + title + ", publisher=" + publisher + ", author=" + author
				+ ", total_count=" + total_count + ", category=" + category + ", book_serial=" + book_serial
				+ ", regdate=" + regdate + ", ld=" + ld + ", rv=" + rv + ", dl=" + dl + ", ba=" + ba + ", od=" + od
				+ ", sp=" + sp + ", dg=" + dg + ", nm=" + nm + ", wh=" + wh + "]";
	}

	public BookInfoVo(String isbn, String title, String publisher, String author, String total_count, String category,
			String book_serial, String regdate, String ld, String rv, String dl, String ba, String od, String sp,
			String dg, String nm, String wh) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.total_count = total_count;
		this.category = category;
		this.book_serial = book_serial;
		this.regdate = regdate;
		this.ld = ld;
		this.rv = rv;
		this.dl = dl;
		this.ba = ba;
		this.od = od;
		this.sp = sp;
		this.dg = dg;
		this.nm = nm;
		this.wh = wh;
	}
	
	

}
