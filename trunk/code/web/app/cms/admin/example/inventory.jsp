<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修正库存流水</title>
	<script type="text/javascript" src="<%=mainWeb %>/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="<%=mainWeb %>/js/json/json2.js"></script>
</head>
<body>
  <input type="button" value="1、修正材料库存流水"  onclick="doTestJson('<%=mainWeb %>/admin/inventoryService/modifyMaterialSerialData')">
  <input type="button" value="2、修正材料库存台帐" onclick="doTestJson('<%=mainWeb %>/admin/inventoryService/modifyMaterialStatisticalLedger')">
   <input type="button" value="3、修正商品库存流水"  onclick="doTestJson('<%=mainWeb %>/admin/inventoryService/modifyGoodsSerialData')">
  <input type="button" value="2、修正商品库存台帐" onclick="doTestJson('<%=mainWeb %>/admin/inventoryService/modifyGoodsStatisticalLedger')">
<script type="text/javascript">
 	var cfg = 	{
	    type: 'POST', 
	    dataType: 'json',
	    contentType:'application/json;charset=UTF-8',	    
	    success: function(result) { 
	        alert(result.msg); 
	    } 
	};
	function doTestJson(actionName){ 
	    cfg.url = actionName; 
	  	$.ajax(cfg);  
	}  
</script>
</body>
</html>