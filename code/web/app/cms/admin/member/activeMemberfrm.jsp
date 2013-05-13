<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ա�</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<!-- begin of ���Ա�б� -->	
<div id="activeMembertoolbar">
	<div style="float:right;margin-right:5px;">
		<span>
			����ڣ�
			<input id="activeMemberBeginDate" type="text" class="easyui-datebox" />
			 - 
			<input id="activeMemberEndDate" type="text" class="easyui-datebox" />
		</span>
		<span> <input type="text"
				name="activeMemberSearchWord" value=""
				style="width: 130px; height: 22px;line-height: 22px;border:1px solid #CCC;" />
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" onclick="activeMemberAction.searchMemebers()">����</a>
			<!-- a href="javascript:void(0)" class="easyui-linkbutton" id="activeMemberExportBtn"
			iconCls="icon-undo" onclick="activeMemberAction.exportMember()">����</a -->
		</span>
	</div>
</div>
<table id="activeMemberDatagrid" toolbar="#activeMembertoolbar">
</table>
<!-- end of ���Ա�б� -->	
<script type="text/javascript">
//��ʼ��
$(function(){
	//��ʼ�� -> ���Ա�б�
	$('#activeMemberDatagrid').datagrid({fit:true,
		border:false,
		idField:'recid',
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		remoteSort:false,
		pagination:true,
		showFooter:true,
		url:'<%=mainWeb%>/admin/member/getActiveMembers',
		columns:[[
			{field:'code',title:'���',width:50,align:'center',sortable:true},
			{field:'username',title:'�û���',width:50,align:'center',sortable:true},
			{field:'membername',title:'����',width:50,align:'center',sortable:true},
			{field:'mobile',title:'�ֻ�����',width:50,align:'center',sortable:true},
			{field:'stationname',title:'վ��',width:50,align:'center',sortable:true},
			{field:'ordercount',title:'��������',width:50,align:'center',sortable:true},
			{field:'ordermoney',title:'�������',width:50,align:'center',sortable:true},
			{field:'returncount',title:'�˻�����',width:50,align:'center',sortable:true},
			{field:'returnmoney',title:'�˻����',width:50,align:'center',sortable:true}
		]]
	});
	//��ʼ�� -> �����¼� -> �ʱ��
	$('#activeMemberBeginDate').datebox({
		onSelect : function(date) {
			activeMemberAction.refresh();
		}
	});
	$('#activeMemberEndDate').datebox({
		onSelect : function(date) {
			activeMemberAction.refresh();
		}
	});
});
//���Ա
var activeMemberAction;
$(function(){
	activeMemberAction = new ActiveMemberAction();
	function ActiveMemberAction(){
		//���Ա -> ����
		this.searchMemebers = function(){
			activeMemberAction.refresh();
		}
		//���Ա -> ˢ��
		this.refresh = function(){
			$("#activeMemberDatagrid").datagrid('load',{
				searchWord:$("input[name='activeMemberSearchWord']").val(),
				beginDate:$("#activeMemberBeginDate").datebox('getValue'),
				endDate:$("#activeMemberEndDate").datebox('getValue')
			});
		}
		//���Ա -> ����
		this.exportMember = function(){
			$('#activeMemberExportBtn').linkbutton({text:'������...'}).linkbutton('disable');
			$.post(mainWeb+'/admin/member/exportActiveMember',
				{
					searchWord:$("input[name='activeMemberSearchWord']").val(),
					beginDate:$("#activeMemberBeginDate").datebox('getValue'),
					endDate:$("#activeMemberEndDate").datebox('getValue')
				},
				function(result){
					alert(result);
					$.messager.alert('��ʾ',result.message,'info');
					$('#activeMemberExportBtn').linkbutton({text:'����'}).linkbutton('enable');
			},'json');
		}
	}
});
</script>
</body>
</html>

