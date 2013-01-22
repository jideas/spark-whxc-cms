/**
 * 
 */
package com.spark.cms.dao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jideas
 * 
 */
@Entity
@Table(name = "SA_SerialNumber")
@SuppressWarnings("serial")
public class SerialNumberPo implements java.io.Serializable {

	private byte[] recid;

	private Long recver;

	private String type;

	private String prefix;
	
	private long serial;

	private long createTime;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	@Column(name = "PREFIX")
	public String getPrefix() {
		return prefix;
	}
	@Column(name = "SERIAL")
	public long getSerial() {
		return serial;
	}
	@Column(name = "CREATETIME")
	public long getCreateTime() {
		return createTime;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	} 
}
