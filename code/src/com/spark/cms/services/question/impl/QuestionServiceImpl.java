package com.spark.cms.services.question.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.base.hibernate.GenericDAO;
import com.spark.base.type.GUID;
import com.spark.cms.dao.po.QuestionPo;
import com.spark.cms.services.question.QuestionService;
import com.spark.cms.services.vo.QuestionCountVo;
import com.spark.cms.services.vo.QuestionVo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private GenericDAO baseDAO;
	
	/**
	 * 统计问卷
	 */
	@Override
	public QuestionCountVo countQuestion(String beginDate, String endDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		
		sb.append(" SUM(CASE WHEN  q.QUESTION1 = '1' THEN 1 ELSE 0 END) AS q101,");
		sb.append(" SUM(CASE WHEN  q.QUESTION1 = '2' THEN 1 ELSE 0 END) AS q102,");
		sb.append(" SUM(CASE WHEN  q.QUESTION1 = '3' THEN 1 ELSE 0 END) AS q103,");
		sb.append(" SUM(CASE WHEN  q.QUESTION1 = '4' THEN 1 ELSE 0 END) AS q104,");
		
		sb.append(" SUM(CASE WHEN  q.QUESTION2 = '1' THEN 1 ELSE 0 END) AS q201,");
		sb.append(" SUM(CASE WHEN  q.QUESTION2 = '2' THEN 1 ELSE 0 END) AS q202,");
		sb.append(" SUM(CASE WHEN  q.QUESTION2 = '3' THEN 1 ELSE 0 END) AS q203,");
		sb.append(" SUM(CASE WHEN  q.QUESTION2 = '4' THEN 1 ELSE 0 END) AS q204,");
		sb.append(" SUM(CASE WHEN  q.QUESTION2 = '5' THEN 1 ELSE 0 END) AS q205,");
		
		sb.append(" SUM(CASE WHEN  q.QUESTION3 = '1' THEN 1 ELSE 0 END) AS q301,");
		sb.append(" SUM(CASE WHEN  q.QUESTION3 = '2' THEN 1 ELSE 0 END) AS q302,");
		sb.append(" SUM(CASE WHEN  q.QUESTION3 = '3' THEN 1 ELSE 0 END) AS q303,");
		sb.append(" SUM(CASE WHEN  q.QUESTION3 = '4' THEN 1 ELSE 0 END) AS q304,");
		sb.append(" SUM(CASE WHEN  q.QUESTION3 = '5' THEN 1 ELSE 0 END) AS q305,");
		
		sb.append(" SUM(CASE WHEN  q.QUESTION401 = '1' THEN 1 ELSE 0 END) AS q401,");
		sb.append(" SUM(CASE WHEN  q.QUESTION402 = '1' THEN 1 ELSE 0 END) AS q402,");
		sb.append(" SUM(CASE WHEN  q.QUESTION403 = '1' THEN 1 ELSE 0 END) AS q403,");
		sb.append(" SUM(CASE WHEN  q.QUESTION404 = '1' THEN 1 ELSE 0 END) AS q404,");
		sb.append(" SUM(CASE WHEN  q.QUESTION405 = '1' THEN 1 ELSE 0 END) AS q405,");
		sb.append(" SUM(CASE WHEN  q.QUESTION406 = '1' THEN 1 ELSE 0 END) AS q406,");
		sb.append(" SUM(CASE WHEN  q.QUESTION407 = '1' THEN 1 ELSE 0 END) AS q407,");
		sb.append(" SUM(CASE WHEN  q.QUESTION408 = '1' THEN 1 ELSE 0 END) AS q408,");
		sb.append(" SUM(CASE WHEN  q.QUESTION409 = '1' THEN 1 ELSE 0 END) AS q409,");
		sb.append(" SUM(CASE WHEN  q.QUESTION410 = '1' THEN 1 ELSE 0 END) AS q410,");
		sb.append(" SUM(CASE WHEN  q.QUESTION411 = '1' THEN 1 ELSE 0 END) AS q411,");		

		sb.append(" SUM(CASE WHEN  q.QUESTION5 = '1' THEN 1 ELSE 0 END) AS q501,");
		sb.append(" SUM(CASE WHEN  q.QUESTION5 = '2' THEN 1 ELSE 0 END) AS q502,");
		sb.append(" SUM(CASE WHEN  q.QUESTION5 = '3' THEN 1 ELSE 0 END) AS q503,");
		sb.append(" SUM(CASE WHEN  q.QUESTION5 = '4' THEN 1 ELSE 0 END) AS q504,");
		sb.append(" SUM(CASE WHEN  q.QUESTION5 = '5' THEN 1 ELSE 0 END) AS q505,");
		
		sb.append(" SUM(CASE WHEN  q.QUESTION601 = '1' THEN 1 ELSE 0 END) AS q601,");
		sb.append(" SUM(CASE WHEN  q.QUESTION602 = '1' THEN 1 ELSE 0 END) AS q602,");
		sb.append(" SUM(CASE WHEN  q.QUESTION603 = '1' THEN 1 ELSE 0 END) AS q603,");
		sb.append(" SUM(CASE WHEN  q.QUESTION604 = '1' THEN 1 ELSE 0 END) AS q604,");
		sb.append(" SUM(CASE WHEN  q.QUESTION605 = '1' THEN 1 ELSE 0 END) AS q605,");
		sb.append(" SUM(CASE WHEN  q.QUESTION606 = '1' THEN 1 ELSE 0 END) AS q606,");
		sb.append(" SUM(CASE WHEN  q.QUESTION607 = '1' THEN 1 ELSE 0 END) AS q607,");
		sb.append(" SUM(CASE WHEN  q.QUESTION608 = '1' THEN 1 ELSE 0 END) AS q608,");
		sb.append(" SUM(CASE WHEN  q.QUESTION609 = '1' THEN 1 ELSE 0 END) AS q609,");
		sb.append(" SUM(CASE WHEN  q.QUESTION610 = '1' THEN 1 ELSE 0 END) AS q610,");
		sb.append(" SUM(CASE WHEN  q.QUESTION611 = '1' THEN 1 ELSE 0 END) AS q611,");

		sb.append(" SUM(CASE WHEN  q.QUESTION7 = '1' THEN 1 ELSE 0 END) AS q701,");
		sb.append(" SUM(CASE WHEN  q.QUESTION7 = '2' THEN 1 ELSE 0 END) AS q702,");
		sb.append(" SUM(CASE WHEN  q.QUESTION7 = '3' THEN 1 ELSE 0 END) AS q703,");
		sb.append(" SUM(CASE WHEN  q.QUESTION7 = '4' THEN 1 ELSE 0 END) AS q704,");
		sb.append(" SUM(CASE WHEN  q.QUESTION7 = '5' THEN 1 ELSE 0 END) AS q705,");

		sb.append(" SUM(CASE WHEN  q.QUESTION801 = '1' THEN 1 ELSE 0 END) AS q801,");
		sb.append(" SUM(CASE WHEN  q.QUESTION802 = '1' THEN 1 ELSE 0 END) AS q802,");
		sb.append(" SUM(CASE WHEN  q.QUESTION803 = '1' THEN 1 ELSE 0 END) AS q803,");
		sb.append(" SUM(CASE WHEN  q.QUESTION804 = '1' THEN 1 ELSE 0 END) AS q804,");
		sb.append(" SUM(CASE WHEN  q.QUESTION805 = '1' THEN 1 ELSE 0 END) AS q805,");
		sb.append(" SUM(CASE WHEN  q.QUESTION806 = '1' THEN 1 ELSE 0 END) AS q806,");
		sb.append(" SUM(CASE WHEN  q.QUESTION807 = '1' THEN 1 ELSE 0 END) AS q807,");
		sb.append(" SUM(CASE WHEN  q.QUESTION808 = '1' THEN 1 ELSE 0 END) AS q808");
		
		sb.append(" FROM cms_question q");
		sb.append(" WHERE 1 = 1");
		if(beginDate != null && beginDate != "") sb.append(" AND QUESTIONDATE >= '" + beginDate + "'");
		if(endDate != null && endDate != "") sb.append(" AND QUESTIONDATE <= '" + endDate + "'");
		Object[] objs = (Object[])baseDAO.query(sb.toString()).get(0);
		return contentObjectsToQuestionCountVo(objs);
	}
	/**
	 * 转换Object到QuestionCountVo
	 */
	private QuestionCountVo contentObjectsToQuestionCountVo(Object[] objs){
		QuestionCountVo qcv = new QuestionCountVo();
		//问题1
		objs[0] = (objs[0] == null) ? "0" : objs[0];
		objs[1] = (objs[1] == null) ? "0" : objs[1];
		objs[2] = (objs[2] == null) ? "0" : objs[2];
		objs[3] = (objs[3] == null) ? "0" : objs[3];
		//问题2
		objs[4] = (objs[4] == null) ? "0" : objs[4];		
		objs[5] = (objs[5] == null) ? "0" : objs[5];
		objs[6] = (objs[6] == null) ? "0" : objs[6];
		objs[7] = (objs[7] == null) ? "0" : objs[7];
		objs[8] = (objs[8] == null) ? "0" : objs[8];
		//问题3
		objs[9] = (objs[9] == null) ? "0" : objs[9];		
		objs[10] = (objs[10] == null) ? "0" : objs[10];
		objs[11] = (objs[11] == null) ? "0" : objs[11];
		objs[12] = (objs[12] == null) ? "0" : objs[12];
		objs[13] = (objs[13] == null) ? "0" : objs[13];
		//问题4
		objs[14] = (objs[14] == null) ? "0" : objs[14];		
		objs[15] = (objs[15] == null) ? "0" : objs[15];
		objs[16] = (objs[16] == null) ? "0" : objs[16];
		objs[17] = (objs[17] == null) ? "0" : objs[17];
		objs[18] = (objs[18] == null) ? "0" : objs[18];
		objs[19] = (objs[19] == null) ? "0" : objs[19];
		objs[20] = (objs[20] == null) ? "0" : objs[20];
		objs[21] = (objs[21] == null) ? "0" : objs[21];
		objs[22] = (objs[22] == null) ? "0" : objs[22];
		objs[23] = (objs[23] == null) ? "0" : objs[23];
		objs[24] = (objs[24] == null) ? "0" : objs[24];
		//问题5
		objs[25] = (objs[25] == null) ? "0" : objs[25];		
		objs[26] = (objs[26] == null) ? "0" : objs[26];
		objs[27] = (objs[27] == null) ? "0" : objs[27];
		objs[28] = (objs[28] == null) ? "0" : objs[28];
		objs[29] = (objs[29] == null) ? "0" : objs[29];
		//问题6
		objs[30] = (objs[30] == null) ? "0" : objs[30];		
		objs[31] = (objs[31] == null) ? "0" : objs[31];
		objs[32] = (objs[32] == null) ? "0" : objs[32];
		objs[33] = (objs[33] == null) ? "0" : objs[33];
		objs[34] = (objs[34] == null) ? "0" : objs[34];
		objs[35] = (objs[35] == null) ? "0" : objs[35];
		objs[36] = (objs[36] == null) ? "0" : objs[36];
		objs[37] = (objs[37] == null) ? "0" : objs[37];
		objs[38] = (objs[38] == null) ? "0" : objs[38];
		objs[39] = (objs[39] == null) ? "0" : objs[39];
		objs[40] = (objs[40] == null) ? "0" : objs[40];
		//问题7
		objs[41] = (objs[41] == null) ? "0" : objs[41];		
		objs[42] = (objs[42] == null) ? "0" : objs[42];
		objs[43] = (objs[43] == null) ? "0" : objs[43];
		objs[44] = (objs[44] == null) ? "0" : objs[44];
		objs[45] = (objs[45] == null) ? "0" : objs[45];
		//问题8
		objs[46] = (objs[46] == null) ? "0" : objs[46];		
		objs[47] = (objs[47] == null) ? "0" : objs[47];
		objs[48] = (objs[48] == null) ? "0" : objs[48];
		objs[49] = (objs[49] == null) ? "0" : objs[49];
		objs[50] = (objs[50] == null) ? "0" : objs[50];
		objs[51] = (objs[51] == null) ? "0" : objs[51];
		objs[52] = (objs[52] == null) ? "0" : objs[52];
		objs[53] = (objs[53] == null) ? "0" : objs[53];
		
		//问题1
		qcv.setQ101(Integer.valueOf(objs[0].toString())); 
		qcv.setQ102(Integer.valueOf(objs[1].toString()));
		qcv.setQ103(Integer.valueOf(objs[2].toString()));
		qcv.setQ104(Integer.valueOf(objs[3].toString()));	
		//问题2
		qcv.setQ201(Integer.valueOf(objs[4].toString()));
		qcv.setQ202(Integer.valueOf(objs[5].toString()));
		qcv.setQ203(Integer.valueOf(objs[6].toString()));
		qcv.setQ204(Integer.valueOf(objs[7].toString()));
		qcv.setQ205(Integer.valueOf(objs[8].toString()));
		//问题3
		qcv.setQ301(Integer.valueOf(objs[9].toString()));
		qcv.setQ302(Integer.valueOf(objs[10].toString()));
		qcv.setQ303(Integer.valueOf(objs[11].toString()));
		qcv.setQ304(Integer.valueOf(objs[12].toString()));
		qcv.setQ305(Integer.valueOf(objs[13].toString()));
		//问题4
		qcv.setQ401(Integer.valueOf(objs[14].toString()));
		qcv.setQ402(Integer.valueOf(objs[15].toString()));
		qcv.setQ403(Integer.valueOf(objs[16].toString()));
		qcv.setQ404(Integer.valueOf(objs[17].toString()));
		qcv.setQ405(Integer.valueOf(objs[18].toString()));
		qcv.setQ406(Integer.valueOf(objs[19].toString()));
		qcv.setQ407(Integer.valueOf(objs[20].toString()));
		qcv.setQ408(Integer.valueOf(objs[21].toString()));
		qcv.setQ409(Integer.valueOf(objs[22].toString()));
		qcv.setQ410(Integer.valueOf(objs[23].toString()));
		qcv.setQ411(Integer.valueOf(objs[24].toString()));
		//问题5
		qcv.setQ501(Integer.valueOf(objs[25].toString()));
		qcv.setQ502(Integer.valueOf(objs[26].toString()));
		qcv.setQ503(Integer.valueOf(objs[27].toString()));
		qcv.setQ504(Integer.valueOf(objs[28].toString()));
		qcv.setQ505(Integer.valueOf(objs[29].toString()));
		//问题6
		qcv.setQ601(Integer.valueOf(objs[30].toString()));
		qcv.setQ602(Integer.valueOf(objs[31].toString()));
		qcv.setQ603(Integer.valueOf(objs[32].toString()));
		qcv.setQ604(Integer.valueOf(objs[33].toString()));
		qcv.setQ605(Integer.valueOf(objs[34].toString()));
		qcv.setQ606(Integer.valueOf(objs[35].toString()));
		qcv.setQ607(Integer.valueOf(objs[36].toString()));
		qcv.setQ608(Integer.valueOf(objs[37].toString()));
		qcv.setQ609(Integer.valueOf(objs[38].toString()));
		qcv.setQ610(Integer.valueOf(objs[39].toString()));
		qcv.setQ611(Integer.valueOf(objs[40].toString()));
		//问题7
		qcv.setQ701(Integer.valueOf(objs[41].toString()));
		qcv.setQ702(Integer.valueOf(objs[42].toString()));
		qcv.setQ703(Integer.valueOf(objs[43].toString()));
		qcv.setQ704(Integer.valueOf(objs[44].toString()));
		qcv.setQ705(Integer.valueOf(objs[45].toString()));
		//问题8
		qcv.setQ801(Integer.valueOf(objs[46].toString()));
		qcv.setQ802(Integer.valueOf(objs[47].toString()));
		qcv.setQ803(Integer.valueOf(objs[48].toString()));
		qcv.setQ804(Integer.valueOf(objs[49].toString()));
		qcv.setQ805(Integer.valueOf(objs[50].toString()));
		qcv.setQ806(Integer.valueOf(objs[51].toString()));
		qcv.setQ807(Integer.valueOf(objs[52].toString()));
		qcv.setQ808(Integer.valueOf(objs[53].toString()));
		
		return qcv;
	}
	
	/**
	 * 提交问卷
	 */
	public boolean createQuestion(QuestionVo qv){
		QuestionPo qp = new QuestionPo();
		qp.setRecid(GUID.randomID().toBytes());
		qp.setQuestionCreatorId(qv.getQuestionCreatorId());
		qp.setQuestionCreator(qv.getQuestionCreator());
		qp.setQuestionDate(qv.getQuestionDate());
		
		qp.setQuestion1(qv.getQuestion1());
		qp.setQuestion2(qv.getQuestion2());
		qp.setQuestion3(qv.getQuestion3());
		
		qp.setQuestion401(qv.getQuestion401());
		qp.setQuestion402(qv.getQuestion402());
		qp.setQuestion403(qv.getQuestion403());
		qp.setQuestion404(qv.getQuestion404());
		qp.setQuestion405(qv.getQuestion405());
		qp.setQuestion406(qv.getQuestion406());
		qp.setQuestion407(qv.getQuestion407());
		qp.setQuestion408(qv.getQuestion408());
		qp.setQuestion409(qv.getQuestion409());
		qp.setQuestion410(qv.getQuestion410());
		qp.setQuestion411(qv.getQuestion411());
		
		qp.setQuestion5(qv.getQuestion5());
		
		qp.setQuestion601(qv.getQuestion601());
		qp.setQuestion602(qv.getQuestion602());
		qp.setQuestion603(qv.getQuestion603());
		qp.setQuestion604(qv.getQuestion604());
		qp.setQuestion605(qv.getQuestion605());
		qp.setQuestion606(qv.getQuestion606());
		qp.setQuestion607(qv.getQuestion607());
		qp.setQuestion608(qv.getQuestion608());
		qp.setQuestion609(qv.getQuestion609());
		qp.setQuestion610(qv.getQuestion610());
		qp.setQuestion611(qv.getQuestion611());
		
		qp.setQuestion7(qv.getQuestion7());
		
		qp.setQuestion801(qv.getQuestion801());
		qp.setQuestion802(qv.getQuestion802());
		qp.setQuestion803(qv.getQuestion803());
		qp.setQuestion804(qv.getQuestion804());
		qp.setQuestion805(qv.getQuestion805());
		qp.setQuestion806(qv.getQuestion806());
		qp.setQuestion807(qv.getQuestion807());
		qp.setQuestion808(qv.getQuestion808());
		
		qp.setQuestion9(qv.getQuestion9());
		
		baseDAO.save(qp);
		return true;
	}

}
