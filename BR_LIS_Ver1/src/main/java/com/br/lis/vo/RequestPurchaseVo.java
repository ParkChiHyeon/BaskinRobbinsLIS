package com.br.lis.vo;

public class RequestPurchaseVo {

	private String wish_serial;
	private String member_id;
	private String purchase_code;
	private String isbn;
	private String title;
	private String publisher;
	private String author;
	private String translator;
	private String price;
	private String ea;
	private String confirm;
	private String history;
	private String receive_date;

	public RequestPurchaseVo() {
	}

	public String getWish_serial() {
		return wish_serial;
	}

	public void setWish_serial(String wish_serial) {
		this.wish_serial = wish_serial;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPurchase_code() {
		return purchase_code;
	}

	public void setPurchase_code(String purchase_code) {
		this.purchase_code = purchase_code;
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

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getEa() {
		return ea;
	}

	public void setEa(String ea) {
		this.ea = ea;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(String receive_date) {
		this.receive_date = receive_date;
	}

	@Override
	public String toString() {
		return "RequestPurchaseVo [wish_serial=" + wish_serial + ", member_id=" + member_id + ", purchase_code="
				+ purchase_code + ", isbn=" + isbn + ", title=" + title + ", publisher=" + publisher + ", author="
				+ author + ", translator=" + translator + ", price=" + price + ", ea=" + ea + ", confirm=" + confirm
				+ ", history=" + history + ", receive_date=" + receive_date + "]";
	}

	public RequestPurchaseVo(String wish_serial, String member_id, String purchase_code, String isbn, String title,
			String publisher, String author, String translator, String price, String ea, String confirm, String history,
			String receive_date) {
		super();
		this.wish_serial = wish_serial;
		this.member_id = member_id;
		this.purchase_code = purchase_code;
		this.isbn = isbn;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.translator = translator;
		this.price = price;
		this.ea = ea;
		this.confirm = confirm;
		this.history = history;
		this.receive_date = receive_date;
	}
	
	

}
