/**
 * 
 */
package com.spark.front.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.ServiceMessage;
import com.spark.front.form.member.InfoForm;
import com.spark.front.service.ActionService;

/**
 * @author Jideas
 * 
 */
@Controller
public class AccountAction extends BaseAction {

	@Autowired
	private ActionService actionService;

	/**
	 * 订单确认付款
	 * 
	 * @return
	 */
	@RequestMapping("/payForOrders")
	@ResponseBody
	public ResponseEntity<MessageModel> payForOrders(@RequestParam(value = "ids", required = false)
	String ids, @RequestParam(value = "password", required = false)
	String password, HttpSession session) {

		try {
			return this.actionService.exePayForOrders(ids, password, session);
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "出错了！", 2).getMessageModel();
		}
	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/infoUpdate")
	@ResponseBody
	public ResponseEntity<MessageModel> infoUpdate(InfoForm data, HttpServletRequest request) {
		try {
			return this.actionService.exeInfoUpdate(data, request);
		} catch (ServiceMessage e) {
			return new ServiceMessage(false, e.getMessage(), 2).getMessageModel();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "操作失败！", 1).getMessageModel();
		}
	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/initInfoPage")
	@ResponseBody
	public ResponseEntity<MessageModel> initInfoPage(HttpServletRequest request) {
		try {
			return this.actionService.exeInitInfoPage(request);
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 确认购买包月送货上门
	 * 
	 * @return
	 */
	@RequestMapping("/payForDelivery")
	@ResponseBody
	public ResponseEntity<MessageModel> payForDelivery(@RequestParam(value = "id", required = false)
	String id, @RequestParam(value = "monthNo", required = false)
	String monthNo, @RequestParam(value = "password", required = false)
	String password, HttpSession session) {
		try {
			return this.actionService.exePayForDelivery(id, monthNo, password, session);
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "出错了！", 2).getMessageModel();
		}
	}

	/**
	 * 确认购买送货包月
	 * 
	 * @return
	 */
	@RequestMapping("/toSureBuyDelivery")
	public ModelAndView toSureBuyDelivery(@RequestParam(value = "id", required = false)
	String id, @RequestParam(value = "monthNo", required = false)
	String monthNo, HttpSession session) {
		try {
			return this.actionService.exeToSureBuyDelivery(id, monthNo, session);
		} catch (Throwable e) {
			ModelAndView maw = new ModelAndView();
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			maw.addObject("message", "出错了！");
			maw.setViewName("/pro/member/delivercharge");
			return maw;
		}
	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getDeliveryListPerMonth")
	@ResponseBody
	public ResponseEntity<MessageModel> getDeliveryListPerMonth(HttpServletRequest request) {
		try {
			return this.actionService.getDeliveryListPerMonth(request);
		} catch (ServiceMessage e) {
			return new ServiceMessage(false, e.getMessage(), 2).getMessageModel();
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到账户信息
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getAccountInfo")
	@ResponseBody
	public ResponseEntity<MessageModel> getAccountInfo(HttpServletRequest request) {
		try {
			return this.actionService.getAccountInfo(request);
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到送货包月方案
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getDeliveryPriceList")
	@ResponseBody
	public ResponseEntity<MessageModel> getDeliveryPriceList(HttpServletRequest request) {
		try {
			return this.actionService.getDeliveryPriceList(request);
		} catch (ServiceMessage e) {
			return new ServiceMessage(false, e.getMessage(), 2).getMessageModel();
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到邮箱、余额和积分
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getBalanceAndVangete")
	@ResponseBody
	public ResponseEntity<MessageModel> getBalanceAndVangete(HttpServletRequest request) {
		try {
			return this.actionService.getBalanceAndVangete(request);
		} catch (ServiceMessage e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace(); 
			return new ServiceMessage(false,"请先登录！",2).getMessageModel();
		}
	}

	/**
	 * 得到积分往来
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getVantegeLogs")
	@ResponseBody
	public ResponseEntity<MessageModel> getVantegeLogs(@RequestParam(value = "pageIndex", required = false)
	String pageIndex, HttpServletRequest request) {
		try {
			return this.actionService.getVantegeLogs(pageIndex, request);
		} catch (ServiceMessage e) {
			return new ServiceMessage(false, e.getMessage(), 2).getMessageModel();
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到账户往来
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getBalanceLogs")
	@ResponseBody
	public ResponseEntity<MessageModel> getBalanceLogs(@RequestParam(value = "pageIndex", required = false)
	String pageIndex, HttpServletRequest request) {
		try {
			return this.actionService.getBalanceLogs(pageIndex, request);
		} catch (ServiceMessage e) {
			return new ServiceMessage(false, e.getMessage(), 2).getMessageModel();
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到账户余额
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/getBalance")
	public ModelAndView getBalance(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			return this.actionService.getBalance(request);
		} catch (ServiceMessage e) {
			mv.setViewName("/pub/sub/login");
			return mv;
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 得到账户余额
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/toVantegePage")
	public ModelAndView toVantegePage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			return this.actionService.toVantegePage(request);
		} catch (ServiceMessage e) {
			mv.setViewName("/pub/sub/login");
			return mv;
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 设置支付密码
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/checkPayAccountSms")
	@ResponseBody
	public ResponseEntity<MessageModel> checkPayAccountSms(@RequestParam(value = "smsCheckCode", required = true)
	String smsCheckCode, HttpServletRequest request) {
		try {
			return this.actionService.exeCheckPayAccountSms(smsCheckCode, request);
		} catch (ServiceMessage e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return new ServiceMessage(false, e.getMessage(), 0).getMessageModel();
		} catch (Throwable e) {
			log.error("设置支付密码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return new ServiceMessage(false, "发送短信验证码发生异常,请重试!", 0).getMessageModel();
		}
	}

	/**
	 * 发送短线验证码
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/sendPayAccountSms")
	@ResponseBody
	public ResponseEntity<MessageModel> sendPayAccountSms(@RequestParam(value = "password", required = true)
	String password, HttpServletRequest request) {
		try {
			return this.actionService.exeSendPayAccountSms(password, request);
		} catch (ServiceMessage e) {
			log.error("发送短信验证码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return new ServiceMessage(false, e.getMessage(), 0).getMessageModel();
		} catch (Throwable e) {
			log.error("发送短信验证码发生异常====" + e.getStackTrace());
			e.printStackTrace();
			return new ServiceMessage(false, "发送短信验证码发生异常,请重试!", 0).getMessageModel();
		}
	}

}
