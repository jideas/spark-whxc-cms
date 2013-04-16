<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Ⱥ������</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of ��Ա�б� -->
		<div id="sendSMStoolbar" style="padding: 5px 10px 2px;">
			<span style="float: right; padding-right: 5px;">
				<a href="#"
				class="easyui-linkbutton"
				iconCls="icon-ok" onclick="sendSMSAction.openSendSMSDialog();">���Ͷ���</a>
			</span>
			<span id="sendSMSMemberNumber" style="float: left; padding-top: 5px;">��Ա������</span>
			<span style="float: right;"> <input type="text"
					name="sendSMSSearchWord" value=""
					style="width: 200px; height: 22px; padding-top: 3px; line-height: 22px;border:1px solid #CCCCCC;" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="sendSMSAction.searchMemebers()">����</a>
			</span>
		</div>
		<table id="sendSMSMemberDatagrid" toolbar="#sendSMStoolbar">
		</table>
		<!-- end of ��Ա�б� -->

		<!-- begin of ���ŷ��� -->
		<div id="sendSMSDialog" class="easyui-dialog"
			data-options="closable:true,maximizable:false,buttons:'#sendSMSDialog-buttons'"
			style="width: 400px; height: 280px; padding: 10px" title="���ű༭"
			closed="true" iconCls="icon-save">
			<p>
				�Ƿ�Ⱥ����<input type="checkbox" name="isBatchSendSMS" onclick="sendSMSAction.isBatchSendSMS()"/>
				<font color="#FF0000">(����ѡ�������л�Ա���Ͷ���)</font>
			</p>
			<p>
				�������ݣ�<textarea onkeyup="sendSMSAction.countSMSLetter()" name="SMSContent" rows="7" cols="40" style="border: 1px solid #CCCCCC;"></textarea>
			</p>
			<p style="text-align:right;width: 355px;">
				����������<font id="SMSLetterSUM" color="#FF0000">0</font>/60
			</p>
		</div>
		<div id="sendSMSDialog-buttons">
			<a href="#" id="batchSendMsgButton" class="easyui-linkbutton" iconCls="icon-ok" disabled="true"
				onclick="sendSMSAction.batchSendMsg()">Ⱥ��</a>
			<a href="#" id="sendMsgButton" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="sendSMSAction.sendMsg()">����</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#sendSMSDialog').dialog('close')">�ر�</a>
		</div>
		<!-- end of ���ŷ��� -->
	
<script type="text/javascript">
	//��ʼ��
	$(function(){
		//��ʼ�� -> ��Ա�б� ->�����Ա�б���
		$('#sendSMSMemberDatagrid').datagrid({
			fit:true,
			border:false,
			idField:'recid',
			rownumbers:true,
			fitColumns:true,
			remoteSort:false,
			pagination:true,
			pageList:[10,20,30,40,50,100],
			url:'<%=mainWeb%>/admin/member/getMembers',
			columns:[[
				{field:'checkbox',checkbox:true},
				{field:'code',title:'���',width:50,align:'center',sortable:true},
				{field:'username',title:'�û���',width:50,align:'center',sortable:true},
				{field:'membername',title:'����',width:50,align:'center',sortable:true},
				{field:'mobile',title:'�ֻ�����',width:50,align:'center',sortable:true},
				{field:'email',title:'Email',width:50,align:'center',sortable:true},
				{field:'statusStr',title:'״̬',width:50,align:'center',sortable:true}
			]]
		});
		//��ʼ�� -> ���Dialog��Debug
		var sendSMSDialog = $("#pageContent #sendSMSDialog");
		for(var i =1;i< sendSMSDialog.length;i++){
			$(sendSMSDialog[i]).dialog("destroy");
		}
		//��ʼ�� -> ��ʾ��Ա����/�������ѡ����$("#memberDatagrid").datagrid('getData').total
		$("#sendSMSMemberDatagrid").datagrid({
			onLoadSuccess:function(data){
				$("span#sendSMSMemberNumber").empty();
				$("span#sendSMSMemberNumber").append("��Ա������"+data.total);
				$('#sendSMSMemberDatagrid').datagrid("clearSelections");
			}
		});
	});
	
	//���Ͷ���
	var sendSMSAction;
	$(function(){
		//���Ͷ��� -> ��ʼ��sendSMSAction��
		sendSMSAction = new SendSMSAction();
		//���Ͷ��� -> ����SendSMSAction��
		function SendSMSAction(){
			//���Ͷ��� -> ����
			this.searchMemebers = function(){
				$("#sendSMSMemberDatagrid").datagrid('reload',{
					searchWord:$("input[name='sendSMSSearchWord']").val()
				});
			}
			
			//���Ͷ��� -> ͳ�ƶ�������
			this.countSMSLetter = function(){
				if($("textarea[name='SMSContent']").val().length > 60){
					var subSMSContent = $("textarea[name='SMSContent']").val().substring(0,60);
					$("textarea[name='SMSContent']").val(subSMSContent);
					$.messager.alert('��ʾ', '���������Ѵ�����60��!', 'warning');
				}
				$("p font#SMSLetterSUM").text($("textarea[name='SMSContent']").val().length);
			}
			
			//���Ͷ��� -> �򿪷��Ͷ��ŶԻ���
			this.openSendSMSDialog = function(){
				$("input[name='isBatchSendSMS']").attr("checked",false);
				$("textarea[name='SMSContent']").val("");
				$("#pageContent #sendSMSDialog").dialog("open");
			}
			
			//���Ͷ��� -> �Ƿ�Ⱥ��
			this.isBatchSendSMS = function(){
				//��Ⱥ��
				if($("input[name='isBatchSendSMS']").attr("checked") == undefined || $("input[name='isBatchSendSMS']").attr("checked") == false){
					$('#sendMsgButton').linkbutton('enable'); 
					$('#batchSendMsgButton').linkbutton('disable'); 
				}else{
					$('#sendMsgButton').linkbutton('disable'); 
					$('#batchSendMsgButton').linkbutton('enable');
				}
			}
			
			//���Ͷ��� -> ���Ͷ���
			this.sendMsg = function(){
				var ids = getSelectedIDS("#sendSMSMemberDatagrid");
				if(ids == "" && ($("input[name='isBatchSendSMS']").attr("checked") == undefined || $("input[name='isBatchSendSMS']").attr("checked") == false)){
					$.messager.alert('��ʾ', '��ѡ��Ҫ���Ͷ��ŵĻ�Ա!', 'warning');
					return;
				}
				execSendMsg(ids,'#sendMsgButton','����');
			}
			
			//���Ͷ��� -> Ⱥ������
			this.batchSendMsg = function(){
				var ids = '';
				execSendMsg(ids,'#batchSendMsgButton','Ⱥ��');
			}
			
			//���Ͷ��� -> ִ�ж��ŷ���
			function execSendMsg(ids,buttonID,buttonText){
				var SMSContent = $.trim($("textarea[name='SMSContent']").val());
				if(SMSContent == ""){
					$.messager.alert('��ʾ', '�������ݲ���Ϊ��!', 'warning');
					return;
				}
				//��ʾ��Ϣ
				$.messager.confirm('��ʾ','�Ƿ�ȷ�����ͣ�',function(result){
					if(!result){
						return;
					}
					//���Ͷ���
					$(buttonID).linkbutton({
					    text:'���ŷ�����...'
					}); 
					$(buttonID).linkbutton('disable'); 
					$.post(mainWeb+'/front/login/batchSendSafeSms',{'ids[]' : ids,'SMSContent':SMSContent},function(result){
						$(buttonID).linkbutton({  
						    text:buttonText
						}); 
						$(buttonID).linkbutton('enable'); 
						if(result){
							$.messager.alert('��ʾ',"���Ͷ�������ɣ�",'info');
						}else{
							$.messager.alert('��ʾ',result.message,'info');
						}
					},'json');
				});
			}
			
			// ���Ͷ��� -> �������� -> ��ȡѡ�е�Ids
			function getSelectedIDS(targetDatagrid) {
				var rows = $(targetDatagrid).datagrid('getSelections');
				var ids = '';
				if (rows) {
					for (var i = 0; i < rows.length; i++) { 
						ids += rows[i].recid;
						if (i != rows.length - 1) {
							ids += ",";
						}
					}
				}
				return ids;
			}

		} 		
	});
</script>

	</body>
</html>

