<%@ page language="java" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery/jquery.js"></script>

		<title>7ºÅÉú»î¹Ý</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<style type="text/css">
*{
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

#index{
	width: 1200px;
	margin: 0 auto;
}
#shortcut {
	top: 0px;
	padding-bottom: 1px;
	line-height: 30px; 
	width: 100%;
	background-image: URL('./images/page/page_top1.png'); 
}
#mainFloors{
	display: block;
	float: left;
	width: 1200px;
	height: 280px;
}

#productFloors{
	display: block;
	float: left;
	width: 1200px;
}
</style>


	</head>

	<body>
		<div id="index">
			<div id="shortcut">
				<jsp:include page="main/shortcut.jsp" flush="true" />
			</div>
			<div id="o-header">
				<jsp:include page="main/o-header1.jsp" flush="true" />
			</div>
			<div id="mainMenu">
				<jsp:include page="main/mainMenu.jsp" flush="true" />
			</div>
			>
		</div>
	</body>
</html>
