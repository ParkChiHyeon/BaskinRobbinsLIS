package com.br.lis.vo;

public class AdminVo {
	
	private String admin_id;
	private String password;
	private String hiredate;
	
	public AdminVo() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	@Override
	public String toString() {
		return "AdminVo [admin_id=" + admin_id + ", password=" + password + ", hiredate=" + hiredate + "]";
	}

	public AdminVo(String admin_id, String password, String hiredate) {
		super();
		this.admin_id = admin_id;
		this.password = password;
		this.hiredate = hiredate;
	}
	
	
	

}
