<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <div class="layui-form" style="background-color:#fff;min-width: 1280px;margin:2%;margin-bottom:0px;padding:15px 20px">
<form id="idForm" name="idForm" class="layui-form" action="">

<table class="layui-table" lay-even lay-skin="line" style="">
  <colgroup>
    <col width="4%">
    <col width="11%">
	<col width="11%">
	<col width="11%">
	<col width="11%">
	<col width="11%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th nowrap>登录名称</th>
      <th nowrap>账户状态</th>
      <th nowrap>角色</th>
      <th nowrap>创建时间</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${userList}" var="us" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${us.loginName}</td>
					<td>
						<c:if test="${us.userStatus eq 0}">可用</c:if>
						<c:if test="${us.userStatus eq 1}">禁用</c:if>
					</td>
					<td>
						${us.role.roleName}
					</td>
					<td>
						${us.createTime}
					</td>
					<td>
						<c:choose>
							<c:when test="${us.role.code eq 'admin'}">
								--<!-- 超级管理员角色账户不允许操作 -->
							</c:when>
							<c:otherwise>
								<a href="javascript:deleteUser('${us.id}')" class="layui-btn layui-btn-small">删除</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="user/getList" />
</jsp:include>