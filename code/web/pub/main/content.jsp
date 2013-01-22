<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>7号生活馆</title>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
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
#product_content {
	position: relative;
	width: 1200px;
	margin-top: 7px; *
	margin-top: 5px;
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
##product_hot-sale{
	height: 30px;
	line-height: 30px;
	border-bottom: 3px solid #CCC;
}
#product_hot-sale span{
	margin-left: 5px;
}
/*内容 -> 标题*/
#product_content .contentTitle{
	height: 35px;
	line-height: 35px;
	color: #777777;
	font-size:20px;
}
/*内容 -> 作者*/
#product_content .contentAuthor{
	width:1160px;
	*width:1180px;
	margin-left:10px;
	*margin-left:0px;
	padding:0px 10px;
	height:30px;
	line-height:30px;
	color:#333333;	
	background-color: #EEEEEE;
	border: 1px dotted #999999;
}
#product_content .contentAuthor .author{
	float: left;
}
#product_content .contentAuthor .date{
	float:right;
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
</style>
		<script type="text/javascript">
	var code= window.document.location.href;
	var start = code.indexOf("=")+1;
	code = code.substr(start);
	
	function loadContent(code){
		//设置级别
		$("#product_hot-sale .content_tip").html("提示");
		//设置标题
		$("#product_content .contentTitle").html("标题");
		$("#product_content .author").html("作者："+"新辰食品");
		$("#product_content .date").html("发布时间："+"2013-01-04");
		$("#product_content .contentText").html("正文正文");
		//设置内容
		$.ajax({ 
			url: "<%=basePath%>/pub/direction/"+code+".jsp",
			type: 'post', 
			datatype: 'json',
			success: function(content){ 
				$("#product_category-show .direction_content").html(content);
			} 
		});		
	}
		
	$(function(){
		loadContent(code);
	});
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
				您的位置：首页<span>&gt;</span><span class="content_tip">商城资讯</span><span>&gt;</span><span>正文</span>
			</div>
			<div id="product_content">
				<div class="contentTitle">新辰公司新辰公司新辰公司新辰公司新辰公司</div>
				<div class="contentAuthor">
					<div class="author">作者：柒号生活馆</div>
					<div class="date">发布时间：2013-01-04</div>
				</div>
				<div class="contentText">
					正文
				</div>
			</div>
			<div id="product_ad-floor">
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