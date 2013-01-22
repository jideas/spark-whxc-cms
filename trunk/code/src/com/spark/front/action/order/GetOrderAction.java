package com.spark.front.action.order;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.common.utils.DateUtil;
import com.spark.base.common.utils.DoubleUtil;
import com.spark.cms.action.BaseAction;
import com.spark.cms.common.Constant;
import com.spark.cms.common.Constant.Order.OnlineOrderStatus;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.form.Login;
import com.spark.cms.services.freeDelivery.DeliveryPriceService;
import com.spark.cms.services.goods.GoodsService;
import com.spark.cms.services.goodsPromotion.GoodsPromotionService;
import com.spark.cms.services.member.MemberService;
import com.spark.cms.services.order.OrderService;
import com.spark.cms.services.order.key.GetEffectedOrderListKey;
import com.spark.cms.services.order.key.GetUnEffectedOrderListKey;
import com.spark.cms.services.orderPromotion.OrderPromotionService;
import com.spark.cms.services.vo.GoodsVo;
import com.spark.cms.services.vo.MemberVo;
import com.spark.cms.services.vo.OrderDetVo;
import com.spark.front.form.order.OrderInfo;
import com.spark.front.form.order.OrderListData;

/**
 * 订单查询
 * 
 */
@Controller
public class GetOrderAction extends BaseAction {
	@Autowired
	private MemberService memberService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsPromotionService goodsPromotionService;

	@Autowired
	private OrderPromotionService orderPromotionService;

	@Autowired
	private DeliveryPriceService deliveryPriceService;

	/**
	 * 获取订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/order/getOrdersHtml")
	@ResponseBody
	public OrderListData getOrdersHtml(
			@RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows, @RequestParam(value = "month", required = false)
			String month,@RequestParam(value = "selecttor", required = false)
			String selecttor, @RequestParam(value = "searchText", required = false)
			String searchText,
			@RequestParam(value = "queryTotal", required = false)
			String queryTotal, HttpServletRequest request, HttpSession session) {
		OrderListData data = new OrderListData();
		StringBuffer html = new StringBuffer();
		Login login = getLogin(request);
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			data.setSuccess(false);
			data.setNoLogin(true);
			return data;
		}
		if (CheckIsNull.isEmpty(page)) {
			page = "1";
		}
		if (CheckIsNull.isEmpty(rows)) {
			rows = "20";
		}
		boolean thisMonth = true;
		if (CheckIsNull.isNotEmpty(month) && "1".equals(month)) {
			thisMonth = false;
		}
		int p = Integer.valueOf(page);
		int r = Integer.valueOf(rows);
		searchText = "undefined".equals(searchText)
				|| "null".equals(searchText) || CheckIsNull.isEmpty(searchText) ? ""
				: searchText;
		int totalCount = 0;
		if(CheckIsNull.isEmpty(selecttor))
		{
			selecttor = "0";
		}
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		if(CheckIsNull.isNotEmpty(selecttor)&&"2".equals(selecttor))
		{
			GetUnEffectedOrderListKey ukey = new GetUnEffectedOrderListKey(p,
					r, false);
			ukey.setMemberId(login.getRecid());
			ukey.setThisMonth(thisMonth);
			ukey.setSearchText(searchText);
			try {
				orderList = this.orderService.getList(ukey);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				totalCount = this.orderService.getCount(ukey);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (CheckIsNull.isNotEmpty(selecttor)&&"0".equals(selecttor)) {

			GetEffectedOrderListKey key = new GetEffectedOrderListKey(p, r,
					false);
			key.setMemberId(login.getRecid());
			key.setThisMonth(thisMonth);
			key.setLog(false);
			key.setSearchText(searchText);
			try {
				orderList = this.orderService.getList(key);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				totalCount = this.orderService.getCount(key);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			GetEffectedOrderListKey key = new GetEffectedOrderListKey(p, r,
					false);
			key.setMemberId(login.getRecid());
			key.setThisMonth(thisMonth);
			key.setLog(true);
			try {
				orderList = this.orderService.getList(key);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				totalCount = this.orderService.getCount(key);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String webURLContext = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		html.append("<tr class=\"tableHead\">");
		html.append("<th width=\"12%\">");
		html.append("订单编号");
		html.append("</th>");
		html.append("<th width=\"27%\">");
		html.append("订单商品");
		html.append("</th>");
		html.append("<th width=\"10%\">");
		html.append("收货人");
		html.append("</th>");
		html.append("<th width=\"12%\">");
		html.append("订单金额");
		html.append("</th>");
		html.append("<th width=\"12%\">");
		html.append("下单时间");
		html.append("</th>");
		html.append("<th width=\"12%\">");
		html.append("订单状态");
		html.append("</th>");
		html.append("<th width=\"12%\">");
		html.append("操作");
		html.append("</th>");
		html.append("</tr>");
		for (OrderInfo order : orderList) {
			html.append("<tr id=" + order.getRecid() + ">");
			html.append("<td>");
			html.append("<a name='orderIdLinks' id='idUrl" + order.getRecid()
					+ "'");
			html.append("href='" + webURLContext
					+ "/front/order/getOrderInfo?orderId=" + order.getRecid()
					+ "'  target=\"_blank\"");
			html.append("target=\"_blank\" ");
			html.append("class=\"orderLink\">"
					+ order.getBillsno().split("WSDD")[1] + "</a>");
			html.append("</td>");
			html.append("<td align=\"left\">");
			html.append("<div id=\"img" + order.getRecid()
					+ "\" class=\"img-list\">");

			for (OrderDetVo det : order.getDets()) {
				
				html.append("<a href=\""+webURLContext+"/front/toGoodsInfoPage?id="+det.getGoodsid()+"\" ");
				html.append("target=\"_blank\"> <img style=\"border:#DEDEDE 0.5px solid;\" title="
						+ det.getGoodsname() + " ");
				GoodsVo g = this.goodsService.getGoodsVo(det.getGoodsid());
				html.append("src=\""+(null!=g?webURLContext+g.getPicturepath1():"")+"\" "
						+ " width=\"50\"");
				html.append("height=\"50\" /> </a>");

			}

			html.append("</div>");
			html.append("</td>");
			html.append("<td>");
			html.append(order.getConsignee());
			html.append("</td>");
			html.append("<td>");
			html.append(DoubleUtil.getRoundStr(order.getTotalamount(), 2));
			html.append("</td>");
			html.append("<td>");
			html.append("<span class=\"ftx-03\">"
					+ DateUtil.dateFromat(order.getCreatedate()));
			html.append("<br />" + DateUtil.getHHMMSS(order.getCreatedate())
					+ "</span>");
			html.append("</td>");

			html.append("<td>");
			html.append("<span class=\"ftx-03\">"
					+ Constant.Order.OnlineOrderStatus.getStatus(
							order.getStatus()).getName());
			html.append("</span>");
			html.append("</td>");
			html.append("<td>");
			html.append("<a href=\"" + webURLContext
					+ "/front/order/getOrderInfo?orderId=" + order.getRecid()
					+ "\" target=\"_blank\">");

			if (OnlineOrderStatus.Paying.getCode().equals(order.getStatus())) {
				html.append("支付");
			} else {
				html.append("查看");
			}

			html.append("</a>");
			html.append("</td>");
			html.append("</tr>");
		}
		data.setHtml(html.toString());
		data.setTotalCount(totalCount);
		data.setSuccess(true);
		return data;
	}
	
	/**
	 * 获取订单列表
	 * 
	 * @return
	 */
	@RequestMapping("/order/getOrders")
	public ModelAndView getOrders(
			@RequestParam(value = "page", required = false)
			String page, @RequestParam(value = "rows", required = false)
			String rows, @RequestParam(value = "month", required = false)
			String month, @RequestParam(value = "selecttor", required = false)
			String selecttor, @RequestParam(value = "searchText", required = false)
			String searchText, HttpServletRequest request, HttpSession session) {
		ModelAndView maw = new ModelAndView();
		Login login = getLogin(request);
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			maw.addObject("url", request.getContextPath()+"/front/order/getOrders");
			maw.setViewName("/pub/sub/login");
			return maw;
		}
		if (CheckIsNull.isEmpty(page)) {
			page = "1";
		}
		if (CheckIsNull.isEmpty(rows)) {
			rows = "20";
		}
		boolean thisMonth = true;
		if (CheckIsNull.isNotEmpty(month) && "0".equals(month)) {
			thisMonth = false;
		}
		int p = Integer.valueOf(page);
		int r = Integer.valueOf(rows);
		int totalCount = 0;
		if(CheckIsNull.isEmpty(selecttor))
		{
			selecttor = "0";
		}
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		if(CheckIsNull.isNotEmpty(selecttor)&&"2".equals(selecttor))
		{
			GetUnEffectedOrderListKey ukey = new GetUnEffectedOrderListKey(p,
					r, false);
			ukey.setMemberId(login.getRecid());
			ukey.setThisMonth(thisMonth);
			ukey.setSearchText(searchText);
			try {
				orderList = this.orderService.getList(ukey);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				totalCount = this.orderService.getCount(ukey);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (CheckIsNull.isNotEmpty(selecttor)&&"0".equals(selecttor)) {

			GetEffectedOrderListKey key = new GetEffectedOrderListKey(p, r,
					false);
			key.setMemberId(login.getRecid());
			key.setThisMonth(thisMonth);
			key.setLog(false);
			key.setSearchText(searchText);
			try {
				orderList = this.orderService.getList(key);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				totalCount = this.orderService.getCount(key);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			GetEffectedOrderListKey key = new GetEffectedOrderListKey(p, r,
					false);
			key.setMemberId(login.getRecid());
			key.setThisMonth(thisMonth);
			key.setLog(true);
			try {
				orderList = this.orderService.getList(key);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				totalCount = this.orderService.getCount(key);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (OrderInfo order : orderList) {
			for (OrderDetVo v : order.getDets()) {
				GoodsVo g = this.goodsService.getGoodsVo(v.getGoodsid());
				if (null != g) {
					v.setImgUrl(g.getPicturepath1());
				}
			}
		}

		maw.addObject("orderList", orderList);
		maw.addObject("totalCount", totalCount);
		// maw.addObject("goodsList", goods);
		maw.setViewName("/pro/member/orderCenter");
		return maw;
	}

	/**
	 * 获取订单详情
	 */
	@RequestMapping("/order/getOrderInfo")
	public ModelAndView getOrderInfo(String orderId,
			HttpServletRequest request, HttpSession session) {
		ModelAndView maw = new ModelAndView();
		if (CheckIsNull.isEmpty(orderId)) {
			// maw.setViewName("/pub/sub/login");
			return null;
		}
		Login login = getLogin(request);
		if (null == login || CheckIsNull.isEmpty(login.getRecid())) {
			maw.setViewName("/pub/sub/login");
			return maw;
		}
		OrderInfo info = null;
		try {
			info = this.orderService.getEffectedOrder(orderId);
		} catch (Throwable e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		if (null == info) {
			try {
				info = this.orderService.getUnEffectedOrder(orderId);
			} catch (Throwable e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		if (null != info && info.getMemberid().equals(login.getRecid())) {
			maw.addObject("orderInfo", info);
			maw.setViewName("/pro/member/orderDetail");
			return maw;
		}
		return null;
	}

	/**
	 * 获取未生效订单详情(付款查询专用)
	 * 
	 * @return
	 */
	@RequestMapping("/order/getUnPayingOrder")
	public OrderInfo getUnPayingOrder(
			@RequestParam(value = "orderId", required = true)
			String orderId, HttpServletRequest request, HttpSession session) {
		OrderInfo info = null;
		try {
			info = this.orderService.getUnEffectedOrder(orderId);
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return info;
	}

	private Login getLogin(HttpServletRequest request) {
		Login login = null;
		try {
			if (CheckIsNull.isNotEmpty(request.getSession().getAttribute(
					Constant.LoginMemberUser)))
				login = new Login((MemberVo) request.getSession().getAttribute(
						Constant.LoginMemberUser));
		} catch (ServiceMessage e) {
			System.out.println(e.getMessage());
		}
		return login;
	}
}
