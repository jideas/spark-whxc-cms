package com.spark.cms.services.question;

import com.spark.cms.services.vo.QuestionCountVo;
import com.spark.cms.services.vo.QuestionVo;

public interface QuestionService {

	/**
	 * �ύ�ʾ�
	 */
	public abstract boolean createQuestion(QuestionVo qv);
	
	/**
	 * ͳ���ʾ�
	 */
	public abstract QuestionCountVo countQuestion(String beginDate,String endDate);

}