<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.spark.front.action.goods.GoodsKey"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
/*分类级别*/
#category-level {
	width: 1200px;
	height: 25px;
	font-size: 12px;
	padding-left: 20px;
	background: url("<%=basePath%>/images/page/hot-sale-homePag.png") left
		top no-repeat;
}

#category-level span {
	margin: 0px 3px;
	height: 18px;
	line-height: 18px;
}

/*商品详情购买*/
#product-detail_buy {
	width: 1200px;
	height: 334px; *
	height: 342px;
	padding-top: 10px;
	border-top: 1px solid RGB(143, 178, 150);
}

/*商品图片*/
#product-detail_buy .product_image {
	width: 350px;
	height: 332px; *
	height: 331px;
	border: 1px solid RGB(171, 195, 107);
	text-align: center;
	float: left;
}

#product-detail_buy .product_image img {
	vertical-align: middle;
}

/*商品购买*/
#product-detail_buy .product_buying {
	width: 840px;
	height: 330px;
	float: right;
	font-family: fantasy monospace;
}

#product-detail_buy .product_buying p {
	height: 35px;
	line-height: 35px;
	width: 100%;
	color: RGB(54, 129, 49);
	font-size: 22px;
	font-weight: bold;
	border-bottom: 1px solid #EEEEEE;
}

#product-detail_buy .product_buying .code,#product-detail_buy .product_buying .type,#product-detail_buy .product_buying .specif,#product-detail_buy .product_buying .price
	{
	height: 30px;
	line-height: 30px;
	color: #000000;
	font-size: 12px;
	font-weight: normal;
	margin-left: 25px;
}

#product-detail_buy .product_buying div.price b {
	color: RGB(230, 45, 12);
	font-size: 16px;
}

/*商品购买 -> 按钮区*/
#product-detail_buy #product_buying_button {
	position: relative;
	width: 600px;
	height: 100px;
	line-height: 100px;
	margin-top: 5px;
	margin-left: 0px;
	padding-left: 15px;
	border: 1px solid RGB(244, 201, 145);
}

#product-detail_buy #product_buying_button div.oper {
	float: left;
}

#product-detail_buy #product_buying_button div.oper div.warp_specil,#product-detail_buy #product_buying_button div.oper div.buy_number
	{
	height: 35px;
	line-height: 35px;
	width: 200px;
	margin-top: 10px;
	margin-left: 0px;
}

#product-detail_buy #product_buying_button div.oper div input {
	width: 22px;
	height: 22px;
	border: 1px solid #CCCCCC; *
	padding-top: 3px;
}

#product-detail_buy #product_buying_button div img {
	vertical-align: middle;
}

/*商品规格*/
#product-detail_buy #product_buying_button div.buying_specil {
	display: inline-table; *
	display: inline;
	height: 28px;
	line-height: 28px;
	padding: 0px;
	border: 1px solid #CCCCCC;
	margin-right: 10px;
	padding: 0px 8px;
}

.buying_specil_select {
	display: inline-table; *
	display: inline;
	height: 28px;
	line-height: 28px;
	padding: 0px;
	border: 1px solid #CCCCCC;
	margin-right: 10px;
	padding: 0px 8px;
	cursor: pointer;
}

.buying_specil_selected {
	display: inline-table; *
	display: inline;
	height: 28px;
	line-height: 28px;
	padding: 0px;
	border: 2px solid green;
	margin-right: 10px;
	padding: 0px 8px;
	cursor: pointer;
}

/*加入购物车*/
#product-detail_buy #product_buying_button .pay {
	float: right; *
	clear: both;
	width: 370px;
	height: 50px;
	margin-top: 0px; *
	margin-top: 25px;
}

.canClickSpan {
	text-decoration: none;
	cursor: pointer;
	color: BLACK;
}

#category-level a:HOVER {
	text-decoration: underline;
	color: #CD0000;
}

#specSelectDiv {
	display: inline;
	float: right;
	margin-right: 100px;
	width: 80%;
	height: 30px;
	line-height: 30px; *
	margin-top: -30px;
}
</style>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/cookieutil.js"> 
</script>
		<script type="text/javascript">
	function navigatorForward(url){
		location = url;
	}
	function changeGoodsSpec(id,spec,price,pmt,oldPrice,vtype,bookingText){
		$('#goodsSpecSpan1').html(spec);
		$('#goodsPriceSpan1').html(price);
		$('#goodsPriceSpan0').html(oldPrice);
		$('#vantagesValue').html(vtype);
		$('#pmtValue').html(pmt);
		if(bookingText==''){
			$('#bookingText').show();
		}else if('none'==bookingText){
			$('#bookingText').hide();
		}
		$('#goodsIdInput1').val(id);
		var divs = $('#specSelectDiv div');
		for(var i=0;i<divs.length;i++){
			var div = divs[i];
			div.style.border='1px solid #CCCCCC';
		}
		$('#specSelect'+id).css('border', '2px solid green');
	}
	$(function(){
		var goodsId = '<%=request.getAttribute(GoodsKey.GoodsId.toString())%>';
		if(!goodsId){
			return ;
		}
		var thisTimeId = goodsId;
		for(var i=1;i<6;i++){
			var value = GetCookie("lastest-view"+i+"-id");
			if(value==goodsId){
				return ;
			}else if(value==thisTimeId){
				SetCookie("lastest-view"+i+"-id",goodsId);
				break;
			}else{
				SetCookie("lastest-view"+i+"-id",goodsId);
				goodsId = value;
			}
		}
	});
	function Count_inputOnChange(){
		var id = "#count_input";
		var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
		if ($(id).val().match(reg) == null) {
			$(id).val($(id).attr("preValue"));
		} else {
			$(id).attr("preValue", $(id).val());
			this.changeTotal();
		}
		}
		function subCount(){
			var input = $("#count_input");
			var value = input.val(); 
			if(value<=1){
				return;
			}
			input.val(parseInt(value)-1);
			input.attr("preValue",value);
		}
		function sumCount(){
			var input =  $("#count_input");
			var value = input.val();
			input.val(parseInt(value)+1);
			input.attr("preValue",value);
		}
		
		function addToShoppingCar(){
			var sign = '<%=request.getAttribute(GoodsKey.GoodsType.toString())%>';
			if('2'==sign){
				cookieutil.addVantegeGoodsToShoppingCar($('#goodsIdInput1').val(), $("#count_input").val());
			}else{
				cookieutil.addGoodsToShoppingCar($('#goodsIdInput1').val(), $("#count_input").val());
			}
			cmsAlertSuccess("成功添加到购物车！");
			if(_productListMenuApp) {
				_productListMenuApp.loasShoppingCharCount();
			}
		}
		
		function buyRightNow(){
			cookieutil.addGoodsToShoppingCar($('#goodsIdInput1').val(), $("#count_input").val());
			location = mainWeb + '/front/shopingCar/getGoods';
		}
</script>
	</head>
	<body>
		<div id="category-level"><%=request.getAttribute(GoodsKey.CategoryNavigator.toString())%></div>
		<div id="product-detail_buy">
			<div class="product_image">
				<img width="350px" height="330"
					src="<%=request.getAttribute(GoodsKey.GoodsImageUrl.toString())%>" />
			</div>
			<div class="product_buying">
				<p>
					<%=request.getAttribute(GoodsKey.GoodsName.toString())%>
				</p>
				<div calss="code"
					style="margin-left: 25px; height: 40px; line-height: 40px;">
					商品编号：&nbsp;&nbsp;<%=request.getAttribute(GoodsKey.GoodsCode.toString())%>
				</div>
				<div class="specif" style="width: 100%; * margin-left: 37px;">
					&nbsp;&nbsp;&nbsp;&nbsp;规格：
					<div id="specSelectDiv"><%=request.getAttribute(GoodsKey.SpecSelector.toString())%>
					</div>
				</div>
				<div class="price" style="margin-left: 25px; * margin-left: 37px;">
					&nbsp;&nbsp;&nbsp;&nbsp;原价：
					<font color="gray"><span id="goodsPriceSpan0"
						style="text-decoration: line-through;"><%=request.getAttribute(GoodsKey.GoodsOldPrice.toString())%></span>
					</font>
				</div>
				<div class="price" style="margin-left: 25px; * margin-left: 37px;">
					&nbsp;&nbsp;&nbsp;&nbsp;价格：
					<b><span id="goodsPriceSpan1"><%=request.getAttribute(GoodsKey.GoodsPrice.toString())%></span>
					</b>
				</div>
				<div class="price" >
					赠送积分：&nbsp;&nbsp;<font id="vantagesValue"><%=request.getAttribute(GoodsKey.VantegesRule.toString())%></font>
				</div>
				<div class="price">
					促销信息：&nbsp;&nbsp;<font id="pmtValue"><%=request.getAttribute(GoodsKey.PromotionInfo.toString())%></font>
				</div>
				<div class="price" id="bookingText" style="display:<%=request.getAttribute(GoodsKey.IsBooking.toString())%>">
					预定商品：&nbsp;&nbsp;<font color=red>本商品为预定商品，请提前2天下单。</font>
				</div>
				<div id="product_buying_button">
					<div class="oper">
						<div class="warp_specil">
							<span>商品规格：</span>
							<div class="buying_specil" id="goodsSpecSpan1"
								onselectstart="return false"><%=request.getAttribute(GoodsKey.GoodsSpec.toString())%>
							</div>
						</div>
						<div class="buy_number">
							<span>购买数量：</span>
							<img
								src="<%=basePath%>/images/page/product-detail-buy-button-minus.png"
								onclick="subCount();" style="cursor: pointer;" />
							<input type="text" value="1" id="count_input"
								style="text-align: center;" onchange="Count_inputOnChange();"
								preValue="1" />
							<img
								src="<%=basePath%>/images/page/product-detail-buy-button-add.png"
								onclick="sumCount();" style="cursor: pointer;" />
						</div>
					</div>
					<div class="pay">
						<input type="hidden" name="goodsId" id="goodsIdInput1"
							value="<%=request.getAttribute(GoodsKey.GoodsId.toString())%>" />
						<img src="<%=basePath%>/images/page/buy_rightnow.png"
							onclick="buyRightNow();" style="cursor: pointer;">
						<img src="<%=basePath%>/images/page/addtocar_01.png"
							onclick="addToShoppingCar();" style="cursor: pointer;" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>