<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
<style type="text/css">
#service{
	position:relative;
	width:1198px;
	*width:1200px;
	height:200px;
	text-align:left;
	margin: 0 auto;
	float: left;
	border-bottom: none;
	text-align: center;
}

#service .direction_line{
	vertical-align:middle;
	width:1100px;
	text-align: center;
	display:inline-block;
}

#service .direction{
	float: left;
	width:175px;
	height:140px;
	margin-top:15px;
	margin-left:20px;
}
#service .direction_1{
	margin-left: 30px;
}

#service dl dt{
	padding-left:30px;
	color:#333333;
	font-size:13px;
	font-weight: bold;
	height: 20px;
	text-align: left;
}

#service dl dd{
	padding-left: 20px;
	margin-left:10px;
	margin-top:5px;
	color: #000000;
	font-size:12px;
	height: 110px;
	border-left:1px dotted #CCCCCC;
	text-align: left;
	*display:inline-block;
	
}

#service dl dd a,#service dl dd a:link{
	color: #000000;
	height: 18px;
	line-height: 18px;
	*height: 15px;
	*line-height: 15px;
	margin-bottom: 4px;
	text-decoration: none;
}
#service dl dd a:hover{
	color: #FF0000;
	text-decoration: underline;
}

#service dl.direction_1 dt{
	background: url("<%=basePath%>/images/page/service-icon-buy.png") no-repeat left;
}
#service dl.direction_2 dt{
	background: url("<%=basePath%>/images/page/service-icon-member.png") no-repeat left;
}
#service dl.direction_3 dt{
	background: url("<%=basePath%>/images/page/service-icon-pay.png") no-repeat left;
}
#service dl.direction_4 dt{
	background: url("<%=basePath%>/images/page/service-icon-delivy.png") no-repeat left;
}
#service dl.direction_5 dt{
	background: url("<%=basePath%>/images/page/service-icon-sale.png") no-repeat left;
}
#service dl.direction_6 dt{
	background: url("<%=basePath%>/images/page/service-icon-seven.png") no-repeat left;
}

#service .promise{
	position:absolute;
	bottom: 0px;
	left:0px;
	float:left;
	height: 44px;
	width: 1200px;
	margin-bottom:0px;
}
</style>
	</head>
	<body>
<div id="service">
	 <div class="direction_line" style="border: 1px solid RGB(221,221,221);width:1198px;*width:1200px;border-bottom: none;">
		<dl class="direction direction_1">
			<dt>购物指南</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=00">购物流程</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=01">下单时间</a></div>
			    <!-- div><a target="_blank" href="/pub/direction/direction.jsp?code=02">订单修改与取消</a></div -->
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=03">用户须知</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=04">用户协议</a></div>
			</dd>
		</dl>
		<dl class="direction direction_2">
			<dt>会员中心</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=10">会员权益</a></div>
				<!--div><a target="_blank" href="/pub/direction/direction.jsp?code=11">VIP会员</a></div-->
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=12">会员积分</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=13">余额查询</a></div>
			</dd>
		</dl>
		<dl class="direction direction_3">
			<dt>支付方式</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=21">网上银行</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=22">面值卡</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=23">网上充值</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=24">代金券</a></div>
			</dd>
		</dl>
		<dl class="direction direction_4">
			<dt>配送说明</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=30">配送范围</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=31">配送时间</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=32">配送方式</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=33">取货时间</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=34">验收评价</a></div>
			</dd>
		</dl>
		<dl class="direction direction_5">
			<dt>售后服务</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=40">购物保障</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=41">退换货原则</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=42">退换货流程</a></div>
			</dd>
		</dl>
		<dl class="direction direction_6">
			<dt>7号生活馆</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=50">公司介绍</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=51">企业价值</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=52">企业理念</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=53">运行模式</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=54">基地介绍</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=55">资质和荣誉</a></div>
			</dd>
		</dl>
	</div>
	<div class="primise"><img src="<%=basePath%>/images/page/service-promise.png" width="1200px" height="44px"/></div>
</div>
	</body>
</html>