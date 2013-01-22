<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>在线充值</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/pro/member/scripts/checkCode.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/onlineCharge.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<script type="text/javascript">
		$(function() {
			var url = mainWeb + "/front/getBalanceAndVangete";
			$.post(url, function(result) { 
						if (result.result) {
							var array = result.returnObj;
							$('#email_box').html(array[0]);
							$('#balance_box').html(array[1] + '元');
							$('#vanteges_box').html(array[2]);
						} else {
							if (result.returnObj == 2) {
								location = mainWeb + '/pub/sub/login.jsp';
							} else if (result.returnObj == 0) {
								cmsAlertAtt(result.message);
							}
						}
					},'json');
		});
</script>
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
			<div class="oldmain">
				<div class="breadcrumb">
					<span class="my7"><strong><a href="#">我的7号</a> </strong> </span><span>&nbsp;&gt;&nbsp;在线支付充值</span>
				</div>
				<div class='left'>
					<jsp:include page="left.jsp" flush="true" />
				</div>
				<div class='oldmaindiv'>
					<div id="ordercenter-data-title"
						style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
						<h3>
							在线支付充值
						</h3>
					</div>
					<table class="oldmaintable"> 
						<tr>
							<td class="table_wrap" width="100%">
								<jsp:include page="paying.jsp" flush="true" />
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