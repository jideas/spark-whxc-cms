<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<%
	com.spark.front.manager.PageManager pageManager = com.spark.front.manager.JSPServericeProvider.getPageManager(request);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告 - 右边</title>
<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
<style type="text/css">
/*整体布局*/
#ad-right{
	position:relative;
	width:230px;
	height:495px;
	margin:0px;
	padding:0px;
}
/*图片*/
#ad-right div a img{
border:0px;
}
/*秒杀*/
#ad-right .miaosha{
	margin:5px 0px 5px 0px;
}
/*栏目*/
#ad-right .channel{
	background:url(<%=basePath%>/images/page/shangcheng-bg.png) repeat-x;
	float: right;
	width:230px;
}
/*栏目 -> 标题样式*/
#ad-right .channel  ol{
	padding-left:15px;
	height:33px;
	margin-bottom:0px;
	background:url(<%=basePath%>/images/page/shangcheng-left.png) left top no-repeat;
}
/*栏目 -> 标题样式 -> span*/
#ad-right .channel  span{
	float:right;
	width:14px;
	height:33px;
	background:url(<%=basePath%>/images/page/shangcheng-right.png) no-repeat right top;
}
/*栏目 -> 标题样式 -> 标题子项*/
#ad-right .channel ol li{
	float:left;
	line-height:32px;
	margin:1px -1px 0px 0px;
	width:80px;
	text-align:center;
	color:#414241;
	font-size:13px;
	font-weight:bold;
	list-style:none;
	border-left:#CCCCCC solid 1px;
	border-right:#CCCCCC solid 1px;	
}
/*栏目内容*/
.channel div{
	margin:0px;
	padding-top:15px;
	border:#CCCCCC solid 1px;
	border-top:none;
	height:260px;
	*height:270px;
	*margin-bottom:-5px;
	position: relative;
}	
.channel ul{
	display:none;
	margin-top:-10px;
	*margin-top:-5px;
}
/*栏目内容 -> 栏目子项*/
.channel ul li{
	color:#dc3400;
	margin:3px 0px;
	clear:both;
	width:190px;
	list-style-image:url("<%=basePath%>/images/page/list-style.png");
	list-style-position:outside;
	text-align:left;
	margin: 6px 0px 0px 20px;
	*margin: 6px 0px 5px 5px;
	height:20px;
}
.channel a{
	display:block;
	height:20px;
	line-height:20px;
	*height:16px;
	*line-height:16px;
	color:#000000;
	font-size:12px;
	text-decoration:none;
	blr:expression(this.onFocus=this.blur());
	outline: none;
}
.channel a,.channel a:link{
	color:#000000;
}
.channel a:visited{
	color:#dc3400;
}
.channel a:hover{
	color:#dc3400;
}
.channel ol li{
	cursor:pointer;
}
.background_color{
	background-color:#FFFFFF;
}
</style>
</head>

<body>
<div id="ad-right">
	<div id="chunzhi">
		<%=pageManager.getPictureAd("64CE0511837C940A3F5962EF1C05E208", basePath) %>
	</div>
	<div class="miaosha" id="miaosha">
		<%=pageManager.getPictureAd("B9DA1373C1A5FEFEC6998E5E424A8B7D", basePath) %>
	</div>
	<div class="channel">
		<span></span>
		<ol>
			<li onclick="toNewsListPage('9B3689226C6C30E5EB5E42F824F15B2F')">商城资讯</li>
			<li onclick="toNewsListPage('1C9EEDA0B6FD28490AB85B72111C5A1E')">促销活动</li>
		</ol>
		<div>
			<ul class="shangcheng">
				<%=pageManager.getNewsList("9B3689226C6C30E5EB5E42F824F15B2F", "0", "10",basePath) %>
			</ul>
			<ul class="chuxiao">
				<%=pageManager.getNewsList("1C9EEDA0B6FD28490AB85B72111C5A1E", "0", "10",basePath) %>			
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	//新闻 -> 点击栏目标题转向新闻列表
	function toNewsListPage(channelId){
		window.location.href = mainWeb + "/front/channel/getNewsListToNews/"+channelId;
	}

	$(function(){
		$(".channel ol li:first").bind("mouseover",function(){
			$(".channel ol li").removeClass("background_color").css("color","#000000");
			$(".channel ul").css("display","none");
			$(this).addClass("background_color").css("color","#074a02");
			$(".channel ul.shangcheng").css("display","block");
		});
		$(".channel ol li:last").bind("mouseover",function(){
			$(".channel ol li").removeClass("background_color").css("color","#000000");
			$(".channel ul").css("display","none");
			$(this).addClass("background_color").css("color","#074a02");
			$(".channel ul.chuxiao").css("display","block");
		});
		$(".channel ol li:first").mouseover();		
	});
	/*
	$(function(){
		function getNewsList(e,channelid,page,size){
			$.ajax({
	 			url: mainWeb + '/front/channel/getNewsInfo',
	 	 		type: 'POST',
	 	 		dataType: 'json',
	 	 		data:{"id":channelid,"page":page,"rows":size},
	 			async: true,
	 			success: function(data) {
	 				if (data) {
	 				 $(e).html(data[0].toString());
	 				} 
	 			}
	 		});
		}
		getNewsList(".channel .shangcheng","9B3689226C6C30E5EB5E42F824F15B2F","0","15");
		getNewsList(".channel .chuxiao","1C9EEDA0B6FD28490AB85B72111C5A1E","0","15");
		
	});
	
	
	$(function(){
		function getNewsList(e,channelid){
			$.ajax({
	 			url: mainWeb + '/front/channel/getPictureAd',
	 	 		type: 'POST',
	 	 		dataType: 'json',
	 	 		data:{"id":channelid},
	 			async: true,
	 			success: function(data) {
	 				if (data) {
	 				  $(e).html(data[0].toString());
	 				} 
	 			}
	 		});
		}
		getNewsList("#ad-right #chunzhi","64CE0511837C940A3F5962EF1C05E208");
		getNewsList("#ad-right #miaosha","B9DA1373C1A5FEFEC6998E5E424A8B7D");
		
	});
	*/
</script>
</body>
</html>
