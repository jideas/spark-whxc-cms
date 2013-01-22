<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>热门搜索</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 热门搜索 -->
		<form id="hotSearchForm" method="post"
			modelAttribute="popularKeyWordsVo">
			<input type="hidden" name="recid" />
			<table>
				<tr>
					<td colspan="2">
						提示：关键词之间使用<font color="red">#</font>号隔开，如：红壳鸡蛋#五香粉蒸肉#泡藕带#蜜汁红枣#北京烤鸭
					</td>
				</tr>
				<tr>
					<td>
						关键词：
					</td>
					<td>
						<textarea rows="5" cols="55" name="popularKeyWords"></textarea>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td style="text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
							onclick="hotSearchAction.hotSearchSaveOrUpdate()">保 存</a>
					</td>
				</tr>
			</table>
		</form>
		<!-- begin of 热门搜索 -->
		<script type="text/javascript">
		//初始化
		$(function(){
			$.ajax({
			  	url: "<%=mainWeb%>/admin/channel/getPopularKeyWords",
			  	type: "post",
			  	complete: function(data, textStatus, jqXHR) {
			  		data = eval("("+data.responseText+")");
			  		$("#hotSearchForm input[name='recid']").val(data.recid);
			  		$("#hotSearchForm textarea[name='popularKeyWords']").val(data.popularKeyWords);
			  	}
			},"JSON");
		});
		
		//热门搜索
		var hotSearchAction;
		$(function(){
			//热门搜索 -> 初始化
			hotSearchAction = new HotSearchAction();
			//热门搜索 -> 定义类
			function HotSearchAction(){
				//热门搜索 -> 保存
				this.hotSearchSaveOrUpdate = function(){
					$('#hotSearchForm').form('submit',{
						url: "<%=mainWeb%>/admin/channel/modifyPopularKeyWords",
						dataType: 'json',
					    onSubmit:function(){
					    	return $.trim($("textarea[name='popularKeyWords']").val()) != "";
					    },   
					    success:function(data){
					    	$.messager.alert('提示','保存成功!','info');
					    }
					});
				}
			}
		});
	
	</script>
	</body>
</html>