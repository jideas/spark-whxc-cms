<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>包月充值</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet" href="<%=basePath%>/scripts/dialog/cmsDialog.css"/>
		<script type="text/javascript" src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
<script type="text/javascript">
	function doSubmitForm(){
		if (!validateForm()) {
			return;
		}
		var form = $('#doChargeForm');
		form.attr("action", mainWeb + "/front/doCharge");
		form.submit();
	}
	$(function (){ 
		var form = $('#doChargeForm');
		form.submit(function() {
			$.post(form.attr('action'), form.serialize(), function(result, status) {
						if (result.result) { 
							var cc = new RealConfirm("提示", result.message+"点击确定继续充值。",'继续','取消','ok');
							cc.setActionListener(function(data) {
								if(data){
									form[0].reset();
									createCode();
								}else{
									location = mainWeb+'/pro/member/accountbalance.jsp';
								}
							}); 
						} else {
						 	createCode();
							cmsAlertAtt(result.message);
						}
					}, 'json');
			return false;
		});
	});

	function validateForm(){
		var returnValue = true;
		var cardNO = $('#cardNo_input').val();
		if ($.trim(cardNO) == '') {
			$('#cardNo_input').css('border', 'red solid 2px');
			$('#cardNo_input_suf').html('<font color=red >&nbsp;卡号必须填写！</font>');
			returnValue = false;
		} else if (cardNO) {
			$('#cardNO_input').css('border', '#ABADB3 solid 1px');
			$('#cardNO_input_suf').html('');
		}
		var pass0 = $('#password_input').val();
		if ($.trim(pass0) == '') {
			$('#password_input').css('border', 'red solid 2px');
			$('#password_input_suf')
					.html('<font color=red >&nbsp;登录密码必须填写！</font>');
			returnValue = false;
		} else if (pass0) {
			$('#password_input').css('border', '#ABADB3 solid 1px');
			$('#password_input_suf').html('');
		}
		if (!validate())// 验证验证码
		{
			returnValue = false;
		}
		return returnValue;
	}
	var addKeyDownListener = function() {  
				$('#cardNo_input').keydown(function(event) { 
					if (event.which == 13) {
						$("#password_input").focus();
					}
				});
				$('#password_input').keydown(function(event) {
					if (event.which == 13) {
						$("#checkNum").focus();
					}
				});
				$('#checkNum').keydown(function(event) {
					if (event.which == 13) {
						doSubmitForm();
					}
				});
			};		
		$(function(){ 
			addKeyDownListener();
			$("#cardNo_input").focus();
		});
</script>
		<style type="text/css">
.main .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

.maindiv {
	width: 1040px;
	float: right;
}

.maindiv .maintable {
	width: 100%;
	border-left: 1px solid #E6E6E6;
	border-bottom: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
	border-top: 1px solid #E6E6E6;
	font-size: 12px;
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

.maindiv .maintable .table_wrap .payment {
	margin: 15px auto;
	height: 280px;
}

.maindiv .maintable .table_wrap table td {
	font-size: 12px;
	height: 30px;
}

button {
	padding: 0;
	height: 23px;
	vertical-align: middle;
	font-size: 12px;
	line-height: 20px;
	cursor: pointer;
	margin: 0 4px;
}

.btn-text {
	/*background: url(http://jd2008.360buy.com/JdHome/Misc/skin/i/btn0420.gif);*/
	/*border:none;*/
	height: 22px;
	width: 60px;
	margin-top: 2px;
}
#ordercenter-data-title
{width:1032px;*width:1040px;height:35px;line-height: 35px;border-bottom: #66CD00 2px solid;float: left;text-align: left;padding-left: 8px;color: #66CD00;}
 
#checkCode {
	cursor: hand;
	margin-top: 2px;
	margin-left: 5px;
	/*float: left;*/
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
/*面值卡充值文本框*/
.payment form#doChargeForm input#cardNo_input,
.payment form#doChargeForm input#password_input,
.payment form#doChargeForm input#checkNum{
	border: 1px solid #CCCCCC;
	width:200px;
	height:22px;
	line-height:22px; 
}
</style>
	</head>
	<body  style="background-color: #FFFFFF" onload="createCode();" onselectstart="return false;">
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
					<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;面值卡充值</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='maindiv'>
					<div id="ordercenter-data-title" style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							面值卡充值
						</h3>
					</div>
					<table class="maintable">
						<!--  tr>
							<th colspan="2" align="left"
								style="background-image: url('<%=basePath%>/images/page/sort-bg05.png');">
								<strong>面值卡充值</strong>
							</th>
						</tr>-->
						<tr>
							<td class="table_wrap" width="100%">
								<div class="payment"><form id="doChargeForm" method="post">
									<table width="100%">
										<tr>
											<td align="right" style="height: 21px">
												卡号：
											</td>
											<td style="height: 21px">
												<input name="cardNo" type="text" id="cardNo_input" /><span id="cardNo_input_suf"></span>
											</td>
										</tr>
										<tr>
											<td align="right" style="height: 21px">
												密码：
											</td>
											<td style="height: 21px">
												<input name="password" type="password"
													id="password_input" /><span id="password_suf"></span>
											</td>
										</tr>
										<tr>
											<td align="right">
												验证码：
											</td>
											<td>
												<!-- input name="txtTenpayOrderId" type="text"	id="txtTenpayOrderId" /> -->
												<div>
													<input type="text" id="checkNum" style='display: inline;' />
													<div id="checkCode" onclick="createCode();"
														style='display: inline;'></div>
													<font
														style="font-size: 13px; display: inline; color: #999999">看不清?</font>
													<a href="javascript:void(0);" onclick="createCode();"><font
														style="font-size: 13px; display: inline;">换一张</font> </a>
												</div>
											</td>

										</tr>
										<tr>
											<td align='right'>
											</td>
											<td>
												<input type="button" value="确定充值" onclick="doSubmitForm();" class="btn-text" />
											</td>
										</tr>
									</table>
									</form>
								</div>
							</td>
						</tr>
					</table>
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