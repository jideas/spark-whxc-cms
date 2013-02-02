<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.spark.front.form.order.ShopingCarGoods"%>
<%@page import="com.spark.base.common.utils.CheckIsNull"%>
<%@page import="com.spark.base.common.utils.DoubleUtil"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	List<ShopingCarGoods> goodsList = (List<ShopingCarGoods>) request
			.getAttribute("goodsList");
	double totalAmount = 0;
	double totalCount = 0;
	double totalVantages = 0;
	if (null != goodsList && goodsList.size() > 0) {
		totalCount = goodsList.size();
		for (ShopingCarGoods goods : goodsList) {
			totalAmount += DoubleUtil.mul(goods.getPrice(), goods
					.getCount());
			totalVantages += goods.isVantagesGoods() ? (0 - DoubleUtil
					.mul(goods.getVantagesCost(), goods.getCount()))
					: DoubleUtil.mul(goods.getVantages(), goods
							.getCount());
		}
	}
	String footerHtml = (String) request.getAttribute("footerHtml");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery-1.8.1.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/cookieutil.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/shopingcar.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/common.js"></script>
		<link href="<%=basePath%>/pro/member/css/shopingcar.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<style type="text/css">
.step li b {
	position: absolute;
	width: 22px;
	height: 25px;
	background: url('<%=basePath%>/images/page/flow.gif') no-repeat;
	top: 0;
	right: -1px;
}

#shopingcar-center-header {
	height: 32px;
	line-height: 32px;
	width: 1200px;
	background-image: url('<%=basePath%>/images/page/lab-bg.png');
	border-bottom: #e0e0e0 1px solid;
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
		<div id="shopingcar-body">
			<div id="shopingcar-header">
				<div id="shopingcar-header-left">
					<div id="shopingcar-header-left-top">
						<img src="<%=basePath%>/images/page/logo.png"
							style="height: 75px; cursor: pointer;"
							onclick="shopingCar.toHome()"></img>
					</div>
				</div>
				<div id="shopingcar-header-right">
					<div id="step1" class="step">
						<ul>
							<li class="fore1">
								1.我的购物车
								<b></b>
							</li>
							<li class="fore2">
								2.填写核对信息
								<b></b>
							</li>
							<li class="fore3">
								3.成功提交订单
							</li>
						</ul>
					</div>
				</div>
			</div>
			<%
				if (CheckIsNull.isNotEmpty(goodsList)) {
			%>
			<div id="shopingcar-center">
				<div id="shopingcar-center-header">
					<div class="column-checkbox">
						<input
							style="vertical-align:middle;height:32px;"
							onclick="shopingCar.headCheck()" id="toggle-checkboxes"
							type="checkbox" checked value="" />&nbsp;全选
					</div>
					<div class="column-goods">
						商品
					</div>
					<div class="column-spec">
						规格
					</div>
					<div class="column-price">
						价格
					</div>
					<div class="column-promotion">
						积分
					</div>
					<div class="column-quantity">
						数量
					</div>
					<div class="column-action">
						操作
					</div>
				</div>
				<!-- <div id="shopingcar-center-promotion">
					<div class="shopingcar-center-promotion-img">
						<img src="../../images/page/promotion_01.png"></img>
					</div>
					<div class="shopingcar-center-promotion-text">
						<b style="margin-left: 5px;">活动商品已购满1000.00元， 您只需再加价29.00元，即可<a
							href="#" style="text-decoration: none">领取赠品</a> </b>
					</div>
					<div class="shopingcar-center-promotion-text-price">
						<b>3199.00</b>
					</div>
				</div> -->
				<div class="shopingcar-center-list">
					<%
						for (ShopingCarGoods goods : goodsList) {
					%>
					<div class="shopingcar-center-row" id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_row">
						<div class="list-column-checkbox">
							<div class="list-column-checkbox-input">
								<input onclick="shopingCar.rowCheck()" type="checkbox" checked
									value="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>"
									id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_checkBox"
									vantagesGoods="<%=goods.isVantagesGoods()%>" />
							</div>
							<div class="list-column-checkbox-img">
								<img style="border:#DEDEDE 0.5px solid;" width="50px" height="50px"
									src="<%=basePath%><%=goods.getMiniPicturePath()%>"></img>
							</div>
						</div>
						<div class="list-column-goods">
						<%if(goods.isBookingGoods()){%>[预订]<%}
								if (goods.isVantagesGoods()) {
							%>[积分商城商品]<%
								} else { if(goods.isFreedelivery()){
							%>[免运费]<%}
								if (null != goods.getDisrate()
													&& goods.getDisrate() > 0
													|| ((CheckIsNull.isNotEmpty(goods
															.getVantagesType()) && "2".equals(goods
															.getVantagesType())))) {
							%><span class="promotion_message">[促销：<%
								if (null != goods.getDisrate()
														&& goods.getDisrate() > 0) {
							%><%=goods.getDisrate() * 10%>折优惠<%
								}
												if (CheckIsNull.isNotEmpty(goods.getVantagesType())
														&& "2".equals(goods.getVantagesType())) {
							%>双倍积分<%
								}
							%>]</span>
							<%
								}
										}
							%><a
								href="<%=basePath%>/front/toGoodsInfoPage?id=<%=goods.getGoodsId()%>&sign=<%=goods.isVantagesGoods()?"2":"1"%>"
								target="_blank"><%=goods.getGoodsName()%></a>
						</div>
						<div class="list-column-spec">
							<span id=""><font color="gray"><%=goods.getSpec()%></font>
							</span>
						</div>
						<div class="list-column-price">
							<span id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_price"
								value="<%=goods.getPrice()%>"><font
								color="gray"><%=DoubleUtil.getRoundStr(goods.getPrice(), 2)%></font>
							</span>
						</div>
						<div class="list-column-promotion">
							<span id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_vantages"
								value="<%=goods.isVantagesGoods() ? (0 - goods
							.getVantagesCost()) : goods.getVantages()%>"><font
								color="gray"><%="0" == (DoubleUtil.getRoundStr(goods
							.isVantagesGoods() ? 0 - goods.getVantagesCost()
							: goods.getVantages(), 0)) ? "-"
							: DoubleUtil.getRoundStr(
									goods.isVantagesGoods() ? 0 - goods
											.getVantagesCost() : goods
											.getVantages(), 0)%></font> </span>
						</div>
						<div class="list-column-quantity">
							<img src="<%=basePath%>/images/page/button_sub.png"
								style="margin-top: 27px; cursor: pointer;"
								onclick="shopingCar.subGoods('<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_quantity',1)" />
							<input class="_quantity_input"
								vantagesGoods="<%=goods.isVantagesGoods()%>"
								id="<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_quantity"
								onchange="shopingCar.countChangeListener(this)"
								type="text"
								style="margin-top: 25px; width: 30px; height: 21px; line-height: 21px; border: #C1C1C1 solid 1px; text-align: center; vertical-align: top;"
								value="<%=DoubleUtil.getRoundStr(goods.getCount(), 0)%>"
								preValue="<%=DoubleUtil.getRoundStr(goods.getCount(), 0).replaceAll(",","")%>"
								align="bottom" />
							<img src="<%=basePath%>/images/page/button_sum.png"
								style="margin-top: 27px; cursor: pointer;"
								onclick="shopingCar.addGoods('<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>_quantity',1)" />
						</div>
						<div class="list-column-action">
							<a href="javascript:void(0)" style="text-decoration: none"
								onclick="shopingCar.deleteGoods('<%=goods.getGoodsId()%>_<%=goods.isVantagesGoods()%>')">删除</a>
						</div>

					</div>


					<%
						}
					%>
				</div>
			</div>
			<div id="shopingcar-total">
				<div id="shopingcar-total-left">
					<div
						style="margin-left: 5px; margin-right: 0px; display: inline; height: 20px; line-height: 20px; float: left; padding-top: 2px;">
						<img style="margin-top: 2px; margin-left: 2px; margin-right: 2px;"
							src="<%=basePath%>/images/page/shopcart-icon-delete.png"
							style="height: 12px;"></img>
					</div>
					<div
						style="display: inline; height: 20px; line-height: 20px; float: left;">
						<a href="javascript:shopingCar.deleteSelectedGoods()">删除选中商品</a>
					</div>
				</div>
				<div id="shopingcar-total-right">
					<div id="shopingcar-total-right-left">
						<font color="red"><span id="total_GoodsCount"><%=DoubleUtil.getRoundStr(totalCount, 0)%></span>
						</font>件商品
					</div>
					<div id="shopingcar-total-right-right">
						<div id="shopingcar-total-right-right-top">
							<div id="shopingcar-total-right-right-top-left">
								<span style="align: left">总计：</span>
							</div>
							<div id="shopingcar-total-right-right-top-right">
								￥<span id="total_GoodsAmount" style="align: right;font-family: 'Microsoft yahei'"><%=DoubleUtil.getRoundStr(totalAmount, 2)%></span>
							</div>
						</div>
						<div id="shopingcar-total-right-right-bottom">
							<div id="shopingcar-total-right-right-bottom-left">
								<span style="align: left">积分：</span>
							</div>
							<div id="shopingcar-total-right-right-bottom-right" style="align: right;font-family: 'Microsoft yahei'">
								<span id="total_GoodsVantages"><%=DoubleUtil.getRoundStr(totalVantages, 0)%></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="shopingcar-totalamount">
				<ul>
					<li>
						<span id="shopingcar-totalamount-left">总计(不含运费)：</span><span
							id="shopingcar-totalamount-right" style="font-family: 'Microsoft yahei';font-size: 20px;">￥<%=DoubleUtil.getRoundStr(totalAmount, 2)%></span>
					</li>
				</ul>
			</div>
			<div id="shopingcar-button">
				<div id="shopingcar-button-left">
					<img style="cursor: pointer;"
						src="<%=basePath%>/images/page/shopcart-icon02-normal.png"
						onclick="shopingCar.toHome()"
						onmouseover="shopingCar.continueMouseOver(this)"
						onmouseout="shopingCar.continueMouseOut(this)"></img>
				</div>
				<div id="shopingcar-button-right">
					<div onselectstart="return false"
						id="shopingcar-button-right-button" onclick="shopingCar.toOrder()">
						<!-- <b>去结算</b> -->
						<img src="<%=basePath%>/images/page/shopcart-icon01.png"
							style="cursor: pointer;"></img>
					</div>
				</div>
			</div>
			<%
				} else {
			%>
			<div 
				style="width: 1200px; height: 100px; background: #f5f5f5; margin-top: 8px;margin-bottom: 8px;">
				<div id="noGoods" 
					style="text-align: center; width: 1200px; padding-top: 40px; font-family: '宋体'; font-size: 16px; font-weight: normal;">
					您的购物车中还没有商品，快去
					<a href="javascript:shopingCar.toHome()" style="color:#031C86;">[选购]</a>吧。
				</div>
			</div>
			<%
				}
			%>
			<div id="footer">
				<jsp:include page="/pro/member/shopingCar_footer.jsp?footerHtml=<%=footerHtml%>" flush="true" />
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
