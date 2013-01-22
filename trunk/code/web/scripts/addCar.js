
$(function(){
        $(".categoryPagecontantRight3 .bellowGoodsSmallInfo_img_buy").click(function(){
              //删除页面中已经有的 阴影
              $("#cart_shadow").remove();
              //创建阴影div
              var $shadow = $('<div id="cart_shadow" style="display: none; background-color: #bbb; border:1px solid #aaa;z-index: 9999;"> </div>').prependTo("body");        
              //获取当前点击的前一个img标签
              var $img = $(this).prev("img");
              //利用img标签的宽度，高度，上边距等属性来设置阴影div
              $shadow.css({
                        'width' : $img.css('width'),
                        'height': $img.css('height') ,
                        'position' : 'absolute',
                        'top' : $img.offset().top,
                        'left' : $img.offset().left,
                        'opacity' : 0.6                 
                     })
                     .show();

              //获取购物车标签
              var $cart = $("#cart");
              //利用购物车表情按的宽度，高度，上边距等属性来设置阴影div的动画效果
              $shadow.animate({ 
                          width: $cart.innerWidth(), 
                          height: $cart.innerHeight(), 
                          top: $cart.offset().top, 
                          left: $cart.offset().left,
                          opacity: 0
                     },  { duration: 600 , complete: function(){
                             $cart.append("<div>"+$img.attr('alt') +" 添加成功.</div>");
                         } 
                     })
            //阻止超链接跳转
            return false;
        });
    });