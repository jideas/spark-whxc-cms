<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	text-align: left;
	padding-left: 5px;
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
	color: #F00;
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
					购物指南
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('00')">购物流程</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('01')">下单时间</a>
					</div>
					<!-- div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('02')">订单修改与取消</a>
					</div -->
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('03')">用户须知</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('04')">用户协议</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					会员中心
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('10')">会员权益</a>
					</div>
					<!-- div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('11')" >VIP会员</a>
					</div -->
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('12')" >会员积分</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('13')" >余额查询</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					支付方式
				</dt>
				<dd>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('21')" >网上银行</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('22')" >面值卡</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('23')" >网上充值</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('24')" >代金券</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					配送说明
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('30')">配送范围</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('31')" >配送时间</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('32')" >配送方式</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('33')" >取货时间</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('34')" >验收评价</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					售后服务
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('40')">购物保障</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('41')" >退换货原则</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('42')" >退换货流程</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					7号生活馆
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('50')">公司介绍</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('51')" >企业价值</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('52')" >企业理念</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('53')" >运行模式</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('54')" >基地介绍</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('55')" >资质和荣誉</a>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>