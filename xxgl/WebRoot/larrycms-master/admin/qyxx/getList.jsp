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
	<col width="5%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap><input lay-filter="ckall" id="ckall" type="checkbox" title="全选" checkAll="ids" lay-skin="primary"></th>
      <th nowrap>序号</th>
      <th nowrap>名称</th>
      <th nowrap>城市</th>
      <th nowrap>负责人</th>
      <th nowrap>联系方式</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${qyxxList}" var="qyxx" varStatus="statu" >
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" primarys="${qyxx.id }" idnum="${qyxx.fzr }" value="${qyxx.lxfs }" group="ids" name="ids" lay-skin="primary"></td>
      <td nowrap>${statu.index+1 }</td>
      <td nowrap>${qyxx.mc }</td>
      <td nowrap>${qyxx.cs }</td>
      <td nowrap>${qyxx.fzr }</td>
      <td nowrap>${qyxx.lxfs }</td>
      <td nowrap>
        <c:if test="${record.type == '0'}">
        <a class="layui-btn layui-btn-small" href="javascript:fwjl('${qyxx.id }')" >院校档案</a>
        </c:if>
      	<a class="layui-btn layui-btn-small" href="javascript:upd('${qyxx.id }')" >修改</a>
      	<a class="layui-btn layui-btn-small" href="javascript:del('${qyxx.id }')" >删除</a>
	  </td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="qyxx/getList?type=${record.type}" />
</jsp:include>