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
	<c:if test="${record.type =='0'}">
	    <title>学校信息</title>
	</c:if>
	<c:if test="${record.type =='1'}">
	    <title>企业信息</title>
	</c:if>
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
</head>
<body>
<section class="larry-grid">
	<div class="larry-personal">
		<header class="larry-personal-tit">
			<span><c:choose><c:when test="${empty record.mc }">新增</c:when><c:otherwise>修改</c:otherwise></c:choose><c:if test="${record.type =='0'}">学校</c:if><c:if test="${record.type =='1'}">企业</c:if>
			</span>
		</header><!-- /header -->
		<div class="larry-personal-body clearfix changepwd">
		<c:if test="${record.type =='0'}">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">名称(中文)</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="mc" id="mc" value="${record.mc}" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">名称(日语)</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="rymc" id="rymc" value="${record.rymc}" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">城市</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="cs" id="cs" required value="${record.cs}" lay-verify="required" placeholder="请输入城市" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">地区</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="dq" id="dq" required value="${record.dq}" lay-verify="required" placeholder="请输入地区" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">学校地址</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="dz" id="dz" required value="${record.dz}" lay-verify="required" placeholder="请输入学校地址" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">负责人</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="fzr" id="fzr" value="${record.fzr}" required  lay-verify="required" placeholder="请输入负责人" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">电话号码</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="lxfs" id="lxfs" value="${record.lxfs}" required  lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">领区</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="lq" id="lq" required value="${record.lq}" lay-verify="required" placeholder="请输入领区" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">电子邮件</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="yx" id="yx" required value="${record.yx}" lay-verify="required" placeholder="请输入电子邮件地址" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label">备注</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="bz" id="bz" required value="${record.bz}" lay-verify="required" placeholder="请输入备注" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">学校材料落款日期</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <input type="text" name="byzd1" value="${record.byzd1}" onclick="layui.laydate({ format: 'YYYY年MM月DD日',position: 'fixed',elem: this})" id="byzd1" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">学生出发月份</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <input type="text" name="xscfyf" value="${record.xscfyf}" onclick="layui.laydate({ format: 'YYYY年MM月',position: 'fixed',elem: this})" id="xscfyf" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">在学免税日期</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <input type="text" name="byzd2" id="byzd2" value="${record.byzd2}" required onclick="layui.laydate({ format: 'YYYY年MM月DD日',position: 'fixed',elem: this})"  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">在学签证日期</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <input type="text" name="byzd3" id="byzd3" value="${record.byzd3}" required onclick="layui.laydate({ format: 'YYYY年MM月DD日',position: 'fixed',elem: this})" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">大学简介(中文)</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <textarea rows="" lay-verify="required" id="byzd8" name="byzd8" style="width:528px;height:200px" cols="">${record.byzd8}</textarea>
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">大学简介(日语)</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <textarea rows="" lay-verify="required" id="byzd9" name="byzd9" style="width:528px;height:200px" cols="">${record.byzd9}</textarea>
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">学分取得内容(中文)</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <textarea rows="" id="byzd4" name="byzd4" lay-verify="required" style="width:528px;height:200px" cols="">${record.byzd4}</textarea>
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">学分取得内容(日语)</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <textarea rows="" id="byzd5" name="byzd5" lay-verify="required" style="width:528px;height:200px" cols="">${record.byzd5}</textarea>
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">学分取得内容(研究生)(中文)</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <textarea rows="" id="byzd6" name="byzd6" lay-verify="required" style="width:528px;height:200px" cols="">${record.byzd6}</textarea>
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label" style="width:222px!important">学分取得内容(研究生)(日语)</label>
					    <div class="layui-input-block"  style="margin-left:222px;width:528px;">
					      <textarea rows="" id="byzd7" name="byzd7" lay-verify="required" style="width:528px;height:200px" cols="">${record.byzd7}</textarea>
					    </div>
					  </div>
					
					<div class="layui-form-item change-submit" style="width:388px;margin-left:466px;">
						<div class="layui-input-block">
							<input type="hidden" name="id" id="id" value="${record.id }" />
							<input type="hidden" name="type" id="type" value="${record.type }" />
							<a class="layui-btn" lay-submit="" lay-filter="formDemo">提交</a>
							<a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" ><span id="fanhui">返回</span></a>
						</div>
					</div>
			</form>
			<script type="text/javascript">
			var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
			function doSubmit(){
				layui.use(['form','laydate','upload','element','jquery','layer'], function(){
				var $ = layui.jquery;
				var layer = layui.layer;
					$.ajax({
							url:"http://"+window.location.host+'/qyxx/save',
							//async : true, 
							dataType:"json",
							type:"post",
							data:{
								id:$("#id").val(),
								mc:$("#mc").val(),
								cs:$("#cs").val(),
								fzr:$("#fzr").val(),
								lxfs:$("#lxfs").val(),
								type:$("#type").val(),
								byzd1:$("#byzd1").val(),
								byzd2:$("#byzd2").val(),
								byzd3:$("#byzd3").val(),
								byzd4:$("#byzd4").val(),
								byzd5:$("#byzd5").val(),
								byzd6:$("#byzd6").val(),
								byzd7:$("#byzd7").val(),
								byzd8:$("#byzd8").val(),
								byzd9:$("#byzd9").val(),
								rymc:$("#rymc").val(),
								dq:$("#dq").val(),
								dz:$("#dz").val(),
								lq:$("#lq").val(),
								xscfyf:$("#xscfyf").val(),
								bz:$("#bz").val()
							},
							success:function(data){
								if(data.code==0){
									layer.msg(data.msg);
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
			</script>
		</c:if>
		<c:if test="${record.type =='1'}">
			<form class="layui-form layui-form-pane col-lg-4 col-md-5 col-sm-6 " method="post" action="">
				<!-- 表单区域 -->
					<div class="layui-form-item">
					    <label class="layui-form-label">名称</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="mc" id="mc" value="${record.mc }" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">城市</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="cs" id="cs" value="${record.cs }" required  lay-verify="required" placeholder="请输入城市" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">时给</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="sg" id="sg" value="${record.sg }" required  lay-verify="required" placeholder="请输入时给" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">负责人</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="fzr" id="fzr" value="${record.fzr }" required  lay-verify="required" placeholder="请输入负责人" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					<div class="layui-form-item">
					    <label class="layui-form-label">联系方式</label>
					    <div class="layui-input-block"  style="margin-left:120px;width:630px;">
					      <input type="text" name="lxfs" value="${record.lxfs }" id="lxfs" required  lay-verify="required" placeholder="请输入联系方式" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					
					<div class="layui-form-item change-submit" style="width:388px;margin-left:466px;">
						<div class="layui-input-block">
							<input type="hidden" name="id" id="id" value="${record.id }" />
							<input type="hidden" name="type" id="type" value="${record.type }" />
							<a class="layui-btn" lay-submit="" lay-filter="formDemo">提交</a>
							<a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" ><span id="fanhui">返回</span></a>
						</div>
					</div>
			</form>
			<script type="text/javascript">
			var basePath="http://"+window.location.host+window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1);
			function doSubmit(){
				layui.use(['form','laydate','upload','element','jquery','layer'], function(){
				var $ = layui.jquery;
				var layer = layui.layer;
					$.ajax({
							url:"http://"+window.location.host+'/qyxx/save',
							//async : true, 
							dataType:"json",
							type:"post",
							data:{
								mc:$("#mc").val(),
								cs:$("#cs").val(),
								fzr:$("#fzr").val(),
								lxfs:$("#lxfs").val(),
								type:$("#type").val(),
								sg:$("#sg").val()
							},
							success:function(data){
								if(data.code==0){
									layer.msg(data.msg);
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
			</script>
			
		</c:if>
		</div>
	</div>
</section>
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script>
<script type="text/javascript">


layui.use(['upload','jquery','layer','form','laydate'], function(){
var form = layui.form();
//监听提交
 form.on('submit(formDemo)', function(data){
	 doSubmit();
 });
var layer = layui.layer;
var laydate = layui.laydate;

var $ = layui.jquery;
	  layui.upload({
	    url: "http://"+window.location.host+'/template/fileUpload2json' //上传接口
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
 });
</script>
</body>
</html>