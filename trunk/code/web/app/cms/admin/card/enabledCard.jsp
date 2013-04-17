<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>待分发面值卡</title>
	</head> 
	<body>
		<!-- begin of 面值卡列表 -->
		<div id="activedCardToolbar">
			<span style="float: left; padding-right: 5px;">面值：
			<input id="selectAmountList2" name="selectAmountList2">
			开始日期：
			<input id="beginDate2" type="text" class="easyui-datebox" />
			结束日期：
			<input id="endDate2" type="text" class="easyui-datebox" />
			<input type="text" name="enabledCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="activedCardAction.refreshCards()">卡号查询</a>
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="activedCardAction.distributeCard()">分 发</a> </span>
		</div>
		<table id="activedCardDatagrid" toolbar="#activedCardToolbar">
		</table>
		<!-- end of 面值卡列表 -->
		<!-- begin of 新增 -->
		<div id="distributeCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 500px; height: 180px; padding: 20px 10px 0px;"
			title="面值卡" buttons="#distributeCard-dlg-buttons" closed="true">
			<form id="distributeCardForm" method="post">
				<table>
					<tr>
						<td>
							站点：
						</td>
						<td>
							<input id="selectStationList" name="stationId"
								style="width: 400px;" readonly="readonly" />
								<input id="hiddenids" name="ids[]" value="" type="hidden"/>
						</td>
					</tr>
					<tr>
						<td>
							责任人：
						</td>
						<td>
							<input id="personNameTextId" type="text" name="personName"
								style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="distributeCard-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="activedCardAction.cardDistribute()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#distributeCardDialog').dialog('close')">取
				消</a>
		</div>
		<script type="text/javascript">
		// 初始化
$(function() {
			// 初始化 --> 面值卡 --> 定义面值卡列表列
			$('#activedCardDatagrid').datagrid({
				fit : true,
				border : false,
				idField : 'recid',
				rownumbers : true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : mainWeb+'/admin/card/getActivedList',
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
							field : 'activedate',
							title : '启用日期',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'activeperson',
							title : '启用人',
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
								return "<a href='#' class='operateChannel' onClick=activedCardAction.cancelCard('"
										+ value + "')>停 用</a>";
							}
						}]]
			});

			// 初始化 -> 初始化面值卡类型
			$('input#selectAmountList2').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'cardvalue',
						textField : 'title',
						editable:false
					});

			// 监听事件 -> 面值卡类型改变
			$('#selectAmountList2').combobox({
						onSelect : function(record) {
							// 刷新面值卡
							activedCardAction.refreshCards();
						}
					});
			$('#selectAmountList2').combobox('setValue','全部');

			// 监听事件 -> 开始时间改变
			$('#beginDate2').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							activedCardAction.refreshCards();
						}
					});

			// 监听事件 -> 结束时间改变
			$('#endDate2').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							activedCardAction.refreshCards();
						}
					});

		});

// 面值卡
var activedCardAction;
$(function() {
			var url = '';
			// 面值卡 -> 初始化
			activedCardAction = new DistributeCardAction();

			// 面值卡 -> 定义面值卡
			function DistributeCardAction() {
				// 面值卡 -> 刷新
				this.refreshCards = function() {
					var cardType = $('#selectAmountList2').combobox('getValue');
					var beginDate2 = $('#beginDate2').datebox('getValue');
					var endDate2 = $('#endDate2').datebox('getValue');
					var cardNO = $.trim($("input[name='enabledCard_searchcardno']").val());
					$('#activedCardDatagrid').datagrid('clearSelections');
					$('#activedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate2,
								endDate : endDate2,
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
												activedCardAction
														.refreshCards();// 刷新面值卡列表信息
											}, 'json');
								}
							});
				}

				// 面值卡 -> 填充面值卡类型
				function fillCardType() {
					$('#activedCardForm #activedCardvaluetype').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'cardvalue',
								textField : 'title',
								editable : false
							});
				}

				// 面值卡 -> 分发
				this.distributeCard = function() {
					var ids = getSelectedIDS("#activedCardDatagrid"); // 获取批量处理的ID
					// $('#hideCardId').val(ids) ;
					fillStationList();
					if (ids == '') {
						$.messager.alert('提示', '请选择要批量分发的面值卡!', 'warning');
					} else {
						// 清空form
						$('#distributeCardForm').form('clear');
						// 打开对话框
						$('#distributeCardDialog').dialog('open').dialog(
								'setTitle', '分发面值卡');
						url = mainWeb+'/admin/card/batchDistribute';
					}
				}

				function fillStationList() {
					$('#distributeCardForm #selectStationList').combobox({
								url : mainWeb+'/admin/card/getStationList',
								valueField : 'recid',
								textField : 'stationName',
								editable : false
							});
				}
				// 面值卡 -> 分发
				this.cardDistribute = function() {
					var ids = getSelectedIDS("#activedCardDatagrid"); // 获取批量处理的ID
					var stid = $('#selectStationList').combobox('getValue');
					var pname = $("#personNameTextId").val();
					$("#hiddenids").val(ids);
					$('#distributeCardForm').form('submit', {
								url : url,
								onSubmit : function() {
									return enabledCardValidateForm(); // 验证表单
								},
								success : function(data) {
									$.messager.alert('提示', '面值卡保存成功!', 'info');// 保存成功给出提示信息
									$("#distributeCardDialog").dialog('close'); // 关闭面值卡对话框
									activedCardAction.refreshCards();// 刷新面值卡列表信息
								}
							});
				}

				// 面值卡 -> 验证表单
				function enabledCardValidateForm() {
					if ($.trim($('#selectStationList').combobox('getValue')) == '') {
						$.messager.alert('提示', '请选择站点!', 'warning'); // 面值类型未选择
						return false;
					}
					if ($
							.trim($("#distributeCardForm input[id='personNameTextId']")
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