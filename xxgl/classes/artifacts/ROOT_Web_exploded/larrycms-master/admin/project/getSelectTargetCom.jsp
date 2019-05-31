<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <div class="layui-form" style="background-color:#fff;min-width: 1280px;margin:2%;margin-bottom:0px;padding:15px 20px">
<table class="layui-table" lay-even lay-skin="line" style="">
  <colgroup>
    <col width="7%">
    <col width="10%">
    <col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="7%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th nowrap>项目名称</th>
      <th nowrap>项目编码</th>
      <th nowrap>建档日期</th>
		<th nowrap>状态</th>
      <th nowrap>报价情况</th>
		<th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${projectList}" var="p" varStatus="sta">
    <tr>
      <td nowrap>${sta.index+1}</td>
      <td nowrap>${p.proName}</td>
      <td nowrap>${p.proCode}</td>
		<td nowrap>${p.createTime}</td>
		<td nowrap>
			<c:if test="${p.proStatus eq '0'}">新建</c:if>
			<c:if test="${p.proStatus eq '1'}">竞价中</c:if>
			<c:if test="${p.proStatus eq '2'}">已结束</c:if>
		</td>
		<td nowrap>${p.offerNum}</td>
		<td>
			<a class="layui-btn layui-btn-small" href="<%=basePath %>project/preSelectTargetCom?proId=${p.id}" >选择供应商</a>
		</td> 
    </tr>
</c:forEach>
  </tbody>
</table>
 </div>
