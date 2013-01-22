<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	Object reUrl = request.getAttribute("url");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "">
<html>
	<head>
		<title>登录7号生活馆</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<script type="text/javascript">
		var addKeyDownListener = function() {  
				$('#userName_input').keydown(function(event) { 
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
						doLogin();
					}
				});
			};		
		$(function(){ 
			addKeyDownListener();
			$("#username_input").focus();
		});
		</script>
	</head>
	<body onload="createCode();" style="background-color: #FFFFFF">
		<div id="loginPageMainDiv">
			<div>
				<a href="<%=webURLContext%>"> <img 
						src="<%=basePath%>/images/page/logo.png"> </a>
			</div>
			<div id="mainLoginDiv">
				<div id="topLongDiv">
					&nbsp;&nbsp;
					<b>用户登录</b>
					</font>
				</div>
				<div id="contantLoginDiv">
					<div id="loginLeftDiv">
						<form id="realLoginForm" method="post">
							<div class="FieldRow">
								<font style="font-size: 14px">账户名：</font>
								<input type="hidden" value="<%=reUrl%>" name="forwardUrl" />
								<input type="text" id="userName_input" name="userName"
									style="width: 245px; color: gray;" value="邮箱\手机号\登录名"
									alt="tianxie" onblur="userBlur();" onfocus="userFocus();" />
								<span id="userName_input_suf"></span>
							</div>
							<div class="FieldRow">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<font style="font-size: 14px">密码：</font>
								<input type="password" id="password_input" name="password"
									style="width: 245px;" />
								<span id="password_input_suf"></span>&nbsp;&nbsp;
								<a href="<%=basePath%>/pub/sub/getPass.jsp"><font
									style="font-size: 13px">忘记密码？</font> </a>
							</div>
							<div class="FieldRow">
								<div style="display: inline; float: left;">
									<font style="font-size: 14px">验证码：</font>
									<input type="text" id="checkNum" style="width: 150px;" />
								</div>
								<div id="checkCode" onclick="createCode();"
									onselectstart="return false"></div>
								<font style="font-size: 13px; color: #999999">看不清?</font>
								<a href="javascript:void(0);" onclick="createCode();"><font
									style="font-size: 13px;">换一张</font> </a>
							</div>
							<div class="FieldRow">
								<font style="font-size: 14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
								<input type="checkbox" id="rememberUserName" />
								<font style="font-size: 13px">记住账户名</font>&nbsp;&nbsp;

							</div>
							<div class="FieldRow">
								<font style="font-size: 14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
								<img src="<%=basePath%>/images/page/button_login.png"
									style="cursor: pointer;" onclick="doLogin();"
									id="submitLoginButton" />
							</div>
						</form>
					</div>
					<div id="loginVerSplitDiv"></div>
					<div id="loginRightDiv">
						<br>
						<font style="font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>还不是7号生活馆用户？</b>
						</font>
						<br>
						<br>
						<font style="font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;现在免费注册成为7号生活馆用户，便能立刻享受便宜<br>
							&nbsp;&nbsp;&nbsp;&nbsp;又放心的购物乐趣。 </font>
						<br>
						<br>
						<br>
						<div id="button_reg" onclick="toRegistrationPage();"
							onselectstart="return false">
							注册新用户
						</div>
						<br>
						<div id="loginHSplitDiv"></div>
						<br>
						<br>
						<div id="loginAdvertisementDiv">
							<img src="<%=basePath%>/images/page/ad01-02.png" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
