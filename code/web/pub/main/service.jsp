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
			<dt>����ָ��</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=00">��������</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=01">�µ�ʱ��</a></div>
			    <!-- div><a target="_blank" href="/pub/direction/direction.jsp?code=02">�����޸���ȡ��</a></div -->
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=03">�û���֪</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=04">�û�Э��</a></div>
			</dd>
		</dl>
		<dl class="direction direction_2">
			<dt>��Ա����</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=10">��ԱȨ��</a></div>
				<!--div><a target="_blank" href="/pub/direction/direction.jsp?code=11">VIP��Ա</a></div-->
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=12">��Ա����</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=13">����ѯ</a></div>
			</dd>
		</dl>
		<dl class="direction direction_3">
			<dt>֧����ʽ</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=21">��������</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=22">��ֵ��</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=23">���ϳ�ֵ</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=24">����ȯ</a></div>
			</dd>
		</dl>
		<dl class="direction direction_4">
			<dt>����˵��</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=30">���ͷ�Χ</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=31">����ʱ��</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=32">���ͷ�ʽ</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=33">ȡ��ʱ��</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=34">��������</a></div>
			</dd>
		</dl>
		<dl class="direction direction_5">
			<dt>�ۺ����</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=40">���ﱣ��</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=41">�˻���ԭ��</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=42">�˻�������</a></div>
			</dd>
		</dl>
		<dl class="direction direction_6">
			<dt>7�������</dt>
			<dd>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=50">��˾����</a></div>
				<div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=51">��ҵ��ֵ</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=52">��ҵ����</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=53">����ģʽ</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=54">���ؽ���</a></div>
			    <div><a target="_blank" href="<%=basePath %>/pub/direction/direction.jsp?code=55">���ʺ�����</a></div>
			</dd>
		</dl>
	</div>
	<div class="primise"><img src="<%=basePath%>/images/page/service-promise.png" width="1200px" height="44px"/></div>
</div>
	</body>
</html>