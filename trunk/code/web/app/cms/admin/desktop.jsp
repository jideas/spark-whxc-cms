<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>7�������</title>
		<link rel="shortcut icon" href="<%=mainWeb%>/images/page/icon-7.ico"
			type="image/x-icon" />
		<%@ include file="/common/jqueryui.jsp"%>
<style type="text/css">
/*��������ʽ*/
a.operateChannel,
a.operateChannel:link{
	color: #00F;
	text-decoration: none;
}
a.operateChannel:hover{
	color: #F00;
	text-decoration: underline;
}
</style>	
	</head>
	<body style="visibility: hidden;" class="easyui-layout" id="pageContent">
		<!-- logo�� -->
		<div data-options="region:'north',border:false,noheader:true"
			style="height: 66px;">
			<%@ include file="/app/cms/admin/desktop/logo.jsp"%>
		</div>
		<!-- �˵������� -->
		<div data-options="region:'west',title:'�˵�����',split:true"
			style="width: 200px;" id="desktopNavigation">
			<%@ include file="/app/cms/admin/desktop/navigation.jsp"%>
		</div>
		<!-- ������ -->
		<div data-options="region:'center',border:false">
			<div id="contentRegion" class="easyui-tabs"
				data-options="fit:true,border:true">
			</div>
		</div>
		<!-- ��Ȩ�� -->
		<div data-options="region:'south',border:false,noheader:true"
			style="height: 26px;">
			<%@ include file="/app/cms/admin/desktop/copyRight.jsp"%>			
		</div>
		<script type="text/javascript">
    	//����������ʱ������ʽ����
		$(function(){			
			var timer = setTimeout(function() {
				$("body").css("visibility","visible");
				clearTimeout(timer);
			}, 50);
		});
    </script>
	</body>
</html>
