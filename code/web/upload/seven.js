/**
 * 
 * @param {} parameters {
 * 		parentId,
 * 		type: 0价格 1积分
 * 		onNodeSelection
 * }
 * 
 */
function PriceNavigator(parameters) {
	
	var type = 0;
	var $parent = $('#' + parameters.parentId);
	var priceIntervalArray = new Array();
	
	var render = function() {
		if (priceIntervalArray.length < 1) return;
		$parent.empty();
		$parent.css('color', '#076A00');
		var $node = null;
		for (var index = 0; index < priceIntervalArray.length; index++) {
			var title = priceIntervalArray[index].begin + "-" + priceIntervalArray[index].end;
			if (1 == type) {
				title += "积分";
			} else {
				title += "元";
			}
			if (index == 0) {
				title = "全部";
			} else if (index == priceIntervalArray.length - 1) {
				title = priceIntervalArray[index].begin + "以上";
			}
			$node = $("<a style='cursor: hand' begin='" + priceIntervalArray[index].begin + "' end='" + priceIntervalArray[index].end + "'>" + title + "</a>");
			
			//
			$node.click(function() {
				if (parameters && parameters.onNodeSelection) {
					parameters.onNodeSelection($(this).attr("begin"),$(this).attr("end"));
				}
				$(this).addClass("current").siblings().removeClass("current");
			});
			$node.bind("mouseover",function(){
				if($(this).hasClass("current"))return;
				$(this).addClass("currentover");
			}).bind("mouseout",function(){
				$(this).removeClass("currentover");
			});
			$node.appendTo($parent);
			if (0 == index) {
				$node.addClass("current");
			}
		}
	};
	
	var initData = function() {
		if (type == 0) { //价格
			priceIntervalArray[0] = {begin: -1, end: -1};
			priceIntervalArray[1] = {begin: 0, end: 5};
			priceIntervalArray[2] = {begin: 5, end: 10};
			priceIntervalArray[3] = {begin: 10, end: 20};
			priceIntervalArray[4] = {begin: 20, end: 50};
			priceIntervalArray[5] = {begin: 50, end: 100};
			priceIntervalArray[6] = {begin: 100, end: 200};
			priceIntervalArray[7] = {begin: 200, end: 500};
			priceIntervalArray[8] = {begin: 500, end: -1};
		} else { // 积分
			priceIntervalArray[0] = {begin: -1, end: -1};
			priceIntervalArray[1] = {begin: 0, end: 500};
			priceIntervalArray[2] = {begin: 500, end: 1000};
			priceIntervalArray[3] = {begin: 1000, end: 2000};
			priceIntervalArray[4] = {begin: 2000, end: 5000};
			priceIntervalArray[5] = {begin: 5000, end: 10000};
			priceIntervalArray[6] = {begin: 10000, end: -1};
		}
	};
	
	var init = function() {
		if (parameters.type) {
			if (parameters.type == 1) {
				type = 1;
			} else {
				type = 0;
			}
		}
		initData();
		render();
	};
	
	init();
}
/**
 * 
 * @param {} parameters {
 * 		parentId,
 * 		onSortAction: function(sortWay, sortType) {
 * 		}
 * }
 * 
 */
function GoodsSortBar(parameters) {
	
	var SORTBYSALEDCOUNT = "0";
	var SORTBYSALEDPRICE = "1";
	var SORTBYSALEDPUBLISHDATE = "2";
	
	var sortType = 0; // 0升序 1降序
	var currentSortWay = SORTBYSALEDPRICE;
	
	var $saledCountSort = $("<span class='buttonInitClassLeft' id='btnSale'></span>");
	var $realPriceSort = $("<span class='buttonInitClassLeft asc' id='btnPrice'></span>");
	var $publishDateSort = $("<span class='buttonInitClassLeft' id='btnDate'></span>");
	
	var bindTargetMouseEvent = function($target) {
		$target.bind("mouseover",function(){
			if($(this).hasClass("asc") || $(this).hasClass("desc")){
				return;
			}
			$(this).css("background-image","url('" + mainWeb + "/images/page/" + $(this).attr("id") + "_hover.png')");
		}).bind("mouseout",function(){
			if($(this).hasClass("asc") || $(this).hasClass("desc")){
				return;
			}
			$(this).css("background-image","url('" + mainWeb + "/images/page/" + $(this).attr("id") + "_normal.png')");
		});
	};
	
	var resetStyle = function($target) {
		$target.removeClass("asc");
		$target.removeClass("desc");
		$target.css("background-image","url('" + mainWeb + "/images/page/" + $target.attr("id") + "_normal.png')");
	};
	
	var bindTargetClickEvent = function($target, way, $brother1, $brother2) {
		$target.click(function() {
			// change style
			resetStyle($brother1);
			resetStyle($brother2);
			if (currentSortWay == way) {
				sortType = !sortType ? 1 : 0;
			} else {
				currentSortWay = way;
				if ($realPriceSort == $target) {
					sortType = 0;
				} else {
					sortType = 1;
				}
			}
			if(1 == sortType){
				$(this).removeClass("asc");
				$(this).addClass("desc");
				$(this).css("background-image","url('" + mainWeb + "/images/page/" + $(this).attr("id") + "_desc.png')");							
			} else {
				$(this).removeClass("desc");
				$(this).addClass("asc");
				$(this).css("background-image","url('" + mainWeb + "/images/page/" + $(this).attr("id") + "_asc.png')");
			}
			// action 
			parameters.onSortAction(way, sortType);
		});
	};
	
	var render = function() {
		var $parent = $('#' + parameters.parentId);
		var $title = $("<span class='orderBy'>排序：</span>");
		
		$title.appendTo($parent);
		$saledCountSort.appendTo($parent);
		$realPriceSort.appendTo($parent);
		$publishDateSort.appendTo($parent);
		
		bindTargetClickEvent($saledCountSort, SORTBYSALEDCOUNT, $realPriceSort, $publishDateSort);
		bindTargetClickEvent($realPriceSort, SORTBYSALEDPRICE, $saledCountSort, $publishDateSort);
		bindTargetClickEvent($publishDateSort, SORTBYSALEDPUBLISHDATE, $saledCountSort, $realPriceSort);
		
		bindTargetMouseEvent($saledCountSort);
		bindTargetMouseEvent($realPriceSort);
		bindTargetMouseEvent($publishDateSort);
	};
	
	var init = function() {
		render();
	};
	
	init();
}