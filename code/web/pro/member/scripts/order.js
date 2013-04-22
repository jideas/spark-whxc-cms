var order = new Order();
function Order() {
	this.toShopingCar = function() {
		window.location.href = mainWeb + "/front/shopingCar/getGoods";
	}
	this.toHome = function() {
		window.location.href = mainWeb;
	}
	var areaObject;
	this.Edit_Consignee = function() {
		var html = "";
		html += "<div class='o_show'>";
		html += "<h1>";
		html += "收货人信息&nbsp;";
		html += "<a id='lbtnConsigneeWrite' href='javascript:void(0)'";
		html += " onclick='order.Edit_Consignee_Close(this)'>[关闭]</a>";
		html += "</h1>";
		html += "<div class='middle'>";
		html += this.getAddressHtml();
		html += this.getConsigneeTableHtml();

		html += "<div>";
		html += "<a href='javascript:void(0)' onclick='order.addAddress()'>[添加到常用地址]</a>";
		html += "</div>";
		html += "<div id='consignee_save' onselectstart=\"return false\" onclick='order.saveConsignee()' style='color:#ffffff;background:#FFA54F;width:100px;cursor: pointer;margin-bottom:5px;'>";
		html += "<b>保存收货人信息</b>";
		html += "</div>";
		html += "</div>";
		html += "</div>";

		$("#part_consignee").html(html.toString());

		$("#part_consignee").css("background", "#FAFAFA");
		areaObject = new AreaProvider("select_provence", "select_city",
				"select_town");
		areaObject.addSelectionListener(function() {
					order.loadStation(areaObject.getSelectedDistrictCode());
				});
		areaObject.setSelection($("#province").attr("province"), $("#province")
						.attr("city"), $("#province").attr("town"));

	}
	this.saveConsignee = function() {
		if (!this.validationAddress()) {
			return;
		}
		var html = "";
		html += "<div class='o_show'>";
		html += "<h1>";
		html += "收货人信息&nbsp;";
		html += "<a id='lbtnConsigneeWrite' href='javascript:void(0)'";
		html += "clstag='checkout|keycount|jiesuan|lconsigneewrite'";
		html += "onclick='order.Edit_Consignee(this)'>[修改]</a>";
		html += "</h1>";
		html += "<div class='middle'>";

		html += "<table>";
		html += "<tr>";
		html += "<td style='width: 85px;' align='right'>";
		html += "收货人姓名：";
		html += "</td>";
		html += "<td id='consignee' prevalue='" + $("#consignee").val() + "'>";
		html += $("#consignee").val();
		html += "</td>";
		html += "<td><div id='valuewarning_consignee' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "地&nbsp;&nbsp;&nbsp;&nbsp;区：";
		html += "</td>";
		var provinceValue = areaObject.getSelectedProvinceTitle()
				+ areaObject.getSelectedCityTitle()
				+ areaObject.getSelectedDistrictTitle();
		var addressId = this.getSelectedAddressId();
		html += "<td id='province' addressId='" + addressId + "' prevalue='"
				+ provinceValue + "' province='"
				+ areaObject.getSelectedProvinceCode() + "' city='"
				+ areaObject.getSelectedCityCode() + "' town='"
				+ areaObject.getSelectedDistrictCode() + "'>";
		html += provinceValue;
		html += "</td>";
		html += "<td><div id='valuewarning_province' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr style='display: '>";
		html += "<td style='text-align: right;'>";
		html += "站&nbsp;&nbsp;&nbsp;&nbsp;点：";
		html += "</td>";
		html += "<td id='station' value='"
				+ $("#station_selector").children('option:selected').text()
				+ "' recid='" + $("#station_selector").val() + "'>";
		html += $("#station_selector").children('option:selected').text();

		html += "</td>";
		html += "<td><div id='valuewarning_station' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "地&nbsp;&nbsp;&nbsp;&nbsp;址：";
		html += "</td>";
		html += "<td id='address' prevalue='" + $("#address").val() + "'>";
		html += $("#address").val();
		html += "</td>";
		html += "<td><div id='valuewarning_address' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "手机号码：";
		html += "</td>";
		html += "<td id='mobile' prevalue='" + $("#mobile").val() + "'>";
		html += $("#mobile").val();
		html += "</td>";
		html += "<td><div id='valuewarning_mobile' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "固定电话：";
		html += "</td>";
		html += "<td id='phone' prevalue='" + $("#phone").val() + "'>";
		html += $("#phone").val();
		html += "</td>";
		html += "</tr>";

		html += "<tr>";
		html += "<td align='right'>";
		html += "邮 编：";
		html += "</td>";
		html += "<td id='postCode' prevalue='" + $("#postCode").val() + "'>";
		html += $("#postCode").val();
		html += "</td>";
		html += "</tr>";
		html += "</table>";

		html += "</div>";
		html += "</div>";

		$("#part_consignee").html(html.toString());
		$("#part_consignee").css("background-color", "#ffffff");
	}

	this.getSelectedAddressId = function() {
		var addressId;
		$("input:radio[name='addressradio']").each(function() {
					if (this.checked) {
						addressId = $(this).attr("addressId");
					}
				});
		return addressId;
	}
	this.Edit_Consignee_Close = function() {
		var html = "";
		html += "<div class='o_show'>";
		html += "<h1>";
		html += "收货人信息&nbsp;";
		html += "<a id='lbtnConsigneeWrite' href='javascript:void(0)'";
		html += "clstag='checkout|keycount|jiesuan|lconsigneewrite'";
		html += "onclick='order.Edit_Consignee(this)'>[修改]</a>";
		html += "</h1>";
		html += "<div class='middle'>";

		html += "<table>";
		html += "<tr>";
		html += "<td style='width: 85px;' align='right'>";
		html += "收货人姓名：";
		html += "</td>";
		html += "<td id='consignee' prevalue='"
				+ $("#consignee").attr("prevalue") + "'>";

		html += $("#consignee").attr("prevalue");
		html += "</td>";
		html += "<td><div id='valuewarning_consignee' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "地&nbsp;&nbsp;&nbsp;&nbsp;区：";
		html += "</td>";
		html += "<td id='province' addressId='"
				+ $("#province").attr("addressId") + "' prevalue='"
				+ $("#province").attr("prevalue") + "' province='"
				+ $("#province").attr("province") + "' city='"
				+ $("#province").attr("city") + "' town='"
				+ $("#province").attr("town") + "'>";
		html += $("#province").attr("prevalue");
		html += "</td>";
		html += "<td><div id='valuewarning_province' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr style='display: '>";
		html += "<td style='text-align: right;'>";
		html += "站&nbsp;&nbsp;&nbsp;&nbsp;点：";
		html += "</td>";
		html += "<td id='station' value='" + $("#station").attr("value")
				+ "' recid='" + $("#station").attr("recid") + "'>";
		html += $("#station").attr("value");
		html += "</td>";
		html += "<td><div id='valuewarning_station' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "地&nbsp;&nbsp;&nbsp;&nbsp;址：";
		html += "</td>";
		html += "<td id='address' prevalue='" + $("#address").attr("prevalue")
				+ "'>";
		html += $("#address").attr("prevalue");
		html += "</td>";
		html += "<td><div id='valuewarning_address' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "手机号码：";
		html += "</td>";
		html += "<td id='mobile' prevalue='" + $("#mobile").attr("prevalue")
				+ "'>";
		html += $("#mobile").attr("prevalue");
		html += "</td>";
		html += "<td><div id='valuewarning_mobile' class='valuewarning'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "固定电话：";
		html += "</td>";
		html += "<td id='phone' prevalue='" + $("#phone").attr("prevalue")
				+ "'>";
		html += $("#phone").attr("prevalue");
		html += "</td>";
		html += "</tr>";

		html += "<tr>";
		html += "<td align='right'>";
		html += "邮&nbsp;&nbsp;&nbsp;&nbsp;编：";
		html += "</td>";
		html += "<td id='postCode' prevalue='"
				+ $("#postCode").attr("prevalue") + "'>";
		html += $("#postCode").attr("prevalue");
		html += "</td>";
		html += "</tr>";
		html += "</table>";

		html += "</div>";
		html += "</div>";

		$("#part_consignee").html(html.toString());
		$("#part_consignee").css("background-color", "#ffffff");
	}

	var timeSelector;
	var btimeSelector;
	this.Edit_PayType = function() {
		var html = "";
		html += "<div class='o_show'>";
		html += "<h1>";
		html += "支付及配送方式&nbsp;";
		html += "<a id='linkPayTypeShipType' href='javascript:void(0)'";
		html += "clstag='checkout|keycount|jiesuan|linkpayshiptype'";
		html += "onclick='order.Edit_PayType_Close();' style='display: '>[关闭]</a>";
		html += "</h1>";
		html += "<div id='updateInfo_payType'></div>";
		html += "<div class='middle'>";
		html += this.getPaytypeHtml();
		var timeCode;
		if ($("#deliverTime").length > 0) {
			html += "<div id='deliverydate' class='paytypeExtra'>";
			html += "<div style='display:inline'><font color='red'>*</font>配送时间：";
			/**html += "<input type='text' id='deliverDay' day='"
					+ $("#deliverTime").attr("day")
					+ "' time='"
					+ $("#deliverTime").attr("time")
					+ "' timeCode='"
					+ $("#deliverTime").attr("timeCode")
					+ "' value='"
					+ $("#deliverTime").attr("day")
					+ "' class='Wdate' onFocus=\"WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,isShowWeek:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'%y-%M-{%d+3}'})\"/>";*/
			html += "<select type='text' id='deliverTime' timeCode='"
					+ $("#deliverTime").attr("timeCode") + "' time='"
					+ $("#deliverTime").attr("time") + "'/>";
			html += "</div>";
			timeCode = $("#deliverTime").attr("timeCode");
			html += "<div style='display:inline;' id='paytype_warning_deliverTime' class='paytype_warning'></div>";
			html += "</div>";
		}
		var btimeCode;
		if ($("#bdeliverTime").length > 0) {
			html += "<div id='bdeliverydate' class='paytypeExtra'>";
			html += "<div style='display:inline'><font color='red'>*</font>预订配送时间：";
			/**html += "<input type='text' id='bdeliverDay' day='"
					+ $("#bdeliverTime").attr("day")
					+ "' time='"
					+ $("#bdeliverTime").attr("time")
					+ "' timeCode='"
					+ $("#bdeliverTime").attr("timeCode")
					+ "' value='"
					+ $("#bdeliverTime").attr("day")
					+ "' class='Wdate' onFocus=\"WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,isShowWeek:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d+2}',maxDate:'%y-%M-{%d+5}'})\"/>";*/
			html += "<select type='text' id='bdeliverTime' timeCode='"
					+ $("#bdeliverTime").attr("timeCode") + "' time='"
					+ $("#bdeliverTime").attr("time") + "'/>";
			html += "</div>";
			btimeCode = $("#bdeliverTime").attr("timeCode");
			html += "<div style='display:inline;' id='paytype_warning_bdeliverTime' class='paytype_warning'></div>";
			html += "</div>";
		}
		html += "<div id='delivery' class='paytypeExtra'>";
		html += "&nbsp;送货上门：";
		html += "<select type='text' id='toDoor' price='"
				+ $("#toDoor").attr("price") + "' beginAmount='"
				+ $("#toDoor").attr("beginAmount") + "' style='width:50px;'>";

		if ($("#toDoor").attr("value") == "是") {
			html += "<option value='是'";

			html += " selected>是</option>";
			html += "<option value='否'";

			html += ">否</option>";
		} else {
			html += "<option value='是'";

			html += ">是</option>";
			html += "<option value='否'";

			html += " selected>否</option>";
		}

		html += "</select>";
		html += "&nbsp;非包月客户送货上门费用" + $("#toDoor").attr("price")
				+ "元/次。办理上门送货包月，享更多优惠，点击<a href='" + basePath
				+ "/pro/member/delivercharge.jsp' target='_blank'>[办理]</a>";
		if ($("#toDoor").attr("beginAmount") > 0) {
			html += "。<font color=\"#000000\">[促销信息：整单满"
					+ Number($("#toDoor").attr("beginAmount")).toFixed(2)
					+ "元，免费送货上门]</font>";
		}
		// if ($("#toDoor").attr("value")) {
		//			
		// html += "。办理包月送货更多优惠，点击<a href='javascript:void(0)'
		// onclick='order.toDeliveryCharge()'>[办理]</a>";
		// } else {
		// html += "&nbsp;您还有包月送货余额，点击<a href='javascript:void(o)
		// onclick='order.viewDeliveryBalance()'>[查看]</a>，本次送货上门将直接从余额中扣除";
		// }
		html += "</div>";

		// html += "<div class='paytypeExtra'>";
		// html += "&nbsp;&nbsp;&nbsp;购物袋：";
		// html += "<input type='text' id='bags'
		// onchange='order.bagsChangeListener(\"bags\")' number='"
		// + $("#bags").attr("number")
		// + "' value='"
		// + $("#bags").attr("number")
		// + "' price='"
		// + $("#bags").attr("price")
		// + "' preValue='"
		// + $("#bags").attr("number")
		// + "'/>个&nbsp;("
		// + $("#bags").attr("price") + "元/个)";
		// html += "</div>";

		html += "<div id='paytype_save' onselectstart=\"return false\" onclick='order.savePayType()' style='color:#ffffff;background:#FFA54F;width:130px;cursor: pointer;margin-bottom:5px;'>";
		html += "<b>保存支付及配送方式</b>";
		html += "</div>";

		html += "</div>";

		html += "</div>";
		$("#part_payTypeAndShipType").html(html.toString());
		$("#part_payTypeAndShipType").css("background-color", "#FAFAFA");
		if ($("#deliverTime").length > 0) {
			timeSelector = new DeliveryTimeSelector('deliverTime',false);
			if (timeCode)
				timeSelector.setSelection(timeCode);
		}
		if ($("#bdeliverTime").length > 0) {
			btimeSelector = new DeliveryTimeSelector('bdeliverTime',true);
			if (btimeCode)
				btimeSelector.setSelection(btimeCode);
		}
	}

	this.savePayType = function() {
		if (!this.validationPayType()) {
			return;
		}

		var html = "";
		html += "<div class='o_show'>";
		html += "<h1>";
		html += "支付及配送方式&nbsp;";
		html += "<a id='linkPayTypeShipType' href='javascript:void(0)'";
		html += " onclick='order.Edit_PayType();'>[修改]</a>";
		html += "</h1>";
		html += "<div id='updateInfo_payType'></div>";
		html += "<div class='middle'>";

		html += "<table>";

		html += "<tr>";
		html += "<td style='text-align: right; width: 85px'>";
		html += "支付方式：";
		html += "</td>";
		var payTypeSelector = new getSelectedPayType();
		html += "<td id='paytype' value='" + payTypeSelector.getTitle()
				+ "' code='" + payTypeSelector.getCode() + "'>"
				+ payTypeSelector.getTitle() + "</td>";
		html += "<td><div id='valuewarning_paytype' class='valuewarning'></div></td>";
		html += "</tr>";

		if ($("#deliverTime").length > 0) {
			html += "<tr style=''>";
			html += "<td style='text-align: right;'>";
			html += "配送时间：";
			html += "</td>";

			html += "<td id='deliverTime' value='" + $("#deliverTime").val()
					//+ "&nbsp;" + timeSelector.getSelectedTimeTitle()
					+"'"
					//+ " day='" + $("#deliverDay").val() 
					//+ "'" 
					+" time='"
					+ timeSelector.getSelectedTimeTitle() + "' timeCode='"
					+ timeSelector.getSelectedTimeCode() + "'>"
					+ $("#deliverTime").val() 
					//+ "&nbsp;"
					//+ timeSelector.getSelectedTimeTitle() 
					+ "</td>";
			html += "<td><div id='valuewarning_deliverTime' class='valuewarning'></div></td>";
			html += "</tr>";
		}
		if ($("#bdeliverTime").length > 0) {
			html += "<tr style=''>";
			html += "<td style='text-align: right;'>";
			html += "预订配送时间：";
			html += "</td>";

			html += "<td id='bdeliverTime' value='" + $("#bdeliverTime").val()
					//+ "&nbsp;" + btimeSelector.getSelectedTimeTitle()
					+ "'"
					//+" day='" + $("#bdeliverDay").val() 
					//+ "'" 
					+" time='"
					+ btimeSelector.getSelectedTimeTitle() 
					+ "'"
					+" timeCode='"
					+ btimeSelector.getSelectedTimeCode() + "'>"
					//+ $("#bdeliverDay").val() + "&nbsp;"
					+ btimeSelector.getSelectedTimeTitle() + "</td>";
			html += "<td><div id='valuewarning_bdeliverTime' class='valuewarning'></div></td>";
			html += "</tr>";
		}
		html += "<tr style='display: '>";
		html += "<td style='text-align: right;'>";
		html += "送货上门：";
		html += "</td>";

		html += "<td id='toDoor' price='" + $("#toDoor").attr("price")
				+ "' beginAmount='" + $("#toDoor").attr("beginAmount")
				+ "' value='";
		if ($("#toDoor").val() == "是") {
			html += "是";
		} else {
			html += "否";
		}
		html += "'>";
		html += $("#toDoor").val() + "&nbsp;非包月客户送货上门费用"
				+ $("#toDoor").attr("price") + "元/次。办理上门送货包月，享更多优惠，点击<a href='"
				+ basePath
				+ "/pro/member/delivercharge.jsp' target='_blank'>[办理]</a>";
		if ($("#toDoor").attr("beginAmount") > 0) {
			html += "。<font color=\"#000000\">[促销信息：整单满"
					+ Number($("#toDoor").attr("beginAmount")).toFixed(2)
					+ "元，免费送货上门]</font>";
		}
		html += "</td>";
		html += "</tr>";

		// html += "<tr style=''>";
		// html += "<td style='text-align: right;'>";
		// html += "购物袋：";
		// html += "</td>";
		// html += "<td id='bags' number='" + $("#bags").val() + "' price='"
		// + $("#bags").attr("price") + "' cost='0'>" + $("#bags").val()
		// + "&nbsp;(" + $("#bags").attr("price") + "元/个)</td>";
		// html += "</tr>";

		html += "</table>";

		html += "</div>";
		this.updateTotal();
		$("#part_payTypeAndShipType").html(html.toString());
		$("#part_payTypeAndShipType").css("background-color", "#ffffff");

	}

	this.updateTotal = function() {
		var total_deliveryCost = 0;
		// var total_bagsCost = 0;
		var tatalAmount = Number($("#total_goods_amount").attr("totalAmount"));
		if ($("#toDoor").val() == "是"
				&& $("#total_deliveryCost").attr("p_freedelivery") == "否") {
			total_deliveryCost = Number($("#toDoor").attr("price"));
			tatalAmount = tatalAmount + total_deliveryCost;
		}
		// if ($("#bags").val() > 0) {
		// total_bagsCost = Number($("#bags").val())
		// * Number($("#bags").attr("price"));
		// tatalAmount = tatalAmount + total_bagsCost;
		// }
		$("#total_deliveryCost").text(total_deliveryCost.toFixed(2));
		// $("#total_bagsCost").text(total_bagsCost.toFixed(2));
		$("#tatalAmount").text("￥" + tatalAmount.toFixed(2));
		$("#total_deliveryCost").attr("total_deliveryCost",
				total_deliveryCost.toFixed(2));
		// $("#total_bagsCost").attr("total_bagsCost",
		// total_bagsCost.toFixed(2));
		$("#part_total_amounting_right").attr("tatalAmount",
				tatalAmount.toFixed(2));
	}
	function getSelectedPayType() {

		var code;
		var title;

		$("input:radio[name='paytype']").each(function() {
					{
						if (this.checked) {
							title = $(this).attr("value");
							code = $(this).attr("code");
						}
					}
				});
		this.getTitle = function() {
			return title;
		}
		this.getCode = function() {
			return code;
		}

	}
	function getSelectedStation() {
		var recid;
		var title;
		$("input:radio[name='station']").each(function() {
					{
						if (this.checked) {
							title = $(this).attr("value");
							recid = $(this).attr("recid");
						}
					}
				});
		this.getRecid = function() {
			return recid;
		}
		this.getTitle = function() {
			return title;
		}

	}

	this.getStationRowHtml = function() {
		var html = "";
		var areaCode = areaObject
				? areaObject.getSelectedDistrictCode()
				: $("#province").attr("town");
		$.ajax({
			url : mainWeb + "/front/common/getStationList",
			async : false,
			// data: {'areaCode': areaCode},
			dataType : 'json',
			success : function(data) {
				if (data.total > 0) {
					var stations = data.rows;
					for (var index = 0; index < stations.length; index++) {
						html += "<div class='station_row'>";
						html += "<div class='station_row_left'>";

						html += "<input type='radio' name='station' ";
						if ($("#station").attr("recid") == stations[index].recid) {
							html += " checked='true' ";
						}
						html += "recid='" + stations[index].recid + "' value='"
								+ stations[index].stationname + "'/>&nbsp;"
								+ stations[index].stationname;

						html += "</div>";
						html += "<div class='station_row_right'>";
						html += "地址：" + stations[index].provinceTitle
								+ stations[index].cityTitle
								+ stations[index].townTitle
								+ stations[index].address
								+ "&nbsp;010-86786868";
						html += "</div>";
						html += "</div>";
					}
				} else {
				}
			}
		});
		return html;
	}

	this.Edit_PayType_Close = function() {
		var html = "";
		html += "<div class='o_show'>";
		html += "<h1>";
		html += "支付及配送方式&nbsp;";
		html += "<a id='linkPayTypeShipType' href='javascript:void(0)'";
		html += "clstag='checkout|keycount|jiesuan|linkpayshiptype'";
		html += "onclick='order.Edit_PayType();' style='display: '>[修改]</a>";
		html += "</h1>";
		html += "<div id='updateInfo_payType'></div>";
		html += "<div class='middle'>";

		html += "<table>";

		html += "<tr>";
		html += "<td style='text-align: right; width: 85px'>";
		html += "支付方式：";
		html += "</td>";
		html += "<td id='paytype' value='余额支付' code='1'>余额支付</td>";
		html += "<td><div id='valuewarning_paytype' class='valuewarning'></div></td>";
		html += "</tr>";

		if ($("#deliverTime").length > 0) {
			html += "<tr style=''>";
			html += "<td style='text-align: right;'>";
			html += "配送时间：";
			html += "</td>";
			html += "<td id='deliverTime' value='"
					//+ $("#deliverTime").attr("timeCode") + "&nbsp;"
					+ $("#deliverTime").attr("time") + "'"
					//+" day='"
					//+ $("#deliverDay").attr("day") + "'"
					+" time='"
					+ $("#deliverTime").attr("time") + "' timeCode='"
					+ $("#deliverTime").attr("timeCode") + "'>"
					//+ $("#deliverTime").attr("value") + "&nbsp;"
					+ $("#deliverTime").attr("time") + "</td>";
			html += "<td><div id='valuewarning_deliverTime' class='valuewarning'></div></td>";
			html += "</tr>";
		}
		if ($("#bdeliverTime").length > 0) {
			html += "<tr style=''>";
			html += "<td style='text-align: right;'>";
			html += "预订配送时间：";
			html += "</td>";
			html += "<td id='bdeliverTime' value='"
					//+ $("#bdeliverDay").attr("day") + "&nbsp;"
					+ $("#bdeliverTime").attr("time") + "'"
					//+" day='"
					//+ $("#bdeliverDay").attr("day") + "'"
					+" time='"
					+ $("#bdeliverTime").attr("time") + "' timeCode='"
					+ $("#bdeliverTime").attr("timeCode") + "'>"
					//+ $("#bdeliverDay").attr("value") + "&nbsp;"
					+ $("#bdeliverTime").attr("time") + "</td>";
			html += "<td><div id='valuewarning_bdeliverTime' class='valuewarning'></div></td>";
			html += "</tr>";
		}
		html += "<tr style='display: '>";
		html += "<td style='text-align: right;'>";
		html += "送货上门：";
		html += "</td>";

		html += "<td id='toDoor' price='" + $("#toDoor").attr("price")
				+ "' beginAmount='" + $("#toDoor").attr("beginAmount")
				+ "' value='" + $("#toDoor").attr("value") + "'>";
		html += $("#toDoor").attr("value");
		html += "&nbsp;非包月客户送货上门费用" + $("#toDoor").attr("price")
				+ "元/次。办理上门送货包月，享更多优惠，点击<a href='" + basePath
				+ "/pro/member/delivercharge.jsp' target='_blank'>[办理]</a>";
		if ($("#toDoor").attr("beginAmount") > 0) {
			html += "。<font color=\"#000000\">[促销信息：整单满"
					+ Number($("#toDoor").attr("beginAmount")).toFixed(2)
					+ "元，免费送货上门]</font>";
		}
		html += "</td>";
		html += "</tr>";

		// html += "<tr style=''>";
		// html += "<td style='text-align: right;'>";
		// html += "购物袋：";
		// html += "</td>";
		// html += "<td id='bags' number='" + $("#bags").attr("number")
		// + "' price='" + $("#bags").attr("price") + "' cost='0'>"
		// + $("#bags").attr("number") + "&nbsp;("
		// + $("#bags").attr("price") + "元/个)</td>";
		// html += "</tr>";

		html += "</table>";

		html += "</div>";
		$("#part_payTypeAndShipType").html(html.toString());
		$("#part_payTypeAndShipType").css("background-color", "#ffffff");
	}

	this.getStationHtml = function() {
		var html = "";
		html += "<tr id='station_header'>";
		html += "<td align='right'>";
		html += "<font color='red'>*</font>选择站点：";
		html += "</td>";
		html += "<td id='station' recid='" + $("#station").attr("recid")
				+ "' value='" + $("#station").attr("value") + "' >";
		html += "<select style='width:150px;' id='station_selector' name='stationid'></select>";
		html += "</td>";
		html += "<td colspan='4'>";
		html += "<div style='display:inline' id='paytype_warning_station' class='paytype_warning'></div>";
		html += "</div>";
		html += "</td>";
		html += "</tr>";
		// html += this.getStationRowHtml();

		return html;
	}

	this.getPaytypeHtml = function() {
		var html = "";
		html += "<div id='paytype_header'>";
		html += "<div id='paytype_header_left'>";
		html += "<div style='display:inline;'><h1>";
		html += "<font color='red' size='1px' weight='lighter'>*</font>支付方式";
		html += "</h1></div>";
		html += "<div style='display:inline;' id='paytype_warning_paytype' class='paytype_warning'></div>";
		html += "</div>";
		html += "<div id='paytype_header_right'>";
		html += "备注";
		html += "</div>";
		html += "</div>";
		html += "<div class='paytype_row'>";
		html += "<div class='paytype_row_left'>";

		html += "<input type='radio' name='paytype' code='1' value='余额支付'";
		if ($("#paytype").attr("code") == 1) {
			html += " checked='true' ";
		}
		html += "/>&nbsp;余额支付";

		html += "</div>";
		html += "<div class='paytype_row_right'>";
		html += "用账户余额支付&nbsp;<a href='" + basePath
				+ "/front/getBalance' target='_blank'>查看账户余额</a>";
		html += "</div>";
		html += "</div>";
		html += "<div class='paytype_row'>";
		html += "<div class='paytype_row_left'>";

		html += "<input type='radio' name='paytype' code='2' value='在线支付'";
		if ($("#paytype").attr("code") == 2) {
			html += " checked='true' ";
		}
		html += "/>&nbsp;在线支付";

		html += "</div>";
		html += "<div class='paytype_row_right'>";
		html += "即时到帐，支持大多数银行卡";
		html += "</div>";
		html += "</div>";
		return html;
	}

	this.getAddressHtml = function() {
		var html = "";
		html += "<div class='address_list'";
		html += " style=\"color:#4F94CD;background:D1EEEE;\" ";
		html += ">";
		html += "常用地址";
		var addressId = $("#province").attr("addressId");
		$.ajax({
			url : mainWeb + '/front/member/getAddressList',
			dataType : 'json',
			async : false,
			cache : false,
			success : function(data) {
				if (data.total > 0) {
					addresses = data.rows;
					for (var index = 0; index < addresses.length; index++) {
						var addressStr = "recid~" + addresses[index].recid
								+ "&consignee~" + addresses[index].consignee
								+ "&provinceTitle~"
								+ addresses[index].provinceTitle
								+ "&cityTitle~" + addresses[index].cityTitle
								+ "&townTitle~" + addresses[index].townTitle
								+ "&province~" + addresses[index].province
								+ "&city~" + addresses[index].city + "&town~"
								+ addresses[index].town + "&address~"
								+ addresses[index].address + "&mobile~"
								+ addresses[index].mobile + "&phone~"
								+ addresses[index].telephone + "&postCode~"
								+ addresses[index].postcode;
						html += "<div class='address' id='"
								+ addresses[index].recid + "'";
						if (addressId) {
							if (addressId == addresses[index].recid) {
								html += " style=\"color:#32CD32;border:#32CD32 1px dotted;background:#C1FFC1;\" ";
							} else {
								html += " style=\"color:#4F94CD;border:#D1EEEE 1px dotted;background:D1EEEE;\" ";
							}
						} else {
							if (addresses[index].isdefault) {
								html += " style=\"color:#32CD32;border:#32CD32 1px dotted;background:#C1FFC1;\" ";
							} else {
								html += " style=\"color:#4F94CD;border:#D1EEEE 1px dotted;background:D1EEEE;\" ";
							}
						}
						html += ">";
						html += "<input name='addressradio' addressId='"
								+ addresses[index].recid
								+ "' style='margin-left:3px;margin-top:4px;*margin-top:1px;' type='radio':line-height:20px;vertical-align:middle;";

						html += " id='" + addresses[index].recid
								+ "_radio' addresstemp='" + addressStr
								+ "' onclick='order.addressChangeListener(\""
								+ addressStr + "\")'";
						if (addressId) {
							if (addressId == addresses[index].recid) {
								html += " checked "
							}
						} else {
							if (addresses[index].isdefault) {
								html += " checked "
							}
						}
						html += " /><span style='margin-top:6px;height:20px;*height:18px;line-height:20px;*line-height:18px;'>&nbsp;"
								+ addresses[index].consignee
								+ "&nbsp;"
								+ addresses[index].provinceTitle
								+ addresses[index].cityTitle
								+ addresses[index].townTitle
								+ addresses[index].address
								+ "</span><div class='address_del'><a href='javascript:void(0)' onclick='order.deleteAddress(\""
								+ addresses[index].recid
								+ "\")'>[删除]</a></div>";
						html += "</div>";
					}
				}
			}
		});

		html += "</div>";
		return html;
	}

	this.deleteAddress = function(addressId) {
		if (!addressId) {
			return;
		}
		var dataStr = "id=" + addressId;
		dataStr = encodeURI(dataStr);
		var cc = new CmsConfirm("提示", "确定要删除该地址吗？");
		cc.setActionListener(function(result) {
					if (result) {
						$.ajax({
									url : mainWeb
											+ "/front/member/deleteAddress",
									data : dataStr,
									dataType : 'json',
									success : function(data) {
										if (data.result) {
											var id = "#" + addressId;
											$(id).remove();
										}
									}
								});
					}
				});
	}
	// var areaObject;
	this.getConsigneeTableHtml = function() {
		var html = "";
		html += "<table>";
		html += "<tr>";
		html += "<td style='width: 85px;' align='right'>";
		html += "<font color='red'>*</font>收货人姓名：";
		html += "</td>";
		html += "<td>";
		html += "<input id='consignee' style='width:150px;' value='"
				+ $("#consignee").attr("prevalue") + "' prevalue='"
				+ $("#consignee").attr("prevalue") + "'/>";
		html += "</td>";
		html += "<td colspan='3'><div class='warning' id='warning_consignee'></div></td>";
		html += "</tr>";
		html += this.getProvinceCityTownHtml();
		html += this.getStationHtml();
		html += "<tr>";
		html += "<td align='right'>";
		html += "<font color='red'>*</font>地 址：";
		html += "</td>";
		html += "<td  colspan='3'>";
		html += "<input style='width:335px;*width:329px;' id='address' value='"
				+ $("#address").attr("prevalue") + "' prevalue='"
				+ $("#address").attr("prevalue") + "'/>";
		html += "</td>";
		html += "<td colspan='3'><div class='warning' id='warning_address'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "<font color='red'>*</font>手机号码：";
		html += "</td>";
		html += "<td>";
		html += "<input id='mobile' style='width:150px;' value='"
				+ $("#mobile").attr("prevalue") + "' prevalue='"
				+ $("#mobile").attr("prevalue") + "'/>";
		html += "</td>";
		html += "<td colspan='3'><div class='warning' id='warning_mobile'></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td align='right'>";
		html += "固定电话：";
		html += "</td>";
		html += "<td>";
		html += "<input id='phone' style='width:150px;' value='"
				+ $("#phone").attr("prevalue") + "' prevalue='"
				+ $("#phone").attr("prevalue") + "'/>";
		html += "</td>";
		html += "</tr>";

		html += "<tr>";
		html += "<td align='right'>";
		html += "邮 编：";
		html += "</td>";
		html += "<td>";
		html += "<input id='postCode' style='width:150px;' value='"
				+ $("#postCode").attr("prevalue") + "' prevalue='"
				+ $("#postCode").attr("prevalue") + "'/>";
		html += "</td>";
		html += "</tr>";

		html += "</table>";
		return html;
	}

	this.loadStation = function(areaCode) {
		var $stationEditor = $('#station_selector');
		var $stationPrompt = $("#paytype_warning_station");
		$.ajax({
			url : mainWeb + "/front/common/getStation",
			data : {
				'areaCode' : areaCode
			},
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.total > 0) {
					$stationPrompt.empty();
					$stationEditor.empty();
					var stations = data.rows;
					var selected = "";
					for (var index = 0; index < stations.length; index++) {
						if ($("#station").attr("recid")
								&& $("#station").attr("recid") == stations[index].recid) {
							selected = "selected";
						} else {
							selected = "";
						}
						var $option = $("<option value='"
								+ stations[index].recid + "' " + selected + ">"
								+ stations[index].stationName + "</option>");
						$option.appendTo($stationEditor);
					}
				} else {
					$stationEditor.empty();
					$stationPrompt.html('当前选择的地区没有站点，请重新选择地区');
				}
			}
		});
	};

	this.addressChangeListener = function(addressStr) {
		$("#consignee").val(this.parseAddressStr(addressStr, "consignee"));
		$("#address").val(this.parseAddressStr(addressStr, "address"));
		$("#mobile").val(this.parseAddressStr(addressStr, "mobile"));
		$("#phone").val(this.parseAddressStr(addressStr, "phone"));
		$("#postCode").val(this.parseAddressStr(addressStr, "postCode"));
		areaObject.setSelection(this.parseAddressStr(addressStr, "province"),
				this.parseAddressStr(addressStr, "city"), this.parseAddressStr(
						addressStr, "town"));
		$(".address").css("border", "#D1EEEE 1px solid");
		$(".address").css("color", "#4F94CD");
		$(".address").css("background", "#D1EEEE");
		var addressDivId = "#" + this.parseAddressStr(addressStr, "recid");
		$(addressDivId).css("border", "#32CD32 1px dotted");
		$(addressDivId).css("color", "#32CD32");
		$(addressDivId).css("background", "#C1FFC1");
	}

	this.getProvinceCityTownHtml = function() {
		var html = "";

		html += "<tr>";
		html += "<td align='right'>";
		html += "<font color='red'>*</font>地&nbsp;区：";
		html += "</td>";
		html += "<td id='province' addressId='"
				+ $("#province").attr("addressId") + "' prevalue='"
				+ $("#province").attr("prevalue") + "' province='"
				+ $("#province").attr("province") + "'" + "' city='"
				+ $("#province").attr("city") + "'" + "' town='"
				+ $("#province").attr("town") + "'" + ">";
		html += "<select class='select' style='width:150px;' id='select_provence'>";
		html += "</select>";
		html += "</td>";
		html += "<td align='right'>";
		html += "市：";
		html += "</td>";
		html += "<td>";
		html += "<select class='select' style='width:150px;' id='select_city'>";
		html += "</select>";
		html += "</td>";
		html += "<td align='right'>";
		html += "区：";
		html += "</td>";
		html += "<td>";
		html += "<select class='select' style='width:150px;' id='select_town'>";
		html += "</select>";
		html += "</td>";
		html += "</tr>";
		return html;
	}

	this.parseAddressStr = function(addressStr, fieldName) {
		if (!addressStr || !fieldName) {
			return "";
		}
		for (var i = 0; i < addressStr.split("&").length; i++) {
			var fieldStr = addressStr.split("&")[i];
			if (fieldName == fieldStr.split("~")[0]) {
				return fieldStr.split("~")[1];
			}
		}
	}

	this.addAddress = function() {
		var consignee = $("#consignee").val();
		var address = $("#address").val();
		var mobile = $("#mobile").val();
		var phone = $("#phone").val();
		var postCode = $("#postCode").val();
		var stationId = $("#station_selector").val();
		var province = areaObject.getSelectedProvinceCode();
		var city = areaObject.getSelectedCityCode();
		var town = areaObject.getSelectedDistrictCode();
		if (!this.validationAddress()) {
			return;
		}
		var dataStr = "consignee=" + consignee + "&address=" + address
				+ "&mobile=" + mobile + "&telephone=" + phone + "&postcode="
				+ postCode + "&province=" + province + "&city=" + city
				+ "&town=" + town + "&stationid=" + stationId;
		dataStr = encodeURI(dataStr);
		$.ajax({
					// type: 'post',
					url : mainWeb + "/front/member/addAddress",
					data : dataStr,
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (data.result) {
							new cmsAlertSuccess("提示", "保存成功！");
						}
					}
				});

	}

	this.validationAddress = function() {
		var success = true;
		$(".warning").each(function() {
					if (!("warning_province" == this.id)) {
						var id = "#" + this.id.split("warning_")[1];
						if ($(id).val()) {
							$(this).html("");
						} else {
							var str = "";
							if ("#consignee" == id) {
								str += "收货人姓名不能为空！";
							} else if ("#province" == id) {
								str += "地区不能为空！";
							} else if ("#address" == id) {
								str += "地址不能为空！";
							} else if ("#mobile" == id) {
								str += "手机号码格式不正确！";
							}
							$(this).html(str);
							success = false;
						}
					}

				});
		var stationeselected = false;
		if ($("#station_selector").val()) {
			stationeselected = true;
		}
		if (stationeselected) {
			$("#paytype_warning_station").html("");
		} else {
			var str = "站点不能为空！";
			$("#paytype_warning_station").html(str);
			success = false;
		}
		return success;
	}

	this.validationPayType = function() {
		var success = true;
		var paytypeselected = false;
		$("input:radio[name='paytype']").each(function() {
					if (this.checked) {
						paytypeselected = true;
					}
				}

		);
		var paytypeId = "#paytype_warning_paytype";
		if (paytypeselected) {
			$(paytypeId).html("");
		} else {
			var str = "支付方式不能为空！";
			$(paytypeId).html(str);
			success = false;
		}
		if ($("#deliverTime").length > 0) {
			if (!$("#deliverTime").val()) {
				var str = "配送时间不能为空！";
				$("#paytype_warning_deliverTime").html(str);
				success = false;
			} else {
				$("#paytype_warning_deliverTime").html("");
			}
		}
		if ($("#bdeliverTime").length > 0) {
			if (!$("#bdeliverTime").val()) {
				var str = "预订配送时间不能为空！";
				$("#paytype_warning_bdeliverTime").html(str);
				success = false;
			} else {
				$("#paytype_warning_bdeliverTime").html("");
			}
		}

		// TODO 时间点校验
		/**if ($("#deliverDay").val() && timeSelector
				&& timeSelector.getSelectedTimeCode()) {
			var currentDate = new Date();
			if ($("#deliverDay").val().split("-")[2] == currentDate.getDate()) {
				if (0 <= currentDate.getHours() && currentDate.getHours() < 11) {
					if (timeSelector.getSelectedTimeCode().toString()
							.split(":")[0] == 11) {
						var str = "当日0时到11时只能选择17时或以后配送！";
						$("#paytype_warning_deliverTime").html(str);
						success = false;
					} else {
						$("#paytype_warning_deliverTime").html("");
					}
				} else {
					var str = "当日11时后只能选择次日或以后配送！";
					$("#paytype_warning_deliverTime").html(str);
					success = false;
				}
			} else {
				$("#paytype_warning_deliverTime").html("");
			}
		}*/

		return success;
	}

	// 上门送货充值
	this.toDeliveryCharge = function() {
		// TODO
	}
	// 上门送货余额
	this.viewDeliveryBalance = function() {
		// TODO
	}

	// this.bagsChangeListener = function(input) {
	// var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
	// if ($(input).val().match(reg) == null) {
	// $(input).val($(input).attr("preValue"));
	// } else {
	// $(input).attr("preValue", $(input).val());
	// }
	// }

	var successful = false;
	this.submit = function() {
		if (successful) {
			new cmsAlertError("", "该订单已经提交成功，请勿重复提交！");
			return;
		}
		if ($("#consignee_save").html()) {
			new cmsAlertError("", "请先保存收货人信息！");
			return;
		}
		if ($("#paytype_save").html()) {
			new cmsAlertError("", "请先保存支付及配送方式！");
			return;
		}
		if (!this.validationValue()) {
			this.scrollToTop();
			return;
		}
		if (this.onlyVantagesGoods() && $("#toDoor").attr("value") == "是") {
			new cmsAlertError("", "抱歉，积分商城商品暂不提供送货上门服务！");
			return;
		}
		// TODO
		var orderInfo = "";
		orderInfo += "[{";
		orderInfo += "address:\"" + $("#address").attr("prevalue") + "\"";
		// orderInfo += ",bagsCost:\""
		// + $("#total_bagsCost").attr("total_bagsCost") + "\"";
		if ($("#deliverTime").length > 0) {
			orderInfo += ",deliverTime:\"" + $("#deliverTime").attr("timeCode")+ ":00"+ "\"";
		} else {
			orderInfo += ",deliverTime:\"\"";
		}
		if ($("#bdeliverTime").length > 0) {
			orderInfo += ",bdeliverTime:\"" + $("#bdeliverTime").attr("timeCode")+ ":00"+"\"";
		} else {
			orderInfo += ",bdeliverTime:\"\"";
		}
		orderInfo += ",consignee:\"" + $("#consignee").attr("prevalue") + "\"";
		orderInfo += ",mobile:\"" + $("#mobile").attr("prevalue") + "\"";
		orderInfo += ",deliveryCost:\""
				+ $("#total_deliveryCost").attr("total_deliveryCost") + "\"";
		orderInfo += ",station:\"" + $("#station").attr("recid") + "\"";
		orderInfo += ",stationname:\"" + $("#station").attr("value") + "\"";
		orderInfo += ",totalAmount:\""
				+ $("#part_total_amounting_right").attr("totalAmount") + "\"";
		var vantages = Number($("#total_vantages").attr("totalVantages"));
		var p_vantages = Number($("#total_vantages").attr("p_vantages"));
		orderInfo += ",vantages:\"" + vantages + "\"";
		orderInfo += ",p_vantages:\"" + p_vantages + "\"";
		orderInfo += ",toDoor:\"" + $("#toDoor").attr("value") + "\"";
		orderInfo += ",payType:\"" + $("#paytype").attr("code") + "\"";
		orderInfo += "";

		orderInfo += ",goodsArray:[";
		orderInfo += this.getGoodsArray();
		orderInfo += "]}]";

		// dataStr = encodeURI(orderInfo);
		dataStr = orderInfo;
		$.ajax({
					type : 'post',
					url : mainWeb + "/front/order/createOrder",
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					data : {
						orderInfo : dataStr
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							successful = true;
							order.updateCookie();
							if (data.payType && data.orderIds) {
								order.toPaying(data.payType, data.orderIds);
							} else {
								order.toOrderCenter();
							}
						} else {
							successful = false;
							if (data.noLogin) {
								order.toLogin();
							} else {
								new cmsAlertError(data.errorMsg);
							}
						}
					},
					error : function() {
						successful = false;
						new cmsAlertError("未知错误！");
					}
				});
	}

	this.onlyVantagesGoods = function() {
		var onlyVG = true;
		$(".part_goods_row").each(function() {
					if ("false" == $(this).attr("vantagesGoods")) {
						onlyVG = false;
					}
				});
		return onlyVG;
	}

	this.toPaying = function(payType, orderIds) {
		// if (payType == 1) {
		var url = mainWeb + "/pub/sub/orderMainPay.jsp?orderIds=" + orderIds
				+ '&payType=' + payType;
		location.replace(url);
		event.returnValue = false;
		// } else if (payType == 2) {
		// var url = mainWeb + "/pub/sub/orderMainPay.jsp?orderIds="
		// + orderIds;
		// location.replace(url);
		// event.returnValue = false;
		// }
	}

	this.toOrderCenter = function() {
		var url = mainWeb + "/front/order/getOrders";
		location.replace(url);
		event.returnValue = false;
	}

	this.getGoodsArray = function() {
		var ga = "";
		var index = 0;
		$(".part_goods_row").each(function() {
					if (0 != index) {
						ga += ",";
					}
					ga += "{";
					ga += "goodsId:\"" + this.id.split("_")[0] + "\"";
					var count = "";
					if ("null" != $(this).attr("count")) {
						count = $(this).attr("count");
					}
					ga += ",count:\"" + count + "\"";
					var price = "";
					if ("null" != $(this).attr("price")) {
						price = $(this).attr("price");
					}
					ga += ",price:\"" + price + "\"";
					var vantagesType = "";
					if ("null" != $(this).attr("vantagesType")) {
						vantagesType = $(this).attr("vantagesType");
					}
					ga += ",vantagesType:\"" + vantagesType + "\"";
					var disrate = "";
					if ("null" != $(this).attr("disrate")) {
						disrate = $(this).attr("disrate");
					}
					ga += ",disrate:\"" + disrate + "\"";
					var vantagesGoods = "";
					if ("null" != $(this).attr("vantagesGoods")) {
						vantagesGoods = $(this).attr("vantagesGoods");
					}
					ga += ",vantagesGoods:\"" + vantagesGoods + "\"";
					var vantagesCost = "";
					if ("null" != $(this).attr("vantagesCost")) {
						vantagesCost = $(this).attr("vantagesCost");
					}
					ga += ",vantagesCost:\"" + vantagesCost + "\"";
					ga += ",isGift:\"" + $(this).attr("isGift") + "\"";
					ga += ",isOtherGift:\"" + $(this).attr("isOtherGift")
							+ "\"";
					var vantages = "";
					if ("null" != $(this).attr("vantages")) {
						vantages = $(this).attr("vantages");
					}
					ga += ",vantages:\"" + vantages + "\"";
					ga += "}";
					index++;
				});
		return ga;
	}
	this.validationValue = function() {
		var success = true;
		$(".valuewarning").each(function() {

			var id = "#" + this.id.split("valuewarning_")[1];

			if ("#consignee" == id) {
				if ($(id).attr("prevalue")) {
					$(this).html("");
				} else {
					$(this).html("收货人姓名不能为空！");
					success = false;
				}

			} else if ("#province" == id) {
				if ($(id).attr("province") && $(id).attr("city")
						&& $(id).attr("town")) {
					$(this).html("");
				} else {
					$(this).html("地区不能为空！");
					success = false;
				}
			} else if ("#address" == id) {
				if ($(id).attr("prevalue")) {
					$(this).html("");
				} else {
					$(this).html("地址不能为空！");
					success = false;
				}
			} else if ("#mobile" == id) {
				if ($(id).attr("prevalue")) {
					$(this).html("");
				} else {
					$(this).html("手机号码格式不正确！");
					success = false;
				}
			} else if ("#paytype" == id) {
				if ($(id).attr("code")) {
					$(this).html("");
				} else {
					$(this).html("支付方式不能为空！");
					success = false;
				}
			} else if ("#deliverTime" == id) {
				if ($(id).attr("timeCode")) {
					$(this).html("");
				} else {
					$(this).html("配送时间不能为空！");
					success = false;
				}
			} else if ("#bdeliverTime" == id) {
				if ($(id).attr("timeCode")) {
					$(this).html("");
				} else {
					$(this).html("预订配送时间不能为空！");
					success = false;
				}
			} else if ("#station" == id) {
				if ($(id).attr("recid")) {
					$(this).html("");
				} else {
					$(this).html("站点不能为空！");
					success = false;
				}
			}

		});
		return success;
	}

	this.scrollToTop = function() {
		document.documentElement.scrollTop = 0;
		document.body.scrollTop = 0;
	}

	this.updateCookie = function() {
		$(".part_goods_row").each(function() {
			if (this.id) {
				if ("true" == $(this).attr("vantagesGoods")) {
					cookieutil.removeVantegeGoodsFromShoppingCar(this.id
							.split("_")[0]);
				} else {
					cookieutil
							.removeGoodsFromShoppingCar(this.id.split("_")[0]);
				}
			}

		});
	}
}
