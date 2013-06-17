<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>内容</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<script type="text/javascript" charset="gbk"
			src="<%=mainWeb%>/scripts/ueditor/editorcustomconfig.js"></script>
		<script type="text/javascript" charset="gbk"
			src="<%=mainWeb%>/scripts/ueditor/editorcustom.js"></script>
		<!-- begin of 内容列表 -->
		<table id="subContentDatagrid"">
		</table>
		<!-- end of 内容列表 -->

		<!-- begin of 内容编辑 height:520px -->
		<div id="subContentDialog" class="easyui-dialog"
			data-options="modal:false,closable:true,maximizable:true"
			style="width: 940px; height: 600px; padding: 20px 10px 0px; z-index: 1"
			title="内容" buttons="#subContent-dlg-buttons" closed="true">
			<form id="subContentForm" method="post" modelAttribute="content">
				<input name="recid" type="hidden" />
				<input name="creatorid" type="hidden" />
				<input name="creator" type="hidden" />
				<input name="channelid" type="hidden" />
				<table width="880px">
					<tr>
						<td width='45px'>
							栏目：
						</td>
						<td width="835px">
							<input name="show_channelName" type="text" style="width: 300px;border: 0px;" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>
							标题：
						</td>
						<td>
							<input name="title" type="text" style="width: 835px;border: #bfccd8 1px solid;line-height: 18px;height: 22px;" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="z-index: 2">
								<script type="text/plain" charset="gbk" id="editor"
									name="content"></script>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="subContent-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="subContentAction.contentSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#subContentDialog').dialog('close')">取消</a>
		</div>
		<!-- end of 内容编辑 -->

		<script type="text/javascript">
		//初始化
		var subContent_editor;
		$(function(){
			//初始化 -> 定义富文本编辑器
			var option = {
	        	initialContent: '',//初始化编辑器的内容
	            minFrameHeight: 280,
	            imageUrl:'<%=mainWeb%>/admin/image/uploadImage',
	            imagePath:mainWeb,
	            imageManagerUrl:'<%=mainWeb%>/admin/image/getOnlineImagesList',
	            imageManagerPath:mainWeb
	            
		    };
			subContent_editor = new UE.ui.Editor(option);
			subContent_editor.render('editor');
		
			//初始化 -> 定义内容列表
			$("#subContentDatagrid").datagrid({
				fit : true,
				border : false,
				idField : 'id',
				rownumbers : true,
				singleSelect:true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : '<%=mainWeb%>/admin/content/getContents?id='+channelId,
				columns:[[
					//{field:'code',title:'编号',width:75,align:'center',sortable:true},
					{field:'title',title:'标题',width:250,align:'left',sortable:true},
					{field:'creator',title:'创建人',width:75,align:'center',sortable:true},
					{field:'createdate',title:'创建时间',width:75,align:'center',sortable:true},
					{field:'status',title:'状态',width:50,align:'center',sortable:true,
						formatter:function(value,rec){
							if(value == null || value == "02"){
								return "未启用";
							} else if (value == '01'){
								return "已启用";
							}
						}
					},
					{field:'recid',title:'操作',width:100,align:'center', 
						formatter:function(value,rec){
							var isDelete = "";
							var isEnabled = "";
							if(rec.status == null || rec.status == "02"){
								isEnabled = "<a href='#' class='operateChannel' onClick=subContentAction.enabledContent('"+value+"')>启用</a>";
								isDelete = "<a href='#' class='operateChannel' onClick=subContentAction.deleteContent('"+value+"')>删除</a>";
							} else if (rec.status == '01'){
								isEnabled = "<a href='#' class='operateChannel' onClick=subContentAction.disabledContent('"+value+"')>停用</a>";
							} 
							return isEnabled + "    " + isDelete;
						}
					}
				]]
			});
			
			//初始化 -> 解决dialog的BUG
			var subContentDialogDebug = $("#pageContent #subContentDialog");
			for(var i = 1;i < subContentDialogDebug.length;i++){
				$(subContentDialogDebug[i]).dialog("destroy");
			}
			
		});
		
		//内容
		var subContentAction;
		$(function(){
			//内容 -> 初始化
			subContentAction = new SubContentAction();
			//内容 -> 定义类
			function SubContentAction(){
				var url;
				//内容 -> 新增
				this.addContent = function(){
					//清空表单
					$('#subContentForm').form('clear');
					//设置栏目ID和名称
					$("#subContentForm input[name='channelid']").val(channelId);
					$("#subContentForm input[name='show_channelName']").val(channelName);
					//初始化富文本框的内容
					subContent_editor.setContent("");
					//打开对话框
					$('#subContentDialog').dialog('open').dialog('setTitle','新增内容');
					url = '<%=mainWeb%>/admin/content/addContent';
				}
				
				//内容 -> 修改
				this.updateContent = function(){
					var row = $('#subContentDatagrid').datagrid('getSelected');   
					if (row){
						//设置富文本框的内容
						subContent_editor.setContent(row.content);
						$('#subContentForm').form('load',row);
						$("#subContentForm input[name='show_channelName']").val(channelName); 
					   	$('#subContentDialog').dialog('open').dialog('setTitle','修改内容'); 
					   	url = '<%=mainWeb%>/admin/content/updateContent';   
					}else{
						$.messager.alert('提示','请选择要修改的内容!','warning');
					} 	
				}
				
				//内容 -> 保存
				this.contentSaveOrUpdate = function(){
					if(subContent_editor.hasContents()){
					    subContent_editor.sync();       //同步内容
					}else{
						$.messager.alert('提示','内容未填写!','warning');
						return;
					}
					$('#subContentForm').form('submit',{
						url:url,
					    onSubmit:function(){
					    	return subContentAction.validateForm();   
					    },   
					    success:function(data){
					    	data = eval("("+data+")");
					    	if(data.result){
					    		$.messager.alert('提示','内容保存成功!','info');//保存成功给出提示信息
						    	$("#subContentDialog").dialog('close');	//关闭内容对话框
						    	$('#subContentDatagrid').datagrid('reload');  //刷新内容列表信息
					    	}else{
					    		$.messager.alert('提示','内容保存失败!','error');//保存失败给出提示信息
					    	}					    	
					    }   
					}); 
				}
				
				//内容 -> 保存 -> 验证表单
				this.validateForm = function(){
					/*
					if($.trim($("#subContentForm input[name='code']").val()) == ''){
						$.messager.alert('提示','编号未填写!','warning'); //编号未填写
						return false;
					}
					*/
					if($.trim($("#subContentForm input[name='title']").val()) == ''){
						$.messager.alert('提示','标题未填写!','warning'); //标题未填写
						return false;
					}
					return true;
				}
				
				//内容 -> 删除
				this.deleteContent = function(recid){
					$.messager.confirm('删除','是否确定删除?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/content/deleteContent',{id:recid},function(result){
			                	$('#subContentDatagrid').datagrid('reload');  //刷新内容列表信息   
			                },'json');		                  
			            }   
				    });
				}
				
				//内容 -> 启用
				this.enabledContent = function(recid){
					$.post('<%=mainWeb%>/admin/content/enabledContent',{'recid':recid},
						function(){
							$('#subContentDatagrid').datagrid('reload');  //刷新内容列表信息
						}
					);
				}
				
				//内容 -> 禁用
				this.disabledContent = function(recid){
					$.post('<%=mainWeb%>/admin/content/disabledContent',{'recid':recid},
						function(){
							$('#subContentDatagrid').datagrid('reload');  //刷新内容列表信息
						}
					);
				}
			}
		});
	</script>
	</body>
</html>