package com.br.lis.vo;

public class LibMemberVo {

	private String member_id;
	private String member_code;
	private String password;
	private String phone;
	private String name;
	private String address;
	private String email;
	private String auth;
	private String penalty;
	private String filepath;
	private int rental_count; //string -> int 수정
	private String notification;
	private String birth;
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_code() {
		return member_code;
	}
	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPenalty() {
		return penalty;
	}
	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getRental_count() {
		return rental_count;
	}
	public void setRental_count(int rental_count) {
		this.rental_count = rental_count;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "LibMemberVo [member_id=" + member_id + ", member_code=" + member_code + ", password=" + password
				+ ", phone=" + phone + ", name=" + name + ", address=" + address + ", email=" + email + ", auth=" + auth
				+ ", penalty=" + penalty + ", filepath=" + filepath + ", rental_count=" + rental_count
				+ ", notification=" + notification + ", birth=" + birth + "]";
	}
	public LibMemberVo(String member_id, String member_code, String password, String phone, String name, String address,
			String email, String auth, String penalty, String filepath, int rental_count, String notification,
			String birth) {
		super();
		this.member_id = member_id;
		this.member_code = member_code;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.email = email;
		this.auth = auth;
		this.penalty = penalty;
		this.filepath = filepath;
		this.rental_count = rental_count;
		this.notification = notification;
		this.birth = birth;
	}
	public LibMemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
