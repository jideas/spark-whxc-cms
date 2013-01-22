<% 	
	String basePath = request.getContextPath();
	String webURLContext = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ basePath;
	String GoodsCountSplitRegex = com.spark.front.utils.FrontConstant.GoodsCountSplitRegex;
	String GoodsSplitRegex = com.spark.front.utils.FrontConstant.GoodsSplitRegex;
	String ShopingCarGoodsCookieName = com.spark.front.utils.FrontConstant.ShopingCarGoodsCookieName;
	String ShopingCarVantagesGoodsCookieName = com.spark.front.utils.FrontConstant.ShopingCarVantagesGoodsCookieName;
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