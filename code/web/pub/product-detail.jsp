<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/cookieHelper.js"></script>
		<title>商品详情</title>
		<style type="text/css">
/*整体布局*/
body {
	margin: 0px;
	padding: 0px;
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

#product_container {
	position: relative;
	margin: 0px auto;
	padding: 0px;
	width: 1200px;
}

/*头部*/
#product_header {
	position: relative;
	width: 1200px;
	height: 135px;
	z-index: 100;
}

/*商品详情购买*/
#product_detail_buy {
	position: relative;
	width: 1200px;
	height: 370px; *
	height: 368px;
	margin-top: 5px;
	padding: 0px;
	text-align: left;
	z-index: 0;
}

/*内容区域*/
#product_content {
	position: relative;
	width: 1200px;
	margin-top: 7px; *
	margin-top: 5px;
	z-index: 0;
}

/*热销商品区*/
#product_promation {
	position: relative;
	float: left;
	width: 218px;
	height: 1600px;
	*height: 1550px;
	text-align: left;
	z-index: 0;
}

/*同类人气*/
#product_common_popular {
	position: relative;
	width: 218px;
	height: 775px; *
	height: 773px;
	margin-bottom: 5px;
}

/*最近浏览*/
#product_lastest_view {
	position: relative;
	width: 218px;
	height: 775px; *
	height: 773px;
	z-index: 0;
}

/*商品详情tab面板*/
#product_detail_tab {
	position: relative;
	float: right;
	width: 970px; *
	margin-bottom: -8px;
	z-index: 0;
}

/*底楼广告*/
#product_ad_floor {
	clear: both;
	width: 1200px;
	height: 107px;
	margin: 5px 0px;
	z-index: 0;
}

/*用户指南/客服*/
#product_service {
	width: 1200px;
	height: 200px;
	z-index: 0;
}

/*版权*/
#product_copyRight {
	width: 1200px;
	height: 60px;
	margin: 5px 0px;
	z-index: 0;
}
</style>
	</head>
	<body style="background-color: #FFFFFF">
		<div id="shortcut">
			<jsp:include page="main/shortcut.jsp" flush="true" />
		</div>
		<div id="product_container">
			<div id="product_header">
				<jsp:include page="main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div id="product_detail_buy">
				<jsp:include page="main/product-detail-buy.jsp" flush="true" />
			</div>
			<div id="product_content">
				<div id="product_promation">
					<div id="product_common_popular">
						<jsp:include page="main/common-popular.jsp" flush="true" />
					</div>
					<div id="product_lastest_view">
						<jsp:include page="main/lastest-view.jsp" flush="true" />
					</div>
				</div>
				<div id="product_detail_tab">
					<jsp:include page="main/product-detail-tab.jsp" flush="true" />
				</div>
				<div
					style="width: 1200px; clear: both; * height: 0px; * line-height: 0px;"></div>
			</div>
			<div id="product_ad_floor">
				<jsp:include page="main/downstairs-ad.jsp" flush="true" />
			</div>
			<div id="product_service">
				<jsp:include page="main/service.jsp" flush="true" />
			</div>
			<div id="product_copyRight">
				<jsp:include page="main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
</html>