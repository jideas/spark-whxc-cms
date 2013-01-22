<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>当前订单</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--begin of 当前订单列表-->
		<div id="currentordertoolbar" style="height:32px;">
			<div style="padding-top:9px;">
				<span id="memberName">会员姓名：</span>
				<span id="count">订单数量：</span>
				<span id="amount">订单金额：</span>
			</div>
		</div>
		<table id="currentorderDatagrid" toolbar="#currentordertoolbar">
		</table>
		<!--end of 当前订单列表 -->
		
		<script type="text/javascript">
	//初始化 
	$(function(){
		
		$("#currentordertoolbar #memberName").text("会员姓名："+memberName);
		
		//初始化 --> 当前订单 --> 定义当前订单列表
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
				{field:'createDateStr',title:'日期',width:150,align:'center',sortable:true},
				{field:'totalamount',title:'金额',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				{field:'vantages',title:'赠送积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vantagesCost',title:'消费积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'billsno',title:'单号',width:150,align:'center',sortable:true},
				{field:'payTypeStr',title:'支付方式',width:100,align:'center',sortable:true},
				{field:'statusStr',title:'状态',width:100,align:'center',sortable:true}
			]],
			onLoadSuccess:function(data){
				$("#currentordertoolbar #count").text("订单数量："+data.total.toFixed(0));
				$("#currentordertoolbar #amount").text("订单金额："+data.footer[0].totalAmount.toFixed(2));
			}
		});
	});
	
</script>
	</body>
</html>