<%@page import="com.spark.cms.action.common.CategorySummary"%>
<%@page import="com.spark.cms.action.common.SearchResultInfo"%>
<%@page import="com.spark.cms.base.constant.SearchConstant"%>
<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<% 
SearchResultInfo initData = (SearchResultInfo)request.getAttribute(SearchConstant.INITRESULT);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
<style type="text/css">
#my7 .mc {
	border: solid #E6E6E6;
	border-width: 0 1px 1px;
	overflow: hidden;
}

#my7 dl dt {
	position: relative;
	margin-bottom: -1px;
	height: 27px;
	border: solid #E6E6E6;
	border-width: 1px 0;
	background: url('<%=basePath%>/images/page/bg_jdleft.jpg') #E6E6E6 repeat-x 0 -30px;
	font-weight: bold;
	line-height: 27px;
	cursor: pointer;
	text-align: left;
	padding-left: 5px;
}

#my7 dl dt:hover {
	background-position: 0 0;
}

#my7 dd {
	padding: 4px 0 5px;
}

#my7 dd .item {
	height: 30px;
	line-height: 30px;
	text-align: left;
	padding-left: 5px;
	border-top: 1px dotted #CACACA;
	margin-left: 5px;
	margin-right: 5px;
	
}

#my7 dd .item a{
	padding-left: 2px;
	font-family: "ËÎÌå";
}

#my7 dd .item a:link,a:visited {
	color: #333;
	text-decoration: none;
}

#my7 dd .item a:hover {
	color: #C00;
	text-decoration: underline;
}

#my7 dd .item a:active {
	color: #900;
}
#my7 dd .pre {
	border-top: none;
}
span.collapse{
	width: 18px;
	height: 18px;
	margin-top: 5px;
	background-image: url(<%=basePath%>/images/page/collapse.png);
}
span.expand{
	width: 18px;
	height: 18px;
	margin-top: 5px;
	background-image: url(<%=basePath%>/images/page/expand.png);
}
</style>
</head>
<body style="background-color: #FFFFFF">
	<div id="my7" class="m">
		<div class="mc" id='leftcategorysummery'>
			<dl>
				<dt id='totalTile' style='cursor: auto'>
					<a href='javascript:void(0)' style='color:#000000;text-decoration: none;cursor: default'>ËùÓÐÀàÄ¿</a>
				</dt>
			</dl>
			<%
				for (CategorySummary summary : initData.getCategorySummarys()) {
					String level2summary = summary.getCategory().getCategoryname() + "(" + summary.getGoodsCount() + ")";
			%>
			<dl>
				<dt>
					<span class='collapse'></span><%=level2summary %>
				</dt>
				<dd style='display:none;'>
			<%
					for (CategorySummary subSummary : summary.getChildrenList()) {
						String level3summary = subSummary.getCategory().getCategoryname() + "(" + subSummary.getGoodsCount() + ")";
			%>
				<div id="_MYJD_personal" class="item pre">
					&nbsp;&gt;&nbsp;<a href="javascript:void(0)" onclick="_categoryLeftApp.nodeSelection('<%=subSummary.getCategory().getRecid() %>')"><%=level3summary %></a>
				</div>
			<%
					}
			%>
				</dd>
			</dl>
			<%
				}
			%>
			<!-- 
			<dl>
				<dt>
					ÕÊ»§ÖÐÐÄ
				</dt>
				<dd>
					<div id="_MYJD_personal" class="item pre">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/accountinfo.jsp">ÕÊ»§ÐÅÏ¢</a>
					</div>
					<div id="_MYJD_safe" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/accountsafe.jsp" >ÕÊ»§°²È«</a>
					</div>
					<div id="_MYJD_balance" class="item">
						&nbsp;&gt;&nbsp;<a  href="<%=basePath%>/front/getBalance">ÕÊ»§Óà¶î</a>
					</div>
					<div id="_MYJD_score" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/front/toVantegePage">ÎÒµÄ»ý·Ö</a>
					</div>
					<div id="_MYJD_easybuy" class="item">
						&nbsp;&gt;&nbsp;<a href="<%=basePath%>/pro/member/deliveraddress.jsp" >ÊÕ»õµØÖ·</a>
					</div>
				</dd>
			</dl>
			 -->
		</div>
	</div>
</body>
<script type="text/javascript">
var _categoryLeftApp = null;
function CategoryLeftApp() {
	var categoryId = null;
	var $summaryCmp = $("#leftcategorysummery");
	
	this.nodeSelection = function(id) {
		categoryId = id;
		var $titleLink = $("dt[id='totalTile'] a");
		$titleLink.html("²é¿´ËùÓÐÀàÄ¿");
		$titleLink.css("cursor", "pointer");
		$titleLink.unbind('mouseover');
		$titleLink.mouseover(function() {
			$(this).css("text-decoration", "underline");
			$(this).css("color", "#C00");
		}).mouseout(function() {
			$(this).css("text-decoration", "none");
			$(this).css("color", "#000000");
		});
		//triggerGoodsListLoad();
		$summaryCmp.trigger('selection');
	};
	this.addLeftCategorySelectionListener = function(callBackFun) {
		$summaryCmp.unbind('selection');
		$summaryCmp.bind('selection', function() {
			callBackFun(categoryId);
		});
	};
	var addCategoryAction = function() {
		$(".mc dt").each(function() {
			var $this = $(this);
			if ($this.attr('id') == "totalTile") {
				$this.click(function() {
					if (categoryId != null) {
						categoryId = null;
						$this.find("a").html("ËùÓÐÀàÄ¿");
						$this.find("a").unbind('mouseover');
						$this.find("a").css("cursor", "default");
						/*
						$this.find("a").hover(function() {
							$(this).css("text-decoration", "none");
							$(this).css("color", "#000000");
						});*/
						$summaryCmp.trigger('selection');
						//triggerGoodsListLoad();
					}
				});
			} else {
				$this.bind('click', function() {
					$this.siblings().each(function() {
						if ($(this).is(":hidden")) {
							$(this).show();
							$this.find("span").removeClass("collapse");
							$this.find("span").addClass("expand");
						} else {
							$(this).hide();
							$this.find("span").removeClass("expand");
							$this.find("span").addClass("collapse");
						}
					});
				});
			}
		});
	};
	
	var init = function() {
		addCategoryAction();
	};
	init();
}
$(document).ready(function() {
	_categoryLeftApp = new CategoryLeftApp();
});
</script>
</html>