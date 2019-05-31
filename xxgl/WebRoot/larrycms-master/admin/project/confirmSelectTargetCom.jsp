<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="com.guochen.model.TProject" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

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
<div  style="margin-left: 4%;margin-right: 4%;">
	
	<br>
<form class="layui-form" style="margin-left: 4%;margin-right: 4%;">
<input type="hidden" id="offerId" name="offerId" value="${offer.id}"/>
<input type="hidden" id="id" name="id" value="${contrat.id}"/>
<input type="hidden" id="projectNo" name="projectNo" value="${contrat.projectNo}"/>
<fieldset class="layui-elem-field">
  <legend style="font-size:16px">请确认合同信息</legend>
  <div class="layui-field-box">

 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">交易内容</label>
      <div class="layui-input-inline">
        <input name="con"  id="con" value="${contrat.con}" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">交付时间</label>
      <div class="layui-input-inline">
        <input type="text"  value="${contrat.deliveryTime}" id="deliveryTime" name="deliveryTime" lay-verify="required" autocomplete="off" class="layui-input ">
      </div>
    </div>
  </div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">结算方式</label>
      <div class="layui-input-inline">
        <select id="seMe" name="seMe" class="xmlist_inw jbxx_inw">
			<c:forEach items="${codeList}" var="code">
				<option value="${code.codeValue}" <c:if test="${code.codeValue eq offer.project.seMe}">selected</c:if> >${code.codeName}</option>
			</c:forEach>
		</select>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">合同金额</label>
      <div class="layui-input-inline">
        <select id="bjxz" name="bjxz" class="xmlist_inw jbxx_inw">
			<option value="cash">现金报价</option>
			<option value="acce">承兑报价</option>
		</select>
      </div>
    </div>
  </div>
 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">合同模板</label>
      <div class="layui-input-inline">
        <select id="templateId" name="templateId" class="xmlist_inw jbxx_inw">
			<c:forEach items="${templateList}" var="tmpt">
				<option value="${tmpt.id}">${tmpt.templateName}</option>
			</c:forEach>
		</select>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">采购商简称</label>
      <div class="layui-input-inline">
        <input name="jc" id="jc" value="${contrat.jc}" type="text" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
  </div>
	<div class="layui-form-item">
		<div class="layui-inline">
	  <label class="layui-form-label">报价单</label>
	  <div class="layui-input-inline">
		    <a href="<%=basePath %>down/downloadoffer?fileName=${offer.offerSheetPath}"><lable class='layui-form-label' id='' style='margin-top: 5px;margin-right:10px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'><c:out value="${fn:substring(offer.offerSheetPath,0,fn:length(offer.offerSheetPath)-6)}" />  </lable></a>
			</div>
		</div>
		<div class="layui-inline">
	  <label class="layui-form-label">是否加急</label>
	  <div class="layui-input-inline">
	  		<select id="isemergency" name="isemergency">
	  			<option value="0">不加急</option>
	  			<option value="1">加急</option>
	  		</select>
			</div>
		</div>
	</div>
	
	<br>
	<hr>
	<br>
	 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">甲方名称</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.project.buyer.buyerName}" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">地址</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.project.buyer.buyerAddr}" class="layui-input">
      </div>
    </div>
  </div>
	 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">电话</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.project.buyer.buyerPhone}" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">开户行</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.project.buyer.buyerBankAccount}" class="layui-input">
      </div>
    </div>
  </div>
	 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">账号</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.project.buyer.buyerAccount}" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">税号</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.project.buyer.buyerTaxreceNo}" class="layui-input">
      </div>
    </div>
  </div>
  <br>
  <hr>
  <br>
	 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">乙方名称</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.com.comName}" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">地址</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.com.comAddr}" class="layui-input">
      </div>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">电话</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.com.comPhone}" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">开户行</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.com.bankAccount}" class="layui-input">
      </div>
    </div>
  </div>
	 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">账号</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.com.bankAccount}" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">税号</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.com.taxreceNo}" class="layui-input">
      </div>
    </div>
  </div>
  <br>
  <hr>
  <br>
	 <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">交易金额</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.doubleOfCashOffer}元" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">交易配送费</label>
      <div class="layui-input-inline">
        <input type="text" readonly="readonly" value="${offer.doubleOfCashOffer*0.3}元" class="layui-input">
      </div>
    </div>
  </div>
  
  </div>
</fieldset>
<br>
<br>
<div class="layui-form-item">
    <div class="layui-input-block">
    <input type="hidden" id="offerSheetPath" name="offerSheetPath">
    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
      <a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" ><span id="fanhui">返回</span></a>
    </div>
    </div>
</form>
</div>

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
 var layer= layui.layer;


 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 var con = $("#con").val();
	 var deliveryTime = $("#deliveryTime").val();
	 $.ajax({
			cache : true,
			type : "POST",
			url : "<%=basePath%>contract/save",
			data : {
				id:$("#id").val(),
				con:con,
				deliveryTime:deliveryTime,
				offerId:$("#offerId").val(),
				seMe:$("#seMe").val(),
				templateId:$("#templateId").val(),
				isemergency:$("#isemergency").val(),
				jc:$("#jc").val(),
				projectNo:$("#projectNo").val(),
				bjxz:$("#bjxz").val()
			},
			async : false,
			error : function(request) {
				layer.msg("Connection error");
			},
			success : function(data) {
				layer.msg("保存成功");
				location.href='<%=basePath%>project/selectTargetCom';
			}
	});
	 return false;
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
        
