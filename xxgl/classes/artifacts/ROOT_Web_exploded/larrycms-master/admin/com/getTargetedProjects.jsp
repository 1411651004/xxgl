<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <div class="layui-form" style="background-color:#fff;min-width: 1280px;margin:2%;margin-bottom:0px;padding:15px 20px">
<form id="idForm" name="idForm" class="layui-form" action="">

<table class="layui-table" lay-even lay-skin="line" style="">
  <colgroup>
    <col width="4%">
    <col width="17%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="8%">
	<col width="8%">
	<col width="10%">
	<col width="13%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th >项目名称</th>
      <th nowrap>项目编码</th>
      <th nowrap>承兑报价(元)</th>
      <th nowrap>现金报价(元)</th>
      <th nowrap>运费报价(元)</th>
      <th nowrap>状态</th>
      <th >联系人</th>
      <th >联系电话</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${contractList}" var="c" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td><a style="text-decoration: underline;" href = "<%=basePath%>project/proinfo?returnpath=com/accProjects&id=${c.offer.project.id}" >${c.offer.project.proName}</a></td>
					<td>${c.project.proCode}</td>
					<td>${c.offer.acceOffer}</td>
					<td>${c.offer.cashOffer}</td>
					<td>${c.offer.freOffer}</td>
					<td>
						<c:if test="${c.offer.project.proStatus eq '0'}">新建</c:if>
						<c:if test="${c.offer.project.proStatus eq '1'}">竞价中</c:if>
						<c:if test="${c.offer.project.proStatus eq '2'}">已结束</c:if>
					</td>
					<td>${c.offer.project.conName}</td>
					<td>${c.offer.project.conPhone}</td>
					<td>
						<a class="layui-btn layui-btn-small" href="contract/downloadContractFile?fileName=${fn:substringBefore(c.filePath, '#')}">PDF格式</a>
						<a class="layui-btn layui-btn-small" href="contract/downloadContractFile?fileName=${fn:substringAfter(c.filePath, '#')}">DOCX格式</a>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
