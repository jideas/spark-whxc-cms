<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ֵ����</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	</head>
	<body>
		<!-- begin of ��ֵ�б� -->
		<div id="valueManagerToolbar" style="text-align: right;padding-right:35px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					onclick="valueManagerAction.addValue()">�� ��</a> 
		</div>
		<table id="valueManagerDatagrid">
		</table>
		<!-- end of ��ֵ�б� -->
		
		<!-- ��ֵ�༭�Ի��� -->
	<div id="valueManagerDialog" class="easyui-dialog" 
		data-options="modal:true,closable:true,maximizable:false"
		style="width: 250px; height: 140px; padding: 20px 10px 0px;"
		title="��ֵ" buttons="#valueManager-dlg-buttons" closed="true">
		<form id="valueManagerForm" method="post">
			��ֵ��<input id="cardvalue" class="easyui-numberspinner" style="width:150px;" required="required" data-options="min:1,precision:2,editable:true,invalidMessage:'��ֵ��Ч',missingMessage:'��ֵδ��д'">  
		</form>
	</div>
	<div id="valueManager-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="valueManagerAction.saveOrUpdateValue()">ȷ ��</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#valueManagerDialog').dialog('close')">ȡ ��</a>
	</div>
	<!-- ��ֵ�༭�Ի��� -->
	
<script type="text/javascript">
$(function(){
	//��ʼ�� -> ��ֵ�б�
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
			{field : 'cardvalue',title : '��ֵ',width : 100,align : 'center',sortable : true},
			{field : 'creator',title : '������',width : 100,align : 'center',sortable : true},
			{field : 'createdate',title : '����ʱ��',width : 150,align : 'center',sortable : true},
			{field : 'recid',title : '����',width : 150,align : 'center',
				formatter:function(value,rec){
					var update_ = "<a href='#' class='operateChannel' onClick=valueManagerAction.updateValue('"+rec.recid+"','"+rec.cardvalue+"')>�޸�</a>";
					var delete_ = "<a href='#' class='operateChannel' onClick=valueManagerAction.deleteValue('"+ value +"')>ɾ��</a>";
					return update_ +��"&nbsp;&nbsp;&nbsp;&nbsp;" + delete_;
				}
			}
		]]
	});
	
	//		
});

var valueManagerAction;
$(function(){
	//����
	valueManagerAction = new ValueManagerAction();
	function ValueManagerAction(){
		this.url = "";
		//��ֵ -> ����
		this.addValue = function(){
			this.url = mainWeb+'/admin/card/addValue?recid=&cardvalue=';
			$("#valueManagerDialog").dialog({'title':'������ֵ'}).dialog("open");
			$('#cardvalue').numberspinner('setValue', '');
		}
		
		//��ֵ -> �޸�
		this.updateValue = function(recid,cardvalue){
			this.url = mainWeb+'/admin/card/updateValue?recid='+recid+'&cardvalue=';
			$("#valueManagerDialog").dialog({'title':'�޸���ֵ'}).dialog("open");
			$('#cardvalue').numberspinner('setValue',cardvalue);
		}
		
		//��ֵ -> ɾ��
		this.deleteValue = function(recid){
			$.messager.confirm('��ʾ', '�Ƿ�ɾ��?', function(r) {
				if (r) {
					$.post(mainWeb+'/admin/card/deleteValue', {
						recid : recid
					}, function(result) {
						valueManagerAction.refreshValue();// ˢ����ֵ�б���Ϣ
					}, 'json');
				}
			});
		}
		
		//��ֵ -> ˢ��
		this.refreshValue = function(){
			$('#valueManagerDatagrid').datagrid("reload");
		}
		
		//��ֵ -> ִ�б�����޸�
		this.saveOrUpdateValue = function(){
			var cardvalue = $('#cardvalue').numberspinner('getValue');
			$('#valueManagerForm').form('submit', {
				url : this.url+cardvalue,
				onSubmit : function() {
					return $('#cardvalue').numberspinner('isValid');// ��֤��
				},
				success : function(data) {
					data = eval("("+data+")");
					$.messager.alert('��ʾ',data.message, 'info');// ����ɹ�������ʾ��Ϣ
					$("#valueManagerDialog").dialog('close'); // �ر���ֵ���Ի���
					valueManagerAction.refreshValue();
				}
			});
		}
		
	}
});
</script>
	</body>
</html>