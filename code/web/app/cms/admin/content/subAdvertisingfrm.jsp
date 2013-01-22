<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>广告位</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 广告列表 -->
		<table id="subAdvertisingDatagrid"">
		</table>
		<!-- end of 广告列表 -->

		<!-- begin of 广告编辑 -->
		<div id="subAdvertisingDialog" class="easyui-dialog"
			data-options="modal:true,closable:true,maximizable:false"
			style="width: 550px; height: 240px; padding: 20px 10px 0px;"
			title="广告位" buttons="#subAdvertising-dlg-buttons" closed="true">
			<form id="subAdvertisingForm" method="post"
				modelAttribute="advertising">
				<input name="recid" type="hidden" />
				<input name="creatorid" type="hidden" />
				<input name="creator" type="hidden" />
				<input name="channelid" type="hidden" />
				<table>
					<tr style="height: 25px;">
						<td>
							所属栏目：
						</td>
						<td>
							<input name="show_channelName" type="text" style="width: 350px;border: #bfccd8 0px solid;"
								readonly="readonly" />
						</td>
					</tr>
					<tr style="height: 35px;">
						<td>
							图片地址：
						</td>
						<td>
							<input name=imageurl type="text" readonly="readonly" style="width: 350px;border: #bfccd8 1px solid;"/>
							<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
							onclick="subAdvertisingAction.selectImg()">选择</a>
						</td>
					</tr>
					<tr style="height: 35px;">
						<td>
							链接地址：
						</td>
						<td>
							<input type="text" name="url" style="width: 350px;border: #bfccd8 1px solid;"/>
						</td>
					</tr>
					<!-- tr>
						<td>
							启用：
						</td>
						<td>
							<input type="checkbox" name="status" value="1"/>
						</td>
					</tr> -->
				</table>
			</form>
		</div>
		<div id="subAdvertising-dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="subAdvertisingAction.advertisingSaveOrUpdate()">确 定</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#subAdvertisingDialog').dialog('close')">取消</a>
		</div>
		<!-- end of 广告编辑 -->

		<script type="text/javascript">
		//初始化
		$(function(){
			//初始化 -> 定义广告列表
			$("#subAdvertisingDatagrid").datagrid({
				fit : true,
				border : false,
				idField : 'id',
				rownumbers : true,
				singleSelect:true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : '<%=mainWeb%>/admin/advertising/getAdvertisings?id='+channelId,
				columns:[[
					{field:'creator',title:'创建人',width:100,align:'center',sortable:true},
					{field:'createdate',title:'创建时间',width:100,align:'center',sortable:true},
					{field:'imageurl',title:'图片地址',width:150,align:'center',sortable:true},
					{field:'url',title:'链接地址',width:150,align:'center',sortable:true},
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
								isEnabled = "<a href='#' class='operateChannel' onClick=subAdvertisingAction.enabledAdvertising('"+value+"')>启用</a>";
								isDelete = "<a href='#' class='operateChannel' onClick=subAdvertisingAction.deleteAdvertising('"+value+"')>删除</a>";
							} else if (rec.status == '01'){
								isEnabled = "<a href='#' class='operateChannel' onClick=subAdvertisingAction.disabledAdvertising('"+value+"')>停用</a>";
							} 
							return isEnabled + "    " + isDelete;
						}
					}
				]]
			});
			
			//初始化 -> 解决dialog的BUG
			var subAdvertisingDialogDebug = $("#pageContent #subAdvertisingDialog");
			for(var i = 1;i < subAdvertisingDialogDebug.length;i++){
				$(subAdvertisingDialogDebug[i]).dialog("destroy");
			}
			
		});
		
		//广告
		var subAdvertisingAction;
		$(function(){
			//广告 -> 初始化
			subAdvertisingAction = new SubAdvertisingAction();
			//广告 -> 定义类
			function SubAdvertisingAction(){
				var url="";
				//广告 -> 新增
				this.addAdvertising = function(){
					//清空表单
					$('#subAdvertisingForm').form('clear');
					//设置栏目ID和名称
					$("#subAdvertisingForm input[name='channelid']").val(channelId);
					$("#subAdvertisingForm input[name='show_channelName']").val(channelName);
					$('#subAdvertisingDialog').dialog('open').dialog('setTitle','新增广告');
					url = '<%=mainWeb%>/admin/advertising/addAdvertising';
				}
				
				//广告 -> 修改
				this.updateAdvertising = function(){
					var row = $('#subAdvertisingDatagrid').datagrid('getSelected');   
					if (row){
						$('#subAdvertisingForm').form('load',row);
						$("#subAdvertisingForm input[name='show_channelName']").val(channelName);  
					   	$('#subAdvertisingDialog').dialog('open').dialog('setTitle','修改广告'); 
					   	url = '<%=mainWeb%>/admin/advertising/updateAdvertising';   
					}else{
						$.messager.alert('提示','请选择要修改的广告!','warning');
					} 	
				}
				
				//广告 -> 选择图片
				this.selectImg = function(){
					var subAd_chooseImagesWindow = new ChooseImagesWindow();
					subAd_chooseImagesWindow.addConfirmActionListener(function(path){
						$("input[name='imageurl']").val(path);
					});
				}
				
				//广告 -> 保存
				this.advertisingSaveOrUpdate = function(){
					$('#subAdvertisingForm').form('submit',{
						url:url,
					    onSubmit:function(){
					    	return true;   
					    },   
					    success:function(data){
					    	data = eval("("+data+")");
					    	if(data.result){
					    		$.messager.alert('提示','广告保存成功!','info');//保存成功给出提示信息
						    	$("#subAdvertisingDialog").dialog('close');	//关闭广告对话框
						    	$('#subAdvertisingDatagrid').datagrid('reload');  //刷新广告列表信息
					    	}else{
					    		$.messager.alert('提示','广告保存失败!','error');//保存失败给出提示信息
					    	}				    	
					    }   
					});
				}
				
				//广告 -> 保存 -> 验证表单
				this.validateForm = function(){
					if($.trim($('#advertisingOfChannel').combobox('getValue')) == ''){
						$.messager.alert('提示','所属栏目未选择!','warning'); //所属栏目未选择
						 return false;
					}
					if($.trim($("#subAdvertisingForm input[name='imageUrl']").val()) == ''){
						$.messager.alert('提示','图片地址未填写!','warning'); //图片地址未填写
						return false;
					}
					if($.trim($("#subAdvertisingForm input[name='url']").val()) == ''){
						$.messager.alert('提示','链接地址未填写!','warning'); //链接地址未填写
						return false;
					}
					return true;
				}
				
				//广告 -> 删除
				this.deleteAdvertising = function(recid){
					$.messager.confirm('删除','是否确定删除?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/advertising/deleteAdvertising',{id:recid},function(result){
			                	$('#subAdvertisingDatagrid').datagrid('reload');  //刷新广告列表信息   
			                },'json');		                  
			            }   
				    });
				}
				
				//广告 -> 启用
				this.enabledAdvertising = function(recid){
					$.post('<%=mainWeb%>/admin/advertising/enabledAdvertising',{'recid':recid},
						function(){
							$('#subAdvertisingDatagrid').datagrid('reload');  //刷新广告列表信息
						}
					);
				}
				
				//广告 -> 禁用
				this.disabledAdvertising = function(recid){
					$.post(mainWeb + '/admin/advertising/disabledAdvertising',{'recid':recid},
						function(){
							$('#subAdvertisingDatagrid').datagrid('reload');  //刷新广告列表信息
						}
					);
				}
			}
		});
	</script>
	</body>
</html>