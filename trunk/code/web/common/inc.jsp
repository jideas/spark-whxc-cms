<%@page import="java.io.PrintWriter"%>
<%@page import="org.infinispan.util.concurrent.ConcurrentWeakKeyHashMap.ReusableIterator"%>
<%@page import="com.spark.cms.common.Constant"%>
<%@page import="com.spark.cms.services.form.UserExtForm"%>
<%
  request.setCharacterEncoding("GBK");
  String mainWeb = request.getContextPath();
  String jqueryUIPath = mainWeb+"/scripts/jqueryui";
 
  UserExtForm user = (UserExtForm)request.getSession().getAttribute(Constant.LoginAdminUser);

%>
<script type="text/javascript">
  var mainWeb = "<%=mainWeb%>";
  var webPath = mainWeb;
  window.UEDITOR_HOME_URL = "<%=mainWeb%>/scripts/ueditor/";
</script>
<script type="text/javascript" src="<%=mainWeb%>/scripts/common.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=mainWeb%>/css/common.css">