<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����</title>
	</head> 
	<body>
		<!-- begin of �б� -->
		<div id="deliveryPriceToolbar">
			<span style="float: left; padding-right: 5px;"> ״̬��
			<input id="deliveryStatusSelectList" name="deliveryStatusSelectList"></span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="deliveryPriceAction.saveDeliveryPrice('')">��������</a></span>
		</div>
		<table id="deliveryPriceDatagrid" toolbar="#deliveryPriceToolbar">
		</table>
		<!-- end of �б� -->

		<!-- begin of ������Ʒ���� -->
		<div id="deliveryPriceDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 195px; padding: 20px 10px 0px;"
			title="1" buttons="#deliveryPrice-dlg-buttons" closed="true">
			<form id="deliveryPriceForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;�ͻ�������
						</td>
						<td>
							<input id="dcount0" name="dcount" type="text"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid0" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;���ۼ۸�
						</td>
						<td>
							<input type="text" id="dprice0" name="dprice"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��������
						</td>
						<td>
							<input type="text" name="remark" id="remark0"
								style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="deliveryPrice-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="deliveryPriceAction.deliverySaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#deliveryPriceDialog').dialog('close')">ȡ
				��</a>
		</div>
		<script type="text/javascript">
// ��ʼ��
$(function() {
	// ��ʼ�� --> --> �����б���
	$('#deliveryPriceDatagrid').datagrid({
		fit : true,
		bgoods : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,singleSelect: true,
		pagination : true,
		url : mainWeb+'/admin/delivery/getDeliveryPriceList',
		columns : [[{
					field : 'dcount',
					title : '�ͻ�����',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'dprice',
					title : '���ۼ۸�',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'remark',
					title : '����',
					width : 160,
					align : 'center',
					sortable : true
				}, {
					field : 'lastModify',
					title : '����޸���',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'lastModifyDate',
					title : '����޸�����',
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
						var begin = "<a href='#' class='operateChannel' onClick=deliveryPriceAction.editDelivery('"
								+ value + "')>�༭</a>";
						if (rec.status == '������') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=deliveryPriceAction.stopDeliveryPrice('"
									+ value + "')>ͣ��</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=deliveryPriceAction.activeDeliveryPrice('"
									+ value + "')>����</a>"
						}
					}
				}]]
	});

	// ��ʼ�� -> ��ʼ������
	$('input#deliveryStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});

	// �����¼� -> ���͸ı�
	$('#deliveryStatusSelectList').combobox({
				onSelect : function(record) {
					// ˢ��
					deliveryPriceAction.refreshCards();
				}
			});
});

// 
var deliveryPriceAction;
$(function() {
			var url = '';
			// -> ��ʼ��
			deliveryPriceAction = new AddCardAction();

			// -> ����
			function AddCardAction() {
				// -> ˢ��
				this.refreshCards = function() {
					var deliveryStatus = $('#deliveryStatusSelectList')
							.combobox('getValue');
					$('#deliveryPriceDatagrid').datagrid('reload', {
								deliveryStatus : deliveryStatus
							}); // ˢ���б���Ϣ
				}

				// -> ����
				this.saveDeliveryPrice = function(valueId) {
					// ���form
					$('#deliveryPriceForm').form('clear');
					if (null != valueId && '' != valueId) {
						fillPromotionInfo0(valueId);
					}
					// �򿪶Ի���
					$('#deliveryPriceDialog').dialog('open').dialog('setTitle',
							'����');
					url = mainWeb+'/admin/delivery/saveDeliveryPrice';
				}

				function fillPromotionInfo0(valueId) {
					$.post(mainWeb+'/admin/delivery/findDeliveryPrice', {
								recid : valueId
							}, function(result) {
								if (!result.result) {
									return;
								}
								$('#dcount0').val(result.returnObj.dcount);
								$('#dprice0').val(result.returnObj.dprice);
								$('#remark0').val(result.returnObj.remark);
								$('#hiddenrecid0').val(result.returnObj.recid);
							}, 'json');
				}

				// -> ͣ��
				this.editDelivery = function(valueId) {
					this.saveDeliveryPrice(valueId);
				}

				// -> ͣ��
				this.stopDeliveryPrice = function(valueId) {
					$.messager.confirm('ͣ��', '�Ƿ�ȷ��ͣ��?', function(r) {
								if (r) {
									$
											.post(
													mainWeb+'/admin/delivery/stopDeliveryPrice',
													{
														recid : valueId
													}, function(result) {
														$.messager.alert('��ʾ',
																result.message,
																'info');// ����ɹ�������ʾ��Ϣ
														if (result.result == true) {
															deliveryPriceAction
																	.refreshCards();// ˢ���б���Ϣ
														}
													}, 'json');
								}
							});
				}

				// -> ͣ��
				this.activeDeliveryPrice = function(valueId) {
					$.messager.confirm('����', '�Ƿ�ȷ������?', function(r) {
								if (r) {
									$
											.post(
													mainWeb+'/admin/delivery/activeDeliveryPrice',
													{
														recid : valueId
													}, function(result) {
														$.messager.alert('��ʾ',
																result.message,
																'info');// ����ɹ�������ʾ��Ϣ
														if (result.result == true) {
															deliveryPriceAction
																	.refreshCards();// ˢ���б���Ϣ
														}
													}, 'json');
								}
							});
				}

				// -> ����
				this.deliverySaveOrUpdate = function() {
					$('#deliveryPriceForm').form('submit', {
								url : url,
								onSubmit : function() {
									return deliveryPriceValidateForm(); // ��֤��
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
									if (data.result == true) {
										$("#deliveryPriceDialog")
												.dialog('close'); // �رնԻ���
										deliveryPriceAction.refreshCards();// ˢ���б���Ϣ
									}
								}
							});
				}

				// -> ��֤��
				function deliveryPriceValidateForm() {
					var disrate = $('#dcount0').val();
					if ($.trim(disrate) == '') {
						$.messager.alert('��ʾ', '�ͻ�����δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($.trim($('#dprice0').val()) == '') {
						$.messager.alert('��ʾ', '���ۼ۸�δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					return true;
				}
			}
		});
		</script>
	</body>
</html>