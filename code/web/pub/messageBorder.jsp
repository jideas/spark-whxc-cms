<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>���԰�</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
<style>
/*������ʽ*/
* {
	padding: 0px;
	margin: 0px;
}

body {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: center;
	margin: 0 auto;
	background-color: RGB(238,238,238); 
}
/*����*/
#shortcut {
	padding-bottom: 1px;
	height:30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top left;
}
/*��Ȩ*/
#copyRight{
	height:60px;
	text-align:center;
	font-size:12px;
	color: #000000;
	margin:0px;
	padding:0px;
}
#copyRight div{
	margin:10px 0px;
}
#copyRight div span{
	margin:0px 3px;
}
/*����*/
#container{
	width: 980px; 
	*height: 500px;
	min-height:500px; 
	text-align:left;
	background-color: #FFFFFF;
	margin: 0px auto;
	border: 1px solid #CCCCCC;
}
/*����[���԰�]*/
.title{
	height: 35px;
	line-height: 35px;
	width:980px;
	font-size:18px;
	font-family:'΢���ź�';
	font-weight:bold;
	color:RGB(0,68,153);
	text-align:center;
	margin:15px auto 0px;
	background-color: RGB(222,229,242);
}
/*�༭����*/
#container #editor{
	height: 180px;
	background-color: RGB(248,248,248);
	border: 1px solid #CCCCCC;
	margin: 10px 15px;
}
/*�༭���� ->���������*/
#container #editor #shortRegion{
	height: 40px;
	line-height: 40px;
}
/*�༭���� -> ������� -> b*/
#container #editor #shortRegion b{
	font-size:18px;
	color:#333333;
	margin-left: 10px;
	margin-right: 10px;
}
/*�༭���� -> ������� -> a*/
#container #editor #shortRegion a,
#container #editor #shortRegion a:link{
	color: #0000CC;
	text-decoration:none;
	margin-right: 3px;
}
#container #editor #shortRegion a:hover{
	color: #FF0000;
	text-decoration:underline;
}
/*�༭���� -> ������� -> span*/
#container #editor #shortRegion span{
	color: #666666;
	margin-right: 3px;
}
/*�༭���� -> ��������*/
#container #editor #msgcontent{
	height: 105px;
}
#container #editor #msgcontent textarea{
	margin-top:5px;
	margin-left: 10px;
	border: 1px solid #CCCCCC;
}
/*�༭���� -> �����ύ*/
#container #editor #submitMsg{
	height: 35px;
	line-height: 35px;
	text-align: right;
	margin-right: 8px;
}
#container #editor #submitMsg img{
	height: 32px;
	width: 98px;
	cursor: hand;
}
/*����ͳ�� -> p*/
#container p.msgcount{
	margin: 0px 15px;
	height: 25px;
	line-height: 25px;
	font-size: 13px;
	color:#333333;
	border-bottom: 1px dotted #AAAAAA;
}
/*����ͳ�� -> p -> b*/
#container p.msgcount b{
	font-size: 18px;
	margin-right: 10px;
}
/*����ͳ�� -> p -> span*/
#container p.msgcount span{
	color: #F00;
}
/*�����б�����*/
.msgitemcontainer{
	padding-bottom: 15px;
	border-bottom: 1px dashed #CCCCCC;
}
#msgitemcontainer{
	padding-bottom: 0px;
	border-bottom: 0px dashed #CCCCCC;
}
/*�����б�*/
#msglist{
	margin: 15px;
	margin-top:5px;
	padding-bottom:10px;
}
/*�����б� -> �������� -> �û���/ʱ��*/
#msglist .msgitem{
	margin-top: 5px;
}
#msglist .msgitem p{
	height: 25px;
	line-height: 25px;
}
/*�����б� -> �������� -> �û���/ʱ��*/
#msglist .msgitem p b{
	font-size: 12px;
	color: 999999;
	margin-right: 20px;
}
/*�����б� -> �������� -> �û���/ʱ��*/
#msglist .msgitem p span{
	color: #CCCCCC;
}
/*�����б� -> �������� -> ��������*/
#msglist .msgitem .msgtext{
	border: 1px solid #EEEEEE;
	background-color: RGB(255,255,238);
	padding: 10px;
	font-size: 13px;
}
</style>
<script type="text/javascript">
var messageBorder;
$(function(){
	$.ajaxSetup({cache:false});
	messageBorder = new MessageBorder();
	messageBorder.init();
	messageBorder.initDate();
	function MessageBorder(){
		//��ʼ����������
		this.initDate = function(){
			$.ajax({
				type: 'post',
				url: mainWeb + "/front/messageBorder/getInitMessage",
				dataType: 'json',
				success: function(data){
					if(data.result){
						$("#msglist").prepend(data.message);
					}else{
						alert("������æµ�У����Ժ����ԣ�");
					}
				},
				error: function(data) {
					alert("������æµ�У����Ժ����ԣ�");
				}
			});
		}
		//��ʼ���¼�
		this.init = function(){
			$("#editor #submitMsg img").bind("click",function(){
				//��֤����
				if(!messageBorder.validateDate()) return;
				//��֤�Ƿ���ύ
				if(!messageBorder.validateIsSubmit(this)) return;
				//�ύ
				$.ajax({
					type: 'post',
					url: mainWeb + "/front/messageBorder/submitMessage",
					data: {'msgcontent':$.trim($("#editor #msgcontent textarea").val())},
					dataType: 'json',
					success: function(data){
						if(data.result){
							alert("�����ύ�ɹ���");
							messageBorder.recoverySubmit();
							messageBorder.clearMsgContent();
							//׷������
							$("#msglist").prepend(data.message);
						}else{
							alert("�����ύʧ�ܣ����Ժ����ԣ�");
							messageBorder.recoverySubmit();
						}
					},
					error: function(data) {
						alert(data);
						alert("�����ύʧ�ܣ����Ժ����ԣ�");
						messageBorder.recoverySubmit();
					}
				});
			});
		}
		//��֤�Ƿ��ύ
		this.validateIsSubmit = function(imgButton){
			if($(imgButton).hasClass("submiting")){
				return false;
			}else{
				$(imgButton).addClass("submiting").animate({'opacity':0.5},1);
				return true;
			}		
		}
		//��֤����
		this.validateDate = function(){
			var msgcontent = $.trim($("#editor #msgcontent textarea").val());
			if(msgcontent == ""){
				alert("�������ݲ���Ϊ�գ�");
				return false;
			}
			if(msgcontent.length > 200){
				alert("�������ݹ�����");
				return false;
			}
			return true;
		}
		//�ָ����ύ״̬
		this.recoverySubmit = function(){
			$("#editor #submitMsg img").removeClass("submiting").animate({'opacity':1},1);
		}
		//�������
		this.clearMsgContent = function(){
			$("#editor #msgcontent textarea").val("");
		}
	}
});
</script>    
  </head>
  <body>
 	<div id="shortcut">
		<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
	</div>
	<div class="title">���԰�</div>
	<!-- begin of ���� -->
	<div id="container">
		<!-- begin of �༭�� -->
		<div id="editor">
			<div id="shortRegion">
				<b>��������</b>
				<a href="<%=basePath%>/pub/sub/login.jsp">��½</a>
				<a href="<%=basePath%>/pub/sub/registration.jsp">���ע��</a>
				<span>(�οͿ�ֱ������)</span>
			</div>
			<div id="msgcontent">
				<textarea rows="6" style="width: 927px;*width: 925px;"></textarea>
			</div>
			<div id="submitMsg">
				<img src="<%=basePath%>/images/page/submitMsgBorder.jpg"/>
			</div>
		</div>
		<!-- end of �༭�� -->
		<p class="msgcount"><b>��������</b><span></span></p>
		<div id="msglist">
		</div>
	</div>
	<!-- end of ���� -->	
	<div id="copyRight">
			<div>
				<span>�绰��4001-027-577</span>
				<span>���棺027-61818108</span>
				<span>��ַ���人�л��������ũ����ҵ԰</span>
				<span>�ʱࣺ430345</span>
			</div>
			<div>
				<span>��Ȩ���У��人�³�ʳƷ���޹�˾</span>
				<span>���䣺whyc777@126.com</span>
				<span>��ICP��12002886��</span>
				<!-- span><script src="http://s24.cnzz.com/stat.php?id=3931318&web_id=3931318&show=pic" language="JavaScript"></script></span> -->
			</div>
	</div>
  </body>
</html>
