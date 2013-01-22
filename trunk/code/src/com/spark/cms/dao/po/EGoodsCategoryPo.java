package com.spark.cms.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "psi_base_goodscategory")
@SuppressWarnings("serial")
public class EGoodsCategoryPo implements java.io.Serializable {
	private byte[] recid;
	private Long recver;
	private String categoryno;
	private String categoryname;
	private byte[] parentid;
	private String path;
	private Date createdate;
	private boolean leafflag;
	public EGoodsCategoryPo(byte[] recid, Long recver, String categoryno,
			String categoryname, byte[] parentid, String path, Date createdate) {
		super();
		this.recid = recid;
		this.recver = recver;
		this.categoryno = categoryno;
		this.categoryname = categoryname;
		this.parentid = parentid;
		this.path = path;
		this.createdate = createdate;
	}
	
	public EGoodsCategoryPo(byte[] recid) {
		this.recid = recid;
	}
	
	public EGoodsCategoryPo() {
		
	}
	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}

	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}
	@Column(name = "CATEGORYNO", length = 25)
	public String getCategoryno() {
		return categoryno;
	}

	public void setCategoryno(String categoryno) {
		this.categoryno = categoryno;
	}
	@Column(name = "CATEGORYNAME", length = 100)
	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	@Column(name = "PARENTID")
	public byte[] getParentid() {
		return parentid;
	}

	public void setParentid(byte[] parentid) {
		this.parentid = parentid;
	}
	@Column(name = "PATH")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE", length = 19)
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	@Column(name = "LEAFFLAG")
	public boolean isLeafflag() {
		return leafflag;
	}

	public void setLeafflag(boolean leafflag) {
		this.leafflag = leafflag;
	}

	
}
