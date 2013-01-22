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
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.form.promotion.GoodsPromotionForm;
import com.spark.cms.services.goodsPromotion.GetGoodsPromotionListKey;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.vo.GoodsPromotionVo;

/**
 * @author Jideas
 */
@Controller
public class GoodsPromotionAction extends BaseAction {

	@Autowired
	private GoodsPromotionService service;

	@RequestMapping("/pmt/findGoodsPmt")
	@ResponseBody
	public GoodsPromotionForm findGoodsPmt(@RequestParam(value = "id", required = true)
	String id) {
		GoodsPromotionForm vo = this.service.findGoodsPmtForm(id);
		return vo;
	}

	/**
	 * 停用促销方案
	 */
	@RequestMapping("/pmt/stopGoodsPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> stopGoodsPmt(HttpSession session, @RequestParam(value = "id", required = true)
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
	@RequestMapping("/pmt/activeGoodsPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> activeGoodsPmt(HttpSession session,
			@RequestParam(value = "id", required = true)
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
	@RequestMapping("/pmt/saveGoodsPmt")
	@ResponseBody
	public ResponseEntity<MessageModel> saveGoodsPmt(HttpSession session,
			@RequestParam(value = "goodsId", required = false)
			String goodsId, @RequestParam(value = "beginTime", required = false)
			String beginTime, @RequestParam(value = "endTime", required = false)
			String endTime, @RequestParam(value = "count", required = false)
			String count, @RequestParam(value = "disrate", required = false)
			String disrate, @RequestParam(value = "recid", required = false)
			String recid) {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if (CheckIsNull.isEmpty(goodsId)) {
				return ResponseEntityUtil.getResponseEntity(Failure); 
			}
			GoodsPromotionVo vo = new GoodsPromotionVo();
			vo.setRecid(recid);
			vo.setBegintime(DateUtil.convertStringToDate(beginTime, "yyyy-MM-dd HH:mm:ss").getTime());
			vo.setEndtime(DateUtil.convertStringToDate(endTime, "yyyy-MM-dd HH:mm:ss").getTime());
			vo.setGoodsid(goodsId);
			vo.setIsactiving(false);
			vo.setPcount(DoubleUtil.strToDouble(count));
			vo.setDisrate(DoubleUtil.strToDouble(disrate.replace("%", "")));
			return this.service.createOrModify(vo, login).getMessageModel();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("新增促销发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 查询促销列表（已分发）
	 * 
	 * @return
	 */
	@RequestMapping("/pmt/getGoodsPmtList")
	@ResponseBody
	public DataModel<GoodsPromotionForm> getGoodsPmtList(@RequestParam(value = "pmtStatus", required = false)
	String pmtStatus, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows) {
		GetGoodsPromotionListKey key = new GetGoodsPromotionListKey();
		if (CheckIsNull.isNotEmpty(pmtStatus) && !"0".equals(pmtStatus)) {
			key.setOnlyActiving(Boolean.parseBoolean(pmtStatus));
		}
		key.setOffset(Integer.parseInt(page));
		key.setPageSize(Integer.parseInt(rows));
		return this.service.getFormList(key);
	}
}
