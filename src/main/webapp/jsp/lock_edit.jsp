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
	<script type="text/javascript" src="../js/Calendar3.js"></script>

	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11"
				border="0" alt=""> </span>&nbsp;智能锁管理<span>&nbsp;</span> <span><img
				src="../images/arror.gif" width="7" height="11" border="0" alt="">
			</span>&nbsp;<a href="javascript:void(0)">智能锁编辑</a><span>&nbsp;</span>
		</div>
	</div>
	<%-- <div id="tips">
<strong>编辑智能锁</strong>
</div> --%>
	<div id="mainContainer">

		<form name="addForm" id="addForm" class="layui-form"
			action="<%=path%>/pcLock/updateLock" method="post">
			<input type="hidden" name="id" value="${lock.id }">
			<table>
				<tr>
					<td>单位名称</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<select name="companyId.id">
									<option value="${admin.companyId.id }">${admin.companyId.companyname }</option>
								</select>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="30%">电压等级</td>
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
					<td>变电站</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<select name="transformersubId.id" id="transformsub">
									<option value="">请选择</option>
									<s:forEach var="trans" items="${transformerSubList }">
										<option value="${trans.id }"
											${trans.transformersub == lock.transformersubId.transformersub ? 'selected' : '' }>${trans.transformersub }</option>
									</s:forEach>
								</select>
							</div>
						</div>
					</td>
				</tr>


				<tr>
					<td width="30%">did</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="did" value="${lock.did }" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="30%">mac</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="mac" value="${lock.mac }" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td width="30%">passcode：</td>
					<td>
						<div class="layui-form-item">
							<div class="layui-input-inline">
								<input type="text" name="passcode" value="${lock.passcode }" />
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
			$("button").click(function() {
				//校验变电站数据是否正确
				var trans = $('#transformsub');
				var did = $("input[name='did']");
				var mac = $("input[name='mac']");
				if (trans.val().trim().length == 0) {
					//alert('请选择变电站！')
					trans.css({
						'border' : '1px solid red'
					});
					return false;
				} else {
					trans.css({
						'border' : 'none'
					});
				}

				if (did.val().trim().length == 0) {
					//alert('请输入设备did！')
					did.css({
						'border' : '1px solid red'
					});
					return;
				} else {
					did.css({
						'border' : 'none'
					});
				}
				if (mac.val().trim().length == 0) {
					//alert('请输入mac！')
					mac.css({
						'border' : '1px solid red'
					});
					return;
				} else {
					mac.css({
						'border' : 'none'
					});
				}

				$("#addForm").submit();
			});
		})
	</script>
</body>
</html>