<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>���λ</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of ����б� -->
		<table id="subAdvertisingDatagrid"">
		</table>
		<!-- end of ����б� -->

		<!-- begin of ���༭ -->
		<div id="subAdvertisingDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 240px; padding: 20px 10px 0px;"
			title="���λ" buttons="#subAdvertising-dlg-buttons" closed="true">
			<form id="subAdvertisingForm" method="post"
				modelAttribute="advertising">
				<input name="recid" type="hidden" />
				<input name="creatorid" type="hidden" />
				<input name="creator" type="hidden" />
				<input name="channelid" type="hidden" />
				<table>
					<tr style="height: 25px;">
						<td>
							������Ŀ��
						</td>
						<td>
							<input name="show_channelName" type="text" style="width: 350px;border: #bfccd8 0px solid;"
								readonly="readonly" />
						</td>
					</tr>
					<tr style="height: 35px;">
						<td>
							ͼƬ��ַ��
						</td>
						<td>
							<input name=imageurl type="text" readonly="readonly" style="width: 350px;border: #bfccd8 1px solid;"/>
							<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
							onclick="subAdvertisingAction.selectImg()">ѡ��</a>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td>
							���ӵ�ַ��
						</td>
						<td>
							<input type="text" name="url" style="width: 350px;border: #bfccd8 1px solid;"/>
						</td>
					</tr>
					<!-- tr>
						<td>
							���ã�
						</td>
						<td>
							<input type="checkbox" name="status" value="1"/>
						</td>
					</tr> -->
				</table>
			</form>
		</div>
		<div id="subAdvertising-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="subAdvertisingAction.advertisingSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#subAdvertisingDialog').dialog('close')">ȡ��</a>
		</div>
		<!-- end of ���༭ -->

		<script type="text/javascript">
		//��ʼ��
		$(function(){
			//��ʼ�� -> �������б�
			$("#subAdvertisingDatagrid").datagrid({
				fit : true,
				border : false,
				idField : 'id',
				rownumbers : true,
				singleSelect:true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : '<%=mainWeb%>/admin/advertising/getAdvertisings?id='+channelId,
				columns:[[
					{field:'creator',title:'������',width:100,align:'center',sortable:true},
					{field:'createdate',title:'����ʱ��',width:100,align:'center',sortable:true},
					{field:'imageurl',title:'ͼƬ��ַ',width:150,align:'center',sortable:true},
					{field:'url',title:'���ӵ�ַ',width:150,align:'center',sortable:true},
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
								isEnabled = "<a href='#' class='operateChannel' onClick=subAdvertisingAction.enabledAdvertising('"+value+"')>����</a>";
								isDelete = "<a href='#' class='operateChannel' onClick=subAdvertisingAction.deleteAdvertising('"+value+"')>ɾ��</a>";
							} else if (rec.status == '01'){
								isEnabled = "<a href='#' class='operateChannel' onClick=subAdvertisingAction.disabledAdvertising('"+value+"')>ͣ��</a>";
							} 
							return isEnabled + "    " + isDelete;
						}
					}
				]]
			});
			
			//��ʼ�� -> ���dialog��BUG
			var subAdvertisingDialogDebug = $("#pageContent #subAdvertisingDialog");
			for(var i = 1;i < subAdvertisingDialogDebug.length;i++){
				$(subAdvertisingDialogDebug[i]).dialog("destroy");
			}
			
		});
		
		//���
		var subAdvertisingAction;
		$(function(){
			//��� -> ��ʼ��
			subAdvertisingAction = new SubAdvertisingAction();
			//��� -> ������
			function SubAdvertisingAction(){
				var url="";
				//��� -> ����
				this.addAdvertising = function(){
					//��ձ�
					$('#subAdvertisingForm').form('clear');
					//������ĿID������
					$("#subAdvertisingForm input[name='channelid']").val(channelId);
					$("#subAdvertisingForm input[name='show_channelName']").val(channelName);
					$('#subAdvertisingDialog').dialog('open').dialog('setTitle','�������');
					url = '<%=mainWeb%>/admin/advertising/addAdvertising';
				}
				
				//��� -> �޸�
				this.updateAdvertising = function(){
					var row = $('#subAdvertisingDatagrid').datagrid('getSelected');   
					if (row){
						$('#subAdvertisingForm').form('load',row);
						$("#subAdvertisingForm input[name='show_channelName']").val(channelName);  
					   	$('#subAdvertisingDialog').dialog('open').dialog('setTitle','�޸Ĺ��'); 
					   	url = '<%=mainWeb%>/admin/advertising/updateAdvertising';   
					}else{
						$.messager.alert('��ʾ','��ѡ��Ҫ�޸ĵĹ��!','warning');
					} 	
				}
				
				//��� -> ѡ��ͼƬ
				this.selectImg = function(){
					var subAd_chooseImagesWindow = new ChooseImagesWindow();
					subAd_chooseImagesWindow.addConfirmActionListener(function(path){
						$("input[name='imageurl']").val(path);
					});
				}
				
				//��� -> ����
				this.advertisingSaveOrUpdate = function(){
					$('#subAdvertisingForm').form('submit',{
						url:url,
					    onSubmit:function(){
					    	return true;   
					    },   
					    success:function(data){
					    	data = eval("("+data+")");
					    	if(data.result){
					    		$.messager.alert('��ʾ','��汣��ɹ�!','info');//����ɹ�������ʾ��Ϣ
						    	$("#subAdvertisingDialog").dialog('close');	//�رչ��Ի���
						    	$('#subAdvertisingDatagrid').datagrid('reload');  //ˢ�¹���б���Ϣ
					    	}else{
					    		$.messager.alert('��ʾ','��汣��ʧ��!','error');//����ʧ�ܸ�����ʾ��Ϣ
					    	}				    	
					    }   
					});
				}
				
				//��� -> ���� -> ��֤��
				this.validateForm = function(){
					if($.trim($('#advertisingOfChannel').combobox('getValue')) == ''){
						$.messager.alert('��ʾ','������Ŀδѡ��!','warning'); //������Ŀδѡ��
						 return false;
					}
					if($.trim($("#subAdvertisingForm input[name='imageUrl']").val()) == ''){
						$.messager.alert('��ʾ','ͼƬ��ַδ��д!','warning'); //ͼƬ��ַδ��д
						return false;
					}
					if($.trim($("#subAdvertisingForm input[name='url']").val()) == ''){
						$.messager.alert('��ʾ','���ӵ�ַδ��д!','warning'); //���ӵ�ַδ��д
						return false;
					}
					return true;
				}
				
				//��� -> ɾ��
				this.deleteAdvertising = function(recid){
					$.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/advertising/deleteAdvertising',{id:recid},function(result){
			                	$('#subAdvertisingDatagrid').datagrid('reload');  //ˢ�¹���б���Ϣ   
			                },'json');		                  
			            }   
				    });
				}
				
				//��� -> ����
				this.enabledAdvertising = function(recid){
					$.post('<%=mainWeb%>/admin/advertising/enabledAdvertising',{'recid':recid},
						function(){
							$('#subAdvertisingDatagrid').datagrid('reload');  //ˢ�¹���б���Ϣ
						}
					);
				}
				
				//��� -> ����
				this.disabledAdvertising = function(recid){
					$.post(mainWeb + '/admin/advertising/disabledAdvertising',{'recid':recid},
						function(){
							$('#subAdvertisingDatagrid').datagrid('reload');  //ˢ�¹���б���Ϣ
						}
					);
				}
			}
		});
	</script>
	</body>
</html>