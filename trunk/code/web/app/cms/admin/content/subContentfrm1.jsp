<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<script type="text/javascript" charset="gbk"
			src="<%=mainWeb%>/scripts/ueditor/editorcustomconfig.js"></script>
		<script type="text/javascript" charset="gbk"
			src="<%=mainWeb%>/scripts/ueditor/editorcustom.js"></script>
		<!-- begin of �����б� -->
		<table id="subContentDatagrid"">
		</table>
		<!-- end of �����б� -->

		<!-- begin of ���ݱ༭ height:520px -->
		<div id="subContentDialog" class="easyui-dialog"
			data-options="modal:false,closable:true,maximizable:true"
			style="width: 940px; height: 600px; padding: 20px 10px 0px; z-index: 1"
			title="����" buttons="#subContent-dlg-buttons" closed="true">
			<form id="subContentForm" method="post" modelAttribute="content">
				<input name="recid" type="hidden" />
				<input name="creatorid" type="hidden" />
				<input name="creator" type="hidden" />
				<input name="channelid" type="hidden" />
				<table width="880px">
					<tr>
						<td width='45px'>
							��Ŀ��
						</td>
						<td width="835px">
							<input name="show_channelName" type="text" style="width: 300px;border: 0px;" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>
							���⣺
						</td>
						<td>
							<input name="title" type="text" style="width: 835px;border: #bfccd8 1px solid;line-height: 18px;height: 22px;" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="z-index: 2">
								<script type="text/plain" charset="gbk" id="editor"
									name="content"></script>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="subContent-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="subContentAction.contentSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#subContentDialog').dialog('close')">ȡ��</a>
		</div>
		<!-- end of ���ݱ༭ -->

		<script type="text/javascript">
		//��ʼ��
		var subContent_editor;
		$(function(){
			//��ʼ�� -> ���帻�ı��༭��
			var option = {
	        	initialContent: '',//��ʼ���༭��������
	            minFrameHeight: 280,
	            imageUrl:'<%=mainWeb%>/admin/image/uploadImage',
	            imagePath:mainWeb,
	            imageManagerUrl:'<%=mainWeb%>/admin/image/getOnlineImagesList',
	            imageManagerPath:mainWeb
	            
		    };
			subContent_editor = new UE.ui.Editor(option);
			subContent_editor.render('editor');
		
			//��ʼ�� -> ���������б�
			$("#subContentDatagrid").datagrid({
				fit : true,
				border : false,
				idField : 'id',
				rownumbers : true,
				singleSelect:true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : '<%=mainWeb%>/admin/content/getContents?id='+channelId,
				columns:[[
					//{field:'code',title:'���',width:75,align:'center',sortable:true},
					{field:'title',title:'����',width:250,align:'left',sortable:true},
					{field:'creator',title:'������',width:75,align:'center',sortable:true},
					{field:'createdate',title:'����ʱ��',width:75,align:'center',sortable:true},
					{field:'status',title:'״̬',width:50,align:'center',sortable:true,
						formatter:function(value,rec){
							if(value == null || value == "02"){
								return "δ����";
							} else if (value == '01'){
								return "������";
							}
						}
					},
					{field:'recid',title:'����',width:100,align:'center', 
						formatter:function(value,rec){
							var isDelete = "";
							var isEnabled = "";
							if(rec.status == null || rec.status == "02"){
								isEnabled = "<a href='#' class='operateChannel' onClick=subContentAction.enabledContent('"+value+"')>����</a>";
								isDelete = "<a href='#' class='operateChannel' onClick=subContentAction.deleteContent('"+value+"')>ɾ��</a>";
							} else if (rec.status == '01'){
								isEnabled = "<a href='#' class='operateChannel' onClick=subContentAction.disabledContent('"+value+"')>ͣ��</a>";
							} 
							return isEnabled + "    " + isDelete;
						}
					}
				]]
			});
			
			//��ʼ�� -> ���dialog��BUG
			var subContentDialogDebug = $("#pageContent #subContentDialog");
			for(var i = 1;i < subContentDialogDebug.length;i++){
				$(subContentDialogDebug[i]).dialog("destroy");
			}
			
		});
		
		//����
		var subContentAction;
		$(function(){
			//���� -> ��ʼ��
			subContentAction = new SubContentAction();
			//���� -> ������
			function SubContentAction(){
				var url;
				//���� -> ����
				this.addContent = function(){
					//��ձ�
					$('#subContentForm').form('clear');
					//������ĿID������
					$("#subContentForm input[name='channelid']").val(channelId);
					$("#subContentForm input[name='show_channelName']").val(channelName);
					//��ʼ�����ı��������
					subContent_editor.setContent("");
					//�򿪶Ի���
					$('#subContentDialog').dialog('open').dialog('setTitle','��������');
					url = '<%=mainWeb%>/admin/content/addContent';
				}
				
				//���� -> �޸�
				this.updateContent = function(){
					var row = $('#subContentDatagrid').datagrid('getSelected');   
					if (row){
						//���ø��ı��������
						subContent_editor.setContent(row.content);
						$('#subContentForm').form('load',row);
						$("#subContentForm input[name='show_channelName']").val(channelName); 
					   	$('#subContentDialog').dialog('open').dialog('setTitle','�޸�����'); 
					   	url = '<%=mainWeb%>/admin/content/updateContent';   
					}else{
						$.messager.alert('��ʾ','��ѡ��Ҫ�޸ĵ�����!','warning');
					} 	
				}
				
				//���� -> ����
				this.contentSaveOrUpdate = function(){
					if(subContent_editor.hasContents()){
					    subContent_editor.sync();       //ͬ������
					}else{
						$.messager.alert('��ʾ','����δ��д!','warning');
						return;
					}
					$('#subContentForm').form('submit',{
						url:url,
					    onSubmit:function(){
					    	return subContentAction.validateForm();   
					    },   
					    success:function(data){
					    	data = eval("("+data+")");
					    	if(data.result){
					    		$.messager.alert('��ʾ','���ݱ���ɹ�!','info');//����ɹ�������ʾ��Ϣ
						    	$("#subContentDialog").dialog('close');	//�ر����ݶԻ���
						    	$('#subContentDatagrid').datagrid('reload');  //ˢ�������б���Ϣ
					    	}else{
					    		$.messager.alert('��ʾ','���ݱ���ʧ��!','error');//����ʧ�ܸ�����ʾ��Ϣ
					    	}					    	
					    }   
					}); 
				}
				
				//���� -> ���� -> ��֤��
				this.validateForm = function(){
					/*
					if($.trim($("#subContentForm input[name='code']").val()) == ''){
						$.messager.alert('��ʾ','���δ��д!','warning'); //���δ��д
						return false;
					}
					*/
					if($.trim($("#subContentForm input[name='title']").val()) == ''){
						$.messager.alert('��ʾ','����δ��д!','warning'); //����δ��д
						return false;
					}
					return true;
				}
				
				//���� -> ɾ��
				this.deleteContent = function(recid){
					$.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/content/deleteContent',{id:recid},function(result){
			                	$('#subContentDatagrid').datagrid('reload');  //ˢ�������б���Ϣ   
			                },'json');		                  
			            }   
				    });
				}
				
				//���� -> ����
				this.enabledContent = function(recid){
					$.post('<%=mainWeb%>/admin/content/enabledContent',{'recid':recid},
						function(){
							$('#subContentDatagrid').datagrid('reload');  //ˢ�������б���Ϣ
						}
					);
				}
				
				//���� -> ����
				this.disabledContent = function(recid){
					$.post('<%=mainWeb%>/admin/content/disabledContent',{'recid':recid},
						function(){
							$('#subContentDatagrid').datagrid('reload');  //ˢ�������б���Ϣ
						}
					);
				}
			}
		});
	</script>
	</body>
</html>