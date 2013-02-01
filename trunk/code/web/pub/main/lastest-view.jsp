<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
#categoryPagecontantLeft2 {
	width: 218px;
	border: #D9D9D9 solid 1px;
}

#categoryPagecontantLeft2 .divTitleRow {
	width: 218px;
	font-size: 16px;
	height: 31px;
	line-height: 31px;
	text-align: left;
	background-image: url('<%=basePath%>/images/page/page_top1.png');
	vertical-align: middle;
}

#categoryPagecontantLeft2 .divTitleRow font {
	font-size: 12px;
	height: 31px;
	padding-left: 10px;
}

#categoryPagecontantLeft2 .goodsInfoDiv {
	text-align: center;
	display: block;	
	height:200px;
	margin-top:20px;
}

#categoryPagecontantLeft2 .p-price {
	line-height: 22px;
	font-size: 12px;
	color: #CE0303;
}

#categoryPagecontantLeft2 .p-name {
	line-height: 22px;
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}

#categoryPagecontantLeft2 a:hover {
	color: #FF0000;
	text-decoration: underline;
}
</style>
<script type="text/javascript" src='<%=basePath %>/scripts/cookieHelper.js'></script>
		<script type="text/javascript">
$(function(){
		var value1 = GetCookie("lastest-view1-id");
		var value2 = GetCookie("lastest-view2-id");
		var value3 = GetCookie("lastest-view3-id");
		var value4 = GetCookie("lastest-view4-id"); 
		var value5 = GetCookie("lastest-view5-id"); 
		var arrayObj = [value1,value2,value3,value4,value5];
		var goodsId = '<%=request.getAttribute(com.spark.front.action.goods.GoodsKey.GoodsId.toString()) %>';
		$.post(mainWeb+'/front/getLastestGoodsViews',{
			ids:arrayObj,
			thisId:goodsId
		},function(result){
			if(result.returnObj){
				var arr = result.returnObj;
				for(var i=0;i<3&&i<arr.length;i++){
					$('#smallGoodsLastestShowDiv'+(i+1)).html(arr[i]);
				}
			}		
		},'json');
});
</script>
	</head>
	<body>
		<div id="categoryPagecontantLeft2">
			<div class="divTitleRow">
				<font>&nbsp;&nbsp;<b>最近浏览商品</b> </font>
			</div>
			<div class="goodsInfoDiv" id="smallGoodsLastestShowDiv1">
				
			</div>
			<div class="goodsInfoDiv" id="smallGoodsLastestShowDiv2">
				 
			</div>
			<div class="goodsInfoDiv" id="smallGoodsLastestShowDiv3">
				 
			</div>
		</div>
	</body>
</html>