<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>
		&nbsp;管理员管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path%>/pcAdmin/selectAdminList?page=1">管理员列表</a><span>&nbsp;</span>
	</div>
</div>
<div id="tips">
	提示
</div>
<div id="mainContainer">
	
   <strong style="color: red;">${result }</strong>
   <strong><a href="<%=path%>/pcAdmin/selectAdminList?page=1">返回查看</a></strong>
   <!--数据表格空白 -->
</div>
</body>
</html>