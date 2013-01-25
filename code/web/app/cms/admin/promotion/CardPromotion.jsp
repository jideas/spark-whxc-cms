<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����</title>
	</head>
	<body>
		<!-- begin of �б� -->
		<div id="cardPmtToolbar">
			<span style="float: left; padding-right: 5px;">״̬��
			<input id="cardpmtStatusSelectList" name="cardpmtStatusSelectList"></span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="cardPmtAction.editCardGiftPmt('')">��Ʒ����</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="cardPmtAction.editCardVtgPmt('')">���ִ���</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="cardPmtAction.editCardExtraPmt('')">������</a> </span>
		</div>
		<table id="cardPmtDatagrid" toolbar="#cardPmtToolbar">
		</table>
		<!-- end of �б� -->

		<!-- begin of ������Ʒ���� -->
		<div id="cardPmtDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 220px; padding: 20px 10px 0px;"
			title="1" buttons="#cardPmt-dlg-buttons" closed="true">
			<form id="cardPmtForm" method="post">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��ֵ��� ��
						</td>
						<td>
							<input type="text" id="amount0" name="amount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid0_cardpmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ʒ��
						</td>
						<td>
							<input id="goodsName0_cardpmt" id="goodsName0_cardpmt" name="goodsName"
								class="easyui-searchbox" type="text" style="width: 400px;" />
							<input name="goodsId" id="goodsId0_cardpmt" type="hidden" />

						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��Ʒ������
						</td>
						<td>
							<input type="text" name="count" id="count0_card" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="cardPmt-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="cardPmtAction.cardSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#cardPmtDialog').dialog('close')">ȡ ��</a>
		</div>

		<!-- begin of ������Ʒ���� -->
		<div id="cardPmtDialog1" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 210px; padding: 20px 10px 0px;"
			title="1" buttons="#cardPmt-dlg-buttons1" closed="true">
			<form id="cardPmtForm1" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��ֵ��� ��
						</td>
						<td>
							<input type="text" id="amount1_cardpmt" name="amount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid1_cardpmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;���ͻ��֣�
						</td>
						<td>
							<input type="text" name="count" id="count1_card" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="cardPmt-dlg-buttons1">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="cardPmtAction.cardSaveOrUpdate1()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#cardPmtDialog1').dialog('close')">ȡ ��</a>
		</div>
		<!-- begin of ������Ʒ���� -->
		<div id="cardPmtDialog2" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 210px; padding: 20px 10px 0px;"
			title="1" buttons="#cardPmt-dlg-buttons2" closed="true">
			<form id="cardPmtForm2" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��ֵ��� ��
						</td>
						<td>
							<input type="text" id="amount2_cardpmt" name="amount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid2_cardpmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;���ͽ�
						</td>
						<td>
							<input type="text" name="count" id="count2_cardpmt" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="cardPmt-dlg-buttons2">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="cardPmtAction.cardSaveOrUpdate2()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#cardPmtDialog2').dialog('close')">ȡ ��</a>
		</div>
		<script type="text/javascript">
	// ��ʼ��
$(function() {
	// ��ʼ�� --> --> �����б���
	$('#cardPmtDatagrid').datagrid({
		fit : true,
		bcard : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		pagination : true,singleSelect: true,
		url : mainWeb+'/admin/pmt/getCardPmtList',
		queryParams:{pmtStatus:'true'},
		columns : [[{
					field : 'amount',
					title : '��ֵ���',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'detail',
					title : '��Ʒ',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'count',
					title : '��Ʒ����',
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
						var begin = "<a href='#' class='operateChannel' onClick=cardPmtAction.editPmt('"
								+ value + "'," + rec.type + ")>�༭</a>";
						if (rec.status == '������') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=cardPmtAction.stopCardPmt('"
									+ value + "')>ͣ��</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=cardPmtAction.activeCardPmt('"
									+ value + "')>����</a>"
						}
					}
				}]]
	});

	// ��ʼ�� -> ��ʼ������
	$('input#cardpmtStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});
	// �����¼� -> ���͸ı�
	$('#cardpmtStatusSelectList').combobox({
				onSelect : function(record) {
					// ˢ��
					cardPmtAction.refreshCards();
				}
			});
	$('#cardpmtStatusSelectList').combobox('setValue','true');

	$("#goodsName0_cardpmt").searchbox({
		searcher : function(value, name) {
			var imageSelectWindow = new ChooseGoodsWindow(true);
			imageSelectWindow.addConfirmActionListener(function(goodsVo) {
						$('#goodsId0_cardpmt').val(goodsVo.recid);
						alert(goodsVo.recid);
						$("#goodsName0_cardpmt").searchbox(
								'setValue',
								goodsVo.goodsname + '[' + goodsVo.goodsspec
										+ ']');
					});
		}
	});
	$("#cardPmtForm span.searchbox").attr('disabled', true);
	$("#cardPmtForm span.searchbox").css("width", 400);
	$("#cardPmtForm span.searchbox input.searchbox-text").css("width", 380);
});

// 
var cardPmtAction;
$(function() {
			var url = '';
			// -> ��ʼ��
			cardPmtAction = new AddCardAction();

			// -> ����
			function AddCardAction() {
				// -> ˢ��
				this.refreshCards = function() {
					var pmtStatus = $('#cardpmtStatusSelectList')
							.combobox('getValue');
					$('#cardPmtDatagrid').datagrid('reload', {
								pmtStatus : pmtStatus
							}); // ˢ���б���Ϣ
				}

				// -> ����
				this.editCardGiftPmt = function(valueId) {
					// ���form
					$('#cardPmtForm').form('clear');
					fillPromotionInfo0(valueId);
					// �򿪶Ի���
					$('#cardPmtDialog').dialog('open').dialog('setTitle', '����');
					//����searchbox(cardPmtForm)
					$("#cardPmtForm span.searchbox input.searchbox-text").attr("readonly","readonly");
					url = mainWeb+'/admin/pmt/editCardGiftPmt';
				}
				// -> ����
				this.editCardVtgPmt = function(valueId) {
					// ���form
					$('#cardPmtForm1').form('clear');
					fillPromotionInfo1(valueId);
					// �򿪶Ի���
					$('#cardPmtDialog1').dialog('open')
							.dialog('setTitle', '����');
					url = mainWeb+'/admin/pmt/editCardVtgPmt';
				}
				// -> ����
				this.editCardExtraPmt = function(valueId) {
					// ���form
					$('#cardPmtForm2').form('clear');
					fillPromotionInfo2(valueId);
					// �򿪶Ի���
					$('#cardPmtDialog2').dialog('open')
							.dialog('setTitle', '����');
					url = mainWeb+'/admin/pmt/editCardExtraPmt';
				}

				function fillPromotionInfo0(valueId) {
					// ��ʼ�� -> ��ʼ������
					$('#cardPmtForm #amount0').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'code',
								textField : 'title',
								editable:false
							});
					if (null != valueId && '' != valueId) {
						$.post(mainWeb+'/admin/pmt/findCardPmt', {
									id : valueId
								}, function(result) {
									$('#amount0').combobox('setValue',
											result.amount)
									$("#goodsName0_cardpmt").searchbox('setValue',
											result.detail);
									$('#goodsId0_cardpmt').val(result.detailId);
									$('#count0_card').val(result.count);
									$('#hiddenrecid0_cardpmt').val(result.recid);
								}, 'json');
					}
				}

				function fillPromotionInfo1(valueId) {
					$('#cardPmtForm1 #amount1_cardpmt').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'code',
								textField : 'title',
								editable:false
							});
					if (null != valueId && '' != valueId) {
						$.post(mainWeb+'/admin/pmt/findCardPmt', {
									id : valueId
								}, function(result) {
									$('#amount1_cardpmt').combobox('setValue',
											result.amount)
									$('#count1_card').val(result.count);
									$('#hiddenrecid1_cardpmt').val(result.recid);
								}, 'json');
					}
				}

				function fillPromotionInfo2(valueId) {
					$('#cardPmtForm2 #amount2_cardpmt').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'code',
								textField : 'title',
								editable:false
							});
					if (null != valueId && '' != valueId) {
						$.post(mainWeb+'/admin/pmt/findCardPmt', {
									id : valueId
								}, function(result) {
									$('#amount2_cardpmt').combobox('setValue',
											result.amount)
									$('#count2_cardpmt').val(result.count);
									$('#hiddenrecid2_cardpmt').val(result.recid);
								}, 'json');
					}
				}
				// -> ͣ��
				this.editPmt = function(valueId, typeId) {
					if (1 == typeId) {
						this.editCardGiftPmt(valueId);
					} else if (2 == typeId) {
						this.editCardVtgPmt(valueId);
					} else if (3 == typeId) {
						this.editCardExtraPmt(valueId);
					}
				}

				// -> ͣ��
				this.stopCardPmt = function(valueId) {
					$.messager.confirm('ͣ��', '�Ƿ�ȷ��ͣ��?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/stopCardPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('��ʾ',
														result.message, 'info');// ����ɹ�������ʾ��Ϣ
												if (result.result == true) {
													cardPmtAction
															.refreshCards();// ˢ���б���Ϣ
												}
											}, 'json');
								}
							});
				}

				// -> ͣ��
				this.activeCardPmt = function(valueId) {
					$.messager.confirm('����', '�Ƿ�ȷ������?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/activeCardPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('��ʾ',
														result.message, 'info');// ����ɹ�������ʾ��Ϣ
												if (result.result == true) {
													cardPmtAction
															.refreshCards();// ˢ���б���Ϣ
												}
											}, 'json');
								}
							});
				}

				// -> ����
				this.cardSaveOrUpdate = function() {
					$('#cardPmtForm').form('submit', {
								url : url,
								onSubmit : function() {
									return cardPmtValidateForm(); // ��֤��
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
									if (data.result == true) {
										$("#cardPmtDialog").dialog('close'); // �رնԻ���
										cardPmtAction.refreshCards();// ˢ���б���Ϣ
									}
								}
							});
				}

				// -> ����
				this.cardSaveOrUpdate1 = function() {
					$('#cardPmtForm1').form('submit', {
								url : url,
								onSubmit : function() {
									return cardPmtValidateForm1(); // ��֤��
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
									if (data.result == true) {
										$("#cardPmtDialog1").dialog('close'); // �رնԻ���
										cardPmtAction.refreshCards();// ˢ���б���Ϣ
									}
								}
							});
				}

				// -> ����
				this.cardSaveOrUpdate2 = function() {
					$('#cardPmtForm2').form('submit', {
								url : url,
								onSubmit : function() {
									return cardPmtValidateForm2(); // ��֤��
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
									if (data.result == true) {
										$("#cardPmtDialog2").dialog('close'); // �رնԻ���
										cardPmtAction.refreshCards();// ˢ���б���Ϣ
									}
								}
							});
				}

				// -> ��֤��
				function cardPmtValidateForm() {
					var ba0 = $('#amount0').combobox('getValue');
					if ($.trim(ba0) == '') {
						$.messager.alert('��ʾ', '��ֵ���δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($.trim($("#cardPmtForm input[id='goodsId0_cardpmt']").val()) == '') {
						$.messager.alert('��ʾ', '��Ʒδѡ��!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($.trim($("#cardPmtForm input[id='count0_card']").val()) == '') {
						$.messager.alert('��ʾ', '��Ʒ����δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					return true;
				}
				// -> ��֤��
				function cardPmtValidateForm1() {
					var ba0 = $('#amount1_cardpmt').combobox('getValue');
					if ($.trim(ba0) == '') {
						$.messager.alert('��ʾ', '��ֵ���δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($.trim($("#cardPmtForm1 input[id='count1_card']").val()) == '') {
						$.messager.alert('��ʾ', '���ͻ���δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					return true;
				}

				// -> ��֤��
				function cardPmtValidateForm2() {
					var ba0 = $('#amount2_cardpmt').combobox('getValue');
					if ($.trim(ba0) == '') {
						$.messager.alert('��ʾ', '��ֵ���δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($.trim($("#cardPmtForm2 input[id='count2_cardpmt']").val()) == '') {
						$.messager.alert('��ʾ', '���ͽ��δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					return true;
				}
			}
		});
		</script>
	</body>
</html>