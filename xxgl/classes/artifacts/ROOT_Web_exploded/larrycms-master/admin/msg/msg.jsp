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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>larrycms-master/admin/css/common.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>larrycms-master/admin/css/personal.css" media="all">
	<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
	<script type="text/JavaScript" src="<%=basePath%>larrycms-master/common/js/jquery-1.12.4.min.js"></script>
	<script type="text/JavaScript" src="<%=basePath%>larrycms-master/common/js/jquery.ztree.all.min.js"></script>
	<link rel="stylesheet" href="<%=basePath%>larrycms-master/common/css/zTreeStyle/zTreeStyle.css">
	<script type="<%=basePath%>larrycms-master/common/js/jquery.ztree.core.js"></script>
	<script type="<%=basePath%>larrycms-master/common/js/jquery.ztree.excheck.js"></script>
  </head>
  <style>
   .layui-form-label1{
    margin-left: 15px;
    margin-top: 5px;
    padding: 9px 25px;
    background-color: #009688;
    height: 10px;
    width: auto;
    line-height: 10px;
    color: #fff;
    border-radius: 5px;
    float: left;
    display: block;
    padding: 9px 15px;
    width: 80px;
    font-weight: 400;
    text-align: right;
    }
  </style>
  <body>
    <section class="larry-grid">
	<div class="larry-personal">
		<div class="larry-personal-body clearfix">
			<form class="layui-form col-lg-5 layui-form-pane">
			<div class="layui-input-inline" style="top: 35px;left: 1143px;width:auto;max-height:385px;overflow-x:hidden;position: absolute;z-index: 99999;">
						<input type="text" class="layui-input" style="display: inline-block;width: 225px;" size="10" id="u_name1"/>
					<a class="layui-btn" href="javascript:void(0);" onclick="loadUserList(1)">查询</a>
					<ul id="tree" class="ztree"  style="margin-left:0px;height:auto;"></ul>
			</div>
				<div class="layui-form-item" style="width: 1059px;">
						<div class="layui-inline" style="margin-right: 0px;">
						<label class="layui-form-label">接收人</label>
						<div class="layui-input-inline" style="margin-right: 0px;min-height: 33px;min-width:937px;padding-bottom: 3px;border: 1px solid rgb(230, 230, 230);" id="appendtag">  
						&nbsp;
						</div>
						
					    </div>
				</div>
				<div class="layui-form-item" style="width: 1059px;">
				<label class="layui-form-label">消息内容</label>
				<div class="layui-input-block" style="margin-left:120px">
					<textarea name="remindCon" id="remindCon" placeholder="请输入内容" class="layui-textarea"></textarea>
				</div>
				</div>
				<div class="layui-form-item" style="width: 1059px;">
				<label class="layui-form-label">快捷消息</label>
				<div class="layui-input-block" style="margin-left:120px;border: 1px solid rgb(230, 230, 230);">
					<a style="margin-left:20px;" href="javascript:void(0);" class="tuisong layui-btn">1.快捷回复快捷回复快捷回复快捷回复</a><br>
					<a style="margin-left:20px;margin-top:5px;" href="javascript:void(0);" class="tuisong layui-btn">2.快捷回复快捷回复快捷回复快捷回复</a><br>
					<a style="margin-left:20px;margin-top:5px;" href="javascript:void(0);" class="tuisong layui-btn">3.快捷回复快捷回复快捷回复快捷回复</a><br>
					<a style="margin-left:20px;margin-top:5px;" href="javascript:void(0);" class="tuisong layui-btn">4.快捷回复快捷回复快捷回复快捷回复</a><br>
					<a style="margin-left:20px;margin-top:5px;" href="javascript:void(0);" class="tuisong layui-btn">5.快捷回复快捷回复快捷回复快捷回复</a>
				</div>
				</div>
				<div class="layui-form-item" style="margin-top:35px">
			    <div class="layui-input-block" style="margin-left:150px">
			      <button class="layui-btn" onclick="appsend()">APP推送</button>
			      <button class="layui-btn" onclick="onCheck()">发送短信</button>
			    </div>
			    </div>
			</form>
	    </div>
	</div>
</section>
<script type="text/javascript">
var t ;
function appsend(){
layui.use(['jquery','layer'],function(){

	var $ = layui.jquery;
	var layer = layui.layer;
		var sNodes = t.getCheckedNodes(true);
		var str = "";
		var phones = "";
		if (sNodes.length > 0) {
			for(var i=0;i<sNodes.length;i++){
				var k = sNodes[i].rid+"";
				var p = sNodes[i].mobilephone+"";
				if(k!='-1'){
					str+=k+="|";
				}
				if(p!='-1'){
					phones+=p+=",";
				}
				
			}
		}
		str=str.substring(0,str.length-1);
		phones=phones.substring(0,phones.length-1);
		var remindCon = $("#remindCon").val();
		//提交
		$.ajax({
			url:"msg/appsend",
			type:"post",
			async:true,
			data:{
				remindCon:remindCon,
				phones:phones,
				ids:str
			},
			 beforeSend:function(){
		        /* layer.msg('短信发送中', {
				  icon: 16
				  ,shade: 0.3
				  ,time:1000*1000
				}); */
		    },
			success:function(data){
				layer.msg(data);
		    }
		});
		});
	}
function onCheck(){
	layui.use(['jquery','layer'],function(){
	var $ = layui.jquery;
	var layer=layui.layer;
		var sNodes = t.getCheckedNodes(true);
		var str = "";
		var phones = "";
		if (sNodes.length > 0) {
			for(var i=0;i<sNodes.length;i++){
				var k = sNodes[i].rid+"";
				var p = sNodes[i].mobilephone+"";
				if(k!='-1'){
					str+=k+="|";
				}
				if(p!='-1'){
					phones+=p+=",";
				}
				
			}
		}
		str=str.substring(0,str.length-1);
		phones=phones.substring(0,phones.length-1);
		var remindCon = $("#remindCon").val();
		//提交
		$.ajax({
			url:"msg/sendmobile",
			type:"post",
			async:true,
			data:{
				remindCon:remindCon,
				phones:phones,
				ids:str
			},
			 beforeSend:function(){
		        /* layer.msg('短信发送中', {
				  icon: 16
				  ,shade: 0.3
				  ,time:1000*1000
				}); */
		    },
			success:function(data){
				layer.msg(data);
		    }
		});
	});
}
function zTreeOnCheck(event, treeId, treeNode){
	console.log(treeNode);
	if(treeNode.checked){
		$('#appendtag').append("<lable class='layui-form-label1' id='"+treeNode.id+"' style='margin-left:15px;margin-top: 5px;padding:9px 25px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+treeNode.name+"<span  class='guanbispan' id='"+treeNode.id+"span' lablename='"+treeNode.id+"' ><i style='display:none' class='larry-icon larry-guanbi1'></i></span></lable>");
	}else{
		$("#"+treeNode.id+'span').click();
	}
}
	layui.use(['form','upload','element','jquery'],function(){
         var form = layui.form();
         var element = layui.element;
         var $ = layui.jquery;
         var jQuery  = layui.jquery;
         var upload = layui.upload;
         
         $(".tuisong").bind('click',function(){
		var str = $(this).html();
		str = str.substring(str.indexOf(".")+1,str.length);
		$("#remindCon").val(str);
	});
         
         var nodes = ${treelist};
		t = $("#tree");
		var setting = {
		check:{
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "s", "N": "ps" }
		},
		view: {
			dblClickExpand: true,
			showLine: true,
			selectedMulti: true
		},
		callback:{
			onCheck: zTreeOnCheck
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pid"
			}
		}
	};
	 $('#appendtag').on('click','span', function(){
	 var lablename = $(this).attr("lablename").replace(".","");
		$("#"+lablename).remove();
		//removeByValue(filearr,$(this).attr("lablename"));
 	});
		t = $.fn.zTree.init(t, setting, nodes);
         layui.upload({ 
             url: '' ,//上传接口 
             success: function(res){
              //上传成功后的回调 
              console.log(res) 
            } 
         });

	});
	//根据用户输入重新加载zTree
function loadUserList(flag){
layui.use(['jquery'],function(){
	var $ = layui.jquery;
	var u_name = "";
	if(flag==1){
		u_name = $("#u_name1").val();
	}
	if(flag==2){
		u_name = $("#u_name2").val();
	}
			var setting = {
		check:{
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "s", "N": "ps" }
		},
		view: {
			dblClickExpand: true,
			showLine: true,
			selectedMulti: true
		},
		callback:{
			onCheck: zTreeOnCheck
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pid"
			}
		}
	};
	$.ajax({
			url:"msg/loadUserList",
			type:"post",
			async:true,
			data:{
				u_name:u_name
			},
			success:function(data){
				var nodes = eval(data);
				if(flag==1){
					var t = $("#tree");
					t = $.fn.zTree.init(t, setting, nodes);
				}
				if(flag==2){
					var t1 = $("#tree1");
					t1 = $.fn.zTree.init(t1, setting, nodes);
				}
		    }
	});
	});
}
</script>
  </body>
</html>
