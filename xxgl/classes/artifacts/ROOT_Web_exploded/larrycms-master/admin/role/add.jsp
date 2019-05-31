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
	<script type="text/JavaScript" src="<%=basePath%>larrycms-master/common/js/jquery-1.12.4.min.js"></script>
	<script type="text/JavaScript" src="<%=basePath%>larrycms-master/common/js/jquery.ztree.all.min.js"></script>
	<link rel="stylesheet" href="<%=basePath%>larrycms-master/common/css/zTreeStyle/zTreeStyle.css">
	<script type="<%=basePath%>larrycms-master/common/js/jquery.ztree.core.js"></script>
	<script type="<%=basePath%>larrycms-master/common/js/jquery.ztree.excheck.js"></script>
</head>
<body>
<section class="larry-grid">
	<div class="larry-personal">
		<header class="larry-personal-tit">
			<span>
			<c:if test="${!empty role }">
			修改角色
			</c:if>
			<c:if test="${empty role }">
			新增角色
			</c:if>
			
			</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix changepwd">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">角色名称</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					    	<input type="hidden" name="id" id="id" value="${requestScope.role.id }">
					      <input type="text" name="roleName" id="roleName" required value="${requestScope.role.roleName }" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">角色授权</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <div style="width: 500px;height: 400px">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
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
basePath="http://"+window.location.host;
function updateData(authId) {
		var requestUrl = "";
		requestUrl = basePath+"/roleauthority/saveOrDelete";
		$.ajax({
			url : requestUrl,
			dataType : "json",
			type : "post",
			data : {
				authorityId:authId,
				roleId:$("#roleId").val()
			},
			success : function(data) {
				if (data == 1) {
					
				} else {
					alert("出错");
				}
			},
			error : function(data) {
				alert("出错");
			}
		});
	}
function onCheck(e, treeId, treeNode) {
			//alert(treeNode.id+"/"+treeNode.pId+"/"+treeNode.name);
			updateData(treeNode.id);
	}
var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				//onCheck: onCheck
			}
	};

	var zNodes =${json4Ztree};
	var code;
function doSubmit(){
	layui.use(['form','laydate','upload','element','jquery','layer'], function(){
	var $ = layui.jquery;
	var layer = layui.layer;
		$.ajax({
				url:basePath+'/user/save',
				//async : true, 
				dataType:"json",
				type:"post",
				data:{
					loginName:$("#loginNameStr").val(),
					roleId:$("#roleId").val(),
					alternateField1:$("#alternateField1").val()
				},
				success:function(data){
					if(data==1){
						layer.msg("操作成功");
						setTimeout("self.location=document.referrer;",1500);
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

var t;
layui.use(['upload','jquery','layer','form'], function(){
var form = layui.form();
var layer = layui.layer;
var $ = layui.jquery;
//监听提交
 form.on('submit(formDemo)', function(data){
	 	$.ajax({
				url:basePath+"/role/update",
				dataType:"json",
				type:"post",
				data:{
					roleName:$("#roleName").val(),
					id:$("#id").val()
				},
				success:function(data){
					if(data!=0){
						 var sNodes = t.getCheckedNodes(true);
						 var str = "";
						 for(var i=0;i<sNodes.length;i++){
						 	str+=sNodes[i].id;
						 	if(i!=sNodes.length-1){
						 		str+="|";
						 	}
						 }
						 $.ajax({
								url : basePath+"/roleauthority/deleteAndSave",
								dataType : "json",
								type : "post",
								data : {
									str:str,
									roleId:data
								},
								success : function(data) {
									if (data == 0) {
										layer.msg("操作成功");
										setTimeout("self.location=document.referrer;",1500);
										
									} else {
										layer.msg("出错");
									}
								},
								error : function(data) {
									layer.msg("出错");
								}
							});
					}else{
						layer.msg("出错");
					}
				},
				error:function(data){
					layer.msg("出错");
				}
		});
	 
 });
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
	t = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	type = { "Y":"ps", "N":"ps"};
	zTree.setting.check.chkboxType = type;
	var str = 'setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };';
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
	
 });
function showCode(str) {
	
}
</script>
</body>
</html>