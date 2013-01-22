<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<script type="text/javascript" src="<%=basePath%>/pub/common/pagin.js"></script>
<script type="text/javascript" src="<%=basePath%>/scripts/cookieutil.js"></script>
<% 
	String serachKeyItem = (String)request.getAttribute("serachKeyItems");
	String categoryId = (String)request.getAttribute("categoryId");
%>
<style type="text/css">
*{
	padding: 0px;
	margin: 0px;
}

.categoryPagecontantRight {
	float: left;
	width: 975px;
	height: 1730px;
}
.categoryPagecontantRight .categoryPagecontantRight1{
	float:left;
	width:975px;
	min-height:40px;
	border-top:2 solid green;
	margin-top: 2px;
	display:inline;
	text-align:center;
}
.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Left{
	float:left;
	width: 85;
	min-height: 40px;
	padding-top: 16px;
	font-size: 14px;
	font-family: "宋体"
	
}
.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Right{
	float:left;
	text-align: left;
	padding-left:5px;
}

.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Right a{
	padding-left:15px;
	color: #076A00;;
	cursor: hand;
}

.categoryPagecontantRight .categoryPagecontantRight1 .categoryPagecontantRight1Right a:HOVER{
	padding-left:15px;
	color: #CE0303;;
}
.categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant {
	width: 875px;
	height: 35px;
	line-height: 35px;
	font-size: 13px;
	padding-top: 5px;
	font-family:"宋体";
	border-bottom: #96C180 dotted 1px;
}

.categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant .currentover{
	color: #CE0303;
	text-decoration: underline;
}

.categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant .current{
	color: #CE0303;
	text-decoration: underline;
}

.categoryPagecontantRight .categoryPagecontantRight2{
	float:left;
	width:975px;
	height: 35px;
	line-height: 35px;
	text-align: left;
	margin-top:5px;
	border-bottom:1px solid #96C180 ;
}

.categoryPagecontantRight .categoryPagecontantRight2 .orderDiv{
	float:left;
	width:450px;
	height: 35px;
	line-height: 35px;
	text-align: left;
}
.categoryPagecontantRight .categoryPagecontantRight2 .orderDiv .orderBy{
	margin-left: 15px;
	float: left;
	line-height: 33px;
	height: 33px;
	vertical-align:bottom;
	margin-top: 3px;
}

.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv{
	float:left;
	width:490px;
	height: 33px;
	line-height: 33px;
	text-align: right;
}
.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv #productNum{
	float:left;
	width:280px;
	text-align: right;
	color: #DE4F02;
	font-size: 12;
}
.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv #currentPage{
	float:left;
	width:50px;
	text-align: center;
	font-size: 14;
}

.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv .buttonInitClassPageUp{
	width: 66px;
	line-height: 28px;
	height: 28px;
	float: left;
	background: url('<%=basePath%>/images/page/button_pageDown_disabled.png') no-repeat;
	cursor: hand;
	margin-left: 15px;
	margin-top: 3px;
	
}

.categoryPagecontantRight .categoryPagecontantRight2 .showInfoDiv .buttonInitClassPageDown{
	width: 66px;
	line-height: 28px;
	height: 28px;
	float: left;
	background: url('<%=basePath%>/images/page/button_pageUp_disabled.png') no-repeat;
	cursor: hand;
	margin-left: 5px;
	margin-top: 3px;
}

.categoryPagecontantRight .categoryPagecontantRight2 .buttonInitClassLeft{
	float:left;
	width: 82px;
	line-height: 29px;
	height: 29px;
	cursor: hand;
	margin-left:5px;
	margin-top:4px;
}
/*过滤 -> 【销量】【价格】【上架时间】*/
.categoryPagecontantRight2 #btnSale{
	background-image:url('<%=basePath%>/images/page/btnSale_normal.png');
	background-repeat: no-repeat; 
}
.categoryPagecontantRight2 #btnPrice{
	background-image:url('<%=basePath%>/images/page/btnPrice_asc.png');
	background-repeat: no-repeat;
}
.categoryPagecontantRight2 #btnDate{
	background-image:url('<%=basePath%>/images/page/btnDate_normal.png');
	background-repeat: no-repeat;
}

.categoryPagecontantRight .categoryPagecontantRight3 {
	width: 975px;
	float:left;
	text-align: center;
	margin:0px;
	padding:0px;
	height: 1560px;
}
/*商品行*/
.categoryPagecontantRight .goodsInfoRow {
	height: 250px;
	float: left;
	width:975px;
	margin-top:10px;
	text-align: left;
}
/*商品项*/
.categoryPagecontantRight .goodsInfoRowItem {
	text-align: left;
	display: inline;
	float: left;
	width: 235px;
	height: 250px; 
	text-align: center;
	margin-left:7px;
}
.categoryPagecontantRight .categoryPagecontantRight3 .p-price {
	line-height: 22px;
	font-size: 12px;
	color: #CE0303;
}

.categoryPagecontantRight .categoryPagecontantRight3 .p-name {
	line-height: 22px;
	font-size: 12px;
	color: #000000;
	width:200px;
	white-space:nowrap;
	overflow:hidden;
	text-decoration: none;
}
.categoryPagecontantRight .categoryPagecontantRight3 div.p-name a:hover{
	color: #FF0000;
	text-decoration: underline;
}

.goodsInfoRow img.bellowGoodsSmallInfo_img_minus,
.goodsInfoRow img.bellowGoodsSmallInfo_img_add,
.goodsInfoRow img.bellowGoodsSmallInfo_img_buy
{
	vertical-align: middle;
	cursor: hand;
}

.goodsInfoRow img.bellowGoodsSmallInfo_img_add{
	margin: 0px 2px;
}

.categoryPagecontantRight .goodsInfoRow .goodsInfoRowItem input {
	width: 22px;
	height: 21px;
	line-height: 21px;
	border: #C1C1C1 solid 1px;
	text-align: center;
	vertical-align: middle;
}
/*商品图片 -> 浮动框*/
.categoryPagecontantRight3 a.goodsCellImage{
	display:inline-block;
	width: 220px;
	height:150px;
	border: 1px solid #FFFFFF;
}
.categoryPagecontantRight3 a.goodsCellImage img{
	margin-top: 5px;
	width: 140px;
	height: 140px;
}
.categoryPagecontantRight3 a.goodsCellImage:hover{
	border: 1px solid #DDDDDD;
	background-color: RGB(254,254,254);
}

.categoryPagecontantRight #pageManagerDiv{
	float:left;
	width:100%;
	height: 40px;
	line-height:40px;
	text-align:right;
	*padding-top: 10px;
	margin-top: 5px;
}
/*分页控件*/
.pagin a,.pagin span {
	height: 20px;
	padding: 3px 10px;
	border: 1px solid #ccc;
	margin-left: 2px;
	font-family: arial;
	line-height: 20px;
	font-size: 14px;
	*vertical-align: middle;
	overflow: hidden;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
}

.pagin .text,.pagin .current {
	border: none;
	padding: 4px 11px;
}

.pagin a:link,.pagin a:visited {
	color: #005aa0;
	text-decoration:none;
}

.pagin a:hover,.pagin a:active {
	background: #005aa0;
	color: #fff;
	text-decoration: none;
}

.pagin .current,.pagin .current:link,.pagin .current:visited {
	color: #f60;
	font-weight: bold;
}

.pagin b {
	dispaly: block;
	position: absolute;
	top: 9px;
	width: 5px;
	height: 9px;
	background-image: url(<%=basePath%>/images/page/pagin.png);
	background-repeat: no-repeat;
	overflow: hidden;
}

.pagin .prev,.pagin .next,.pagin .prev-disabled,.pagin .next-disabled {
	position: relative;
	padding-top: 5px;
	height: 18px;
	line-height: 18px;
}

.pagin .prev-disabled,.pagin .next-disabled {
	color: #ccc;
	cursor: default;
}

.pagin .prev,.pagin .prev-disabled {
	padding-left: 12px;
}

.pagin .prev b {
	left: 3px;
	background-position: -6px 0px;
}

.pagin .prev-disabled b {
	left: 3px;
	background-position: -18px 0px;
}

.pagin .next,.pagin .next-disabled {
	padding-right: 12px;
}

.pagin .next b {
	right: 3px;
	background-position: 0px 0px;
}

.pagin .next-disabled b {
	right: 3px;
	background-position: -12px 0px;
}

.pagin-m a,.pagin-m span {
	height: 14px;
	line-height: 14px;
	font-size: 12px;
}

.pagin-m b {
	top: 5px;
}

.pagin-m .prev,.pagin-m .next,.pagin-m .prev-disabled,.pagin-m .next-disabled
	{
	padding-top: 3px;
	height: 14px;
	*height: 22px;
	line-height: 14px;
	*line-height: 16px;
}
.pagin a, .pagin span {
	height: 14px;
	*height: 22px;
}

</style>
	</head>
	<body>
		<div class="categoryPagecontantRight">
			<!-- begin of 类别刷选 -->
			<div class="categoryPagecontantRight1">
				<div class="categoryPagecontantRight1Left">
					<b>&nbsp;商品筛选</b>
				</div>
				<div class="categoryPagecontantRight1Right">
				    <%=serachKeyItem %>
				</div>
			</div>
			<!-- end of 类别刷选 -->
			<!-- begin of 排序 -->
			<div class="categoryPagecontantRight2">
			  	<div class="orderDiv">
				  	<span class="orderBy">排序：</span>
					<span class="buttonInitClassLeft" id='btnSale' onclick="product.changeSort('btnSale','saledCount');">
					</span>
					<span class="buttonInitClassLeft asc" id='btnPrice' onclick="product.changeSort('btnPrice','realprice');">
					</span>
					<span class="buttonInitClassLeft" id="btnDate" onclick="product.changeSort('btnDate','publishDate');">
					</span>
			  	</div>
			  	<div class="showInfoDiv">
			  		<span id="productNum">共 <b id="allProductNum">0</b> 个商品</span>
			  		<span id="currentPage">0/0</span>
			  		<span class="buttonInitClassPageDown" id="retButton" onclick="product.doPageDown()"></span>
			  		<span class="buttonInitClassPageUp" id="preButton" onclick="product.doPageUp()"></span>
				</div>
			</div>
			<div class="categoryPagecontantRight3">
			</div>
			<div id="pageManagerDiv" class='pagin pagin-m fr' >
			</div>
		</div>
	</body>
<script type="text/javascript">
/**
	$(function(){
		$("#categoryPagecontantRight3 .goodsInfoRow .goodsInfoRowItem").bind("mouseover",function(){
			$(this).css("border","2px solid #CCCCCC");
			$($(this).children(".bellowGoodsSmallInfo")).css("background-color","#EEEEEE");
		}).bind("mouseout",function(){
			$(this).css("border","2px solid #FFFFFF");
			$($(this).children(".bellowGoodsSmallInfo")).css("background-color","#FFFFFF");
		});
		
	});	
**/
	var product = new Product();
	function Product(){
		this.parentCid = "<%=categoryId%>";
		this.cid = "<%=categoryId%>";
		this.allNum = 0;
		this.offset = 0;
		this.pageNum = 0;
		this.orderBy = "realprice";
		this.lastButton = null;
		this.currentOrderby = "realprice~Asc";
		this.ord = "Asc";
		this.minprice = 0;
		this.maxprice = 10000;
		
		serachArray = {};
		this.initSortPage = function(){
			this.lastButton = $('#btnPrice');
			//初始化 -> 按钮 -> 移入/移除
			$(".categoryPagecontantRight2 .buttonInitClassLeft").each(function(){
				$(this).bind("mouseover",function(){
					if($(this).hasClass("asc") || $(this).hasClass("desc")){
						return;
					}
					$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_hover.png')");
				}).bind("mouseout",function(){
					if($(this).hasClass("asc") || $(this).hasClass("desc")){
						return;
					}
					$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_normal.png')");
				});				
			});
		};
		
		this.changeSort = function(id,orderby){
			//改变按钮图片
			$(".categoryPagecontantRight2 .buttonInitClassLeft").each(function(){
				//当前点击的按钮
				if($(this).attr("id") == id){
					//按钮为“价格”
					if(id == "btnPrice"){
						if($(this).hasClass("asc")){
							$(this).removeClass("asc");
							$(this).addClass("desc");
							$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_desc.png')");							
						}else{
							$(this).removeClass("desc");
							$(this).addClass("asc");
							$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_asc.png')");
						}
					}else{
						if($(this).hasClass("desc")){
							$(this).removeClass("desc");
							$(this).addClass("asc");
							$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_asc.png')");
						}else{
							$(this).removeClass("asc");
							$(this).addClass("desc");
							$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_desc.png')");					
						}					
					}
				}else{
					$(this).removeClass("asc");
					$(this).removeClass("desc");
					$(this).css("background-image","url('<%=basePath%>/images/page/" + $(this).attr("id") + "_normal.png')");
				}
			});
			
			this.lastButton = $("#"+id);
			var qob ="";
			if(this.orderBy==orderby){
				if(this.ord=="Asc"){
					this.ord = "Desc";
				}else{
					this.ord = "Asc";
				}
				if("realprice"==orderby){
					qob = "realprice~"+this.ord ;
				}else if("publishDate"==orderby){
					qob = "publishDate~"+this.ord ;
				}else if("saledCount"==orderby){
					qob = "saledCount~"+this.ord ;
				}
			}else{
				if("realprice"==orderby){
					qob = "realprice~Asc";
				}else if("publishDate"==orderby){
					qob = "publishDate~Desc";
				}else if("saledCount"==orderby){
					qob = "saledCount~Desc";
				}
			}
			this.orderBy = orderby;
			this.currentOrderby = qob;
			this.query(this.cid,null,null,qob);
		};
		
		this.countChangeListener = function(eventId) {
			var id = "#" + eventId;
			var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
			if ($(id).val().match(reg) == null) {
				$(id).val($(id).attr("preValue"));
			} else {
				$(id).attr("preValue", $(id).val());
			}
		};
		
		/**
		* 加减产品，需要写cookie
		*/
		this.setbuyNum = function(inputId,count){
			if (!inputId || !count) {
				return;
			}
			var id = "#" + inputId;
			var oldValue = $(id).val();
			var newValue = Number(oldValue) + Number(count);
			if (newValue < 0) {
				newValue = 0;
			}
			$(id).val(newValue);
		};
		
		this.serachPrice = function(minprice,maxprice){
			this.minprice = minprice;
			this.maxprice = maxprice;
			this.query(null,null,null,null,minprice,maxprice);
		};
		
		this.dobuy = function(inputid){
			var id = "#txt_" + inputid;
			var count = $(id).val();
			cookieutil.addGoodsToShoppingCar(inputid, count);
			if(_productListMenuApp){
				_productListMenuApp.loasShoppingCharCount();
			}
			cmsAlertSuccess("提示","添加成功！");
		};
		
		this.hurrybuy = function(inputid,count){
			cookieutil.addGoodsToShoppingCar(inputid, count);
			if(_productListMenuApp){
				_productListMenuApp.loasShoppingCharCount();
			}
			cmsAlertSuccess("提示","添加成功！");
		};
		
		this.serachCategory = function(id){
			if(id==null||id=="null"){
				this.cid = this.parentCid;
			}else{
				this.cid = id;
			}
			this.query();
		};
		
		this.sort = function(orderby){
			product.query(null,0,24,orderby);
		};
		
		this.query = function(cid,offset,pagesize,orderby,minprice,maxprice){
			 if(!offset)offset=0;
			 if(!pagesize)pagesize=24;
			 if(!cid) cid = this.cid;
			 if(!orderby)orderby = this.currentOrderby;
			 if(!minprice) minprice=this.minprice;
			 if(!maxprice) maxprice=this.maxprice;
			$.ajax({
	 			url: '<%=basePath%>/front/showProuctsListHtml',
	 	 		type: 'POST',
	 	 		dataType: 'json',
	 	 		data:{'offset':offset,'pageSize':pagesize,'goodsCategoryId':cid,'orderBy':orderby,'minPrice':minprice,'maxPrice':maxprice},
	 			async: true,
	 			success: function(data) {
	 				if (data) {
	 					if(data[0]==""||data[0]=="undefined"){
	 						$(".categoryPagecontantRight3").html("");
		 					product.setPage(data[1],data[2],data[3]);
		 					if(Number(data[2])>=1){
		 						product.setPageSize(data[2]);
		 					}else{
		 						//$("#pageManagerDiv").hide();
		 					}
	 					}else{
	 						$(".categoryPagecontantRight3").html(data[0].toString());
		 					product.setPage(data[1],data[2],data[3]);
		 					if(Number(data[2])>=1)
		 						product.setPageSize(data[2]);
	 					}
	 					
	 				} 					
	 			}
	 		});
		};
		this.getPageInfo = function(cid,offset,pagesize,orderby){
			if(!offset)offset=this.offset;
			 if(!pagesize)pagesize=24;
			 if(!cid) cid = this.cid;
			 if(!orderby)orderby = this.currentOrderby;
			  $.ajax({
	 			url: '<%=basePath%>/front/showProuctsListHtml',
	 	 		type: 'POST',
	 	 		dataType: 'json',
	 	 		data:{'offset':offset,'pageSize':pagesize,'goodsCategoryId':cid,'orderBy':orderby},
	 			async: true,
	 			success: function(data) {
	 				if (data) {
	 					$(".categoryPagecontantRight3").html(data[0].toString());
	 				} 
	 			}
	 		});
		};
		this.setPage = function(da1,da2,da3){
			product.allNum = da1;
			product.pageNum = da2;
			product.offset = da3;
			 $("#allProductNum").html(da1.toString());
			 $("#currentPage").html((Number(da3)+1)+"/"+da2.toString());
			 if(da3>=1){
				  $("#retButton").css("background-image","url(<%=basePath%>/images/page/button_pageUp.png)");
			  }else{
				 $("#retButton").css("background-image", "url(<%=basePath%>/images/page/button_pageUp_disabled.png)");
			  }
			  if(da2>1&&(Number(da3)+1)!=da2){
				  $("#preButton").css("background-image","url(<%=basePath%>/images/page/button_pageDown.png)");
			  }else{
				 $("#preButton").css("background-image", "url(<%=basePath%>/images/page/button_pageDown_disabled.png)");
			  }
		};
		this.doPageUp = function(){
			var topage = Number(product.offset)+1;
			if(topage>=product.pageNum)return;
			cmsp.setSelection(topage+1);
			//product.query(product.cid,topage,null);
		};
		this.doPageDown = function(){
			var topage = Number(product.offset)-1;
			if(topage<0)return;
			cmsp.setSelection(topage+1);
			//product.query(product.cid,topage,null);
		};
		this.goPageSize = function(topage){
			product.getPageInfo(product.cid,topage,null);
		};
		
		
		var cmsp;
		this.setPageSize=function(pagesize){
			var pageChangeAction = function(pageIndex) {
				product.offset = pageIndex;
				product.goPageSize();
				product.setPage(product.allNum,product.pageNum,pageIndex-1);
			};
			
			cmsp = new CmsPaging({
				parentId: 'pageManagerDiv',
				count: product.allNum,
				pageSize: 24,
				isActionOnLoad: false,
				actionListener: function(pageIndex) {
					pageChangeAction(pageIndex);
				}
			});
			//new CmsPaging('pageManagerDiv', pagesize, pageChangeAction);
		};
	}
	product.initSortPage();
	product.query();
	function serachPrice(minp,maxp){
		product.serachPrice(minp,maxp);
	}
	function serachCategory(id){
		product.serachCategory(id);
	}
	
	$(function(){
		$(".categoryPagecontantRight .categoryPagecontantRight1  .categoryPagecontantRight1_contant a").bind("mouseover",function(){
			if($(this).hasClass("current"))return;
			$(this).addClass("currentover");
		}).bind("mouseout",function(){
			$(this).removeClass("currentover");
		}).bind("click",function(){
			$(this).addClass("current").siblings().removeClass("current");;
		});
	})
</script>
</html>