/**
 * 
 */
package com.spark.front.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spark.base.common.system.dic.DictionaryType;
import com.spark.base.common.system.dic.SparkDictionaryManager;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.base.type.GUID;
import com.spark.cms.base.constant.SheetNumberType;
import com.spark.cms.base.constant.card.CMS;
import com.spark.cms.base.constant.payment.PayingBillStatus;
import com.spark.cms.base.constant.payment.PayingBillType;
import com.spark.cms.base.utils.encrypt.EncryptionUtil;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.Constant.MemberEnum.DealingsType;
import com.spark.cms.common.Constant.MemberEnum.VantagesType;
import com.spark.cms.common.Constant.OrderEnum.PayType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.delivery.DeliveryForm;
import com.spark.cms.services.freeDelivery.DeliveryPriceService;
import com.spark.cms.services.freeDelivery.GetDeliveryListKey;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.member.key.GetDealingListKey;
import com.spark.cms.services.member.key.GetVantagesListKey;
import com.spark.cms.services.order.OrderService;
import com.spark.cms.services.payment.PayingBillsService;
import com.spark.cms.services.serial.SerialNumberService;
import com.spark.cms.services.sms.SmsService;
import com.spark.cms.services.sms.utils.SmsSendTask;
import com.spark.cms.services.vo.DeliveryPriceVo;
import com.spark.cms.services.vo.MemberAccountVo;
import com.spark.cms.services.vo.MemberDealingVo;
import com.spark.cms.services.vo.MemberDeliveryVo;
import com.spark.cms.services.vo.MemberInfoVo;
import com.spark.cms.services.vo.MemberVantagesVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.PayingBillsVo;
import com.spark.front.form.member.AccountInfo;
import com.spark.front.form.member.DealingLogForm;
import com.spark.front.form.member.InfoForm;
import com.spark.front.form.member.VantegesLogForm;
import com.spark.front.form.order.OrderInfo;
import com.spark.front.form.payment.AliPayErrorForm;
import com.spark.front.form.payment.AliSubmitForm;
import com.spark.front.form.payment.UnionPayForm;
import com.spark.front.service.ActionService;
import com.spark.front.utils.AlipayNotify;
import com.spark.front.utils.CheckCode;
import com.spark.front.utils.CheckCodeManager;
import com.spark.front.utils.CmsString;
import com.spark.front.utils.PayFormCreator;
import com.spark.front.utils.RegEx;
import com.spark.front.utils.UnionPayChkValue;

/**
 * @author Jideas
 * 
 */
@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private MemberService memberService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private DeliveryPriceService deliveryPriceService;

	@Autowired
	private SerialNumberService sheetNumberService;

	@Autowired
	private PayingBillsService payingBillsService;

	@Autowired
	private OrderService orderService;

	public String exeErrorAliPay(HttpServletRequest request, AliPayErrorForm form) throws Throwable {
		if (null == form || !CMS.PayInfo.AliPaySellerEmail.equals(form.getSeller_email())) {
			return null;
		}
		if (null == form.getOut_trade_no()) {
			return null;
		}
		PayingBillsVo vo = this.payingBillsService.findPayingBillByOrderNo(form.getOut_trade_no());
		if (null == vo) {
			return null;
		}
		this.payingBillsService.exeUpdateStatus(vo, PayingBillStatus.Failure.getCode(), false);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String exePaidAliPay(HttpServletRequest request) throws Throwable {
		String seller_email = request.getParameter("seller_email");
		if (!CMS.PayInfo.AliPaySellerEmail.equals(seller_email)) {
			return null;
		}
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
			// "gbk");
			params.put(name, valueStr);
		}
		if (AlipayNotify.verify(params)) {
			return null;
		}
		String order_no = request.getParameter("out_trade_no"); // 获取订单号
		String total_fee = request.getParameter("total_fee"); // 获取总金额
		String trade_status = request.getParameter("trade_status");
		String extra_common_param = request.getParameter("extra_common_param");
		PayingBillsVo payingBill = null;
		if (CheckIsNull.isNotEmpty(extra_common_param)) {
			payingBill = this.payingBillsService.findPayingBill(extra_common_param);
		} else if (CheckIsNull.isNotEmpty(order_no)) {
			payingBill = this.payingBillsService.findPayingBillByOrderNo(order_no);
		} else {
			return "";
		}
		if (payingBill == null || payingBill.getStatus().equals(PayingBillStatus.Success.getCode())
				|| DoubleUtil.strToDouble(payingBill.getAmount()) != DoubleUtil.strToDouble(total_fee)) {
			return "";
		}
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			// TRADE_FINISHED
			// 判断该笔订单是否在商户网站中已经做过处理
			// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// 如果有做过处理，不执行商户的业务程序
			// 注意：
			// 该种交易状态只在两种情况下出现
			// 1、开通了普通即时到账，买家付款成功后。
			// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。

			// TRADE_SUCCESS
			// 判断该笔订单是否在商户网站中已经做过处理
			// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// 如果有做过处理，不执行商户的业务程序
			// 注意：
			// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			boolean result = this.payingBillsService.exeUpdateStatus(payingBill, PayingBillStatus.Success.getCode(),
					true);
			if (result) {
				return "success";
			} else {
				return "f";
			}
		}
		// 这里执行业务处理
		this.payingBillsService.exeUpdateStatus(payingBill, PayingBillStatus.Failure.getCode(), false);
		return "f";
	}

	@SuppressWarnings("deprecation")
	public String exePaidUnionPay(HttpServletRequest request, UnionPayForm form) throws Throwable {
		System.out.println("网银反馈函数：成功接收，开始处理！");
		if (form == null || CheckIsNull.isEmpty(form.getPriv1())) {
			System.out.println("网银反馈函数：丢失参数！");
			return null;
		}
		boolean b = UnionPayChkValue.checkVerifyTransResponse(form, request.getRealPath(""));
		if (!b) {
			System.out.println("网银反馈函数：验证码错误！");
			return null;
		}
		PayingBillsVo vo = this.payingBillsService.findPayingBill(form.getPriv1());
		if (null == vo) {
			System.out.println("网银反馈函数：未找到支付记录！");
			return null;
		}
		PayingBillStatus status = PayingBillStatus.Failure;
		boolean must = false;
		if ("1001".equals(form.getStatus())) {
			System.out.println("网银反馈函数：成功付款！");
			status = PayingBillStatus.Success;
			must = true;
		}
		boolean result = this.payingBillsService.exeUpdateStatus(vo, status.getCode(), must);
		System.out.println("网银反馈函数：更新数据完成！");
		return result + "";
	}

	/**
	 * 去第三方付款
	 * 
	 * @param from
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String exePayCharge(String value, String bank, HttpServletRequest request) {
		Login login = null;
		try {
			login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			return "<script type='text/javascript'>location='" + request.getContextPath()
					+ "/pub/sub/login.jsp'</script>";
		}
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return "<script type='text/javascript'>location='" + request.getContextPath()
					+ "/pub/sub/login.jsp'</script>";
		}
		if ("alipay".equals(bank)) {
			return payOfChargeAlibaba(value, request);
		}
		if (value == null) {
			return "操作失败重试！";
		}
		double vamount = DoubleUtil.strToDouble(value);
		String private1 = GUID.randomID().toString();
		value = DoubleUtil.getRoundStr(vamount);
		String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String merId = CMS.PayInfo.UnionPayUserNo;
		String orderId = sheetNumberService.exeGetSheetNumber(SheetNumberType.UnionPayOrdId).substring(1);
		String bgRetUrl = context + CMS.PayInfo.UnionPayBgRetUrl;
		String pageRetUrl = context + CMS.PayInfo.PayChargePageRetUrl;
		String amount = "000000000000" + value;
		amount = amount.replace(".", "");
		amount = amount.replace(",", "");
		amount = amount.substring(amount.length() - 12);
		String gateId = SparkDictionaryManager.getItem(DictionaryType.BankGateMapping, bank).getTitle();
		String form = PayFormCreator.getUnionPay(merId, orderId, amount, bgRetUrl, pageRetUrl, gateId, private1,
				request.getRealPath(""));
		PayingBillsVo vo = new PayingBillsVo();
		vo.setRecid(private1);
		vo.setAmount(amount);
		vo.setOrderId(orderId);
		vo.setCreateTime(new Date().getTime());
		vo.setPayType(PayingBillType.UnionCharge.getCode());
		vo.setStatus(PayingBillStatus.Paying.getCode());
		vo.setTransDate(orderId.substring(0, 8));
		vo.setRelaBillsId(login.getRecid());
		this.payingBillsService.createPayingBill(vo);
		return form;
	}

	/**
	 * 去第三方付款
	 * 
	 * @param from
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String exePayOrder(String recids, String bank, HttpServletRequest request) {
		Login login = null;
		try {
			login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			return "<script type='text/javascript'>location='" + request.getContextPath()
					+ "/pub/sub/login.jsp'</script>";
		}
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return "<script type='text/javascript'>location='" + request.getContextPath()
					+ "/pub/sub/login.jsp'</script>";
		}
		if ("alipay".equals(bank)) {
			return payOfOrderAlibaba(recids, request);
		}
		if (recids == null) {
			return "操作失败重试！";
		}
		String[] ids = recids.split(",");
		double vamount = 0;
		for (String id : ids) {
			OrderInfo oi = null;
			try {
				oi = this.orderService.getUnEffectedOrder(id);
			} catch (Throwable e) {
				return "操作失败重试！";
			}
			vamount += oi.getTotalamount();
			if (oi.isToDoor() && oi.getLockedDeliveryBalance() > 0) {
				vamount -= oi.getDeliveryCost();
			}
		}
		String private1 = GUID.randomID().toString();
		String value = DoubleUtil.getRoundStr(vamount);
		String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		String merId = CMS.PayInfo.UnionPayUserNo;
		String orderId = sheetNumberService.exeGetSheetNumber(SheetNumberType.UnionPayOrdId).substring(1);
		String bgRetUrl = context + CMS.PayInfo.UnionPayBgRetUrl;
		String pageRetUrl = context + CMS.PayInfo.PayOrderPageRetUrl;
		String amount = "000000000000" + value;
		amount = amount.replace(".", "");
		amount = amount.replace(",", "");
		amount = amount.substring(amount.length() - 12);
		String gateId = SparkDictionaryManager.getItem(DictionaryType.BankGateMapping, bank).getTitle();
		String form = PayFormCreator.getUnionPay(merId, orderId, amount, bgRetUrl, pageRetUrl, gateId, private1,
				request.getRealPath(""));
		PayingBillsVo vo = new PayingBillsVo();
		vo.setRecid(private1);
		vo.setAmount(amount);
		vo.setOrderId(orderId);
		vo.setCreateTime(new Date().getTime());
		vo.setPayType(PayingBillType.UnionOrder.getCode());
		vo.setStatus(PayingBillStatus.Paying.getCode());
		vo.setTransDate(orderId.substring(0, 8));
		vo.setRelaBillsId(recids);
		this.payingBillsService.createPayingBill(vo);
		return form;
	}

	/**
	 * @param recids
	 * @param request
	 * @return
	 */
	private String payOfOrderAlibaba(String recids, HttpServletRequest request) {
		Login login = null;
		try {
			login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			return "请先登录！";
		}
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return "请先登录！";
		}
		if (recids == null) {
			return "操作失败重试！";
		}
		String[] ids = recids.split(",");
		double vamount = 0;
		for (String id : ids) {
			OrderInfo oi = null;
			try {
				oi = this.orderService.getUnEffectedOrder(id);
			} catch (Throwable e) {
				return "操作失败重试！";
			}
			vamount += oi.getTotalamount();
			if (oi.isToDoor() && oi.getLockedDeliveryBalance() > 0) {
				vamount -= oi.getDeliveryCost();
			}
		}
		String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		AliSubmitForm form = new AliSubmitForm();
		form.setError_notify_url(CMS.PayInfo.AliPayErrorBgRetUrl);
		form.setExter_invoke_ip(getIpAddr(request));
		form.setNeed_ctu_check("N");
		form.setNotify_url(context + CMS.PayInfo.AliPayBgRetUrl);
		String orderId = sheetNumberService.exeGetSheetNumber(SheetNumberType.UnionPayOrdId).substring(1);
		form.setOut_trade_no(orderId);
		form.setPartner(CMS.PayInfo.AliPayPartner);
		form.setPayment_type("1");
		form.setPaymethod("directPay");
		form.setReturn_url(context + CMS.PayInfo.PayOrderPageRetUrl);
		form.setSeller_email(CMS.PayInfo.AliPaySellerEmail);
		form.setService(CMS.PayInfo.AliPayServiceName);
		form.setSubject("7号生活馆商品");
		form.setTotal_fee(DoubleUtil.getRoundStr(vamount));
		String html = PayFormCreator.getAliPay(form);
		PayingBillsVo vo = new PayingBillsVo();
		vo.setRecid(GUID.randomID().toString());
		vo.setAmount(DoubleUtil.getRoundStr(vamount));
		vo.setOrderId(orderId);
		vo.setCreateTime(new Date().getTime());
		vo.setPayType(PayingBillType.AliOrder.getCode());
		vo.setStatus(PayingBillStatus.Paying.getCode());
		vo.setTransDate(orderId.substring(0, 8));
		vo.setRelaBillsId(recids);
		this.payingBillsService.createPayingBill(vo);
		form.setExtra_common_param(vo.getRecid());
		return html;
	}

	/**
	 * @param value
	 * @return
	 */
	private String payOfChargeAlibaba(String value, HttpServletRequest request) {
		Login login = null;
		try {
			login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			return "请先登录！";
		}
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return "请先登录！";
		}
		if (value == null) {
			return "操作失败重试！";
		}
		double vamount = DoubleUtil.strToDouble(value);
		String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		AliSubmitForm form = new AliSubmitForm();
		// form.setAnti_phishing_key(anti_phishing_key);
		form.setError_notify_url(context + CMS.PayInfo.AliPayErrorBgRetUrl);
		form.setExter_invoke_ip(getIpAddr(request));
		form.setNeed_ctu_check("N");
		form.setNotify_url(context + CMS.PayInfo.AliPayBgRetUrl);
		String orderId = sheetNumberService.exeGetSheetNumber(SheetNumberType.UnionPayOrdId).substring(1);
		form.setOut_trade_no(orderId);
		form.setPartner(CMS.PayInfo.AliPayPartner);
		form.setPayment_type("1");
		form.setPaymethod("directPay");
		form.setReturn_url(context + CMS.PayInfo.PayOrderPageRetUrl);
		form.setSeller_email(CMS.PayInfo.AliPaySellerEmail);
		form.setService(CMS.PayInfo.AliPayServiceName);
		form.setSubject("7号生活馆商品");
		form.setTotal_fee(DoubleUtil.getRoundStr(vamount));
		String html = PayFormCreator.getAliPay(form);
		PayingBillsVo vo = new PayingBillsVo();
		vo.setRecid(GUID.randomID().toString());
		vo.setAmount(DoubleUtil.getRoundStr(vamount));
		vo.setOrderId(orderId);
		vo.setCreateTime(new Date().getTime());
		vo.setPayType(PayingBillType.AliOrder.getCode());
		vo.setStatus(PayingBillStatus.Paying.getCode());
		vo.setTransDate(orderId.substring(0, 8));
		vo.setRelaBillsId(login.getRecid());
		this.payingBillsService.createPayingBill(vo);
		form.setExtra_common_param(vo.getRecid());
		return html;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

	/**
	 * 得到订单金额
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> getOrderInfo(String recids, HttpServletRequest request) throws Throwable {
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
		}
		if (CheckIsNull.isEmpty(recids) || "null".equals(recids)) {
			return new ServiceMessage(false, "参数错误，请刷新重试！", 1).getMessageModel();
		}
		String orderNos = "";
		double amount = 0;
		String[] ids = recids.split(",");
		for (String id : ids) {
			OrderInfo oi = this.orderService.getUnEffectedOrder(id);
			orderNos += "," + oi.getBillsno().substring(4);
			amount += oi.getTotalamount();
			if (oi.isToDoor() && oi.getLockedDeliveryBalance() > 0) {
				amount -= oi.getDeliveryCost();
			}
		}
		return new ServiceMessage(true, "", new String[] { orderNos.substring(1), DoubleUtil.getRoundStr(amount) })
				.getMessageModel();

	}

	/**
	 * 订单确认付款
	 * 
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> exePayForOrders(String ids, String password, HttpSession session)
			throws Throwable {
		Login login;
		if (CmsString.isEmpty(ids) || "null".equals(ids) || CmsString.isEmpty(password)) {
			return new ServiceMessage(false, "参数丢失！", 1).getMessageModel();
		}
		login = new Login((MemberVo) session.getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		List<OrderInfo> orders = new ArrayList<OrderInfo>();
		double amount = 0;
		for (String orderId : ids.split(",")) {
			OrderInfo order = orderService.getUnEffectedOrder(orderId);
			amount += order.getTotalamount();
			if (order.isToDoor() && order.getLockedDeliveryBalance() > 0) {
				amount -= order.getDeliveryCost();
			}
			orders.add(order);
		}
		if (amount == 0) {
			return new ServiceMessage(false, "订单选择错误！", 1).getMessageModel();
		}
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			return new ServiceMessage(false, "账户异常", 1).getMessageModel();
		}
		if (CmsString.isEmpty(balance.getPaypassword())) {
			return new ServiceMessage(false, "您尚未设置支付密码！", 3).getMessageModel();
		}
		if (!balance.getPaypassword().equals(EncryptionUtil.encryptMD5(password))) {
			return new ServiceMessage(false, "支付密码不正确！", 4).getMessageModel();
		}
		if (balance.getMoneybalance() < amount) {
			return new ServiceMessage(false, "余额不足请先充值！", 5).getMessageModel();
		}
		for (OrderInfo order : orders) {
			MemberDealingVo deal = new MemberDealingVo();
			deal.setAmount(-order.getTotalamount());
			deal.setMemberid(login.getRecid());
			deal.setDealtype(DealingsType.Consume.getCode());
			deal.setOccurdate(new Date());
			deal.setRelabillsid(order.getRecid());
			deal.setRelabillsno(order.getBillsno());
			try {
				this.memberService.createDealing(deal);
			} catch (ServiceMessage e) {
				return new ServiceMessage(false, "余额不足请先充值！", 5).getMessageModel();
			}
			try {
				this.orderService.exeEffectiveOrder(order.getRecid(),PayType.Balance);
			} catch (Throwable e) {
				return new ServiceMessage(false, "出错了！", 1).getMessageModel();
			}
		}
		return new ServiceMessage(true, "付款成功！", "").getMessageModel();

	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeInfoUpdate(InfoForm data, HttpServletRequest request) throws ServiceMessage {
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		MemberVo user = this.memberService.find(login.getRecid());
		if (null == user) {
			return new ServiceMessage(false, "账户异常！", 1).getMessageModel();
		}
		user.setMembername(data.getRealName());
		this.memberService.modifyMember(user);
		// 这里更新info
		MemberInfoVo vo = new MemberInfoVo();
		vo.setAddress(data.getAddress());
		StringBuilder s = new StringBuilder();
		s.append(data.getBirthday01());
		if (CheckIsNull.isNotEmpty(data.getBirthday02()) && data.getBirthday02().length() == 1) {
			s.append("0");
		}
		s.append(data.getBirthday02());
		if (CheckIsNull.isNotEmpty(data.getBirthday03()) && data.getBirthday03().length() == 1) {
			s.append("0");
		}
		s.append(data.getBirthday03());

		if (CmsString.matches(RegEx.OnlyNumber, s.toString())) {
			vo.setBirthday(Integer.parseInt(s.toString()));
		}
		vo.setCity(data.getCity());
		vo.setIdentity(data.getIdentity());
		vo.setLivingconditions(data.getLivingconditions());
		vo.setMaritalstatus(data.getMaritalstatus());
		vo.setMemberid(user.getRecid());
		vo.setMembername(data.getRealName());
		vo.setMobile(user.getMobile());
		vo.setProvince(data.getProvince());
		vo.setSex(data.getSex());
		vo.setTelephone(data.getTelephone());
		vo.setTown(data.getTown());
		this.memberService.modifyMemberInfo(vo);
		return new ServiceMessage(true, "保存账户信息成功！").getMessageModel();

	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeInitInfoPage(HttpServletRequest request) throws ServiceMessage {

		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		InfoForm form = new InfoForm();
		MemberVo user = this.memberService.find(login.getRecid());
		MemberInfoVo info = this.memberService.findMemberInfo(login.getRecid());
		form.setEmail(CmsString.hiddenStr(user.getEmail()));
		form.setMobile(CmsString.hiddenStr(user.getMobile()));
		form.setUsername(CmsString.hiddenStr(user.getUsername()));
		form.setRealName(user.getMembername());
		if (null != info) {
			form.setProvince(info.getProvince());
			form.setCity(info.getCity());
			form.setTown(info.getTown());
			form.setAddress(info.getAddress());
			form.setIdentity(info.getIdentity());
			form.setLivingconditions(info.getLivingconditions());
			form.setMaritalstatus(info.getMaritalstatus());
			form.setSex(info.getSex());
			form.setTelephone(info.getTelephone());
			if (info.getBirthday() > 0) {
				String b = info.getBirthday() + "";
				form.setBirthday01(b.substring(0, 4));
				form.setBirthday02(b.substring(4, 6));
				form.setBirthday03(b.substring(6, 8));
			}
		}
		form.setIdentityList(SparkDictionaryManager.getDicItemsList(DictionaryType.Identity));
		form.setLivingconditionsList(SparkDictionaryManager.getDicItemsList(DictionaryType.LivingCondition));
		form.setMaritalstatusList(SparkDictionaryManager.getDicItemsList(DictionaryType.MaritalStatus));
		return new ServiceMessage(true, "", form).getMessageModel();

	}

	/**
	 * 确认购买包月送货上门
	 * 
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exePayForDelivery(String id, String monthNo, String password,
			HttpSession session) throws ServiceMessage {
		Login login;
		if (CmsString.isEmpty(id) || CmsString.isEmpty(monthNo) || CmsString.isEmpty(password)) {
			return new ServiceMessage(false, "参数丢失！", 1).getMessageModel();
		}
		login = new Login((MemberVo) session.getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		DeliveryPriceVo form = deliveryPriceService.findDelivery(id);
		if (null == form) {
			return new ServiceMessage(false, "包月方案选择错误！", 1).getMessageModel();
		}
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			return new ServiceMessage(false, "账户异常", 1).getMessageModel();
		}
		if (CmsString.isEmpty(balance.getPaypassword())) {
			return new ServiceMessage(false, "您尚未设置支付密码！", 3).getMessageModel();
		}
		if (!balance.getPaypassword().equals(EncryptionUtil.encryptMD5(password))) {
			return new ServiceMessage(false, "支付密码不正确！", 4).getMessageModel();
		}
		if (balance.getMoneybalance() < form.getDprice()) {
			return new ServiceMessage(false, "余额不足请先充值！", 5).getMessageModel();
		}
		MemberDealingVo deal = new MemberDealingVo();
		deal.setAmount(-form.getDprice());
		deal.setMemberid(login.getRecid());
		deal.setDealtype(DealingsType.Consume.getCode());
		deal.setOccurdate(new Date());
		deal.setRelabillsid(form.getRecid());
		deal.setRelabillsno("送货上门包月");
		try {
			this.memberService.createDealing(deal);
		} catch (ServiceMessage e) {
			return new ServiceMessage(false, "余额不足请先充值！", 5).getMessageModel();
		}
		MemberDeliveryVo vo = new MemberDeliveryVo();
		vo.setBegindate(getBeginTime(monthNo));
		vo.setEnddate(getEndTime(monthNo));
		vo.setMember(login.getRecid());
		vo.setCounted(0l);
		vo.setDcount(Long.parseLong(form.getDcount() + ""));
		this.memberService.createDelivery(vo);
		return new ServiceMessage(true, "购买成功！", "").getMessageModel();
	}

	private Date getBeginTime(String monthNo) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(monthNo.substring(0, 4)), Integer.parseInt(monthNo.substring(4)), 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}

	private Date getEndTime(String monthNo) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(monthNo.substring(0, 4)), Integer.parseInt(monthNo.substring(4)), 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		return new Date(c.getTime().getTime() - 1);

	}

	/**
	 * 确认购买送货包月
	 * 
	 * @return
	 * @throws ServiceMessage
	 */
	public ModelAndView exeToSureBuyDelivery(String id, String monthNo, HttpSession session) throws ServiceMessage {
		ModelAndView maw = new ModelAndView();
		Login login;
		if (CmsString.isEmpty(id) || CmsString.isEmpty(monthNo)) {
			maw.addObject("message", "参数丢失！");
			maw.setViewName("/pro/member/delivercharge");
			return maw;
		}
		login = new Login((MemberVo) session.getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			maw.addObject("message", "请先登录！");
			maw.setViewName("/pro/member/delivercharge");
			return maw;
		}
		DeliveryForm form = deliveryPriceService.findDeliveryPrice(id);
		if (null == form) {
			maw.addObject("message", "包月方案选择错误！");
			maw.setViewName("/pro/member/delivercharge");
			return maw;
		}
		maw.addObject("id", form.getRecid());
		maw.addObject("dcount", form.getDcount());
		maw.addObject("dprice", form.getDprice());
		maw.addObject("monthNo", monthNo.substring(0, 4) + "年" + monthNo.substring(4) + "月");
		maw.addObject("monthNoValue", monthNo);
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			maw.addObject("message", "账户异常！");
			maw.setViewName("/pro/member/delivercharge");
			return maw;
		}
		maw.addObject("balance", DoubleUtil.getRoundStr(balance.getMoneybalance()));
		maw.setViewName("/pro/member/suredelivercharge");
		return maw;

	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getDeliveryListPerMonth(HttpServletRequest request) throws ServiceMessage {

		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		List<MemberDeliveryVo> list = this.memberService.getDeliveryList(login.getRecid());
		String[] counts = new String[7];
		int thisMonth = getMonthNo(new Date());
		for (MemberDeliveryVo vo : list) {
			int monthNo = getMonthNo(vo.getBegindate());
			int index = monthNo - thisMonth;
			if (index > 88) {
				index = index - 88;
			}
			counts[index] = vo.getDcount() + "";
		}
		return new ServiceMessage(true, "有效", counts).getMessageModel();

	}

	/**
	 * 获得月编号
	 * 
	 * @param date
	 * @return
	 */
	private int getMonthNo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String s = sdf.format(date);
		return Integer.valueOf(s);
	}

	/**
	 * 得到账户信息
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getAccountInfo(HttpServletRequest request) throws ServiceMessage {
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			return new ServiceMessage(false, "账户异常！", 0).getMessageModel();
		}
		AccountInfo info = new AccountInfo();
		info.setMemberId(login.getRecid());
		info.setMobile(login.getMobile());
		info.setEmail(login.getEmail());
		info.setUsername(login.getUsername());
		info.setMessage("有效");
		info.setMoneyBalance(DoubleUtil.getRoundStr(balance.getMoneybalance()));
		info.setVanteges(DoubleUtil.getRoundStr(balance.getVantages(), 0));
		List<MemberDeliveryVo> list = this.memberService.getDeliveryList(login.getRecid());
		int thisMonth = getMonthNo(new Date());
		String value = "0";
		if (list != null && !list.isEmpty() && thisMonth == getMonthNo(list.get(0).getBegindate())) {
			value = "" + list.get(0).getDcount();
		}
		info.setDeliveryCountThisMonth(value);
		return new ServiceMessage(true, "有效", info).getMessageModel();

	}

	/**
	 * 得到送货包月方案
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getDeliveryPriceList(HttpServletRequest request) throws ServiceMessage {

		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		GetDeliveryListKey key = new GetDeliveryListKey();
		key.setIsOnlyActiving(true);
		DataModel<DeliveryForm> dm = this.deliveryPriceService.getDeliveryPriceList(key);
		return new ServiceMessage(true, "有效", dm.getRows()).getMessageModel();

	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> getBalanceAndVangete(HttpServletRequest request) throws ServiceMessage {
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			return new ServiceMessage(false, "账户异常！", 0).getMessageModel();
		}
		String[] array = new String[] { login.getEmail(), DoubleUtil.getRoundStr(balance.getMoneybalance()),
				DoubleUtil.getRoundStr(balance.getVantages(), 0) };
		return new ServiceMessage(true, "有效", array).getMessageModel();

	}

	/**
	 * 得到积分往来
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> getVantegeLogs(String pageIndex, HttpServletRequest request) throws Throwable {

		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		int offset = 0, pageSize = CMS.TablePageSize;
		if (CmsString.isNotEmpty(pageIndex)) {
			offset = Integer.parseInt(pageIndex);
		}
		GetVantagesListKey key = new GetVantagesListKey(offset, pageSize, true);
		key.setMemberId(login.getRecid());
		List<MemberVantagesVo> volist = this.memberService.getVantagesList(key);
		List<VantegesLogForm> forms = new ArrayList<VantegesLogForm>();
		for (MemberVantagesVo vo : volist) {
			VantegesLogForm form = new VantegesLogForm();
			form.setOccurdate(DateUtil.dateFromat(vo.getOccurdate(), DateUtil.DATE_TIME_PATTERN2));
			form.setRelabillsno(vo.getRelabillsno().replace(SheetNumberType.OnlineOrder.getDefaultPrefix(), ""));
			form.setVtype(VantagesType.getType(vo.getVtype()).getName());
			if (vo.getVantages() > 0) {
				form.setSumVantages(DoubleUtil.getRoundStr(vo.getVantages(), 0));
				form.setSubVantages(0 + "");
			} else {
				form.setSumVantages(0 + "");
				form.setSubVantages(DoubleUtil.getRoundStr(vo.getVantages(), 0).replace("-", ""));
			}
			forms.add(form);
		}
		return new ServiceMessage(true, "有效", forms).getMessageModel();

	}

	/**
	 * 得到账户往来
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ResponseEntity<MessageModel> getBalanceLogs(String pageIndex, HttpServletRequest request) throws Throwable {

		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 2).getMessageModel();
		}
		int offset = 0, pageSize = CMS.TablePageSize;
		if (CmsString.isNotEmpty(pageIndex)) {
			offset = Integer.parseInt(pageIndex);
		}
		GetDealingListKey key = new GetDealingListKey(offset, pageSize, true);
		key.setMemberId(login.getRecid());
		List<MemberDealingVo> volist = this.memberService.getDealingList(key);
		List<DealingLogForm> forms = new ArrayList<DealingLogForm>();
		for (MemberDealingVo vo : volist) {
			DealingLogForm form = new DealingLogForm();
			if (vo.getAmount() > 0) {
				form.setSumAmount(DoubleUtil.getRoundStr(vo.getAmount()));
				form.setSubAmount("--");
			} else {
				form.setSubAmount(DoubleUtil.getRoundStr(vo.getAmount()).replace("-", ""));
				form.setSumAmount("--");
			}
			form.setDate(DateUtil.dateFromat(vo.getOccurdate(), DateUtil.DATE_TIME_PATTERN2));
			form.setDealType(DealingsType.getType(vo.getDealtype()).getName());
			form.setRelaBillsNo(vo.getRelabillsno().replace(SheetNumberType.OnlineOrder.getDefaultPrefix(), ""));
			forms.add(form);
		}
		return new ServiceMessage(true, "有效", forms).getMessageModel();

	}

	/**
	 * 得到账户余额
	 * 
	 * @param from
	 * @return
	 * @throws Throwable
	 */
	public ModelAndView getBalance(HttpServletRequest request) throws Throwable {
		ModelAndView mv = new ModelAndView();
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			mv.setViewName("/pub/sub/login");
			return mv;
		}
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			mv.setViewName("/pro/member/orderCenter");
			return mv;
		}
		mv.addObject("moneyBalance", DoubleUtil.getRoundStr(balance.getMoneybalance()));
		mv.setViewName("/pro/member/accountbalance");
		mv.addObject("memberStatus", "有效");
		GetDealingListKey key = new GetDealingListKey(0, 0, true);
		key.setMemberId(login.getRecid());
		int count = this.memberService.getDealingCount(key);
		mv.addObject("listSize", count);
		return mv;

	}

	/**
	 * 设置支付密码
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeCheckPayAccountSms(String smsCheckCode, HttpServletRequest request)
			throws ServiceMessage {

		if (CmsString.isEmpty(smsCheckCode)) {
			return new ServiceMessage(false, "参数传输错误！", 0).getMessageModel();
		}
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
		}
		MemberVo user = this.memberService.find(login.getRecid());
		CheckCode cc = CheckCodeManager.getMobileSafeMemberId(user.getRecid());
		if (cc == null || !cc.getCode().equals(smsCheckCode)) {
			return new ServiceMessage(false, "验证码已过期！", 1).getMessageModel();
		}
		ServiceMessage sMessage = this.memberService.modifyPayPassword(user.getRecid(), cc.getRelaObj().toString());
		return sMessage.getMessageModel();

	}

	/**
	 * 发送短线验证码
	 * 
	 * @param from
	 * @return
	 * @throws ServiceMessage
	 */
	public ResponseEntity<MessageModel> exeSendPayAccountSms(String password, HttpServletRequest request)
			throws ServiceMessage {

		if (CmsString.isEmpty(password)) {
			return new ServiceMessage(false, "参数传输错误！", 0).getMessageModel();
		}
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
		}
		MemberVo user = this.memberService.find(login.getRecid());
		String code = CmsString.getRandomNumStr(6);
		CheckCodeManager.putMobileSafeCheckCode(code, login.getRecid(), password);
		SmsSendTask task = new SmsSendTask();
		task.setPhoneNo(user.getMobile());
		task.setMessage("尊敬的顾客，7号生活馆提醒您：您申请的手机校验码为" + code + "，请在页面中提交校验码完成验证");
		this.smsService.sendMsg(task);
		return new ServiceMessage(true, "").getMessageModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.front.service.ActionService#toVantegePage(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ModelAndView toVantegePage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			mv.setViewName("/pub/sub/login");
			return mv;
		}
		MemberAccountVo balance = this.memberService.getMemberAccountVo(login.getRecid());
		if (balance == null) {
			mv.setViewName("/pro/member/orderCenter");
			return mv;
		}
		mv.addObject("vantegeBalance", DoubleUtil.getRoundStr(balance.getVantages(), 0));
		mv.setViewName("/pro/member/accountvantege");
		GetVantagesListKey key = new GetVantagesListKey(0, 0, true);
		key.setMemberId(login.getRecid());
		int count = this.memberService.getVantagesCount(key);
		mv.addObject("listSize", count);
		return mv;
	}
}
