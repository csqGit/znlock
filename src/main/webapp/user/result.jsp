<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/navi_query.css" />
<style type="text/css">

</style>
<body>
<div id="navi">
	<!-- 导航空白 -->
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;用户管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/pcLock/selectLockList?page=1">用户列表</a><span>&nbsp;</span>
	</div>
</div>
<div id="tips">
	提示
</div>
<div id="mainContainer">
   <%-- <strong>${result }<a href="<%=path%>/user/user_add.jsp">继续添加？</a></strong> --%>
   <strong style="color: red;">${result }</strong>
   <strong><a href="<%=path%>/pcUser/selectUserList?page=1">返回查看</a></strong>
   <!--数据表格空白 -->
</div>
</body>
</html>