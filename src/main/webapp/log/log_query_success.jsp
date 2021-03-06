<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/navi_query.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
</head>

<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="<%=path %>/images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;历史记录<span>&nbsp;</span>
		<span><img src="<%=path %>/images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
		<a href="<%=path%>/pcAdmin/selectLogList?page=1">开锁记录</a><span>&nbsp;</span>
	</div>
</div>
<!-- <div id="tips">

	<strong>开锁记录</strong>
</div> -->
<div id="mainContainer">
<!-- 从session中获取学生集合 -->

<table id="tab" class="default" width="100%">
	<col width="3%">
	<col width="8%">
	<col width="7%">
	<col width="3%">
	<col width="13%">
	<col width="13%">
	<col width="5%">
	
	<col width="5%">
	<tr class="title" height="40px;" >
		<td>编号</td>
		<td>单位名称</td>
		<td>变电站</td>
		<!-- <td>锁名称</td> -->
		<td>申请人</td>
		<td>申请日期</td>
		<td>开锁日期</td>
		<td>审核结果</td>
		
		<td>操作</td>
	</tr>
	
	<!-- 遍历开始 -->
	<c:forEach items="${logList }" var="log" varStatus="i">
		<tr class="tit">
			<td>${i.index+1 }</td>
			<td>${log.companyId.companyname }</td>
			<td>${log.transformersubId.transformersub }</td> 
			<td>${log.realname }</td>
			<td>${log.requesttime }</td>
			<td>${log.opentime }</td>
			<td>${log.status == '0' ? '<span style="color: orange;">未审核</span>' : log.status == '1' ?
			  '<span style="color: green;">同意</span>' : '<span style="color: red;">拒绝</span>'}</td>
			
			<td>
			<a href="<%=path%>/pcAdmin/deleteRequestlog?id=${log.id}&page=${pageData.pages}" onclick="javascript:return confirm('真的要删除吗？');">删除</a>
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
			<li><font id="count">总条数：${pageData.count }&nbsp;</font></li>
		</ul>
	</div>
</div>


<script type="text/javascript">
	$(function(){
		var pagesStr = $("#pages").text();
		var currentPageStr = $("#currentPage").text();
		var pages = parseInt(pagesStr);
		var currentPage = parseInt(currentPageStr);
		var url = "<%=path %>/pcAdmin/selectLogList?currentPage=";
		
		$("#first").click(function(){
			
			if(currentPage == 1){
				return false;
			}//uuu
			window.location.href = url + 1;
		});
		
		$("#prePage").click(function(){
			if(currentPage == 1){
				return false;
			}
			currentPage --;
			window.location.href= url + currentPage;
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