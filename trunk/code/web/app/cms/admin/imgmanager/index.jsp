<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>文件夹管理</title>
	</head>
	<body>
		<div class="easyui-layout" fit="true" border="false">
			<!-- begin of 增，删，改 -->
			<div region="north"
				style="height: 28px; padding-top: 1px; background-color: #EEF;"
				border="false">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="imageOperAction.addFolder()" id="topButton1">新增文件夹</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
					plain="true" onclick="imageOperAction.updateFolder()"
					id="topButton2">修改文件夹</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="imageOperAction.deleteFolder()"
					id="topButton3">删除文件夹</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="imageOperAction.uploadImage()"
					id="topButton4">上传图片</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="imageOperAction.deleteImage()"
					id="topButton5">删除图片</a>
			</div>
			<!-- end of 增，删，改 -->
			<!-- begin of  -->
			<div region="west" split="true" title="文件目录" style="width: 200px;">
				<ul id="folderTree" class="easyui-tree" lines="true"
					url="<%=mainWeb%>/admin/folder/getFolders">
				</ul>
			</div>
			<!-- end of  -->
			<div region="center" id="imagesContext" title="已上传图片">


			</div>
			<!-- end of  -->
		</div>
		<div id="imagesShowDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:true"
			style="width: 800px; height: 600px; padding: 20px 10px 0px; text-align: center; vertical-align: middle; line-height: 600px;"
			title="查看图片" closed="true">

		</div>

		<div id="imagesUploadDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 1050px;*width: 980px; height: 588px; padding: 20px 10px 0px;text-align: left;"
			title="上传图片" closed="true">
			<iframe id="imagesUploadDialogIframe" 
				style="margin-left: -100px; margin-top: -20px; margin-bottom: -50px;"
				align="top" frameborder="0" scrolling="auto" width="100%"
				height="100%">

			</iframe>
		</div>
		<div id="folderDetailDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 520px; height: 170px; padding: 20px 10px 0px;"
			title="文件夹" buttons="#addfolder-dlg-buttons" closed="true">
			<form id="folderForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							上级文件夹：
						</td>
						<td>
							<input id="parentName0" name="parentName" style="width: 400px;"
								readonly="readonly" />
							<input id="parentId0" name="parentId" type="hidden"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							文件夹名称：
						</td>
						<td>
							<input id="folderName0" name="title" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addfolder-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="imageOperAction.folderSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#folderDetailDialog').dialog('close')">取
				消</a>
		</div>
		<div id="folderDetailDialog1" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 520px; height: 170px; padding: 20px 10px 0px;"
			title="文件夹" buttons="#addfolder-dlg-buttons1" closed="true">
			<form id="folderForm1" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							原名：
						</td>
						<td>
							<input id="parentName1" name="parentName" style="width: 400px;"
								readonly="readonly" />
							<input id="parentId1" name="parentId" type="hidden"
								style="width: 400px;" />
							<input id="hiddenrecid1" name="recid" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							修改为：
						</td>
						<td>
							<input id="folderName1" name="title" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addfolder-dlg-buttons1">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="imageOperAction.folderSaveOrUpdate1()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#folderDetailDialog1').dialog('close')">取
				消</a>
		</div>
		<script type="text/javascript">
		$('#folderTree').tree({
			onClick : function(node) {
				imageOperAction.selectedImage = null;
				imageOperAction.lastSelectedImg = null;
				$('#topButton5').linkbutton('disable');
				if (!node) {
					return;
				}
				$('#topButton1').linkbutton('enable');
				$('#topButton2').linkbutton('enable');
				$('#topButton3').linkbutton('enable');
				$('#topButton4').linkbutton('enable');
				if (node.attributes.code == "1") {
					$('#topButton4').linkbutton('disable');
				} else if (node.attributes.code == "2") {
					$('#topButton4').linkbutton('disable');
					$('#topButton2').linkbutton('disable');
				}
				if(!$('#folderTree').tree('isLeaf', node.target) ){
					$('#topButton3').linkbutton('disable');
				}
				doRefreshRightPage(node);
			}
		});

	function doRefreshRightPage(node) { 
	if(!node){
			node = $('#folderTree').tree('getSelected');
	}
							imageOperAction.setSelectedNull();
	if (node && $('#folderTree').tree('isLeaf', node.target) ) {
		$.post(mainWeb + '/admin/images/getImages', {
					folderId : node.id
				}, function(result) {
					if (!result || result.length == 0) {
						$('#imagesContext').html("");
						$('#topButton3').linkbutton('enable');
						return;
					}
					$('#topButton3').linkbutton('disable');
					$('#topButton1').linkbutton('disable');
					var str = '<table width="100%"> ';
					var x = 0;
					for (var i = 0; i < result.length;) {
						str = str + '<tr> ';
						if (i < result.length) {
							str = str + '<td width="20%" align="center"> &nbsp;';
							str = str
									+ '<img src='
									+ mainWeb
									+ result[i].imgUrl
									+ ' width="'
									+ result[i].width
									+ '" max-height="'
									+ result[i].height
									+ '" style="cursor:hand" title="双击放大" onclick="imageOperAction.selectedOneImage(\''
									+ result[i].imgUrl
									+ '\',this);" ondblclick="imageOperAction.showImage(\''
									+ mainWeb + result[i].imgUrl + '\');"/>';
							str = str + '</td>';
							i++;
						} else {
							str = str + '<td width="20%"> &nbsp;</td>';
							i++;
						}
						if (i < result.length) {
							str = str + '<td width="20%" align="center"> &nbsp;';
							str = str
									+ '<img src='
									+ mainWeb
									+ result[i].imgUrl
									+ ' width="'
									+ result[i].width
									+ '" max-height="'
									+ result[i].height
									+ '" style="cursor:hand" title="双击放大" onclick="imageOperAction.selectedOneImage(\''
									+ result[i].imgUrl
									+ '\',this);" ondblclick="imageOperAction.showImage(\''
									+ mainWeb + result[i].imgUrl + '\');"/>';
							str = str + '</td>';
							i++;
						} else {
							str = str + '<td width="20%"> &nbsp;</td>';
							i++;
						}
						if (i < result.length) {
							str = str + '<td width="20%" align="center"> &nbsp;';
							str = str
									+ '<img src='
									+ mainWeb
									+ result[i].imgUrl
									+ ' width="'
									+ result[i].width
									+ '" max-height="'
									+ result[i].height
									+ '" style="cursor:hand" title="双击放大" onclick="imageOperAction.selectedOneImage(\''
									+ result[i].imgUrl
									+ '\',this);" ondblclick="imageOperAction.showImage(\''
									+ mainWeb + result[i].imgUrl + '\');"/>';
							str = str + '</td>';
							i++;
						} else {
							str = str + '<td width="20%"> &nbsp;</td>';
							i++;
						}
						if (i < result.length) {
							str = str + '<td width="20%" align="center"> &nbsp;';
							str = str
									+ '<img src='
									+ mainWeb
									+ result[i].imgUrl
									+ ' width="'
									+ result[i].width
									+ '" max-height="'
									+ result[i].height
									+ '" style="cursor:hand" title="双击放大" onclick="imageOperAction.selectedOneImage(\''
									+ result[i].imgUrl
									+ '\',this);"  ondblclick="imageOperAction.showImage(\''
									+ mainWeb + result[i].imgUrl + '\');"/>';
							str = str + '</td>';
							i++;
						} else {
							str = str + '<td width="20%"> &nbsp;</td>';
							i++;
						}
						if (i < result.length) {
							str = str + '<td width="20%" align="center"> &nbsp;';
							str = str
									+ '<img src='
									+ mainWeb
									+ result[i].imgUrl
									+ ' width="'
									+ result[i].width
									+ '" max-height="'
									+ result[i].height
									+ '" style="cursor:hand" title="双击放大" onclick="imageOperAction.selectedOneImage(\''
									+ result[i].imgUrl
									+ '\',this);" ondblclick="imageOperAction.showImage(\''
									+ mainWeb + result[i].imgUrl + '\');"/>';
							str = str + '</td>';
							i++;
						} else {
							str = str + '<td width="20%"> &nbsp;</td>';
							i++;
						}
						str = str + '</tr> ';
						str = str + '<tr> ';
						if (x < result.length) {
							str = str
									+ '<td width="20%" align="center"> &nbsp;';
							str = str + result[x].title;
							str = str + '</td>';
							x++;
						} else {
							str = str
									+ '<td width="20%" align="center"> &nbsp;</td>';
							x++;
						}
						if (x < result.length) {
							str = str
									+ '<td width="20%" align="center"> &nbsp;';
							str = str + result[x].title;
							str = str + '</td>';
							x++;
						} else {
							str = str
									+ '<td width="20%" align="center"> &nbsp;</td>';
							x++;
						}
						if (x < result.length) {
							str = str
									+ '<td width="20%" align="center"> &nbsp;';
							str = str + result[x].title;
							str = str + '</td>';
							x++;
						} else {
							str = str
									+ '<td width="20%" align="center"> &nbsp;</td>';
							x++;
						}
						if (x < result.length) {
							str = str
									+ '<td width="20%" align="center"> &nbsp;';
							str = str + result[x].title;
							str = str + '</td>';
							x++;
						} else {
							str = str
									+ '<td width="20%" align="center"> &nbsp;</td>';
							x++;
						}
						if (x < result.length) {
							str = str
									+ '<td width="20%" align="center"> &nbsp;';
							str = str + result[x].title;
							str = str + '</td>';
							x++;
						} else {
							str = str + '<td width="20%"> &nbsp;</td>';
							i++;
						}
						str = str + '</tr> ';
					}
					str = str + '</table> ';
					$('#imagesContext').html(str);
				}, 'json');
	} else {
		$('#imagesContext').html("");
	}
}
function previewImage(file) {
	var MAXWIDTH = 200;
	var MAXHEIGHT = 200;
	var div = document.getElementById('preview');
	if (file.files && file.files[0]) {
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.onload = function() {
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			img.style.marginLeft = rect.left + 'px';
			img.style.marginTop = rect.top + 'px';
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	} else {
		file.select();
		$('#preview').html('<img id=imghead width=200 height=200/>');
		var newPreview = document.getElementById("imghead");
		newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);";
		newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = document.selection
				.createRange().text;
	}
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		top : 0,
		left : 0,
		width : width,
		height : height
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		} else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}

	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}
// 资源的查看
$(function() {

			// 监听事件 --> 展开节点 --> 为节点添加展开监听事件
			$('#folderTree').tree({
						onExpand : function(node) {
							// 选中展开的节点
							$('#folderTree').tree('select', node.target);
						}
					});

			// 监听事件 --> 收缩节点 --> 为节点添加收缩监听事件
			$('#folderTree').tree({
						onCollapse : function(node) {
							// 选中展开的节点
							$('#folderTree').tree('select', node.target);
						}
					});
		});

// 资源的增，删，改
var imageOperAction;
$(function() {

	// 初始化imageOperAction
	imageOperAction = new ImageOperAction();
	// 定义ImageOperAction类
	function ImageOperAction() {
		// 定义全局变量
		var node = null;
		var action = null;
		var operate = true;
		var selectedImage = null;
		var lastSelectedImg = null;
		

		// --> 新增资源
		this.addFolder = function() {
			if (!operate)
				return;
			action = 'add';
			node = $('#folderTree').tree('getSelected');
			$('#folderForm').form('clear');
			// 设置新增资源的父节点
			if (node != null && node.id != 'undefined') {
				$("#folderForm input[name='parentId']").val(node.id);
				$("#parentName0").val(node.text);
			} else {
				$.messager.alert('提示', '请选择上级文件夹!', 'info');
				return;
			}
			// 打开对话框
			$('#folderDetailDialog').dialog('open').dialog('setTitle', '新增文件夹');
			url = mainWeb + '/admin/folder/saveFolder';
		}

		// --> 修改资源
		this.updateFolder = function() {
			if (!operate)
				return;
			action = 'update';
			node = $('#folderTree').tree('getSelected');
			$('#folderForm1').form('clear');
			if (node != null && node.id != 'undefined') {
				$("#parentId1").val(node.attributes.parentId);
				$("#hiddenrecid1").val(node.id);
				$("#parentName1").val(node.text);
			} else {
				$.messager.alert('提示', '请选择要修改的文件夹!', 'info');
				return;
			}
			// 打开对话框
			$('#folderDetailDialog1').dialog('open')
					.dialog('setTitle', '修改文件夹');
			url = mainWeb + '/admin/folder/saveFolder';
		}
	this.setSelectedNull = function(){
		 selectedImage = null;
		 lastSelectedImg = null;
		 $('#topButton5').linkbutton('disable');
	}
		// 资源 --> 保存
		this.folderSaveOrUpdate = function() {
			$('#folderForm').form('submit', {
						url : url,
						onSubmit : function() {
							return imageValidateForm(); // 验证表单
						},
						success : function(data) {
							data = eval('(' + data + ')');
							$.messager.alert('提示', data.message, 'info');// 保存成功给出提示信息
							if (data.result == true) {
								$("#folderDetailDialog").dialog('close'); // 关闭面值卡对话框
								handleNode(data.list);
							}
						}
					});
		} // 资源 --> 保存
		this.folderSaveOrUpdate1 = function() {
			$('#folderForm1').form('submit', {
						url : url,
						onSubmit : function() {
							return imageValidateForm1(); // 验证表单
						},
						success : function(data) {
							data = eval('(' + data + ')');
							$.messager.alert('提示', data.message, 'info');// 保存成功给出提示信息
							if (data.result == true) {
								$("#folderDetailDialog1").dialog('close'); // 关闭面值卡对话框
								handleNode(data.list);
							}
						}
					});
		}

		// 资源 --> 保存 --> 保存后处理节点
		function handleNode(data) {
			if (action == 'add') {
				// 新增时追加一个节点
				if (node == null || !node.id || node.id == 'root') {
					$('#folderTree').tree('append', {
								parent : $('#folderTree').tree("getRoot").target,
								data : data
							});
				} else {
					$('#folderTree').tree('append', {
								parent : node.target,
								data : data
							});
				}

			} else {
				// 修改时，修改资源的文本，编码，URL
				if (node) {
					$('#folderTree').tree('update', {
								target : node.target,
								text : data[0].text,
								attributes : {
									parentId : data[0].attributes.parentId
								}
							});
				}
			}
		}

		// 资源 --> 删除资源
		this.deleteFolder = function() {
			var node = $('#folderTree').tree('getSelected');
			if (node == null || !node.id) {
				$.messager.alert('提示', '请选择要删除的文件夹!', 'info');
			} else {
				$.messager.confirm('删除', '是否确定删除？', function(r) {
							if (r) {

								$.post(mainWeb + '/admin/folder/deleteFolder',
										{
											id : node.id
										}, function(result) {
											if (result.result) {
												$('#folderTree').tree('remove',
														node.target);
											} else {
												$.messager.alert('提示',
														result.message,
														'warning');
											}
										}, 'json');
							}
						})
			}
		}
		this.deleteImage = function() {
			if (!selectedImage) {
				$.messager.alert('提示', '请先选择商品', 'warning');
			}
			$.messager.confirm('提示',"确定删除图片？", function(result){
				if(!result){
					return ;
				} 
				$.post(mainWeb + '/admin/images/deleteImage', {
						imgUrl : selectedImage
					}, function(result) {
						if (result.result) {
							doRefreshRightPage($('#folderTree').tree('getSelected'));
						} else {
							$.messager.alert('提示', result.message, 'warning');
						}
					}, 'json');
			});
		}

		this.selectedOneImage = function(url, img) { 
			if(url==selectedImage){
				return;
			}
			selectedImage = url; 
			img.style["border"] = "blue solid 3px";
			if (lastSelectedImg) {
				lastSelectedImg.style["border"] = "0px";
			}
			lastSelectedImg = img;
			$('#topButton5').linkbutton('enable');
		}
		this.showImage = function(url) {
			$('#imagesShowDialog').html('<img src="' + url + '"/>');
			$('#imagesShowDialog').dialog('open').dialog('setTitle', '查看图片'); 
		}

		// 面值卡 -> 验证表单
		function imageValidateForm() {
			if ($.trim($("#folderForm input[name='title']").val()) == '') {
				$.messager.alert('提示', '请填写文件夹标题!', 'warning'); // 数量未填写
				return false;
			}
			return true;
		}// 面值卡 -> 验证表单
		function imageValidateForm1() {
			if ($.trim($("#folderForm1 input[name='title']").val()) == '') {
				$.messager.alert('提示', '请填写文件夹标题!', 'warning'); // 数量未填写
				return false;
			}
			return true;
		}

		// --> 上传图片
		this.uploadImage = function() {
			node = $('#folderTree').tree('getSelected');
			if (node != null && node.id != 'undefined'
					&& node.attributes.code != "1") {
				$('#imagesUploadForm').form('clear');
				$('#preview').html('');
				$("#hiddenNodeId").val(node.id);
			} else if (node != null && node.id != 'undefined'
					&& node.attributes.code == "1") {
				$.messager.alert('提示', '只有最底层的文件夹才支持上传图片!', 'info');
				return;
			} else {
				$.messager.alert('提示', '请选择要修改的文件夹!', 'info');
				return;
			}
			// initUploader(); 
			$('#imagesUploadDialogIframe').attr('src','<%=mainWeb%>/upload/uploader.jsp?folderId='+node.id+'&datetime='+new Date().getTime());
			$('#imagesUploadDialog').dialog('open').dialog('setTitle', '上传图片');
		}
	// --> 上传图片
		this.uploadImageOver = function() {
			 doRefreshRightPage();
			$('#imagesUploadDialog').dialog('close');
			$('#imagesUploadDialogIframe').attr('src','<%=mainWeb%>/upload/uploader.jsp?folderId='+node.id+'&datetime='+new Date().getTime());
		
		}
		this.doImageUpload = function() {
			$('#imagesUploadForm').form('submit', {
						url : url,
						success : function(data) {
							data = eval('(' + data + ')');
							$.messager.alert('提示', data.message, 'info');// 保存成功给出提示信息
							if (data.result == true) {
								$("#imagesUploadDialog").dialog('close'); // 关闭面值卡对话框
								doRefreshRightPage(node);
							}
						}
					});
		}
	}
});
		</script>
	</body>
</html>