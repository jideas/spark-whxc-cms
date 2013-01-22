<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>积分查看</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!--积分列表开始 -->
		<div id="scoreviewtoolbar" style="height:32px;">
			<div style="display:inline;float:left;padding-top: 9px;">
				<span id="memberName">会员姓名：</span>
				<span id="vantages">当前积分：</span>
			</div>
			<div style="display:inline;float:right;padding-top: 3px;">
				<span>开始时间：<input id="scoreBeginDate" type="text" class="easyui-datebox" /></span>
				<span>结束时间：<input id="scoreEndDate" type="text" class="easyui-datebox" /></span>
				<!-- <span>类别：<input id="type" name="type"></span> -->
				<span><input style='cursor: pointer;' type="button" value="确定" onclick="scoreAction.queryAction()"></span>
			</div>
		</div>
		<table id="scoreviewDatagrid" toolbar="#scoreviewtoolbar">
		</table>
		<!-- 积分列表结束 -->
		
		<script type="text/javascript">
	//初始化 
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
		$("#scoreviewtoolbar #memberName").text("会员姓名："+memberName);
		//初始化 --> 积分 --> 定义积分列表
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
				{field:'occurdateStr',title:'日期',width:100,align:'center',sortable:true},
				
				//{field:'code2',title:'金额',width:100,align:'center',sortable:true},
				{field:'vantages',title:'积分',width:100,align:'center',sortable:true,formatter:function(value,rec){return value.toFixed(0);}},
				{field:'vtypeStr',title:'类型',width:100,align:'center',sortable:true},
				{field:'relabillsno',title:'单号',width:100,align:'center',sortable:true},
				//{field:'code5',title:'来源',width:100,align:'center',sortable:true,formatter:function(value,rec){return "面值卡";}}
			]],
			onLoadSuccess:function(data){
				$("#scoreviewtoolbar #vantages").text("当前积分："+data.footer[0].vantages.toFixed(0));
			}
		});
		});
</script>
	</body>
</html>