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
      <th nowrap><input lay-filter="ckall"  type="checkbox" checkAll="ids" onclick="checkAll($(this))" lay-skin="primary"></th>
      <th nowrap>序号</th>
      <th nowrap>供应商名称</th>
      <th nowrap>供应商类型</th>
      <th nowrap>开户银行</th>
      <th nowrap>公司地址</th>
		<th nowrap>添加人</th>
		<th nowrap>添加时间</th>
		<th nowrap>原因</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${comList}" var="com" varStatus="sta">
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" value="${com.id}" group="ids" name="ids" onclick="checkOne($(this))" lay-skin="primary"></td>
      <td nowrap>${sta.index+1}</td>
      <td nowrap><a href="<%=basePath %>com/comInfo?id=${com.id}">${com.comName}</a></td>
      <td nowrap><c:if test="${com.comType eq '0'}">备选供应商</c:if>
					<c:if test="${com.comType eq '1'}">合作供应商</c:if></td>
      <td nowrap>${com.bankAccount}</td>
      <td nowrap>${com.comAddr}</td>
      <td nowrap>${com.forbidUser}</td>
		<td nowrap>${com.forbidTime}</td>
		<td nowrap>
			${com.forbidReason}
		</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
