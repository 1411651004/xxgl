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
    <col width="6%">
	<col width="50%">
	<col width="30%">
	<col width="28%">
  </colgroup>
  <thead>
    <tr>
      <th nowrap>序号</th>
      <th >文件名称</th>
      <th nowrap>接收时间</th>
      <th nowrap>操作</th>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${list}" var="file" varStatus="sta">
				<tr>
					<td>${sta.index+1}</td>
					<td>${fn:substring(file.fileName,0,fn:length(file.fileName)-6)}</td>
					<td>${file.time}</td>
					<td>
						<a class="layui-btn layui-btn-small" href="down/downloadproject?fileName=${file.fileName}" >下载</a>
					</td>
				</tr>
			</c:forEach>
  </tbody>
</table>
</form>
 </div>
