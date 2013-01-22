/**
 * 
 */
package com.spark.cms.action.pmt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.base.common.system.dic.DicItem;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.constant.promotion.PromotionConstant;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.form.promotion.OrderPromotionForm;
import com.spark.cms.services.orderPromotion.GetOrderPmtListKey;
import com.spark.cms.services.orderPromotion.OrderPromotionService;
import com.spark.cms.services.vo.OrderPromotionVo;

/**
 * @author Jideas
 */
@Controller
public class OrderPromotionAction extends BaseAction {

	@Autowired
	private OrderPromotionService service;

	@RequestMapping("/pmt/findPmt")
	@ResponseBody
	public OrderPromotionForm findPmt(@RequestParam(value = "id", required = true)
	String id) {
		OrderPromotionForm vo = this.service.findOrderPmtForm(id);
		return vo;
	}

	/**
	 * ͣ�ô�������
	 */
	@RequestMapping("/pmt/stopPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> stopPmt(HttpSession session, @RequestParam(value = "id", required = true)
	String id) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			return this.service.exeStopPromotion(id, login).getMessageModel();
		} catch (Throwable e) {
			log.error("ͣ�ô������������쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * ���ô�������
	 */
	@RequestMapping("/pmt/activePmt")
	@ResponseBody
	public ResponseEntity<MessageModel> activePmt(HttpSession session, @RequestParam(value = "id", required = true)
	String id) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			return this.service.exeActivePromotion(id, login).getMessageModel();
		} catch (Throwable e) {
			log.error("�����ô������������쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * ��������
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/pmt/orderPmtGift")
	@ResponseBody
	public ResponseEntity<MessageModel> orderPmtGift(HttpSession session,
			@RequestParam(value = "goodsId", required = false)
			String goodsId, @RequestParam(value = "beginAmount", required = false)
			String beginAmount, @RequestParam(value = "endAmount", required = false)
			String endAmount, @RequestParam(value = "count", required = false)
			String count, @RequestParam(value = "recid", required = false)
			String recid) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if (CheckIsNull.isEmpty(goodsId)) {
				return ResponseEntityUtil.getResponseEntity(Failure);
			}
			OrderPromotionVo vo = new OrderPromotionVo();
			vo.setRecid(recid);
			vo.setBeginamount(DoubleUtil.strToDouble(beginAmount));
			vo.setEndamount(DoubleUtil.strToDouble(endAmount));
			vo.setGoodsid(goodsId);
			vo.setIsactiving(false);
			vo.setGoodscount(DoubleUtil.strToDouble(count));
			return this.service.createOrModifyPmt(vo, login).getMessageModel();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("�������������쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * ��������
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/pmt/orderPmtVtg")
	@ResponseBody
	public ResponseEntity<MessageModel> orderPmtVtg(HttpSession session,
			@RequestParam(value = "beginAmount", required = false)
			String beginAmount, @RequestParam(value = "endAmount", required = false)
			String endAmount, @RequestParam(value = "count", required = false)
			String count, @RequestParam(value = "recid", required = false)
			String recid) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			OrderPromotionVo vo = new OrderPromotionVo();
			vo.setRecid(recid);
			vo.setBeginamount(DoubleUtil.strToDouble(beginAmount));
			vo.setEndamount(DoubleUtil.strToDouble(endAmount));
			vo.setIsactiving(false);
			vo.setVantages(Long.parseLong(count));
			return this.service.createOrModifyPmt(vo, login).getMessageModel();
		} catch (Throwable e) {
			log.error("�������������쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * ��������
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/pmt/orderPmtFreeD")
	@ResponseBody
	public ResponseEntity<MessageModel> orderPmtFreeD(HttpSession session,
			@RequestParam(value = "beginAmount", required = false)
			String beginAmount) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			OrderPromotionVo vo = new OrderPromotionVo();
			vo.setRecid(PromotionConstant.DefaultFreeDeliveryId);
			vo.setBeginamount(DoubleUtil.strToDouble(beginAmount));
			vo.setIsactiving(false);
			vo.setIsfreedelivery(true);
			return this.service.createOrModifyPmt(vo, login).getMessageModel();
		} catch (Throwable e) {
			log.error("�������������쳣====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	@RequestMapping("/pmt/getActiveStatusList")
	@ResponseBody
	public List<DicItem> getActiveStatusList() {
		List<DicItem> list = new ArrayList<DicItem>();
		list.add(new DicItem("0", "ȫ��"));
		list.add(new DicItem("true", "������"));
		list.add(new DicItem("false", "��ͣ��"));
		return list;
	}

	@RequestMapping("/pmt/getOldBeginAmount")
	@ResponseBody
	public String getOldBeginAmount() {
		OrderPromotionVo vo = this.service.findOrderPromotion(PromotionConstant.DefaultFreeDeliveryId);
		if (vo == null) {
			return "";
		}
		return DoubleUtil.getRoundStr(vo.getBeginamount());
	}

	/**
	 * ��ѯ�����б��ѷַ���
	 * 
	 * @return
	 */
	@RequestMapping("/pmt/getOrderPmtList")
	@ResponseBody
	public DataModel<OrderPromotionForm> getOrderPmtList(@RequestParam(value = "pmtStatus", required = false)
	String pmtStatus, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows) {
		GetOrderPmtListKey key = new GetOrderPmtListKey();
		if (CheckIsNull.isNotEmpty(pmtStatus) && !"0".equals(pmtStatus)) {
			key.setActivingOnly(Boolean.parseBoolean(pmtStatus));
		}
		key.setOffset(Integer.parseInt(page));
		key.setPageSize(Integer.parseInt(rows));
		return this.service.getList(key);
	}
}
