<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
#my7 .mc {
	border: solid #E6E6E6;
	border-width: 0 1px 1px;
	overflow: hidden;
}

#my7 dl dt {
	position: relative;
	margin-bottom: -1px;
	height: 27px;
	border: solid #E6E6E6;
	border-width: 1px 0;
	background: url('<%=basePath%>/images/page/bg_jdleft.jpg') #E6E6E6 repeat-x 0 -30px;
	font-weight: bold;
	line-height: 27px;
	text-align: left;
	padding-left: 5px;
	cursor: pointer;
}

#my7 dd {
	padding: 4px 0 5px;
}

#my7 dd .item {
	height: 30px;
	line-height: 30px;
	text-align: left;
	padding-left: 5px;
	border-top: 1px dotted #CACACA;
	margin-left: 5px;
	margin-right: 5px;
	
}

#my7 dd .item a{
	padding-left: 2px;
	font-family: "ËÎÌå";
}

#my7 dd .item a:link,a:visited {
	color: #333;
	text-decoration: none;
}

#my7 dd .item a:hover {
	color: #F00;
	text-decoration: underline;
}

#my7 dd .item a:active {
	color: #900;
}
#my7 dd .pre {
	border-top: none;
}
</style>
</head>
<body style="background-color: #FFFFFF">
	<%=request.getAttribute("newsNav")%>
</body>
</html>