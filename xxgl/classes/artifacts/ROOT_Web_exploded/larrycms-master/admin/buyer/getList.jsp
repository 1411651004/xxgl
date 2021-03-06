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
    <col width="12%">
	<col width="12%">
	<col width="8%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th >采购商名称</th>
      <th >采购商地址</th>
      <th >电话</th>
      <th >开户行</th>
      <th >账号</th>
      <th >税号</th>
      <th >创建时间</th>
      <th >操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${buyerList}" var="buyer" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${buyer.buyerName}</td>
					<td>${buyer.buyerAddr}</td>
					<td>${buyer.buyerPhone}</td>
					<td>${buyer.buyerBankAccount}</td>
					<td>${buyer.buyerAccount}</td>
					<td>${buyer.buyerTaxreceNo}</td>
					<td>${buyer.createTime}</td>
					<td>
						<a class="layui-btn layui-btn-small" href="javascript:updateBuyer('${buyer.id}')" >修改</a>
						<a class="layui-btn layui-btn-small" href="javascript:deleteBuyer('${buyer.id}')" >删除</a>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
