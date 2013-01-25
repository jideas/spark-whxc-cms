<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>留言板</title>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<script type="text/javascript" src="<%=basePath%>/scripts/jquery/jquery.js"></script>
<style>
/*整体样式*/
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
/*版权*/
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
/*容器*/
#container{
	width: 980px; 
	*height: 500px;
	min-height:500px; 
	text-align:left;
	background-color: #FFFFFF;
	margin: 0px auto;
	border: 1px solid #CCCCCC;
}
/*标题[留言板]*/
.title{
	height: 35px;
	line-height: 35px;
	width:980px;
	font-size:18px;
	font-family:'微软雅黑';
	font-weight:bold;
	color:RGB(0,68,153);
	text-align:center;
	margin:15px auto 0px;
	background-color: RGB(222,229,242);
}
/*编辑区域*/
#container #editor{
	height: 180px;
	background-color: RGB(248,248,248);
	border: 1px solid #CCCCCC;
	margin: 10px 15px;
}
/*编辑区域 ->　快捷区域*/
#container #editor #shortRegion{
	height: 40px;
	line-height: 40px;
}
/*编辑区域 -> 快捷区域 -> b*/
#container #editor #shortRegion b{
	font-size:18px;
	color:#333333;
	margin-left: 10px;
	margin-right: 10px;
}
/*编辑区域 -> 快捷区域 -> a*/
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
/*编辑区域 -> 快捷区域 -> span*/
#container #editor #shortRegion span{
	color: #666666;
	margin-right: 3px;
}
/*编辑区域 -> 留言内容*/
#container #editor #msgcontent{
	height: 105px;
}
#container #editor #msgcontent textarea{
	margin-top:5px;
	margin-left: 10px;
	border: 1px solid #CCCCCC;
}
/*编辑区域 -> 留言提交*/
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
/*留言统计 -> p*/
#container p.msgcount{
	margin: 0px 15px;
	height: 25px;
	line-height: 25px;
	font-size: 13px;
	color:#333333;
	border-bottom: 1px dotted #AAAAAA;
}
/*留言统计 -> p -> b*/
#container p.msgcount b{
	font-size: 18px;
	margin-right: 10px;
}
/*留言统计 -> p -> span*/
#container p.msgcount span{
	color: #F00;
}
/*留言列表容器*/
.msgitemcontainer{
	padding-bottom: 15px;
	border-bottom: 1px dashed #CCCCCC;
}
#msgitemcontainer{
	padding-bottom: 0px;
	border-bottom: 0px dashed #CCCCCC;
}
/*留言列表*/
#msglist{
	margin: 15px;
	margin-top:5px;
	padding-bottom:10px;
}
/*留言列表 -> 留言子项 -> 用户名/时间*/
#msglist .msgitem{
	margin-top: 5px;
}
#msglist .msgitem p{
	height: 25px;
	line-height: 25px;
}
/*留言列表 -> 留言子项 -> 用户名/时间*/
#msglist .msgitem p b{
	font-size: 12px;
	color: 999999;
	margin-right: 20px;
}
/*留言列表 -> 留言子项 -> 用户名/时间*/
#msglist .msgitem p span{
	color: #CCCCCC;
}
/*留言列表 -> 留言子项 -> 留言内容*/
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
		//初始化留言数据
		this.initDate = function(){
			$.ajax({
				type: 'post',
				url: mainWeb + "/front/messageBorder/getInitMessage",
				dataType: 'json',
				success: function(data){
					if(data.result){
						$("#msglist").prepend(data.message);
					}else{
						alert("服务器忙碌中，请稍候再试！");
					}
				},
				error: function(data) {
					alert("服务器忙碌中，请稍候再试！");
				}
			});
		}
		//初始化事件
		this.init = function(){
			$("#editor #submitMsg img").bind("click",function(){
				//验证数据
				if(!messageBorder.validateDate()) return;
				//验证是否可提交
				if(!messageBorder.validateIsSubmit(this)) return;
				//提交
				$.ajax({
					type: 'post',
					url: mainWeb + "/front/messageBorder/submitMessage",
					data: {'msgcontent':$.trim($("#editor #msgcontent textarea").val())},
					dataType: 'json',
					success: function(data){
						if(data.result){
							alert("留言提交成功！");
							messageBorder.recoverySubmit();
							messageBorder.clearMsgContent();
							//追加留言
							$("#msglist").prepend(data.message);
						}else{
							alert("留言提交失败，请稍候再试！");
							messageBorder.recoverySubmit();
						}
					},
					error: function(data) {
						alert(data);
						alert("留言提交失败，请稍候再试！");
						messageBorder.recoverySubmit();
					}
				});
			});
		}
		//验证是否提交
		this.validateIsSubmit = function(imgButton){
			if($(imgButton).hasClass("submiting")){
				return false;
			}else{
				$(imgButton).addClass("submiting").animate({'opacity':0.5},1);
				return true;
			}		
		}
		//验证数据
		this.validateDate = function(){
			var msgcontent = $.trim($("#editor #msgcontent textarea").val());
			if(msgcontent == ""){
				alert("留言内容不能为空！");
				return false;
			}
			if(msgcontent.length > 200){
				alert("留言内容过长！");
				return false;
			}
			return true;
		}
		//恢复可提交状态
		this.recoverySubmit = function(){
			$("#editor #submitMsg img").removeClass("submiting").animate({'opacity':1},1);
		}
		//清空留言
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
	<div class="title">留言板</div>
	<!-- begin of 容器 -->
	<div id="container">
		<!-- begin of 编辑器 -->
		<div id="editor">
			<div id="shortRegion">
				<b>快速留言</b>
				<a href="<%=basePath%>/pub/sub/login.jsp">登陆</a>
				<a href="<%=basePath%>/pub/sub/registration.jsp">免费注册</a>
				<span>(游客可直接留言)</span>
			</div>
			<div id="msgcontent">
				<textarea rows="6" style="width: 927px;*width: 925px;"></textarea>
			</div>
			<div id="submitMsg">
				<img src="<%=basePath%>/images/page/submitMsgBorder.jpg"/>
			</div>
		</div>
		<!-- end of 编辑器 -->
		<p class="msgcount"><b>热门留言</b><span></span></p>
		<div id="msglist">
		</div>
	</div>
	<!-- end of 容器 -->	
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
  </body>
</html>
