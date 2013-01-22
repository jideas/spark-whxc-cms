<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/commonFloor.js"></script>
	<link type="text/css" href="<%=basePath%>/css/commonFloor.css" rel="stylesheet">
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
	background-color: #FFFFFF;
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
	background: url("./images/page/page_top1.png") repeat-x top left;
}

#mainFloors {
	display: block;
	float: left;
	width: 1200px;
	height: 280px;
}

#productFloors {
	display: block;
	float: left;
	width: 1200px;
	margin-top: 10px;
}
#serviceFloors{
	float: left;
	margin: 10px 0px;
}
#copyRight{
	float: left;
}
</style>
</head>

<body>
	<div id="shortcut">
		<jsp:include page="main/shortcut.jsp" flush="true" />
	</div>
	<div id="index">
		<div id="o-header">
			<jsp:include page="main/header.jsp" flush="true" />
		</div>
		<div id="mainMenu">
			<jsp:include page="main/mainMenu.jsp" flush="true" />
		</div>
		<div id="mainFloor">
			<jsp:include page="main/mainFloor.jsp" flush="true" />
		</div>
		<div id="productFloors">
			<jsp:include page="main/floor_1.jsp" flush="true" />
		</div>
		<div id="productFloors">
			<jsp:include page="main/floor_2.jsp" flush="true" />
		</div>
		<div id="productFloors">
			<jsp:include page="main/floor_3.jsp" flush="true" />
		</div>
		<div id="productFloors">
			<jsp:include page="main/floor_4.jsp" flush="true" />
		</div>
		<div id="productFloors">
			<jsp:include page="main/floor_5.jsp" flush="true" />
		</div>
		<div id="productFloors">
			<jsp:include page="main/floor_6.jsp" flush="true" />
		</div>
		<div id="serviceFloors">
			<jsp:include page="main/service.jsp" flush="true" />
		</div>
		<div id="copyRight">
			<jsp:include page="main/copyRight.jsp" flush="true" />
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var floor_1Action = new FloorAction();
		floor_1Action.setArgs("#floor_1","./images/page/sort-bg01.png","#22a618");
	});
	
	$(function(){
		
	});
	

	
</script>
</html>
