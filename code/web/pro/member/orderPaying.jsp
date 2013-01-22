<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
	<head>
		<title>订单提交成功-选择支付方式</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/orderPaying.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<script type="text/javascript">
			$(function() {
			var payType = '<%=request.getParameter("payType")%>';
			if(payType&&'null'!=payType&&''!=payType&&'2'==payType){
				changePayCompany(1);
			}
			$.post(mainWeb + '/front/payment/getOrderInfo', {
						ids : '<%=request.getParameter("orderIds")%>'
					}, function(result) { 
						if (result.result) {
							var array = result.returnObj;
							$('#balance_box').html(array[0]);
							$('#vanteges_box').html(array[1]);
						} else {
							if (result.returnObj == 0) {
								parent.location = mainWeb + '/pub/sub/login.jsp';
							} else {
								cmsAlertAtt(result.message);
							}
						}
					}, 'json');
	var form = $('#payForm');
	$('#payForm').submit(function() { 
		if (!beforeSubmit()) { 
			return false;
		} 
		$.post(form.attr('action'), form.serialize(), function(result) {
			if (result.result) {
				var orderIds = '<%=request.getParameter("orderIds")%>';
				if(orderIds.length>33){
					parent.location = mainWeb +"/front/order/getOrders";
				}else{
					parent.location = mainWeb
						+ '/front/order/getOrderInfo?orderId='+orderIds;
				}
			} else {createCode();
				if (result.returnObj == 2) {
					location = mainWeb + '/pub/sub/login.jsp';
				} else if (result.returnObj == 4) {
					$('#password_input_error')
							.html('<font color=red >&nbsp;支付密码不正确！</font>');
				} else {
					cmsAlertAtt(result.message);
				}
			}
		}, 'json');
		return false;
	});
});

function beforeSubmit() {
	var returnValue = true;
	var pass = $('#password_input').val();
	if ($.trim(pass) == '') {
		$('#password_input_error')
				.html('<font color=red style="font-size:12px;">&nbsp;支付密码必须填写！</font>');
		returnValue = false;
	} else {
		$('#password_input_error').html('');
	}
	if (!validate())// 验证验证码
	{
		returnValue = false;
	}
	return returnValue;
}
var selectedBankStr = 'icbc';
var isSelectedAlipay = 2;
function selectBank(value) {
	selectedBankStr = value;
	document.getElementById('RadioGroup1_' + value).checked = 'true';
}
function changePayCompany(value) { 
	if (value==1) {
		document.getElementById('selectWayDivTitleLeft01').style.backgroundColor = '#FFFDEE';
		document.getElementById('selectWayDivTitleLeft01').style.borderBottom = '#FFFDEE solid 1px';
		document.getElementById('selectWayDivTitleLeft02').style.backgroundColor = '#FFFFFF';
		document.getElementById('selectWayDivTitleLeft02').style.borderBottom = '#EDD28B solid 1px'; 
		document.getElementById('selectWayDivTitleLeft02').style.cursor = 'pointer';
		document.getElementById('selectWayDivTitleLeft01').style.cursor = 'default';
		document.getElementById('aliPayDiv').style.display = 'none';
		document.getElementById('balancePayDiv').style.display = 'none';
		document.getElementById('unionPayDiv03').style.display = '';
		document.getElementById('unionPayDiv04').style.display = '';
		document.getElementById('unionPayDiv05').style.display = '';
		document.getElementById('unionPayDiv06').style.display = '';
		document.getElementById('selectWayDiv').style.height = 315;
		var hhh = 560;
		if (document.all) {
			hhh = hhh +25;
		}
		parent.document.getElementById('loginContent').style.height = hhh;
		isSelectedAlipay = 1;
	} else if (value==2){
		document.getElementById('selectWayDivTitleLeft02').style.backgroundColor = '#FFFDEE';
		document.getElementById('selectWayDivTitleLeft02').style.borderBottom = '#FFFDEE solid 1px';
		document.getElementById('selectWayDivTitleLeft01').style.backgroundColor = '#FFFFFF';
		document.getElementById('selectWayDivTitleLeft01').style.borderBottom = '#EDD28B solid 1px';
		document.getElementById('selectWayDivTitleLeft01').style.cursor = 'pointer';
		document.getElementById('selectWayDivTitleLeft02').style.cursor = 'default';
		document.getElementById('unionPayDiv03').style.display = 'none';
		document.getElementById('unionPayDiv04').style.display = 'none';
		document.getElementById('unionPayDiv05').style.display = 'none';
		document.getElementById('unionPayDiv06').style.display = 'none';
		document.getElementById('balancePayDiv').style.display = '';
		document.getElementById('selectWayDiv').style.height = 230; 
		var hhh = 475; 
		parent.document.getElementById('loginContent').style.height = hhh;
		isSelectedAlipay = 2;
		if (document.all) {
			hhh = hhh - 5;
		}
	}else {
		document.getElementById('selectWayDivTitleLeft02').style.backgroundColor = '#FFFDEE';
		document.getElementById('selectWayDivTitleLeft02').style.borderBottom = '#FFFDEE solid 1px';
		document.getElementById('selectWayDivTitleLeft01').style.backgroundColor = '#FFFFFF';
		document.getElementById('selectWayDivTitleLeft01').style.borderBottom = '#EDD28B solid 1px';
		document.getElementById('unionPayDiv03').style.display = 'none';
		document.getElementById('unionPayDiv04').style.display = 'none';
		document.getElementById('unionPayDiv05').style.display = 'none';
		document.getElementById('unionPayDiv06').style.display = 'none';
		document.getElementById('aliPayDiv').style.display = '';
		document.getElementById('selectWayDiv').style.height = 170; 
		var hhh = 415; 
		parent.document.getElementById('loginContent').style.height = hhh;
		isSelectedAlipay = 3;
	}
}

function doSubmit() {
	if (isSelectedAlipay==2) {
		$('#payForm').submit();
		return ;
	}
	if (!validatePayForm()) {
		return;
	}
	var form = $('#toPayPageForm');
	var bank = selectedBankStr;
	if (isSelectedAlipay==0) {
		bank = 'alipay';
	}
	$('#bank_input').val(bank);
	form.attr('action', mainWeb + '/front/payment/payOrder');

	var cc = new RealConfirm("支付", '请您在新打开的网上银行页面进行支付，<br>支付完成前请不要关闭该窗口。',
			'已完成付款', "重选付款方式", 'att');
	cc.setActionListener(function(result) {
				if (result) {
					var orderIds = '<%=request.getParameter("orderIds")%>';
					if(orderIds.length>33){
						parent.location = mainWeb +"/front/order/getOrders";
					}else{
						parent.location = mainWeb
							+ '/front/order/getOrderInfo?orderId='+orderIds;
					}
				}
			});
	form.submit();
}
function validatePayForm() {
	var renturnValue = true;
	if (!$('#RadioGroup1_1') && !$('#RadioGroup1_2')) {
		cmsAlertAtt('请先选一种支付方式！');
		renturnValue = true
	}
	return renturnValue;
}
		</script>
		<style type="text/css">
#checkCode {
	cursor: hand;
	margin-top: 2px;
	margin-left: 5px;
	float: left;
	display: inline;
	width: 65px;
	height: 15px;
	line-height: 15px;
	color: red;
	font-size: 16px;
	font-family: Arial;
	font-style: italic;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
	background-image: url('<%=basePath%>/images/page/checkCodeBg.png');
	letter-spacing: 3px;
	font-weight: bolder;
	margin-left: 5px;
	color: red
}

.FieldRow {
	height: 33px;
	line-height: 33px;
	margin-top: 28px;
	margin-left: 25px;
	margin-bottom: -10px;
}
</style>
	</head>

	<body style="background-color: #FFFFFF;" onload="createCode();">
		<div class="w" id="headers" style="width: 1200px; height: 90px;">
			<div style="vertical-align: middle; display: inline; float: left;">
				<img src="<%=basePath%>/images/page/logo.png"
					style="cursor: pointer;" title="去首页"
					onclick="javascript:parent.location=mainWeb;">
			</div>
			<div id="step1" class="step" style="margin-top: 40px;">
				<ul>
					<li class="fore1">
						1.选择支付方式
						<b></b>
					</li>
					<li class="fore2">
						2.支付平台付款
						<b></b>
					</li>
					<li class="fore3">
						3.支付结果信息
					</li>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
		<div class="w main" id="payingcontentdiv" style="width: 1200px;">
			<div class="m m3" id="qpay">
				<div class="mc">
					<s class="icon-succ02"></s>
					<div class="fore">
						<h3 class="ftx-02">
							订单提交成功，请您尽快付款！
						</h3>
						<ul class="list-h">
							<li class="fore1">
								订单号：
								<strong class="ftx-01" id="balance_box"></strong>
							</li>
							<li class="fore3">
								应付金额：
								<strong class="ftx-01" id="vanteges_box"></strong>&nbsp;元
							</li>
						</ul>
						<p id="p_show_info">
							&nbsp;
						</p>
					</div>
				</div>
			</div>
			<div id="selectWayDiv" style="height: 230px; margin-left: 120px;">
				<div id="selectWayDivTitle">
					<div id="selectWayDivTitleLeft02" class="selectWayDivTitleLeft01"
						onselectstart="return false" onclick="changePayCompany(2);">
						<font
							style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
							color="#FF6600"><b>余额支付</b> </font>
					</div>
					<div id="selectWayDivTitleLeft01" class="selectWayDivTitleLeft02"
						onselectstart="return false" onclick="changePayCompany(1);">
						<font
							style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
							color="#FF6600"><b>银联支付</b> </font>
					</div>
					<!-- 
					<div id="selectWayDivTitleLeft02" class="selectWayDivTitleLeft02"
						onclick="changePayCompany(0);">
						<font
							style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
							color="#FF6600"><b>支付宝</b> </font>
					</div> -->
					<div id="selectWayDivTitleRight">
					</div>
				</div>
				<div class="allWaysDiv" id="unionPayDiv03" style="display: none;">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('icbc');" id="RadioGroup1_icbc"
								name="RadioGroup1" checked="checked" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('icbc');" alt="工商银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/icbc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('abc');" id="RadioGroup1_abc"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('abc');" alt="农业银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/abc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('ccb');" id="RadioGroup1_ccb"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('ccb');" alt="建设银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/ccb.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('bcom');" id="RadioGroup1_bcom"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('bcom');" alt="交通银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/bcom.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="unionPayDiv04" style="display: none;">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('cmb');" id="RadioGroup1_cmb"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('cmb');" alt="招商银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/cmb.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('boc');" id="RadioGroup1_boc"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('boc');" alt="中国银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/boc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('cib');" id="RadioGroup1_cib"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('cib');" alt="兴业银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/cib.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('ceb');" id="RadioGroup1_ceb"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('ceb');" alt="光大银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/ceb.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="unionPayDiv05" style="display: none;">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('post');" id="RadioGroup1_post"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('post');" alt="邮政银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/post.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('sdb');" id="RadioGroup1_sdb"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('sdb');" alt="深发银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/sdb.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('citic');" id="RadioGroup1_citic"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('citic');" alt="中信银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/citic.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('gdb');" id="RadioGroup1_gdb"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('gdb');" alt="广发银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/gdb.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="unionPayDiv06" style="display: none;">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('cmbc');" id="RadioGroup1_cmbc"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('cmbc');" alt="民生银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/cmbc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('pab');" id="RadioGroup1_pab"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('pab');" alt="平安银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/pab.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="aliPayDiv" style="display: none;">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio" checked="checked" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										alt="支付宝" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/zhifu.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="balancePayDiv"
					style="margin-left: -37px; height: 110px;">
					<ul>
						<li>
							<form action="<%=basePath%>/front/payForOrders" id="payForm"
								method="post">
								<div id="chargeValueDiv">
									支付密码：
									<input type="password" id="password_input" name="password" />
									<input type="hidden" name="ids" id="value_input"
										value="<%=request.getParameter("orderIds")%>">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:parent.location='<%=basePath%>/pro/member/accountsafe.jsp';" style="text-decoration: none;">还未设置？</a>
									<span id="password_input_error"></span>
									<div class="FieldRow" style="margin-left: 5px;">
										<div style="display: inline; float: left;">
											<font style="font-size: 14px">验证码：</font>
											<input type="text" id="checkNum" style="width: 150px;" />
										</div>
										<div id="checkCode" onclick="createCode();"></div>
										<font style="font-size: 13px; color: #999999">看不清?</font>
										<a href="javascript:void(0);" onclick="createCode();"><font
											style="font-size: 13px;">换一张</font> </a>
									</div>
								</div>
							</form>
						</li>
					</ul>
				</div>
				<div id="ChooseOkButtonDiv">
					<form action="" method="post" id="toPayPageForm" target="_blank">
						<a href="javascript:void(0);" onclick="doSubmit();"><img
								src="<%=basePath%>/images/page/quickpay_02.png"
								style="border: 0px;" /> </a>
						<input type="hidden" name="bank" id="bank_input" />
						<input type="hidden" name="value" id="value_input"
							value="<%=request.getParameter("orderIds")%>">
					</form>
				</div>
			</div>
			<div id="divBottom">
				&nbsp; 完成支付后，您可以：&nbsp;
				<a
					href="javascript:parent.location = '<%=basePath%>/front/order/getOrderInfo?orderId=<%=request.getParameter("orderIds")%>';">查看订单详情</a>&nbsp;
				<a href="javascript:void(0);">支付调查</a>
			</div>
		</div>
	</body>
</html>
