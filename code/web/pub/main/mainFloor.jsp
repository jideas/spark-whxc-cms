<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript">
		$(function(){
			var current = $("#mainFloorTitleDiv ul li:first");
			current.addClass("current");
			var id=current.attr("id");
			var currentContain = $("#mainFloorContain #"+id);
			currentContain.css("display","block");
			
			$.each($("#mainFloorTitleDiv ul li"),function(){
				 var _this = $(this);
				 var _id =  $(this).attr("id");
				 var _thisContain =  $("#mainFloorContain #"+_id);
				
				 _this.bind("mouseover",function(){
					if(current==_this)return
					current.removeClass("current");
					currentContain.css("display","none");
					
				 	current  = _this;
				 	currentContain = _thisContain;
					_this.addClass("current");
					_thisContain.css("display","block");
					
				}).bind("mouseout",function(){
					if(current==_this)return;
					_this.removeClass("current");
					_thisContain.css("display","none");
				});
			});
		});
		
	</script>
		<style type="text/css">
#mainFloorMainDiv {
	width: 1200px;
	height:260px;
	margin: 0 auto;
	border-bottom: #CDCDCD solid 1px;
	border-right: #CDCDCD solid 1px;
	border-left: #CDCDCD solid 1px;
	border-top: #22A618 solid 2px;
	display: block;
	float: left;
	margin-top: 5px;
	margin-bottom: 5px;
}

#mainFloorMainDiv #mainFloorTitleDiv {
	width: 1200px;
	line-height: 40px;
	display: block;
	float: left;
	
}

#mainFloorTitleDiv  ul {
	list-style: none;
}

#mainFloorTitleDiv  li {
	display: inline-block;
	float: left;
	width: 299px;
	*width: 300px;
	cursor: pointer;
	background:url("./images/page/bg-jd.png") repeat-x left bottom;
	border-left: 1px solid #CDCDCD;
	height: 38px;
	color: #000000;
	text-align: center;
	text-decoration: none;
	font-size: 14px;
	font-weight: bold;
	font-family: '΢���ź�','����'
}


#mainFloorTitleDiv  .current {
	border-bottom-style: none;
	background-image:none;
	color: red;
}

#mainFloorTitleDiv  .pre {
	border-left-style: none;
}

#mainFloorContain ul{
	display: none;
	position: relative;
	height: 220px;
	width: 100%;
	text-align: left;
	float: left;
	text-decoration: none;
	margin-top: 5 auto;
}
#mainFloorContain ul li{
	display: block;
	text-align: center;
	float: left;
	text-decoration: none;
	width:190px;
	height:200px;
	margin-top:10px;
	margin-bottom:10px;
	margin-left:8px;
	vertical-align:middle;
	border: 1 solid red;
}

#mainFloorContain ul li .product_price {
	line-height: 22px;
	font-size: 12px;
	height: 22px;
	width:100%;
	float: left;
	font-weight: bold;
	color: #CC0000;
}

#mainFloorContain ul li .product_name{
	line-height: 22px;
	width:100%;
	font-size: 12px;
	color: #000000;
	text-decoration: none;
	height: 22px;
	float: left;
}
#mainFloorContain ul li .product_type {
	line-height: 22px;
	width:100%;
	font-size: 12px;
	color: #000000;
	text-decoration: none;
	height: 22px;
	float: left;
	line-height: 22px;
}
#mainFloorContain ul li .product_image{
	text-align: center;
}


</style>
	</head>
	<body>
		<div id="mainFloorMainDiv">
		<!-- 
			<div id="mainFloorTitleDiv">
				<ul>
					<li id="mainFloorLi1" class="pre" >
						�������
					</li>
					<li id="mainFloorLi2">
						��Ʒ�ϼ�
					</li>
					<li id="mainFloorLi3">
						������Ʒ
					</li>
					<li id="mainFloorLi4">
						������Ʒ
					</li>
				</ul>
			</div>
			<div id="mainFloorContain">
				<ul id="mainFloorLi1">
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							����				
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
				</ul>
				<ul id="mainFloorLi2">
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
				</ul >
				<ul id="mainFloorLi3">
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
				</ul>
				<ul id="mainFloorLi4">
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
				</ul>
				<ul id="mainFloorLi5">
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">����</a>					
						</div>
						<div class="product_type">
							���Լ500g/��
						</div>
						<div class="product_price">
							��28.00/��
						</div>
					</li>
				</ul>
			</div>
		 -->
		</div>
	</body>
<script type="text/javascript">
	$(function(){
		$.ajax({
 			url: mainWeb + '/front/channel/getMainFloorHtml',
 	 		type: 'POST',
 	 		dataType: 'json',
 			async: true,
 			success: function(data) {
 				if (data) {
 				  $("#mainFloorMainDiv").html(data[0].toString());
 				  
					var current = $("#mainFloorTitleDiv ul li:first");
					current.addClass("current");
					var id=current.attr("id");
					var currentContain = $("#mainFloorContain #"+id);
					currentContain.css("display","block");
					
					$.each($("#mainFloorTitleDiv ul li"),function(){
						 var _this = $(this);
						 var _id =  $(this).attr("id");
						 var _thisContain =  $("#mainFloorContain #"+_id);
						
						 _this.bind("mouseover",function(){
							if(current==_this)return
							current.removeClass("current");
							currentContain.css("display","none");
							
						 	current  = _this;
						 	currentContain = _thisContain;
							_this.addClass("current");
							_thisContain.css("display","block");
							
						}).bind("mouseout",function(){
							if(current==_this)return;
							_this.removeClass("current");
							_thisContain.css("display","none");
						});
					});
 				} 
 			},
 			error: function() {
 				alert("�ף�����ͨ�Ŵ����쳣����ˢ��ҳ�棡");
 			}
 		});
	});
	</script>
</html>
