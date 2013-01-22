<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<script type="text/javascript">
	$(function(){
		var floor_3Action = new FloorAction();
		floor_3Action.setArgs("#floor_3","./images/page/sort-bg03.png","#ffb847");
	});	
	
	this.bind_mouseover = function(){
		$(".commonFloor .floor_product .floor_category").each(function(){
			 var _this = this;
			 $(_this).children("ul li:gt(1)").each(function(){
				 
				 $(this).bind("mouseover",function(){
						$(this).removeClass("floor_third_category_hover");
						$(this).addClass("floor_third_category_hover");
						var id = "product_"+$(this).attr("id");
						$(id).show().siblings().hide();
						
				});
			 });
		});
		$(floorId + " .floor_product ul li:gt(1)").each(function(index){
			$(this).bind("mouseover",function(){
				$(floorId + " .floor_product ul li:gt(1)").removeClass("floor_third_category_hover");
				$(this).addClass("floor_third_category_hover");
				$(floorId + " .floor_product div.floor_category_product").css("display","none");
				$(floorId + " .floor_product div.floor_categore_product_"+index).css("display","block");
			});
		});
	}
	//默认显示第一个
	this.show = function(floorId){
		$(floorId + " .floor_product ul li:eq(2)").mouseover();
	}	
	
</script>

	</head>
	<body>
	<div class="commonFloor" id="floor_3">
		<div class="floor_product">
			<div class="floor_line_dotted"></div>
			<div class="floor_category">
				<ul>
					<li class="floor_second_category">
						新食材
					</li>
					<li class="floor_span">
					</li>
					<li>
						蔬菜
					</li>
					<li>
						水果
					</li>
					<li>
						蛋品
					</li>
					<li>
						肉禽
					</li>
					<li>
						腌腊制品
					</li>
					<li>
						豆制品
					</li>
					<li>
						水产
					</li>
				</ul>
			</div>
			<div class="floor_category_product floor_categore_product_0">
				<ol>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
					<li>
						<div class="product_image">
							<a href="#">
								<img src="./images/page/temp.png"/>
							</a>
						</div>
						<div class="product_name">
							<a href="#">茼蒿</a>					
						</div>
						<div class="product_type">
							规格：约500g/份
						</div>
						<div class="product_price">
							￥28.00/份
						</div>
					</li>
				</ol>
			</div>
			<div class="floor_category_product floor_categore_product_1">
				1
			</div>
			<div class="floor_category_product floor_categore_product_2">
				2
			</div>
			<div class="floor_category_product floor_categore_product_3">
				3
			</div>
			<div class="floor_category_product floor_categore_product_4">
				4
			</div>
			<div class="floor_category_product floor_categore_product_5">
				5
			</div>
			<div class="floor_category_product floor_categore_product_6">
				6
			</div>
		</div>
		<div class="floor_ad">
			<div> <a href="#"> <img src="./images/page/ad03-01.png" /></a> </div>
			<div class="floor_ad_last">
				<a href="#">
					<img src="./images/page/ad03-02.png" />
				</a>
			</div>	
		</div>
	</div>
	</body>
</html>