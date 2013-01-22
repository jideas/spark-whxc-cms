<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�ļ��й���</title>
	</head>
	<body>
		<div class="easyui-layout" fit="true" border="false">
			<!-- begin of ����ɾ���� -->
			<div region="north"
				style="height: 28px; padding-top: 1px; background-color: #EEF;"
				border="false">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="imageOperAction.addFolder()" id="topButton1">�����ļ���</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
					plain="true" onclick="imageOperAction.updateFolder()"
					id="topButton2">�޸��ļ���</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="imageOperAction.deleteFolder()"
					id="topButton3">ɾ���ļ���</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="imageOperAction.uploadImage()"
					id="topButton4">�ϴ�ͼƬ</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="imageOperAction.deleteImage()"
					id="topButton5">ɾ��ͼƬ</a>
			</div>
			<!-- end of ����ɾ���� -->
			<!-- begin of  -->
			<div region="west" split="true" title="�ļ�Ŀ¼" style="width: 200px;">
				<ul id="folderTree" class="easyui-tree" lines="true"
					url="<%=mainWeb%>/admin/folder/getFolders">
				</ul>
			</div>
			<!-- end of  -->
			<div region="center" id="imagesContext" title="���ϴ�ͼƬ">


			</div>
			<!-- end of  -->
		</div>
		<div id="imagesShowDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:true"
			style="width: 800px; height: 600px; padding: 20px 10px 0px; text-align: center; vertical-align: middle; line-height: 600px;"
			title="�鿴ͼƬ" closed="true">

		</div>

		<div id="imagesUploadDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 1050px;*width: 980px; height: 588px; padding: 20px 10px 0px;text-align: left;"
			title="�ϴ�ͼƬ" closed="true">
			<iframe id="imagesUploadDialogIframe" 
				style="margin-left: -100px; margin-top: -20px; margin-bottom: -50px;"
				align="top" frameborder="0" scrolling="auto" width="100%"
				height="100%">

			</iframe>
		</div>
		<div id="folderDetailDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 520px; height: 170px; padding: 20px 10px 0px;"
			title="�ļ���" buttons="#addfolder-dlg-buttons" closed="true">
			<form id="folderForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							�ϼ��ļ��У�
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
							�ļ������ƣ�
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
				onclick="imageOperAction.folderSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#folderDetailDialog').dialog('close')">ȡ
				��</a>
		</div>
		<div id="folderDetailDialog1" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 520px; height: 170px; padding: 20px 10px 0px;"
			title="�ļ���" buttons="#addfolder-dlg-buttons1" closed="true">
			<form id="folderForm1" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							ԭ����
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
							�޸�Ϊ��
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
				onclick="imageOperAction.folderSaveOrUpdate1()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#folderDetailDialog1').dialog('close')">ȡ
				��</a>
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
									+ '" style="cursor:hand" title="˫���Ŵ�" onclick="imageOperAction.selectedOneImage(\''
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
									+ '" style="cursor:hand" title="˫���Ŵ�" onclick="imageOperAction.selectedOneImage(\''
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
									+ '" style="cursor:hand" title="˫���Ŵ�" onclick="imageOperAction.selectedOneImage(\''
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
									+ '" style="cursor:hand" title="˫���Ŵ�" onclick="imageOperAction.selectedOneImage(\''
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
									+ '" style="cursor:hand" title="˫���Ŵ�" onclick="imageOperAction.selectedOneImage(\''
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
// ��Դ�Ĳ鿴
$(function() {

			// �����¼� --> չ���ڵ� --> Ϊ�ڵ����չ�������¼�
			$('#folderTree').tree({
						onExpand : function(node) {
							// ѡ��չ���Ľڵ�
							$('#folderTree').tree('select', node.target);
						}
					});

			// �����¼� --> �����ڵ� --> Ϊ�ڵ�������������¼�
			$('#folderTree').tree({
						onCollapse : function(node) {
							// ѡ��չ���Ľڵ�
							$('#folderTree').tree('select', node.target);
						}
					});
		});

// ��Դ������ɾ����
var imageOperAction;
$(function() {

	// ��ʼ��imageOperAction
	imageOperAction = new ImageOperAction();
	// ����ImageOperAction��
	function ImageOperAction() {
		// ����ȫ�ֱ���
		var node = null;
		var action = null;
		var operate = true;
		var selectedImage = null;
		var lastSelectedImg = null;
		

		// --> ������Դ
		this.addFolder = function() {
			if (!operate)
				return;
			action = 'add';
			node = $('#folderTree').tree('getSelected');
			$('#folderForm').form('clear');
			// ����������Դ�ĸ��ڵ�
			if (node != null && node.id != 'undefined') {
				$("#folderForm input[name='parentId']").val(node.id);
				$("#parentName0").val(node.text);
			} else {
				$.messager.alert('��ʾ', '��ѡ���ϼ��ļ���!', 'info');
				return;
			}
			// �򿪶Ի���
			$('#folderDetailDialog').dialog('open').dialog('setTitle', '�����ļ���');
			url = mainWeb + '/admin/folder/saveFolder';
		}

		// --> �޸���Դ
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
				$.messager.alert('��ʾ', '��ѡ��Ҫ�޸ĵ��ļ���!', 'info');
				return;
			}
			// �򿪶Ի���
			$('#folderDetailDialog1').dialog('open')
					.dialog('setTitle', '�޸��ļ���');
			url = mainWeb + '/admin/folder/saveFolder';
		}
	this.setSelectedNull = function(){
		 selectedImage = null;
		 lastSelectedImg = null;
		 $('#topButton5').linkbutton('disable');
	}
		// ��Դ --> ����
		this.folderSaveOrUpdate = function() {
			$('#folderForm').form('submit', {
						url : url,
						onSubmit : function() {
							return imageValidateForm(); // ��֤��
						},
						success : function(data) {
							data = eval('(' + data + ')');
							$.messager.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
							if (data.result == true) {
								$("#folderDetailDialog").dialog('close'); // �ر���ֵ���Ի���
								handleNode(data.list);
							}
						}
					});
		} // ��Դ --> ����
		this.folderSaveOrUpdate1 = function() {
			$('#folderForm1').form('submit', {
						url : url,
						onSubmit : function() {
							return imageValidateForm1(); // ��֤��
						},
						success : function(data) {
							data = eval('(' + data + ')');
							$.messager.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
							if (data.result == true) {
								$("#folderDetailDialog1").dialog('close'); // �ر���ֵ���Ի���
								handleNode(data.list);
							}
						}
					});
		}

		// ��Դ --> ���� --> �������ڵ�
		function handleNode(data) {
			if (action == 'add') {
				// ����ʱ׷��һ���ڵ�
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
				// �޸�ʱ���޸���Դ���ı������룬URL
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

		// ��Դ --> ɾ����Դ
		this.deleteFolder = function() {
			var node = $('#folderTree').tree('getSelected');
			if (node == null || !node.id) {
				$.messager.alert('��ʾ', '��ѡ��Ҫɾ�����ļ���!', 'info');
			} else {
				$.messager.confirm('ɾ��', '�Ƿ�ȷ��ɾ����', function(r) {
							if (r) {

								$.post(mainWeb + '/admin/folder/deleteFolder',
										{
											id : node.id
										}, function(result) {
											if (result.result) {
												$('#folderTree').tree('remove',
														node.target);
											} else {
												$.messager.alert('��ʾ',
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
				$.messager.alert('��ʾ', '����ѡ����Ʒ', 'warning');
			}
			$.messager.confirm('��ʾ',"ȷ��ɾ��ͼƬ��", function(result){
				if(!result){
					return ;
				} 
				$.post(mainWeb + '/admin/images/deleteImage', {
						imgUrl : selectedImage
					}, function(result) {
						if (result.result) {
							doRefreshRightPage($('#folderTree').tree('getSelected'));
						} else {
							$.messager.alert('��ʾ', result.message, 'warning');
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
			$('#imagesShowDialog').dialog('open').dialog('setTitle', '�鿴ͼƬ'); 
		}

		// ��ֵ�� -> ��֤��
		function imageValidateForm() {
			if ($.trim($("#folderForm input[name='title']").val()) == '') {
				$.messager.alert('��ʾ', '����д�ļ��б���!', 'warning'); // ����δ��д
				return false;
			}
			return true;
		}// ��ֵ�� -> ��֤��
		function imageValidateForm1() {
			if ($.trim($("#folderForm1 input[name='title']").val()) == '') {
				$.messager.alert('��ʾ', '����д�ļ��б���!', 'warning'); // ����δ��д
				return false;
			}
			return true;
		}

		// --> �ϴ�ͼƬ
		this.uploadImage = function() {
			node = $('#folderTree').tree('getSelected');
			if (node != null && node.id != 'undefined'
					&& node.attributes.code != "1") {
				$('#imagesUploadForm').form('clear');
				$('#preview').html('');
				$("#hiddenNodeId").val(node.id);
			} else if (node != null && node.id != 'undefined'
					&& node.attributes.code == "1") {
				$.messager.alert('��ʾ', 'ֻ����ײ���ļ��в�֧���ϴ�ͼƬ!', 'info');
				return;
			} else {
				$.messager.alert('��ʾ', '��ѡ��Ҫ�޸ĵ��ļ���!', 'info');
				return;
			}
			// initUploader(); 
			$('#imagesUploadDialogIframe').attr('src','<%=mainWeb%>/upload/uploader.jsp?folderId='+node.id+'&datetime='+new Date().getTime());
			$('#imagesUploadDialog').dialog('open').dialog('setTitle', '�ϴ�ͼƬ');
		}
	// --> �ϴ�ͼƬ
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
							$.messager.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
							if (data.result == true) {
								$("#imagesUploadDialog").dialog('close'); // �ر���ֵ���Ի���
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