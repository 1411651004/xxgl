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
.layui-form-item .layui-input-block {
    margin-left: 150px;
}

</style>
</head>
<body style="background: #fff">
<header class="larry-personal-tit">
			<span>新建企业客户</span>
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
        <input name="proName" id="proName" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">项目编码</label>
      <div class="layui-input-inline">
        <input type="text" value="${project.proCode }" readonly name="proCode" lay-verify="required" disabled="disabled" autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
  </div>
<div class="layui-form-item">
  <label class="layui-form-label">交易文件</label>
  <div class="layui-input-block" id="appendtag">
    <input type="file" name="file1" lay-type="file" class="layui-upload-file">
  </div>
</div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input name="conName" id="conName" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">联系方式</label>
      <div class="layui-input-inline">
        <input type="tel" name="conPhone" id="conPhone" lay-verify="required|phone" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>

 <div class="layui-form-item">
 <div class="layui-inline">
      <label class="layui-form-label"  >开始时间</label>
      <div class="layui-input-inline" >
        <input  onclick="layui.laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',elem: this})" type="text" lay-verify="required" name="proStarttime" id="proStarttime" placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input">
      </div>
</div>
 <div class="layui-inline">
    <label class="layui-form-label">结束时间</label>
      <div class="layui-input-inline" >
        <input onclick="layui.laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',elem: this})" type="text" lay-verify="required" name="proEndtime" id="proEndtime" placeholder="yyyy-mm-dd hh:mm:ss" autocomplete="off" class="layui-input">
      </div>
    </div>
</div>
  <div class="layui-form-item">
    <label class="layui-form-label">结算方式</label>
    <div class="layui-input-block">
      <select name="seMe" lay-filter="aihao">
      	<c:forEach items="${listcode }" var = "tmp">
        <option value="${tmp.codeName }">${tmp.codeName }</option>
        </c:forEach>
      </select>
    </div>
  </div>
<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">项目简介</label>
    <div class="layui-input-block">
      <textarea name="proProfile" id="proProfile" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
  </div>

<div class="layui-form-item layui-form-text">
    <label class="layui-form-label">交易公告</label>
    <div class="layui-input-block">
      <textarea name="biAn" id="biAn" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
  </div>

  </div>
</fieldset>
<br>
<fieldset class="layui-elem-field">
  <legend style="font-size:16px">采购商信息</legend>
  <div class="layui-field-box">
  <div class="layui-form-item">
    <label class="layui-form-label">采购商</label>
    <div class="layui-input-block">
      <select id="buyerId" lay-search="" name="buyerId" lay-filter="buyerselect">
      <c:forEach items="${buyerList }" var = "b">
        <option value="${b.id }" buyerName="${b.buyerName}" buyerAddr="${b.buyerAddr}" buyerPhone="${b.buyerPhone}" buyerBankAccount="${b.buyerBankAccount}" buyerAccount="${b.buyerAccount}" buyerTaxreceNo="${b.buyerTaxreceNo}">${b.buyerName }</option>
        </c:forEach>
      </select>
    </div>
  </div>

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">名称</label>
      <div class="layui-input-inline">
        <input name="buyerName" id="buyerName" type="text" lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">地址</label>
      <div class="layui-input-inline">
        <input type="text" readonly name="buyerAddr" id="buyerAddr" lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
  </div>

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">电话</label>
      <div class="layui-input-inline">
        <input name="buyerPhone" id="buyerPhone" type="text" lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">开户行</label>
      <div class="layui-input-inline">
        <input type="text" readonly name="buyerBankAccount" id="buyerBankAccount" lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
  </div>

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">账号</label>
      <div class="layui-input-inline">
        <input name="buyerAccount" id="buyerAccount" type="text" lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input layui-disabled">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">税号</label>
      <div class="layui-input-inline">
        <input type="text" readonly name="buyerTaxreceNo" id="buyerTaxreceNo" lay-verify="required" readonly="readonly" autocomplete="off" class="layui-input layui-disabled">
      </div>
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
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
    </div>
    <br>
    <br>
    <br>
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
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 //
 $('#appendtag').on('click','span', function(){
	 var lablename = $(this).attr("lablename").replace(".","");
		$("#"+lablename).remove();
		removeByValue(filearr,$(this).attr("lablename"));
 });
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $("#fileId").val(filearr.join('|'));
	 $.ajax({
         cache: true,
         type: "POST",
         url:basePath+"/project/save?fileId="+$("#fileId").val(),
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

	form.on('select(buyerselect)',function(data){
		$("#buyerName").val($(data.elem).find("option:selected").attr("buyerName"));
		$("#buyerAddr").val($(data.elem).find("option:selected").attr("buyerAddr"));
		$("#buyerPhone").val($(data.elem).find("option:selected").attr("buyerPhone"));
		$("#buyerBankAccount").val($(data.elem).find("option:selected").attr("buyerBankAccount"));
		$("#buyerAccount").val($(data.elem).find("option:selected").attr("buyerAccount"));
		$("#buyerTaxreceNo").val($(data.elem).find("option:selected").attr("buyerTaxreceNo"));
	});
  layui.upload({
	
    url: basePath+'/project/fileUpload2json' //上传接口
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
    		filearr.push(res.filename);
	    	$('#appendtag').append("<lable class='layui-form-label' id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ><i class='larry-icon larry-guanbi1'></i></span></lable>");
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
</html>        
        
