<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/scripts/jslider/lib/jquery.ad-gallery.css">
  <script type="text/javascript" src="<%=basePath%>/scripts/jslider/lib/jquery.ad-gallery.js"></script>
  <title></title>
</head>
<body>
    <div id="gallery" class="ad-gallery">
      <div class="ad-image-wrapper"></div>
      <div class="ad-nav" style="margin-top:-5px;">
        <div class="ad-thumbs">
          <ul class="ad-thumb-list">
           <li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/013.jpg">
	               	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/013_thumb.jpg" class="image010">              
				</a>
			</li>
           <li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/010.jpg">
	               	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/010_thumb.jpg" class="image010">              
				</a>
			</li>
	          <li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/011.jpg">
	               	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/011_thumb.jpg" class="image11">              
				</a>
			</li>
			<li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/012.jpg">
	               	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/012_thumb.jpg" class="image012">              
				</a>
			</li>
           <li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/02.jpg">
                	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/02_thumb.jpg" class="image1">              
				</a>            
			</li>
			<li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/03.jpg">
                	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/03_thumb.jpg" class="image2">              
				</a>            
			</li>
			<li>
	            <a href="<%=basePath%>/scripts/jslider/slider-images/04.jpg">
                	<img src="<%=basePath%>/scripts/jslider/slider-images/thumbs/04_thumb.jpg" class="image3">            
				</a>            
			</li>
          </ul>
        </div>
      </div>
    </div>
<script type="text/javascript" defer="defer">
	$('.ad-gallery').adGallery({
		width: 730,
		height: 400,
		thumb_opacity: 0.7,
	  	start_at_index: 0,
	  	update_window_hash: false, 
	  	animate_first_image: false, 
	  	animation_speed: 500, 
	  	display_next_and_prev: true, 
	  	display_back_and_forward: false, 
	  	scroll_jump: 0, 
	  	slideshow: {
			enable: true,
			autostart: true,
			speed: 5000,
			start_label: '',
			stop_label: '',
			stop_on_scroll: false, 
			countdown_prefix: '(', 
			countdown_sufix: ')',
			onStart: function() {
			  
			},
			onStop: function() {
		 		
			}
	  	},
	  	effect: 'slide-hori', 
	 	enable_keyboard_move: true, 
	  	cycle: true,
	  	hooks: {
			displayDescription: function(image) {
		  
			}
	  	},
	  	callbacks: {
			init: function() {
		  		this.preloadAll();
		  		this.preloadImage(0);
		  		this.preloadImage(1);
		  		this.preloadImage(2);
			},
			afterImageVisible: function() {
		 		var context = this;
		  		this.loading(true);
		  		this.preloadImage(this.current_index + 1,
				function() {
			  		context.loading(false);
				}
		  	);
		  	if(this.current_index % 4 == 0) {
				this.settings.effect = 'slide-hori';
		 	}else if(this.current_index % 4 == 1){
				this.settings.effect = 'slide-vert';
		 	}else if(this.current_index % 4 == 2){
				this.settings.effect = 'fade';
		 	}else if(this.current_index % 4 == 3){
				this.settings.effect = 'resize';
		 	}else{
				this.settings.effect = 'fade';
			}
		},
		beforeImageVisible: function(new_image, old_image) {
		}
	  }
	});
  </script>    
</body>
</html>