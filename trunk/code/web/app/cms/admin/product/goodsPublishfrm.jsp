<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��Ʒ����</title>
		<%@ include file="/common/jqueryui.jsp"%>
	</head>
	<body>
	<div class="easyui-layout" fit="true" >
		<div region="west" id="goodsCategory" split="false" border="true" id="left_area" title="��Ʒ����" style="width:200px;">
			<ul id='goodsCategoryTree_publish' class='easyui-tree' fit="true"></ul>
    	</div>  
    	<div id="content" region="center" id="right_area" style="padding:0px;" fit="false" border="true"> 
			<!-- begin of ��Ʒ�����б� -->
			<div id="goodsPublishtoolbar">
				<span style="float: left; padding-top: 3px; padding-left: 10px;">
					<select id="goodsPublish_isPublish" class="easyui-combobox" style="width: 100px;">
						<option value="0">δ����</option>
						<option value="1">�ѷ���</option>
					</select> </span>
				<span style="float: right; margin-right: 10px; padding-top: 2px;">
					<input type="text" name="searchWord" value=""
						style="width: 200px; height: 18px; border:1px solid #CCCCCC;"/> <a href="#"
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="goodsPublishAction.searchPublishGoods()">����</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="goodsPublishExportBtn"
			iconCls="icon-undo" onclick="goodsPublishAction.exportProduct()">����</a> 
					<a href="#"
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="goodsPublishAction.highSearchPublishGoods()">�߼�����</a></span>
			</div>
			<table id="goodsPublishDatagrid" toolbar="#goodsPublishtoolbar">
			</table>
		</div>
	</div>
		<!-- end of ��Ʒ�����б� -->

		<!-- begin of ������Ʒ -->
		<div id="editGoodsPublishDialog"
			style="width: 950px; height: 450px; padding: 10px 10px 0px;">
			<%@ include file="/app/cms/admin/product/editGoodsPublishfrm.jsp"%>
		</div>
		<!-- end of ������Ʒ -->

		<!--begin of script -->
		<script type="text/javascript" charset="GBK">
	var selectedCategoryId_publish = "";
	//��ʼ��
	$(function(){
		//��ʼ�� -> ��ʼ����Ŀ��
		$('#goodsCategoryTree_publish').tree({
			url: mainWeb + "/admin/goods/getTreeChildrenNode",
			onSelect: function(node) {
				selectedCategoryId_publish = node.id;
				if (node.id == 'root') {
					selectedCategoryId_publish = '';
				} 
				goodsPublishAction.searchPublishGoods(selectedCategoryId_publish);
			}
		});
		
		//��ʼ�� --> ���Dialog��Debug
		var editGoodsPublishDialogDebug = $("#pageContent #editGoodsPublishDialog");
		for(var i =1;i< editGoodsPublishDialogDebug.length;i++){
			$(editGoodsPublishDialogDebug[i]).dialog("destroy");
		}
		
		//��ʼ�� -> �Ի���ر��¼�
		$("#editGoodsPublishDialog").dialog({
			modal:true,
			closable:true,
			maximizable:true,
			title:"������Ʒ",
			closed:true,
			buttons:[{
					id:'goodsPublish_saveButton',
					text:'����',
					iconCls:'icon-ok',
					handler:function(){editGoodsPublishAction.save();}
				},{
					id:'goodsPublish_publishButton',
					text:'����',
					iconCls:'icon-ok',
					handler:function(){editGoodsPublishAction.publish();}
				},{
					text:'�ر�',
					iconCls:'icon-cancel',
					handler:function(){$('#editGoodsPublishDialog').dialog('close');}
				}
			],
			onClose:function(){
				//�����������
				$('#editGoodsPublishTd').empty();
				/*
				$("table.effacts").each(function(index,domEle){
					var id = $(domEle).attr("id").substring(7);
					editGoodsPublishAction.deleteEffact(id);				
				});
				$("table.effacts").remove();
				*/
				$("img.preBrowerImg_1").attr("src","");
				$("img.preBrowerImg_2").attr("src","");
				$("img.preBrowerImg_3").attr("src","");
				//�����̳Ǽ۸�
				$("td span.score").hide();
			}
		});
		
		//��ʼ�� --> �û� --> �����û��б���
		$('#goodsPublishDatagrid').datagrid({
			border:false,
			idField:'recid',
			rownumbers:true,
			fitColumns:true,
			fit:true,
			singleSelect:true,
			remoteSort:false,
			pagination:true,
			url:'<%=mainWeb%>/admin/goods/getPublishGoods?selectedCategoryId_publish='+selectedCategoryId_publish,
			columns:[[
				{field:'goodscode',title:'���',width:50,align:'center',sortable:true},
				{field:'goodsname',title:'����',width:100,align:'center',sortable:true},
				{field:'goodsspec',title:'���',width:35,align:'center',sortable:true},
				{field:'goodsunit',title:'��λ',width:30,align:'center',sortable:true},				
				{field:'originalprice',title:'ԭ��',width:40,align:'center',sortable:true},
				{field:'realprice',title:'�ּ�',width:40,align:'center',sortable:true},
				{field:'vantagestype',title:'�������',width:40,align:'center',sortable:true,
					formatter:function(value){
						if(value == "0"){
							return "�޻���";
						}else if(value == "1"){
							return "��ͨ����";
						}else if(value == "2"){
							return "˫������";
						}
					}
				},
				{field:'freedelivery',title:'�Ƿ�����ͻ�',width:50,align:'center',sortable:true,
					formatter:function(value){
						if(value == "0" || value == false){
							return "�շ�";
						}else{
							return "���";
						}
					}
				},
				{field:'published',title:'����״̬',width:40,align:'center',sortable:true,
					formatter:function(value){
						if(value==null || value == "0"){
							return "δ����";
						}else{
							return "�ѷ���";
						}
					}
				},
				{field:'recid',title:'����',width:80,align:'center',
					formatter:function(value,rec,rowIndex){
						var publishGoods = "";
						var cannelPublish = "";
						if(rec.published == true){
							publishGoods = "<a href='#' class='operateChannel' onClick=goodsPublishAction.publishGoods('"+rowIndex+"')>������Ʒ</a>"
							cannelPublish += "&nbsp;&nbsp;&nbsp;&nbsp;";
							cannelPublish += "<a href='#' class='operateChannel' onClick=goodsPublishAction.cancelPublished('"+value+"')>ȡ������</a>";				
						}else{
							publishGoods = "<a href='#' class='operateChannel' onClick=goodsPublishAction.publishGoods('"+rowIndex+"')>������Ʒ</a>"
						}
						return publishGoods + cannelPublish;
					}
				}
			]]
		});
		
		//��ʼ�� -> �ѷ���/δ���������ˣ�
		$("select#goodsPublish_isPublish").combobox({
			onSelect:function(record){
				$("input[name='searchWord']").val("");
				//���ط�����Ʒ
				$('#goodsPublishDatagrid').datagrid('loadData', { total: 0, rows: [] });
				$("#goodsPublishDatagrid").datagrid('reload',{
					ispublished:record.value,
					categoryId: selectedCategoryId_publish
				});
				//����/���ð�ť
				if(record.value == "1"){
					goodsPublish_buttonsEnable = false;
					//$("a#editGoodsPublish_addEffactButton").linkbutton('disable');
					//$("a#editGoodsPublish_selectImgButton1").linkbutton('disable');
					//$("a#editGoodsPublish_selectImgButton2").linkbutton('disable');
					//$("a#editGoodsPublish_selectImgButton3").linkbutton('disable');
					$("#goodsPublish_saveButton").linkbutton('disable');
					//$("#goodsPublish_publishButton").linkbutton('disable');
				}else{
					goodsPublish_buttonsEnable = true;
					//$("a#editGoodsPublish_addEffactButton").linkbutton('enable');
					//$("a#editGoodsPublish_selectImgButton1").linkbutton('enable');
					//$("a#editGoodsPublish_selectImgButton2").linkbutton('enable');
					//$("a#editGoodsPublish_selectImgButton3").linkbutton('enable');
					$("#goodsPublish_saveButton").linkbutton('enable');
					//$("#goodsPublish_publishButton").linkbutton('enable');
				}
			}
		});
	});
	
	//��Ʒ����
	var goodsPublishAction;
	$(function(){
		//��Ʒ���� --> ��ʼ��userAction��
		goodsPublishAction = new GoodsPublishAction();
		//��Ʒ���� --> ������Ʒ������(GoodsPublishAction)
		function GoodsPublishAction(){
			/*
			//��Ʒ���� --> ������Ʒ -> �򿪶Ի���
			this.publishGoods = function(){
				var row = $('#goodsPublishDatagrid').datagrid('getSelected');
				if(row){
					$('#editGoodsPublishDialog').dialog('open');
					$('#editGoodsPublishForm').form('clear');
					$('#editGoodsPublishForm').form('load',row);					
					goodsPublishAction.fillImageSrc(row);
					$("td span.score").hide();
					//��Ⱦ���ı��༭��
					editGoodsPublishAction.initEffact(3);
				}else{
					$.messager.alert('��ʾ','��ѡ��Ҫ�ķ�����Ʒ!','warning');
				}					
			}
			*/
			//��Ʒ���� --> ������Ʒ -> �򿪶Ի���
			this.publishGoods = function(rowIndex){
				$('#goodsPublishDatagrid').datagrid('selectRow',rowIndex);
				var row = $('#goodsPublishDatagrid').datagrid('getSelected');  
				if (row){
					$('#editGoodsPublishDialog').dialog('open').dialog('setTitle','������Ʒ');
					editGoodsPublish_editor0 = null;
					editGoodsPublish_editor1 = null;
					editGoodsPublish_editor2 = null;
					editGoodsPublish_editor3 = null;
					editGoodsPublish_editor4 = null;
					editGoodsPublish_editor5 = null;
					editGoodsPublish_editor6 = null;
					editGoodsPublish_editor7 = null;
					editGoodsPublish_editor8 = null;
					editGoodsPublish_editor9 = null;
					$('#editGoodsPublishForm').form('clear');
					$('#editGoodsPublishForm').form('load',row);
					goodsPublishAction.fillImageSrc(row);
					if(row.vantagesGoods){
						$("input[name='isVantagesGoods']").click();
						$("td span.score").show();
					}
					//������������					
					goodsPublishAction.loadEffactDate(row);
				}else{
					$.messager.alert('��ʾ','��ѡ��Ҫ��������Ʒ!','warning');
				}
			}
			
			//��Ʒ���� -> ȡ������
			this.cancelPublished = function(goodsId){
				$.ajax({
					url: mainWeb + "/admin/goods/cancelPublished",
					data: {"goodsId":goodsId},
					cache: false,
					success: function(data) {
						$('#goodsPublishDatagrid').datagrid('loadData', { total: 0, rows: []});
						$("#goodsPublishDatagrid").datagrid('reload',{
							searchWord:$("input[name='searchWord']").val(),
							ispublished:$("select#goodsPublish_isPublish").combobox("getValue"),
							categoryId: selectedCategoryId_publish
						});
					}
				});
				/*
					$.get(mainWeb + "/admin/goods/cancelPublished",{"goodsId":goodsId},function(data){
						$('#goodsPublishDatagrid').datagrid('loadData', { total: 0, rows: []});
						$("#goodsPublishDatagrid").datagrid('reload',{
							searchWord:$("input[name='searchWord']").val(),
							ispublished:$("select#goodsPublish_isPublish").combobox("getValue")
						});
				});*/
			};
			
			//��Ʒ���� -> �򿪶Ի��� -> ����ͼƬ·��
			this.fillImageSrc = function(row){
				$("td img.preBrowerImg_1").attr("src",mainWeb + row.picturepath1);
				goodsPublishAction.fillImageTitle($("td img.preBrowerImg_1"),row.picturepath1)
				$("td img.preBrowerImg_2").attr("src",mainWeb + row.picturepath2);
				goodsPublishAction.fillImageTitle($("td img.preBrowerImg_2"),row.picturepath2)
				$("td img.preBrowerImg_3").attr("src",mainWeb + row.picturepath3);
				goodsPublishAction.fillImageTitle($("td img.preBrowerImg_3"),row.picturepath3)
			}
			
			//��Ʒ���� -> �򿪶Ի��� -> ����ͼƬ���
			this.fillImageTitle = function(obj,imgUrl){
				$.ajaxSetup({cache: false});
				$.getJSON('<%=mainWeb%>/admin/goods/getPublishGoodsSpecial',{'imgUrl':imgUrl},function(data){
					if(data != null || data != "undefined"){
						$(obj).attr("title","ͼƬ���("+data.imgWidth + " �� " + data.imgHeight+")");
					}
				});
			}
			
			//��Ʒ���� -> �޸���Ʒ -> ������������
			this.loadEffactDate = function(row){
				$.ajaxSetup({cache: false});
				//������ƷID��ȡ��������
				$.getJSON('<%=mainWeb%>/admin/goods/getPublishGoodsEffacts',{'goodsId':row.recid},function(data){
					//�ж��Ƿ�������
					if(data == null || data == "undefined" || data.length < 1 ){
						//��Ⱦ���ı��༭��
						editGoodsPublishAction.initEffact(1,true);
						return;
					}
					//��Ⱦ���ı��༭��
					editGoodsPublishAction.initEffact(data.length,false);
				 	for(var i = 0;i<data.length;i++){
                		$('input[name=effactName_'+i+']').val(data[i].contenttitle);
                		$('input[name=effactOrdinal_'+i+']').val(data[i].ordinal);
                		goodsPublishAction.setEffactContent(i,data[i].goodscontent);
                	}
				});
			}
			
			//��Ʒ���� -> �༭������Ʒ -> ������������
			this.setEffactContent = function(temp_id,temp_content){
				if(temp_id == 0){
					editGoodsPublish_editor0.ready(function(){
						editGoodsPublish_editor0.setContent(temp_content);	
					});
				}else if(temp_id == 1){
					editGoodsPublish_editor1.ready(function(){
						editGoodsPublish_editor1.setContent(temp_content);	
					});
				}else if(temp_id == 2){
					editGoodsPublish_editor2.ready(function(){
						editGoodsPublish_editor2.setContent(temp_content);	
					});
				}else if(temp_id == 3){
					editGoodsPublish_editor3.ready(function(){
						editGoodsPublish_editor3.setContent(temp_content);	
					});
				}else if(temp_id == 4){
					editGoodsPublish_editor4.ready(function(){
						editGoodsPublish_editor4.setContent(temp_content);	
					});
				}else if(temp_id == 5){
					editGoodsPublish_editor5.ready(function(){
						editGoodsPublish_editor5.setContent(temp_content);	
					});
				}else if(temp_id == 6){
					editGoodsPublish_editor6.ready(function(){
						editGoodsPublish_editor6.setContent(temp_content);	
					});
				}else if(temp_id == 7){
					editGoodsPublish_editor7.ready(function(){
						editGoodsPublish_editor7.setContent(temp_content);	
					});
				}else if(temp_id == 8){
					editGoodsPublish_editor8.ready(function(){
						editGoodsPublish_editor8.setContent(temp_content);	
					});
				}else if(temp_id == 9){
					editGoodsPublish_editor9.ready(function(){
						editGoodsPublish_editor9.setContent(temp_content);	
					});
				}
			};
			
			//��Ʒ���� -> ���� 
			this.searchPublishGoods = function(nCategoryId){
				if (!nCategoryId) {
					nCategoryId = '';
				}
				$('#goodsPublishDatagrid').datagrid('loadData', { total: 0, rows: [] });
				$("#goodsPublishDatagrid").datagrid('reload',{
					searchWord:$("input[name='searchWord']").val(),
					ispublished:$("select#goodsPublish_isPublish").combobox("getValue"),
					categoryId: selectedCategoryId_publish
				});
			};
			
			//��Ʒ���� -> ����
			this.exportProduct = function(){
				var searchWord = $("input[name='searchWord']").val();
				searchWord = encodeURI(searchWord);
				var ispublished = $("select#goodsPublish_isPublish").combobox("getValue");
				$('#goodsPublishExportBtn').attr("href","<%=mainWeb%>/admin/goods/exportGoodsPublish?searchWord="+searchWord+"&ispublished="+ispublished+"&categoryId="+selectedCategoryId_publish);
			}
			
			//��Ʒ���� -> �߼�����
			this.highSearchPublishGoods = function(){
				GoodsAdvanceSearchWindow(function(searchData) {
					$("#goodsPublishDatagrid").datagrid('reload',{
						ispublished:$("select#goodsPublish_isPublish").combobox("getValue"),
						advance: searchData.advance,
						categoryId: selectedCategoryId_publish
					});
				});
			};
		}
		
	});
	
</script>
	</body>
</html>