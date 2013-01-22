<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ʒ</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of ��Ʒ�б� -->
		<table id="subGoodsDatagrid">
		</table>
		<!-- end of ��Ʒ�б� -->
		<script type="text/javascript">
		//��ʼ��
		$(function(){
			//��ʼ�� -> ������Ʒ�б�
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
					//{field:'ordinal',title:'���',width:75,align:'center',sortable:true},
					{field:'goodsName',title:'��Ʒ',width:200,align:'center',sortable:true},
					{field:'creator',title:'������',width:100,align:'center',sortable:true},
					{field:'createdate',title:'����ʱ��',width:100,align:'center',sortable:true},
					{field:'status',title:'״̬',width:50,align:'center',sortable:true,
						formatter:function(value){
							if(value == null || value == "02"){
								return "δ����";
							} else if (value == '01'){
								return "������";
							}
						}
					},
					{field:'recid',title:'����',width:100,align:'center', 
						formatter:function(value,rec){
							var isDelete = "";
							var isEnabled = "";
							if(rec.status == null || rec.status == "02"){
								isEnabled = "<a href='#' class='operateChannel' onClick=subGoodsAction.enabledGoods('"+value+"')>����</a>";
								isDelete = "<a href='#' class='operateChannel' onClick=subGoodsAction.deleteGoods('"+rec.goodsid+"')>ɾ��</a>";
							} else if (rec.status == '01'){
								isEnabled = "<a href='#' class='operateChannel' onClick=subGoodsAction.disabledGoods('"+value+"')>ͣ��</a>";
							}
							return isEnabled + "     " + isDelete;
						}
					}
				]]
			});			
		});
		
		//��Ʒ
		var subGoodsAction;
		$(function(){
			//��Ʒ -> ��ʼ��
			subGoodsAction = new SubGoodsAction();
			//��Ʒ -> ������
			function SubGoodsAction(){
				//��Ʒ -> ѡ����Ʒ
				this.selectGoods = function(){
					var parentNode = $('#channelTreeOfContent').tree('getParent', channelNode.target);
					if (channelNode.attributes.mainMenu || (parentNode && parentNode.id == "01")) {// ���˵���ҳ�µ�Ĭ����Ŀ����ɾ��
						var param = {
							url: mainWeb + '/admin/channel/getGoodsForChannelSel?channelId=' + channelId,
							isPublished: true		
						};
						var subGoods_win = new ChooseGoodsWindow(false, param);
						subGoods_win.addConfirmActionListener(function(goodsVos) {
							//��Ʒ -> ȷ��ѡ��
							sureSelect(goodsVos);							
						});	
					} else {
						var subGoods_win = new ChooseGoodsWindowByCategory(channelId);
						subGoods_win.addConfirmActionListener(function(goodsVos) {
							//��Ʒ -> ȷ��ѡ��
							sureSelect(goodsVos);							
						});	
					}
				};
				
				//��Ʒ -> ȷ��ѡ��
				function sureSelect(goodsVos){
					var ids = "";
					for(var i = 0;i< goodsVos.length;i++){
						ids += goodsVos[i].recid;
						if(i != goodsVos.length-1){
							ids += ",";
						}
					}
					//�ύѡ�����Ʒ
					$.post('<%=mainWeb%>/admin/channel/sureSelectGoods',{'ids[]':ids,'id':channelId},function(){
						$('#subGoodsDatagrid').datagrid('reload');  //ˢ����Ʒ�б���Ϣ
					});	
				}
								
				//��Ʒ -> ɾ��
				this.deleteGoods = function(goodsId){
					$.messager.confirm('ɾ��','�Ƿ�ȷ��ɾ��?',function(r){   
			            if (r){   
			                $.post('<%=mainWeb%>/admin/channel/deleteGoodsByChannelId',{'goodsId':goodsId,'channelId':channelId},function(result){
			                	$('#subGoodsDatagrid').datagrid('reload');  //ˢ����Ʒ�б���Ϣ   
			                },'json');		                  
			            }   
				    });
				}
				
				//��Ʒ -> ����
				this.enabledGoods =  function(recid){
					$.post('<%=mainWeb%>/admin/channel/enabledGoodsOfChannelId',{'recid':recid},function(result){
	                	$('#subGoodsDatagrid').datagrid('reload');  //ˢ����Ʒ�б���Ϣ   
	                },'json');
				}

				//��Ʒ -> ����
				this.disabledGoods = function(recid){
					$.post('<%=mainWeb%>/admin/channel/disabledGoodsOfChannelId',{'recid':recid},function(result){
	                	$('#subGoodsDatagrid').datagrid('reload');  //ˢ����Ʒ�б���Ϣ   
	                },'json');
				}		
			}
		});
	</script>
	</body>
</html>