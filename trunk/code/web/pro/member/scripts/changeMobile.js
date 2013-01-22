var _changePsdApp = null;
$(document).ready(function() {
			_changePsdApp = new ChangePasswordApp();
			_changePsdApp.createCode();
			_changePsdApp.whenItSubmit();
		});

function ChangePasswordApp() {
	// 输入初始状态
	var inputStatus = 0;
	// 验证码
	var validateCode = "";

	this.submit = function() {
		if (!validate()) {
			return;
		}
		var form = $('#changePasswordForm');
		form.attr("action", mainWeb + "/front/checkSafeSms");
		form.submit();
	};

	this.whenItSubmit = function() {
		var form = $('#changePasswordForm');
		form.submit(function() {
			$.post(form.attr('action'), form.serialize(), function(result,
					status) { 
				if (result.result) {
					document.getElementById('changeMobileFormDiv').style.display = 'none';
					document.getElementById('SuccessInfoDiv').style.display = '';
				} else {
					_changePsdApp.createCode();
					if (result.returnObj == 0) {
						cmsAlertAtt(result.message);
					} else if (result.returnObj == 1) {
						$(".table_a #smsCodeInput_error")
								.html("<font color=red>" + result.message
										+ "</font>");
						$(".table_a #smsCodeInput_error").show();
					}
				}
			}, 'json');
			return false;
		});
	}

	var canSendSmsCode = true;
	this.sendSafeSms = function() {
		if (!canSendSmsCode) {
			return;
		}
		if (!validateSendFrom()) {
			return;
		}
		var password = $('#password').val();
		var phoneNo = $('#phoneNo').val();
		var url = mainWeb + '/front/sendSafeSms';
		$.post(url, {
					password : password,
					phoneNo : phoneNo
				}, function(result, status) {
					if (result.result) {
						document.getElementById("getSmsCodeDiv").style.backgroundImage = 'url('
								+ mainWeb + '/images/page/get_sms_code2.png)';
						$('#getSmsCodeDiv')
								.html('<font color=gray>&nbsp;180秒后重新发送!</font>');
						cmsAlertAtt('验证码已发送，请查收短信!');
						$('#smsCodeInput').attr('disabled', false);
						$('#checkNum').attr('disabled', false);
						$('#sureToModifyButton').attr('disabled', false);
						canSendSmsCode = false;
						doTimer();
					} else {
						if (result.returnObj == '0') {
							cmsAlertAtt(result.message);
						} else if (result.returnObj == '1') {
							$(".table_a #password_error")
									.html("<font color=red>" + result.message
											+ "</font>");
							$(".table_a #password_error").show();
						}
						canSendSmsCode = true;
					}
				}, 'json');
	}
	var doTimer = function() {
		var time = 180;
		var m = setInterval(function() {
			time = time - 1;
			$('#getSmsCodeDiv').html('<font color=gray>&nbsp;' + time
					+ '秒后重新发送!</font>');
			if (time == 0) {
				clearInterval(m);
				$('#smsCodeInput').attr('disabled', true);
				document.getElementById("getSmsCodeDiv").style.backgroundImage = 'url('
						+ mainWeb + '/images/page/get_sms_code.png)';
				$('#getSmsCodeDiv').html('获取短信验证码');
				canSendSmsCode = true;
			}
		}, 1000);
	}
	var validateSendFrom = function() {
		var returnValue = true;
		var password = $('#password').val();
		var phoneNo = $('#phoneNo').val();
		if ($.trim(password) == '') {
			$(".table_a #password_error")
					.html("<font color=red>请输入登录密码</font>");
			$(".table_a #password_error").show();
			returnValue = false;
		}
		if ($.trim(phoneNo) == '') {
			$(".table_a #phoneNo_error").html("<font color=red>请输入手机号码</font>");
			$(".table_a #phoneNo_error").show();
			returnValue = false;
		} else if (!/^1[3-8]\d{9}$/.test(phoneNo)) {
			$(".table_a #phoneNo_error")
					.html("<font color=red>手机号码格式不正确</font>");
			$(".table_a #phoneNo_error").show();
			returnValue = false;
		}
		return returnValue;
	}

	this.createCode = function(isInner) {
		if (!isInner) {
			validateCode = "";
		}
		var codeLength = 4;// 验证码的长度
		$(".table_a #checkCode").html();
		var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

		for (var i = 0; i < codeLength; i++) {
			var charIndex = Math.floor(Math.random() * 32);
			validateCode += selectChar[charIndex];
		}
		if (validateCode.length != codeLength) {
			this.createCode(true);
		}
		$(".table_a #checkCode").html(validateCode);
	};

	var validate = function() {
		if (inputStatus == 0) {
			$(".table_a #prepassword_error").html("请输入登录密码");
			$(".table_a #prepassword_error").show();
			return false;
		}
		var errorDivs = $(".table_a div[id$='_error']");
		for (var i = 0; i < errorDivs.length; i++) {
			var errorDiv = errorDivs[i];
			if (!isEmptyString(errorDiv.innerHTML)) {
				return false;
			}
		}
		return true;
	};

	var isEmptyString = function(string) {
		if (string == null || string == "") {
			return true;
		} else {
			return false;
		}
	};
	var registeValidate = function() {
		$(".table_a #password").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #password_error")
						.html("<font color=red>请输入登录密码</font>");
				$(".table_a #password_error").show();
			} else if (!/^[A-Za-z0-9]+$/.test($.trim($(this).val()))) {
				$(".table_a #password_error")
						.html("<font color=red>登录密码只能由字母和数字组成</font>");
				$(".table_a #password_error").show();
			} else if ($.trim($(this).val()).length < 6
					|| $.trim($(this).val()).length > 16) {
				$(".table_a #password_error")
						.html("<font color=red>登录密码长度应该在6到16之间</font>");
				$(".table_a #password_error").show();
			}
		});
		$(".table_a #password").bind('focusin', function() {
					$(".table_a #password_error").html('');
					$(".table_a #password_error").hide();
					inputStatus = inputStatus == 0
							? inputStatus -= 1
							: inputStatus;
				});
		$(".table_a #phoneNo").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #phoneNo_error")
						.html("<font color=red>请输入手机号码</font>");
				$(".table_a #phoneNo_error").show();
			} else if (!/^1[3-8]\d{9}$/.test($.trim($(this).val()))) {
				$(".table_a #phoneNo_error")
						.html("<font color=red>手机号码格式不正确</font>");
				$(".table_a #phoneNo_error").show();
			}
		});
		$(".table_a #phoneNo").bind('focusin', function() {
					$(".table_a #phoneNo_error").html('');
					$(".table_a #phoneNo_error").hide();
					inputStatus = inputStatus == 0
							? inputStatus -= 1
							: inputStatus;
				});

		$(".table_a #smsCodeInput").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #smsCodeInput_error")
						.html("<font color=red>请输入短信验证码</font>");
				$(".table_a #smsCodeInput_error").show();
			}
		});
		$(".table_a #smsCodeInput").bind('focusin', function() {
					$(".table_a #smsCodeInput_error").html('');
					$(".table_a #smsCodeInput_error").hide();
					inputStatus = inputStatus == 0
							? inputStatus -= 1
							: inputStatus;
				});

		$(".table_a #checkNum").bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$(".table_a #validatecode_error")
						.html("<font color=red>请输入验证码</font>");
				$(".table_a #validatecode_error").show();
			} else if ($(this).val().toUpperCase() != validateCode
					.toUpperCase()) {
				$(".table_a #validatecode_error")
						.html("<font color=red>验证码输入有误</font>");
				$(".table_a #validatecode_error").show();
				this.createCode();
			}
		});
		$(".table_a #checkNum").bind('focusin', function() {
					$(".table_a #validatecode_error").html('');
					$(".table_a #validatecode_error").hide();
					inputStatus = inputStatus == 0
							? inputStatus -= 1
							: inputStatus;
				});
	};

	var init = function() {
		registeValidate();
		$(".table_a #password").focus();
	};

	init();
}
