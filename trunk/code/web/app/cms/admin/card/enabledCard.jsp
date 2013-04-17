<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>���ַ���ֵ��</title>
	</head> 
	<body>
		<!-- begin of ��ֵ���б� -->
		<div id="activedCardToolbar">
			<span style="float: left; padding-right: 5px;">��ֵ��
			<input id="selectAmountList2" name="selectAmountList2">
			��ʼ���ڣ�
			<input id="beginDate2" type="text" class="easyui-datebox" />
			�������ڣ�
			<input id="endDate2" type="text" class="easyui-datebox" />
			<input type="text" name="enabledCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="activedCardAction.refreshCards()">���Ų�ѯ</a>
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="activedCardAction.distributeCard()">�� ��</a> </span>
		</div>
		<table id="activedCardDatagrid" toolbar="#activedCardToolbar">
		</table>
		<!-- end of ��ֵ���б� -->
		<!-- begin of ���� -->
		<div id="distributeCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 500px; height: 180px; padding: 20px 10px 0px;"
			title="��ֵ��" buttons="#distributeCard-dlg-buttons" closed="true">
			<form id="distributeCardForm" method="post">
				<table>
					<tr>
						<td>
							վ�㣺
						</td>
						<td>
							<input id="selectStationList" name="stationId"
								style="width: 400px;" readonly="readonly" />
								<input id="hiddenids" name="ids[]" value="" type="hidden"/>
						</td>
					</tr>
					<tr>
						<td>
							�����ˣ�
						</td>
						<td>
							<input id="personNameTextId" type="text" name="personName"
								style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="distributeCard-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="activedCardAction.cardDistribute()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#distributeCardDialog').dialog('close')">ȡ
				��</a>
		</div>
		<script type="text/javascript">
		// ��ʼ��
$(function() {
			// ��ʼ�� --> ��ֵ�� --> ������ֵ���б���
			$('#activedCardDatagrid').datagrid({
				fit : true,
				border : false,
				idField : 'recid',
				rownumbers : true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : mainWeb+'/admin/card/getActivedList',
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
							field : 'activedate',
							title : '��������',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'activeperson',
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
								return "<a href='#' class='operateChannel' onClick=activedCardAction.cancelCard('"
										+ value + "')>ͣ ��</a>";
							}
						}]]
			});

			// ��ʼ�� -> ��ʼ����ֵ������
			$('input#selectAmountList2').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'cardvalue',
						textField : 'title',
						editable:false
					});

			// �����¼� -> ��ֵ�����͸ı�
			$('#selectAmountList2').combobox({
						onSelect : function(record) {
							// ˢ����ֵ��
							activedCardAction.refreshCards();
						}
					});
			$('#selectAmountList2').combobox('setValue','ȫ��');

			// �����¼� -> ��ʼʱ��ı�
			$('#beginDate2').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							activedCardAction.refreshCards();
						}
					});

			// �����¼� -> ����ʱ��ı�
			$('#endDate2').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							activedCardAction.refreshCards();
						}
					});

		});

// ��ֵ��
var activedCardAction;
$(function() {
			var url = '';
			// ��ֵ�� -> ��ʼ��
			activedCardAction = new DistributeCardAction();

			// ��ֵ�� -> ������ֵ��
			function DistributeCardAction() {
				// ��ֵ�� -> ˢ��
				this.refreshCards = function() {
					var cardType = $('#selectAmountList2').combobox('getValue');
					var beginDate2 = $('#beginDate2').datebox('getValue');
					var endDate2 = $('#endDate2').datebox('getValue');
					var cardNO = $.trim($("input[name='enabledCard_searchcardno']").val());
					$('#activedCardDatagrid').datagrid('clearSelections');
					$('#activedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate2,
								endDate : endDate2,
								cardNO : cardNO
							}); // ˢ����ֵ���б���Ϣ
				}

				// ��ֵ�� -> ɾ��
				this.cancelCard = function(cardId) {
					$.messager.confirm('ͣ��', '�Ƿ�ȷ��ͣ��?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/card/cancelCard', {
												id : cardId
											}, function(result) {
												activedCardAction
														.refreshCards();// ˢ����ֵ���б���Ϣ
											}, 'json');
								}
							});
				}

				// ��ֵ�� -> �����ֵ������
				function fillCardType() {
					$('#activedCardForm #activedCardvaluetype').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'cardvalue',
								textField : 'title',
								editable : false
							});
				}

				// ��ֵ�� -> �ַ�
				this.distributeCard = function() {
					var ids = getSelectedIDS("#activedCardDatagrid"); // ��ȡ���������ID
					// $('#hideCardId').val(ids) ;
					fillStationList();
					if (ids == '') {
						$.messager.alert('��ʾ', '��ѡ��Ҫ�����ַ�����ֵ��!', 'warning');
					} else {
						// ���form
						$('#distributeCardForm').form('clear');
						// �򿪶Ի���
						$('#distributeCardDialog').dialog('open').dialog(
								'setTitle', '�ַ���ֵ��');
						url = mainWeb+'/admin/card/batchDistribute';
					}
				}

				function fillStationList() {
					$('#distributeCardForm #selectStationList').combobox({
								url : mainWeb+'/admin/card/getStationList',
								valueField : 'recid',
								textField : 'stationName',
								editable : false
							});
				}
				// ��ֵ�� -> �ַ�
				this.cardDistribute = function() {
					var ids = getSelectedIDS("#activedCardDatagrid"); // ��ȡ���������ID
					var stid = $('#selectStationList').combobox('getValue');
					var pname = $("#personNameTextId").val();
					$("#hiddenids").val(ids);
					$('#distributeCardForm').form('submit', {
								url : url,
								onSubmit : function() {
									return enabledCardValidateForm(); // ��֤��
								},
								success : function(data) {
									$.messager.alert('��ʾ', '��ֵ������ɹ�!', 'info');// ����ɹ�������ʾ��Ϣ
									$("#distributeCardDialog").dialog('close'); // �ر���ֵ���Ի���
									activedCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
								}
							});
				}

				// ��ֵ�� -> ��֤��
				function enabledCardValidateForm() {
					if ($.trim($('#selectStationList').combobox('getValue')) == '') {
						$.messager.alert('��ʾ', '��ѡ��վ��!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($
							.trim($("#distributeCardForm input[id='personNameTextId']")
									.val()) == '') {
						$.messager.alert('��ʾ', '����д������!', 'warning'); // ����δ��д
						return false;
					}
					return true;
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