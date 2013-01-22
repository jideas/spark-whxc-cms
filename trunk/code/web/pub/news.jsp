<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.spark.cms.services.vo.ChannelContentVo" %>	
<%@ include file="/pub/common/inc.jsp"%>
<%
	ChannelContentVo channelContent = (ChannelContentVo)request.getAttribute("news");
	String isNav = "1";
	if(channelContent == null){
		channelContent = new ChannelContentVo();
		isNav = "0";
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>7号生活馆</title>
	<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
<style type="text/css">
/*整体布局*/
body {
	margin: 0px;
	padding: 0px;
}

#shortcut {
	padding-bottom: 1px;
	line-height: 30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}

#product_container {
	position: relative;
	margin: 0px auto;
	padding: 0px;
	width: 1200px;
}

/*头部*/
#product_header {
	position: relative;
	width: 1200px;
	height: 135px;
	z-index: 10000
}

/*热卖*/
#product_hot-sale {
	width: 1200px;
	height: 35px;
	line-height: 35px;
	margin: 5px 0px;
	text-align: left;
	z-index: 0;
	position: relative;
	border-bottom: 1px solid #CCCCCC;
}

/*内容区域*/
#product_content,
#newsList_content {
	position: relative;
	width: 1200px;
	margin-top: 7px; *
	margin-top: 5px;
	text-align:left;
	z-index: 0;
}

/*分类展示*/
#product_category-show {
	position: relative;
	float: right;
	width: 950px;
	text-align: left;
	z-index: 0;
}

/*底楼广告*/
#product_ad-floor {
	width: 1200px;
	height: 107px;
	margin: 5px 0px; *
	margin-top: 010px;
	z-index: 0;
}

/*用户指南/客服*/
#product_service {
	width: 1200px;
	height: 200px;
	z-index: 0;
}

/*版权*/
#product_copyRight {
	width: 1200px;
	height: 60px;
	margin: 5px 0px;
	z-index: 0;
}

/*内容 -> 级联显示区*/
#product_hot-sale{
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #CCC;
}
#product_hot-sale a,
#product_hot-sale a:link{
	text-decoration: none;
	color: #000000;
}
#product_hot-sale a:hover{
	text-decoration: underline;
	color: #FF0000;
}
#product_hot-sale span{
	margin-left: 5px;
}
/*内容 -> 导航区*/
#product_content #news_navigation{
	width:220px; 
	height: 693px;
	float: left;
}
/*内容 -> 正文区*/
#product_content .contentText{
	width:1180px;
	margin-top:10px;
	margin-left:10px;
	*margin-left:0px;
	text-align:left;
	min-height: 200px;
    height:auto !important;
    *height:200px;
    overflow:visible;	
	border: 1px solid #CCCCCC;
}

/*新闻列表*/
#newsList_content .newsList_title{
	margin-left:10px;
	height:60px;
	line-height: 60px;
	font-family:'微软雅黑'; 
	font-size:30px; 
	color:#666666;
}

#newsList_content ul{
	margin:auto;
	padding:0;
	margin-left:35px;
	color:#666666; 
	width:900px;
	font-size:14px;
}
#newsList_content ul li{
	line-height:40px;
	font-family:"微软雅黑"; 
	border-bottom:1px dotted #CCC;
	color:#666666;
	list-style-position: inside;
}
#newsList_content ul li span{
	color: #999999;
	margin-left: 5px;
}
#newsList_content ul li a{
	text-decoration:none; 
	font-size:14px; 
	color:#666666;
}
#newsList_content ul li a:hover{ 
	color:#F00;
}
/*展示区 -> 内容/列表*/
#news_content,
#newsList_content{
	width:970px; 
	min-height: 693px;
	*height:693px; 
	float: right;
	margin-top: 0px;
	text-align: center;
	border: 1px solid RGB(220,220,220);
}
#newsList_content{
	text-align: left;
}
/*展示区 -> 内容 -> 标题*/
#news_content .title{
	height: 40px;
	line-height: 40px;
	font-size: 22px;
	color: rgb(48,48,48);
	font-weight: bold;
}
/*展示区 -> 内容 -> 日期*/
#news_content .data{
{
	height: 30px;
	line-height: 30px;
	*height: 20px;
	*line-height: 20px;
}
/*展示区 -> 内容 -> 横线*/
#news_content hr{
	width:930px;
	margin: 0px auto;
	margin-bottom:15px;
	*margin-bottom:10px;
}
/*展示区 -> 内容 -> 正文*/
#news_content .content{
	width: 930px;
	margin:0px auto;
	text-align: left;
}

</style>	
<script type="text/javascript">
//初始化 -> 判断显示的区域
var news_isNav = <%=isNav%>; 
$(function(){
	$("#newsList_content").hide();
	$("#news_content").hide();
	if(<%=isNav%> == "0"){
		$("#newsList_content").show();
		$("#news_content").hide();
	}else{
		$("#newsList_content").hide();
		$("#news_content").show();
	}
});

//加载新闻
function loadNews(newsId){
	$.post("<%=basePath%>/front/channel/getNewsLocal/"+newsId,function(data){
		$("#news_content .title").html(data.title);
		$("#news_content .date").html(data.createdate);
		$("#news_content .content").html(data.content);
		
		$("#news_content").show();
		$("#newsList_content").hide();
	},"json");
}
//加载新闻列表
function loadNewsList(channelId,_this){
	var title = $(_this)[0].innerText;
	$.post("<%=basePath%>/front/channel/getNewsListByChannelId/"+channelId,function(data){
		//填充数据
		$("#product_hot-sale .content_tip").html("<a onclick=loadNewsList('"+channelId+"',this) href='javascript:void(0)'>"+title+"</a>");
		$("#newsList_content .newsList_title").html(title);
		$("#newsList_content ul").html(data);
		//显示与隐藏
		$("#newsList_content").show();
		$("#news_content").hide();
	},"json");
}
</script>	
	</head>
	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id="product_container">
			<div id="product_header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
			</div>
			<div id="product_hot-sale">
				您的位置：<a href="<%=basePath%>" target="_blank">首页</a>
					<span>&gt;</span>
					<span class="content_tip"><a href='javascript:void(0)' onClick='loadNewsList("<%=request.getAttribute("channelId")%>",this)'><%=request.getAttribute("channelTitle")%></a></span>
					<span>&gt;</span>
					<span>正文</span>
			</div>
			<!-- begin of 展示区 -->
			<div id="product_content">
				<!-- begin of 导航区 -->
				<div id="news_navigation">
					<jsp:include page="/pub/main/newsNav.jsp" flush="true" />
				</div>
				<!-- end of 展示区 -->
				<!-- begin of 新闻内容区 -->
				<div id="news_content">
					<div class="title">
						<%=channelContent.getTitle()%>
					</div>
					<div class="date">
						<%=channelContent.getCreatedate()%>
					</div>
					<hr/>
					<div class="content" style="text-align: left;">
						<%=channelContent.getContent()%>
					</div>
				</div>
				<!-- end of 新闻内容区 -->
				<!-- begin of 新闻列表区 -->
				<div id="newsList_content">
					<div class="newsList_title"><%=request.getAttribute("channelTitle")%></div>
					<ul>
						<%=request.getAttribute("newsList") %>
					</ul>
				</div>
				<!-- end of 新闻列表区 -->
				<div style="width:1200px;clear: both;*height:0px;*line-height: 0px;"></div>
			</div>
			<!-- end of 展示区 -->
			<div id="product_ad-floor" style="*margin-top: 5px;">
				<jsp:include page="/pub/main/downstairs-ad.jsp" flush="true" />
			</div>
			<div id="product_service">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="product_copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
</html>