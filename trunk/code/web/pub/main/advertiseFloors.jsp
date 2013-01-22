<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<script type="text/javascript">
$(function advertiseFloorsLoading()
{
	var leftUrl = "leftUrl#";
	var left1Url = "left1Url#";
	var rightUrl = "rightUrl#";
	var right1Url = "right1Url#";
	var adType = 2;
	if(adType==1)
	{
		$("#advertise-floor-left").css("display","block");
		$("#advertise-floor-right").css("display","block");
		$("#advertise-floor-left").click(function leftClick(){alert(leftUrl);});
		$("#advertise-floor-right").click(function rightClick(){alert(rightUrl);});
	}
	else
	{
		//$("#advertise-floor-left1").css("background-image","url('./images/page/ad07-01.png')");
		//$("#advertise-floor-right1").css("background-image","url('./images/page/ad07-02.png')");
		$("#advertise-floor-left1").css("display","block");
		$("#advertise-floor-center").css("display","block");
		$("#advertise-floor-right1").css("display","block");
		$("#advertise-floor-left1").click(function left1Click(){alert(left1Url);});
		$("#advertise-floor-right1").click(function right1Click(){alert(right1Url);});
	}
});
</script>
		<style type="text/css">
#advertise-floor {
	width: 1200px;
	margin: 0 auto;
	height: 105px;
}

#advertise-floor-left {
	background-image: url('./images/page/ad01-aa.png');
	width: 830px;
	display: inline;
	float: left;
	height: 105px;
}

#advertise-floor-right {
	background-image: url('./images/page/ad02-01.png');
	display: inline;
	width: 370px;
	float: right;
	height: 105px;
}

#advertise-floor-left1 {
	background-image: url('./images/page/ad01-aa.png');
	width: 916px;
	display: inline;
	float: left;
	height: 105px;
}

#advertise-floor-center {
	width: 8px;
	display: inline;
	float: left;
	height: 105px;
}

#advertise-floor-right1 {
	background-image: url('./images/page/ad02-01.png');
	display: inline;
	width: 276px;
	float: right;
	height: 105px;
}
</style>

	</head>

	<body>
		<div id="advertise-floor">
			<div id="advertise-floor-left" style="display: none;"></div>
			<div id="advertise-floor-right" style="display: none;"></div>
			<div id="advertise-floor-left1" style="display: none;"></div>
			<div id="advertise-floor-center" style="display: none;"></div>
			<div id="advertise-floor-right1" style="display: none;"></div>
		</div>
	</body>
</html>
