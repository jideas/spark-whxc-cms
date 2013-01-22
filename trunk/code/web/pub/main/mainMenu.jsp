<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
	<head>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">function toShopingCar()
			{
			window.location.href = mainWeb + "/front/shopingCar/getGoods";
			}</script>
		<link type="text/css" href="<%=basePath%>/css/menu.css"
			rel="stylesheet">
		<style>
.nav .categoryItems {
	display: block;
}
</style>
</head>
<body>
	<div class="nav">
		<!--���� -->
		<div id="category" class="category">
			<div class="all-sort">
				<h3>ȫ����Ʒ����</h3>
			</div>
			<DIV class="categoryItems">
			</div>
			<div class="menuList">
				<div class="nav1-list">
					<ul>
						<li class="current">
							<a href="#">��ҳ</a>
						</li>
						<li>
							<a href="#">�����̳�</a>
						</li>
						<li>
							<a href="#">�Ź�</a>
						</li>
						<li>
							<a href="#">һԪר��</a>
						</li>
						<li>
							<a href="#">�ؼ���</a>
						</li>
						<li>
							<a href="<%=basePath%>/pub/sub/joinus.jsp">����7��</a>
						</li>
					</ul>
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
									<a href="javascript:toShopingCar()">���ﳵ</a>
								</li>
							</ul>
							<span><b>12</b>
							</span>
						</div>
						<div id='carContent' class='poptip'></div>
					</div>
					<div class="nav-line">
						<img src="<%=basePath%>/images/page/nav-line.png" alt="l" />
					</div>
					<div class="jiesuan">
						<ul>
							<li>
								<a href="javascript:toShopingCar()">ȥ����</a>
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

			<div class="slider">
				<jsp:include page="slider.jsp" flush="true" />
			</div>
			<div class="shoppingNews">
				<jsp:include page="ad-right.jsp" flush="true" />
			</div>
		</div>



</body>



<script type="text/javascript" src="<%=basePath%>/scripts/pub/menu.js "></script>
<SCRIPT type=text/javascript>

	
/**
*���ﳵЧ������ 
*/
$(function(){
	var _thiscontain = $(".nav .caddy .caddyList #carContent");
	var _cardHeader = $(".nav .caddy .caddyList .caddyHead");
	var iscartHead = false;
	var iscartBody = false;
	
	
	$(".nav .caddy .caddyList .caddyHead").bind("mouseover",function(){
		iscartHead = true;
		doBindAction(true);
		setTimeout(function(){doAction();},1);
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


	
	/**
	* ��ȡ�˵�����
	*/
	$(function(){
		$.ajax({
 			url: mainWeb + '/front/channel/getMenuHtml',
 	 		type: 'POST',
 	 		dataType: 'json',
 			async: true,
 			success: function(data) {
 				if (data) {
 				  $(".menuList .nav1-list").html(data[0].toString());
 				} 
 			},
 			error: function() {
 				alert("����ʧ�ܡ�");
 			}
 		});
	});
	
	

	/**
	* ��ȡ��Ŀ����
	*/
	$(function(){
		$.ajax({
 			url: mainWeb + '/front/goods/getCategoryTreeHtml',
 	 		type: 'POST',
 	 		dataType: 'json',
 			async: true,
 			success: function(data) {
 				if (data) {
 				  $(".categoryItems").html(data[0].toString());
 				  initCategorys();
 				} 
 			}
 		});
	});
function initCategorys() {
/**
*ÿ����ƷItem���¼�
*/
$(".categoryItems .item").each(function(){
	var timer1 = null, timer2 = null, flag = false;
	$(this).bind("mouseover",function() {
		if (flag) {
			clearTimeout(timer2);
		} else {
			var _this = $(this);
			timer1 = setTimeout(function() {
				_this.addClass("hover");
				_this.css("border-top", "0 dotted #22a618");
				if (_this.next()) {
					_this.next().css("border-top", "0 dotted #22a618");
				}
				flag = true;
			}, 150);
		}
	}).bind("mouseout",function() {
		if (flag){
			var _this = $(this);
			timer2 = setTimeout(function(){
				_this.removeClass("hover");
				_this.css("border-top", "1px dotted #22a618");
				if (_this.next()) {
					_this.next().css("border-top","1px dotted #22a618");
				}
				$(".categoryItems .fore").css("border-top","0 dotted #22a618");
				flag = false;
			}, 150);
		}else {
			clearTimeout(timer1);
		}
	});
});
}
function inintMenu(){
$(function() {
	$.each($(".nav .menuList .nav1-list li"), function() {
		var _this = $(this);
		$(this).bind("click", function() {
			$(".nav .menuList .nav1-list .current").removeClass("current");
			_this.removeClass("mouseover");
			_this.addClass("current");
		}).bind("mouseover", function() {
			if (_this.hasClass("current")) {
				return;
			} else {
				_this.addClass("mouseover");
			}
		}).bind("mouseout", function() {
			_this.removeClass("mouseover");
		});
	});
});
}
</SCRIPT>
</html>
