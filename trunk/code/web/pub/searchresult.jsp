<%@page import="com.spark.cms.base.constant.SearchConstant"%>
<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<script type="text/javascript" src="<%=basePath%>/pub/common/pagin.js"></script>
<script type="text/javascript" src="<%=basePath%>/scripts/cookieutil.js"></script>
<script type="text/javascript" src="<%=basePath%>/scripts/seven.js"></script>
<% 
String initData = (String)request.getAttribute(SearchConstant.INITRESULT);
%>
<style type="text/css">
*{
	padding: 0px;
	margin: 0px;
}

.categoryPagecontantRight {
	float: left;
	width: 975px;
	height: 1730px;
}
.categoryPagecontantRight .categoryPagecontantRight1{
	float:left;
	width:975px;
	min-height:40px;
	border-top:2 solid green;
	margin-top: 2px;
	display:inline;
	text-align:center;
}
.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Left{
	float:left;
	width: 85;
	min-height: 40px;
	padding-top: 16px;
	font-size: 14px;
	font-family: "宋体"
	
}
.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Right{
	float:left;
	text-align: left;
	padding-left:5px;
}

.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Right a{
	padding-left:15px;
	color: #076A00;;
	cursor: hand;
}

.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Right a:HOVER{
	padding-left:15px;
	color: #CE0303;;
}
.categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant {
	width: 875px;
	height: 35px;
	line-height: 35px;
	font-size: 13px;
	padding-top: 5px;
	font-family:"宋体";
	border-bottom: #96C180 dotted 1px;
}

.categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant .currentover{
	color: #CE0303;
	text-decoration: underline;
}

.categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant .current{
	color: #CE0303;
	text-decoration: underline;
}

.categoryPagecontantRight .categoryPagecontantRight2{
	float:left;
	width:975px;
	height: 35px;
	line-height: 35px;
	text-align: left;
	margin-top:5px;
	border-bottom:1px solid #96C180 ;
}

.categoryPagecontantRight .categoryPagecontantRight2 .orderDiv{
	float:left;
	width:450px;
	height: 35px;
	line-height: 35px;
	text-align: left;
}
.categoryPagecontantRight .categoryPagecontantRight2 .orderDiv .orderBy{
	margin-left: 15px;
	float: left;
	line-height: 33px;
	height: 33px;
	vertical-align:bottom;
	margin-top: 3px;
}

.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv{
	float:left;
	width:490px;
	height: 33px;
	line-height: 33px;
	text-align: right;
}
.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv #productNum{
	float:left;
	width:280px;
	text-align: right;
	color: #DE4F02;
	font-size: 12;
}
.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv #currentPage{
	float:left;
	width:50px;
	text-align: center;
	font-size: 14;
}

.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv .buttonInitClassPageUp{
	width: 66px;
	line-height: 28px;
	height: 28px;
	float: left;
	background: url('<%=basePath%>/images/page/button_pageDown_disabled.png') no-repeat;
	cursor: hand;
	margin-left: 15px;
	margin-top: 3px;
	
}

.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv .buttonInitClassPageDown{
	width: 66px;
	line-height: 28px;
	height: 28px;
	float: left;
	background: url('<%=basePath%>/images/page/button_pageUp_disabled.png') no-repeat;
	cursor: hand;
	margin-left: 5px;
	margin-top: 3px;
}

.categoryPagecontantRight .categoryPagecontantRight2 .buttonInitClassLeft{
	float:left;
	width: 82px;
	line-height: 29px;
	height: 29px;
	cursor: hand;
	margin-left:5px;
	margin-top:4px;
}
/*过滤 -> 【销量】【价格】【上架时间】*/
.categoryPagecontantRight2 #btnSale{
	background-image:url('<%=basePath%>/images/page/btnSale_normal.png');
	background-repeat: no-repeat; 
}
.categoryPagecontantRight2 #btnPrice{
	background-image:url('<%=basePath%>/images/page/btnPrice_asc.png');
	background-repeat: no-repeat;
}
.categoryPagecontantRight2 #btnDate{
	background-image:url('<%=basePath%>/images/page/btnDate_normal.png');
	background-repeat: no-repeat;
}

.categoryPagecontantRight .categoryPagecontantRight3 {
	width: 975px;
	float:left;
	text-align: center;
	margin:0px;
	padding:0px;
	height: 1560px;
}
/*商品行*/
.categoryPagecontantRight .goodsInfoRow {
	height: 250px;
	float: left;
	width:975px;
	margin-top:10px;
	text-align: left;
}
/*商品项*/
.categoryPagecontantRight .goodsInfoRowItem {
	text-align: left;
	display: inline;
	float: left;
	width: 235px;
	height: 250px; 
	text-align: center;
	margin-left:7px;
}
.categoryPagecontantRight .categoryPagecontantRight3 .p-price {
	line-height: 22px;
	font-size: 12px;
	color: #CE0303;
}

.categoryPagecontantRight .categoryPagecontantRight3 .p-name {
	line-height: 22px;
	font-size: 12px;
	color: #000000;
	width:200px;
	white-space:nowrap;
	overflow:hidden;
	text-decoration: none;
}
.categoryPagecontantRight .categoryPagecontantRight3 div.p-name a:hover{
	color: #FF0000;
	text-decoration: underline;
}

.goodsInfoRow img.bellowGoodsSmallInfo_img_minus,
.goodsInfoRow img.bellowGoodsSmallInfo_img_add,
.goodsInfoRow img.bellowGoodsSmallInfo_img_buy
{
	vertical-align: middle;
	cursor: hand;
}

.goodsInfoRow img.bellowGoodsSmallInfo_img_add{
	margin: 0px 2px;
}

.categoryPagecontantRight .goodsInfoRow .goodsInfoRowItem input {
	width: 22px;
	height: 21px;
	line-height: 21px;
	border: #C1C1C1 solid 1px;
	text-align: center;
	vertical-align: middle;
}
/*商品图片 -> 浮动框*/
.categoryPagecontantRight3 a.goodsCellImage{
	display:inline-block;
	width: 220px;
	height:150px;
	border: 1px solid #FFFFFF;
}
.categoryPagecontantRight3 a.goodsCellImage img{
	margin-top: 5px;
	width: 140px;
	height: 140px;
}
.categoryPagecontantRight3 a.goodsCellImage:hover{
	border: 1px solid #DDDDDD;
	background-color: RGB(254,254,254);
}

.categoryPagecontantRight #pageManagerDiv{
	float:left;
	width: 100%;
	height: 40px;
	line-height:40px;
	text-align:right;
	*padding-top: 10px;
	margin-top: 5px;
}


/*分页控件*/
.pagin a,.pagin span {
	height: 20px;
	padding: 3px 10px;
	border: 1px solid #ccc;
	margin-left: 2px;
	font-family: arial;
	line-height: 20px;
	font-size: 14px;
	*vertical-align: middle;
	overflow: hidden;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}

.pagin .text,.pagin .current {
	border: none;
	padding: 4px 11px;
}

.pagin a:link,.pagin a:visited {
	color: #005aa0;
	text-decoration:none;
}

.pagin a:hover,.pagin a:active {
	background: #005aa0;
	color: #fff;
	text-decoration: none;
}

.pagin .current,.pagin .current:link,.pagin .current:visited {
	color: #f60;
	font-weight: bold;
}

.pagin b {
	dispaly: block;
	position: absolute;
	top: 9px;
	width: 5px;
	height: 9px;
	background-image: url(<%=basePath%>/images/page/pagin.png);
	background-repeat: no-repeat;
	overflow: hidden;
}

.pagin .prev,.pagin .next,.pagin .prev-disabled,.pagin .next-disabled {
	position: relative;
	padding-top: 5px;
	height: 18px;
	line-height: 18px;
}

.pagin .prev-disabled,.pagin .next-disabled {
	color: #ccc;
	cursor: default;
}

.pagin .prev,.pagin .prev-disabled {
	padding-left: 12px;
}

.pagin .prev b {
	left: 3px;
	background-position: -6px 0px;
}

.pagin .prev-disabled b {
	left: 3px;
	background-position: -18px 0px;
}

.pagin .next,.pagin .next-disabled {
	padding-right: 12px;
}

.pagin .next b {
	right: 3px;
	background-position: 0px 0px;
}

.pagin .next-disabled b {
	right: 3px;
	background-position: -12px 0px;
}

.pagin-m a,.pagin-m span {
	height: 14px;
	line-height: 14px;
	font-size: 12px;
}

.pagin-m b {
	top: 5px;
}

.pagin-m .prev,.pagin-m .next,.pagin-m .prev-disabled,.pagin-m .next-disabled
	{
	padding-top: 3px;
	height: 14px;
	*height: 22px;
	line-height: 14px;
	*line-height: 16px;
}
.pagin a, .pagin span {
	height: 14px;
	*height: 22px;
}
</style>
	</head>
	<body>
		<div class="categoryPagecontantRight">
			<!-- begin of 类别刷选 -->
			<div class="categoryPagecontantRight1">
				<div class="categoryPagecontantRight1Left">
					<b>&nbsp;商品筛选</b>
				</div>
				<div class="categoryPagecontantRight1Right">
				      <div class="categoryPagecontantRight1_contant" id="priceNavigator"></div>
				</div>
			</div>
			<!-- end of 类别刷选 -->
			<!-- begin of 排序 -->
			<div class="categoryPagecontantRight2">
			  	<div class="orderDiv" id='search_orderDiv'>
				  	<!-- span class="orderBy">排序：</span>
					<span class="buttonInitClassLeft" id='btnSale' onclick="_searchResultApp.sort('0');">
						销  量
					</span>
					<span class="buttonInitClassLeft" id='btnPrice' onclick="_searchResultApp.sort('1');">
						价  格
					</span>
					<span class="buttonInitClassLeft" id="btnDate" onclick="_searchResultApp.sort('2');">
						上架时间
					</span> -->
			  	</div>
			  	<div class="showInfoDiv">
			  		<span id="productNum">共 <b id="allProductNum">0</b> 个商品</span>
			  		<span id="currentPage">0/0</span>
			  		<!-- span class="buttonInitClassPageDown" id="retButton" onclick="product.doPageDown()"></span>
			  		<span class="buttonInitClassPageUp" id="preButton" onclick="product.doPageUp()"></span> -->
			  		<span class="buttonInitClassPageDown" id="v_prePageButton"></span>
			  		<span class="buttonInitClassPageDown" id="v_nextPageButton"></span>
				</div>
			</div>
			<div class="categoryPagecontantRight3">
			</div>
			<div id="pageManagerDiv" class='pagin pagin-m fr'></div>
		</div>
	</body>
<script type="text/javascript">
var _searchResultApp = null;
var _initSearchDataStr = '<%=initData%>';
var _pageSize = '<%=SearchConstant.PAGESIZE %>';
$(document).ready(function() {
	_searchResultApp = new SearchResultApp();
});
function SearchResultApp() {
	var PAGESIZE = Number(_pageSize);
	var totalCount = 0;
	var searchKey = null;
	var priceBegin = -1;
	var priceEnd = -1;
	var sortWay = "1";
	var sortType = 0;
	
	/*
	this.sort = function(way) {
		if ("0" == way) {
			$("#btnPrice").removeClass("buttonInitClassLeftOver");
			$("#btnDate").removeClass("buttonInitClassLeftOver");
			$("#btnSale").addClass("buttonInitClassLeftOver");
		} else if ("1" == way){
			$("#btnSale").removeClass("buttonInitClassLeftOver");
			$("#btnDate").removeClass("buttonInitClassLeftOver");
			$("#btnPrice").addClass("buttonInitClassLeftOver");
		} else if ("2" == way) {
			$("#btnSale").removeClass("buttonInitClassLeftOver");
			$("#btnPrice").removeClass("buttonInitClassLeftOver");
			$("#btnDate").addClass("buttonInitClassLeftOver");
		}
		if (way == sortWay) {
			sortType = !sortType ? 1 : 0;
		}
		sortWay = way;
		reload();
	};
	*/
	this.adjustGoodsCount = function(goodsId, count) {
		if (!goodsId || !count) {
			return;
		}
		var id = "#txt_" + goodsId;
		var oldValue = $(id).val();
		var newValue = Number(oldValue) + Number(count);
		if (newValue < 0) {
			newValue = 0;
		}
		$(id).val(newValue);
	};
	
	this.buyGoods = function(goodsId) {
		var id = "#txt_" + goodsId;
		var count = $(id).val();
		cookieutil.addGoodsToShoppingCar(goodsId, count);
		if(_productListMenuApp) {
			_productListMenuApp.loasShoppingCharCount();
		}
		cmsAlertSuccess("提示","添加成功！");
	};
	
	this.countChangeListener = function(eventId) {
		var id = "#" + eventId;
		var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
		if ($(id).val().match(reg) == null) {
			$(id).val($(id).attr("preValue"));
		} else {
			$(id).attr("preValue", $(id).val());
		}
	};
	/**
	 * 加载指定页数据
	 */
	var load = function(pageIndex) {
		$.ajax({
			type: 'post',
			url: mainWeb + '/front/common/searchGoods',
			//data: {'searchKey': _searchKey, 'pageSize': PAGESIZE, 'pageIndex': pageIndex},
			data: "searchKey=" + encodeURIComponent(searchKey) + "&pageNumber=" + pageIndex + "&priceBegin=" + priceBegin 
					+ "&priceEnd=" + priceEnd + "&sortType=" + sortType + "&sortWay=" + sortWay,
			async: false,
			dataType: 'json',
			success: function(data) {
				renderData(data.goodsList);
			},
			error: function() {
				renderError();
			}
		});
	};
	
	var renderData = function(resultData) {
		if (resultData == null || resultData.length < 1) {
			
		}
		var html = "";
		var columnCount = 4;
		for (var index = 0; index < resultData.length; index++) {
			if (index % columnCount == 0) { // row begin
				html += "<div class=\"goodsInfoRow\">";
			}
			
			html += "<div class='goodsInfoRowItem'>";
			html += "<div>";
			html += "<a class='goodsCellImage' href='" + mainWeb + "/front/toGoodsInfoPage?id=" + resultData[index].recid + "'";
			html += " target='_blank'><img width='130px' height='130px'";
			html += " src='" + mainWeb + resultData[index].picturepath1 + "' /></a>";
			html += "</div>";
			html += "<div class='p-name'>";
			html += "<a href='" + mainWeb + "/front/toGoodsInfoPage?id=" + resultData[index].recid + "'";
			html += " class='p-name'>" + resultData[index].goodsname + "</a>";
			html += "</div>";
			html += "<div class='p-name'>规格：" + resultData[index].goodsspec + "</div>";
			html += "<div class='p-price'>";
			html += "<strong>￥" + resultData[index].realprice + "/" + resultData[index].goodsunit + "</strong>";
			html += "</div>";
			html += "<div class='bellowGoodsSmallInfo'>";
			html += "<img onclick=\"_searchResultApp.adjustGoodsCount('" + resultData[index].recid + "','-1') \"";
			html += " src='" + mainWeb + "/images/page/button_sub.png'";
			html += " class='bellowGoodsSmallInfo_img_minus' /> <input";
			html += " id='txt_" + resultData[index].recid + "' type='text' value='1'";
			html += " align='center' preValue='0'";
			html += " onchange=\"_searchResultApp.countChangeListener('txt_" + resultData[index].recid + "')\">";
			html += "<img onclick=\"_searchResultApp.adjustGoodsCount('" + resultData[index].recid + "','1') \"";
			html += " src='" + mainWeb + "/images/page/button_sum.png'";
			html += " class='bellowGoodsSmallInfo_img_add' /> ";
			html += "<img onclick=\"_searchResultApp.buyGoods('" + resultData[index].recid + "') \"";
			html += " src='" + mainWeb + "/images/page/buy25-01.png'";
			html += " class='bellowGoodsSmallInfo_img_buy'";
			html += " onmouseover='product_buy_over(this)'";
			html += " onmouseout='product_buy_out(this)' />";
			html += "</div>";
			html += "</div>";
			if ((index + 1) % columnCount == 0) { // row end
				html += "</div>";
			}
		}
		$('.categoryPagecontantRight3').html(html);
	};
	
	var renderError = function() {
		
	};
	/**
	 * 重新加载数据
	 */
	var reload = function() {
		$.ajax({
			type: 'post',
			url: mainWeb + '/front/common/searchGoods',
			data: "searchKey=" + searchKey + "&pageNumber=1" + "&priceBegin=" + priceBegin 
					+ "&priceEnd=" + priceEnd + "&sortType=" + sortType + "&sortWay=" + sortWay,
			dataType: 'json',
			success: function(data) {
				totalCount = data.totalCount;
				renderData(data.goodsList);
				initPaging();
			}
		});
	};
	
	var initPaging = function() {
		//
		var paginCmp = new CmsPaging({
			parentId: 'pageManagerDiv',
			count: totalCount,
			pageSize: PAGESIZE,
			actionListener: function(pageIndex) {
				load(pageIndex);
				loadTitlaPageBar(pageIndex, paginCmp.getTotalPageCount());
			},
			isActionOnLoad: false
		});
		
		//
		if (totalCount > 0) {
			loadTitlaPageBar(1, paginCmp.getTotalPageCount());
		} else {
			loadTitlaPageBar(0, paginCmp.getTotalPageCount());
		}
		
		//
		$("#v_prePageButton").unbind('click');
		$("#v_prePageButton").bind('click', function() {
			paginCmp.prePage();
		});
		$("#v_nextPageButton").unbind('click');
		$("#v_nextPageButton").bind('click', function() {
			paginCmp.nextPage();
		});
	};
	
	var loadTitlaPageBar = function(pageIndex, pageCount) {
		$("#allProductNum").html(totalCount);
		$("#currentPage").html(pageIndex + "/" + pageCount);
		if(pageIndex > 1) {
			 $("#v_prePageButton").css("background-image","url(" + mainWeb + "/images/page/button_pageUp.png)");
		} else {
			$("#v_prePageButton").css("background-image", "url(" + mainWeb + "/images/page/button_pageUp_disabled.png)");
		}
		if(pageIndex < pageCount) {
		 	$("#v_nextPageButton").css("background-image","url(" + mainWeb + "/images/page/button_pageDown.png)");
		} else {
			$("#v_nextPageButton").css("background-image", "url(" + mainWeb + "/images/page/button_pageDown_disabled.png)");
		}
	};
	
	var initSortBar = function() {
		GoodsSortBar({
			parentId: 'search_orderDiv',
			onSortAction: function(way, type) {
				sortType = type;
				sortWay = way;
				reload();
			}
		});
	};
	
	var initPriceNavigator = function() {
		PriceNavigator({
			parentId: 'priceNavigator',
			onNodeSelection: function(begin, end) {
				if (Number(begin) != "NaN" && Number(end) != "NaN") {
					priceBegin = begin;
					priceEnd = end;
					reload();
				}
			}
		});
	};
	var initSearchText = function() {
		var $searchText = $('#searchKey_main');
		if ($searchText) {
			$searchText.val(searchKey);
		}
	};
	
	var init = function() {
		var initData = eval('(' + _initSearchDataStr + ')');
		totalCount = initData.totalCount;
		renderData(initData.goodsList);
		searchKey = initData.searchKey;
		//initData();
		initSearchText();
		initPriceNavigator();
		initSortBar();
		initPaging();
		_initSearchDataStr = null; // 初始化后就用不到了，清空
	};
	init();
}
</script>
</html>