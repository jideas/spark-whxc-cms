<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�Ѵ�ӡ��ֵ��</title>
	</head>
	<body>
		<!-- begin of ��ֵ���б� -->
		<div id="printedCardToolbar">
			<span style="float: left; padding-right: 5px;">��ֵ�� <input
					id="selectAmountList" name="selectAmountList"> ��ʼ���ڣ� <input
					id="beginDate1" type="text" class="easyui-datebox" /> �������ڣ� <input
					id="endDate1" type="text" class="easyui-datebox" />
			<input type="text" name="printedCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="printedCardAction.refreshCards()">���Ų�ѯ</a>		
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="printedCardAction.activeCard()">�� ��</a> </span>
		</div>
		<table id="printedCardDatagrid" toolbar="#printedCardToolbar">
		</table>
		<!-- end of ��ֵ���б� -->
		<script type="text/javascript">
// ��ʼ��
$(function() {
			// ��ʼ�� --> ��ֵ�� --> ������ֵ���б���
			$('#printedCardDatagrid').datagrid({
				fit : true,
				border : false,
				idField : 'recid',
				rownumbers : true,
				fitColumns : true,
				remoteSort : false,
				pagination : true, 
				url : mainWeb+'/admin/card/getPrintedList',
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
							field : 'lastDate',
							title : '��Ч����',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'status',
							title : '״̬',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'recid',
							title : '����',
							width : 100,
							align : 'center',
							formatter : function(value, rec) {
								return "<a href='#' class='operateChannel' onClick=printedCardAction.deleteCard('"
										+ value + "')>ɾ��</a>";
							}
						}]]
			});

			// ��ʼ�� -> ��ʼ����ֵ������
			$('input#selectAmountList').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'code',
						textField : 'title',
						editable:false
					});

			// �����¼� -> ��ֵ�����͸ı�
			$('#selectAmountList').combobox({
						onSelect : function(record) {
							// ˢ����ֵ��
							printedCardAction.refreshCards();
						}
					});
			$('#selectAmountList').combobox('setValue','0');

			// �����¼� -> ��ʼʱ��ı�
			$('#beginDate1').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							printedCardAction.refreshCards();
						}
					});

			// �����¼� -> ����ʱ��ı�
			$('#endDate1').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							printedCardAction.refreshCards();
						}
					});

		});

// ��ֵ��
var printedCardAction;
$(function() {
			var url = '';
			// ��ֵ�� -> ��ʼ��
			printedCardAction = new AddCardAction();

			// ��ֵ�� -> ������ֵ��
			function AddCardAction() {
				// ��ֵ�� -> ˢ��
				this.refreshCards = function() {
					var cardType = $('#selectAmountList').combobox('getValue');
					var beginDate1 = $('#beginDate1').datebox('getValue');
					var endDate1 = $('#endDate1').datebox('getValue');
					var cardNO = $.trim($("input[name='printedCard_searchcardno']").val());
					$('#printedCardDatagrid').datagrid('clearSelections');
					$('#printedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate1,
								endDate : endDate1,
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
												printedCardAction
														.refreshCards();// ˢ����ֵ���б���Ϣ
											}, 'json');
								}
							});
				}

				// ��ֵ�� -> �����ֵ������
				function fillCardType() {
					$('#printedCardForm #printedCardvaluetype').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'code',
								textField : 'title',
								editable:false
							});
				}

				// ��ֵ�� -> ӡ��
				this.activeCard = function() {
					var ids = getSelectedIDS("#printedCardDatagrid"); // ��ȡ���������ID
					if (ids == '') {
						$.messager.alert('��ʾ', '��ѡ��Ҫ�������õ���ֵ��!', 'warning');
					} else {
						$.post(mainWeb+'/admin/card/batchActive', {
									'ids[]' : ids
								}, function() {
									printedCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
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