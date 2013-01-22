package com.spark.cms.services.question;

import com.spark.cms.services.vo.QuestionCountVo;
import com.spark.cms.services.vo.QuestionVo;

public interface QuestionService {

	/**
	 * 提交问卷
	 */
	public abstract boolean createQuestion(QuestionVo qv);
	
	/**
	 * 统计问卷
	 */
	public abstract QuestionCountVo countQuestion(String beginDate,String endDate);

}