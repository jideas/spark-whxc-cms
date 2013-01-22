<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�ͻ��۸�</title>
	</head>
	<body>
		<div class="easyui-layout" fit="true" border="false">
			<div region="center" title="��������" style="padding: 5px;">
				<!-- begin of ��Դ�༭ -->
				<form id="pricePurForm" method="post" modelAttribute="resource">
					<input type="hidden" name="id" />
					<input type="hidden" name="parentId" />
					<table>
						<tr>
							<td>
								���μ۸�
							</td>
							<td>
								<input type="text" id="dprice0" name="price"
									style="width: 200px;" class="easyui-validatebox"
									required="true" missingMessage="������" />
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td style="text-align: right;">
								<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
									onclick="pricePurAction.pricePurSaveOrUpdate()">�� ��</a>
							</td>
						</tr>
					</table>
				</form>
				<!-- begin of ��Դ�༭ -->
			</div>
			<!-- end of ��Դ���� -->
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
									return priceconfigValidateForm(); // ��֤��
								},
								success : function(data) {
									data = eval ("("+data+")")
									$.messager
											.alert('��ʾ', data.message, 'info');// ����ɹ�������ʾ��Ϣ
								}
							});
				}

				function priceconfigValidateForm() {
					if ($.trim($('#dprice0').val()) == '') {
						$.messager.alert('��ʾ', '����д���μ۸�!', 'warning'); // ��ֵ����δѡ��
						return false;
					}
				}
			}
			pricePurAction = new PricePurAction();
		});
		</script>
	</body>
</html>










