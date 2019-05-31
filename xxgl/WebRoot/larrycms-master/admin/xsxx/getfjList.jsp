<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <div class="layui-form" style="background-color:#fff;min-width: 1140px;margin:2%;margin-bottom:0px;padding:15px 20px">
<form id="idForm" name="idForm" class="layui-form" action="">

<table class="layui-table" lay-even lay-skin="line" style="">
  <colgroup>
    <col width="5%">
    <col width="5%">
    <col width="5%">
	<col width="10%">
	<col width="10%">
	<col width="7%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap><input lay-filter="ckall" id="ckall" type="checkbox" title="全选" checkAll="ids" lay-skin="primary"></th>
      <th nowrap>姓名</th>
      <th nowrap>性别</th>
      <th nowrap>学校名称</th>
      <th nowrap>所学专业</th>
      <th nowrap>联系电话</th>
	  <th nowrap>身份证号</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${xsxxList}" var="xsxx" >
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" idnum="${xsxx.id }" value="${xsxx.dh }" sfz="${xsxx.sfz }" group="ids" name="ids" lay-skin="primary"></td>
      <td nowrap>${xsxx.xm }</td>
      <td nowrap>${xsxx.xb }</td>
      <td nowrap>${xsxx.xx }</td>
      <td nowrap>${xsxx.zy }</td>
      <td nowrap>${xsxx.dh }</td>
      <td nowrap>${xsxx.sfz }</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="xsxx/getfjList?sta=${record.sta}" />
</jsp:include>