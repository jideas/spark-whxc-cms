package com.spark.cms.action.member;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.utils.ExcelReadHelper;
import com.spark.cms.base.utils.encrypt.MD5;
import com.spark.cms.common.Constant;
import com.spark.cms.common.DataModel;
import com.spark.cms.common.MemberModel;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.common.Constant.OrderEnum.PayType;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.form.UserExtForm;
import com.spark.cms.services.form.member.MemberForm;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.member.key.GetDealingListKey;
import com.spark.cms.services.member.key.GetMemberListKey;
import com.spark.cms.services.member.key.GetVantagesListKey;
import com.spark.cms.services.order.OrderService;
import com.spark.cms.services.order.key.GetEffectedOrderListKey;
import com.spark.cms.services.order.key.GetUnEffectedOrderListKey;
import com.spark.cms.services.vo.MemberAccountVo;
import com.spark.cms.services.vo.MemberActiveVo;
import com.spark.cms.services.vo.MemberChargeFlowVo;
import com.spark.cms.services.vo.MemberDealingVo;
import com.spark.cms.services.vo.MemberVantagesVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.front.form.order.OrderInfo;

/**
 * 会员管理管理
 */
@Controller
public class MemberAction extends BaseAction {

	@Autowired
	private MemberService memberService;

	@Autowired
	private OrderService orderService;
	/**
	 * 上传excel
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/member/importMember")
	@ResponseBody
	public String importMember(HttpServletRequest request, HttpServletResponse response) throws ServiceMessage {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFileMap = multipartHttpServletRequest.getFileMap();
		MultipartFile excel = multipartFileMap.get("excel");

		ExcelReadHelper reader = new ExcelReadHelper();
		try {
			reader.read(excel.getInputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (reader.getRowCount() == 0) {
			return 0 + "";
		} 
		try
		{
			List<String[]> data = reader.getData();
			for(String[] str:data)
			{
				int index = 0;
				MemberForm v = new MemberForm();
				v.setUsername(str[index++]);
				v.setPassword(str[index++]);
				v.setMembername(str[index++]);
				String mobile = str[index++];
				if(CheckIsNull.isNotEmpty(mobile))
				v.setMobile(DoubleUtil.getRoundStrWithOutQfw(DoubleUtil.strToDouble(mobile), 0));
				v.setEmail(str[index++]);
				v.setTelephone(str[index++]);
				String birthDay = str[index++];
				if(CheckIsNull.isNotEmpty(birthDay))
				{
					Calendar cal = Calendar.getInstance();
					cal.set(Integer.valueOf(birthDay.split("-")[2]), getMonth(birthDay), Integer.valueOf(birthDay.split("-")[0]));
					v.setBirthday(cal.getTimeInMillis());
				}
				
				String sex = str[index++];
				if("1".equals(sex))
				{
					v.setSex("01");
				}
				else if("0".equals(sex))
				{
					v.setSex("02");
				}
				else
				{
					v.setSex("03");
				}
				v.setAddress(str[index++]);
				v.setMoneybalance(Double.valueOf(str[index++]));
				v.setVantages(Double.valueOf(str[index++]));
				String payPass = str[index++];
				if(CheckIsNull.isNotEmpty(payPass))
				{
					v.setPaypassword(new MD5().getMD5ofStr(payPass));
				}
				this.memberService.createMember(v);
			}
			return data.size()+"";
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return 0+"";
		}
		
	}
	private int getMonth(String birthDay) {
		String mon = birthDay.split("-")[1];
		if("一月".equals(mon))
		{
			return 1;
		}
		else if("二月".equals(mon))
		{
			return 2;
		}
		else if("三月".equals(mon))
		{
			return 3;
		}
		else if("四月".equals(mon))
		{
			return 4;
		}
		else if("五月".equals(mon))
		{
			return 5;
		}
		else if("六月".equals(mon))
		{
			return 6;
		}
		else if("七月".equals(mon))
		{
			return 7;
		}
		else if("八月".equals(mon))
		{
			return 8;
		}
		
		else if("九月".equals(mon))
		{
			return 9;
		}
		else if("十月".equals(mon))
		{
			return 10;
		}
		else if("十一月".equals(mon))
		{
			return 11;
		}
		else if("十二月".equals(mon))
		{
			return 12;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * 会员积分清零
	 */
	@RequestMapping("/member/clearVangetes")
	@ResponseBody
	public ResponseEntity<MessageModel> clearVangetes(HttpSession session) {
		try {
			Login login = null;
			try {
				login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			} catch (ServiceMessage e) {
				return new ServiceMessage(false, "请先登录！", 1).getMessageModel();
			}
			memberService.exeClearVangetes(login);
			return new ServiceMessage(true, "积分清零已完成",0).getMessageModel();
		} catch (Exception e) {
			log.error("会员积分清零发生异常====" + e.getStackTrace());
			return new ServiceMessage(false, "积分清零", 2).getMessageModel();
		}
	}

	/**
	 * 获取会员列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/getMembers")
	@ResponseBody
	public MemberModel getMembers(@RequestParam(value = "searchWord", required = false)
	String searchWord, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate) {
		try {
			// 查询会员列表
			GetMemberListKey key = new GetMemberListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberVo> membervoList = memberService.getList(key);
			int count = memberService.getCount(key);
			double sumMoney = memberService.getSumMoney(key);
			for (MemberVo v : membervoList) {
				v.setStatusStr(Constant.MemberEnum.MemberStatus.getStatus(v.getStatus()).getName());
			}
			// 转换
			MemberModel memberModel = new MemberModel();
			if (membervoList != null) {
				memberModel.setRows(membervoList);
				memberModel.setTotal(count);
				memberModel.setSumMoney(sumMoney);
			}
			return memberModel;
		} catch (Exception e) {
			log.error("获取会员列表发生异常====" + e.getMessage());
		}
		return null;
	}

	/**
	 * 新增会员
	 */
	@RequestMapping("/member/addMember")
	@ResponseBody
	public ResponseEntity<MessageModel> addMember(MemberVo member) {
		try {
			memberService.createMember(member);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("新增会员发生异常====" + e.getStackTrace());
			return null;
		}
	}

	/**
	 * 获取会员充值记录列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/getChargelog")
	@ResponseBody
	public DataModel getChargelog(@RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "memberId", required = false)
	String memberId, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate) {
		try {
			GetDealingListKey key = new GetDealingListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setMemberId(memberId);
			if (CheckIsNull.isNotEmpty(beginDate)) {
				key.setBeginDate(new Date(DateUtil.getDayStartTime(DateUtil.convertStringToDate(beginDate).getTime())));
			}
			if (CheckIsNull.isNotEmpty(endDate)) {
				key.setEndDate(new Date(DateUtil.getDayStartTime(DateUtil.convertStringToDate(endDate).getTime())));
			}
			int count = memberService.getDealingCount(key);
			List<MemberDealingVo> list = memberService.getDealingList(key);

			for (MemberDealingVo v : list) {
				v.setDealtypeStr(Constant.MemberEnum.DealingsType.getType(v.getDealtype()).getName());
				v.setOccurdateStr(DateUtil.dateFromat(v.getOccurdate(), DateUtil.DATE_TIME_PATTERN2));
			}
			DataModel dataModel = new DataModel();
			if (list != null) {
				dataModel.setRows(list);
				dataModel.setTotal(count);
			}
			List<MemberAccountVo> al = new ArrayList<MemberAccountVo>();
			MemberAccountVo av = memberService.getMemberAccountVo(memberId);
			al.add(av);
			dataModel.setFooter(al);
			return dataModel;
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			log.error("获取会员充值记录列表异常====" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取会员积分列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/getScoreview")
	@ResponseBody
	public DataModel getScoreview(@RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "memberId", required = false)
	String memberId, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate) {
		try {
			GetVantagesListKey key = new GetVantagesListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setMemberId(memberId);
			if (CheckIsNull.isNotEmpty(beginDate)) {
				key.setBeginDate(new Date(DateUtil.getDayStartTime(DateUtil.convertStringToDate(beginDate).getTime())));
			}
			if (CheckIsNull.isNotEmpty(endDate)) {
				key.setEndDate(new Date(DateUtil.getDayStartTime(DateUtil.convertStringToDate(endDate).getTime())));
			}
			List<MemberVantagesVo> list = memberService.getVantagesList(key);
			int count = memberService.getVantagesCount(key);
			DataModel dataModel = new DataModel();
			for (MemberVantagesVo v : list) {
				v.setOccurdateStr(DateUtil.dateFromat(v.getOccurdate(), DateUtil.DATE_TIME_PATTERN2));
				v.setVtypeStr(Constant.MemberEnum.VantagesType.getType(v.getVtype()).getName());
			}
			if (list != null) {
				dataModel.setRows(list);
				dataModel.setTotal(count);
			}
			List<MemberAccountVo> al = new ArrayList<MemberAccountVo>();
			MemberAccountVo av = memberService.getMemberAccountVo(memberId);
			al.add(av);
			dataModel.setFooter(al);
			return dataModel;
		} catch (Throwable e) {
			log.error("获取会员积分列表异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 获取会员当前订单
	 */
	@RequestMapping("/member/getCurrentorder")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public DataModel getCurrentorder(@RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "memberId", required = false)
	String memberId) {
		try {
			GetEffectedOrderListKey key = new GetEffectedOrderListKey(Integer.valueOf(page), Integer.valueOf(rows),
					false);
			key.setLog(false);
			key.setMemberId(memberId);
			List<OrderInfo> list = orderService.getList(key);
			int count = orderService.getCount(key);
			DataModel dataModel = new DataModel();

			Double totalAmount = 0d;
			for (OrderInfo v : list) {
				v.setStatusStr(Constant.Order.OnlineOrderStatus.getStatus(v.getStatus()).getName());
				v.setPayTypeStr(Constant.OrderEnum.PayType.getPayType(v.getPayType()).getName());
				v.setCreateDateStr(DateUtil.dateFromat(v.getCreatedate(), DateUtil.DATE_TIME_PATTERN2));
				totalAmount += v.getTotalamount();
			}
			if (list != null) {
				dataModel.setRows(list);
				dataModel.setTotal(count);
			}
			List<OrderFooter> l = new ArrayList<OrderFooter>();
			l.add(new OrderFooter(totalAmount));
			dataModel.setFooter(l);
			return dataModel;
		} catch (Throwable e) {
			log.error("获取会员当前订单列表异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 获取会员历史订单
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/getHistoryOrder")
	@ResponseBody
	public DataModel getHistoryOrder(@RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "memberId", required = false)
	String memberId, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate) {
		try {
			GetEffectedOrderListKey key = new GetEffectedOrderListKey(Integer.valueOf(page), Integer.valueOf(rows),
					false);
			key.setLog(true);
			key.setMemberId(memberId);
			if (CheckIsNull.isNotEmpty(beginDate)) {
				key.setBeginDate(new Date(DateUtil.getDayStartTime(DateUtil.convertStringToDate(beginDate).getTime())));
			}
			if (CheckIsNull.isNotEmpty(endDate)) {
				key.setEndDate(new Date(DateUtil.getDayStartTime(DateUtil.convertStringToDate(endDate).getTime())));
			}
			List<OrderInfo> list = orderService.getList(key);
			int count = orderService.getCount(key);
			DataModel dataModel = new DataModel();

			Double totalAmount = 0d;
			for (OrderInfo v : list) {
				v.setStatusStr(Constant.Order.OnlineOrderStatus.getStatus(v.getStatus()).getName());
				v.setPayTypeStr(Constant.OrderEnum.PayType.getPayType(v.getPayType()).getName());
				v.setCreateDateStr(DateUtil.dateFromat(v.getCreatedate(), "yyyy-MM-dd HH:mm:ss"));
				totalAmount += v.getTotalamount();
			}
			if (list != null) {
				dataModel.setRows(list);
				dataModel.setTotal(count);
			}
			List<OrderFooter> l = new ArrayList<OrderFooter>();
			l.add(new OrderFooter(totalAmount));
			dataModel.setFooter(l);
			return dataModel;
		} catch (Throwable e) {
			log.error("获取会员历史订单列表异常====" + e.getStackTrace());
		}
		return null;
	}

	public class OrderFooter {
		private double totalAmount;

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public OrderFooter(double totalAmount) {
			this.totalAmount = totalAmount;
		}

	}
	
	/**
	 * 获取会员未付款订单
	 */
	@RequestMapping("/member/getuneffectedorder")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public DataModel getUneffectedorder(@RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "memberId", required = false)
	String memberId) {
		try {
			GetUnEffectedOrderListKey key = new GetUnEffectedOrderListKey(Integer.valueOf(page), Integer.valueOf(rows),
					false);
			key.setMemberId(memberId);
			List<OrderInfo> list = orderService.getList(key);
			int count = orderService.getCount(key);
			DataModel dataModel = new DataModel();

			Double totalAmount = 0d;
			for (OrderInfo v : list) {
				v.setStatusStr(Constant.Order.OnlineOrderStatus.getStatus(v.getStatus()).getName());
				v.setPayTypeStr(Constant.OrderEnum.PayType.getPayType(v.getPayType()).getName());
				v.setCreateDateStr(DateUtil.dateFromat(v.getCreatedate(), DateUtil.DATE_TIME_PATTERN2));
				totalAmount += v.getTotalamount();
			}
			if (list != null) {
				dataModel.setRows(list);
				dataModel.setTotal(count);
			}
			List<OrderFooter> l = new ArrayList<OrderFooter>();
			l.add(new OrderFooter(totalAmount));
			dataModel.setFooter(l);
			return dataModel;
		} catch (Throwable e) {
			log.error("获取会员未付款订单列表异常====" + e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * 确认收款
	 */
	@RequestMapping("/member/effectOrder")
	@ResponseBody
	public ResponseEntity<MessageModel> effectOrder(@RequestParam(value = "orderId", required = true)
			String orderId) {
		try {
			this.orderService.exeEffectiveOrder(orderId, PayType.Online);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (ServiceMessage e) {
			log.error("确认收款发生异常====" + e.getStackTrace());
			return null;
		}
	}

	/**
	 * 发送信息
	 */
	@RequestMapping("/member/sendMessage")
	@ResponseBody
	public MessageModel sendMessage(@RequestParam(value = "ids[]", required = false)
	String[] ids) {

		return null;
	}
	
	/**
	 * 活动会员 -> 获取活动会员列表
	 * @param searchWord
	 * @param page
	 * @param rows
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/getActiveMembers")
	@ResponseBody
	public MemberModel getActiveMembers(@RequestParam(value = "searchWord", required = false)
	String searchWord, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate) {
		MemberModel memberModel = new MemberModel();
		try {
			// 查询会员列表
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberActiveVo>  mavList = memberService.getActiveMemberList(key);
			List<MemberActiveVo> footerList = memberService.getActiveMemberTotal(key);
			int count = memberService.getActiveMemberCount(key);
			// 转换
			if (mavList != null) {
				memberModel.setRows(mavList);
				memberModel.setTotal(count);
				memberModel.setFooter(footerList);
			}
			return memberModel;
		} catch (Exception e) {
			log.error("获取活动会员列表发生异常====" + e.getMessage());
		}
		return memberModel;
	}
	
	/**
	 * 活动会员 -> 导出会员
	 * @param searchWord
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/exportActiveMember")
	@ResponseBody
	public void exportActiveMember(@RequestParam(value = "searchWord", required = false)
	String searchWord,@RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate,HttpServletResponse response) {
		try {
			// 查询会员列表
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(0, 0, false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberActiveVo>  mavList = memberService.getActiveMemberList(key);
			//导出设置
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=member.xls");
			response.setContentType("application/msexcel");
			//excel设置
			WritableWorkbook wbook = jxl.Workbook.createWorkbook(os); // 建立excel文件    
		    WritableSheet wsheet = wbook.createSheet("活动会员", 0); // sheet名称
			//表头设置
		    WritableFont titleStyle = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleStyle);
		    titleFormat.setBackground(Colour.GREY_25_PERCENT);
		    titleFormat.setAlignment(Alignment.CENTRE);
			wsheet.addCell(new Label(0, 0, "会员编号",titleFormat));
			wsheet.addCell(new Label(1, 0, "会员账号",titleFormat));
			wsheet.addCell(new Label(2, 0, "会员名",titleFormat));
			wsheet.addCell(new Label(3, 0, "手机号",titleFormat));
			wsheet.addCell(new Label(4, 0, "站点",titleFormat));
			wsheet.addCell(new Label(5, 0, "订单数量",titleFormat));
			wsheet.addCell(new Label(6, 0, "订单总额",titleFormat));
			wsheet.addCell(new Label(7, 0, "退货单数量",titleFormat));
			wsheet.addCell(new Label(8, 0, "退货金额",titleFormat));
			//excel添加数据
			WritableFont contentStyle = new WritableFont(WritableFont.createFont("宋体"),12);
		    WritableCellFormat contentFormat = new WritableCellFormat(contentStyle);
		    wsheet.setColumnView(0, 20);
		    wsheet.setColumnView(1, 20);
		    wsheet.setColumnView(2, 18);
		    wsheet.setColumnView(3, 20);
		    wsheet.setColumnView(4, 20);
		    wsheet.setColumnView(5, 10);
		    wsheet.setColumnView(6, 15);
		    wsheet.setColumnView(7, 10);
		    wsheet.setColumnView(8, 15);
			for(int i = 0;mavList != null && i < mavList.size();i++){
				MemberActiveVo mav = mavList.get(i);
			    wsheet.addCell(new Label(0, i+1, mav.getCode(),contentFormat));
			    wsheet.addCell(new Label(1, i+1, mav.getUsername(),contentFormat));
			    wsheet.addCell(new Label(2, i+1, mav.getMembername(),contentFormat));
			    wsheet.addCell(new Label(3, i+1, mav.getMobile(),contentFormat));
			    wsheet.addCell(new Label(4, i+1, mav.getStationname(),contentFormat));
			    wsheet.addCell(new Number(5,i+1,mav.getOrdercount(),contentFormat));
			    wsheet.addCell(new Number(6, i+1, mav.getOrdermoney(),contentFormat));
			    wsheet.addCell(new Number(7, i+1, mav.getReturncount(),contentFormat));
			    wsheet.addCell(new Number(8, i+1, mav.getReturnmoney(),contentFormat));
			}           
	        //资源回收 
			wbook.write();
			wbook.close();
			os.close();
		} catch (Exception e) {
			log.error("导出活动会员列表发生异常====" + e.getMessage());
		}
	}

	/**
	 * 会员充值流水
	 * @param searchWord
	 * @param page
	 * @param rows
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/getMemberChargeFlows")
	@ResponseBody
	public MemberModel getMemberChargeFlows(@RequestParam(value = "searchWord", required = false)
	String searchWord, @RequestParam(value = "page", required = false)
	String page, @RequestParam(value = "rows", required = false)
	String rows, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate, @RequestParam(value = "valueType", required = false)
	String valueType, @RequestParam(value = "chargeType", required = false)
	String chargeType) {
		MemberModel memberModel = new MemberModel();
		try {
			// 查询充值记录
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberChargeFlowVo>  mavList = memberService.getChargeFlows(key,valueType,chargeType);
			List<MemberChargeFlowVo> footerList = memberService.getChargeFlowTotal(key,valueType,chargeType);
			int count = memberService.getChargeFlowCount(key,valueType,chargeType);
			// 转换
			if (mavList != null) {
				memberModel.setRows(mavList);
				memberModel.setTotal(count);
				memberModel.setFooter(footerList);
			}
			return memberModel;
		} catch (Exception e) {
			log.error("获取会员充值流水列表发生异常====" + e.getMessage());
		}
		return memberModel;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/exportChargeFlow")
	@ResponseBody
	public void exportChargeFlow(@RequestParam(value = "searchWord", required = false)
	String searchWord, @RequestParam(value = "beginDate", required = false)
	String beginDate, @RequestParam(value = "endDate", required = false)
	String endDate, @RequestParam(value = "valueType", required = false)
	String valueType, @RequestParam(value = "chargeType", required = false)
	String chargeType,HttpServletResponse response) {
		try {
			// 查询充值记录
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(0, 0, false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberChargeFlowVo> mcfvList = memberService.getChargeFlows(key,valueType,chargeType);
			//导出设置
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=charge flow.xls");
			response.setContentType("application/msexcel");
			//excel设置
			WritableWorkbook wbook = jxl.Workbook.createWorkbook(os); // 建立excel文件    
		    WritableSheet wsheet = wbook.createSheet("会员充值流水", 0); // sheet名称
			//表头设置
		    WritableFont titleStyle = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleStyle);
		    titleFormat.setBackground(Colour.GREY_25_PERCENT);
		    titleFormat.setAlignment(Alignment.CENTRE);
			wsheet.addCell(new Label(0, 0, "编号",titleFormat));
			wsheet.addCell(new Label(1, 0, "用户名",titleFormat));
			wsheet.addCell(new Label(2, 0, "姓名",titleFormat));
			wsheet.addCell(new Label(3, 0, "手机号码",titleFormat));
			wsheet.addCell(new Label(4, 0, "充值类型",titleFormat));
			wsheet.addCell(new Label(5, 0, "卡号/单号",titleFormat));
			wsheet.addCell(new Label(6, 0, "金额",titleFormat));
			wsheet.addCell(new Label(7, 0, "充值时间",titleFormat));
			//excel添加数据
			WritableFont contentStyle = new WritableFont(WritableFont.createFont("宋体"),12);
		    WritableCellFormat contentFormat = new WritableCellFormat(contentStyle);
		    wsheet.setColumnView(0, 20);
		    wsheet.setColumnView(1, 20);
		    wsheet.setColumnView(2, 18);
		    wsheet.setColumnView(3, 20);
		    wsheet.setColumnView(4, 20);
		    wsheet.setColumnView(5, 25);
		    wsheet.setColumnView(6, 10);
		    wsheet.setColumnView(7, 30);
			for(int i = 0;mcfvList != null && i < mcfvList.size();i++){
				MemberChargeFlowVo mcfv = mcfvList.get(i);
				String chargeTypeTemp = "01".equals(mcfv.getChargetype()) ? "面值卡" : ("02".equals(mcfv.getChargetype()) ? "网银" : "");
			    wsheet.addCell(new Label(0, i+1, mcfv.getCode(),contentFormat));
			    wsheet.addCell(new Label(1, i+1, mcfv.getUsername(),contentFormat));
			    wsheet.addCell(new Label(2, i+1, mcfv.getMembername(),contentFormat));
			    wsheet.addCell(new Label(3, i+1, mcfv.getMobile(),contentFormat));
			    wsheet.addCell(new Label(4, i+1, chargeTypeTemp,contentFormat));
			    wsheet.addCell(new Label(5,i+1,mcfv.getOrderno(),contentFormat));
			    wsheet.addCell(new Number(6, i+1, Double.parseDouble(mcfv.getAmount()),contentFormat));
			    wsheet.addCell(new Label(7, i+1, mcfv.getOccurdate(),contentFormat));
			}           
	        //资源回收
			wbook.write();
			wbook.close();
			os.close();
		} catch (Exception e) {
			log.error("导出会员充值流水列表发生异常====" + e.getMessage());
		}
	}
	
}
