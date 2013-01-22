<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>7�������</title>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<style type="text/css">
/*���岼��*/
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

/*ͷ��*/
#product_header {
	position: relative;
	width: 1200px;
	height: 135px;
	z-index: 10000
}

/*����*/
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

/*��������*/
#product_content {
	position: relative;
	width: 1200px;
	margin-top: 7px; *
	margin-top: 5px;
	z-index: 0;
}

/*����չʾ*/
#product_category-show {
	position: relative;
	float: right;
	width: 950px;
	text-align: left;
	z-index: 0;
}

/*��¥���*/
#product_ad-floor {
	width: 1200px;
	height: 107px;
	margin: 5px 0px; *
	margin-top: 010px;
	z-index: 0;
}

/*�û�ָ��/�ͷ�*/
#product_service {
	width: 1200px;
	height: 200px;
	z-index: 0;
}

/*��Ȩ*/
#product_copyRight {
	width: 1200px;
	height: 60px;
	margin: 5px 0px;
	z-index: 0;
}

/*���� -> ������ʾ��*/
##product_hot-sale{
	height: 30px;
	line-height: 30px;
	border-bottom: 3px solid #CCC;
}
#product_hot-sale span{
	margin-left: 5px;
}
/*���� -> ����*/
#product_content .contentTitle{
	height: 35px;
	line-height: 35px;
	color: #777777;
	font-size:20px;
}
/*���� -> ����*/
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
/*���� -> ������*/
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
		//���ü���
		$("#product_hot-sale .content_tip").html("��ʾ");
		//���ñ���
		$("#product_content .contentTitle").html("����");
		$("#product_content .author").html("���ߣ�"+"�³�ʳƷ");
		$("#product_content .date").html("����ʱ�䣺"+"2013-01-04");
		$("#product_content .contentText").html("��������");
		//��������
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
				����λ�ã���ҳ<span>&gt;</span><span class="content_tip">�̳���Ѷ</span><span>&gt;</span><span>����</span>
			</div>
			<div id="product_content">
				<div class="contentTitle">�³���˾�³���˾�³���˾�³���˾�³���˾</div>
				<div class="contentAuthor">
					<div class="author">���ߣ���������</div>
					<div class="date">����ʱ�䣺2013-01-04</div>
				</div>
				<div class="contentText">
					����
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