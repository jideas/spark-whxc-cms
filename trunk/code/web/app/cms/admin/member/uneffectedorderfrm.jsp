<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>未付款订单</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--begin of 当前订单列表-->
		<div id="uneffectedordertoolbar" style="height: 32px;">
			<div style="padding-top: 9px;">
				<span id="memberName">会员姓名：</span>
				<span id="count">订单数量：</span>
				<span id="amount">订单金额：</span>
			</div>
		</div>
		<table id="uneffectedorderDatagrid" toolbar="#uneffectedordertoolbar">
		</table>
		<!--end of 当前订单列表 -->

		<script type="text/javascript">
		var effectedOrderAction;
	//初始化 
	$(function(){
		$(function(){
			effectedOrderAction = new EffectedOrderAction();
			function EffectedOrderAction()
			{
				this.effectOrder = function(orderId)
				{
					$.messager.confirm('提示','确定已经收到该订单的款项？',function(result){
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
									$.messager.alert("操作成功！");
								} else {
									$.messager.alert("操作失败！");
								}
								$("#uneffectedorderDatagrid").datagrid('reload');
							},
							error : function() {
								$.messager.alert("操作失败！");
								$("#uneffectedorderDatagrid").datagrid('reload');
							}
						});
				});
			}
		}});
		$("#uneffectedordertoolbar #memberName").text("会员姓名："+memberName);
		
		//初始化 --> 当前订单 --> 定义当前订单列表
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
				{field:'createDateStr',title:'日期',width:150,align:'center',sortable:true},
				{field:'totalamount',title:'金额',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				{field:'vantages',title:'赠送积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vantagesCost',title:'消费积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'billsno',title:'单号',width:150,align:'center',sortable:true},
				{field:'payTypeStr',title:'支付方式',width:100,align:'center',sortable:true},
				{field:'recid',title:'操作',width:50,align:'center',
					formatter:function(value){
						return "<a href='#' class='operateChannel' onClick=effectedOrderAction.effectOrder('"+value+"')>确认收款</a>";
					}
				}
			]],
			onLoadSuccess:function(data){
				$("#uneffectedordertoolbar #count").text("订单数量："+data.total.toFixed(0));
				$("#uneffectedordertoolbar #amount").text("订单金额："+data.footer[0].totalAmount.toFixed(2));
			}
		});
	});
	
	
	
</script>
	</body>
</html>