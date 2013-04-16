<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ա����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of ��Ա�б� -->
		<div id="membertoolbar" style="padding: 5px 10px 2px;">
			<span style="float: left; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" id="clearVangetesButton"
				iconCls="icon-reload" onclick="memberAction.clearVangetes();">��������</a>
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" id="clearVangetesButton"
				iconCls="icon-add" onclick="memberAction.importMember();">�����Ա</a>
			</span>
			<span id="memberNumber" style="float: left; padding-top: 5px;">��Ա������</span>
			<span style="float: right;"> <input type="text"
					name="searchWord" value=""
					style="width: 200px; height: 22px; padding-top: 3px; line-height: 22px;" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="memberAction.searchMemebers()">����</a>
			</span>
		</div>
		<table id="memberDatagrid" toolbar="#membertoolbar">
		</table>
		<!-- end of ��Ա�б� -->

		<!-- begin of ��Ա���� -->
		<div id="memderdetailDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 900px; height: 410px; padding: 0px" title="��Ա����"
			closed="true" iconCls="icon-save">
		</div>
		<!-- end of ��Ա���� -->

		<!-- begin of ��Ա�༭ -->
		<div id="memberDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 600px; height: 240px; padding: 20px 10px 0px;"
			title="��Ա" buttons="#member-dlg-buttons" closed="true">
			<form id="memberForm" method="post" modelAttribute="member">
				<table style="font-size: 14px;">
					<tr>
						<td>
							recver��
						</td>
						<td>
							<input type="text" name="recver" style="width: 200px;" />
						</td>
						<td>
							username��
						</td>
						<td>
							<input type="text" name="username" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							password��
						</td>
						<td>
							<input type="text" name="password" style="width: 200px;" />
						</td>
						<td>
							membername��
						</td>
						<td>
							<input type="text" name="membername" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							mobile��
						</td>
						<td>
							<input type="text" name="mobile" style="width: 200px;" />
						</td>
						<td>
							email��
						</td>
						<td>
							<input type="text" name="email" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							status��
						</td>
						<td>
							<input type="text" name="status" style="width: 200px;" />
						</td>
						<td>
							invitationcode��
						</td>
						<td>
							<input type="text" name="invitationcode" style="width: 200px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="member-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="memberAction.memberSaveOrUpdate()">����</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#memberDialog').dialog('close')">�ر�</a>
		</div>
		<!-- end of ��Ա�༭ -->
<div id="importMemberDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 150px; padding: 20px 10px 0px;"
			title="��ֵ��" buttons="#importMemberDialog-dlg-buttons" closed="true">
			<form id="importMemberForm" method="post" modelAttribute="form"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;����excel��
						</td>
						<td>
							<input id="importMemberExcelInput" name="excel"
								style="width: 350px;" type="file" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="importMemberDialog-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				id="importMemberDialog-dlg-buttons01"
				onclick="memberAction.sureImportSubmit()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				id="importMemberDialog-dlg-buttons02"
				onclick="javascript:$('#importMemberDialog').dialog('close')">ȡ ��</a>
		</div>

		<script type="text/javascript">
	//��ʼ��
	$(function(){
		//��ʼ�� -> ��Ա�б� ->�����Ա�б���
		$('#memberDatagrid').datagrid({fit:true,
			border:false,
			idField:'recid',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			url:'<%=mainWeb%>/admin/member/getMembers',
			columns:[[
				{field:'code',title:'���',width:50,align:'center',sortable:true},
				{field:'username',title:'�û���',width:50,align:'center',sortable:true},
				{field:'membername',title:'����',width:50,align:'center',sortable:true},
				{field:'mobile',title:'�ֻ�����',width:50,align:'center',sortable:true},
				{field:'email',title:'Email',width:50,align:'center',sortable:true},
				{field:'statusStr',title:'״̬',width:50,align:'center',sortable:true},
				{field:'recid',title:'����',width:50,align:'center',
					formatter:function(value,rec){
						return "<a href='#' class='operateChannel' onClick=memberAction.viewDetail('"+value+"','"+rec.membername+"')>�鿴����</a>";
					}
				}
			]]
		});
		//��ʼ�� -> ���Dialog��Debug
		var memderdetailDialog = $("#pageContent #memderdetailDialog");
		for(var i =1;i< memderdetailDialog.length;i++){
			$(memderdetailDialog[i]).dialog("destroy");
		}
		//��ʼ�� -> ��ʾ��Ա����$("#memberDatagrid").datagrid('getData').total
		$("#memberDatagrid").datagrid({
			onLoadSuccess:function(data){
				$("span#memberNumber").empty();
				$("span#memberNumber").append("��Ա������"+data.total);
			}
		});
		
	});
	
	//��Ա
	var memberAction;
	$(function(){
		//��Ա -> ��ʼ��memberAction��
		memberAction = new MemberAction();
		//��Ա -> ����MemberAction��
		function MemberAction(){
			this.clearVangetes = function(){
					$.messager.confirm('��ʾ','ȷ�����Ҫ�����е��û���������ô��',function(result){
						if(!result){
							return;
						}
						$('#clearVangetesButton').linkbutton({  
						    text:'������...' 
						}); 
						$('#clearVangetesButton').linkbutton('disable'); 
						$.post(mainWeb+'/admin/member/clearVangetes',function(result){
							if(result){
								$('#clearVangetesButton').linkbutton({  
								    text:'��������' 
								}); 
								$('#clearVangetesButton').linkbutton('enable'); 
								$.messager.alert('��ʾ',"������������ɣ�",'info');
							}else{
								$.messager.alert('��ʾ',result.message,'info');
							} 
						},'json');
					});		
			}
			//��Ա -> ����
			this.searchMemebers = function(){
				$("#memberDatagrid").datagrid('reload',{
					searchWord:$("input[name='searchWord']").val()
				});
			}
			//��Ա -> ����
			this.addMemeber = function(){
				$('#memberForm').form('clear');  
				$('#memberDialog').dialog('open').dialog('setTitle','������Ա');
			}			
			//��Ա -> ����
			this.memberSaveOrUpdate = function(){
				$('#memberForm').form('submit',{
					url:'<%=mainWeb%>/admin/member/addMember',
				    onSubmit:function(){
				    	
				    },   
				    success:function(data){
				    	$.messager.alert('��ʾ','��Ա����ɹ�!','info');//����ɹ�������ʾ��Ϣ
				    	$("#memberDialog").dialog('close');	//�رջ�Ա�Ի���
				    	$('#memberDatagrid').datagrid('reload');  //ˢ�»�Ա�б���Ϣ
				    }   
				}); 
			}
			//��Ա -> �鿴����
			this.viewDetail = function(recid,name){
				memberId = recid;
				memberName = name;
				$("#memderdetailDialog").dialog("refresh","<%=mainWeb%>/app/cms/admin/member/membermainfrm.jsp");
				$("#memderdetailDialog").dialog("open");
			}
			
			//��Ա -> �����Ա
			this.importMember = function(){
				$('#importMemberDialog').dialog('open').dialog('setTitle', '�����Ա');
			}

		this.sureImportSubmit = function(){
			$('#importMemberForm').form('submit', {
						url : mainWeb+'/admin/member/importMember',
						onSubmit : function() {
							var canSubmit =  $.trim($('#importMemberExcelInput').val())!='';
							$('#importMemberDialog-dlg-buttons01').linkbutton("disable");
							$('#importMemberDialog-dlg-buttons02').linkbutton("disable");
							return canSubmit;// ��֤��
						},
						success : function(data) { 
							$('#importMemberDialog-dlg-buttons01').linkbutton("enable");
							$('#importMemberDialog-dlg-buttons02').linkbutton("enable"); 
							var count = parseInt(data);
							if(count==0){
								$.messager.alert('��ʾ',"����ʧ�ܣ����鵼������", 'info');// ����ɹ�������ʾ��Ϣ
							}else{
								$.messager.alert('��ʾ', "�ɹ�����"+count+"λ��Ա��", 'info');// ����ɹ�������ʾ��Ϣ
							}
							$("#importMemberDialog").dialog('close'); // �ر���ֵ���Ի��� 
							$('#importMemberExcelInput').val('');
						}
					});
		}
		} 		
	});
	var memberId;
	var memberName;
</script>

	</body>
</html>

