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
    <col width="30%">
	<col width="30%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th >结算方式简称</th>
      <th >实际值</th>
      <th >操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${codeList}" var="code" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${code.codeName}</td>
					<td>${code.codeValue}</td>
					<td>
						<a class="layui-btn layui-btn-small" href="javascript:updatecode('${code.id}')" >修改</a>
						<a class="layui-btn layui-btn-small" href="javascript:deletecode('${code.id}')" >删除</a>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
