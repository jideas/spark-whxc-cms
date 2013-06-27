<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>留言板管理</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 留言列表 -->
		<div id="messagebordrerFilterToolbar" style="height: 23px;line-height: 23px;padding-top: 3px;padding-left: 5px;">
			<select id="messageborderfilterSelect" class="easyui-combobox" style="width: 100px;">
				<option value="-1">全部</option>
				<option value="0">未回复</option>
				<option value="1">已回复</option>
			</select></div>
		<table id="messageborderDatagrid">
		</table>
		<!-- end of 留言列表 -->

		<!-- begin of 留言内容 -->
		<div id=msgcontentDialog title="留言板" class="easyui-dialog" closed="true"
			buttons="#msgcontent-dlg-buttons" closable="true" modal="true"
			style="width: 650px; height: 350px; padding: 30px 30px 20px 10px">
			<table id="clientMsg">
				<tr>
					<td class="clientName" style="height: 100px;width:100px;vertical-align: top;color:#00F;font-weight: bold;">柒号生活馆</td>
					<td class="clientMsgcontent" style="width: 500px;vertical-align: top;font-size: 13px;"></td>
				</tr>
				<tr>
					<td class="adminName" style="height: 100px;width:75px;vertical-align: top;color:#00F;font-weight: bold;">管理员</td>
					<td class="adminMsgcontent" style="width: 500px;vertical-align: top;">
						<textarea rows="7" cols="65" style="border:1px solid #CCCCCC;"></textarea>
					</td>
				</tr>
			</table>
		</div>
		<div id="msgcontent-dlg-buttons">
			<a href="#" id="msgcontentSaveButton" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="messageborderAction.save()">保 存</a>
			<a href="#" id="msgcontentRecoveryButton" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="messageborderAction.recovery()">回 复</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#msgcontentDialog').dialog('close')">关 闭</a>
		</div>
		<!-- end of 留言内容 -->

		<!--begin of script -->
		<script type="text/javascript" charset="GBK">
//初始化
$(function(){
	$.ajaxSetup({cache:false});
	//初始化 --> 解决Dialog的Debug
	var msgcontentDialogDebug = $("#pageContent #msgcontentDialog");
	for(var i =1;i< msgcontentDialogDebug.length;i++){
		$(msgcontentDialogDebug[i]).dialog("destroy");
	}
	
	//初始化
	$("select#messageborderfilterSelect").combobox({
		onSelect:function(record){
			//加载发布商品
			messageborderAction.reload();
		}
	});
	
	//初始化 --> 定义留言列表列
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
			{field:'msgcreator',title:'留言人',width:100,align:'center',sortable:true},
			{field:'msgcontent',title:'留言内容',width:300,align:'center',sortable:true,
				formatter:function(value,rec){
					if(value != null && value.length > 40){
						return "<span title='"+value+"'>"+value.substring(0,40)+"..."+"</span>";
					}else{
						return "<span>"+value+"</span>";
					}
				}
			},
			{field:'msgdate',title:'留言时间',width:100,align:'center',sortable:true},
			{field:'isrecovery',title:'是否已回复',width:50,align:'center',sortable:true,
				formatter:function(value,rec){
					if("0"==value){
						return "未回复";
					}else{
						return "<span sytle='color:#F00;'>已回复</span>";
					}
				}
			},
			{field:'recid',title:'操作',width:75,align:'center',sortable:true,
				formatter:function(value,rec){
					var view = "<a href='#' class='operateChannel' onClick=messageborderAction.viewMsg('"+value+"','"+rec.msgcreator+"','"+rec.msgcontent+"')>查看</a>";
					var recovery = "<a href='#' class='operateChannel' onClick=messageborderAction.recoveryMsg('"+value+"','"+rec.msgcreator+"','"+rec.msgcontent+"')>回复</a>";
					var deleteMsg = "<a href='#' class='operateChannel' onClick=messageborderAction.deleteMsg('"+value+"')>删除</a>";
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

//留言板
var messageborderAction;
$(function(){
	//留言板 -> 初始化
	messageborderAction = new MessageborderAction();
	//留言板 -> 定义
	function MessageborderAction(){
		var local_recid;
		var admin_recid;
		//查看
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
		
		//回复
		this.recoveryMsg = function(recid,msgcreator,msgcontent){
			local_recid = recid;
			$("#msgcontentDialog").dialog("open");
			$("#msgcontentSaveButton").linkbutton('disable');
			$("#msgcontentRecoveryButton").linkbutton('enable');
			$("#clientMsg .clientName").html(msgcreator);
			$("#clientMsg .clientMsgcontent").html(msgcontent);
			$("#clientMsg .adminMsgcontent textarea").val("");
		}
		
		//保存
		this.save = function(){
			var msgcontent = $.trim($("#clientMsg textarea").val());
			if(msgcontent == ""){
				$.messager.alert('提示','回复内容不能为空!','warning'); 
				return;
			}
			$.ajax({
				  url: '<%=mainWeb%>/admin/messageborder/updateRecovery',
				  data:{recid:admin_recid,msgcontent:msgcontent},
				  complete: function(data) {
				  	$.messager.alert('提示','留言修改成功!','info');
				  	$("#msgcontentDialog").dialog("close");
				   	//加载发布商品
					messageborderAction.reload();
				  }
			});
		}
		
		//回复
		this.recovery = function(){
			var msgcontent = $.trim($("#clientMsg textarea").val());
			if(msgcontent == ""){
				$.messager.alert('提示','回复内容不能为空!','warning'); 
				return;
			}
			$.ajax({
				  url: '<%=mainWeb%>/admin/messageborder/recovery',
				  data:{recid:local_recid,msgcontent:msgcontent},
				  complete: function(data) {
				  	$.messager.alert('提示','留言回复成功!','info');
				  	$("#msgcontentDialog").dialog("close");
				   	//加载发布商品
					messageborderAction.reload();
				  }
			}); 
		}
		
		//删除
		this.deleteMsg = function(recid){
			 $.messager.confirm('删除','是否确定删除?',function(r){   
		         if (r){
		        	$.ajax({
						  url: '<%=mainWeb%>/admin/messageborder/deleteMsg',
						  data:{recid:recid},
						  complete: function(data) {
							//加载发布商品
							messageborderAction.reload();					
						  }
					});  
		      	}
	      	});
		}
		
		//重新加载数据
		this.reload = function(){
			//加载发布商品
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