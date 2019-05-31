<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
  </head>
  
  <body>
  <form class="layui-form" action="" id="searchForm" name="searchForm">
    <blockquote class="layui-elem-quote" style="background-color:#fff;margin-top:2%;margin-bottom:1%;margin: 2%;min-width: 1140px;">
  <div class="layui-form-item" style="margin-bottom: 0px;">
    <div class="layui-inline">
      <label class="layui-form-label">姓名</label>
      <div class="layui-input-inline">
      <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
      <input type="hidden" id="sta" name="${record.sta }">
        <input name="xm" id="xm" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">学校</label>
      <div class="layui-input-inline">
        <input name="xx" id="xx" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">身份证号码</label>
      <div class="layui-input-inline">
        <input name="sfz" id="sfz" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" id="search">
    	<div style="width:auto;" class="layui-input-inline">
      		<button class="layui-btn" lay-submit=""  lay-filter="formDemo">查询</button>
	      		<a class="layui-btn" onclick="xzfj()">下载附件</a>
	      		<!-- <a class="layui-btn" onclick="sendmessage()">导出文件</a> -->
      	</div>
    </div>
  </div>
</blockquote>
</form>
<div id="tanchu" style="display:none">
<form action="" class="layui-form">
	<div class="layui-form-item">
    <label class="layui-form-label">选择附件</label>
    <div class="layui-input-block">
      <input type="checkbox" name="fjtype" value="1" title="报名表">
      <input type="checkbox" name="fjtype" value="2" title="在留资格">
      <input type="checkbox" name="fjtype" value="3" title="护照照片">
    </div>
  </div>
</form>
</div>
<div id="dataList">
			<jsp:include page="getfjList.jsp" />
</div>
<script>
var idsarr = new Array();
var phonesarr=new Array();
var sfzarr = new Array();
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

function exp(){
	layui.use(['form','laydate','upload','element','jquery','layer'], function(){
		 var form = layui.form();
		 var laydate=layui.laydate();
		 var upload = layui.upload();
		 var element = layui.element();
		 var $=layui.jquery;
		 var layer = layui.layer;
		 layer.open({
			  title: '相关信息'
			  ,content: $("#wjxx").html(),
			  btn: ['确定'],
			  area:['624px','693px'],
			  yes: function(index, layero){
				  var str = (layero.find('textarea').val());
				  layer.confirm('短信发送后将无法撤回，确定发送短信吗？', {
					  btn: ['确定', '取消'] //可以无限个按钮
					}, function(index, layero){
						//提交
						$.ajax({
							url:"<%=basePath%>xsxx/sendmobile",
							type:"post",
							async:true,
							dataType:"json",
							data:{
								remindCon:str,
								phones:phones,
								ids:ids
							},
							 beforeSend:function(){
						         layer.msg('短信发送中', {
								  icon: 16
								  ,shade: 0.3
								  ,time:1000*1000
								}); 
						    },
							success:function(data){
								layer.closeAll();
								layer.msg(data.msg);
						    }
						});
					}, function(index){
						layer.closeAll();
					  return false;
					});
				  }
			}); 
		
		 
	
	});
}
function xzfj(){
	layui.use(['form','laydate','upload','element','jquery','layer'], function(){
		 var form = layui.form();
		 var laydate=layui.laydate();
		 var upload = layui.upload();
		 var element = layui.element();
		 var $=layui.jquery;
		 var layer = layui.layer;
		 var ids=idsarr.join(",");
		 var phones = phonesarr.join(",");
		 var sfzs=sfzarr.join(",");
		 if(sfzs==""){
			 layer.msg("请选择学生");
			 return;
		 }
		 layer.open({
			  title: '请选择要下载的附件'
			  ,content: $("#tanchu").html(),
			  area:[553,185],
			  btn: ['下载'],
			  success: function(layero, index){
				  form.render();
				  },
			  yes: function(index, layero){
					var str = "";
				  $(layero.find('form')[0]['fjtype']).each(function(){
					  if($(this).prop("checked")){
						  str+=$(this).val();
					  }
				  });
				  if(str!=''){
						//提交
						$.ajax({
							url:"<%=basePath%>xsxx/xzfj",
							type:"post",
							async:true,
							dataType:"json",
							data:{
								ids:ids,
								fjtype:str
							},
							 beforeSend:function(){
						         layer.msg('文件生成中，请稍后……', {
								  icon: 16
								  ,shade: 0.3
								  ,time:1000*1000
								}); 
						    },
							success:function(data){
								layer.closeAll();
								location.href="<%=basePath%>"+data.file;
						    }
						});
					  
				  }
					}
			}); 
	
	});
}

layui.use(['form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 var searchFormName='searchForm';
 var layer = layui.layer;
 layui.upload({
		
	    url:'<%=basePath%>xsxx/expfile' //上传接口
	    	,before:function(res){
	    		//加载层-风格4
	    		layer.msg('文件导入中', {
	    		  icon: 16
	    		  ,shade: 0.01
	    		});
		    }
	    ,success: function(res){ //上传成功后的回调0-成功1-失败
	    	if(res.code==0){
		    	layer.closeAll();
	    		layer.alert(res.msg);
	    	}else if(res.code!=0){
	    		layer.closeAll();
	    		layer.alert(res.msg);
	    	}
	    }
	  });
 
 form.on('checkbox(ckall)', function(data){
		if(data.elem.checked){
			idsarr = new Array();
			phonesarr=new Array();
			sfzarr=new Array();
			$("[group='ids']").each(function(){
				idsarr.push($(this).attr("idnum"));
				sfzarr.push($(this).attr("sfz"));
				phonesarr.push($(this).val());
			});
			$("[group='ids']").each(function(){
				$(this).prop("checked",true);
			});
			
			form.render('checkbox');
		}else{
			idsarr = new Array();
			sfzarr = new Array();
			phonesarr=new Array();
			$("[group='ids']").prop("checked",false);
		}
		form.render('checkbox');
	});
 form.on('checkbox(ckone)', function(data){
	 if(data.elem.checked){
		idsarr.push($(data.elem).attr("idnum"))
	 	phonesarr.push($(data.elem).val());
	 	sfzarr.push($(data.elem).attr("sfz"));
	 }else{
		 removeByValue(sfzarr,$(data.elem).attr("sfz"));
		 removeByValue(idsarr,$(data.elem).attr("idnum"));
		 removeByValue(phonesarr,$(data.elem).val());
	 }
	 if(idsarr.length!=$("[group='ids']").length){
		 $("#ckall").prop("checked",false);
	 }
	 if(idsarr.length==$("[group='ids']").length){
		 $("#ckall").prop("checked",true);
	 }
	 form.render('checkbox');
	});
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $.ajax({
		url:"<%=basePath %>xsxx/getList?sta=${record.sta}",
		dataType:"text",
		type:"post",
		data:$("#"+searchFormName+", form[name='"+searchFormName+"']").serialize(),
		success:function(data){
			//$("#"+pfId).remove();
			layer.closeAll('loading');
			$("#dataList").html(data);
			form.render('checkbox');
		},
		error:function(data){
			layer.closeAll('loading');
			alert("出错");
		}
	
	});
	 return false;
 });

});
</script>
  </body>
</html>
