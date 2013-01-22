var floor = new Floor();

function Floor(){
	this.active = function(){
		/**
		 * 主楼添加mouserOver事件 
		 */
		$(".commonFloor .floor_product .floor_category").each(function(){
			 var _this = this;
			 $(_this).find("UL LI:gt(1)").each(function(){
				 $(this).bind("mouseover",function(){
						$(_this).find(".floor_third_category_hover").removeClass("floor_third_category_hover");
						$(this).addClass("floor_third_category_hover");
						var id = "#product_"+$(this).attr("id");
						$(id).show().siblings().hide();
				});
			 });
			 $(_this).find("UL LI:eq(2)").mouseover();
		});
		
		$(function(){
			var current = $("#mainFloorTitleDiv ul li:first");
			current.addClass("current");
			var id=current.attr("id");
			var currentContain = $("#mainFloorContain #"+id);
			currentContain.show();
			
			$.each($("#mainFloorTitleDiv ul li"),function(){
				 var _this = $(this);
				 var _id =  $(this).attr("id");
				 var _thisContain =  $("#mainFloorContain #"+_id);
				
				 _this.bind("mouseover",function(){
					if(current==_this)return
					current.removeClass("current");
					currentContain.hide();
					
				 	current  = _this;
				 	currentContain = _thisContain;
					_this.addClass("current");
					_thisContain.show();
					
				}).bind("mouseout",function(){
					if(current==_this)return;
					_this.removeClass("current");
					_thisContain.hide();
				});
			});
		});
	};
}