<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<div id="memberTabs" class="easyui-tabs" fit="true" border="false">  
    <div title="����¼">
    </div>  
    <div title="���ֲ鿴"> 
    </div>
    <div title="��ǰ����"> 
    </div>
    <div title="��ʷ����">  
    </div>
</div>
<script type="text/javascript">
$(function(){
	var titles = new Array("����¼","��ǰ����","��ʷ����","���ֲ鿴");
	var hrefs = new Array(
		mainWeb+"/app/cms/admin/member/chargelogfrm.jsp",
		mainWeb+"/app/cms/admin/member/currentorderfrm.jsp",
		mainWeb+"/app/cms/admin/member/historyorderfrm.jsp",
		mainWeb+"/app/cms/admin/member/scoreviewfrm.jsp");	
	//�����¼� -> ����tab���
	$('#memberTabs').tabs({
		onSelect: function(title){
			isExists(title);
		}
	});
	function isExists(title){
		var tab = $('#memberTabs').tabs('getSelected');
		for(var i = 0;i<titles.length;i++){
			if(title == titles[i] && $.trim(tab[0].innerText) == ""){
				updateTabContent(tab,hrefs[i]);
				break;
			}
		}
	}
	function updateTabContent(tab,href){
		$('#memberTabs').tabs('update', {
			tab: tab,
			options: {
				href:href
			}
		});
	}
});
</script>