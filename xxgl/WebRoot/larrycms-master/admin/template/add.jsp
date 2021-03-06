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
	<title>个人信息</title>
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
			<span>新增合同模板</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix changepwd">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">模板名称</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="templateName" id="templateName" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					<input type="hidden" id="templatePath" name="templatePath">
					    <label class="layui-form-label">模板文件</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="file" name="file1" lay-type="file" class="layui-upload-file">
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

function doSubmit(){
	layui.use(['form','laydate','upload','element','jquery','layer'], function(){
	var $ = layui.jquery;
	var layer = layui.layer;
		$.ajax({
                cache: true,
                type: "POST",
                url:basePath+"/template/save",
                data:{
                	templatePath:$("#templatePath").val(),
                	templateName:$("#templateName").val(),
                	id:$("#id").val(),
                	createTime:$("#createTime").val()
                },
                async: false,
                error: function(request) {
                    layer.msg("Connection error");
                },
                success: function(data) {
                   	layer.msg("保存成功");
                   	$("#fanhui").click();
                }
            });
	});
}
var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
layui.use(['upload','jquery','layer','form'], function(){
var form = layui.form();
//监听提交
 form.on('submit(formDemo)', function(data){
	 doSubmit();
 });
var layer = layui.layer;
var $ = layui.jquery;
	  layui.upload({
	    url: basePath+'/template/fileUpload2json' //上传接口
	    ,before:function(input){
	    	var filesize = $(input)[0].files[0].size;
	    	if(filesize>10485760){
	    		layer.msg('文件大小必须小于10MB');
	    		return false;
	    	}
    	layer.load(2);
    }
    ,success: function(res){ //上传成功后的回调0-成功1-失败
    	if(res.code==0){
    		$("#templatePath").val(res.filename);
	    	layer.closeAll('loading');
	    	layer.msg(res.msg);
    	}else if(res.code!=0){
    		layer.closeAll('loading');
    		layer.msg(res.msg);
    	}
    }
  });
 });
</script>
</body>
</html>