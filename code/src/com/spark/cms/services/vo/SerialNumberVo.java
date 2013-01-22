/**
 * 
 */
package com.spark.cms.services.vo;


/**
 * @author Jideas
 * 
 */
@SuppressWarnings("serial")
public class SerialNumberVo implements java.io.Serializable {

	private String recid;

	private Long recver;

	private String type;

	private String prefix;

	private long serial;

	private long createDate;

	public String getRecid() {
		return recid;
	}

	public Long getRecver() {
		return recver;
	}

	public String getType() {
		return type;
	}

	public String getPrefix() {
		return prefix;
	}

	public long getSerial() {
		return serial;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setRecid(String recid) {
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

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
