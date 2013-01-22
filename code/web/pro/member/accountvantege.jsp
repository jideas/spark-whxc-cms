<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<style type="text/css">
.main {
	width: 1200;
	float: left;
	margin-bottom: 10px;
}

.right {
	width: 1040px;
	line-height: 1.5em;
	float: right;
	font-family: Verdana;
}

.main .left {
	float: left;
	width: 150px;
	margin-right: 10px
}

/** user base **/
.right a:link,.right a:visited {
	color: #005ea7;
}

.m .mt a:link,.m .mt a:visited {
	color: #333
}

.main {
	margin-bottom: 10px;
}

.form em {
	color: #ff6600;
	margin-right: 3px;
}

.form .text,.form .area,.sele {
	padding: 4px;
	border: 1px solid;
	border-color: #aaa #ddd #ddd #aaa;
}

.form .text {
	width: 130px;
	margin-right: 5px;
}

.form .area {
	width: 300px;
	height: 55px;
	margin-right: 5px;
}

.sele {
	margin-right: 10px;
}

/*.right{width:830px;float:right;font-family:Verdana}*/
.right .form .item {
	margin-bottom: 10px;
}

.right .form .label {
	color: #666666;
	text-align: right;
}

.right .form .label,.right .form label {
	line-height: 25px;
	margin-right: 8px;
}

.right .form .point {
	color: #999999;
	padding-top: 3px;
}

.right .form .check,.right .form .radio {
	margin-top: 3px;
}

.right .check,.right .radio {
	margin-top: 5px;
	float: left;
}

.right .radio {
	margin-top: 5px;
	float: left;
}

/* user info */
.right .o-mt { *
	height: 27px;
	font-size: 12px;
	font-weight: 400;
	letter-spacing: 1px;
	text-align: left;
	background-image: url('<%=basePath%>/images/page/sort-bg05.png');
	padding: 4px 5px;
	border-top: 1px solid #E6E6E6;
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
}

.right .tab {
	margin-top: 10px;
	margin-bottom: 10px
}

.right .i-m {
	float: left;
	width: 600px;
	padding-top: 10px
}

.right .i-m .item {
	padding-bottom: 10px;
}

.right .item .label {
	width: 185px;
	text-align: right;
	color: #666666
}

.right .i-m .form .text {
	width: 140px
}

.right .i-m .form .text1 {
	width: 300px
}

.right .i-m .form .area {
	width: 308px;
	height: 65px;
	float: none
}

.right .fl {
	width: 400px
}

.right .i-m h5 {
	color: #cc0000;
	border-bottom: 1px solid #e6e6e6;
	line-height: 30px;
	padding-left: 15px;
	margin-bottom: 20px
}

.balancetop .topleft {
	float: left;
	margin-top: 10px;
}

.balancetop .topright {
	float: right;
	margin-top: 10px;
	margin-right: 0;
	text-align: right;
	/*border: red solid 1px;*/
}

.btncharge {
	background-image: url(<%=basePath%>/images/page/get_sms_code.png);
	width: 108px; *
	width: 121px;
	height: 25px;
	line-height: 25px;
	border: red solid 0px;
	text-align: center;
	cursor: pointer;
}

.topleft .ftx-01 {
	font-size: 18px;
	color: #FF0000;
	font-family: Verdana, Geneva, sans-serif
}

.tableArea {
	margin-top: 70px; *
	margin-top: -10px; *
	padding: 20px 0;
	width: 1040px;
}

table { *
	border-collapse: collapse;
	border-spacing: 0;
	table-layout: fixed;
}

caption {
	font-size: 12px;
	font-weight: 700;
	letter-spacing: 2px;
	height: 24px;
	line-height: 24px;
	text-align: left;
	color: #FC6210;
}

th,td {
	font-size: 12px;
	text-align: center;
}

th {
	font-weight: 400;
	letter-spacing: 1px;
}

.table_a {
	border-left: 1px solid #E7CA96;
	border-bottom: 1px solid #E7CA96;
}

.table_a td,.table_a th {
	border-top: 1px solid #E7CA96;
	border-right: 1px solid #E7CA96;
	padding: 4px 5px;
}

.table_a th,.table_c th {
	background: #FBF7EE;
}

.table_a strong.dd {
	color: #DA2B28;
}

.table_a strong.ww {
	color: #005aa0;
}

td.table_wrap {
	padding: 0;
}

td.table_wrap td,.table_wrap th,.table_wrap td {
	border-top: none;
	border-right: none;
}

td {
	word-break: break-all;
	word-wrap: break-word;
}

#ordercenter-data-title {
	width: 1032px; *
	width: 1040px;
	height: 35px;
	line-height: 35px;
	border-bottom: #66CD00 2px solid;
	float: left;
	text-align: left;
	padding-left: 8px;
	color: #66CD00;
}

/*分页控件*/
.pagin a,.pagin span {
	height: 20px;
	padding: 3px 10px;
	border: 1px solid #ccc;
	margin-left: 2px;
	font-family: arial;
	line-height: 20px;
	font-size: 14px;
	*vertical-align: middle;
	overflow: hidden;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}

.pagin .text,.pagin .current {
	border: none;
	padding: 4px 11px;
}

.pagin a:link,.pagin a:visited {
	color: #005aa0;
	text-decoration:none;
}

.pagin a:hover,.pagin a:active {
	background: #005aa0;
	color: #fff;
	text-decoration: none;
}

.pagin .current,.pagin .current:link,.pagin .current:visited {
	color: #f60;
	font-weight: bold;
}

.pagin b {
	dispaly: block;
	position: absolute;
	top: 9px;
	width: 5px;
	height: 9px;
	background-image: url(<%=basePath%>/images/page/pagin.png);
	background-repeat: no-repeat;
	overflow: hidden;
}

.pagin .prev,.pagin .next,.pagin .prev-disabled,.pagin .next-disabled {
	position: relative;
	padding-top: 5px;
	height: 18px;
	line-height: 18px;
}

.pagin .prev-disabled,.pagin .next-disabled {
	color: #ccc;
	cursor: default;
}

.pagin .prev,.pagin .prev-disabled {
	padding-left: 12px;
}

.pagin .prev b {
	left: 3px;
	background-position: -6px 0px;
}

.pagin .prev-disabled b {
	left: 3px;
	background-position: -18px 0px;
}

.pagin .next,.pagin .next-disabled {
	padding-right: 12px;
}

.pagin .next b {
	right: 3px;
	background-position: 0px 0px;
}

.pagin .next-disabled b {
	right: 3px;
	background-position: -12px 0px;
}

.pagin-m a,.pagin-m span {
	height: 14px;
	line-height: 14px;
	font-size: 12px;
}

.pagin-m b {
	top: 5px;
}

.pagin-m .prev,.pagin-m .next,.pagin-m .prev-disabled,.pagin-m .next-disabled
	{
	padding-top: 3px;
	height: 14px;
	*height: 22px;
	line-height: 14px;
	*line-height: 16px;
}
.pagin a, .pagin span {
	height: 14px;
	*height: 22px;
}

</style>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/pub/common/pagin.js"></script>
		<script type="text/javascript">
</script>
	</head>
	<body style="background-color: #FFFFFF">

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
					<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;我的积分</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='right'>
					<!-- div class="o-mt">
						<strong>我的积分</strong>
					</div>  -->
					<div id="ordercenter-data-title"
						style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							我的积分
						</h3>
					</div>
					<div class="balancetop">
						<div class="topleft">
							您当前的积分：
							<strong class="ftx-01"><label id="lblBalance">
									<%=request.getAttribute("vantegeBalance")%>
								</label>&nbsp;&nbsp;</strong>
						</div>
					</div>
					<div class="tableArea">
						<table id="vangetesLogTable" border="0" width="100%"
							cellspacing="0" cellpadding="0" class="table_a">

						</table>
					</div>
					<div id="pagin_div" style='text-align: right;width: 100%; padding-top: 10px;*padding-top: 0px;' class="pagin pagin-m fr"></div>
				</div>
				<div id="serviceFloors">
					<jsp:include page="/pub/main/service.jsp" flush="true" />
				</div>
				<div id="copyRight">
					<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
				</div>
			</div>
	</body>
	<script type="text/javascript">
			var loadPage = function (pageIndex){ 
			 		var table = $('#vangetesLogTable');
					table.html('<tr class="thead-tbl-grade"><th>序号</th><th>交易类型</th><th>收入积分</th><th>支出积分</th><th>相关数据</th><th>发生时间</th></tr>');
			 		$.post(mainWeb+'/front/getVantegeLogs',{pageIndex:pageIndex},function(result){
			 		result = eval('('+result+')');
			 		if(!result.returnObj){
			 			return ;
			 		}
			 			for(var i=0;i<10;i++){
				 		if(result.returnObj&&i<result.returnObj.length){
				 			var form=result.returnObj[i]; 
				 			var tr = '<tr height="27px;">'
				 			tr = tr + '<td>'+(i+1)+'</td>';
				 			tr = tr + '<td>'+form.vtype+'</td>';
				 			tr = tr + '<td>'+form.sumVantages+'</td>';
				 			tr = tr + '<td>'+form.subVantages+'</td>';
				 			tr = tr + '<td>'+form.relabillsno+'</td>';
				 			tr = tr + '<td>'+form.occurdate+'</td>';
				 			tr = tr + '</tr>'; 
				 			table.append(tr);
			 			}else{
			 				var tr = '<tr height="25px;">'
				 			tr = tr + '<td>&nbsp;</td>'; 
				 			tr = tr + '<td>&nbsp;</td>'; 
				 			tr = tr + '<td>&nbsp;</td>'; 
				 			tr = tr + '<td>&nbsp;</td>'; 
				 			tr = tr + '<td>&nbsp;</td>'; 
				 			tr = tr + '<td>&nbsp;</td>'; 
				 			tr = tr + '</tr>'; 
				 			table.append(tr);
			 			}
			 		}
			 	});
			}
			$(function(){
				var parameters = {
						count: <%=request.getAttribute("listSize")%>,
						pageSize: 10,
						parentId: 'pagin_div',
						actionListener: function(pageIndex) {
							loadPage(pageIndex);
						}
				};
				new CmsPaging(parameters);
			});	 
	</script>
</html>