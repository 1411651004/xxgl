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
    <col width="17%">
	<col width="14%">
	<col width="14%">
	<col width="9%">
	<col width="7%">
	<col width="11%">
	<col width="11%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th nowrap>项目名称</th>
      <th nowrap>项目编码</th>
      <th nowrap>报价截止时间</th>
      <th nowrap>状态</th>
      <th nowrap>联系人</th>
      <th nowrap>联系电话</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${projectList}" var="p" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td><a style="text-decoration: underline;" href = "<%=basePath%>project/proinfo?returnpath=com/accProjects&id=${p.id}" >${p.proName}</a></td>
					<td>${p.proCode}</td>
					<td>${p.proEndtime}</td>
					<td>
						<c:if test="${p.proStatus eq '0'}">新建</c:if>
						<c:if test="${p.proStatus eq '1'}">竞价中</c:if>
						<c:if test="${p.proStatus eq '2'}">已结束</c:if>
					</td>
					<td>${p.conName}</td>
					<td>${p.conPhone}</td>
					<td>
					<c:if test="${com.comType eq '1' }">
						<a href="<%=basePath%>com/preOffer?proId=${p.id}" class="layui-btn layui-btn-small" >报价</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
