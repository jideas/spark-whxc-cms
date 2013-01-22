/**
 * 
 * @param {} pListId
 * 		省下拉控件ID
 * @param {} cListId
 * 		市下拉控件ID
 * @param {} dListId
 * 		区/县下拉控件ID
 */
function AreaProvider(pListId, cListId, dListId) {
	
	var $pList = $('#' + pListId);
	var $cList = $('#' + cListId);
	var $dList = $('#' + dListId);
	
	var DEFAULT_PROVINCE_CODE = '420000000000';
	
	this.getSelectedProvinceCode = function() {
		return $pList.val();
	};
	
	this.getSelectedCityCode = function() {
		return $cList.val();
	};
	
	this.getSelectedDistrictCode = function() {
		return $dList.val();
	};
	
	this.getSelectedProvinceTitle = function() {
		return $($pList).children('option:selected').text();
	};
	
	this.getSelectedCityTitle = function() {
		return $($cList).children('option:selected').text();
	};
	
	this.getSelectedDistrictTitle = function() {
		return $($dList).children('option:selected').text();
	};
	
	this.setSelection = function(provinceCode, cityCode, districtCode) {
		$pList.val(provinceCode);
		$pList.trigger('change');
		
		$cList.val(cityCode);
		$cList.trigger('change');
		
		$dList.val(districtCode);
		$dList.trigger('change');
	};
	
	this.addSelectionListener = function(fn) {
		$dList.unbind('selection');
		$dList.bind('selection', function() {
			fn();
		});
	};
	
	var getDataList = function(parentId) {
		var dataList = new Array();
		$.ajax({
			url: basePath + '/front/common/getAreaList',
			async: false,
			data: {'parentId': parentId},
			success: function(data) {
				dataList = data.rows;
			}
		});
		return dataList;
	};
	
	var setProvinceList = function() {
		$pList.empty();
		var dataList = getDataList(null);
		var defaultIndex = 0;
		for (var index = 0; index < dataList.length; index++) {
			var $option = null;
			if (DEFAULT_PROVINCE_CODE == dataList[index].code) {
				defaultIndex = index;
				$option = $("<option value='" + dataList[index].code + "' selected>" + dataList[index].title + "</option>");
			} else {
				$option = $("<option value='" + dataList[index].code + "'>" + dataList[index].title + "</option>");
			}
			$option.appendTo($pList);
		}
		if (dataList.length < 1) {
			setCityList('');
		} else {
			setCityList(dataList[defaultIndex].code);
		}
		
	};
	
	var setCityList = function(parentId) {
		$cList.empty();
		if ('' == parentId) return;
		var dataList = getDataList(parentId);
		for (var index = 0; index < dataList.length; index++) {
			$option = $("<option value='" + dataList[index].code + "'>" + dataList[index].title + "</option>");
			$option.appendTo($cList);
		}
		if (dataList.length < 1) {
			setDistrictList('');
		} else {
			setDistrictList(dataList[0].code);
		}
	};
	
	var setDistrictList = function(parentId) {
		$dList.empty();
		$dList.trigger('selection');
		if ('' == parentId) return;
		var dataList = getDataList(parentId);
		for (var index = 0; index < dataList.length; index++) {
			$option = $("<option value='" + dataList[index].code + "'>" + dataList[index].title + "</option>");
			$option.appendTo($dList);
		}
	};
	
	var registChangeAction = function() {
		$pList.unbind('change');
		$cList.unbind('change');
		// 省
		$pList.bind('change', function() {
			var id = $(this).children('option:selected').val();
			setCityList(id);
		});
		// 市
		$cList.bind('change', function() {
			var id = $(this).children('option:selected').val();
			setDistrictList(id);
		});
		// 区
		$dList.bind('change', function() {
			$dList.trigger('selection');
		});
	};
	
	var init = function() {
		setProvinceList();
		registChangeAction();
	};
	
	init();
}