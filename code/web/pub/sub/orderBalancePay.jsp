<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	Object obj = request.getAttribute("url");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<title>7ºÅÉú»î¹Ý</title>
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link type="text/css" href="<%=basePath%>/pro/member/css/login.css"
			rel="stylesheet">
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/login.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/cookie.js"></script>

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
	padding-bottom: 1px;
	width: 100%;
	margin: 0 auto;
	text-align: left;
	height: 520px;
	margin-bottom: 10px;
}
</style>
	</head>
	<body style="background-color: #FFFFFF">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="index">
			<div id="ordercenter-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div id="loginContent">
				<iframe align="top" height="100%" width="100%" frameborder="0"
					id="payContentIframe" scrolling="no"
					src="<%=basePath%>/pro/member/balancePaying.jsp?orderIds=<%=request.getParameter("orderIds")%>">
				</iframe>
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
