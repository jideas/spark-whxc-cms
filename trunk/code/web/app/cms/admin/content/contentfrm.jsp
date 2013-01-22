<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>内容管理</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of 布局 -->
		<div id="contentfrmLayout" class="easyui-layout" fit="true">
			<!-- begin of 北 -->
			<div data-options="region:'north',border:false,noheader:true"
				style="height: 30px; background-color: rgb(239, 239, 239); text-align: right; padding-top: 3px;">
				<span id="contentfrm_buttons_advertising" style="display:none;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="subAdvertisingAction.addAdvertising()">新增广告</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
						plain="true" onclick="subAdvertisingAction.updateAdvertising()">修改广告</a>
				</span>
				<span id="contentfrm_buttons_content" style="display:none;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="subContentAction.addContent()">新增内容</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
						plain="true" onclick="subContentAction.updateContent()">修改内容</a>
				</span>
				<span id="contentfrm_buttons_goods" style="display:none;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						plain="true" onclick="subGoodsAction.selectGoods()">选择商品</a>
				</span>	
			</div>
			<!-- end of 北 -->
			<!-- begin of 西 -->
			<div data-options="region:'west',title:'栏目',split:true,border:true"
				style="width: 200px;">
				<ul id="channelTreeOfContent" class="easyui-tree" lines="true"
					url="<%=mainWeb%>/admin/channel/getChannels">
				</ul>
			</div>
			<!-- end of 西 -->
			<!-- begin of 中 -->
			<div data-options="region:'center',title:'内容',border:true">
			</div>
			<!-- end of 中 -->

		</div>
		<!-- end of 布局 -->

		<script type="text/javascript">
	//初始化
	$(function(){
		//监听事件 --> 单击节点
		$('#channelTreeOfContent').tree({
			onLoadSuccess: function(node, data) {
			},
			onClick: function(node){
				if($("#channelTreeOfContent").tree("isLeaf",node.target)){
					//加载内容
					contentAction.load(node);
				}else{
					contentAction.disableButtons();
				}
			}
		});	
	});
	
	//内容管理
	var contentAction;
	var channelId;
	var channelName;
	var channelNode;
	$(function(){
		//内容管理 -> 初始化
		contentAction = new ContentAction();
		//内容管理 -> 定义内容类
		function ContentAction(){
			//内容加载 -> 禁用按钮
			this.disableButtons = function(){
				$("#contentfrmLayout #contentfrm_buttons_advertising").css("display","none");
				$("#contentfrmLayout #contentfrm_buttons_content").css("display","none");
				$("#contentfrmLayout #contentfrm_buttons_goods").css("display","none");
			};
		
			//内容管理 -> 加载
			this.load = function(node){
				contentAction.disableButtons();
				channelId = node.id;
				channelName = node.text;
				channelNode = node;
				if (node.attributes.autoContent) {
					$("#contentfrmLayout").layout("panel","center").panel("setTitle",channelName);
					var html = "<span style='padding-left: 10px;font-size: 14px;'>该栏目内容为自动获取，不需要进行设置了。</span>";
					
					$("#contentfrmLayout").layout("panel","center").html(html);
					return;
				}
				var title="";
				var href="";
				if(node.attributes.channeltype == "01"){ //商品
					title="商品";
					href="<%=mainWeb%>/app/cms/admin/content/subGoodsfrm.jsp";
					$("#contentfrmLayout #contentfrm_buttons_goods").css("display","block");
				} else if(node.attributes.channeltype == "02"){ //广告
					title="广告位";
					href="<%=mainWeb%>/app/cms/admin/content/subAdvertisingfrm.jsp";
					$("#contentfrmLayout #contentfrm_buttons_advertising").css("display","block");
				} else { //内容
					title="内容";
					href="<%=mainWeb%>/app/cms/admin/content/subContentfrm1.jsp";
					$("#contentfrmLayout #contentfrm_buttons_content").css("display","block");
				}
				$("#contentfrmLayout").layout("panel","center").panel("setTitle",title);
				$("#contentfrmLayout").layout("panel","center").panel("refresh",href);
			};
			
		}
		
	});	
</script>
	</body>
</html>
