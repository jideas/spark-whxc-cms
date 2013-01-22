<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>余额记录</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--充值记录列表开始 -->
		<div id="chargelogtoolbar" style="height:32px;">
			<div style="display:inline;float:left;padding-top: 9px;">
				<span id="memberName" style="">会员姓名：</span>
				<span id="amount">当前余额：</span>
			</div>
			<div style="display:inline;float:right;padding-top: 3px;">
				<span>开始时间：<input id="chargelogBeginDate" type="text" class="easyui-datebox" /></span>
				<span>结束时间：<input id="chargelogEndDate" type="text" class="easyui-datebox" /></span>
				<!-- <span>类别：<input id="type" name="type"></span> -->
				<span><input style='cursor: pointer;' type="button" value="确定" onclick="chargeAction.queryAction()"></span>
			</div>
		</div>
		<table id="chargelogDatagrid" toolbar="#chargelogtoolbar">
		</table>
		<!-- 充值记录列表结束 -->

		<script type="text/javascript">
	//初始化 
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
	$("#memberName").text("会员姓名："+memberName);
		//初始化 --> 充值记录 --> 定义充值记录列表列
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
				{field:'occurdateStr',title:'日期',width:100,align:'center',sortable:true},
				{field:'amount',title:'金额',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(2);}},
				//{field:'code2',title:'赠送积分',width:100,align:'center',sortable:true},
				{field:'dealtypeStr',title:'类型',width:100,align:'center',sortable:true},
				{field:'relabillsno',title:'单号',width:100,align:'center',sortable:true},
				//{field:'code5',title:'结果',width:200,align:'center', formatter:function(value,rec){return "成功";}}
			]],
			onLoadSuccess:function(data){
				$("#chargelogtoolbar #amount").text("当前余额："+data.footer[0].moneybalance.toFixed(2));
				
			}
		});
		
	});
	
</script>
	</body>
</html>