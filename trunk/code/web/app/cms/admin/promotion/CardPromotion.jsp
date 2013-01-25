<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head>
	<body>
		<!-- begin of 列表 -->
		<div id="cardPmtToolbar">
			<span style="float: left; padding-right: 5px;">状态：
			<input id="cardpmtStatusSelectList" name="cardpmtStatusSelectList"></span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="cardPmtAction.editCardGiftPmt('')">赠品促销</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="cardPmtAction.editCardVtgPmt('')">积分促销</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="cardPmtAction.editCardExtraPmt('')">金额促销</a> </span>
		</div>
		<table id="cardPmtDatagrid" toolbar="#cardPmtToolbar">
		</table>
		<!-- end of 列表 -->

		<!-- begin of 新增赠品促销 -->
		<div id="cardPmtDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 220px; padding: 20px 10px 0px;"
			title="1" buttons="#cardPmt-dlg-buttons" closed="true">
			<form id="cardPmtForm" method="post">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;充值金额 ：
						</td>
						<td>
							<input type="text" id="amount0" name="amount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid0_cardpmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赠品：
						</td>
						<td>
							<input id="goodsName0_cardpmt" id="goodsName0_cardpmt" name="goodsName"
								class="easyui-searchbox" type="text" style="width: 400px;" />
							<input name="goodsId" id="goodsId0_cardpmt" type="hidden" />

						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;赠品数量：
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
				onclick="cardPmtAction.cardSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#cardPmtDialog').dialog('close')">取 消</a>
		</div>

		<!-- begin of 新增赠品促销 -->
		<div id="cardPmtDialog1" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 210px; padding: 20px 10px 0px;"
			title="1" buttons="#cardPmt-dlg-buttons1" closed="true">
			<form id="cardPmtForm1" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;充值金额 ：
						</td>
						<td>
							<input type="text" id="amount1_cardpmt" name="amount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid1_cardpmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;赠送积分：
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
				onclick="cardPmtAction.cardSaveOrUpdate1()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#cardPmtDialog1').dialog('close')">取 消</a>
		</div>
		<!-- begin of 新增赠品促销 -->
		<div id="cardPmtDialog2" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 210px; padding: 20px 10px 0px;"
			title="1" buttons="#cardPmt-dlg-buttons2" closed="true">
			<form id="cardPmtForm2" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;充值金额 ：
						</td>
						<td>
							<input type="text" id="amount2_cardpmt" name="amount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid2_cardpmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;赠送金额：
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
				onclick="cardPmtAction.cardSaveOrUpdate2()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#cardPmtDialog2').dialog('close')">取 消</a>
		</div>
		<script type="text/javascript">
	// 初始化
$(function() {
	// 初始化 --> --> 定义列表列
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
					title : '充值金额',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'detail',
					title : '赠品',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'count',
					title : '赠品数量',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'status',
					title : '状态',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'recid',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, rec) {
						var begin = "<a href='#' class='operateChannel' onClick=cardPmtAction.editPmt('"
								+ value + "'," + rec.type + ")>编辑</a>";
						if (rec.status == '启用中') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=cardPmtAction.stopCardPmt('"
									+ value + "')>停用</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=cardPmtAction.activeCardPmt('"
									+ value + "')>启用</a>"
						}
					}
				}]]
	});

	// 初始化 -> 初始化类型
	$('input#cardpmtStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});
	// 监听事件 -> 类型改变
	$('#cardpmtStatusSelectList').combobox({
				onSelect : function(record) {
					// 刷新
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
			// -> 初始化
			cardPmtAction = new AddCardAction();

			// -> 定义
			function AddCardAction() {
				// -> 刷新
				this.refreshCards = function() {
					var pmtStatus = $('#cardpmtStatusSelectList')
							.combobox('getValue');
					$('#cardPmtDatagrid').datagrid('reload', {
								pmtStatus : pmtStatus
							}); // 刷新列表信息
				}

				// -> 新增
				this.editCardGiftPmt = function(valueId) {
					// 清空form
					$('#cardPmtForm').form('clear');
					fillPromotionInfo0(valueId);
					// 打开对话框
					$('#cardPmtDialog').dialog('open').dialog('setTitle', '新增');
					//禁用searchbox(cardPmtForm)
					$("#cardPmtForm span.searchbox input.searchbox-text").attr("readonly","readonly");
					url = mainWeb+'/admin/pmt/editCardGiftPmt';
				}
				// -> 新增
				this.editCardVtgPmt = function(valueId) {
					// 清空form
					$('#cardPmtForm1').form('clear');
					fillPromotionInfo1(valueId);
					// 打开对话框
					$('#cardPmtDialog1').dialog('open')
							.dialog('setTitle', '新增');
					url = mainWeb+'/admin/pmt/editCardVtgPmt';
				}
				// -> 新增
				this.editCardExtraPmt = function(valueId) {
					// 清空form
					$('#cardPmtForm2').form('clear');
					fillPromotionInfo2(valueId);
					// 打开对话框
					$('#cardPmtDialog2').dialog('open')
							.dialog('setTitle', '新增');
					url = mainWeb+'/admin/pmt/editCardExtraPmt';
				}

				function fillPromotionInfo0(valueId) {
					// 初始化 -> 初始化类型
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
				// -> 停用
				this.editPmt = function(valueId, typeId) {
					if (1 == typeId) {
						this.editCardGiftPmt(valueId);
					} else if (2 == typeId) {
						this.editCardVtgPmt(valueId);
					} else if (3 == typeId) {
						this.editCardExtraPmt(valueId);
					}
				}

				// -> 停用
				this.stopCardPmt = function(valueId) {
					$.messager.confirm('停用', '是否确定停用?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/stopCardPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('提示',
														result.message, 'info');// 保存成功给出提示信息
												if (result.result == true) {
													cardPmtAction
															.refreshCards();// 刷新列表信息
												}
											}, 'json');
								}
							});
				}

				// -> 停用
				this.activeCardPmt = function(valueId) {
					$.messager.confirm('启用', '是否确定启用?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/activeCardPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('提示',
														result.message, 'info');// 保存成功给出提示信息
												if (result.result == true) {
													cardPmtAction
															.refreshCards();// 刷新列表信息
												}
											}, 'json');
								}
							});
				}

				// -> 保存
				this.cardSaveOrUpdate = function() {
					$('#cardPmtForm').form('submit', {
								url : url,
								onSubmit : function() {
									return cardPmtValidateForm(); // 验证表单
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('提示', data.message, 'info');// 保存成功给出提示信息
									if (data.result == true) {
										$("#cardPmtDialog").dialog('close'); // 关闭对话框
										cardPmtAction.refreshCards();// 刷新列表信息
									}
								}
							});
				}

				// -> 保存
				this.cardSaveOrUpdate1 = function() {
					$('#cardPmtForm1').form('submit', {
								url : url,
								onSubmit : function() {
									return cardPmtValidateForm1(); // 验证表单
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('提示', data.message, 'info');// 保存成功给出提示信息
									if (data.result == true) {
										$("#cardPmtDialog1").dialog('close'); // 关闭对话框
										cardPmtAction.refreshCards();// 刷新列表信息
									}
								}
							});
				}

				// -> 保存
				this.cardSaveOrUpdate2 = function() {
					$('#cardPmtForm2').form('submit', {
								url : url,
								onSubmit : function() {
									return cardPmtValidateForm2(); // 验证表单
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('提示', data.message, 'info');// 保存成功给出提示信息
									if (data.result == true) {
										$("#cardPmtDialog2").dialog('close'); // 关闭对话框
										cardPmtAction.refreshCards();// 刷新列表信息
									}
								}
							});
				}

				// -> 验证表单
				function cardPmtValidateForm() {
					var ba0 = $('#amount0').combobox('getValue');
					if ($.trim(ba0) == '') {
						$.messager.alert('提示', '充值金额未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($.trim($("#cardPmtForm input[id='goodsId0_cardpmt']").val()) == '') {
						$.messager.alert('提示', '赠品未选择!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($.trim($("#cardPmtForm input[id='count0_card']").val()) == '') {
						$.messager.alert('提示', '赠品数量未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					return true;
				}
				// -> 验证表单
				function cardPmtValidateForm1() {
					var ba0 = $('#amount1_cardpmt').combobox('getValue');
					if ($.trim(ba0) == '') {
						$.messager.alert('提示', '充值金额未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($.trim($("#cardPmtForm1 input[id='count1_card']").val()) == '') {
						$.messager.alert('提示', '赠送积分未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					return true;
				}

				// -> 验证表单
				function cardPmtValidateForm2() {
					var ba0 = $('#amount2_cardpmt').combobox('getValue');
					if ($.trim(ba0) == '') {
						$.messager.alert('提示', '充值金额未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($.trim($("#cardPmtForm2 input[id='count2_cardpmt']").val()) == '') {
						$.messager.alert('提示', '赠送金额未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					return true;
				}
			}
		});
		</script>
	</body>
</html>