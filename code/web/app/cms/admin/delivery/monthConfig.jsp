<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增</title>
	</head> 
	<body>
		<!-- begin of 列表 -->
		<div id="deliveryPriceToolbar">
			<span style="float: left; padding-right: 5px;"> 状态：
			<input id="deliveryStatusSelectList" name="deliveryStatusSelectList"></span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="deliveryPriceAction.saveDeliveryPrice('')">新增方案</a></span>
		</div>
		<table id="deliveryPriceDatagrid" toolbar="#deliveryPriceToolbar">
		</table>
		<!-- end of 列表 -->

		<!-- begin of 新增赠品方案 -->
		<div id="deliveryPriceDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 195px; padding: 20px 10px 0px;"
			title="1" buttons="#deliveryPrice-dlg-buttons" closed="true">
			<form id="deliveryPriceForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;送货次数：
						</td>
						<td>
							<input id="dcount0" name="dcount" type="text"
								style="width: 400px;" />
							<input type="hidden" id="hiddenrecid0" name="recid" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;销售价格：
						</td>
						<td>
							<input type="text" id="dprice0" name="dprice"
								style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;简单描述：
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
				onclick="deliveryPriceAction.deliverySaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#deliveryPriceDialog').dialog('close')">取
				消</a>
		</div>
		<script type="text/javascript">
// 初始化
$(function() {
	// 初始化 --> --> 定义列表列
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
					title : '送货次数',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'dprice',
					title : '销售价格',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'remark',
					title : '描述',
					width : 160,
					align : 'center',
					sortable : true
				}, {
					field : 'lastModify',
					title : '最后修改人',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'lastModifyDate',
					title : '最后修改日期',
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
						var begin = "<a href='#' class='operateChannel' onClick=deliveryPriceAction.editDelivery('"
								+ value + "')>编辑</a>";
						if (rec.status == '启用中') {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=deliveryPriceAction.stopDeliveryPrice('"
									+ value + "')>停用</a>"
						} else {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=deliveryPriceAction.activeDeliveryPrice('"
									+ value + "')>启用</a>"
						}
					}
				}]]
	});

	// 初始化 -> 初始化类型
	$('input#deliveryStatusSelectList').combobox({
				url : mainWeb+'/admin/pmt/getActiveStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});

	// 监听事件 -> 类型改变
	$('#deliveryStatusSelectList').combobox({
				onSelect : function(record) {
					// 刷新
					deliveryPriceAction.refreshCards();
				}
			});
});

// 
var deliveryPriceAction;
$(function() {
			var url = '';
			// -> 初始化
			deliveryPriceAction = new AddCardAction();

			// -> 定义
			function AddCardAction() {
				// -> 刷新
				this.refreshCards = function() {
					var deliveryStatus = $('#deliveryStatusSelectList')
							.combobox('getValue');
					$('#deliveryPriceDatagrid').datagrid('reload', {
								deliveryStatus : deliveryStatus
							}); // 刷新列表信息
				}

				// -> 新增
				this.saveDeliveryPrice = function(valueId) {
					// 清空form
					$('#deliveryPriceForm').form('clear');
					if (null != valueId && '' != valueId) {
						fillPromotionInfo0(valueId);
					}
					// 打开对话框
					$('#deliveryPriceDialog').dialog('open').dialog('setTitle',
							'新增');
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

				// -> 停用
				this.editDelivery = function(valueId) {
					this.saveDeliveryPrice(valueId);
				}

				// -> 停用
				this.stopDeliveryPrice = function(valueId) {
					$.messager.confirm('停用', '是否确定停用?', function(r) {
								if (r) {
									$
											.post(
													mainWeb+'/admin/delivery/stopDeliveryPrice',
													{
														recid : valueId
													}, function(result) {
														$.messager.alert('提示',
																result.message,
																'info');// 保存成功给出提示信息
														if (result.result == true) {
															deliveryPriceAction
																	.refreshCards();// 刷新列表信息
														}
													}, 'json');
								}
							});
				}

				// -> 停用
				this.activeDeliveryPrice = function(valueId) {
					$.messager.confirm('启用', '是否确定启用?', function(r) {
								if (r) {
									$
											.post(
													mainWeb+'/admin/delivery/activeDeliveryPrice',
													{
														recid : valueId
													}, function(result) {
														$.messager.alert('提示',
																result.message,
																'info');// 保存成功给出提示信息
														if (result.result == true) {
															deliveryPriceAction
																	.refreshCards();// 刷新列表信息
														}
													}, 'json');
								}
							});
				}

				// -> 保存
				this.deliverySaveOrUpdate = function() {
					$('#deliveryPriceForm').form('submit', {
								url : url,
								onSubmit : function() {
									return deliveryPriceValidateForm(); // 验证表单
								},
								success : function(data) {
									data = eval("(" + data + ")")
									$.messager
											.alert('提示', data.message, 'info');// 保存成功给出提示信息
									if (data.result == true) {
										$("#deliveryPriceDialog")
												.dialog('close'); // 关闭对话框
										deliveryPriceAction.refreshCards();// 刷新列表信息
									}
								}
							});
				}

				// -> 验证表单
				function deliveryPriceValidateForm() {
					var disrate = $('#dcount0').val();
					if ($.trim(disrate) == '') {
						$.messager.alert('提示', '送货次数未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($.trim($('#dprice0').val()) == '') {
						$.messager.alert('提示', '销售价格未填写!', 'warning'); // 面值类型未选择
						return false;
					}
					return true;
				}
			}
		});
		</script>
	</body>
</html>