/**
 * 
 * @param {} paramters: {	
 * 				parentId: parentId, 显示分页控件的ID
 * 				count: totalCount, 记录总数
 * 				pageSize: pageSize, 每页显示条数
 * 				actionListener: pageActoin 分页发生变化后的回调方法
 * 				isActionOnLoad: 初始化时是否执行pageAction,默认为true
 * 			}
 */
function CmsPaging(paramters) {
	
	var _totalPage = 0;
	var _showCount = 6;
	var _parentId = '';
	var _isActionOnLoad = true;
	
	this.getTotalPageCount = function() {
		return _totalPage;
	};
	
	this.setSelection = function(pageIndex) {
		_isActionOnLoad = true;
		loadPage(pageIndex);
	};
	
	this.prePage = function() {
		pageAction(0);
	};
	
	this.nextPage = function() {
		pageAction(1);
	};
	
	var initPage = function() {
		var pageHtml = "<a class='prev prev-disabled' href='javascript:void(0);'>上一页<b></b></a> \n";
		if (_totalPage < 0) return;
		pageHtml += "<a href='javascript:void(0);' class='current'>1</a> \n";
		for (var pageIndex = 2; pageIndex < _totalPage; pageIndex++) {
			if (pageIndex > _showCount && (_totalPage - _showCount) > 1) {
				pageHtml += "<span class='text'>...</span> \n";
				break;
			} else {
				pageHtml += "<a href='javascript:void(0);' >" + pageIndex + "</a> \n";
			}
		}
		if (_totalPage > 1) {
			pageHtml += "<a href='javascript:void(0);'>" + _totalPage + "</a> \n";
		}
		if (_totalPage == 1) {
			pageHtml += "<a href='javascript:void(0);' class='next-disabled '>下一页<b></b></a>";
		} else {
			pageHtml += "<a href='javascript:void(0);' class='next'>下一页<b></b></a>";
		}
		$('#' + _parentId).html(pageHtml);	
		if (_isActionOnLoad) {
			paramters.actionListener(1);
		}
	};

	var loadPage = function(defaultPage) {
		if (_totalPage < 0) return;
		if (defaultPage == 1) {
			initPage();
			bindTargetAction();
			bindPageAction();
			return;
		}
		// 显示第一个
		var pageHtml = "<a class='prev' id='pagin_pre'>上一页<b></b></a> \n";
		pageHtml += "<a href='javascript:void(0);'>1</a> \n";
		// 计算要显示的数
		// defaultPage - 1, defaultPage, defaultPage + 1, defaultPage + 2 ...  count < _showCount
		var middleStart = Number(defaultPage) - 1;
		var middleEnd = defaultPage + (_showCount - 3);
		if (middleStart > 2) {
			pageHtml += "<span class='text'>...</span> \n";
		}
		for (var pageIndex = middleStart; pageIndex <= middleEnd; pageIndex++) {
			if (pageIndex == 1) continue;
			if (pageIndex >= _totalPage) break;
			if (pageIndex == defaultPage) {
				pageHtml += "<a href='javascript:void(0);' class='current'>" + pageIndex + "</a> \n";
			} else {
				pageHtml += "<a href='javascript:void(0);' >" + pageIndex + "</a> \n";
			}
		}
		if (middleEnd < _totalPage) {
			pageHtml += "<span class='text'>...</span> \n";
		}
		if (defaultPage == _totalPage) {
			pageHtml += "<a href='javascript:void(0);' class='current'>" + _totalPage + "</a> \n";
		} else {
			pageHtml += "<a href='javascript:void(0);'>" + _totalPage + "</a> \n";
		}
		
		pageHtml += "<a href='javascript:void(0);' class='next' action='next'>下一页<b></b></a>";
		$('#' + _parentId).html(pageHtml);
		bindTargetAction();
		bindPageAction();
		paramters.actionListener(defaultPage);
	};
	
	var bindTargetAction = function() {
		$('#' + _parentId + ' a').unbind('click');
		$('#' + _parentId + ' a').click(function (e) {
			var $targetObj = $(e.target);
			if ($targetObj.attr('class') == null || $targetObj.attr('class') == "") {
				selectPage($targetObj, Number($targetObj.html()));
			} else {
				if ($targetObj.attr('class').indexOf('prev') > -1) {
					pageAction(0);
				} else if ($targetObj.attr('class').indexOf('next') > -1) {
					pageAction(1);
				}
			}
		});
	};
	
	var bindPageAction = function() {
		$('#pagin_pre').unbind('click');
		$('#pagin_pre').bind('click', function() {
			pageAction(0);
		});
		
		$('#pagin_next').unbind('click');
		$('#pagin_next').bind('click', function() {
			pageAction(1);
		});
	};
	/**
	 *
	 * @param type
	 * 0: 上一页
	 * 1: 下一页
	 **/
	 var pageAction = function(type) {
		var $prePageElement = $('#' + _parentId + ' .current');
		var prePage = $prePageElement.html();
		if (0 == type && prePage == 1) {
			return;
		}
		if (1 == type && prePage == _totalPage) {
			return;
		}
		var $desElement = null;
		var desPage = 0;
		if (0 == type) {
			$desElement = $prePageElement.prev();
			desPage = Number(prePage) - 1;
		} else if (1 == type) {
			$desElement = $prePageElement.next();
			desPage = Number(prePage) + 1;
		}
		selectPage($desElement, desPage);
	 };

	 var selectPage = function ($target, desPage) {
		var $prePageElement = $('#' + _parentId + ' .current');
		if ($target.html() == '...') {
			loadPage(desPage);
			//paramters.actionListener(desPage);
			return;
		}
		// 去掉原来的 current
		$prePageElement.removeClass('current');
		// 目标要变为 current
		if ($target != null) {
			$target.addClass('current');
		}
		// 判断上一页是否可用
		var $preButton = $('#' + _parentId + ' a:first-child');
		if (desPage == 1) {
			$preButton.addClass('prev-disabled');
		} else {
			$preButton.removeClass('prev-disabled');
			$preButton.addClass('prev');
		}
		// 判断下一页是否可用
		var $nextButton = $('#' + _parentId + ' a:last-child');
		if (desPage == _totalPage) {
			$nextButton.addClass('next-disabled');
		} else {
			$nextButton.removeClass('next-disabled');
			$nextButton.addClass('next');
		}
		paramters.actionListener(desPage);
	 };
	 
	 var validateParameter = function() {
	 	if (paramters.count == "undefined") return false;
	 	if (paramters.pageSize == "undefined") return false;
	 	if (paramters.parentId == "undefined") return false;
	 	if (paramters.count < 1) return false;
	 	return true;
	 };
	 var init = function() {
	 	if (!validateParameter()) return;
	 	//_totalPage = Math.floor(paramters.count / paramters.pageSize) + 1;
	 	if (paramters.count % paramters.pageSize == 0) {
	 		_totalPage = paramters.count / paramters.pageSize;
	 	} else {
	 		_totalPage = Math.floor(paramters.count / paramters.pageSize) + 1;
	 	}
	 	if (_totalPage == "NaN") { 
	 		return;
	 	}
	 	_parentId = paramters.parentId;
	 	if (paramters.isActionOnLoad != null && typeof(paramters.isActionOnLoad) != "undefined") {
	 		_isActionOnLoad = paramters.isActionOnLoad;
	 	}
		loadPage(1);
	 };
	 init();
}