<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN">
<html>
	<head>
		<title>��ʹ����ֵ��</title>
	</head> 
	<body>
		<!-- begin of ��ֵ���б� -->
		<div id="usedCardToolbar">
			��ֵ��
			<input id="selectAmountList5" name="selectAmountList5">
			��ʼ���ڣ�
			<input id="beginDate5" type="text" class="easyui-datebox" />
			�������ڣ�
			<input id="endDate5" type="text" class="easyui-datebox" />
		</div>
		<table id="usedCardDatagrid" toolbar="#usedCardToolbar">
		</table>
		<!-- end of ��ֵ���б� -->
		<script type="text/javascript" >
		// ��ʼ��
$(function() {
			// ��ʼ�� --> ��ֵ�� --> ������ֵ���б���
			$('#usedCardDatagrid').datagrid({
						fit : true,
						border : false,
						idField : 'recid',
						rownumbers : true,
						fitColumns : true,
						remoteSort : false,
						pagination : true,singleSelect: true,
						url : mainWeb+'/admin/card/getUsedList',
						columns : [[{
									field : 'checkbox',
									checkbox : 'true'
								}, {
									field : 'ordinal',
									title : '˳���',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'cardNo',
									title : '����',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'amount',
									title : '��ֵ',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'createDate',
									title : '��������',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'creator',
									title : '������',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'useddate',
									title : 'ʹ������',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'usedperson',
									title : '��Ա��',
									width : 100,
									align : 'center',
									sortable : true
								}]]
					});

			// ��ʼ�� -> ��ʼ����ֵ������
			$('input#selectAmountList5').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'code',
						textField : 'title',
						editable:false
					});

			// �����¼� -> ��ֵ�����͸ı�
			$('#selectAmountList5').combobox({
						onSelect : function(record) {
							// ˢ����ֵ��
							usedCardAction.refreshCards();
						}
					});
			$('#selectAmountList5').combobox('setValue','0');
			// �����¼� -> ��ʼʱ��ı�
			$('#beginDate5').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							usedCardAction.refreshCards();
						}
					});

			// �����¼� -> ����ʱ��ı�
			$('#endDate5').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							usedCardAction.refreshCards();
						}
					});

		});

// ��ֵ��
var usedCardAction;
$(function() {
			var url = '';
			// ��ֵ�� -> ��ʼ��
			usedCardAction = new ReActiveCardAction();

			// ��ֵ�� -> ������ֵ��
			function ReActiveCardAction() {
				// ��ֵ�� -> ˢ��
				this.refreshCards = function() {
					var cardType = $('#selectAmountList5').combobox('getValue');
					var beginDate5 = $('#beginDate5').datebox('getValue');
					var endDate5 = $('#endDate5').datebox('getValue');
					$('#usedCardDatagrid').datagrid('clearSelections');
					$('#usedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate5,
								endDate : endDate5
							}); // ˢ����ֵ���б���Ϣ
				}
			}
		});
		
		</script>
	</body>
</html>