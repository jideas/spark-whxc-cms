<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�ѷ�����ֵ��</title>
	</head>
	<body>
		<!-- begin of ��ֵ���б� -->
		<div id="distributedCardToolbar">
		<span style="float: left; padding-right: 5px;">	��ֵ��
			<input id="selectAmountList3" name="selectAmountList3">
			��ʼ���ڣ�
			<input id="beginDate3" type="text" class="easyui-datebox" />
			�������ڣ�
			<input id="endDate3" type="text" class="easyui-datebox" />
			<input type="text" name="publishedCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="distributedCardAction.refreshCards()">���Ų�ѯ</a>
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="distributedCardAction.reDistributeCard()">���·ַ�</a> </span>
		</div>
		<table id="distributedCardDatagrid" toolbar="#distributedCardToolbar">
		</table>
		<!-- end of ��ֵ���б� -->
		<!-- begin of ���� -->
		<div id="reDistributeCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 500px; height: 180px; padding: 20px 10px 0px;"
			title="��ֵ��" buttons="#reDistributeCard-dlg-buttons" closed="true">
			<form id="reDistributeCardForm" method="post">
				<table>
					<tr>
						<td>
							վ�㣺
						</td>
						<td>
							<input id="selectStationList2" name="stationId"
								style="width: 400px;" readonly="readonly" />
							<input id="hiddenids2" name="ids[]" value="" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							�������ˣ�
						</td>
						<td>
							<input id="personNameTextId2" type="text" name="personName"
								style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="reDistributeCard-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="distributedCardAction.cardReDistribute()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#reDistributeCardDialog').dialog('close')">ȡ
				��</a>
		</div>
		<script type="text/javascript">
// ��ʼ��
$(function() {
	// ��ʼ�� --> ��ֵ�� --> ������ֵ���б���
	$('#distributedCardDatagrid').datagrid({
		fit : true,
		border : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		pagination : true,
		url : mainWeb+'/admin/card/getDistributedList',
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
					field : 'distributedate',
					title : '�ַ�����',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'responsibleperson',
					title : '������',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'distributeperson',
					title : '�ַ���',
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
						return "<a href='#' class='operateChannel' onClick=distributedCardAction.cancelCard('"
								+ value + "')>ͣ ��</a>";
					}
				}]]
	});

	// ��ʼ�� -> ��ʼ����ֵ������
	$('input#selectAmountList3').combobox({
				url : mainWeb+'/admin/card/getAmountSelectList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});

	// �����¼� -> ��ֵ�����͸ı�
	$('#selectAmountList3').combobox({
				onSelect : function(record) {
					// ˢ����ֵ��
					distributedCardAction.refreshCards();
				}
			});
			$('#selectAmountList3').combobox('setValue','0');

	// �����¼� -> ��ʼʱ��ı�
	$('#beginDate3').datebox({
				onSelect : function(date) {
					// ˢ����ֵ��
					distributedCardAction.refreshCards();
				}
			});

	// �����¼� -> ����ʱ��ı�
	$('#endDate3').datebox({
				onSelect : function(date) {
					// ˢ����ֵ��
					distributedCardAction.refreshCards();
				}
			});

});

// ��ֵ��
var distributedCardAction;
$(function() {
			var url = '';
			// ��ֵ�� -> ��ʼ��
			distributedCardAction = new DistributeCardAction();

			// ��ֵ�� -> ������ֵ��
			function DistributeCardAction() {
				// ��ֵ�� -> ˢ��
				this.refreshCards = function() {
					var cardType = $('#selectAmountList3').combobox('getValue');
					var beginDate3 = $('#beginDate3').datebox('getValue');
					var endDate3 = $('#endDate3').datebox('getValue');
					var cardNO = $.trim($("input[name='publishedCard_searchcardno']").val());
					$('#distributedCardDatagrid').datagrid('clearSelections');
					$('#distributedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate3,
								endDate : endDate3,
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
												distributedCardAction
														.refreshCards();// ˢ����ֵ���б���Ϣ
											}, 'json');
								}
							});
				}

				// ��ֵ�� -> �����ֵ������
				function fillCardType() {
					$('#distributedCardForm #distributedCardvaluetype')
							.combobox({
										url : mainWeb+'/admin/card/getAmountList',
										valueField : 'code',
										textField : 'title',
										editable : false
									});
				}

				// ��ֵ�� -> �ַ�
				this.reDistributeCard = function() {
					var ids = getSelectedIDS("#distributedCardDatagrid"); // ��ȡ���������ID
					// $('#hideCardId').val(ids) ;
					fillStationList2();
					if (ids == '') {
						$.messager.alert('��ʾ', '��ѡ��Ҫ�����ַ�����ֵ��!', 'warning');
					} else {
						// ���form
						$('#reDistributeCardForm').form('clear');
						// �򿪶Ի���
						$('#reDistributeCardDialog').dialog('open').dialog(
								'setTitle', '�ַ���ֵ��');
						url = mainWeb+'/admin/card/batchDistribute';
					}
				}

				function fillStationList2() {
					$('#reDistributeCardForm #selectStationList2').combobox({
								url : mainWeb+'/admin/card/getStationList',
								valueField : 'recid',
								textField : 'stationName',
								editable : false
							});
				}
				// ��ֵ�� -> �ַ�
				this.cardReDistribute = function() {
					var ids = getSelectedIDS("#distributedCardDatagrid"); // ��ȡ���������ID
					var stid = $('#selectStationList2').combobox('getValue');
					var pname = $("#personNameTextId2").val();
					$("#hiddenids2").val(ids);
					$('#reDistributeCardForm').form('submit', {
								url : url,
								onSubmit : function() {
									return publishedCardValidateForm(); // ��֤��
								},
								success : function(data) {
									$.messager.alert('��ʾ', '��ֵ������ɹ�!', 'info');// ����ɹ�������ʾ��Ϣ
									$("#reDistributeCardDialog")
											.dialog('close'); // �ر���ֵ���Ի���
									distributedCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
								}
							});
				}

				// ��ֵ�� -> ��֤��
				function publishedCardValidateForm() {
					if ($.trim($('#selectStationList2').combobox('getValue')) == '') {
						$.messager.alert('��ʾ', '��ѡ��վ��!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($
							.trim($("#reDistributeCardForm input[id='personNameTextId2']")
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