<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>蔬菜首页-7号生活馆</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet" href="<%=basePath%>/scripts/dialog/cmsDialog.css"/>
		<script type="text/javascript" src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
		var lastButton;
		function changeButtonStatus(div){ 
			div.style.color='#FFFFFF';
			div.style.backgroundImage='url("../../images/page/button_bg02.png")';
			if(lastButton){
				lastButton.style.color='#000000';
				lastButton.style.backgroundImage='url("../../images/page/button_bg01.png")';
			}
			lastButton = div;
		}
		
		$(function(){
			$("#categoryPagecontantRight3 .goodsInfoRow .goodsInfoRowItem").bind("mouseover",function(){
				$(this).css("border","2px solid #CCCCCC");
				$($(this).children(".bellowGoodsSmallInfo")).css("background-color","#EEEEEE");
			}).bind("mouseout",function(){
				$(this).css("border","2px solid #FFFFFF");
				$($(this).children(".bellowGoodsSmallInfo")).css("background-color","#FFFFFF");
			});
		});
		</script>
<style type="text/css">
body{
	margin:0px;
}
#categoryPage {
	width: 1200px;
	position:relative;
	left:50%;
	margin-left:-600px;
}
/*商品热卖*/
#categoryPage-hot-sale{
	width: 1200px;
	height: 200px;
}

#categoryPagecontant {
	width: 1200px;
	height: 1670px;
	margin-top: 6px;
}

#categoryPagecontantLeft {
	display: inline;
	width: 238px;
	height: 1670px;
	float: left;
}

#categoryPagecontantRight {
	display: inline;
	float: right;
	margin-right: -2px;
	width: 950px;
	height: 1670px;
	padding-right: 
}

#categoryPageBottom {
	width: 1200px;
	height: 107px;
	margin-top: 6px;
}

#categoryPageBottomLeft {
	display: inline;
	width: 910px;
	height: 107px;
	float: left;
}

#categoryPageBottomRight {
	display: inline;
	float: right;
	margin-right: -2px;
	width: 280px;
	height: 107px;
	padding-right: 
}

#categoryPagecontantLeft1 {
	width: 240px;
	height: 742px;
	border: #D9D9D9 solid 1px;
}

#categoryPagecontantLeft2 {
	margin-top: 6px;
	width: 240px;
	height: 742px;
	border: #D9D9D9 solid 1px;
}

#categoryPagecontantLeft3 {
	margin-top: 6px;
	width: 240px;
	height: 170px;
}

#categoryPagecontantRight1 {
	width: 950px;
	height: 104px;
	background-image: url('../../images/page/cgpage_bg1.png');
}

.categoryPagecontantRight1_contant {
	width: 860px;
	height: 29px;
	line-height: 29px;
	border-bottom: #96C180 dotted 1px;
	font-size: 12px;
	padding-top: 5px;
}

.categoryPagecontantRight1_contantLast {
	width: 860px;
	line-height: 29px;
	font-size: 12px;
	padding-top: 5px;
}

#categoryPagecontantRight2 {
	width: 950px;
	height: 33px;
	line-height: 33px;
	border-top: #D9D9D9 solid 1px;
	padding-top: 4px;
}

#categoryPagecontantRight3 {
	width: 950px;
	height: 1528px;
	border-top: #96C180 solid 1px;
}

.divTitleRow {
	width: 240px;
	font-size: 16px;
	height: 31px;
	line-height: 31px;
	background-image: url('../../images/page/page_top1.png');
	vertical-align: middle;
}

#categoryPagecontantRight1Left {
	display: inline;
	float: left;
	width: 85;
	line-height: 40px;
}

#categoryPagecontantRight1Right {
	display: inline;
	float: right;
}

.goodsInfoDiv {
	text-align: center;
}

.p-price {
	line-height: 22px;
	font-size: 12px;
	color: #CE0303;
}

.p-name {
	line-height: 22px;
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}

a:hover {
	color: #CC0000;
}

.bellowGoodsSmallInfo {
	top: 0px;
	height: 30px;
	line-height: 30px;
	width: 100%;
	vertical-align: middle;
	padding: 5px 0px;
}

.goodsInfoRow {
	height: 260px;
	margin-top: 32px; *
	margin-top: 36px;
}

.goodsInfoRowItem {
	text-align: center;
	display: inline;
	float: left;
	margin-left: 43px;
	border: 2px solid #FFFFFF;
	width: 180px;
	height: 236px; *
	height: 240px;
}

#buttonInitClassLeft {
	width: 50px;
	line-height: 27px;
	height: 27px;
	border: #D9D9D9 solid 1px;
	display: inline;
	float: left;
	text-align: center;
	font-size: 12px;
	background-image: url('../../images/page/button_bg01.png');
	cursor: hand;
	margin-left: 5px;
}

.buttonInitClassPageUp {
	width: 66px;
	line-height: 28px;
	height: 28px;
	display: inline;
	float: right;
	background-image: url('../../images/page/button_pageUp_disabled.png');
	cursor: hand;
	margin-right: 5px;
}

.buttonInitClassPageDown {
	width: 66px;
	line-height: 28px;
	height: 28px;
	display: inline;
	float: right;
	background-image: url('../../images/page/button_pageDown.png');
	cursor: hand;
	margin-right: 5px;
}

#pageManagerDiv {
	width: 950px; * width : 948px;
	height: 30px;
	border: #96C180 solid 1px;
	margin-top: 35px;
	width: 948px;
}

/*商品底部信息*/
.goodsInfoRow img.bellowGoodsSmallInfo_img_minus,.goodsInfoRow img.bellowGoodsSmallInfo_img_add
	{
	margin-bottom: 5px; *
	margin-top: -27px;
}

.goodsInfoRow .goodsInfoRowItem input {
	margin-top: 3px;
	width: 22px;
	height: 21px;
	line-height: 21px;
	border: #C1C1C1 solid 1px;
	text-align: right;
	vertical-align: top;
}
/*指南/客服*/
#categoryPage-serviceFloors{
	
}
</style>
	</head>
	<body style="background-color: #FFFFFF;">
		<!-- begin of 页面 -->
		<div id="categoryPage">
			<!-- begin of 热销 -->
			<div id="categoryPage-hot-sale">
				<jsp:include page="../../pub/main/hot-sale.jsp" flush="true" />
			</div>
			<!-- end of  热销 -->
			<!-- begin of  内容区  -->
			<div id="categoryPagecontant">
				<!-- begin of 内容左 -->
				<div id="categoryPagecontantLeft">
					<!-- begin of 同类人气商品 -->
					<div id="categoryPagecontantLeft1">
						<div class="divTitleRow">
							<font style="font-size: 12px;">&nbsp;&nbsp;<b>同类人气商品</b> </font>
						</div>
						<div class="goodsInfoDiv">
							<div>
								<a href="http://www.360buy.com/product/1006521125.html"
									class="a-loseBlue"> <img src="../../images/page/temp.png"
										width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
								</a>
							</div>
							<div class='p-name'>
								<a href="#" class="p-name" title="茼蒿">茼蒿</a>
							</div>
							<div class='p-name'>
								规格：约500g/份
							</div>
							<div class="p-price">
								<strong>￥228.00/份</strong>
							</div>
							<div class="bellowGoodsSmallInfo"></div>
						</div>
						<div class="goodsInfoDiv">
							<div>
								<a href="http://www.360buy.com/product/1006521125.html"
									class="a-loseBlue"> <img src="../../images/page/temp.png"
										width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
								</a>
							</div>
							<div class='p-name'>
								<a href="#" class="p-name" title="茼蒿">茼蒿</a>
							</div>
							<div class='p-name'>
								规格：约500g/份
							</div>
							<div class="p-price">
								<strong>￥228.00/份</strong>
							</div>
							<div class="bellowGoodsSmallInfo"></div>
						</div>
						<div class="goodsInfoDiv">
							<div>
								<a href="http://www.360buy.com/product/1006521125.html"
									class="a-loseBlue"> <img src="../../images/page/temp.png"
										width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
								</a>
							</div>
							<div class='p-name'>
								<a href="#" class="p-name" title="茼蒿">茼蒿</a>
							</div>
							<div class='p-name'>
								规格：约500g/份
							</div>
							<div class="p-price">
								<strong>￥228.00/份</strong>
							</div>
							<div class="bellowGoodsSmallInfo"></div>
						</div>
					</div>
					<!-- end of 同类人气商品 -->
					<!-- begin of 最近浏览商品 -->
					<div id="categoryPagecontantLeft2">
						<div class="divTitleRow">
							<font style="font-size: 12px;">&nbsp;&nbsp;<b>最近浏览商品</b> </font>
						</div>
						<div class="goodsInfoDiv">
							<div>
								<a href="http://www.360buy.com/product/1006521125.html"
									class="a-loseBlue"> <img src="../../images/page/temp.png"
										width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
								</a>
							</div>
							<div class='p-name'>
								<a href="#" class="p-name" title="茼蒿">茼蒿</a>
							</div>
							<div class='p-name'>
								规格：约500g/份
							</div>
							<div class="p-price">
								<strong>￥228.00/份</strong>
							</div>
							<div class="bellowGoodsSmallInfo"></div>
						</div>
						<div class="goodsInfoDiv">
							<div>
								<a href="http://www.360buy.com/product/1006521125.html"
									class="a-loseBlue"> <img src="../../images/page/temp.png"
										width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
								</a>
							</div>
							<div class='p-name'>
								<a href="#" class="p-name" title="茼蒿">茼蒿</a>
							</div>
							<div class='p-name'>
								规格：约500g/份
							</div>
							<div class="p-price">
								<strong>￥228.00/份</strong>
							</div>
							<div class="bellowGoodsSmallInfo"></div>
						</div>
						<div class="goodsInfoDiv">
							<div>
								<a href="http://www.360buy.com/product/1006521125.html"
									class="a-loseBlue"> <img src="../../images/page/temp.png"
										width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
								</a>
							</div>
							<div class='p-name'>
								<a href="#" class="p-name" title="茼蒿">茼蒿</a>
							</div>
							<div class='p-name'>
								规格：约500g/份
							</div>
							<div class="p-price">
								<strong>￥228.00/份</strong>
							</div>
							<div class="bellowGoodsSmallInfo"></div>
						</div>
					</div>
					<!-- end of 最近浏览商品 -->
					<div id="categoryPagecontantLeft3">
						<img src="../../images/page/cg_ad01.png" />
					</div>
				</div>
				<!-- end of 内容左 -->
				<!-- begin of 内容右 -->
				<div id="categoryPagecontantRight">
					<!-- begin of 类别刷选 -->
					<div id="categoryPagecontantRight1">
						<div id="categoryPagecontantRight1Left">
							<b>&nbsp;蔬菜筛选</b>
						</div>
						<div id="categoryPagecontantRight1Right">
							<div class="categoryPagecontantRight1_contant">
								类别：
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">全部特色菜</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">叶菜类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">根茎类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">瓜果类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">豆类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">薯芋类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">菌藻类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">调味类</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">精品加工系列</font>
							</div>
							<div class="categoryPagecontantRight1_contant">
								品牌：
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">梅赛德斯</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">宝马</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">福特</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">丰田</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">本田</font>
							</div>
							<div class="categoryPagecontantRight1_contantLast">
								价格：
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">全部</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">1-30元</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">30-100元</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">100-200元</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">200-300元</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">300-500元</font>&nbsp;&nbsp;
								<font color="#076A00" style="cursor: hand;"
									onclick="cmsAlertAtt(111);">500元以上</font>
							</div>
						</div>

					</div>
					<!-- end of 类别刷选 -->
					<!-- begin of 排序 -->
					<div id="categoryPagecontantRight2">
						<font style="font-size: 12px; float: left;">&nbsp;&nbsp;排序：</font>
						<div id="buttonInitClassLeft" onclick="changeButtonStatus(this);">
							销量
						</div>
						<div id="buttonInitClassLeft" onclick="changeButtonStatus(this);">
							价格
						</div>
						<div id="buttonInitClassLeft" onclick="changeButtonStatus(this);">
							上架时间
						</div>
						<div class="buttonInitClassPageDown">

						</div>
						<div class="buttonInitClassPageUp">

						</div>
						<font style="float: right; font-size: 12px;">1/22&nbsp;&nbsp;&nbsp;&nbsp;</font>
						<font style="float: right; font-size: 12px;" color="DE4F02">共<b>105</b>个商品&nbsp;&nbsp;&nbsp;&nbsp;</font>
					</div>
					<!-- end of 排序 -->
					<!-- begin of 商品信息行 -->
					<div id="categoryPagecontantRight3">
						<div class="goodsInfoRow">
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
						</div>
						<div class="goodsInfoRow">
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
						</div>
						<div class="goodsInfoRow">
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
						</div>
						<div class="goodsInfoRow">
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
						</div>
						<div class="goodsInfoRow">
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
							<div class="goodsInfoRowItem">
								<div>
									<a href="http://www.360buy.com/product/1006521125.html"
										class="a-loseBlue"> <img src="../../images/page/temp.png"
											width="130" height="130" style="border: 0px;" alt="芝宝铜康斯坦丁">
									</a>
								</div>
								<div class='p-name'>
									<a href="#" class="p-name" title="茼蒿">茼蒿</a>
								</div>
								<div class='p-name'>
									规格：约500g/份
								</div>
								<div class="p-price">
									<strong>￥228.00/份</strong>
								</div>
								<div class="bellowGoodsSmallInfo">
									<img src="../../images/page/button_sub.png"
										class="bellowGoodsSmallInfo_img_minus" />
									<input type="text" value="01" align="bottom">
									<img src="../../images/page/button_sum.png"
										class="bellowGoodsSmallInfo_img_add" />
									<img src="../../images/page/button_buy.png" />
								</div>
							</div>
						</div>
						<div id="pageManagerDiv">
							分页控件
						</div>
					</div>
					<!-- end of 商品信息行 -->
				</div>
				<!-- end of  内容右-->
			</div>
			<!-- end of 内容区 -->
			<!-- begin of 底楼广告 -->
			<div id="categoryPageBottom">
				<div id="categoryPageBottomLeft">
					<img src="../../images/page/cg_ad02.png" />
				</div>
				<div id="categoryPageBottomRight">
					<img src="../../images/page/cg_ad03.png" />
				</div>
			</div>
			<!-- end of 底楼广告 -->
			<!-- begin of 指南/客服 -->
			<div id="categoryPage-serviceFloors">
				<jsp:include page="../../pub/main/service.jsp" flush="true" />
			</div>
			<!-- end of 指南/客服 -->
			<!-- begin of 版权 -->
			<div id="categoryPage-copyRight">
				<jsp:include page="../../pub/main/copyRight.jsp" flush="true" />
			</div>
			<!-- end of 版权 -->
		</div>
		<!-- end of 页面 -->
	</body>
</html>
