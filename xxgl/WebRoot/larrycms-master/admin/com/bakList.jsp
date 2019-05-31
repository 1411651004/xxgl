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
    
    <title>My JSP 'bakList.jsp' starting page</title>
    
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
<script type="text/javascript">
function checkOne(thiss){
layui.use(['jquery'], function(){
var $ = layui.jquery;
	var group = thiss.attr("group");
	var checkAll = true;
	if($("[group='"+group+"']").length != $("[group='"+group+"']:checked").length){
		checkAll = false;
	}
	if(checkAll){
		$("[checkAll='"+group+"']").prop("checked", true);
	}else{
		$("[checkAll='"+group+"']").prop("checked", false);
	}
	});
}
function checkAll(thiss){
layui.use(['jquery'], function(){
var $ = layui.jquery;
	var group = thiss.attr("checkAll");
	if(thiss.attr("checked")){
		$("[group='"+group+"']").prop("checked",true);
	}else{
		$("[group='"+group+"']").prop("checked",false);
	}
	});
}
function addBlackList(){
layui.use(['jquery','layer'], function(){
var $ = layui.jquery;
var layer = layui.layer;
	if($("#idForm [type=checkbox][group=ids]:checked").length==0){
		alert("请选择需要拉黑的记录");
		return;
	}else{
		layer.prompt({title: '请输入拉黑原因'},function(val, index){
		  layer.close(index);
		  $("#forbidReason").val(val);
		  	$.ajax({
				url:'com/black',
				//async : true,
				dataType:"json",
				type:"post",
				data:$("#idForm").serialize(),
				success:function(data){
					if(data.code==1){
						goPage(currpageno);
						layer.msg("拉黑成功");
					}else{
						layer.msg(data.msg);
					}
				},
				error:function(data){
					layer.msg("出错");
				}
			});
		});

	}
	});
}

function resetPwd(id){
layui.use(['jquery','layer'], function(){
var $ = layui.jquery;
var layer = layui.layer;
	$.ajax({
		url:"<%=basePath %>com/resetPwd",
		//async : true, 
		dataType:"json",
		type:"post",
		data:{id:id},
		success:function(data){
			if(data.code=="1"){
				layer.msg("操作成功");
			}else{
				layer.msg("操作失败");
			}
		},
		error:function(data){
			layer.msg("出错");
		}
	});
	});
}
</script>
  </head>
  
  <body>
   <form class="layui-form" action="" id="searchForm" name="searchForm">
    <blockquote class="layui-elem-quote" style="background-color:#fff;margin-top:2%;margin-bottom:1%;margin: 2%;min-width: 1280px;">
  <div class="layui-form-item" style="margin-bottom: 0px;">
    <div class="layui-inline">
      <label class="layui-form-label">供应商名称</label>
      <div class="layui-input-inline">
      <input type="hidden" id="pageNum" name="pageNum">
        <input name="comName" id="comName" type="text" lay-verify="" autocomplete="off" class="layui-input">
      </div>
      <label class="layui-form-label">联系人</label>
      <div class="layui-input-inline">
        <input type="text" lay-verify="" name="conName" id="conName" autocomplete="off" class="layui-input">
      </div>
    <div class="layui-inline" id="search" style="margin-left:20px;">
    	<div style="width:auto;" class="layui-input-inline">
      		<button class="layui-btn" lay-submit=""  lay-filter="formDemo">查询</button>
 			<a style="margin-left:20px;" class="layui-btn" href="javascript:void(0)" onclick="delForBatList('com/delete','com/getBakList');">删除</a>
			<a style="margin-left:20px;" class="layui-btn" href="javascript:void(0)" onclick="addBlackList();">加入黑名单</a>
      	</div>
    </div>
    </div>
  </div>
</blockquote>
</form>
<div id="dataList">
			<jsp:include page="getBakList.jsp" />
</div>

 <jsp:include page="../common/page-ajax.jsp">
		<jsp:param name="url" value="com/getBakList" />
</jsp:include>
  </body>
  <script>
  layui.use(['form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form();
 var laydate=layui.laydate();
 var upload = layui.upload();
 var element = layui.element();
 var $=layui.jquery;
 var searchFormName='searchForm';
 setTimeout(function(){form.render('checkbox');}, 300);
 
 form.on('checkbox(ckall)', function(data){
	var group = $(data.elem).attr("checkAll");
	if(data.elem.checked){
		$("[group='"+group+"']").prop("checked",true);
	}else{
		$("[group='"+group+"']").prop("checked",false);
	}
	form.render('checkbox');
});
 form.on('checkbox(ckone)', function(data){
 	var group = $(data.elem).attr("group");
	var checkAll = true;
	if($("[group='"+group+"']").length != $("[group='"+group+"']:checked").length){
		checkAll = false;
	}
	if(checkAll){
		$("[checkAll='"+group+"']").prop("checked", true);
	}else{
		$("[checkAll='"+group+"']").prop("checked", false);
	}
	form.render('checkbox');
});
 
 //监听提交
 form.on('submit(formDemo)', function(data){
	 layer.load(2);
	 $.ajax({
		url:"<%=basePath %>com/getBakList",
		dataType:"text",
		type:"post",
		data:$("#"+searchFormName+", form[name='"+searchFormName+"']").serialize(),
		success:function(data){
			//$("#"+pfId).remove();
			layer.closeAll('loading');
			$("#"+tId).html(data);
			setTimeout(function(){form.render('checkbox');}, 300);
		},
		error:function(data){
			layer.closeAll('loading');
			layer.msg("出错");
		}
	
	});
	 return false;
 });

});
  	//批量删除备选供应商
function delForBatList(delUrl,dataUrl){
layui.use(['jquery','layer'], function(){
var $ = layui.jquery;
var layer = layui.layer;
	if($("#idForm [type=checkbox][group=ids]:checked").length==0){
		layer.msg("请选择需要批量删除的记录");
		return;
	}
	
	$.ajax({
		url:delUrl,
		//async : true,
		dataType:"json",
		type:"post",
		data:$("#idForm").serialize(),
		success:function(data){
			if(data.code==1){
				goPage(currpageno);
				layer.msg("删除成功");
			}else{
				layer.msg(data.msg);
			}
		},
		error:function(data){
			layer.msg("出错");
		}
	});
	});
}


//批量撤销拉黑供应商
function whiteForBatList(whiteUrl,dataUrl){
layui.use(['jquery'], function(){
var $ = layui.jquery;
	if($("#idForm [type=checkbox][group=ids]:checked").length==0){
		alert("请选择需要撤销的记录");
		return;
	}
	
	$.ajax({
		url:whiteUrl,
		//async : true,
		dataType:"json",
		type:"post",
		data:$("#idForm").serialize(),
		success:function(data){
			if(data.code==1){
				goPage('','searchForm',dataUrl,'','');
				alert("撤销成功");
			}else{
				alert(data.msg);
			}
		},
		error:function(data){
			alert("出错");
		}
	});
	});
}

//审核供应商
function doVerifyCom(id,sta,dataUrl){
layui.use(['jquery'], function(){
var $ = layui.jquery;
	if(sta=="2"){
		var unpassReason = $("#Reason").val();
		if(unpassReason==""){
			alert("请输入审核不通过的原因");
			return;
		}
	}
	$.ajax({
		url:'com/doVerifyCom',
		//async : true,
		dataType:"json",
		type:"post",
		data:{
			id:id,
			comType:sta,
			unpassReason:unpassReason
		},
		success:function(data){
			if(data.code==1){
				alert("审核成功");
				$("#closeBtn").click();
				show_menu(null,dataUrl);
			}else{
				alert(data.msg);
			}
		},
		error:function(data){
			alert("出错");
		}
	});
	});
}

  </script>
</html>
