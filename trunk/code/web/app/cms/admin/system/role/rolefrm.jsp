<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ɫ����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--��ɫ�б�ʼ -->
		<div id="roletoolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="roleAction.addRole()">������ɫ</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true" onclick="roleAction.updateRole()">�޸Ľ�ɫ</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" onclick="roleAction.deleteRole()">ɾ����ɫ</a>
		</div>
		<table id="roleDatagrid" toolbar="#roletoolbar" fit="true"
			border="false" idField="id" rownumbers="true" fitColumns="true"
			singleSelect="true" url="<%=mainWeb%>/admin/role/getRoles">

		</table>
		<!-- ��ɫ�б���� -->
		
		<!--��ɫ�༭��ʼ -->
		<div id="roleDialog" class="easyui-dialog" closed="true" buttons="#role-dlg-buttons" closable="true"
			modal="true"
			style="width: 350px; height: 200px; padding: 30px 30px 20px 30px">
			<form id="roleForm" method="post" modelAttribute="role">
				<input type="hidden" name="id" />
				<table>
					<tr>
						<td>
							��ɫ���룺
						</td>
						<td>
							<input type="text" name="code" style="width: 180px;" />
						</td>
					</tr>
					<tr>
						<td>
							��ɫ���ƣ�
						</td>
						<td>
							<input type="text" name="title" style="width: 180px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="role-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="roleAction.roleSaveOrUpdate()">�� ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#roleDialog').dialog('close')">�� ��</a>
		</div>
		<!-- ��ɫ�༭���� -->

		<!-- ������Դ��ʼ -->
		<div id="configResourceDialog" class="easyui-dialog" buttons="#configResource-dlg-buttons"
			data-options="modal:true,closable:true,maximizable:false,closed:true"
			style="width: 300px; height: 400px; padding: 5px" title="������Դ"
			closed="true" iconCls="icon-save">
			<ul id="configResourceTree" lines="true"
				checkbox="true">
			</ul>
		</div>
		<div id="configResource-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="roleConfigResourceAction.roleSelectedResource()">ȷ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#configResourceDialog').dialog('close')">�ر�</a>
		</div>
		<!-- ������Դ���� -->

		<script type="text/javascript">
	//��ʼ�� 
	$(function(){
		//��ʼ�� --> ��ɫ --> �����ɫ�б���
		$('#roleDatagrid').datagrid({
			remoteSort:false,
			columns:[[
				{field:'code',title:'��ɫ����',width:250,align:'center',sortable:true},
				{field:'title',title:'��ɫ����',width:250,align:'center',sortable:true},
				{field:'id',title:'����',width:200,align:'center', formatter:function(value,rec){return "<a href='#' class='operateChannel' onClick=roleConfigResourceAction.configureResource('"+value+"')>������Դ</a>";}}
			]]
		});
		
		//��ʼ�� --> ����Dialog��Debug
		var roleDialogDebug = $("#pageContent #roleDialog");
		for(var i =1;i< roleDialogDebug.length;i++){
			$(roleDialogDebug[i]).dialog("destroy");
		}
		var configResourceDialogDebug = $("#pageContent #configResourceDialog");
		for(var i = 1;i < configResourceDialogDebug.length;i++){
			$(configResourceDialogDebug[i]).dialog("destroy");
		}
	});
		
	//��ɫ --> ����ɾ����
	var roleAction;
	$(function(){
		//ʵ����roleAction
		roleAction = new RoleAction();
		//��ɫ --> ����RoleAction��
		function RoleAction(){
			//ȫ�ֱ���
			var weburl = "";
			//��ɫ --> ����������ɫ�Ի���
			this.addRole = function(){
				$('#roleForm').form('clear'); //���form
			  	$('#roleDialog').dialog('open').dialog('setTitle','������ɫ');
			   	weburl = '<%=mainWeb%>/admin/role/addRole';
			} 
			
			//��ɫ --> �����޸Ľ�ɫ�Ի���
			this.updateRole = function(){
				$('#roleForm').form('clear'); //���form
				var row = $('#roleDatagrid').datagrid('getSelected');   
				if (row){
					$('#roleForm').form('load',row);	   
				  	$('#roleDialog').dialog('open').dialog('setTitle','�޸Ľ�ɫ');			   
				   	weburl = '<%=mainWeb%>/admin/role/updateRole';  
				}else{
					$.messager.alert('��ʾ','��ѡ��Ҫ�޸ĵĽ�ɫ��','warning');
				} 		
			}
			
			//��ɫ --> ɾ����ɫ
			this.deleteRole = function(){
				var row = $('#roleDatagrid').datagrid('getSelected');  
			    if (row){   
			        $.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(r){   
			          if (r){
			          		$.ajax({
							  url: '<%=mainWeb%>/admin/role/deleteRole',
							  data:{id:row.id},
							  complete: function(data) {
							    $('#roleDatagrid').datagrid('reload');
							  }
							});  
			      	}});
			    }else{
			    	$.messager.alert('��ʾ','��ѡ��Ҫɾ���Ľ�ɫ��','warning');
			    }
			    //���ѡ�е���
			    $('#roleDatagrid').datagrid('clearSelections');  
			} 
			
			//��ɫ --> �����ɫ
			this.roleSaveOrUpdate = function(){
				$('#roleForm').form('submit',{url:weburl,
					    onSubmit:function(){return validateRoleForm();}, 
			  			contentType:'application/json;charset=UTF-8',
					    success:function(data){
					    	$.messager.alert('��ʾ','��ɫ����ɹ�!','info');//����ɹ�������ʾ��Ϣ
					    	$("#roleDialog").dialog('close');	//�ر�������ɫ�Ի���
					    	$('#roleDatagrid').datagrid('reload');  //ˢ�½�ɫ�б���Ϣ  
					    	return false;
					    }   
					}); 
			}
			
			//��ɫ --> �����ɫ --> ��֤from
			function validateRoleForm(){
				if($.trim($("#roleForm input[name='code']").val()) == ''){
					$.messager.alert('��ʾ','��ɫ����δ��д!','warning'); //��ɫ����δ��д
					return false;
				}
				if($.trim($("#roleForm input[name='title']").val()) == ''){
					$.messager.alert('��ʾ','��ɫ����δ��д!','warning'); //��ɫ����δ��д
					return false;
				}		
				return true;
			}
		}
		
	});	
	
	//������Դ
	var roleConfigResourceAction;
	$(function(){
		//������Դ --> ��ʼ��RoleConfigResourceAction��
		roleConfigResourceAction = new RoleConfigResourceAction();
		 
		//������Դ --> ����RoleConfigResourceAction��
		var configRoleId = ''; //��ǰ������Դ�Ľ�ɫID
		function RoleConfigResourceAction(){
			//������Դ --> ����Դ�Ի���
			this.configureResource = function(roleId){
				$('#configResourceTree').tree({  
				    url:"<%=mainWeb%>/admin/role/getResourceOfRole?roleId="+roleId 
				}); 
				$("#configResourceDialog").dialog("open");
				configRoleId = roleId;
			}
			
			//������Դ --> �����Դѡ��
			this.roleSelectedResource = function(){
				var ids = getSelectedIDS("#configResourceTree");
				if(ids == ''){
					$.messager.alert('��ʾ','��ѡ��ý�ɫ����Դ!','warning');
				}else{
					$.post('<%=mainWeb%>/admin/role/roleConfigResource',{'ids[]':ids,'id':configRoleId});
					$("#configResourceDialog").dialog('close');
					$('#roleDatagrid').datagrid('reload');  //ˢ�½�ɫ�б���Ϣ   
				}
			}
		}
		
		
		//������Դ --> չ���ڵ� --> ���չ���ڵ�
		$('#configResourceTree').tree({
			onExpand: function(node){
				//�ж��Ƿ��Ǹ��ڵ�
				node.id = typeof(node.id) == "undefined" ? '' : node.id;
				//�ж��Ƿ��Ѿ�׷�ӹ��ӽڵ�
				if(isHasNodes(node)==false){
					//�ӷ�������ȡ�ӽڵ�
					getNodesFormServer(node);
				}
			}
		});
		
		//������Դ --> չ���ڵ� --> �ӷ�������ȡ�ӽڵ�
		function getNodesFormServer(node){
			$.ajax({
			  url: "<%=mainWeb%>/admin/role/getResourceOfRole",
			  type: "POST", 
	 		  data: {id : node.id},
			  success: function(data) {
			  	//���ӽڵ���ӵ����ڵ�
			  	showNodes(node,data);
			  }
			});	
		}
		
		//������Դ --> չ���ڵ� --> ����ӽڵ㵽���ڵ�
		function showNodes(node,resourceJSON){
			$('#configResourceTree').tree('append', {
				parent: node.target,
				data:resourceJSON
			});
		}
		
		//������Դ --> չ���ڵ� --> �жϸýڵ��Ƿ����ӽڵ㣨����Ѿ����ӽڵ㣬��׷��;����׷�ӣ�
		function isHasNodes(node){
			return $('#configResourceTree').tree('getChildren',node.target) != "";
		} 
		
		//�������� --> ��ȡѡ�е�Ids
		function getSelectedIDS(targetTree){
			var parents = $(targetTree).tree('getChecked', 'indeterminate');
			var childrens = $(targetTree).tree('getChecked');
			var ids='';
			if(parents){
				for(var i = 0;i<parents.length;i++){
					ids += parents[i].id;
					ids += ",";
				}
			}
			if(childrens){
				for(var i = 0;i<childrens.length;i++){
					ids += childrens[i].id;
					if(i != childrens.length-1){
						ids += ",";
					}
				}
			}
			return ids;
		}
	});
	
</script>
	</body>
</html>

