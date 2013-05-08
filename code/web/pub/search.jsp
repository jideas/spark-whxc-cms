<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>搜索结果</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link type="text/css" href="<%=basePath%>/css/hotsale.css"
			rel="stylesheet">
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
	z-index: 10000
}

/*热卖*/
#product_hot-sale {
	width: 1200px;
	height: 180px;
	margin: 5px 0px;
	text-align: left;
	z-index: 0;
	position: relative;
}

/*内容区域*/
#product_content {
	position: relative;
	width: 1200px;
	height: 1734px;
	margin-top: 7px;
	z-index: 0;
}

/*热销商品区*/
#product_promation {
	position: relative;
	float: left;
	width: 218px;
	height: 1600px;
	text-align: left;
	z-index: 0;
}

/*同类人气*/
#product_common-popular {
	position: relative;
	width: 218px;
	height: 935px;
	margin-bottom: 5px;
}

/*最近浏览*/
#product_lastest-view {
	position: relative;
	width: 218px;
	height:790px;
	*height: 793px; 
	z-index: 0;
}
#categoryPagecontantLeft2 {
	height: 945px;
	*height: 930px;
}
/*分类展示*/
#product_category-show {
	position: relative;
	float: right;
	width: 975px;
	height: 1732px;
	z-index: 0;
}

/*底楼广告*/
#product_ad-floor {
	width: 1200px;
	height: 107px;
	margin: 5px 0px; *
	margin-top: 010px;
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
	<body>
		<div id="shortcut">
			<jsp:include page="main/shortcut.jsp" flush="true" />
		</div>
		<div id="product_container">
			<div id="product_header">
				<jsp:include page="main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div id="product_hot-sale">
				<jsp:include page="main/hotsale.jsp" flush="true" />
			</div>
			<div id="product_content" style="height: 945px;*height: 932px;">
				<div id="product_promation" style="height: 947px;*height: 930px;">
					<div id="product_lastest-view" style="height: 945px;*height: 930px;">
						<jsp:include page="main/lastest-view.jsp" flush="true" />
					</div>
				</div>
				<div id="product_category-show" style="height: 945px;*height: 932px;">
					<jsp:include page="searchresult.jsp" flush="true" />
				</div>
			</div>
			<!-- 
			<div id="product_ad-floor">
				<jsp:include page="main/downstairs-ad.jsp" flush="true" />
			</div
			 -->
			<div id="product_service">
				<jsp:include page="main/service.jsp" flush="true" />
			</div>
			<div id="product_copyRight">
				<jsp:include page="main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
	<script type="text/javascript">
//【购买】悬浮效果
function product_buy_over(img){
	$(img).attr("src","<%=basePath%>/images/page/buy25-02.png");
}
function product_buy_out(img){
	$(img).attr("src","<%=basePath%>/images/page/buy25-01.png");
}
</script>
</html>