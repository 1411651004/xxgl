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
    
    <title>资料导出</title>
    
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
      <label class="layui-form-label">名称</label>
      <div class="layui-input-inline">
      <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
      <input type="hidden" id="type" name="${record.type }">
        <input name="mc" id="mc" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">城市</label>
      <div class="layui-input-inline">
        <input name="cs" id="cs" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">负责人</label>
      <div class="layui-input-inline">
        <input name="fzr" id="fzr" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-inline" id="search">
    	<div style="width:auto;" class="layui-input-inline">
      		<button class="layui-btn" lay-submit=""  lay-filter="formDemo">查询</button>
      	</div>
    </div>
  </div>
</blockquote>
</form>
<div id="dataList">
			<jsp:include page="getzlList.jsp" />
</div>


<script>
var mailindex ;
var idsarr = new Array();
var phonesarr=new Array();
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

function expword(id){
	layui.use(['jquery','layer'], function(){
		var $=layui.jquery;
		 var layer = layui.layer;
	 $.ajax({
			url:"<%=basePath %>qyxx/expword?id="+id,
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.code==1){
					layer.closeAll('loading');
					layer.msg(data.msg);
				}else if(data.code==0){
					layer.closeAll('loading');
					setTimeout("location.reload();",1500);
					window.open("<%=basePath %>uploadFile/zip/文件.zip")
				}
				
				
				
			},
			error:function(data){
				layer.closeAll('loading');
				alert("出错");
			}
		
		});
	});
  }
function sendmessage(){
	layui.use(['form','laydate','upload','element','jquery','layer','layedit'], function(){
		 var form = layui.form();
		 var laydate=layui.laydate();
		 var upload = layui.upload();
		 var element = layui.element();
		 var $=layui.jquery;
		 var layer = layui.layer;
		 var layedit=layui.layedit;
		 var ids=idsarr.join(",");
		 var phones = phonesarr.join(",");
		 if(ids==""){
			 layer.msg("请选择收件人");
			 return;
		 }
		 layer.open({
			  title: '邮件内容'
			  ,content: "<div style='width:1045px'><form class='layui-form'><div class='layui-form-item'> <label class='layui-form-label'>邮件主题</label> <div class='layui-input-block'> <input type='text' id='title' name='title' required  lay-verify='required' placeholder='请输入邮件主题' autocomplete='off' class='layui-input'> </div> </div> <div class='layui-form-item'> <label class='layui-form-label'>邮件内容</label> <div class='layui-input-block'> <textarea  id='remindCon' ></textarea></div></div></form></div>",
			  area: ['1087px', '713px'],
			  btn: ['发送邮件'],
			  success: function(layero, index){
				  mailindex = layedit.build('remindCon', {
					  height: 450, //设置编辑器高度
					  uploadImage: {
						    url: '<%=basePath%>qyxx/fileUpload4json' //接口url
						    ,type: 'post' //默认post
						  }
					});
				  },
			  yes: function(index, layero){
				  var title = (layero.find('input').val());
				  var str = layedit.getContent(mailindex);
				  layer.confirm('邮件发送后将无法撤回，确定发送邮件吗？', {
					  btn: ['确定', '取消'] //可以无限个按钮
					}, function(index, layero){
						//提交
						$.ajax({
							url:"<%=basePath%>qyxx/sendmobile",
							type:"post",
							async:true,
							dataType:"json",
							data:{
								remindCon:str,
								phones:phones,
								ids:ids,
								title:title
							},
							 beforeSend:function(){
						         layer.msg('邮件发送中,请稍候', {
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
layui.use(['form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;

 var searchFormName='searchForm';

 form.on('checkbox(ckall)', function(data){
		if(data.elem.checked){
			idsarr = new Array();
			phonesarr=new Array();
			$("[group='ids']").each(function(){
				idsarr.push($(this).attr("idnum"));
				phonesarr.push($(this).val());
			});
			console.log($("[group='ids']").length);
			$("[group='ids']").each(function(){
				$(this).prop("checked",true);
			});
			
			form.render('checkbox');
		}else{
			idsarr = new Array();
			phonesarr=new Array();
			$("[group='ids']").prop("checked",false);
		}
		form.render('checkbox');
		console.log(idsarr);
		console.log(phonesarr);
	});
 form.on('checkbox(ckone)', function(data){
	 if(data.elem.checked){
		idsarr.push($(data.elem).attr("idnum"))
	 	phonesarr.push($(data.elem).val());
	 }else{
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
	 console.log(idsarr);
	console.log(phonesarr);
	});
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $.ajax({
		url:"<%=basePath %>qyxx/getList?type=${record.type}",
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
