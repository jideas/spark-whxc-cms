<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link type="text/css" rel="stylesheet"
			href="<%=basePath%>/css/base.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<style type="text/css">
/*  accountinfo main */
.main {
	margin-bottom: 20px
}

.right {
	width: 1040px;
	line-height: 1.5em;
	float: left;
	font-family: Verdana;
	margin-bottom: 20px;
}

.main .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

/** user base **/
.right a:link,.right a:visited {
	color: #005ea7;
}

.m .mt a:link,.m .mt a:visited {
	color: #333
}

.main {
	margin-bottom: 10px;
}

/* user info */
.right .o-mt { *
	height: 27px;
	font-size: 12px;
	font-weight: 400;
	letter-spacing: 1px;
	text-align: left;
	background-image: url('<%=basePath%>/images/page/sort-bg05.png');
	padding: 4px 5px;
	border-top: 1px solid #E6E6E6;
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
}

em {
	color: #ff6600;
	margin-right: 3px;
}

.infoform {
	width: 1040px;
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
}

.infoform .title {
	color: #FC6210;
	font-size: 12px;
	font-weight: 700;
}

.infoform .labelArea {
	float: left;
	width: 240px;
	text-align: right;
	line-height: 40px;
	border: red solid 0px;
}

.infoform .inputArea {
	float: left;
	width: 780px;
	text-align: left;
	height: 40px;
	border: blue solid 0px;
}

.inputShowArea {
	float: left;
	width: 780px;
	text-align: left;
	height: 40px;
	line-height: 40px;
	margin-left: 12px;
}

.infoform .buttonArea {
	float: left;
	width: 780px;
	text-align: left;
	height: 40px;
	margin-left: 240px;
}

.infoform input,.infoform select {
	margin-top: 9px;
	margin-left: 10px;
	height: 22px;
}

.infoform textarea {
	margin-top: 9px;
	margin-left: 10px;
	height: 60px;
	width: 400px;
	resize: none;
}

.infoform select {
	width: 120px;
}

.infoform .prompt {
	display: inline;
	color: red;
}

#ordercenter-data-title {
	width: 1032px; *
	width: 1040px;
	height: 35px;
	line-height: 35px;
	border-bottom: #66CD00 2px solid;
	float: left;
	text-align: left;
	padding-left: 8px;
	color: #66CD00;
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
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/scripts/area.js"></script>
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type='text/javascript'>
var areaProvider;
$(document).ready(function() {
	init();
	var form = $('#infoformId');
	form.submit(function() {
		if (!validateForm()) {
			return false;
		}
		$.ajax({
					type : 'post',
					url : form.attr('action'),
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					data : form.serialize(),
					dataType : 'json',
					success : function(result) {
						if (result.result) {
							cmsAlertSuccess(result.message);
						} else {
							if (result.returnObj == 2) {
								location = mainWeb + '/pub/sub/login.jsp';
							} else {
								cmsAlertError(result.message);
							}
						}
					}
				});
		return false;
	});
});
function validateForm(){
	var returnValue = true;
	var name = $('#realName_input').val();
	if($.trim(name)==''){
		$('#realName_input_suf').html('<font color=red>真实姓名必须填写!</font>');
		returnValue = false;
	}else{
		$('#realName_input_suf').html('');
	}
	var address = $('#address_input').val();
	if($.trim(address)==''){
		$('#address_input_suf').html('<font color=red>地址必须填写!</font>');
		returnValue = false;
	}else{
		$('#address_input_suf').html('');
	}
	var tel = $('#telephone_input').val();
	if($.trim(tel)!=''&&!/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test($.trim(tel))){
		$('#telephone_input_suf').html('<font color=red>固定电话格式不正确!</font>');
		returnValue = false;
	}else{
		$('#telephone_input_suf').html('');
	}
	return  returnValue;
}
function init() {
	areaProvider = new AreaProvider('deliver_province', 'deliver_city',
			'deliver_town');
	var url = mainWeb + '/front/initInfoPage';
	$.post(url, function(result, status) {
				result = eval('(' + result + ')');
				if (result.result) {
					fillForm(result.returnObj)
				} else {
					if (result.returnObj == 2) {
						location = mainWeb + '/pub/sub/login.jsp';
					} else {
						cmsAlertAtt(result.message);
					}
				}
			});
};

function fillForm(form) {
	if (null == form) {
		return;
	}
	if (form && form.username) {
		$('#username_input').html(form.username);
	}
	if (form && form.email) {
		$('#email_input').html(form.email);
	}
	if (form && form.mobile) {
		$('#mobile_input').html(form.mobile);
	}
	if (form && form.realName) {
		$('#realName_input').val(form.realName);
	}
	if (form && form.sex) {
		$('#RadioGroup1_' + form.sex).attr('checked', "checked");
	} else {
		$('#RadioGroup1_0').attr('checked', "checked");
	}
	if (form && form.birthday01) {
		$('#birthday_input1').val( form.birthday01);
		$('#birthday_input2').val( parseInt(form.birthday02));
		$('#birthday_input3').val( form.birthday03);
	}
	if (form && form.address) {
		$('#address_input').val(form.address);
	}
	if (form && form.telephone) {
		$('#telephone_input').val(form.telephone);
	}
	if (form && form.province) {
		areaProvider.setSelection(form.province, form.city, form.town);
	}
	if (form && form.identityList) {
		var ops = '<option value="">请选择您的身份</option>';
		for (var i = 0; i < form.identityList.length; i++) {
			var dic = form.identityList[i];
			if (!dic) {
				continue;
			}
			if (form.identity && form.identity == dic.code) {
				ops = ops + '<option value="' + dic.code
						+ '" selected="selected">' + dic.title + '</option>';
				continue;
			}
			ops = ops + '<option value="' + dic.code + '">' + dic.title
					+ '</option>';
		}
		$('#identity_input').html(ops);
	}
	if (form && form.maritalstatusList) {
		var ops = '<option value="">请选择您的婚姻状况</option>';
		for (var i = 0; i < form.maritalstatusList.length; i++) {
			var dic = form.maritalstatusList[i];
			if (!dic) {
				continue;
			}
			if (form.maritalstatus && form.maritalstatus == dic.code) {
				ops = ops + '<option value="' + dic.code
						+ '" selected="selected">' + dic.title + '</option>';
				continue;
			}
			ops = ops + '<option value="' + dic.code + '">' + dic.title
					+ '</option>';
		}
		$('#maritalstatus_input').html(ops);
	}
	if (form && form.livingconditionsList) {
		var ops = '<option value="">请选择您的住宅状况</option>';
		for (var i = 0; i < form.livingconditionsList.length; i++) {
			var dic = form.livingconditionsList[i];
			if (!dic) {
				continue;
			}
			if (form.livingconditions && form.livingconditions == dic.code) {
				ops = ops + '<option value="' + dic.code
						+ '" selected="selected">' + dic.title + '</option>';
				continue;
			}
			ops = ops + '<option value="' + dic.code + '">' + dic.title
					+ '</option>';
		}
		$('#livingconditions_input').html(ops);
	}
}
</script>
	</head>
	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id='chargecontent' class='w'>
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div class="main">
				<div class="breadcrumb">
					<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;账户信息</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='right'>
					<!--  div class="o-mt">
						<strong>账户信息</strong>
					</div> -->
					<div id="ordercenter-data-title"
						style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							账户信息
						</h3>
					</div>
					<div class='infoform'>
						<form name='infoform' id='infoformId'
							action="<%=basePath%>/front/infoUpdate">
							<div class='labelArea'>
								<label>
									用户名：
								</label>
							</div>
							<div class='inputShowArea'>
								<span id="username_input"></span>
							</div>
							<div class='labelArea'>
								<label>
									邮箱：
								</label>
							</div>
							<div class='inputShowArea'>
								<span id="email_input"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%=basePath%>/pro/member/changeEmail.jsp">修改</a>
							</div>
							<div class='labelArea'>
								<label>
									<em>*</em>真实姓名：
								</label>
							</div>
							<div class='inputArea'>
								<input type='text' name='realName' id='realName_input' />
								<div class='prompt' id='realName_input_suf'></div>
							</div>
							<div class='labelArea'>
								<label>
									手机号码：
								</label>
							</div>
							<div class='inputShowArea'>
								<span id="mobile_input"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="<%=basePath%>/pro/member/changeMobile.jsp">修改</a>
							</div>
							<div class='labelArea'>
								<label>
									性别：
								</label>
							</div>
							<div class='inputArea'>
								<input type="radio" name="sex" value="0" id="RadioGroup1_0" />
								&nbsp;
								<label>
									男
								</label>
								<input type="radio" name="sex" value="1" id="RadioGroup1_1" />
								&nbsp;
								<label>
									女
								</label>
								<input type="radio" name="sex" value="2" id="RadioGroup1_2" />
								&nbsp;
								<label>
									保密
								</label>
								<div class='prompt' id='sex_input_suf'></div>
							</div>
							<div class='labelArea'>
								<label>
									<em>*</em>所在地区：
								</label>
							</div>
							<div class='inputArea'>
								<select id='deliver_province' name='province'></select>
								<select id='deliver_city' name='city'></select>
								<select id='deliver_town' name='town'></select>
							</div>
							<div class='labelArea'>
								<label>
									<em>*</em>地址：
								</label>
							</div>
							<div class='inputArea' style='height: 78px;'>
								<textarea name='address' id='address_input'></textarea>
								<div class='prompt' id='address_input_suf'></div>
							</div>

							<div class='labelArea'>
								<label>
									出生日期：
								</label>
							</div>
							<div class='inputArea'>
								<select name='birthday01' id='birthday_input1'>
									<%
										for (int i = 1930; i < new java.util.Date().getYear() + 1900; i++) {
											if (i == 1990) {
												out.print("<option value=" + i + " selected=\"selected\">" + i + "年</option>");
												continue;
											}
									%>
									<option value="<%=i%>"><%=i%>年
									</option>
									<%
										}
									%>
								</select>
								<select name='birthday02' id='birthday_input2'>
									<option value="1" selected="selected">
										1月
									</option>
									<option value="2">
										2月
									</option>
									<option value="3">
										3月
									</option>
									<option value="4">
										4月
									</option>
									<option value="5">
										5月
									</option>
									<option value="6">
										6月
									</option>
									<option value="7">
										7月
									</option>
									<option value="8">
										8月
									</option>
									<option value="9">
										9月
									</option>
									<option value="10">
										10月
									</option>
									<option value="11">
										11月
									</option>
									<option value="12">
										12月
									</option>
								</select>
								<select name='birthday03' id='birthday_input3'>
									<option value="1" selected="selected">
										1日
									</option>
									<option value="2">
										2日
									</option>
									<option value="3">
										3日
									</option>
									<option value="4">
										4日
									</option>
									<option value="5">
										5日
									</option>
									<option value="6">
										6日
									</option>
									<option value="7">
										7日
									</option>
									<option value="8">
										8日
									</option>
									<option value="9">
										9日
									</option>
									<option value="10">
										10日
									</option>
									<option value="11">
										11日
									</option>
									<option value="12">
										12日
									</option>
									<option value="13">
										13日
									</option>
									<option value="14">
										14日
									</option>
									<option value="15">
										15日
									</option>
									<option value="16">
										16日
									</option>
									<option value="17">
										17日
									</option>
									<option value="18">
										18日
									</option>
									<option value="19">
										19日
									</option>
									<option value="20">
										20日
									</option>
									<option value="21">
										21日
									</option>
									<option value="22">
										22日
									</option>
									<option value="23">
										23日
									</option>
									<option value="24">
										24日
									</option>
									<option value="25">
										25日
									</option>
									<option value="26">
										26日
									</option>
									<option value="27">
										27日
									</option>
									<option value="28">
										28日
									</option>
									<option value="29">
										29日
									</option>
									<option value="30">
										30日
									</option>
									<option value="31">
										31日
									</option>
								</select>
								<div class='prompt' id='birthday_input_suf'></div>
							</div>

							<div class='labelArea'>
								<label>
									固定电话：
								</label>
							</div>
							<div class='inputArea'>
								<input type='text' name='telephone' id='telephone_input' />
								<div class='prompt' id='telephone_input_suf'></div>
							</div>

							<div class='labelArea'>
								<label>
									身份：
								</label>
							</div>
							<div class='inputArea'>
								<select type='text' name='identity' id='identity_input'
									style="width: 200px;">

								</select>
								<div class='prompt' id='identity_input_suf'></div>
							</div>
							<div class='labelArea'>
								<label>
									婚姻状况：
								</label>
							</div>
							<div class='inputArea'>
								<select type='text' name='maritalstatus' style="width: 200px;"
									id='maritalstatus_input'></select>
								<div class='prompt' id='maritalstatus_input_suf'></div>
							</div>
							<div class='labelArea'>
								<label>
									居住状况：
								</label>
							</div>
							<div class='inputArea'>
								<select type='text' name='livingconditions'
									style="width: 200px;" id='livingconditions_input'></select>
								<div class='prompt' id='livingconditions_input_suf'></div>
							</div>

							<!-- 
				<div class='labelArea'><label>设为默认：</label></div>
				<div class='inputArea'><input type='checkbox' name='isdefault' id='da_isdefault'/></div>
				 -->
							<div class='buttonArea'>
								<input type='submit' style="width: 75px; height: 21"
									value="保  存" />
								<input type='reset' style='display: none' />
							</div>
						</form>
					</div>
				</div>
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