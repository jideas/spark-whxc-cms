function toRegistrationPage() {
	this.location = mainWeb+'/pub/sub/registration.jsp';
}
var oldValue = '邮箱\\手机号\\登录名';

function userFocus() {
	var value = $('#userName_input').val();
	if (value == oldValue) {
		$('#userName_input').val(' ');
	}
	$('#userName_input').css('color', '#000000');
}

function userBlur() {
	var value = $('#userName_input').val();
	if ($.trim(value) == '') {
		$('#userName_input').val(oldValue);
		$('#userName_input').css('color', 'gray');
	}
}

function doLogin() {
	if (!validateForm()) {
		return;
	}
	var form = $('#realLoginForm');
	form.attr("action", mainWeb + "/front/login");
	form.submit(); 
	SetPwdAndChk(); 
}

function validateForm() {
	var returnValue = true;
	var userName = $('#userName_input').val();
	if ($.trim(userName) == '' || $.trim(userName) == oldValue) {
		$('#userName_input').css('border', 'red solid 2px');
		$('#userName_input_suf').html('<font color=red >&nbsp;必须填写！</font>');
		returnValue = false;
	} else {
		$('#userName_input').css('border', '#ABADB3 solid 1px');
		$('#userName_input_suf').html('');
	}
	var pass0 = $('#password_input').val();
	if ($.trim(pass0) == '') {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf').html('<font color=red >&nbsp;必须填写！</font>');
		returnValue = false;
	} else if (pass0) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#password_input_suf').html('');
	}
	if (!validate())// 验证验证码
	{
		returnValue = false;
	}
	return returnValue;
}

$(document).ready(function() {
	var form = $('#realLoginForm');
	form.submit(function() {
		$.post(form.attr('action'), form.serialize(), function(result, status) {
					if (result.result) {
						url = basePath;
						if(result.returnObj&&result.returnObj!='null'){
							url = result.returnObj;
						}
						window.location = url;
					} else {
						createCode();
						if (1 == result.returnObj) {
							$('#userName_input').css('border', 'red solid 2px');
							$('#userName_input_suf')
									.html('<font color=red >&nbsp;'
											+ result.message + '</font>');
						} else if (2 == result.returnObj) {
							$('#password_input').css('border', 'red solid 2px');
							$('#password_input_suf')
									.html('<font color=red >&nbsp;'
											+ result.message + '</font>');
						}
					}
				}, 'json');
		return false;
	});
});