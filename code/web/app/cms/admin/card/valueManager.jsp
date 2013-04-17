<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>面值管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	</head>
	<body>
		<!-- begin of 面值列表 -->
		<div id="valueManagerToolbar" style="text-align: right;padding-right:35px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					onclick="valueManagerAction.addValue()">新 增</a> 
		</div>
		<table id="valueManagerDatagrid">
		</table>
		<!-- end of 面值列表 -->
		
		<!-- 面值编辑对话框 -->
	<div id="valueManagerDialog" class="easyui-dialog" 
		data-options="modal:true,closable:true,maximizable:false"
		style="width: 250px; height: 140px; padding: 20px 10px 0px;"
		title="面值" buttons="#valueManager-dlg-buttons" closed="true">
		<form id="valueManagerForm" method="post">
			面值：<input id="cardvalue" class="easyui-numberspinner" style="width:150px;" required="required" data-options="min:1,precision:2,editable:true,invalidMessage:'面值无效',missingMessage:'面值未填写'">  
		</form>
	</div>
	<div id="valueManager-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="valueManagerAction.saveOrUpdateValue()">确 定</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#valueManagerDialog').dialog('close')">取 消</a>
	</div>
	<!-- 面值编辑对话框 -->
	
<script type="text/javascript">
$(function(){
	//初始化 -> 面值列表
	$('#valueManagerDatagrid').datagrid({
		fit : true,
		border : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		singleSelect : true,
		toolbar:'#valueManagerToolbar',
		url : mainWeb+'/admin/card/getAmountList',
		columns : [[
			{field : 'cardvalue',title : '面值',width : 100,align : 'center',sortable : true},
			{field : 'creator',title : '创建人',width : 100,align : 'center',sortable : true},
			{field : 'createdate',title : '创建时间',width : 150,align : 'center',sortable : true},
			{field : 'recid',title : '操作',width : 150,align : 'center',
				formatter:function(value,rec){
					var update_ = "<a href='#' class='operateChannel' onClick=valueManagerAction.updateValue('"+rec.recid+"','"+rec.cardvalue+"')>修改</a>";
					var delete_ = "<a href='#' class='operateChannel' onClick=valueManagerAction.deleteValue('"+ value +"')>删除</a>";
					return update_ +　"&nbsp;&nbsp;&nbsp;&nbsp;" + delete_;
				}
			}
		]]
	});
	
	//		
});

var valueManagerAction;
$(function(){
	//定义
	valueManagerAction = new ValueManagerAction();
	function ValueManagerAction(){
		this.url = "";
		//面值 -> 新增
		this.addValue = function(){
			this.url = mainWeb+'/admin/card/addValue?recid=&cardvalue=';
			$("#valueManagerDialog").dialog({'title':'新增面值'}).dialog("open");
			$('#cardvalue').numberspinner('setValue', '');
		}
		
		//面值 -> 修改
		this.updateValue = function(recid,cardvalue){
			this.url = mainWeb+'/admin/card/updateValue?recid='+recid+'&cardvalue=';
			$("#valueManagerDialog").dialog({'title':'修改面值'}).dialog("open");
			$('#cardvalue').numberspinner('setValue',cardvalue);
		}
		
		//面值 -> 删除
		this.deleteValue = function(recid){
			$.messager.confirm('提示', '是否删除?', function(r) {
				if (r) {
					$.post(mainWeb+'/admin/card/deleteValue', {
						recid : recid
					}, function(result) {
						valueManagerAction.refreshValue();// 刷新面值列表信息
					}, 'json');
				}
			});
		}
		
		//面值 -> 刷新
		this.refreshValue = function(){
			$('#valueManagerDatagrid').datagrid("reload");
		}
		
		//面值 -> 执行保存或修改
		this.saveOrUpdateValue = function(){
			var cardvalue = $('#cardvalue').numberspinner('getValue');
			$('#valueManagerForm').form('submit', {
				url : this.url+cardvalue,
				onSubmit : function() {
					return $('#cardvalue').numberspinner('isValid');// 验证表单
				},
				success : function(data) {
					data = eval("("+data+")");
					$.messager.alert('提示',data.message, 'info');// 保存成功给出提示信息
					$("#valueManagerDialog").dialog('close'); // 关闭面值卡对话框
					valueManagerAction.refreshValue();
				}
			});
		}
		
	}
});
</script>
	</body>
</html>