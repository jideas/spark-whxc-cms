//初始化
function FloorAction(){		
	this.sum_width = 730;
	this.li_count;
	this.li_width;
	this.li_lost;
	this.floorId;
	//设置参数
	this.setArgs = function(floorId,floorImg,floorColor){
		this.floorId = floorId;
		this.init(this.floorId,floorImg,floorColor);
		this.bind_mouseover(this.floorId);
		this.show(this.floorId);
	}
	
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

	//绑定事件
	this.bind_mouseover = function(){
		$(".")
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
}