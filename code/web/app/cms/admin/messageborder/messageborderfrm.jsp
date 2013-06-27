<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>���԰����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of �����б� -->
		<div id="messagebordrerFilterToolbar" style="height: 23px;line-height: 23px;padding-top: 3px;padding-left: 5px;">
			<select id="messageborderfilterSelect" class="easyui-combobox" style="width: 100px;">
				<option value="-1">ȫ��</option>
				<option value="0">δ�ظ�</option>
				<option value="1">�ѻظ�</option>
			</select></div>
		<table id="messageborderDatagrid">
		</table>
		<!-- end of �����б� -->

		<!-- begin of �������� -->
		<div id=msgcontentDialog title="���԰�" class="easyui-dialog" closed="true"
			buttons="#msgcontent-dlg-buttons" closable="true" modal="true"
			style="width: 650px; height: 350px; padding: 30px 30px 20px 10px">
			<table id="clientMsg">
				<tr>
					<td class="clientName" style="height: 100px;width:100px;vertical-align: top;color:#00F;font-weight: bold;">��������</td>
					<td class="clientMsgcontent" style="width: 500px;vertical-align: top;font-size: 13px;"></td>
				</tr>
				<tr>
					<td class="adminName" style="height: 100px;width:75px;vertical-align: top;color:#00F;font-weight: bold;">����Ա</td>
					<td class="adminMsgcontent" style="width: 500px;vertical-align: top;">
						<textarea rows="7" cols="65" style="border:1px solid #CCCCCC;"></textarea>
					</td>
				</tr>
			</table>
		</div>
		<div id="msgcontent-dlg-buttons">
			<a href="#" id="msgcontentSaveButton" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="messageborderAction.save()">�� ��</a>
			<a href="#" id="msgcontentRecoveryButton" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="messageborderAction.recovery()">�� ��</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#msgcontentDialog').dialog('close')">�� ��</a>
		</div>
		<!-- end of �������� -->

		<!--begin of script -->
		<script type="text/javascript" charset="GBK">
//��ʼ��
$(function(){
	$.ajaxSetup({cache:false});
	//��ʼ�� --> ���Dialog��Debug
	var msgcontentDialogDebug = $("#pageContent #msgcontentDialog");
	for(var i =1;i< msgcontentDialogDebug.length;i++){
		$(msgcontentDialogDebug[i]).dialog("destroy");
	}
	
	//��ʼ��
	$("select#messageborderfilterSelect").combobox({
		onSelect:function(record){
			//���ط�����Ʒ
			messageborderAction.reload();
		}
	});
	
	//��ʼ�� --> ���������б���
	$('#messageborderDatagrid').datagrid({
		remoteSort:false,
		fit:true,
		border:false,
		idField:"recid",
		rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		toolbar:"#messagebordrerFilterToolbar",
		pagination:true,
		url:"<%=mainWeb%>/admin/messageborder/getMessageborderList",
		columns:[[
			{field:'msgcreator',title:'������',width:100,align:'center',sortable:true},
			{field:'msgcontent',title:'��������',width:300,align:'center',sortable:true,
				formatter:function(value,rec){
					if(value != null && value.length > 40){
						return "<span title='"+value+"'>"+value.substring(0,40)+"..."+"</span>";
					}else{
						return "<span>"+value+"</span>";
					}
				}
			},
			{field:'msgdate',title:'����ʱ��',width:100,align:'center',sortable:true},
			{field:'isrecovery',title:'�Ƿ��ѻظ�',width:50,align:'center',sortable:true,
				formatter:function(value,rec){
					if("0"==value){
						return "δ�ظ�";
					}else{
						return "<span sytle='color:#F00;'>�ѻظ�</span>";
					}
				}
			},
			{field:'recid',title:'����',width:75,align:'center',sortable:true,
				formatter:function(value,rec){
					var view = "<a href='#' class='operateChannel' onClick=messageborderAction.viewMsg('"+value+"','"+rec.msgcreator+"','"+rec.msgcontent+"')>�鿴</a>";
					var recovery = "<a href='#' class='operateChannel' onClick=messageborderAction.recoveryMsg('"+value+"','"+rec.msgcreator+"','"+rec.msgcontent+"')>�ظ�</a>";
					var deleteMsg = "<a href='#' class='operateChannel' onClick=messageborderAction.deleteMsg('"+value+"')>ɾ��</a>";
					if("0"==rec.isrecovery){
						return recovery + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + deleteMsg;
					}else{
						return view + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + deleteMsg;
					}
				}
			}
		]]
	});
	
});

//���԰�
var messageborderAction;
$(function(){
	//���԰� -> ��ʼ��
	messageborderAction = new MessageborderAction();
	//���԰� -> ����
	function MessageborderAction(){
		var local_recid;
		var admin_recid;
		//�鿴
		this.viewMsg = function(recid,msgcreator,msgcontent){
			local_recid = recid;
			$("#msgcontentDialog").dialog("open");
			$("#msgcontentSaveButton").linkbutton('enable');
			$("#msgcontentRecoveryButton").linkbutton('disable');
			$("#clientMsg .clientName").html(msgcreator);
			$("#clientMsg .clientMsgcontent").html(msgcontent);
			$.get('<%=mainWeb%>/admin/messageborder/getRecoveryById',{'recid':recid},function(result){
		       	if(result){
		       		admin_recid = result.recid;
		       		$("#clientMsg .adminMsgcontent textarea").val(result.msgcontent);
		       	}
		     },'json');
		}
		
		//�ظ�
		this.recoveryMsg = function(recid,msgcreator,msgcontent){
			local_recid = recid;
			$("#msgcontentDialog").dialog("open");
			$("#msgcontentSaveButton").linkbutton('disable');
			$("#msgcontentRecoveryButton").linkbutton('enable');
			$("#clientMsg .clientName").html(msgcreator);
			$("#clientMsg .clientMsgcontent").html(msgcontent);
			$("#clientMsg .adminMsgcontent textarea").val("");
		}
		
		//����
		this.save = function(){
			var msgcontent = $.trim($("#clientMsg textarea").val());
			if(msgcontent == ""){
				$.messager.alert('��ʾ','�ظ����ݲ���Ϊ��!','warning'); 
				return;
			}
			$.ajax({
				  url: '<%=mainWeb%>/admin/messageborder/updateRecovery',
				  data:{recid:admin_recid,msgcontent:msgcontent},
				  complete: function(data) {
				  	$.messager.alert('��ʾ','�����޸ĳɹ�!','info');
				  	$("#msgcontentDialog").dialog("close");
				   	//���ط�����Ʒ
					messageborderAction.reload();
				  }
			});
		}
		
		//�ظ�
		this.recovery = function(){
			var msgcontent = $.trim($("#clientMsg textarea").val());
			if(msgcontent == ""){
				$.messager.alert('��ʾ','�ظ����ݲ���Ϊ��!','warning'); 
				return;
			}
			$.ajax({
				  url: '<%=mainWeb%>/admin/messageborder/recovery',
				  data:{recid:local_recid,msgcontent:msgcontent},
				  complete: function(data) {
				  	$.messager.alert('��ʾ','���Իظ��ɹ�!','info');
				  	$("#msgcontentDialog").dialog("close");
				   	//���ط�����Ʒ
					messageborderAction.reload();
				  }
			}); 
		}
		
		//ɾ��
		this.deleteMsg = function(recid){
			 $.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(r){   
		         if (r){
		        	$.ajax({
						  url: '<%=mainWeb%>/admin/messageborder/deleteMsg',
						  data:{recid:recid},
						  complete: function(data) {
							//���ط�����Ʒ
							messageborderAction.reload();					
						  }
					});  
		      	}
	      	});
		}
		
		//���¼�������
		this.reload = function(){
			//���ط�����Ʒ
			var filter = $("#messageborderfilterSelect").combobox("getValue");
			$('#messageborderDatagrid').datagrid('loadData', { total: 0, rows: [] });
			$("#messageborderDatagrid").datagrid('reload',{
				filter:filter
			});
		}
	}
});
</script>
	</body>
</html>