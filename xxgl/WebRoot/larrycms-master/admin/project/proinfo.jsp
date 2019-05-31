<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="com.guochen.model.TProject" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fileId = ((TProject)request.getAttribute("project")).getFileId();
String[] filearr = fileId.split("\\|");

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <title>项目报价</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
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
</style>
</head>
<body style="background: #fff">
<header class="larry-personal-tit">
			<span>&nbsp;</span>
		</header>
<form class="layui-form" style="margin-left: 4%;margin-right: 4%;">
	
	<br>
<fieldset class="layui-elem-field">
  <legend style="font-size:16px">项目基本信息</legend>
  <div class="layui-field-box">

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">项目名称</label>
      <div class="layui-input-inline">
        <input name="proName"  readonly="readonly" id="proName" value="${project.proName }" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">项目编码</label>
      <div class="layui-input-inline">
        <input type="text"  readonly="readonly" value="${project.proCode }" name="proCode" lay-verify="required" autocomplete="off" class="layui-input ">
      </div>
    </div>
  </div>
<div class="layui-form-item">
  <label class="layui-form-label">交易文件</label>
  <c:forEach var="filename" items="${fileList }">  
	<c:if test="${ !empty filename}">
	    <a href="<%=basePath %>down/downloadproject?fileName=${filename}"><lable class='layui-form-label' id='' style='margin-top: 5px;margin-right:10px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'><c:out value="${fn:substring(filename, 0,fn:length(filename)-6)}" />  </lable></a>
	</c:if>
</c:forEach>
</div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input name="conName" readonly="readonly" id="conName" value="${project.conName }" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">联系方式</label>
      <div class="layui-input-inline">
        <input type="tel" name="conPhone"  readonly="readonly" value="${project.conPhone }" id="conPhone" lay-verify="required|phone" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>

 <div class="layui-form-item">
 <div class="layui-inline">
      <label class="layui-form-label"  >开始时间</label>
      <div class="layui-input-inline" >
        <input  value="${project.proStarttime }"  readonly="readonly" type="text" lay-verify="required" name="proStarttime" id="proStarttime" placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input">
      </div>
</div>
 <div class="layui-inline">
    <label class="layui-form-label">结束时间</label>
      <div class="layui-input-inline" >
        <input value="${project.proEndtime }" type="text"  readonly="readonly" lay-verify="required" name="proEndtime" id="proEndtime" placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input">
      </div>
    </div>
</div>
  <div class="layui-form-item">
    <label class="layui-form-label">结算方式</label>
    <div class="layui-input-block" style="margin-left: 150px;">
      <input name="seMe" readonly="readonly" id="seMe" value="${project.seMe }" type="text" lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">项目简介</label>
    <div class="layui-input-block" style="margin-left: 150px;">
      <textarea name="proProfile" id="proProfile" placeholder="请输入内容" readonly = "readonly" class="layui-textarea">${project.proProfile }</textarea>
    </div>
  </div>

<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">交易公告</label>
    <div class="layui-input-block" style="margin-left: 150px;">
      <textarea name="biAn" id="biAn" placeholder="请输入内容" readonly = "readonly" class="layui-textarea">${project.biAn }</textarea>
    </div>
  </div>
  </div>
</fieldset>
<div class="layui-form-item">
    <div class="layui-input-block">
    <input type="hidden" id="offerSheetPath" name="offerSheetPath">
      <a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" ><span id="fanhui">返回</span></a>
    </div>
    </div>
<br>
</form>
</body>
<script>
var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
var shangchuandiv="";
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
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 //
 $('#appendtag').on('click','span', function(){
	 var lablename = $(this).attr("lablename").replace(".","");
		$("#"+lablename).remove();
		$("#offerSheetPath").val("");
 });
 form.verify({
	    numberOrEmpty: function (value, item) {
	        if (value != '') {
	            if (!/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(value)) {
	                return '只能填写金额';
	            }
	        }
	    }
	});   
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $.ajax({
			cache : true,
			type : "POST",
			url:basePath+"/com/saveOffer",
			data : data.field,
			async : false,
			error : function(request) {
				layer.closeAll('loading');
	             layer.msg("Connection error");
			},
			success : function(data) {
				layer.closeAll('loading');
	        	 layer.msg("保存成功");
	        	 $("#fanhui").click();
			}
		});
	 return false;
 });

	form.on('select(buyerselect)',function(data){
		$("#buyerName").val($(data.elem).find("option:selected").attr("buyerName"));
		$("#buyerAddr").val($(data.elem).find("option:selected").attr("buyerAddr"));
		$("#buyerPhone").val($(data.elem).find("option:selected").attr("buyerPhone"));
		$("#buyerBankAccount").val($(data.elem).find("option:selected").attr("buyerBankAccount"));
		$("#buyerAccount").val($(data.elem).find("option:selected").attr("buyerAccount"));
		$("#buyerTaxreceNo").val($(data.elem).find("option:selected").attr("buyerTaxreceNo"));
	});
  layui.upload({
	
    url: basePath+'/com/fileUpload2json' //上传接口
    ,before:function(input){
    	layer.load(2);
    }
    ,success: function(res){ //上传成功后的回调0-成功1-失败
    	if(res.code==0){
    		if($("#offerSheetPath").val()==''){
	    		$("#offerSheetPath").val(res.filename);
		    	$('#appendtag').append("<lable class='layui-form-label' id='"+res.filename.replace(".","")+"' style='margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.filename+"<span  class='guanbispan' lablename='"+res.filename+"' ><i class='larry-icon larry-guanbi1'></i></span></lable>");
		    	layer.closeAll('loading');
		    	layer.msg(res.msg);
        	}else{
		    	layer.closeAll('loading');
		    	layer.msg("只能上传一个文件");
        		
        	}
    	}else if(res.code!=0){
    		layer.closeAll('loading');
    		layer.msg(res.msg);
    	}
    }
  });
  
});

</script>
</html>        
        
