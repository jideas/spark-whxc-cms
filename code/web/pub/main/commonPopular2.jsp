<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
#categoryPagecontantLeft1 {
	width: 218px;
	height: 935px;
	border: #D9D9D9 solid 1px;
}

#categoryPagecontantLeft1 .divTitleRow {
	width: 218px;
	font-size: 16px;
	height: 31px;
	line-height: 31px;
	text-align: left;
	background-image: url('<%=basePath%>/images/page/page_top1.png');
	vertical-align: middle;
}

#categoryPagecontantLeft1 .divTitleRow font {
	font-size: 12px;
	height: 31px;
	padding-left: 10px;
}

#categoryPagecontantLeft1 .goodsInfoDiv {
	text-align: center;
	display: block;
	margin:20px 0px;
	height:200px;
}

#categoryPagecontantLeft1 .p-price {
	line-height: 22px;
	font-size: 12px;
	color: #CE0303;
}

#categoryPagecontantLeft1 .p-name {
	line-height: 22px;
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}

#categoryPagecontantLeft1 a:hover {
	color: #FF0000;
	text-decoration: underline;
}
</style>
	</head>
	<body>
		<%=request.getAttribute("pop")%>
	</body>
</html>