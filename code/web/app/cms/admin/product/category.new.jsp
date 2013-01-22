<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品发布</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<div class="easyui-layout" fit="true" >
	<!--  div region="north"	id="top_area" style="height: 28px; padding-top: 1px; background-color: #EEF;" border="false">
		<span style="position: absolute; top: 7px;" id="Label_Path">全部</span>
	</div>-->
    <div class="easyui-layout" region="west" id="goodsCategory" split="false" border="true" id="left_area" title="商品分类" style="width:250px;">
		<div id="content" region="center" id="right_area" border="false">
			<ul id='category_categoryTree' class='easyui-tree' fit="true" ></ul>
		</div>
		<div region="south"	id="category_formButton" style="height: 30px; background-color: #EEF;" border="false">
			<input type="button" value="新增分类" id="Button_New"/>
			<input type="button" value="修改分类" id="Button_Edit"/>
			<input type="button" value="删除分类" id="Button_C_Delete"/>
		</div>
    </div>
    <div id="propertyContent" region="center" id="right_area" style="padding:0px;" fit="true" border="true"> 
    	<div region="north" border="false" id='category_buttonArea' style="height: 28px; padding-top: 1px; background-color: #EEF;"
			border="false">
		</div>
		<div region='center' id='category_formArea' border='false'>
		</div>
    </div>  
</div> 
<script type="text/javascript">
var _categoryApp = null;
$(document).ready(function() {
	_categoryApp = new CategoryApp();
});

function CategoryApp() {
	var tree = null;
	var form = null;
	var $createButton = $("<input type='button' />");
	var $modifyButton = $("<input type='button' />");
	var $deleteButton = $("<input type='button' />");
	
	var selectedNode = null;
	
	var treeSelectionListener = function(node) {
		selectedNode = node;
		// create
		if (!node.id || node.id == "root") {
			$createButton.show();
		} else if (node.attributes.levelNo == 3) {
			$createButton.hide();
		}
		// delete
		if (tree.isLeaf()) {
			$deleteButton.show();
		} else {
			$deleteButton.hide();
		}
	};
	
	var initButtons = function() {
		$createButton.linkbutton({
			
		});
		$createButton.click(function() {
			if (!form.validateInput()) return;
			form.load(null, 0);
			
			var datas = form.getEditValue();
			$.ajax({
				url: mainWeb + "/admin/goods/createGoodsCategory",
				data: {"categoryNo":datas.code, "categoryName":datas.name, "parentId":selectedNode.id},
				dataType: 'json',
				success: function(data) {
					form.load(null, 0);
				}
			});
		});
		
		$modifyButton.linkbutton({
			
		});
		$modifyButton.click(function() {
			if (!form.validateInput()) return;
			form.load(selectedNode, 0);
			
			var datas = form.getEditValue();
			$.ajax({
				url: mainWeb + "/admin/goods/modifyCategory",
				data: {"categoryId":selectedNode.id, "categoryName":datas.name},
				dataType: 'json',
				success: function(data) {
					
				}
			});
		});
		
		$deleteButton.linkbutton({
			
		});
		$deleteButton.click(function() {
			$.messager.confirm('确认', '确认要删除该分类吗?', function(r) {
				if (r) {
					$.ajax({
						url: mainWeb + "/admin/goods/deleteCategory",
						data: {"categoryId":selectedNode.id},
						dataType: 'json',
						success: function(data) {
							if (data.result) {
								//_goodsCategoryTree.reload();
							} else {
								alert(data.message);
							}
						},
						error: function() {
							alert("删除时，发生错误。");
						}
					});
				}
			});
		});
	};
	
	var init = function() {
		tree = new GoodsCategoryTree();
		tree.addSelectionListener(function(node) {
			treeSelectionListener(node);
		});
		
		form = new GoodsCategoryEditForm();
		initButtons();
		
	};
	
	init();
}

function GoodsCategoryTree() {
	
	var $tree = null;
	
	this.addSelectionListener = function(callBackFun) {
		$tree.unbind('selection');
		$tree.bind('selection', function(node) {
			callBackFun(node);
		});
	};
	
	this.isLeaf = function(node) {
		return $tree.tree('isLeaf', node.target);
	};
	
	this.updateNode = function(id, name) {
		
	};
	
	var init = function() {
		$tree = $('#category_categoryTree');
		$tree.tree({
			url : mainWeb + '/admin/goods/getLightGoodsCategoryTree',
			border : false,
			onSelect : function(node) {
				$.trigger('selection', node);
			}
		});
	};
	init();
}

/**
 * operationType: 0新增, 1修改
 */
function GoodsCategoryEditForm(node, operationType) {
	var $formArea = $('#category_formArea');
		
	var $codeEditor = null;
	var $nameEditor = null;
	
	var $saveButton = null;
	
	this.confrimActionListener = function(fn) {
		$saveButton.unbind('confirm');
		$saveButton.bind('confirm', function() {
			
		});
	};
	this.validateInput = function() {
		if (0 == operationType) {
			if (!$codeEditor.validatebox('isValid')) {
				return false;
			}
			if (!$nameEditor.validatebox('isValid')) {
				return false;
			}
		} else if (1 == operationType) {
			if (!$nameEditor.validatebox('isValid')) {
				return false;
			}
		}
		return true;
	};
	
	this.getEditValue = function() {
		return {code: $codeEditor.val(), name: $nameEditor.val()};
	};
	
	this.clear = function() {
		$codeEditor = null;
		$nameEditor = null;
		$formArea.html();
	};
	
	this.render = function(node) {
		renderInit();
		initEditors();
		initContent();
	};
	
	var initEditors = function() {
		$codeEditor.numberbox({  
		    min:0,
		    precision:0,
		    required: true
		});
		
		$nameEditor.validatebox({  
		    required: true
		});
	};	
	var initContent = function() {
		if (null == node) return;
		//$codeEditor.val(node);
		$nameEditor.val(node.text);
	};
	var renderInit = function() {
		var html = "<table>";
		html += "<tr>";
		//html += "<td class='labelItem'>分类编号:</td>";
		//html += "<td class='inputItem'><input type='text' name='number' id='category_number'/></td>";
		html += "<td class='labelItem'>分类名称:</td>";
		html += "<td class='inputItem'><input type='text' name='title' id='category_name'/></td>";
		html += "</tr>";
		html += "<table>";

		$formArea.html(html);
		
		$codeEditor = $('#category_number');
		$nameEditor = $('#category_name');
	};
}
</script>
</body>
</html>