<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ǰ����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--begin of ��ǰ�����б�-->
		<div id="currentordertoolbar" style="height:32px;">
			<div style="padding-top:9px;">
				<span id="memberName">��Ա������</span>
				<span id="count">����������</span>
				<span id="amount">������</span>
			</div>
		</div>
		<table id="currentorderDatagrid" toolbar="#currentordertoolbar">
		</table>
		<!--end of ��ǰ�����б� -->
		
		<script type="text/javascript">
	//��ʼ�� 
	$(function(){
		
		$("#currentordertoolbar #memberName").text("��Ա������"+memberName);
		
		//��ʼ�� --> ��ǰ���� --> ���嵱ǰ�����б�
		$('#currentorderDatagrid').datagrid({
			fit:true,
			border:false,
			idField:'code',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			url:'<%=mainWeb%>/admin/member/getCurrentorder?memberId='+memberId,
			columns:[[
				{field:'createDateStr',title:'����',width:150,align:'center',sortable:true},
				{field:'totalamount',title:'���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				{field:'vantages',title:'���ͻ���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vantagesCost',title:'���ѻ���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'billsno',title:'����',width:150,align:'center',sortable:true},
				{field:'payTypeStr',title:'֧����ʽ',width:100,align:'center',sortable:true},
				{field:'statusStr',title:'״̬',width:100,align:'center',sortable:true}
			]],
			onLoadSuccess:function(data){
				$("#currentordertoolbar #count").text("����������"+data.total.toFixed(0));
				$("#currentordertoolbar #amount").text("������"+data.footer[0].totalAmount.toFixed(2));
			}
		});
	});
	
</script>
	</body>
</html>