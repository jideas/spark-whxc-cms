<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��������</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of �������� -->
		<form id="hotSearchForm" method="post"
			modelAttribute="popularKeyWordsVo">
			<input type="hidden" name="recid" />
			<table>
				<tr>
					<td colspan="2">
						��ʾ���ؼ���֮��ʹ��<font color="red">#</font>�Ÿ������磺��Ǽ���#���������#��ź��#��֭����#������Ѽ
					</td>
				</tr>
				<tr>
					<td>
						�ؼ��ʣ�
					</td>
					<td>
						<textarea rows="5" cols="55" name="popularKeyWords"></textarea>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td style="text-align: right;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
							onclick="hotSearchAction.hotSearchSaveOrUpdate()">�� ��</a>
					</td>
				</tr>
			</table>
		</form>
		<!-- begin of �������� -->
		<script type="text/javascript">
		//��ʼ��
		$(function(){
			$.ajax({
			  	url: "<%=mainWeb%>/admin/channel/getPopularKeyWords",
			  	type: "post",
			  	complete: function(data, textStatus, jqXHR) {
			  		data = eval("("+data.responseText+")");
			  		$("#hotSearchForm input[name='recid']").val(data.recid);
			  		$("#hotSearchForm textarea[name='popularKeyWords']").val(data.popularKeyWords);
			  	}
			},"JSON");
		});
		
		//��������
		var hotSearchAction;
		$(function(){
			//�������� -> ��ʼ��
			hotSearchAction = new HotSearchAction();
			//�������� -> ������
			function HotSearchAction(){
				//�������� -> ����
				this.hotSearchSaveOrUpdate = function(){
					$('#hotSearchForm').form('submit',{
						url: "<%=mainWeb%>/admin/channel/modifyPopularKeyWords",
						dataType: 'json',
					    onSubmit:function(){
					    	return $.trim($("textarea[name='popularKeyWords']").val()) != "";
					    },   
					    success:function(data){
					    	$.messager.alert('��ʾ','����ɹ�!','info');
					    }
					});
				}
			}
		});
	
	</script>
	</body>
</html>