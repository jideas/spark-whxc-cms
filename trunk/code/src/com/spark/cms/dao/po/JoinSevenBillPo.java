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
@Table(name = "CMS_JOINUS")
@SuppressWarnings("serial")
public class JoinSevenBillPo implements java.io.Serializable {
	private byte[] recid;
	private Long recver;
	private String joinerName;// 姓名
	private String joinerSex;// 性别
	private String joinerAge;// 年龄
	private String maritalstatus;// 婚姻状况
	private String birthplace;// 籍贯
	private String culturalLevel;// 文化程度
	private String idCardNo;// 身份证号
	private String email;// email
	private String telephone;// 联系电话
	private String fax;// 传真
	private String qq;// qq
	private String msn;// msn
	private String mobile;// 手机号码
	private String livingAddress;// 居住地址
	private String storeAddress;// 详细店址
	private String storeLocation;// 所处商业街地段
	private String rentOfYear;// 全年租金
	private String sumCost;// 费用合计
	private String salesEmployee;// 销售人员
	private String businessWay;// 经营方式
	private String fund;// 投入资金
	private String funding;// 运作资金
	private String askStartDate;// 要求开业时间
	private String competitionSituation;// 竞争情况
	private String joinusReason;// 加盟原因
	private String experience;// 个人经验
	private String dispositionAndFamily;// 性格和家庭
	private String riskAndHope;// 风险与期待
	private long createDate;
	private boolean opered;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}
	@Column(name = "joinerName")
	public String getJoinerName() {
		return joinerName;
	}
	@Column(name = "joinerSex")
	public String getJoinerSex() {
		return joinerSex;
	}
	@Column(name = "joinerAge")
	public String getJoinerAge() {
		return joinerAge;
	}
	@Column(name = "maritalstatus")
	public String getMaritalstatus() {
		return maritalstatus;
	}
	@Column(name = "birthplace")
	public String getBirthplace() {
		return birthplace;
	}
	@Column(name = "culturalLevel")
	public String getCulturalLevel() {
		return culturalLevel;
	}
	@Column(name = "idCardNo")
	public String getIdCardNo() {
		return idCardNo;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}
	@Column(name = "fax")
	public String getFax() {
		return fax;
	}
	@Column(name = "qq")
	public String getQq() {
		return qq;
	}
	@Column(name = "msn")
	public String getMsn() {
		return msn;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "livingAddress")
	public String getLivingAddress() {
		return livingAddress;
	}
	@Column(name = "storeAddress")
	public String getStoreAddress() {
		return storeAddress;
	}
	@Column(name = "storeLocation")
	public String getStoreLocation() {
		return storeLocation;
	}
	@Column(name = "rentOfYear")
	public String getRentOfYear() {
		return rentOfYear;
	}
	@Column(name = "sumCost")
	public String getSumCost() {
		return sumCost;
	}
	@Column(name = "salesEmployee")
	public String getSalesEmployee() {
		return salesEmployee;
	}
	@Column(name = "businessWay")
	public String getBusinessWay() {
		return businessWay;
	}
	@Column(name = "fund")
	public String getFund() {
		return fund;
	}
	@Column(name = "funding")
	public String getFunding() {
		return funding;
	}
	@Column(name = "askStartDate")
	public String getAskStartDate() {
		return askStartDate;
	}
	@Column(name = "competitionSituation")
	public String getCompetitionSituation() {
		return competitionSituation;
	}
	@Column(name = "joinusReason")
	public String getJoinusReason() {
		return joinusReason;
	}
	@Column(name = "experience")
	public String getExperience() {
		return experience;
	}
	@Column(name = "dispositionAndFamily")
	public String getDispositionAndFamily() {
		return dispositionAndFamily;
	}
	@Column(name = "riskAndHope")
	public String getRiskAndHope() {
		return riskAndHope;
	}
	@Column(name = "createDate")
	public long getCreateDate() {
		return createDate;
	}
	@Column(name = "opered")
	public boolean isOpered() {
		return opered;
	}
	public void setOpered(boolean opered) {
		this.opered = opered;
	}
	public void setRecid(byte[] recid) {
		this.recid = recid;
	}
	public void setJoinerName(String joinerName) {
		this.joinerName = joinerName;
	}
	public void setJoinerSex(String joinerSex) {
		this.joinerSex = joinerSex;
	}
	public void setJoinerAge(String joinerAge) {
		this.joinerAge = joinerAge;
	}
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public void setCulturalLevel(String culturalLevel) {
		this.culturalLevel = culturalLevel;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setLivingAddress(String livingAddress) {
		this.livingAddress = livingAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public void setRentOfYear(String rentOfYear) {
		this.rentOfYear = rentOfYear;
	}
	public void setSumCost(String sumCost) {
		this.sumCost = sumCost;
	}
	public void setSalesEmployee(String salesEmployee) {
		this.salesEmployee = salesEmployee;
	}
	public void setBusinessWay(String businessWay) {
		this.businessWay = businessWay;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public void setFunding(String funding) {
		this.funding = funding;
	}
	public void setAskStartDate(String askStartDate) {
		this.askStartDate = askStartDate;
	}
	public void setCompetitionSituation(String competitionSituation) {
		this.competitionSituation = competitionSituation;
	}
	public void setJoinusReason(String joinusReason) {
		this.joinusReason = joinusReason;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public void setDispositionAndFamily(String dispositionAndFamily) {
		this.dispositionAndFamily = dispositionAndFamily;
	}
	public void setRiskAndHope(String riskAndHope) {
		this.riskAndHope = riskAndHope;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public void setRecver(Long recver) {
		this.recver = recver;
	}

}
