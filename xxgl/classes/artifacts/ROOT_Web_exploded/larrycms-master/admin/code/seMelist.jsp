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
      <div class="layui-input-inline">
      <input type="hidden" id="pageNum" name="pageNum">
      </div>
    </div>
    <div class="layui-inline" id="search" style="float: right;margin-right: 200px;">
    	<div style="width:auto;" class="layui-input-inline">
      		<a class="layui-btn" href="javascript:newcode()" >新建</a>
      	</div>
    </div>
  </div>
</blockquote>
</form>
<div id="dataList">
			<jsp:include page="getSeMeList.jsp" />
</div>

 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="code/getSeMeList" />
</jsp:include>
  </body>
  <script>
  function newcode(){
  	window.location.href="<%=basePath %>code/add?codeId=0";
  }
  function updatecode(id){
  	window.location.href="<%=basePath %>code/add?codeId="+id;
  }
  function deletecode(id){
  layui.use(['form','laydate','element','jquery','layer'], function(){
	var $ = layui.jquery;
	var layer = layui.layer;
  		 $.ajax({
				url:"<%=basePath %>code/del",
				dataType:"text",
				type:"post",
				data:{"codeId":id},
				success:function(data){
					if(data==1){
						layer.msg("操作成功");
						goPage(currpageno);
					}
				},
				error:function(data){
					layer.closeAll('loading');
					layer.msg("出错");
				}
			
			});
	});
  }
  layui.use(['form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 var searchFormName='searchForm';
 setTimeout(function(){form.render('checkbox');}, 300);
 
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $.ajax({
		url:"<%=basePath %>buyer/getList",
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
