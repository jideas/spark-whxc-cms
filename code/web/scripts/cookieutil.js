var cookieutil = new cookieUtil();
function cookieUtil() {
	var expiredays = 10;
	var cookiePath = "/";

	this.setCookie = function(c_name, value, expiredays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + expiredays);
		cookieVal = c_name
				+ "="
				+ value
				+ ((expiredays == null) ? "" : ";expires="
						+ exdate.toGMTString()) + ";path=" + cookiePath;
		document.cookie = cookieVal;
	};
	this.clearCookie = function(c_name) {
		var cval = this.getCookie(c_name);
		if (cval) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + expiredays);
			cookieVal = c_name
					+ "="
					+ ""
					+ ((expiredays == null) ? "" : ";expires="
							+ exdate.toGMTString()) + ";path=" + cookiePath;
			document.cookie = cookieVal;
		}
	};

	this.getCookie = function(c_name) {
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=");
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				return document.cookie.substring(c_start, c_end);
			}
		}
	};

	/**
	 * 获得购物车(cookie)中的商品种数
	 * 
	 * @return {Number}
	 */
	this.getShoppingCarGoodsCount = function() {
		// 普通商品
		var count = 0;
		var goodsInfos = getAllGoodsInfoStr(this, ShopingCarGoodsCookieName);
		if (null != goodsInfos) {
			count += goodsInfos.length;
		}
		var vantegeGoodsInfos = getAllGoodsInfoStr(this,
				ShopingCarVantagesGoodsCookieName);
		if (null != vantegeGoodsInfos) {
			count += vantegeGoodsInfos.length;
		}
		return count;
	};
	
	/**
	 * 设置cookie中的商品为指定的商品
	 * @param {} data
	 * 		data为数组，且数组里对象必须包含
	 * 			goodsid：商品ID
	 * 			count：商品数量
	 * 			vantagesGoods: 是否积分商品
	 */
	this.setShoppingCarGoods = function(data) {
		this.clearShopingCarCookie(ShopingCarGoodsCookieName);
		this.clearShopingCarCookie(ShopingCarVantagesGoodsCookieName);
		for (var index = 0; index < data.length; index++) {
			var goodsInfo = {
				id: data[index].goodsId,
				count: data[index].count
			};
			if (data[index].vantagesGoods) {
				insertGoods(this, goodsInfo, ShopingCarVantagesGoodsCookieName);
			} else {
				insertGoods(this, goodsInfo, ShopingCarGoodsCookieName);
			}
		}
	};
	
	/** ******* vanteage goods cookie methods ********** */

	/**
	 * 添加积分商城商品到购物车
	 * 
	 * @param {}
	 *            targetGoodsId
	 * @param {}
	 *            goodsCount
	 */
	this.addVantegeGoodsToShoppingCar = function(targetGoodsId, goodsCount) {
		var goodsInfo = {
			id : targetGoodsId,
			count : Number(goodsCount)
		};
		if (isGoodsExist(this, targetGoodsId, ShopingCarVantagesGoodsCookieName)) {
			updateGoods(this, goodsInfo, 0, ShopingCarVantagesGoodsCookieName);
		} else {
			insertGoods(this, goodsInfo, ShopingCarVantagesGoodsCookieName);
		}
	};

	/**
	 * 从购物车中(cookie)中移出指定的积分商城商品信息
	 * 
	 * @param {}
	 *            targetGoodsId
	 */
	this.removeVantegeGoodsFromShoppingCar = function(targetGoodsId) {
		deleteGoods(this, targetGoodsId, ShopingCarVantagesGoodsCookieName);
	};

	/** ******* goods cookie methods ********** */

	/**
	 * 向购物车(cookie)中添加商品
	 * 
	 * @param {}
	 *            targetGoodsId 商品ID
	 * @param {}
	 *            goodsCount 添加数量
	 */
	this.addGoodsToShoppingCar = function(targetGoodsId, goodsCount) {
		var goodsInfo = {
			id : targetGoodsId,
			count : Number(goodsCount)
		};
		if (isGoodsExist(this, targetGoodsId, ShopingCarGoodsCookieName)) {
			updateGoods(this, goodsInfo, 0, ShopingCarGoodsCookieName);
		} else {
			insertGoods(this, goodsInfo, ShopingCarGoodsCookieName);
		}
	};

	/**
	 * 从购物车中(cookie)中移出指定的商品信息
	 * 
	 * @param {}
	 *            targetGoodsId 商品ID
	 * @return {}
	 */
	this.removeGoodsFromShoppingCar = function(targetGoodsId) {
		deleteGoods(this, targetGoodsId, ShopingCarGoodsCookieName);
	};

	/**
	 * 清除cookie中的所有商品信息
	 */
	this.clearShopingCarCookie = function(cookieName) {
		this.clearCookie(cookieName);
	};

	/**
	 * 新增一条商品到cookie
	 * 
	 * @param {}
	 *            goodsInfo
	 */
	var insertGoods = function(utilInstance, goodsInfo, cookieName) {
		if (isGoodsExist(utilInstance, goodsInfo.id, cookieName))
			return;
		var shpCarContent = utilInstance.getCookie(cookieName);
		if (shpCarContent == null || shpCarContent == ""
				|| shpCarContent == 'undefined') {
			shpCarContent = goodsInfo.id + GoodsCountSplitRegex
					+ goodsInfo.count;
		} else {
			shpCarContent = shpCarContent + GoodsSplitRegex + goodsInfo.id
					+ GoodsCountSplitRegex + goodsInfo.count;
		}
		utilInstance.clearShopingCarCookie(cookieName);
		utilInstance.setCookie(cookieName, shpCarContent, expiredays);
	};

	/**
	 * 更新指定的商品信息
	 * 
	 * @param {}
	 *            goodsInfo
	 * @param {}
	 *            countDealWay 整数 0累加，1累减，2替换
	 */
	var updateGoods = function(utilInstance, goodsInfo, countDealWay,
			cookieName) {
		if (!isGoodsExist(utilInstance, goodsInfo.id, cookieName))
			return;
		var goodsInfos = getAllGoodsInfoStr(utilInstance, cookieName);
		if (null == goodsInfos)
			return null;
		var newGoodsCookieStr = "";
		for (var index = 0; index < goodsInfos.length; index++) {
			if (0 != index) {
				newGoodsCookieStr += GoodsSplitRegex;
			}
			if (goodsInfos[index].split(GoodsCountSplitRegex)[0] == goodsInfo.id) {
				var count = Number(goodsInfos[index]
						.split(GoodsCountSplitRegex)[1]);
				if (countDealWay == 0) {
					count += goodsInfo.count;
				} else if (countDealWay == 1) {
					count -= goodsInfo.count;
				} else if (countDealWay == 2) {
					count = goodsInfo.count;
				}
				newGoodsCookieStr += goodsInfos[index]
						.split(GoodsCountSplitRegex)[0]
						+ GoodsCountSplitRegex + count;
			} else {
				newGoodsCookieStr += goodsInfos[index];
			}
		}
		utilInstance.clearShopingCarCookie(cookieName);
		utilInstance.setCookie(cookieName, newGoodsCookieStr, expiredays);
	};
	/**
	 * 从cookie中删除指定的商品
	 * 
	 * @param {}
	 *            targetGoodsId
	 */
	var deleteGoods = function(utilInstance, targetGoodsId, cookieName) {
		if (!isGoodsExist(utilInstance, targetGoodsId, cookieName))
			return;
		var goodsInfos = getAllGoodsInfoStr(utilInstance, cookieName);
		if (null == goodsInfos)
			return null;
		var newGoodsCookieStr = "";
		for (var index = 0; index < goodsInfos.length; index++) {
			if (goodsInfos[index].split(GoodsCountSplitRegex)[0] == targetGoodsId
				|| "" == goodsInfos[index]) {
				continue;
			}
			if (0 != index) {
				newGoodsCookieStr += GoodsSplitRegex;
			}
			newGoodsCookieStr += goodsInfos[index];
		}
		utilInstance.clearShopingCarCookie(cookieName);
		utilInstance.setCookie(cookieName, newGoodsCookieStr, expiredays);
	};

	/**
	 * 判断指定商品在cookie中是否存在
	 * 
	 * @param {}
	 *            targetGoodsId
	 * @return {Boolean}
	 */
	var isGoodsExist = function(utilInstance, targetGoodsId, cookieName) {
		var shpCarContent = utilInstance.getCookie(cookieName);
		if (shpCarContent == null || "" == shpCarContent)
			return false;
		return !(shpCarContent.indexOf(targetGoodsId + GoodsCountSplitRegex) == -1);
	};

	/**
	 * 取得cookie中指定商品的信息
	 * 
	 * @param {}
	 *            targetGoodsId
	 * @return {id:goodsId, count: goodsCount}
	 */
	/*
	 * var getGoodsInfoById = function(utilInstance, targetGoodsId) { if
	 * (!isGoodsExist(targetGoodsId)) return null; var goodsInfos =
	 * getAllGoodsInfoStr(utilInstance); if (null == goodsInfos) return null;
	 * for (var gIndex = 0; gIndex < goodsInfos.length; gIndex++) { var goodsId =
	 * goodsInfos[gIndex].split(GoodsCountSplitRegex)[0]; if (goodsId ==
	 * targetGoodsId) { return {id: goodsId, count:
	 * Number(goodsInfos[gIndex].split(GoodsCountSplitRegex)[1])}; } } return
	 * null; };
	 */
	/**
	 * 获得cookie中所有的商品信息
	 * 
	 * @return {}字符串数组
	 */
	var getAllGoodsInfoStr = function(utilInstance, cookieName) {
		var shpCarContent = utilInstance.getCookie(cookieName);
		if (null == shpCarContent || "" == shpCarContent)
			return null;
		var goodsInfos = shpCarContent.split(GoodsSplitRegex);
		if (null == goodsInfos || goodsInfos.length < 1)
			return null;
		return goodsInfos;
	};
}