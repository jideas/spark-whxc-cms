/**
 * 
 */
package com.spark.front.action.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.card.CardService;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.vo.MemberVo;
import com.spark.front.utils.CmsString;
import com.spark.front.utils.RegEx;

/**
 * @author Jideas
 * 
 */
@Controller
public class ChargeAction extends BaseAction {

	@Autowired
	private CardService cardService; 

	/**
	 * ��ֵ
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/doCharge")
	@ResponseBody
	public ResponseEntity<MessageModel> doCharge(@RequestParam(value = "cardNo", required = true)
	String cardNo, @RequestParam(value = "password", required = true)
	String password, HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "���ȵ�¼��", 0).getMessageModel();
			}
			if (CmsString.matches(RegEx.OnlyNumber, cardNo) && CmsString.matches(RegEx.OnlyNumber, password)) {
				ServiceMessage sMessage = this.cardService.exeUseCard(cardNo, password, login);
				return sMessage.getMessageModel();
			}
			return new ServiceMessage(false, "��ֵ����Ϣ����ȷ�����飡", 0).getMessageModel();
		} catch (Throwable e) {
			log.error("��ֵ�����쳣====" + e.getStackTrace());
			e.printStackTrace();
			return new ServiceMessage(false, "���ȵ�¼��", 0).getMessageModel();
		}
	}
}
