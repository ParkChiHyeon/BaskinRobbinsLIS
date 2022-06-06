package com.br.lis.vo;

public class PurchaseVo {

	private String purchase_code;
	private String total_price;
	private String total_ea;
	private String order_date;
	private String distributor_email;
	private String confirm;
	private String confirm_date;

	public PurchaseVo() {
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getTotal_ea() {
		return total_ea;
	}

	public void setTotal_ea(String total_ea) {
		this.total_ea = total_ea;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getDistributor_email() {
		return distributor_email;
	}

	public void setDistributor_email(String distributor_email) {
		this.distributor_email = distributor_email;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getConfirm_date() {
		return confirm_date;
	}

	public void setConfirm_date(String confirm_date) {
		this.confirm_date = confirm_date;
	}

	public String getPurchase_code() {
		return purchase_code;
	}

	public void setPurchase_code(String purchase_code) {
		this.purchase_code = purchase_code;
	}

	@Override
	public String toString() {
		return "PurchaseVo [purchase_code=" + purchase_code + ", total_price=" + total_price + ", total_ea=" + total_ea
				+ ", order_date=" + order_date + ", distributor_email=" + distributor_email + ", confirm=" + confirm
				+ ", confirm_date=" + confirm_date + "]";
	}

	public PurchaseVo(String purchase_code, String total_price, String total_ea, String order_date,
			String distributor_email, String confirm, String confirm_date) {
		super();
		this.purchase_code = purchase_code;
		this.total_price = total_price;
		this.total_ea = total_ea;
		this.order_date = order_date;
		this.distributor_email = distributor_email;
		this.confirm = confirm;
		this.confirm_date = confirm_date;
	}
	
	

}
