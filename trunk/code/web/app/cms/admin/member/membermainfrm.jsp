<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<div id="memberTabs" class="easyui-tabs" fit="true" border="false">  
    <div title="余额记录">
    </div>  
    <div title="积分查看"> 
    </div>
    <div title="当前订单"> 
    </div>
    <div title="历史订单">  
    </div>
</div>
<script type="text/javascript">
$(function(){
	var titles = new Array("余额记录","当前订单","历史订单","积分查看");
	var hrefs = new Array(
		mainWeb+"/app/cms/admin/member/chargelogfrm.jsp",
		mainWeb+"/app/cms/admin/member/currentorderfrm.jsp",
		mainWeb+"/app/cms/admin/member/historyorderfrm.jsp",
		mainWeb+"/app/cms/admin/member/scoreviewfrm.jsp");	
	//监听事件 -> 单击tab面板
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