<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script type="text/javascript" src="../js/Calendar3.js"></script>

	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11"
				border="0" alt=""></span>&nbsp;用户管理<span>&nbsp;</span> <span><img
				src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
			<a href="javascript:void(0)">编辑用户</a><span>&nbsp;</span>
		</div>
	</div>
	<%-- <div id="tips">
		<strong>用户编辑</strong>
	</div> --%>
	<div id="mainContainer">

		<form name="addForm" id="addForm" class="layui-form"
			action="<%=path%>/pcUser/updateUser" method="post">
			<input type="hidden" name="id" value="${user.id }">
			<table width="400">

				<%-- <tr>
					<td width="30%">旧用户名</td>
					<td><input type="text" name="oldUsername"  value="${user.username }" /></td>
				</tr> --%>
				<%-- <tr>
					<td width="30%">用户名</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="username" value="${user.username }" />
							</div>
						</div>
					</td>
				</tr> --%>
				<tr>
					<td width="30%">姓名</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="realname" value="${user.realname }" />
							</div>
						</div>
					</td>
				</tr>


				<tr>
					<td width="30%">新密码</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="password" name="password" value="${user.password }"
									placeholder="每次修改信息必须修改密码" />
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td width="30%">电话</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="phone" id="phone" maxlength="11"
									value="${user.phone }" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="30%">电压等级</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="voltage" value="${user.voltage }"
									readonly="readonly" />
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td width="30%">变电站</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<select name="transformersubId.id" id="transformsub">
									<s:forEach var="trans" items="${transformerSubList }">
										<option value="${trans.id }"
											${trans.transformersub == user.transformersubId.transformersub ? "selected" : "" }>${trans.transformersub }</option>
									</s:forEach>
								</select>
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
		/*onblur="checkme(this)" 
		function checkme(obj) {
			var val = document.getElementById('phone').value;
			var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
			if (!mobilevalid.test(val)) {
				alert('请输入正确的手机号码！')
				return false;
			}
		} */

		$(function() {
			$("button")
					.click(
							function() {
								var phone = $("input[name='phone']");
								var realname = $("input[name='realname']");
								var password = $("input[name='password']");
								var trans = $("#transformsub");

								var val = document.getElementById('phone').value;
								var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;

								if (realname.val().trim().length == 0) {
									//alert('姓名不能为空！');
									realname.css({
										'border' : '1px solid red'
									});
									return false;
								} else {
									realname.css({
										'border' : 'none'
									});
								}

								if (!mobilevalid.test(val)) {
									//alert('请输入正确的手机号码！')
									$("input[name='phone']").val('');

									phone.css({
										'border' : '1px solid red'
									});
									return false;
								} else {
									phone.css({
										'border' : 'none'
									});
								}

								if (password.val().trim().length == 0) {
									//alert('密码不能为空！');
									password.css({
										'border' : '1px solid red'
									});
									return false;
								} else if (password.val().trim().length < 6) {
									//alert('密码不能小于6位！');
									password.val('');

									return false;
								} else {
									password.css({
										'border' : 'none'
									});
								}

								if (trans.val().trim().length == 0) {
									//alert('请选择变电站！');
									trans.css({
										'border' : '1px solid red'
									});
									return false;
								} else {
									trans.css({
										'border' : 'none'
									});
								}

								$('#addForm').submit();
							});
		})
	</script>
</body>


</html>