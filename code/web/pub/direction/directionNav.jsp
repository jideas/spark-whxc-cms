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
	font-family: "����";
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
					����ָ��
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('00')">��������</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('01')">�µ�ʱ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('02')">�����޸���ȡ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('03')">�û���֪</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('04')">�û�Э��</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					��Ա����
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('10')">��ԱȨ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('11')" >VIP��Ա</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('12')" >��Ա����</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('13')" >����ѯ</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					֧����ʽ
				</dt>
				<dd>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('21')" >��������</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('22')" >��ֵ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('23')" >���ϳ�ֵ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('24')" >����ȯ</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					����˵��
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('30')">���ͷ�Χ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('31')" >����ʱ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('32')" >���ͷ�ʽ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('33')" >ȡ��ʱ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('34')" >��������</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					�ۺ����
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('40')">���ﱣ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('41')" >�˻���ԭ��</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('42')" >�˻�������</a>
					</div>
				</dd>
			</dl>
			<dl>
				<dt>
					7�������
				</dt>
				<dd>
					<div class="item pre">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('50')">��˾����</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('51')" >��ҵ��ֵ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('52')" >��ҵ����</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('53')" >����ģʽ</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('54')" >���ؽ���</a>
					</div>
					<div class="item">
						&nbsp;&gt;&nbsp;<a href="javascript:loadDirction('55')" >���ʺ�����</a>
					</div>
				</dd>
			</dl>
		</div>
	</div>
</body>
</html>