<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
<style type="text/css">
#my7 .mc {
	border: solid #E6E6E6;
	border-width: 0 1px 1px;
	overflow: hidden;
}

#my7 dl dt {
	position: relative;
	margin-bottom: -1px;
	height: 27px;
	border: solid #E6E6E6;
	border-width: 1px 0;
	background: url('<%=basePath%>/images/page/bg_jdleft.jpg') #E6E6E6 repeat-x 0 -30px;
	font-weight: bold;
	line-height: 27px;
	cursor: pointer;
	text-align: left;
	padding-left: 5px;
}

#my7 dl dt:hover {
	background-position: 0 0;
}

#my7 dd {
	padding: 4px 0 5px;
}

#my7 dd .item {
	height: 30px;
	line-height: 30px;
	text-align: left;
	padding-left: 5px;
	border-top: 1px dotted #CACACA;
	margin-left: 5px;
	margin-right: 5px;
	
}

#my7 dd .item a{
	padding-left: 2px;
	font-family: "宋体";
}

#my7 dd .item a:link,a:visited {
	color: #333;
	text-decoration: none;
}

#my7 dd .item a:hover {
	color: #C00;
	text-decoration: underline;
}

#my7 dd .item a:active {
	color: #900;
}
#my7 dd .pre {
	border-top: none;
}
</style>
</head>
<body style="background-color: #FFFFFF">
	<div id="my7" class="m">
		<div class="mc">
			<dl>
				<dt>
					订单中心
				</dt>
				<dd>
					<div id="_MYJD_ordercenter" class="item pre">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/front/order/getOrders">我的订单</a>
					</div>
					<div id="_MYJD_charge" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/charge.jsp">面值卡充值</a>
					</div>
					<div id="_MYJD_online_charge" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/onlineCharge.jsp">在线支付充值</a>
					</div>
					<div id="_MYJD_deliver" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/delivercharge.jsp">包月送货</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					帐户中心
				</dt>
				<dd>
					<div id="_MYJD_personal" class="item pre">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/accountinfo.jsp">帐户信息</a>
					</div>
					<div id="_MYJD_safe" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/accountsafe.jsp" >帐户安全</a>
					</div>
					<div id="_MYJD_balance" class="item">
						&nbsp;&gt;&nbsp;<a  href="<%=basePath%>/front/getBalance">帐户余额</a>
					</div>
					<div id="_MYJD_score" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/front/toVantegePage">我的积分</a>
					</div>
					<div id="_MYJD_easybuy" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/deliveraddress.jsp" >收货地址</a>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>