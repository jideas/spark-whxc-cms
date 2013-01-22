var ordercenter = new orderCenter();
function orderCenter() {
	this.refreshData = function(page, pa) {
		var month = $("#month").val();
		var selecttor = $("#effected").val();
		var searchText = $("#searchwords").val();
		$.ajax({
					url : basePath + '/front/order/getOrdersHtml',
					async : false,
					cache : false,
					data : {
						"month" : month,
						"selecttor" : selecttor,
						"searchText" : searchText,
						"page" : page
					},
					success : function(data) {
						if (data.success) {
							$("#dataTable").html(data.html.toString());
							if (!pa) {
								var paramters = {
									parentId : 'pagin_div',
									count : data.totalCount,
									pageSize : 20,
									isActionOnLoad : false,
									actionListener : function(pageIndex) {
										ordercenter.pageAction(pageIndex);
									}
								}
								new CmsPaging(paramters);
							}
						} else {
							if (data.noLogin) {
								var url = basePath + "/pub/sub/login";
								window.location.href = url;
							}
						}
					}
				});
	}
	this.toHome = function() {
		window.location.href = basePath;
	}

	this.pageAction = function(pageIndex) {
		ordercenter.refreshData(pageIndex, true);
	}
}