$(document).ready(function onLoginLoaded() {
			GetLastUser();
		});

function GetLastUser() {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";// GUID标识符
	var usr = GetCookie(id);
	if (usr != null) {
		var userName_input = document.getElementById('userName_input');
		if (userName_input) {
			userName_input.value = usr;
			document.getElementById('rememberUserName').checked = true;
		}
	} else {
		var userName_input = document.getElementById('userName_input');
		if (userName_input) {
			userName_input.value = oldValue;
		}
	}
}
// 点击登录时触发客户端事件

function SetPwdAndChk() {
	// 取用户名
	var usr = document.getElementById('userName_input').value;
	// 将最后一个用户信息写入到Cookie
	SetLastUser(usr);
	if (!document.getElementById('rememberUserName').checked) {
		ResetCookie();
	}
}

function SetLastUser(usr) {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
	var expdate = new Date();
	// 当前时间加上两周的时间
	expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
	SetCookie(id, usr, expdate);
}
// 取Cookie的值

function GetCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
			break;
	}
	return null;
}

function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}
// 写入到Cookie

function SetCookie(name, value, expires) {
	var argv = SetCookie.arguments;
	// 本例中length = 3
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape(value)
			+ ((expires == null) ? "" : ("; expires=" + expires.toGMTString()))
			+ ((path == null) ? "" : ("; path=" + path))
			+ ((domain == null) ? "" : ("; domain=" + domain))
			+ ((secure == true) ? "; secure" : "");
}

function ResetCookie() {
	var usr = document.getElementById('userName_input').value;
	var expdate = new Date();
	SetCookie(usr, null, expdate);
}