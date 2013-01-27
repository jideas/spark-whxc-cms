<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.spark.cms.services.vo.MemberAddressVo"%>
<%@page import="com.spark.front.form.order.ShopingCarGoods"%>
<%@page import="com.spark.front.form.order.OrderForm"%>
<%@page import="com.spark.base.common.utils.DateUtil"%>
<%@page import="com.spark.base.common.system.dic.DicItem"%>
<%@ include file="/pub/common/inc.jsp"%>
<%@page import="com.spark.base.common.utils.CheckIsNull"%>
<%@page import="com.spark.base.common.utils.DoubleUtil"%>

<%
	OrderForm order = (OrderForm) request.getAttribute("order");
	List<MemberAddressVo> addressList = null;
	List<ShopingCarGoods> goodsList = null;
	MemberAddressVo defaultAddress = null;
	if (null == order || CheckIsNull.isEmpty(order.getGoodsList())) {
		return;
	}
	goodsList = order.getGoodsList();
	addressList = order.getAddressList();
	if (null != addressList) {
		for (MemberAddressVo v : addressList) {
			defaultAddress = v;
			if (v.isIsdefault()) {
				defaultAddress = v;
				break;
			}
		}
	}

	List<DicItem> deliverTimeItems = order.getDeliverTimeItems();
	//double bagPrice = order.getBagPrice();
	double deliverPrice = order.getDeliverPrice();
	boolean p_freedelivery = order.isP_freedelivery();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/orderinfomain.css">
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/area.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/deliveryTimeSelector.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/order.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/common.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/cookieutil.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>/scripts/datePicker/WdatePicker.js"></script>
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<style type="text/css">
.step li b { position:absolute;
	width: 22px;
	height: 25px;
	background: url('<%=basePath%>/images/page/flow.gif') no-repeat;
	top: 0;
	right: -1px;
}

#paytype_save {
	color: #ffffff;
	background-image: url('<%=basePath%>/images/page/sort-bg02.png');
	height: 25px;
	line-height: 25px;
	width: 130px;
	cursor: pointer;
	margin-top: 3px;
	margin-bottom: 5px;
	text-align: center;
}

#consignee_save {
	color: #ffffff;
	background-image: url('<%=basePath%>/images/page/sort-bg02.png');
	height: 25px;
	line-height: 25px;
	width: 100px;
	cursor: pointer;
	margin-bottom: 5px;
}

* {
	padding: 0px;
	margin: 0px;
}

body {
	font-size: 12px;
	font-family: "����";
	text-align: center;
	margin: 0 auto;
	background-color: #ffffff;
}

body,h1,h2,h3,h4,h5,h6,p,ul,ol,li,form,img,dl,dt,dd,blockquote,fieldset,div,strong,label,em
	{
	margin: 0;
	padding: 0;
	border: 0;
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
</style>

	</head>

	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="orderinfomain-body">
			<div id="orderinfomain-header">
				<div id="orderinfomain-header-left">
					<div id="orderinfomain-header-left-top">
						<img src="<%=basePath%>/images/page/logo.png"
							style="height: 75px; cursor: pointer;" onclick="order.toHome()"
							title="����7���������ҳ"></img>
					</div>
				</div>
				<div id="orderinfomain-header-right">
					<div id="step2" class="step">
						<ul>
							<li class="fore1">
								1.�ҵĹ��ﳵ
								<b></b>
							</li>
							<li class="fore2">
								2.��д�˶���Ϣ
								<b></b>
							</li>
							<li class="fore3">
								3.�ɹ��ύ����
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div id="orderinfo">
				<div id="orderinfo-title">
					<div class="orderinfotitle">
						<strong>��д�˶Զ�����Ϣ</strong>
					</div>
				</div>
				<div id="orderinfo-info">
					<!--�ջ��˵�ַ��ʼ-->
					<div id="part_consignee">
						<div class="o_show">
							<h1>
								�ջ�����Ϣ
								<a href="javascript:void(0)" onclick="order.Edit_Consignee()">[�޸�]</a>
							</h1>
							<div class="middle">

								<table>
									<tr>
										<td style="width: 85px;" align="right">
											�ջ���������
										</td>
										<td id="consignee"
											prevalue="<%=null == defaultAddress
					|| CheckIsNull.isEmpty(defaultAddress.getConsignee()) ? ""
					: defaultAddress.getConsignee()%>">
											<%
												if (null != defaultAddress) {
											%><%=CheckIsNull.isEmpty(defaultAddress
										.getConsignee()) ? "" : defaultAddress
								.getConsignee()%>
											<%
												}
											%>
										</td>
										<td>
											<div id="valuewarning_consignee" class="valuewarning"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											��&nbsp;&nbsp;&nbsp;&nbsp;����
										</td>
										<td id="province" addressId="<%if(null!=defaultAddress){%><%=defaultAddress.getRecid()%><%}%>"
											prevalue="<%if(null!=defaultAddress){%><%=defaultAddress.getProvinceTitle()%><%=defaultAddress.getCityTitle()%><%=defaultAddress.getTownTitle()%><%}%>"
											province="<%if(null!=defaultAddress){%><%=defaultAddress.getProvince()%><%}%>"
											city="<%if(null!=defaultAddress){%><%=defaultAddress.getCity()%><%}%>"
											town="<%if(null!=defaultAddress){%><%=defaultAddress.getTown()%><%}%>">
											<%
												if (null != defaultAddress) {
											%><%=defaultAddress.getProvinceTitle()%><%=defaultAddress.getCityTitle()%><%=defaultAddress.getTownTitle()%>
											<%
												}
											%>
										</td>
										<td>
											<div id="valuewarning_province" class="valuewarning"></div>
										</td>
									</tr>
									<tr style="display: ">
										<td style="text-align: right;">
											վ&nbsp;&nbsp;&nbsp;&nbsp;�㣺
										</td>
										<td id="station"
											value="<%=defaultAddress == null
							|| CheckIsNull.isEmpty(defaultAddress
									.getStationName()) ? "" : defaultAddress
							.getStationName()%>"
											recid="<%=defaultAddress == null
					|| CheckIsNull.isEmpty(defaultAddress.getStationid()) ? ""
					: defaultAddress.getStationid()%>"><%=defaultAddress == null
							|| CheckIsNull.isEmpty(defaultAddress
									.getStationName()) ? "" : defaultAddress
							.getStationName()%>
										</td>
										<td>
											<div id="valuewarning_station" class="valuewarning"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											��&nbsp;&nbsp;&nbsp;&nbsp;ַ��
										</td>
										<td id="address"
											prevalue="<%if(null!=defaultAddress){%><%=defaultAddress.getAddress()%><%}%>">
											<%
												if (null != defaultAddress) {
											%><%=defaultAddress.getAddress()%>
											<%
												}
											%>
										</td>
										<td>
											<div id="valuewarning_province" class="valuewarning"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											�ֻ����룺
										</td>
										<td id="mobile"
											prevalue="<%if(null!=defaultAddress){%><%=defaultAddress.getMobile()%><%}%>">
											<%
												if (null != defaultAddress) {
											%><%=defaultAddress.getMobile()%>
											<%
												}
											%>
										</td>
										<td>
											<div id="valuewarning_mobile" class="valuewarning"></div>
										</td>
									</tr>
									<tr>
										<td align="right">
											�̶��绰��
										</td>
										<td id="phone"
											prevalue="<%if(null!=defaultAddress){%><%=defaultAddress.getTelephone()%><%}%>">
											<%
												if (null != defaultAddress) {
											%><%=defaultAddress.getTelephone()%>
											<%
												}
											%>
										</td>
									</tr>
									<tr>
										<td align="right">
											��&nbsp;&nbsp;&nbsp;&nbsp;�ࣺ
										</td>
										<td id="postCode"
											prevalue="<%if(null!=defaultAddress){%><%=defaultAddress.getPostcode()%><%}%>">
											<%
												if (null != defaultAddress) {
											%><%=defaultAddress.getPostcode()%>
											<%
												}
											%>
										</td>
									</tr>
								</table>

							</div>
						</div>
					</div>
					<!--�ջ��˵�ַ����-->
					<!--֧����ʽ�����ͷ�ʽ��ʼ-->
					<div id="part_payTypeAndShipType">
						<div class="o_show">
							<h1>
								֧�������ͷ�ʽ
								<a id="linkPayTypeShipType" href="javascript:void(0)"
									onclick="order.Edit_PayType();">[�޸�]</a>
							</h1>
							<div id="updateInfo_payType"></div>
							<div class="middle">

								<table>

									<tr>
										<td style="text-align: right; width: 85px">
											֧����ʽ��
										</td>
										<td id="paytype" value="���֧��" code="1">
											���֧��
										</td>
										<td>
											<div id="valuewarning_paytype" class="valuewarning"></div>
										</td>
									</tr>
									
									<!-- ��������ʱ�� -->
									<%if(!order.isOnlyBookingGoods()){%>
									<tr style="">
										<td style="text-align: right;">
											����ʱ�䣺
										</td>
										<td id="deliverTime"
											value="<%=DateUtil.getTomorrow(null)%>&nbsp;<%=deliverTimeItems.get(0).getTitle()%>"
											day="<%=DateUtil.getTomorrow(null)%>"
											time="<%=deliverTimeItems.get(0).getTitle()%>"
											timeCode="<%=deliverTimeItems.get(0).getCode()%>"><%=DateUtil.getTomorrow(null)%>&nbsp;<%=deliverTimeItems.get(0).getTitle()%></td>
										<td>
											<div id="valuewarning_deliverTime" class="valuewarning"></div>
										</td>
									</tr><%} if(order.isHasBookingGoods()){%>
									<tr style="">
										<td style="text-align: right;">
											Ԥ������ʱ�䣺
										</td>
										<td id="bdeliverTime"
											value="<%=DateUtil.getTheDayAfterTomorrow(null)%>&nbsp;<%=deliverTimeItems.get(0).getTitle()%>"
											day="<%=DateUtil.getTheDayAfterTomorrow(null)%>"
											time="<%=deliverTimeItems.get(0).getTitle()%>"
											timeCode="<%=deliverTimeItems.get(0).getCode()%>"><%=DateUtil.getTheDayAfterTomorrow(null)%>&nbsp;<%=deliverTimeItems.get(0).getTitle()%></td>
										<td>
											<div id="valuewarning_bdeliverTime" class="valuewarning"></div>
										</td>
									</tr><%}%>
									
									<tr style="display: ">
										<td style="text-align: right;">
											�ͻ����ţ�
										</td>

										<td id="toDoor" value="��"
											price="<%=deliverPrice%>" beginAmount="<%=order.getBeginAmount()%>">
											��&nbsp;�ǰ��¿ͻ��ͻ����ŷ���<%=DoubleUtil.getRoundStr(deliverPrice, 2)%>Ԫ/�Ρ����������ͻ����£�������Żݣ����<a href="<%=basePath%>/pro/member/delivercharge.jsp" target='_blank'>[����]</a><%if(order.getBeginAmount()>0){%>��<font color="#000000">[������Ϣ��������<%=DoubleUtil.getRoundStr(order.getBeginAmount())%>Ԫ������ͻ�����]</font><%}%>
										</td>
									</tr>
								</table>

							</div>
						</div>
					</div>
					<!--֧����ʽ�����ͷ�ʽ����-->
					<!--��Ʒ�嵥��ʼ-->
					<div id="part_goods">
						<div class="o_show">
							<h1>
								��Ʒ�嵥
							</h1>
							<div class="backtoshopingcar-text">
								<a id="linkPayTypeShipType" href="javascript:void(0)"
									onclick="order.toShopingCar();" style="text-align: right">�����޸Ĺ��ﳵ</a>
							</div>

						</div>
						<div id="part_goods_data">
							<div id="part_goods-header">
								<div class="column-code">
									��Ʒ���
								</div>
								<div class="column-goods">
									��Ʒ
								</div>
								<div class="column-spec">
									���
								</div>
								<div class="column-price">
									�۸�
								</div>
								<div class="column-promotion">
									����
								</div>
								<div class="column-quantity">
									����
								</div>
							</div>
							<%
								if (CheckIsNull.isNotEmpty(order.getGoodsList())) {
									for (ShopingCarGoods goods : order.getGoodsList()) {
							%>
							<div class="part_goods_row" id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>"
								vantagesGoods="<%=goods.isVantagesGoods()%>" isGift="<%=goods.isGift()%>" disrate="<%=goods.getDisrate()%>"
									vantagesCost="<%=goods.getVantagesCost()%>"
									vantages="<%=goods.getVantages()%>"
									vantagesType="<%=goods.getVantagesType()%>" price="<%=goods.getPrice()%>" count="<%=goods.getCount()%>">
								<div class="row-column-code" id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_code">
									<%=goods.getGoodsCode()%>
								</div>
								<div class="row-column-goods" id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_name">
								<%if(goods.isBookingGoods()){%>[Ԥ��]<%}
										if (goods.isVantagesGoods()) {
									%>[�����̳���Ʒ]<%
										} else {if(goods.isFreedelivery()){
									%>[���˷�]
									<%}if(goods.isGift()||goods.isOtherGift()){%>[��Ʒ]<%}
										if (null != goods.getDisrate()
															&& goods.getDisrate() > 0
															|| ((CheckIsNull.isNotEmpty(goods
																	.getVantagesType()) && "2".equals(goods
																	.getVantagesType())))) {
									%><span class="promotion_message">[������<%
										if (null != goods.getDisrate()
																&& goods.getDisrate() > 0) {
									%><%=goods.getDisrate() * 10%>���Ż� <%
										}
														if (CheckIsNull.isNotEmpty(goods.getVantagesType())
																&& "2".equals(goods.getVantagesType())) {
									%>˫������<%
										}
									%>]</span>
									<%
										}
												}
									%><a
										href="<%=basePath%>/front/toGoodsInfoPage?id=<%=goods.getGoodsId()%>"
										target="_blank"><%=goods.getGoodsName()%></a>
								</div>
								<div class="row-column-spec" id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_spec">
									<%=goods.getSpec()%>
								</div>
								<div class="row-column-price" id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_price"
									price="<%=goods.getPrice()%>">
									<%=DoubleUtil.getRoundStr(goods.getPrice(), 2)%>
								</div>
								<div class="row-column-promotion"
									id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_vantages"
									disrate="<%=goods.getDisrate()%>"
									vantagesGoods="<%=goods.isVantagesGoods()%>"
									vantagesCost="<%=goods.getVantagesCost()%>"
									vantages="<%=goods.getVantages()%>"
									vantagesType="<%=goods.getVantagesType()%>"
									isGift="<%=goods.isGift()%>" isOtherGift="<%=goods.isOtherGift()%>">
									<%=goods.isVantagesGoods() ? DoubleUtil
							.getRoundStr((0 - goods.getVantagesCost()), 0)
							: DoubleUtil.getRoundStr(goods.getVantages(), 0)%>
								</div>
								<div class="row-column-quantity"
									id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_count"
									count="<%=goods.getCount()%>">
									<%=DoubleUtil.getRoundStr(goods.getCount(), 0)%>
								</div>
							</div>
							<%
								}
								}
							%>

						</div>
						<!-- <div id="part_gift">
							������Ʒ�����<a href="#">[��ȡ]</a>
						</div> -->
					</div>
					<!--��Ʒ�嵥����-->
					<!--������Ϣ��ʼ-->
					<div id="part_total">
						<div class="o_show">
							<div>
								<h1>
									������Ϣ
								</h1>
							</div>
							<div class="middle">

								<div id="part_total_amount">
									��Ʒ��
									<span id="total_goods_amount" style="font-family: 'Microsoft yahei';line-height: 20px;"
										totalAmount="<%=order.getTotalamount()%>"><%=DoubleUtil.getRoundStr(order.getTotalamount(), 2)%></span>
									+ �˷ѣ�
									<span id="total_deliveryCost" total_deliveryCost="0.00" style="font-family: 'Microsoft yahei'" p_freedelivery="<%=order.isP_freedelivery()?"��":"��"%>">0.00</span>
									<!-- + �������
									<span id="total_bagsCost" total_bagsCost="0.00">0.00</span>Ԫ -->
								</div>
								<div id="part_total_vantages">
									���ͻ��֣�
									<span id="total_vantages" style="font-family: 'Microsoft yahei';line-height: 20px;"
										p_vantages="<%=null == order.getP_vantages() ? 0 : order
					.getP_vantages()%>"
										totalVantages="<%=order.getVantages()%>"><%=DoubleUtil.getRoundStr(order.getVantages(), 0)%></span>
								</div>
								<div id="part_total_vantagesCost">
									���ѻ��֣�
									<span id="total_vantages_Cost" style="font-family: 'Microsoft yahei';line-height: 20px;"
										total_vantages_Cost="<%=order.getVantagesCost()%>"><%=DoubleUtil.getRoundStr(order.getVantagesCost(), 0)%></span>
								</div>
								<div id="part_total_amounting">
									<div id="part_total_amounting_left"></div>
									<div id="part_total_amounting_right"
										totalAmount="<%=order.getTotalamount()%>">
										Ӧ���ܶ
										<b class="tatalamount" id="tatalAmount"
											style="color: #CD2626; font-size: 20px;font-family: 'Microsoft yahei'">��<%=DoubleUtil.getRoundStr(order.getTotalamount(), 2)%></b>
									</div>
								</div>

							</div>
							<!-- <div class="footer"></div> -->
						</div>
					</div>
					<!--������Ϣ����-->
					<!--���o��ʼ-->

					<div id="submitbutton-text">
						<img onclick="order.submit()" style="cursor: pointer;"
							src="<%=basePath%>/images/page/shopcart-icon02.png"></img>
					</div>


					<!--���o����-->
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
