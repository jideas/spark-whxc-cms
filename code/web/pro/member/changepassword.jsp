<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<title>7号生活馆</title>
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<link type="text/css" rel="stylesheet"	href="<%=basePath%>/css/base.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/pro/member/css/my7.css">
		<style type="text/css">
/*  accountsafe main */
.main {
	margin-bottom: 10px
}

.right1 {
	width: 1040px;
	line-height: 1.5em;
	float: right;
	font-family: Verdana;
}

.main .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

/*table*/
.marginb10 {
	margin-bottom: 10px;
}

table { *
	border-collapse: collapse;
	border-spacing: 0;
}

caption {
	font-size: 14px;
	font-weight: 700;
	letter-spacing: 2px;
	height: 24px;
	line-height: 24px;
	background: #ebf4fb;
	/*background: red;*/
	border-top: 1px solid #E7CA96;
	border-right: 1px solid #E7CA96;
	border-left: 1px solid #E7CA96;
}

th,td {
	font-size: 12px;
}

th {
	font-weight: 400;
	letter-spacing: 1px;
	height: 27px;
}

.table_a {
	width: 100%;
}

.table_a td,.table_a th {
	padding: 4px 5px;
}

.table_a th,.table_c th { /*background: #FBF7EE;*/
	/*background: #99FFCC;*/
	
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

.delbuycard_cont {
	border: 1px solid #ccc;
	width: 60%;
	margin: 15px auto;
	text-align: center;
}

.delnormal_tab2 {
	font-size: 12px;
	color: #333;
	width: 98%;
	margin: 4px; *
	margin: 2 auto;
}

.delnormal_tab2 .normal_Tab {
	width: 25%;
	height: 90px;
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #e7e7e7;
	background: url(<%=basePath%>/images/page/deliver_visit_bg.gif) repeat-x
		bottom;
	padding: 0 2px 0 5px;
	cursor: pointer;
}

.delnormal_tab2 .hover_Tab {
	width: 25%;
	height: 90px;
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #FF9900;
	background: url(<%=basePath%>/images/page/deliver_hover_bg.gif) repeat-x
		bottom;
	/*cursor:pointer;*/
}

.delnormal_tab2 .click_Tab {
	width: 25%;
	height: 90px;
	line-height: 18px;
	font-family: Arial, Helvetica, sans-serif;
	border: 2px solid #FF9900;
	background: url(<%=basePath%>/images/page/deliver_buycard_right_ico.gif)
		top right no-repeat;
	/*cursor:pointer;*/
}

.delfont_span {
	font-size: 18px;
	color: #E20404;
	font-weight: bold;
}
#ordercenter-data-title
{width:1032px;*width:1040px;height:35px;line-height: 35px;border-bottom: #66CD00 2px solid;float: left;text-align: left;padding-left: 8px;color: #66CD00;}
 
/** change psd **/
.step {
	overflow: hidden;
	background: #ededed;
	color: #999999;
	font-weight: bold;
	margin: 0 auto 10px;
	zoom: 1
}

.step li {
	float: left;
	position: relative;
	padding-left: 25px;
	padding-right: 35px;
	line-height: 25px;
	height: 25px;
	overflow: hidden;
}

.step li b {
	position: absolute;
	width: 22px;
	height: 25px;
	background: url(<%=basePath%>/images/page/flow.gif) no-repeat;
	top: 0;
	right: -1px;
}

#step1 .fore1 {
	background: #ffe6bc;
	color: #ff6600;
}

#step1 .fore1 b {
	background-position: 0 -26px;
}

.step01 li {
	width: 270px;
	text-align: center
}

.safetitle {
	font-size: 20px;
	font-weight: bold;
}

.safetip {
	color: #A2A2A2;
}

em {
	color: #ff6600;
	margin-right: 3px;
}

.msg-error {
	color: '#ff6600';
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
	background-image: url('<%=basePath%>/images/page/checkCodeBg.png');
	letter-spacing: 3px;
	font-weight: bolder;
	margin-left: 5px;
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
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
var _changePsdApp = null;
$(document).ready(function() {
	_changePsdApp = new ChangePasswordApp();
	_changePsdApp.createCode();
	_changePsdApp.whenItSubmit();
});

function ChangePasswordApp() {
	// 输入初始状态
	var inputStatus = 0;
	// 验证码
	var validateCode = "";

	this.submit = function () {
		// TODO 验证密码，如果正确转向第二步
		if (!validate()){
	 		 return;
		} 
		var form = $('#changePasswordForm');
		form.attr("action", mainWeb + "/front/changePass");
		form.submit(); 
	};
	
	this.whenItSubmit = function() {
	var form = $('#changePasswordForm');
		form.submit(function() {
			$.post(form.attr('action'), form.serialize(), function(result, status) {
					 cmsAlertAtt(result.message);
					 if (result.result) { 
						form[0].reset();  
					} 
						_changePsdApp.createCode();
				}, 'json');
		return false;
	});
}
	
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
	
	var validate = function() {
		if (inputStatus == 0) {
			$(".table_a #prepassword_error").html("请输入登录密码");
			$(".table_a #prepassword_error").show();
			return false;
		}
		var errorDivs = $(".table_a div[id$='_error']");
		for (var i = 0; i < errorDivs.length; i++) {
			var errorDiv = errorDivs[i];
			if (!isEmptyString(errorDiv.innerHTML)) {
				return false;		
			}
		}
		return true;
	};
	
	var isEmptyString = function(string) {
		if (string == null || string == "") {
			return true;		
		} else {
			return false;
		}
	};
	var registeValidate = function() {
		$(".table_a #prepassword").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #prepassword_error").html("<font color=red>请输入登录密码</font>");
				$(".table_a #prepassword_error").show();
			}
		});
		$(".table_a #prepassword").bind('focusin', function() {
			$(".table_a #prepassword_error").html('');
			$(".table_a #prepassword_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		$(".table_a #password").bind('focusout', function() { 
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #password_error").html("<font color=red>请输入新的登录密码</font>");
				$(".table_a #password_error").show();
			} else if(!/^[A-Za-z0-9]+$/.test($.trim($(this).val()))){
				$(".table_a #password_error").html("<font color=red>登录密码只能由字母和数字组成</font>");
				$(".table_a #password_error").show();
			}else if($.trim($(this).val()).length < 6 || $.trim($(this).val()).length > 16){ 
				$(".table_a #password_error").html("<font color=red>登录密码长度应该在6到16之间</font>");
				$(".table_a #password_error").show();
			}
		});
		$(".table_a #password").bind('focusin', function() {
			$(".table_a #password_error").html('');
			$(".table_a #password_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		$(".table_a #confirmPassword").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #confirmpassword_error").html("<font color=red>请确认密码</font>");
				$(".table_a #confirmpassword_error").show();
			}else if($.trim($(this).val()) != $.trim($(".table_a #password").val()) ){
				$(".table_a #confirmpassword_error").html("<font color=red>两次输入密码不一致</font>");
				$(".table_a #confirmpassword_error").show();
			}
		});
		$(".table_a #confirmPassword").bind('focusin', function() {
			$(".table_a #confirmpassword_error").html('');
			$(".table_a #confirmpassword_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		$(".table_a #checkNum").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #validatecode_error").html("<font color=red>请输入验证码</font>");
				$(".table_a #validatecode_error").show();
			} else if ($(this).val().toUpperCase() != validateCode.toUpperCase()){
				$(".table_a #validatecode_error").html("<font color=red>验证码输入有误</font>");
				$(".table_a #validatecode_error").show();
				this.createCode();
			} 
		});
		$(".table_a #checkNum").bind('focusin', function() {
			$(".table_a #validatecode_error").html('');
			$(".table_a #validatecode_error").hide();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
	};
	
	var init = function() {
		registeValidate();
		$(".table_a #prepassword").focus();
	};
	
	init();
}
</script>
	</head>
	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id='chargecontent' class='w'>
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div class="main">
				<div class="breadcrumb">
					<span class="my7"><strong><a href="javascript:;">我的7号</a></strong></span><span>&nbsp;&gt;&nbsp;帐户安全</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='right1'>
					<div id="ordercenter-data-title" style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							修改登录密码
						</h3>
					</div>
					<table class="table_a marginb10">
						<form id="changePasswordForm" method="post">
						<!-- tr>
							<th colspan="3" align="left"
								style="background-image: url('<%=basePath%>/images/page/sort-bg05.png');">
								<strong>修改登录密码</strong>
							</th>
						</tr> -->
						<tr style='background-color: #FFFFFF'>
							<td colspan='3' style='border: #E6E6E6 solid 1px;' align='center'>
								<table>
									<tr height="57px">
										<td align='right' style="vertical-align: bottom;">
											<em>*</em>当前密码：
										</td>
										<td align='left' style="vertical-align: bottom;">
											<input type='password' name="prepassword" id='prepassword' />
										</td>
									</tr>
									<tr height="22px">
										<td></td>
										<td style="vertical-align: top;">
											<div id="prepassword_error" class="msg-error"
												style="display: none;"></div>
										</td>
									</tr>
									<tr height="40px">
										<td align='right' style="vertical-align: bottom;">
											<em>*</em>新密码：
										</td>
										<td align='left' style="vertical-align: bottom;">
											<input type='password' name="password" id='password' />
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
											<em>*</em>确认新密码：
										</td>
										<td align='left' style="vertical-align: bottom;">
											<input type='password' id='confirmPassword' />
										</td>
									</tr>
									<tr height="22px">
										<td></td>
										<td style="vertical-align: top;">
											<div id="confirmpassword_error" class="msg-error"
												style="display: none;"></div>
										</td>
									</tr>
									<tr height="40px">
										<td align='right' style="vertical-align: bottom;">
											<em>*</em>验证码：
										</td>
										<td align='left' style="vertical-align: bottom;">
											<div>
												<input type="text" id="checkNum" style='display: inline;' />
												<div id="checkCode" onclick="_changePsdApp.createCode();"
													style='display: inline;'></div>
												<font
													style="font-size: 13px; display: inline; color: #999999">看不清?</font>
												<a href="#" onclick="_changePsdApp.createCode();"><font
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
											<input type="button" id="sureToModifyButton"
												onclick="_changePsdApp.submit();" value="确定修改" />
										</td>
									</tr>
									</form>
								</table>
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