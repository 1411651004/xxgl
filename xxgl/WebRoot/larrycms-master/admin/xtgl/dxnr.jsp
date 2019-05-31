<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="com.guochen.model.Xtgl" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>短信内容设置</title>
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
</head>
<body>
<section class="larry-grid">
	<div class="larry-personal">
		<header class="larry-personal-tit">
			<span>短信内容设置</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix changepwd">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
			        <div class="layui-form-item">
				    <label class="layui-form-label">短信内容</label>
				    <div class="layui-input-block" style="margin-left:120px">
				      <textarea  id="remindCon" rows="6" style="font-size:16px;" cols="75">${dxnr }</textarea>
				    </div>
				  </div>
					
					<div class="layui-form-item change-submit" style="width:388px;margin-left:466px;">
						<div class="layui-input-block">
							<a class="layui-btn" lay-submit="" lay-filter="formDemo">提交</a>
						</div>
					</div>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script>
<script type="text/javascript">
var isok="${dxnr }";
var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
function doSubmit(){
	layui.use(['form','laydate','upload','element','jquery','layer'], function(){
	var $ = layui.jquery;
	var layer = layui.layer;
	var isok = $("#remindCon").val();
		$.ajax({
				url:"http://"+window.location.host+'/xtgl/update',
				//async : true, 
				dataType:"json",
				type:"post",
				data:{
					id:2,
					code:'dxnr',
					codevalue:isok
				},
				success:function(data){
					if(data==1){
						layer.msg("操作成功");
					}else{
						layer.msg("出错");
					}
				},
				error:function(data){
					layer.msg("出错");
				}
		});
	});
}
layui.use(['upload','jquery','layer','form'], function(){
var form = layui.form();
var layer = layui.layer;
var $ = layui.jquery;
//监听提交
 form.on('submit(formDemo)', function(data){
	 doSubmit();
 });
 form.on('radio(sqtk)', function(data){
  isok=data.value; //被点击的radio的value值
}); 
$("input[name='sqtk'][value='"+isok+"']").attr("checked",true); 
form.render();
 });
</script>
</body>
</html>