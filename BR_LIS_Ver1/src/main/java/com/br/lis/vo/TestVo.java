package com.br.lis.vo;

public class TestVo {

	private String isbn;
	private String title;
	private String publisher;
	private String author;
	private String total_count;
	private String category;

	public TestVo() {
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

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "TestVo [isbn=" + isbn + ", title=" + title + ", publisher=" + publisher + ", author=" + author
				+ ", total_count=" + total_count + ", category=" + category + "]";
	}

}
