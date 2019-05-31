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
    <col width="3%">
    <col width="5%">
	<col width="10%">
	<col width="40%">
	<col width="6%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap><input lay-filter="ckall" type="checkbox" checkAll="ids" onclick="checkAll($(this))" lay-skin="primary"></th>
      <th nowrap>序号</th>
      <th nowrap>用户名</th>
      <th nowrap>内容</th>
      <th nowrap>状态</th>
      <th nowrap>推送时间</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${msgList}" var="msg" varStatus="count">
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" value="${msg.id}" group="ids" name="ids" onclick="checkOne($(this))" lay-skin="primary"></td>
      <td nowrap>${count.index+1}</td>
      <td nowrap>${msg.userName}</td>
      <td >${msg.remindCon}</td>
		<td nowrap>${msg.isok=='0'?'推送成功':(msg.isok=='1'?'推送失败':msg.isok)}</td>
		<td nowrap>${msg.createTime}</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
