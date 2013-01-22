<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "">
<html>
	<head>
		<title>登录7号生活馆</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link type="text/css"
			href="<%=basePath%>/pro/member/css/procedure.css" rel="stylesheet">
		<script type="text/javascript">
			 function getBackToHome(){
				 this.parent.location = '<%=basePath%>';
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
	height: 48px; line-height : 48px; margin-left : 300px; text-align :
	left; margin-top : 70px;
	font-size: 12px;
	line-height: 48px;
	margin-left: 300px;
	text-align: left;
	margin-top: 70px;
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
				<div id="step4" class="step">
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
					<div style="display: inline; float: left;">
						<img src="<%=basePath%>/images/page/success01.png" />
					</div>
					<div style="display: inline; float: left; line-height: 20px;">
						<div>
							<font color="green">&nbsp;&nbsp;新密码设置成功！</font>
						</div>
						<div>
							<font color="black">&nbsp;&nbsp;请牢记您新设置的密码。</font> &nbsp;&nbsp;
							<a href="#" onclick="getBackToHome();">返回首页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
