<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>���ֲ鿴</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--�����б�ʼ -->
		<div id="scoreviewtoolbar" style="height:32px;">
			<div style="display:inline;float:left;padding-top: 9px;">
				<span id="memberName">��Ա������</span>
				<span id="vantages">��ǰ���֣�</span>
			</div>
			<div style="display:inline;float:right;padding-top: 3px;">
				<span>��ʼʱ�䣺<input id="scoreBeginDate" type="text" class="easyui-datebox" /></span>
				<span>����ʱ�䣺<input id="scoreEndDate" type="text" class="easyui-datebox" /></span>
				<!-- <span>���<input id="type" name="type"></span> -->
				<span><input style='cursor: pointer;' type="button" value="ȷ��" onclick="scoreAction.queryAction()"></span>
			</div>
		</div>
		<table id="scoreviewDatagrid" toolbar="#scoreviewtoolbar">
		</table>
		<!-- �����б���� -->
		
		<script type="text/javascript">
	//��ʼ�� 
	var scoreAction;
	$(function(){
		scoreAction = new ScoreAction;
		function ScoreAction()
		{
			this.queryAction=function()
			{
				$("#scoreviewDatagrid").datagrid('reload',{beginDate:$("#scoreBeginDate").datebox('getValue'),endDate:$("#scoreEndDate").datebox('getValue')
				});
			}
		}
	});
	$(function(){
		if("null"==memberName)
		{
		memberName = "";
		}
		$("#scoreviewtoolbar #memberName").text("��Ա������"+memberName);
		//��ʼ�� --> ���� --> ��������б�
		$('#scoreviewDatagrid').datagrid({
			fit:true,
			singleSelect:true,
			remoteSort:false,
			border:false,
			idField:'code',
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			url:'<%=mainWeb%>/admin/member/getScoreview?memberId='+memberId,
			columns:[[
				{field:'occurdateStr',title:'����',width:100,align:'center',sortable:true},
				
				//{field:'code2',title:'���',width:100,align:'center',sortable:true},
				{field:'vantages',title:'����',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vtypeStr',title:'����',width:100,align:'center',sortable:true},
				{field:'relabillsno',title:'����',width:100,align:'center',sortable:true},
				//{field:'code5',title:'��Դ',width:100,align:'center',sortable:true,formatter:function(value,rec){return "��ֵ��";}}
			]],
			onLoadSuccess:function(data){
				$("#scoreviewtoolbar #vantages").text("��ǰ���֣�"+data.footer[0].vantages.toFixed(0));
			}
		});
		});
</script>
	</body>
</html>