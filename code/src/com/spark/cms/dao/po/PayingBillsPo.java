/**
 * 
 */
package com.spark.cms.dao.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jideas
 * 
 */
@Entity
@Table(name = "CMS_PAYINGBILLS")
@SuppressWarnings("serial")
public class PayingBillsPo implements Serializable {
	private byte[] recid;
	private Long recver;
	private String payType;// ֧������
	private String amount;// ����ַ���
	private long createTime;// ��������
	private String orderId;// ֧������
	private String transDate;// ֧������
	private String status;// ����״̬
	private String relaBillsId;

	@Column(name = "relaBillsId")
	public String getRelaBillsId() {
		return relaBillsId;
	}

	public void setRelaBillsId(String relaBillsId) {
		this.relaBillsId = relaBillsId;
	}

	@Column(name = "payType")
	public String getPayType() {
		return payType;
	}

	@Column(name = "amount")
	public String getAmount() {
		return amount;
	}

	@Column(name = "createTime")
	public long getCreateTime() {
		return createTime;
	}

	@Column(name = "orderId")
	public String getOrderId() {
		return orderId;
	}

	@Column(name = "transDate")
	public String getTransDate() {
		return transDate;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
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

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}
}
