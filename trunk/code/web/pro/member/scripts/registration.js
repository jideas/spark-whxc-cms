function validateForm() {
	var returnValue = true;
	var email = $('#email_input').val();
	if ($.trim(email) == '') {
		$('#email_input').css('border', 'red solid 2px');
		$('#email_input_suf').html('<font color=red >&nbsp;���������д��</font>');
		returnValue = false;
	} else {
		$('#email_input').css('border', '#ABADB3 solid 1px');
		$('#email_input_suf').html('');
	}
	var _emailReg = /^[0-9a-z][a-z0-9\._-]{1,}@[a-z0-9-]{1,}[a-z0-9]\.[a-z\.]{1,}[a-z]$/;
	if (email && !_emailReg.test(email)) {
		$('#email_input').css('border', 'red solid 2px');
		$('#email_input_suf').html('<font color=red >&nbsp;�����ʽ����ȷ!</font>');
		returnValue = false;
	} else if (email) {
		$('#email_input').css('border', '#ABADB3 solid 1px');
		$('#email_input_suf').html('');
	}
	var mobile = $('#mobile_input').val();
	if ($.trim(mobile) == '') {
		$('#mobile_input').css('border', 'red solid 2px');
		$('#mobile_input_suf').html('<font color=red >&nbsp;�ֻ����������д��</font>');
		returnValue = false;
	} else {
		$('#mobile_input').css('border', '#ABADB3 solid 1px');
		$('#mobile_input_suf').html('');
	}
	var _mobileReg = /^1[3-8]\d{9}$/;
	if (mobile && !_mobileReg.test(mobile)) {
		$('#mobile_input').css('border', 'red solid 2px');
		$('#mobile_input_suf').html('<font color=red >&nbsp;�ֻ������ʽ����ȷ��</font>');
		returnValue = false;
	} else if (mobile) {
		$('#mobile_input').css('border', '#ABADB3 solid 1px');
		$('#mobile_input_suf').html('');
	}
	var userName = $('#userName_input').val();
	if ($.trim(userName) == '') {
		$('#userName_input').css('border', 'red solid 2px');
		$('#userName_input_suf').html('<font color=red >&nbsp;��¼��������д��</font>');
		returnValue = false;
	} else {
		$('#userName_input').css('border', '#ABADB3 solid 1px');
		$('#userName_input_suf').html('');
	}
	var _userNameReg = /^[a-zA-Z]\w*$/;
	var _userNameReg2 = /^[a-zA-Z0-9_\u4e00-\u9fa5]*$ /;
	if (userName && !_userNameReg.test(userName)) {
		if (userName && !_userNameReg2.test(userName)) {
			$('#userName_input').css('border', 'red solid 2px');
			$('#userName_input_suf')
					.html('<font color=red >&nbsp;��¼��ֻ���Ǻ�����ĸ�����»��ߣ�</font>');
			returnValue = false;
		} else if (userName && (userName.length < 2 || userName.length > 20)) {
			$('#userName_input').css('border', 'red solid 2px');
			$('#userName_input_suf')
					.html('<font color=red >&nbsp;��¼��������Ҫ������</font>');
			returnValue = false;
		}
	} else if (userName) {
		$('#userName_input').css('border', '#ABADB3 solid 1px');
		$('#userName_input_suf').html('');
		if (userName && (userName.length < 8 || userName.length > 30)) {
			$('#userName_input').css('border', 'red solid 2px');
			$('#userName_input_suf')
					.html('<font color=red >&nbsp;��¼��������Ҫ������</font>');
			returnValue = false;
		}
	}
	var pass0 = $('#password_input').val();
	if ($.trim(pass0) == '') {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf')
				.html('<font color=red >&nbsp;��¼���������д��</font>');
		returnValue = false;
	} else if (pass0) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#password_input_suf').html('');
	}
	var pass1 = $('#repassword_input').val();
	if ($.trim(pass1) == '') {
		$('#repassword_input').css('border', 'red solid 2px');
		$('#repassword_input_suf')
				.html('<font color=red >&nbsp;����дȷ�����룡</font>');
		returnValue = false;
	} else if (pass1) {
		$('#repassword_input').css('border', '#ABADB3 solid 1px');
		$('#repassword_input_suf').html('');
	}
	if (pass0 && pass1 && pass0 != pass1) {
		$('#password_input').css('border', 'red solid 2px');
		$('#repassword_input').css('border', 'red solid 2px');
		$('#repassword_input_suf')
				.html('<font color=red >&nbsp;������д�����벻һ��!</font>');
		returnValue = false;
	} else if (pass0 && pass1) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#repassword_input').css('border', '#ABADB3 solid 1px');
		$('#repassword_input_suf').html('');
	}
	if (pass0 && !/^[A-Za-z0-9]+$/.test(pass0)) {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf')
				.html('<font color=red >&nbsp;�����������ĸ��������ɣ�</font>');
		returnValue = false;
	} else if (pass0) {
		$('#password_input').css('border', '#ABADB3 solid 1px');
		$('#password_input_suf').html('');
	}
	if (pass0 && (pass0.length < 6 || pass0.length > 16)) {
		$('#password_input').css('border', 'red solid 2px');
		$('#password_input_suf')
				.html('<font color=red >&nbsp;����Ӧ��Ϊ6��16λ��</font>');
		returnValue = false;
	}
	if (!validate())// ��֤��֤��
	{
		returnValue = false;
	}
	return returnValue;
}
$(document).ready(function() {
	var form = $('#registform');
	form.submit(function() {
		$.post(form.attr('action'), form.serialize(), function(result, status) {
					if (result.result) {
						window.location = basePath;
					} else {
						if (1 == result.returnObj) {
							$('#email_input').css('border', 'red solid 2px');
							$('#email_input_suf')
									.html('<font color=red >&nbsp;'
											+ result.message + '</font>');
						}
						if (2 == result.returnObj) {
							$('#mobile_input').css('border', 'red solid 2px');
							$('#mobile_input_suf')
									.html('<font color=red >&nbsp;'
											+ result.message + '</font>');
						}
						if (3 == result.returnObj) {
							$('#userName_input').css('border', 'red solid 2px');
							$('#userName_input_suf')
									.html('<font color=red >&nbsp;'
											+ result.message + '</font>');
						}
					}
				}, 'json');
		return false;
	});
});
function doSubmit() {
	if (!validateForm()) {
		return;
	}
	var form = $('#registform');
	form.attr("action", mainWeb + "/front/regist");
	form.submit();

}