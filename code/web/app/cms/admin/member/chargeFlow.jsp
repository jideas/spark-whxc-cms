<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ֵ��ˮ</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
<body>
<!-- begin of ��ֵ��ˮ�б� -->	
<div id="chargeFlowtoolbar">
	<div style="float:left;margin-left:5px;margin-top:2px;">
		<span>��ֵ��<input id="chargeFlowValueType" name="chargeFlowValueType"/></span>
		<span>��ֵ���ͣ�<input id="chargeFlowChargeType" name="chargeFlowChargeType" value="00"/></span>
	</div>
	<div style="float:right;margin-right:5px;">
		<span>
			��ֵ���ڣ�
			<input id="chargeFlowBeginDate" type="text" class="easyui-datebox" />
			 - 
			<input id="chargeFlowEndDate" type="text" class="easyui-datebox" />
		</span>
		<span> <input type="text"
				name="chargeFlowSearchWord" value=""
				style="width: 130px; height: 22px;line-height: 22px;border:1px solid #CCC;" />
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-search" onclick="chargeFlowAction.search()">����</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="chargeFlowExportBtn"
			iconCls="icon-undo" onclick="chargeFlowAction.exportChargeFlow()">����</a>
		</span>
	</div>
</div>
<table id="chargeFlowDatagrid" toolbar="#chargeFlowtoolbar">
</table>
<!-- end of ��ֵ��ˮ�б� -->	
<script type="text/javascript">
//��ʼ��
$(function(){
	//��ʼ�� -> ��ֵ��ˮ�б�
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
			{field:'code',title:'���',width:40,align:'center',sortable:true},
			{field:'username',title:'�û���',width:50,align:'center',sortable:true},
			{field:'membername',title:'����',width:40,align:'center',sortable:true},
			{field:'mobile',title:'�ֻ�����',width:35,align:'center',sortable:true},
			{field:'chargetype',title:'��ֵ����',width:30,align:'center',sortable:true,
				formatter:function(value,rec){
					if("01" == value) return "��ֵ��";
					else if("02" == value) return "����";
				}
			},
			{field:'orderno',title:'����/����',width:45,align:'center',sortable:true},
			{field:'amount',title:'���',width:50,align:'center',sortable:true},
			{field:'occurdate',title:'��ֵʱ��',width:65,align:'center',sortable:true}
		]]
	});
	//��ʼ�� -> �����¼� -> ��ֵ��ˮʱ��
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
	// ��ʼ�� -> ��ʼ����ֵ������
	$('input#chargeFlowValueType').combobox({
		url : mainWeb+'/admin/card/getAmountSelectList',
		valueField : 'cardvalue',
		textField : 'title',
		editable:false,
		onSelect:function(record){
			chargeFlowAction.refresh();
		}			
	});
	// ��ʼ�� -> ��ʼ����ֵ����
	$('input#chargeFlowChargeType').combobox({
		valueField : 'chargevalue',
		textField : 'title',
		data:[{
			chargevalue:'00',
			title:'ȫ��'
		},{
			chargevalue:'01',
			title:'��ֵ��'
		},{
			chargevalue:'02',
			title:'����'
		}],
		editable:false,
		onSelect:function(record){
			chargeFlowAction.refresh();
		}			
	});
	
});
//��ֵ��ˮ
var chargeFlowAction;
$(function(){
	chargeFlowAction = new ChargeFlowAction();
	function ChargeFlowAction(){
		//��ֵ��ˮ -> ����
		this.search = function(){
			chargeFlowAction.refresh();
		}
		//��ֵ��ˮ -> ˢ��
		this.refresh = function(){
			$("#chargeFlowDatagrid").datagrid('load',{
				searchWord:$("input[name='chargeFlowSearchWord']").val(),
				beginDate:$("#chargeFlowBeginDate").datebox('getValue'),
				endDate:$("#chargeFlowEndDate").datebox('getValue'),
				valueType:$('#chargeFlowValueType').combobox('getValue'),
				chargeType:$('#chargeFlowChargeType').combobox('getValue')
			});
		}
		//��ֵ��ˮ -> ����
		this.exportChargeFlow = function(){
			var valueType = $("#chargeFlowValueType").combobox("getValue");
			var chargeType = $("#chargeFlowChargeType").combobox("getValue");
			var searchWord = $("input[name='chargeFlowSearchWord']").val();
			var beginDate = $("#chargeFlowBeginDate").datebox('getValue');
			var endDate = $("#chargeFlowEndDate").datebox('getValue');
			searchWord = encodeURI(searchWord);
			var url = "<%=mainWeb%>/admin/member/exportChargeFlow?searchWord="+searchWord+"&beginDate="+beginDate+"&endDate="+endDate+"&valueType="+valueType+"&chargeType="+chargeType;
			$('#chargeFlowExportBtn').attr("href",url);
		}
	}
});
</script>
</body>
</html>

