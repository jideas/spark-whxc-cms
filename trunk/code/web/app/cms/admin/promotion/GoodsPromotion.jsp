<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ʒ����</title>
	</head>
	</head>
	<body>
		<style type="text/css">
.goodswinleft {
	float: left;
	border: #999 solid 1px;
}

.goodswinright {
	float: left;
	border: #999 solid 1px;
}
</style>
		<!-- begin of �б� -->
		<div id="goodsPmtToolbar">
			<span style="float: left; padding-right: 5px;">״̬�� <input
					id="goodspmtStatusSelectList" name="goodspmtStatusSelectList"> </span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="goodsPmtAction.saveGoodsPmt('')">��������</a> </span>
		</div>
		<table id="goodsPmtDatagrid" toolbar="#goodsPmtToolbar">
		</table>
		<!-- end of �б� -->

		<!-- begin of ������Ʒ���� -->
		<div id="goodsPmtDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 248px; padding: 20px 10px 0px;"
			title="1" buttons="#goodsPmt-dlg-buttons" closed="true">
			<form id="goodsPmtForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;������Ʒ��
						</td>
						<td>
							<input id="goodsName0_goodspmt" name="goodsName" class="easyui-searchbox"
								type="text" style="width: 400px;" />
							<input name="goodsId" id="goodsId0_goodspmt" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��ʼʱ�䣺
						</td>
						<td>
							<input type="text" class="easyui-datetimebox" id="beginTime0"
								name="beginTime" style="width: 400px;" readonly="readonly" />
							<input type="hidden" id="hiddenrecid0_goodsPmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;����ʱ�䣺
						</td>
						<td>
							<input type="text" class="easyui-datetimebox" name="endTime"
								id="endTime0_goodsPmt" style="width: 400px;" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;��Ʒ�ۿ�(%)��
						</td>
						<td>
							<input type="text" name="disrate" id="disrate0_goodsPmt"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;����������
						</td>
						<td>
							<input type="text" name="count" id="count0_goodsPmt" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="goodsPmt-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="goodsPmtAction.pmtSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#goodsPmtDialog').dialog('close')">ȡ ��</a>
		</div>
		<script type="text/javascript">// ��ʼ��
$(function() {
	// ��ʼ�� --> --> �����б���
	$('#goodsPmtDatagrid').datagrid({
		fit : true,
		bgoods : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		pagination : true,singleSelect: true,
		url : mainWeb+'/admin/pmt/getGoodsPmtList',
		queryParams:{pmtStatus:'true'},
		columns : [[{
					field : 'goodsName',
					title : '��Ʒ����',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'beginTime',
					title : '��ʼʱ��',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'endTime',
					title : '����ʱ��',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'disRate',
					title : '��Ʒ�ۿ�',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'count',
					title : '��������',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'salesCount',
					title : '��������',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'reserveCount',
					title : 'ʣ������',
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
						var begin = "<a href='#' class='operateChannel' onClick=goodsPmtAction.editPmt('"
								+ value + "')>�༭</a>";
						if (rec.status == '������') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=goodsPmtAction.stopGoodsPmt('"
									+ value + "')>ͣ��</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=goodsPmtAction.activeGoodsPmt('"
									+ value + "')>����</a>"
						}
					}
				}]]
	});

	// ��ʼ�� -> ��ʼ������
	$('input#goodspmtStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});
	// �����¼� -> ���͸ı�
	$('#goodspmtStatusSelectList').combobox({
				onSelect : function(record) {
					// ˢ��
					goodsPmtAction.refreshCards();
				}
			});
	$('#goodspmtStatusSelectList').combobox('setValue','true');

	$("#goodsName0_goodspmt").searchbox({
		searcher : function(value, name) {
			// ����д����ѡ���Ĵ���
			var win = new ChooseGoodsWindow(true);
			win.addConfirmActionListener(function(goodsVo) {
						$('#goodsId0_goodspmt').val(goodsVo.recid);
						$("#goodsName0_goodspmt").searchbox(
								'setValue',
								goodsVo.goodsname + '[' + goodsVo.goodsspec
										+ ']');
					});
		}
	});  
});

// 
var goodsPmtAction;
$(function() {
			var url = '';
			// -> ��ʼ��
			goodsPmtAction = new AddCardAction();
			// -> ����
			function AddCardAction() {
				// -> ˢ��
				this.refreshCards = function() {
					var pmtStatus = $('#goodspmtStatusSelectList')
							.combobox('getValue');
					$('#goodsPmtDatagrid').datagrid('reload', {
								pmtStatus : pmtStatus
							}); // ˢ���б���Ϣ
				}

				// -> ����
				this.saveGoodsPmt = function(valueId) {
					// ���form
					$('#goodsPmtForm').form('clear');
					if (null != valueId && '' != valueId) {
						fillPromotionInfo0(valueId);
					}
					// �򿪶Ի���
					$('#goodsPmtDialog').dialog('open')
							.dialog('setTitle', '��Ʒ����');
					//����searchbox(goodsPmtForm)
					$("#goodsPmtForm span.searchbox input.searchbox-text").attr("readonly","readonly");		
					url = mainWeb+'/admin/pmt/saveGoodsPmt';
				}

				function fillPromotionInfo0(valueId) {
					$.post(mainWeb+'/admin/pmt/findGoodsPmt', {
								id : valueId
							}, function(result) {
								$("#goodsName0_goodspmt").searchbox('setValue',
										result.goodsName);
								$('#goodsId0_goodspmt').val(result.goodsId);
								$('#beginTime0').datetimebox('setValue',
										result.beginTime);
								$('#endTime0_goodsPmt').datetimebox('setValue',
										result.endTime);
								$('#count0_goodsPmt').val(result.count);
								$('#disrate0_goodsPmt').val(result.disRate);
								$('#hiddenrecid0_goodsPmt').val(result.recid);
							}, 'json');
				}

				// -> ͣ��
				this.editPmt = function(valueId) {
					this.saveGoodsPmt(valueId);
				}

				// -> ͣ��
				this.stopGoodsPmt = function(valueId) {
					$.messager.confirm('ͣ��', '�Ƿ�ȷ��ͣ��?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/stopGoodsPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('��ʾ',
														result.message, 'info');// ����ɹ�������ʾ��Ϣ
												if (result.result == true) {
													goodsPmtAction
															.refreshCards();// ˢ���б���Ϣ
												}
											}, 'json');
								}
							});
				}

				// -> ͣ��
				this.activeGoodsPmt = function(valueId) {
					$.messager.confirm('����', '�Ƿ�ȷ������?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/activeGoodsPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('��ʾ',
														result.message, 'info');// ����ɹ�������ʾ��Ϣ
												if (result.result == true) {
													goodsPmtAction
															.refreshCards();// ˢ���б���Ϣ
												}
											}, 'json');
								}
							});
				}

				// -> ����
				this.pmtSaveOrUpdate = function() {
					$('#goodsPmtForm').form('submit', {
								url : url,
								onSubmit : function() {
									return goodsPmtvalidateForm(); // ��֤��
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
									if (data.result == true) {
										$("#goodsPmtDialog").dialog('close'); // �رնԻ���
										goodsPmtAction.refreshCards();// ˢ���б���Ϣ
									}
								}
							});
				}

				// -> ��֤��
				function goodsPmtvalidateForm() {

					if ($.trim($("#goodsPmtForm input[id='goodsId0_goodspmt']").val()) == '') {
						$.messager.alert('��ʾ', '������Ʒδѡ��!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					var beginDate = $('#beginTime0').datetimebox('getValue');
					var endDate = $('#endTime0_goodsPmt').datetimebox('getValue');
					if (beginDate == '') {
						$.messager.alert('��ʾ', '��ʼʱ��δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if (endDate == '') {
						$.messager.alert('��ʾ', '����ʱ��δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					var disrate = $("#goodsPmtForm input[id='disrate0_goodsPmt']").val();
					if ($.trim(disrate) == '') {
						$.messager.alert('��ʾ', '��Ʒ�ۿ�δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if (parseFloat(disrate) >= 100 || parseFloat(disrate) <= 0) {
						$.messager.alert('��ʾ', '����ȷ��д��Ʒ�ۿ�!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					if ($.trim($("#goodsPmtForm input[id='count0_goodsPmt']").val()) == '') {
						$.messager.alert('��ʾ', '��������δ��д!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
					return true;
				}
			}
		});
		</script>
	</body>
</html>