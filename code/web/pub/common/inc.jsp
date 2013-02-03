<% 	
	String basePath = request.getContextPath();
	String webURLContext = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ basePath;
	String GoodsCountSplitRegex = com.spark.front.utils.FrontConstant.GoodsCountSplitRegex;
	String GoodsSplitRegex = com.spark.front.utils.FrontConstant.GoodsSplitRegex;
	String ShopingCarGoodsCookieName = com.spark.front.utils.FrontConstant.ShopingCarGoodsCookieName;
	String ShopingCarVantagesGoodsCookieName = com.spark.front.utils.FrontConstant.ShopingCarVantagesGoodsCookieName;
	
	basePath = webURLContext;
%>
<script type="text/javascript">
  var mainWeb = "<%=basePath%>";
  var webPath = mainWeb;
  var basePath ="<%=basePath%>";
  var GoodsCountSplitRegex = "<%=GoodsCountSplitRegex%>";
  var GoodsSplitRegex = "<%=GoodsSplitRegex%>";
  var ShopingCarGoodsCookieName = "<%=ShopingCarGoodsCookieName%>";
  var ShopingCarVantagesGoodsCookieName = "<%=ShopingCarVantagesGoodsCookieName%>";
</script>
<script type="text/javascript">
try{
	if ((document.characterSet || document.charset).toLowerCase() == 'utf-8' && navigator.userAgent.indexOf("MSIE")>0){
		var locationStr = ""+window.location;
		if((locationStr.length -1) == (locationStr.lastIndexOf('#'))){
			locationStr = locationStr.substring(0,(locationStr.length -1));
		}
		window.location = locationStr;
	}
}catch (exp) {}
</script>