<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/navi_query.css" />
	<title>管理员信息页面</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>

<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;用户管理<span>&nbsp;</span>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path%>/pcUser/selectUserList?page=1">用户列表</a><span>&nbsp;</span>
	</div>
</div>
<%--  <div id="tips" style="text-align: center">
	<div id="buttonGroup">
	
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/pcUser/requestType?param=add">添加用户</a>
		</div> 
		
		<form action="<%=path%>/pcLock/selectLockByName">
	<div class="button" style="width: 130px; height: 30px; margin-left: 20px; background: none;">
			<input style="width: 120px;height: 30px; padding-left: 10px; background: none; border: none; 
			outline:none;" name="lockName" type="text" placeholder="请输入用户名称" />
		</div>
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<input type="submit" value="查询" />
		</div>
		</form>
	</div>
	<strong>用户列表</strong>
</div> --%>
<div id="mainContainer">
<!-- 从session中获取学生集合 -->

<table class="default" width="100%">
	<col width="5%">
	<col width="10%">
	<col width="10%">
	<%-- <col width="10%">
	<col width="10%"> --%>
	<col width="10%">
	<col width="10%">
	<%-- <col width="10%"> --%>
	<col width="10%">
	<col width="7%">
	<col width="8%">
	<tr class="title" height="40px">
		<td>编号${obj.realname }</td>
		<td>用户名</td>
		<td>真实姓名</td>
		<td>密码</td>
		<td>电压等级</td>
		<td>变电站</td> 
		<td>电话号码</td>
		<!-- <td>职务</td>
		<td>工号</td>
		<td>管理员</td> -->
		<td>操作</td>
	</tr>
	<c:forEach var="obj" items="${user }" varStatus="i">
		<tr class="bg" height="30px;" style="line-height:30px;">
			<td>${i.index + 1 }</td>
			<td>${obj.username }</td>
			<td>${obj.realname }</td>
			<td>***</td>
			<td>${obj.voltage }</td>
			<td>${obj.transformersubId.transformersub }</td>
			<td>${obj.phone }</td>
			<%-- <td>${obj.post }</td>
			<td>${obj.jobnumber }</td>
			<td>${obj.administrators }</td> --%>
			<td>
				<%-- <a href="<%=path %>/pcUser/requestType?param=update&id=${obj.id}">修改</a> --%>
				&nbsp;&nbsp;
				<a href="<%=path%>/pcUser/deleteUser?id=${obj.id}" 
				onclick="javascript:return confirm('真的要删除吗？');">删除</a>
			</td>
		</tr>
	</c:forEach>
	
	<!-- 遍历结束 -->
</table>
	
	<div id="bottom">
		<ul>
			<li><font id="first">首页</font></li>
			<li><font id="prePage">上一页</font></li>
			<li><font id="currentPage">${pageData.currentPage }</font>/<font id="pages">${pageData.pages }</font></li>
			<li><font id="next">下一页</font></li>
			<li><font id="end">尾页</font></li>
			<li><font id="enddd">总条数：${pageData.count }&nbsp;</font></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		var pagesStr = $("#pages").text();
		var currentPageStr = $("#currentPage").text();
		var pages = parseInt(pagesStr);
		var currentPage = parseInt(currentPageStr);
		var url = "<%=path %>/pcUser/selectUserList?currentPage=";
		$("#first").click(function(){
			if(currentPage == 1){
				return false;
			}
			window.location.href = url + 1;
		});
		
		$("#prePage").click(function(){
			if(currentPage == 1){
				return false;
			}
			currentPage --;
			window.location.href=url + currentPage;
		});
		$("#next").click(function(){
			if(currentPage == pages){
				return false;
			}
			currentPage ++;
			window.location.href = url + currentPage;
		});
		$("#end").click(function(){
			if(currentPage == pages){
				return false;
			}
			window.location.href = url + pages;
		});
	})
	
</script>
</body>
</html>