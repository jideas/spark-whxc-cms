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
 * ��Ա�������
 */
@Controller
public class MemberAction extends BaseAction {

	@Autowired
	private MemberService memberService;

	@Autowired
	private OrderService orderService;
	/**
	 * �ϴ�excel
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
		if("һ��".equals(mon))
		{
			return 1;
		}
		else if("����".equals(mon))
		{
			return 2;
		}
		else if("����".equals(mon))
		{
			return 3;
		}
		else if("����".equals(mon))
		{
			return 4;
		}
		else if("����".equals(mon))
		{
			return 5;
		}
		else if("����".equals(mon))
		{
			return 6;
		}
		else if("����".equals(mon))
		{
			return 7;
		}
		else if("����".equals(mon))
		{
			return 8;
		}
		
		else if("����".equals(mon))
		{
			return 9;
		}
		else if("ʮ��".equals(mon))
		{
			return 10;
		}
		else if("ʮһ��".equals(mon))
		{
			return 11;
		}
		else if("ʮ����".equals(mon))
		{
			return 12;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * ��Ա��������
	 */
	@RequestMapping("/member/clearVangetes")
	@ResponseBody
	public ResponseEntity<MessageModel> clearVangetes(HttpSession session) {
		try {
			Login login = null;
			try {
				login = new Login((UserExtForm) session.getAttribute(Constant.LoginAdminUser));
			} catch (ServiceMessage e) {
				return new ServiceMessage(false, "���ȵ�¼��", 1).getMessageModel();
			}
			memberService.exeClearVangetes(login);
			return new ServiceMessage(true, "�������������",0).getMessageModel();
		} catch (Exception e) {
			log.error("��Ա�������㷢���쳣====" + e.getStackTrace());
			return new ServiceMessage(false, "��������", 2).getMessageModel();
		}
	}

	/**
	 * ��ȡ��Ա�б�
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
			// ��ѯ��Ա�б�
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
			// ת��
			MemberModel memberModel = new MemberModel();
			if (membervoList != null) {
				memberModel.setRows(membervoList);
				memberModel.setTotal(count);
				memberModel.setSumMoney(sumMoney);
			}
			return memberModel;
		} catch (Exception e) {
			log.error("��ȡ��Ա�б����쳣====" + e.getMessage());
		}
		return null;
	}

	/**
	 * ������Ա
	 */
	@RequestMapping("/member/addMember")
	@ResponseBody
	public ResponseEntity<MessageModel> addMember(MemberVo member) {
		try {
			memberService.createMember(member);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (Exception e) {
			log.error("������Ա�����쳣====" + e.getStackTrace());
			return null;
		}
	}

	/**
	 * ��ȡ��Ա��ֵ��¼�б�
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
			log.error("��ȡ��Ա��ֵ��¼�б��쳣====" + e.getMessage());
			return null;
		}
	}

	/**
	 * ��ȡ��Ա�����б�
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
			log.error("��ȡ��Ա�����б��쳣====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * ��ȡ��Ա��ǰ����
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
			log.error("��ȡ��Ա��ǰ�����б��쳣====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * ��ȡ��Ա��ʷ����
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
			log.error("��ȡ��Ա��ʷ�����б��쳣====" + e.getStackTrace());
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
	 * ��ȡ��Աδ�����
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
			log.error("��ȡ��Աδ������б��쳣====" + e.getStackTrace());
		}
		return null;
	}
	
	/**
	 * ȷ���տ�
	 */
	@RequestMapping("/member/effectOrder")
	@ResponseBody
	public ResponseEntity<MessageModel> effectOrder(@RequestParam(value = "orderId", required = true)
			String orderId) {
		try {
			this.orderService.exeEffectiveOrder(orderId, PayType.Online);
			return ResponseEntityUtil.getResponseEntity(Success);
		} catch (ServiceMessage e) {
			log.error("ȷ���տ���쳣====" + e.getStackTrace());
			return null;
		}
	}

	/**
	 * ������Ϣ
	 */
	@RequestMapping("/member/sendMessage")
	@ResponseBody
	public MessageModel sendMessage(@RequestParam(value = "ids[]", required = false)
	String[] ids) {

		return null;
	}
	
	/**
	 * ���Ա -> ��ȡ���Ա�б�
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
			// ��ѯ��Ա�б�
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberActiveVo>  mavList = memberService.getActiveMemberList(key);
			List<MemberActiveVo> footerList = memberService.getActiveMemberTotal(key);
			int count = memberService.getActiveMemberCount(key);
			// ת��
			if (mavList != null) {
				memberModel.setRows(mavList);
				memberModel.setTotal(count);
				memberModel.setFooter(footerList);
			}
			return memberModel;
		} catch (Exception e) {
			log.error("��ȡ���Ա�б����쳣====" + e.getMessage());
		}
		return memberModel;
	}
	
	/**
	 * ���Ա -> ������Ա
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
			// ��ѯ��Ա�б�
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(0, 0, false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberActiveVo>  mavList = memberService.getActiveMemberList(key);
			//��������
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=member.xls");
			response.setContentType("application/msexcel");
			//excel����
			WritableWorkbook wbook = jxl.Workbook.createWorkbook(os); // ����excel�ļ�    
		    WritableSheet wsheet = wbook.createSheet("���Ա", 0); // sheet����
			//��ͷ����
		    WritableFont titleStyle = new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleStyle);
		    titleFormat.setBackground(Colour.GREY_25_PERCENT);
		    titleFormat.setAlignment(Alignment.CENTRE);
			wsheet.addCell(new Label(0, 0, "��Ա���",titleFormat));
			wsheet.addCell(new Label(1, 0, "��Ա�˺�",titleFormat));
			wsheet.addCell(new Label(2, 0, "��Ա��",titleFormat));
			wsheet.addCell(new Label(3, 0, "�ֻ���",titleFormat));
			wsheet.addCell(new Label(4, 0, "վ��",titleFormat));
			wsheet.addCell(new Label(5, 0, "��������",titleFormat));
			wsheet.addCell(new Label(6, 0, "�����ܶ�",titleFormat));
			wsheet.addCell(new Label(7, 0, "�˻�������",titleFormat));
			wsheet.addCell(new Label(8, 0, "�˻����",titleFormat));
			//excel�������
			WritableFont contentStyle = new WritableFont(WritableFont.createFont("����"),12);
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
	        //��Դ���� 
			wbook.write();
			wbook.close();
			os.close();
		} catch (Exception e) {
			log.error("�������Ա�б����쳣====" + e.getMessage());
		}
	}

	/**
	 * ��Ա��ֵ��ˮ
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
			// ��ѯ��ֵ��¼
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(Integer.valueOf(page), Integer.valueOf(rows), false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberChargeFlowVo>  mavList = memberService.getChargeFlows(key,valueType,chargeType);
			List<MemberChargeFlowVo> footerList = memberService.getChargeFlowTotal(key,valueType,chargeType);
			int count = memberService.getChargeFlowCount(key,valueType,chargeType);
			// ת��
			if (mavList != null) {
				memberModel.setRows(mavList);
				memberModel.setTotal(count);
				memberModel.setFooter(footerList);
			}
			return memberModel;
		} catch (Exception e) {
			log.error("��ȡ��Ա��ֵ��ˮ�б����쳣====" + e.getMessage());
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
			// ��ѯ��ֵ��¼
			beginDate = CheckIsNull.isNotEmpty(beginDate) ? beginDate : "1900-01-01";
			GetMemberListKey key = new GetMemberListKey(0, 0, false);
			key.setSearchText(searchWord);
			key.setBeginDate(beginDate);
			key.setEndDate(endDate);
			List<MemberChargeFlowVo> mcfvList = memberService.getChargeFlows(key,valueType,chargeType);
			//��������
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=charge flow.xls");
			response.setContentType("application/msexcel");
			//excel����
			WritableWorkbook wbook = jxl.Workbook.createWorkbook(os); // ����excel�ļ�    
		    WritableSheet wsheet = wbook.createSheet("��Ա��ֵ��ˮ", 0); // sheet����
			//��ͷ����
		    WritableFont titleStyle = new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD);
		    WritableCellFormat titleFormat = new WritableCellFormat(titleStyle);
		    titleFormat.setBackground(Colour.GREY_25_PERCENT);
		    titleFormat.setAlignment(Alignment.CENTRE);
			wsheet.addCell(new Label(0, 0, "���",titleFormat));
			wsheet.addCell(new Label(1, 0, "�û���",titleFormat));
			wsheet.addCell(new Label(2, 0, "����",titleFormat));
			wsheet.addCell(new Label(3, 0, "�ֻ�����",titleFormat));
			wsheet.addCell(new Label(4, 0, "��ֵ����",titleFormat));
			wsheet.addCell(new Label(5, 0, "����/����",titleFormat));
			wsheet.addCell(new Label(6, 0, "���",titleFormat));
			wsheet.addCell(new Label(7, 0, "��ֵʱ��",titleFormat));
			//excel�������
			WritableFont contentStyle = new WritableFont(WritableFont.createFont("����"),12);
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
				String chargeTypeTemp = "01".equals(mcfv.getChargetype()) ? "��ֵ��" : ("02".equals(mcfv.getChargetype()) ? "����" : "");
			    wsheet.addCell(new Label(0, i+1, mcfv.getCode(),contentFormat));
			    wsheet.addCell(new Label(1, i+1, mcfv.getUsername(),contentFormat));
			    wsheet.addCell(new Label(2, i+1, mcfv.getMembername(),contentFormat));
			    wsheet.addCell(new Label(3, i+1, mcfv.getMobile(),contentFormat));
			    wsheet.addCell(new Label(4, i+1, chargeTypeTemp,contentFormat));
			    wsheet.addCell(new Label(5,i+1,mcfv.getOrderno(),contentFormat));
			    wsheet.addCell(new Number(6, i+1, Double.parseDouble(mcfv.getAmount()),contentFormat));
			    wsheet.addCell(new Label(7, i+1, mcfv.getOccurdate(),contentFormat));
			}           
	        //��Դ����
			wbook.write();
			wbook.close();
			os.close();
		} catch (Exception e) {
			log.error("������Ա��ֵ��ˮ�б����쳣====" + e.getMessage());
		}
	}
	
}
