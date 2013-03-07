<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>δ�����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--begin of ��ǰ�����б�-->
		<div id="uneffectedordertoolbar" style="height: 32px;">
			<div style="padding-top: 9px;">
				<span id="memberName">��Ա������</span>
				<span id="count">����������</span>
				<span id="amount">������</span>
			</div>
		</div>
		<table id="uneffectedorderDatagrid" toolbar="#uneffectedordertoolbar">
		</table>
		<!--end of ��ǰ�����б� -->

		<script type="text/javascript">
		var effectedOrderAction;
	//��ʼ�� 
	$(function(){
		$(function(){
			effectedOrderAction = new EffectedOrderAction();
			function EffectedOrderAction()
			{
				this.effectOrder = function(orderId)
				{
					$.messager.confirm('��ʾ','ȷ���Ѿ��յ��ö����Ŀ��',function(result){
						if(!result){
							return;
							}
						$.ajax({
							type : 'post',
							url : mainWeb + "/front/member/effectOrder?orderId="+orderId,
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							
							dataType : 'json',
							success : function(data) {
								if (data) {
									$.messager.alert("�����ɹ���");
								} else {
									$.messager.alert("����ʧ�ܣ�");
								}
								$("#uneffectedorderDatagrid").datagrid('reload');
							},
							error : function() {
								$.messager.alert("����ʧ�ܣ�");
								$("#uneffectedorderDatagrid").datagrid('reload');
							}
						});
				});
			}
		}});
		$("#uneffectedordertoolbar #memberName").text("��Ա������"+memberName);
		
		//��ʼ�� --> ��ǰ���� --> ���嵱ǰ�����б�
		$('#uneffectedorderDatagrid').datagrid({
			fit:true,
			border:false,
			idField:'code',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			url:'<%=mainWeb%>/admin/member/getuneffectedorder?memberId='+memberId,
			columns:[[
				{field:'createDateStr',title:'����',width:150,align:'center',sortable:true},
				{field:'totalamount',title:'���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				{field:'vantages',title:'���ͻ���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vantagesCost',title:'���ѻ���',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'billsno',title:'����',width:150,align:'center',sortable:true},
				{field:'payTypeStr',title:'֧����ʽ',width:100,align:'center',sortable:true},
				{field:'recid',title:'����',width:50,align:'center',
					formatter:function(value){
						return "<a href='#' class='operateChannel' onClick=effectedOrderAction.effectOrder('"+value+"')>ȷ���տ�</a>";
					}
				}
			]],
			onLoadSuccess:function(data){
				$("#uneffectedordertoolbar #count").text("����������"+data.total.toFixed(0));
				$("#uneffectedordertoolbar #amount").text("������"+data.footer[0].totalAmount.toFixed(2));
			}
		});
	});
	
	
	
</script>
	</body>
</html>