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
    <col width="10%">
    <col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="5%">
	<col width="5%">
	<col width="5%">
	<col width="5%">
	<c:if test='${record.sta == "1" }'>
      	<col width="5%">
      </c:if>
	<c:if test='${record.sta == "2" }'>
      	<col width="5%">
      	<col width="5%">
      </c:if>
	<c:if test='${record.sta == "3" }'>
      	<col width="5%">
      </c:if>
  </colgroup>
  <thead>
    <tr>
      <th nowrap><input lay-filter="ckall" id="ckall" type="checkbox" title="全选" checkAll="ids" lay-skin="primary"></th>
      <th nowrap>季度</th>
      <th nowrap>窗口</th>
      <th nowrap>企业</th>
      <th nowrap>编号</th>
      <th nowrap>姓名</th>
      <th nowrap>学校</th>
      <th nowrap>手机号</th>
      <th nowrap>年级</th>
      <th nowrap>备注</th>
	  <c:if test='${record.sta == "1" }'>
      	<th nowrap>操作</th>
      </c:if>
	  <c:if test='${record.sta == "2" }'>
      	<th nowrap>退款状态</th>
      	<th nowrap>操作</th>
      </c:if>
	  <c:if test='${record.sta == "3" }'>
      	<th nowrap>操作</th>
      </c:if>
    </tr> 
  </thead>
<tbody>
<c:forEach items="${xsxxList}" var="xsxx" >
    <tr>
      <td nowrap><input type="checkbox" lay-filter="ckone" sta="${xsxx.sta}" idnum="${xsxx.id }" value="${xsxx.dh }" sfz="${xsxx.sfz }" group="ids" name="ids" lay-skin="primary"></td>
      <td nowrap>${xsxx.jd }</td>
      <td nowrap>${xsxx.byzd3 }</td>
      <td nowrap>${xsxx.byzd2 }</td>
      <td nowrap>${xsxx.dlbh }</td>
      <td nowrap>${xsxx.xm }</td>
      <td nowrap>${xsxx.xx }</td>
      <td nowrap>${xsxx.dh }</td>
      <td nowrap>${xsxx.nj }</td>
      <td nowrap>${xsxx.qtbz }</td>
      <c:if test='${xsxx.sta == "10" }'>
      	<td nowrap>请发送短信提醒</td>
      </c:if>
      <c:if test='${xsxx.sta == "11" }'>
      	<td nowrap>等待二次录入</td>
      </c:if>
      <c:if test='${xsxx.sta == "1" }'>
      	<td nowrap><a class="layui-btn layui-btn-small" href="javascript:sh('${xsxx.id }')" >审核</a></td>
      </c:if>
      <c:if test='${xsxx.sta == "2" }'>
      	<td nowrap>
      		<c:if test="${xsxx.kftk == '0'}">允许退款</c:if>
      		<c:if test="${xsxx.kftk == '1'}">不允许退款</c:if>
      		<c:if test="${xsxx.kftk == '2'}">已申请退款</c:if>
      	</td>
      	<td nowrap><a class="layui-btn layui-btn-small" href="javascript:ck('${xsxx.id }')" >修改</a>&nbsp;&nbsp;
      	<c:if test="${xsxx.kftk!='2' }">
      	<a class="layui-btn layui-btn-small" href="javascript:kftk('${xsxx.id }')" >
      	<c:if test="${xsxx.kftk=='0' }">关闭退款</c:if>
      	<c:if test="${xsxx.kftk=='1' }">开放退款</c:if>
      	</a>
      	</c:if>
      	</td>
      </c:if>
      <c:if test='${record.sta == "3" }'>
      	<td nowrap>
      		<a class="layui-btn layui-btn-small" href="javascript:ck('${xsxx.id }')" >查看</a>
      		<a class="layui-btn layui-btn-small" href="javascript:exp('${xsxx.id }')" >导出文件</a>
      	</td>
      </c:if>
    </tr>
</c:forEach>
  </tbody>
</table>
</form>
 </div>
 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="xsxx/getList?sta=${record.sta}" />
</jsp:include>