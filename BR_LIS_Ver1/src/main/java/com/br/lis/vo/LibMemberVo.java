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
	private String rental_count;
	private String notification;
	private String birth;

	public LibMemberVo() {
	}

	public String getMember_id() {
		return member_id;
	}

	public String getMember_code() {
		return member_code;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getAuth() {
		return auth;
	}

	public String getPenalty() {
		return penalty;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getRental_count() {
		return rental_count;
	}

	public String getNotification() {
		return notification;
	}

	public String getBirth() {
		return birth;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setRental_count(String rental_count) {
		this.rental_count = rental_count;
	}

	public void setNotification(String notification) {
		this.notification = notification;
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

}
