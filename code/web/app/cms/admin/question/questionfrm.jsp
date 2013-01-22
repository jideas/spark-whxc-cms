<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�ʾ����</title>
    <script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
  </head>
  <body>
<style>
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
	border:2px solid #FFFFFF;
	border-top:3px solid #FFFFFF;
	border-bottom: 1px solid #EEEEEE;
	padding: 10px;
	margin:10px;
	padding-bottom: 25px;
}
#questionnaire .container div.bottomLineTitle{
	border:1px solid #FFFFFF;
	border-bottom: 1px solid #EEEEEE;
	padding: 10px;
	padding-bottom: 0px;
}
#questionnaire .container div.bottomLine p.tip{
	color: #333333;
	font-size: 16px;
	font-weight: bold;
	margin-bottom: 5px;
}
/*�ʾ�ѡ��*/
#questionnaire .container div.bottomLine p.choose{
	height: 25px;
	line-height: 25px;
	text-indent: 2em;
	font-size:13px;
	border-bottom: 1px dotted RGB(222,229,242);
}
#questionnaire .container div.bottomLine p.choose span{
	float: left;
}
#questionnaire .container div.bottomLine p.choose b{
	float:right;
	color: RGB(20,149,10);
	margin-right: 15px;
}
/*��ʼ����/��������*/
#questionnaire .container .bottomLineTitle p span.spanDate{
	float: left;
	font-size:12px;
}
/*�ʾ�����*/
#questionnaire .container .bottomLineTitle p b{
	float:right;
	color: #000000;
	font-size:12px;
}
#questionnaire .container .bottomLineTitle p font{
	color:#FF0000;
	font-size:14px;
}
</style>
<center>
	<div id="questionnaire">
		<div class="container">
			<div class="title">�û�����������ⷴ��</div>
			<div class="bottomLineTitle">
				<p>
				<span class="spanDate">
					��ʼ���ڣ� <input	id="beginDate" type="text" class="easyui-datebox" /> 
					�������ڣ� <input	id="endDate" type="text" class="easyui-datebox" />
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"	onclick="questionAction.getQuestionByDate()">��ѯ</a>
				</span>
				<b>�ܼ�¼��<font id="qCount">0</font></b>
				</p>
			</div>
			<div class="bottomLine">
				<p class="tip">01 ������7������ݵĹ��ﾭ�����������֣�</p>
				<p class="choose"><span>��û����7�������ע���</span><b id="q101">[0]</b></p>
				<p class="choose"><span>��7�������ע���������δ����</span><b id="q102">[0]</b></p>
				<p class="choose"><span>����7������ݹ���1��</span><b id="q103">[0]</b></p>
				<p class="choose"><span>��7������ݹ���1������</span><b id="q104">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">02 ��������������7������ݵ���������ȡ�</p>
				<p class="choose"><span>�ǳ�����</span><b id="q201">[0]</b></p>
				<p class="choose"><span>����</span><b id="q202">[0]</b></p>
				<p class="choose"><span>һ��</span><b id="q203">[0]</b></p>
				<p class="choose"><span>������</span><b id="q204">[0]</b></p>
				<p class="choose"><span>�ǳ�������</span><b id="q205">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">03 ������7��������Ƿ����ҵ���Ҫ����Ʒ���������ḻ�Ƚ������ۡ�</p>
				<p class="choose"><span>�ǳ�����</span><b id="q301">[0]</b></p>
				<p class="choose"><span>����</span><b id="q302">[0]</b></p>
				<p class="choose"><span>һ��</span><b id="q303">[0]</b></p>
				<p class="choose"><span>������</span><b id="q304">[0]</b></p>
				<p class="choose"><span>�ǳ�������</span><b id="q305">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">04 ����ϣ��7������ݷḻ������Щ�������Ʒ����ѡ��0-3�֡�</p>
				<p class="choose"><span>����ʳƷ</span><b id="q401">[0]</b></p>
				<p class="choose"><span>��ݲ�ϵ��</span><b id="q402">[0]</b></p>
				<p class="choose"><span>����ʳƷ</span><b id="q403">[0]</b></p>
				<p class="choose"><span>Ӫ������</span><b id="q404">[0]</b></p>
				<p class="choose"><span>���͸ɻ�</span><b id="q405">[0]</b></p>
				<p class="choose"><span>��ζ��</span><b id="q406">[0]</b></p>
				<p class="choose"><span>��ˮ��Ʒ</span><b id="q407">[0]</b></p>
				<p class="choose"><span>����ʳƷ</span><b id="q408">[0]</b></p>
				<p class="choose"><span>�ط��ز�</span><b id="q409">[0]</b></p>
				<p class="choose"><span>����Ƶ��</span><b id="q410">[0]</b></p>
				<p class="choose"><span>�Ҿ�Ƶ��</span><b id="q411">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">05 ��������7���������Ʒ�ļ۸��Ƿ�ʵ�ݣ������Ʒ�۸�������ۡ�</p>
				<p class="choose"><span>�ǳ�����</span><b id="q501">[0]</b></p>
				<p class="choose"><span>����</span><b id="q502">[0]</b></p>
				<p class="choose"><span>һ��</span><b id="q503">[0]</b></p>
				<p class="choose"><span>������</span><b id="q504">[0]</b></p>
				<p class="choose"><span>�ǳ�������</span><b id="q505">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">06 ��������7���������Щ�������Ʒ�۸񲻹�ʵ�ݣ���ѡ��0-3�֡�</p>
				<p class="choose"><span>����ʳƷ</span><b id="q601">[0]</b></p>
				<p class="choose"><span>��ݲ�ϵ��</span><b id="q602">[0]</b></p>
				<p class="choose"><span>����ʳƷ</span><b id="q603">[0]</b></p>
				<p class="choose"><span>Ӫ������</span><b id="q604">[0]</b></p>
				<p class="choose"><span>���͸ɻ�</span><b id="q605">[0]</b></p>
				<p class="choose"><span>��ζ��</span><b id="q606">[0]</b></p>
				<p class="choose"><span>��ˮ��Ʒ</span><b id="q607">[0]</b></p>
				<p class="choose"><span>����ʳƷ</span><b id="q608">[0]</b></p>
				<p class="choose"><span>�ط��ز�</span><b id="q609">[0]</b></p>
				<p class="choose"><span>����Ƶ��</span><b id="q610">[0]</b></p>
				<p class="choose"><span>�Ҿ�Ƶ��</span><b id="q611">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">07 ������7���������վ��������̸о���Σ���������վ�������ȡ�</p>
				<p class="choose"><span>�ǳ�����</span><b id="q701">[0]</b></p>
				<p class="choose"><span>����</span><b id="q702">[0]</b></p>
				<p class="choose"><span>һ��</span><b id="q703">[0]</b></p>
				<p class="choose"><span>������</span><b id="q704">[0]</b></p>
				<p class="choose"><span>�ǳ�������</span><b id="q705">[0]</b></p>
			</div>
			<div class="bottomLine">
				<p class="tip">08 ��������7���������Щ����������ø��ã���ѡ������Ϊ����Ҫ��3-5�</p>
				<p class="choose"><span>�ṩ�����Ϊ�ḻ����Ʒ</span><b id="q801">[0]</b></p>
				<p class="choose"><span>������վ���ʳ���</span><b id="q802">[0]</b></p>
				<p class="choose"><span>�ṩ�۸��Ϊʵ�ݵ���Ʒ</span><b id="q803">[0]</b></p>
				<p class="choose"><span>�ṩ��Ϊ�����֧������������</span><b id="q804">[0]</b></p>
				<p class="choose"><span>�Ż���վ��Ʒ�������ͷ��๦��</span><b id="q805">[0]</b></p>
				<p class="choose"><span>�����վ�ṹ����ҳ��������ˮƽ</span><b id="q806">[0]</b></p>
				<p class="choose"><span>�����վ�ķ����ٶ�</span><b id="q807">[0]</b></p>
			</div>
		</div>
	</div>
	</center>
	<script type="text/javascript">
//ˢ���ʾ��¼
var questionAction = new QuestionAction();
questionAction.init();
function QuestionAction(){
	//��ʼ��
	this.init = function(){
		$.get('<%=basePath%>/admin/question/countQuestion',{'beginDate':'','endDate':''},function(result){
	       	if(result){
	       		questionAction.refreshQuestionCount(result);
	       	}
	     },'json');	
	}

	//����ѯ������
	this.getQuestionByDate = function(){
		var beginDate = $('#beginDate').datebox('getValue');
		var endDate = $('#endDate').datebox('getValue');
		$.get('<%=basePath%>/admin/question/countQuestion',{'beginDate':beginDate,'endDate':endDate},function(result){
	       	if(result){
	       		questionAction.refreshQuestionCount(result);
	       	}
	     },'json');	
	}
	
	//ˢ���ʾ�ͳ������
	this.refreshQuestionCount = function(result){
		$("#questionnaire b#q101").html("["+result.q101+"]");
   		$("#questionnaire b#q102").html("["+result.q102+"]");
   		$("#questionnaire b#q103").html("["+result.q103+"]");
   		$("#questionnaire b#q104").html("["+result.q104+"]");
   		
   		$("#questionnaire b#q201").html("["+result.q201+"]");
   		$("#questionnaire b#q202").html("["+result.q202+"]");
   		$("#questionnaire b#q203").html("["+result.q203+"]");
   		$("#questionnaire b#q204").html("["+result.q204+"]");
   		$("#questionnaire b#q205").html("["+result.q205+"]");
   		
   		$("#questionnaire b#q301").html("["+result.q301+"]");
   		$("#questionnaire b#q302").html("["+result.q302+"]");
   		$("#questionnaire b#q303").html("["+result.q303+"]");
   		$("#questionnaire b#q304").html("["+result.q304+"]");
   		$("#questionnaire b#q305").html("["+result.q305+"]");
   		
   		$("#questionnaire b#q401").html("["+result.q401+"]");
   		$("#questionnaire b#q402").html("["+result.q402+"]");
   		$("#questionnaire b#q403").html("["+result.q403+"]");
   		$("#questionnaire b#q404").html("["+result.q404+"]");
   		$("#questionnaire b#q405").html("["+result.q405+"]");
   		$("#questionnaire b#q406").html("["+result.q406+"]");
   		$("#questionnaire b#q407").html("["+result.q407+"]");
   		$("#questionnaire b#q408").html("["+result.q408+"]");
   		$("#questionnaire b#q409").html("["+result.q409+"]");
   		$("#questionnaire b#q410").html("["+result.q410+"]");
   		$("#questionnaire b#q411").html("["+result.q411+"]");
   		
   		$("#questionnaire b#q501").html("["+result.q501+"]");
   		$("#questionnaire b#q502").html("["+result.q502+"]");
   		$("#questionnaire b#q503").html("["+result.q503+"]");
   		$("#questionnaire b#q504").html("["+result.q504+"]");
   		$("#questionnaire b#q505").html("["+result.q505+"]");
   		
   		$("#questionnaire b#q601").html("["+result.q601+"]");
   		$("#questionnaire b#q602").html("["+result.q602+"]");
   		$("#questionnaire b#q603").html("["+result.q603+"]");
   		$("#questionnaire b#q604").html("["+result.q604+"]");
   		$("#questionnaire b#q605").html("["+result.q605+"]");
   		$("#questionnaire b#q606").html("["+result.q606+"]");
   		$("#questionnaire b#q607").html("["+result.q607+"]");
   		$("#questionnaire b#q608").html("["+result.q608+"]");
   		$("#questionnaire b#q609").html("["+result.q609+"]");
   		$("#questionnaire b#q610").html("["+result.q610+"]");
   		$("#questionnaire b#q611").html("["+result.q611+"]");
   		
   		$("#questionnaire b#q701").html("["+result.q701+"]");
   		$("#questionnaire b#q702").html("["+result.q702+"]");
   		$("#questionnaire b#q703").html("["+result.q703+"]");
   		$("#questionnaire b#q704").html("["+result.q704+"]");
   		$("#questionnaire b#q705").html("["+result.q705+"]");
   		
   		$("#questionnaire b#q801").html("["+result.q801+"]");
   		$("#questionnaire b#q802").html("["+result.q802+"]");
   		$("#questionnaire b#q803").html("["+result.q803+"]");
   		$("#questionnaire b#q804").html("["+result.q804+"]");
   		$("#questionnaire b#q805").html("["+result.q805+"]");
   		$("#questionnaire b#q806").html("["+result.q806+"]");
   		$("#questionnaire b#q807").html("["+result.q807+"]");
   		$("#questionnaire b#q808").html("["+result.q808+"]");
   		
   		$("#questionnaire #qCount").html(result.q101+result.q102+result.q103+result.q104);
	}
}
//��ʼ��
$(function(){
	//��ʼ�� -> Ϊѡ����ӱ߿�Ч��(questionDivFloat)
	/*
	$("#questionnaire .bottomLine").bind("mouseover",function(){
		$(this).css("border","1px solid RGB(20,149,10)");
	}).bind("mouseout",function(){
		$(this).css("border","1px solid #FFF").css("border-bottom","1px solid #EEEEEE");
	});
	*/
	//��ʼ�� -> Ϊѡ���������Ч��
	$("#questionnaire .bottomLine p.choose").bind("mouseover",function(){
		$(this).css("background-color","RGB(235,235,235)");
	}).bind("mouseout",function(){
		$(this).css("background-color","RGB(255,255,255)");
	});
});

</script>
  </body>
</html>
