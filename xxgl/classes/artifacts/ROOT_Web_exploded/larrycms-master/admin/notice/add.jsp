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
<style>
.zs_right_main { background-color:#FFF; margin:2%;overflow: hidden;padding: 2%;}
.jbxx_inw_jd {
    width: 68%;
    margin: 2% 0.5% 2% 0;
}
.xmlist_inw {
    height: 2rem;
    background-color: #FFF;
    padding-left: 10px;
    color: #999;
    border: #ccc 1px solid;
    border-radius: 5px;
}
.jb_blue {
    color: #FFF;
    background-color: #61a4ea;
}
.but_ll {
    text-align: center;
    height: 2rem;
    line-height: 2rem;
    border-radius: 5px;
    padding: 0 1.5%;
    border: 0;
    font-size: 16px;
}
.jb_jh {
    color: #FFF;
    background-color: #ff824e;
}
.tjjd_t{
	font-family: "微软雅黑", Arial,Verdana, sans-serif, Helvetica;
    font-size: 14px;
    color: #444;
}
</style>
<!-- 百度编辑器 -->
<script type="text/javascript" charset="utf-8" src="<%=basePath %>larrycms-master/common/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>larrycms-master/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>larrycms-master/common/ueditor/ueditor.all.js"> </script>
<body>
<section class="larry-grid">
	<div class="larry-personal">
		<header class="larry-personal-tit">
			<span>新建公告</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix changepwd">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">公告标题</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="notice_title" id="notice_title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
						<div class="layui-inline">
					    <label class="layui-form-label">公告内容</label>
						    <div class="layui-input-inline">
								<div id="noticeCon" style="width: 630px;height: 300px;"></div>
							</div>
						</div>
					</div>
					<div class="layui-form-item change-submit" style="width:388px;margin-left:466px;">
						<div class="layui-input-block">
							<a class="layui-btn" href="javascript:doSubmit()" >提交</a>
							<a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" >返回</a>
						</div>
					</div>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script>
<script type="text/javascript">
$(function(){
	createUE();
});
function createUE(){
	UE.getEditor("noticeCon", {
		toolbars: [
		    [
		    	'italic', //斜体
		        'underline', //下划线
		        'strikethrough', //删除线
		        'forecolor', //字体颜色
	            'backcolor', //背景色
	    		'formatmatch', //格式刷
	            'subscript', //下标
	            'superscript', //上标
	            'insertorderedlist', //有序列表
	            'insertunorderedlist', //无序列表
	            'justifyleft', //居左对齐
	            'justifyright', //居右对齐
	            'justifycenter', //居中对齐
	            'justifyjustify', //两端对齐
		        'fontfamily', //字体
		        'fontsize', //字号
		        'emotion', //表情
		        'simpleupload', //单图上传
		        //'insertvideo', //视频
		        'preview' //预览
			]
		]
		,wordCount: false //是否启用元素路径，默认是显示
		,elementPathEnabled: false //是否开启字数统计
		,autoHeightEnabled: false //是否显示滚动条
		,iframeCssUrl: "<%=basePath %>larrycms-master/common/ueditor/themes/iframe.css" //给编辑区域的iframe引入一个css文件
		,zIndex : 900000     //编辑器层级的基数,默认是900
	    ,autoFloatEnabled: false //是否保持toolbar的位置不动,默认true
	});
}

function doCancel(){
	$.ajax({
        type: "POST",
        url: '<%=basePath%>notice/list',
        async: true,
        dataType: "text",
        success: function(data){
        	$("#RightBox", parent.document).html(data);
        }
    });
}

function doSubmit(){
layui.use(['form','laydate','upload','element','jquery','layer'], function(){

	var noticeTitle = $("#notice_title").val();
	//var noticeCon = $("#noticeCon").val();
	var noticeCon = UE.getEditor('noticeCon').getContent();
	if(noticeTitle==""){
		layer.msg("请输入公告标题");
		return;
	}
	if(noticeCon==""){
		layer.msg("请输入公告内容");
		return;
	}
	$.ajax({
			url:'<%=basePath%>notice/save',
			//async : true, 
			dataType:"json",
			type:"post",
			data:{
				noticeTitle:noticeTitle,
				noticeCon:noticeCon
			},
			success:function(data){
				if(data==1){
					layer.msg("操作成功");
					//show_menu(null,'notice/list');
					window.location.href="<%=basePath%>notice/list";
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

function updateNotice(noticeId,noticeStatus){
	$.ajax({
			url:'notice/update',
			//async : true, 
			dataType:"json",
			type:"post",
			data:{
				id:noticeId,
				noticeStatus:noticeStatus
			},
			success:function(data){
				if(data==1){
					alert("操作成功");
					show_menu(null,'notice/list');
				}else{
					alert("出错");
				}
			},
			error:function(data){
				alert("出错");
			}
	});
}
</script>
</body>
</html>