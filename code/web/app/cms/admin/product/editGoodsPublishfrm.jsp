<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�༭������Ʒ</title>
	</head>
	<style type="text/css">
.labelAlignRight {
	text-align: right;
	width: 100px;
	display: inline-block;
	font-size: 12px;
}

input.disabledEditor {
	width: 300px;
	border: #bfccd8 1px solid;
	background-color: RGB(252, 252, 252);
}

table.table_layout thead p {
	height: 25px;
	line-height: 25px;
	margin: 0px;
}
table.table_layout thead p.p_img{
	height: 55px;
	line-height: 55px;
}
table.table_layout thead p.p_img span{
	float: left;
}
</style>
	<body>
		<script type="text/javascript" charset="gbk"
			src="<%=mainWeb%>/scripts/ueditor/editorcustomconfig.js"></script>
		<script type="text/javascript" charset="gbk"
			src="<%=mainWeb%>/scripts/ueditor/editorcustom.js"></script>
		<!-- begin of �༭������Ʒ -->
		<form id="editGoodsPublishForm" method="post"
			modelAttribute="goodsForm">
			<input type="hidden" name="recid" />
			<input type="hidden" name="isPublished" value="false" />
			<table style="font-size: 12px;" width="880px" class="table_layout">
				<thead>
					<tr>
						<td>
							<p>
								<span class="labelAlignRight"">���룺</span>
								<span><input type="text" name="goodscode"
										readonly="readonly" class="disabledEditor" /> </span>
								<span class="labelAlignRight">���룺</span>
								<span><input type="text" name="goodsno"
										readonly="readonly" class="disabledEditor" /> </span>
							</p>
							<p>
								<span class="labelAlignRight">��Ʒ���ƣ�</span>
								<span><input type="text" name="goodsname"
										readonly="readonly" class="disabledEditor" /> </span>
							</p>
							<p>
								<span class="labelAlignRight">���</span>
								<span><input type="text" name="goodsspec"
										readonly="readonly" class="disabledEditor" /> </span>
								<span class="labelAlignRight">��λ��</span>
								<span><input type="text" name="goodsunit"
										readonly="readonly" class="disabledEditor" /> </span>
							</p>
							<p>
								<span class="labelAlignRight">ԭ�ۣ�</span>
								<span><input type="text" name="originalprice"
										readonly="readonly" class="disabledEditor" /> </span>
								<span class="labelAlignRight">���ۼ۸�</span>
								<span><input type="text" name="realprice"
										readonly="readonly" class="disabledEditor" /> </span>
							</p>
							<p style="height: 25px;line-height: 25px;">
								<span class="labelAlignRight">�����̳ǣ�</span>
								<span
									style="width: 303px; *width: 306px; display: inline-block;"><input
										type="checkbox" name="isVantagesGoods" /> </span>
								<span class="labelAlignRight score">�̳Ǽ۸�</span>
								<span class="score"><input type="text"
										name="vantagesCost" class="disabledEditor"
										style="background-color: #FFF;" /> 
								</span>
							</p>
							<p
								style="height: 1px; line-height: 1px; border-top: 1px solid #c9cdd0; width: 880px;"></p>
							<p class="p_img">
								<span class="labelAlignRight">����ͼ��</span>
								<span style="margin-top: 15px;">
									<input type="text" name="picturepath1"
										readonly="readonly" class="disabledEditor" /> 
								</span>
								<span class="labelAlignRight" style="margin-top: 15px;">
									<a href="#"	onclick="editGoodsPublishAction.selectImg(1)"
									id="editGoodsPublish_selectImgButton1"> ѡ�� </a>
								</span>
								<span/>
									<img src="" width="50px" height="50px"
										class="preBrowerImg_1" style="border: 1px solid #EEE;margin-left:17px;*margin-left:15px;" /> 
								</span>
							</p>
							<p class="p_img">
								<span class="labelAlignRight">����ͼ��</span>
								<span style="margin-top: 15px;">
									<input type="text" name="picturepath2"
										readonly="readonly" class="disabledEditor" /> 
								</span>
								<span class="labelAlignRight" style="margin-top: 15px;">
									<a style="line-height: 20px;" href="#"
									onclick="editGoodsPublishAction.selectImg(2)"
									id="editGoodsPublish_selectImgButton2"> ѡ�� </a>
								</span>
								<span >
									<img src="" width="50px" height="50px"
										class="preBrowerImg_2" style="border: 1px solid #EEE;margin-left:17px;" /> 
								</span>
							</p>
							<p class="p_img">
								<span class="labelAlignRight">����ͼ��</span>
								<span style="margin-top: 15px;">
									<input type="text" name="picturepath3"
										readonly="readonly" class="disabledEditor" /> 
								</span>
								<span class="labelAlignRight" style="margin-top: 15px;">
									<a href="#" onclick="editGoodsPublishAction.selectImg(3)"
									id="editGoodsPublish_selectImgButton3"> ѡ�� </a> 
								</span>
								<span>
									<img src="" width="50px" height="50px"
										class="preBrowerImg_3" style="border: 1px solid #EEE;margin-left:17px;" /> 
								</span>
							</p>
							<p
								style="height: 1px; line-height: 1px; border-top: 1px solid #c9cdd0; width: 880px;"></p>
						</td>
					</tr>					
				</thead>
				<tbody>
					<tr>
						<td id="editGoodsPublishTd">
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<a href="#" id="editGoodsPublish_addEffactButton"
								onclick="editGoodsPublishAction.addEffact()">��������</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
		<!-- end of �༭������Ʒ -->

		<script type="text/javascript" charset="GBK">
	//��ʼ�� -> ��ťЧ��
	$("a#editGoodsPublish_addEffactButton").linkbutton({
		iconCls:"icon-add"
	});
	$("a#editGoodsPublish_selectImgButton1").linkbutton({});
	$("a#editGoodsPublish_selectImgButton2").linkbutton({});	
	$("a#editGoodsPublish_selectImgButton3").linkbutton({});			
		
	//��ʼ��
	var goodsPublish_buttonsEnable = true;
	var editGoodsPublishOption;
	var editGoodsPublish_editor0;
	var editGoodsPublish_editor1;
	var editGoodsPublish_editor2;
	var editGoodsPublish_editor3;
	var editGoodsPublish_editor4;
	var editGoodsPublish_editor5;
	var editGoodsPublish_editor6;
	var editGoodsPublish_editor7;
	var editGoodsPublish_editor8;
	var editGoodsPublish_editor9;
	$(function(){
		//��ʼ�� -> ���帻�ı��༭������
		editGoodsPublishOption = {
	       	initialContent: '',//��ʼ���༭��������
	        minFrameHeight: 300,
	        imageUrl: mainWeb + '/admin/image/uploadImage',
	        imagePath:mainWeb,
	        imageManagerUrl: mainWeb + '/admin/image/getOnlineImagesList',
	        imageManagerPath:mainWeb           
	    };
	});	
	
	//�༭������Ʒ -> ��ʾ�����ػ����̳�
	$("td span.score").hide();
	$("input[name='isVantagesGoods']").bind("click",function(){
		if($(this).attr("checked")){
			$("td span.score").show();
		}else{
			$("td span.score").hide();
		}
	});
	
	//�༭������Ʒ
	var editGoodsPublishAction;
	$(function(){
	    $("#vantagesCost").attr("preValue",$("#vantagesCost").val());
		//�༭������Ʒ -> ʵ����
		editGoodsPublishAction = new EditGoodsPublishAction(1);
		//�༭������Ʒ -> ����editGoodsPublishAction��
		function EditGoodsPublishAction(effactNum){
			var url = "";
			var message = "";
			//�༭������Ʒ -> ����
			this.save = function(){
				url = mainWeb + '/admin/goodsPublish/publishPublishGoods?goodsContent=';
				message = "����";
				this.saveOrUpdate();
			};
			//���������У��
			this.numberChangeListener = function(input) {
		var reg = new RegExp("^[0-9]*[1-9][0-9]*$");
		if ($(input).val().match(reg) == null) {
			$(input).val($(input).attr("preValue"));
		} else {
			$(input).attr("preValue", $(input).val());
		}
	}
			
			//�༭������Ʒ -> ����
			this.publish = function(){
				$("input[name='isPublished']").val("true");
				url = mainWeb + '/admin/goodsPublish/publishPublishGoods?goodsContent=';
				message = "����";
				this.saveOrUpdate();
			};
			
			//�༭������Ʒ -> ������޸�
			this.saveOrUpdate = function(){
				//У������
				if(!validateForm()) return;
				if(!validateEffact()) return;
				var effacts = editGoodsPublishAction.pieceEffactContent();
				var parameters = $('#editGoodsPublishForm').serialize();
				effacts = encodeURIComponent(effacts);
				$.ajax({
					type: 'post',
					url: url,
					contentType: "application/x-www-form-urlencoded; charset=utf-8",
					data: parameters + "&goodsContent=" + effacts,
					dataType: 'json',
					success: function(data) {
				    	if(data.result){
				    		$.messager.alert('��ʾ',message+'��Ʒ�ɹ�!','info');//�����ɹ�������ʾ��Ϣ			    	
					    	$('#editGoodsPublishDialog').dialog('close'); //�رնԻ���
					    	 $('#goodsPublishDatagrid').datagrid('loadData', { total: 0, rows: [] }); 
					    	$('#goodsPublishDatagrid').datagrid('reload',{
					    		searchWord:$("input[name='searchWord']").val(),
								ispublished:$("select#goodsPublish_isPublish").combobox("getValue"),
								categoryId: selectedCategoryId_publish
					    	});  //ˢ�·�����Ʒ�б���Ϣ
				    	}else{
				    		$.messager.alert('��ʾ',message+'��Ʒʧ��!','waring');
				    	}
					}
				});
			};
			
			//��֤form
			function validateForm(){
				//�̳Ǽ۸�
				if($("#editGoodsPublishForm input[name='isVantagesGoods']").attr("checked")=="checked"){
					var reg = /^\d{1,11}(\.{1})?(\d{1,2})?$/;
					var vantagesCost = $.trim($("#editGoodsPublishForm input[name='vantagesCost']").val());			
					if(!reg.test(vantagesCost)){
						$.messager.alert('��ʾ','�̳Ǽ۸���ȷ!','info');
						return false;
					}else if(vantagesCost == 0){
						$.messager.alert('��ʾ','�̳Ǽ۸�������0!','info');
						return false;
					}
				}
				//����ͼ
				if($.trim($("#editGoodsPublishForm input[name='picturepath3']").val()) == ""){
					$.messager.alert('��ʾ','��ѡ������ͼ!','info');
					return false;
				}
				//����ͼ
				if($.trim($("#editGoodsPublishForm input[name='picturepath1']").val()) == ""){
					$("#editGoodsPublishForm input[name='picturepath1']").val($("#editGoodsPublishForm input[name='picturepath3']").val());
				}
				//����ͼ
				if($.trim($("#editGoodsPublishForm input[name='picturepath2']").val()) == ""){
					$("#editGoodsPublishForm input[name='picturepath2']").val($("#editGoodsPublishForm input[name='picturepath3']").val());
				}				
				return true;
			}
			
			//��֤����
			function validateEffact(){
				var effactsObj = $('.effacts');
				for(var i = 0;i<effactsObj.length;i++){
					var temp_id = parseInt($(effactsObj[i]).attr("id").substring(7));
					temp_content = $.trim(editGoodsPublishAction.returnEffactContent(temp_id));
					temp_name = $.trim($('input[name=effactName_'+temp_id+']').val());
					temp_ordinal = $.trim($('input[name=effactOrdinal_'+temp_id+']').val());	
					if(temp_name == ""){
						$.messager.alert('��ʾ','�������δ��д!','info');
						return false;
					}
					var reg = /^\d{1,10}$/;
					if(temp_ordinal == ""){
						$.messager.alert('��ʾ','���δ��д!','info');
						return false;
					}
					if(!reg.test(temp_ordinal)){
						$.messager.alert('��ʾ','�����д����!','info');
						return false;
					}
					if(temp_content == ""){
						$.messager.alert('��ʾ','��������δ��д!','info');
						return false;
					}
				}
				return true;
			}
			
			//�༭������Ʒ -> ѡ��ͼƬ
			this.selectImg = function(temp_id){
				var goodsname = $("#editGoodsPublishForm input[name='goodsname']").val();
				var editGoodsPublish_chooseImagesWindow = new ChooseImagesWindow(goodsname);
				editGoodsPublish_chooseImagesWindow.addConfirmActionListener(function(path,width,height){
					$("td input[name='picturepath"+temp_id+"']").val(path);
					$("td img.preBrowerImg_"+temp_id).attr("src", mainWeb + path);
					$("td img.preBrowerImg_"+temp_id).attr("title", "ͼƬ���("+width+" �� "+ height + ")");
				});
			};
			
			//�༭������Ʒ -> ���� -> ��װ��������
			this.pieceEffactContent = function(){
				var effacts = "[";
				var effactsObj = $('.effacts');
				var temp_content = '';
				for(var i = 0;i<effactsObj.length;i++){
					var temp_id = parseInt($(effactsObj[i]).attr("id").substring(7));
					temp_content = editGoodsPublishAction.returnEffactContent(temp_id);		
					effacts = effacts + '{';
					effacts = effacts + "'name':";
					effacts = effacts + "'" + $('input[name=effactName_'+temp_id+']').val() + "'";
					effacts = effacts + ",'ordinal':";
					effacts = effacts + "'" +$('input[name=effactOrdinal_'+temp_id+']').val() + "'";
					effacts = effacts + ",'content':" + "'" + temp_content + "'";
					if(i==(effactsObj.length - 1)){
						effacts = effacts + '}';
					}else{
						effacts = effacts + '},';
					}
				}
				effacts = effacts + "]";
				return effacts;
			};
			
			//�༭������Ʒ -> ���� -> ��װ�������� -> ������������
			this.returnEffactContent = function(temp_id){
				var temp_content;
				if(temp_id == 0){
					temp_content = editGoodsPublish_editor0.getContent();
				}else if(temp_id == 1){
					temp_content = editGoodsPublish_editor1.getContent();
				}else if(temp_id == 2){
					temp_content = editGoodsPublish_editor2.getContent();
				}else if(temp_id == 3){
					temp_content = editGoodsPublish_editor3.getContent();
				}else if(temp_id == 4){
					temp_content = editGoodsPublish_editor4.getContent();
				}else if(temp_id == 5){
					temp_content = editGoodsPublish_editor5.getContent();
				}else if(temp_id == 6){
					temp_content = editGoodsPublish_editor6.getContent();
				}else if(temp_id == 7){
					temp_content = editGoodsPublish_editor7.getContent();
				}else if(temp_id == 8){
					temp_content = editGoodsPublish_editor8.getContent();
				}else if(temp_id == 9){
					temp_content = editGoodsPublish_editor9.getContent();
				}
				return temp_content;
			};
			
			//�༭������Ʒ -> ���� -> ɾ������
			this.deleteEffact = function(num){
				editGoodsPublishAction.destroyEditor(num);
				$("table").remove("#effact_"+num);
			};			
			
			//�༭������Ʒ -> ���� -> ��������
			this.addEffact = function(){
				if($("table.effacts").length >= 10){
					$.messager.alert('��ʾ','���������Ѵ�����!','info');//���������Ѵ�������ʾ��Ϣ
					return;
				}
				var id = editGoodsPublishAction.getEnabledEffact();
				//����Editor
				editGoodsPublishAction.createEffact(id,false);
			};
			
			//�༭������Ʒ -> ���� -> �������� -> �жϲ���ȡû��ʹ�õı༭��
			this.getEnabledEffact = function(){
				var arrayObj = new Array($("table.effacts").length);
				$("table.effacts").each(function(index,domEle){
					arrayObj[index] = $(domEle).attr("id").substring(7);				
				});
				for(var i = 0;i < 10;i++){
					var isExists = false;
					for(var j = 0;j < arrayObj.length;j++){
						if(i == j){
							isExists = true;
							break;
						}
					}
					if(!isExists){
						return i;
					}
				}
			};			
			
			//�༭������Ʒ -> ���� -> ��ʼ������
			this.initEffact = function(num,isDefault){
				for(var i = 0;i < num;i++){
					editGoodsPublishAction.createEffact(i,isDefault);
				}	
			};
			
			//�༭������Ʒ -> ���� -> ����ID����Editor
			this.createEffact = function(id,isDefault){
				var effactStructure = "";
				//��ȡ����HTML
				effactStructure = editGoodsPublishAction.pieceEffactStructure(id,isDefault);
				//׷������HTML��Table��
				editGoodsPublishAction.appendEffact(effactStructure);
				//��Ⱦ���ı��༭��
				editGoodsPublishAction.renderEditor(id);
				//��ʼ��ɾ����ťЧ��
				$("a.editGoodsPublish_deleteEffactButton").linkbutton({
					iconCls:"icon-cancel",
					plain:true
				});	
			};
			
			//�༭������Ʒ -> ���� -> ׷�����HTML��
			this.appendEffact = function(effactStructure){
				$("td#editGoodsPublishTd").append(effactStructure);
				//$('#editGoodsPublishTd').html(effactStructure);
			};
			
			//�༭������Ʒ -> ���� -> ��Ⱦ���ı��༭��
			this.renderEditor = function(num){
				if(num == 0){
					editGoodsPublish_editor0 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor0.render('editor_0');
				}else if(num == 1){
					editGoodsPublish_editor1 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor1.render('editor_1');
				}else if(num == 2){
					editGoodsPublish_editor2 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor2.render('editor_2');
				}else if(num == 3){
					editGoodsPublish_editor3 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor3.render('editor_3');
				}else if(num == 4){
					editGoodsPublish_editor4 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor4.render('editor_4');
				}else if(num == 5){
					editGoodsPublish_editor5 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor5.render('editor_5');
				}else if(num == 6){
					editGoodsPublish_editor6 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor6.render('editor_6');
				}else if(num == 7){
					editGoodsPublish_editor7 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor7.render('editor_7');
				}else if(num == 8){
					editGoodsPublish_editor8 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor8.render('editor_8');
				}else if(num == 9){
					editGoodsPublish_editor9 = new UE.ui.Editor(editGoodsPublishOption);
	    			editGoodsPublish_editor9.render('editor_9');
				}
				
			};
			
			//�༭������Ʒ -> ���� -> �����ı��༭��
			this.destroyEditor = function(num){
				if(num == 0){
					editGoodsPublish_editor0.destroy();
				}else if(num == 1){
					editGoodsPublish_editor0.destroy();
				}else if(num == 2){
					editGoodsPublish_editor0.destroy();
				}else if(num == 3){
					editGoodsPublish_editor0.destroy();
				}else if(num == 4){
					editGoodsPublish_editor0.destroy();
				}else if(num == 5){
					editGoodsPublish_editor0.destroy();
				}else if(num == 6){
					editGoodsPublish_editor0.destroy();
				}else if(num == 7){
					editGoodsPublish_editor0.destroy();
				}else if(num == 8){
					editGoodsPublish_editor0.destroy();
				}else if(num == 9){
					editGoodsPublish_editor0.destroy();
				}				
			};
			
			//�༭������Ʒ -> ���� -> ��װ����ṹ
			this.pieceEffactStructure = function(num,isDefault){
				var effactStructure = "";
				effactStructure += "<table class=\"effacts\" id=\"effact_"+num+"\">";
				effactStructure += "<tr>";
				effactStructure += "<td>";
				effactStructure += "<span class=\"labelAlignRight\">";
				effactStructure += "�������ƣ�";
				effactStructure += "</span>";
				effactStructure += "<span>";
				if(goodsPublish_buttonsEnable){
					if(isDefault){
						effactStructure += "<input type=\"text\" name=\"effactName_"+num+"\" value=\"��Ʒ˵��\" class=\"disabledEditor\" style=\"background-color:#FFF;\"/>";	
					}else{
						effactStructure += "<input type=\"text\" name=\"effactName_"+num+"\" class=\"disabledEditor\" style=\"background-color:#FFF;\"/>";
					}					
				}else{
					effactStructure += "<input type=\"text\" name=\"effactName_"+num+"\" class=\"disabledEditor\" readonly=\"readonly\"/>";
				}				
				effactStructure += "</span>";
				effactStructure += "<span class=\"labelAlignRight\">";
				effactStructure += "˳��ţ�";
				effactStructure += "</span>";
				effactStructure += "<span>";
				if(goodsPublish_buttonsEnable){
					if(isDefault){
						effactStructure += "<input type=\"text\" name=\"effactOrdinal_"+num+"\" value=\"0\" class=\"disabledEditor\" style=\"background-color:#FFF;width:200px;margin-right:100px;\" />";					
					}else{
						effactStructure += "<input type=\"text\" name=\"effactOrdinal_"+num+"\" class=\"disabledEditor\" style=\"background-color:#FFF;width:200px;margin-right:100px;\" />";
					}
				}else{
					effactStructure += "<input type=\"text\" name=\"effactOrdinal_"+num+"\" class=\"disabledEditor\" style=\"width:200px;margin-right:100px;\"  readonly=\"readonly\"/>";
				}
				effactStructure += "</span>";
				effactStructure += "<span>";
				if(goodsPublish_buttonsEnable){
					effactStructure += "<a href=\"#\" class=\"editGoodsPublish_deleteEffactButton\" onclick=\"editGoodsPublishAction.deleteEffact(" + num + ")\">";
					effactStructure += "ɾ������";
					effactStructure += "</a>";
				}
				effactStructure += "</span>";				
				effactStructure += "</td>";
				effactStructure += "</tr>";			
				effactStructure += "<tr>";
				effactStructure += "<td>";				
				effactStructure += "<div style=\"z-index: 2\">";
				effactStructure += "<script type=\"text/plain\" charset=\"gbk\" id=\"editor_"+num+"\" name=\"effactContent_"+num+"\">";
				effactStructure += "<\/script>";
				effactStructure += "</div>";
				effactStructure += "</td>";
				effactStructure += "</tr>";
				effactStructure += "</table>";
				return effactStructure;
			};
		}
	});
	
	
</script>
	</body>
</html>