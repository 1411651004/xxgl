<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>二次采集</title>
    <link rel="stylesheet" href="<%=basePath %>static/css/stylesheet.css">
    <link rel="stylesheet" href="<%=basePath %>larrycms-master/layui/css/layui.css">
</head>
<body>
    
    <div class="table">
        
        <div class="top-bar"></div>
        
        <div class="table-cell">
            <div class="table-cell-header">
               ${user.xm}同学，您的基本资料已经通过审核，请完善以下资料。
            </div>
            <div class="table-cell-title"></div>
            <h3>学生信息采集</h3>
            <form id="form1" name="form1" accept-charset="UTF-8" action="<%=basePath%>xsxx/add" method="post">
            <div class="table-cell-box">
                <div class="table-cell-box-title">
                    <h3>基本信息</h3>
                    <small><font style="color:red">（内容部分是您本人第一次录入时的资料，如有变动或录入错误请点击修改按钮进行修改）</font></small>
                </div>
                <ul>
                    <li>
                        <label for="">姓&emsp;名：</label>
                        <input type="text" lable="姓名" v="bt" name="xm" value="${user.xm }" id="xm">
                    </li>
                    <li>
                        <label for="">学&emsp;校：</label>
                        <input type="text" lable="学校" v="bt" name="xx" value="${user.xx }" id="xx">
                    </li>
                    <li>
                        <label for="">性&emsp;别：</label>
                        <input type="text" lable="性别" v="bt"  name="xb" value="${user.xb }" id="xb" class="small">
                        <label for="">民&emsp;族：</label>
                        <input type="text"  lable="民族" v="bt"  name="mz" value="${user.mz }" id="mz" class="small">
                    </li>
                    <li>
                        <label for="">专&emsp;业：</label>
                        <input type="text" lable="专业" v="bt" name="zy" value="${user.zy }" id="zy" >
                    </li>
                    <li>
                        <label for="">身份证：</label>
                        <input type="text" lable="身份证" v="bt" name="sfz" value="${user.sfz }" id="sfz">
                    </li>
                    <li>
                        <label for="">电&emsp;话：</label>
                        <input type="text" lable="电话" v="bt" name="dh" value="${user.dh }" id="dh">
                    </li>
                    <!-- <li>
                        <label for="">照&emsp;片：</label>
                        <input type="file" class="file">
                    </li> -->
                </ul>
            </div>

            <div class="table-cell-box">
                <div class="table-cell-box-title">
                    <h3>个人信息</h3>
                    <small><font style="color:red">（如果为空，请填写无）</font></small>
                </div>
                <ul>
                    <li>
                        <label for="">身&emsp;高：</label>
                        <input type="text" lable="身高" v="bt" name="sg" placeholder="" value="${user.sg }" id="sg" class="small">
                        <label for="" style="width: 28px;color: black;">cm</label>
                        <label for="">体&emsp;重：</label>
                        <input type="text" lable="体重" v="bt" name="tz" placeholder="" value="${user.tz }" id="tz" class="small">
                        <label for="" style="width: 28px;color: black;">kg</label>
                    </li>
                    <li>
                        <label for="">父亲姓名：</label>
                        <input type="text" lable="父亲姓名" v="bt" name="fqxm" value="${user.fqxm }" id="fqxm" class="small">
                        <label for="">联系电话：</label>
                        <input type="text" lable="联系电话" v="bt" name="fqlxdh" value="${user.fqlxdh }" id="fqlxdh" class="small">
                    </li>
                    <li>
                        <label for="">胸&emsp;围：</label>
                        <input type="text" lable="胸围" v="bt" name="xw" value="${user.xw }" id="xw" class="small">
                        <label for="" style="width: 28px;color: black;">cm</label>
                        <label for="">腰&emsp;围：</label>
                        <input type="text" lable="腰围" v="bt" name="yw" value="${user.yw }" id="yw" class="small">
                        <label for="" style="width: 28px;color: black;">cm</label>
                    </li>
                    <li>
                        <label for="">母亲姓名：</label>
                        <input type="text" lable="母亲姓名" v="bt" name="mqxm" value="${user.mqxm }" id="mqxm" class="small">
                        <label for="">联系电话：</label>
                        <input type="text" lable="联系电话" v="bt" name="mqlxdh" value="${user.mqlxdh }" id="mqlxdh" class="small">
                    </li>
                    <li>
                        <label for="">臀&emsp;围：</label>
                        <input type="text" lable="臀围" v="bt" name="tw" value="${user.tw }" id="tw" class="small">
                        <label for="" style="width: 28px;color: black;">cm</label>
                        <label for="">鞋&emsp;号：</label>
                        <input type="text" lable="鞋号" v="bt" name="xh" value="${user.xh }" id="xh" class="small">
                    </li>
                    <li>
                        <label for="">家庭地址：</label>
                        <input type="text" lable="家庭地址" v="bt" name="jtdz" id="jtdz" value="${user.jtdz }">
                    </li>
                </ul>
            </div>

            <div class="table-cell-box">
                <div class="table-cell-box-title">
                    <h3>其他信息</h3>
                    <small><font style="color:red">（如果为空，请填写无）</font></small>
                </div>
                <ul>
                    <li>
                        <label for="">入学年份：</label>
                        <input type="text" lable="入学年份" v="bt" name="rxnf" value="${user.rxnf }" id="rxnf" class="small">
                        <label for="">年级：</label>
                        <input type="text" lable="年级" v="bt" name="nj" value="${user.nj }" id="nj" class="small">
                    </li>
                    <li>
                        <label for="">学制：</label>
                        <input type="text" lable="学制" v="bt" name="xz" value="${user.xz }" id="xz" class="small">
                        <label style="width:99px!important"  for="">是否为研究生：</label>
                        <input style="width:22px!important" value="1" lable="是否为研究生" name="sfyjs" id="sfyjs" type="checkbox">
                    </li>
                    
                </ul>
            </div>

            <div class="table-cell-box">
                <div class="table-cell-box-title">
                    <h3>插入附件</h3>
                </div>
                <div class="menu">
                <input type="hidden" name="flag" id="flag" value="2">
                <input type="hidden" name="id" id="id" value="${user.id }">
                <input type="hidden" name="bmb" id="bmbpath">
                <input type="hidden" name="zlzg" id="zlzgpath">
                <input type="hidden" name="hzzp" id="hzzppath">
                    <a href="javascript:void(0)" class="up" labtn="bmb">
                        报名表：
                        <input type="button" value="请选择文件">
                    </a>
                    <a href="javascript:void(0)" class="up" labtn="zlzg">
                        在留资格：
                        <input type="button" value="请选择文件">
                    </a>
                    <a href="javascript:void(0)" class="up" labtn="hzzp">
                        护照照片：
                        <input type="button" value="请选择文件">
                    </a>
                </div>
            </div>
            <br>
            </form>
        </div>

        <input type="button" class="table-btn" onclick="sub()" value="提交">

    </div>

</body>
<script src="<%=basePath%>static/js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>larrycms-master/layui/layui.js"></script> 
<script type="text/javascript">
var bmbpath="";
var zlzgpath="";
var hzzppath="";
var sfyjs="${user.sfyjs }";
layui.use([ 'form','laydate','upload','element','jquery','layer'], function(){
 var form = layui.form;
 var laydate=layui.laydate;
 var upload = layui.upload;
 var element = layui.element;
 var $=layui.jquery;
 var layer = layui.layer;
 if(sfyjs!="0"){
 	$("#sfyjs").prop("checked","true");
 }
 	  //执行实例
	  var uploadInst = upload.render({
	    elem: '.up' //绑定元素
	    ,url: '<%=basePath%>xsxx/fileUpload4json' //上传接口
	    ,accept:'file'
	    ,before:function(obj){
	    	layer.load(2);
	    }
	    ,done: function(res){
	      //上传完毕回调
	    	if(res.code==0){
	    	var item = this.item;
	    	$(item).find('input').val(res.myFileName);
		    	//$('#fjtag').append("<lable class='layui-form-label'  id='"+res.myFileName.replace(".","")+"' style='margin-right:10px;margin-top: 5px;padding:9px 9px;background-color:#009688;height:10px;width:auto;line-height:10px;color: #fff;border-radius: 5px;'>"+res.myFileName+"<span  class='guanbispan' lablename='"+res.myFileName+"' ></span></lable>");
		    	//filearr.push(res.filename);
		    	eval($(item).attr('labtn')+"path='"+res.filename+"';");
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

function sub(){
var k = true;
	//校验必填项
	$("[v='bt']").each(function(){
		if(!$(this).val()){
			layer.msg($(this).attr("lable")+"为必填项");
			$(this).focus();
			k= false;
			return false;
		}
	});
	if(!k){
		return false;
	}
		//校验文件上传
	if(bmbpath==''){
		layer.msg("请上传报名表");
		return false;
	}else if(zlzgpath==''){
		layer.msg("请上传在留资格");
		return false;
	}
	//全部校验通过后提交资料
	$("#bmbpath").val(bmbpath);
	$("#zlzgpath").val(zlzgpath);
	$("#form1").submit();
}
</script>
</html>