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
      <th nowrap><input lay-filter="ckall" type="checkbox" checkAll="ids" onclick="checkAll($(this))" lay-skin="primary"></th>
      <th nowrap>序号</th>
      <th nowrap>模板名称</th>
      <th nowrap>创建时间</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${templateList}"  var="tm" varStatus="sta">
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" value="${tm.id}" group="ids" name="ids" onclick="checkOne($(this))" lay-skin="primary"></td>
      <td nowrap>${sta.index+1}</td>
      <td nowrap>${tm.templateName}</td>
      <td >${tm.createTime}</td>
		<td nowrap>
		<a href="javascript:delTemplate('${tm.id}')" class="layui-btn" >删除</a>
		<a target="_blank" href="template/download?templateName=${tm.templatePath}" class="layui-btn" >下载</a>
		</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
