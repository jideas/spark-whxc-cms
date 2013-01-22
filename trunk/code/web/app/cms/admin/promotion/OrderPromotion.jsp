<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	</head>
	<body>
		<!-- begin of �б� -->
		<div id="orderPmtToolbar">
			<span style="float: left; padding-right: 5px;"> ״̬�� <input
					id="pmtStatusSelectList" name="pmtStatusSelectList"> </span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="orderPmtAction.orderPmtGift('')">��Ʒ����</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="orderPmtAction.orderPmtVtg('')">���ִ���</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="orderPmtAction.orderPmtFreeD()">�ͻ�����</a> </span>
		</div>
		<table id="orderPmtDatagrid" toolbar="#orderPmtToolbar">
		</table>
		<!-- end of �б� -->

		<!-- begin of ������Ʒ���� -->
		<div id="orderPmtDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 220px; padding: 20px 10px 0px;"
			title="1" buttons="#orderPmt-dlg-buttons" closed="true">
			<form id="orderPmtForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							����������ޣ�
						</td>
						<td>
							<input type="text" id="beginAmount0" name="beginAmount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid0" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							����������ޣ�
						</td>
						<td>
							<input type="text" name="endAmount" id="endAmount0"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Ʒ��
						</td>
						<td>
							<input id="goodsName0" name="goodsName" class="easyui-searchbox"
								type="text" style="width: 400px;" />
							<input name="goodsId" id="goodsId0" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��Ʒ������
						</td>
						<td>
							<input type="text" name="count" id="count0" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="orderPmt-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="orderPmtAction.cardSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orderPmtDialog').dialog('close')">ȡ ��</a>
		</div>

		<!-- begin of ������Ʒ���� -->
		<div id="orderPmtDialog1" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 210px; padding: 20px 10px 0px;"
			title="1" buttons="#orderPmt-dlg-buttons1" closed="true">
			<form id="orderPmtForm1" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							����������ޣ�
						</td>
						<td>
							<input type="text" name="beginAmount" id="beginAmount1"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid1" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							����������ޣ�
						</td>
						<td>
							<input type="text" name="endAmount" id="endAmount1"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;���ͻ��֣�
						</td>
						<td>
							<input type="text" name="count" id="count1" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="orderPmt-dlg-buttons1">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="orderPmtAction.cardSaveOrUpdate1()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orderPmtDialog1').dialog('close')">ȡ ��</a>
		</div>
		<!-- begin of �����ͻ����Ŵ��� -->
		<div id="orderPmtDialog2" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 160px; padding: 20px 10px 0px;"
			title="1" buttons="#orderPmt-dlg-buttons2" closed="true">
			<form id="orderPmtForm2" method="post" modelAttribute="form">
				<table>
					<tr>
						<td colspan="2">
							��д��������ͻ����ŵ���ͽ�
						</td>
					</tr>
					<tr>
						<td>
							����ֵ��
						</td>
						<td>
							<input type="text" name="beginAmount" id="beginAmount2"
								style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="orderPmt-dlg-buttons2">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="orderPmtAction.cardSaveOrUpdate2()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orderPmtDialog2').dialog('close')">ȡ ��</a>
		</div>
		<script type="text/javascript">
		// ��ʼ��
$(function() {	
	// ��ʼ�� -->  �����б���
	$('#orderPmtDatagrid').datagrid({
		fit : true,
		border : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		pagination : true,singleSelect: true,
		url : mainWeb+'/admin/pmt/getOrderPmtList',
		queryParams:{pmtStatus:'true'},
		columns : [[{
					field : 'beginAmount',
					title : '�������',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'endAmount',
					title : '�������',
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
						var begin = "<a href='#' class='operateChannel' onClick=orderPmtAction.editPmt('"
								+ value + "'," + rec.type + ")>�༭</a>";
						if (rec.status == '������') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=orderPmtAction.stopPmt('"
									+ value + "')>ͣ��</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=orderPmtAction.activePmt('"
									+ value + "')>����</a>"
						}
					}
				}]]
	});

	// ��ʼ�� -> ��ʼ������
	$('input#pmtStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});
	// �����¼� -> ���͸ı�
	$('#pmtStatusSelectList').combobox({
				onSelect : function(record) {
					// ˢ��
					orderPmtAction.refreshCards();
				}
			}); 
	$('#pmtStatusSelectList').combobox('setValue','true');
	
	$("#goodsName0").searchbox({
		searcher : function(value, name) {
			var chooseGoodsWindow = new ChooseGoodsWindow(true);
			chooseGoodsWindow.addConfirmActionListener(function(goodsVo) {
						$('#goodsId0').val(goodsVo.recid);
						$("#goodsName0").searchbox(
								'setValue',
								goodsVo.goodsname + '[' + goodsVo.goodsspec
										+ ']');
					});
		}
	});
	$("#orderPmtForm span.searchbox").attr('disabled', true);
	$("#orderPmtForm span.searchbox").css("width", 400);
	$("#orderPmtForm span.searchbox input.searchbox-text").css("width", 380);
});

// 
var orderPmtAction;
$(function() {
	var url = '';
	// -> ��ʼ��
	orderPmtAction = new AddCardAction();

	// -> ����
	function AddCardAction() {
		// -> ˢ��
		this.refreshCards = function() {
			var pmtStatus = $('#pmtStatusSelectList').combobox('getValue');
			$('#orderPmtDatagrid').datagrid('reload', {
						pmtStatus : pmtStatus
					}); // ˢ���б���Ϣ
		}

		// -> ����
		this.orderPmtGift = function(valueId) {
			// ���form
			$('#orderPmtForm').form('clear');
			if (null != valueId && '' != valueId) {
				fillPromotionInfo0(valueId);
			}
			// �򿪶Ի���
			$('#orderPmtDialog').dialog('open').dialog('setTitle', '����');
			//����searchbox��orderPmtForm��
			$("#orderPmtForm span.searchbox input.searchbox-text").attr("readonly","readonly");
			url = mainWeb+'/admin/pmt/orderPmtGift';
		}
		// -> ����
		this.orderPmtVtg = function(valueId) {
			// ���form
			$('#orderPmtForm1').form('clear');
			if (null != valueId && '' != valueId) {
				fillPromotionInfo1(valueId);
			}
			// �򿪶Ի���
			$('#orderPmtDialog1').dialog('open').dialog('setTitle', '����');
			url = mainWeb+'/admin/pmt/orderPmtVtg';
		}
		// -> ����
		this.orderPmtFreeD = function() {
			// ���form
			$('#orderPmtForm2').form('clear');
			fillBeginAmountOfFreeDelibery();
			// �򿪶Ի���
			$('#orderPmtDialog2').dialog('open').dialog('setTitle', '����');
			url = mainWeb+'/admin/pmt/orderPmtFreeD';
		}

		function fillPromotionInfo0(valueId) {
			$.post(mainWeb+'/admin/pmt/findPmt', {
						id : valueId
					}, function(result) {
						$('#beginAmount0').val(result.beginAmount);
						$('#endAmount0').val(result.endAmount);
						$("#goodsName0").searchbox('setValue', result.detail);
						$('#goodsId0').val(result.detailId);
						$('#count0').val(result.count);
						$('#hiddenrecid0').val(result.recid);
					}, 'json');
		}

		function fillPromotionInfo1(valueId) {
			$.post(mainWeb+'/admin/pmt/findPmt', {
						id : valueId
					}, function(result) {
						$('#beginAmount1').val(result.beginAmount);
						$('#endAmount1').val(result.endAmount);
						$('#count1').val(result.count);
						$('#hiddenrecid1').val(result.recid);
					}, 'json');
		}
		function fillBeginAmountOfFreeDelibery() {
			$.post(mainWeb+'/admin/pmt/getOldBeginAmount',
					function(result) {
						$('#beginAmount2').val(result);
					}, 'json');
		}

		// -> ͣ��
		this.editPmt = function(valueId, typeId) {
			if (1 == typeId) {
				this.orderPmtGift(valueId);
			} else if (2 == typeId) {
				this.orderPmtVtg(valueId);
			} else if (3 == typeId) {
				this.orderPmtFreeD();
			}
		}

		// -> ͣ��
		this.stopPmt = function(valueId) {
			$.messager.confirm('ͣ��', '�Ƿ�ȷ��ͣ��?', function(r) {
						if (r) {
							$.post(mainWeb+'/admin/pmt/stopPmt', {
										id : valueId
									}, function(result) {
										$.messager.alert('��ʾ', result.message,
												'info');// ����ɹ�������ʾ��Ϣ
										if (result.result == true) {
											orderPmtAction.refreshCards();// ˢ���б���Ϣ
										}
									}, 'json');
						}
					});
		}

		// -> ͣ��
		this.activePmt = function(valueId) {
			$.messager.confirm('����', '�Ƿ�ȷ������?', function(r) {
						if (r) {
							$.post(mainWeb+'/admin/pmt/activePmt', {
										id : valueId
									}, function(result) {
										$.messager.alert('��ʾ', result.message,
												'info');// ����ɹ�������ʾ��Ϣ
										if (result.result == true) {
											orderPmtAction.refreshCards();// ˢ���б���Ϣ
										}
									}, 'json');
						}
					});
		}

		// -> ����
		this.cardSaveOrUpdate = function() {
			$('#orderPmtForm').form('submit', {
						url : url,
						onSubmit : function() {
							return orderPmtValidateForm(); // ��֤��
						},
						success : function(data) {
							data = eval("(" + data + ")")
							$.messager.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
							if (data.result == true) {
								$("#orderPmtDialog").dialog('close'); // �رնԻ���
								orderPmtAction.refreshCards();// ˢ���б���Ϣ
							}
						}
					});
		}

		// -> ����
		this.cardSaveOrUpdate1 = function() {
			$('#orderPmtForm1').form('submit', {
						url : url,
						onSubmit : function() {
							return orderPmtValidateForm1(); // ��֤��
						},
						success : function(data) {
							data = eval("(" + data + ")")
							$.messager.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
							if (data.result == true) {
								$("#orderPmtDialog1").dialog('close'); // �رնԻ���
								orderPmtAction.refreshCards();// ˢ���б���Ϣ
							}
						}
					});
		}

		// -> ����
		this.cardSaveOrUpdate2 = function() {
			$('#orderPmtForm2').form('submit', {
						url : url,
						onSubmit : function() {
							return orderPmtValidateForm2(); // ��֤��
						},
						success : function(data) {
							data = eval("(" + data + ")")
							$.messager.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
							if (data.result == true) {
								$("#orderPmtDialog2").dialog('close'); // �رնԻ���
								orderPmtAction.refreshCards();// ˢ���б���Ϣ
							}
						}
					});
		}

		// -> ��֤��
		function orderPmtValidateForm() {
			var ba0 = $("#orderPmtForm input[id='beginAmount0']").val();
			var ea0 = $("#orderPmtForm input[id='endAmount0']").val();
			if ($.trim(ba0) == '') {
				$.messager.alert('��ʾ', '���޽��δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			if ($.trim(ea0) == '') {
				$.messager.alert('��ʾ', '���޽��δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			ba0 = ba0.replace(',',"")
			ba0 = ba0.replace(',',"")
			ba0 = ba0.replace(',',"")
			ea0 = ea0.replace(',',"")
			ea0 = ea0.replace(',',"")
			ea0 = ea0.replace(',',"")
			if (parseFloat(ba0) >= parseFloat(ea0)) {
				$.messager.alert('��ʾ', '���޽�����������޽��!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			if ($.trim($("#orderPmtForm input[id='goodsId0']").val()) == '') {
				$.messager.alert('��ʾ', '��Ʒδѡ��!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			if ($.trim($("#orderPmtForm input[id='count0']").val()) == '') {
				$.messager.alert('��ʾ', '��Ʒ����δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			return true;
		}
		// -> ��֤��
		function orderPmtValidateForm1() {
			var ba1 = $("#orderPmtForm1 input[id='beginAmount1']").val();
			var ea1 = $("#orderPmtForm1 input[id='endAmount1']").val();
			if ($.trim(ba1) == '') {
				$.messager.alert('��ʾ', '���޽��δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			if ($.trim(ea1) == '') {
				$.messager.alert('��ʾ', '���޽��δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			ba1 = ba1.replace(',',"")
			ba1 = ba1.replace(',',"")
			ba1 = ba1.replace(',',"")
			ea1 = ea1.replace(',',"")
			ea1 = ea1.replace(',',"")
			ea1 = ea1.replace(',',"")
			if (parseFloat(ba1) >= parseFloat(ea1)) {
				$.messager.alert('��ʾ', '���޽�����������޽��!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			if ($.trim($("#orderPmtForm1 input[id='count1']").val()) == '') {
				$.messager.alert('��ʾ', '���ͻ���δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			return true;
		}
		// -> ��֤��
		function orderPmtValidateForm2() {
			if ($.trim($("#orderPmtForm2 input[id='beginAmount2']").val()) == '') {
				$.messager.alert('��ʾ', '���޽��δ��д!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			return true;
		}
	}
});
		</script>
	</body>
</html>