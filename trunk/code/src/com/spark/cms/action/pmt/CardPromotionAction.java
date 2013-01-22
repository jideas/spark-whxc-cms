/**
 * 
 */
package com.spark.cms.action.pmt;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.CardPromotion.CardPmtService;
import com.spark.cms.services.CardPromotion.GetCardPmtListKey;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.form.promotion.CardPromotionForm;
import com.spark.cms.services.vo.CardPromotionVo;

/**
 * @author Jideas
 * 
 */
@Controller
public class CardPromotionAction extends BaseAction {

	@Autowired
	private CardPmtService service;

	@RequestMapping("/pmt/findCardPmt")
	@ResponseBody
	public CardPromotionForm findCardPmt(@RequestParam(value = "id", required = true)
	String id) {
		CardPromotionForm vo = this.service.findCardPmtForm(id);
		return vo;
	}

	/**
	 * 停用促销方案
	 */
	@RequestMapping("/pmt/stopCardPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> stopCardPmt(HttpSession session, @RequestParam(value = "id", required = true)
	String id) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			return this.service.exeStopPromotion(id, login).getMessageModel();
		} catch (Throwable e) {
			log.error("停用促销方案发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 启用促销方案
	 */
	@RequestMapping("/pmt/activeCardPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> activeCardPmt(HttpSession session, @RequestParam(value = "id", required = true)
	String id) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			return this.service.exeActivePromotion(id, login).getMessageModel();
		} catch (Throwable e) {
			log.error("启用用促销方案发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 新增促销
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/pmt/editCardGiftPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> editCardGiftPmt(HttpSession session,
			@RequestParam(value = "goodsId", required = false)
			String goodsId, @RequestParam(value = "amount", required = false)
			String amount, @RequestParam(value = "count", required = false)
			String count, @RequestParam(value = "recid", required = false)
			String recid) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if(CheckIsNull.isEmpty(goodsId)){
				return ResponseEntityUtil.getResponseEntity(Failure);
			}
			CardPromotionVo vo = new CardPromotionVo();
			vo.setRecid(recid);
			if (CheckIsNull.isEmpty(amount)) {
				return ResponseEntityUtil.getResponseEntity(Failure);
			}
			vo.setAmount(DoubleUtil.strToDouble(amount));
			vo.setGoodsId(goodsId);
			vo.setActiving(false);
			vo.setGoodsCount(DoubleUtil.strToDouble(count));
			return this.service.createOrModifyPmt(vo, login).getMessageModel();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("新增促销发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 新增促销
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/pmt/editCardVtgPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> editCardVtgPmt(HttpSession session,
			@RequestParam(value = "amount", required = false)
			String amount, @RequestParam(value = "count", required = false)
			String count, @RequestParam(value = "recid", required = false)
			String recid) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			CardPromotionVo vo = new CardPromotionVo();
			vo.setRecid(recid);
			vo.setAmount(DoubleUtil.strToDouble(amount));
			vo.setActiving(false);
			vo.setVantages(DoubleUtil.strToDouble(count));
			return this.service.createOrModifyPmt(vo, login).getMessageModel();
		} catch (Throwable e) {
			log.error("新增促销发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 新增促销
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/pmt/editCardExtraPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> editCardExtraPmt(HttpSession session,
			@RequestParam(value = "amount", required = false)
			String amount, @RequestParam(value = "count", required = false)
			String count, @RequestParam(value = "recid", required = false)
			String recid) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			CardPromotionVo vo = new CardPromotionVo();
			vo.setRecid(recid);
			vo.setAmount(DoubleUtil.strToDouble(amount));
			vo.setActiving(false); 
			vo.setExtraAmount(DoubleUtil.strToDouble(count));
			return this.service.createOrModifyPmt(vo, login).getMessageModel();
		} catch (Throwable e) {
			log.error("新增促销发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 查询促销列表
	 * 
	 * @return
	 */
	@RequestMapping("/pmt/getCardPmtList")
	@ResponseBody
	public DataModel<CardPromotionForm> getCardPmtList(@RequestParam(value = "pmtStatus", required = false)
	String pmtStatus, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows) {
		GetCardPmtListKey key = new GetCardPmtListKey();
		if (CheckIsNull.isNotEmpty(pmtStatus) && !"0".equals(pmtStatus)) {
			key.setOnlyActiving(Boolean.parseBoolean(pmtStatus));
		}
		key.setOffset(Integer.parseInt(page));
		key.setPageSize(Integer.parseInt(rows));
		return this.service.getList(key);
	}
}
