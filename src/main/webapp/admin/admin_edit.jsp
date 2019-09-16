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
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/default.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/layui.js"></script>
<script type="text/javascript" src="<%=path%>/js/layui-form.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/layui.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/add.css" />

<body>


	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11"
				border="0" alt=""></span>&nbsp;管理员管理<span>&nbsp;</span> <span><img
				src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
			<a href="javascript:void(0)">管理员编辑</a><span>&nbsp;</span>
		</div>
	</div>
	<%-- <div id="tips">
<strong style="margin: 0 auto; paddig: 10px 0; text-align: center; display: block;">管理员编辑</strong>
</div> --%>
	<div id="mainContainer">



		<form name="addForm" id="addForm" class="layui-form"
			action="<%=path%>/pcAdmin/updateAdmin" method="post">
			<input type="hidden" name="id" value="${admin.id }">
			<table>

				<tr>
					<td>用&nbsp;户&nbsp;名</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="username" value="${admin.username }" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>真实姓名</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="realname" value="${admin.realname }" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="password" name="password"
									value="${admin.password }" />
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="telephone" id="telephone"
									value="${admin.telephone}" />
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td>单位名称</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<select name="companyId.id">
									<option value="${admin.companyId.id }" selected="selected">${admin.companyId.companyname }</option>
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
					<td colspan="2">
						<div class="layui-form-item form-button">
							<button class="button active layui-btn" type="button">提&nbsp;交</button>
							<input class="button layui-btn layui-btn-normal" type="reset"
								value="重&nbsp;置">
						</div>
					</td>
				</tr>

			</table>

		</form>


	</div>

	<script type="text/javascript">
		$(function() {

			$('button')
					.click(
							function() {
								var username = $("input[name='username']");
								var realname = $("input[name='realname']");
								var password = $("input[name='password']");
								var telephone = $("input[name='telephone']");
								var val = document.getElementById('telephone').value;
								var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;

								if (username.val().trim().length == 0) {
									username.css({
										'border' : '1px solid red'
									});
									return false;
								} else {
									username.css({
										'border' : 'none'
									});
								}

								if (realname.val().trim().length == 0) {
									realname.css({
										'border' : '1px solid red'
									});
									return false;
								} else {
									realname.css({
										'border' : 'none'
									});
								}
								if (password.val().trim().length == 0) {
									password.css({
										'border' : '1px solid red'
									});
									return false;
								} else if (password.val().trim().length < 6) {

									password.val('');
									password.css({
										'border' : '1px solid red'
									});
									return false;
								}
								if (!mobilevalid.test(val)) {
									telephone.val('');
									telephone.placeholder = '';
									telephone.placeholder = 'dianhua';
									telephone.css({
										'border' : '1px solid red'
									});
									return false;
								} else {
									telephone.css({
										'border' : 'none'
									});
								}

								$('#addForm').submit();
							});

		})
	</script>
</body>
</html>