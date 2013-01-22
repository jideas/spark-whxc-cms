<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品促销</title>
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
		<!-- begin of 列表 -->
		<div id="goodsPmtToolbar">
			<span style="float: left; padding-right: 5px;">状态： <input
					id="goodspmtStatusSelectList" name="goodspmtStatusSelectList"> </span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="goodsPmtAction.saveGoodsPmt('')">新增促销</a> </span>
		</div>
		<table id="goodsPmtDatagrid" toolbar="#goodsPmtToolbar">
		</table>
		<!-- end of 列表 -->

		<!-- begin of 新增赠品促销 -->
		<div id="goodsPmtDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 248px; padding: 20px 10px 0px;"
			title="1" buttons="#goodsPmt-dlg-buttons" closed="true">
			<form id="goodsPmtForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;促销商品：
						</td>
						<td>
							<input id="goodsName0_goodspmt" name="goodsName" class="easyui-searchbox"
								type="text" style="width: 400px;" />
							<input name="goodsId" id="goodsId0_goodspmt" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;开始时间：
						</td>
						<td>
							<input type="text" class="easyui-datetimebox" id="beginTime0"
								name="beginTime" style="width: 400px;" readonly="readonly" />
							<input type="hidden" id="hiddenrecid0_goodsPmt" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;结束时间：
						</td>
						<td>
							<input type="text" class="easyui-datetimebox" name="endTime"
								id="endTime0_goodsPmt" style="width: 400px;" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;商品折扣(%)：
						</td>
						<td>
							<input type="text" name="disrate" id="disrate0_goodsPmt"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;促销数量：
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
				onclick="goodsPmtAction.pmtSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#goodsPmtDialog').dialog('close')">取 消</a>
		</div>
		<script type="text/javascript">// 初始化
$(function() {
	// 初始化 --> --> 定义列表列
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
					title : '商品名称',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'beginTime',
					title : '开始时间',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'endTime',
					title : '结束时间',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'disRate',
					title : '商品折扣',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'count',
					title : '促销数量',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'salesCount',
					title : '已售数量',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'reserveCount',
					title : '剩余数量',
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
						var begin = "<a href='#' class='operateChannel' onClick=goodsPmtAction.editPmt('"
								+ value + "')>编辑</a>";
						if (rec.status == '启用中') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=goodsPmtAction.stopGoodsPmt('"
									+ value + "')>停用</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=goodsPmtAction.activeGoodsPmt('"
									+ value + "')>启用</a>"
						}
					}
				}]]
	});

	// 初始化 -> 初始化类型
	$('input#goodspmtStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});
	// 监听事件 -> 类型改变
	$('#goodspmtStatusSelectList').combobox({
				onSelect : function(record) {
					// 刷新
					goodsPmtAction.refreshCards();
				}
			});
	$('#goodspmtStatusSelectList').combobox('setValue','true');

	$("#goodsName0_goodspmt").searchbox({
		searcher : function(value, name) {
			// 这里写弹出选择框的代码
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
			// -> 初始化
			goodsPmtAction = new AddCardAction();
			// -> 定义
			function AddCardAction() {
				// -> 刷新
				this.refreshCards = function() {
					var pmtStatus = $('#goodspmtStatusSelectList')
							.combobox('getValue');
					$('#goodsPmtDatagrid').datagrid('reload', {
								pmtStatus : pmtStatus
							}); // 刷新列表信息
				}

				// -> 新增
				this.saveGoodsPmt = function(valueId) {
					// 清空form
					$('#goodsPmtForm').form('clear');
					if (null != valueId && '' != valueId) {
						fillPromotionInfo0(valueId);
					}
					// 打开对话框
					$('#goodsPmtDialog').dialog('open')
							.dialog('setTitle', '商品促销');
					//禁用searchbox(goodsPmtForm)
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

				// -> 停用
				this.editPmt = function(valueId) {
					this.saveGoodsPmt(valueId);
				}

				// -> 停用
				this.stopGoodsPmt = function(valueId) {
					$.messager.confirm('停用', '是否确定停用?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/stopGoodsPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('提示',
														result.message, 'info');// 保存成功给出提示信息
												if (result.result == true) {
													goodsPmtAction
															.refreshCards();// 刷新列表信息
												}
											}, 'json');
								}
							});
				}

				// -> 停用
				this.activeGoodsPmt = function(valueId) {
					$.messager.confirm('启用', '是否确定启用?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/pmt/activeGoodsPmt', {
												id : valueId
											}, function(result) {
												$.messager.alert('提示',
														result.message, 'info');// 保存成功给出提示信息
												if (result.result == true) {
													goodsPmtAction
															.refreshCards();// 刷新列表信息
												}
											}, 'json');
								}
							});
				}

				// -> 保存
				this.pmtSaveOrUpdate = function() {
					$('#goodsPmtForm').form('submit', {
								url : url,
								onSubmit : function() {
									return goodsPmtvalidateForm(); // 验证表单
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('提示', data.message, 'info');// 保存成功给出提示信息
									if (data.result == true) {
										$("#goodsPmtDialog").dialog('close'); // 关闭对话框
										goodsPmtAction.refreshCards();// 刷新列表信息
									}
								}
							});
				}

				// -> 验证表单
				function goodsPmtvalidateForm() {

					if ($.trim($("#goodsPmtForm input[id='goodsId0_goodspmt']").val()) == '') {
						$.messager.alert('提示', '促销商品未选择!', 'warning'); // 面值类型未选择
						return false;
					}
					var beginDate = $('#beginTime0').datetimebox('getValue');
					var endDate = $('#endTime0_goodsPmt').datetimebox('getValue');
					if (beginDate == '') {
						$.messager.alert('提示', '开始时间未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					if (endDate == '') {
						$.messager.alert('提示', '结束时间未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					var disrate = $("#goodsPmtForm input[id='disrate0_goodsPmt']").val();
					if ($.trim(disrate) == '') {
						$.messager.alert('提示', '商品折扣未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					if (parseFloat(disrate) >= 100 || parseFloat(disrate) <= 0) {
						$.messager.alert('提示', '请正确填写商品折扣!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($.trim($("#goodsPmtForm input[id='count0_goodsPmt']").val()) == '') {
						$.messager.alert('提示', '促销数量未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					return true;
				}
			}
		});
		</script>
	</body>
</html>