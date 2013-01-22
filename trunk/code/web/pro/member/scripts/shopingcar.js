var shopingCar = new ShopingCar();
function getGoodsArray() {
	var goodsArray = "[";
	var vgoodsArray = "[";
	var i = 0;
	var j = 0;
	$("input[type='checkbox']").each(function() {
				if (this.checked && this.id != "toggle-checkboxes") {
					if ("true" == $(this).attr("vantagesGoods")) {
						if (0 == j) {
							vgoodsArray += "{";
						} else {
							vgoodsArray += ",{";
						}
						vgoodsArray += "goodsId:" + "'" + this.value.split("_")[0] + "'";
						var countId = "#" + this.value + "_quantity";
						var count = $(countId).val();
						vgoodsArray += ",count:" + "'" + count + "'";
						vgoodsArray += "}";
						j++;
					} else {
						if (0 == i) {
							goodsArray += "{";
						} else {
							goodsArray += ",{";
						}
						goodsArray += "goodsId:" + "'" + this.value.split("_")[0] + "'";
						var countId = "#" + this.value + "_quantity";
						var count = $(countId).val();
						goodsArray += ",count:" + "'" + count + "'";
						goodsArray += "}";
						i++;
					}
				}
			});
	goodsArray += "]";
	vgoodsArray += "]";
	this.getGA = function() {
		return goodsArray;
	}
	this.getVGA = function() {
		return vgoodsArray;
	}
}
function ShopingCar() {
	this.toOrder = function() {
		var g = new getGoodsArray();
		var goodsArray = g.getGA();
		var vgoodsArray = g.getVGA();
		if (goodsArray.length <= 2 && vgoodsArray.length <= 2) {
			new cmsAlertError("", "请至少选择一件商品！");
			return;
		}

		var url = mainWeb + "/front/shopingCar/toOrder?goodsArray="
				+ goodsArray + "&vgoodsArray=" + vgoodsArray;
		location.replace(url);
		event.returnValue = false;

	}

	this.toHome = function() {
		window.location.href = mainWeb;
	}

	this.addGoods = function(inputId, count) {
		if (!inputId || !count) {
			return;
		}
		var id = "#" + inputId;
		var oldValue = $(id).val();
		var newValue = Number(oldValue) + Number(count);
		if (newValue < 0) {
			newValue = 0;
		}
		$(id).val(newValue);
		this.changeTotal();
	}

	this.subGoods = function(inputId, count) {

		if (!inputId || !count) {
			return;
		}
		var id = "#" + inputId;
		var oldValue = $(id).val();
		var newValue = Number(oldValue) - Number(count);
		if (newValue <= 0) {
			newValue = 1;
		}
		$(id).val(newValue);
		this.changeTotal();
	}

	this.refreshCar = function() {
		window.location.href = mainWeb + "/front/shopingCar/getGoods";
	}

	this.headCheck = function() {
		if ($("#toggle-checkboxes").attr("checked")) {
			$("input[type='checkbox']").each(function() {
						if (!this.checked) {
							this.checked = true;
						}
					});

		} else {
			$("input[type='checkbox']").each(function() {
						if (this.checked) {
							this.checked = false;
						}
					});
		}
		this.changeTotal();

	}

	this.rowCheck = function() {
		var totalGoodsCount = 0;
		var totalGoodsAmount = 0;
		var checkBoxCount = 0;
		$("input[type='checkbox']").each(function() {
					if (this.id != "toggle-checkboxes") {
						checkBoxCount += 1;
					}
					if (this.checked && this.id != "toggle-checkboxes") {
						totalGoodsCount += 1;
						// var priceId = "#" + this.value + "_price"
						// var price = $(priceId).attr("value");
						// var countId = "#" + this.value + "_quantity";
						// var count = $(countId).val();
						// totalGoodsAmount = Number(totalGoodsAmount)
						// + (Number(price) * Number(count));
					}
				});
		this.changeTotal();
		// $("#total_GoodsCount").text(totalGoodsCount);
		// $("#total_GoodsAmount").text(totalGoodsAmount.toFixed(2));
		// $("#shopingcar-totalamount-right").text("￥"
		// + totalGoodsAmount.toFixed(2));
		if (0 == totalGoodsCount) {
			if ($("#toggle-checkboxes").attr("checked")) {
				$("#toggle-checkboxes").attr("checked", false);
			}
		}
		if (totalGoodsCount == checkBoxCount) {
			if (!$("#toggle-checkboxes").attr("checked")) {
				$("#toggle-checkboxes").attr("checked", true);
			}
		}
	}

	this.changeTotal = function() {
		var totalGoodsCount = 0;
		var totalGoodsAmount = 0;
		var totalGoodsVantages = 0;
		$("input[type='checkbox']").each(function() {
			if (this.checked && this.id != "toggle-checkboxes") {
				totalGoodsCount += 1;
				var priceId = "#" + this.value + "_price"
				var price = $(priceId).attr("value");
				countId = "#" + this.value + "_quantity";
				var count = $(countId).val();
				var vantagesId = "#" + this.value + "_vantages";
				var vantages = $(vantagesId).attr("value");
				totalGoodsAmount = Number(totalGoodsAmount)
						+ (Number(price) * Number(count));
				totalGoodsVantages = Number(totalGoodsVantages)
						+ (Number(vantages) * Number(count));
			}
		});
		$("#total_GoodsCount").text(totalGoodsCount);
		$("#total_GoodsAmount").text(totalGoodsAmount.toFixed(2));
		$("#total_GoodsVantages").text(totalGoodsVantages.toFixed(0));
		$("#shopingcar-totalamount-right").text("￥"
				+ totalGoodsAmount.toFixed(2));
		if (0 == totalGoodsCount) {
			if ($("#toggle-checkboxes").attr("checked")) {
				$("#toggle-checkboxes").attr("checked", false);
			}
		}
	}
	this.deleteGoods = function(goodsId) {
		if (!goodsId) {
			return;
		}

		var cc = new CmsConfirm("提示", "确定要从购物车中删除吗?");
		cc.setActionListener(function(result) {
					if (result) {
						var id = "#" + goodsId + "_row";
						$(id).remove();
						shopingCar.changeTotal();
						shopingCar.updateCookie()
						// setTimeout(shopingCar.updateCookie(), 50);
					}
				});

	}

	this.deleteSelectedGoods = function() {
		var hasSelectedRow = false;
		$("input[type='checkbox']").each(function() {
					if (this.checked) {
						hasSelectedRow = true;
					}
				});
		if (!hasSelectedRow) {
			new cmsAlertAtt("提示", "请选择要删除的商品。");
			return;
		}

		var cc = new CmsConfirm("提示", "确定要从购物车中删除吗?");
		cc.setActionListener(function(result) {
					if (result) {
						$("input[type='checkbox']").each(function() {
									if (this.checked) {
										var goodsId = this.value;
										if (goodsId
												&& "" != "toggle-checkboxes") {
											var id = "#" + goodsId + "_row";
											$(id).remove();
										}
									}
								});
						shopingCar.changeTotal(), 50;
						shopingCar.updateCookie()
						// setTimeout(shopingCar.updateCookie(), 50);
					}
				});

	}

	this.updateCookie = function() {
		cookieutil.clearShopingCarCookie(ShopingCarGoodsCookieName);
		cookieutil.clearShopingCarCookie(ShopingCarVantagesGoodsCookieName);
		var totalCount = 0;
		$("._quantity_input").each(function() {
					var goodsId = this.id.split("_")[0];
					var count = this.value;
					var vantagesGoods = $(this).attr("vantagesGoods");
					if ("true" == vantagesGoods) {
						cookieutil.addVantegeGoodsToShoppingCar(goodsId, count);
					} else {
						cookieutil.addGoodsToShoppingCar(goodsId, count);
					}
					totalCount += 1;
				});
		if (0 == totalCount) {
			this.refreshCar();
		}

	}

	this.countChangeListener = function(input) {
		var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
		if ($(input).val().match(reg) == null) {
			$(input).val($(input).attr("preValue"));
		} else {
			$(input).attr("preValue", $(input).val());
			this.changeTotal();
		}
	}

	this.continueMouseOver = function(img) {
		img.src = basePath + "/images/page/shopcart-icon02-hover.png";
	}

	this.continueMouseOut = function(img) {
		img.src = basePath + "/images/page/shopcart-icon02-normal.png";
	}

	this.addHotGoods = function(button) {
		var goodsId = $(button).attr("recid");
		var count = 1;
		if (goodsId && "null" != goodsId) {
			cookieutil.addGoodsToShoppingCar(goodsId, count);
			setTimeout(this.refreshCar(), 50);
		}
	}

	// this.openGoodsInfo = function(goodsId) {
	// location = mainWeb + "/front/toGoodsInfoPage?id=" + goodsId;
	// }

}
