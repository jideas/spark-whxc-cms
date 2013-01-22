<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.spark.front.action.goods.GoodsKey"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	java.util.List<String> htmllist = (java.util.List<String>) request
			.getAttribute(GoodsKey.SameCategoryPopularGoods.toString());
	String html0 = "";
	String html1 = "";
	String html2 = "";
	if (htmllist != null && !htmllist.isEmpty() && htmllist.size() > 0) {
		html0 = htmllist.get(0);
	}
	if (htmllist != null && !htmllist.isEmpty() && htmllist.size() > 1) {
		html1 = htmllist.get(1);
	}
	if (htmllist != null && !htmllist.isEmpty() && htmllist.size() > 2) {
		html2 = htmllist.get(2);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
#categoryPagecontantLeft1 {
	width: 218px;
	height: 770px;
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
	margin: 35px 0px;
	display: block;
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
	color: #CC0000;
	text-decoration: underline;
}
</style>
	</head>
	<body>
		<div id="categoryPagecontantLeft1">
			<div class="divTitleRow">
				<font><b>同类人气商品</b> </font>
			</div>
			<div class="goodsInfoDiv" style="margin-bottom: 10px;">
				<%=html0%>
			</div>
			<div class="goodsInfoDiv">
				<%=html1%>
			</div>
			<div class="goodsInfoDiv">
				<%=html2%>
			</div>
		</div>
	</body>
</html>