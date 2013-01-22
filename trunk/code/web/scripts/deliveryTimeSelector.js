/**
 * 
 * @param {}
 *            pListId 配送时间点下拉控件ID
 */
function DeliveryTimeSelector(pListId) {

	var $pList = $('#' + pListId);

	this.getSelectedTimeCode = function() {
		return $pList.val();
	};

	this.getSelectedTimeTitle = function() {
		return $($pList).children('option:selected').text();
	};

	this.setSelection = function(TimeCode) {
		$pList.val(TimeCode);
		$pList.trigger('change');
	};

	var getDataList = function(parentId) {
		var dataList = new Array();
		$.ajax({
					url : basePath + '/front/common/getDeliveryTimeList',
					async : false,
					// data: {'parentId': parentId},
					success : function(data) {
						dataList = data.rows;
					}
				});
		return dataList;
	};

	var setTimeList = function() {
		$pList.empty();
		var dataList = getDataList(null);
		var defaultIndex = 0;
		for (var index = 0; index < dataList.length; index++) {
			var $option = null;

			$option = $("<option value='" + dataList[index].code + "'>"
					+ dataList[index].title + "</option>");

			$option.appendTo($pList);
		}
		
	};

	var registChangeAction = function() {
		// $pList.unbind('change');
		// $pList.bind('change', function() {
		// var id = $(this).children('option:selected').val();
		// setCityList(id);
		// });

	};

	var init = function() {
		setTimeList();
		registChangeAction();
	};

	init();
}