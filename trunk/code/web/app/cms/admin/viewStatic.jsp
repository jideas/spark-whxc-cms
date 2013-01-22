<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>首页预览</title>
		<script type="text/javascript"
			src="<%=mainWeb%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
		var staticHomeAction;
		$(function() {
			function StaticHomeAction() {
				this.staticHomePage = function() { 
					 $('#staticHomeForm').submit();
				} 
				this.closeThisPage = function(){
					window.close();
				}
			}
			staticHomeAction = new StaticHomeAction();
		});
		</script>
		<style type="text/css">
.btncharge {
	background-image: url(<%=mainWeb%>/images/page/get_sms_code.png);
	width: 121px;
	height: 25px;
	line-height: 25px;
	border: red solid 0px;
	text-align: center;
	cursor: pointer;
	float: right;
	margin-right:10px;
	margin-top:3px;
}
</style>
	</head>
	<body style="background-color: #FFFFFF">
		<div style="height: 30px;text-align: right;vertical-align: middle;">
			<form action="<%=mainWeb%>/pub/index.jsp" id="staticHomeForm"
				method="post">
				<input type="hidden" name="openType" id="openTypeHidden"
					value="managerStatic" />
				<span id="toBankChargePageSpan" class="btn btncharge"
					onclick="staticHomeAction.closeThisPage();"
					onselectstart="return false">&nbsp;&nbsp;取消发布&nbsp;&nbsp;</span>
				<span id="toCardChargePageSpan" class="btn btncharge"
					onclick="staticHomeAction.staticHomePage()"
					onselectstart="return false">&nbsp;&nbsp;确定发布&nbsp;&nbsp;</span>
			</form>
		</div>
		<div>
			<jsp:include page="/pub/index.jsp"></jsp:include>
		</div>
	</body>
</html>
