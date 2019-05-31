<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bakList.jsp' starting page</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
  </head>
  
  <body>
   <form class="layui-form" action="" id="searchForm" name="searchForm">
    <blockquote class="layui-elem-quote" style="background-color:#fff;margin-top:2%;margin-bottom:1%;margin: 2%;min-width: 1280px;">
  <div class="layui-form-item" style="margin-bottom: 0px;">
     <div class="layui-inline">
      <label class="layui-form-label">模板名称</label>
      <div class="layui-input-inline">
      <input type="hidden" id="pageNum" name="pageNum">
        <input id="templateNameQuery" name="templateNameQuery" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">创建时间</label>
      <div class="layui-input-inline">
        <input  onclick="layui.laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',elem: this})" type="text" lay-verify="" name="queryDate1" id="queryDate1" placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input">
      </div>
      <div class="layui-form-mid">--</div>
      <div class="layui-input-inline">
        <input onclick="layui.laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',elem: this})" type="text" lay-verify="" name="queryDate2" id="queryDate2" placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" id="search">
    	<div style="width:auto;" class="layui-input-inline">
      		<button class="layui-btn" lay-submit=""  lay-filter="formDemo">查询</button>
      		<a class="layui-btn" href="javascript:newtemplate()" >新建</a>
      	</div>
    </div>
  </div>
</blockquote>
</form>
<div id="dataList">
			<jsp:include page="getList.jsp" />
</div>

 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="template/getList" />
</jsp:include>
  </body>
  <script>
  function delTemplate(id){
  layui.use(['jquery','layer'], function(){
   var $=layui.jquery;
   var layer = layui.layer;
  		$.ajax({
		url:"<%=basePath %>template/del",
		dataType:"text",
		type:"post",
		data:{id:id},
		success:function(data){
			//$("#"+pfId).remove();
			layer.closeAll('loading');
			layer.msg(data);
			goPage(currpageno);
		},
		error:function(data){
			layer.closeAll('loading');
			layer.msg("出错");
		}
	
	});
	});
  }
  function newtemplate(){
  	window.location.href="<%=basePath %>template/add";
  }
  layui.use(['form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 var searchFormName='searchForm';
 setTimeout(function(){form.render('checkbox');}, 300);
 
 form.on('checkbox(ckall)', function(data){
	var group = $(data.elem).attr("checkAll");
	if(data.elem.checked){
		$("[group='"+group+"']").prop("checked",true);
	}else{
		$("[group='"+group+"']").prop("checked",false);
	}
	form.render('checkbox');
});
 form.on('checkbox(ckone)', function(data){
 	var group = $(data.elem).attr("group");
	var checkAll = true;
	if($("[group='"+group+"']").length != $("[group='"+group+"']:checked").length){
		checkAll = false;
	}
	if(checkAll){
		$("[checkAll='"+group+"']").prop("checked", true);
	}else{
		$("[checkAll='"+group+"']").prop("checked", false);
	}
	form.render('checkbox');
});
 
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $.ajax({
		url:"<%=basePath %>template/getList",
		dataType:"text",
		type:"post",
		data:$("#"+searchFormName+", form[name='"+searchFormName+"']").serialize(),
		success:function(data){
			//$("#"+pfId).remove();
			layer.closeAll('loading');
			$("#"+tId).html(data);
			setTimeout(function(){form.render('checkbox');}, 300);
		},
		error:function(data){
			layer.closeAll('loading');
			layer.msg("出错");
		}
	
	});
	 return false;
 });

});

  </script>
</html>
