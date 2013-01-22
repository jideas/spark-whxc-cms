<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/pub/common/inc.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" type="text/javascript" src="<%=basePath %>/scripts/jquery/jquery.js"></script>
</head>
<body>
	<span onclick="dosubmit()" style="width:80px;background-color: red;height: 25px;"> gd3222222222222222 </span>
 <script>
 	function dosubmit(){
 		$.ajax({
 			url: mainWeb + '/admin/goods/createGoods',
 	 		type: 'POST',
 	 		dataType: 'json',
 	 		contentType:"application/x-www-form-urlencoded;chartset=UTF-8",
 			async: false,
 			data:{dataString:"你好"},
 			success: function(data) {
 				if (data.result) {
 			 		alert("保存成功。");
 			 		win.window('close');
 			 		win.trigger('confirmCreate'); 
 				} else {
 					alert("保存失败。");
 				}
 			},
 			error: function() {
 				alert("保存失败。");
 			}
 		});
 		
 	}
 	
 </script>
	
</body>
</html>