<%@page import="java.util.Calendar"%>
<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>包月充值</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<style type="text/css">
.main .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

.maindiv {
	width: 1038px; *
	width: 1038px;
	float: right;
	/*border: 1px solid #E6E6E6;*/ 
}
.mainTabelArea {
	*padding-top: 10px;
	border-top: 1px solid #E6E6E6;
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
}
.maindiv .maintable {
	width: 100%;
	padding-top: 10px;
}

.maindiv .maintable th {
	height: 30px;
	width: 100%;
	background: url('<%=basePath%>/images/page/sort-bg05.png') repeat-x;
	alight: left;
	padding-left: 5px;
	text-align: left;
	font-family: "宋体";
	font-size: 14px;
	border-top: 1px solid #E6E6E6;
}

.maindiv .maintable .table_wrap {
	width: 100%;
	text-align: center;
	margin-top: 15px;
	margin-bottom: 15px;
}

.maindiv .maintable .table_wrap .cardContain {
	border: 1px solid #ccc;
	width: 638px;
	margin: 20px auto;
	text-align: center;
	-webkit-user-select: none;
	-moz-user-select: none;
}

.maindiv .maintable .table_wrap .cardContain .cardTable {
	font-size: 12px;
	color: #333;
	width: 600px;
	margin: 5px;
}

.maindiv .maintable .table_wrap .cardContain .cardTable .normal_Tab {
	width: 196px;
	height: 90px;
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #e7e7e7;
	padding: 5px;
	background: url(<%=basePath%>/images/page/deliver_visit_bg.gif) repeat-x
		bottom;
	cursor: pointer;
}

.maindiv .maintable .table_wrap .cardContain .cardTable .hover_Tab {
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #FF9900;
	background: url(<%=basePath%>/images/page/deliver_hover_bg.gif) repeat-x
		bottom;
}

.maindiv .maintable .table_wrap .cardContain .cardTable .click_Tab {
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #FF9900;
	background: url(<%=basePath%>/images/page/deliver_buycard_right_ico.gif)
		top right no-repeat;
}

.maindiv .maintable .table_wrap .cardContain .cardTable .delfont_span {
	font-size: 18px;
	color: #E20404;
	font-weight: bold;
}

.maindiv .maintable .table_wrap  .cardTools {
	width: 610px;
	margin: 20px auto;
	text-align: right;
}

.maindiv .maintable .table_wrap  .cardtools span label {
	font-family: "宋体";
	font-size: 14px;
}

.btn-text {
	height: 22px;
	width: 60px;
	margin-top: 2px;
}

.tableArea {
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
	border-bottom: 1px solid #E6E6E6;
	margin-top: 50px; *
	padding-bottom: 20px;
	/*padding: 20px 0;*/
	/*width: 800px;*/
	margin:0 auto;
	width: "100%"
}

table { *
	border-collapse: collapse;
	border-spacing: 0;
	table-layout: fixed;
}

caption {
	font-size: 12px;
	font-weight: 700;
	letter-spacing: 2px;
	height: 24px;
	line-height: 24px;
	text-align: left;
	color: #FC6210;
}

th,td {
	font-size: 12px;
	text-align: center;
}

th {
	font-weight: 400;
	letter-spacing: 1px;
}

.table_a {
	border-left: 1px solid #E7CA96;
	border-bottom: 1px solid #E7CA96;
}

.table_a td,.table_a th {
	border-top: 1px solid #E7CA96;
	border-right: 1px solid #E7CA96;
	padding: 4px 5px;
}

.table_a th,.table_c th {
	background: #FBF7EE;
}

.table_a strong.dd {
	color: #DA2B28;
}

.table_a strong.ww {
	color: #005aa0;
}

td.table_wrap {
	padding: 0;
}

td.table_wrap td,.table_wrap th,.table_wrap td {
	border-top: none;
	border-right: none;
}

/*
td {
	word-break: break-all;
	word-wrap: break-word;
}*/
#ordercenter-data-title
{width:1032px;*width:1040px;height:35px;line-height: 35px;border-bottom: #66CD00 2px solid;float: left;text-align: left;padding-left: 8px;color: #66CD00;}
 
</style>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
$(function() {
			var url = mainWeb + '/front/getDeliveryPriceList';
			$.post(url, function(result) {
						result = eval('(' + result + ')');
						if (result.result) {
							var array = result.returnObj;
							for (var i = 0; i < 6;) {
								var list;
								if (i < array.length) {
									list = array[i];
								} else {
									list = null;
								}
								i++;
								if (!array || !list) {
									$('#card0' + i).hide();
									continue;
								}
								$('#deliverySalesRecidInput' + i)
										.val(list.recid);
								$('#deliveryCount' + i).html(list.dcount);
								$('#deliveryPrice' + i).html(list.dprice);
							}
						} else {
							if (result.returnObj == 2) {
								location = mainWeb + '/pub/sub/login.jsp';
							} else if (result.returnObj == 0) {
								cmsAlertAtt(result.message);
							}
						}
					});
			initBalanceSpan();
			initCountPerMonth();
		});

function initBalanceSpan() {
	var url = mainWeb + '/front/getAccountInfo';
	$.post(url, function(result) {
				result = eval('(' + result + ')');
				if (result.result) {
					$('#moneyBalanceSpan').html('&yen;'
							+ result.returnObj.moneyBalance);
					$('#deliveryBalanceSpan')
							.html(result.returnObj.deliveryCountThisMonth);
				} else {
					if (result.returnObj == 2) {
						location = mainWeb + '/pub/sub/login.jsp';
					} else if (result.returnObj == 0) {
						cmsAlertAtt(result.message);
					}
				}
			});
}
function initCountPerMonth() {
	var url = mainWeb + '/front/getDeliveryListPerMonth';
	$.post(url, function(result) {
				result = eval('(' + result + ')');
				if (result.result) {
					var array = result.returnObj;
					for (var i = 0; i < 7; i++) {
						var value = array[i];
						if (!value||value=='null') {
							value = '--';
						}
						$('#deliveryCountTd' + i).html(value);
					}
				} else {
					if (result.returnObj == 2) {
						location = mainWeb + '/pub/sub/login.jsp';
					} else if (result.returnObj == 0) {
						cmsAlertAtt(result.message);
					}
				}
			});
}

function toSureBuyIt() {
	if (selectedIndex) {
		return true;
	} else {
		cmsAlertAtt('请选择您要购买的包月方案！');
		return false;
	}
}
$(function() {
			$("#card_Tab td").hover(function() {
						if ($(this).hasClass("click_Tab"))
							return;
						$(this).addClass("hover_Tab");
					}, function() {
						$(this).removeClass("hover_Tab");
					}).bind("click", function() {
				$(".maindiv .maintable .table_wrap .cardContain .cardTable .click_Tab")
						.removeClass("click_Tab");
				$(this).addClass("click_Tab");
			});
		});

var saveData = function(index) {
	selectedIndex = index;
	$('#deliveryIdInput').val($('#deliverySalesRecidInput' + index).val());
}
var selectedIndex;
		</script>
	</head>
	<body style="background-color: #FFFFFF" onselectstart="return false;">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id='chargecontent'>
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div class="main">
				<div class="breadcrumb">
					<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;包月送货</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='maindiv'>
					<div id="ordercenter-data-title" style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							包月送货
						</h3>
					</div>
					<div class='mainTabelArea'>
						<table class="maintable">
							<!-- tr>
								<th>
									<strong>包月送货</strong>
								</th>
							</tr> -->
							<tr>
								<td>
									<div id="sthMessagesDiv" style="font-size: 13px;">
										&nbsp;您的账户余额为：
										<span style="color: red; font-weight: bold;"
											id="moneyBalanceSpan"></span>元，本月剩余送货次数：
										<span style="color: red; font-weight: bold;"
											id="deliveryBalanceSpan"></span>次。
									</div>
								</td>
							</tr>
							<tr>
								<td class="table_wrap ">
									<div class='cardContain' unselectable="on"
										onselectstart="return false;">
										<table class="cardTable" id="card_Tab" cellspacing="5px">
											<tr>
												<td id='card01' class="normal_Tab" onclick="saveData(1)"
													id="">
													<input type="hidden" id="deliverySalesRecidInput1" />
													送货次数：
													<span id="deliveryCount1"></span>次
													<br />
													优惠价：
													<span class="delfont_span">&yen;</span>
													<span class="delfont_span" id="deliveryPrice1"></span>元
												</td>
												<td id='card02' class="normal_Tab" onclick="saveData(2)"
													id="">
													<input type="hidden" id="deliverySalesRecidInput2" />
													送货次数：
													<span id="deliveryCount2"></span>次
													<br />
													优惠价：
													<span class="delfont_span">&yen;</span>
													<span class="delfont_span" id="deliveryPrice2"></span>元
												</td>
												<td id='card03' class="normal_Tab" onclick="saveData(3)"
													id="">
													<input type="hidden" id="deliverySalesRecidInput3" />
													送货次数：
													<span id="deliveryCount3"></span>次
													<br />
													优惠价：
													<span class="delfont_span">&yen;</span>
													<span class="delfont_span" id="deliveryPrice3"></span>元
												</td>
											</tr>
											<tr>
												<td id='card04' class="normal_Tab" onclick="saveData(4)"
													id="">
													<input type="hidden" id="deliverySalesRecidInput4" />
													送货次数：
													<span id="deliveryCount4"></span>次
													<br />
													优惠价：
													<span class="delfont_span">&yen;</span>
													<span class="delfont_span" id="deliveryPrice4"></span>元
												</td>
												<td id='card05' class="normal_Tab" onclick="saveData(5)"
													id="">
													<input type="hidden" id="deliverySalesRecidInput5" />
													送货次数：
													<span id="deliveryCount5"></span>次
													<br />
													优惠价：
													<span class="delfont_span">&yen;</span>
													<span class="delfont_span" id="deliveryPrice5"></span>元
												</td>
												<td id='card06' class="normal_Tab" onclick="saveData(6)"
													id="">
													<input type="hidden" id="deliverySalesRecidInput6" />
													送货次数：
													<span id="deliveryCount6"></span>次
													<br />
													优惠价：
													<span class="delfont_span">&yen;</span>
													<span class="delfont_span" id="deliveryPrice6"></span>元
												</td>
											</tr>
										</table>
									</div>
									<div class="cardtools">
										<form action="<%=basePath%>/front/toSureBuyDelivery"
											onsubmit="return toSureBuyIt();">
											<input type='hidden' name="id" id="deliveryIdInput" />
											<span> <label>
													选择日期：
												</label> <select name="monthNo">
													<%
														int year = Calendar.getInstance().get(Calendar.YEAR);
														int month = Calendar.getInstance().get(Calendar.MONTH);
														// 从下一个月开始
														month += 1;
														for (int monthIndex = 0; monthIndex < 6; monthIndex++) {
															if (month < 12) {
																month += 1;
															} else if (month == 12) {
																month = 1;
																year += 1;
															}
															String timeLabel = year + "年" + month + "月";
															String timeValue = year + "" + month;
													%>
													<option value='<%=timeValue%>'><%=timeLabel%></option>
													<%
														}
													%>
												</select> </span>
											<span> <input type="submit" value="确定购买"
													class="btn-text" /> </span>
										</form>
									</div>
								</td>
							</tr>
						</table>
					</div>
					 <div class="tableArea">
						<table id="dealingLogTable" border="0"   style='margin: 0 auto; width: 690px;'
							cellspacing="0" cellpadding="0" class="table_a">
							<tr>
								<th style="border-left: 1px; font-size: 14px;">
									月份
								</th>
								<%
									int year1 = Calendar.getInstance().get(Calendar.YEAR);
									int month1 = Calendar.getInstance().get(Calendar.MONTH);
									// 从下一个月开始 
									for (int monthIndex = 0; monthIndex < 7; monthIndex++) {
										if (month1 < 12) {
											month1 += 1;
										} else if (month1 == 12) {
											month1 = 1;
											year1 += 1;
										}
										String timeLabel1 = year1 + "年" + month1 + "月";
								%>
								<th><%=timeLabel1%></th>
								<%
									}
								%>
							</tr>
							<tr>
								<td style='word-break: keep-all;width: 100px;' nowrap>
									剩余送货次数
								</td>
								<td id="deliveryCountTd0">

								</td>
								<td id="deliveryCountTd1">

								</td>
								<td id="deliveryCountTd2">

								</td>
								<td id="deliveryCountTd3">

								</td>
								<td id="deliveryCountTd4">

								</td>
								<td id="deliveryCountTd5">

								</td>
								<td id="deliveryCountTd6">

								</td>
							</tr>
						</table>
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