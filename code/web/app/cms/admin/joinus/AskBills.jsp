<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ʒ����</title>
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

#MainBillInfo {
	width: 800px;
	height: 1200px;
	margin: 0 auto;
	margin-top: 0px;
}

#MainBillInfo .JoinChannelTitleDivStyle {
	height: 30px;
	line-height: 30px;
	background-color: #D8E4FE;
	text-align: left;
	padding-left: 10px;
	margin-top: 10px;
}

#MainBillInfo .JoinChannelFormDivStyle {
	text-align: left;
	padding-left: 10px;
	width: 100%;
	height: 30px;
	line-height: 30px;
}

.JoinChannelFormDivStyle .fieldLableSpan {
	width: 70px;
	height: 22px;
	line-height: 22px;
	text-align: right;
	font-size: 12px;
	vertical-align: middle;
	display: inline;
	float: left;
	margin-top: 8px;
}

.JoinChannelFormDivStyle .fieldInputSpan {
	text-align: left;
	height: 22px;
	line-height: 22px;
	font-size: 12px;
	display: inline;
	margin-top: 8px;
	float: left;
}

.fieldTextAreaDiv {
	height: 120px;
	line-height: 120px;
	text-align: left;
	padding-left: 10px;
	margin-top: 10px;
}
</style>
		<!-- begin of �б� -->
		<div id="goodsBillToolbar">
			<span style="float: left; padding-right: 5px;">״̬�� <input
					id="billStatusSelectList" name="billStatusSelectList"> </span>
		</div>
		<table id="goodsBillDatagrid" toolbar="#goodsBillToolbar">
		</table>
		<!-- begin of ������Ʒ���� -->
		<div id="goodsBillDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:true"
			style="width: 852px; height: 508px; padding: 10px 10px 0px;"
			title="1" closed="true">
			<div id="MainBillInfo">
				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;��������</strong>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -38px;">
						������
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="joinerName" style="width: 150px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						�Ա�
					</div>
					<div class="fieldInputSpan">
						<select id="joinerSex" style="width: 50px;" disabled="disabled">
							<option value="��">
								��
							</option>
							<option value="Ů">
								Ů
							</option>
						</select>
					</div>
					<div class="fieldLableSpan">
						���䣺
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="joinerAge" style="width: 50px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						����״����
					</div>

					<div class="fieldInputSpan">
						<select id="maritalstatus" style="width: 60px;"
							disabled="disabled">
							<option value="�ѻ�">
								�ѻ�
							</option>
							<option value="δ��">
								δ��
							</option>
						</select>
					</div>
					<div class="fieldLableSpan">
						���᣺
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="birthplace" style="width: 100px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						�Ļ��̶ȣ�
					</div>

					<div class="fieldInputSpan">
						<select id="culturalLevel" style="width: 100px;"
							disabled="disabled">
							<option value="����">
								��������
							</option>
							<option value="ר��">
								ר��
							</option>
							<option value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="����">
								����
							</option>
						</select>
					</div>
					<div class="fieldLableSpan">
						���֤��
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="idCardNo" style="width: 200px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						email��
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="email" style="width: 228px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						��ϵ�绰��
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="telephone" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						���棺
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="fax" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						QQ��
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="qq" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						MSN��
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="msn" style="width: 158px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						�ֻ����룺
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="mobile" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						��ס��ַ��
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="livingAddress" style="width: 498px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;��������</strong>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						��ϸ��ַ��
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="storeAddress" style="width: 668px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan"
						style="margin-left: -10px; width: 100px;">
						������ҵ�ֵضΣ�
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="storeLocation" disabled="disabled">
					</div>
					<div class="fieldLableSpan">
						��Ӫ��ʽ��
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="businessWay" disabled="disabled">
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						ȫ�����
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="rentOfYear" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						���úϼƣ�
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="sumCost" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						������Ա��
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="salesEmployee" style="width: 176px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						Ͷ���ʽ�
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="fund" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						�����ʽ�
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="funding" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan" style="width: 100px;">
						Ҫ��ҵʱ�䣺
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="askStartDate" style="width: 146px;"
							disabled="disabled" />
					</div>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;�����ھ���Ʒ��״��</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="competitionSituation" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;��ʲô�������������</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="joinusReason" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;���˴�ҵ����</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="experience" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;�����Ը񼰼�ͥ״��</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="dispositionAndFamily" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;�Լ��˵�Ԥ�ڷ�����δ����һ���ڶ�����������ڴ�</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="riskAndHope" disabled="disabled"></textarea>
				</div>
			</div>
		</div>
		<script type="text/javascript">// ��ʼ��
$(function() {
	// ��ʼ�� --> --> �����б���
	$('#goodsBillDatagrid').datagrid({
		fit : true,
		bgoods : false,
		idField : 'recid',
		rownumbers : true,
		fitColumns : true,
		remoteSort : false,
		pagination : true,singleSelect: true,
		url : mainWeb+'/admin/join/getBillsList',
		columns : [[{
					field : 'joinerName',
					title : '����',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'mobile',
					title : '�ֻ�����',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'businessWay',
					title : '��Ӫ��ʽ',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'fund',
					title : 'Ͷ����',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'funding',
					title : '��Ӫ�ʽ�',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'qq',
					title : 'QQ',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'storeLocation',
					title : '��������',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'createDateStr',
					title : '����ʱ��',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'recid',
					title : '����',
					width : 100,
					align : 'center',
					formatter : function(value, rec) {
						var begin = "<a href='#' class='operateChannel' onClick=goodsBillAction.editBill('"
								+ value + "')>�鿴</a>";
						if (rec.opered == false) {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=goodsBillAction.activeGoodsBill('"
									+ value + "')>����</a>"
						}
						return begin;
					}
				}]]
	});

	// ��ʼ�� -> ��ʼ������
	$('input#billStatusSelectList').combobox({
				url : mainWeb+'/admin/join/getStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});

	// �����¼� -> ���͸ı�
	$('#billStatusSelectList').combobox({
				onSelect : function(record) {
					// ˢ��
					goodsBillAction.refreshCards();
				}
			});
	$('#billStatusSelectList').combobox('setValue','0');
	$("#goodsName0").searchbox({
		searcher : function(value, name) {
			// ����д����ѡ���Ĵ���
			var win = new ChooseGoodsWindow(true);
			win.addConfirmActionListener(function(goodsVo) {
						$('#goodsId0').val(goodsVo.recid);
						$("#goodsName0").searchbox(
								'setValue',
								goodsVo.goodsname + '[' + goodsVo.goodsspec
										+ ']');
					});
		}
	});
	$("#goodsBillForm span.searchbox").attr('disabled', false);
	$("#goodsBillForm span.searchbox").css("width", 400);
	$("#goodsBillForm span.searchbox input.searchbox-text").css("width", 380);
});

// 
var goodsBillAction;
$(function() {
			var url = '';
			// -> ��ʼ��
			goodsBillAction = new AddCardAction();

			// -> ����
			function AddCardAction() {
				// -> ˢ��
				this.refreshCards = function() {
					var billStatus = $('#billStatusSelectList')
							.combobox('getValue');
					$('#goodsBillDatagrid').datagrid('reload', {
								billStatus : billStatus
							}); // ˢ���б���Ϣ
				}

				function fillPromotionInfo0(valueId) {
					$.post(mainWeb+'/admin/bill/findBill', {
								id : valueId
							}, function(result) { 
								 $('#joinerName').val(result.joinerName);
								 $('#joinerSex').val(result.joinerSex);
								 $('#joinerAge').val(result.joinerAge);
								 $('#maritalstatus').val(result.maritalstatus);
								 $('#birthplace').val(result.birthplace);
								 $('#culturalLevel').val(result.culturalLevel);
								 $('#idCardNo').val(result.idCardNo);
								 $('#email').val(result.email);
								 $('#telephone').val(result.telephone);
								 $('#fax').val(result.fax);
								 $('#qq').val(result.qq);
								 $('#msn').val(result.msn);
								 $('#mobile').val(result.mobile);
								 $('#livingAddress').val(result.livingAddress);
								 $('#storeAddress').val(result.storeAddress);  
								 $('#storeLocation').val(result.storeLocation);
								 $('#rentOfYear').val(result.rentOfYear);
								 $('#sumCost').val(result.sumCost);
								 $('#salesEmployee').val(result.salesEmployee);
								 $('#businessWay').val(result.businessWay);
								 $('#fund').val(result.fund);
								 $('#funding').val(result.funding);
								 $('#askStartDate').val(result.askStartDate);
								 $('#competitionSituation').val(result.competitionSituation);
								 $('#joinusReason').val(result.joinusReason);
								 $('#experience').val(result.experience);
								 $('#dispositionAndFamily').val(result.dispositionAndFamily);
								 $('#riskAndHope').val(result.riskAndHope); 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
								 
							}, 'json');
				}

			 	this.editBill = function(valueId) {
					if (null != valueId && '' != valueId) {
						fillPromotionInfo0(valueId);
					}
					// �򿪶Ի���
					$('#goodsBillDialog').dialog('open')
							.dialog('setTitle', '�������뵥'); 
				}
				// -> ����
				this.activeGoodsBill = function(valueId) {
					$.messager.confirm('����', '�Ƿ�ȷ���Ѵ���?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/bill/activeBill', {
												id : valueId
											}, function(result) {
												$.messager.alert('��ʾ',
														result.message, 'info');// ����ɹ�������ʾ��Ϣ
												if (result.result == true) {
													goodsBillAction
															.refreshCards();// ˢ���б���Ϣ
												}
											}, 'json');
								}
							});
				}
  
			}
		});
		</script>
	</body>
</html>