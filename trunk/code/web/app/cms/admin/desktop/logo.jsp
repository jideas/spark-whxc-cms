<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
<style type="text/css">
/*���岼��*/
#logo{
	width:100%;
	height:66px;
	background: url("<%=mainWeb%>/images/admin/HT-topbg.png") repeat-x top left; 
	overflow: hidden;
}
/*logoͼƬ*/
#logo .logoImg{
	float:left;
	height:66px;
	width:410px;
	background: url("<%=mainWeb%>/images/admin/HT-title.png") no-repeat top left;
}
/*�޸�����/�˳�*/
#logo .logoLogin{
	float:right;
	height:66px;
	line-height: 66px;
	margin-top: 10px;
	margin-right: 20px;	
}
#logo .logoLogin img{
	cursor: pointer;
}
/*�޸�����Ի���*/
#logo_updatePwd_dialog{
	text-align: center;
}
#logo_updatePwd_dialog table{
	margin-top: 15px;
}
#logo_updatePwd_dialog td.td_label{
	width: 65px;
	text-align: right;
}
</style>
<script type="text/javascript">
	$(function(){
		//��ʼ�� -> ���¼� -> �޸�����
		$("#logo .logoLogin .pwdImg").bind("mouseover",function(){
			$(this).attr("src","<%=mainWeb%>/images/admin/key02.png");
		}).bind("mouseout",function(){
			$(this).attr("src","<%=mainWeb%>/images/admin/key01.png");
		}).bind("click",function(){
			logoAction.updatePwd();
		});
		//��ʼ�� -> ���¼� -> �˳�
		$("#logo .logoLogin .logoutImg").bind("mouseover",function(){
			$(this).attr("src","<%=mainWeb%>/images/admin/logout021.png");
		}).bind("mouseout",function(){
			$(this).attr("src","<%=mainWeb%>/images/admin/logout01.png");
		}).bind("click",function(){
			logoAction.logOut();
		});
		//��ʼ�� --> ���Dialog��Debug
		var logo_updatePwd_dialogDebug = $("#pageContent #logo_updatePwd_dialog");
		for(var i =1;i< logo_updatePwd_dialogDebug.length;i++){
			$(logo_updatePwd_dialogDebug[i]).dialog("destroy");
		}
		
	});
	
	var logoAction = new LogoAction();
	function LogoAction(){
		//�޸����� -> ���޸�����Ի���
		this.updatePwd = function(){
			$("#logo_updatePwd_dialog input").val("");
			$('#logo_updatePwd_dialog').dialog('open');
		}
		//�޸����� -> ִ�б���
		this.save = function(){
			if(!logoAction.validate()){
				return;
			}
			var oldPwd = $.trim($("#logo_updatePwd_dialog input[name='oldPwd']").val());
			var newPwd = $.trim($("#logo_updatePwd_dialog input[name='newPwd1']").val());
			$.get("<%=mainWeb%>/admin/user/updatePwd",{'oldPwd':oldPwd,'newPwd':newPwd},function(data){
				data = eval("("+data+")");
			 	if(data.result){
			 		$.messager.alert('��ʾ',data.message,'warning');
			 		$('#logo_updatePwd_dialog').dialog('close')
			 	}else{
			 		$.messager.alert('��ʾ',data.message,'warning');
			 	}
			});
		}
		//�޸����� -> ��֤����
		this.validate = function(){
			if($.trim($("#logo_updatePwd_dialog input[name='oldPwd']").val()) == ""){
				$.messager.alert('��ʾ','ԭʼ����δ��д!','warning');
				return false;
			}else if($.trim($("#logo_updatePwd_dialog input[name='newPwd1']").val()) == ""){
				$.messager.alert('��ʾ','������δ��д!','warning');
				return false;
			}else if($.trim($("#logo_updatePwd_dialog input[name='newPwd2']").val()) == ""){
				$.messager.alert('��ʾ','�ظ�����δ��д!','warning');
				return false;
			}else if($.trim($("#logo_updatePwd_dialog input[name='newPwd1']").val()) != $.trim($("#logo_updatePwd_dialog input[name='newPwd2']").val())){
				$.messager.alert('��ʾ','���������ظ����벻һ��!','warning');
				$("#logo_updatePwd_dialog input[name='newPwd1']").val("");
				$("#logo_updatePwd_dialog input[name='newPwd2']").val("");
				return false;
			}
			return true;
		}
		//�˳�
		this.logOut = function(){
			$.messager.confirm("�˳�","ȷ���˳�ϵͳ��",function(result){
				if(result){
					document.location.href="<%=mainWeb%>/admin/user/logout";
				}
			})
		}		
	}
	
</script>
  </head>
  
  <body>
	<div id="logo">
   		<div class="logoImg">   		
   		</div>
   		<div class="logoLogin">
   			<image src="<%=mainWeb%>/images/admin/key01.png" class="pwdImg"/><image onclick="logOut()" src="<%=mainWeb%>/images/admin/logout01.png" class="logoutImg" />
   		</div>
	   	<div id="logo_updatePwd_dialog" class="easyui-dialog" title="�޸�����" style="width:400px;height:200px;"  
		        data-options="iconCls:'icon-save',resizable:true,modal:true" buttons="#logo-updatePwd-buttons" closed="true">  
		    <table>
		    	<tr>
		    		<td class="td_label">
		    			ԭʼ���룺
		    		</td>
		    		<td>
		    			<input type="password" name="oldPwd" style="width:250px;"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="td_label">
		    			�����룺
		    		</td>
		    		<td>
		    			<input type="password" name="newPwd1"  style="width:250px;"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="td_label">
		    			�ظ����룺
		    		</td>
		    		<td>
		    			<input type="password" name="newPwd2"  style="width:250px;"/>
		    		</td>
		    	</tr>
		    </table>  
		</div>
		<div id="logo-updatePwd-buttons">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
					onclick="logoAction.save()">ȷ��</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#logo_updatePwd_dialog').dialog('close')">�ر�</a>
		</div>
   	</div>   
  </body>
</html>
