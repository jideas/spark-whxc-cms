<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	String message = (String) request.getAttribute("message");
	String id = (String) request.getAttribute("id");
	String email = (String) request.getAttribute("email");
	String email_suf = email.substring(email.indexOf("@") + 1);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "">
<html>
	<head>
		<title>登录7号生活馆</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet" href="<%=basePath%>/scripts/dialog/cmsDialog.css"/>
		<script type="text/javascript" src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<link type="text/css"
			href="<%=basePath%>/pro/member/css/procedure.css" rel="stylesheet">
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"> 
$(document).ready(function() {
	var mess = '<%=message%>';
	$('#id_hidden_input').val('<%=id%>');
	if (mess && mess != 'null') {
		$('#smsCodeInput').css('border', 'red solid 2px');
		$('#smsCodeInput_suf').html('<font color=red >&nbsp;' + mess
				+ '</font>');
	}
});
function sendEmailCode() {
	var url = "<%=basePath%>/front/sendCheckEmail";
	$.post(url, {
				id : '<%=id%>'
			}, function(result) {
				result = eval('(' + result + ')');
				if(result.result){
					$('#CheckByEmail').css('display', 'none');
					$('#selectChecktypeDiv').css('display', 'none');
					$('#emailMessageDiv').css('display', ''); 
				}else{
					cmsAlertAtt('邮件发送失败，请稍后重试');
				}
			});
}

function doNextStep() {
	if (!validateForm()) {
		return;
	}
	var form = $('#getPasswordForm');
	form.attr("action", mainWeb + "/front/checkSms");
	form.submit();
}

function validateForm() {
	var returnValue = true;
	var userName = $('#smsCodeInput').val();
	if ($.trim(userName) == '') {
		$('#smsCodeInput').css('border', 'red solid 2px');
		$('#smsCodeInput_suf').html('<font color=red >&nbsp;必须填写！</font>');
		returnValue = false;
	} else {
		$('#smsCodeInput').css('border', '#ABADB3 solid 1px');
		$('#smsCodeInput_suf').html('');
	}
	return returnValue;
}
function checkTypeChanged() {
	var value = $('#checkType').val();
	if (0 == value) {
		$('#CheckByMobile').css('display', '');
		$('#CheckByEmail').css('display', 'none');
	} else if (1 == value) {
		$('#CheckByMobile').css('display', 'none');
		$('#CheckByEmail').css('display', '');
	}
}

var canSendSmsCode = true;

function sendSmsCode() {
	if (!canSendSmsCode) {
		return;
	}
	var url = mainWeb + '/front/sendSms';
	$.post(url, {
				id : '<%=request.getAttribute("id")%>'
			}, function(result) {
				if (result.result) {
					document.getElementById("getSmsCodeDiv").style.backgroundImage = 'url('
							+ mainWeb + '/images/page/get_sms_code2.png)';
					$('#getSmsCodeDiv')
							.html('<font color=gray>&nbsp;180秒后重新发送!</font>');
					cmsAlertAtt('验证码已发送，请查收短信!');
					$('#smsCodeInput').attr('disabled', false);
					canSendSmsCode = false;
					doTimer();
				} else {
					cmsAlertAtt(result.message);
					canSendSmsCode = true;
				}
			}, "json");
}

function doTimer() {
	var time = 180;
	var m = setInterval(function() {
		time = time - 1;
		$('#getSmsCodeDiv').html('<font color=gray>&nbsp;' + time
				+ '秒后重新发送!</font>');
		if (time == 0) {
			clearInterval(m);
			$('#smsCodeInput').attr('disabled', true);
			document.getElementById("getSmsCodeDiv").style.backgroundImage = 'url('
					+ mainWeb + '/images/page/get_sms_code.png)';
			$('#getSmsCodeDiv').html('获取短信验证码');
			canSendSmsCode = true;
		}
	}, 1000);
}
function gotoLoginMail() {
	this.parent.location = 'http://mail.<%=email_suf%>' ;
}
		</script>
		<style type="text/css">
#loginPageMainDiv {
	width: 1200px;
	height: 280px;
	margin: 0 auto;
	text-align: left;
}

#mainRegDiv {
	width: 1198px;
	height: 280px;
	border: #D1D1D1 solid 1px;
	border-bottom: 0px;
}

#topWholeDiv {
	width: 1198px;
	background-image: url('<%=basePath%>/images/page/page_top1.png');
	vertical-align: middle;
	height: 31px;
	line-height: 31px;
}

#topLeftDiv {
	margin-left: 10px;
	margin-top: -2px;
	float: left;
	display: inline;
	float: left;
}

#topRightDiv {
	display: inline;
	float: right;
}

#contantRegDiv {
	width: 620px;
	height: 248px;
	margin-left: 300px;
	text-align: left;
	margin-top: 10px;
}

.FieldRow {
	width: 550px;
	height: 33px;
	line-height: 33px;
	margin-left: 70px;
	font-size: 12px;
	margin-top: 8px; *
	margin-top: 4px;
}

#getSmsCodeDiv {
	cursor: pointer;
	background-image: url('<%=basePath%>/images/page/get_sms_code.png');
	width: 121px;
	height: 25px;
	display: inline;
	float: right;
	line-height: 25px;
	margin-right: 200px; *
	margin-top: -30px;
	text-align: center;
}

#getEmailCodeDiv {
	cursor: pointer;
	background-image: url('<%=basePath%>/images/page/get_sms_code.png');
	width: 121px;
	height: 25px;
	line-height: 25px;
	margin-left: 108px;
	text-align: center;
}

#CheckByMobile { *
	margin-top: -20px;
}
</style>
	</head>
	<body style="background-color: #FFFFFF">
		<div id="loginPageMainDiv">
			<div>
				<a href="<%=webURLContext%>"> <img width="240" height="62"
						src="<%=basePath%>/images/page/logo.png" border="0px"> </a>
			</div>
			<div id="mainRegDiv">
				<div id="topWholeDiv">
					<div id="topLeftDiv">
						<b style="font-size: 13px;">找回密码</b>
					</div>
					<div id="topRightDiv">
					</div>
					</font>
				</div>
				<div id="step2" class="step">
					<ul>
						<li class="fore1">
							1.填写账户名
							<b></b>
						</li>
						<li class="fore2">
							2.验证身份
							<b></b>
						</li>
						<li class="fore3">
							3.设置新密码
							<b></b>
						</li>
						<li class="fore4">
							4.完成
						</li>
					</ul>
				</div>
				<div id="contantRegDiv">
					<div id="selectChecktypeDiv" class="FieldRow">
						请选择验证身份方式：
						<select id="checkType" onchange="checkTypeChanged();"
							style="width: 260px; color: gray;">
							<option value="0" selected="selected">
								手机验证
							</option>
							<option value="1">
								邮箱验证
							</option>
						</select>
					</div>
					<div id="CheckByMobile">
						<form id="getPasswordForm" method="post">
							<div class="FieldRow">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								用户名：&nbsp;
								<font color="#FF6600"><%=request.getAttribute("username")%></font>
								<input type="hidden" name="id" value="" id="id_hidden_input" />
							</div>
							<div class="FieldRow">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 已验证手机：&nbsp;
								<font color="#FF6600"><%=request.getAttribute("mobile")%></font>&nbsp;&nbsp;
								<div id="getSmsCodeDiv" onclick="sendSmsCode();">
									<font style="font-size: 12px;">获取短信验证码</font>
								</div>
							</div>
							<div class="FieldRow">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;短信验证码 ：&nbsp;
								<input type="text" id="smsCodeInput" name="checkcode"
									style="width: 108px;" disabled="disabled" />
								<span id="smsCodeInput_suf"></span>
							</div>
							<div class="FieldRow">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<img src="<%=basePath%>/images/page/next_step.png"
									style="cursor: pointer;" onclick="doNextStep();" />
							</div>
						</form>
					</div>
					<div id="CheckByEmail" style="display: none">
						<div class="FieldRow">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							用户名：&nbsp;
							<font color="#FF6600"><%=request.getAttribute("username")%></font>
						</div>
						<div class="FieldRow">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号：&nbsp;
							<font color="#FF6600"><%=request.getAttribute("mobile")%></font>&nbsp;&nbsp;
						</div>
						<div class="FieldRow">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱账号：&nbsp;
							<font color="#FF6600"><%=request.getAttribute("email")%></font>&nbsp;&nbsp;
						</div>
						<div class="FieldRow">
							<div id="getEmailCodeDiv" onclick="sendEmailCode();">
								<font style="font-size: 12px;">发送验证邮件</font>
							</div>
						</div>
					</div>
					<div id="emailMessageDiv" style="display: none;margin-top: 70px;">
						<div style="display: inline; float: left;">
							<img src="<%=basePath%>/images/page/success01.png" />
						</div>
						<div style="display: inline; float: left; line-height: 20px;">
							<font color="black" style="font-size: 12px;">&nbsp;&nbsp;验证邮件已发送，请您</font><a
								href="#" onclick="gotoLoginMail();" style="font-size: 12px;">登录邮箱</a><font
								color="black" style="font-size: 12px;">完成验证</font> &nbsp;&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
