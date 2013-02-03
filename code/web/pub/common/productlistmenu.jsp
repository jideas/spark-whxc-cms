<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	String menuId = (String) request.getParameter("menuId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
	<head>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link type="text/css" href="<%=basePath%>/css/menu.css"
			rel="stylesheet">
		<style>
.nav .categoryItems {
	width: 216px; *
	width: 220px;
	height: 480px;
	background: #ebfbe7;
	border-right: 2px solid #22a618;
	border-bottom: 2px solid #22a618;
	border-left: 2px solid #22a618;
	-moz-border-radius: 0 0 5px 5px;
	-webkit-border-radius: 0 0 5px 5px;
	text-align: left;
	position: absolute;
	top: 40px;
	left: 0px;
	z-index: 10;
	display: none;
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
}
</style>
		<script type="text/javascript">function toShopingCar()
			{
			window.location.href = mainWeb + "/front/shopingCar/getGoods";
			}</script>
	</head>
	<body>
		<div class="nav">
			<!--导航 -->
			<div id="category" class="category">
				<div class="all-sort">
					<h3>
						全部商品分类
					</h3>
					<span></span>
				</div>
				<DIV class="categoryItems">


				</div>
			</div>
			<div class="menuList">
				<div class="nav1-list">

				</div>
				<div class="caddy">
					<div class="caddyList" id="shoppingCar">
						<div class="caddyHead">
							<ul>
								<li>
									<img src="<%=basePath%>/images/page/caddy-full.png" width="27"
										height="21" />
								</li>
								<li>
									<a href="javascript:void(0)" onclick="toShopingCar()">购物车</a>
								</li>
							</ul>
							<span></span>
						</div>
						<div id='carContent' class='poptip'>
							<div id="carShopingInfo" style="z-index:10;">
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
								<img src="<%=basePath%>/images/page/jiesuan-arrow.png" alt="c"
									width="16" height="16" />
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuRight">
			</div>
		</div>
	</body>
	<script type="text/javascript"
		src="<%=basePath%>/scripts/jquery/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/pub/menu.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/scripts/cookieutil.js"></script>
	<SCRIPT type=text/javascript>
var _mainMenuId = '<%=menuId%>';

/**
* 获取栏目数据
*/
$(function(){
	
	/**
	* 获取菜单
	*/
	
	$.ajax({
		url: mainWeb + '/front/channel/getMenuHtml',
 		type: 'POST',
 		dataType: 'json',
		async: true,
		success: function(data) {
			if (data) {
			  $(".menuList .nav1-list").html(data[0].toString());
			  menu.active();
			  $('#' + _mainMenuId).addClass('current');
			} 
		}
	});
	
	/**
	*获取栏目信息。
	*/
	$.ajax({
			url: mainWeb + '/front/goods/getCategoryTreeHtml',
	 		type: 'POST',
	 		dataType: 'json',
			async: true,
			success: function(data) {
				if (data) {
				  $(".categoryItems").html(data[0].toString());
					 var productCategory = new ProductCateGroy();
					productCategory.init({baseurl:"<%=basePath%>"}).addAction();
				} 
			}
		});
});


	
var _productListMenuApp = null;
function ProductListMenuApp() {
	this.loasShoppingCharCount = function(count) {
		if (count < 0 || typeof(count) === 'undefined') {
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
		_productListMenuApp = new ProductListMenuApp();
		
		_productListMenuApp.loasShoppingCharCount(Number('-1'));
		
		$(".nav .caddy .caddyList .caddyHead").bind("mouseenter",function(){
			iscartHead = true;
			doBindAction(true);
			setTimeout(function(){doAction();},1);
			carContent.show();
		}).bind("mouseleave",function(){
			iscartHead = false;
			setTimeout(function(){doAction();},1);
		});
		
		function doAction(){
			if(iscartHead||iscartBody){
				_cardHeader.addClass("hover");
				_thiscontain.css("display","block");
			}else{
				_cardHeader.removeClass("hover");
				_thiscontain.css("display","none");
				//carContent.hide();
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

		

/*购物车【行】悬浮效果*/
function caddy_tr_over(tr){
	$(tr).addClass("caddy_tr_over");
}
function caddy_tr_out(tr){
	$(tr).removeClass("caddy_tr_over");
}

function ShowShoppingCarDetail() {
	//$.ajaxSetup({cache:false});
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
			cache: false,
			success: function(data) {
				var html = "";
				var infoHtml = "";
				if (null == data || data.length == 0) {
					//html += "<tr>";
					//html += "<td class='' align='left'>购物车中还没有商品，赶紧选购吧！</td>";
					//html += "</tr>";
					$("#noGoods").css("display","block");
					$("#carShopingOperate").css("display","none");
					$("#carShopingItems").css("display","none");
					$table.html(html);
					
					//infoHtml += "<div class='caddy_count'>共<span>0</span>类<span>0</span>件商品</div>";
					//infoHtml += "<div class='caddy_money'>总计:￥<span>0</span>元</div>";
					$summaryInfo.html(infoHtml);
					_productListMenuApp.loasShoppingCharCount(0);
					return;
				}
				$("#noGoods").hide();
				$("#carShopingItems").show();
				$("#carShopingOperate").show();
				/**
				 *  
				 *
				 **/
				var totalCount = 0;
				var totalAmount = 0;
				for (var index = 0; index < data.length; index++) {
					html += "<tr id='shpcar_row-" + data[index].goodsId + "' onmouseover='caddy_tr_over(this)' onmouseout='caddy_tr_out(this)'>";
					html += "<td class='cardShop_img'>";
					html += "	<img style='width:50px;height:50px;' src=" + mainWeb  + data[index].miniPicturePath + " />";
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
				
				_productListMenuApp.loasShoppingCharCount(data.length);
				cookieutil.setShoppingCarGoods(data);
				addDeleteAction();
			}
		});		
	};
	
	var addDeleteAction = function() {
		// 普通商品
		$("a[id|='shpcar_del']").each(function() {
			var goodsId =  $(this).attr('id').split('-')[1];
			$(this).unbind('click');
			$(this).bind('click', function() {
				$('#shpcar_row-' + goodsId).remove();
				cookieutil.removeGoodsFromShoppingCar(goodsId);
				$table.trigger('change');
			});
		});
		// 积分商品
		$("a[id|='shpcar_del_vantage']").each(function() {
			var goodsId =  $(this).attr('id').split('-')[1];
			$(this).unbind('click');
			$(this).bind('click', function() {
				$('#shpcar_row-' + goodsId).remove();
				cookieutil.removeVantegeGoodsFromShoppingCar(goodsId);
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

function toCategoryPage(id){
	window.location.href = mainWeb + "/front/toCategoryPage/"+id;
}

</SCRIPT>
</html>