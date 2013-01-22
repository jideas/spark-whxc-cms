var menu = new Menu();
function Menu(){
	this.active = function(){
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
	};
}

var productCategory = new ProductCateGroy();
function ProductCateGroy(){
	this.addAction = function(){
		$(".categoryItems .item").each(function(){
			var timer1 = null, timer2 = null, flag = false;
			$(this).bind("mouseover",function() {
				if (flag) {
					clearTimeout(timer2);
				} else {
					var _this = $(this);
					timer1 = setTimeout(function() {
						_this.addClass("hover");
						_this.css("border-top", "1px solid #ebfbe7");
						if (_this.next()) {
							_this.next().css("border-top", "1px solid #ebfbe7");
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
						$(".categoryItems .fore").css("border-top","1px solid #ebfbe7");
						flag = false;
					}, 150);
				}else {
					clearTimeout(timer1);
				}
			});
		});
	};
	this.init = function(option){
		var config = $.extend({current : "hover",delay : 10,hiddenContain : true,baseurl:""}, option || {});

		var _thiscontain = $(".nav .category .categoryItems");
		var _headflag = $(".nav .category .all-sort span");
		if(!config.hiddenContain){
			_thiscontain.css("display","block");
			_headflag.css("display","none");
		}else{
			_thiscontain.css("display","none");
			_headflag.css("display","block");
			
			var isAllsort = false;
			var isBody = false;

			$(".nav .category .all-sort").bind("mouseover", function() {
				isAllsort = true;
				doBindAction(true);
				_headflag.css("background-image","url("+config.baseurl+"/images/page/navlist-hover.png)");
				setTimeout(function() {doAction();}, 1);
			}).bind("mouseleave", function() {
				isAllsort = false;
				setTimeout(function() { doAction(); }, 1);
			});

			function doAction() {
				if (isAllsort || isBody) {
					_thiscontain.css("display", "block");
				} else {
					_thiscontain.css("display", "none");
					doBindAction(false);
					_headflag.css("background-image","url("+config.baseurl+"/images/page/navlist-normal.png)");
				}
			}
			function doBindAction(t) {
				if (t) {
					_thiscontain.bind("mouseenter", function(){
						_headflag.css("background-image","url("+config.baseurl+"/images/page/navlist-active.png)");
						isBody = true;
						setTimeout(function() { doAction(); }, 100);
					}).bind("mouseleave", function() {
						isBody = false;
						setTimeout(function() { doAction(); }, 200);
					});
				} else {
					_thiscontain.unbind("mouseenter",function() { 
						isBody = true;
						setTimeout(function() { doAction(); }, 1);
					}).unbind("mouseleave", function(){
						isBody = false;
						setTimeout(function() {doAction();}, 1);
					});
				}
			}
		}
		return this;
	};
}
