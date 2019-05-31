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
    <col width="5%">
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
      <th nowrap><input lay-filter="ckall" type="checkbox" checkAll="ids" onclick="checkAll($(this))" lay-skin="primary"></th>
      <th nowrap>序号</th>
      <th nowrap>供应商名称</th>
      <th nowrap>开户银行</th>
      <th nowrap>公司地址</th>
      <th nowrap>联系人</th>
		<th nowrap>联系人电话</th>
		<th nowrap>办公电话</th>
		<th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${comList}" var="com" varStatus="sta">
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" value="${com.id}" group="ids" name="ids" onclick="checkOne($(this))" lay-skin="primary"></td>
      <td nowrap>${sta.index+1}</td>
      <td nowrap>${com.comName}</td>
      <td nowrap>${com.bankAccount}</td>
		<td nowrap>${com.comAddr}</td>
		<td nowrap>${com.conName}</td>
		<td nowrap>
			${com.conPhone}
		</td>
		<td nowrap>${com.comPhone}</td>
		<td nowrap><a href="javascript:resetPwd('${com.id}')" class="layui-btn layui-btn-small" >重置密码</a></td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
