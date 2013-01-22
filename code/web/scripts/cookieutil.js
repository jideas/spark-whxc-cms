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
	 * ��ù��ﳵ(cookie)�е���Ʒ����
	 * 
	 * @return {Number}
	 */
	this.getShoppingCarGoodsCount = function() {
		// ��ͨ��Ʒ
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
	 * ����cookie�е���ƷΪָ������Ʒ
	 * @param {} data
	 * 		dataΪ���飬�����������������
	 * 			goodsid����ƷID
	 * 			count����Ʒ����
	 * 			vantagesGoods: �Ƿ������Ʒ
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
	 * ��ӻ����̳���Ʒ�����ﳵ
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
	 * �ӹ��ﳵ��(cookie)���Ƴ�ָ���Ļ����̳���Ʒ��Ϣ
	 * 
	 * @param {}
	 *            targetGoodsId
	 */
	this.removeVantegeGoodsFromShoppingCar = function(targetGoodsId) {
		deleteGoods(this, targetGoodsId, ShopingCarVantagesGoodsCookieName);
	};

	/** ******* goods cookie methods ********** */

	/**
	 * ���ﳵ(cookie)�������Ʒ
	 * 
	 * @param {}
	 *            targetGoodsId ��ƷID
	 * @param {}
	 *            goodsCount �������
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
	 * �ӹ��ﳵ��(cookie)���Ƴ�ָ������Ʒ��Ϣ
	 * 
	 * @param {}
	 *            targetGoodsId ��ƷID
	 * @return {}
	 */
	this.removeGoodsFromShoppingCar = function(targetGoodsId) {
		deleteGoods(this, targetGoodsId, ShopingCarGoodsCookieName);
	};

	/**
	 * ���cookie�е�������Ʒ��Ϣ
	 */
	this.clearShopingCarCookie = function(cookieName) {
		this.clearCookie(cookieName);
	};

	/**
	 * ����һ����Ʒ��cookie
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
	 * ����ָ������Ʒ��Ϣ
	 * 
	 * @param {}
	 *            goodsInfo
	 * @param {}
	 *            countDealWay ���� 0�ۼӣ�1�ۼ���2�滻
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
	 * ��cookie��ɾ��ָ������Ʒ
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
	 * �ж�ָ����Ʒ��cookie���Ƿ����
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
	 * ȡ��cookie��ָ����Ʒ����Ϣ
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
	 * ���cookie�����е���Ʒ��Ϣ
	 * 
	 * @return {}�ַ�������
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