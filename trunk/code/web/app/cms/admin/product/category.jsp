<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品发布</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<script type="text/javascript">
var _categoryApp;
var _propertyTable;
var _pValueTable;
var _goodsCategoryTree;
var _categoryInfo;

$(document).ready(function() {
	_categoryApp = new GoodsCategory();
	_categoryApp.init();
});
	
function GoodsCategory() {
	_propertyTable = new PropertyTable();
	_pValueTable = new ProertyValueTable();
	_goodsCategoryTree = new CategoryCmsTree("goodsCategoryTree");
	
	this.init = function() {
		$("#Button_New").click(function() {
			// $('#createWin').window('open');  
			// createWin.open();
			var node = _goodsCategoryTree.getSelection();
			var categoryNo = '';
			if (node.id != 'root') {
				categoryNo = node.attributes.categoryNo;
			}
			new CategoryCreateWindow(categoryNo);
		});
		
		$("#Button_Edit").click(function() {
			var selectedNode = _goodsCategoryTree.getSelection();
			if (null == selectedNode) {
				$.messager.alert("提示", "请先选择要修改的分类。");
				return;
			}
			
			$('#editWin').window('open');
			var categoryNo = selectedNode.attributes.categoryNo;
			$('#Text_CategoryNo').val(categoryNo.substring(categoryNo.length - 2, categoryNo.length));
			$("#Text_CategoryName_E").val(selectedNode.text);
			
			var parentNode = _goodsCategoryTree.getParent(selectedNode);
			if (parentNode && parentNode.id != 'root') {
				$('#parent_categoryNo').html(parentNode.attributes.categoryNo);
			} else {
				$('#parent_categoryNo').empty();
			}
			$('#Text_CategoryNo').unbind('keyup');
			$('#Text_CategoryNo').keyup(function() {
				var value = $(this).val();
				$(this).val(value.replace(/[^\d]/g,''));
				if (value.length > 2) {
					$(this).val(value.substring(0, 2));
				}
			});
		});
		
		/*
		$("#Button_Cancel").click(function() {
			//$('#createWin').window('close'); 
			createWin.close();
		});
		*/
		$("#Button_Save_E").click(function() {
			var categoryNo = $('#Text_CategoryNo').val();
			var categoryName = $("#Text_CategoryName_E").val();
			if ($.trim(categoryNo) == '') $.messager.alert("提示", "分类编号不能为空");
			if ($.trim(categoryName) == '') $.messager.alert("提示", "分类名称不能为空");
			var selectedNode = _goodsCategoryTree.getSelection();
			var parentNode = _goodsCategoryTree.getParent(selectedNode);
			$.ajax({
				url: mainWeb + "/admin/goods/modifyCategory",
				data: {"categoryId":selectedNode.id, "categoryNo": categoryNo, "categoryName":categoryName},
				dataType: 'json',
				cache: false,
				success: function(data) {
					if (data.result) {
						$('#editWin').window('close'); 
						//_goodsCategoryTree.reload();
						$('#Text_CategoryNo').val('');
						$("#Text_CategoryName_E").val('');
						$('#parent_categoryNo').empty();
						_goodsCategoryTree.reload(parentNode);
					} else {
						$.messager.alert("提示", data.message);
					}
				},
				error: function() {
					// alert("保存失败。");
				}
			});
			
		});
		
		$("#Button_Cancel_E").click(function() {
			$('#editWin').window('close'); 
			$('#Text_CategoryNo').val('');
			$("#Text_CategoryName_E").val('');
			$('#parent_categoryNo').empty();
		});
		
		//addSaveCategoryAction();
		addSavePropertyAction();
		addDeleteCategoryAction();
		
		$("#Button_SaveProperty").hide();
	};
	
	this.nodeSelectionAction = function(node) {
		if (node.id != "root" && (node.attributes.levelNo == 3 
				|| node.attributes.propertied)) {
			$("#Button_New").hide();
		} else {
			$("#Button_New").show();
		}
		if (node.id != "root" && node.attributes.levelNo == 3 ) {
			$('#propertyContent').show();
		} else {
			$('#propertyContent').hide();
		}
		if (node.id == "root" || _goodsCategoryTree.getChildren(node).length > 0 || node.state == "closed") {
			_categoryApp.hideRightArea();
			$("#Button_C_Delete").hide();
			return;
		}
		$("#Button_C_Delete").show();
		_categoryApp.showRightArea();
		$.post(mainWeb + '/admin/goods/getCategoryInfo', {"categoryId": node.id}, function(data) {
			_categoryInfo = strToJson("{categoryInfo:" + data + "}").categoryInfo;
			_categoryApp.showCategoryInfo();
			_categoryApp.showProperties();
		});
	};
	
	this.hideRightArea = function() {
		$("#Button_SaveProperty").hide();
	};
	
	this.showRightArea = function() {
		$("#Button_SaveProperty").show();
	};
	
	this.showCategoryInfo = function() {
		// baseinfo
		$("#Label_CategoryName").html(_categoryInfo.categoryname);
		$("#Label_CategoryNo").html(_categoryInfo.categoryno);
	};
	
	this.showProperties = function() {
		var properties = _categoryInfo.propertiesForm;
		if (null == properties) return ;
		var pNames = "[";
		for (var index = 0; index < properties.length; index++) {
			if (0 != index) {
				pNames += ",";
			}
			var property = new Property(properties[index].ordinal, properties[index].propertiyname);
			var iValues = properties[index].values;
			var newValueArray = new Array();
			for (var vIndex = 0; vIndex < properties[index].values.length; vIndex++) {
				var propertyValue = new PropertyValue(iValues[vIndex].ordinal, iValues[vIndex].pvalue);
				newValueArray[vIndex] = propertyValue;
			}
			property.setValues(newValueArray);
			addProperty(property);
			pNames += "{pName: '" + property.getPropertiyName() + "'}";
		}
		pNames += "]";
		_propertyTable.loadData(strToJson(pNames));
		_pValueTable.loadData();
	};
	
	var addDeleteCategoryAction = function() {
		// deleteCategory
		$("#Button_C_Delete").click(function() {
			var selectedNode = _goodsCategoryTree.getSelection();
			if (null == selectedNode) {
				$.messager.alert("提示","请先选择要删除的分类。");
				return;
			}
			var parentNode = _goodsCategoryTree.getParent(selectedNode);
			$.messager.confirm('确认', '确认要删除该分类吗?', function(r) {
				if (r) {
					$.ajax({
						url: mainWeb + "/admin/goods/deleteCategory",
						data: {"categoryId":selectedNode.id},
						dataType: 'json',
						success: function(data) {
							if (data.result) {
								var sblings = _goodsCategoryTree.getChildren(parentNode);
								if (sblings.length == 1) {
									var parentOfParent = _goodsCategoryTree.getParent(parentNode);
									_goodsCategoryTree.reload(parentOfParent);
								} else {
									_goodsCategoryTree.reload(parentNode);
								}
							} else {
								$.messager.alert("提示", data.message, "info");
							}
						},
						error: function() {
							$.messager.alert("提示", "删除时，发生错误。");
						}
					});
				}
			});
		});
	};
	
	this.addSaveCategoryAction = function(categoryNo, categoryName) {
		//var categoryNo = code;
		//var categoryName = name;
		var selectedNode = _goodsCategoryTree.getSelection();
		var parentId = selectedNode == null ? '' : selectedNode.id;
		var result = false;
		$.ajax({
			url: mainWeb + "/admin/goods/createGoodsCategory",
			async: false,
			cache: false,
			data: {"categoryNo":categoryNo, "categoryName":categoryName, "parentId":parentId},
			dataType: 'json',
			success: function(data) {
				if (data.result) {
					if (null != selectedNode) {
						_goodsCategoryTree.append({id: 'new', text: categoryName}, selectedNode);
						_goodsCategoryTree.reload(selectedNode);
					}
				} else {
					$.messager.alert("提示", data.message);
				}
				result = data.result;
			},
			error: function() {
				// alert("保存失败。");
			}
		});
		return result;
		/*
		$("#Button_Save").click(function() {
			var categoryNo = $("#Text_CategoryNo").val();
			var categoryName = $("#Text_CategoryName").val();
			var selectedNode = _goodsCategoryTree.getSelection();
			var parentId = selectedNode == null ? null : selectedNode.id;
			$.ajax({
				url: mainWeb + "/admin/goods/createGoodsCategory",
				data: {"categoryNo":categoryNo, "categoryName":categoryName, "parentId":parentId},
				dataType: 'json',
				success: function(data) {
					//$('#createWin').window('close'); 
					createWin.close();
					_goodsCategoryTree.reload();
				},
				error: function() {
					// alert("保存失败。");
				}
			});
		});
		*/
	};
	
	var addSavePropertyAction = function() {
		$("#Button_SaveProperty").click(function() {
			// 先临时存储当前编辑的属性
			var currPRow = _propertyTable.getSelection();
			if (null != currPRow) {
				var property = new Property(_propertyTable.getRowIndex(currPRow), currPRow.pName);
				var vDatas = _pValueTable.getData();
				var values = new Array();
				for (var vIndex = 0; vIndex < vDatas.length; vIndex++) {
					var vData = vDatas[vIndex];
					var propertyValue = new PropertyValue(vIndex, vData.vName);
					values[vIndex] = propertyValue;
				}
				property.setValues(values);
				addProperty(property);
			}
			
			var pDatas = _propertyTable.getDatas();
			var jsonStr = "[";
			for (var index = 0; index < pDatas.length; index++) {
				if (pDatas[index].pName == null || "" == pDatas[index].pName ) continue;
				if (index != 0) {
					jsonStr += ",";
				}
				var preProperty = getInfoPropertyByIndex(index);
				var propertyId = null;
				if (null != preProperty) {
					propertyId = preProperty.recid; 
				}
				jsonStr += "{";
				jsonStr += "'recid':'" + propertyId + "'"; 
				jsonStr += ", 'recver':'0'"; 
				jsonStr += ", 'categoryid':'" + _categoryInfo.recid + "'";
				jsonStr += ", 'propertiyname': '" + pDatas[index].pName + "'";
				jsonStr += ", 'ordinal': '" + index + "'";
				var pValus = getPropertyByIndex(index).getValues();
				jsonStr += ", values: [" + convertValuesToString(propertyId, pValus) + "]";
				jsonStr += "}";
			}
			jsonStr += "]";
			$.ajax({
				type: 'POST',
	  			url: mainWeb + '/admin/goods/modifyProperties',
	  			data: {"propertiesStr": jsonStr},
	  			dataType: 'json',
	  			contentType: "application/x-www-form-urlencoded; charset=utf-8",
	  			async: false,
	  			success: function(data) {
	  				if (data.result) {
	  					$.messager.alert("提示", "保存成功。");
	  				} else {
	  					if (data.message == null) {
							$.messager.alert("提示", "保存失败");
	  					} else {
	  						$.messager.alert("提示", data.message);
	  					}
					}
	  			}
			});
		});
	};
	var getInfoPropertyByIndex = function(index) {
		if (_categoryInfo.propertiesForm == null) return null;
		for (var pIndex = 0; pIndex < _categoryInfo.propertiesForm.length; pIndex++) {
			var property = _categoryInfo.propertiesForm[pIndex];
			if (property.ordinal == index) {
				return property;
			}
		}
		return null;
	};
	
	var convertValuesToString = function(propertyId, values) {
		if (null == values) return null;
		var jsonStr = "";
		for (var index = 0; index < values.length; index++) {
			if (0 != index) {
				jsonStr += ",";
			}
			var value = values[index];
			jsonStr += "{";
			jsonStr += "'recid': 'null'";
			jsonStr += ", 'recver': '0'";
			jsonStr += ", 'propertyid': '" + propertyId + "'";
			jsonStr += ", 'categoryid': '" + _categoryInfo.recid + "'";
			jsonStr += ", 'pvalue': '" + value.getPValue() + "'";
			jsonStr += ", 'ordinal': '" + index + "'";
			jsonStr += "}";
		}
		return jsonStr;
	};
}

function CategoryCreateWindow(parentCode) {
	var $win = $('<div></div>');
	var $confirmButton = $("<a href='javascript:void(0)'>保存</a>");
	var $cancelButton = $("<a href='javascript:void(0)'>取消</a>");
	var $codeEditor = $("<input type='text' style='width: 50px;'\"/>");
	var $nameEditor = $("<input type='text' />");
	
	this.close = function() {
		$win.window('close');
	};
	
	this.open = function() {
		$win.window('open');
	};
	
	var renderInit = function() {
		var $formArea = $("<form style='padding:10px 20px 10px 40px;'></form>");
		var $codeRow = $('<p><label>分类编号：' + parentCode + '</label></p>');
		var $nameRow = $('<p><label>分类名称：</label></p>');
		var $buttonArea = $("<div style='padding:5px;text-align:center;'></div>");
		$win.appendTo('body');
		$formArea.appendTo($win);
		$codeRow.appendTo($formArea);
		$nameRow.appendTo($formArea);
		$buttonArea.appendTo($formArea);
		
		$codeEditor.appendTo($codeRow);
		$nameEditor.appendTo($nameRow);
		
		$confirmButton.appendTo($buttonArea);
		$cancelButton.appendTo($buttonArea);
		
		$confirmButton.linkbutton({
			iconCls: 'icon-ok'
		});
		
		$cancelButton.linkbutton({
			iconCls: 'icon-cancel'
		});
		
		$codeEditor.keyup(function() {
			var value = $(this).val();
			$(this).val(value.replace(/[^\d]/g,''));
			if (value.length > 2) {
				$(this).val(value.substring(0, 2));
			}
		});
	};
	
	var validateInput = function() {
		if ($codeEditor.val() == null || $codeEditor.val() == "") {
			$.messager.alert("提示", '分类编号不能为空。');
			return false;
		}
		if ($.trim($codeEditor.val()).length != 2) {
			$.messager.alert("提示", '分类编号必须为两位数。');
			return false;
		}
		if ($nameEditor.val() == null || $nameEditor.val() == "") {
			$.messager.alert("提示",'分类名称不能为空。');
			return false;
		}
		return true;
	};
	var init = function() {
		if (parentCode == null) {
			parentCode = '';
		}
		renderInit();
		$win.window({
			collapsible: false, 
			modal: true, 
			resizable: false, 
			minimizable: false, 
			maximizable: false, 
			onClose: function() {
				$win.remove();
			},
			title: "新增分类", 
			width:350,
			height:230
		});
		
		$confirmButton.click(function() {
			if (!validateInput())return;
			var saveSuccess = _categoryApp.addSaveCategoryAction($codeEditor.val(), $nameEditor.val());
			if (saveSuccess) {
				$win.window('close');
			}
		});
		
		$cancelButton.click(function() {
			$win.window('close');
		});
	};
	init();
}
/**
 * 分类树
 * attributes: levelNo, propertied
 * @param {} treeId
 */
function CategoryCmsTree(treeId) {
	var id = treeId;
	var goodsCategoryTree = $("#" + id);
	this.getSelection = function() {
		return $(goodsCategoryTree).tree("getSelected");
	};
	
	this.getParent = function(node) {
		if (!node) return null;
		return $(goodsCategoryTree).tree("getParent", node.target);
	};
	
	/**
	 * 0新增 1修改 2删除
	 */
	this.reload = function(node, type) {
		if (node && node.id != 'root') {
			$(goodsCategoryTree).tree('reload', node.target);
		} else {
			$(goodsCategoryTree).tree('reload');
		}
	};
	
	this.getChildren = function(node) {
		return goodsCategoryTree.tree('getChildren', node.target);
	};
	
	this.append = function(node, parent) {
		return goodsCategoryTree.tree('append', {parent: parent.target, data: [{id: node.id, text: node.text}]});
	};
	
	var init = function() {
		var lastSelectedNodeId = null;
		goodsCategoryTree.tree({
			//url: mainWeb + "/admin/goods/getCategoryTree",
			url: mainWeb + "/admin/goods/getTreeChildrenNode",
			onSelect: function(node) {                                
				// alert(node.attributes.levelNo + ":" + node.attributes.propertied);
				if (node.id == lastSelectedNodeId) return; 
				_categoryApp.nodeSelectionAction(node);
				lastSelectedNodeId = node.id;
			}
		});
	};
	init();
}

/**
 * 属性表
 */
function PropertyTable() {
	var table = $('#Table_Properties');
	// 属性表列
	var pColumns = [[
		{
			field:'pName',
	        title:'属性名称', 
	        width:'300', 
	        align: 'center', 
	        editor: 'text'
	    },{
	    	field:'action',
		        	title:'操作', 
		        	width:'60', 
		        	align: 'center',
		        	formatter : function(value, row, index) {
								if (row.editing) {
									var s = "<a href='#' class='operateChannel' onclick=\"saveRowAction('Table_Properties', " + index + ")\">保存</a> ";
									var c = "<a href='#' class='operateChannel' onclick=\"cancelEditRowAction('Table_Properties', " + index + ")\">取消</a> ";
									return s + c;
								} else {
									var e = "<a href='#' class='operateChannel' onclick=\"editRowAction('Table_Properties', " + index + ")\">编辑</a> ";
									var d = "<a href='#' class='operateChannel' onclick=\"deleteRowAction('Table_Properties', " + index + ")\">删除</a> ";
									return e + d;
								}
							}
	    }
	]];
	// 最后一个选择的行号
	var lastSelectedRowIndex = '-1';
	// 最后一个选择的属性名称
	var lastPName = null;
	var init = function() {
		table.datagrid({
			iconCls : 'icon-edit',
			singleSelect : true,
			toolbar: [{
							text : '增加',
							iconCls : 'icon-add',
							handler : addPropertyrow
						}],
			columns: pColumns,
			onBeforeEdit : function(index, row) {
					row.editing = true;
					table.datagrid('refreshRow', index);
					//editcount++;
				},
			onAfterEdit : function(index, row) {
				row.editing = false;
				table.datagrid('refreshRow', index);
				//editcount--;
			},
			onCancelEdit : function(index, row) {
				row.editing = false;
				table.datagrid('refreshRow', index);
				//editcount--;
			},
			onSelect: function(rowIndex, rowData) {
				// 先保存当前的属性值
				if (Number(lastSelectedRowIndex) == rowIndex) return;
				if (-1 != Number(lastSelectedRowIndex)) {
					var property = new Property(Number(lastSelectedRowIndex), lastPName);
					var vDatas = _pValueTable.getData();
					var values = new Array();
					for (var vIndex = 0; vIndex < vDatas.length; vIndex++) {
						var vData = vDatas[vIndex];
						var propertyValue = new PropertyValue(vIndex, vData.vName);
						values[vIndex] = propertyValue;
					}
					property.setValues(values);
					addProperty(property);
				}
				// 更新显示新选择的属性值
				lastSelectedRowIndex = "" + rowIndex;
				lastPName = rowData.pName;
				var property = getPropertyByIndex(rowIndex);
				if (null != property) {
					var values = property.getValues();
					var valuesOption = "[";
					for (var vIndex = 0; vIndex < values.length; vIndex++) {
						if (vIndex != 0) {
							valuesOption += ",";
						}
						valuesOption += "{vName: '" + values[vIndex].getPValue() + "'}";
					}
					valuesOption += "]";
					_pValueTable.loadData(strToJson(valuesOption));
				} else {
					_pValueTable.loadData(null);
				}
				//alert(rowData.pName);
			}
			
		});
		table.datagrid('acceptChanges');
	};
	
	this.appendRow = function(rows) {
		for (var rowIndex = 0; rowIndex < rows.length; rowIndex++) {
			var row = rows[rowIndex];
			table.datagrid('appendRow', row);
		}
	};
	
	this.loadData = function(datas) {
		lastSelectedRowIndex = '-1';
		table.datagrid('loadData', datas);
	};
	
	this.setSelection = function(index) {
		table.datagrid("selectRow", index);
	};
	this.getSelection = function() {
		return table.datagrid("getSelected");
	};
	
	this.getRowIndex = function(row) {
		return table.datagrid("getRowIndex", row);
	} ;
	this.getDatas = function() {
		return table.datagrid("getRows");
	};
	init();
}
/**
 * 属性值表
 */
function ProertyValueTable() {
	var table = $('#Table_Values');
	var vColumns = [[
		{
			field:'vName',
	        title:'属性值', 
	        width:'300', 
	        align: 'center', 
	        editor: 'text'
	    },{
	    	field:'action',
		        	title:'操作', 
		        	width:'60', 
		        	align: 'center',
		        	formatter : function(value, row, index) {
								if (row.editing) {
									var s = "<a href='#' class='operateChannel' onclick=\"saveRowAction('Table_Values', " + index + ")\">保存</a> ";
									var c = "<a href='#' class='operateChannel' onclick=\"cancelEditRowAction('Table_Values', " + index + ")\">取消</a> ";
									return s + c;
								} else {
									var e = "<a href='#' class='operateChannel' onclick=\"editRowAction('Table_Values', " + index + ")\">编辑</a> ";
									var d = "<a href='#' class='operateChannel' onclick=\"deleteRowAction('Table_Values', " + index + ")\">删除</a> ";
									return e + d;
								}
							}
	    }
	]];
	var init = function() {
		table.datagrid({
			columns:vColumns,
	    	toolbar: [{
							text : '增加',
							iconCls : 'icon-add',
							handler : addPValuerow
						}],
			onBeforeEdit : function(index, row) {
					row.editing = true;
					table.datagrid('refreshRow', index);
				},
			onAfterEdit : function(index, row) {
				row.editing = false;
				table.datagrid('refreshRow', index);
			},
			onCancelEdit : function(index, row) {
				row.editing = false;
				table.datagrid('refreshRow', index);
			}
		});
		table.datagrid('acceptChanges');
	};
	
	this.getData = function() {
		return table.datagrid("getRows");
	};
	
	this.loadData = function(values) {
		if (null == values) {
			table.datagrid("loadData", []);
		} else {
			table.datagrid("loadData", values);
		}
	};
	
	this.clean = function() {
		table.datagrid('rejectChanges');
	};
	init();
}

/**
 * table actions
 *
 */
function editRowAction(tableId, index) {
	$('#' + tableId).datagrid('selectRow', index);
	$('#' + tableId).datagrid('beginEdit', index);
}
function deleteRowAction(tableId, index) {
	$.messager.confirm('确认', '是否真的删除?', function(r) {
				if (r) {
					$('#' + tableId).datagrid('deleteRow', index);
				}
			});
}
function saveRowAction(tableId, index) {
	$('#' + tableId).datagrid('endEdit', index);
}
function cancelEditRowAction(tableId, index) {
	$('#' + tableId).datagrid('cancelEdit', index);
}

function addPValuerow() {
	$('#Table_Values').datagrid('appendRow', {
				vName : ''
			});
}
function savePValueall() {
	$('#Table_Values').datagrid('acceptChanges');
}


function addPropertyrow() {
	var currentRows = $('#Table_Properties').datagrid('getRows');
	if (currentRows != null && currentRows.length < 5) {
		$('#Table_Properties').datagrid('appendRow', {
					vName : ''
				});
	} else {
		$.messager.alert('最多只能添加5条');
	}
}
function savePropertyall() {
	$('#Table_Properties').datagrid('acceptChanges');
}

/**
 * 属性对象
 * @param {} pIndex
 * @param {} pName
 */
function Property(pIndex, pName) {
	var ordinal = pIndex;
	var propertiyname = pName;
	var values = null; // 属性值 
	
	this.getOrdinal = function() {
		return ordinal;
	};
	
	this.getPropertiyName = function() {
		return propertiyname;
	};
	
	this.setValues = function(pValues) {
		values = pValues;
	};
	
	this.getValues = function() {
		return values;
	};
}
/**
 * 属性值
 * @param {} vIndex
 * @param {} vName
 */
function PropertyValue(vIndex, vName) {
	var ordinal = vIndex;
	var pvalue = vName;
	
	this.getOrdinal = function() {
		return ordinal;
	};
	
	this.getPValue = function() {
		return pvalue;
	};
}

function addProperty(nProperty) {
	$('body').data("property_" + nProperty.getOrdinal(), nProperty);
}

function getPropertyByIndex(index) {
	return $('body').data("property_" + index);
}

function strToJson(str){  
     var json = eval('(' + str + ')');  
     return json;  
}
</script>
<div class="easyui-layout" fit="true" >
	<!--  div region="north"	id="top_area" style="height: 28px; padding-top: 1px; background-color: #EEF;" border="false">
		<span style="position: absolute; top: 7px;" id="Label_Path">全部</span>
	</div>-->
    <div class="easyui-layout" region="west" id="goodsCategory" split="false" border="true" id="left_area" title="商品分类" style="width:250px;">
		<div id="content" region="center" id="right_area" border="false">
			<ul id='goodsCategoryTree' class='easyui-tree' fit="true" ></ul>
		</div>
		<div region="south"	id="bottom_area" style="height: 30px; background-color: #EEF;" border="false">
			<input type="button" value="新增分类" id="Button_New"/>
			<input type="button" value="修改分类" id="Button_Edit"/>
			<input type="button" value="删除分类" id="Button_C_Delete"/>
		</div>
    </div>
    <div id="propertyContent" region="center" id="right_area" title="" style="padding:0px;" fit="true" border="true"> </div>
    <!-- div id="propertyContent" region="center" id="right_area" title="分类属性" style="padding:0px;" fit="true" border="true"> 
    	<label><font size="2">商品分类属性设置</font></label><label style="position: relative; left: 10px;">说明：商品分类属性用于对分类商品进行描述（可以不设置）</label>
    	<hr/>
    	<span style="display:-moz-inline-box; display:inline-block; width:200px;"><label><font>分类名称：</font></label><label id="Label_CategoryName"></label></span>
    	<label><font>分类编号：</font></label><label id="Label_CategoryNo"></label>
    	<div class="easyui-layout" fit="true" split="false" id="test_area" border="false">
    		<div region="west" style="padding:5px;width:400px;" border=false>
				<table  id="Table_Properties" class="easyui-datagrid" style="height: 380px" 
					title="属性列表"  rownumbers="true" pagination="false">
					 <thead>  
				        <tr>  
				            <th field="pName" width="80" align="center" editor="{type:'numberbox',options:{precision:1}}">属性名称</th>  
				            <th field="operation" width="80" align="center" editor="numberspinner">删除</th>  
				        </tr>  
    				</thead>  
				</table>
    		</div>
		    <div region="center" style="padding:5px;width:400px;" border="false">
				<table id="Table_Values" class="easyui-datagrid"  style="width:390px; height: 380px"
			        title="属性值列表"  rownumbers="true" pagination="false">  
				</table>
			</div>
			<div region="south"	id="right_bottom_area" style=" height: 76px; background-color: #EEF;" border="false">
				<input type="button" value="保存属性" id="Button_SaveProperty"/>
			</div>
		</div>
    </div>   -->
</div> 
<!--  div id="createWin" class="easyui-window" collapsible='false' modal='true' resizable='false' minimizable='false' maximizable='false' title="新增分类" style="width:350px;height:230px;" closed="true"> -->  
<div id="createWin"></div>
<!--
<div id="createWin" style='display: none'>
    <form style="padding:10px 20px 10px 40px;">  
        <p>分类编号: <input type="text" id="Text_CategoryNo" /></p>  
        <p>分类名称: <input type="text" id="Text_CategoryName" /></p>  
        <div style="padding:5px;text-align:center;">  
            <a href="#" class="easyui-linkbutton" icon="icon-ok" id="Button_Save">保存</a>  
            <a href="#" class="easyui-linkbutton" icon="icon-cancel" id="Button_Cancel">取消</a>  
        </div> 
        <input type='reset' id='category_new_reset' style='display:none;' /> 
    </form>  
</div>
-->
<div id="editWin" class="easyui-window" collapsible='false' modal='true' resizable='false' minimizable='false' maximizable='false' title="修改分类" style="width:300px;height:230px;" closed="true">  
    <form style="padding:10px 20px 10px 40px;">  
   		 <p>分类编号: <label id='parent_categoryNo'></label><input type="text" id="Text_CategoryNo" style='width:50px;'/></p>  
        <p>分类名称: <input type="text" id="Text_CategoryName_E" /></p>  
        <div style="padding:5px;text-align:center;">  
            <a href="#" class="easyui-linkbutton" icon="icon-ok" id="Button_Save_E">保存</a>  
            <a href="#" class="easyui-linkbutton" icon="icon-cancel" id="Button_Cancel_E">取消</a>  
        </div>  
    </form>  
</div>

</body>
</html>