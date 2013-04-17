<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>������ֵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	</head>
	<body>
		<!-- begin of ��ֵ���б� -->
		<div id="addCardToolbar">
			<span style="float: left; padding-right: 5px;"> ��ֵ�� <input
					id="addValueType" name="addValueType"> ��ʼ���ڣ� <input
					id="beginDate" type="text" class="easyui-datebox" /> �������ڣ� <input
					id="endDate" type="text" class="easyui-datebox" />
			<input type="text" name="addCard_searchcardno" style="width:100px;border:1px solid #CCCCCC;"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="addCardAction.refreshCards()">���Ų�ѯ</a>	
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="addCardAction.addCard()">�� ��</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-add"
				onclick="addCardAction.importCards()">�� ��</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-print"
				onclick="addCardAction.printCard()">ӡ ��</a> </span>
		</div>
		<table id="addCardDatagrid" toolbar="#addCardToolbar">
		</table>
		<!-- end of ��ֵ���б� -->

		<!-- begin of ���� -->
		<div id="addCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 250px; padding: 20px 10px 0px;"
			title="��ֵ��" buttons="#addCard-dlg-buttons" closed="true">
			<form id="addCardForm" method="post" modelAttribute="form">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��ֵ��
						</td>
						<td>
							<input id="addCardvaluetype" name="amount" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;������
						</td>
						<td>
							<input type="text" name="count" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							��Ч������
						</td>
						<td>
							<input type="text" class="easyui-datebox" name="lastDate"
								id="lastDate0" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;��Կ��
						</td>
						<td>
							<input type="password" name="secretKey" style="width: 400px;" />
						</td>
					</tr>
					<tr>
						<td>
							ȷ����Կ��
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
				onclick="addCardAction.cardSaveOrUpdate()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				id="addCard-dlg-buttons02"
				onclick="javascript:$('#addCardDialog').dialog('close')">ȡ ��</a>
		</div>
		<div id="ImportCardDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 150px; padding: 20px 10px 0px;"
			title="��ֵ��" buttons="#ImportCardDialog-dlg-buttons" closed="true">
			<form id="importCardForm" method="post" modelAttribute="form"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;����excel��
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
				onclick="addCardAction.sureImportSubmit()">ȷ ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				id="ImportCardDialog-dlg-buttons02"
				onclick="javascript:$('#ImportCardDialog').dialog('close')">ȡ ��</a>
		</div>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript" src="<%=mainWeb%>/scripts/Lodop/LodopFuncs.js"></script>
		<script type="text/javascript">
// ��ʼ��
$(function() {
			// ��ʼ�� --> ��ֵ�� --> ������ֵ���б���
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
							title : '˳���',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'cardNo',
							title : '����',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'amount',
							title : '��ֵ',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'createDate',
							title : '��������',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'creator',
							title : '������',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'lastDate',
							title : '��Ч����',
							width : 100,
							align : 'center',
							sortable : true
						},{
							field : 'status',
							title : '״̬',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'recid',
							title : '����',
							width : 100,
							align : 'center',
							formatter : function(value, rec) {
								return "<a href='#' class='operateChannel' onClick=addCardAction.deleteCard('"
										+ value + "')>ɾ��</a>";
							}
						}]]
			});

			// ��ʼ�� -> ��ʼ����ֵ������
			$('input#addValueType').combobox({
						url : mainWeb+'/admin/card/getAmountSelectList',
						valueField : 'cardvalue',
						textField : 'title',
						editable:false					
					});

			// �����¼� -> ��ֵ�����͸ı�
			$('#addValueType').combobox({
						onSelect : function(record) {
							// ˢ����ֵ��
							addCardAction.refreshCards();
						}
					});
			$('#addValueType').combobox('setValue','ȫ��');
			// �����¼� -> ��ʼʱ��ı�
			$('#beginDate').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							addCardAction.refreshCards();
						}
					});

			// �����¼� -> ����ʱ��ı�
			$('#endDate').datebox({
						onSelect : function(date) {
							// ˢ����ֵ��
							addCardAction.refreshCards();
						}
					});

		});
$(function() {
	var url = '';
	// ��ֵ�� -> ��ʼ��
	addCardAction = new AddCardAction();

	// ��ֵ�� -> ������ֵ��
	function AddCardAction() {
		// ��ֵ�� -> ˢ��
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
					}); // ˢ����ֵ���б���Ϣ
		}
				
		this.importCards = function(){
			$('#ImportCardDialog').dialog('open').dialog('setTitle', '������ֵ��');
		}

		this.sureImportSubmit = function(){
			$('#importCardForm').form('submit', {
						url : mainWeb+'/admin/card/importCard',
						onSubmit : function() {
							var canSubmit =  $.trim($('#importCardExcelInput').val())!='';
							$('#ImportCardDialog-dlg-buttons01').linkbutton("disable");
							$('#ImportCardDialog-dlg-buttons02').linkbutton("disable");
							return canSubmit;// ��֤��
						},
						success : function(data) { 
							$('#ImportCardDialog-dlg-buttons01').linkbutton("enable");
							$('#ImportCardDialog-dlg-buttons02').linkbutton("enable"); 
							var count = parseInt(data);
							if(count==0){
								$.messager.alert('��ʾ',"����ʧ�ܣ����鵼������", 'info');// ����ɹ�������ʾ��Ϣ
							}else{
								$.messager.alert('��ʾ', "�ɹ�����"+count+"����ֵ���������ѷ���ҳǩ�в鿴��", 'info');// ����ɹ�������ʾ��Ϣ
							}
							$("#ImportCardDialog").dialog('close'); // �ر���ֵ���Ի��� 
							$('#importCardExcelInput').val('');
						}
					});
		}
		// ��ֵ�� -> ����
		this.addCard = function() {
			// ���form
			$('#addCardForm').form('clear');
			// �����ֵ������
			fillCardType();
			// �򿪶Ի���
			$('#addCardDialog').dialog('open').dialog('setTitle', '������ֵ��');
			url = mainWeb+'/admin/card/addCard';
		}

		// ��ֵ�� -> ɾ��
		this.deleteCard = function(cardId) {
			$.messager.confirm('ɾ��', '�Ƿ�ȷ��ɾ��?', function(r) {
						if (r) {
							$.post(mainWeb+'/admin/card/deleteCard', {
										id : cardId
									}, function(result) {
										addCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
									}, 'json');
						}
					});
		}

		// ��ֵ�� -> �����ֵ������
		function fillCardType() {
			$('#addCardForm #addCardvaluetype').combobox({
						url : mainWeb+'/admin/card/getAmountList',
						valueField : 'cardvalue',
						textField : 'title',
						editable:false
					});
		}

		// ��ֵ�� -> ����
		this.cardSaveOrUpdate = function() {
			$('#addCardForm').form('submit', {
						url : url,
						onSubmit : function() {
							var canSubmit =  addCardValidateForm();
							$('#addCard-dlg-buttons01').linkbutton('disable');
							$('#addCard-dlg-buttons02').linkbutton('disable');
							return canSubmit;// ��֤��
						},
						success : function(data) {
							$('#addCard-dlg-buttons01').linkbutton('enable');
							$('#addCard-dlg-buttons02').linkbutton('enable');
							$.messager.alert('��ʾ', '��ֵ������ɹ�!', 'info');// ����ɹ�������ʾ��Ϣ
							$("#addCardDialog").dialog('close'); // �ر���ֵ���Ի���
							addCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
						}
					});
		}

		// ��ֵ�� -> ��֤��
		function addCardValidateForm() {
			if ($.trim($('#addCardvaluetype').combobox('getValue')) == '') {
				$.messager.alert('��ʾ', '��ֵ����δѡ��!', 'warning'); // ��ֵ����δѡ��
				return false;
			}
			if ($.trim($("#addCardForm input[name='count']").val()) == '') {
				$.messager.alert('��ʾ', '����δ��д!', 'warning'); // ����δ��д
				return false;
			} 
			if ($('#lastDate0').datebox('getValue') == '') {
				$.messager.alert('��ʾ', '��Ч�ڽ�������δ��д!', 'warning'); // ����δ��д
				return false;
			} 
			if ($.trim($("#addCardForm input[name='secretKey']").val()) != $
					.trim($("#addCardForm input[name='reSecretKey']").val())) {
				$.messager.alert('��ʾ', '��Կ��һ��!', 'warning'); // ��Կ��һ��
				$("#addCardForm input[name='secretKey']").val("");
				$("#addCardForm input[name='reSecretKey']").val("");
				return false;
			}
			return true;
		}

		// ��ֵ�� -> ӡ��
		this.printCard = function() {
			var ids = getSelectedIDS("#addCardDatagrid"); // ��ȡ���������ID
			if (ids == '') {
				$.messager.alert('��ʾ', '��ѡ��Ҫ����ӡ������ֵ��!', 'warning');
			} else {
				$.post(mainWeb+'/admin/card/batchPrint',{'ids[]' : ids},function(data) {
					data = eval("("+data+")");
					if(data.result){
						addCardAction.refreshCards();// ˢ����ֵ���б���Ϣ
						//��ȡҪ��ӡ����ֵ��
						$.post(mainWeb+'/admin/card/getVoListByIds',{'ids[]' : ids},function(data) {
							data = eval("("+data+")");
							createPrintPage1(data);
						});
					}
				});
			}
		}
		
		//��ֵ�� -> ������ӡҳ��
		function createPrintPage1(data){
			var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
			LODOP.PRINT_INIT("��ֵ����ӡ...");
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
				
				LODOP.ADD_PRINT_TEXT('64.5mm','16mm','50mm','5mm','���ţ�'+data.returnObj[i].cardno);
				LODOP.ADD_PRINT_TEXT('74mm','16mm','50mm','5mm','���룺'+data.returnObj[i].password);
			}
			//LODOP.PREVIEW();
			LODOP.PRINT();
		}

		function CreatePrintPage(value, number, productDate, enddate, card, ps) {
			LODOP.PRINT_INITA(4, 3, 1200, 1600, "�״�EMS��ģ��");
			//LODOP.ADD_PRINT_SETUP_BKIMG("C:\\Users\\mengyongfeng\\Desktop\\��ҳ.jpg");
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

		// ��ֵ�� -> �������� --> ��ȡѡ�е�Ids
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