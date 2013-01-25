<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>�ʾ����</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
<style>
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
/*�ʾ����*/
#questionnaire{
	width: 980px;
	margin: 20px auto 0px;
	background-color: #FFFFFF;
	
}
/*��������*/
#questionnaire .header{
	height: 35px;
	line-height: 35px;
	font-size:18px;
	font-family:'΢���ź�';
	font-weight:bold;
	color:RGB(0,68,153);
	background-color: RGB(222,229,242);
	
	text-align:left;
	padding-left:15px;
}
/*�ʾ�������*/
#questionnaire .container{
	width:950px;
	margin: 10px 15px;
	border: 1px solid #DDDDDD;
	text-align:left;
}
#questionnaire .container .title{
	height: 35px;
	line-height: 35px;
	color: RGB(246,61,5);
	font-size: 20px;
	font-weight: bold;
	text-align: center;
	margin-top: 15px;
}
#questionnaire .container div{
	margin-bottom: 10px;
}
#questionnaire .container p{
	font-size:14px;
	line-height: 25px;
	height: 25px;
}
#questionnaire .container b{
	color: RGB(255,0,0);
	margin: 0px 3px;
}
#questionnaire .container div.bottomLine{
	border:1px solid #FFFFFF;
	border-top:1px solid #FFFFFF;
	border-bottom: 1px solid #EEEEEE;
	padding: 10px;
	margin:10px;
	padding-bottom: 25px;
}
#questionnaire .container div.bottomLineTitle{
	border:1px solid #FFFFFF;
	border-bottom: 1px solid #EEEEEE;
	padding: 10px;
	padding-bottom: 25px;
}
#questionnaire .container div.bottomLine p.tip{
	color: #333333;
	font-size: 16px;
	font-weight: bold;
	margin-bottom: 5px;
}
#questionnaire .container div.bottomLine p.tip span{
	color: RGB(0,102,255);
	font-seze:12px;
}
#questionnaire .container div.bottomLine p.choose{
	height: 25px;
	line-height: 25px;
	text-indent: 2em;
	font-size:13px;
}
#questionnaire .container div.bottomLine p.choose input{
	margin-right: 5px;
}
/*�����ı�*/
#questionnaire .container div.bottomLine textarea{
	border: 1px solid #CCCCCC;
	line-height: 25px;
	font-size:14px;
}
/*��Ȩ*/
#copyRight{
	width:980px;
	text-align:center;
	font-size:12px;
	color: #000000;
	margin:0px;
	padding:0px;
	height: 60px;
	
}
#copyRight div{
	margin:10px 0px;
}
#copyRight div span{
	margin:0px 3px;
}
</style>
<script type="text/javascript">
//��ʼ��
$(function(){
	//Ϊѡ����ӱ߿�Ч��(questionDivFloat)
	$("#questionnaire .bottomLine").bind("mouseover",function(){
		$(this).css("border","1px solid RGB(20,149,10)");
	}).bind("mouseout",function(){
		$(this).css("border","1px solid #FFF").css("border-bottom","1px solid #EEEEEE");
	});
	
	//�ύ��ťЧ��
	$("#questionnaire img.submitImg").bind("mouseover",function(){
		$(this).attr("src","<%=basePath%>/images/page/submitQuest02.jpg");
	}).bind("mouseout",function(){
		$(this).attr("src","<%=basePath%>/images/page/submitQuest01.jpg");
	}).bind("click",function(){
		if(!validateQuestion())return;
		$.ajax({
			type: 'post',
			url: mainWeb + "/front/question/question",
			data: $('#questionForm').serialize(),
			dataType: 'json',
			success: function(data) {
				if(data.result){
					alert("�ʾ��ύ�ɹ���");
					window.location.href = mainWeb;
				}else{
					alert("�ʾ��ύʧ�ܣ����Ժ����ԣ�");
				}
			},
			error: function(data) {
				alert("�ʾ��ύʧ�ܣ����Ժ����ԣ�");
			}
			
		});
	});
	
	//��֤��
	function validateQuestion(){
		var isValidate = true;
		//����1
		if($('input:radio[name="q1"]:checked').val() == undefined){
			$("#questionnaire .validate1").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//����2
		if($('input:radio[name="q2"]:checked').val() == undefined){
			$("#questionnaire .validate2").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//����3
		if($('input:radio[name="q3"]:checked').val() == undefined){
			$("#questionnaire .validate3").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//����4
		/*
		if($('input:checkbox[name="q4"]:checked').length > 3){
			$("#questionnaire .validate4").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		*/
		//����5
		if($('input:radio[name="q5"]:checked').val() == undefined){
			$("#questionnaire .validate5").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//����6
		/*
		if($('input:checkbox[name="q6"]:checked').length > 3){
			$("#questionnaire .validate6").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		*/
		//����7
		if($('input:radio[name="q7"]:checked').val() == undefined){
			$("#questionnaire .validate7").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//����8
		if($('input:checkbox[name="q8"]:checked').length < 0){
			$("#questionnaire .validate8").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		if(!isValidate){
			alert("�ʾ���д��������");
		}
		return isValidate;
	}
});
</script>
	</head>
	<body>
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<form name="questionForm" id="questionForm" action="<%=basePath%>/front/question/question" accept-charset="utf-8"  onsubmit="document.charset='utf-8'">
		<div id="questionnaire">
			<div class="header">7�������</div>
			<div class="container">
				<div class="title">�û�����������ⷴ��</div>
				<div class="bottomLineTitle">
					<p>�𾴵Ĺ˿ͣ�</p>
					<p style="text-indent: 2em;">���ã���л��һ�������7������ݵ�֧�֡�Ϊ����������ǵķ���ˮƽ�����ǳ�ֿ��������������������������ⷴ����������������1����ʱ������ʾ����͹ۡ�̹�ʵ�������������ǲ���Ŭ����Ϊ���ṩ����������������Ʒ�����ʵķ��񡣣�ע������<b>*</b>��Ŀ��ѡ��</p>
				</div>
				<div class="bottomLine validate1">
					<p class="tip">01 ������7������ݵĹ��ﾭ�����������֣�<b>*</b></p>
					<p class="choose"><input type="radio" name="q1" value="1" id="q1_1"/><label for="q1_1">��û����7�������ע���</label></p>
					<p class="choose"><input type="radio" name="q1" value="2" id="q1_2"/><label for="q1_2">��7�������ע���������δ����</label></p>
					<p class="choose"><input type="radio" name="q1" value="3" id="q1_3"/><label for="q1_3">����7������ݹ���1��</label></p>
					<p class="choose"><input type="radio" name="q1" value="4" id="q1_4"/><label for="q1_4">��7������ݹ���1������</label></p>
				</div>
				<div class="bottomLine validate2">
					<p class="tip">02 ��������������7������ݵ���������ȡ�<b>*</b></p>
					<p class="choose"><input type="radio" name="q2" value="1" id="q2_1"/><label for="q2_1">�ǳ�����</label></p>
					<p class="choose"><input type="radio" name="q2" value="2" id="q2_2"/><label for="q2_2">����</label></p>
					<p class="choose"><input type="radio" name="q2" value="3" id="q2_3"/><label for="q2_3">һ��</label></p>
					<p class="choose"><input type="radio" name="q2" value="4" id="q2_4"/><label for="q2_4">������</label></p>
					<p class="choose"><input type="radio" name="q2" value="5" id="q2_5"/><label for="q2_5">�ǳ�������</label></p>
				</div>
				<div class="bottomLine validate3">
					<p class="tip">03 ������7��������Ƿ����ҵ���Ҫ����Ʒ���������ḻ�Ƚ������ۡ�<b>*</b></p>
					<p class="choose"><input type="radio" name="q3" value="1" id="q3_1"/><label for="q3_1">�ǳ�����</label></p>
					<p class="choose"><input type="radio" name="q3" value="2" id="q3_2"/><label for="q3_2">����</label></p>
					<p class="choose"><input type="radio" name="q3" value="3" id="q3_3"/><label for="q3_3">һ��</label></p>
					<p class="choose"><input type="radio" name="q3" value="4" id="q3_4"/><label for="q3_4">������</label></p>
					<p class="choose"><input type="radio" name="q3" value="5" id="q3_5"/><label for="q3_5">�ǳ�������</label></p>
				</div>
				<div class="bottomLine validate4">
					<p class="tip">04 ����ϣ��7������ݷḻ������Щ�������Ʒ����ѡ��0-3�֡�<span></span></p>
					<p class="choose"><input type="checkbox" name="q4" value="401" id="q4_1"/><label for="q4_1">����ʳƷ</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="402" id="q4_2"/><label for="q4_2">��ݲ�ϵ��</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="403" id="q4_3"/><label for="q4_3">����ʳƷ</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="404" id="q4_4"/><label for="q4_4">Ӫ������</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="405" id="q4_5"/><label for="q4_5">���͸ɻ�</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="406" id="q4_6"/><label for="q4_6">��ζ��</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="407" id="q4_7"/><label for="q4_7">��ˮ��Ʒ</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="408" id="q4_8"/><label for="q4_8">����ʳƷ</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="409" id="q4_9"/><label for="q4_9">�ط��ز�</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="410" id="q4_10"/><label for="q4_10">����Ƶ��</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="411" id="q4_11"/><label for="q4_11">�Ҿ�Ƶ��</label></p>
				</div>
				<div class="bottomLine validate5">
					<p class="tip">05 ��������7���������Ʒ�ļ۸��Ƿ�ʵ�ݣ������Ʒ�۸�������ۡ�<b>*</b></p>
					<p class="choose"><input type="radio" name="q5" value="1" id="q5_1"/><label for="q5_1">�ǳ�����</label></p>
					<p class="choose"><input type="radio" name="q5" value="2" id="q5_2"/><label for="q5_2">����</label></p>
					<p class="choose"><input type="radio" name="q5" value="3" id="q5_3"/><label for="q5_3">һ��</label></p>
					<p class="choose"><input type="radio" name="q5" value="4" id="q5_4"/><label for="q5_4">������</label></p>
					<p class="choose"><input type="radio" name="q5" value="5" id="q5_5"/><label for="q5_5">�ǳ�������</label></p>
				</div>
				<div class="bottomLine validate6">
					<p class="tip">06 ��������7���������Щ�������Ʒ�۸񲻹�ʵ�ݣ���ѡ��0-3�֡�<span></span></p>
					<p class="choose"><input type="checkbox" name="q6" value="601" id="q6_1"/><label for="q6_1">����ʳƷ</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="602" id="q6_2"/><label for="q6_2">��ݲ�ϵ��</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="603" id="q6_3"/><label for="q6_3">����ʳƷ</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="604" id="q6_4"/><label for="q6_4">Ӫ������</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="605" id="q6_5"/><label for="q6_5">���͸ɻ�</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="606" id="q6_6"/><label for="q6_6">��ζ��</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="607" id="q6_7"/><label for="q6_7">��ˮ��Ʒ</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="608" id="q6_8"/><label for="q6_8">����ʳƷ</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="609" id="q6_9"/><label for="q6_9">�ط��ز�</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="610" id="q6_10"/><label for="q6_10">����Ƶ��</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="611" id="q6_11"/><label for="q6_11">�Ҿ�Ƶ��</label></p>
				</div>
				<div class="bottomLine validate7">
					<p class="tip">07 ������7���������վ��������̸о���Σ���������վ�������ȡ�<b>*</b></p>
					<p class="choose"><input type="radio" name="q7" value="1" id="q7_1"/><label for="q7_1">�ǳ�����</label></p>
					<p class="choose"><input type="radio" name="q7" value="2" id="q7_2"/><label for="q7_2">����</label></p>
					<p class="choose"><input type="radio" name="q7" value="3" id="q7_3"/><label for="q7_3">һ��</label></p>
					<p class="choose"><input type="radio" name="q7" value="4" id="q7_4"/><label for="q7_4">������</label></p>
					<p class="choose"><input type="radio" name="q7" value="5" id="q7_5"/><label for="q7_5">�ǳ�������</label></p>
				</div>
				<div class="bottomLine validate8">
					<p class="tip">08 ��������7���������Щ����������ø��ã���ѡ������Ϊ����Ҫ��3-5�<b>*</b></p>
					<p class="choose"><input type="checkbox" name="q8" value="801" id="q8_1"/><label for="q8_1">�ṩ�����Ϊ�ḻ����Ʒ</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="802" id="q8_2"/><label for="q8_2">������վ���ʳ���</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="803" id="q8_3"/><label for="q8_3">�ṩ�۸��Ϊʵ�ݵ���Ʒ</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="804" id="q8_4"/><label for="q8_4">�ṩ��Ϊ�����֧������������</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="805" id="q8_5"/><label for="q8_5">�Ż���վ��Ʒ�������ͷ��๦��</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="806" id="q8_6"/><label for="q8_6">�����վ�ṹ����ҳ��������ˮƽ</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="807" id="q8_7"/><label for="q8_7">�����վ�ķ����ٶ�</label></p>
				</div>
				<div class="bottomLine validate9">
					<p class="tip">09 ������7������ݻ��к�����������飿</p>
					<p style="height:150px;*height:100px;"><textarea name="q9" rows="6" cols="120"></textarea></p>
				</div>
				<div style="text-align: center;">
					<img class="submitImg"  src="<%=basePath%>/images/page/submitQuest01.jpg" width="98px" height="33px" style="cursor: hand;"/>
				</div>
			</div>
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
		</div>
		</form>
	</body>
</html>