<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>已作废面值卡</title>
	</head>
	<body>
		<!-- begin of 面值卡列表 -->
		<div id="stopedCardToolbar">
			<span style="float: left; padding-right: 5px;">面值： <input
					id="selectAmountList4" name="selectAmountList4"> 开始日期： <input
					id="beginDate4" type="text" class="easyui-datebox" /> 结束日期： <input
					id="endDate4" type="text" class="easyui-datebox" />
					<input type="text" name="stopedCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="stopedCardAction.refreshCards()">卡号查询</a>
			</span>
		</div>
		<table id="stopedCardDatagrid" toolbar="#stopedCardToolbar">
		</table>
		<!-- end of 面值卡列表 -->
		<script type="text/javascript">
// 初始化
$(function() {
			// 初始化 --> 面值卡 --> 定义面值卡列表列
			$('#stopedCardDatagrid').datagrid({
						fit : true,
						border : false,
						idField : 'recid',
						rownumbers : true,
						fitColumns : true,
						remoteSort : false,
						pagination : true,singleSelect: true,
						url : mainWeb+'/admin/card/getStopedList',
						columns : [[ {
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
									field : 'stopdate',
									title : '停用日期',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'usedperson',
									title : '停用人',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'status',
									title : '状态',
									width : 100,
									align : 'center',
									sortable : true
								}]]
					});

			// 初始化 -> 初始化面值卡类型
			$('input#selectAmountList4').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'code',
						textField : 'title',
						editable:false
					});

			// 监听事件 -> 面值卡类型改变
			$('#selectAmountList4').combobox({
						onSelect : function(record) {
							// 刷新面值卡
							stopedCardAction.refreshCards();
						}
					});
			$('#selectAmountList4').combobox('setValue','0');

			// 监听事件 -> 开始时间改变
			$('#beginDate4').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							stopedCardAction.refreshCards();
						}
					});

			// 监听事件 -> 结束时间改变
			$('#endDate4').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							stopedCardAction.refreshCards();
						}
					});

		});

// 面值卡
var stopedCardAction;
$(function() {
			var url = '';
			// 面值卡 -> 初始化
			stopedCardAction = new ReActiveCardAction();

			// 面值卡 -> 定义面值卡
			function ReActiveCardAction() {
				// 面值卡 -> 刷新
				this.refreshCards = function() {
					var cardType = $('#selectAmountList4').combobox('getValue');
					var beginDate4 = $('#beginDate4').datebox('getValue');
					var endDate4 = $('#endDate4').datebox('getValue');
						var cardNO = $.trim($("input[name='stopedCard_searchcardno']").val());
					$('#stopedCardDatagrid').datagrid('clearSelections');
					$('#stopedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate4,
								endDate : endDate4,
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
												stopedCardAction.refreshCards();// 刷新面值卡列表信息
											}, 'json');
								}
							});
				}

				// 面值卡 -> 印发
				this.activeCard = function() {
					var ids = getSelectedIDS("#stopedCardDatagrid"); // 获取批量处理的ID
					if (ids == '') {
						$.messager.alert('提示', '请选择要批量启用的面值卡!', 'warning');
					} else {
						$.post(mainWeb+'/admin/card/batchReActive', {
									'ids[]' : ids
								}, function() {
									stopedCardAction.refreshCards();// 刷新面值卡列表信息
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