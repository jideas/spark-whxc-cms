<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>һԪר��</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico"
			type="image/x-icon" />
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
	height: 200px;
	margin: 5px 0px;
	text-align: left;
	z-index: 0;
	position: relative;
}

/*��������*/
#product_content {
	position: relative;
	width: 1200px;
	/*height: 1734px;*/
	margin-top: 7px;
	/*border: solid red 1px;*/
	z-index: 0;
}

/*������Ʒ��*/
#product_promation {
	position: relative;
	float: left;
	width: 218px;
	/*height: 1600px;*/
	text-align: left;
	z-index: 0;
}

/*ͬ������*/
#product_common-popular {
	position: relative;
	width: 218px;
	/*height: 935px;*/
	margin-bottom: 5px;
}

/*������*/
#product_lastest-view {
	position: relative;
	width: 218px;
	height:790px;
	*height: 793px; 
	z-index: 0;
}

/*����չʾ*/
#product_category-show {
	position: relative;
	float: right;
	width: 975px;
	/*height: 1732px;*/
	z-index: 0;
}

/*��¥���*/
#product_ad-floor {
	width: 1200px;
	height: 107px;
	margin: 5px 0px; *
	margin-top: 0 10px;
	z-index: 0;
}

/*�û�ָ��/�ͷ�*/
#product_service {
	width: 1200px;
	height: 200px;
	padding-top: 10px;
	*padding-top: 0px;
	z-index: 0;
}

/*��Ȩ*/
#product_copyRight {
	float: left;
	width: 1200px;
	height: 60px;
	/*margin: 5px 0px;*/
	/*border: solid 1px red;*/
	z-index: 10;
}
</style>
	</head>
	<body>
		<div id="shortcut">
			<jsp:include page="main/shortcut.jsp" flush="true" />
		</div>
		<div id="product_container">
			<div id="product_header">
				<jsp:include page="main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp?menuId=oneyuan" flush="true" />
			</div>
			<div id="product_content">
				<div id="product_promation">
					<div id="product_common-popular">
						<!-- jsp:include page="main/commonPopular2.jsp" flush="true" /> -->
						<jsp:include page="category-left.jsp" flush="true" />
					</div>
					<div id="product_lastest-view">
						<jsp:include page="main/lastest-view.jsp" flush="true" />
					</div>
				</div>
				<div id="product_category-show">
					<!-- jsp:include page="main/category-show.jsp" flush="true" /> -->
					<jsp:include page="oneyuan-goodslist.jsp" flush="true" />
				</div>
			</div>
			<div id="product_ad-floor">
				<jsp:include page="main/downstairs-ad.jsp" flush="true" />
			</div>
			<div id="product_service">
				<jsp:include page="main/service.jsp" flush="true" />
			</div>
			<div id="product_copyRight">
				<jsp:include page="main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
	<script type="text/javascript">
//����������Ч��
function product_buy_over(img){
	$(img).attr("src","<%=basePath%>/images/page/buy25-02.png");
}
function product_buy_out(img){
	$(img).attr("src","<%=basePath%>/images/page/buy25-01.png");
}
</script>
</html>