<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>会员活动</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<!-- begin of 活动会员列表 -->	
<div id="activeMembertoolbar">
	<div style="float:right;margin-right:5px;">
		<span>
			活动日期：
			<input id="activeMemberBeginDate" type="text" class="easyui-datebox" />
			 - 
			<input id="activeMemberEndDate" type="text" class="easyui-datebox" />
		</span>
		<span> <input type="text"
				name="activeMemberSearchWord" value=""
				style="width: 130px; height: 22px;line-height: 22px;border:1px solid #CCC;" />
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" onclick="activeMemberAction.searchMemebers()">搜索</a>
			<!-- a href="javascript:void(0)" class="easyui-linkbutton" id="activeMemberExportBtn"
			iconCls="icon-undo" onclick="activeMemberAction.exportMember()">导出</a -->
		</span>
	</div>
</div>
<table id="activeMemberDatagrid" toolbar="#activeMembertoolbar">
</table>
<!-- end of 活动会员列表 -->	
<script type="text/javascript">
//初始化
$(function(){
	//初始化 -> 活动会员列表
	$('#activeMemberDatagrid').datagrid({fit:true,
		border:false,
		idField:'recid',
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		remoteSort:false,
		pagination:true,
		showFooter:true,
		url:'<%=mainWeb%>/admin/member/getActiveMembers',
		columns:[[
			{field:'code',title:'编号',width:50,align:'center',sortable:true},
			{field:'username',title:'用户名',width:50,align:'center',sortable:true},
			{field:'membername',title:'姓名',width:50,align:'center',sortable:true},
			{field:'mobile',title:'手机号码',width:50,align:'center',sortable:true},
			{field:'stationname',title:'站点',width:50,align:'center',sortable:true},
			{field:'ordercount',title:'订单数量',width:50,align:'center',sortable:true},
			{field:'ordermoney',title:'订单金额',width:50,align:'center',sortable:true},
			{field:'returncount',title:'退货数量',width:50,align:'center',sortable:true},
			{field:'returnmoney',title:'退货金额',width:50,align:'center',sortable:true}
		]]
	});
	//初始化 -> 监听事件 -> 活动时间
	$('#activeMemberBeginDate').datebox({
		onSelect : function(date) {
			activeMemberAction.refresh();
		}
	});
	$('#activeMemberEndDate').datebox({
		onSelect : function(date) {
			activeMemberAction.refresh();
		}
	});
});
//活动会员
var activeMemberAction;
$(function(){
	activeMemberAction = new ActiveMemberAction();
	function ActiveMemberAction(){
		//活动会员 -> 搜索
		this.searchMemebers = function(){
			activeMemberAction.refresh();
		}
		//活动会员 -> 刷新
		this.refresh = function(){
			$("#activeMemberDatagrid").datagrid('load',{
				searchWord:$("input[name='activeMemberSearchWord']").val(),
				beginDate:$("#activeMemberBeginDate").datebox('getValue'),
				endDate:$("#activeMemberEndDate").datebox('getValue')
			});
		}
		//活动会员 -> 导出
		this.exportMember = function(){
			$('#activeMemberExportBtn').linkbutton({text:'导出中...'}).linkbutton('disable');
			$.post(mainWeb+'/admin/member/exportActiveMember',
				{
					searchWord:$("input[name='activeMemberSearchWord']").val(),
					beginDate:$("#activeMemberBeginDate").datebox('getValue'),
					endDate:$("#activeMemberEndDate").datebox('getValue')
				},
				function(result){
					alert(result);
					$.messager.alert('提示',result.message,'info');
					$('#activeMemberExportBtn').linkbutton({text:'导出'}).linkbutton('enable');
			},'json');
		}
	}
});
</script>
</body>
</html>

