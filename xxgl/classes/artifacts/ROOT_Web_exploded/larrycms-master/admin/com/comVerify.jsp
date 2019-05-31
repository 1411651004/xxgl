<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'comVerify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>larrycms-master/admin/css/common.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>larrycms-master/admin/css/personal.css" media="all">
	<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
  </head>
  <style>
  .layui-input-inline{
  	width:280px!important;
  }
  </style>
  <body>
    <section class="larry-grid">
	<div class="larry-personal">
		<header class="larry-personal-tit">
			<span>${com.comName}</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix">
			<form class="layui-form col-lg-5" action="" method="post">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">开户行</label>
						<div class="layui-input-inline">  
							<input type="text" name="title"  autocomplete="off"  class="layui-input layui-disabled" value="${com.bankAccount}" disabled="disabled" >
						</div>
						<label class="layui-form-label">税号</label>
						<div class="layui-input-inline">  
							<input type="text" name="title"  autocomplete="off"  class="layui-input layui-disabled" value="${com.taxreceNo}" disabled="disabled" >
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">银行账号</label>
						<div class="layui-input-inline">  
							<input type="text" name="title"  autocomplete="off"  class="layui-input layui-disabled" value="${com.account}" disabled="disabled" >
						</div>
						<label class="layui-form-label">公司地址</label>
						<div class="layui-input-inline">  
							<input type="text" name="title"  autocomplete="off"  class="layui-input layui-disabled" value="${com.comAddr}" disabled="disabled" >
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">公司电话</label>
						<div class="layui-input-inline">  
							<input type="text" name="title"  autocomplete="off"  class="layui-input layui-disabled" value="${com.comPhone}" disabled="disabled" >
						</div>
						<label class="layui-form-label">经营范围</label>
						<div class="layui-input-inline">  
							<input type="text" name="title"  autocomplete="off"  class="layui-input layui-disabled" value="${com.alternateField1}" disabled="disabled" >
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">附件</label>
						<c:forEach items="${fileList}" var="f" varStatus="sta">
							<c:if test="${sta.index eq 0 }">
								<div style="width:190px!important;" class="layui-input-inline">
							</c:if>
							<c:if test="${sta.index ne 0 }">
								<div style="width:190px!important; margin-left:20px;" class="layui-input-inline">
							</c:if>
							<a class="layui-btn"  href="<%=basePath %>uploadFile/com/${f}">${fn:substring(f,0,fn:length(f)-6)}</a>
						</div>
						</c:forEach>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">证件</label>
						<div style="width:83px!important;" class="layui-input-inline">
							<a class="layui-btn" src="" href="javascript:showProjectImage('<%=basePath%>/uploadFile/com/${com.businessLicensePath}')">营业执照</a>
						</div>
						<div style="width:98px!important; margin-left:20px;" class="layui-input-inline">
							<a class="layui-btn" src="" href="javascript:showProjectImage('<%=basePath%>/uploadFile/com/${com.taxrecePath}')">税务登记证</a>
						</div>
						<div style="width:125px!important; margin-left:20px;" class="layui-input-inline">	
							<a class="layui-btn" src="" href="javascript:showProjectImage('<%=basePath%>/uploadFile/com/${com.orcodePath}')">组织机构代码证</a>
						</div>
						<div style="width:83px!important; margin-left:20px;" class="layui-input-inline">	
							<a class="layui-btn" src="" href="javascript:showProjectImage('<%=basePath%>/uploadFile/com/${com.ceauPath}')">授权书</a>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<a class="layui-btn" href="javascript:doVerifyCom('${com.id}','1','<%=basePath%>com/verifyList')" >审核通过</a>
						<a class="layui-btn" href="javascript:doVerifyCom('${com.id}','2','<%=basePath%>com/verifyList')" >审核不通过</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript">
//审核供应商
function doVerifyCom(id,sta,dataUrl){
layui.use(['jquery','layer'], function(){
var $ = layui.jquery;
var layer = layui.layer;
	if(sta=="2"){
			layer.prompt({title: '请输入审核不通过的原因'},function(val, index){
		  		layer.close(index);
		  		$.ajax({
						url:'com/doVerifyCom',
						//async : true,
						dataType:"json",
						type:"post",
						data:{
							id:id,
							comType:sta,
							unpassReason:val
						},
						success:function(data){
							if(data.code==1){
								layer.msg("审核成功");
								setTimeout(function(){location.href=dataUrl;},800);
								
							}else{
								layer.msg(data.msg);
							}
						},
						error:function(data){
							layer.msg("出错");
						}
					});
		  	});
			return;
	}
	if(sta=="1"){
		$.ajax({
						url:'com/doVerifyCom',
						//async : true,
						dataType:"json",
						type:"post",
						data:{
							id:id,
							comType:sta
						},
						success:function(data){
							if(data.code==1){
								layer.msg("审核成功");
								setTimeout(function(){location.href=dataUrl;},800);
								
							}else{
								layer.msg(data.msg);
							}
						},
						error:function(data){
							layer.msg("出错");
						}
					});
	}
	
	
	});
}
function showProjectImage(src){
layui.use(['form','upload','layer'],function(){
var layer = layui.layer;
	var json = {
			  "title": "", //相册标题
			  "id": 123, //相册id
			  "start": 0, //初始显示的图片序号，默认0
			  "data": [   //相册包含的图片，数组格式
			    {
			      "alt": "图片名",
			      "pid": 666, //图片id
			      "src": src, //原图地址
			      "thumb": "" //缩略图地址
			    }
			  ]
			};
	
		layer.photos({
		    photos: json //格式见API文档手册页
		    ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
		  });
	});
}
	layui.use(['form','upload'],function(){
         var form = layui.form();
         layui.upload({ 
             url: '' ,//上传接口 
             success: function(res){
              //上传成功后的回调 
              console.log(res) 
            } 
         });

	});
</script>
  </body>
</html>
