<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ʷ����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--begin of ��ʷ�����б�-->
		<div id="historyordertoolbar" style="height:32px">
		<div style="display:inline;float:left;padding-top: 9px;">
			<span id="memberName">��Ա������</span>
			<span id="count">����������</span>
			<span id="amount">������</span>
			</div>
			<div style="display:inline;float:right;padding-top: 3px;">
			<span>��ʼʱ�䣺<input id="beginDate" type="text" class="easyui-datebox" /></span>
				<span>����ʱ�䣺<input id="endDate" type="text" class="easyui-datebox" /></span>
				<!-- <span>���<input id="type" name="type"></span> -->
				<span><input style='cursor: pointer;' type="button" value="ȷ��" onclick="historyAction.queryAction()"></span>
			</div>		
			</div>
		<table id="historyorderDatagrid" toolbar="#historyordertoolbar">
		</table>
		<!--end of ��ʷ�����б� -->
		
		<script type="text/javascript">
	//��ʼ�� 
	var historyAction;
	$(function(){
		historyAction = new HistoryAction;
		function HistoryAction()
		{
			this.queryAction=function()
			{
				//console.dir($("#beginDate").datebox('getValue'));
				//console.dir($("#endDate").datebox('getValue'));
				$("#historyorderDatagrid").datagrid('reload',{
					beginDate:$("#historyordertoolbar #beginDate").datebox('getValue'),endDate:$("#historyordertoolbar #endDate").datebox('getValue')
				});
			}
		}
	});
	$(function(){
		memberName = "null"==memberName?"":memberName;
		$("#historyordertoolbar #memberName").text("��Ա������"+memberName);
		//��ʼ�� --> ��ǰ���� --> ���嵱ǰ�����б�
		$('#historyorderDatagrid').datagrid({
			fit:true,
			singleSelect:true,
			remoteSort:false,
			border:false,
			idField:'code',
			rownumbers:true,
			pagination:true,
			fitColumns:true,
			url:'<%=mainWeb%>/admin/member/getHistoryOrder?memberId='+memberId,
			columns:[[
				{field:'stationname',title:'վ��',width:100,align:'center',sortable:true},
				{field:'createDateStr',title:'����',width:150,align:'center',sortable:true},
				{field:'totalamount',title:'�������',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				{field:'vantages',title:'���ͻ���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vantagesCost',title:'���ѻ���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'billsno',title:'����',width:150,align:'center',sortable:true},
				{field:'payTypeStr',title:'֧����ʽ',width:100,align:'center',sortable:true},
				//{field:'code6',title:'״̬',width:200,align:'center',sortable:true,formatter:function(value,rec){return "�ɹ�";}}
			]],
			onLoadSuccess:function(data){
				$("#historyordertoolbar #count").text("����������"+data.total.toFixed(0));
				$("#historyordertoolbar #amount").text("������"+data.footer[0].totalAmount.toFixed(2));
			}
		});
	});
	
</script>
	</body>
</html>