<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>采购商信息</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/bootstrap/css/bootstrap.min.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/common.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/personal.css" media="all">
	<script type="text/JavaScript" src="<%=basePath%>larrycms-master/common/js/jquery-1.12.4.min.js"></script>

</head>
<body>
<section class="larry-grid">
	<div class="larry-personal">
		<header class="larry-personal-tit">
			<span>
			<c:if test="${!empty code }">
			修改结算方式
			</c:if>
			<c:if test="${empty code }">
			新增结算方式
			</c:if>
			
			</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix changepwd">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">结算方式</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					    	<input type="hidden" name="id" id="id" value="${requestScope.code.id }">
					    	<input type="hidden" name="codeType" id="codeType" value="seme">
					      <input type="text" name="codeName" id="codeName" required value="${requestScope.code.codeName }" lay-verify="required"  autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">实际值</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="codeValue" id="codeValue" required value="${requestScope.code.codeValue }" lay-verify="required"  autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item change-submit" style="width:388px;margin-left:466px;">
						<div class="layui-input-block">
							<a class="layui-btn" lay-submit="" lay-filter="formDemo">提交</a>
							<a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" ><span id="fanhui">返回</span></a>
						</div>
					</div>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script>
<script type="text/javascript">
var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
layui.use(['upload','jquery','layer','form'], function(){
var form = layui.form();
var layer = layui.layer;
var $ = layui.jquery;
	//监听提交
	 form.on('submit(formDemo)', function(data){
	 var url="";
	 if($("#id").val()==""){
	 	url=basePath+"/code/saveSeMe";
	 }else{
	 	url=basePath+"/code/updateSeMe";
	 }
	 	$.ajax({
				url:url,
				dataType:"json",
				type:"post",
				data:data.field,
				success:function(data){
					if(data=="1"){
						layer.msg("操作成功");
						$("#fanhui").click();
					}
				},
				error:function(data){
					layer.msg("出错");
				}
		});
		 
	 });
 });
</script>
</body>
</html>