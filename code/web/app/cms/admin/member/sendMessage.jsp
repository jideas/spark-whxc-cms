<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>群发短信</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 会员列表 -->
		<div id="sendSMStoolbar" style="padding: 5px 10px 2px;">
			<span style="float: right; padding-right: 5px;">
				<a href="#"
				class="easyui-linkbutton"
				iconCls="icon-ok" onclick="sendSMSAction.openSendSMSDialog();">发送短信</a>
			</span>
			<span id="sendSMSMemberNumber" style="float: left; padding-top: 5px;">会员数量：</span>
			<span style="float: right;"> <input type="text"
					name="sendSMSSearchWord" value=""
					style="width: 200px; height: 22px; padding-top: 3px; line-height: 22px;border:1px solid #CCCCCC;" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="sendSMSAction.searchMemebers()">搜索</a>
			</span>
		</div>
		<table id="sendSMSMemberDatagrid" toolbar="#sendSMStoolbar">
		</table>
		<!-- end of 会员列表 -->

		<!-- begin of 短信发送 -->
		<div id="sendSMSDialog" class="easyui-dialog"
			data-options="closable:true,maximizable:false,buttons:'#sendSMSDialog-buttons'"
			style="width: 400px; height: 280px; padding: 10px" title="短信编辑"
			closed="true" iconCls="icon-save">
			<p>
				是否群发：<input type="checkbox" name="isBatchSendSMS" onclick="sendSMSAction.isBatchSendSMS()"/>
				<font color="#FF0000">(若勾选，向所有会员发送短信)</font>
			</p>
			<p>
				短信内容：<textarea onkeyup="sendSMSAction.countSMSLetter()" name="SMSContent" rows="7" cols="40" style="border: 1px solid #CCCCCC;"></textarea>
			</p>
			<p style="text-align:right;width: 355px;">
				短信字数：<font id="SMSLetterSUM" color="#FF0000">0</font>/60
			</p>
		</div>
		<div id="sendSMSDialog-buttons">
			<a href="#" id="batchSendMsgButton" class="easyui-linkbutton" iconCls="icon-ok" disabled="true"
				onclick="sendSMSAction.batchSendMsg()">群发</a>
			<a href="#" id="sendMsgButton" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="sendSMSAction.sendMsg()">发送</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#sendSMSDialog').dialog('close')">关闭</a>
		</div>
		<!-- end of 短信发送 -->
	
<script type="text/javascript">
	//初始化
	$(function(){
		//初始化 -> 会员列表 ->定义会员列表列
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
				{field:'code',title:'编号',width:50,align:'center',sortable:true},
				{field:'username',title:'用户名',width:50,align:'center',sortable:true},
				{field:'membername',title:'姓名',width:50,align:'center',sortable:true},
				{field:'mobile',title:'手机号码',width:50,align:'center',sortable:true},
				{field:'email',title:'Email',width:50,align:'center',sortable:true},
				{field:'statusStr',title:'状态',width:50,align:'center',sortable:true}
			]]
		});
		//初始化 -> 解决Dialog的Debug
		var sendSMSDialog = $("#pageContent #sendSMSDialog");
		for(var i =1;i< sendSMSDialog.length;i++){
			$(sendSMSDialog[i]).dialog("destroy");
		}
		//初始化 -> 显示会员数量/清空所有选中行$("#memberDatagrid").datagrid('getData').total
		$("#sendSMSMemberDatagrid").datagrid({
			onLoadSuccess:function(data){
				$("span#sendSMSMemberNumber").empty();
				$("span#sendSMSMemberNumber").append("会员数量："+data.total);
				$('#sendSMSMemberDatagrid').datagrid("clearSelections");
			}
		});
	});
	
	//发送短信
	var sendSMSAction;
	$(function(){
		//发送短信 -> 初始化sendSMSAction类
		sendSMSAction = new SendSMSAction();
		//发送短信 -> 定义SendSMSAction类
		function SendSMSAction(){
			//发送短信 -> 搜索
			this.searchMemebers = function(){
				$("#sendSMSMemberDatagrid").datagrid('reload',{
					searchWord:$("input[name='sendSMSSearchWord']").val()
				});
			}
			
			//发送短信 -> 统计短信字数
			this.countSMSLetter = function(){
				if($("textarea[name='SMSContent']").val().length > 60){
					var subSMSContent = $("textarea[name='SMSContent']").val().substring(0,60);
					$("textarea[name='SMSContent']").val(subSMSContent);
					$.messager.alert('提示', '短信内容已达上限60字!', 'warning');
				}
				$("p font#SMSLetterSUM").text($("textarea[name='SMSContent']").val().length);
			}
			
			//发送短信 -> 打开发送短信对话框
			this.openSendSMSDialog = function(){
				$("input[name='isBatchSendSMS']").attr("checked",false);
				$("textarea[name='SMSContent']").val("");
				$("#pageContent #sendSMSDialog").dialog("open");
			}
			
			//发送短信 -> 是否群发
			this.isBatchSendSMS = function(){
				//非群发
				if($("input[name='isBatchSendSMS']").attr("checked") == undefined || $("input[name='isBatchSendSMS']").attr("checked") == false){
					$('#sendMsgButton').linkbutton('enable'); 
					$('#batchSendMsgButton').linkbutton('disable'); 
				}else{
					$('#sendMsgButton').linkbutton('disable'); 
					$('#batchSendMsgButton').linkbutton('enable');
				}
			}
			
			//发送短信 -> 发送短信
			this.sendMsg = function(){
				var ids = getSelectedIDS("#sendSMSMemberDatagrid");
				if(ids == "" && ($("input[name='isBatchSendSMS']").attr("checked") == undefined || $("input[name='isBatchSendSMS']").attr("checked") == false)){
					$.messager.alert('提示', '请选择要发送短信的会员!', 'warning');
					return;
				}
				execSendMsg(ids,'#sendMsgButton','发送');
			}
			
			//发送短信 -> 群发短信
			this.batchSendMsg = function(){
				var ids = '';
				execSendMsg(ids,'#batchSendMsgButton','群发');
			}
			
			//发送短信 -> 执行短信发送
			function execSendMsg(ids,buttonID,buttonText){
				var SMSContent = $.trim($("textarea[name='SMSContent']").val());
				if(SMSContent == ""){
					$.messager.alert('提示', '短信内容不能为空!', 'warning');
					return;
				}
				//提示信息
				$.messager.confirm('提示','是否确定发送？',function(result){
					if(!result){
						return;
					}
					//发送短信
					$(buttonID).linkbutton({
					    text:'短信发送中...'
					}); 
					$(buttonID).linkbutton('disable'); 
					$.post(mainWeb+'/front/login/batchSendSafeSms',{'ids[]' : ids,'SMSContent':SMSContent},function(result){
						$(buttonID).linkbutton({  
						    text:buttonText
						}); 
						$(buttonID).linkbutton('enable'); 
						if(result){
							$.messager.alert('提示',"发送短信已完成！",'info');
						}else{
							$.messager.alert('提示',result.message,'info');
						}
					},'json');
				});
			}
			
			// 发送短信 -> 公共方法 -> 获取选中的Ids
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

