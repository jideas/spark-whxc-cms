<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	</head>
	<body>
		<!-- begin of 列表 -->
		<div id="orderPmtToolbar">
			<span style="float: left; padding-right: 5px;"> 状态： <input
					id="pmtStatusSelectList" name="pmtStatusSelectList"> </span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="orderPmtAction.orderPmtGift('')">赠品促销</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="orderPmtAction.orderPmtVtg('')">积分促销</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="orderPmtAction.orderPmtFreeD()">送货上门</a> </span>
		</div>
		<table id="orderPmtDatagrid" toolbar="#orderPmtToolbar">
		</table>
		<!-- end of 列表 -->

		<!-- begin of 新增赠品促销 -->
		<div id="orderPmtDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 220px; padding: 20px 10px 0px;"
			title="1" buttons="#orderPmt-dlg-buttons" closed="true">
			<form id="orderPmtForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							订单金额下限：
						</td>
						<td>
							<input type="text" id="beginAmount0" name="beginAmount"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid0" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							订单金额上限：
						</td>
						<td>
							<input type="text" name="endAmount" id="endAmount0"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赠品：
						</td>
						<td>
							<input id="goodsName0" name="goodsName" class="easyui-searchbox"
								type="text" style="width: 400px;" />
							<input name="goodsId" id="goodsId0" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;赠品数量：
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
				onclick="orderPmtAction.cardSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orderPmtDialog').dialog('close')">取 消</a>
		</div>

		<!-- begin of 新增赠品促销 -->
		<div id="orderPmtDialog1" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 210px; padding: 20px 10px 0px;"
			title="1" buttons="#orderPmt-dlg-buttons1" closed="true">
			<form id="orderPmtForm1" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							订单金额下限：
						</td>
						<td>
							<input type="text" name="beginAmount" id="beginAmount1"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid1" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							订单金额上限：
						</td>
						<td>
							<input type="text" name="endAmount" id="endAmount1"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;赠送积分：
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
				onclick="orderPmtAction.cardSaveOrUpdate1()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orderPmtDialog1').dialog('close')">取 消</a>
		</div>
		<!-- begin of 新增送货上门促销 -->
		<div id="orderPmtDialog2" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 160px; padding: 20px 10px 0px;"
			title="1" buttons="#orderPmt-dlg-buttons2" closed="true">
			<form id="orderPmtForm2" method="post" modelAttribute="form">
				<table>
					<tr>
						<td colspan="2">
							填写整单免费送货上门的最低金额：
						</td>
					</tr>
					<tr>
						<td>
							下限值：
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
				onclick="orderPmtAction.cardSaveOrUpdate2()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#orderPmtDialog2').dialog('close')">取 消</a>
		</div>
		<script type="text/javascript">
		// 初始化
$(function() {	
	// 初始化 -->  定义列表列
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
					title : '金额下限',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'endAmount',
					title : '金额上限',
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
						var begin = "<a href='#' class='operateChannel' onClick=orderPmtAction.editPmt('"
								+ value + "'," + rec.type + ")>编辑</a>";
						if (rec.status == '启用中') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=orderPmtAction.stopPmt('"
									+ value + "')>停用</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=orderPmtAction.activePmt('"
									+ value + "')>启用</a>"
						}
					}
				}]]
	});

	// 初始化 -> 初始化类型
	$('input#pmtStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});
	// 监听事件 -> 类型改变
	$('#pmtStatusSelectList').combobox({
				onSelect : function(record) {
					// 刷新
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
	// -> 初始化
	orderPmtAction = new AddCardAction();

	// -> 定义
	function AddCardAction() {
		// -> 刷新
		this.refreshCards = function() {
			var pmtStatus = $('#pmtStatusSelectList').combobox('getValue');
			$('#orderPmtDatagrid').datagrid('reload', {
						pmtStatus : pmtStatus
					}); // 刷新列表信息
		}

		// -> 新增
		this.orderPmtGift = function(valueId) {
			// 清空form
			$('#orderPmtForm').form('clear');
			if (null != valueId && '' != valueId) {
				fillPromotionInfo0(valueId);
			}
			// 打开对话框
			$('#orderPmtDialog').dialog('open').dialog('setTitle', '新增');
			//禁用searchbox（orderPmtForm）
			$("#orderPmtForm span.searchbox input.searchbox-text").attr("readonly","readonly");
			url = mainWeb+'/admin/pmt/orderPmtGift';
		}
		// -> 新增
		this.orderPmtVtg = function(valueId) {
			// 清空form
			$('#orderPmtForm1').form('clear');
			if (null != valueId && '' != valueId) {
				fillPromotionInfo1(valueId);
			}
			// 打开对话框
			$('#orderPmtDialog1').dialog('open').dialog('setTitle', '新增');
			url = mainWeb+'/admin/pmt/orderPmtVtg';
		}
		// -> 新增
		this.orderPmtFreeD = function() {
			// 清空form
			$('#orderPmtForm2').form('clear');
			fillBeginAmountOfFreeDelibery();
			// 打开对话框
			$('#orderPmtDialog2').dialog('open').dialog('setTitle', '新增');
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

		// -> 停用
		this.editPmt = function(valueId, typeId) {
			if (1 == typeId) {
				this.orderPmtGift(valueId);
			} else if (2 == typeId) {
				this.orderPmtVtg(valueId);
			} else if (3 == typeId) {
				this.orderPmtFreeD();
			}
		}

		// -> 停用
		this.stopPmt = function(valueId) {
			$.messager.confirm('停用', '是否确定停用?', function(r) {
						if (r) {
							$.post(mainWeb+'/admin/pmt/stopPmt', {
										id : valueId
									}, function(result) {
										$.messager.alert('提示', result.message,
												'info');// 保存成功给出提示信息
										if (result.result == true) {
											orderPmtAction.refreshCards();// 刷新列表信息
										}
									}, 'json');
						}
					});
		}

		// -> 停用
		this.activePmt = function(valueId) {
			$.messager.confirm('启用', '是否确定启用?', function(r) {
						if (r) {
							$.post(mainWeb+'/admin/pmt/activePmt', {
										id : valueId
									}, function(result) {
										$.messager.alert('提示', result.message,
												'info');// 保存成功给出提示信息
										if (result.result == true) {
											orderPmtAction.refreshCards();// 刷新列表信息
										}
									}, 'json');
						}
					});
		}

		// -> 保存
		this.cardSaveOrUpdate = function() {
			$('#orderPmtForm').form('submit', {
						url : url,
						onSubmit : function() {
							return orderPmtValidateForm(); // 验证表单
						},
						success : function(data) {
							data = eval("(" + data + ")")
							$.messager.alert('提示', data.message, 'info');// 保存成功给出提示信息
							if (data.result == true) {
								$("#orderPmtDialog").dialog('close'); // 关闭对话框
								orderPmtAction.refreshCards();// 刷新列表信息
							}
						}
					});
		}

		// -> 保存
		this.cardSaveOrUpdate1 = function() {
			$('#orderPmtForm1').form('submit', {
						url : url,
						onSubmit : function() {
							return orderPmtValidateForm1(); // 验证表单
						},
						success : function(data) {
							data = eval("(" + data + ")")
							$.messager.alert('提示', data.message, 'info');// 保存成功给出提示信息
							if (data.result == true) {
								$("#orderPmtDialog1").dialog('close'); // 关闭对话框
								orderPmtAction.refreshCards();// 刷新列表信息
							}
						}
					});
		}

		// -> 保存
		this.cardSaveOrUpdate2 = function() {
			$('#orderPmtForm2').form('submit', {
						url : url,
						onSubmit : function() {
							return orderPmtValidateForm2(); // 验证表单
						},
						success : function(data) {
							data = eval("(" + data + ")")
							$.messager.alert('提示', data.message, 'info');// 保存成功给出提示信息
							if (data.result == true) {
								$("#orderPmtDialog2").dialog('close'); // 关闭对话框
								orderPmtAction.refreshCards();// 刷新列表信息
							}
						}
					});
		}

		// -> 验证表单
		function orderPmtValidateForm() {
			var ba0 = $("#orderPmtForm input[id='beginAmount0']").val();
			var ea0 = $("#orderPmtForm input[id='endAmount0']").val();
			if ($.trim(ba0) == '') {
				$.messager.alert('提示', '下限金额未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			if ($.trim(ea0) == '') {
				$.messager.alert('提示', '上限金额未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			ba0 = ba0.replace(',',"")
			ba0 = ba0.replace(',',"")
			ba0 = ba0.replace(',',"")
			ea0 = ea0.replace(',',"")
			ea0 = ea0.replace(',',"")
			ea0 = ea0.replace(',',"")
			if (parseFloat(ba0) >= parseFloat(ea0)) {
				$.messager.alert('提示', '上限金额必须大于下限金额!', 'warning'); // 面值类型未选择
				return false;
			}
			if ($.trim($("#orderPmtForm input[id='goodsId0']").val()) == '') {
				$.messager.alert('提示', '赠品未选择!', 'warning'); // 面值类型未选择
				return false;
			}
			if ($.trim($("#orderPmtForm input[id='count0']").val()) == '') {
				$.messager.alert('提示', '赠品数量未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			return true;
		}
		// -> 验证表单
		function orderPmtValidateForm1() {
			var ba1 = $("#orderPmtForm1 input[id='beginAmount1']").val();
			var ea1 = $("#orderPmtForm1 input[id='endAmount1']").val();
			if ($.trim(ba1) == '') {
				$.messager.alert('提示', '下限金额未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			if ($.trim(ea1) == '') {
				$.messager.alert('提示', '上限金额未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			ba1 = ba1.replace(',',"")
			ba1 = ba1.replace(',',"")
			ba1 = ba1.replace(',',"")
			ea1 = ea1.replace(',',"")
			ea1 = ea1.replace(',',"")
			ea1 = ea1.replace(',',"")
			if (parseFloat(ba1) >= parseFloat(ea1)) {
				$.messager.alert('提示', '上限金额必须大于下限金额!', 'warning'); // 面值类型未选择
				return false;
			}
			if ($.trim($("#orderPmtForm1 input[id='count1']").val()) == '') {
				$.messager.alert('提示', '赠送积分未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			return true;
		}
		// -> 验证表单
		function orderPmtValidateForm2() {
			if ($.trim($("#orderPmtForm2 input[id='beginAmount2']").val()) == '') {
				$.messager.alert('提示', '下限金额未填写!', 'warning'); // 面值类型未选择
				return false;
			}
			return true;
		}
	}
});
		</script>
	</body>
</html>