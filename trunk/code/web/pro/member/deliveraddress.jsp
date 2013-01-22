<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/pro/member/css/my7.css">
<style type="text/css">
/*  accountinfo main */
.main {margin-bottom: 20px}
.right {width: 1040px;line-height: 1.5em;float: left;font-family:Verdana; margin-bottom: 20px; }
.main .left {float: left;width: 150px;margin-right: 10px}

/** user base **/
.right a:link,.right a:visited{color:#005ea7;}
.m .mt a:link, .m .mt a:visited{color:#333}
.main{margin-bottom:10px;}

/* user info */
.right .o-mt {*height: 27px;font-size: 12px;font-weight: 400;letter-spacing: 1px;text-align: left;
	background-image: url('<%=basePath%>/images/page/sort-bg05.png');padding: 4px 5px;
	border-top: 1px solid #E6E6E6; border-left: 1px solid #E6E6E6; border-right: 1px solid #E6E6E6;}

em{color:#ff6600;margin-right:3px;}
.addressform {
	width: 1040px;
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
}
.addressform .title {
	color: #FC6210;
	font-size:12px;
	font-weight:700;
}
.addressform .labelArea {
	float: left;
	width: 240px;
	text-align: right;
	line-height: 40px;
	border: red solid 0px;
}
.addressform .inputArea {
	float: left;
	width: 780px;
	text-align: left;
	height: 40px;
	border: blue solid 0px;
}
.addressform .buttonArea { 
	float: left;
	width: 780px;
	text-align: left;
	height: 40px;
	margin-left: 240px;
	*margin-left: 120px;
}
.addressform input, .addressform select {
	margin-top: 9px;
	margin-left: 10px;
	height: 22px;
}
.addressform textarea {
	margin-top: 9px;
	margin-left: 10px;
	height: 60px;
	width: 400px;
	resize: none;
}
.addressform select {
	width: 120px;
}
.addressform .prompt{
	display: inline;
	color: red;
}

.tableArea {
	border-left: 1px solid #E6E6E6;
	border-right: 1px solid #E6E6E6;
	border-bottom: 1px solid #E6E6E6;
	*padding: 20px 0;
}

#addresstable {
	margin-left: 30px;
	margin-bottom:20px 
}
#ordercenter-data-title
{width:1032px;*width:1040px;height:35px;line-height: 35px;border-bottom: #66CD00 2px solid;float: left;text-align: left;padding-left: 8px;color: #66CD00;}
 

/*table*/
table{*border-collapse:collapse;border-spacing:0;table-layout: fixed;} 
caption{font-size:12px;font-weight:700;letter-spacing:2px;height:24px;line-height:24px;text-align:left;color:#FC6210;}
th,td{font-size:12px;}
th{font-weight:400;letter-spacing:1px;}
.table_a{border-left:1px solid #E7CA96;border-bottom:1px solid #E7CA96;}
.table_a td,.table_a th{border-top:1px solid #E7CA96;border-right:1px solid #E7CA96;padding:4px 5px;}
.table_a th,.table_c th{background:#FBF7EE;}
.table_a strong.dd{color:#DA2B28;}
.table_a strong.ww{color:#005aa0;}
td.table_wrap{padding:0;}
td.table_wrap td,.table_wrap th,.table_wrap td{border-top:none;border-right:none;}
td{word-break: break-all; word-wrap:break-word;}
tr.addressdefault {
	background: #F69401;
	height: 30px;
}
#shortcut {
	padding-bottom: 1px;
	line-height: 30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}

</style>
<script type="text/javascript" src="<%=basePath %>/scripts/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/scripts/area.js"></script>
<script type='text/javascript'>
var _deliverAddressApp = null;
$(document).ready(function() {
	_deliverAddressApp = new DeliverAddressApp();
});

function DeliverAddressApp() {
	$.ajaxSetup({cache:false});
	
	var addresses = null;
	// �����ʼ״̬
	var inputStatus = 0;
	
	var $consigneeEditor = $('.addressform #da_consignee');
	var $addressEditor = $('.addressform #da_address');
	var $postcodeEditor = $('.addressform #da_postcode');
	var $mobileEditor = $('.addressform #da_mobile');
	var $stationEditor = $('.addressform #da_stationid');
	var $telephoneEditor = $('.addressform #da_telephone');
	//var $isDefaultEditor = $('.addressform #da_isdefault');
	
	var areaProvider = null;
	
	var $consigneePrompt = $('.addressform #prompt_consignee');
	var $addressPrompt = $('.addressform #prompt_address');
	//var $postcodePrompt = $('.addressform #prompt_postcode');
	var $mobilePrompt = $('.addressform #prompt_mobile');
	var $stationPrompt = $('.addressform #prompt_stationid');
	//var $telephonePrompt = $('.addressform #prompt_telephone');
	
	
	this.del = function(id) {
		$.ajax({
			url: mainWeb + "/front/member/deleteAddress",
			data: {'id': id},
			dataType: 'json',
			success: function(data) {
				if (data.result) {
					loadAddressTable();
				}
			}
		});
	};
	
	this.modify = function(id) {
		$('.addressform #formtitle').html('�޸��ջ���ַ');
		$('.addressform #recid').val(id);
		var addressInfo = getAddressById(id);
		$consigneeEditor.val(addressInfo.consignee);
		$addressEditor.val(addressInfo.address);
		$postcodeEditor.val(addressInfo.postcode);
		$mobileEditor.val(addressInfo.mobile);
		$telephoneEditor.val(addressInfo.telephone);
		//$isDefaultEditor.attr('checked', addressInfo.isdefault);
		areaProvider.setSelection(addressInfo.province, addressInfo.city, addressInfo.town);
		//$stationEditor.delay(2000);
		$stationEditor.promise().done(function() {
			$stationEditor.val(addressInfo.stationid);
		});
		
		$consigneeEditor.focus();
	};
	
	this.setDefault = function(id) {
		$.ajax({
			url: mainWeb + "/front/member/addressDefault",
			data: {'id': id},
			dataType: 'json',
			success: function(data) {
				if (data.result) {
					loadAddressTable();
				}
			}
		});
	};
	
	var getAddressById = function(id) {
		for (var index = 0; index < addresses.length; index++) {
			if (id == addresses[index].recid) {
				return addresses[index];
			}
		}
	};
	var resetEditors = function() {
		$('#formtitle').html('�����ջ���ַ');
		$('#resetAddressBtn').click();
	};
	
	var doSave = function() {
		if (!validateInput()) return;
		if (!isEmptyString($('.addressform #recid').val())) {
			$.ajax({
				//type: 'post',
				url: mainWeb + "/front/member/modifyAddress",
				data: $('#addressform').serialize(),
				dataType: 'json',
				success: function(data) {
					if (data.result) {
						loadAddressTable();
						resetEditors();
					}
				}
			});
		} else {
			$.ajax({
				//type: 'post',
				url: mainWeb + "/front/member/addAddress",
				data: $('#addressform').serialize(),
				dataType: 'json',
				success: function(data) {
					if (data.result) {
						loadAddressTable();
						resetEditors();
					}
				}
			});
		}
	};
	
	var loadStation = function(areaCode) {
		$.ajax({
			url: mainWeb + "/front/common/getStation",
			data: {'areaCode': areaCode},
			dataType: 'json',
			success: function(data) {
				if (data.total > 0) {
					$stationPrompt.empty();
					$stationEditor.empty();
					var stations = data.rows;
					for (var index = 0; index < stations.length; index++) {
						var $option = $("<option value='" + stations[index].recid + "'>" + stations[index].stationName + "</option>");
						$option.appendTo($stationEditor);
					}
				} else {
					$stationEditor.empty();
					$stationPrompt.html('��ǰѡ��ĵ���û��վ�㣬������ѡ�����');
				}
			}
		});
	};
	
	var validateInput = function() {
		if ($consigneeEditor.val() == null || $.trim($consigneeEditor.val()) == "") {
			$consigneePrompt.html('�ջ�����������Ϊ��');
			return false;
		}
		if ($stationEditor.val() == null || $.trim($stationEditor.val()) == "") {
			$stationPrompt.html('վ�㲻��Ϊ��');
			return false;
		}
		if ($addressEditor.val() == null || $.trim($addressEditor.val()) == "") {
			$addressPrompt.html('��ַ����Ϊ��');
			return false;
		}
		var value = $mobileEditor.val();
		if (value == null || $.trim(value) == "") {
			$mobilePrompt.html('�ֻ����벻��Ϊ��');
			return false;
		} else if (!/^1[3-8]\d{9}$/.test($.trim(value))) {
			$mobilePrompt.html('�ֻ������ʽ����');
			return false;
		}
		
		return true;
	};
	var isEmptyString = function(string) {
		if (string == null || string == "") {
			return true;		
		} else {
			return false;
		}
	};
	var registeValidates = function() {
		// 
		$consigneeEditor.bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$consigneePrompt.html('�ջ�����������Ϊ��');
			}
		});
		$consigneeEditor.bind('focusin', function() {
			$consigneePrompt.empty();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		$addressEditor.bind('focusout', function() {
			if ($(this).val() == null || $.trim($(this).val()) == "") {
				$addressPrompt.html('��ַ����Ϊ��');
			}
		});
		$addressEditor.bind('focusin', function() {
			$addressPrompt.empty();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		/*
		$postcodeEditor.bind('focusout', function() {
			//
			var value = $(this).val();
			if (value == null || $.trim(value) == "") {
				$postcodePrompt.html('�������벻��Ϊ��');
			} else if (!/[^0]\d{5}/.test($.trim(value))) {
				$postcodePrompt.html('���������ʽ����');
			}
		});
		$postcodeEditor.bind('focusin', function() {
			$postcodePrompt.empty();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		*/
		//
		$mobileEditor.bind('focusout', function() {
			var value = $(this).val();
			if (value == null || $.trim(value) == "") {
				$mobilePrompt.html('�ֻ����벻��Ϊ��');
			} else if (!/^1[3-8]\d{9}$/.test($.trim(value))) {
				$mobilePrompt.html('�ֻ������ʽ����');
			}
		});
		$mobileEditor.bind('focusin', function() {
			$mobilePrompt.empty();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		//
		/*
		$telephoneEditor.bind('focusout', function() {
			var value = $(this).val();
			if (value == null || $.trim(value) == "") {
				$telephonePrompt.html('�绰���벻��Ϊ��');
			} else if (!/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test($.trim(value))) {
				$telephonePrompt.html('�绰�����ʽ����');
			}
		});
		$telephoneEditor.bind('focusin', function() {
			$telephonePrompt.empty();
			inputStatus = inputStatus == 0 ? inputStatus -= 1 : inputStatus;
		});
		*/
	};

	var loadAddressTable = function() {
		$.ajax({
			type: 'post',
			url: mainWeb + '/front/member/getAddressList',
			dataType: 'json',
			success: function(data) {
				if (data.total > 0) {
					addresses = data.rows;
					//var html = "<table id='addresstable' border='0' cellspacing='0' cellpadding='0' class='table_a'>";
					var html = "";
					html += "<caption>�ѱ������Ч��ַ</caption>";
					html += "<tr>";
					html += "	<th width='70px'>�ջ���</th>";
					html += "	<th width='140px'>���ڵ���</th>";
					html += "	<th width='200px'>�ֵ���ַ</th>";
					html += "	<th width='100px'>վ��</th>";
					html += "	<th width='70px'>�ʱ�</th>";
					html += "	<th width='110px'>�ֻ�</th>";
					html += "	<th width='110px'>�绰</th>";
					html += "	<th width='140px;'>����</th>";
					html += " </tr>";
					for (var index = 0; index < addresses.length; index++) {
						if (addresses[index].isdefault) {
							html += "<tr id='" + addresses[index].recid + "' class='addressdefault'>";
						} else {
							html += "<tr id='" + addresses[index].recid + "' >";
						}
						html += "<td width='70px'>" + addresses[index].consignee + "</td>";
						html += "<td width='140px'>" + addresses[index].provinceTitle + addresses[index].cityTitle + addresses[index].townTitle + "</td>";
						html += "<td width='200px'>" + addresses[index].address + "</td>";
						var stationName = "";
						if (addresses[index].stationName != null) {
							stationName = addresses[index].stationName;
						}
						html += "<td width='100px'>" + stationName + "</td>";
						var postcode = "";
						if (addresses[index].postcode != null) {
							postcode = addresses[index].postcode;
						}
						html += "<td width='70px'>" + postcode + "</td>";
						html += "<td width='110px'>" + addresses[index].mobile + "</td>";
						var telephone = "";
						if (addresses[index].telephone != null && addresses[index].telephone != "null") {
							telephone = addresses[index].telephone;
						}
						html += "<td width='110px'>" + telephone + "</td>";
						html += "<td width='130px;'>";
						if (!addresses[index].isdefault) {
							html += "	<a href='javascript:void(0)' onclick=\"_deliverAddressApp.setDefault('" + addresses[index].recid + "')\">��ΪĬ��</a> |";
						}
						html += "	<a href='#' style='text-decoration: none' onclick=\"_deliverAddressApp.modify('" + addresses[index].recid + "')\">�޸�</a> |";
						html += "	<a href='javascript:void(0)' style='text-decoration: none' onclick=\"_deliverAddressApp.del('" + addresses[index].recid + "')\">ɾ��</a>";
						html += "</td>";
						html += "</tr>";
					}
					//html += "</table>";
					$('#addresstable').html(html);
					//$('.tableArea').html(html);
				} else {
					addresses = new Array();
					//$('.tableArea').empty();
					$('#addresstable').empty();
				}
			}
		});
	};
	
	var init = function() {
		$('#formtitle').html('�����ջ���ַ');
		loadAddressTable();
		registeValidates();
		$consigneeEditor.focus();
		areaProvider = new AreaProvider('deliver_province', 'deliver_city', 'deliver_town');
		areaProvider.addSelectionListener(function() {
			loadStation(areaProvider.getSelectedDistrictCode());
		});
		$('.addressform #saveAddressBtn').click(function() {
			doSave();
		});
	};
	
	init();
}
</script>
</head>
<body>
<div id="shortcut">
	<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
</div>
<div id='chargecontent' class='w'>
	<div id="o-header">
		<jsp:include page="/pub/main/header.jsp" flush="true" />
		<jsp:include page="/pub/common/productlistmenu.jsp" flush="true" />
	</div>
	<div class="main">
		<div class="breadcrumb">
			<span class="my7"><strong><a href="#">�ҵ�7��</a></strong></span><span>&nbsp;&gt;&nbsp;�ջ���ַ</span>
		</div>
		<div class='left'>
			<jsp:include page="left.jsp" flush="true" />
		</div>
		<div class='right'>
			<!-- div class="o-mt"><strong>�ջ���ַ</strong></div> -->
			<div id="ordercenter-data-title" style="background-image: url('<%=basePath%>/images/page/my7L-myorderbg.png')">
				<h3>
					�ջ���ַ
				</h3>
			</div>
			<div class='addressform'>
			<form name='addressform' id='addressform' action="<%=basePath %>/front/member/addAddress">
				<div class='labelArea'><label class='title' id='formtitle'></label></div>
				<div class='inputArea'><input type='hidden' name='recid' id='recid'/></div>
				
				<div class='labelArea'><label><em>*</em>�ջ���������</label></div>
				<div class='inputArea'>
					<input type='text' name='consignee' id='da_consignee'/>
					<div class='prompt' id='prompt_consignee'></div>
				</div>
				
				<div class='labelArea'><label><em>*</em>���ڵ�����</label></div>
				<div class='inputArea'>
					<select id='deliver_province' name='province'></select>
					<select id='deliver_city' name='city'></select>
					<select id='deliver_town' name='town'></select>
				</div>
				
				<div class='labelArea'><label><em>*</em>վ�㣺</label></div>
				<div class='inputArea'>
					<select id="da_stationid" name='stationid'></select>
					<div class='prompt' id='prompt_stationid'></div>
				</div>
				
				<div class='labelArea'><label><em>*</em>��ַ��</label></div>
				<div class='inputArea' style='height: 78px;'>
					<textarea name='address' id='da_address'></textarea>
					<div class='prompt' id='prompt_address'></div>
				</div>
				
				<div class='labelArea'><label><em>*</em>�ֻ����룺</label></div>
				<div class='inputArea'>
					<input type='text' name='mobile' id='da_mobile'/>
					<div class='prompt' id='prompt_mobile'></div>
				</div>
				
				<div class='labelArea'><label>�������룺</label></div>
				<div class='inputArea'>
					<input type='text' name='postcode' id='da_postcode'/>
					<div class='prompt' id='prompt_postcode'></div>
				</div>
				
				<div class='labelArea'><label>�绰���룺</label></div>
				<div class='inputArea'>
					<input type='text' name='telephone' id='da_telephone'/>
					<div class='prompt' id='prompt_telephone'></div>
				</div>
				
				<!-- 
				<div class='labelArea'><label>��ΪĬ�ϣ�</label></div>
				<div class='inputArea'><input type='checkbox' name='isdefault' id='da_isdefault'/></div>
				 -->
				<div class='buttonArea'>
					<input type='button' id='saveAddressBtn' value="����"/>
					<input type='reset' id='resetAddressBtn' style='display: none'/>
				</div>
			</form>
			</div>
			<div class="tableArea">
				<table id="addresstable" border="0" cellspacing="0" cellpadding="0" class="table_a">
					<!--
					<caption>�ѱ������Ч��ַ</caption>
					<tr class="thead-tbl-grade">
						<th width="70px">�ջ���</th>
						<th>���ڵ���</th>
						<th>�ֵ���ַ</th>
						<th>վ��</th>
						<th>�ʱ�</th>
						<th>�ֻ�</th>
						<th>�绰</th>
						<th>����</th>
                    </tr>
                    -->
					<!-- tr class="thead-tbl-address" id="648564449" >
						<td class="cell-align-center">����</td>
						<td  width="150px">���� ������ ��������</td>
						<td width="250px">����������ɽ����ж�</td>
						<td width="70px">400000</td>
						<td width="70px">13888888888</td>
						<td width="130px;">
							<a href="#" onclick="selectDeliver(648564449)">��ΪĬ��</a> |
							<a href="#" onclick="selectDeliver(648564449)">�޸�</a> |
							<a href="#" onclick="del(648564449)">ɾ��</a>
						</td>
                     </tr>
                     <tr class="thead-tbl-address" id="648564449" >
						<td width="70px">����</td>
						<td width="150px">���� ������ ��������</td>
						<td width="250px">����������ɽ����ж�</td>
						<td width="70px">400000</td>
						<td width="70px">13888888888</td>
						<td width="130px;">
							<a href="#" onclick="selectDeliver(648564449)">��ΪĬ��</a> |
							<a href="#" onclick="selectDeliver(648564449)">�޸�</a> |
							<a href="#" onclick="del(648564449)">ɾ��</a>
						</td>
                     </tr>
                     <tr class="thead-tbl-address" id="648564449" >
						<td width="70px">����</td>
						<td width="150px">���� ������ ��������</td>
						<td width="250px">����������ɽ����ж�</td>
						<td width="70px">400000</td>
						<td width="70px">13888888888</td>
						<td width="130px;">
							<a href="#" onclick="selectDeliver(648564449)">��ΪĬ��</a> |
							<a href="#" onclick="selectDeliver(648564449)">�޸�</a> |
							<a href="#" onclick="del(648564449)">ɾ��</a>
						</td>
                     </tr> -->
				</table>
			</div>
		</div>
	</div>
	<div id="serviceFloors">
		<jsp:include page="/pub/main/service.jsp" flush="true" />
	</div>
	<div id="copyRight">
		<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
	</div>
</div>
</body>
</html>