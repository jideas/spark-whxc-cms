package com.spark.cms.action.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.vo.MemberAddressVo;
import com.spark.cms.services.vo.MemberVo;

@Controller
public class MemberAddressAction extends BaseAction {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/addAddress")
	@ResponseBody
	public ResponseEntity<MessageModel> createAddress (MemberAddressVo addressVo, HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			addressVo.setMemberid(login.getRecid());
			memberService.createAdress(addressVo);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (ServiceMessage e) {
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "±£¥Ê ß∞‹"));
		}
	}
	
	@RequestMapping("/member/modifyAddress")
	@ResponseBody
	public ResponseEntity<MessageModel> modifyAddress (MemberAddressVo addressVo, HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			addressVo.setMemberid(login.getRecid());
			memberService.modifyAdress(addressVo);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "±£¥Ê ß∞‹"));
		} 
	}
	
	@RequestMapping("/member/addressDefault")
	@ResponseBody
	public ResponseEntity<MessageModel> addressDefault (String id) {
		try {
			memberService.exeDefaultAdress(id);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "…Ë÷√ƒ¨»œ ß∞‹"));
		}
	}
	
	@RequestMapping("/member/deleteAddress")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteAddress (String id, HttpServletRequest request) {
		try {
			//Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginUser));
			memberService.deleteAdress(id);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntityUtil.getResponseEntity(new MessageModel(false, "…æ≥˝ ß∞‹"));
		}
	}
	
	@RequestMapping(value = "/member/getAddressList")
	@ResponseBody
	public DataModel<MemberAddressVo> getChildrenCategoryList(String categoryId, HttpServletRequest request) {
		try {
			Login login = new Login((MemberVo) request.getSession().getAttribute(Constant.LoginMemberUser));
			List<MemberAddressVo> childrenList = memberService.getAdressList(login.getRecid());
			return new DataModel<MemberAddressVo>(childrenList, childrenList.size());
		} catch (Exception e) {
			e.printStackTrace();
			return new DataModel<MemberAddressVo>(new ArrayList<MemberAddressVo>(), 0);
		} 
	}

}
