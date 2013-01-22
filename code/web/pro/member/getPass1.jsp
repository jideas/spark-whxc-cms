<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	String message = (String)request.getAttribute("message");
String lastUsername = (String)request.getAttribute("lastUsername");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "">
<html>
	<head>
		<title>登录7号生活馆</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link type="text/css" href="<%=basePath%>/pro/member/css/getPass1.css"
			rel="stylesheet">
		<link type="text/css"
			href="<%=basePath%>/pro/member/css/procedure.css" rel="stylesheet">
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var mes = '<%=message%>';
			var lastUsername = '<%=lastUsername%>';
			if(mes&&mes!='null'){
				$('#userName_input').css('border', 'red solid 2px');
				$('#userName_input').val(lastUsername);
				$('#userName_input_suf').html('<font color=red >&nbsp;'+mes+'</font>');
			}
		});
		var oldValue = '用户名/邮箱/已验证手机';
function userFocus() {
	var value = $('#userName_input').val();
	if (value == oldValue) {
		$('#userName_input').val(' ');
	}
	$('#userName_input').css('color', '#000000');
}

function userBlur() {
	var value = $('#userName_input').val();
	if ($.trim(value) == '') {
		$('#userName_input').val(oldValue);
		$('#userName_input').css('color', 'gray');
	}
}
function doNextStep(){
	if (!validateForm()) {
		return;
	}
	var form = $('#getPasswordForm');
	form.attr("action", mainWeb+"/front/getPass1");
	form.submit();  
}

function validateForm() {
	var returnValue = true;
	var userName = $('#userName_input').val();
	if ($.trim(userName) == '' || $.trim(userName) == oldValue) {
		$('#userName_input').css('border', 'red solid 2px');
		$('#userName_input_suf').html('<font color=red >&nbsp;必须填写！</font>');
		returnValue = false;
	} else {
		$('#userName_input').css('border', '#ABADB3 solid 1px');
		$('#userName_input_suf').html('');
	}
	if (!validate())// 验证验证码
	{
		returnValue = false;
	}
	return returnValue;
}
		</script>
	</head>
	<body onload="createCode();" style="background-color: #FFFFFF">
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
				<div id="step1" class="step">
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
					<form id="getPasswordForm" method="post">
						<div class="FieldRow">
							<font color="red">*</font>账户名：
							<input type="text" id="userName_input" name="username"
								onblur="userBlur();" onfocus="userFocus();"
								style="width: 260px; color: gray;" value="用户名/邮箱/已验证手机" />
							<span id="userName_input_suf"></span>
						</div>
						<div class="FieldRow">
							<div style="display: inline; float: left;">
								<font style="font-size: 12px">&nbsp;验证码：</font>
								<input type="text" id="checkNum" style="width: 150px;" />
							</div>
							<div id="checkCode" onclick="createCode();"></div>
							<font style="font-size: 13px; color: #999999">看不清?</font>
							<a href="javascript:void(0);" onclick="createCode();"><font
								style="font-size: 13px;">换一张</font> </a>
						</div>
						<div class="FieldRow">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="<%=basePath%>/images/page/next_step.png"
								style="cursor: pointer;" onclick="doNextStep();" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
