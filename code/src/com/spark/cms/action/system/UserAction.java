package com.spark.cms.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.base.common.utils.StringUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.common.tree.TreeModel;
import com.spark.cms.manager.system.UserManage;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.vo.UserVo;

/**
 * 用户管理
 */
@Controller
public class UserAction extends BaseAction {
	@Autowired
	private UserManage userManage;

	// @RequestMapping(value = "/*")
	// public String adminLogin() {
	// return "/login";
	// }

	@RequestMapping(value = "/*")
	public String adminLogin(HttpServletRequest request) {
		return "/app/cms/admin/login";
	}
	
	@RequestMapping(value = "/user/toDesktop/{id}")
	public ModelAndView goDesktop(@PathVariable String id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String userid = (String)request.getSession().getAttribute(Constant.Login);
		if (userid == null|| !userid.equals(id)) {
			request.getSession().invalidate();
			mav.setViewName( "redirect:/admin");
			return mav;
		}  else {
			
			UserExtForm userExtForm = userManage.getUserInfo(id);
			request.getSession().setAttribute(Constant.LoginAdminUser, userExtForm);
			request.getSession().setAttribute(Constant.Login,id);
			
			mav.setViewName( "redirect:/admin/desktop");
			mav.addObject(Constant.LoginAdminUser,userExtForm);
			return mav;
		}
	}
	
	
	@RequestMapping(value = "/desktop")
	public ModelAndView desktop(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getSession().getAttribute(Constant.LoginAdminUser) == null) {
			request.getSession().invalidate();
			mav.setViewName( "redirect:/admin");
			return mav;
		}  else {
			mav.setViewName( "/app/cms/admin/desktop");
			mav.addObject(Constant.LoginAdminUser,request.getSession().getAttribute(Constant.LoginAdminUser));
			return mav;
		}
		
		
	}

	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/user/login")
	public ResponseEntity<MessageModel> userLogin(UserExtForm user,
			HttpServletRequest request) {
		MessageModel messageModel = new MessageModel();
		/*
		 * // 获取验证码 String sessionKey = (String)
		 * request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		 * String kaptcha = (String)
		 * request.getParameter(Constant.Login_CheckWord); // 验证码验证 if
		 * (sessionKey == null || !sessionKey.equalsIgnoreCase(kaptcha)) {
		 * 
		 * messageModel.setResult(false);
		 * messageModel.setMessage("验证码无效，请重新输入!"); return
		 * ResponseEntityUtil.getResponseEntity(messageModel);
		 *  }
		 */
		String userAccount = user.getAccount();
		String ps = user.getPwd();

		//String userId = userManage.getLoginInfo(userAccount, ps);

		UserVo userInfo = userManage.getUserInfoByLoginNameAndPassword(userAccount, ps);
		if (null == userInfo) {
			messageModel.setResult(false);
			messageModel.setMessage("1");
		} else if ("0".equals(userInfo.getIsEnabled())){
			messageModel.setResult(false);
			messageModel.setMessage("2");
		} else {
			UserExtForm userExtForm = userManage.getUserInfo(userInfo.getId());
			request.getSession().setAttribute(Constant.LoginAdminUser, userExtForm);
			messageModel.setResult(true);
			messageModel.setMessage(userInfo.getId());
			request.getSession().setAttribute(Constant.Login,userInfo.getId());
		}
		return ResponseEntityUtil.getResponseEntity(messageModel);
	}

	/**
	 * 用户注销
	 */
	@RequestMapping(value = "/user/logout")
	public String userLogout(HttpSession session,HttpServletRequest request) {
		session.removeAttribute(Constant.LoginAdminUser);
		session.invalidate();
		return "redirect:/admin";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/user/updatePwd")
	@ResponseBody
	public ResponseEntity<MessageModel>  updatePwd(@RequestParam(value = "oldPwd", required = true) String oldPwd,
			@RequestParam(value = "newPwd", required = true) String newPwd,
			HttpSession session) {
		MessageModel messageModel = new MessageModel();
		UserExtForm user = (UserExtForm)session.getAttribute(Constant.LoginAdminUser);
		boolean ret = userManage.exeResetPassWord(user.getId(),oldPwd, newPwd);
		if(ret){
			messageModel.setResult(true);
			messageModel.setMessage("密码修改正确，请牢记新密码!");
		}else{
			messageModel.setResult(false);
			messageModel.setMessage("原始密码错误!");
		}
		return ResponseEntityUtil.getResponseEntity(messageModel);
	}

	/**
	 * 获取所有用户
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/user/getUsers")
	@ResponseBody
	public DataModel getUsers() {
		try {
			DataModel model = userManage.getUsers();
			return model;
		} catch (Exception e) {
			log.error("获取用户发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 新增用户
	 */
	@RequestMapping("/user/addUser")
	@ResponseBody
	public ResponseEntity<MessageModel> addUser(UserExtForm user) {
		user.setIsEnabled(user.getIsEnabled() == null ? Constant.FALSE
				: Constant.TRUE);
		try {
			userManage.addUser(user);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("新增用户发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}

	/**
	 * 修改用户
	 */
	@RequestMapping("/user/updateUser")
	@ResponseBody
	public ResponseEntity<MessageModel> updateUser(UserExtForm user) {
		user.setIsEnabled(user.getIsEnabled() == null ? Constant.FALSE
				: Constant.TRUE);
		try {
			userManage.updateUser(user);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("修改用户发生异常====" + e.getStackTrace());
			return ResponseEntityUtil.getResponseEntity(Failure);
		}
	}
	
	/**
	 * 修改用户 -> 根据ID获取用户
	 */
	@RequestMapping("/user/getUserById")
	@ResponseBody
	public UserExtForm getUserById(String id) {
		try {
			return userManage.getUserById(id);
		} catch (Exception e) {
			log.error("根据ID获取用户发生异常====" + e.getStackTrace());
			return null;
		}
	}

	/**
	 * 获取所有角色及该用户所具有角色
	 * 
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/user/getRolesOfUser/{userId}")
	@ResponseBody
	public DataModel getRolesOfUser(@PathVariable
	String userId) {
		return this.userManage.getRolesOfUser(userId);
	}
	/**
	 * 检查帐号是否存在
	 * @param id
	 * @param account
	 * @return
	 */
	@RequestMapping("/user/checkUserAccount")
	@ResponseBody
	public boolean  checkUserAccountExisted(String id, String account){
		boolean isExisted = userManage.checkUserAccountExisted(id, account);
		return isExisted;
	}
	
	/**
	 * 检查手机号码是否存在
	 * @param id
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/user/checkUserMobile")
	@ResponseBody
	boolean checkUserMobileExisted(String id, String mobile){
		boolean isExisted = userManage.checkUserMobileExisted(id, mobile);
		return isExisted;
	}
	
	
	/**
	 * 检查email是否存在 
	 * @param id
	 * @param email
	 * @return
	 */
	@RequestMapping("/user/checkUseEmail")
	@ResponseBody
	boolean checkUseEmailExisted(String id, String email){
		boolean isExisted = userManage.checkUseEmailExisted(id, email);
		return isExisted;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/user/deleteUser")
	@ResponseBody
	public MessageModel deleteUser(@RequestParam(value = "id", required = true)
	String id) {
		try {
			userManage.deleteUser(id);
			return Success;
		} catch (Exception e) {
			log.error("删除用户发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * 批量删除用户
	 */
	@RequestMapping("/user/batchDelete")
	@ResponseBody
	public MessageModel batchDelete(
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			for (String id : ids) {
				userManage.deleteUser(id);
			}
			return Success;
		} catch (Exception e) {
			log.error("删除用户发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * 批量启用用户
	 */
	@RequestMapping("/user/batchEnabled")
	@ResponseBody
	public MessageModel batchEnabled(
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			userManage.setUserState(Constant.TRUE, ids);
			return Success;
		} catch (Exception e) {
			log.error("启用用户发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * 批量禁用用户
	 */
	@RequestMapping("/user/batchUnabled")
	@ResponseBody
	public MessageModel batchUnabled(
			@RequestParam(value = "ids[]", required = true)
			String[] ids) {
		try {
			userManage.setUserState(Constant.FALSE, ids);
			return Success;
		} catch (Exception e) {
			log.error("停用户发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * 用户指定角色
	 */
	@RequestMapping("/user/assignRoles")
	@ResponseBody
	public MessageModel assignRoles(
			@RequestParam(value = "ids[]", required = true)
			String[] ids, @RequestParam(value = "id", required = false)
			String userId) {
		try {
			userManage.assignRoles(userId, ids);
			return Success;
		} catch (Exception e) {
			log.error("停用户发生异常====" + e.getStackTrace());
			return Failure;
		}
	}

	/**
	 * 获取用户资源
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/user/getResourcesOfUser")
	@ResponseBody
	public TreeModel getResourcesOfUser(HttpSession session) {
		try {
			UserExtForm userExtForm = (UserExtForm) session
					.getAttribute(Constant.LoginAdminUser);
			return userExtForm.getResourceTree();
		} catch (Exception e) {
			log.error("获取用户资源====" + e.getStackTrace());
			return null;
		}
	}

}
