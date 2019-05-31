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
    <col width="4%">
    <col width="17%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="8%">
	<col width="8%">
	<col width="10%">
	<col width="5%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th nowrap>项目名称</th>
      <th nowrap>项目编码</th>
      <th nowrap>承兑报价(元)</th>
      <th nowrap>现金报价(元)</th>
      <th nowrap>运费报价(元)</th>
      <th nowrap>状态</th>
      <th nowrap>联系人</th>
      <th nowrap>联系电话</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${offerList}" var="o" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td><a style="text-decoration: underline;" href = "<%=basePath%>project/proinfo?returnpath=com/offeredProjects&id=${o.project.id}" >${o.project.proName}</a></td>
					<td>${o.project.proCode}</td>
					<td>${o.acceOffer}</td>
					<td>${o.cashOffer}</td>
					<td>${o.freOffer}</td>
					<td>
						<c:if test="${o.project.proStatus eq '0'}">新建</c:if>
						<c:if test="${o.project.proStatus eq '1'}">竞价中</c:if>
						<c:if test="${o.project.proStatus eq '2'}">已结束</c:if>
					</td>
					<td>${o.project.conName}</td>
					<td>${o.project.conPhone}</td>
					<td>
						<c:if test="${o.project.proStatus eq '1'}">
							<a href="<%=basePath %>com/preOffer?proId=${o.project.id}&type=1" class="layui-btn layui-btn-small" onClick="show_menu_nocss(this,'com/preOffer?proId=${o.project.id}&type=1')">重新报价</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
