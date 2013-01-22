<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ʒ����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<style type="text/css">
	.formItem {
	        display:-moz-inline-box; 
	        display:inline-block; 
	        width:230px;
	        height: 20px;
	}
	.formItem .label {
		width: 80px;
		text-align: right;
	}
	.editor {
		display:-moz-inline-box; 
        display:inline-block; 
        width:100px;
	}
</style>
<script type="text/javascript">

var _goodsApp = null;
// var mainWeb = "/cms";
$(document).ready(function() {
	_goodsApp = new GoodsListApp();
});

function GoodsListApp() {
	var categoryTree = null;
	var goodsTable = null;
	var newButton = null;
	
	var selectedCategoryId = null;
	this.categorySelectionListener = function(node) {
		if (selectedCategoryId == node.id) return;
		goodsTable.reload(node.id);
		selectedCategoryId = node.id;
		if (node.id == 'root' || categoryTree.getChildren(node).length > 0
				|| node.attributes.levelNo != 3) {
			newButton.hide(true);
			//selectedCategoryId = null;
			$('#goods_addtip').show();
			return;
		}
		//selectedCategoryId = node.id;
		if (selectedCategoryId == "root") selectedCategoryId = null;
		$('#goods_addtip').hide();
		newButton.hide(false);
		/*
		if (node.attributes.propertied) {
			newButton.hide(false);
		} else {
			newButton.hide(true);
		}
		*/
		
	};
	
	this.newButtonActionListener = function() {
		var win = new Window('win');
		win.getWin().unbind("confirmESelect");
		/* ��ѡ
		win.getWin().bind("confirmESelect", function(event, eGoodsRow){
			win.close();
			var createWin = new WindowForCreate(selectedCategoryId, eGoodsRow);
			createWin.getWin().unbind("confirmCreate");
			createWin.getWin().bind('confirmCreate', function(event) {
				goodsTable.reload(selectedCategoryId);
			});
			
		});
		*/
		// �ɶ�ѡ
		win.getWin().bind("confirmESelect", function(event, data){
			win.close();
			var paramters = "categoryId=" + selectedCategoryId;
			for (var rowIndex = 0; rowIndex < data.selections.length; rowIndex++) {
				paramters += "&eGoodsIds=" + data.selections[rowIndex].recid;
			}
			$.ajax({
				type: 'post',
				url: mainWeb + '/admin/goods/createGoods',
				data: paramters,
				dataType: 'json',
				success: function(data) {
					if (data.result) {
						$.messager.alert("��ʾ", "����ɹ���");
						goodsTable.reload(selectedCategoryId);
					} else {
						$.messager.alert("��ʾ", data.message);
					}
				}
			});
		});
	};
	
	this.getCategoryInfoById = function(categoryId) {
		var categoryInfo = null;
		$.ajax({
			type: 'POST',
  			url: mainWeb + '/admin/goods/getCategoryInfo',
  			data: {"categoryId": categoryId},
  			async: false,
  			success: function(data) {
  				categoryInfo = strToJson("{categoryInfo:" + data + "}").categoryInfo;
  			}
		});
		return categoryInfo;
	};
	
	this.getGoodsBaseInfoById = function(goodsId) {
		var godosBaseInfo = null;
		$.ajax({
			type: 'POST',
  			url: mainWeb + '/admin/goods/getGoodsVo',
  			data: {"goodsId": goodsId},
  			dataType: 'json',
  			contentType: "application/x-www-form-urlencoded; charset=utf-8",
  			async: false,
  			success: function(data) {
  				// godosBaseInfo = strToJson("{categoryInfo:" + data + "}").categoryInfo;
  				godosBaseInfo = data;
  			}
		});
		return godosBaseInfo;
	};
	
	this.editGoods = function(recid) {
		var goodsVo = _goodsApp.getGoodsBaseInfoById(recid);
		new WindowForCreate(selectedCategoryId, goodsVo, true);
	};
	
	this.tableDataChangeAction= function() {
		goodsTable.reload(selectedCategoryId);
	};
	
	this.deleteGoods = function(recid, index) {
		$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������Ʒ�룿', function(r) {
				if (r) {
					$.ajax({
						type: 'POST',
			  			url: mainWeb + '/admin/goods/deleteGoods',
			  			data: {"goodsId": recid},
			  			dataType: 'json',
			  			async: false,
			  			success: function(data) {
			  				if (data.result) {
			  					//goodsTable.removeRow(index);
			  					goodsTable.reload(selectedCategoryId);
			  				} else {
			  					$.messager.alert("��ʾ", data.message);
			  				}
			  			}
					});
					
				}
			});
	};
	
	var addSearchListener = function() {
		$('#goods_search_action').unbind('click');
		$('#goods_search_action').click(function() {
			//var searchText = $('goods_search_text').val();
			goodsTable.search(selectedCategoryId, $.trim($('#goods_search_text').val()));
		});
		
		$('#goods_search_text').unbind('keydown');
		$('#goods_search_text').bind('keydown', function(event) {
			if (event.which == 13) {
				$('#goods_search_action').click();
			}
		});
		
		$('#goods_search_Advance').click(function() {
			GoodsAdvanceSearchWindow(function(searchData){
				goodsTable.advanceSearch(searchData);
			});
		});
	};
	
	var init = function() {
		//categoryTree = new CmsTree('goodsCategoryTree_g', mainWeb + "/admin/goods/getCategoryTree");
		categoryTree = new CmsTree('goodsCategoryTree_g', mainWeb + "/admin/goods/getTreeChildrenNode");
		goodsTable = new GoodsGrid();
		newButton = new NewButton();
		
		goodsTable.reload(selectedCategoryId);
		addSearchListener();
	};
	init();
}

function NewButton() {
	var button = $('#Button_NewGoods');
	
	this.hide = function(isHide) {
		//button.attr('disabled', isHide);
		///*
		if (isHide) {
			button.hide();
		} else {
			button.show();
		}
		//*/
	};
	
	var init = function() {
		button.hide();
		//button.attr('disabled', true);
		button.click(function() {
			_goodsApp.newButtonActionListener();
		});
	};
	
	init();
}

/**
 * ������
 * @param {} treeId
 */
function CmsTree(treeId, nUrl) {
	var id = treeId;
	var goodsCategoryTree = $('#' + id);
	this.getSelection = function() {
		return goodsCategoryTree.tree('getSelected');
	};
	
	this.getParent = function(node) {
		return goodsCategoryTree.tree('getParent', node.target);
	};
	
	this.getChildren = function(node) {
		return goodsCategoryTree.tree('getChildren', node.target);
	};
	
	this.reload = function() {
		goodsCategoryTree.tree('reload');
	};
	
	var init = function() {
		goodsCategoryTree.tree({
			url: nUrl,
			onSelect: function(node) {
				_goodsApp.categorySelectionListener(node);
			}
		});
	};
	
	init();
}

function GoodsGrid() {
	var table = $('#Table_GoodsList');
	
	this.reload = function(categoryId) {
		/*
		$.post(mainWeb + '/admin/goods/getGoodsList', {"categoryId": categoryId}, function(data) {
			table.datagrid('loadData', data.rows);
		});
		*/
		//reload
		table.datagrid('reload', {"categoryId": categoryId});
	};
	
	this.search = function(categoryId, searchText) {
		table.datagrid('reload', {"categoryId": categoryId, 'searchText': searchText});
	};
	
	this.advanceSearch = function(searchData) {
		table.datagrid('reload', searchData);
	};
	
	this.removeRow = function(index) {
		table.datagrid('deleteRow', index);
	};
	
	var init = function() {
		table.datagrid({
			url: mainWeb + '/admin/goods/getGoodsList',
			/*
			queryParams: {
				categoryId: nCategoryId
			},
			*/
			singleSelect: true,
			pagination: true,
			//fitColumns: true,
			//fit: true,
			columns: [[
				{field:'goodscode',title:'��Ʒ���',width:100},
				{field:'goodsno',title:'��Ʒ����',width:100},
				{field:'goodsname',title:'��Ʒ����',width:200},
				{field:'goodsspec',title:'���',width:100, align:"center"},
				{field:'goodsunit',title:'��λ',width:60, align:"center"},
				{field:'originalprice',title:'ԭ��',width:80, align:"right"},
				{field:'realprice',title:'�ּ�',width:80, align:"right"},
				{
			    	field:'action',
				        	title:'����', 
				        	width:'60', 
				        	align: 'center',
				        	formatter : function(value, row, index) {
								var e = "<a href='#' class='operateChannel' onclick=\"_goodsApp.editGoods('" + row.recid + "')\">�༭</a> ";
								var d = "<a href='#' class='operateChannel' onclick=\"_goodsApp.deleteGoods('" + row.recid + "', " + index + ")\">ɾ��</a> ";
								return e + d;
							}
			    }
			]]
		});
	};
	init();
}

function Window(id) {
	
	var win = $('#' + id);
	var goodsCategoryTree = $('#eGoodsCategoryTree');
	var confirmButton = $('#Button_ConfirmSelect');
	var confirmCancel = $('#Button_CancelSelect');
	var table = new EGoodsTable('');
	
	this.open = function() {
		win.window('open');
	};
	
	this.close = function() {
		win.window('close');
	};
	
	this.getWin = function() {
		return win;
	};
	
	var closeListener = function() {
		// table.datagrid('rejectChanges');
	};
	
	var init = function() {
		win.window({
			width:1005,  
	    	height:546,
	    	collapsible: false,
	    	//resizable: false,
	    	scrollbarSize: 15,
	    	minimizable: false,
	    	maximizable: false,
	    	onClose: closeListener,
	    	title: 'ѡ����Ʒ��ERP��',
	    	modal:true 
		});
		goodsCategoryTree.tree({
			//url: mainWeb + '/admin/goods/getECategoryTree',
			url: mainWeb + '/admin/goods/getETreeChildrenNode',
			width: 200,
			onSelect: function(node) {
				//table = new EGoodsTable(node.id); 
				table.reload(node.id);
			}
		});
		//table = new EGoodsTable('');
		
		confirmButton.unbind("click");
		confirmButton.click(function() {
			var eGoodsRow = table.getSelection();
			if (null == eGoodsRow) {
				alert("��ѡ����Ʒ��");
				return;
			}
			win.trigger('confirmESelect',{selections: eGoodsRow}); 
		});
		confirmCancel.unbind("click");
		confirmCancel.click(function() {
			win.window('close');
		});
	};
	
	init();
}

function WindowForCreate(nCategoryId, goodsInfo, isEdit) {
	//step1 �򿪱༭���ڣ���ʾ��Ʒ������Ϣ
	//step2 ȡ����Ʒ����������Ϣ����ʾ
	var win = $('#goodsCreateWin');
	var confirmButton = $('#c_button_save');
	var cancelButton = $('#c_button_cancel');
	var edit = false;
	if (isEdit) {
		nCategoryId = goodsInfo.categoryid1;
		edit = true;
	}
	
	
	//var $salePriceEditor = $("<input type='text' name='realprice' />");
	var $inventoryCountEditor = $("<input type ='text' name='inventoryCount' />");
	var $vantageEditor = $("<input name='vantagestype' />");
	var $freeDeliverEditor = $("<input />");
	var $mostSalesEditor = $("<input />");
	var $popularEditor = $("<input />");
	var $goodsTypeEditor = $("<input />");
	
	var propertEditors = new Array();
	
	this.getWin = function() {
		return win;
	};
	
	var initBaseEditors = function() {
		//$salePriceEditor.appendTo($("#goods_salePriceEditor"));
		$inventoryCountEditor.appendTo($("#goods_inventoryCountEditor"));
		$vantageEditor.appendTo($("#goods_vantageEditor"));
		$freeDeliverEditor.appendTo($("#goods_freeDeliverEditor"));
		$mostSalesEditor.appendTo($("#goods_isPopularEditor"));
		$popularEditor.appendTo($("#goods_isMostSalesEditor"));
		$goodsTypeEditor.appendTo($("#goods_isBookEditor"));
		
		//$salePriceEditor.addClass('editor');
		$inventoryCountEditor.addClass('editor');
		$vantageEditor.addClass('editor');
		$freeDeliverEditor.addClass('editor');
		$mostSalesEditor.addClass('editor');
		$popularEditor.addClass('editor');
		$goodsTypeEditor.addClass('editor');
		
		/*
		$salePriceEditor.numberbox({  
		    min:0,
		    precision:2,
		    required: true
		}); 
		*/
		$inventoryCountEditor.numberbox({  
		    min:0,  
		    precision:2,
		    required: false  
		});
		
		$vantageEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
			url: mainWeb + '/admin/goods/getVantageList',
		    required: true,
		    editable:false
		});
		
		$freeDeliverEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
	        data: [{'code':'false', 'title': '��'}, {'code':'true', 'title': '��'}],
		    required: true,
		    editable:false
		});
		
		$mostSalesEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
	        data: [{'code':'false', 'title': '��'}, {'code':'true', 'title': '��'}],
		    required: true,
		    editable:false
		});
		
		$popularEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
	        data: [{'code':'false', 'title': '��'}, {'code':'true', 'title': '��'}],
		    required: true,
		    editable:false
		});
		
		$goodsTypeEditor.combobox({
			valueField: 'code',  
	        textField: 'name',
	        url: mainWeb + '/admin/goods/getGoodsTypeList',
		    required: true,
		    editable:false
		});
		
	};
	var initBaseInfo = function() {
		initBaseEditors();
		
		var goodsCode = edit ? goodsInfo.goodscode : goodsInfo.goodsCode;
		var goodsNo = edit ? goodsInfo.goodsno : goodsInfo.goodsNo;
		var goodsName = edit ? goodsInfo.goodsname : goodsInfo.goodsName;
		var goodsSpec = edit ? goodsInfo.goodsspec : goodsInfo.goodsSpec;
		var goodsUnit = edit ? goodsInfo.goodsunit : goodsInfo.goodsUnit;
		var originalPrice = edit ? goodsInfo.originalprice : goodsInfo.originalPrice;
		var salePrice = edit ? goodsInfo.realprice : goodsInfo.salePrice;
		var vantagesType = edit ? goodsInfo.vantagestype : '1';
		var freeDelivery = edit ? goodsInfo.freedelivery : false;
		var mostSale = edit ? goodsInfo.mostSales : false;
		var popular = edit ? goodsInfo.popular : false;
		var goodsType = edit ? goodsInfo.goodsType : '0';
		var inventoryCount = edit ? goodsInfo.inventoryCount : '';
		
		$('#c_label_code').html(goodsCode);
		$("#c_label_no").html(goodsNo);
		$('#c_label_name').html(goodsName);
		$('#c_label_spec').html(goodsSpec);
		$('#c_label_unit').html(goodsUnit);
		$('#c_label_originalPrice').html(originalPrice.toFixed(2));
		$('#c_label_saleprice').html(salePrice.toFixed(2));
		
		//$salePriceEditor.numberbox('setValue', salePrice);
		$vantageEditor.combobox('select', vantagesType);
		$freeDeliverEditor.combobox('select', freeDelivery + '');
		$mostSalesEditor.combobox('select', mostSale + '');
		$popularEditor.combobox('select', popular + '');
		$goodsTypeEditor.combobox('select', goodsType + '');
		$inventoryCountEditor.numberbox('setValue', inventoryCount);
	};
	/*
	var initPropertyEditors = function(properties) {
		for (var index = 0; index < properties.length; index++) {
			var property = properties[index];
			$('#c_label_property' + index).html(property.propertiyname + "��");
			var values = property.values;
			var valueEditor = null;
			var editorParent = $('#c_span_property' + index);
			var name = "propertiy" + (index + 1);
			var defaultValue = getGoodsPropertyValueByIndex(index);
			if (null == values || values.length <1) {
				valueEditor = getTextValueEditor(editorParent, null, name, defaultValue);
			} else if (values.length == 1) {
				valueEditor = getTextValueEditor(editorParent, values[0], name, defaultValue);
			} else {
				valueEditor = getComboValueEditor(editorParent, values, name, defaultValue);
			}
			propertEditors[index] = valueEditor;
		}
	};*/
	/*
	var getGoodsPropertyValueByIndex = function(index) {
		if (!edit) return null;
		switch(index) {
		case 0:
			return goodsInfo.propertiy1;
		case 1:
			return goodsInfo.propertiy2;
		case 2:
			return goodsInfo.propertiy3;
		case 3:
			return goodsInfo.propertiy4;
		case 4:
			return goodsInfo.propertiy5;
		default:
			return null;
		}
	};
	var getTextValueEditor = function(parent, value, name, defaultValue) {
		var $valueEditor = $("<input type='text' name='" + name + "' />");
		$valueEditor.appendTo(parent);
		$valueEditor.addClass('editor');
		if (null == value) {
			$valueEditor.val(defaultValue);
			$valueEditor.validatebox({  
			    required: true
			});
		} else {
			$valueEditor.val(value.pvalue);
			$valueEditor.validatebox({  
			    required: true
			});
			$valueEditor.attr('disabled', true);
		}
		return $valueEditor;
	};
	var getComboValueEditor = function(parent, values, name, defaultValue) {
		var $valueEditor = $("<input name='" + name + "' />");
		$valueEditor.appendTo(parent);
		$valueEditor.addClass('editor');
		$valueEditor.combobox({
			valueField: 'pvalue',  
	        textField: 'pvalue',
	        data: values,
		    required: true
		});
		if (null == defaultValue) {
			$valueEditor.combobox('select', values[0].pvalue);
		} else {
			$valueEditor.combobox('select', defaultValue);
		}
		return $valueEditor;
	};
	var initPropertyInfo = function() {
		if (null == nCategoryId) return;
		$.post(mainWeb + '/admin/goods/getCategoryInfo', {"categoryId": nCategoryId}, function(data) {
			var categoryInfo = strToJson("{categoryInfo:" + data + "}").categoryInfo;
			var properties = categoryInfo.propertiesForm;
			if (properties == null || properties.length < 1) {
				$('#c_label_property0').html("<font color='red'>��Ʒ����δ�������ԣ������������ԡ�</font>");
				confirmButton.hide();
				return;
			} else {
				initPropertyEditors(properties);
				confirmButton.show();
			}
		});
	};*/
	
	var validateInput = function() {
		var baseEdtors = new Array();
	//	baseEdtors[0] = $salePriceEditor;
		//baseEdtors[1] = $inventoryCountEditor;
		baseEdtors[0] = $vantageEditor;
		baseEdtors[1] = $freeDeliverEditor;
		baseEdtors[2] = $mostSalesEditor;
		baseEdtors[3] = $popularEditor;
		baseEdtors[4] = $goodsTypeEditor;
		for (var index = 0; index < baseEdtors.length; index++) {
			try {
				if (!baseEdtors[index].validatebox('isValid')) {
					return false;
				}
			} catch (m) {
				if (!baseEdtors[index].combobox('isValid')) {
					return false;
				}
			}
		}
		for (var index = 0; index < propertEditors.length; index++) {
			try {
				if (!propertEditors[index].validatebox('isValid')) {
					return false;
				}
			} catch (m) {
				if (!propertEditors[index].combobox('isValid')) {
					return false;
				}
			}
		}
		return true;
		//return $('#createGoodsFrom').form('validate');
	};
	
	var saveGoods = function() {
		var jsonStr = "{";
		jsonStr += "recid: '" + goodsInfo.recid + "'";
		jsonStr += ", categoryId: '" + nCategoryId + "'";
		jsonStr += ", goodsCode: '" + goodsInfo.goodsCode + "'";
		jsonStr += ", goodsNo: '" + goodsInfo.goodsNo + "'";
		jsonStr += ", goodsName: '" + goodsInfo.goodsName + "'";
		jsonStr += ", goodsSpec: '" + goodsInfo.goodsSpec + "'";
		jsonStr += ", goodsUnit: '" + goodsInfo.goodsUnit + "'";
		jsonStr += ", originalPrice: '" + goodsInfo.originalPrice + "'";
		jsonStr += ", vantagestype: '" + $vantageEditor.combobox('getValue') + "'";
		jsonStr += ", salePrice: '" + goodsInfo.salePrice + "'";
		jsonStr += ", freedelivery: '" + $freeDeliverEditor.combobox('getValue') + "'";
		jsonStr += ", inventoryCount: '" + $inventoryCountEditor.val() + "'";
		jsonStr += ", isMostSales: '" + $mostSalesEditor.combobox('getValue') + "'";
		jsonStr += ", isPopular: '" + $popularEditor.combobox('getValue') + "'";
		jsonStr += ", goodsType: '" + $goodsTypeEditor.combobox('getValue') + "'";
		for (var index = 0; index < propertEditors.length; index++) {
			var value = null;
			try {
				value = propertEditors[index].combobox('getValue');
			} catch (m){
				value = null;
			}
			if (null == value) {
				value = propertEditors[index].val();
			}
			jsonStr += ", property" + index + ": '" + value + "'";
		}
		for (var index = propertEditors.length; index < 5; index++) {
			jsonStr += ", property" + index + ": ''";
		}
		jsonStr += "}";
		$.ajax({
			type: 'POST',
		  	url: mainWeb + '/admin/goods/createGoods',
		  	data: {"dataString" : jsonStr},
		  	dataType: 'json',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8",
		  	async: false,
			success: function(data) {
				if (data.result) {
			 		//alert("����ɹ���");
			 		//win.window('close');
			 		win.trigger('confirmCreate'); 
			 		_goodsApp.tableDataChangeAction();
			 		$.messager.alert("��ʾ", "����ɹ���", "info", function() {
			 			win.window('close');
			 		});
				} else {
					alert("����ʧ�ܡ�");
				}
			},
			error: function() {
				alert("����ʧ�ܡ�");
			}
		});
	};
	
	var saveEdit = function() {
		var jsonStr = "{";
		jsonStr += "goodsId: '" + goodsInfo.recid + "'";
		jsonStr += ", vantagestype: '" + $vantageEditor.combobox('getValue') + "'";
		//jsonStr += ", realprice: '" + $salePriceEditor.val() + "'";
		jsonStr += ", freedelivery: '" + $freeDeliverEditor.combobox('getValue') + "'";
		jsonStr += ", inventoryCount: '" + $inventoryCountEditor.val() + "'";
		jsonStr += ", isMostSales: '" + $mostSalesEditor.combobox('getValue') + "'";
		jsonStr += ", isPopular: '" + $popularEditor.combobox('getValue') + "'";
		jsonStr += ", goodsType: '" + $goodsTypeEditor.combobox('getValue') + "'";
		for (var index = 0; index < propertEditors.length; index++) {
			var value = null;
			try {
				value = propertEditors[index].combobox('getValue');
			} catch (m){
				value = null;
			}
			if (null == value) {
				value = propertEditors[index].val();
			}
			jsonStr += ", property" + index + ": '" + value + "'";
		}
		for (var index = propertEditors.length; index < 5; index++) {
			jsonStr += ", property" + index + ": ''";
		}
		jsonStr += "}";
		$.ajax({
			type: 'POST',
		  	url: mainWeb + '/admin/goods/modifyGoodsVo',
		  	data: {"dataString" : jsonStr},
		  	dataType: 'json',
		  	contentType: "application/x-www-form-urlencoded; charset=utf-8",
		  	async: false,
			success: function(data) {
				if (data.result) {
			 		//alert("����ɹ���");
			 		//win.window('close');
			 		_goodsApp.tableDataChangeAction();
			 		$.messager.alert("��ʾ", "����ɹ���", "info", function() {
			 			win.window('close');
			 		});
				} else {
					//alert("����ʧ�ܡ�");
					$.messager.alert("��ʾ", "����ʧ�ܡ�");
				}
			},
			error: function() {
				alert("����ʧ�ܡ�");
			}
		});
	};
	var closeListener = function() {
		// empty base area
		$('#c_label_code').html("");
		$("#c_label_no").html("");
		$('#c_label_name').html("");
		$('#c_label_spec').html("");
		$('#c_label_unit').html("");
		$('#c_label_originalPrice').html("");
		$('#c_label_salePrice').html("");
		//$salePriceEditor.remove();
		$inventoryCountEditor.remove();
		$vantageEditor.combobox('destroy');
		$freeDeliverEditor.combobox('destroy');
		$mostSalesEditor.combobox('destroy');
		$popularEditor.combobox('destroy');
		$goodsTypeEditor.combobox('destroy');
		
		// empty property area
		for (var index = 0; index < 5; index++) {
			$('#c_label_property' + index).empty();
		}
		if (propertEditors != null) {
			for (var index = 0; index < propertEditors.length; index++) {
				try {
					$(propertEditors[index]).combobox('destroy');
				} catch (m) {
					$(propertEditors[index]).remove();
				}
			}
		}
		
	};
	var init = function() {
		win.window({
			width:550,  
	    	height:360,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	title: '������Ʒ',
	    	onClose: closeListener,
	    	modal:true 
		});
		
		initBaseInfo();
		
		//initPropertyInfo();
		
		confirmButton.unbind("click");
		confirmButton.click(function() {
			if (validateInput()) {
				if (edit) {
					saveEdit();
				} else {
					saveGoods();
				}
			}
		});
		cancelButton.unbind("click");
		cancelButton.click(function() {
			win.window('close');
		});
		
		win.window('open');
		$.parser.parse('#goodsCreateWin');
	};
	init();
}
function EGoodsTable(nCategoryId) {
	var table = $('#Table_EGoodsList');
	
	this.getSelection = function() {
		return table.datagrid('getSelections');
	};
	
	this.reload = function(categoryId) {
		table.datagrid('reload', {"categoryId": categoryId});
	};
	
	var init = function() {
		table.datagrid({
			pagination: true,
			pagePosition: 'top',
			singleSelect: false,
			checkbox: true,
			width: 770,
			height: 450,
			url: mainWeb + '/admin/goods/getEGoodsList',
			queryParams: {
				categoryId: nCategoryId
			},
			fitColumns: false,
			columns: [[
				{field:'recid', checkbox: true, width:50},
				{field:'goodsCode',title:'��Ʒ���',width:120},
				{field:'goodsNo',title:'��Ʒ����',width:120},
				{field:'goodsName',title:'��Ʒ����',width:100},
				{field:'goodsSpec',title:'���',width:120},
				{field:'goodsUnit',title:'��λ',width:70},
				{field:'originalPrice',title:'ԭ��',width:80},
				{field:'salePrice',title:'�ּ�',width:80}
			]]
		});
	};
	init();
}
</script>
<div class="easyui-layout" fit="true" >
	<!--  div region="north"	id="top_area" style="height: 28px; padding-top: 1px; background-color: #EEF;" border="false">
		<span style="position: absolute; top: 7px;" id="Label_Path">ȫ��</span>
	</div>-->
    <div class="easyui-layout" region="west" id="goodsCategory" split="false" border="true" id="left_area" title="��Ʒ����" style="width:250px;">
		<div id="content" region="center" id="right_area" border="false">
			<ul id='goodsCategoryTree_g' class='easyui-tree' fit="true"></ul>
		</div>
    </div>  
    <div id="content" region="center" id="right_area" title="��Ʒ�б�" style="padding:0px;" fit="false" border="true"> 
    	<div class="easyui-layout" fit="true" split="false" id="test_area" border="false">
		    <div region="north"	id="right_bottom_area" style="height: 35px; background-color: #EEF;" border="false">
				<div style='float:left;'>
					<input type="button" style='margin-top: 5px;' value="������Ʒ" id="Button_NewGoods"/> 
					<label id='goods_addtip' style='display:inline; display:inline-block;margin-top: 10px;'>
						<font style='font-size:12px;color:red;'>˵����ֻ�������������������Ʒ��</font>
					</label>
				</div>
				<!-- a id="Button_NewGoods" style='margin-top: 5px;' href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">������Ʒ</a> -->
				<span style="float:right;margin-right: 10px;padding-top: 2px;">
					<input type="text" id='goods_search_text' value="" style="width:200px;height:18px;"/>
					<a href="#" class="easyui-linkbutton" iconCls="icon-search" id='goods_search_action'>����</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id='goods_search_Advance'>�߼�����</a>
				</span>
			</div>
		    <div region="center" style="padding:0px;" border="false">
				<table id="Table_GoodsList" class="easyui-datagrid"  fit='true' rownumbers="true" border='false'>  
				</table>
			</div>
		</div>
    </div>  
</div>
<div id='win'>
	<div class='easyui-layout' fit='true'>
	    <div class='easyui-layout' region='west' split='false' border='true' title='��Ʒ����' style='width:220px;'>
			<div region='center' border='false'>
				<ul id='eGoodsCategoryTree' class='easyui-tree' fit='false' ></ul>
			</div>
	    </div>  
	    <div region='center' title='��Ʒ�б�' style='padding:0px;overflow-y:scroll;' fit='true' border='true'> 
	    	<table id='Table_EGoodsList' class='easyui-datagrid'  fit='false' rownumbers='true' border='false' pagination='true'>  
			</table>
	    </div>
	    <div region='south'	style='position: relative; left: 0px; height: 28px; background-color: #EEF;' border='false'>
	    	<div style='position: relative; text-align:right'>
				<input type='button' value='ȷ��' id='Button_ConfirmSelect'/>
				<input type='button' value='ȡ��' id='Button_CancelSelect'/>
			</div>
		</div>
	</div>
</div>
<div id='goodsCreateWin'>
	<div class='easyui-layout' fit='true'>
	<!-- form action="" id='createGoodsFrom'> -->
	 	<div region='center' style='padding:10px;' fit='true' border='true'>  
		<p>
			<span class='formItem'><label class='label'>��Ʒ��ţ�</label><label id='c_label_code'></label></span>
			<span class='formItem'><label class='label'>��Ʒ���룺</label><label id='c_label_no'></label></span>
		</p>
		<p>
			<span class='formItem'><label class='label'>��Ʒ���ƣ�</label><label id='c_label_name'></label></span>
			<span class='formItem'><label class='label'>��Ʒ���</label><label id='c_label_spec'></label></span>
		</p>
		<p>
			<span class='formItem'><label class='label'>��Ʒ��λ��</label><label id='c_label_unit'></label></span>
			<span class='formItem'><label class='label'>��Ʒԭ�ۣ�</label><label id='c_label_originalPrice'></label></span>
		</p>
		<p>
			<span class='formItem'><label class='label'>���ۼ۸�</label><label id='c_label_saleprice'></label></span>
			<span class='formItem'><label class='label'>���������</label><span id='goods_inventoryCountEditor'></span></span>
		</p>
		<p>
			<span class='formItem'><label class='label'>���ֹ���</label><span id='goods_vantageEditor'></span></span> 
			<span class='formItem'><label class='label'>������ͣ�</label><span id='goods_freeDeliverEditor'></span></span>
		</p>
		<p>
			<span class='formItem'><label class='label'>�Ƿ�������</label><span id='goods_isPopularEditor'></span></span>
			<span class='formItem'><label class='label'>�Ƿ�������</label><span id='goods_isMostSalesEditor'></span></span>
		</p>
		<p>
			<span class='formItem'><label class='label'>��Ʒ���ͣ�</label><span id='goods_isBookEditor'></span></span>
			<!-- span class='formItem'><label id='c_label_property0' class='label'></label><span id='c_span_property1'></span></span> -->
		</p>
		<!--
		<p>
			<span class='formItem'><label id='c_label_property1' class='label'></label><span id='c_span_property0'></span></span>
			<span class='formItem'><label id='c_label_property2' class='label'></label><span id='c_span_property1'></span></span>
		</p>
		<p>
			<span class='formItem'><label id='c_label_property3' class='label'></label><span id='c_span_property2'></span></span>
			<span class='formItem'><label id='c_label_property4' class='label'></label><span id='c_span_property3'></span></span>
		</p> -->
		</div>
		<div region='south'	style='position: relative; left: 0px; height: 28px; background-color: #EEF;' border='false'>
			<div id='c_area_bottom' style='text-align: right;'>
				<input type='button' value='����' id='c_button_save'/>
				<input type='button' value='ȡ��' id='c_button_cancel'/>
			</div>
		</div>
		<!--  /form> -->
	</div>
</div>
<div id='goodsEditWin'>
	<div class='easyui-layout' fit='true'>
	 	<div region='center' id='e_area_content' style='padding:10px;' fit='true' border='true'>  
		</div>
		<div region='south'	style='position: relative; left: 0px; height: 28px; background-color: #EEF;' border='false'>
			<div id='e_area_bottom' style='text-align: right;'>
			</div>
		</div>
	</div>
</div>
</body>
</html>