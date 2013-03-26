<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品促销</title>
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
		<!-- begin of 列表 -->
		<div id="goodsBillToolbar">
			<span style="float: left; padding-right: 5px;">状态： <input
					id="billStatusSelectList" name="billStatusSelectList"> </span>
		</div>
		<table id="goodsBillDatagrid" toolbar="#goodsBillToolbar">
		</table>
		<!-- begin of 新增赠品促销 -->
		<div id="goodsBillDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:true"
			style="width: 852px; height: 508px; padding: 10px 10px 0px;"
			title="1" closed="true">
			<div id="MainBillInfo">
				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;个人资料</strong>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -38px;">
						姓名：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="joinerName" style="width: 150px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						性别：
					</div>
					<div class="fieldInputSpan">
						<select id="joinerSex" style="width: 50px;" disabled="disabled">
							<option value="男">
								男
							</option>
							<option value="女">
								女
							</option>
						</select>
					</div>
					<div class="fieldLableSpan">
						年龄：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="joinerAge" style="width: 50px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						婚姻状况：
					</div>

					<div class="fieldInputSpan">
						<select id="maritalstatus" style="width: 60px;"
							disabled="disabled">
							<option value="已婚">
								已婚
							</option>
							<option value="未婚">
								未婚
							</option>
						</select>
					</div>
					<div class="fieldLableSpan">
						籍贯：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="birthplace" style="width: 100px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						文化程度：
					</div>

					<div class="fieldInputSpan">
						<select id="culturalLevel" style="width: 100px;"
							disabled="disabled">
							<option value="本科">
								本科以上
							</option>
							<option value="专科">
								专科
							</option>
							<option value="高中">
								高中
							</option>
							<option value="初中">
								初中
							</option>
							<option value="其他">
								其他
							</option>
						</select>
					</div>
					<div class="fieldLableSpan">
						身份证：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="idCardNo" style="width: 200px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						email：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="email" style="width: 228px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						联系电话：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="telephone" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						传真：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="fax" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						QQ：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="qq" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						MSN：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="msn" style="width: 158px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						手机号码：
					</div>

					<div class="fieldInputSpan">
						<input type="text" id="mobile" style="width: 100px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						居住地址：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="livingAddress" style="width: 498px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;加盟详情</strong>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						详细店址：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="storeAddress" style="width: 668px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan"
						style="margin-left: -10px; width: 100px;">
						所处商业街地段：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="storeLocation" disabled="disabled">
					</div>
					<div class="fieldLableSpan">
						经营方式：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="businessWay" disabled="disabled">
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						全年租金：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="rentOfYear" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						费用合计：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="sumCost" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						销售人员：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="salesEmployee" style="width: 176px;"
							disabled="disabled" />
					</div>
				</div>
				<div class="JoinChannelFormDivStyle">
					<div class="fieldLableSpan" style="margin-left: -15px;">
						投入资金：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="fund" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan">
						运作资金：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="funding" style="width: 176px;"
							disabled="disabled" />
					</div>
					<div class="fieldLableSpan" style="width: 100px;">
						要求开业时间：
					</div>
					<div class="fieldInputSpan">
						<input type="text" id="askStartDate" style="width: 146px;"
							disabled="disabled" />
					</div>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;区域内竞争品牌状况</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="competitionSituation" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;是什么触动您意向加盟</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="joinusReason" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;个人从业经验</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="experience" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;个人性格及家庭状况</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="dispositionAndFamily" disabled="disabled"></textarea>
				</div>

				<div class="JoinChannelTitleDivStyle">
					<strong>&gt;&gt;对加盟的预期风险与未来第一、第二、第三年的期待</strong>
				</div>

				<div class="fieldTextAreaDiv">
					<textarea style="width: 100%; height: 120px; resize: none;"
						id="riskAndHope" disabled="disabled"></textarea>
				</div>
			</div>
		</div>
		<script type="text/javascript">// 初始化
$(function() {
	// 初始化 --> --> 定义列表列
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
					title : '姓名',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'mobile',
					title : '手机号码',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'businessWay',
					title : '经营方式',
					width : 140,
					align : 'center',
					sortable : true
				}, {
					field : 'fund',
					title : '投入金额',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'funding',
					title : '运营资金',
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
					title : '店铺类型',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'createDateStr',
					title : '申请时间',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'recid',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, rec) {
						var begin = "<a href='#' class='operateChannel' onClick=goodsBillAction.editBill('"
								+ value + "')>查看</a>";
						if (rec.opered == false) {
							return begin
									+ "&nbsp;&nbsp;<a href='#' class='operateChannel' onClick=goodsBillAction.activeGoodsBill('"
									+ value + "')>处理</a>"
						}
						return begin;
					}
				}]]
	});

	// 初始化 -> 初始化类型
	$('input#billStatusSelectList').combobox({
				url : mainWeb+'/admin/join/getStatusList',
				valueField : 'code',
				textField : 'title',
				editable:false
			});

	// 监听事件 -> 类型改变
	$('#billStatusSelectList').combobox({
				onSelect : function(record) {
					// 刷新
					goodsBillAction.refreshCards();
				}
			});
	$('#billStatusSelectList').combobox('setValue','0');
	$("#goodsName0").searchbox({
		searcher : function(value, name) {
			// 这里写弹出选择框的代码
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
			// -> 初始化
			goodsBillAction = new AddCardAction();

			// -> 定义
			function AddCardAction() {
				// -> 刷新
				this.refreshCards = function() {
					var billStatus = $('#billStatusSelectList')
							.combobox('getValue');
					$('#goodsBillDatagrid').datagrid('reload', {
								billStatus : billStatus
							}); // 刷新列表信息
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
					// 打开对话框
					$('#goodsBillDialog').dialog('open')
							.dialog('setTitle', '加盟申请单'); 
				}
				// -> 处理
				this.activeGoodsBill = function(valueId) {
					$.messager.confirm('处理', '是否确定已处理?', function(r) {
								if (r) {
									$.post(mainWeb+'/admin/bill/activeBill', {
												id : valueId
											}, function(result) {
												$.messager.alert('提示',
														result.message, 'info');// 保存成功给出提示信息
												if (result.result == true) {
													goodsBillAction
															.refreshCards();// 刷新列表信息
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