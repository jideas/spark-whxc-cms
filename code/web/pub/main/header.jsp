<%@page import="com.spark.cms.services.vo.PopularKeyWordsVo"%>
<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<%
com.spark.cms.services.channel.ChannelService channelService = com.spark.front.manager.JSPServericeProvider.getChannelService(request);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link type="text/css" href="<%=basePath %>/css/logRegion.css" rel="stylesheet"/>
</head>
<body>
	<div class="header">
		<div class="logo">
			<a href="<%=basePath%>/"><img src="<%=basePath%>/images/page/logo.png" alt="返回7号生活馆首页" /></a>
		</div>
		<div class="logo1">
			<img src="<%=basePath%>/images/page/index-top-pic.png" />
		</div>
		<div class="search">
			<div class="phone">
				<div class="i-phone">
					<img src="<%=basePath%>/images/page/phone.png" alt="icon-phone" />
				</div>
				<div class="phone-num">服务电话：4001-027-577</div>
			</div>
			<div class="i-search">
				<form action='<%=basePath%>/front/search' id='searchform' method='get' accept-charset="utf-8"  onsubmit="document.charset='utf-8'">
					<ul>
						<li class="li_input"><input name='searchKey' type="text" id='searchKey_main'/></li>
						<li class="li_img"><img id='submitButton' src="<%=basePath%>/images/page/logo-search-right.gif" style="cursor: pointer;"/>					
					</ul>
				</form>
			</div>
			<div class="search-key">
				热门搜索：
				<% 
					String hotWordsHtml = "";
					PopularKeyWordsVo vo = channelService.getPopularKeyWordsVo();
					if (null != vo && null != vo.getPopularKeyWords()) {
						String[] words = vo.getPopularKeyWords().split("#");
						for (String word : words) {
							hotWordsHtml += "<a href='#'>" + word + "</a>";
						}
					}
				%>
				<span class="sk-red" id='hotSearchKey'><%=hotWordsHtml %></span> 
			</div>
		</div>
		<!-- 
		<div class="xinchenlog">
			<img src="<%=basePath%>/images/page/xinchen-link.png" />
		</div>
		 -->
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('#submitButton').unbind('click');
	$('#submitButton').click(function() {
		$('#searchform').submit();
	});
	
	$('#hotSearchKey a').each(function() {
		var hotKeyLink = $(this);
		hotKeyLink.unbind('click');
		hotKeyLink.click(function() {
			$('#searchKey_main').val(hotKeyLink.html());
			$('#searchform').submit();
		});
	});
});

</script>
</html>