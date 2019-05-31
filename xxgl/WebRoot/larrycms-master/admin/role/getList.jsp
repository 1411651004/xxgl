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
    <col width="5%">
    <col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th nowrap>角色名称</th>
      <th nowrap>创建时间</th>
      <th nowrap>更新时间</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${roleList}" var="role" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${role.roleName}</td>
					<td>${role.createTime}</td>
					<td>${role.updateTime}</td>
					<td>
						<a class="layui-btn layui-btn-small" href="javascript:updateRole('${role.id}')" onclick="">修改</a>
						<a class="layui-btn layui-btn-small" href="javascript:delRole('${role.id}')" onclick="">删除</a>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="role/getList" />
</jsp:include>