<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
		$.ajax({
			type: 'post', 
			url: mainWeb + "/front/hasLogin",
			dataType: 'json',
			success: function(data) {
				if (data.result) {
					 $('#login_username').html('<font  color="orange" face="幼圆" size="3">'
							+ data.message + '</font>');
					$('.login-in').css('display', 'none');
					$('#logoutSpan').css('display', '');
				} else {
					 $('#logoutSpan').css('display', 'none');
				}
			} 
		});
});
function doLogout() {
	url = '<%=basePath%>/front/logout';
	$.post(url, {
				id : '12'
			}, function(result) {
				if (result.result) {
					$('#login_username').html('');
					$('.login-in').css('display', '');
					$('#logoutSpan').css('display', 'none');
				}
			}, 'json');
}
function AddFavorite(sURL, sTitle) {
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, "");
		} catch (e) {
			cmsAlertError("加入收藏失败，请手动进行添加");
		}
	}
}
function SetHome(obj, vrl) {
	try {
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(vrl);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				cmsAlertError("设为主页失败，请手动进行添加");
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1']
					.getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', vrl);
		}
	}
}

function toOrderCenter()
{
		window.location.href = "<%=basePath%>/front/order/getOrders";
}
        </script>
		<link style="text/css" href="<%=basePath%>/css/shortcut.css"
			rel="stylesheet">

	</head>
	<body>
		<div class="shortcut">
			<div class="shortcut-body">
				<div class="welcome">
					<ul>
						<li>
							<span class="w1">您好！</span>
							<span id="login_username"></span>

							<span class="w2">欢迎光临7号生活馆</span>

							<span class="login-in" id="loginSpan1"><a
								href="<%=basePath%>/pub/sub/login.jsp">[登录]</a> </span>

							<span class="login-in" id="loginSpan2"><a
								href="<%=basePath%>/pub/sub/registration.jsp">[免费注册]</a> </span>

							<span class="login-out" style="display: none;" id="logoutSpan"><a
								href="#" onclick="doLogout();">[离开]</a> </span>
						</li>
					</ul>
				</div>
				<div class="cutkey">
					<ul>
						<li>
							<span style="width: 40px;"><a href="<%=basePath%>/">首页</a></span><span style="width: 60px;"><a href="javascript:void(0)"
								onclick="toOrderCenter()" style="vertical-align: baseline;zoom:1;">我的7号</a></span>
							<span><img
									src="<%=basePath%>/images/page/page_top_gift.png"
									style="margin:5px 0px 0px 3px;float: left;"><a
								href="<%=basePath%>/pro/member/charge.jsp">在线充值</a> </span>
							<span><a href="javascript:void(0);"
								onclick="SetHome(this,window.location);">设为主页</a> </span>
							<span><img
									src="<%=basePath%>/images/page/page_top_fav.png"
									style="margin-right: 5px"><a href="javascript:void(0);"
								onclick="AddFavorite(window.location,document.title);">加入收藏</a>
							</span>
							<span><a href="<%=basePath%>/pub/questionnaire.jsp">问卷调查</a> </span>
							<span><a href="<%=basePath%>/pub/messageBorder.jsp">客户留言</a> </span>
							<span class="tailor"><a
								href="http://www.chinavegetable.com.cn" target="_blank">新辰食品</a>
							</span>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 
		<div id="shortcut-body">
			<div id="shortcut-left">
				<span><font size="2" face="宋体">您好, </font> </span>
				<span><font color="orange" face="幼圆" size="3"><b>孙悟空！
					</b> </font> </span>
				<span><font size="2" face="宋体">7号生活馆欢迎您！ </font> </span>
				<span><font style="cursor: hand;" onclick="alert('退出登录');"
					size="2" color="gray"> [退出]</font> </span>
			</div>
		</div>
 -->
	</body>
</html>
