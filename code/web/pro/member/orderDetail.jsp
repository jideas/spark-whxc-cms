<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.spark.front.form.order.OrderInfo"%>
<%@page import="com.spark.cms.common.Constant"%>
<%@page import="com.spark.cms.common.Constant.Order.OnlineOrderStatus"%>
<%@page import="com.spark.cms.services.vo.OrderLogVo"%>
<%@page import="com.spark.cms.services.vo.OrderDetVo"%>
<%@page import="com.spark.base.common.utils.DoubleUtil"%>
<%@page import="com.spark.base.common.utils.DateUtil"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	OrderInfo order = (OrderInfo) request.getAttribute("orderInfo");
	if (null == order) {
		return;
	}
	String orderMessage = "";
	if (Constant.Order.OnlineOrderStatus.Finished.getCode().equals(
			order.getStatus())) {
		orderMessage = "�����Ѿ���ɣ���л����7������ݹ��";
	} else if (Constant.Order.OnlineOrderStatus.Paying.getCode()
			.equals(order.getStatus())) {
		orderMessage = "�𾴵Ŀͻ������ǻ�û�յ��ö����Ŀ���뾡��֧�����ö�����Ϊ������24Сʱ��24Сʱ�������û���ϵͳ���Զ�ȡ���ö���";
	} else if (Constant.Order.OnlineOrderStatus.Effected.getCode()
			.equals(order.getStatus())) {
		orderMessage = "���Ķ����Ѹ���ɹ�";
	} else if (Constant.Order.OnlineOrderStatus.Picking.getCode()
			.equals(order.getStatus())) {
		orderMessage = "���Ķ����ѿ�ʼ����������ĵȴ�";
	} else if (Constant.Order.OnlineOrderStatus.Delivering.getCode()
			.equals(order.getStatus())) {
		orderMessage = "���Ķ����ѿ�ʼ���ͣ���׼���ջ�";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/orderdetail.css">
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/order.js"></script>
		<style type="text/css">
#shortcut {
	padding-bottom: 1px;
	line-height: 30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}

#orderdetail-info-data-goods table th {
	background:
		url('<%=basePath%>/images/page/my7L-myorderlistheaderbg.png');
	height: 28px;
	line-height: 28px;
	font-family: "����";
	font-size: 12px;
	font-weight: bolder;
}

#paybutton {
	background-repeat:no-repeat;
	height: 25px;
	width: 55px;
	cursor: pointer;
	background-image: url('<%=basePath%>/images/page/get_sms_code01.png');
	color: #404040;
	display: inline;
	float:left;
	margin-left:5px;
	text-align: center;
	font-size: 14px;
	font-weight: bolder;
}
</style>
	</head>

	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="orderdetail-body">
			<div id="orderdetail-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
			</div>
			<div class="breadcrumb">
				<strong>�ҵ�7�� </strong>
				<span>&nbsp;&gt;&nbsp;<a
					href="<%=basePath%>/front/order/getOrders">�ҵĶ���</a><span></span>&nbsp;&gt;&nbsp;��������</span>
			</div>
			<div id="orderstate" style="" status="<%=order.getStatus()%>"
				orderId="<%=order.getRecid()%>">
				<div class="mt">
					<div style="display: inline;float: left;">
						<h2>
							�����ţ�<%=order.getBillsno().split("WSDD")[1]%>&nbsp;&nbsp;&nbsp;&nbsp;״̬��
							<span class="ftx14"><%=Constant.Order.OnlineOrderStatus.getStatus(
							order.getStatus()).getName()%></span>
						</h2>
					</div>
					<%
						if (Constant.Order.OnlineOrderStatus.Paying.getCode().equals(
								order.getStatus())) {
					%>
					<div id="paybutton" onselectstart="return false"
						onclick="order.toPaying('<%=order.getPayType()%>','<%=order.getRecid()%>')">
					</div>
					<%
						}
					%>
				</div>
				<div class="mc">
					<%=orderMessage%>
				</div>
			</div>
			<div id="orderstate-img"></div>
			<div id="order-log">
				<div id="order-log-title"
					style="background: url('<%=basePath%>/images/page/my7L-myorderlistheaderbg.png')">
					<h2>
						��������
					</h2>
				</div>
				<div id="order-log-table">
					<table cellpadding="0" cellspacing="0" width="100%">


						<tr class="order-log-table-heder">
							<th width="15%">
								<strong>����ʱ��</strong>
							</th>
							<th width="50%">
								<strong>������Ϣ</strong>
							</th>
							<th width="35%">
								<strong>������</strong>
							</th>
						</tr>
						<%
							for (OrderLogVo log : order.getLogs()) {
						%>
						<tr class="order-log-table-row">

							<td>
								<%=DateUtil.dateFromat(log.getProcessingTime(),
								"yyyy-MM-dd HH:mm:ss")%>
							</td>
							<td>
								<div style="padding-left: 190px; text-align: left;"><%=log.getMessage()%></div>
							</td>
							<td>
								<%=log.getOperator()%>
							</td>

						</tr>
						<%
							}
						%>

					</table>
				</div>
			</div>

			<div id="orderdetail-info">
				<div id="orderdetail-info-title"
					style="background: url('<%=basePath%>/images/page/my7L-myorderlistheaderbg.png')">
					<h2>
						������Ϣ
					</h2>
				</div>
				<div id="orderdetail-info-data">
					<div id="orderdetail-info-data-main">
						<table>
							<tr>
								<td style='width: 100px;' align='right'>
									�ջ���������
								</td>
								<td>
									<%=order.getConsignee()%>
								</td>
							</tr>
							<tr>
								<td align='right'>
									��&nbsp;&nbsp;&nbsp;&nbsp;ַ��
								</td>
								<td>
									<%=order.getAddress()%>
								</td>
							</tr>
							<tr>
								<td align='right'>
									�ֻ����룺
								</td>
								<td>
									<%=order.getConsigneetel()%>
								</td>
							</tr>
							<tr>
								<td align='right'>
									վ&nbsp;&nbsp;&nbsp;&nbsp;�㣺
								</td>
								<td>
									<%=order.getStationname()%>
								</td>
							</tr>
							<tr>
								<td align='right'>
									����ʱ�䣺
								</td>
								<td>
									<%=DateUtil.dateFromat(order.getDeliveryedate(),
							"yyyy-MM-dd HH:mm:ss")%>
								</td>
							</tr>
							<tr>
								<td align='right'>
									�ͻ����ţ�
								</td>
								<td>
									<%=order.isToDoor() ? "��" : "��"%>
								</td>
							</tr>

						</table>
					</div>
					<div id="orderdetail-info-data-goods">
						<h3>
							��Ʒ�嵥
						</h3>
						<div id="orderdetail-info-data-goods-table">
							<table cellpadding="0" cellspacing="0" width="1150px">
								<tr>
									<th width="10%">
										��Ʒ���
									</th>
									<th width="30%">
										��Ʒ����
									</th>
									<th width="15%">
										���
									</th>
									<th width="15%">
										����۸�
									</th>
									<th width="15%">
										���ͻ���
									</th>
									<th width="15%">
										��Ʒ����
									</th>
								</tr>
								<%
									for (OrderDetVo det : order.getDets()) {
								%>
								<tr>
									<td>
										<%=det.getGoodscode()%>
									</td>
									<td>
										<div class="al fl"
											style="text-align: left; padding-left: 10px">
											<a class="flk13"
												href="<%=basePath%>/front/toGoodsInfoPage?id=<%=det.getGoodsid()%>"
												target="_blank"><%=det.getGoodsname()%></a>

										</div>
										<div class="clr"></div>
										<div id="coupon_325276" class="fl"></div>
									</td>
									<td>
										<span class="ftx04"><%=det.getGoodsspec()%></span>
									</td>
									<td>
										<span class="ftx04"><%=DoubleUtil.getRoundStr(det.getPrice(), 2)%></span>
									</td>
									<td>
										<span class="ftx04"><%=DoubleUtil.getRoundStr(det.getVantages(), 0)%></span>
									</td>
									<td>
										<%=DoubleUtil.getRoundStr(det.getCount(), 0)%>
									</td>
								</tr>
								<%
									}
								%>
							</table>

						</div>
					</div>

				</div>
				<div id="orderdetail-info-footer">
					<ul>
						<li>
							<span
								style="width: 90px; text-align: right; display: inline-block;">��Ʒ�ܽ�</span><span
								style="width: 80px; text-align: left; font-weight: normal; font-family: 'Microsoft yahei'; display: inline-block;">��<%=DoubleUtil.getRoundStr(DoubleUtil.sub(order
							.getTotalamount(), order.getDeliveryCost()), 2)%></span>
						</li>
						<li>
							<span
								style="width: 90px; text-align: right; display: inline-block;">+&nbsp;��&nbsp;&nbsp;�ѣ�</span><span
								style="width: 80px; text-align: left; font-weight: normal; font-family: 'Microsoft yahei'; display: inline-block;">��<%=DoubleUtil.getRoundStr(order.getDeliveryCost(), 2)%></span>
						</li>
						<li>
							<span
								style="width: 90px; text-align: right; display: inline-block;">���ͻ��֣�</span><span
								style="width: 80px; text-align: left; font-weight: normal; font-family: 'Microsoft yahei'; display: inline-block;">&nbsp;&nbsp;&nbsp;<%=DoubleUtil.getRoundStr(order.getVantages(), 0)%></span>
						</li>
						<li>
							<span
								style="width: 90px; text-align: right; display: inline-block;">���ѻ��֣�</span><span
								style="width: 80px; text-align: left; font-weight: normal; font-family: 'Microsoft yahei'; display: inline-block;">&nbsp;&nbsp;&nbsp;<%=DoubleUtil.getRoundStr(order.getVantagesCost(), 0)%></span>
						</li>
						<!--�������������ģ�����δ���� ���۱����-->

					</ul>
					<div class="extra">
						����֧����
						<span class="ftx04"
							style="color: #CD2626; font-family: 'Microsoft yahei'; font-size: 20px;"><b>��<%=DoubleUtil.getRoundStr(order.getTotalamount(), 2)%></b>
						</span>
					</div>
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
</html>
