/**
 * 
 */
package com.spark.front.action.payment;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.front.form.payment.AliPayErrorForm;
import com.spark.front.form.payment.UnionPayForm;
import com.spark.front.service.ActionService;

/**
 * @author Jideas
 * 
 */
@Controller
public class PayAction extends BaseAction {

	@Autowired
	private ActionService actionService;

	@SuppressWarnings("deprecation")
	@RequestMapping("/payment/errorAliPay")
	@ResponseBody
	public String errorAliPay(HttpServletRequest request,AliPayErrorForm form) throws Throwable {
		return this.actionService.exeErrorAliPay(request,form);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/payment/paidAliPay")
	@ResponseBody
	public String paidAliPay(HttpServletRequest request) throws Throwable {
		return this.actionService.exePaidAliPay(request);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/payment/paidUnionPay")
	@ResponseBody
	public String paidUnionPay(HttpServletRequest request, UnionPayForm form) throws Throwable {
		return this.actionService.exePaidUnionPay(request, form);
	}

	/**
	 * 去第三方付款
	 * 
	 * @param from
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/payment/payCharge")
	public String payCharge(@RequestParam(value = "value", required = false)
	String value, @RequestParam(value = "bank", required = true)
	String bank, HttpServletRequest request,HttpServletResponse response) {
		String html = this.actionService.exePayCharge(value, bank, request);
		try {
			response.setContentType("text/html");
			response.getWriter().write(html);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 去第三方付款
	 * 
	 * @param from
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/payment/payOrder")
	public String payOrder(@RequestParam(value = "value", required = false)
	String recids, @RequestParam(value = "bank", required = true)
	String bank, HttpServletRequest request,HttpServletResponse response) {
		String html = this.actionService.exePayOrder(recids, bank, request);
		try {
			response.setContentType("text/html");
			response.getWriter().write(html);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到订单金额
	 * 
	 * @param from
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/payment/getOrderInfo")
	@ResponseBody
	public ResponseEntity<MessageModel> getOrderInfo(@RequestParam(value = "ids", required = false)
	String recids, HttpServletRequest request) {
		try {
			return this.actionService.getOrderInfo(recids, request);
		} catch (Throwable e) {
			log.error("是否已验证手机发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
		}
	}
}
