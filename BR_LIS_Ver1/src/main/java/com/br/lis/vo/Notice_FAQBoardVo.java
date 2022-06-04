package com.br.lis.vo;

public class Notice_FAQBoardVo {

	private String notice_seq;
	private String faq_seq;

	private String admin_id;
	private String title;
	private String content;
	private String regdate;
	
	private String file_path;

	public Notice_FAQBoardVo() {
	}

	public String getNotice_seq() {
		return notice_seq;
	}

	public void setNotice_seq(String notice_seq) {
		this.notice_seq = notice_seq;
	}

	public String getFaq_seq() {
		return faq_seq;
	}

	public void setFaq_seq(String faq_seq) {
		this.faq_seq = faq_seq;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
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

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	@Override
	public String toString() {
		return "Notice_FAQBoardVo [notice_seq=" + notice_seq + ", faq_seq=" + faq_seq + ", admin_id=" + admin_id
				+ ", title=" + title + ", content=" + content + ", regdate=" + regdate + ", file_path=" + file_path
				+ "]";
	}

	public Notice_FAQBoardVo(String notice_seq, String faq_seq, String admin_id, String title, String content,
			String regdate, String file_path) {
		super();
		this.notice_seq = notice_seq;
		this.faq_seq = faq_seq;
		this.admin_id = admin_id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.file_path = file_path;
	}

	
}
