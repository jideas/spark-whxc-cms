<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>送货价格</title>
	</head>
	<body>
		<div class="easyui-layout" fit="true" border="false">
			<div region="center" title="单价设置" style="padding: 5px;">
				<!-- begin of 资源编辑 -->
				<form id="pricePurForm" method="post" modelAttribute="resource">
					<input type="hidden" name="id" />
					<input type="hidden" name="parentId" />
					<table>
						<tr>
							<td>
								单次价格：
							</td>
							<td>
								<input type="text" id="dprice0" name="price"
									style="width: 200px;" class="easyui-validatebox"
									required="true" missingMessage="必填项" />
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td style="text-align: right;">
								<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
									onclick="pricePurAction.pricePurSaveOrUpdate()">保 存</a>
							</td>
						</tr>
					</table>
				</form>
				<!-- begin of 资源编辑 -->
			</div>
			<!-- end of 资源管理 -->
		</div>
		<script type="text/javascript">
			var pricePurAction;
			$(function() {
			$.post('<%=mainWeb %>/admin/delivery/getPrice', function(result) {	 
						if (result.result) {
							$('#dprice0').val(result.returnObj);
						}
					}, 'json');

			function PricePurAction() {
				this.pricePurSaveOrUpdate = function() { 
					$('#pricePurForm').form('submit', {
								url : '<%=mainWeb %>/admin/delivery/savePrice',
								onSubmit : function() {
									return priceconfigValidateForm(); // 验证表单
								},
								success : function(data) {
									data = eval ("("+data+")")
									$.messager
											.alert('提示', data.message, 'info');// 保存成功给出提示信息
								}
							});
				}

				function priceconfigValidateForm() {
					if ($.trim($('#dprice0').val()) == '') {
						$.messager.alert('提示', '请填写单次价格!', 'warning'); // 面值类型未选择
						return false;
					}
				}
			}
			pricePurAction = new PricePurAction();
		});
		</script>
	</body>
</html>










