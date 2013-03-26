<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.spark.front.action.goods.GoodsKey"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
/*整体布局*/
#product_detail_tab_page {
	position: relative;
	text-align: left;
	width: 950px;
}

/*TAB项*/
#product_detail_tab_page ul.productUL {
	list-style: none;
	width: 950px;
	height: 32px;
	line-height: 32px;
	border: 1px solid #CCCCCC;
	border-bottom: none;
	background: url('<%=basePath%>/images/page/page_top1.png') repeat-x top;
	background-color: RGB(10, 132, 1);
}

/*TAB子项*/
#product_detail_tab_page ul.productUL li {
	float: left;
	text-align: center;
	width: 100px;
	height: 32px;
	line-height: 32px;
	font-weight: bold;
	font-size:16px;
	cursor: pointer;
}

#product_detail_tab_page ul.productUL li a {
	margin-top: 2px;
	margin-left: 2px; *
	margin-left: 0px;
	width: 96px;
	height: 30px;
	line-height: 30px;
	color: #000000;
	display: block;
	text-decoration: none;
}

/*动态样式*/
.product_detail_tab_liOver {
	background-color: RGB(10, 132, 1);
}

.product_detail_tab_liBackgroundImg {
	background-image: url("<%=basePath%>/images/page/tab-border-1px.png");
	background-position: right center;
	background-repeat: no-repeat;
}

.product_detail_tab_aOver {
	background-color: RGB(255, 255, 255);
}

/*TAB详情*/
#product_detail_tab_page .product_detail_tab {
	position: relative;
	width: 950px;
	display: none;
}
</style>
		<script type="text/javascript">
	$(function(){
		//初始化 -> TAB绑定事件
		$("#product_detail_tab_page ul.productUL li").addClass("product_detail_tab_liBackgroundImg");
		$("#product_detail_tab_page ul.productUL li").each(function(index){
			$(this).bind("mouseover",function(){
				//移除其他TAB样式
				$("#product_detail_tab_page ul.productUL li").addClass("product_detail_tab_liBackgroundImg");
				$("#product_detail_tab_page ul.productUL li").removeClass("product_detail_tab_liOver");
				$($("#product_detail_tab_page ul.productUL li").children("a")).removeClass("product_detail_tab_aOver");
				//添加当前TAB样式
				$(this).removeClass("product_detail_tab_liBackgroundImg");
				$(this).addClass("product_detail_tab_liOver");		
				$($(this).children("a")).addClass("product_detail_tab_aOver");
				//隐藏其他说明
				$("#product_detail_tab_page .product_detail_tab").hide();
				$("#product_detail_tab_page .product_detail_tab_"+index).show();
			});
		});
		//初始化 -> 默认显示第一个
		$("#product_detail_tab_page ul.productUL li:eq(0)").mouseover();		
		//根据li的个数重新设置宽度
		if($("#product_detail_tab_page ul.productUL li").length > 9){
			$("#product_detail_tab_page ul.productUL li").css("width",Math.floor(950 / $("#product_detail_tab_page ul.productUL li").length));
		}
	});
</script>
	</head>
	<body>
		<div id="product_detail_tab_page">
			<ul class="productUL"> 
				<%
					java.util.List<String> titles = (java.util.List<String>) request.getAttribute(GoodsKey.GoodsContentTitles
							.toString()); 
					if (titles != null) {

						for (String title : titles) {
				%>
				<li>
					<a href="javascript:void(0);"><%=title%></a>
				</li>
				<%
					}
					}
				%>
			</ul> 
			<%
				java.util.List<String> htmls = (java.util.List<String>) request.getAttribute(GoodsKey.GoodsContentHtmls
						.toString()); 
				for (int index = 0; null != htmls && index < htmls.size(); index++) {
			%>
			<div class="product_detail_tab product_detail_tab_<%=(index)%>" style="padding-top: 10px;padding-left:10px;"><%=htmls.get(index)%></div>
			<%
				}
			%>
		</div>
	</body>
</html>