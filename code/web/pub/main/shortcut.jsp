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
					 $('#login_username').html('<font  color="orange" face="��Բ" size="3">'
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
			cmsAlertError("�����ղ�ʧ�ܣ����ֶ��������");
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
				cmsAlertError("��Ϊ��ҳʧ�ܣ����ֶ��������");
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
							<span class="w1">���ã�</span>
							<span id="login_username"></span>

							<span class="w2">��ӭ����7�������</span>

							<span class="login-in" id="loginSpan1"><a
								href="<%=basePath%>/pub/sub/login.jsp">[��¼]</a> </span>

							<span class="login-in" id="loginSpan2"><a
								href="<%=basePath%>/pub/sub/registration.jsp">[���ע��]</a> </span>

							<span class="login-out" style="display: none;" id="logoutSpan"><a
								href="#" onclick="doLogout();">[�뿪]</a> </span>
						</li>
					</ul>
				</div>
				<div class="cutkey">
					<ul>
						<li>
							<span><a href="<%=basePath%>/">��ҳ</a></span>
							<span><a href="javascript:void(0)" onclick="toOrderCenter()" style="vertical-align: baseline;zoom:1;">�ҵ�7��</a></span>
							<span><img src="<%=basePath%>/images/page/page_top_gift.png" style="margin:5px 0px 0px 0px;float: left;"><a href="<%=basePath%>/pro/member/charge.jsp">���߳�ֵ</a></span>
							<span><a href="javascript:void(0);" onclick="SetHome(this,window.location);">��Ϊ��ҳ</a></span>
							<span><img src="<%=basePath%>/images/page/page_top_fav.png" style="margin-right: 3px"><a href="javascript:void(0);" onclick="AddFavorite(window.location,document.title);">�����ղ�</a></span>
							<span><a href="<%=basePath%>/pub/questionnaire.jsp">�ʾ����</a></span>
							<span><a href="<%=basePath%>/pub/messageBorder.jsp">�ͻ�����</a></span>
							<span><a href="http://www.chinavegetable.com.cn" target="_blank">�³�ʳƷ</a></span>
							<span><img src="<%=basePath%>/images/page/sina.gif" style="margin-right: 3px"><a href="http://weibo.com/signup/signup.php?inviteCode=2643910193" target="_blank">����΢��</a></span>
							<span><img src="<%=basePath%>/images/page/tenxun.gif" style="margin-right: 3px"><a href="http://e.t.qq.com/qhao_777?pgv_ref=im.perinfo.perinfo.icon" target="_blank">��Ѷ΢��</a></span>
							<span class="tailor"><a href="tencent://message/?uin=1215155757"><img src="<%=basePath%>/images/page/qq_online.gif" title="�ͷ�QQ" /></a></span>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 
		<div id="shortcut-body">
			<div id="shortcut-left">
				<span><font size="2" face="����">����, </font> </span>
				<span><font color="orange" face="��Բ" size="3"><b>����գ�
					</b> </font> </span>
				<span><font size="2" face="����">7������ݻ�ӭ���� </font> </span>
				<span><font style="cursor: hand;" onclick="alert('�˳���¼');"
					size="2" color="gray"> [�˳�]</font> </span>
			</div>
		</div>
 -->
	</body>
</html>
