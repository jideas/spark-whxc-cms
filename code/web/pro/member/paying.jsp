<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
	<head>
		<title>订单提交成功-选择支付方式</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/paying.css">
		<script type="text/javascript">
			var selectedBankStr = 'icbc';
			var isSelectedAlipay = false;
			function selectBank(value){ 
				selectedBankStr = value;
				document.getElementById('RadioGroup1_'+value).checked = 'true';
			} 
			function changePayCompany(value){
				if(value){
					document.getElementById('selectWayDivTitleLeft01').style.backgroundColor='#FFFDEE';
					document.getElementById('selectWayDivTitleLeft01').style.borderBottom='#FFFDEE solid 1px';
					document.getElementById('selectWayDivTitleLeft02').style.backgroundColor='#FFFFFF';
					document.getElementById('selectWayDivTitleLeft02').style.borderBottom='#EDD28B solid 1px';  
					document.getElementById('aliPayDiv').style.display = 'none'; 
					document.getElementById('unionPayDiv03').style.display = '';
					document.getElementById('unionPayDiv04').style.display = '';
					document.getElementById('unionPayDiv05').style.display = '';
					document.getElementById('unionPayDiv06').style.display = '';
					document.getElementById('selectWayDiv').style.height=360;
					isSelectedAlipay = false;
				}else{
					document.getElementById('selectWayDivTitleLeft02').style.backgroundColor='#FFFDEE';
					document.getElementById('selectWayDivTitleLeft02').style.borderBottom='#FFFDEE solid 1px';
					document.getElementById('selectWayDivTitleLeft01').style.backgroundColor='#FFFFFF';
					document.getElementById('selectWayDivTitleLeft01').style.borderBottom='#EDD28B solid 1px';  
					document.getElementById('unionPayDiv03').style.display = 'none';
					document.getElementById('unionPayDiv04').style.display = 'none';
					document.getElementById('unionPayDiv05').style.display = 'none';
					document.getElementById('unionPayDiv06').style.display = 'none'; 
					document.getElementById('aliPayDiv').style.display = '';
					document.getElementById('selectWayDiv').style.height=210;
					isSelectedAlipay = true;
				}
			}
			function doSubmit() {
				if (!validatePayForm()) {
					return;
				}
				var form = $('#toPayPageForm');
				var bank = selectedBankStr; 
				if (isSelectedAlipay) {
					bank = 'alipay';
				}
				 $('#bank_input').val(bank);
				 form.attr('action',mainWeb +'/front/payment/payCharge'); 
			
				var cc = new RealConfirm("支付", '请您在新打开的网上银行页面进行支付，<br>支付完成前请不要关闭该窗口。',
						'已完成付款', "重选付款方式", 'att');
				cc.setActionListener(function(result) {
							if(result){
								location = mainWeb+'/front/getBalance';
							}
						}); 
							 form.submit();
			}
			
			function validatePayForm() {
				var renturnValue = true;
				if (!$('#RadioGroup1_1') && !$('#RadioGroup1_2')) {
					cmsAlertAtt('请先选一种支付方式！');
					renturnValue = true
				}
				var amount = $.trim($('#chargeValue').val());
				if (amount == '') {
					cmsAlertAtt('请填写充值金额！');
					renturnValue = false;
					
				} else if (!/^\d{1,10}(\.\d{0,2})?$/.test(amount)) {
					cmsAlertAtt('请填写正确的充值金额');
					$('#chargeValue').val('');
					renturnValue = false;
				}
				return renturnValue;
			}  
		</script>
	</head>

	<body>
		<div class="w" id="headers">
			<div style="vertical-align: middle; display: inline; float: left;">
				<img src="<%=basePath%>/images/page/attention_01.png" />
				&nbsp; 充值资金不能提现。
			</div>
			<div id="step1" class="step">
				<ul>
					<li class="fore1">
						1.选择支付方式
						<b></b>
					</li>
					<li class="fore2">
						2.支付平台付款
						<b></b>
					</li>
					<li class="fore3">
						3.支付结果信息
					</li>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
		<div class="w main" id="payingcontentdiv">
			<div class="m m3" id="qpay">
				<div class="mc">
					<s class="icon-succ02"></s>
					<div class="fore">
						<h3 class="ftx-02">
							充值账户：
							<span id="email_box"></span>
						</h3>
						<ul class="list-h">
							<li class="fore1">
								当前余额：
								<strong class="ftx-01" id="balance_box"></strong>
							</li>
							<li class="fore2">
								当前积分：
								<strong class="ftx-01" id="vanteges_box"></strong>
							</li>
						</ul>
						<p id="p_show_info">
							&nbsp;
						</p>
					</div>
				</div>
			</div>
			<div id="selectWayDiv">
				<div id="selectWayDivTitle">
					<div id="selectWayDivTitleLeft01" class="selectWayDivTitleLeft01">
						<font
							style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
							color="#FF6600"><b>银联支付</b> </font>
					</div>
					<!-- 
					<div id="selectWayDivTitleLeft02" class="selectWayDivTitleLeft02"
						onclick="changePayCompany(0);">
						<font
							style="margin-left: 10px; margin-top: 20px; font-size: 14px;"
							color="#FF6600"><b>支付宝</b> </font>
					</div> -->
					<div id="selectWayDivTitleRight">
					</div>
				</div>
				<div class="allWaysDiv" id="unionPayDiv03">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('icbc');" id="RadioGroup1_icbc"
								name="RadioGroup1" checked="checked" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('icbc');" alt="工商银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/icbc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"
								onclick="selectBank('abc');" id="RadioGroup1_abc"
								name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('abc');" alt="农业银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/abc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio" onclick="selectBank('ccb');" 
								id="RadioGroup1_ccb" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('ccb');" alt="建设银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/ccb.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio" 	onclick="selectBank('bcom');"
								id="RadioGroup1_bcom" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('bcom');" alt="交通银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/bcom.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="unionPayDiv04">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('cmb');"
								id="RadioGroup1_cmb" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('cmb');" alt="招商银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/cmb.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('boc');"
								id="RadioGroup1_boc" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('boc');" alt="中国银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/boc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('cib');" 
								id="RadioGroup1_cib" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('cib');" alt="兴业银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/cib.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('ceb');" 
								id="RadioGroup1_ceb" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('ceb');" alt="光大银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/ceb.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="unionPayDiv05">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('post');" 
								id="RadioGroup1_post" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('post');" alt="邮政银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/post.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('sdb');" 
								id="RadioGroup1_sdb" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('sdb');" alt="深发银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/sdb.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('citic');" 
								id="RadioGroup1_citic" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('citic');" alt="中信银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/citic.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('gdb');" 
								id="RadioGroup1_gdb" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('gdb');" alt="广发银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/gdb.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="unionPayDiv06">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('cmbc');" 
								id="RadioGroup1_cmbc" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('cmbc');" alt="民生银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/cmbc.png"> </a>
							</div>
						</li>
						<li>
							<input type="radio" class="allWaysDivLiRadio"	onclick="selectBank('pab');" 
								id="RadioGroup1_pab" name="RadioGroup1" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										onclick="selectBank('pab');" alt="平安银行" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/pab.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div class="allWaysDiv" id="aliPayDiv" style="display: none;">
					<ul>
						<li>
							<input type="radio" class="allWaysDivLiRadio" checked="checked" />
							<div class="bank-logo">
								<a href="javascript:void(0);"><img width="147" height="40"
										alt="支付宝" style="border: 0px;"
										src="<%=basePath%>/images/page/banks/zhifu.png"> </a>
							</div>
						</li>
					</ul>
				</div>
				<div id="chargeValueDiv">
					<form action="" method="post" id="toPayPageForm" target="_blank">
						充值金额：
						<input type="text" id="chargeValue" name="value" />
						&nbsp;元
						<input type="hidden" name="bank" id="bank_input" />
					</form>
				</div>
				<div id="ChooseOkButtonDiv">
					<a href="javascript:void(0);" onclick="doSubmit();"><img
							src="<%=basePath%>/images/page/quickpay_02.png"
							style="border: 0px;" /> </a>
				</div>
			</div>
			<div id="divBottom">
				&nbsp; 完成支付后，您可以：&nbsp;
				<a href="<%=basePath%>//front/getBalance">查看余额</a>&nbsp;
				<a href="javascript:void(0);">支付调查</a>
			</div>
		</div>
	</body>
</html>
