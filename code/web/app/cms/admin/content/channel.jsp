<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>���ݹ���</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<div class="easyui-layout" fit="true" border='false'>
	<div region="west" split="true" border="false" title="��Ŀ��"
			style="width: 200px;">
		<ul id="channel_channelTree"></ul>
	</div>
	<div region='center' border='false'>
		<div region="north" border="false" id='channel_buttonArea' style="height: 28px; padding-top: 1px; background-color: #EEF;"
			border="false">
		</div>
		<div region='center' id='channel_formArea' border='false'>
		</div>
	</div>
</div>
<script type='text/javascript'>

var _channelApp = null;

// temp
//var mainWeb = '/cms';
$(document).ready(function() {
	_channelApp = new ChannelApp();
});

function ChannelApp() {
	var $channelTree = null;
	var $channelFormArea = null;
	var $channelButtonArea = null;
	
	/**
	 * formִ�в����ɹ���
	 * 0: add
	 * 1: modify
	 * 2: delete
	 */
	this.formProcessSuccessListener = function(actionType, newText) {
		// $channelTree.tree('reload');
		var selectedNode = $channelTree.tree('getSelected');
		if (0 == actionType) {
			$channelTree.tree('reload', selectedNode.target);
		} else if (1 == actionType) {
			if (newText == null || "" == newText) {
				newText = selectedNode.text;
			}
			$channelTree.tree('update', {target: selectedNode.target, text: newText});
		} else if (2 == actionType) {
			$channelTree.tree('remove', selectedNode.target);
		} else {
			$channelTree.tree('reload');
		}
		
		// ���
		$channelFormArea.empty();
		$channelButtonArea.empty();
	};
	
	/**
	 * ����ѡ����ҳ
	 */
	var handleMainPageSelection = function(node) {
		new FloorForm(node, $channelButtonArea, $channelFormArea, true);
	};
	/**
	 * ����ѡ�ж���ҳ��
	 */
	var handleSecondPageSelection = function() {
		
	};
	/**
	 * ����ѡ�е�¥
	 */
	var handleDownStaireSelection = function() {
	
	};
	/**
	 * ����ѡ��¥��
	 */
	var handleFloorSelection = function() {
	
	};
	
	var treeSelectionListener = function(node) {
		// ���
		$channelFormArea.empty();
		$channelButtonArea.empty();
		// ��ʾ
		if(node.id == "01"){
			handleMainPageSelection(node);
		} else if (node.id == "02"){
			handleSecondPageSelection(node);
		} else if (node.id == "0101") {// main menu
			new ChannelForm(null, $channelButtonArea, $channelFormArea, true);		
		} else if (node.attributes.floortype == "03"){
			//handleDownStaireSelection(node);
			new DownStairForm(node, $channelButtonArea, $channelFormArea);
		} else if (node.attributes.isfloor){
			// handleFloorSelection(node);
			new FloorForm(node, $channelButtonArea, $channelFormArea, false);
		} else if (!node.attributes.isfloor) {
			// test
			/*
			var $btn = $("<input type='button' value='���Ĭ�Ͻ��'/>");
			$btn.appendTo($channelButtonArea);
			$btn.click(function(){
				$.ajax({
					url: mainWeb + '/admin/channel/addChannel',
					data: 'recid=12345&code=test&name=test',
					success: function() {
						alert('�����ɹ�');
					}
				});
			});
			//*/
			// ��Ŀ 
			var isDeleteAble = false;
			var parentNode = $channelTree.tree('getParent', node.target);
			if (parentNode && parentNode.id == "01") { // ��ҳ�µ�Ĭ����Ŀ����ɾ��
				isDeleteAble = false;
			} else if (node != null || node.attributes.mainMenu) {
				isDeleteAble = true;
			}
			new ChannelForm(node, $channelButtonArea, $channelFormArea, false, isDeleteAble);
		}
	};
	
	var init = function() {
		$channelTree = $('#channel_channelTree').tree({
			lines: true,
			onSelect: treeSelectionListener,
			url: mainWeb + '/admin/channel/getChannels'
		});
		
		$channelFormArea = $('#channel_formArea');
		$channelButtonArea = $('#channel_buttonArea');
	};
	
	init();
}

function FloorForm(node, $buttonArea, $formArea, isCreateOnly) {
	var $formSaveButton = $("<a	href='#'>����</a>");
	var $addAdButton = null;
	/*
	var $newFloorForm = $("<div></div>");
	var $nubmerEditor = $("<input name='ordinal' type='text' />");
	var $titleEditor = $("<input name='title' type='text' />");
	var $typeEditor = $("<input name='floortype' />");
	var $styleEditor = $("<input name='theme' />");
	var $subEditor = $("<input name='goodsCategoryId' />");
	var $sub1Editor = $("<input name='thirdClassCategoryId' />");
	// ������selectedThirdClassCategorys����
	var $sub1HideEditor = $("<input type='text'/>");
	*/
	var $nubmerEditor = null;
	var $titleEditor = null;
	var $typeEditor = null;
	var $styleEditor = null;
	var $subEditor = null;
	var $sub1Editor = null;
	// ������selectedThirdClassCategorys����
	var $sub1HideEditor = null;
	
	var floorInfo = null;
	var adCount = 0;
	
	// ������ʱ����ѡ�������������Ϣ
	var selectedThirdClassCategorys = new Array();
	
	var isRendered = false;
	
	var showEditors = function(isEditable) {
		$nubmerEditor.attr('disabled', !isEditable);
		$titleEditor.attr('disabled', !isEditable);
		if (isEditable) {
			$typeEditor.combobox('enable');
			$styleEditor.combobox('enable');
			$subEditor.combobox('enable');
			$formSaveButton.show();
			$addAdButton.show();
		} else {
			$typeEditor.combobox('disable');
			$styleEditor.combobox('disable');
			$subEditor.combobox('disable');
			$formSaveButton.hide();
			$addAdButton.hide();
		}
	};
	var doSave = function() {
		if (validateInput()) {
			var saveUrl = "";
			if (isCreateOnly) {
				saveUrl = mainWeb + '/admin/channel/addFloor';
			} else {
				saveUrl = mainWeb + '/admin/channel/updateFloor';
			}
			var recid = null;
			var imagUrl = null;
			var url = null;
			if (null != floorInfo) {
				recid = floorInfo.recid;
				imagUrl = floorInfo.imageUrl;
				url = floorInfo.url;
			}
			var jsonStr = "{";
			jsonStr += "recid: '" + recid + "'";
			jsonStr += ", imageUrl: '" + imagUrl + "'";
			jsonStr += ", url: '" + url + "'";
			jsonStr += ", ordinal: '" + $nubmerEditor.val() + "'";
			jsonStr += ", title: '" + $titleEditor.val() + "'";
			jsonStr += ", floortype: '" + $typeEditor.combobox('getValue') + "'";
			jsonStr += ", theme: '" + $styleEditor.combobox('getValue') + "'";
			jsonStr += ", goodsCategoryId: '" + $subEditor.combobox('getValue') + "'";
			jsonStr += ", channelVos: [";
			var idStr = "";
			for (var index = 0; index < selectedThirdClassCategorys.length; index++) {
				if (selectedThirdClassCategorys[index] == null) {
					continue;
				}
				if (0 != index) {
					idStr += ",";
				}
				idStr += "{recid: '" + selectedThirdClassCategorys[index].getRecid() + "', ";
				idStr += "code: '" + selectedThirdClassCategorys[index].getCode() + "', ";
				idStr += "name: '" + selectedThirdClassCategorys[index].getName() + "'}";
			}
			jsonStr += idStr;
			jsonStr += "]";
			// ���
			var ads = getAdValue();
			if (ads == null) {
				jsonStr += ", floorAdvertisingVo: ''";
			} else {
				jsonStr += ", floorAdvertisingVo: " + ads ;
			}
			jsonStr += "}";
			$.ajax({
				type: 'post',
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				url: saveUrl,
				data: {'dataString': jsonStr},
				dataType: 'json',
				success: function(data) {
					if (data.result) {
						if (isCreateOnly) {
							_channelApp.formProcessSuccessListener(0);
						} else {
							_channelApp.formProcessSuccessListener(1, $titleEditor.val());
						}
					} else {
						//alert(data.message);
						$.messager.alert('��ʾ',data.message);
					}
				},
				error: function() {
					//alert("�������");
					$.messager.alert('����',"�������"); 
				}
			});
		}
	};
	
	var validateInput = function() {
		if (!$nubmerEditor.validatebox('isValid')) {
			return false;
		}
		if (!$titleEditor.validatebox('isValid')) {
			return false;
		}
		if (!$typeEditor.combobox('isValid')) {
			return false;
		}
		
		if ($typeEditor.combobox('getValue') == '02') {
			// ���¥����֤
			return true;
		}
		if (!$styleEditor.combobox('isValid')) {
			return false;
		}
		if (!$subEditor.combobox('isValid')) {
			return false;
		}
		if ($sub1HideEditor.val() == null || $sub1HideEditor.val() == "") {
			alert("�������಻��Ϊ�ա�");
			return false;		
		}
		if (adCount > 0 && !validateAdInput()) {
			return false;
		}
		return true;
	};
	
	var typeSelectionListener = function(record) {
		if (record.code == "02") {
			/*
			$styleEditor.parent().hide();
			$subEditor.parent().hide();
			$sub1Editor.parent().hide();
			*/
			$('#goodsRowOnly').hide();
		} else {
			$styleEditor.combobox('setValue', '');
			$subEditor.combobox('setValue', '');
			$sub1Editor.searchbox('setValue', '');
			$sub1HideEditor.val('');
			
			$('#goodsRowOnly').show();
			/*
			$styleEditor.parent().show();
			$subEditor.parent().show();
			$sub1Editor.parent().show();
			*/
		}
	};
	
	var subSelectionListener = function(record) {
		$sub1HideEditor.val('');
		$sub1Editor.searchbox('setValue', '');
		selectedThirdClassCategorys = new Array();
	};
	
	var sub1SelectionListener = function(value, name) {
		var categoryId = $subEditor.combobox('getValue');
		if (null == categoryId || "" == categoryId) {
			alert('����ѡ��һ�����ࡣ');
			return;
		}
		var preIdsStr = $sub1HideEditor.val();
		var preIds = new Array();
		if (preIdsStr.split(";").length > 0) {
			preIds = preIdsStr.split(";");
		}
		var win = new WinowForChooseCategory(categoryId, preIds);
		win.addConfirmActionListener(function(goodsCategoryVos) {
			if (goodsCategoryVos.length == 1) {
				selectedThirdClassCategorys = new Array();
				selectedThirdClassCategorys[0] = new CategoryInfo(goodsCategoryVos[0].recid, 
								goodsCategoryVos[0].categoryno, goodsCategoryVos[0].categoryname);
				$sub1HideEditor.val(goodsCategoryVos[0].recid);
				$sub1Editor.searchbox('setValue', goodsCategoryVos[0].categoryname);
			} else if (goodsCategoryVos.length > 1) {
				selectedThirdClassCategorys = new Array();
				var idStr = "";
				var nameStr = "";
				for (var index = 0; index < goodsCategoryVos.length; index++) {
					if (index != 0) {
						nameStr += ";";
					}
					idStr += goodsCategoryVos[index].recid + ";";
					nameStr += goodsCategoryVos[index].categoryname;
					selectedThirdClassCategorys[index] 
							= new CategoryInfo(goodsCategoryVos[index].recid, 
								goodsCategoryVos[index].categoryno, goodsCategoryVos[index].categoryname);
				}		
				$sub1HideEditor.val(idStr);
				$sub1Editor.searchbox('setValue', nameStr);
			}
		});
	};
	
	var initEditors = function() {
		$nubmerEditor.numberbox({  
		    min:0,
		    precision:0,
		    required: true
		});
		
		$titleEditor.validatebox({  
		    required: true
		});
		
		$typeEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
			url: mainWeb + '/admin/channel/getFloorTypeList',
			onSelect: function(record) {
				typeSelectionListener(record);
			},
		    required: true,
		    editable:false
		});
		
		$styleEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
			url: mainWeb + '/admin/channel/getThemeList',
		    required: true,
		    editable:false
		});
		
		$subEditor.combobox({
			valueField: 'recid',  
	        textField: 'categoryname',
			url: mainWeb + '/admin/channel/getSecondLeverOfGoodsCategory',
			onSelect: function(record) {
				subSelectionListener(record);
			},
		    required: true,
		    editable:false
		});
		
		$sub1Editor.searchbox({
			valueField: 'recid',  
	        textField: 'categoryname',
			searcher: function(value,name) {
				sub1SelectionListener(value,name);
			},
		    required: true
		});
		
		$sub1Editor.searchbox('textbox').attr('disabled', true);
		
		$sub1HideEditor.validatebox({  
		    required: true
		});
		
		$formSaveButton.linkbutton({  
		    iconCls: 'icon-save'
		});
		$formSaveButton.click(function() {
			doSave();
		});
		
		$addAdButton.linkbutton({  
		    iconCls: 'icon-add'
		});
		$addAdButton.click(function() {
			addOneAd(null);
		});
	};
	
	var addOneAd = function(data) {
		if (adCount < 3) {
			var adIndex =  adCount + 1; //��1��ʼ
			$('.formTable').append(buildAdHtml(adIndex));
			addRemoveAdAction(adIndex);
			addPicSelectAction(adIndex);
			addAdValidate(adIndex);
			adCount++;
			
			if (null != data) {
				setAdValue(adIndex, data);
			}
		}
	};
	
	var showConetent = function() {
		if (node.id == "01") return;
		$.ajax({
			type: 'post',
			url: mainWeb + '/admin/channel/getFloorInfo',
			data: {'id': node.id},
			dataType: 'json',
			success: function(data) {
				floorInfo = data;
				$nubmerEditor.val(data.ordinal);
				$titleEditor.val(data.title);
				$typeEditor.combobox("select",data.floortype);
				$styleEditor.combobox("select",data.theme);
				$subEditor.combobox("select",data.goodsCategoryId);
				
				var chanels = data.channelVos;
				selectedThirdClassCategorys = new Array();
				var nameStr = "";
				var idStr = "";
				for (var index = 0; index < chanels.length; index++) {
					selectedThirdClassCategorys[index] = new CategoryInfo(chanels[index].recid, chanels[index].code, chanels[index].name);
					if (index != 0) {
						nameStr += ";";
					}
					idStr += chanels[index].recid + ";";
					nameStr += chanels[index].name;
				}
				$sub1HideEditor.val(idStr);
				$sub1Editor.searchbox('setValue', nameStr);
				
				if (data.advertisings != null) {
					for (var index = 0; index < data.advertisings.length; index++) {
						addOneAd(data.advertisings[index]);
					}
					enableAdEditos();
				}
			}
		});
	};
	
	var renderInit1 = function() {
		if (isRendered) {
			return;
		}
		var html = "<table>";
		html += "<tr>";
		html += "<td>";
		html += "<table class='formTable'>";
		html += "<tr>";
		html += "<td class='labelItem'>¥�����:</td>";
		html += "<td class='inputItem'><input type='text' name='number' id='number'/></td>";
		html += "<td class='labelItem'>&nbsp;&nbsp;&nbsp;&nbsp;¥�����:</td>";
		html += "<td class='inputItem'><input type='text' name='title' id='title'/></td>";
		html += "</tr>";
		
		html += "<tr>";
		html += "<td class='labelItem'>¥������:</td>";
		html += "<td class='inputItem'><input type='text' name='type' id='type'/></td>";
		html += "<td class='labelItem'>&nbsp;&nbsp;&nbsp;&nbsp;¥����:</td>";
		html += "<td class='inputItem'><input type='text' name='style' id='style'/></td>";
		html += "</tr>";
		
		html += "<tr id='goodsRowOnly'>";
		html += "<td class='labelItem'>һ������:</td>";
		html += "<td class='inputItem'><input type='text' name='sub' id='sub'/></td>";
		html += "<td class='labelItem'>&nbsp;&nbsp;&nbsp;&nbsp;��������:</td>";
		html += "<td class='inputItem'><input type='text' name='sub1' id='sub1'/><input type='hidden' name='sub1hidden' id='sub1hidden'/></td>";
		html += "</tr>";
		html += "</table>";
		
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'><a href='#' id='formsaveBtn'>����</a><a href='#' id='addAdButton'>�������λ</a></td>";
		html += "</tr>";
		html += "</table>";
		$formArea.html(html);
		
		$nubmerEditor = $('#number');
		$titleEditor = $('#title');
		$typeEditor = $('#type');
		$styleEditor = $('#style');
		$subEditor = $('#sub');
		$sub1Editor = $('#sub1');
		$sub1HideEditor = $('#sub1hidden');
		$formSaveButton = $('#formsaveBtn');
		$addAdButton = $('#addAdButton');
		
		initEditors();
		isRendered = true;
	};
	
	/** ad begin */
	
	var addRemoveAdAction = function(adIndex) {
		$('#ad_remove-' + adIndex).click(function() {
			$('tr[id|="ad_row_' + adIndex + '"]').remove();
			adCount--;
		});
	};
	
	var addPicSelectAction = function(adIndex) {
		$('#ad_selimg-' + adIndex).click(function() {
			var picWin = new ChooseImagesWindow();
			picWin.addConfirmActionListener(function(path) {
				$('#ad_img-' + adIndex).val(path);
			});
		});
	};
	
	var enableAdEditos = function(enable) {
		
		$('input[id|="ad_title"]').each(function() {
			$(this).attr('disabled', !enable);
		});
		
		$('input[id|="ad_selimg"]').each(function() {
			if (enable) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		
		$('a[id|="ad_remove"]').each(function() {
			if (enable) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
		$('input[id|="ad_url"]').each(function() {
			$(this).attr('disabled', !enable);
		});
	};
	
	var addAdValidate = function(adIndex) {
		$('#ad_img-' + adIndex).validatebox({  
		    required: true
		});
		
		$('#ad_url-' + adIndex).validatebox({  
		    required: true
		});
	};
	
	var validateAdInput = function() {
		var imgEditors = $('input[id|="ad_img"]');
		var urleEditors = $('input[id|="ad_url"]');
		if (imgEditors == null || null == urleEditors) return true;
		for (var index = 0; index < imgEditors.length; index++) {
			if (!imgEditors.validatebox('isValid')) return false;
		}
		for (var index = 0; index < urleEditors.length; index++) {
			if (!urleEditors.validatebox('isValid')) return false;
		}
		return true;
	};
	
	var setAdValue = function(id, data) {
		if (data == null) return;
		$('#ad_title-' + id).val(data.title);
		
		$('#ad_img-' + id).val(data.imageurl);
		
		$('#ad_url-' + id).val(data.url);
	};
	
	var getAdValue = function() {
		if (adCount < 1) return null;
		
		var titleEditors = $('input[id|="ad_title"]');
		var imgEditors = $('input[id|="ad_img"]');
		var urleEditors = $('input[id|="ad_url"]');
		
		var values = "[";
		// ע�������adIndex��id�е���Ų�һ����Ӧ
		for (var adIndex = 0; adIndex < adCount; adIndex++) {
			if (adIndex != 0) {
				values += ",";
			}
			values += "{";
			values += "'title':'" + titleEditors[adIndex].value + "'";
			values += ", 'imageurl':'" + imgEditors[adIndex].value + "'";
			values += ", 'url':'" + urleEditors[adIndex].value + "'";
			values += "}";
		}
		values += "]";
		return values;
	};
	var buildAdHtml = function(id, data) {
		var html = "";
		html += "<tr id='ad_row_" + id + "-1'>";
		html += "<td class='labelItem'>������:</td>";
		html += "<td class='inputItem'><input type='text' name='ad_title_" + id + "' id='ad_title-" + id + "'/></td>";
		html += "<td class='labelItem'>&nbsp;&nbsp;&nbsp;&nbsp;���ͼƬ:</td>";
		html += "<td class='inputItem'>";
		html += "<input type='text' name='ad_img_" + id + "' id='ad_img-" + id + "' disabled/>";
		html += "<input type='button' name='ad_selimg_" + id + "' id='ad_selimg-" + id + "' value='ѡ��ͼƬ'/>";
		html += "</td>";
		html += "</tr>";
		
		html += "<tr id='ad_row_" + id + "-2'>";
		html += "<td class='labelItem'>�������:</td>";
		html += "<td class='inputItem' colspan='3'>";
		html += "<input type='text' name='ad_url_" + id + "' id='ad_url-" + id + "' style='width:350px'/>";
		html += "<a href='javascript:void(0)' id='ad_remove-" + id + "'>ɾ�����</a>";
		html += "</td>";
		html += "</tr>";
		
		return html;
	};
	
	/** ad end */
	
	/*
	var renderInit = function() {
		if (isRendered) {
			return;
		}
		$newFloorForm.appendTo($formArea);
		
		var $numberLine = $("<p></p>");
		var $titleLine = $("<p></p>");
		var $typeLine = $("<p></p>");
		var $styleLine = $("<p></p>");
		var $subLine = $("<p></p>");
		var $sub1Line = $("<p></p>");
		
		$numberLine.appendTo($newFloorForm);
		$titleLine.appendTo($newFloorForm);
		$typeLine.appendTo($newFloorForm);
		$styleLine.appendTo($newFloorForm);
		$subLine.appendTo($newFloorForm);
		$sub1Line.appendTo($newFloorForm);
		
		var $nubmerLabel = $("<label>¥����ţ�</label>");
		var $titleLabel = $("<label>¥����⣺</label>");
		var $typeLabel = $("<label>¥�����ͣ�</label>");
		var $styleLabel = $("<label>¥����</label>");
		var $subLabel = $("<label>�������ࣺ</label>");
		var $sub1Label = $("<label>�������ࣺ</label>");
		
		$nubmerLabel.appendTo($numberLine);
		$nubmerEditor.appendTo($numberLine);
		
		$titleLabel.appendTo($titleLine);
		$titleEditor.appendTo($titleLine);
		
		$typeLabel.appendTo($typeLine);
		$typeEditor.appendTo($typeLine);
		
		$styleLabel.appendTo($styleLine);
		$styleEditor.appendTo($styleLine);
		
		$subLabel.appendTo($subLine);
		$subEditor.appendTo($subLine);
		
		$sub1Label.appendTo($sub1Line);
		$sub1Editor.appendTo($sub1Line);
		$sub1HideEditor.appendTo($sub1Line);
		
		ad1Editor = new AdvertisingEditorComposite($formArea);
		ad2Editor = new AdvertisingEditorComposite($formArea);
		ad3Editor = new AdvertisingEditorComposite($formArea);
		
		$formSaveButton.appendTo($formArea);
		
		$sub1HideEditor.hide();
		
		initEditors();
		isRendered = true;
	};
	*/
	var newFloorListener = function() {
		renderInit1();
		showEditors(true);
	};
	
	var initFormButton = function() {
		if (isCreateOnly) {
			var $newFloorButton = $("<a	href='#'>����¥��</a>");
			$newFloorButton.appendTo($buttonArea);
			$newFloorButton.linkbutton({  
		   		iconCls: 'icon-add'
			});
			
			$newFloorButton.click(function() {
				newFloorListener();
			});
		} else {
			var $modifFloorButton = $("<a href='#'>�޸�¥��</a>");
			var $deleteFloorButton = $("<a href='#'>ɾ��¥��</a>");
			$modifFloorButton.appendTo($buttonArea);
			$deleteFloorButton.appendTo($buttonArea);
			
			$modifFloorButton.linkbutton({
				iconCls: 'icon-edit'
			});
			$modifFloorButton.click(function() {
				showEditors(true);		
				enableAdEditos(true);
			});
			
			$deleteFloorButton.linkbutton({
				iconCls: 'icon-remove'
			});
			$deleteFloorButton.click(function() {
				$.messager.confirm('ȷ��', 'ȷ��Ҫɾ����¥���룿', function(r) {
					//  ɾ��	
					$.ajax({
						url: mainWeb + '/admin/channel/deleteFloor',
						data: {'id': node.id},
						dataType: 'json',
						success: function(data) {
							if (data.result) {
								_channelApp.formProcessSuccessListener(2);
							} else {
								//alert(data.message);
								$.messager.alert('��ʾ',data.message); 
							}
						},
						error: function() {
							alert("ɾ��ʱ����");
						}
					});
				});
			});
		}
		
	};
	
	var init = function() {
		if (!isCreateOnly) {
			renderInit1();
			showConetent();
			showEditors(false);
		}
		
		initFormButton();
		
	};
	
	init();
	
}

function ChannelForm(node, $buttonArea, $formArea, isCreateOnly, isDeleteAble) {
	var title = "��Ŀ";
	if (node == null || node.attributes.mainMenu) { // ֻ��"���˵�"��� node��Ϊ�գ��ҿ�������
		title = "�˵�";
	}
	var $addChannelButton = $("<a	href='#'>����" + title + "</a>");
	var $editChannelButton = $("<a	href='#'>�༭" + title + "</a>");
	var $deleteChannelButton = $("<a href='#'>ɾ��" + title + "</a>");
	var $formSaveButton = $("<a	href='#'>����</a>");
	var $channelForm = $("<div></div>");
	
	var $codeEditor = $("<input type='text' />");
	var $nameEditor = $("<input type='text' />");
	var $typeEditor = $("<input />");
	
	var doSave = function() {
		if (validateInput()) {
			var saveUrl = null;
			if (isCreateOnly) {
				saveUrl = mainWeb + '/admin/channel/addChannel';
			} else {
				saveUrl = mainWeb + '/admin/channel/updateChannel';
			}
			var jsonStr = "{";
			if (null != node) {
				jsonStr += "recid: '" + node.id + "'";
			} else {
				jsonStr += "recid: 'null'";
			}
			jsonStr += ", code: '" + $codeEditor.val() + "'";
			jsonStr += ", name: '" + $nameEditor.val() + "'";
			jsonStr += ", channeltype: '" + $typeEditor.combobox('getValue') + "'";
			jsonStr += "}";
			$.ajax({
				url: saveUrl,
				data: {'dataString': jsonStr},
				dataType: 'json',
				success: function(data) {
					if (data.result) {
						if (isCreateOnly) {
							_channelApp.formProcessSuccessListener(0);
						} else {
							_channelApp.formProcessSuccessListener(1, $nameEditor.val());
						}
					} else {
						//alert(data.message);
						$.messager.alert('��ʾ',data.message); 
					}
				},
				error: function() {
					alert("�������");
				}
			});
		}
	};
	
	var validateInput = function() {
		if (!$codeEditor.validatebox('isValid')) {
			return false;
		}
		if (!$nameEditor.validatebox('isValid')) {
			return false;
		}
		if (!$typeEditor.combobox('isValid')) {
			return false;
		}
		return true;
	};
	
	var showEditors = function(isEditable) {
		$codeEditor.attr('disabled', !isEditable);
		$nameEditor.attr('disabled', !isEditable);
		if (isEditable) {
			$formSaveButton.show();
			$typeEditor.combobox('enable');
		} else {
			$formSaveButton.hide();
			$typeEditor.combobox('disable');
		}
	};
	
	var initContnet = function() {
		$codeEditor.val(node.attributes.code);
		$nameEditor.val(node.text);	
		$typeEditor.combobox("select",node.attributes.channeltype);
	};
	var initEditors = function() {
		$codeEditor.validatebox({  
		    required: true
		});	
		
		$nameEditor.validatebox({  
		    required: true
		});	
		
		$typeEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
			url: mainWeb + '/admin/channel/getChannelTypeList',
		    required: true,
		    editable:false
		});
		
		$formSaveButton.linkbutton({  
		    iconCls: 'icon-save'
		});
		$formSaveButton.click(function() {
			doSave();
		});
		
	};
	
	var renderInit = function() {
		$channelForm.appendTo($formArea);
		
		var $codeLine = $("<p></p>");
		var $nameLine = $("<p></p>");
		var $typeLine = $("<p></p>");
		
		$codeLine.appendTo($channelForm);
		$nameLine.appendTo($channelForm);
		$typeLine.appendTo($channelForm);
		
		var $codeLabel = $("<label>" + title + "���룺</label>");
		var $nameLabel = $("<label>" + title + "���ƣ�</label>");
		var $typeLabel = $("<label>" + title + "���ͣ�</label>");
		
		$codeLabel.appendTo($codeLine);
		$codeEditor.appendTo($codeLine);
		
		$nameLabel.appendTo($nameLine);
		$nameEditor.appendTo($nameLine);
		
		$typeLabel.appendTo($typeLine);
		$typeEditor.appendTo($typeLine);
		
		$formSaveButton.appendTo($channelForm);
		
		initEditors();
	};
	
	var init = function() {
		if (isCreateOnly) {
			$addChannelButton.appendTo($buttonArea);
			$addChannelButton.linkbutton({  
			    iconCls: 'icon-add'
			});
			
			$addChannelButton.click(function() {
				renderInit();
				showEditors(true);
			});
		} else {
			renderInit();
			initContnet();
			showEditors(false);
			$editChannelButton.appendTo($buttonArea);
			
			$editChannelButton.linkbutton({  
				    iconCls: 'icon-add'
				});
				
			$editChannelButton.click(function() {
				showEditors(true);
			});
			if (isDeleteAble) {
				$deleteChannelButton.appendTo($buttonArea);
				$deleteChannelButton.linkbutton({  
				    iconCls: 'icon-remove'
				});
				$deleteChannelButton.click(function() {
					$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������Ŀ�룿', function(r) {
						//  ɾ��	
						$.ajax({
							url: mainWeb + '/admin/channel/deleteChannel',
							data: {'id': node.id},
							dataType: 'json',
							success: function(data) {
								if (data.result) {
									_channelApp.formProcessSuccessListener(2);
								} else {
									//alert(data.message);
									$.messager.alert('��ʾ',data.message); 
								}
							},
							error: function() {
								alert("ɾ��ʱ����");
							}
						});
					});
				});
			}
		}
	};
	init();
}

function DownStairForm(node, $buttonArea, $formArea) {
	var $editButton = $("<a	href='#'>�༭��¥</a>");
	var $formSaveButton = $("<a	href='#'>����</a>");
	var $editForm = $("<div></div>");
	
	var $styleEditor = $("<input name='theme' />");
	var $subEditor = $("<input name='goodsCategoryId' />");
	var $sub1Editor = $("<input name='thirdClassCategoryId' />");
	var $sub1HideEditor = $("<input type='text'/>");
	var $adUrlEditor = $("<input name='ordinal' type='text' />");
	var $urlEditor = $("<input name='title' type='text' />");
	
	// ¥����Ϣ
	var floorInfo = null;
	
	// ������ʱ����ѡ�������������Ϣ
	var selectedThirdClassCategorys = new Array();
	
	var doSave = function() {
		if (validateInput()) {
			var jsonStr = "{";
			jsonStr += "recid: '" + floorInfo.recid + "'";
			jsonStr += ", ordinal: '" + floorInfo.ordinal + "'";
			jsonStr += ", title: '" + floorInfo.title + "'";
			jsonStr += ", floortype: '" + floorInfo.floortype + "'";
			jsonStr += ", theme: '" + $styleEditor.combobox('getValue') + "'";
			jsonStr += ", goodsCategoryId: '" + $subEditor.combobox('getValue') + "'";
			jsonStr += ", imageUrl: '" + $adUrlEditor.val()+ "'";
			jsonStr += ", url: '" + $urlEditor.val() + "'";
			jsonStr += ", channelVos: [";
			var idStr = "";
			for (var index = 0; index < selectedThirdClassCategorys.length; index++) {
				if (selectedThirdClassCategorys[index] == null) {
					continue;
				}
				if (0 != index) {
					idStr += ",";
				}
				idStr += "{recid: '" + selectedThirdClassCategorys[index].getRecid() + "', ";
				idStr += "code: '" + selectedThirdClassCategorys[index].getCode() + "', ";
				idStr += "name: '" + selectedThirdClassCategorys[index].getName() + "'}";
			}
			jsonStr += idStr;
			jsonStr += "]";
			jsonStr += "}";
			$.ajax({
				url: mainWeb + '/admin/channel/updateFloor',
				data: {'dataString': jsonStr},
				dataType: 'json',
				success: function(data) {
					if (data.result) {
						_channelApp.formProcessSuccessListener(1);
					} else {
						alert(data.message);
					}
				},
				error: function() {
					alert("�������");
				}
			});
		}
	};
	
	var validateInput = function() {
		if (!$styleEditor.combobox('isValid')) {
			return false;
		}
		if (!$subEditor.combobox('isValid')) {
			return false;
		}
		if ($sub1HideEditor.val() == null || $sub1HideEditor.val() == "") {
			return false;		
		}
		if (!$adUrlEditor.validatebox('isValid')) {
			return false;
		}
		if (!$urlEditor.validatebox('isValid')) {
			return false;
		}
		return true;
	};
	
	var showEditors = function(isEditable) {
		$adUrlEditor.attr('disabled', !isEditable);
		$urlEditor.attr('disabled', !isEditable);
		
		if (isEditable) {
			$formSaveButton.show();
			$styleEditor.combobox('enable');
			$subEditor.combobox('enable');
			//$sub1Editor.menubutton('enable');
		} else {
			$formSaveButton.hide();
			$styleEditor.combobox('disable');
			$subEditor.combobox('disable');
			//$sub1Editor.menubutton('disable');
		}
	};
	
	var initConetent = function() {
		$.ajax({
			url: mainWeb + '/admin/channel/getFloorInfo',
			data: {'id': node.id},
			dataType: 'json',
			success: function(data) {
				floorInfo = data;
				$styleEditor.combobox("select",data.theme);
				$subEditor.combobox("select",data.goodsCategoryId);
				
				var chanels = data.channelVos;
				selectedThirdClassCategorys = new Array();
				var nameStr = "";
				var idStr = "";
				for (var index = 0; index < chanels.length; index++) {
					selectedThirdClassCategorys[index] = new CategoryInfo(chanels[index].recid, chanels[index].code, chanels[index].name);
					if (index != 0) {
						nameStr += ";";
					}
					idStr += chanels[index].recid + ";";
					nameStr += chanels[index].name;
				}
				$sub1HideEditor.val(idStr);
				$sub1Editor.searchbox('setValue', nameStr);
				
				$adUrlEditor.val(data.imageUrl);
				$urlEditor.val(data.url);
	
			}
		});
	};
	
	var subSelectionListener = function(record) {
		$sub1HideEditor.val('');
		$sub1Editor.searchbox('setValue', '');
		selectedThirdClassCategorys = new Array();
	};
	
	var sub1SelectionListener = function(value, name) {
		var categoryId = $subEditor.combobox('getValue');
		if (null == categoryId || "" == categoryId) {
			alert('����ѡ��������ࡣ');
			return;
		}
		var preIdsStr = $sub1HideEditor.val();
		var preIds = new Array();
		if (preIdsStr.split(";").length > 0) {
			preIds = preIdsStr.split(";");
		}
		var win = new WinowForChooseCategory(categoryId, preIds);
		win.addConfirmActionListener(function(goodsCategoryVos) {
			if (goodsCategoryVos.length == 1) {
				selectedThirdClassCategorys = new Array();
				selectedThirdClassCategorys[0] = new CategoryInfo(goodsCategoryVos[0].recid, 
								goodsCategoryVos[0].categoryno, goodsCategoryVos[0].categoryname);
				$sub1HideEditor.val(goodsCategoryVos[0].recid);
				$sub1Editor.searchbox('setValue', goodsCategoryVos[0].categoryname);
			} else if (goodsCategoryVos.length > 1) {
				selectedThirdClassCategorys = new Array();
				var idStr = "";
				var nameStr = "";
				for (var index = 0; index < goodsCategoryVos.length; index++) {
					if (index != 0) {
						nameStr += ";";
					}
					idStr += goodsCategoryVos[index].recid + ";";
					nameStr += goodsCategoryVos[index].categoryname;
					selectedThirdClassCategorys[index] 
							= new CategoryInfo(goodsCategoryVos[index].recid, 
								goodsCategoryVos[index].categoryno, goodsCategoryVos[index].categoryname);
				}		
				$sub1HideEditor.val(idStr);
				$sub1Editor.searchbox('setValue', nameStr);
			}
		});
	};
	
	var initEditors = function() {
		$styleEditor.combobox({
			valueField: 'code',  
	        textField: 'title',
			url: mainWeb + '/admin/channel/getThemeList',
		    required: true,
		    editable:false
		});
		
		$subEditor.combobox({
			valueField: 'recid',  
	        textField: 'categoryname',
			url: mainWeb + '/admin/channel/getSecondLeverOfGoodsCategory',
			onSelect: function(record) {
				subSelectionListener(record);
			},
		    required: true,
		    editable:false
		});
		
		$sub1Editor.searchbox({
			valueField: 'recid',  
	        textField: 'categoryname',
			searcher: function(value,name) {
				sub1SelectionListener(value,name);
			},
		    required: true
		});
		
		$sub1Editor.searchbox('textbox').attr('disabled', true);
		
		$sub1HideEditor.validatebox({  
		    required: true
		});
		
		$adUrlEditor.validatebox({  
		    min:0,
		    precision:2,
		    required: true
		});
		
		$urlEditor.validatebox({  
		    required: true
		});
		
		$formSaveButton.linkbutton({  
		    iconCls: 'icon-save'
		});
		$formSaveButton.click(function() {
			doSave();
		});
		
		isRendered = true;
	};
	
	var renderInit = function() {
		$editForm.appendTo($formArea);
		
		var $styleLine = $("<p></p>");
		var $subLine = $("<p></p>");
		var $sub1Line = $("<p></p>");
		var $adUrlLine = $("<p></p>");
		var $urlLine = $("<p></p>");
		
		$styleLine.appendTo($editForm);
		$subLine.appendTo($editForm);
		$sub1Line.appendTo($editForm);
		$adUrlLine.appendTo($editForm);
		$urlLine.appendTo($editForm);
		
		var $styleLabel = $("<label>��¥���</label>");
		var $subLabel = $("<label>�������ࣺ</label>");
		var $sub1Label = $("<label>�������ࣺ</label>");
		var $adUrlLabel = $("<label>����ַ��</label>");
		var $urlLabel = $("<label>���ӵ�ַ��</label>");
		
		$styleLabel.appendTo($styleLine);
		$styleEditor.appendTo($styleLine);
		
		$subLabel.appendTo($subLine);
		$subEditor.appendTo($subLine);
		
		$sub1Label.appendTo($sub1Line);
		$sub1Editor.appendTo($sub1Line);
		$sub1HideEditor.appendTo($sub1Line);
		
		$adUrlLabel.appendTo($adUrlLine);
		$adUrlEditor.appendTo($adUrlLine);
		
		$urlLabel.appendTo($urlLine);
		$urlEditor.appendTo($urlLine);
		
		$formSaveButton.appendTo($formArea);
		
		$sub1HideEditor.hide();
		
	};
	
	var init = function() {
		renderInit();
		initEditors();
		initConetent();
		showEditors(false);
		
		$editButton.appendTo($buttonArea);
		
		$editButton.linkbutton({  
		    iconCls: 'icon-add'
		});
		
		$editButton.click(function() {
			showEditors(true);
		});
		
	};
	
	init();
}

function CategoryInfo(recid, code, name) {
	this.getRecid = function() {
		return recid;
	};
	
	this.getCode = function() {
		return code;
	};
	
	this.getName = function() {
		return name;
	};
}

function WinowForChooseCategory(categoryId, selectedIds) {
	var $win = $("<div></div>");
	var $rightArea = $("<div></div>");
	var $bottomArea = $("<div style='background-color: #EEF;'></div>");

	var $goodsTable = null;

	this.addConfirmActionListener = function(fn) {
		$win.unbind("confirm");
		$win.bind("confirm", function() {
					fn($goodsTable.datagrid('getSelections'));
				});
	};

	var closeListener = function() {
		$win.remove();
	};

	var loadGoodsTable = function() {
		$.post(mainWeb + '/admin/goods/getDirectChildrenCategory', {
					"categoryId" : categoryId
				}, function(data) {
					$goodsTable.datagrid('loadData', data.rows);
				});
	};
	
	var selectDefaultRecord = function() {
		if (null != selectedIds) {
			for (var index = 0; index < selectedIds.length; index++) {
				$goodsTable.datagrid('selectRecord', selectedIds[index]);
			}
		}
	};

	var renderRightArea = function() {
		$goodsTable = $("<div></div>");
		$goodsTable.appendTo($rightArea);
		$goodsTable.datagrid({
					fitColumns : true,
					idField: 'recid',
					onLoadSuccess: selectDefaultRecord,
					columns : [[{
								field : 'recid',
								checkbox : true,
								width : 50
							}, {
								field : 'categoryno',
								title : '������',
								width : 100
							}, {
								field : 'categoryname',
								title : '��������',
								width : 100
							}]]
				});

		loadGoodsTable();
	};

	var renderBottomArea = function() {
		$saveButton = $("<input type='button' value='ȷ��'/>");
		$cancelButton = $("<input type='button' value='ȡ��'>");

		$saveButton.appendTo($bottomArea);
		$cancelButton.appendTo($bottomArea);

		$saveButton.click(function() {
					//var rows = $goodsTable.datagrid('getSelections');
					$win.trigger("confirm");
					$win.window('close');
				});

		$cancelButton.click(function() {
					$win.window('close');
				});
	};

	var renderInit = function() {
		$rightArea.css('float', 'left');
		$rightArea.css('border', '#8DB2E3 solid 0px');
		$rightArea.css('width', '100%');
		$rightArea.css('height', '92%');

		$bottomArea.css('float', 'left');
		$bottomArea.css('width', '100%');
		$bottomArea.css('height', '7%');
		$bottomArea.css('text-align', 'right');

		$win.appendTo('body');
		$rightArea.appendTo($win);
		$bottomArea.appendTo($win);

		$win.window({
			width:300,  
	    	height:450,
	    	collapsible: false,
	    	resizable: true,
	    	minimizable: false,
	    	maximizable: false,
	    	title: 'ѡ����Ʒ����',
	    	onClose: closeListener,
	    	modal:true 
		});
	};

	var init = function() {
		renderInit();
		renderRightArea();
		renderBottomArea();
	};

	init();
}
</script>
</body>
</html>