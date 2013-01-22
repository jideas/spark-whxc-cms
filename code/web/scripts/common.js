var returnPath;
var returnWidth;
var returnHeight;
var lastImg;
function selectOneImage(path, img, width, height) {
	returnPath = path;
	returnWidth = width;
	returnHeight = height;
	img.style["border"] = "blue solid 3px";
	if (lastImg) {
		lastImg.style["border"] = "blue solid 0px";
	}
	lastImg = img;
}

function ChooseImagesWindow(imageSearchText) {
	var $win = $("<div></div>");
	var $leftArea = $("<div></div>");
	var $rightArea = $("<div ></div>");
	var $bottomArea = $("<div style='background-color: #EEF;'></div>");
	var lastSelectNode;
	var $tree = null;
	var closeListener = function() {
		$win.remove();
	};
	this.addConfirmActionListener = function(fn) {
		$win.unbind("imageSelected");
		$win.bind("imageSelected", function(event, value) {
					fn(value.path, value.width, value.height);
				});

	};

	var loadImagesTable = function(node) {
		if ((node && node.attributes.code == '0')
				|| (imageSearchText && $.trim(imageSearchText) != '')) {
			lastSelectNode = node;
			if (null == imageSearchText || imageSearchText == 'null') {
				imageSearchText = '';
			}
			var nodeId;
			if (node && node.attributes.code == '0') {
				nodeId = node.id;
			}
			$.post(mainWeb + '/admin/images/getImages', {
						folderId : nodeId,
						text : imageSearchText
					}, function(result) {
						if (!result || result.length == 0) {
							var str = '<table width="100%" style="hieght: 432px;overflow-y:scroll;"> ';
							str = str
									+ '<tr height="30px;"><td colspan="3" align="right" width="100%"><input type="text" id="imageSearchInput" value="'
									+ imageSearchText
									+ '" style="width:150px;"/><input id="imageSearchButton" type="button" value="搜索" onclick=""/></td></tr>';
							str = str + '</table> ';
							$rightArea.html(str);
							$('#imageSearchButton').click(function() {
								imageSearchText = $.trim($('#imageSearchInput').val());
								loadImagesTable(lastSelectNode)
							});
							return;
						}
						var str = '<table width="100%"> ';
						str = str
								+ '<tr height="30px;"><td colspan="3" align="right" width="100%"><input id="imageSearchInput" type="text" value="'
								+ imageSearchText
								+ '" style="width:150px;"/><input id="imageSearchButton" type="button" value="搜索" onclick=""/></td></tr>';
						var x = 0;
						for (var i = 0; i < result.length;) {
							str = str + '<tr> ';
							if (i < result.length) {
								str = str
										+ '<td width="20%" align="center"> &nbsp;';
								str = str
										+ '<img src='
										+ mainWeb
										+ result[i].imgUrl
										+ ' width="'
										+ result[i].width
										+ '" height="'
										+ result[i].height
										+ '" style="cursor:hand" onclick="selectOneImage(\''
										+ result[i].imgUrl + '\',this,'
										+ result[i].realWidth + ','
										+ result[i].realHeight + ');"/>';
								str = str + '</td>';
								i++;
							} else {
								str = str + '<td width="20%"> &nbsp;</td>';
								i++;
							}
							if (i < result.length) {
								str = str
										+ '<td width="20%" align="center"> &nbsp;';
								str = str
										+ '<img src='
										+ mainWeb
										+ result[i].imgUrl
										+ ' width="'
										+ result[i].width
										+ '" height="'
										+ result[i].height
										+ '" style="cursor:hand" onclick="selectOneImage(\''
										+ result[i].imgUrl + '\',this,'
										+ result[i].realWidth + ','
										+ result[i].realHeight + ');"/>';
								str = str + '</td>';
								i++;
							} else {
								str = str + '<td width="20%"> &nbsp;</td>';
								i++;
							}
							if (i < result.length) {
								str = str
										+ '<td width="20%" align="center"> &nbsp;';
								str = str
										+ '<img src='
										+ mainWeb
										+ result[i].imgUrl
										+ ' width="'
										+ result[i].width
										+ '" height="'
										+ result[i].height
										+ '" style="cursor:hand" onclick="selectOneImage(\''
										+ result[i].imgUrl + '\',this,'
										+ result[i].realWidth + ','
										+ result[i].realHeight + ');"/>';
								str = str + '</td>';
								i++;
							} else {
								str = str + '<td width="20%"> &nbsp;</td>';
								i++;
							}
							str = str + '</tr> ';
							str = str + '<tr> ';
							if (x < result.length) {
								str = str
										+ '<td width="20%" align="center"> &nbsp;';
								str = str + result[x].title;
								str = str + '</td>';
								x++;
							} else {
								str = str
										+ '<td width="20%" align="center"> &nbsp;</td>';
								x++;
							}
							if (x < result.length) {
								str = str
										+ '<td width="20%" align="center"> &nbsp;';
								str = str + result[x].title;
								str = str + '</td>';
								x++;
							} else {
								str = str
										+ '<td width="20%" align="center"> &nbsp;</td>';
								x++;
							}
							if (x < result.length) {
								str = str
										+ '<td width="20%" align="center"> &nbsp;';
								str = str + result[x].title;
								str = str + '</td>';
								x++;
							} else {
								str = str
										+ '<td width="20%" align="center"> &nbsp;</td>';
								x++;
							}
							str = str + '</tr> ';
						}
						str = str + '</table> ';
						$rightArea.html(str);
					}, 'json');
		} else {
			var str = '<table width="100%"> ';
			str = str
					+ '<tr height="30px;"><td colspan="3" align="right" width="100%"><input type="text" id="imageSearchInput" value="'
					+ imageSearchText
					+ '" style="width:150px;"/><input id="imageSearchButton" type="button" value="搜索" onclick=""/></td></tr>';
			str = str + '</table> ';
			$rightArea.html(str);
		}
		$('#imageSearchButton').click(function() {
					imageSearchText = $.trim($('#imageSearchInput').val());
					loadImagesTable(lastSelectNode)
				});
	};

	var renderLeftArea = function() {
		$tree = $("<div></div>");
		$tree.css('height', '432px');
		$tree.css('overflow-y', 'scroll');
		$tree.appendTo($leftArea);
		$tree.tree({
					url : mainWeb + '/admin/folder/getFolders',
					border : false,
					onSelect : function(node) {
						loadImagesTable(node);
					}
				});
	};

	var renderBottomArea = function() {
		$saveButton = $("<input type='button' value='确定'/>");
		$cancelButton = $("<input type='button' value='取消'>");

		$saveButton.appendTo($bottomArea);
		$cancelButton.appendTo($bottomArea);

		$saveButton.click(function() {
					var value = {
						path : returnPath,
						width : returnWidth,
						height : returnHeight
					};
					$win.trigger("imageSelected", value);
					returnPath = '';
					returnWidth = '';
					returnHeight = '';
					lastImg = null;
					$win.window('close');
				});

		$cancelButton.click(function() {
					$win.window('close');
				});
	};

	var renderInit = function() {
		/*
		 * $leftArea.css('float', 'left'); $leftArea.css('border', '#8DB2E3
		 * solid 1px'); $leftArea.css('width', '30%'); $leftArea.css('height',
		 * '92%');
		 * 
		 * $rightArea.css('float', 'left'); $rightArea.css('border', '#8DB2E3
		 * solid 1px'); $rightArea.css('width', '69%'); $rightArea.css('height',
		 * '92%'); $rightArea.css('overflow-y', 'scroll');
		 * 
		 * $bottomArea.css('float', 'left'); $bottomArea.css('width', '100%');
		 * $bottomArea.css('height', '7%'); $bottomArea.css('text-align',
		 * 'right');
		 */
		$leftArea.css('float', 'left');
		$leftArea.css('border', 'red solid 0px');
		$leftArea.css('width', '200px');
		$leftArea.css('height', '432px');

		$rightArea.css('float', 'left');
		$rightArea.css('border', 'red solid 0px');
		$rightArea.css('width', '530px');
		$rightArea.css('height', '432px');
		$rightArea.css('overflow-y', 'scroll');

		$bottomArea.css('float', 'left');
		$bottomArea.css('width', '100%');
		$bottomArea.css('height', '30px');
		$bottomArea.css('text-align', 'right');

		$win.appendTo('body');
		$leftArea.appendTo($win);
		$rightArea.appendTo($win);
		$bottomArea.appendTo($win);

		$win.window({
					width : 750,
					height : 500,
					title : '选择图片',
					onClose : closeListener,
					modal : true
				});
	};

	var init = function() {
		renderInit();
		renderLeftArea();
		renderBottomArea();
		loadImagesTable(null);
	};

	init();
}
function strToJson(str) {
	var json = eval('(' + str + ')');
	return json;
}

/**
 * 选择商品窗口
 * 
 * @param isSingle
 *            是否单选，默认为false
 * @param paramters:
 *            url 商品来源URL（不指定时，自己获取所有商品） isPublished true or false
 */
function ChooseGoodsWindow(isSingle, paramters) {
	var $win = $("<div></div>");
	var $leftArea = $("<div></div>");
	var $rightArea = $("<div class='goodswinleft'></div>");
	var $bottomArea = $("<div style='background-color: #EEF;'></div>");

	var searchKey = null;
	var selectedCategoryId = null;

	var $tree = null;
	var $searchText = null;
	var $goodsTable = null;

	var isSinglSelect = false;
	var isPublishedOnly = true;

	this.addConfirmActionListener = function(fn) {
		$win.unbind("confirm");
		$win.bind("confirm", function() {
					var selectRows = $goodsTable.datagrid('getSelections');
					if (isSinglSelect) {
						fn(selectRows[0]);
					} else {
						fn(selectRows);
					}
				});

	};

	var closeListener = function() {
		$win.remove();
	};

	/*
	 * var loadGoodsTable = function(categoryId) { if (paramters == null ||
	 * typeof(paramters.url) == "undefined" || paramters.url == null) {
	 * $.post(mainWeb + '/admin/goods/getGoodsList', { "categoryId" : categoryId },
	 * function(data) { $goodsTable.datagrid('loadData', data.rows); }); } else {
	 * $.post(paramters.url, { "categoryId" : categoryId }, function(data) {
	 * $goodsTable.datagrid('loadData', data.rows); }); } };
	 */
	var renderLeftArea = function() {
		$tree = $("<div></div>");
		$tree.css('height', '432px');
		$tree.css('overflow-y', 'scroll');
		$tree.appendTo($leftArea);

		$tree.tree({
					url : mainWeb + '/admin/goods/getLightGoodsCategoryTree',
					// url : mainWeb + '/admin/goods/getTreeChildrenNode',
					// fit: true,
					border : false,
					onSelect : function(node) {
						selectedCategoryId = node.id;
						$goodsTable.datagrid('reload', {
									"categoryId" : node.id
								});
					},
					onLoadSuccess : function(node, data) {
						var rootNodes = data[0].children;
						$tree.tree('collapseAll');
						if (rootNodes != null && rootNodes.length > 1) {
							var tNode = $tree.tree('find', rootNodes[0].id);
							$tree.tree('expandTo', tNode.target);
						}
					}
				});
	};

	var renderRightArea = function() {
		// right top
		$searchText = $("<input type='text' style='width:200px;height:18px;'/>");
		var $searchButton = $("<a href='#' class='easyui-linkbutton' iconCls='icon-search' id='goods_search_action'>搜索</a>");
		var $rightTopArea = $("<div></div>");
		$rightTopArea.appendTo($rightArea);
		$rightTopArea.css('height', '32px');
		$rightTopArea.css('text-align', 'right');
		$rightTopArea.css('background-color', '#EEF');
		$searchText.appendTo($rightTopArea);
		var $temp = $("<label style='display:-moz-inline-box;display:inline-block;width=10px;'></label>");
		$temp.appendTo($rightTopArea);
		$searchButton.appendTo($rightTopArea);
		$searchText.css('display', 'inline');
		$searchButton.css('display', 'inline-block');
		$searchButton.linkbutton({
					iconCls : 'icon-search'
				});
		$searchButton.unbind('click');
		$searchButton.click(function() {
					searchKey = $searchText.val();
					$goodsTable.datagrid('reload', {
								"categoryId" : selectedCategoryId,
								'searchText' : searchKey
							});
				});
		$searchText.unbind('keydown');
		$searchText.bind('keydown', function(event) {
					if (event.which == 13) {
						$searchButton.click();
					}
				});
		// right content
		$goodsTable = $("<div></div>");
		$goodsTable.css('height', '400px');
		$goodsTable.appendTo($rightArea);
		if (isSingle) {
			isSinglSelect = true;
		}
		var tableDataUrl = null;

		if (!paramters || !paramters.url) {
			tableDataUrl = mainWeb + '/admin/goods/getGoodsList?isPublised='
					+ (isPublishedOnly ? 1 : 0);
		} else {
			tableDataUrl = paramters.url;
		}
		$goodsTable.datagrid({
					pagination : true,
					// fit: true,
					fitColumns : true,
					singleSelect : isSinglSelect,
					url : tableDataUrl,
					border : false,
					rownumbers : true,
					columns : [[{
								field : 'recid',
								checkbox : true,
								width : 50
							}, {
								field : 'goodscode',
								title : '商品编号',
								width : 100
							}, {
								field : 'goodsno',
								title : '商品条码',
								width : 100
							}, {
								field : 'goodsname',
								title : '商品名称',
								width : 160
							}, {
								field : 'goodsspec',
								title : '规格',
								width : 100,
								align : "left"
							}, {
								field : 'goodsunit',
								title : '单位',
								width : 60,
								align : "center"
							}, {
								field : 'originalprice',
								title : '原价',
								width : 80,
								align : "right"
							}, {
								field : 'realprice',
								title : '现价',
								width : 80,
								align : "right"
							}]]
				});

		// loadGoodsTable(null);
	};

	var renderBottomArea = function() {
		$saveButton = $("<input type='button' value='确定'/>");
		$cancelButton = $("<input type='button' value='取消'>");

		$saveButton.appendTo($bottomArea);
		$cancelButton.appendTo($bottomArea);

		$saveButton.click(function() {
					var selectRows = $goodsTable.datagrid('getSelections');
					if (selectRows == null || selectRows.length < 1) {
						alert('请选择商品。');
					} else {
						$win.trigger("confirm");
						$win.window('close');
					}
				});

		$cancelButton.click(function() {
					$win.window('close');
				});
	};

	var renderInit = function() {
		$leftArea.css('float', 'left');
		$leftArea.css('border', '#8DB2E3 solid 0px');
		// $leftArea.css('width', '27%');
		$leftArea.css('width', '243px');
		$leftArea.css('height', '412px;');
		// $leftArea.css('overflow-y', 'scroll');

		$rightArea.css('float', 'left');
		$rightArea.css('border', '#8DB2E3 solid 0px');
		// $rightArea.css('width', '73%');
		$rightArea.css('width', '637px');
		$rightArea.css('height', '432px;');

		$bottomArea.css('float', 'left');
		$bottomArea.css('width', '100%');
		$bottomArea.css('height', '30px');
		$bottomArea.css('text-align', 'right');

		$win.appendTo('body');
		$leftArea.appendTo($win);
		$rightArea.appendTo($win);
		$bottomArea.appendTo($win);

		$win.window({
					width : 900,
					// height : 482,
					height : 500,
					collapsible : false,
					resizable : true,
					minimizable : false,
					maximizable : false,
					title : '选择商品',
					onClose : closeListener,
					modal : true
				});
	};

	var init = function() {
		if (typeof(paramters) == "undefined") {
			paramters == null;
		} else if (paramters.hasOwnProperty("isPublished")) {
			isPublishedOnly = paramters.isPublished;
		}
		renderInit();
		renderLeftArea();
		renderRightArea();
		renderBottomArea();
	};

	init();
}

/**
 * 选择商品窗口
 */
function ChooseGoodsWindowByCategory(categoryId) {
	var $win = $("<div></div>");
	var $rightArea = $("<div class='goodswinleft'></div>");
	var $bottomArea = $("<div style='background-color: #EEF;'></div>");

	var searchKey = null;

	var $searchText = null;
	var $goodsTable = null;

	var isSinglSelect = false;

	this.addConfirmActionListener = function(fn) {
		$win.unbind("confirm");
		$win.bind("confirm", function() {
					var selectRows = $goodsTable.datagrid('getSelections');
					if (isSinglSelect) {
						fn(selectRows[0]);
					} else {
						fn(selectRows);
					}
				});

	};

	var closeListener = function() {
		$win.remove();
	};

	var renderRightArea = function() {
		// right top
		$searchText = $("<input type='text' style='width:200px;height:18px;'/>");
		var $searchButton = $("<a href='#' class='easyui-linkbutton' iconCls='icon-search' id='goods_search_action'>搜索</a>");
		var $rightTopArea = $("<div></div>");
		$rightTopArea.appendTo($rightArea);
		$rightTopArea.css('height', '32px');
		$rightTopArea.css('text-align', 'right');
		$rightTopArea.css('background-color', '#EEF');
		$searchText.appendTo($rightTopArea);
		var $temp = $("<label style='display:-moz-inline-box;display:inline-block;width=10px;'></label>");
		$temp.appendTo($rightTopArea);
		$searchButton.appendTo($rightTopArea);
		$searchText.css('display', 'inline');
		$searchButton.css('display', 'inline-block');
		$searchButton.linkbutton({
					iconCls : 'icon-search'
				});
		$searchButton.unbind('click');
		$searchButton.click(function() {
					searchKey = $searchText.val();
					$goodsTable.datagrid('reload', {
								'searchText' : searchKey
							});
				});
		$searchText.unbind('keydown');
		$searchText.bind('keydown', function(event) {
					if (event.which == 13) {
						$searchButton.click();
					}
				});
		// right content
		$goodsTable = $("<div></div>");
		$goodsTable.css('height', '400px');
		$goodsTable.appendTo($rightArea);
		$goodsTable.datagrid({
			pagination : true,
			// fit: true,
			fitColumns : true,
			singleSelect : false,
			rownumbers : true,
			url : mainWeb
					+ '/admin/channel/getGoodsForChannelSel?isPublised=1&categoryId='
					+ categoryId + '&channelId=' + categoryId,
			// url: mainWeb + '/admin/goods/getGoodsList?isPublised=' +
			// isPublishedOnly + '&categoryId=' + categoryId,
			border : false,
			columns : [[{
						field : 'recid',
						checkbox : true,
						width : 50
					}, {
						field : 'goodscode',
						title : '商品编号',
						width : 100
					}, {
						field : 'goodsno',
						title : '商品条码',
						width : 100
					}, {
						field : 'goodsname',
						title : '商品名称',
						width : 160
					}, {
						field : 'goodsspec',
						title : '规格',
						width : 100,
						align : "left"
					}, {
						field : 'goodsunit',
						title : '单位',
						width : 60,
						align : "center"
					}, {
						field : 'originalprice',
						title : '原价',
						width : 80,
						align : "right"
					}, {
						field : 'realprice',
						title : '现价',
						width : 80,
						align : "right"
					}]]
		});

		// loadGoodsTable(null);
	};

	var renderBottomArea = function() {
		$saveButton = $("<input type='button' value='确定'/>");
		$cancelButton = $("<input type='button' value='取消'>");

		$saveButton.appendTo($bottomArea);
		$cancelButton.appendTo($bottomArea);

		$saveButton.click(function() {
					var selectRows = $goodsTable.datagrid('getSelections');
					if (selectRows == null || selectRows.length < 1) {
						alert('请选择商品。');
					} else {
						$win.trigger("confirm");
						$win.window('close');
					}
				});

		$cancelButton.click(function() {
					$win.window('close');
				});
	};
	var renderInit = function() {
		$rightArea.css('float', 'left');
		$rightArea.css('border', '#8DB2E3 solid 0px');
		$rightArea.css('width', '100%');
		$rightArea.css('height', '432px;');

		$bottomArea.css('float', 'left');
		$bottomArea.css('width', '100%');
		$bottomArea.css('height', '30px');
		$bottomArea.css('text-align', 'right');

		$win.appendTo('body');
		$rightArea.appendTo($win);
		$bottomArea.appendTo($win);

		$win.window({
					width : 900,
					height : 500,
					collapsible : false,
					resizable : true,
					minimizable : false,
					maximizable : false,
					title : '选择商品',
					onClose : closeListener,
					modal : true
				});
	};

	var init = function() {
		renderInit();
		renderRightArea();
		renderBottomArea();
	};

	init();
}

function GoodsAdvanceSearchWindow(onConfirm) {
	var $win = $("<div></div>");
	var $mostSaleEditor = $("");
	var $popularEditor = $("");
	var $goodsTypeEditor = $("");
	var $vantageTypeEditor = $("");

	var $confirmButton = "";
	var $cancelButton = "";

	var initAction = function() {
		$confirmButton.click(function() {
					var goodsType = $goodsTypeEditor.combobox('getValue');
					var vantageType = $vantageTypeEditor.combobox('getValue');
					var isMostSale = $mostSaleEditor.combobox('getValue');
					var isPopular = $popularEditor.combobox('getValue');
					if ($.trim(goodsType) == '' && $.trim(vantageType) == ''
							&& $.trim(isMostSale) == ''
							&& $.trim(isPopular) == '') {
						$.messager.alert('提示', "请选择搜索内容");
					} else {
						onConfirm({
									advance : "{'goodsType':'" + goodsType
											+ "','goodsVantageType':'"
											+ vantageType + "','isMostSale':'"
											+ isMostSale + "','isPopular':'"
											+ isPopular + "'}"
								});
						$win.window('close');
					}
				});

		$cancelButton.click(function() {
					$win.window('close');
				});
	};

	var initEditors = function() {
		$goodsTypeEditor = $win.find('#goodsType');
		$vantageTypeEditor = $win.find('#vantageType');
		$mostSaleEditor = $win.find('#isMostSale');
		$popularEditor = $win.find('#isPopular');

		$confirmButton = $win.find('#confirm');
		$cancelButton = $win.find('#cancel');

		$goodsTypeEditor.combobox({
					valueField : 'code',
					textField : 'name',
					url : mainWeb + '/admin/goods/getGoodsTypeList',
					required : false,
					editable : false
				});

		$vantageTypeEditor.combobox({
					valueField : 'code',
					textField : 'title',
					url : mainWeb + '/admin/goods/getVantageList',
					required : false,
					editable : false
				});

		$mostSaleEditor.combobox({
					valueField : 'code',
					textField : 'title',
					data : [{
								'code' : '0',
								'title' : '否'
							}, {
								'code' : '1',
								'title' : '是'
							}],
					editable : false
				});

		$popularEditor.combobox({
					valueField : 'code',
					textField : 'title',
					data : [{
								'code' : '0',
								'title' : '否'
							}, {
								'code' : '1',
								'title' : '是'
							}],
					editable : false
				});
		$confirmButton.linkbutton({
					iconCls : 'icon-ok'
				});

		$cancelButton.linkbutton({
					iconCls : 'icon-cancel'
				});

	};

	var closeListener = function() {
		$win.remove();
	};

	var render = function() {
		$win.appendTo('body');
		var html = "<div style='width:100%; *padding: 10px 30px;'>";
		html += "<table style='padding: 10px 30px;'>";
		html += "<tr style='height: 25px;'>";
		html += "<td>";
		html += "商品类型：";
		html += "</td>";
		html += "<td>";
		html += "<input type='text' id='goodsType'/>";
		html += "</td>";
		html += "</tr>";

		html += "<tr style='height: 25px;'>";
		html += "<td>";
		html += "积分类型：";
		html += "</td>";
		html += "<td>";
		html += "<input type='text' id='vantageType'/>";
		html += "</td>";
		html += "</tr>";

		html += "<tr style='height: 25px;'>";
		html += "<td>";
		html += "是否热卖：";
		html += "</td>";
		html += "<td>";
		html += "<input type='text' id='isMostSale'/>";
		html += "</td>";
		html += "</tr>";

		html += "<tr style='height: 25px;'>";
		html += "<td>";
		html += "是否人气：";
		html += "</td>";
		html += "<td>";
		html += "<input type='text' id='isPopular'/>";
		html += "</td>";
		html += "</tr>";
		html += "</table>";
		html += "</div>";

		html += "<div style='heigth: 30px; width: 100%; display: block;text-align: right;'>";
		html += "<a id='confirm'>确定</a>";
		html += "<a id='cancel'>取消</a>";
		html += "</div>";
		$win.html(html);
		$win.window({
					width : 350,
					height : 200,
					collapsible : false,
					resizable : true,
					minimizable : false,
					maximizable : false,
					title : '高级搜索',
					onClose : closeListener,
					modal : true
				});
	};

	var init = function() {
		render();
		initEditors();
		initAction();
	};

	init();
}