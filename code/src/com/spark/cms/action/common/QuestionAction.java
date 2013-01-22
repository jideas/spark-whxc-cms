package com.spark.cms.action.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.question.QuestionService;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.QuestionCountVo;
import com.spark.cms.services.vo.QuestionVo;

@Controller
public class QuestionAction extends BaseAction{

	@Autowired
	QuestionService questionService;
	
	/**
	 * 提交问卷
	 * @return
	 */
	@RequestMapping("/question/question")
	@ResponseBody
	public MessageModel question(HttpServletRequest request,String q1,String q2,String q3,String[] q4,String q5,String[] q6,String q7,String[] q8,String q9) {
		try{	
			MemberVo mv = (MemberVo)request.getSession().getAttribute(Constant.LoginMemberUser);
			QuestionVo qv = setQuestionVo(q1,q2,q3,q4,q5,q6,q7,q8,q9);
			if(mv != null){
				qv.setQuestionCreatorId(mv.getRecid());
				qv.setQuestionCreator(mv.getUsername());
			}
			qv.setQuestionDate(new Date());
			//保存
			questionService.createQuestion(qv);
			return Success;
		} catch (Exception e) {
			log.error("提交问卷发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return Failure;
		}
	}
	
	/**
	 * 组装数据
	 * @return
	 */
	private QuestionVo setQuestionVo(String q1,String q2,String q3,String[] q4,String q5,String[] q6,String q7,String[] q8,String q9) {
		QuestionVo qv = new QuestionVo();
		qv.setQuestion1(q1);
		qv.setQuestion2(q2);
		qv.setQuestion3(q3);
		for(int i = 0;q4 != null && i < q4.length;i++){
			if("401".equals(q4[i])){
				qv.setQuestion401("1");
			}else if("402".equals(q4[i])){
				qv.setQuestion402("1");
			}else if("403".equals(q4[i])){
				qv.setQuestion403("1");
			}else if("404".equals(q4[i])){
				qv.setQuestion404("1");
			}else if("405".equals(q4[i])){
				qv.setQuestion405("1");
			}else if("406".equals(q4[i])){
				qv.setQuestion406("1");
			}else if("407".equals(q4[i])){
				qv.setQuestion407("1");
			}else if("408".equals(q4[i])){
				qv.setQuestion408("1");
			}else if("409".equals(q4[i])){
				qv.setQuestion409("1");
			}else if("410".equals(q4[i])){
				qv.setQuestion410("1");
			}else if("411".equals(q4[i])){
				qv.setQuestion411("1");
			}
			
		}
		qv.setQuestion5(q5);
		for(int i = 0;q6 != null && i < q6.length;i++){
			if("601".equals(q6[i])){
				qv.setQuestion601("1");
			}else if("602".equals(q6[i])){
				qv.setQuestion602("1");
			}else if("603".equals(q6[i])){
				qv.setQuestion603("1");
			}else if("604".equals(q6[i])){
				qv.setQuestion604("1");
			}else if("605".equals(q6[i])){
				qv.setQuestion605("1");
			}else if("606".equals(q6[i])){
				qv.setQuestion606("1");
			}else if("607".equals(q6[i])){
				qv.setQuestion607("1");
			}else if("608".equals(q6[i])){
				qv.setQuestion608("1");
			}else if("609".equals(q6[i])){
				qv.setQuestion609("1");
			}else if("610".equals(q6[i])){
				qv.setQuestion610("1");
			}else if("611".equals(q6[i])){
				qv.setQuestion611("1");
			}
			
		}
		qv.setQuestion7(q7);
		for(int i = 0;q8 != null && i < q8.length;i++){
			if("801".equals(q8[i])){
				qv.setQuestion801("1");
			}else if("802".equals(q8[i])){
				qv.setQuestion802("1");
			}else if("803".equals("1")){
				qv.setQuestion803(q8[i]);
			}else if("804".equals("1")){
				qv.setQuestion804("1");
			}else if("805".equals(q8[i])){
				qv.setQuestion805("1");
			}else if("806".equals(q8[i])){
				qv.setQuestion808("1");
			}else if("807".equals("1")){
				qv.setQuestion807("1");
			}else if("808".equals(q8[i])){
				qv.setQuestion808("1");
			}			
		}
		qv.setQuestion9(q9 == null ? "" : q9);
		
		return qv;
	}

	/**
	 * 统计问卷
	 */
	@RequestMapping("/question/countQuestion")
	@ResponseBody
	public ResponseEntity<QuestionCountVo> countQuestion(String beginDate,String endDate){
		QuestionCountVo qcv = questionService.countQuestion(beginDate, endDate);
		return ResponseEntityUtil.getResponseEntity(qcv);
	}
}
