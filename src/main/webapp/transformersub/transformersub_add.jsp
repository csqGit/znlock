<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/default.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/layui.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layui.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/add.css" />
<style type="text/css">
</style>
</head>
<body>


	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11"
				border="0" alt=""></span>&nbsp;变电站管理<span>&nbsp;</span> <span><img
				src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
			<a href="<%=path%>/transformersub/transformersub_add.jsp">变电站新增</a><span>&nbsp;</span>
		</div>
	</div>
	<%-- <div id="tips">
<strong>新增变电站</strong>
</div> --%>
	<div id="mainContainer">

		<form name="addForm" id="addForm" class="layui-form"
			action="<%=path%>/pcTransformersub/insertTransformerSub"
			method="post">
			<table>
				<tr>
					<td>单位名称</td>
					<td>
						<div class="layui-form-item">

							<div class="layui-input-inline">
								<select name="companyId.id" lay-verify="required" lay-search="">
									<option value="${admin.companyId.id }">${admin.companyId.companyname }</option>
								</select>
							</div>

						</div>
					</td>
				</tr>
				<tr>
					<td>电压等级</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="voltage" value="${admin.voltage }"
									readonly="readonly">
							</div>
						</div>
					</td>

				</tr>
				<tr>
					<td>变电站名称</td>

					<td>
						<div class="layui-form-item">

							<div class="layui-input-inline">
								<input type="text" id="transformersubname"
									placeholder="请输入变电站名称" name="transformersub" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="layui-form-item form-button">
							<button class="button active layui-btn" type="button">提&nbsp;交</button>
							<input class="button layui-btn layui-btn-normal" type="reset" value="重&nbsp;置">
						</div>
					</td>
				</tr>
			</table>
		</form>

		<script type="text/javascript">
			$(function() {
				$("button").click(function() {
					//校验变电站名称是否输入
					var trans = $('#transformersubname');
					if (trans.val().trim().length == 0) {
						//alert('请输入变电站名称！')
						trans.css({
							'border' : '1px solid red'
						});
						return false;
					} else {
						trans.css({
							'border' : 'none'
						});
					}

					$("#addForm").submit();
				});
			})
		</script>


		<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
		<script type="text/javascript" src="<%=path%>/js/layui-form.js">
			
		</script>
	</div>
</body>
</html>