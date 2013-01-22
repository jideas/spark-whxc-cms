<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<style type="text/css">
/*分类级别*/
#category-level {
	width: 1200px;
	height: 20px;
	font-size: 12px;
	padding-left: 20px;
	background: url("<%=basePath%>/images/page/hot-sale-homePag.png") left
		top no-repeat;
}

#category-level span {
	margin: 0px 3px;
	height: 18px;
	line-height: 18px;
}

#category-level span a,
#category-level span a:link{
	color: #000000;
	text-decoration: none;
}

##category-level span a:hover{
	color: #FF0000;
	text-decoration: underline;
}
/*商品热卖*/
#hot-sale {
	position: relative;
	width: 1200px;
	height: 180px; *
	height: 182px;
	margin: 0px;
	padding: 0px;
	border: 1px solid #FC9503;
}

/*商品热卖 -> 左上角广告*/
#hot-sale .ad-left-top {
	position: absolute;
	left: 0px;
	top: 0px;
}

/*上一张/下一张*/
#hot-sale a.next,#hot-sale a.prev {
	position: absolute;
	display: none;
	top: 35%;
	margin-top: -15px;
	z-index: 60;
	height: 75px;
	width: 50px;
	background-image: url("<%=basePath%>/images/page/arrows.png");
}

#hot-sale a.next {
	background-position: 100% 0;
	right: 0px;
}

#hot-sale a.prev {
	left: 0px;
	background-position: 0 0;
}

#hot-sale a.next,#hot-sale a.prev {
	display: block
}

/*滚动区域*/
#hot-sale-scroll {
	position: relative;
	margin-left: 60px;
	width: 1080px;
	height: 180px;
	overflow: hidden;
	overflow-x: hidden;
	overflow-y: hidden;
}

/*滚动区域 -> 滚动子项*/
#hot-sale-scroll ul {
	position: absolute;
	width: 999999px;
	left: 40px;
	list-style: none;
	margin-left: -40px;
}

#hot-sale-scroll li {
	float: left;
	width: 270px;
	height: 180px;
	margin-top: 0px;
}

/*滚动子项 -> 图片*/
#hot-sale-scroll .scroll-image {
	float: left;
	width: 135px;
	height: 180px;
	line-height: 180px;
	padding-top: 25px;
}

#hot-sale-scroll .scroll-image img {
	border: 0px;
	width: 130px;
	height: 130px;
	outline: none;
}

/*滚动子项 -> 详情*/
#hot-sale-scroll .scroll-detail {
	float: right;
	width: 125px;
	height: 180px;
	font-size: 12px;
	color: #000000;
	padding-top: 40px;
}

#hot-sale-scroll .scroll-detail div {
	height: 25px;
	line-height: 25px;
}

#hot-sale-scroll .scroll-detail div b {
	color: #CE0303;
}

#hot-sale-scroll .scroll-detail a,
#hot-sale-scroll .scroll-detail a:link{
	text-decoration: none;
	color: #000000;
}

#hot-sale-scroll .scroll-detail a:hover {
	text-decoration: underline;
	color: #FF0000;
}

#hot-sale-scroll .scroll-detail input {
	border: none;
	cursor: pointer;
	color: #FFFFFF;
	font-weight: bold;
	width: 82px;
	height: 27px;
	background-image: url("<%=basePath%>/images/page/Qbuy01.png");
	background-repeat: no-repeat;
}
</style>
<script type="text/javascript">
function productListToGoodsInfo(recid){
	
}
</script>
	</head>
	<body>
		<div id="category-level">
			<!-- 
			<span><a target="_blank" href="<%=basePath %>">首页</a></span><span>&gt;</span><span>生鲜</span><span>&gt;</span><span>蔬菜</span>
			 -->
			 <%=(String)request.getAttribute("nav")%>
		</div>
		<div id="hot-sale">
			<div class="ad-left-top">
				<img src="<%=basePath%>/images/page/hot-sale-HOT.png" />
			</div>
			<A class="next" href="javascript:void(0)" onfocus="this.blur()"></A><A class="prev"
				href="javascript:void(0)" onfocus="this.blur()"></A>
			<div id="hot-sale-scroll">
				<ul>
					<%=request.getAttribute("hot")%>
				</ul>
			</div>
		</div>
	</body>
</html>