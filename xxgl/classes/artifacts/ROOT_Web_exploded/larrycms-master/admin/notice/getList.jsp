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
	<col width="30%">
	<col width="10%">
	<col width="10%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap><input lay-filter="ckall" type="checkbox" checkAll="ids" onclick="checkAll($(this))" lay-skin="primary"></th>
      <th nowrap>序号</th>
      <th nowrap>公告标题</th>
      <th nowrap>创建时间</th>
      <th nowrap>公告状态</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${noticeList}" var="notice" varStatus="count">
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" value="${notice.id}" group="ids" name="ids" onclick="checkOne($(this))" lay-skin="primary"></td>
      <td nowrap>${count.index+1}</td>
      <td nowrap>${notice.noticeTitle}</td>
      <td >${notice.createTime}</td>
		<td nowrap><c:if test="${notice.noticeStatus eq '0'}">新建</c:if>
						<c:if test="${notice.noticeStatus eq '1'}">已发布</c:if>
						<c:if test="${notice.noticeStatus eq '2'}">已撤回</c:if></td>
		<td nowrap><c:if test="${notice.noticeStatus eq '0'}">
							<a href="#" onclick="updateNotice('${notice.id}','1')">发布</a>
						</c:if>
						<c:if test="${notice.noticeStatus eq '1'}">
							<a href="#" onclick="updateNotice('${notice.id}','2')">撤回</a>
						</c:if>
						<c:if test="${notice.noticeStatus eq '2'}">
							<a href="#" onclick="updateNotice('${notice.id}','1')">发布</a>
						</c:if></td>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
