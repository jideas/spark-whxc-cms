<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<%
	String categoryId = (String)request.getAttribute("categoryId");
	if (null == categoryId) categoryId = "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<style type="text/css">
/*���༶��*/
#category-level {
	width: 1200px;
	height: 20px;
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

#category-level span a,
#category-level span a:link{
	color: #000000;
	text-decoration: none;
}

##category-level span a:hover{
	color: #FF0000;
	text-decoration: underline;
}
/*��Ʒ����*/
#hot-sale {
	position: relative;
	width: 1200px;
	height: 180px; *
	height: 182px;
	margin: 0px;
	padding: 0px;
	border: 1px solid #FC9503;
}

/*��Ʒ���� -> ���Ͻǹ��*/
#hot-sale .ad-left-top {
	position: absolute;
	left: 0px;
	top: 0px;
}

/*��һ��/��һ��*/
#hot-sale a.next,#hot-sale a.prev {
	position: absolute;
	display: none;
	top: 35%;
	margin-top: -15px;
	z-index: 60;
	height: 75px;
	width: 50px;
	background-image: url("<%=basePath%>/images/page/arrows.png");
}

#hot-sale a.next {
	background-position: 100% 0;
	right: 0px;
}

#hot-sale a.prev {
	left: 0px;
	background-position: 0 0;
}

#hot-sale a.next,#hot-sale a.prev {
	display: block
}

/*��������*/
#hot-sale-scroll {
	position: relative;
	margin-left: 60px;
	width: 1080px;
	height: 180px;
	overflow: hidden;
	overflow-x: hidden;
	overflow-y: hidden;
}

/*�������� -> ��������*/
#hot-sale-scroll ul {
	position: absolute;
	width: 999999px;
	left: 40px;
	list-style: none;
	margin-left: -40px;
}

#hot-sale-scroll li {
	float: left;
	width: 270px;
	height: 180px;
	margin-top: 0px;
}

/*�������� -> ͼƬ*/
#hot-sale-scroll .scroll-image {
	float: left;
	width: 135px;
	height: 180px;
	line-height: 180px;
	padding-top: 25px;
}

#hot-sale-scroll .scroll-image img {
	border: 0px;
	width: 130px;
	height: 130px;
	outline: none;
}

/*�������� -> ����*/
#hot-sale-scroll .scroll-detail {
	float: right;
	width: 125px;
	height: 180px;
	font-size: 12px;
	color: #000000;
	padding-top: 40px;
}

#hot-sale-scroll .scroll-detail div {
	height: 25px;
	line-height: 25px;
}

#hot-sale-scroll .scroll-detail div b {
	color: #CE0303;
}

#hot-sale-scroll .scroll-detail a,
#hot-sale-scroll .scroll-detail a:link{
	text-decoration: none;
	color: #000000;
}

#hot-sale-scroll .scroll-detail a:hover {
	text-decoration: underline;
	color: #FF0000;
}

#hot-sale-scroll .scroll-detail input {
	border: none;
	cursor: pointer;
	color: #FFFFFF;
	font-weight: bold;
	width: 82px;
	height: 27px;
	background-image: url("<%=basePath%>/images/page/Qbuy01.png");
	background-repeat: no-repeat;
}
</style>
<script type="text/javascript">
function productListToGoodsInfo(recid){
	
}
</script>
	</head>
	<body>
		<div id="hot-sale">
			<div class="ad-left-top">
				<img src="<%=basePath%>/images/page/hot-sale-HOT.png" />
			</div>
			<A class="next" href="javascript:void(0)" onfocus="this.blur()"></A><A class="prev"
				href="javascript:void(0)" onfocus="this.blur()"></A>
			<div id="hot-sale-scroll">
				<ul></ul>
			</div>
		</div>
<script type="text/javascript">

function HotSaleApp() {
	var categoryId = '<%=categoryId%>';
	
	//������ʱ��
	function func_hot_sale_timer(){		
		$("#hot-sale a.next").click();
		hot_sale_timer = setTimeout(func_hot_sale_timer,5000);
	}
	//var hot_sale_timer = setTimeout(func_hot_sale_timer,5000);
	
	var initActions = function() {
		
		//������������Ч��
		$("#hot-sale li input").bind("mouseover",function(){
			$(this).css("background-image","url('" + mainWeb + "/images/page/Qbuy02.png')");
		}).bind("mouseout",function(){
			$(this).css("background-image","url('" + mainWeb + "/images/page/Qbuy01.png')");
		});
		var tempLength = 0; //��ʱ����,��ǰ�ƶ��ĳ���
		var viewNum = 4; //����ÿ����ʾͼƬ�ĸ�����
		var moveNum = 4; //ÿ���ƶ�������
		var moveTime = 700; //�ƶ��ٶ�,����
		var scrollDiv = $("#hot-sale-scroll ul"); //�����ƶ�����������
		var scrollItems = $("#hot-sale-scroll ul li"); //�ƶ�������ļ���
		var moveLength = scrollItems.eq(0).width() * moveNum; //����ÿ���ƶ��ĳ���
		var countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).width(); //�����ܳ���,�ܸ���*��������
		 
		//��һ��
		$("#hot-sale a.next").bind("click",function(){
			if(tempLength < countLength){
				if((countLength - tempLength) > moveLength){
					scrollDiv.animate({left:"-=" + moveLength + "px"}, moveTime);
					tempLength += moveLength;
				}else{
					scrollDiv.animate({left:"-=" + (countLength - tempLength) + "px"}, moveTime);
					tempLength += (countLength - tempLength);
				}
			}else{
				scrollDiv.animate({left:"+=" + countLength + "px"}, moveTime);
				tempLength = 0;
			}
		});
		//��һ��
		$("#hot-sale a.prev").bind("click",function(){
			if(tempLength > 0){
				if(tempLength > moveLength){
					scrollDiv.animate({left: "+=" + moveLength + "px"}, moveTime);
					tempLength -= moveLength;
				}else{
					scrollDiv.animate({left: "+=" + tempLength + "px"}, moveTime);
					tempLength = 0;
				}
			}else{
				scrollDiv.animate({left:"-=" + countLength + "px"}, moveTime);
				tempLength = countLength;
			}
		});
		//������� -> �����ʱ��/��һ��/��һ��
		$("#hot-sale").bind("mouseenter",function(){
			clearTimeout(hot_sale_timer);
		}).bind("mouseleave",function(){
			hot_sale_timer = setTimeout("func_hot_sale_timer()",5000);
		});		
	};
	
	var render = function(data) {
		if (null == data) return;
		var html = "";
		for (var index = 0; index < data.length; index++) {
			html += "<li>";
			html += "<div class='scroll-image'>";
			html += "<a style='width: 130; height: 130; border: 0'" 
					+ " href='" + mainWeb + "/front/toGoodsInfoPage?id=" + data[index].recid + "'"
					+ " target='_blank'><img src='" + mainWeb  + data[index].picturepath2 + "'"
					+ " width='130px;' /></a>";
			html += "</div>";
			html += "<div class='scroll-detail'>";
			html += "<div>";
			var goodsName = data[index].goodsname;
			if (goodsName.length > 8) {
				goodsName = goodsName.substring(0, 8) + "...";
			}
			html += "<a target='_blank'	href='" + mainWeb + "/front/toGoodsInfoPage?id=" + data[index].recid + "'>" + goodsName + "</a>";
			html += "</div>";
			html += "<div>���" + data[index].goodsspec + "</div>";
			html += "<div>";
			html += "<b>��" + data[index].realprice + "/" + data[index].goodsunit + "</b>";
			html += "</div>";
			html += "<div>";
			html += "<input type='button' value='' onClick=\"hotsale_hurrybuy('" + data[index].recid + "',1)\" />";
			html += "</div>";
			html += "</div>";
			html += "</li>";
		}
		$('#hot-sale-scroll ul').html(html);
	};
	
	var init = function() {
		$.ajax({
			type: 'post',
			url: mainWeb + '/front/getHotSaleList?categoryId=' + categoryId,
			dataType: 'json',
			success: function(data) {
				render(data);
				initActions();
				hot_sale_timer = setTimeout(func_hot_sale_timer,5000);
			}
		});
	};	
	init();
}
new HotSaleApp();

//����������
function hotsale_hurrybuy(inputid,count){
	cookieutil.addGoodsToShoppingCar(inputid, count);
	if(_productListMenuApp){
		_productListMenuApp.loasShoppingCharCount();
	}
	cmsAlertSuccess("��ʾ","��ӳɹ���");
};
</script>
	</body>
</html>