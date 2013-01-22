/**
 * 
 */
package com.spark.front.form.payment;

/**
 * @author Jideas
 * 
 */
public class AliSubmitForm {
	
	private String service;

	private String partner;

	private String _input_charset = "gbk";

	private String sign_type = "MD5";

	private String sign;

	private String notify_url;

	private String return_url;

	private String error_notify_url;// 该功能需要联系技术支持开通。

	private String out_trade_no;//

	private String subject;//

	private String payment_type;//

	private String seller_email;// seller_account_name seller_email

	private String total_fee;// 交易金额

	private String paymethod;// directPay

	private String need_ctu_check;// N

	private String anti_phishing_key;// 防钓鱼时间戳

	private String exter_invoke_ip;//
	
	private String extra_common_param;
	

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getService() {
		return service;
	}

	public String getPartner() {
		return partner;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public String getSign() {
		return sign;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public String getError_notify_url() {
		return error_notify_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public String getPayment_type() {
		return payment_type;
	} 
	public String getTotal_fee() {
		return total_fee;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public String getNeed_ctu_check() {
		return need_ctu_check;
	}

	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setService(String service) {
		this.service = service;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}  

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public void setError_notify_url(String error_notify_url) {
		this.error_notify_url = error_notify_url;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	} 

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public void setNeed_ctu_check(String need_ctu_check) {
		this.need_ctu_check = need_ctu_check;
	}

	public void setAnti_phishing_key(String anti_phishing_key) {
		this.anti_phishing_key = anti_phishing_key;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}
}
