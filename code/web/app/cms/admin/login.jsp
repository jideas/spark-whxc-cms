<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>7号生活馆后台管理-登录</title>
		<link rel="shortcut icon" href="<%=mainWeb%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<style type='text/css'>
body {
	background-color: #e7e7de;
	margin: 0;
	border: 0;
}

.main {
	margin: 170px 0;
	border: solid 0px;
	height: 353px;
	width: 100%;
	text-align: center;
	background-image: url(<%=mainWeb%>/images/admin/login-back.png);
}

.content {
	margin: 0 auto;
	width: 944px;
	height: 350px;
	overflow: hidden;
	border: solid 0px red;
}

.left {
	float: left;
	width: 553px;
	height: 350px;
	padding-left: 0px;
	margin-left: 0px;
}

.lefttop {
	width: 553px;
	height: 59px;
	/*margin-right: 0px;*/ *
	text-align: left;
	margin-left: 0px;
	background-image: url(<%=mainWeb%>/images/admin/login-title.png);
}

.leftbottom {
	width: 553px;
	height: 289px;
	margin-left: 0px;
	background-image: url(<%=mainWeb%>/images/admin/login-left.png);
}

.right {
	float: left;
	width: 391px;
	height: 350px;
	padding-top: 100px;
	paddin-left: 30px;
	text-align: left;
	overflow: hidden;
	background-image: url(<%=mainWeb%>/images/admin/login-right.png);
}

/*table*/
table { /*margin-top: 80px;*/
	margin-left: 20px;
	border-collapse: collapse;
	border-spacing: 0;
	/*z-index: 4;*/
}

th,td {
	font-size: 12px;
}

th {
	font-weight: 400;
	letter-spacing: 1px;
	height: 23px;
}

/** 验证码  **/
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
	background-image: url('<%=mainWeb%>/images/page/checkCodeBg.png');
}
</style>
		<script type="text/javascript"
			src="<%=mainWeb%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
var _loginApp = null;
$(document).ready(function() {
	_loginApp = new LoginApp();
	_loginApp.createCode();
});

function LoginApp() {
	var validateCode = null;
	var inputStatus = 0;
	var $userNameEditor = $(".table_a #username");
	var $passwordEditor = $(".table_a #password");
	var $vaidateCodeEditor = $('.table_a #checkNum');
	
	this.createCode = function(isInner) {
		if (!isInner) {
			 validateCode = "";
		}
		var codeLength = 4;// 验证码的长度
		$(".table_a #checkCode").html();
		var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

		for ( var i = 0; i < codeLength; i++) {
			var charIndex = Math.floor(Math.random() * 32);
			validateCode += selectChar[charIndex];
		}
		if (validateCode.length != codeLength) {  
			this.createCode(true); 
		}
		$(".table_a #checkCode").html(validateCode);
	};
	
	this.submit = function() {
		if (!validate()) return;
		$.ajax({
			type: 'post',
			data: $('#loginform').serialize(),
			url: mainWeb + "/admin/user/login",
			dataType: 'json',
			success: function(data) {
				if (data.result) {
					document.location.href = mainWeb + '/admin/user/toDesktop/'+data.message;
				} else {
					_loginApp.createCode();
					$(".table_a #username").focus();
					if ("1" == data.message) {
						$(".table_a #username_error").html("<font color=red>用户名或密码错误</font>");
						$(".table_a #password_error").html("<font color=red>用户名或密码错误</font>");
						$(".table_a #username_error").show();
						$(".table_a #password_error").show();
					} else if ("2" == data.message) {
						$(".table_a #username_error").html("<font color=red>该用户处于停用状态</font>");
						$(".table_a #username_error").show();
					} else {
						alert(data.message);
					}
					//alert(data.message);
					//$.messager.alert('提示',data.message); 
				}
			},
			error: function() {
				_loginApp.createCode();
			}
		});
		
	};
	
	var addKeyDownListener = function() {
		$('.table_a #username').keydown(function(event) {
			if (event.which == 13) {
				$(".table_a #password").focus();
			}
		});
		$('.table_a #password').keydown(function(event) {
			if (event.which == 13) {
				$(".table_a #checkNum").focus();
			}
		});
		$('.table_a #checkNum').keydown(function(event) {
			if (event.which == 13) {
				$(".table_a #loginButton").focus();
			}
		});
	};
	
	var validate = function() {
		if (inputStatus == 0) {
			$(".table_a #username_error").html("请输入用户名");
			$(".table_a #username_error").show();
			return false;
		}
		var errorDivs = $(".table_a div[id$='_error']");
		for (var i = 0; i < errorDivs.length; i++) {
			var errorDiv = errorDivs[i];
			if (!isEmptyString(errorDiv.innerHTML)) {
				return false;		
			}
		}
		if ($userNameEditor.val() == null || $.trim($userNameEditor.val()) == "") {
			$(".table_a #username_error").html("<font color=red>请输入用户名</font>");
			$(".table_a #username_error").show();
			return false;
		}
		if ($passwordEditor.val() == null || $.trim($passwordEditor.val()) == "") {
			$(".table_a #password_error").html("<font color=red>请输入登录密码</font>");
			$(".table_a #password_error").show();
			return false;
		}
		
		if ($vaidateCodeEditor.val() == null || $.trim($vaidateCodeEditor.val()) == "") {
			$(".table_a #validatecode_error").html("<font color=red>请输入验证码</font>");
			$(".table_a #validatecode_error").show();
			return false;
		} else if ($vaidateCodeEditor.val().toUpperCase() != validateCode.toUpperCase()){
			$(".table_a #validatecode_error").html("<font color=red>验证码输入有误</font>");
			$(".table_a #validatecode_error").show();
			this.createCode();
			return false;
		}
		return true;
	};
	
	var registeValidate = function() {
		$userNameEditor.bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #username_error").html("<font color=red>请输入用户名</font>");
				$(".table_a #username_error").show();
			} else {
				$(".table_a #username_error").empty();
				$(".table_a #username_error").hide();
			}
		});
		$userNameEditor.bind('focusin', function() {
			$(".table_a #username_error").empty();
			$(".table_a #username_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		$passwordEditor.bind('focusout', function() { 
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #password_error").html("<font color=red>请输入登录密码</font>");
				$(".table_a #password_error").show();
			} else {
				$(".table_a #password_error").empty();
				$(".table_a #password_error").hide();
			}
		});
		$passwordEditor.bind('focusin', function() {
			$(".table_a #password_error").empty();
			$(".table_a #password_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		$vaidateCodeEditor.bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #validatecode_error").html("<font color=red>请输入验证码</font>");
				$(".table_a #validatecode_error").show();
			} else if ($(this).val().toUpperCase() != validateCode.toUpperCase()){
				$(".table_a #validatecode_error").html("<font color=red>验证码输入有误</font>");
				$(".table_a #validatecode_error").show();
				this.createCode();
			} else {
				$(".table_a #validatecode_error").empty();
				$(".table_a #validatecode_error").hide();
			}
		});
		$vaidateCodeEditor.bind('focusin', function() {
			$(".table_a #validatecode_error").empty();
			$(".table_a #validatecode_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
	};
	var isEmptyString = function(string) {
		if (string == null || string == "") {
			return true;		
		} else {
			return false;
		}
	};
	var init = function() {
		registeValidate();
		addKeyDownListener();
		$(".table_a #username").focus();
	};
	init();
}
</script>
	</head>
	<body>


		<div class='main'>
			<div class='content'>

				<div class='left'>
					<div class='lefttop'></div>
					<div class='leftbottom'></div>
				</div>
				<div class='right'>
					<form id='loginform'>
						<table class='table_a'>
							<tr height="40px">
								<td align='right' style="vertical-align: bottom;">
									用户名：
								</td>
								<td align='left' style="vertical-align: bottom;">
									<input type='text' name="account" id='username'
										style='width: 120px;' />
								</td>
							</tr>
							<tr height="22px">
								<td></td>
								<td style="vertical-align: top;">
									<div id="username_error" class="msg-error"
										style="display: none;"></div>
								</td>
							</tr>
							<tr height="40px">
								<td align='right' style="vertical-align: bottom;">
									密码：
								</td>
								<td align='left' style="vertical-align: bottom;">
									<input type='password' name="pwd" id='password'
										style='width: 120px;' />
								</td>
							</tr>
							<tr height="22px">
								<td></td>
								<td style="vertical-align: top;">
									<div id="password_error" class="msg-error"
										style="display: none;"></div>
								</td>
							</tr>
							<tr height="40px">
								<td align='right' style="vertical-align: bottom;">
									验证码：
								</td>
								<td align='left' style="vertical-align: bottom;">
									<div>
										<input type="text" id="checkNum"
											style='display: inline; width: 80px;' />
										<div id="checkCode" onclick="_loginApp.createCode();"
											style='display: inline;'></div>
										<!-- font
									style="font-size: 13px; display: inline; color: #999999">看不清?</font> -->
										<a href="#" onclick="_loginApp.createCode();"><font
											style="font-size: 13px; display: inline;">换一张</font> </a>
									</div>
								</td>
							</tr>
							<tr height="22px">
								<td></td>
								<td style="vertical-align: top;">
									<div id="validatecode_error" class="msg-error"
										style="display: none;"></div>
								</td>
							</tr>
							<tr height="40px">
								<td></td>
								<td align='left' style="vertical-align: bottom;">
									<input type="button" style='padding-top: 3px;' id="loginButton"
										onclick="_loginApp.submit();" value=" 登录 " />
								</td>
							</tr>
						</table>
					</form>
				</div>

			</div>
		</div>
	</body>
</html>