<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>���ݹ���</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
		<!-- begin of ���� -->
		<div id="contentfrmLayout" class="easyui-layout" fit="true">
			<!-- begin of �� -->
			<div data-options="region:'north',border:false,noheader:true"
				style="height: 30px; background-color: rgb(239, 239, 239); text-align: right; padding-top: 3px;">
				<span id="contentfrm_buttons_advertising" style="display:none;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="subAdvertisingAction.addAdvertising()">�������</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
						plain="true" onclick="subAdvertisingAction.updateAdvertising()">�޸Ĺ��</a>
				</span>
				<span id="contentfrm_buttons_content" style="display:none;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add"
						plain="true" onclick="subContentAction.addContent()">��������</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
						plain="true" onclick="subContentAction.updateContent()">�޸�����</a>
				</span>
				<span id="contentfrm_buttons_goods" style="display:none;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						plain="true" onclick="subGoodsAction.selectGoods()">ѡ����Ʒ</a>
				</span>	
			</div>
			<!-- end of �� -->
			<!-- begin of �� -->
			<div data-options="region:'west',title:'��Ŀ',split:true,border:true"
				style="width: 200px;">
				<ul id="channelTreeOfContent" class="easyui-tree" lines="true"
					url="<%=mainWeb%>/admin/channel/getChannels">
				</ul>
			</div>
			<!-- end of �� -->
			<!-- begin of �� -->
			<div data-options="region:'center',title:'����',border:true">
			</div>
			<!-- end of �� -->

		</div>
		<!-- end of ���� -->

		<script type="text/javascript">
	//��ʼ��
	$(function(){
		//�����¼� --> �����ڵ�
		$('#channelTreeOfContent').tree({
			onLoadSuccess: function(node, data) {
			},
			onClick: function(node){
				if($("#channelTreeOfContent").tree("isLeaf",node.target)){
					//��������
					contentAction.load(node);
				}else{
					contentAction.disableButtons();
				}
			}
		});	
	});
	
	//���ݹ���
	var contentAction;
	var channelId;
	var channelName;
	var channelNode;
	$(function(){
		//���ݹ��� -> ��ʼ��
		contentAction = new ContentAction();
		//���ݹ��� -> ����������
		function ContentAction(){
			//���ݼ��� -> ���ð�ť
			this.disableButtons = function(){
				$("#contentfrmLayout #contentfrm_buttons_advertising").css("display","none");
				$("#contentfrmLayout #contentfrm_buttons_content").css("display","none");
				$("#contentfrmLayout #contentfrm_buttons_goods").css("display","none");
			};
		
			//���ݹ��� -> ����
			this.load = function(node){
				contentAction.disableButtons();
				channelId = node.id;
				channelName = node.text;
				channelNode = node;
				if (node.attributes.autoContent) {
					$("#contentfrmLayout").layout("panel","center").panel("setTitle",channelName);
					var html = "<span style='padding-left: 10px;font-size: 14px;'>����Ŀ����Ϊ�Զ���ȡ������Ҫ���������ˡ�</span>";
					
					$("#contentfrmLayout").layout("panel","center").html(html);
					return;
				}
				var title="";
				var href="";
				if(node.attributes.channeltype == "01"){ //��Ʒ
					title="��Ʒ";
					href="<%=mainWeb%>/app/cms/admin/content/subGoodsfrm.jsp";
					$("#contentfrmLayout #contentfrm_buttons_goods").css("display","block");
				} else if(node.attributes.channeltype == "02"){ //���
					title="���λ";
					href="<%=mainWeb%>/app/cms/admin/content/subAdvertisingfrm.jsp";
					$("#contentfrmLayout #contentfrm_buttons_advertising").css("display","block");
				} else { //����
					title="����";
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
