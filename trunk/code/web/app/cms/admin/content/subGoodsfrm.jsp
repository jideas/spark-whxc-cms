<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>商品</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 商品列表 -->
		<table id="subGoodsDatagrid">
		</table>
		<!-- end of 商品列表 -->
		<script type="text/javascript">
		//初始化
		$(function(){
			//初始化 -> 定义商品列表
			$("#subGoodsDatagrid").datagrid({
				fit : true,
				border : false,
				idField : 'id',
				rownumbers : true,
				singleSelect:true,
				fitColumns : true,
				remoteSort : false,
				pagination : true,
				url : '<%=mainWeb%>/admin/channel/getGoodsesByChannelId?id='+channelId,
				columns:[[
					//{field:'ordinal',title:'序号',width:75,align:'center',sortable:true},
					{field:'goodsName',title:'商品',width:200,align:'center',sortable:true},
					{field:'creator',title:'创建人',width:100,align:'center',sortable:true},
					{field:'createdate',title:'创建时间',width:100,align:'center',sortable:true},
					{field:'status',title:'状态',width:50,align:'center',sortable:true,
						formatter:function(value){
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
								isEnabled = "<a href='#' class='operateChannel' onClick=subGoodsAction.enabledGoods('"+value+"')>启用</a>";
								isDelete = "<a href='#' class='operateChannel' onClick=subGoodsAction.deleteGoods('"+rec.goodsid+"')>删除</a>";
							} else if (rec.status == '01'){
								isEnabled = "<a href='#' class='operateChannel' onClick=subGoodsAction.disabledGoods('"+value+"')>停用</a>";
							}
							return isEnabled + "     " + isDelete;
						}
					}
				]]
			});			
		});
		
		//商品
		var subGoodsAction;
		$(function(){
			//商品 -> 初始化
			subGoodsAction = new SubGoodsAction();
			//商品 -> 定义类
			function SubGoodsAction(){
				//商品 -> 选择商品
				this.selectGoods = function(){
					var parentNode = $('#channelTreeOfContent').tree('getParent', channelNode.target);
					if (channelNode.attributes.mainMenu || (parentNode && parentNode.id == "01")) {// 主菜单主页下的默认栏目不能删除
						var param = {
							url: mainWeb + '/admin/channel/getGoodsForChannelSel?channelId=' + channelId,
							isPublished: true		
						};
						var subGoods_win = new ChooseGoodsWindow(false, param);
						subGoods_win.addConfirmActionListener(function(goodsVos) {
							//商品 -> 确认选择
							sureSelect(goodsVos);							
						});	
					} else {
						var subGoods_win = new ChooseGoodsWindowByCategory(channelId);
						subGoods_win.addConfirmActionListener(function(goodsVos) {
							//商品 -> 确认选择
							sureSelect(goodsVos);							
						});	
					}
				};
				
				//商品 -> 确认选择
				function sureSelect(goodsVos){
					var ids = "";
					for(var i = 0;i< goodsVos.length;i++){
						ids += goodsVos[i].recid;
						if(i != goodsVos.length-1){
							ids += ",";
						}
					}
					//提交选择的商品
					$.post('<%=mainWeb%>/admin/channel/sureSelectGoods',{'ids[]':ids,'id':channelId},function(){
						$('#subGoodsDatagrid').datagrid('reload');  //刷新商品列表信息
					});	
				}
								
				//商品 -> 删除
				this.deleteGoods = function(goodsId){
					$.messager.confirm('删除','是否确定删除?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/channel/deleteGoodsByChannelId',{'goodsId':goodsId,'channelId':channelId},function(result){
			                	$('#subGoodsDatagrid').datagrid('reload');  //刷新商品列表信息   
			                },'json');		                  
			            }   
				    });
				}
				
				//商品 -> 启用
				this.enabledGoods =  function(recid){
					$.post('<%=mainWeb%>/admin/channel/enabledGoodsOfChannelId',{'recid':recid},function(result){
	                	$('#subGoodsDatagrid').datagrid('reload');  //刷新商品列表信息   
	                },'json');
				}

				//商品 -> 禁用
				this.disabledGoods = function(recid){
					$.post('<%=mainWeb%>/admin/channel/disabledGoodsOfChannelId',{'recid':recid},function(result){
	                	$('#subGoodsDatagrid').datagrid('reload');  //刷新商品列表信息   
	                },'json');
				}		
			}
		});
	</script>
	</body>
</html>