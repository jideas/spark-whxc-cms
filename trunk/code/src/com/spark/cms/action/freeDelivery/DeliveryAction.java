/**
 * 
 */
package com.spark.cms.action.freeDelivery;

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
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.form.delivery.DeliveryForm;
import com.spark.cms.services.freeDelivery.DeliveryPriceService;
import com.spark.cms.services.freeDelivery.GetDeliveryListKey;
import com.spark.cms.services.vo.DeliveryPriceVo;

/**
 * @author Jideas
 */
@Controller
public class DeliveryAction extends BaseAction {

	@Autowired
	private DeliveryPriceService service; 

	/**
	 * 获取单价
	 */
	@RequestMapping("/delivery/getPrice")
	@ResponseBody
	public ResponseEntity<MessageModel> getPrice() {
		Double price = this.service.getPrice();
		if (null == price) {
			return new ServiceMessage(false, "").getMessageModel();
		}
		String value = DoubleUtil.getRoundStr(price);
		return new ServiceMessage(true, "", value).getMessageModel();
	}

	/**
	 * 保存送货上门单价
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/delivery/savePrice")
	@ResponseBody
	public ResponseEntity<MessageModel> savePrice(@RequestParam(value = "price", required = false)
	String price, HttpSession session) throws ServiceMessage {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			return this.service.createPurPrice(DoubleUtil.strToDouble(price), login).getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存送货上门单价发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "保存送货上门单价失败！").getMessageModel();
	}

	/**
	 * 查询列表
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/delivery/getDeliveryPriceList")
	@ResponseBody
	public DataModel<DeliveryForm> getDeliveryPriceList(@RequestParam(value = "deliveryStatus", required = false)
	String deliveryStatus, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, HttpSession session) throws ServiceMessage {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if (null == login) {
				return null;
			}
			GetDeliveryListKey key = new GetDeliveryListKey();
			if (CheckIsNull.isNotEmpty(deliveryStatus) && !"0".equals(deliveryStatus)) {
				key.setIsOnlyActiving(Boolean.parseBoolean(deliveryStatus));
			}
			key.setOffset(Integer.parseInt(page));
			key.setPageSize(Integer.parseInt(rows));
			return this.service.getDeliveryPriceList(key);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询送货上门价格方案发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 保存送货上门方案
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/delivery/saveDeliveryPrice")
	@ResponseBody
	public ResponseEntity<MessageModel> saveDeliveryPrice(@RequestParam(value = "dprice", required = false)
	String price, @RequestParam(value = "dcount", required = false)
	String count, @RequestParam(value = "recid", required = false)
	String recid, @RequestParam(value = "remark", required = false)
	String remark, HttpSession session) throws ServiceMessage {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			DeliveryPriceVo vo = new DeliveryPriceVo();
			vo.setRecid(recid);
			vo.setDprice(DoubleUtil.strToDouble(price));
			vo.setDcount(Integer.parseInt(count));
			vo.setRemark(remark);
			vo.setActiving(false);
			return this.service.createOrModify(vo, login).getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存送货上门单价发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "保存送货上门单价失败！").getMessageModel();
	}

	/**
	 * 得到送货上门方案
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping(" /delivery/findDeliveryPrice")
	@ResponseBody
	public ResponseEntity<MessageModel> findDeliveryPrice(@RequestParam(value = "recid", required = false)
	String recid, HttpSession session) throws ServiceMessage {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if (null == login) {
				return null;
			}
			return new ServiceMessage(true, "", this.service.findDeliveryPrice(recid)).getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存送货上门单价发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "保存送货上门单价失败！").getMessageModel();
	}

	/**
	 * 保存送货上门方案
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/delivery/stopDeliveryPrice")
	@ResponseBody
	public ResponseEntity<MessageModel> stopDeliveryPrice(@RequestParam(value = "recid", required = false)
	String recid, HttpSession session) throws ServiceMessage {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if (null == login) {
				return null;
			}
			return this.service.exeStopDeliveryPrice(recid, login).getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存送货上门单价发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "保存送货上门单价失败！").getMessageModel();
	}

	/**
	 * 保存送货上门方案
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/delivery/activeDeliveryPrice")
	@ResponseBody
	public ResponseEntity<MessageModel> activeDeliveryPrice(@RequestParam(value = "recid", required = false)
	String recid, HttpSession session) throws ServiceMessage {
		try {
			Login login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			if (null == login) {
				return null;
			}
			return this.service.exeActiveDeliveryPrice(recid, login).getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存送货上门单价发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "保存送货上门单价失败！").getMessageModel();
	}
}
