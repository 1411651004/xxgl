<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.guochen.model.TUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <title>审批设置</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/personal.css" media="all">
	<script type="text/JavaScript" src="<%=basePath%>larrycms-master/common/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff">
<header class="larry-personal-tit">
			<span>审批设置</span>
		</header>
<form class="layui-form layui-form-pane" style="margin-left: 4%;margin-right: 4%;">
	<div class="layui-form-item">
			<input type="button" class='layui-btn layui-btn-small' onclick="tj()" value="添加">
	</div>
	<div id='spdiv'>
		<c:forEach begin="1" items="${nodeList }" varStatus="vs" var = "node">
		<div class='layui-form-item' >
			<div class='layui-inline'>
				<label class='layui-form-label' name="csid">${vs.count }</label>
				<div class='layui-input-inline' style='width: 100px;margin-right:-1px;'>
			      <input type='text' name='price_min' readonly = "readonly" value='第${vs.count }级审批' class='layui-input'>
			    </div>
				<label class='layui-form-label'>审批人</label>
				<div class='layui-input-inline' style='width: 200px;'>
			      <select name="city" lay-verify="" lay-search>
				      <c:forEach items="${userList }" var = "tmp">
				      	<c:if test="${node.userId == tmp.id }">
				        	<option value="${tmp.id }" selected >${tmp.loginName }</option>
				      	</c:if>
				      	<c:if test="${node.userId != tmp.id }">
				        	<option value="${tmp.id }" >${tmp.loginName }</option>
				      	</c:if>
				        </c:forEach>
					</select>  
			    </div>
			    <input type='button' class='layui-btn layui-btn-small' onclick='del(this)' value='删除'>
			</div>
		</div>
		</c:forEach>
	</div>
<div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
      <button type="layui-btn" onclick="location.reload()" class="layui-btn layui-btn-primary">重置</button>
    </div>
    </div>
    <br>
    <br>
    <br>
</form>
</body>
<script>
var cs=1;
function showtree(){
	layui.use(['jquery','layer'], function(){
		var $ = layui.jquery;
		var layer = layui.layer;
		//页面层-自定义
		layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  shadeClose: true,
		  skin: 'layui-layer-rim', //加上边框
		  content: $("#treediv").html()
		});
	});
}
function del(obj){
	$(obj).parent().remove();
	var i = 1;
	$("[name='csid']").each(function(){
        $(this).html(i);
        i++;
    });
    cs--;
}
function tj(){
	layui.use(['jquery','layer','form'], function(){
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form();
		cs++;
		$("#spdiv").append("<div class='layui-form-item' ><div class='layui-inline'> <label name='csid' class='layui-form-label'>"+cs+"</label> <div class='layui-input-inline' style='width: 100px;margin-right:-1px;'> <input type='text' name='price_min' value='第"+cs+"级审批'  class='layui-input'> </div> <label class='layui-form-label'>审批人</label> <div class='layui-input-inline' style='width: 200px;'>"+ 
		"<select name='city' lay-verify='' lay-search>"+
		<%
			List list = (List)request.getAttribute("userList");
			for(int i=0;i<list.size();i++){
				TUser user = (TUser)list.get(i);
				%>
				"<option value='<%=user.getId()%>'><%=user.getLoginName()%></option>"+
		<%	}
		%>
		"</select>"+
		"</div> <input type='button' class='layui-btn layui-btn-small' onclick='del(this)' value='删除'> </div></div>");
		form.render('select');
	});
}

var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
layui.use([ 'form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
        var str ="";
		$("[name='city']").each(function(){
			str+=$(this).val()+",";
	    });
	 console.log(str);
	 $.ajax({
         cache: true,
         type: "POST",
         url:basePath+"/node/save?param="+str,
         data:data.field,// 你的formid
         async: false,
         error: function(request) {
        	 layer.closeAll('loading');
             layer.msg("Connection error");
         },
         success: function(data) {
        	 layer.closeAll('loading');
        	 layer.msg(data);
            	return "";
         }
     });
	 return false;
 });

});

</script>
</html>        
        
