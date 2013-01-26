<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	com.spark.front.manager.PageManager pageManager = com.spark.front.manager.JSPServericeProvider
			.getPageManager(request);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>7号生活馆</title>
		<meta http-equiv="Content-Type" content="text/html;charset=GBK" />
		<meta http-equiv="Expires" CONTENT="0">  
		<meta http-equiv="Cache-Control" CONTENT="no-cache">
		<meta http-equiv="Pragma" CONTENT="no-cache">
		<meta http-equiv="keywords" content="蔬菜 生鲜  超市 7号生活馆 粮油">  
		
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/pub/menu.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/pub/floor.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/pub/backtop.js"></script>
		<link type="text/css" href="<%=basePath%>/css/commonFloor.css"
			rel="stylesheet">
		<link type="text/css" href="<%=basePath%>/css/menu.css"
			rel="stylesheet">
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/cookieutil.js"></script>
		<script type="text/javascript">function toShopingCar()
			{
			window.location.href = mainWeb + "/front/shopingCar/getGoods";
			}</script>
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
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}

.mainFloor {
	display: block;
	float: left;
	width: 1200px;
	height: 260px;
}

#productFloors {
	display: block;
	float: left;
	width: 1200px;
}

#serviceFloors {
	float: left;
}

#copyRight {
	float: left;
}

/**
* menu 
*/
.nav .categoryItems {
	display: block;
}

/**回到顶部**/
#goTopBtn {
	POSITION: absolute;
	float: right;
	display: none;
	text-align: right;
	line-height: 60px;
	WIDTH: 28px;
	HEIGHT: 60px;
	FONT-SIZE: 12px;
	CURSOR: hand;
	RIGHT: 0px;
	bottom: 0px;
	z-index: 99;
	zoom: 1;
}

#carShopingInfo {
	background:
		url('<%=basePath%>/images/page/my7L-myorderlistheaderbg.png');
	height: 30px;
	line-height: 30px;
	font-family: '宋体';
	font-size: 12px;
	font-weight: bolder;
	text-align: left;
	padding-left: 7px;
	flost: left;
}
</style>
	</head>
	<body style="position: relative;">
		<!-- begin of 回到顶部 -->
		<DIV id="goTopBtn">
			<IMG border=0 src="<%=basePath%>/images/page/lanren_top.png"
				title="返回顶部">
		</DIV>
		<!-- end of 回到顶部 -->
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="index">
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
			</div>
			<div id="mainMenu">
				<div class="nav">
					<!--导航 -->
					<div id="category" class="category">
						<div class="all-sort">
							<h3>
								全部商品分类
							</h3>
						</div>
						<div class="categoryItems">
							<%
								out.println(pageManager.getGoodsCategoryTreeHtml());
							%>
						</div>
					</div>
					<div class="menuList">
						<div class="nav1-list">
							<ul>
								<%=pageManager.getMenuHtmlIndex(request.getContextPath())%>
							</ul>
						</div>
						<div class="caddy">
							<div class="caddyList" id="shoppingCar">
								<div class="caddyHead">
									<ul>
										<li>
											<img src="<%=basePath%>/images/page/caddy-full.png"
												width="27" height="21" />
										</li>
										<li>
											<a href="javascript:void(0)" onclick="toShopingCar()">购物车</a>
										</li>
									</ul>
									<span></span>
								</div>
								<div id='carContent' class='poptip'>
									<div id="carShopingInfo" style="z-index: 10;">
										最新加入的商品
									</div>
									<div id="noGoods">
										购物车中还没有商品，赶紧选购吧！
									</div>
									<div id="carShopingItems">

										<table cellpadding="0" cellspacing="0">
										</table>
									</div>
									<div id="carShopingOperate" style="height: 60px;">
									</div>
								</div>
							</div>
							<div class="nav-line">
								<img src="<%=basePath%>/images/page/nav-line.png" alt="l" />
							</div>
							<div class="jiesuan">
								<ul>
									<li>
										<a href="javascript:void(0)" onclick="toShopingCar()">去结算</a>
									</li>
									<li>
										<img src="<%=basePath%>/images/page/jiesuan-arrow.png"
											width="16" height="16" />
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="menuRight"></div>

					<div class="slider">
						<jsp:include page="/pub/main/slider.jsp" flush="true" />
					</div>
					<div class="shoppingNews">
						<jsp:include page="/pub/main/ad-right.jsp" flush="true" />
					</div>
				</div>
			</div>
			<div id="mainFloorMainDiv"><%=pageManager.getMainFloorHtml("0", "6", basePath)%></div>
			<div id="productFloors"><%=pageManager.getFloorHtml("0", "8", basePath)%></div>

			<div id="serviceFloors">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
	<script type="text/javascript">
	$(function(){
		productCategory.init({hiddenContain:false}).addAction();
		menu.active();
		//回到顶部
		goTopMenu.active("goTopBtn");
		floor.active();
	});
	
	
	
	var _indexApp = null;
	function IndexApp() {
		this.loasShoppingCharCount = function(count) {
			if (count < 0) {
				count = cookieutil.getShoppingCarGoodsCount();
			}
			$('.caddyHead span').html("<b>" + count + "</b>");
		};
		
		var init = function() {
			
		};
		
		init();
	}
	/**
	*购物车效果控制 
	*/
	$(function(){
		var _thiscontain = $(".nav .caddy .caddyList #carContent");
		var _cardHeader = $(".nav .caddy .caddyList .caddyHead");
		var iscartHead = false;
		var iscartBody = false;
		
		var carContent = new ShowShoppingCarDetail();
		_indexApp = new IndexApp();
		
		_indexApp.loasShoppingCharCount(Number('-1'));
		
		$(".nav .caddy .caddyList .caddyHead").bind("mouseover",function(){
			iscartHead = true;
			doBindAction(true);
			setTimeout(function(){doAction();},1);
			carContent.show();
		}).bind("mouseleave",function(){
			iscartHead = false;
			setTimeout(function(){doAction();},1);
			//carContent.hide();
		});
		
		function doAction(){
			if(iscartHead||iscartBody){
				_cardHeader.addClass("hover");
				_thiscontain.css("display","block");
			}else{
				_cardHeader.removeClass("hover");
				_thiscontain.css("display","none");
				doBindAction(false);
			};
		};
		
		function doBindAction(t){
			if(t){
				_thiscontain.bind("mouseenter",function(){
					iscartBody = true;
					 setTimeout(function(){doAction();},100);
					
				}).bind("mouseleave",function(){
					iscartBody = false;
					setTimeout(function(){doAction();},200);
				});
			}else{
				_thiscontain.unbind("mouseenter",function(){
					 isBody = true;
					 setTimeout(function(){doAction();},1);
				}).unbind("mouseleave",function(){
					 isBody = false;
					 setTimeout(function(){doAction();},1);
				});
			}
		}
		
	});

function caddy_tr_over(tr){
	$(tr).addClass("caddy_tr_over");
}
function caddy_tr_out(tr){
	$(tr).removeClass("caddy_tr_over");
}

function ShowShoppingCarDetail() {
	$.ajaxSetup({cache:false});
	// step 2 ajax方式从后台取商品详情，并显示
	var $table = $('#carShopingItems table');
	var $summaryInfo = $('#carShopingOperate');
	
	
	this.show = function() { 
		load();
	};
	
	this.hide = function() {
		$table.empty();
	};
	
	var load = function() {
		//loadWait();
		$.ajax({
			url: mainWeb + '/front/shopingCar/getGoodsListAsy',
			dataType: 'json',
			success: function(data) {
				var html = "";
				var infoHtml = "";
				if (null == data || data.length == 0) {
					//html += "<tr>";
					//html += "<td style='height:50px;width:100%;float:left;text-align:left;'>购物车中还没有商品，赶紧选购吧！</td>";
					//html += "</tr>";
					$("#noGoods").css("display","block");
					$("#carShopingOperate").css("display","none");
					$("#carShopingItems").css("display","none");
					
					$table.html(html);
					
					//infoHtml += "<div class='caddy_count'>共<span>0</span>类<span>0</span>件商品</div>";
					//infoHtml += "<div class='caddy_money'>总计:￥<span>0</span>元</div>";
					$summaryInfo.html(infoHtml);
					_indexApp.loasShoppingCharCount(0);
					cookieutil.setShoppingCarGoods(data);
					return;
				}
				/**
				 *  链接
				 *
				 **/
				var totalCount = 0;
				var totalAmount = 0;
				for (var index = 0; index < data.length; index++) {
					html += "<tr id='shpcar_row-" + data[index].goodsId + "' onmouseover='caddy_tr_over(this)' onmouseout='caddy_tr_out(this)'>";
					html += "<td class='cardShop_img'>";
					html += "	<img style='width:50px;height:50px;' src=" + basePath + "/" + data[index].miniPicturePath + " />";
					html += "</td>";
					html += "<td class='carShop_direction'>";
					html += "	<a href='" + mainWeb + "/front/toGoodsInfoPage?id=" + data[index].goodsId  + "'> " + data[index].goodsName + "(" + data[index].spec + ")" + "</a>";
					html += "</td>";
					html += "<td class='carShop_price'>";
					if (data[index].vantagesGoods) {
						html += "	<b>" + data[index].vantagesCost.toFixed(2) + "积分</b><span>×" + data[index].count + "</span>";
						html += "	<p><a href='javascript:void(0)' id='shpcar_del_vantage-" + data[index].goodsId + "'>删除</a></p>";
					} else {
						html += "	<b>￥" + data[index].price.toFixed(2) + "</b><span>×" + data[index].count + "</span>";
						html += "	<p><a href='javascript:void(0)' id='shpcar_del-" + data[index].goodsId + "'>删除</a></p>";
					}
					html += "</td>";
					html += "</tr>";
					
					totalCount += data[index].count;
					totalAmount += data[index].totalAmount;
				}
				$table.html(html);
				if(data.length>5&&($.browser.msie))
				{$("#carShopingItems").css("height","316px");}
				
				//infoHtml += "<div class='caddy_count'>共" + totalCount + "</span>件商品</div>";
				infoHtml += "<div class='caddy_money'>共" + totalCount + "件商品&nbsp;&nbsp;总计:<span>￥" + totalAmount.toFixed(2) + "</span></div>";
				infoHtml += "<div class='caddy_goBuy'><a href='" + mainWeb + "/front/shopingCar/getGoods'><img src=" + basePath + "/images/page/addToShoppingCar_01.png width='122px' height='32px' /></a></div>";
				$summaryInfo.html(infoHtml);
				
				_indexApp.loasShoppingCharCount(data.length);
				addDeleteAction();
			}
		});
		
	};
	
	var addDeleteAction = function() {
		$("a[id|='shpcar_del']").each(function() {
			var goodsId =  $(this).attr('id').split('-')[1];
			$(this).unbind('click');
			$(this).bind('click', function() {
				$('#shpcar_row-' + goodsId).remove();
				cookieutil.removeGoodsFromShoppingCar(goodsId);
				$table.trigger('change');
			});
		});
	};
	
	var loadWait = function() {
		//var html = "";
		//html += "<tr>";
		//html += "<td class=''>加载中,请稍候....</td>";
		//html += "</tr>";
		//$table.html(html);
	};
	
	var init = function() {
		$table.unbind('change');
		$table.bind('change', function() {
			load();
		});
	};
	
	init();
}

function toShopingCar()
{
	window.location.href = mainWeb + "/front/shopingCar/getGoods";
}

function toCategoryPage(id){
	window.location.href = mainWeb + "/front/toCategoryPage/"+id;
}

function showProduct(id){
	location = mainWeb+"/front/toGoodsInfoPage?id="+id;
}
</script>
</html>
