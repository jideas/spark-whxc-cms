<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
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
		<title>7ºÅÉú»î¹Ý</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="Êß²Ë ÉúÏÊ  ³¬ÊÐ 7ºÅÉú»î¹Ý Á¸ÓÍ ">
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
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top left;
}

#loginContent { 
	height: 350px; 
	margin-left: -8px;
	*margin-left: -12px;
	margin-right:-3px;  
	*margin-top: -2px;   
}
</style>
	</head>

	<body style="background-color: #FFFFFF">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="index">
			<div id="loginContent">
				<iframe src="<%=basePath%>/pro/member/getPass1.jsp" width="100%"
					height="100%" scrolling="no"
					style="border:0px;" ></iframe>
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
		floor_1Action.setArgs("#floor_1","./images/page/sort-bg01.png","#22a618");
	});		
</script>
</html>
