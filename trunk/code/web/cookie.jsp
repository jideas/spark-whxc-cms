<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/pub/common/inc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script type="text/javascript" src="scripts/cookieutil.js"></script>
<script language="javascript">
function writeMyCookie(c_name,expiredays) { 
	var value = document.getElementById("011DE2AD6FB84C19A529DD71641A1834").value;	
	cookieutil.addGoodsToShoppingCar("011DE2AD6FB84C19A529DD71641A1834",value);
}

function writeMyCookie1(c_name,expiredays) {
	var value = document.getElementById("10E139F1E7E8F91FA3BDBF2C1D515EBC").value;	
	cookieutil.addVantegeGoodsToShoppingCar("10E139F1E7E8F91FA3BDBF2C1D515EBC",value);
}	

function readCookie(c_name){
	alert(cookieutil.getCookie(c_name));
}

function addGoods() {
	cookieutil.addGoodsToShoppingCar('011DE2AD6FB84C19A529DD71641A1834', '1')
}
function removeGoods() {
	cookieutil.removeGoodsFromShoppingCar('011DE2AD6FB84C19A529DD71641A1834')
}
function getGoods() {
	
}
</script>
</head>
<body>
	<input type="text" value="16" id="011DE2AD6FB84C19A529DD71641A1834"/>
	<input type="button" value="写" onclick="writeMyCookie('spark_scar_g',1)"/>
	<input type="button" value="读" onclick="readCookie('spark_scar_g')"/>
	<input type="button" value="清" onclick="cookieutil.clearShopingCarCookie('spark_scar_g')"/>
	<br/>
	<input type="text" value="88" id="10E139F1E7E8F91FA3BDBF2C1D515EBC"/>
	<input type="button" value="写" onclick="writeMyCookie1('spark_scar_v_g',1)"/>
	<input type="button" value="读" onclick="readCookie('spark_scar_v_g')"/>
	<input type="button" value="清" onclick="cookieutil.clearShopingCarCookie('spark_scar_v_g')"/>
	<br/>
	<input type='button' onclick='addGoods()' value='增加商品到Cookie' />
	<input type='button' onclick='removeGoods()' value='从Cookie中移除商品' />
</body>
</html>
