/**
 * 
 */
package com.spark.front.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.cms.common.MessageModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.front.form.member.InfoForm;
import com.spark.front.form.payment.AliPayErrorForm;
import com.spark.front.form.payment.UnionPayForm;

/**
 * @author Jideas
 * 
 */
public interface ActionService {

	public String exeErrorAliPay(HttpServletRequest request,AliPayErrorForm form) throws Throwable;

	public String exePaidAliPay(HttpServletRequest request) throws Throwable;

	@SuppressWarnings("deprecation")
	public String exePaidUnionPay(HttpServletRequest request, UnionPayForm form) throws Throwable;

	/**
	 * 去第三方付款
	 * 
	 * @param from
	 * @return
	 */
	public String exePayCharge(String value, String bank, HttpServletRequest request);

	/**
	 * 去第三方付款
	 * 
	 * @param from
	 * @return
	 */
	public String exePayOrder(String recids, String bank, HttpServletRequest request);

	/**
	 * 得到订单金额
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> getOrderInfo(String recids, HttpServletRequest request) throws Throwable;

	/**
	 * 订单确认付款
	 * 
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> exePayForOrders(String ids, String password, HttpSession session)
			throws Throwable;

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeInfoUpdate(InfoForm data, HttpServletRequest request) throws ServiceMessage;

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeInitInfoPage(HttpServletRequest request) throws ServiceMessage;

	/**
	 * 确认购买包月送货上门
	 * 
	 * @return
	 * @throws ServiceMessage
	 */
	@RequestMapping("/payForDelivery")
	@ResponseBody
	public ResponseEntity<MessageModel> exePayForDelivery(String id, String monthNo, String password,
			HttpSession session) throws ServiceMessage;

	/**
	 * 确认购买送货包月
	 * 
	 * @return
	 * @throws ServiceMessage
	 */
	public ModelAndView exeToSureBuyDelivery(String id, String monthNo, HttpSession session) throws ServiceMessage;

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getDeliveryListPerMonth(HttpServletRequest request) throws ServiceMessage;

	/**
	 * 得到账户信息
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getAccountInfo(HttpServletRequest request) throws ServiceMessage;

	/**
	 * 得到送货包月方案
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getDeliveryPriceList(HttpServletRequest request) throws ServiceMessage;

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getBalanceAndVangete(HttpServletRequest request) throws ServiceMessage;

	/**
	 * 得到积分往来
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> getVantegeLogs(String pageIndex, HttpServletRequest request) throws Throwable;

	/**
	 * 得到账户往来
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> getBalanceLogs(String pageIndex, HttpServletRequest request) throws Throwable;

	/**
	 * 得到账户余额
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ModelAndView getBalance(HttpServletRequest request) throws Throwable;

	/**
	 * 设置支付密码
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeCheckPayAccountSms(String smsCheckCode, HttpServletRequest request)
			throws ServiceMessage;

	/**
	 * 发送短线验证码
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeSendPayAccountSms(String password, HttpServletRequest request)
			throws ServiceMessage;

	/**
	 * @param request
	 * @return
	 */
	public ModelAndView toVantegePage(HttpServletRequest request);

}
