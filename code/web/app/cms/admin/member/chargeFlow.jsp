<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>充值流水</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<!-- begin of 充值流水列表 -->	
<div id="chargeFlowtoolbar">
	<div style="float:left;margin-left:5px;margin-top:2px;">
		<span>面值：<input id="chargeFlowValueType" name="chargeFlowValueType"/></span>
		<span>充值类型：<input id="chargeFlowChargeType" name="chargeFlowChargeType" value="00"/></span>
	</div>
	<div style="float:right;margin-right:5px;">
		<span>
			充值日期：
			<input id="chargeFlowBeginDate" type="text" class="easyui-datebox" />
			 - 
			<input id="chargeFlowEndDate" type="text" class="easyui-datebox" />
		</span>
		<span> <input type="text"
				name="chargeFlowSearchWord" value=""
				style="width: 130px; height: 22px;line-height: 22px;border:1px solid #CCC;" />
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" onclick="chargeFlowAction.search()">搜索</a>
		</span>
	</div>
</div>
<table id="chargeFlowDatagrid" toolbar="#chargeFlowtoolbar">
</table>
<!-- end of 活动会员列表 -->	
<script type="text/javascript">
//初始化
$(function(){
	//初始化 -> 活动会员列表
	$('#chargeFlowDatagrid').datagrid({
		fit:true,
		border:false,
		idField:'recid',
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		remoteSort:false,
		pagination:true,
		showFooter:true,
		url:'<%=mainWeb%>/admin/member/getMemberChargeFlows',
		columns:[[
			{field:'code',title:'编号',width:40,align:'center',sortable:true},
			{field:'username',title:'用户名',width:50,align:'center',sortable:true},
			{field:'membername',title:'姓名',width:40,align:'center',sortable:true},
			{field:'mobile',title:'手机号码',width:35,align:'center',sortable:true},
			{field:'chargetype',title:'充值类型',width:30,align:'center',sortable:true,
				formatter:function(value,rec){
					if("01" == value) return "面值卡";
					else if("02" == value) return "网银";
				}
			},
			{field:'orderno',title:'卡号/单号',width:45,align:'center',sortable:true},
			{field:'amount',title:'金额',width:50,align:'center',sortable:true},
			{field:'occurdate',title:'充值时间',width:65,align:'center',sortable:true}
		]]
	});
	//初始化 -> 监听事件 -> 活动时间
	$('#chargeFlowBeginDate').datebox({
		onSelect : function(date) {
			chargeFlowAction.refresh();
		}
	});
	$('#chargeFlowEndDate').datebox({
		onSelect : function(date) {
			chargeFlowAction.refresh();
		}
	});
	// 初始化 -> 初始化面值卡类型
	$('input#chargeFlowValueType').combobox({
		url : mainWeb+'/admin/card/getAmountSelectList',
		valueField : 'cardvalue',
		textField : 'title',
		editable:false,
		onSelect:function(record){
			chargeFlowAction.refresh();
		}			
	});
	// 初始化 -> 初始化充值类型
	$('input#chargeFlowChargeType').combobox({
		valueField : 'chargevalue',
		textField : 'title',
		data:[{
			chargevalue:'00',
			title:'全部'
		},{
			chargevalue:'01',
			title:'面值卡'
		},{
			chargevalue:'02',
			title:'网银'
		}],
		editable:false,
		onSelect:function(record){
			chargeFlowAction.refresh();
		}			
	});
	
});
//活动会员
var chargeFlowAction;
$(function(){
	chargeFlowAction = new ChargeFlowAction();
	function ChargeFlowAction(){
		//活动会员 -> 搜索
		this.search = function(){
			chargeFlowAction.refresh();
		}
		//活动会员 -> 刷新
		this.refresh = function(){
			$("#chargeFlowDatagrid").datagrid('load',{
				searchWord:$("input[name='chargeFlowSearchWord']").val(),
				beginDate:$("#chargeFlowBeginDate").datebox('getValue'),
				endDate:$("#chargeFlowEndDate").datebox('getValue'),
				valueType:$('#chargeFlowValueType').combobox('getValue'),
				chargeType:$('#chargeFlowChargeType').combobox('getValue')
			});
		}
	}
});
</script>
</body>
</html>

