<%@ page language="java"  pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
#my7 .mc {
	border: solid #E6E6E6;
	border-width: 0 1px 1px;
	overflow: hidden;
}

#my7 dl dt {
	position: relative;
	margin-bottom: -1px;
	height: 27px;
	border: solid #E6E6E6;
	border-width: 1px 0;
	background: url('<%=basePath%>/images/page/bg_jdleft.jpg') #E6E6E6 repeat-x 0 -30px;
	font-weight: bold;
	line-height: 27px;
	text-align: left;
	padding-left: 5px;
}

#my7 dd {
	padding: 4px 0 5px;
}

#my7 dd .item {
	height: 30px;
	line-height: 30px;
	text-align: left;
	padding-left: 5px;
	border-top: 1px dotted #CACACA;
	margin-left: 5px;
	margin-right: 5px;
	
}

#my7 dd .item a{
	padding-left: 2px;
	font-family: "ËÎÌå";
}

#my7 dd .item a:link,a:visited {
	color: #333;
	text-decoration: none;
}

#my7 dd .item a:hover {
	color: #F00;
	text-decoration: underline;
}

#my7 dd .item a:active {
	color: #900;
}
#my7 dd .pre {
	border-top: none;
}
</style>
</head>
<body style="background-color: #FFFFFF">
	<div id="my7" class="m">
		<div class="mc">
			<dl>
				<dt>
					¹ºÎïÖ¸ÄÏ
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('00')">¹ºÎïÁ÷³Ì</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('01')">ÏÂµ¥Ê±¼ä</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('02')">¶©µ¥ÐÞ¸ÄÓëÈ¡Ïû</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('03')">ÓÃ»§ÐëÖª</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('04')">ÓÃ»§Ð­Òé</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					»áÔ±ÖÐÐÄ
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('10')">»áÔ±È¨Òæ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('11')" >VIP»áÔ±</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('12')" >»áÔ±»ý·Ö</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('13')" >Óà¶î²éÑ¯</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					Ö§¸¶·½Ê½
				</dt>
				<dd>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('21')" >ÍøÉÏÒøÐÐ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('22')" >ÃæÖµ¿¨</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('23')" >ÍøÉÏ³äÖµ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('24')" >´ú½ðÈ¯</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					ÅäËÍËµÃ÷
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('30')">ÅäËÍ·¶Î§</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('31')" >ÅäËÍÊ±¼ä</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('32')" >ÅäËÍ·½Ê½</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('33')" >È¡»õÊ±¼ä</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('34')" >ÑéÊÕÆÀ¼Û</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					ÊÛºó·þÎñ
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('40')">¹ºÎï±£ÕÏ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('41')" >ÍË»»»õÔ­Ôò</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('42')" >ÍË»»»õÁ÷³Ì</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					7ºÅÉú»î¹Ý
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('50')">¹«Ë¾½éÉÜ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('51')" >ÆóÒµ¼ÛÖµ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('52')" >ÆóÒµÀíÄî</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('53')" >ÔËÐÐÄ£Ê½</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('54')" >»ùµØ½éÉÜ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('55')" >×ÊÖÊºÍÈÙÓþ</a>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>