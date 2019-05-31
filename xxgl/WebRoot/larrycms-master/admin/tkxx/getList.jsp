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
    <col width="3%">
    <col width="9%">
	<col width="5%">
	<col width="9%">
	<col width="5%">
	<col width="5%">
	<col width="4%">
	<col width="5%">
	<col width="9%">
	<col width="5%">
	<col width="18%">
	
  </colgroup>
  <thead>
    <tr>
      <th nowrap>姓名</th>
      <th nowrap>学校名称</th>
	  <th nowrap>退款银行</th>
	  <th nowrap>退款银行分行信息</th>
	  <th nowrap>退款账号</th>
	  <th nowrap>户名</th>
	  <th nowrap>金额</th>
	  <th nowrap>收件人</th>
	  <th nowrap>收件人手机号</th>
	  <th nowrap>收件地址</th>
	
    </tr> 
  </thead>
<tbody>
<c:forEach items="${tkxxList}" var="tkxx" >
    <tr>
      <td nowrap>${tkxx.xm }</td>
      <td nowrap>${tkxx.byzd1 }</td>
      <td nowrap>${tkxx.tkyh }</td>
      <td nowrap>${tkxx.fhxx }</td>
      <td nowrap>${tkxx.tkzh }</td>
      <td nowrap>${tkxx.hm }</td>
      <td nowrap>${tkxx.je }</td>
      <td nowrap>${tkxx.sjr }</td>
      <td nowrap>${tkxx.sjh }</td>
      <td nowrap>${tkxx.dz }</td>
      
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="xsxx/getList?sta=${record.sta}" />
</jsp:include>