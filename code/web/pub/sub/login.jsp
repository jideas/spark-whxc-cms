<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
Object obj = request.getAttribute("url"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>7ºÅÉú»î¹Ý</title>
		<link id="cssSink" type="text/css" rel="stylesheet" href="<%=basePath%>/scripts/dialog/cmsDialog.css"/>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<script type="text/javascript" src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link type="text/css" href="<%=basePath%>/pro/member/css/login.css" rel="stylesheet">
 		<script type="text/javascript" src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
 		<script type="text/javascript" src="<%=basePath%>/pro/member/scripts/login.js"></script>
 		<script type="text/javascript" src="<%=basePath%>/pro/member/scripts/cookie.js"></script>

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
	background: url("../../images/page/page_top1.png") repeat-x top left;
}

#loginContent {
	padding-bottom: 1px; 
	width: 100%; 
	margin: 0 auto; 
	margin-bottom: 10px;
	border-bottom: #D1D1D1 solid 1px;
}
</style>
	</head>
	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="index">
			<div id="loginContent">
				<jsp:include page='/pro/member/login.jsp?url=<%=obj%>' flush="true" />
			</div>
			<div id="serviceFloors" style="background-color: #F00;">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
</html>
