<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="<%=mainWeb %>/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="<%=mainWeb %>/js/json/json2.js"></script>
  </head>
  <body>
  <input type="button" value="getRequestAndResponseEntityJsonConvert"  onclick="doTestJson('<%=mainWeb %>/admin/getRequestAndResponseEntityJsonConvert')">
  <input type="button" value="getRequestAndReponseBodyAnnJsonConvert" onclick="doTestJson('<%=mainWeb %>/admin/getRequestAndReponseBodyAnnJsonConvert1')">
<script type="text/javascript">
 	var cfg = 	{
	    type: 'POST', 
	    data: JSON.stringify({name:'winzip',pwd:'password',mobile:'13818881888'}), 
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
