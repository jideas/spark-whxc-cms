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
	padding-left: 1px;
}

#category-level span {
	margin: 0px 3px;
	height: 18px;
	line-height: 18px;
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
	margin-left: 80px;
	width: 1040px;
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
	width: 260px;
	height: 180px;
	margin-top: -15px; *
	margin-top: 0px;
}

/*滚动子项 -> 图片*/
#hot-sale-scroll .scroll-image {
	float: left;
	width: 130px;
	height: 180px;
	line-height: 180px;
	padding-top: 25px;
}

#hot-sale-scroll .scroll-image img {
	border: 0px;
}

/*滚动子项 -> 详情*/
#hot-sale-scroll .scroll-detail {
	float: right;
	width: 120px;
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

#hot-sale-scroll .scroll-detail a,#hot-sale-scroll .scroll-detail a:link
	{
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
		<!-- <div id="category-level">
			<span>精美购物袋</span>
		</div> -->
		<div id="hot-sale">
			<div class="ad-left-top">
				<img src="<%=basePath%>/images/page/hot-sale-HOT.png" />
			</div>
			<A class="next" href="javascript:void(0)" onfocus="this.blur()"></A><A class="prev"
				href="javascript:void(0)" onfocus="this.blur()"></A>
			<div id="hot-sale-scroll">
				<ul>
					<%=request.getAttribute("footerHtml")%>
				</ul>
			</div>
		</div>
	</body>
	<script type="text/javascript">
//【立即抢购】悬浮效果
$("#hot-sale li input").bind("mouseover",function(){
	$(this).css("background-image","url('<%=basePath%>/images/page/Qbuy02.png')");
}).bind("mouseout",function(){
	$(this).css("background-image","url('<%=basePath%>/images/page/Qbuy01.png')");
});
//【购买】悬浮效果
function product_buy_over(img){
	$(img).attr("src","<%=basePath%>/images/page/buy25-02.png");
}
function product_buy_out(img){
	$(img).attr("src","<%=basePath%>/images/page/buy25-01.png");
}
//图片预览移动效果,页面加载时触发
$(function(){
	var tempLength = 0; //临时变量,当前移动的长度
	var viewNum = 4; //设置每次显示图片的个数量
	var moveNum = 4; //每次移动的数量
	var moveTime = 1000; //移动速度,毫秒
	var scrollDiv = $("#hot-sale-scroll ul"); //进行移动动画的容器
	var scrollItems = $("#hot-sale-scroll ul li"); //移动容器里的集合
	var moveLength = scrollItems.eq(0).width() * moveNum; //计算每次移动的长度
	var countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).width(); //计算总长度,总个数*单个长度
	 
	//下一张
	$("#hot-sale a.next").bind("click",function(){
		if(tempLength < countLength){
			if((countLength - tempLength) > moveLength){
				scrollDiv.animate({left:"-=" + moveLength + "px"}, moveTime);
				tempLength += moveLength;
			}else{
				scrollDiv.animate({left:"-=" + (countLength - tempLength) + "px"}, moveTime);
				tempLength += (countLength - tempLength);
			}
		}else{
			scrollDiv.animate({left:"+=" + countLength + "px"}, moveTime);
			tempLength = 0;
		}
	});
	//上一张
	$("#hot-sale a.prev").bind("click",function(){
		if(tempLength > 0){
			if(tempLength > moveLength){
				scrollDiv.animate({left: "+=" + moveLength + "px"}, moveTime);
				tempLength -= moveLength;
			}else{
				scrollDiv.animate({left: "+=" + tempLength + "px"}, moveTime);
				tempLength = 0;
			}
		}else{
			scrollDiv.animate({left:"-=" + countLength + "px"}, moveTime);
			tempLength = countLength;
		}
	});
	//鼠标移上 -> 清除定时器
	$("#hot-sale").bind("mouseover",function(){
		clearTimeout(hot_sale_timer);
	}).bind("mouseout",function(){
		hot_sale_timer = setTimeout("func_hot_sale_timer()",5000);
	});		
});
//热销定时器
var hot_sale_timer = setTimeout("func_hot_sale_timer()",5000);
function func_hot_sale_timer(){		
	$("#hot-sale a.next").click();
	hot_sale_timer = setTimeout("func_hot_sale_timer()",5000);
}
</script>
</html>