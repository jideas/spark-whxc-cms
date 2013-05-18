<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>��������</title>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link type="text/css" href="<%=basePath%>/css/commonDirection.css" rel="stylesheet">
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
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top left;
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

/*������Ʒ��*/
#product_promation {
	position: relative;
	float: left;
	width: 220px;
	height: 1055px;
	text-align: left;
	z-index: 0;
}

/*����չʾ*/
#product_category-show {
	position: relative;
	float: right;
	width: 970px;
	text-align: left;
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

/*��������*/
#product_hot-sale b {
	font-size: 16px;
}

/*��������*/
#product_category-show .direction_title {
	height: 26px;
	line-height: 26px;
	padding-left: 5px;
	color: #333333;
	font-size: 16px;
	font-weight: bold;
	border:1px #d7d8d7 solid;
	border-bottom:2px #259c1c solid;  
	background: url("<%=basePath%>/images/page/direction_bg.png") repeat-x top left;
}
</style>
	<script type="text/javascript">
	var code= window.document.location.href;
	var start = code.indexOf("=")+1;
	code = code.substr(start);
	var titles = new Array();
	titles[0] = ['��������','�µ�ʱ��','�����޸���ȡ��','�û���֪','�û�Э��'];
	titles[1] = ['��ԱȨ��','VIP��Ա','��Ա����','����ѯ'];
	titles[2] = ['֧����','��������','��ֵ��','���ϳ�ֵ','����ȯ'];
	titles[3] = ['���ͷ�Χ','����ʱ��','���ͷ�ʽ','ȡ��ʱ��','��������'];
	titles[4] = ['���ﱣ��','�˻���ԭ��','�˻�������'];
	titles[5] = ['��˾����','��ҵ��ֵ','��ҵ����','����ģʽ','���ؽ���','���ʺ�����']; 
	
	function loadDirction(code){
		//���ü���
		var num1 = code.substr(0,1);
		var num2 = code.substr(1);
		$("#product_hot-sale .direction_tip").html(titles[num1][num2]);
		//���ñ���
		$("#product_category-show .direction_title").html(titles[num1][num2]);
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
		loadDirction(code);
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
				<b>��������</b><span>&nbsp;&nbsp;&gt;&nbsp;&nbsp;</span><span
					class="direction_tip">��������</span>
			</div>
			<div id="product_content">
				<div id="product_promation">
					<jsp:include page="/pub/direction/directionNav.jsp" flush="true" />
				</div>
				<div id="product_category-show">
					<div class="direction_title">
						��������
					</div>
					<div class="direction_content">						
					</div>
				</div>
				<div style="width:1200px;clear: both;height:10px;line-height:10px;"></div>
			</div>
			<!--div id="product_ad-floor">
				<jsp:include page="/pub/main/downstairs-ad.jsp" flush="true" />
			</div-->
			<div id="product_service">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="product_copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
</html>