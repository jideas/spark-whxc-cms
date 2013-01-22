<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
	<head>
		<title>�����ύ�ɹ�-ѡ��֧����ʽ</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/paying.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
$(function() {
	$.post(mainWeb + '/front/payment/getOrderInfo', {
				ids : '<%=request.getParameter("orderIds")%>'
			}, function(result) {
				if (result.result) {
					var array = result.returnObj;
					$('#balance_box').html(array[0]);
					$('#vanteges_box').html(array[1]);
				} else {
					if (result.returnObj == 0) {
						location = mainWeb + '/pub/sub/login.jsp';
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
				parent.location = mainWeb
						+ '/front/order/getOrderInfo?orderId=<%=request.getParameter("orderIds")%>';
			} else {createCode();
				if (result.returnObj == 2) {
					location = mainWeb + '/pub/sub/login.jsp';
				} else if (result.returnObj == 4) {
					$('#password_input_error')
							.html('<font color=red >&nbsp;֧�����벻��ȷ��</font>');
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
				.html('<font color=red style="font-size:12px;">&nbsp;֧�����������д��</font>');
		returnValue = false;
	} else {
		$('#password_input_error').html('');
	}
	if (!validate())// ��֤��֤��
	{
		returnValue = false;
	}
	return returnValue;
}
function doSubmit() {
	$('#payForm').submit();
}
		</script>

		<style type="text/css">
.FieldRow {
	height: 33px;
	margin-top: 28px;
	margin-left: 25px;
	margin-bottom: -10px;
}

#checkCode {
	cursor: hand;
	margin-top: 2px;
	margin-left: 5px;
	float: left;
	display: inline;
	width: 65px;
	height: 15px;
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
}
</style>
	</head>

	<body style="background-color: #FFFFFF" onselectstart="return false;"
		onload="createCode();">
		<div class="w" id="headers" style="width: 1200px;">
			<div style="vertical-align: middle; display: inline; float: left;">
			</div>
			<div id="step1" class="step">
				<ul>
					<li class="fore1">
						1.ѡ��֧����ʽ
						<b></b>
					</li>
					<li class="fore2">
						2.֧��ƽ̨����
						<b></b>
					</li>
					<li class="fore3">
						3.֧�������Ϣ
					</li>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
		<div class="w main" style="width: 1200px;">
			<div class="m m3" id="qpay">
				<div class="mc">
					<s class="icon-succ02"></s>
					<div class="fore">
						<h3 class="ftx-02">
							�����ύ�ɹ����������츶�
						</h3>
						<ul class="list-h">
							<li class="fore1">
								�����ţ�
								<strong class="ftx-01" id="balance_box"></strong>
							</li>
							<li class="fore3">
								Ӧ����
								<strong class="ftx-01" id="vanteges_box"></strong>
							</li>
						</ul>
						<p id="p_show_info">
							&nbsp;
						</p>
					</div>
				</div>
			</div>
			<div id="selectWayDiv" style="height: 210px; margin-left: 120px;">
				<div id="selectWayDivTitle">
					<div id="selectWayDivTitleLeft01" class="selectWayDivTitleLeft01"
						onclick="changePayCompany(1);">
						<font
							style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
							color="#FF6600"><b>���֧��</b> </font>
					</div>
					<div id="selectWayDivTitleRight" style="width: 868px;">
					</div>
				</div>
				<form action="<%=basePath%>/front/payForOrders" id="payForm"
					method="post">
					<div id="chargeValueDiv">
						֧�����룺
						<input type="password" id="password_input" name="password" />
						<span id="password_input_error"></span>
						<div class="FieldRow" style="margin-left: 5px;">
							<div style="display: inline; float: left;">
								<font style="font-size: 14px">��֤�룺</font>
								<input type="text" id="checkNum" style="width: 150px;" />
							</div>
							<div id="checkCode" onclick="createCode();"></div>
							<font style="font-size: 13px; color: #999999">������?</font>
							<a href="javascript:void(0);" onclick="createCode();"><font
								style="font-size: 13px;">��һ��</font> </a>
						</div>
					</div>
					<div id="ChooseOkButtonDiv">
						<input type="hidden" name="ids"
							value="<%=request.getParameter("orderIds")%>">
						<img src="<%=basePath%>/images/page/quickpay_02.png"
						style="border: 0px;cursor:pointer;" onclick="doSubmit();" />
					</div>
				</form>
			</div>
			<div id="divBottom">
				&nbsp; ���֧���������ԣ�&nbsp;
				<a href="<%=basePath%>/pro/member/accountbalance.jsp">�鿴���</a>&nbsp;
				<a href="javascript:void(0);">֧������</a>
			</div>
		</div>

	</body>
</html>
