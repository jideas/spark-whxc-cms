<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>����7��</title>
		<script type="text/javascript" src="<%=basePath%>/scripts/idCard.js" charset="GBK"></script>
		<link rel="shortcut icon" href="<%=basePath%>/images/page/icon-7.ico" type="image/x-icon" />
		<link id="cssSink" type="text/css" rel="stylesheet"
			href="<%=basePath%>/scripts/dialog/cmsDialog.css" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/dialog/cmsDialog.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/pro/member/css/my7.css">
		<script type="text/javascript">
function submitJoinInfo(){
	//��֤ -> ����
	var chineseReg = /^([u4e00-u9fa5]|[ufe30-uffa0])*$/gi;
	if(chineseReg.test($("#joinSevenForm input[name='joinerName']").val())){
		alert("����ֻ���������ģ�");
		return false;
	}
	//��֤ -> ����
	var ageReg = /^[0-9]+$/;
	if(!ageReg.test($("#joinSevenForm input[name='joinerAge']").val())){
		alert("���䲻�Ϸ���");
		return false;
	}
	//��֤ -> ����
	if(chineseReg.test($("#joinSevenForm input[name='birthplace']").val())){
		alert("����ֻ���������ģ�");
		return false;
	}
	//��֤ -> ���֤
	if(!checkParseIdCard($("#joinSevenForm input[name='idCardNo']").val())){
		return false;
	}
	//��֤ -> Email
	var emailReg = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if(!emailReg.test($("#joinSevenForm input[name='email']").val())){
		alert("email���Ϸ���");
		return false;
	}
	//��֤ -> ��ϵ�绰
	var telephoneReg = /^([0-9]|[\-])+$/g;
	if(!telephoneReg.test($("#joinSevenForm input[name='telephone']").val())){
		alert("��ϵ�绰��ʽ����");
		return false;
	}
	//��֤ -> ����
	var faxReg = /^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$/;
	if(!faxReg.test($("#joinSevenForm input[name='fax']").val())){
		alert("�����ʽ����ȷ��");
		return false;
	}
	//��֤ -> QQ
	var qqReg = /^[1-9]\d{4,10}$/;
	if(!qqReg.test($("#joinSevenForm input[name='qq']").val())){
		alert("QQ��ʽ����ȷ��");
		return false;
	}
	//��֤ -> MSN
	var msnReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if($("#joinSevenForm input[name='msn']").val() != ""){
		if(!msnReg.test($("#joinSevenForm input[name='msn']").val())){
			alert("msn��ʽ����ȷ��");
			return false;
		}
	}
	//��֤ -> �ֻ�����
	var mobileReg = /^([0-9]|[\-])+$/g;
	if(!mobileReg.test($("#joinSevenForm input[name='mobile']").val())){
		alert("�ֻ������ʽ����");
		return false;
	}
	
	//�ύ���˵�
	var form = $('#joinSevenForm');
	$.ajax({
		type: 'post',
		url: form.attr('action'),
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		data:form.serialize(),
		dataType: 'json',
		success: function(result) {
	    	if (result.result) {
				var cc = new CmsConfirm("��ʾ", "�ύ���������ѳɹ����뾲�����������ȷ��������ҳ��");
				cc.setActionListener(function(result) {
					if(result){
						window.location = mainWeb;
					}else{
						
					}
				}); 
			} else {
				 cmsAlertAtt(result.message);
			}
		}
	});
}
function validateJoinInfo(){
	
}
</script>
		<style type="text/css">
#MainBillInfoDiv {
	width: 100%;
	border: #CDCDCD solid 1px;
	margin-top: 40px; *
	margin-top: 0px;
}

#billsTitleDiv {
	width: 100%;
	height: 30px;
	line-height: 30px;
	text-align: center;
	font-size: 22px;
	margin-top: 0px;
}

#MainBillInfo {
	width: 800px;
	height: 1240px;
	*height:1270px;
	margin: 0 auto;
	margin-top: 0px;
}

#MainBillInfo .JoinChannelTitleDivStyle {
	height: 30px;
	line-height: 30px;
	background-color: #F0F4F5;
	text-align: left;
	padding-left: 10px;
	margin-top: 10px;
}

#MainBillInfo .JoinChannelFormDivStyle {
	text-align: left;
	padding-left: 10px;
	width: 100%;
	height: 30px;
	line-height: 30px;
}

.JoinChannelFormDivStyle .fieldLableSpan {
	width: 70px;
	height: 22px;
	line-height: 22px;
	text-align: right;
	font-size: 12px;
	vertical-align: middle;
	display: inline;
	float: left;
	margin-top: 8px;
}

.JoinChannelFormDivStyle .fieldInputSpan {
	text-align: left;
	height: 22px;
	line-height: 22px;
	font-size: 12px;
	display: inline;
	margin-top: 8px;
	float: left;
}

.fieldTextAreaDiv {
	height: 120px;
	line-height: 120px;
	text-align: left;
	padding-left: 10px;
	margin-top: 10px;
}

.button_submit {
	width: 134px;
	line-height: 32px;
	height: 32px;
	margin-left: 665px;
	margin-top: 10px;
	cursor: pointer;
	text-align: center;
	vertical-align: middle;
	background-image: url('<%=basePath%>/images/page/button_reg.png');
	list-style-type: none;
	display: inline;
	float: left;
	cursor: pointer;
	margin-top: 10px;
	cursor: pointer;
}
#shortcut {
	padding-bottom: 1px;
	line-height: 30px;
	width: 100%;
	text-align: center;
	margin: 0 auto;
	background: url("<%=basePath%>/images/page/page_top1.png") repeat-x top
		left;
}
</style>
	</head>
	<body style="background-color: #FFFFFF" onselectstart="return false;">
		<div id="shortcut">
			<jsp:include page="/pub/main/shortcut.jsp" flush="true" />
		</div>
		<div id='chargecontent'>
			<div id="o-header">
				<jsp:include page="/pub/main/header.jsp" flush="true" />
				<jsp:include page="/pub/common/productlistmenu.jsp?menuId=joinus" flush="true" />
			</div>
			<div id="billsTitleDiv">
				<strong>���������</strong>
			</div>
			<div id="MainBillInfoDiv">
				<div id="MainBillInfo">
					<form action="<%=basePath%>/front/JoinSeven" id="joinSevenForm"
						method="post">
						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;��������</strong>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -38px;">
								������
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="joinerName" style="width: 150px;"/>
							</div>
							<div class="fieldLableSpan">
								�Ա�
							</div>
							<div class="fieldInputSpan">
								<select name="joinerSex" style="width: 50px;">
									<option value="��">
										��
									</option>
									<option value="Ů">
										Ů
									</option>
								</select>
							</div>
							<div class="fieldLableSpan">
								���䣺
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="joinerAge" style="width: 50px;"/>
							</div>
							<div class="fieldLableSpan">
								����״����
							</div>

							<div class="fieldInputSpan">
								<select name="maritalstatus" style="width: 60px;">
									<option value="�ѻ�">
										�ѻ�
									</option>
									<option value="δ��">
										δ��
									</option>
								</select>
							</div>
							<div class="fieldLableSpan">
								���᣺
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="birthplace" style="width: 100px;"/>
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								�Ļ��̶ȣ�
							</div>

							<div class="fieldInputSpan">
								<select name="culturalLevel" style="width: 100px;">
									<option value="����">
										��������
									</option>
									<option value="ר��">
										ר��
									</option>
									<option value="����">
										����
									</option>
									<option value="����">
										����
									</option>
									<option value="����">
										����
									</option>
								</select>
							</div>
							<div class="fieldLableSpan">
								���֤��
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="idCardNo" style="width: 200px;"/>
							</div>
							<div class="fieldLableSpan">
								email��
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="email" style="width: 228px;"/>
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								��ϵ�绰��
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="telephone" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								���棺
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="fax" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								QQ��
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="qq" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								MSN��
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="msn" style="width: 158px;"/>
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								�ֻ����룺
							</div>

							<div class="fieldInputSpan">
								<input type="text" name="mobile" style="width: 100px;"/>
							</div>
							<div class="fieldLableSpan">
								��ס��ַ��
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="livingAddress" style="width: 498px;" />
							</div>
						</div>
						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;��������</strong>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								��ϸ��ַ��
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="storeAddress" style="width: 668px;" />
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan"
								style="margin-left: -10px; width: 100px;">
								������ҵ�ֵضΣ�
							</div>
							<div class="fieldInputSpan">
								<input type="radio" name="storeLocation" value="������">
								������
								<input type="radio" name="storeLocation" value="˫����">
								˫����
								<input type="radio" name="storeLocation" value="������">
								������
								<input type="radio" name="storeLocation" value="����">
								����
								<input type="radio" name="storeLocation" value="ר��">
								ר��
								<input type="radio" name="storeLocation" value="���е�">
								���е�
								<input type="radio" name="storeLocation" value="ר����">
								ר����
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								ȫ�����
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="rentOfYear" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan">
								���úϼƣ�
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="sumCost" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan">
								������Ա��
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="salesEmployee" style="width: 176px;" />
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								��Ӫ��ʽ��
							</div>
							<div class="fieldInputSpan">
								<input type="radio" name="businessWay" value="����">
								����
								<input type="radio" name="businessWay" value="�ϻ�">
								�ϻ�
								<input type="radio" name="businessWay" value="��λ">
								��λ
								<input type="radio" name="businessWay" value="����">
								����
							</div>
						</div>
						<div class="JoinChannelFormDivStyle">
							<div class="fieldLableSpan" style="margin-left: -15px;">
								Ͷ���ʽ�
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="fund" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan">
								�����ʽ�
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="funding" style="width: 176px;" />
							</div>
							<div class="fieldLableSpan" style="width: 100px;">
								Ҫ��ҵʱ�䣺
							</div>
							<div class="fieldInputSpan">
								<input type="text" name="askStartDate" style="width: 146px;" />
							</div>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;�����ھ���Ʒ��״��</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="competitionSituation"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;��ʲô�������������</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="joinusReason"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;���˴�ҵ����</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="experience"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;�����Ը񼰼�ͥ״��</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="dispositionAndFamily"></textarea>
						</div>

						<div class="JoinChannelTitleDivStyle">
							<strong>&gt;&gt;�Լ��˵�Ԥ�ڷ�����δ����һ���ڶ�����������ڴ�</strong>
						</div>

						<div class="fieldTextAreaDiv">
							<textarea style="width: 100%; height: 120px; resize: none;"
								name="riskAndHope"></textarea>
						</div>
						<div class="button_submit" onclick="submitJoinInfo();">
							�ύ���˱�
						</div>
				</div>
				</form>
			</div>
			<div id="serviceFloors">
				<jsp:include page="/pub/main/service.jsp" flush="true" />
			</div>
			<div id="copyRight">
				<jsp:include page="/pub/main/copyRight.jsp" flush="true" />
			</div>
		</div>
	</body>
</html>