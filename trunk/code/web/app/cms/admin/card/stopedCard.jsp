<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��������ֵ��</title>
	</head>
	<body>
		<!-- begin of ��ֵ���б� -->
		<div id="stopedCardToolbar">
			<span style="float: left; padding-right: 5px;">��ֵ�� <input
					id="selectAmountList4" name="selectAmountList4"> ��ʼ���ڣ� <input
					id="beginDate4" type="text" class="easyui-datebox" /> �������ڣ� <input
					id="endDate4" type="text" class="easyui-datebox" />
					<input type="text" name="stopedCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="stopedCardAction.refreshCards()">���Ų�ѯ</a>
			</span>
		</div>
		<table id="stopedCardDatagrid" toolbar="#stopedCardToolbar">
		</table>
		<!-- end of ��ֵ���б� -->
		<script type="text/javascript">
// ��ʼ��
$(function() {
			// ��ʼ�� --> ��ֵ�� --> ������ֵ���б���
			$('#stopedCardDatagrid').datagrid({
						fit : true,
						border : false,
						idField : 'recid',
						rownumbers : true,
						fitColumns : true,
						remoteSort : false,
						pagination : true,singleSelect: true,
						url : mainWeb+'/admin/card/getStopedList',
						columns : [[ {
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
									field : 'stopdate',
									title : 'ͣ������',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'usedperson',
									title : 'ͣ����',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'status',
									title : '״̬',
									width : 100,
									align : 'center',
									sortable : true
								}]]
					});

			// ��ʼ�� -> ��ʼ����ֵ������
			$('input#selectAmountList4').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'code',
						textField : 'title',
						editable:false
					});

			// �����¼� -> ��ֵ�����͸ı�
			$('#selectAmountList4').combobox({
						onSelect : function(record) {
							// ˢ����ֵ��
							stopedCardAction.refreshCards();
						}
					});
			$('#selectAmountList4').combobox('setValue','0');

			// �����¼� -> ��ʼʱ��ı�
			$('#beginDate4').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							stopedCardAction.refreshCards();
						}
					});

			// �����¼� -> ����ʱ��ı�
			$('#endDate4').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							stopedCardAction.refreshCards();
						}
					});

		});

// ��ֵ��
var stopedCardAction;
$(function() {
			var url = '';
			// ��ֵ�� -> ��ʼ��
			stopedCardAction = new ReActiveCardAction();

			// ��ֵ�� -> ������ֵ��
			function ReActiveCardAction() {
				// ��ֵ�� -> ˢ��
				this.refreshCards = function() {
					var cardType = $('#selectAmountList4').combobox('getValue');
					var beginDate4 = $('#beginDate4').datebox('getValue');
					var endDate4 = $('#endDate4').datebox('getValue');
						var cardNO = $.trim($("input[name='stopedCard_searchcardno']").val());
					$('#stopedCardDatagrid').datagrid('clearSelections');
					$('#stopedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate4,
								endDate : endDate4,
								cardNO : cardNO
							}); // ˢ����ֵ���б���Ϣ
				}

				// ��ֵ�� -> ɾ��
				this.deleteCard = function(cardId) {
					$.messager.confirm('ɾ��', '�Ƿ�ȷ��ɾ��?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/card/deleteCard', {
												id : cardId
											}, function(result) {
												stopedCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
											}, 'json');
								}
							});
				}

				// ��ֵ�� -> ӡ��
				this.activeCard = function() {
					var ids = getSelectedIDS("#stopedCardDatagrid"); // ��ȡ���������ID
					if (ids == '') {
						$.messager.alert('��ʾ', '��ѡ��Ҫ�������õ���ֵ��!', 'warning');
					} else {
						$.post(mainWeb+'/admin/card/batchReActive', {
									'ids[]' : ids
								}, function() {
									stopedCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
								});
					}
				}

				// ��ֵ�� -> �������� --> ��ȡѡ�е�Ids
				function getSelectedIDS(targetDatagrid) {
					var rows = $(targetDatagrid).datagrid('getSelections');
					var ids = '';
					if (rows) { 
						for (var i = 0; i < rows.length; i++) { 
							ids += rows[i].recid;
							if (i != rows.length - 1) {
								ids += ",";
							}
						}
					}
					return ids;
				}
			}
		});		
		</script>
	</body>
</html>