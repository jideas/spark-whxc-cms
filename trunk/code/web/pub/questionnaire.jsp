<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>问卷调查</title>
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
/*顶部*/
#shortcut {
	padding-bottom: 1px;
	height:30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top left;
}
/*问卷调查*/
#questionnaire{
	width: 980px;
	margin: 20px auto 0px;
	background-color: #FFFFFF;
	
}
/*柒号生活馆*/
#questionnaire .header{
	height: 35px;
	line-height: 35px;
	font-size:18px;
	font-family:'微软雅黑';
	font-weight:bold;
	color:RGB(0,68,153);
	background-color: RGB(222,229,242);
	
	text-align:left;
	padding-left:15px;
}
/*问卷内容区*/
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
/*多行文本*/
#questionnaire .container div.bottomLine textarea{
	border: 1px solid #CCCCCC;
	line-height: 25px;
	font-size:14px;
}
/*版权*/
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
//初始化
$(function(){
	//为选项添加边框效果(questionDivFloat)
	$("#questionnaire .bottomLine").bind("mouseover",function(){
		$(this).css("border","1px solid RGB(20,149,10)");
	}).bind("mouseout",function(){
		$(this).css("border","1px solid #FFF").css("border-bottom","1px solid #EEEEEE");
	});
	
	//提交按钮效果
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
					alert("问卷提交成功！");
					window.location.href = mainWeb;
				}else{
					alert("问卷提交失败，请稍候再试！");
				}
			},
			error: function(data) {
				alert("问卷提交失败，请稍候再试！");
			}
			
		});
	});
	
	//验证表单
	function validateQuestion(){
		var isValidate = true;
		//问题1
		if($('input:radio[name="q1"]:checked').val() == undefined){
			$("#questionnaire .validate1").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//问题2
		if($('input:radio[name="q2"]:checked').val() == undefined){
			$("#questionnaire .validate2").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//问题3
		if($('input:radio[name="q3"]:checked').val() == undefined){
			$("#questionnaire .validate3").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//问题4
		/*
		if($('input:checkbox[name="q4"]:checked').length > 3){
			$("#questionnaire .validate4").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		*/
		//问题5
		if($('input:radio[name="q5"]:checked').val() == undefined){
			$("#questionnaire .validate5").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//问题6
		/*
		if($('input:checkbox[name="q6"]:checked').length > 3){
			$("#questionnaire .validate6").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		*/
		//问题7
		if($('input:radio[name="q7"]:checked').val() == undefined){
			$("#questionnaire .validate7").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		//问题8
		if($('input:checkbox[name="q8"]:checked').length < 0){
			$("#questionnaire .validate8").css("border","1px solid RGB(255,0,0)");
			isValidate =  false;
		}
		if(!isValidate){
			alert("问卷填写不完整！");
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
			<div class="header">7号生活馆</div>
			<div class="container">
				<div class="title">用户满意度与问题反馈</div>
				<div class="bottomLineTitle">
					<p>尊敬的顾客：</p>
					<p style="text-indent: 2em;">您好，感谢您一如既往对7号生活馆的支持。为不断提高我们的服务水平，我们诚挚地邀请您进行满意度评价与问题反馈。请您抽出宝贵的1分钟时间完成问卷。您客观、坦率的意见将激励我们不断努力，为您提供更多物美价廉的商品和优质的服务。（注：带有<b>*</b>题目必选）</p>
				</div>
				<div class="bottomLine validate1">
					<p class="tip">01 ：您在7号生活馆的购物经历是以下哪种？<b>*</b></p>
					<p class="choose"><input type="radio" name="q1" value="1" id="q1_1"/><label for="q1_1">还没有在7号生活馆注册过</label></p>
					<p class="choose"><input type="radio" name="q1" value="2" id="q1_2"/><label for="q1_2">在7号生活馆注册过，但尚未购物</label></p>
					<p class="choose"><input type="radio" name="q1" value="3" id="q1_3"/><label for="q1_3">仅在7号生活馆购物1次</label></p>
					<p class="choose"><input type="radio" name="q1" value="4" id="q1_4"/><label for="q1_4">在7号生活馆购物1次以上</label></p>
				</div>
				<div class="bottomLine validate2">
					<p class="tip">02 ：请您评价您对7号生活馆的整体满意度。<b>*</b></p>
					<p class="choose"><input type="radio" name="q2" value="1" id="q2_1"/><label for="q2_1">非常满意</label></p>
					<p class="choose"><input type="radio" name="q2" value="2" id="q2_2"/><label for="q2_2">满意</label></p>
					<p class="choose"><input type="radio" name="q2" value="3" id="q2_3"/><label for="q2_3">一般</label></p>
					<p class="choose"><input type="radio" name="q2" value="4" id="q2_4"/><label for="q2_4">不满意</label></p>
					<p class="choose"><input type="radio" name="q2" value="5" id="q2_5"/><label for="q2_5">非常不满意</label></p>
				</div>
				<div class="bottomLine validate3">
					<p class="tip">03 ：您在7号生活馆是否能找到您要的商品，请对种类丰富度进行评价。<b>*</b></p>
					<p class="choose"><input type="radio" name="q3" value="1" id="q3_1"/><label for="q3_1">非常满意</label></p>
					<p class="choose"><input type="radio" name="q3" value="2" id="q3_2"/><label for="q3_2">满意</label></p>
					<p class="choose"><input type="radio" name="q3" value="3" id="q3_3"/><label for="q3_3">一般</label></p>
					<p class="choose"><input type="radio" name="q3" value="4" id="q3_4"/><label for="q3_4">不满意</label></p>
					<p class="choose"><input type="radio" name="q3" value="5" id="q3_5"/><label for="q3_5">非常不满意</label></p>
				</div>
				<div class="bottomLine validate4">
					<p class="tip">04 ：您希望7号生活馆丰富以下哪些种类的商品？请选择0-3种。<span></span></p>
					<p class="choose"><input type="checkbox" name="q4" value="401" id="q4_1"/><label for="q4_1">生鲜食品</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="402" id="q4_2"/><label for="q4_2">便捷菜系列</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="403" id="q4_3"/><label for="q4_3">进口食品</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="404" id="q4_4"/><label for="q4_4">营养健康</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="405" id="q4_5"/><label for="q4_5">粮油干货</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="406" id="q4_6"/><label for="q4_6">调味料</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="407" id="q4_7"/><label for="q4_7">酒水饮品</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="408" id="q4_8"/><label for="q4_8">休闲食品</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="409" id="q4_9"/><label for="q4_9">地方特产</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="410" id="q4_10"/><label for="q4_10">厨具频道</label></p>
					<p class="choose"><input type="checkbox" name="q4" value="411" id="q4_11"/><label for="q4_11">家居频道</label></p>
				</div>
				<div class="bottomLine validate5">
					<p class="tip">05 ：您觉得7号生活馆商品的价格是否实惠，请对商品价格进行评价。<b>*</b></p>
					<p class="choose"><input type="radio" name="q5" value="1" id="q5_1"/><label for="q5_1">非常满意</label></p>
					<p class="choose"><input type="radio" name="q5" value="2" id="q5_2"/><label for="q5_2">满意</label></p>
					<p class="choose"><input type="radio" name="q5" value="3" id="q5_3"/><label for="q5_3">一般</label></p>
					<p class="choose"><input type="radio" name="q5" value="4" id="q5_4"/><label for="q5_4">不满意</label></p>
					<p class="choose"><input type="radio" name="q5" value="5" id="q5_5"/><label for="q5_5">非常不满意</label></p>
				</div>
				<div class="bottomLine validate6">
					<p class="tip">06 ：您觉得7号生活馆哪些种类的商品价格不够实惠？请选择0-3种。<span></span></p>
					<p class="choose"><input type="checkbox" name="q6" value="601" id="q6_1"/><label for="q6_1">生鲜食品</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="602" id="q6_2"/><label for="q6_2">便捷菜系列</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="603" id="q6_3"/><label for="q6_3">进口食品</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="604" id="q6_4"/><label for="q6_4">营养健康</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="605" id="q6_5"/><label for="q6_5">粮油干货</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="606" id="q6_6"/><label for="q6_6">调味料</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="607" id="q6_7"/><label for="q6_7">酒水饮品</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="608" id="q6_8"/><label for="q6_8">休闲食品</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="609" id="q6_9"/><label for="q6_9">地方特产</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="610" id="q6_10"/><label for="q6_10">厨具频道</label></p>
					<p class="choose"><input type="checkbox" name="q6" value="611" id="q6_11"/><label for="q6_11">家居频道</label></p>
				</div>
				<div class="bottomLine validate7">
					<p class="tip">07 ：您在7号生活馆网站的浏览过程感觉如何？请评价网站浏览满意度。<b>*</b></p>
					<p class="choose"><input type="radio" name="q7" value="1" id="q7_1"/><label for="q7_1">非常满意</label></p>
					<p class="choose"><input type="radio" name="q7" value="2" id="q7_2"/><label for="q7_2">满意</label></p>
					<p class="choose"><input type="radio" name="q7" value="3" id="q7_3"/><label for="q7_3">一般</label></p>
					<p class="choose"><input type="radio" name="q7" value="4" id="q7_4"/><label for="q7_4">不满意</label></p>
					<p class="choose"><input type="radio" name="q7" value="5" id="q7_5"/><label for="q7_5">非常不满意</label></p>
				</div>
				<div class="bottomLine validate8">
					<p class="tip">08 ：您觉得7号生活馆哪些方面可以做得更好？请选择您认为最重要的3-5项。<b>*</b></p>
					<p class="choose"><input type="checkbox" name="q8" value="801" id="q8_1"/><label for="q8_1">提供种类更为丰富的商品</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="802" id="q8_2"/><label for="q8_2">减少网站访问出错</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="803" id="q8_3"/><label for="q8_3">提供价格更为实惠的商品</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="804" id="q8_4"/><label for="q8_4">提供更为方便的支付、结算流程</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="805" id="q8_5"/><label for="q8_5">优化网站商品的搜索和分类功能</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="806" id="q8_6"/><label for="q8_6">提高网站结构和网页界面的设计水平</label></p>
					<p class="choose"><input type="checkbox" name="q8" value="807" id="q8_7"/><label for="q8_7">提高网站的访问速度</label></p>
				</div>
				<div class="bottomLine validate9">
					<p class="tip">09 ：您对7号生活馆还有何其他意见或建议？</p>
					<p style="height:150px;*height:100px;"><textarea name="q9" rows="6" cols="120"></textarea></p>
				</div>
				<div style="text-align: center;">
					<img class="submitImg"  src="<%=basePath%>/images/page/submitQuest01.jpg" width="98px" height="33px" style="cursor: hand;"/>
				</div>
			</div>
			<div id="copyRight">
				<div>
					<span>电话：4001-027-577</span>
					<span>传真：027-61818108</span>
					<span>厂址：武汉市黄陂区武湖农场工业园</span>
					<span>邮编：430345</span>
				</div>
				<div>
					<span>版权所有：武汉新辰食品有限公司</span>
					<span>邮箱：whyc777@126.com</span>
					<span>鄂ICP备12002886号</span>
					<!-- span><script src="http://s24.cnzz.com/stat.php?id=3931318&web_id=3931318&show=pic" language="JavaScript"></script></span> -->
				</div>
			</div>
		</div>
		</form>
	</body>
</html>