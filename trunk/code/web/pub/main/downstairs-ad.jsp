<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/pub/common/inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
#downstairs-ad {
	width: 1200px;
	height: 107px;
}

#downstairs-ad div {
	display: inline;
}

#downstairs-ad div img {
	border: 0px;
}

#downstairs-ad div.downstairs-ad-first {
	width: 895px;
	float: left;
}

#downstairs-ad div.downstairs-ad-last {
	width: 305px;
	float: right;
}
</style>
		<script type="text/javascript">
$(function(){ 
	$.post(mainWeb+'/front/getDownStairImages',function(result){
		if(result.result){
			$('#downstair-ad01').html('<a target="_blank" href="'+result.returnObj[2]+'"><img height="107px" width="895" src="'+mainWeb+result.returnObj[0]+'" /></a>');
			$('#downstair-ad02').html('<a target="_blank" href="'+result.returnObj[3]+'"><img height="107px" width="305" src="'+mainWeb+result.returnObj[1]+'" /></a>');
		}else{
			$('#downstairs-ad').hide();
		}
	},'json');
});

</script>
	</head>
	<body>
		<!-- begin of 底楼广告 -->
		<div id="downstairs-ad">
			<div class="downstairs-ad-first" id="downstair-ad01">
			</div>
			<div class="downstairs-ad-last" id="downstair-ad02">
			</div>
		</div>
		<!-- end of 底楼广告 -->
	</body>
</html>