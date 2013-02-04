<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN">
<html>
	<head>
		<title>已使用面值卡</title>
	</head> 
	<body>
		<!-- begin of 面值卡列表 -->
		<div id="usedCardToolbar">
			面值：
			<input id="selectAmountList5" name="selectAmountList5">
			开始日期：
			<input id="beginDate5" type="text" class="easyui-datebox" />
			结束日期：
			<input id="endDate5" type="text" class="easyui-datebox" />
		</div>
		<table id="usedCardDatagrid" toolbar="#usedCardToolbar">
		</table>
		<!-- end of 面值卡列表 -->
		<script type="text/javascript" >
		// 初始化
$(function() {
			// 初始化 --> 面值卡 --> 定义面值卡列表列
			$('#usedCardDatagrid').datagrid({
						fit : true,
						border : false,
						idField : 'recid',
						rownumbers : true,
						fitColumns : true,
						remoteSort : false,
						pagination : true,singleSelect: true,
						url : mainWeb+'/admin/card/getUsedList',
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
									field : 'useddate',
									title : '使用日期',
									width : 100,
									align : 'center',
									sortable : true
								}, {
									field : 'usedperson',
									title : '会员名',
									width : 100,
									align : 'center',
									sortable : true
								}]]
					});

			// 初始化 -> 初始化面值卡类型
			$('input#selectAmountList5').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'code',
						textField : 'title',
						editable:false
					});

			// 监听事件 -> 面值卡类型改变
			$('#selectAmountList5').combobox({
						onSelect : function(record) {
							// 刷新面值卡
							usedCardAction.refreshCards();
						}
					});
			$('#selectAmountList5').combobox('setValue','0');
			// 监听事件 -> 开始时间改变
			$('#beginDate5').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							usedCardAction.refreshCards();
						}
					});

			// 监听事件 -> 结束时间改变
			$('#endDate5').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							usedCardAction.refreshCards();
						}
					});

		});

// 面值卡
var usedCardAction;
$(function() {
			var url = '';
			// 面值卡 -> 初始化
			usedCardAction = new ReActiveCardAction();

			// 面值卡 -> 定义面值卡
			function ReActiveCardAction() {
				// 面值卡 -> 刷新
				this.refreshCards = function() {
					var cardType = $('#selectAmountList5').combobox('getValue');
					var beginDate5 = $('#beginDate5').datebox('getValue');
					var endDate5 = $('#endDate5').datebox('getValue');
					$('#usedCardDatagrid').datagrid('clearSelections');
					$('#usedCardDatagrid').datagrid('reload', {
								cardType : cardType,
								beginDate : beginDate5,
								endDate : endDate5
							}); // 刷新面值卡列表信息
				}
			}
		});
		
		</script>
	</body>
</html>