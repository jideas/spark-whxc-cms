/**
 * 
 */
package com.spark.front.action.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.type.GUID;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.constant.card.CMS;
import com.spark.cms.base.utils.encrypt.EncryptionUtil;
import com.spark.cms.common.Constant;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.Constant.MemberEnum.MemberStatus;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.email.EmailService;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.member.key.GetMemberListKey;
import com.spark.cms.services.sms.SmsService;
import com.spark.cms.services.sms.impl.SmsServiceImpl;
import com.spark.cms.services.sms.utils.SmsSendTask;
import com.spark.cms.services.vo.MemberVo;
import com.spark.front.form.member.RegistMemberForm;
import com.spark.front.utils.CheckCode;
import com.spark.front.utils.CheckCodeManager;
import com.spark.front.utils.CmsString;
import com.spark.front.utils.RegEx;

/**
 * 会员登录、注册、登出
 * 
 * @author Jideas
 */
@Controller
public class LoginAction extends BaseAction {
	@Autowired
	private MemberService memberService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private EmailService emailService;

	/**
	 * 是否已登录
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/hasLogin")
	@ResponseBody
	public ResponseEntity<MessageModel> hasLogin(HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
			}
			return new ServiceMessage(true, login.getUsername(), 1).getMessageModel();
		} catch (Throwable e) {
			log.error("是否已登录发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
		}
	}

	/**
	 * 是否已验证手机
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/isCheckedMobile")
	@ResponseBody
	public ResponseEntity<MessageModel> isCheckedMobile(HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
			}
			MemberVo user = this.memberService.find(login.getRecid());
			if (user.isCheckedMobile()) {
				return new ServiceMessage(true, "").getMessageModel();
			} else {
				return new ServiceMessage(false, "设置支付密码之前，必须先进行手机验证").getMessageModel();
			}
		} catch (Throwable e) {
			log.error("是否已验证手机发生异常====" + e.getStackTrace());

			return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
		}
	}

	/**
	 * 绑定手机
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/checkSafeSms")
	@ResponseBody
	public ResponseEntity<MessageModel> checkSafeSms(@RequestParam(value = "smsCheckCode", required = true)
	String smsCheckCode, HttpServletRequest request) {
		try {
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
			ServiceMessage sMessage = this.memberService.modifyMobile(user.getRecid(), cc.getRelaObj().toString());
			return sMessage.getMessageModel();
		} catch (ServiceMessage e) {
			log.error("绑定手机发生异常====" + e.getStackTrace());

			return new ServiceMessage(false, e.getMessage(), 0).getMessageModel();
		} catch (Throwable e) {
			log.error("绑定手机发生异常====" + e.getStackTrace());

			return new ServiceMessage(false, "发送短信验证码发生异常,请重试!", 0).getMessageModel();
		}
	}

	/**
	 * 发送短线验证码
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/sendSafeSms")
	@ResponseBody
	public ResponseEntity<MessageModel> sendSafeSms(@RequestParam(value = "password", required = true)
	String password, @RequestParam(value = "phoneNo", required = true)
	String phoneNo, HttpServletRequest request) {
		try {
			if (CmsString.isEmpty(password) && CmsString.isEmpty(phoneNo)) {
				return new ServiceMessage(false, "参数传输错误！", 0).getMessageModel();
			}
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "请先登录！", 0).getMessageModel();
			}
			MemberVo user = this.memberService.find(login.getRecid());
			if (!user.getPassword().equals(EncryptionUtil.encryptMD5(password))) {
				return new ServiceMessage(false, "登录密码错误！", 1).getMessageModel();
			}
			String code = CmsString.getRandomNumStr(6);
			CheckCodeManager.putMobileSafeCheckCode(code, login.getRecid(), phoneNo);
			SmsSendTask task = new SmsSendTask();
			task.setPhoneNo(phoneNo);
			task.setMessage("尊敬的顾客，7号生活馆提醒您：您申请的手机校验码为" + code + "，请在页面中提交校验码完成验证");
			this.smsService.sendMsg(task);
			return new ServiceMessage(true, "").getMessageModel();
		} catch (ServiceMessage e) {
			log.error("发送短信验证码发生异常====" + e.getStackTrace());

			return new ServiceMessage(false, e.getMessage(), 0).getMessageModel();
		} catch (Throwable e) {
			log.error("发送短信验证码发生异常====" + e.getStackTrace());

			return new ServiceMessage(false, "发送短信验证码发生异常,请重试!", 0).getMessageModel();
		}
	}
	
	/**
	 * 群发短信
	 * @param args
	 */
	@RequestMapping("/login/batchSendSafeSms")
	@ResponseBody
	public ResponseEntity<MessageModel> batchSendSafeSms(@RequestParam(value = "ids[]", required = true) String[] ids,
			@RequestParam(value = "SMSContent", required = true) String SMSContent) {
		try{
			//获取发送短信的会员
			List<MemberVo> memberVoList;
			if(ids != null && ids.length > 0){
				memberVoList = new ArrayList<MemberVo>();
				for(String id : ids){
					MemberVo memberVo = this.memberService.find(id);
					if(memberVo == null) continue;
					memberVoList.add(memberVo);
				}
			}else{
				GetMemberListKey getMemberListKey = new GetMemberListKey(0,0,true);
				memberVoList = this.memberService.getList(getMemberListKey);
			}
			//向会员发送短信
			for(MemberVo memberVo : memberVoList){
				if(memberVo.getMobile() == null || memberVo.getMobile() == "") continue;
				SmsSendTask task = new SmsSendTask();
				task.setPhoneNo(memberVo.getMobile());
				task.setMessage(SMSContent);
				this.smsService.sendMsg(task);
				Thread.sleep(1000);
			}
			return new ServiceMessage(true, "短信发送成功!", 0).getMessageModel();
		}catch(Exception e){
			return new ServiceMessage(false, "发送短信验证码发生异常,请重试!", 0).getMessageModel();
		}	
	}

	/*
	*/
	/**
	 * 确认邮箱
	 * 
	 * @return
	 */
	@RequestMapping("/sureEmail")
	public ModelAndView sureEmail(@RequestParam(value = "code", required = false)
	String code, HttpSession session) {
		if (CmsString.isEmpty(code)) {
			return null;
		}
		code = code.trim();
		String id = null;
		CheckCode checkCode = null;
		ModelAndView maw = new ModelAndView();
		maw.setViewName("/pub/sub/emailCheck");
		try {
			checkCode = CheckCodeManager.getEmailSafeMemberId(EncryptionUtil.decryptAES(code, CMS.CommonSecretKey));
		} catch (Exception e) {

			return maw;
		}
		if (null != checkCode && checkCode.getRelaObj() != null) {
			id = checkCode.getObjectId();
		}
		MemberVo user = null;
		if (CmsString.isNotEmpty(id)) {
			user = memberService.find(id);
		}
		if (null == user) {
			return maw;
		}
		session.removeAttribute(Constant.LoginMemberUser);
		ServiceMessage sMessage = this.memberService.modifyEmail(id, checkCode.getRelaObj().toString());
		if (sMessage.isOperSuccess()) {
			maw.addObject("id", user.getRecid());
			CheckCodeManager.removeMobilePassCheckCode(id);
		}
		return maw;
	}

	/**
	 * 修改邮箱
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/changeEmail")
	@ResponseBody
	public ResponseEntity<MessageModel> changeEmail(@RequestParam(value = "password", required = false)
	String password, @RequestParam(value = "email", required = false)
	String email, HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "请先登录！").getMessageModel();
			}
			MemberVo user = this.memberService.find(login.getRecid());
			if (null == user) {
				return new ServiceMessage(false, "请先登录！").getMessageModel();
			}
			if (!user.getPassword().equals(EncryptionUtil.encryptMD5(password))) {
				return new ServiceMessage(false, "登录密码不正确！").getMessageModel();
			}
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			String code = GUID.randomID().toString();
			CheckCodeManager.putEmailSafeCheckCode(code, user.getRecid(), email);
			url = url + "/front/sureEmail?code=" + EncryptionUtil.encryptAES(code, CMS.CommonSecretKey);
			ServiceMessage smessage = this.emailService.sendEmail(email, "邮箱验证(7号生活馆)", getHtmlText(url, "验证邮箱"));
			return smessage.getMessageModel();
		} catch (ServiceMessage e) {
			return e.getMessageModel();
		} catch (Throwable e) {
			log.error("修改邮箱地址发生异常====" + e.getStackTrace());

			return new ServiceMessage("修改邮箱地址发生异常,请重试!").getMessageModel();
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/changePass")
	@ResponseBody
	public ResponseEntity<MessageModel> changePass(@RequestParam(value = "password", required = true)
	String password, @RequestParam(value = "prepassword", required = true)
	String prepassword, HttpSession session) {
		try {
			Login login = new Login((MemberVo) session.getAttribute(Constant.LoginMemberUser));
			if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
				return new ServiceMessage(false, "请先登录！").getMessageModel();
			}
			// 验证密码并且更新数据
			if (!CmsString.matches(RegEx.PassWord, password)) {
				return new ServiceMessage(false, "密码格式不正确！").getMessageModel();
			}
			if (password.length() < 6 || password.length() > 16) {
				return new ServiceMessage(false, "密码长度不正确！").getMessageModel();
			}
			this.memberService.modifyPassword(login.getRecid(), prepassword, password);
			return new ServiceMessage(true, "修改密码成功！").getMessageModel();
		} catch (ServiceMessage e) {
			return e.getMessageModel();
		} catch (Throwable e) {
			log.error("修改密码发生异常====" + e.getStackTrace());
			return new ServiceMessage("修改密码发生异常,请重试!").getMessageModel();
		}
	}

	/**
	 * 找回密码第三步
	 * 
	 * @return
	 */
	@RequestMapping("/toEmailCheck")
	public ModelAndView toEmailCheck(@RequestParam(value = "code", required = false)
	String code) {
		if (CmsString.isEmpty(code)) {
			return null;
		}
		code = code.trim();
		String id = null;
		CheckCode checkCode = null;
		try {
			checkCode = CheckCodeManager.getEmailPassMemberId(EncryptionUtil.decryptAES(code, CMS.CommonSecretKey));
		} catch (Exception e) {

			ModelAndView maw = new ModelAndView();
			maw.addObject("message", "密码重置信息已失效，请重试！");
			maw.addObject("lastUsername", "");
			maw.setViewName("/pub/sub/getPass");
			return maw;
		}
		if (null != checkCode) {
			id = checkCode.getObjectId();
		}
		MemberVo user = null;
		if (CmsString.isNotEmpty(id)) {
			user = memberService.find(id);
		}
		if (null == user) {
			ModelAndView maw = new ModelAndView();
			maw.addObject("message", "密码重置信息已失效，请重试！");
			maw.addObject("lastUsername", "");
			maw.setViewName("/pub/sub/getPass");
			return maw;
		}
		ModelAndView maw = new ModelAndView();
		maw.addObject("id", user.getRecid());
		maw.setViewName("/pub/sub/reSetPass");
		CheckCodeManager.removeMobilePassCheckCode(id);
		return maw;
	}

	/**
	 * 发送邮件改密码验证码
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/sendCheckEmail")
	@ResponseBody
	public ResponseEntity<MessageModel> sendCheckEmail(@RequestParam(value = "id", required = true)
	String id, HttpServletRequest request) {
		try {
			if (CmsString.isEmpty(id)) {
				return new ServiceMessage(false, "参数传输错误！").getMessageModel();
			}
			String code = GUID.randomID().toString();
			CheckCodeManager.putEmailPassCheckCode(code, id, null);
			MemberVo user = memberService.find(id);
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			url = url + "/front/toEmailCheck?code=" + EncryptionUtil.encryptAES(code, CMS.CommonSecretKey);
			return this.emailService.sendEmail(user.getEmail(), "密码重置邮件(7号生活馆)", getHtmlText(url, "找回密码"))
					.getMessageModel();
		} catch (Throwable e) {
			log.error("发送短信验证码发生异常====" + e.getStackTrace());

			return new ServiceMessage("发送短信验证码发生异常,请重试!").getMessageModel();
		}
	}

	/**
	 * @param user
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String getHtmlText(String url, String key) {
		StringBuilder h = new StringBuilder();
		h.append("<strong>尊敬的用户，您好:</strong><br>");
		h.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;");
		h.append("您在7号生活馆（www.yc777.com.cn）点击了“" + key + "”按钮，故系统在");
		h.append(new Date().toLocaleString());
		h.append("自动为您发送了这封邮件。您可以点击下面的按钮" + key + "：（按钮2小时内有效）<br>");
		h.append("<input type='button' value='重置密码' onclick='javascript：window.open('" + url + "');'/>");
		h.append("<br>如果按钮无法点击， 请复制下面的链接到浏览器地址栏访问。");
		h.append("<br><a href='" + url + "'>" + url + "</a><br>");
		h.append("<br>如果您没有提交" + key + "请求，请忽略此邮件。");
		h.append("<br><br>");
		h.append("本邮件由系统自动发出，请勿直接回复。");
		h.append("<br><br>");
		h.append("如有任何疑问，请联系7号生活馆客服，客服热线：4001-027-577");
		return h.toString();
	}

	/**
	 * 找回密码第三步
	 * 
	 * @return
	 */
	@RequestMapping("/updatePass")
	public ModelAndView updatePass(@RequestParam(value = "id", required = false)
	String id, @RequestParam(value = "password", required = true)
	String password) {
		if (CmsString.isEmpty(id) || CmsString.isEmpty(password)) {
			return null;
		}
		id = id.trim();
		password = password.trim();
		// 验证密码并且更新数据
		if (!CmsString.matches(RegEx.PassWord, password)) {
			ModelAndView maw = new ModelAndView();
			maw.setViewName("/pro/member/getPass3");
			return maw;
		}
		if (password.length() < 6 || password.length() > 16) {
			ModelAndView maw = new ModelAndView();
			maw.setViewName("/pro/member/getPass3");
			return maw;
		}
		try {
			this.memberService.modifyPassword(id, password);
			ModelAndView maw = new ModelAndView();
			maw.setViewName("/pro/member/getPass4");
			return maw;
		} catch (Throwable e) {

			ModelAndView maw = new ModelAndView();
			maw.setViewName("/pro/member/getPass3");
			return maw;
		}
	}

	/**
	 * 找回密码第二步
	 * 
	 * @return
	 */
	@RequestMapping("/checkSms")
	public ModelAndView checkSms(@RequestParam(value = "id", required = false)
	String id, @RequestParam(value = "checkcode", required = false)
	String checkcode) {
		if (CmsString.isEmpty(id) || CmsString.isEmpty(checkcode)) {
			return null;
		}
		id = id.trim();
		checkcode = checkcode.trim();
		MemberVo member = this.memberService.find(id);
		if (member == null) {
			ModelAndView maw = new ModelAndView();
			maw.addObject("message", "用户名不存在！");
			maw.addObject("lastUsername", "");
			maw.setViewName("/pro/member/getPass1");
			return maw;
		}
		if (null == CheckCodeManager.getMobilePassMemberId(id)
				|| !CheckCodeManager.getMobilePassMemberId(id).getCode().equals(checkcode)) {
			ModelAndView maw = new ModelAndView();
			maw.addObject("id", member.getRecid());
			maw.addObject("username", CmsString.hiddenStr(member.getUsername()));
			maw.addObject("email", CmsString.hiddenStr(member.getEmail()));
			maw.addObject("mobile", CmsString.hiddenStr(member.getMobile()));
			maw.addObject("message", "验证码已过期！");
			maw.setViewName("/pro/member/getPass2");
			return maw;
		} else if (CheckCodeManager.getMobilePassMemberId(id).getCode().equals(checkcode)) {
			ModelAndView maw = new ModelAndView();
			maw.addObject("id", member.getRecid());
			maw.setViewName("/pro/member/getPass3");
			CheckCodeManager.removeMobilePassCheckCode(id);
			return maw;
		}
		return null;
	}

	/**
	 * 发送短线验证码
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/sendSms")
	@ResponseBody
	public ResponseEntity<MessageModel> sendSms(@RequestParam(value = "id", required = true)
	String id) {
		try {
			if (CmsString.isEmpty(id)) {
				return new ServiceMessage(false, "参数传输错误！").getMessageModel();
			}
			String code = CmsString.getRandomNumStr(6);
			CheckCodeManager.putMobilePassCheckCode(code, id, null);
			MemberVo user = memberService.find(id);
			SmsSendTask task = new SmsSendTask();
			task.setPhoneNo(user.getMobile());
			task.setMessage("尊敬的顾客，7号生活馆提醒您：您申请的手机校验码为" + code + "，请在页面中提交校验码完成验证");
			this.smsService.sendMsg(task);
			return new ServiceMessage(true, "").getMessageModel();
		} catch (Throwable e) {
			log.error("发送短信验证码发生异常====" + e.getStackTrace());

			return new ServiceMessage("发送短信验证码发生异常,请重试!").getMessageModel();
		}
	}

	/**
	 * 找回密码第一步
	 * 
	 * @return
	 */
	@RequestMapping("/getPass1")
	public ModelAndView getPass1(@RequestParam(value = "username", required = true)
	String username) {
		if (CmsString.isEmpty(username)) {
			return null;
		}
		username = username.trim();
		MemberVo member = this.memberService.getMemberByUsername(username);
		if (member == null) {
			ModelAndView maw = new ModelAndView();
			maw.addObject("message", "用户名不存在！");
			maw.addObject("lastUsername", username);
			maw.setViewName("/pro/member/getPass1");
			return maw;
		}
		ModelAndView maw = new ModelAndView();
		maw.addObject("id", member.getRecid());
		maw.addObject("username", CmsString.hiddenStr(member.getUsername()));
		maw.addObject("email", CmsString.hiddenStr(member.getEmail()));
		maw.addObject("mobile", CmsString.hiddenStr(member.getMobile()));
		maw.setViewName("/pro/member/getPass2");
		return maw;
	}

	/**
	 * 注册用户
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public ResponseEntity<MessageModel> regist(HttpSession session, RegistMemberForm form) {
		try {
			if (!form.validate()) {
				return new ServiceMessage(false, "提交的数据不正确！").getMessageModel();
			}
			MemberVo vo = new MemberVo();
			vo.setEmail(form.getEmail());
			vo.setInvitationcode(form.getInvitationcode());
			vo.setStatus(MemberStatus.Actived.getCode());
			vo.setUsername(form.getUserName());
			vo.setMobile(form.getMobile());
			vo.setPassword(form.getPassword());
			ServiceMessage sMessage = this.memberService.createMember(vo);
			if (sMessage.isOperSuccess()) {
				session.setAttribute(Constant.LoginMemberUser, vo);
			}
			return sMessage.getMessageModel();
		} catch (Throwable e) {
			log.error("会员注册发生异常====" + e.getStackTrace());
			return new ServiceMessage("会员注册发生异常,请重试!").getMessageModel();
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResponseEntity<MessageModel> login(HttpServletRequest request, RegistMemberForm form) {
		try {
			if (CmsString.isEmpty(form.getUserName()) || CmsString.isEmpty(form.getPassword())) {
				return new ServiceMessage(false, "数据传输错误！").getMessageModel();
			} 
			ServiceMessage sMessage = this.memberService.getLogin(form.getUserName().trim(), form.getPassword().trim());
			if (sMessage.isOperSuccess()) {
				request.getSession().setAttribute(Constant.LoginMemberUser, sMessage.getReturnObj());
				return new ServiceMessage(true, "登录成功！", request.getParameter("forwardUrl")).getMessageModel();
			} else {
				return sMessage.getMessageModel();
			}
		} catch (Throwable e) {
			log.error("会员注册发生异常====" + e.getStackTrace());
			return new ServiceMessage("会员注册发生异常,请重试!").getMessageModel();
		}
	}

	/**
	 * 用户登出
	 * 
	 * @param from
	 * @return
	 */
	@RequestMapping("/logout")
	public ResponseEntity<MessageModel> logout(HttpSession session) {
		session.removeAttribute(Constant.LoginMemberUser);
		return new ServiceMessage(true, "").getMessageModel();
	}
}
