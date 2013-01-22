<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����¼</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--��ֵ��¼�б�ʼ -->
		<div id="chargelogtoolbar" style="height:32px;">
			<div style="display:inline;float:left;padding-top: 9px;">
				<span id="memberName" style="">��Ա������</span>
				<span id="amount">��ǰ��</span>
			</div>
			<div style="display:inline;float:right;padding-top: 3px;">
				<span>��ʼʱ�䣺<input id="chargelogBeginDate" type="text" class="easyui-datebox" /></span>
				<span>����ʱ�䣺<input id="chargelogEndDate" type="text" class="easyui-datebox" /></span>
				<!-- <span>���<input id="type" name="type"></span> -->
				<span><input style='cursor: pointer;' type="button" value="ȷ��" onclick="chargeAction.queryAction()"></span>
			</div>
		</div>
		<table id="chargelogDatagrid" toolbar="#chargelogtoolbar">
		</table>
		<!-- ��ֵ��¼�б���� -->

		<script type="text/javascript">
	//��ʼ�� 
	$(function(){
		chargeAction = new ChargeAction;
		function ChargeAction()
		{
			this.queryAction=function()
			{
				//console.dir($("#beginDate").datebox('getValue'));
				//console.dir($("#endDate").datebox('getValue'));
				$("#chargelogDatagrid").datagrid('reload',{beginDate:$("#chargelogBeginDate").datebox('getValue'),endDate:$("#chargelogEndDate").datebox('getValue')
				});
			}
		}
	});
	$(function(){
	if("null"==memberName)
	{
		memberName = "";
	}
	$("#memberName").text("��Ա������"+memberName);
		//��ʼ�� --> ��ֵ��¼ --> �����ֵ��¼�б���
		$('#chargelogDatagrid').datagrid({
			fit:true,
			border:false,
			idField:'recid',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			url:'<%=mainWeb%>/admin/member/getChargelog?memberId='+memberId,
			columns:[[
				{field:'occurdateStr',title:'����',width:100,align:'center',sortable:true},
				{field:'amount',title:'���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				//{field:'code2',title:'���ͻ���',width:100,align:'center',sortable:true},
				{field:'dealtypeStr',title:'����',width:100,align:'center',sortable:true},
				{field:'relabillsno',title:'����',width:100,align:'center',sortable:true},
				//{field:'code5',title:'���',width:200,align:'center', formatter:function(value,rec){return "�ɹ�";}}
			]],
			onLoadSuccess:function(data){
				$("#chargelogtoolbar #amount").text("��ǰ��"+data.footer[0].moneybalance.toFixed(2));
				
			}
		});
		
	});
	
</script>
	</body>
</html>