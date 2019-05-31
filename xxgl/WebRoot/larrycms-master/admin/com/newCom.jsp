<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <title>项目建档</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
<script type="text/javascript" src="<%=basePath %>larrycms-master/layui/layui.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/personal.css" media="all">
<style>
.guanbispan{
margin-left:5px;
}
.layui-form-item .layui-input-inline {
    float: left;
    width: 400px;
    margin-right: 10px;
}
.layui-form-item .layui-input-block {
    margin-left: 150px;
}

</style>
</head>
<body style="background: #fff">
<header class="larry-personal-tit">
			<span>申请合作</span>
		</header>
<form class="layui-form" style="margin-left: 4%;margin-right: 4%;">
	
	<br>
<fieldset class="layui-elem-field">
  <legend style="font-size:16px">企业基本信息</legend>
  <div class="layui-field-box">

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">企业名称</label>
      <div class="layui-input-inline">
        <input name="comName" id="comName" value="${comName}" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">办公电话</label>
      <div class="layui-input-inline">
        <input name="comPhone" id="comPhone" type="text" value="${comPhone}" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
  
  </div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">开户行</label>
      <div class="layui-input-inline">
        <input name="bankAccount" id="bankAccount" value="${bankAccount}" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">税号</label>
      <div class="layui-input-inline">
        <input name="taxreceNo" id="taxreceNo" type="text" value="${taxreceNo}" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
  
  </div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">银行账号</label>
      <div class="layui-input-inline">
        <input name="account" id="account" type="text" value="${account}"  lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">公司地址</label>
      <div class="layui-input-inline">
        <input name="comAddr" id="comAddr" value="${comAddr}" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
  
  </div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input name="conName" id="conName" type="text" value="${conName}" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">联系人电话</label>
      <div class="layui-input-inline">
        <input name="conPhone" id="conPhone" value="${conPhone}" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
  
  </div>

  </div>
</fieldset>
<br>
<fieldset class="layui-elem-field">
  <legend style="font-size:16px">企业资质</legend>
  <div class="layui-field-box">

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">营业执照</label>
      <div class="layui-input-inline" id="yyzztag">
        <button type="button" class="layui-btn layui-btn-small" id="yyzz">
 			上传文件
		</button>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">税务登记证</label>
      <div class="layui-input-inline" id="swdjztag">
        <button type="button" class="layui-btn layui-btn-small" id="swdjz">
 			上传文件
		</button>
      </div>
    </div>
  </div>

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">组织机构代码证</label>
      <div class="layui-input-inline" id="zzjgdmztag">
        <button type="button" class="layui-btn layui-btn-small" id="zzjgdmz">
 			上传文件
		</button>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">授权书</label>
      <div class="layui-input-inline" id="sqstag">
        <button type="button" class="layui-btn layui-btn-small" id="sqs">
 			上传文件
		</button>
      </div>
    </div>
  </div>

 <div class="layui-form-item">
      <label class="layui-form-label">附件</label>
      <div class="layui-input-block" id="fjtag">
        <button type="button" class="layui-btn layui-btn-small" id="fj">
 			上传文件
		</button>
      </div>
  </div>
    
  </div>
</fieldset>
<br>
<br>
<div class="layui-form-item">
    <div class="layui-input-block">
    <input type="hidden" id="fileId" name="fileId">
      <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary" onclick="location.reload()">重置</button>
    </div>
    </div>
    <br>
    <br>
    <br>
	<input type="hidden" name="businessLicensePath" id="businessLicensePath"><!-- 营业执照 -->
	<input type="hidden" name="taxrecePath" id="taxrecePath"><!-- 税务登记证 -->
	<input type="hidden" name="orcodePath" id="orcodePath"><!-- 组织机构代码证 -->
	<input type="hidden" name="ceauPath" id="ceauPath"><!-- 授权书 -->
	<input type="hidden" name="alternateField1" id="alternateField1">
	<input type="hidden" name="files" id="files">
</form>
</body>
<script>
var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
var filearr = new Array();
function contains(cts,tar){
	if(cts.indexOf(tar) !=-1 ){ 
	     return true;
	 }  else {
	 	return false;
	 }
}
function removeByValue(arr, val) {
  for(var i=0; i<arr.length; i++) {
    if(contains(arr[i],val)) {
      arr.splice(i, 1);
      break;
    }
  }
}

layui.use([ 'form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form;
 var laydate=layui.laydate;
 var upload = layui.upload;
 var element = layui.element;
 var $=layui.jquery;
 //
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $("#files").val(filearr.join('|'));
	 $.ajax({
         cache: true,
         type: "POST",
         url:basePath+"/com/save?fileId="+$("#fileId").val(),
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
 layui.use('upload', function(){
	  var upload = layui.upload;
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#yyzz' //绑定元素
	    ,url: basePath+'/com/fileUpload4json' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    	if(res.code==0){
	    		$('#yyzztag').empty();
		    	$('#yyzztag').append("<lable class='layui-form-label'  id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ><i class='larry-icon larry-guanbi1'></i></span></lable>");
		    	$("#businessLicensePath").val(res.filename);
		    	layer.closeAll('loading');
		    	layer.msg(res.msg);
	    	}else if(res.code!=0){
	    		layer.closeAll('loading');
	    		layer.msg(res.msg);
	    	}
	    }
	    ,error: function(){
	    	layer.msg("服务器异常");
	    }
	  });
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#swdjz' //绑定元素
	    ,url: basePath+'/com/fileUpload4json' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    	if(res.code==0){
	    		$('#swdjztag').empty();
		    	$('#swdjztag').append("<lable class='layui-form-label'  id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ><i class='larry-icon larry-guanbi1'></i></span></lable>");
		    	$("#taxrecePath").val(res.filename);
		    	layer.closeAll('loading');
		    	layer.msg(res.msg);
	    	}else if(res.code!=0){
	    		layer.closeAll('loading');
	    		layer.msg(res.msg);
	    	}
	    }
	    ,error: function(){
	    	layer.msg("服务器异常");
	    }
	  });
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#zzjgdmz' //绑定元素
	    ,url: basePath+'/com/fileUpload4json' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    	if(res.code==0){
	    		$('#zzjgdmztag').empty();
		    	$('#zzjgdmztag').append("<lable class='layui-form-label'  id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ><i class='larry-icon larry-guanbi1'></i></span></lable>");
		    	$("#orcodePath").val(res.filename);
		    	layer.closeAll('loading');
		    	layer.msg(res.msg);
	    	}else if(res.code!=0){
	    		layer.closeAll('loading');
	    		layer.msg(res.msg);
	    	}
	    }
	    ,error: function(){
	    	layer.msg("服务器异常");
	    }
	  });
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#sqs' //绑定元素
	    ,url: basePath+'/com/fileUpload4json' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    	if(res.code==0){
	    		$('#sqstag').empty();
		    	$('#sqstag').append("<lable class='layui-form-label'  id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ><i class='larry-icon larry-guanbi1'></i></span></lable>");
		    	$("#ceauPath").val(res.filename);
		    	layer.closeAll('loading');
		    	layer.msg(res.msg);
	    	}else if(res.code!=0){
	    		layer.closeAll('loading');
	    		layer.msg(res.msg);
	    	}
	    }
	    ,error: function(){
	    	layer.msg("服务器异常");
	    }
	  });
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#fj' //绑定元素
	    ,url: basePath+'/com/fileUpload4json' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    	if(res.code==0){
		    	$('#fjtag').append("<lable class='layui-form-label'  id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ></span></lable>");
		    	filearr.push(res.filename);
		    	layer.closeAll('loading');
		    	layer.msg(res.msg);
	    	}else if(res.code!=0){
	    		layer.closeAll('loading');
	    		layer.msg(res.msg);
	    	}
	    }
	    ,error: function(){
	    	layer.msg("服务器异常");
	    }
	  });
	});
});

</script>
</html>        
        
