package com.spark.cms.dao.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsGoods entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cms_question")
@SuppressWarnings("serial")
public class QuestionPo implements java.io.Serializable {

	// Fields
	private byte[] recid;
	private Long recver;
	private String questionCreatorId;
	private String questionCreator;
	private Date questionDate;
	
	private String question1;
	private String question2;
	private String question3;
	
	private String question401;
	private String question402;
	private String question403;
	private String question404;
	private String question405;
	private String question406;
	private String question407;
	private String question408;
	private String question409;
	private String question410;
	private String question411;
	
	private String question5;
	
	private String question601;
	private String question602;
	private String question603;
	private String question604;
	private String question605;
	private String question606;
	private String question607;
	private String question608;
	private String question609;
	private String question610;
	private String question611;
	
	private String question7;
	
	private String question801;
	private String question802;
	private String question803;
	private String question804;
	private String question805;
	private String question806;
	private String question807;
	private String question808;
	
	private String question9;

	@Id
	@Column(name = "RECID", unique = true, nullable = false)
	public byte[] getRecid() {
		return recid;
	}
	
	public void setRecid(byte[] recid) {
		this.recid = recid;
	}

	@Column(name = "RECVER")
	public Long getRecver() {
		return recver;
	}

	public void setRecver(Long recver) {
		this.recver = recver;
	}

	@Column(name = "QUESTIONCREATORID")
	public String getQuestionCreatorId() {
		return questionCreatorId;
	}
	
	public void setQuestionCreatorId(String questionCreatorId) {
		this.questionCreatorId = questionCreatorId;
	}

	@Column(name = "QUESTIONCREATOR")
	public String getQuestionCreator() {
		return questionCreator;
	}

	public void setQuestionCreator(String questionCreator) {
		this.questionCreator = questionCreator;
	}

	@Column(name = "QUESTIONDATE")
	public Date getQuestionDate() {
		return questionDate;
	}

	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}

	@Column(name = "QUESTION1")
	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	@Column(name = "QUESTION2")
	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	@Column(name = "QUESTION3")
	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	@Column(name = "QUESTION401")
	public String getQuestion401() {
		return question401;
	}

	public void setQuestion401(String question401) {
		this.question401 = question401;
	}

	@Column(name = "QUESTION402")
	public String getQuestion402() {
		return question402;
	}

	public void setQuestion402(String question402) {
		this.question402 = question402;
	}

	@Column(name = "QUESTION403")
	public String getQuestion403() {
		return question403;
	}

	public void setQuestion403(String question403) {
		this.question403 = question403;
	}

	@Column(name = "QUESTION404")
	public String getQuestion404() {
		return question404;
	}

	public void setQuestion404(String question404) {
		this.question404 = question404;
	}

	@Column(name = "QUESTION405")
	public String getQuestion405() {
		return question405;
	}

	public void setQuestion405(String question405) {
		this.question405 = question405;
	}

	@Column(name = "QUESTION406")
	public String getQuestion406() {
		return question406;
	}

	public void setQuestion406(String question406) {
		this.question406 = question406;
	}

	@Column(name = "QUESTION407")
	public String getQuestion407() {
		return question407;
	}

	public void setQuestion407(String question407) {
		this.question407 = question407;
	}

	@Column(name = "QUESTION408")
	public String getQuestion408() {
		return question408;
	}

	public void setQuestion408(String question408) {
		this.question408 = question408;
	}

	@Column(name = "QUESTION409")
	public String getQuestion409() {
		return question409;
	}

	public void setQuestion409(String question409) {
		this.question409 = question409;
	}

	@Column(name = "QUESTION410")
	public String getQuestion410() {
		return question410;
	}

	public void setQuestion410(String question410) {
		this.question410 = question410;
	}

	@Column(name = "QUESTION411")
	public String getQuestion411() {
		return question411;
	}

	public void setQuestion411(String question411) {
		this.question411 = question411;
	}

	@Column(name = "QUESTION5")
	public String getQuestion5() {
		return question5;
	}

	public void setQuestion5(String question5) {
		this.question5 = question5;
	}

	@Column(name = "QUESTION601")
	public String getQuestion601() {
		return question601;
	}

	public void setQuestion601(String question601) {
		this.question601 = question601;
	}

	@Column(name = "QUESTION602")
	public String getQuestion602() {
		return question602;
	}

	public void setQuestion602(String question602) {
		this.question602 = question602;
	}

	@Column(name = "QUESTION603")
	public String getQuestion603() {
		return question603;
	}

	public void setQuestion603(String question603) {
		this.question603 = question603;
	}

	@Column(name = "QUESTION604")
	public String getQuestion604() {
		return question604;
	}

	public void setQuestion604(String question604) {
		this.question604 = question604;
	}

	@Column(name = "QUESTION605")
	public String getQuestion605() {
		return question605;
	}

	public void setQuestion605(String question605) {
		this.question605 = question605;
	}

	@Column(name = "QUESTION606")
	public String getQuestion606() {
		return question606;
	}

	public void setQuestion606(String question606) {
		this.question606 = question606;
	}

	@Column(name = "QUESTION607")
	public String getQuestion607() {
		return question607;
	}

	public void setQuestion607(String question607) {
		this.question607 = question607;
	}

	@Column(name = "QUESTION608")
	public String getQuestion608() {
		return question608;
	}

	public void setQuestion608(String question608) {
		this.question608 = question608;
	}

	@Column(name = "QUESTION609")
	public String getQuestion609() {
		return question609;
	}

	public void setQuestion609(String question609) {
		this.question609 = question609;
	}

	@Column(name = "QUESTION610")
	public String getQuestion610() {
		return question610;
	}

	public void setQuestion610(String question610) {
		this.question610 = question610;
	}

	@Column(name = "QUESTION611")
	public String getQuestion611() {
		return question611;
	}

	public void setQuestion611(String question611) {
		this.question611 = question611;
	}

	@Column(name = "QUESTION7")
	public String getQuestion7() {
		return question7;
	}

	public void setQuestion7(String question7) {
		this.question7 = question7;
	}

	@Column(name = "QUESTION801")
	public String getQuestion801() {
		return question801;
	}

	public void setQuestion801(String question801) {
		this.question801 = question801;
	}

	@Column(name = "QUESTION802")
	public String getQuestion802() {
		return question802;
	}

	public void setQuestion802(String question802) {
		this.question802 = question802;
	}

	@Column(name = "QUESTION803")
	public String getQuestion803() {
		return question803;
	}

	public void setQuestion803(String question803) {
		this.question803 = question803;
	}

	@Column(name = "QUESTION804")
	public String getQuestion804() {
		return question804;
	}

	public void setQuestion804(String question804) {
		this.question804 = question804;
	}

	@Column(name = "QUESTION805")
	public String getQuestion805() {
		return question805;
	}

	public void setQuestion805(String question805) {
		this.question805 = question805;
	}

	@Column(name = "QUESTION806")
	public String getQuestion806() {
		return question806;
	}

	public void setQuestion806(String question806) {
		this.question806 = question806;
	}

	@Column(name = "QUESTION807")
	public String getQuestion807() {
		return question807;
	}

	public void setQuestion807(String question807) {
		this.question807 = question807;
	}

	@Column(name = "QUESTION808")
	public String getQuestion808() {
		return question808;
	}

	public void setQuestion808(String question808) {
		this.question808 = question808;
	}

	@Column(name = "QUESTION9")
	public String getQuestion9() {
		return question9;
	}

	public void setQuestion9(String question9) {
		this.question9 = question9;
	}

}
