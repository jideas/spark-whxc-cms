<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	String id = (String)request.getAttribute("id");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/commonFloor.js"></script>
		<link type="text/css" href="<%=basePath%>/css/commonFloor.css"
			rel="stylesheet">
		<title>7号生活馆</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="蔬菜 生鲜  超市 7号生活馆 粮油 ">
		<script type="text/javascript">
			function pageLoad(){
				var id = '<%=id%>';
				if(id&&id!='null'){
					$('#contantLoginDiv2').hide();
				}else{
					$('#contantLoginDiv1').hide();
				}
			}
			function gotoLoginSite(){
				this.location = '<%=basePath%>/pub/sub/login.jsp';
			}
		</script>
		<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}

body {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
	margin: 0 auto;
}

body,h1,h2,h3,h4,h5,h6,p,ul,ol,li,form,img,dl,dt,dd,table,th,td,blockquote,fieldset,div,strong,label,em
	{
	margin: 0;
	padding: 0;
	border: 0;
}

#index {
	width: 1200px;
	margin: 0 auto;
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

#loginContent {
	padding-bottom: 1px;
	height: 300px;
	text-align: left;
	vertical-align: middle;
}

#topLongDiv {
	font-size: 16px;
	line-height: 31px;
	background-image: url('<%=basePath%>/images/page/page_top1.png');
	vertical-align: middle;
}

#mainLoginDiv {
	height: 100%;
	width: 1198;
	*width: 1200;
	border-top: #D1D1D1 solid 1px;
	border-left: #D1D1D1 solid 1px;
	border-right: #D1D1D1 solid 1px;
}
.contantLoginDiv{
margin-top: 80px;
margin-left: 300px;
}
</style>
	</head>

	<body onload="pageLoad();" style="background-color: #FFFFFF">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="index">
			<div id="loginContent">
				<div>
					<a href="<%=webURLContext%>"> <img width="240" height="62"
							src="<%=basePath%>/images/page/logo.png"> </a>
				</div>
				<div id="mainLoginDiv">
					<div id="topLongDiv">
						&nbsp;&nbsp;
						<b>验证邮箱</b>
						</font>
					</div>
					<div id="contantLoginDiv1" class="contantLoginDiv">
						<div style="display: inline; float: left;">
										<img src="<%=basePath%>/images/page/success01.png" />
									</div>
									<div style="display: inline; float: left; line-height: 20px; padding-top: 20px;">
										<font color="black" style="font-size: 12px;">&nbsp;&nbsp;验证邮箱成功！您可以</font>&nbsp;<a
											href="javascript:void(0);" onclick="gotoLoginSite();" style="font-size: 12px;color: blue" >登录7号生活馆</a><font
											color="black" style="font-size: 12px;">&nbsp;进行购物。</font>
										&nbsp;&nbsp;
									</div>
					</div>
					<div id="contantLoginDiv2" class="contantLoginDiv">
						<div style="display: inline; float: left;">
										<img src="<%=basePath%>/images/page/failure01.png" />
									</div>
									<div style="display: inline; float: left; line-height: 20px; padding-top: 20px;">
										<font color="black" style="font-size: 12px;">&nbsp;&nbsp;验证信息已过期，请您</font>&nbsp;<a
											href="javascript:void(0);" onclick="gotoLoginSite();" style="font-size: 12px;color: blue" >登录7号生活馆</a><font
											color="black" style="font-size: 12px;">&nbsp;重新操作。</font>
										&nbsp;&nbsp;
									</div>
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
	<script type="text/javascript">
	$(function(){
		var floor_1Action = new FloorAction();
		floor_1Action.setArgs("#floor_1","<%=basePath%>/images/page/sort-bg01.png","#22a618");
	});		
</script>
</html>
