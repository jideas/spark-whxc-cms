<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新增面值卡</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	</head>
	<body>
		<!-- begin of 面值卡列表 -->
		<div id="addCardToolbar">
			<span style="float: left; padding-right: 5px;"> 面值： <input
					id="addValueType" name="addValueType"> 开始日期： <input
					id="beginDate" type="text" class="easyui-datebox" /> 结束日期： <input
					id="endDate" type="text" class="easyui-datebox" />
			<input type="text" name="addCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addCardAction.refreshCards()">卡号查询</a>	
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="addCardAction.addCard()">新 增</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="addCardAction.importCards()">导 入</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="addCardAction.printCard()">印 发</a> </span>
		</div>
		<table id="addCardDatagrid" toolbar="#addCardToolbar">
		</table>
		<!-- end of 面值卡列表 -->

		<!-- begin of 新增 -->
		<div id="addCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 250px; padding: 20px 10px 0px;"
			title="面值卡" buttons="#addCard-dlg-buttons" closed="true">
			<form id="addCardForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;面值：
						</td>
						<td>
							<input id="addCardvaluetype" name="amount" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;数量：
						</td>
						<td>
							<input type="text" name="count" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							有效期至：
						</td>
						<td>
							<input type="text" class="easyui-datebox" name="lastDate"
								id="lastDate0" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;秘钥：
						</td>
						<td>
							<input type="password" name="secretKey" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							确定秘钥：
						</td>
						<td>
							<input type="password" name="reSecretKey" style="width: 400px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="addCard-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				id="addCard-dlg-buttons01"
				onclick="addCardAction.cardSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				id="addCard-dlg-buttons02"
				onclick="javascript:$('#addCardDialog').dialog('close')">取 消</a>
		</div>
		<div id="ImportCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 150px; padding: 20px 10px 0px;"
			title="面值卡" buttons="#ImportCardDialog-dlg-buttons" closed="true">
			<form id="importCardForm" method="post" modelAttribute="form"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;导入excel：
						</td>
						<td>
							<input id="importCardExcelInput" name="excel"
								style="width: 350px;" type="file" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="ImportCardDialog-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				id="ImportCardDialog-dlg-buttons01"
				onclick="addCardAction.sureImportSubmit()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				id="ImportCardDialog-dlg-buttons02"
				onclick="javascript:$('#ImportCardDialog').dialog('close')">取 消</a>
		</div>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript" src="<%=mainWeb%>/scripts/Lodop/LodopFuncs.js"></script>
		<script type="text/javascript">
// 初始化
$(function() {
			// 初始化 --> 面值卡 --> 定义面值卡列表列
			$('#addCardDatagrid').datagrid({
				fit : true,
				border : false,
				idField : 'recid',
				rownumbers : true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : mainWeb+'/admin/card/getNewList',
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
						},{
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
								return "<a href='#' class='operateChannel' onClick=addCardAction.deleteCard('"
										+ value + "')>删除</a>";
							}
						}]]
			});

			// 初始化 -> 初始化面值卡类型
			$('input#addValueType').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'cardvalue',
						textField : 'title',
						editable:false					
					});

			// 监听事件 -> 面值卡类型改变
			$('#addValueType').combobox({
						onSelect : function(record) {
							// 刷新面值卡
							addCardAction.refreshCards();
						}
					});
			$('#addValueType').combobox('setValue','全部');
			// 监听事件 -> 开始时间改变
			$('#beginDate').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							addCardAction.refreshCards();
						}
					});

			// 监听事件 -> 结束时间改变
			$('#endDate').datebox({
						onSelect : function(date) {
							// 刷新面值卡
							addCardAction.refreshCards();
						}
					});

		});
$(function() {
	var url = '';
	// 面值卡 -> 初始化
	addCardAction = new AddCardAction();

	// 面值卡 -> 定义面值卡
	function AddCardAction() {
		// 面值卡 -> 刷新
		this.refreshCards = function() {
			var cardType = $('#addValueType').combobox('getValue');
			var beginDate = $('#beginDate').datebox('getValue');
			var endDate = $('#endDate').datebox('getValue');
			var cardNO = $.trim($("input[name='addCard_searchcardno']").val());
			$('#addCardDatagrid').datagrid('clearSelections');
			$('#addCardDatagrid').datagrid('reload', {
						cardType : cardType,
						beginDate : beginDate,
						endDate : endDate,
						cardNO : cardNO
					}); // 刷新面值卡列表信息
		}
				
		this.importCards = function(){
			$('#ImportCardDialog').dialog('open').dialog('setTitle', '导入面值卡');
		}

		this.sureImportSubmit = function(){
			$('#importCardForm').form('submit', {
						url : mainWeb+'/admin/card/importCard',
						onSubmit : function() {
							var canSubmit =  $.trim($('#importCardExcelInput').val())!='';
							$('#ImportCardDialog-dlg-buttons01').linkbutton("disable");
							$('#ImportCardDialog-dlg-buttons02').linkbutton("disable");
							return canSubmit;// 验证表单
						},
						success : function(data) { 
							$('#ImportCardDialog-dlg-buttons01').linkbutton("enable");
							$('#ImportCardDialog-dlg-buttons02').linkbutton("enable"); 
							var count = parseInt(data);
							if(count==0){
								$.messager.alert('提示',"导入失败，请检查导入内容", 'info');// 保存成功给出提示信息
							}else{
								$.messager.alert('提示', "成功导入"+count+"张面值卡，请在已发售页签中查看。", 'info');// 保存成功给出提示信息
							}
							$("#ImportCardDialog").dialog('close'); // 关闭面值卡对话框 
							$('#importCardExcelInput').val('');
						}
					});
		}
		// 面值卡 -> 新增
		this.addCard = function() {
			// 清空form
			$('#addCardForm').form('clear');
			// 填充面值卡类型
			fillCardType();
			// 打开对话框
			$('#addCardDialog').dialog('open').dialog('setTitle', '新增面值卡');
			url = mainWeb+'/admin/card/addCard';
		}

		// 面值卡 -> 删除
		this.deleteCard = function(cardId) {
			$.messager.confirm('删除', '是否确定删除?', function(r) {
						if (r) {
							$.post(mainWeb+'/admin/card/deleteCard', {
										id : cardId
									}, function(result) {
										addCardAction.refreshCards();// 刷新面值卡列表信息
									}, 'json');
						}
					});
		}

		// 面值卡 -> 填充面值卡类型
		function fillCardType() {
			$('#addCardForm #addCardvaluetype').combobox({
						url : mainWeb+'/admin/card/getAmountList',
						valueField : 'cardvalue',
						textField : 'title',
						editable:false
					});
		}

		// 面值卡 -> 保存
		this.cardSaveOrUpdate = function() {
			$('#addCardForm').form('submit', {
						url : url,
						onSubmit : function() {
							var canSubmit =  addCardValidateForm();
							$('#addCard-dlg-buttons01').linkbutton('disable');
							$('#addCard-dlg-buttons02').linkbutton('disable');
							return canSubmit;// 验证表单
						},
						success : function(data) {
							$('#addCard-dlg-buttons01').linkbutton('enable');
							$('#addCard-dlg-buttons02').linkbutton('enable');
							$.messager.alert('提示', '面值卡保存成功!', 'info');// 保存成功给出提示信息
							$("#addCardDialog").dialog('close'); // 关闭面值卡对话框
							addCardAction.refreshCards();// 刷新面值卡列表信息
						}
					});
		}

		// 面值卡 -> 验证表单
		function addCardValidateForm() {
			if ($.trim($('#addCardvaluetype').combobox('getValue')) == '') {
				$.messager.alert('提示', '面值类型未选择!', 'warning'); // 面值类型未选择
				return false;
			}
			if ($.trim($("#addCardForm input[name='count']").val()) == '') {
				$.messager.alert('提示', '数量未填写!', 'warning'); // 数量未填写
				return false;
			} 
			if ($('#lastDate0').datebox('getValue') == '') {
				$.messager.alert('提示', '有效期截至日期未填写!', 'warning'); // 数量未填写
				return false;
			} 
			if ($.trim($("#addCardForm input[name='secretKey']").val()) != $
					.trim($("#addCardForm input[name='reSecretKey']").val())) {
				$.messager.alert('提示', '密钥不一致!', 'warning'); // 密钥不一致
				$("#addCardForm input[name='secretKey']").val("");
				$("#addCardForm input[name='reSecretKey']").val("");
				return false;
			}
			return true;
		}

		// 面值卡 -> 印发
		this.printCard = function() {
			var ids = getSelectedIDS("#addCardDatagrid"); // 获取批量处理的ID
			if (ids == '') {
				$.messager.alert('提示', '请选择要批量印发的面值卡!', 'warning');
			} else {
				$.post(mainWeb+'/admin/card/batchPrint',{'ids[]' : ids},function(data) {
					data = eval("("+data+")");
					if(data.result){
						addCardAction.refreshCards();// 刷新面值卡列表信息
						//获取要打印的面值卡
						$.post(mainWeb+'/admin/card/getVoListByIds',{'ids[]' : ids},function(data) {
							data = eval("("+data+")");
							createPrintPage1(data);
						});
					}
				});
			}
		}
		
		//面值卡 -> 创建打印页面
		function createPrintPage1(data){
			var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
			LODOP.PRINT_INIT("面值卡打印...");
			LODOP.SET_PRINT_PAGESIZE(1,'75mm','101mm','CreateCustomPage');
			LODOP.SET_PRINT_STYLE("FontSize",12);
			LODOP.SET_PRINT_STYLE("FontColor","#000000");
			var invalidDate = new Date();
			for(var i = 0;i < data.returnObj.length;i++){
				invalidDate.setTime(data.returnObj[i].lastDate);
				var tempDate = invalidDate.getFullYear() + "-" + (invalidDate.getMonth()+1) + "-" + invalidDate.getDate();
				LODOP.NewPage();

				LODOP.ADD_PRINT_TEXT('30mm','30mm','40mm','5mm',data.returnObj[i].amount);
				LODOP.ADD_PRINT_TEXT('36.5mm','30mm','40mm','5mm',data.returnObj[i].ordinal);
				LODOP.ADD_PRINT_TEXT('43mm','30mm','40mm','5mm',tempDate);
				
				LODOP.ADD_PRINT_TEXT('64.5mm','16mm','50mm','5mm','卡号：'+data.returnObj[i].cardno);
				LODOP.ADD_PRINT_TEXT('74mm','16mm','50mm','5mm','密码：'+data.returnObj[i].password);
			}
			//LODOP.PREVIEW();
			LODOP.PRINT();
		}

		function CreatePrintPage(value, number, productDate, enddate, card, ps) {
			LODOP.PRINT_INITA(4, 3, 1200, 1600, "套打EMS的模板");
			//LODOP.ADD_PRINT_SETUP_BKIMG("C:\\Users\\mengyongfeng\\Desktop\\首页.jpg");
			LODOP.SET_PRINT_PAGESIZE(1, 1200, 1600, "");
			LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW", true);
			LODOP.SET_SHOW_MODE("BKIMG_PRINT", true);
			LODOP.ADD_PRINT_TEXT(174, 160, 150, 30, value);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			LODOP.SET_PRINT_STYLEA(0, "FontColor", "#FF00FF");
			LODOP.ADD_PRINT_TEXT(211, 160, 150, 30, number);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			LODOP.SET_PRINT_STYLEA(0, "FontColor", "#FF00FF");
			LODOP.ADD_PRINT_TEXT(248, 160, 150, 30, productDate);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			LODOP.SET_PRINT_STYLEA(0, "FontColor", "#FF00FF");
			LODOP.ADD_PRINT_TEXT(286, 160, 150, 30, enddate);
			LODOP.SET_PRINT_STYLEA(0, "FontSize", 12);
			LODOP.SET_PRINT_STYLEA(0, "FontColor", "#FF00FF");
			LODOP.ADD_PRINT_TEXT(368, 78, 250, 35, card);
			LODOP.ADD_PRINT_TEXT(420, 78, 250, 31, ps);
		};

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