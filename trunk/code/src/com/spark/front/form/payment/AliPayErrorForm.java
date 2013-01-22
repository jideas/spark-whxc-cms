/**
 * 
 */
package com.spark.front.form.payment;

/**
 * @author Jideas
 * 
 */
public class AliPayErrorForm {
	private String partner;
	private String out_trade_no;
	private String error_code;
	private String return_url;
	private String seller_email;

	public String getPartner() {
		return partner;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getError_code() {
		return error_code;
	}

	public String getReturn_url() {
		return return_url;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

}
