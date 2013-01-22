<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.spark.front.form.order.OrderInfo"%>
<%@page import="com.spark.base.common.utils.CheckIsNull"%>
<%@page import="com.spark.cms.services.vo.OrderDetVo"%>
<%@page import="com.spark.base.common.utils.DoubleUtil"%>
<%@page import="com.spark.base.common.utils.DateUtil"%>
<%@page import="com.spark.cms.common.Constant"%>
<%@page import="com.spark.cms.common.Constant.Order.OnlineOrderStatus"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	List<OrderInfo> orderList = (List<OrderInfo>) request
			.getAttribute("orderList");
	int totalCount = (Integer) request.getAttribute("totalCount");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/ordercenter.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/ordercenter.js"></script>
		<script type="text/javascript" src="<%=basePath%>/pub/common/pagin.js"></script>
		<style type="text/css">
.tableHead {
	background-image: url('<%=basePath%>/images/page/my7L-myorderlistheaderbg.png');
	height: 30px;
}
#shortcut {
	padding-bottom: 1px;
	line-height: 30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}

/*分页控件*/
.pagin a,.pagin span {
	height: 20px;
	padding: 3px 10px;
	border: 1px solid #ccc;
	margin-left: 2px;
	font-family: arial;
	line-height: 20px;
	font-size: 14px;
	*vertical-align: middle;
	overflow: hidden;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}

.pagin .text,.pagin .current {
	border: none;
	padding: 4px 11px;
}

.pagin a:link,.pagin a:visited {
	color: #005aa0;
	text-decoration:none;
}

.pagin a:hover,.pagin a:active {
	background: #005aa0;
	color: #fff;
	text-decoration: none;
}

.pagin .current,.pagin .current:link,.pagin .current:visited {
	color: #f60;
	font-weight: bold;
}

.pagin b {
	dispaly: block;
	position: absolute;
	top: 9px;
	width: 5px;
	height: 9px;
	background-image: url(<%=basePath%>/images/page/pagin.png);
	background-repeat: no-repeat;
	overflow: hidden;
}

.pagin .prev,.pagin .next,.pagin .prev-disabled,.pagin .next-disabled {
	position: relative;
	padding-top: 5px;
	height: 18px;
	line-height: 18px;
}

.pagin .prev-disabled,.pagin .next-disabled {
	color: #ccc;
	cursor: default;
}

.pagin .prev,.pagin .prev-disabled {
	padding-left: 12px;
}

.pagin .prev b {
	left: 3px;
	background-position: -6px 0px;
}

.pagin .prev-disabled b {
	left: 3px;
	background-position: -18px 0px;
}

.pagin .next,.pagin .next-disabled {
	padding-right: 12px;
}

.pagin .next b {
	right: 3px;
	background-position: 0px 0px;
}

.pagin .next-disabled b {
	right: 3px;
	background-position: -12px 0px;
}

.pagin-m a,.pagin-m span {
	height: 14px;
	line-height: 14px;
	font-size: 12px;
}

.pagin-m b {
	top: 5px;
}

.pagin-m .prev,.pagin-m .next,.pagin-m .prev-disabled,.pagin-m .next-disabled
	{
	padding-top: 3px;
	height: 14px;
	*height: 22px;
	line-height: 14px;
	*line-height: 16px;
}
.pagin a, .pagin span {
	height: 14px;
	*height: 22px;
}
</style>
<script type="text/javascript">
	var addKeyDownListener = function() {  
				$('#searchwords').keydown(function(event) { 
					if (event.which == 13) {
						ordercenter.refreshData();
					}
				});
			};		
		$(function(){ 
			addKeyDownListener();
		});
</script>
	</head>
	<body style="background-color: #FFFFFF">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="ordercenter-body">
			<div id="ordercenter-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div class="breadcrumb">
				<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;我的订单</span>
			</div>
			<div id="ordercenter-data-body">

				<div id="ordercenter-menu">
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div id="ordercenter-data">
					<div id="ordercenter-data-title" style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							我的订单
						</h3>
					</div>
					<div id="ordercenter-data-query">
						<div id="ordercenter-data-query-left">
							<select id="month" style="width: 150px;"
								onchange="ordercenter.refreshData()">
								<option value="0">
									近一个月订单
								</option>
								<option value="1">
									一个月前订单
								</option>
							</select>
							<select id="effected" style="width: 150px;"
								onchange="ordercenter.refreshData()">
								<option value="2">
									未付款的订单
								</option>
								<option value="0" selected>
									进行中的订单
								</option>
								<option value="1">
									已完成的订单
								</option>
							</select>
						</div>
						<div id="ordercenter-data-query-right">
							<input type="text" id="searchwords" style="width: 200px;"></input>
							<input type="button" id="searchButton"
								onclick="ordercenter.refreshData()" value="查询"
								style="width: 50px; height: 20px; cursor: pointer;" />
						</div>
					</div>
					<div id="ordercenter-data-table">
						<table id="dataTable" cellpadding="0" cellspacing="0" width="100%">
							<tr class="tableHead">
								<th width="12%">
									订单编号
								</th>
								<th width="27%">
									订单商品
								</th>
								<th width="10%">
									收货人
								</th>
								<th width="12%">
									订单金额
								</th>
								<th width="12%">
									下单时间
								</th>
								<th width="12%">
									订单状态
								</th>
								<th width="12%">
									操作
								</th>
							</tr>
							<%
								if (CheckIsNull.isNotEmpty(orderList)) {
									for (OrderInfo order : orderList) {
							%>
							<tr id="<%=order.getRecid()%>">
								<td>
									<a name='orderIdLinks' id='idUrl<%=order.getRecid()%>'
										href='<%=basePath%>/front/order/getOrderInfo?orderId=<%=order.getRecid()%>'
										target="_blank" clstag='click|keycount|orderinfo|order_num'
										class="orderLink"><%=order.getBillsno().split("WSDD")[1]%></a>
								</td>
								<td align="left">
									<div id="img<%=order.getRecid()%>" class="img-list">
										<%
											for (OrderDetVo det : order.getDets()) {
										%>
										<a href="<%=basePath%>/front/toGoodsInfoPage?id=<%=det.getGoodsid()%>" target="_blank"> <img style="border:#DEDEDE 0.5px solid;" title="<%=det.getGoodsname()%>"
												src="<%=basePath%>/<%=det.getImgUrl()%>" width="50"
												height="50" /> </a>
										<%
											}
										%>
									</div>
								</td>
								<td>
									<%=order.getConsignee()%>
								</td>
								<td>
									<%=DoubleUtil.getRoundStr(order
											.getTotalamount(), 2)%>
								</td>
								<td>
									<span class="ftx-03"><%=DateUtil.dateFromat(order.getCreatedate())%>
										<br /><%=DateUtil.getHHMMSS(order
											.getCreatedate())%></span>
								</td>

								<td>
									<span class="ftx-03"><%=Constant.Order.OnlineOrderStatus.getStatus(
									order.getStatus()).getName()%></span>
								</td>
								<td>
									<a
										href="<%=basePath%>/front/order/getOrderInfo?orderId=<%=order.getRecid()%>">
										<%
											if (OnlineOrderStatus.Paying.getCode().equals(
															order.getStatus())) {
										%>支付<%
											} else {
										%>查看<%
											}
										%> </a>
								</td>
							</tr>

							<%
								}
								}
							%>

						</table>

					</div>
					<div id="pagin_div" class="pagin pagin-m fr"></div>
				</div>
			</div>
			<div id="serviceFloors">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>

	</body>
	<script type="text/javascript">
	$(function() {
				var  paramters = {	
				parentId: 'pagin_div', 
				count: <%=totalCount%>, 
 				pageSize: 20, 
 				isActionOnLoad:false,
 				actionListener: function(pageIndex) {
 					ordercenter.pageAction(pageIndex);
 				}
 			}
				
				new CmsPaging(paramters);
			});
			</script>
</html>
