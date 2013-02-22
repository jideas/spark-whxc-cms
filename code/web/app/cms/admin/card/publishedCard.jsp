<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>已发售面值卡</title>
	</head>
	<body>
		<!-- begin of 面值卡列表 -->
		<div id="distributedCardToolbar">
		<span style="float: left; padding-right: 5px;">	面值：
			<input id="selectAmountList3" name="selectAmountList3">
			开始日期：
			<input id="beginDate3" type="text" class="easyui-datebox" />
			结束日期：
			<input id="endDate3" type="text" class="easyui-datebox" />
			<input type="text" name="publishedCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="distributedCardAction.refreshCards()">卡号查询</a>
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="distributedCardAction.reDistributeCard()">重新分发</a> </span>
		</div>
		<table id="distributedCardDatagrid" toolbar="#distributedCardToolbar">
		</table>
		<!-- end of 面值卡列表 -->
		<!-- begin of 新增 -->
		<div id="reDistributeCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 500px; height: 180px; padding: 20px 10px 0px;"
			title="面值卡" buttons="#reDistributeCard-dlg-buttons" closed="true">
			<form id="reDistributeCardForm" method="post">
				<table>
					<tr>
						<td>
							站点：
						</td>
						<td>
							<input id="selectStationList2" name="stationId"
								style="width: 400px;" readonly="readonly" />
							<input id="hiddenids2" name="ids[]" value="" type="hidden" />
						</td>
					</tr>
					<tr>
						<td>
							新责任人：
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
				onclick="distributedCardAction.cardReDistribute()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#reDistributeCardDialog').dialog('close')">取
				消</a>
		</div>
		<script type="text/javascript">
// 初始化
$(function() {
	// 初始化 --> 面值卡 --> 定义面值卡列表列
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
					title : '顺序号',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'cardNo',
					title : '卡号',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'amount',
					title : '面值',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'distributedate',
					title : '分发日期',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'responsibleperson',
					title : '责任人',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'distributeperson',
					title : '分发人',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'lastDate',
					title : '有效期至',
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
						return "<a href='#' class='operateChannel' onClick=distributedCardAction.cancelCard('"
								+ value + "')>停 用</a>";
					}
				}]]
	});

	// 初始化 -> 初始化面值卡类型
	$('input#selectAmountList3').combobox({
				url : mainWeb+'/admin/card/getAmountSelectList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});

	// 监听事件 -> 面值卡类型改变
	$('#selectAmountList3').combobox({
				onSelect : function(record) {
					// 刷新面值卡
					distributedCardAction.refreshCards();
				}
			});
			$('#selectAmountList3').combobox('setValue','0');

	// 监听事件 -> 开始时间改变
	$('#beginDate3').datebox({
				onSelect : function(date) {
					// 刷新面值卡
					distributedCardAction.refreshCards();
				}
			});

	// 监听事件 -> 结束时间改变
	$('#endDate3').datebox({
				onSelect : function(date) {
					// 刷新面值卡
					distributedCardAction.refreshCards();
				}
			});

});

// 面值卡
var distributedCardAction;
$(function() {
			var url = '';
			// 面值卡 -> 初始化
			distributedCardAction = new DistributeCardAction();

			// 面值卡 -> 定义面值卡
			function DistributeCardAction() {
				// 面值卡 -> 刷新
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
							}); // 刷新面值卡列表信息
				}

				// 面值卡 -> 删除
				this.cancelCard = function(cardId) {
					$.messager.confirm('停用', '是否确定停用?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/card/cancelCard', {
												id : cardId
											}, function(result) {
												distributedCardAction
														.refreshCards();// 刷新面值卡列表信息
											}, 'json');
								}
							});
				}

				// 面值卡 -> 填充面值卡类型
				function fillCardType() {
					$('#distributedCardForm #distributedCardvaluetype')
							.combobox({
										url : mainWeb+'/admin/card/getAmountList',
										valueField : 'code',
										textField : 'title',
										editable : false
									});
				}

				// 面值卡 -> 分发
				this.reDistributeCard = function() {
					var ids = getSelectedIDS("#distributedCardDatagrid"); // 获取批量处理的ID
					// $('#hideCardId').val(ids) ;
					fillStationList2();
					if (ids == '') {
						$.messager.alert('提示', '请选择要批量分发的面值卡!', 'warning');
					} else {
						// 清空form
						$('#reDistributeCardForm').form('clear');
						// 打开对话框
						$('#reDistributeCardDialog').dialog('open').dialog(
								'setTitle', '分发面值卡');
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
				// 面值卡 -> 分发
				this.cardReDistribute = function() {
					var ids = getSelectedIDS("#distributedCardDatagrid"); // 获取批量处理的ID
					var stid = $('#selectStationList2').combobox('getValue');
					var pname = $("#personNameTextId2").val();
					$("#hiddenids2").val(ids);
					$('#reDistributeCardForm').form('submit', {
								url : url,
								onSubmit : function() {
									return publishedCardValidateForm(); // 验证表单
								},
								success : function(data) {
									$.messager.alert('提示', '面值卡保存成功!', 'info');// 保存成功给出提示信息
									$("#reDistributeCardDialog")
											.dialog('close'); // 关闭面值卡对话框
									distributedCardAction.refreshCards();// 刷新面值卡列表信息
								}
							});
				}

				// 面值卡 -> 验证表单
				function publishedCardValidateForm() {
					if ($.trim($('#selectStationList2').combobox('getValue')) == '') {
						$.messager.alert('提示', '请选择站点!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($
							.trim($("#reDistributeCardForm input[id='personNameTextId2']")
									.val()) == '') {
						$.messager.alert('提示', '请填写责任人!', 'warning'); // 数量未填写
						return false;
					}
					return true;
				}

				// 面值卡 -> 公共方法 --> 获取选中的Ids
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