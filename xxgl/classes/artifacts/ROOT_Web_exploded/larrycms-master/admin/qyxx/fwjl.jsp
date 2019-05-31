<%@ page language="java" import="java.util.*,com.guochen.model.Qyxx" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>企业信息</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>larrycms-master/layui/css/layui.css"
	media="all">
<style type="text/css">
	table{
/* 	    border:2px solid black; */
	    max-width: 1200px;
	    margin-top: 30px;
	    margin-left: 10px !important;
	}
	td{
	border:2px solid black;
	}
</style>
<script type="text/javascript"
		src="<%=basePath%>larrycms-master/layui/layui.js"></script>
</head>
<body>
    <form id="qyxxForm" class="layui-form" action="/qyxx/save">
        <a class="layui-btn layui-btn-primary" href="javascript:window.history.go(-1);" ><span id="fanhui">返回</span></a>
        <input name="id" value="${record.id }" hidden="hidden" />
		<table class="layui-table">
		    <tr>
		        <td colspan="6" style="text-align: center;">${record.mc }&nbsp;</td>
		    </tr>
		    <tr>
		        <td style="width: 100px;">协议签署情况 </td>
		        <td  style="width: 180px;">
		            <input name="xyqsqk"  value="${record.xyqsqk }" placeholder="请输入协议签署情况" autocomplete="off" class="layui-input"/>
		        </td>
		        <td style="width: 100px;">院校态度</td>
		        <td style="width: 180px;">
		            <input name="yxtd"  value="${record.yxtd }" placeholder="请输入院校态度" autocomplete="off" class="layui-input"/>
		        </td>
		        <td style="width: 100px;">其他机构</td>
		        <td>
		            <input name="qtjg"  value="${record.qtjg }" placeholder="请输入其他机构" autocomplete="off" class="layui-input"/>
		        </td>
		    </tr>
		    <tr>
		        <td>主要负责老师 </td>
		        <td>
		          <input name="fzr"  value="${record.fzr }" placeholder="主要负责老师" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>联系方式</td>
		        <td>
		          <input name="lxfs"  value="${record.lxfs }" placeholder="联系方式" lay-verify="phone" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>邮箱</td>
		        <td>
		          <input name="yx"  value="${record.yx }" placeholder="邮箱" lay-verify="email" autocomplete="off" class="layui-input"/>
		        </td>
		    </tr>
		    <tr>
		        <td>负责老师 </td>
		        <td>
		          <input name="fzls2"  value="${record.fzls2 }" placeholder="负责老师" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>联系方式</td>
		        <td>
		          <input name="lxfs2"  value="${record.lxfs2 }" placeholder="联系方式" lay-verify="phone" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>邮箱</td>
		        <td>
		          <input name="yx2"  value="${record.yx2 }" lay-verify="email" placeholder="邮箱" autocomplete="off" class="layui-input"/>
		        </td>
		    </tr>
		    <tr>
		        <td>负责老师 </td>
		        <td>
		          <input name="fzls3"  value="${record.fzls3 }" placeholder="负责老师" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>联系方式</td>
		        <td>
		          <input name="lxfs3"  value="${record.lxfs3 }" lay-verify="phone" placeholder="联系方式" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>邮箱</td>
		        <td>
		          <input name="yx3"  value="${record.yx3 }" placeholder="邮箱" lay-verify="email" autocomplete="off" class="layui-input"/>
		        </td>
		    </tr>
		    <tr>
		        <td>日语专业人数 </td>
		        <td>
		          <input name="ryzyrs" value="${record.ryzyrs }" lay-verify="number" placeholder="日语专业人数" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>可参与时间</td>
		        <td>
		          <input name="kcysj" id="kcysj"  value="${record.kcysj }" placeholder="可参与时间" lay-verify="date" autocomplete="off" class="layui-input"/>
		        </td>
		        <td>&nbsp;</td><td>&nbsp;</td>
		    </tr>
		</table>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="formQyxx">立即提交</button>
		    </div>
		  </div>
	</form>
	<hr/>
	<button id="addfwjl" class="layui-btn" style="margin-left:30px;">
      <i class="layui-icon">&#xe608;</i> 添加访问记录
    </button>
	<table id="fwjl" class="layui-table">
	    <c:forEach items="${bfgls}" var="bfgl">
		    <tr>
		        <td>拜访时间</td><td>${bfgl.bfsj }&nbsp;</td>
		        <td>是否宣讲</td><td>${bfgl.sfxj }&nbsp;</td>
		        <td>听讲人数</td><td>${bfgl.tjrs }&nbsp;</td>
		    </tr>
		    <tr>
		        <td>重要事件</td><td colspan="5">${bfgl.zysj }&nbsp;</td>
		    </tr>
		    <tr>
		        <td>详细情况</td><td colspan="5">${bfgl.xxqk }&nbsp;</td>
		    </tr>
	    </c:forEach>
	</table>
	
	<div id="addFwjlDialog" style="display: none;">
	   <form id="fwjlForm" class="layui-form" action="/qyxx/addFwjl">
	     <input hidden="hidden" name="qyxxId" value="${record.id }"/>
	     <div class="layui-form-item">
	       <div class="layui-inline">
		    <label class="layui-form-label">拜访时间</label>
		    <div class="layui-input-block">
		      <input type="text" name="bfsj" id="bfsj" required  lay-verify="date|required" placeholder="拜访时间" autocomplete="off" class="layui-input">
		    </div>
		   </div>
	       <div class="layui-inline">
		    <label class="layui-form-label">是否宣讲</label>
		    <div class="layui-input-block">
		      <input type="text" name="sfxj" required  lay-verify="required" placeholder="是否宣讲" autocomplete="off" class="layui-input">
		    </div>
		   </div>
	       <div class="layui-inline">
		    <label class="layui-form-label">听讲人数</label>
		    <div class="layui-input-block">
		      <input type="text" name="tjrs" required  lay-verify="number|required" placeholder="听讲人数" autocomplete="off" class="layui-input">
		    </div>
		   </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">重要事件</label>
		    <div class="layui-input-block">
		      <input style="width: 945px;" type="text" name="zysj" required  lay-verify="required" placeholder="重要事件" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">详细情况</label>
		    <div class="layui-input-block">
		      <input style="width: 945px;" type="text" name="xxqk" required  lay-verify="required" placeholder="详细情况" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="formFwjl">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
	   </form>
	</div>
	
	<script type="text/javascript">
 		layui.use([ 'jquery', 'layer', 'form', 'laydate' ], function() {
 			var form = layui.form;
 			var $ = layui.jquery;
 			var laydate = layui.laydate;
 			laydate.render({
 			  elem: '#kcysj', //指定元素
 			  format: 'yyyy-MM-dd',
 			  position: 'fixed'
 			});
 			laydate.render({
 			  elem: '#bfsj', //指定元素
 			  format: 'yyyy-MM-dd',
 			  position: 'fixed'
 			});
 			
 			$(document).on("click","#addfwjl",function(){
 				layer.open({
 				  title: '添加访问记录',
	    		  type: 1,
	    		  area:['1080px','260px'],
	    		  content: $('#addFwjlDialog') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
	    		});
 			});
 			//监听提交
 			form.on('submit(formQyxx)', function() {
 				$.ajax({
 					url : "/qyxx/save",
 					data : $('#qyxxForm').serialize(),
 					dataType:"json",
 					success:function(data){
 						if(data && data.code == 0) {
 		 					alert(data.msg);
 		 				} else {
 		 					alert("保存失败");
 		 				}
 					}
 				});
 				return false;
 			});
 			form.on('submit(formFwjl)', function(data) {
 				$.ajax({
 					url : "/qyxx/addFwjl",
 					data : $('#fwjlForm').serialize(),
 					dataType:"json",
 					success:function(data){
 						if(data && data.code == 0) {
 		 					alert(data.msg);
 		 					setTimeout("location.reload();",1500);
 		 				} else {
 		 					alert("保存失败");
 		 				}
 					}
 				});
 				return false;
 			});
// 			var layer = layui.layer;
// 			var laydate = layui.laydate;
	
// 			var $ = layui.jquery;
// 			layui.upload({
// 				url : "http://" + window.location.host + '/template/fileUpload2json', //上传接口
// 				before : function(input) {
// 					var filesize = $(input)[0].files[0].size;
// 					if (filesize > 10485760) {
// 						layer.msg('文件大小必须小于10MB');
// 						return false;
// 					}
// 					layer.load(2);
// 				},
// 				success : function(res) { //上传成功后的回调0-成功1-失败
// 					if (res.code == 0) {
// 						$("#templatePath").val(res.filename);
// 						layer.closeAll('loading');
// 						layer.msg(res.msg);
// 					} else if (res.code != 0) {
// 						layer.closeAll('loading');
// 						layer.msg(res.msg);
// 					}
// 				}
// 			});
 		});
	</script>
</body>
</html>