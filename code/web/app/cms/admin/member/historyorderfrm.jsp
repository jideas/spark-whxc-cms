<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>历史订单</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--begin of 历史订单列表-->
		<div id="historyordertoolbar" style="height:32px">
		<div style="display:inline;float:left;padding-top: 9px;">
			<span id="memberName">会员姓名：</span>
			<span id="count">订单数量：</span>
			<span id="amount">订单金额：</span>
			</div>
			<div style="display:inline;float:right;padding-top: 3px;">
			<span>开始时间：<input id="beginDate" type="text" class="easyui-datebox" /></span>
				<span>结束时间：<input id="endDate" type="text" class="easyui-datebox" /></span>
				<!-- <span>类别：<input id="type" name="type"></span> -->
				<span><input style='cursor: pointer;' type="button" value="确定" onclick="historyAction.queryAction()"></span>
			</div>		
			</div>
		<table id="historyorderDatagrid" toolbar="#historyordertoolbar">
		</table>
		<!--end of 历史订单列表 -->
		
		<script type="text/javascript">
	//初始化 
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
		$("#historyordertoolbar #memberName").text("会员姓名："+memberName);
		//初始化 --> 当前订单 --> 定义当前订单列表
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
				{field:'stationname',title:'站点',width:100,align:'center',sortable:true},
				{field:'createDateStr',title:'日期',width:150,align:'center',sortable:true},
				{field:'totalamount',title:'订单金额',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				{field:'vantages',title:'赠送积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vantagesCost',title:'消费积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'billsno',title:'单号',width:150,align:'center',sortable:true},
				{field:'payTypeStr',title:'支付方式',width:100,align:'center',sortable:true},
				//{field:'code6',title:'状态',width:200,align:'center',sortable:true,formatter:function(value,rec){return "成功";}}
			]],
			onLoadSuccess:function(data){
				$("#historyordertoolbar #count").text("订单数量："+data.total.toFixed(0));
				$("#historyordertoolbar #amount").text("订单金额："+data.footer[0].totalAmount.toFixed(2));
			}
		});
	});
	
</script>
	</body>
</html>