<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�û�����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of �û��б� -->
		<div id="usertoolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="userAction.addUser()">�����û�</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" onclick="userAction.updateUser()">�޸��û�</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" onclick="userAction.deleteUser()">ɾ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-redo"
				plain="true" onclick="userAction.configureRole()">���ý�ɫ</a>	
			<!-- 	
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" onclick="userAction.batchDelete()">����ɾ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-redo"
				plain="true" onclick="userAction.batchEnabled()">��������</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-undo"
				plain="true" onclick="userAction.batchUnabled()">��������</a>
			-->
		</div>
		<table id="userDatagrid">
		</table>
		<!-- end of �û��б� -->

		<!-- begin of �û��༭ -->
		<div id="userDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 600px; height: 240px; padding: 20px 10px 0px;"
			title="�û�" buttons="#user-dlg-buttons" closed="true">
			<form id="userForm" method="post" modelAttribute="user">
				<input type="hidden" name="id" />
				<table style="font-size: 14px;">
					<tr>
						<td>
							������
						</td>
						<td>
							<input type="text" name="name" style="width: 200px;" />
						</td>
						<td>
							�˻�����
						</td>
						<td>
							<input type="text" name="account" style="width: 200px;" 
								onblur="userAction.validateAccountIsExists()"/>
						</td>
					</tr>
					<tr>
						<td>
							�̻���
						</td>
						<td>
							<input type="text" name="tele" style="width: 200px;" />
						</td>
						<td>
							�ֻ����룺
						</td>
						<td>
							<input type="text" name="mobile" style="width: 200px;" 
								onblur="userAction.validateMobileIsExists()"/>
						</td>
					</tr>
					<tr>
						<td>
							���룺
						</td>
						<td>
							<input type="password" name="pwd" style="width: 200px;" />
						</td>
						<td>
							�ظ����룺
						</td>
						<td>
							<input type="password" name="rePwd" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							Email��
						</td>
						<td>
							<input type="text" name="email" style="width: 200px;" 
								onblur="userAction.validateEmailIsExists()"/>
						</td>
						<td>
							�Ƿ����ã�
						</td>
						<td>
							<input type="checkbox" name="isEnabled" value="1" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="user-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="userAction.userSaveOrUpdate()">����</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#userDialog').dialog('close')">�ر�</a>
		</div>
		<!-- end of �û��༭ -->

		<!-- begin of ���ý�ɫ -->
		<div id="configRoleDialog" class="easyui-dialog" buttons="#configRole-dlg-buttons"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 600px; height: 300px; padding: 0px" title="���ý�ɫ"
			closed="true" iconCls="icon-save">
			<table id="configRoleDatagrid" fit="true" border="false" idField="id"
				fitColumns="true">

			</table>
		</div>
		<div id="configRole-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="userAction.userSelectedRole()">ȷ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#configRoleDialog').dialog('close')">�ر�</a>
		</div>
		<!-- end of ���ý�ɫ -->

		<!--begin of script -->
		<script type="text/javascript" charset="GBK">
	//��ʼ��
	$(function(){
		//��ʼ�� --> ���Dialog��Debug
		var userDialogDebug = $("#pageContent #userDialog");
		for(var i =1;i< userDialogDebug.length;i++){
			$(userDialogDebug[i]).dialog("destroy");
		}
		var configRoleDialogDebug = $("#pageContent #configRoleDialog");
		for(var i = 1;i < configRoleDialogDebug.length;i++){
			$(configRoleDialogDebug[i]).dialog("destroy");
		}
	
		//��ʼ�� --> �û� --> �����û��б���
		$('#userDatagrid').datagrid({
			remoteSort:false,
			toolbar:"#usertoolbar",
			fit:true,
			border:false,
			idField:"id",
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			url:"<%=mainWeb%>/admin/user/getUsers",
			columns:[[
				{field:'name',title:'����',width:100,align:'center',sortable:true},
				{field:'account',title:'�˻���',width:100,align:'center',sortable:true},
				{field:'tele',title:'�̻�',width:100,align:'center',sortable:true},
				{field:'mobile',title:'�ֻ�����',width:100,align:'center',sortable:true},				
				{field:'email',title:'Email',width:100,align:'center',sortable:true},
				{field:'isEnabled',title:'����״̬',width:100,align:'center',sortable:true,
					formatter:function(value,rec){
						if("1"==value){
							return "������";
						}else{
							return "δ����";
						}
					}
				}
			]]
		});
		
	});
	
	//�û� --> ����ɾ����
	var userAction;
	$(function(){
		//�û� --> ��ʼ��userAction��
		userAction = new UserAction();
				
		//�û� --> ȫ�ֱ��� --> URL
		var url = "";
				
		//�û� --> �����û���(UserAction)
		function UserAction(){
			//�û� --> �����û� --> �����Ի���
			this.addUser = function(){
				$('#userForm').form('clear');  
				$('#userDialog').dialog('open').dialog('setTitle','�����û�');
				url = '<%=mainWeb%>/admin/user/addUser'; 
				//�����û���/�˺������
				$("#userForm input[name='name']").removeAttr("disabled");
				$("#userForm input[name='account']").removeAttr("disabled");
			}
			
			//�û� --> �޸��û� --> �����Ի���
			this.updateUser = function(){
				var row = $('#userDatagrid').datagrid('getSelected');   
				if (row){
					$.get("<%=mainWeb%>/admin/user/getUserById",{'id':row.id},function(data){
						//���form����						
						$("#userForm input[name='id']").val(data.id);
						$("#userForm input[name='name']").val(data.name);
						$("#userForm input[name='account']").val(data.account);
						$("#userForm input[name='tele']").val(data.tele);
						$("#userForm input[name='mobile']").val(data.mobile);
						$("#userForm input[name='pwd']").val(data.pwd);
						$("#userForm input[name='rePwd']").val(data.pwd);
						$("#userForm input[name='email']").val(data.email);
						if(data.isEnabled == "1")$("#userForm input[name='isEnabled']").attr("checked",'true');
						//�򿪶Ի���
					   	$('#userDialog').dialog('open').dialog('setTitle','�޸��û�');
					    $("#userForm input[name='rePwd']").val($("#userForm input[name='pwd']").val()); 
					   	url = '<%=mainWeb%>/admin/user/updateUser';
					   	//��ֹ�û���/�˺������
					    $("#userForm input[name='name']").attr("disabled","disabled");
					   	$("#userForm input[name='account']").attr("disabled","disabled");
					});					
				}else{
					$.messager.alert('��ʾ','��ѡ��Ҫ�޸ĵ��û�!','warning');
				} 		
			}
			
			//�û� --> ɾ���û�
			this.deleteUser = function(){
				var row = $('#userDatagrid').datagrid('getSelected');   
				if (row){
				    $.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/user/deleteUser',{id:row.id},function(result){
			                	$('#userDatagrid').datagrid('reload');  //ˢ���û��б���Ϣ   
			                },'json');		                  
			            }   
				    });
				}else{
					$.messager.alert('��ʾ','��ѡ��Ҫɾ�����û�!','warning');
				}    
			}
			
			//�û� --> �����û�
			this.userSaveOrUpdate = function(){
				$('#userForm').form('submit',{
					url:url,
				    onSubmit:function(){		    	
				    	return validateUserForm();   
				    },   
				    success:function(data){
				    	$.messager.alert('��ʾ','�û�����ɹ�!','info');//����ɹ�������ʾ��Ϣ
				    	$("#userDialog").dialog('close');	//�ر��û��Ի���
				    	$('#userDatagrid').datagrid('reload');  //ˢ���û��б���Ϣ
				    }   
				}); 
			}
			
			//�û� -->  ��֤ --> ��֤���˺š��Ƿ��Ѵ���
			this.validateAccountIsExists = function(){	
				//�ж��˺��Ƿ����
				var id = $("#userForm input[name='id']").val();				
				var account = $.trim($("#userForm input[name='account']").val());
				 $.get('<%=mainWeb%>/admin/user/checkUserAccount',{id:id,account:account},function(result){
		         	if(result){
		         		$.messager.alert('��ʾ','�˺��Ѵ���!','warning');
		         		$("#userForm input[name='account']").val("");
		         	}
		         },'json');		         		         
			}
			
			//�û� -->  ��֤ --> ��֤���ֻ����Ƿ��Ѵ���
			this.validateMobileIsExists = function(){	
				//�ж��ֻ��Ƿ����
				var id = $("#userForm input[name='id']").val();
				var mobile = $.trim($("#userForm input[name='mobile']").val());
				 $.get('<%=mainWeb%>/admin/user/checkUserMobile',{id:id,mobile:mobile},function(result){
		         	if(result){
		         		$.messager.alert('��ʾ','�ֻ������Ѵ���!','warning');
		         		$("#userForm input[name='mobile']").val("");
		         	}
		         },'json');		         
			}
			
			//�û� -->  ��֤ --> ��֤��Email���Ƿ��Ѵ���
			this.validateEmailIsExists = function(){
				//�ж������Ƿ����
				var id = $("#userForm input[name='id']").val();
		        var email = $.trim($("#userForm input[name='email']").val());				
				$.get('<%=mainWeb%>/admin/user/checkUseEmail',{id:id,email:email},function(result){
		        	if(result){
		         		$.messager.alert('��ʾ','�����Ѵ���!','warning');
		         		$("#userForm input[name='email']").val("");
		         	}
		        },'json');		         
			}
					        
			//�û� --> ���� --> ��֤ --> ��֤�û������ݵ�һ����
			function validateUserForm(){
				if($.trim($("#userForm input[name='name']").val()) == ''){
					$.messager.alert('��ʾ','����δ��д!','warning'); //����δ��д
					return false;
				}
				if($.trim($("#userForm input[name='account']").val()) == ''){
					$.messager.alert('��ʾ','�˺�δ��д!','warning'); //�˺�δ��д
					return false;
				}
				if($.trim($("#userForm input[name='mobile']").val()) == ''){
					$.messager.alert('��ʾ','�ֻ�����δ��д!','warning'); //�ֻ�����δ��д
					return false;
				}
				if($.trim($("#userForm input[name='email']").val()) == ''){
					$.messager.alert('��ʾ','����δ��д!','warning'); //����δ��д
					return false;
				}
				if($.trim($("#userForm input[name='email']").val()) != ''){
					var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
					if(!emailReg.test($.trim($("#userForm input[name='email']").val()))){
						$.messager.alert('��ʾ','�����ʽ����ȷ!','warning'); //�ֻ�����δ��д
						return false;
					}
				}
				if($("#userForm input[name='pwd']").val() != $("#userForm input[name='rePwd']").val()){
					$.messager.alert('��ʾ','���벻һ��!','warning');
					$("#userForm input[name='pwd']").val("");
					$("#userForm input[name='rePwd']").val("");
					return false;
				}
				return true;
			}
			
			//�������� --> ���������û�
			this.batchEnabled = function(){
				var ids = getSelectedIDS("#userDatagrid"); //��ȡ���������ID
				if(ids == ''){
					$.messager.alert('��ʾ','��ѡ��Ҫ�������õ��û�!','warning');
				}else{
					$.post('<%=mainWeb%>/admin/user/batchEnabled',{'ids[]':ids},
							function(){
								$('#userDatagrid').datagrid('reload');  //ˢ���û��б���Ϣ
							}
					);
				}
				
			}
			
			//�������� --> ���������û�
			this.batchUnabled = function(){
				var ids = getSelectedIDS("#userDatagrid"); //��ȡ���������ID
				if(ids == ''){
					$.messager.alert('��ʾ','��ѡ��Ҫ�������õ��û�!','warning');
				}else{
					$.post('<%=mainWeb%>/admin/user/batchUnabled',{'ids[]':ids},
							function(){
								$('#userDatagrid').datagrid('reload');  //ˢ���û��б���Ϣ
							}
					);
				}
			}
			
			//�������� --> ����ɾ���û�
			this.batchDelete = function(){
				var ids = getSelectedIDS("#userDatagrid"); //��ȡ���������ID
				if(ids == ''){
					$.messager.alert('��ʾ','��ѡ��Ҫ����ɾ�����û�!','warning');
				}else{
					$.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(isSure){
						if(isSure){
							$.post('<%=mainWeb%>/admin/user/batchDelete',{'ids[]':ids},
							  function(){
								$('#userDatagrid').datagrid('reload');  //ˢ���û��б���Ϣ
							  }
							);
						}
					}); 
				}
			}
			
			//���ý�ɫ --> �򿪽�ɫ�Ի���
			var configUserId = ''; //���ý�ɫ���û�ID
			
			this.configureRole = function(){
				var row = $('#userDatagrid').datagrid('getSelected');
				if(!row){
					$.messager.alert('��ʾ','��ѡ���û�!','warning');
					return;
				}
				$("#configRoleDialog").dialog('open');
				$('#configRoleDatagrid').datagrid({
					onLoadSuccess: function (data){
						$("#configRoleDatagrid").datagrid("uncheckAll");
						for(var i=0;i<data.rows.length;i++){
							if(data.rows[i].ck == true){
								$("#configRoleDatagrid").datagrid("checkRow",i);
							}
						}
					},
					url:"<%=mainWeb%>/admin/user/getRolesOfUser/"+row.id,
					columns:[[
						{field:'ck',checkbox:true},
						{field:'code',title:'��ɫ����',width:50,align:'center'},
						{field:'title',title:'��ɫ����',width:50,align:'center'}
					]]
					
				});
				configUserId = row.id;
			}
			
			//���ý�ɫ --> ȷ��ѡ��
			this.userSelectedRole = function(){
				var ids = getSelectedIDS("#configRoleDatagrid"); //��ȡ���������ID
				if(ids == ''){
					$.messager.alert('��ʾ','��ѡ����û��Ľ�ɫ!','warning');
				}else{
					$.post('<%=mainWeb%>/admin/user/assignRoles',{'ids[]':ids,'id':configUserId}
						,function(){
							$("#configRoleDialog").dialog('close');
							$('#userDatagrid').datagrid('reload');  //ˢ���û��б���Ϣ
						}
						,'json'
					);
					
				}
			}
			
			//�������� --> ��ȡѡ�е�Ids
			function getSelectedIDS(targetDatagrid){
				var rows = $(targetDatagrid).datagrid('getSelections');
				var ids='';
				if(rows){
					for(var i = 0;i<rows.length;i++){
						ids += rows[i].id;
						if(i != rows.length-1){
							ids += ",";
						}
					}
				}
				return ids;
			}
		}
		
	});
</script>
	</body>
</html>