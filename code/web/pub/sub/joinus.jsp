<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>加盟7号</title>
		<script type="text/javascript" src="<%=basePath%>/scripts/idCard.js" charset="GBK"></script>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<script type="text/javascript">
function submitJoinInfo(){
	//验证 -> 姓名
	var chineseReg = /^([u4e00-u9fa5]|[ufe30-uffa0])*$/gi;
	if(chineseReg.test($("#joinSevenForm input[name='joinerName']").val())){
		alert("姓名只能输入中文！");
		return false;
	}
	//验证 -> 年龄
	var ageReg = /^[0-9]+$/;
	if(!ageReg.test($("#joinSevenForm input[name='joinerAge']").val())){
		alert("年龄不合法！");
		return false;
	}
	//验证 -> 籍贯
	if(chineseReg.test($("#joinSevenForm input[name='birthplace']").val())){
		alert("籍贯只能输入中文！");
		return false;
	}
	//验证 -> 身份证
	if(!checkParseIdCard($("#joinSevenForm input[name='idCardNo']").val())){
		return false;
	}
	//验证 -> Email
	var emailReg = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if(!emailReg.test($("#joinSevenForm input[name='email']").val())){
		alert("email不合法！");
		return false;
	}
	//验证 -> 联系电话
	var telephoneReg = /^([0-9]|[\-])+$/g;
	if(!telephoneReg.test($("#joinSevenForm input[name='telephone']").val())){
		alert("联系电话格式错误！");
		return false;
	}
	//验证 -> 传真
	var faxReg = /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/;
	if(!faxReg.test($("#joinSevenForm input[name='fax']").val())){
		alert("传真格式不正确！");
		return false;
	}
	//验证 -> QQ
	var qqReg = /^[1-9]\d{4,10}$/;
	if(!qqReg.test($("#joinSevenForm input[name='qq']").val())){
		alert("QQ格式不正确！");
		return false;
	}
	//验证 -> MSN
	var msnReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if($("#joinSevenForm input[name='msn']").val() != ""){
		if(!msnReg.test($("#joinSevenForm input[name='msn']").val())){
			alert("msn格式不正确！");
			return false;
		}
	}
	//验证 -> 手机号码
	var mobileReg = /^([0-9]|[\-])+$/g;
	if(!mobileReg.test($("#joinSevenForm input[name='mobile']").val())){
		alert("手机号码格式错误！");
		return false;
	}
	
	//提交加盟单
	var form = $('#joinSevenForm');
	$.ajax({
		type: 'post',
		url: form.attr('action'),
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		data:form.serialize(),
		dataType: 'json',
		success: function(result) {
	    	if (result.result) {
				var cc = new CmsConfirm("提示", "提交加盟申请已成功，请静待回音，点击确定返回首页。");
				cc.setActionListener(function(result) {
					if(result){
						window.location = mainWeb;
					}else{
						
					}
				}); 
			} else {
				 cmsAlertAtt(result.message);
			}
		}
	});
}
function validateJoinInfo(){
	
}
</script>
		<style type="text/css">
#MainBillInfoDiv {
	width: 100%;
	border: #CDCDCD solid 1px;
	margin-top: 40px; *
	margin-top: 0px;
}

#billsTitleDiv {
	width: 100%;
	height: 30px;
	line-height: 30px;
	text-align: center;
	font-size: 22px;
	margin-top: 0px;
}

#MainBillInfo {
	width: 800px;
	height: 1240px;
	*height:1270px;
	margin: 0 auto;
	margin-top: 0px;
}

#MainBillInfo .JoinChannelTitleDivStyle {
	height: 30px;
	line-height: 30px;
	background-color: #F0F4F5;
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

.button_submit {
	width: 134px;
	line-height: 32px;
	height: 32px;
	margin-left: 665px;
	margin-top: 10px;
	cursor: pointer;
	text-align: center;
	vertical-align: middle;
	background-image: url('<%=basePath%>/images/page/button_reg.png');
	list-style-type: none;
	display: inline;
	float: left;
	cursor: pointer;
	margin-top: 10px;
	cursor: pointer;
}
#shortcut {
	padding-bottom: 1px;
	line-height: 30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}
</style>
	</head>
	<body style="background-color: #FFFFFF" onselectstart="return false;">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id='chargecontent'>
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp?menuId=joinus" flush="true" />
			</div>
			<div id="billsTitleDiv">
				<strong>加盟申请表</strong>
			</div>
			<div id="MainBillInfoDiv">
				<div id="MainBillInfo">
					<form action="<%=basePath%>/front/JoinSeven" id="joinSevenForm"
						method="post">
						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;个人资料</strong>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -38px;">
								姓名：
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="joinerName" style="width: 150px;"/>
							</div>
							<div class="fieldLableSpan">
								性别：
							</div>
							<div class="fieldInputSpan">
								<select name="joinerSex" style="width: 50px;">
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
								<input type="text" name="joinerAge" style="width: 50px;"/>
							</div>
							<div class="fieldLableSpan">
								婚姻状况：
							</div>

							<div class="fieldInputSpan">
								<select name="maritalstatus" style="width: 60px;">
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
								<input type="text" name="birthplace" style="width: 100px;"/>
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								文化程度：
							</div>

							<div class="fieldInputSpan">
								<select name="culturalLevel" style="width: 100px;">
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
								<input type="text" name="idCardNo" style="width: 200px;"/>
							</div>
							<div class="fieldLableSpan">
								email：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="email" style="width: 228px;"/>
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								联系电话：
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="telephone" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								传真：
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="fax" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								QQ：
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="qq" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								MSN：
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="msn" style="width: 158px;"/>
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								手机号码：
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="mobile" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								居住地址：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="livingAddress" style="width: 498px;" />
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
								<input type="text" name="storeAddress" style="width: 668px;" />
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan"
								style="margin-left: -10px; width: 100px;">
								所处商业街地段：
							</div>
							<div class="fieldInputSpan">
								<input type="radio" name="storeLocation" value="单门面">
								单门面
								<input type="radio" name="storeLocation" value="双门面">
								双门面
								<input type="radio" name="storeLocation" value="三门面">
								三门面
								<input type="radio" name="storeLocation" value="店铺">
								店铺
								<input type="radio" name="storeLocation" value="专柜">
								专柜
								<input type="radio" name="storeLocation" value="店中店">
								店中店
								<input type="radio" name="storeLocation" value="专卖店">
								专卖店
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								全年租金：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="rentOfYear" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan">
								费用合计：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="sumCost" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan">
								销售人员：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="salesEmployee" style="width: 176px;" />
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								经营方式：
							</div>
							<div class="fieldInputSpan">
								<input type="radio" name="businessWay" value="个人">
								个人
								<input type="radio" name="businessWay" value="合伙">
								合伙
								<input type="radio" name="businessWay" value="单位">
								单位
								<input type="radio" name="businessWay" value="其他">
								其他
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								投入资金：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="fund" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan">
								运作资金：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="funding" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan" style="width: 100px;">
								要求开业时间：
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="askStartDate" style="width: 146px;" />
							</div>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;区域内竞争品牌状况</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="competitionSituation"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;是什么触动您意向加盟</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="joinusReason"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;个人从业经验</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="experience"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;个人性格及家庭状况</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="dispositionAndFamily"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;对加盟的预期风险与未来第一、第二、第三年的期待</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="riskAndHope"></textarea>
						</div>
						<div class="button_submit" onclick="submitJoinInfo();">
							提交加盟表
						</div>
				</div>
				</form>
			</div>
			<div id="serviceFloors">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
</html>