<%@page import="java.util.Calendar"%>
<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>���³�ֵ</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/paying.css">

		<style type="text/css">
.oldmain .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

.oldmaindiv {
	width: 1038px; *
	width: 1038px;
	float: right;
	border-bottom: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
	border-top: 1px solid #E6E6E6;
	border-left: 1px solid #E6E6E6;
}

.oldmaindiv .oldmaintable {
	width: 100%;
}

.oldmaindiv .oldmaintable th {
	height: 30px;
	width: 100%;
	background: url('<%=basePath%>/images/page/sort-bg05.png') repeat-x;
	alight: left;
	padding-left: 5px;
	text-align: left;
	font-family: "����";
	font-size: 14px;
	border-top: 1px solid #E6E6E6;
}

.oldmaindiv .oldmaintable .table_wrap {
	width: 100%;
	text-align: center;
	margin-top: 15px;
	margin-bottom: 15px;
}

.oldmaindiv .oldmaintable .table_wrap .cardContain {
	border: 1px solid #ccc;
	width: 610px;
	margin: 20px auto;
	text-align: center;
	-webkit-user-select: none;
	-moz-user-select: none;
}

.oldmaindiv .oldmaintable .table_wrap .cardContain .cardTable {
	font-size: 12px;
	color: #333;
	width: 600px;
	margin: 5px;
}

.oldmaindiv .oldmaintable .table_wrap .cardContain .cardTable .normal_Tab
	{
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

.oldmaindiv .oldmaintable .table_wrap .cardContain .cardTable .hover_Tab
	{
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #FF9900;
	background: url(<%=basePath%>/images/page/deliver_hover_bg.gif) repeat-x
		bottom;
}

.oldmaindiv .oldmaintable .table_wrap .cardContain .cardTable .click_Tab
	{
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #FF9900;
	background: url(<%=basePath%>/images/page/deliver_buycard_right_ico.gif)
		top right no-repeat;
}

.oldmaindiv .oldmaintable .table_wrap .cardContain .cardTable .delfont_span
	{
	font-size: 18px;
	color: #E20404;
	font-weight: bold;
}

.oldmaindiv .oldmaintable .table_wrap  .cardTools {
	width: 610px;
	margin: 20px auto;
	text-align: right;
}

.oldmaindiv .oldmaintable .table_wrap  .cardtools span label {
	font-family: "����";
	font-size: 14px;
}

.btn-text {
	height: 22px;
	width: 60px;
	margin-top: 2px;
}

#formTable {
	margin-left: 300px;
	margin-top: 10px;
	font-size: 13px;
}

#formTable tr {
	height: 25px;
}

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
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
$(function() {
			var form = $('#payForm');
			form.submit(function() {
						if (!beforeSubmit()) { 
							return false;
						}
						$.post(form.attr('action'), form.serialize(), function(
										result, status) {
									if (result.result) {
										window.location = basePath
												+ '/pro/member/delivercharge.jsp';
									} else {
										if(result.returnObj==2){
											location = mainWeb+'/pub/sub/login.jsp';
										}else if(result.returnObj==4){
											$('#password_input_error').html('<font color=red >&nbsp;֧�����벻��ȷ��</font>');
										}else if(result.returnObj==3){
											cmsConfirm("��ʾ",'����δ����֧�����룬���ȷ��ȥ�˻���ȫ��������֧������',function(result){
												if(result){
													location = '<%=basePath%>/pro/member/accountsafe.jsp';
												}
											});
										}else{
											cmsAlertAtt(result.message);
										}
									}
								}, 'json');
						return false;
					});
			initBalanceSpan();
			createCode();
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
function beforeSubmit() {
	var returnValue = true;
	var pass = $('#password_input').val();
	if ($.trim(pass) == '') {
		$('#password_input_error').html('<font color=red style="font-size:12px;">&nbsp;֧�����������д��</font>');
		returnValue =  false;
	}else{
		$('#password_input_error').html('');
	}
	if (!validate())// ��֤��֤��
	{
		returnValue = false;
	}
	return returnValue;
}		
function doSubmit(){
$('#payForm').submit();
}
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
			<div class="oldmain">
				<div class="breadcrumb">
					<span class="my7"><strong><a href="#">�ҵ�7��</a> </strong> </span><span>&nbsp;&gt;&nbsp;�����ͻ�</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='oldmaindiv'>
					<div>
						<table class="oldmaintable">
							<tr>
								<th>
									<strong>�����ͻ�</strong>
								</th>
							</tr>
							<tr>
								<td>
									<div id="sthMessagesDiv" style="font-size: 13px;">
										&nbsp;�����˻����Ϊ��
										<span style="color: red; font-weight: bold;"
											id="moneyBalanceSpan"></span>Ԫ������ʣ���ͻ�������
										<span style="color: red; font-weight: bold;"
											id="deliveryBalanceSpan"></span>�Ρ�
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div>
										<div class="w main">
											<div class="m m3" id="qpay">
												<div class="mc">
													<s class="icon-succ02"></s>
													<div class="fore">
														<h3 class="ftx-02">
															ʹ��ʱ�䣺
															<%=request.getAttribute("monthNo")%>
														</h3>
														<ul class="list-h">
															<li class="fore1">
																�ײ��ͻ�������
																<strong class="ftx-01"><%=request.getAttribute("dcount")%>��</strong>
															</li>
															<li class="fore2">
																�ײͼ۸�
																<strong class="ftx-01"><%=request.getAttribute("dprice")%>Ԫ</strong>
															</li>
														</ul>
														<p id="p_show_info">
															&nbsp;
														</p>
													</div>
												</div>
											</div>
											<div id="selectWayDiv" style="height: 210px">
												<div id="selectWayDivTitle">
													<div id="selectWayDivTitleLeft01"
														class="selectWayDivTitleLeft01"
														onclick="changePayCompany(1);">
														<font
															style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
															color="#FF6600"><b>���֧��</b> </font>
													</div>
													<div id="selectWayDivTitleRight" style="width: 868px;">
													</div>
												</div>
												<form action="<%=basePath%>/front/payForDelivery"
													id="payForm" method="post" onsubmit="beforeSubmit();">
													<div id="chargeValueDiv">
														֧�����룺
														<input type="password" id="password_input" name="password" />
														<a
															href="javascript:parent.location='<%=basePath%>/pro/member/accountsafe.jsp';"
															style="text-decoration: none;font-size: 12px;">��δ���ã�</a>
														<span id="password_input_error"></span>
														<div class="FieldRow">
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
														<input type="hidden" name="monthNo"
															value="<%=request.getAttribute("monthNoValue")%>" />
														<input type="hidden" name="id"
															value="<%=request.getAttribute("id")%>">
														<a href="javascript:void(0);" onclick="doSubmit();"><img
																src="<%=basePath%>/images/page/quickpay_02.png"
																style="border: 0px;" /> </a>
													</div>
												</form>
											</div>
											<div id="divBottom">
												&nbsp; ���֧���������ԣ�&nbsp;
												<a href="<%=basePath%>/pro/member/accountbalance.jsp">�鿴���</a>&nbsp;
												<a href="javascript:void(0);">֧������</a>
											</div>
										</div>

									</div>
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