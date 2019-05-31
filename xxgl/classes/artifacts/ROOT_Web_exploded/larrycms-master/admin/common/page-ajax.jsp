<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.guochen.page.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	
	String pageName = request.getParameter("pageName");
	if(pageName==null){
		pageName = "page";
	}
	
	Page page1 = (Page) request.getAttribute(pageName);
	String url = request.getParameter("url"); 
	System.out.print(page1.getTotalPage());
	//String url = "project/getList"; 
%>
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
<%-- <script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script>  --%>

<div style="background-color:#fff;margin: 2%;margin-top:0px;min-width: 1140px;">
<div id="demo3" style="background-color: white;padding:0px 20px;padding-bottom:10px;min-width: 1140px;"></div>
</div>
<script>

function goPage(curr){
	layui.use(['laydate','laypage', 'layer','element','jquery','form'], function(){
		  var laypage = layui.laypage
		  ,layer = layui.layer;
		  var form = layui.form();
			var $ = layui.jquery;
    //得到了当前页，用于向服务端请求对应数据
    var url ='<%=url%>';
    var searchFormName='searchForm';
	$("#"+searchFormName).find("input[name='pageNum']").val(curr);
	tId = "dataList";
	$.ajax({
		url:"<%=basePath %>"+url,
		//async : true, 
		dataType:"text",
		type:"post",
		data:$("#"+searchFormName+", form[name='"+searchFormName+"']").serialize(),
		success:function(data){
			//$("#"+pfId).remove();
			$("#"+tId).html(data);
		},
		error:function(data){
			alert("出错");
		}
	
	});
	setTimeout(function(){form.render('checkbox');}, 300);
	});
  }
 var currpageno;
 layui.use(['laydate','laypage', 'layer','element','jquery','form'], function(){
	  var laypage = layui.laypage
	  ,layer = layui.layer;
		var $ = layui.jquery;
		var form = layui.form();
		var searchFormName='searchForm';
		
	  laypage({
		    cont: 'demo3'
		    ,pages:<%=page1.getTotalPage()%>
		    ,first:1
		    ,last:<%=page1.getTotalPage()%>
		    ,prev: '<em><</em>'
		    ,next: '<em>></em>'
		    ,curr:<%=page1.getPageNum()%>
		    ,skip: true
	    	,jump: function(obj, first){
	    		if(!first){
			      //do something
		        //得到了当前页，用于向服务端请求对应数据
		        var curr = obj.curr;
		        var url ='<%=url%>';
				$("#"+searchFormName).find("input[name='pageNum']").val(curr);
				tId = "dataList";
				$.ajax({
					url:"<%=basePath %>"+url,
					//async : true, 
					dataType:"text",
					type:"post",
					data:$("#"+searchFormName+", form[name='"+searchFormName+"']").serialize(),
					success:function(data){
						//$("#"+pfId).remove();
						$("#"+tId).html(data);
						//console.log(data);
						currpageno=curr;
						
					},
					error:function(data){
						layer.msg("请勿重复刷新页面");
					}
				
				});
				setTimeout(function(){form.render('checkbox');}, 300);
				}
		      }
		  });
	  
	  
}); 
</script>