
$(function(){
        $(".categoryPagecontantRight3 .bellowGoodsSmallInfo_img_buy").click(function(){
              //ɾ��ҳ�����Ѿ��е� ��Ӱ
              $("#cart_shadow").remove();
              //������Ӱdiv
              var $shadow = $('<div id="cart_shadow" style="display: none; background-color: #bbb; border:1px solid #aaa;z-index: 9999;"> </div>').prependTo("body");        
              //��ȡ��ǰ�����ǰһ��img��ǩ
              var $img = $(this).prev("img");
              //����img��ǩ�Ŀ�ȣ��߶ȣ��ϱ߾��������������Ӱdiv
              $shadow.css({
                        'width' : $img.css('width'),
                        'height': $img.css('height') ,
                        'position' : 'absolute',
                        'top' : $img.offset().top,
                        'left' : $img.offset().left,
                        'opacity' : 0.6                 
                     })
                     .show();

              //��ȡ���ﳵ��ǩ
              var $cart = $("#cart");
              //���ù��ﳵ���鰴�Ŀ�ȣ��߶ȣ��ϱ߾��������������Ӱdiv�Ķ���Ч��
              $shadow.animate({ 
                          width: $cart.innerWidth(), 
                          height: $cart.innerHeight(), 
                          top: $cart.offset().top, 
                          left: $cart.offset().left,
                          opacity: 0
                     },  { duration: 600 , complete: function(){
                             $cart.append("<div>"+$img.attr('alt') +" ��ӳɹ�.</div>");
                         } 
                     })
            //��ֹ��������ת
            return false;
        });
    });