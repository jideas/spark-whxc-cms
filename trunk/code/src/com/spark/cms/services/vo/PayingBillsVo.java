/**
 * 
 */
package com.spark.cms.services.vo;

import java.io.Serializable;

/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
public class PayingBillsVo implements Serializable {
	private String recid;
	private Long recver;
	private String payType;// ֧������
	private String amount;// ����ַ���
	private long createTime;// ��������
	private String orderId;// ֧������
	private String transDate;// ֧������
	private String status;// ����״̬

	private String relaBillsId;

	public String getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(String relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public String getPayType() {
		return payType;
	}

	public String getAmount() {
		return amount;
	}

	public long getCreateTime() {
		return createTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getTransDate() {
		return transDate;
	}

	public String getStatus() {
		return status;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
