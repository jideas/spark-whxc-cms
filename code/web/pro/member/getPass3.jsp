<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	String id = (String) request.getAttribute("id");
if(null==id){
	id = request.getParameter("id");	
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "">
<html>
	<head>
		<title>登录7号生活馆</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link type="text/css"
			href="<%=basePath%>/pro/member/css/procedure.css" rel="stylesheet">
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"> 
		
		$(document).ready(function(){
			var id = '<%=id%>'; 
			if(id&&id!='null'){
				$('#id_input').val(id); 
			}
		});
 
function doNextStep(){ 
	if (!validateForm()) {
		return;
	}
	var form = $('#updatePasswordForm');
	form.attr("action", mainWeb+"/front/updatePass");
	form.submit();  
}

function validateForm() { 
	var returnValue = true;
	var id = $('#id_input').val(); 
	if ($.trim(id) == '') { 
		returnValue = false;
	}  
	var pass0 = $('#password_input').val();
	if ($.trim(pass0) == '') {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf')
				.html('<font color=red >&nbsp;新密码必须填写！</font>');
		returnValue = false;
	} else if (pass0) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#password_input_suf').html('');
	}
	var pass1 = $('#repassword_input').val();
	if ($.trim(pass1) == '') {
		$('#repassword_input').css('border', 'red solid 2px');
		$('#repassword_input_suf')
				.html('<font color=red >&nbsp;请确认新密码！</font>');
		returnValue = false;
	} else if (pass1) {
		$('#repassword_input').css('border', '#ABADB3 solid 1px');
		$('#repassword_input_suf').html('');
	}
	if (pass0 && pass1 && pass0 != pass1) {
		$('#password_input').css('border', 'red solid 2px');
		$('#repassword_input').css('border', 'red solid 2px');
		$('#repassword_input_suf')
				.html('<font color=red >&nbsp;两次填写的密码不一样!</font>');
		returnValue = false;
	} else if (pass0 && pass1) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#repassword_input').css('border', '#ABADB3 solid 1px');
		$('#repassword_input_suf').html('');
	} 
	if (pass0 && !/^[A-Za-z0-9]+$/.test(pass0)) {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf')
				.html('<font color=red >&nbsp;密码只能由字母和数字组成！</font>');
		returnValue = false;
	} else if (pass0) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#password_input_suf').html('');
	}
	if (pass0 && (pass0.length < 6 || pass0.length > 16)) {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf')
				.html('<font color=red >&nbsp;密码应该为6到16位！</font>');
		returnValue = false;
	} 
	return returnValue;
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
	margin-top: 8px;
	margin-left: 70px;
	font-size: 12px;
	margin-top: 8px;
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
				<div id="step3" class="step">
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
					<form id="updatePasswordForm" method="post">
						<div class="FieldRow">
							新登录密码：&nbsp; <input type="password" style="width: 245px;" name="password" id="password_input"/><span id="password_input_suf"></span>
							<input type="hidden" name="id" id="id_input"/>
						</div>
						<div class="FieldRow">
							确定新密码：&nbsp; <input style="width: 245px;" type="password" name="repassword" id="repassword_input"/><span id="repassword_input_suf"></span>
						</div>
						<div class="FieldRow">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="<%=basePath%>/images/page/next_step.png"
								style="cursor: pointer;" onclick="doNextStep();" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
