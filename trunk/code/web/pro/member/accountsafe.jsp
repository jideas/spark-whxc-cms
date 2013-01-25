<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>帐户安全</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet" href="<%=basePath%>/scripts/dialog/cmsDialog.css"/>
		<script type="text/javascript" src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
<script type="text/javascript">
function toSetPayPassPage(){ 
	url='<%=basePath%>/pro/member/changePayPass.jsp';
	$.post('<%=basePath%>/front/isCheckedMobile',function(result){
		result = eval('(' + result + ')'); 
		if(result.result){
			location = url;
		}else{
			cmsAlertAtt(result.message);
		}
	});
}
</script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<style type="text/css">
.main .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

.maindiv {
	width: 1040px;
	float: right;
}

.maindiv .maintable {
	width: 100%;
	border-left: 1px solid #E6E6E6;
	border-bottom: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
	/*border-top: 1px solid #E6E6E6;*/
}

.maindiv .maintable th {
	height: 30px;
	width: 100%;
	background: url('<%=basePath%>/images/page/sort-bg05.png') repeat-x;
	alight: left;
	padding-left: 5px;
	text-align: left;
	font-family: "宋体";
	font-size: 14px;
	/*border-top: 1px solid #E6E6E6;*/
}

.maintable td {
	border-bottom: 1px solid #E6E6E6;
	border-right: 0px solid #E6E6E6;
	padding-left: 5px;
}

.safetitle {
	font-size: 20px;
	font-weight: bold;
}

.safetip {
	font-size: 14px;
	color: #A2A2A2;
	vertical-align: bottom;
}

.smod a {
	font-size: 18px;
	text-decoration: none;
}

.smod a:HOVER {
	font-size: 18px;
	color: red;
	text-decoration: underline;
}
#ordercenter-data-title
{width:1032px;*width:1040px;height:35px;line-height: 35px;border-bottom: #66CD00 2px solid;float: left;text-align: left;padding-left: 8px;color: #66CD00;}
 
</style>
	</head>
	<body style="background-color: #FFFFFF" onselectstart="return false;">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id='chargecontent'>
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div class="main">
				<div class="breadcrumb">
					<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;账户安全</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='maindiv'>
					<div id="ordercenter-data-title" style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							帐户安全
						</h3>
					</div>
					<table class="maintable">
						<!-- tr>
							<th colspan="3" align="left"
								style="background-image: url('<%=basePath%>/images/page/sort-bg05.png');">
								<strong>帐户安全</strong>
							</th>
						</tr> -->
						<tr height='76px'>
							<td width='150px'>
								<span><font class='safetitle'><strong>登录密码</strong>
								</font> </span>
							</td>
							<td>
								<span class='safetip'>互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</span>
							</td>
							<td width='200px' class="smod">
								<a href="<%=basePath%>/pro/member/changepassword.jsp">修改</a>
							</td>
						</tr>
						<tr height='76px'>
							<td width='150px'>
								<span><font class='safetitle'><strong>邮箱验证</strong>
								</font> </span>
							</td>
							<td>
								<span class='safetip'>验证后，可用于快速找回登录密码，接收账户余额变动提醒。</span>
							</td>
							<td width='200px' class="smod">
								<a href="<%=basePath%>/pro/member/changeEmail.jsp">修改</a>
							</td>
						</tr>
						<tr height='76px'>
							<td width='150px'>
								<span><font class='safetitle'><strong>手机验证</strong>
								</font> </span>
							</td>
							<td>
								<span class='safetip'>验证后，可用于快速找回登录密码及支付密码，接收账户余额变动提醒。</span>
							</td>
							<td width='200px' class="smod">
								<a href="<%=basePath%>/pro/member/changeMobile.jsp">修改</a>
							</td>
						</tr>
						<tr height='76px'>
							<td width='150px' style='border-bottom: 0px solid #E6E6E6;'> <!-- 最后一行不显示底部border -->
								<span><font class='safetitle'><strong>支付密码</strong>
								</font> </span>
							</td>
							<td style='border-bottom: 0px solid #E6E6E6;'>
								<span class='safetip'>启用支付密码后，在使用账户中余额或优惠劵等资产时，需输入支付密码。</span>
							</td>
							<td width='200px' class="smod" style='border-bottom: 0px solid #E6E6E6;'>
								<a href="javascript:void(0);" onclick="toSetPayPassPage();">设置</a>
							</td>
						</tr>
					</table>
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