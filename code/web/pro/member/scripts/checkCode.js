var code = ""; // 在全局 定义验证码
function createCode() {
	code = "";
	var codeLength = 4;// 验证码的长度
	var checkCode = document.getElementById("checkCode");
	checkCode.value = "";
	var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
			'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

	for (var i = 0; i < codeLength; i++) {
		var charIndex = Math.floor(Math.random() * 32);
		code += selectChar[charIndex];
	}
	if (code.length != codeLength) {
		createCode();
	}
	document.getElementById("checkCode").innerHTML = code;
}

function validate() {
	var inputCode = document.getElementById("checkNum").value.toUpperCase();
	if (inputCode.length <= 0) {
		$('#checkNum').css('border', 'red solid 2px');
		createCode();
		return false;
	} else if (inputCode != code) {
		$('#checkNum').css('border', 'red solid 2px');
		createCode();
		return false;
	} else {
		$('#checkNum').css('border', '#ABADB3 solid 1px');
		return true;
	}
}