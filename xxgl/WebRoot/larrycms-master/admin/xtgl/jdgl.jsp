<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'bakList.jsp' starting page</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath %>larrycms-master/common/layui/css/layui.css"
	media="all">
<link rel="stylesheet" type="text/css"
	href="<%=basePath %>larrycms-master/admin/css/font.css">
<script type="text/javascript"
	src="<%=basePath %>larrycms-master/common/layui/layui.js"></script>
<script type="text/javascript"
	src="<%=basePath %>larrycms-master/admin/js/xtgl/jdgl.js"></script>
</head>

<body>
	<form class="layui-form" action="" id="searchForm" name="searchForm">
		<blockquote class="layui-elem-quote"
			style="background-color:#fff;margin-top:2%;margin-bottom:1%;margin: 2%;min-width: 1280px;">
			<div class="layui-form-item" style="margin-bottom: 0px;">
				<div class="layui-inline">
					<label class="layui-form-label">季度名称</label>
					<div class="layui-input-inline">
						<input name="loginName" id="quarterName" type="text" lay-verify=""
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline" id="search">
					<div style="width:auto;" class="layui-input-inline">
						<button class="layui-btn" lay-submit="" lay-filter="formDemo">查询</button>
						<a class="layui-btn" href="javascript:addQuarterDiv()">新建</a>
					</div>
				</div>
			</div>
		</blockquote>
	</form>
	
	<!-- 列表展示 -->
	<div class="layui-form"
		style="background-color:#fff;min-width: 1280px;margin:2%;margin-bottom:0px;padding:15px 20px">
		<form id="jdglList" name="jdglList" class="layui-form" action="">

			<table class="layui-table" lay-even lay-skin="line" style="">
				<colgroup>
					<col width="33%">
					<col width="34%">
					<col width="33%">
				</colgroup>
				<thead>
					<tr>
						<th nowrap>季度ID</th>
						<th nowrap>季度名称</th>
						<th nowrap>操作</th>
					</tr>
				</thead>
				<tbody id="jdglBody">
					<c:forEach items="${userList}" var="us" varStatus="sta">
						<tr>
							<td>${sta.index+1}</td>
							<td>${us.loginName}</td>
							<td><c:if test="${us.userStatus eq 0}">可用</c:if> <c:if
									test="${us.userStatus eq 1}">禁用</c:if></td>
							<td>${us.role.roleName}</td>
							<td>${us.createTime}</td>
							<td><c:choose>
									<c:when test="${us.role.code eq 'admin'}">
								--<!-- 超级管理员角色账户不允许操作 -->
									</c:when>
									<c:otherwise>
										<a href="javascript:deleteUser('${us.id}')"
											class="layui-btn layui-btn-small">删除</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>


</body>
</html>
