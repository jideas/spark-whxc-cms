var goTopMenu = new GoTopMenu();
function GoTopMenu(){
    this.active = function(dom){
    	 var obj= document.getElementById(dom);
         function getScrollTop(){
                 return document.body.scrollTop+document.documentElement.scrollTop;
         }
         function setScrollTop(value){
                 document.documentElement.scrollTop=value;
                 document.body.scrollTop = value;
         }    
         window.onscroll=function(){
        	 getScrollTop()>0?obj.style.display ="block":obj.style.display ="none";
        	 obj.style.bottom = - getScrollTop();
         }
        
         obj.onclick=function(){        	
        	/*var goTop=setInterval(scrollMove,1);
            function scrollMove(){
                   setScrollTop(getScrollTop() - 50);
                   if(getScrollTop()<1)clearInterval(goTop);
            }
            */
            document.documentElement.scrollTop=0;
            document.body.scrollTop = 0;
         }  
	};
	
}
