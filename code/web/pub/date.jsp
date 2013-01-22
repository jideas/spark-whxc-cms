<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" type="text/javascript" src="<%=basePath %>/scripts/datePicker/WdatePicker.js"></script>
</head>
<body>
	<input id="date"  class="Wdate" onFocus="WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,isShowWeek:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'%y-%M-{%d+3}'})"/>
</body>
</html>