<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>会员管理</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 会员列表 -->
		<div id="membertoolbar" style="padding: 5px 10px 2px;">
			<span style="float: left; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" id="clearVangetesButton"
				iconCls="icon-reload" onclick="memberAction.clearVangetes();">积分清零</a>
			</span>
			<span style="float: right; padding-right: 5px;"> <a href="#"
				class="easyui-linkbutton" id="clearVangetesButton"
				iconCls="icon-add" onclick="memberAction.importMember();">导入会员</a>
			</span>
			<span id="memberNumber" style="float: left; padding-top: 5px;">会员数量：</span>
			<span style="float: right;"> <input type="text"
					name="searchWord" value=""
					style="width: 200px; height: 22px; padding-top: 3px; line-height: 22px;" />
				<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="memberAction.searchMemebers()">搜索</a>
			</span>
		</div>
		<table id="memberDatagrid" toolbar="#membertoolbar">
		</table>
		<!-- end of 会员列表 -->

		<!-- begin of 会员详情 -->
		<div id="memderdetailDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 900px; height: 410px; padding: 0px" title="会员管理"
			closed="true" iconCls="icon-save">
		</div>
		<!-- end of 会员详情 -->

		<!-- begin of 会员编辑 -->
		<div id="memberDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 600px; height: 240px; padding: 20px 10px 0px;"
			title="会员" buttons="#member-dlg-buttons" closed="true">
			<form id="memberForm" method="post" modelAttribute="member">
				<table style="font-size: 14px;">
					<tr>
						<td>
							recver：
						</td>
						<td>
							<input type="text" name="recver" style="width: 200px;" />
						</td>
						<td>
							username：
						</td>
						<td>
							<input type="text" name="username" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							password：
						</td>
						<td>
							<input type="text" name="password" style="width: 200px;" />
						</td>
						<td>
							membername：
						</td>
						<td>
							<input type="text" name="membername" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							mobile：
						</td>
						<td>
							<input type="text" name="mobile" style="width: 200px;" />
						</td>
						<td>
							email：
						</td>
						<td>
							<input type="text" name="email" style="width: 200px;" />
						</td>
					</tr>
					<tr>
						<td>
							status：
						</td>
						<td>
							<input type="text" name="status" style="width: 200px;" />
						</td>
						<td>
							invitationcode：
						</td>
						<td>
							<input type="text" name="invitationcode" style="width: 200px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="member-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="memberAction.memberSaveOrUpdate()">保存</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#memberDialog').dialog('close')">关闭</a>
		</div>
		<!-- end of 会员编辑 -->
<div id="importMemberDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 150px; padding: 20px 10px 0px;"
			title="面值卡" buttons="#importMemberDialog-dlg-buttons" closed="true">
			<form id="importMemberForm" method="post" modelAttribute="form"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;导入excel：
						</td>
						<td>
							<input id="importMemberExcelInput" name="excel"
								style="width: 350px;" type="file" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="importMemberDialog-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				id="importMemberDialog-dlg-buttons01"
				onclick="memberAction.sureImportSubmit()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				id="importMemberDialog-dlg-buttons02"
				onclick="javascript:$('#importMemberDialog').dialog('close')">取 消</a>
		</div>

		<script type="text/javascript">
	//初始化
	$(function(){
		//初始化 -> 会员列表 ->定义会员列表列
		$('#memberDatagrid').datagrid({fit:true,
			border:false,
			idField:'recid',
			rownumbers:true,
			fitColumns:true,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			url:'<%=mainWeb%>/admin/member/getMembers',
			columns:[[
				{field:'code',title:'编号',width:50,align:'center',sortable:true},
				{field:'username',title:'用户名',width:50,align:'center',sortable:true},
				{field:'membername',title:'姓名',width:50,align:'center',sortable:true},
				{field:'mobile',title:'手机号码',width:50,align:'center',sortable:true},
				{field:'email',title:'Email',width:50,align:'center',sortable:true},
				{field:'statusStr',title:'状态',width:50,align:'center',sortable:true},
				{field:'recid',title:'操作',width:50,align:'center',
					formatter:function(value,rec){
						return "<a href='#' class='operateChannel' onClick=memberAction.viewDetail('"+value+"','"+rec.membername+"')>查看详情</a>";
					}
				}
			]]
		});
		//初始化 -> 解决Dialog的Debug
		var memderdetailDialog = $("#pageContent #memderdetailDialog");
		for(var i =1;i< memderdetailDialog.length;i++){
			$(memderdetailDialog[i]).dialog("destroy");
		}
		//初始化 -> 显示会员数量$("#memberDatagrid").datagrid('getData').total
		$("#memberDatagrid").datagrid({
			onLoadSuccess:function(data){
				$("span#memberNumber").empty();
				$("span#memberNumber").append("会员数量："+data.total);
			}
		});
		
	});
	
	//会员
	var memberAction;
	$(function(){
		//会员 -> 初始化memberAction类
		memberAction = new MemberAction();
		//会员 -> 定义MemberAction类
		function MemberAction(){
			this.clearVangetes = function(){
					$.messager.confirm('提示','确定真的要把所有的用户积分清零么？',function(result){
						if(!result){
							return;
						}
						$('#clearVangetesButton').linkbutton({  
						    text:'清零中...' 
						}); 
						$('#clearVangetesButton').linkbutton('disable'); 
						$.post(mainWeb+'/admin/member/clearVangetes',function(result){
							if(result){
								$('#clearVangetesButton').linkbutton({  
								    text:'积分清零' 
								}); 
								$('#clearVangetesButton').linkbutton('enable'); 
								$.messager.alert('提示',"积分清零已完成！",'info');
							}else{
								$.messager.alert('提示',result.message,'info');
							} 
						},'json');
					});		
			}
			//会员 -> 搜索
			this.searchMemebers = function(){
				$("#memberDatagrid").datagrid('reload',{
					searchWord:$("input[name='searchWord']").val()
				});
			}
			//会员 -> 新增
			this.addMemeber = function(){
				$('#memberForm').form('clear');  
				$('#memberDialog').dialog('open').dialog('setTitle','新增会员');
			}			
			//会员 -> 保存
			this.memberSaveOrUpdate = function(){
				$('#memberForm').form('submit',{
					url:'<%=mainWeb%>/admin/member/addMember',
				    onSubmit:function(){
				    	
				    },   
				    success:function(data){
				    	$.messager.alert('提示','会员保存成功!','info');//保存成功给出提示信息
				    	$("#memberDialog").dialog('close');	//关闭会员对话框
				    	$('#memberDatagrid').datagrid('reload');  //刷新会员列表信息
				    }   
				}); 
			}
			//会员 -> 查看详情
			this.viewDetail = function(recid,name){
				memberId = recid;
				memberName = name;
				$("#memderdetailDialog").dialog("refresh","<%=mainWeb%>/app/cms/admin/member/membermainfrm.jsp");
				$("#memderdetailDialog").dialog("open");
			}
			
			//会员 -> 导入会员
			this.importMember = function(){
				$('#importMemberDialog').dialog('open').dialog('setTitle', '导入会员');
			}

		this.sureImportSubmit = function(){
			$('#importMemberForm').form('submit', {
						url : mainWeb+'/admin/member/importMember',
						onSubmit : function() {
							var canSubmit =  $.trim($('#importMemberExcelInput').val())!='';
							$('#importMemberDialog-dlg-buttons01').linkbutton("disable");
							$('#importMemberDialog-dlg-buttons02').linkbutton("disable");
							return canSubmit;// 验证表单
						},
						success : function(data) { 
							$('#importMemberDialog-dlg-buttons01').linkbutton("enable");
							$('#importMemberDialog-dlg-buttons02').linkbutton("enable"); 
							var count = parseInt(data);
							if(count==0){
								$.messager.alert('提示',"导入失败，请检查导入内容", 'info');// 保存成功给出提示信息
							}else{
								$.messager.alert('提示', "成功导入"+count+"位会员。", 'info');// 保存成功给出提示信息
							}
							$("#importMemberDialog").dialog('close'); // 关闭面值卡对话框 
							$('#importMemberExcelInput').val('');
						}
					});
		}
		} 		
	});
	var memberId;
	var memberName;
</script>

	</body>
</html>

