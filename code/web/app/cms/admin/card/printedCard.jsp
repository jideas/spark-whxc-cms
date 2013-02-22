<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>已打印面值卡</title>
	</head>
	<body>
		<!-- begin of 面值卡列表 -->
		<div id="printedCardToolbar">
			<span style="float: left; padding-right: 5px;">面值： <input
					id="selectAmountList" name="selectAmountList"> 开始日期： <input
					id="beginDate1" type="text" class="easyui-datebox" /> 结束日期： <input
					id="endDate1" type="text" class="easyui-datebox" />
			<input type="text" name="printedCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="printedCardAction.refreshCards()">卡号查询</a>		
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="printedCardAction.activeCard()">启 用</a> </span>
		</div>
		<table id="printedCardDatagrid" toolbar="#printedCardToolbar">
		</table>
		<!-- end of 面值卡列表 -->
		<script type="text/javascript">
// 初始化
$(function() {
			// 初始化 --> 面值卡 --> 定义面值卡列表列
			$('#printedCardDatagrid').datagrid({
				fit : true,
				border : false,
				idField : 'recid',
				rownumbers : true,
				fitColumns : true,
				remoteSort : false,
				pagination : true, 
				url : mainWeb+'/admin/card/getPrintedList',
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
							field : 'createDate',
							title : '生产日期',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'creator',
							title : '创建人',
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
								return "<a href='#' class='operateChannel' onClick=printedCardAction.deleteCard('"
										+ value + "')>删除</a>";
							}
						}]]
			});

			// 初始化 -> 初始化面值卡类型
			$('input#selectAmountList').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'code',
						textField : 'title',
						editable:false
					});

			// 监听事件 -> 面值卡类型改变
			$('#selectAmountList').combobox({
						onSelect : function(record) {
							// 刷新面值卡
							printedCardAction.refreshCards();
						}
					});
			$('#selectAmountList').combobox('setValue','0');

			// 监听事件 -> 开始时间改变
			$('#beginDate1').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							printedCardAction.refreshCards();
						}
					});

			// 监听事件 -> 结束时间改变
			$('#endDate1').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							printedCardAction.refreshCards();
						}
					});

		});

// 面值卡
var printedCardAction;
$(function() {
			var url = '';
			// 面值卡 -> 初始化
			printedCardAction = new AddCardAction();

			// 面值卡 -> 定义面值卡
			function AddCardAction() {
				// 面值卡 -> 刷新
				this.refreshCards = function() {
					var cardType = $('#selectAmountList').combobox('getValue');
					var beginDate1 = $('#beginDate1').datebox('getValue');
					var endDate1 = $('#endDate1').datebox('getValue');
					var cardNO = $.trim($("input[name='printedCard_searchcardno']").val());
					$('#printedCardDatagrid').datagrid('clearSelections');
					$('#printedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate1,
								endDate : endDate1,
								cardNO : cardNO
							}); // 刷新面值卡列表信息
				}

				// 面值卡 -> 删除
				this.deleteCard = function(cardId) {
					$.messager.confirm('删除', '是否确定删除?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/card/deleteCard', {
												id : cardId
											}, function(result) {
												printedCardAction
														.refreshCards();// 刷新面值卡列表信息
											}, 'json');
								}
							});
				}

				// 面值卡 -> 填充面值卡类型
				function fillCardType() {
					$('#printedCardForm #printedCardvaluetype').combobox({
								url : mainWeb+'/admin/card/getAmountList',
								valueField : 'code',
								textField : 'title',
								editable:false
							});
				}

				// 面值卡 -> 印发
				this.activeCard = function() {
					var ids = getSelectedIDS("#printedCardDatagrid"); // 获取批量处理的ID
					if (ids == '') {
						$.messager.alert('提示', '请选择要批量启用的面值卡!', 'warning');
					} else {
						$.post(mainWeb+'/admin/card/batchActive', {
									'ids[]' : ids
								}, function() {
									printedCardAction.refreshCards();// 刷新面值卡列表信息
								});
					}
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