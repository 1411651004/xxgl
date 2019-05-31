<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link rel="stylesheet" href="<%=basePath %>larrycms-master/layui/css/layui.css">
    <style type="text/css">
    .layui-form-label{
    	width:120px
    }
    </style>
</head>
<body>
    <c:if test="${user.sta!='1' && user.sta!='2' }">
      <blockquote class="layui-elem-quote">${user.xm}同学，您的基本资料已经通过审核，请完善以下资料。</blockquote>
    </c:if>
    <c:if test="${user.sta=='1' }">
      <blockquote class="layui-elem-quote">${user.xm}同学，您已二次录入完成，请耐心等待审核。</blockquote>
    </c:if>
    <c:if test="${user.sta=='2' }">
      <blockquote class="layui-elem-quote">${user.xm}同学，二次录入审核通过。</blockquote>
    </c:if>
    <form id="form1" name="form1" accept-charset="UTF-8" action="<%=basePath%>xsxx/add" method="post" class="layui-form">
    <div class="layui-collapse">
	  <div class="layui-colla-item">
	    <h2 class="layui-colla-title">基本信息 （内容部分是您本人第一次录入时的资料，如有变动或录入错误请点击修改按钮进行修改）</h2>
	    <div class="layui-colla-content layui-show">
			
	    	<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">中文姓名</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="xm" value="${user.xm }" id="xm" lay-verify="required" placeholder="请输入中文姓名" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">英文姓名</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="ywxm" value="${user.ywxm }" id="ywxm" lay-verify="required" placeholder="请输入英文姓名" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">日文姓名</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="rwxm" value="${user.rwxm }" id="rwxm" lay-verify="required" placeholder="フリガナ" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			</div>
			
			<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="xb" value="${user.xb }" id="xb" lay-verify="required" placeholder="请输入性别" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">民族</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="mz" value="${user.mz }" name="mz" lay-verify="required" placeholder="请输入民族" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">学校</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="xx"  id="xx" lay-verify="required" lay-search="">
			        <option value="">直接选择或搜索选择学校</option>
			      	<c:forEach items="${qyxxlist}" var="qyxx" varStatus="sta">
				      	<option value="${qyxx.mc }">${qyxx.mc }</option>
			      	</c:forEach>
			      </select>
			    </div>
			  </div>
			</div>
			
			<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">专业</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input readonly="readonly" type="text" name="zy" value="日本语专攻" id="zy" lay-verify="required" placeholder="请输入专业" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">身份证号码</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="sfz" value="${user.sfz}" id="sfz" lay-verify="required|identity" placeholder="请输入身份证号码" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">电话</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="dh" value="${user.dh }" id="dh" lay-verify="required|phone" placeholder="请输入电话" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			</div>
			
		</div>
	  </div>
	   <div class="layui-colla-item">
	    <h2 class="layui-colla-title">护照信息 （如果为空，请填写无）</h2>
		    <div class="layui-colla-content layui-show">
		    	<div class="layui-form-item">
		    		<div class="layui-inline">
					    <label class="layui-form-label">是否有护照</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <select name="sfyhz"  lay-filter="sfyhz" id="sfyhz" lay-verify="required" lay-search="">
					      	<option value="">请选择</option>
					      	<option value="是">是</option>
					      	<option value="否">否</option>
					      </select>
					    </div>
					  </div>
					  <div class="layui-inline">
					    <label class="layui-form-label">婚姻情况</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <select name="hyqk" id="hyqk" lay-verify="required" lay-search="">
					      	<option value="">请选择</option>
					      	<option value="未婚">未婚</option>
					      	<option value="既婚">既婚</option>
					      </select>
					    </div>
					  </div>
					  <div class="layui-inline" id="myhz" style="display:none">
					    <label class="layui-form-label">取得预定日</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <input type="text" name="qdydr" value="${user.qdydr }" id="qdydr" placeholder="请选择取得预定日" autocomplete="off" class="layui-input">
					    </div>
					  </div>
		    	</div>
		    	<!-- 是否有护照选择是 -->
		    	<div class="layui-form-item" id="cyhz" style="display:none">
		    		<div class="layui-inline">
					    <label class="layui-form-label">护照号码</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <input type="text" name="hzhm" value="${user.hzhm }" id="hzhm" placeholder="请输入护照号码" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-inline">
					    <label class="layui-form-label">有效期至</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <input type="text" name="hzyxq" value="${user.hzyxq }" id="hzyxq" placeholder="请输入护照有效期" autocomplete="off" class="layui-input">
					    </div>
					  </div>
		    	</div>
		    	<div class="layui-form-item">
		    		<div class="layui-inline">
					    <label class="layui-form-label">是否去过日本</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <select name="sfqgrb" lay-filter="sfqgrb" id="sfqgrb" lay-verify="required" lay-search="">
					      	<option value="">请选择</option>
					      	<option value="是">是</option>
					      	<option value="否">否</option>
					      </select>
					    </div>
					  </div>
					  <div class="layui-inline" id="cs" style="display:none">
					    <label class="layui-form-label">去日本的次数</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <input type="text" name="qrbcs" value="${user.qrbcs }" id="qrbcs" placeholder="请输入去日本的次数" autocomplete="off" class="layui-input">
					    </div>
					  </div>
					  <div class="layui-inline" id="zhsj" style="display:none">
					    <label class="layui-form-label">最近一次去<br>日本的时间</label>
					    <div class="layui-input-inline" style="width: 200px;">
					      <input type="text" name="zhfrsj" value="${user.zhfrsj }" id="zhfrsj" placeholder="请选择最后赴日时间" autocomplete="off" class="layui-input">
					    </div>
					  </div>
		    	</div>
		    	
		    </div>
	   </div>
	  <div class="layui-colla-item">
	    <h2 class="layui-colla-title">学籍信息 （如果为空，请填写无）</h2>
	    <div class="layui-colla-content layui-show">
	    	<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">入学年份</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="rxnf" value="${user.rxnf }" lay-verify="required" id="rxnf" placeholder="请选择入学年份" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">年级</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="nj" id="nj" lay-verify="required" lay-filter="nj" lay-search="">
			        
			      </select>
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">学制</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="xz" id="xz" lay-verify="required" lay-search="">
			        <option value="">直接选择或搜索选择</option>
			      	<option value="2">两年制</option>
			      	<option value="3">三年制</option>
			      	<option value="4">四年制</option>
			      </select>
			    </div>
			  </div>
			</div>
			<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">学历</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="sfyjs" id="sfyjs" lay-verify="required" lay-filter="sfyjs" lay-search="">
			        <option value="">直接选择或搜索选择</option>
			      	<option value="专科">专科</option>
			      	<option value="本科">本科</option>
			      	<option value="研究生">研究生</option>
			      	<option value="博士">博士</option>
			      </select>
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">季度</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="jd" id="jd" lay-verify="required" lay-search="">
			        <option value="">直接选择或搜索选择</option>
			      	<c:forEach items="${jdList}" var="jd" varStatus="sta">
				      	<option value="${jd.id }">${jd.codevalue }</option>
			      	</c:forEach>
			      </select>
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">实习时间</label>
			    <div class="layui-input-inline" style="width: 200px;">
			       <select name="sxsj" id="sxsj" lay-verify="required" lay-search="">
			        <option value="">直接选择或搜索选择</option>
			      	<option value="三个月">三个月</option>
			      	<option value="六个月">六个月</option>
			      	<option value="九个月">九个月</option>
			      </select>
			    </div>
			  </div>
			</div>
	    </div>
	  </div>
	  <div class="layui-colla-item">
	    <h2 class="layui-colla-title">报名表（请先下载报名表模板进行填写）</h2>
	    <div class="layui-colla-content layui-show">
	    	<a href="<%=basePath %>uploadFile/template/赴日社会実践报名表(需证件照）.doc" class="layui-btn">报名表模板</a>
	    	<c:if test="${!empty  user.bmb }">
	    	<a href="<%=basePath %>uploadFile/${user.bmb}" class="layui-btn">报名表</a>下载后请去掉文件名的后六位<br>
	    	</c:if>
	    	<br>
	    	<div class="layui-upload-drag" id="scbmb" style="margin-top:10px">
			  <i class="layui-icon"></i>
			  <p id="wa">点击上传，或将文件拖拽到此处</p>
			</div>
			<input type="hidden" name="bmb" id="bmb" value="${user.bmb }">
			<input type="hidden" name="flag" id="flag" value="2">
			
	    </div>
    </div>
	  <div class="layui-colla-item">
	    <h2 class="layui-colla-title">家庭信息 （如果为空，请填写无）</h2>
	    <div class="layui-colla-content layui-show">
	    	<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">父亲姓名</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="fqxm" value="${user.fqxm }" id="fqxm" lay-verify="required" placeholder="若为单亲家庭，请填写无" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">父亲联系电话</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="fqlxdh" value="${user.fqlxdh }" id="fqlxdh" lay-verify="fatherNumber" placeholder="请输入父亲联系电话" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			</div>
			<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">母亲姓名</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="mqxm" value="${user.mqxm }" id="mqxm" autocomplete="off" lay-verify="required" placeholder="若为单亲家庭，请填写无" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">母亲联系电话</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="mqlxdh" value="${user.mqlxdh }" id="mqlxdh" autocomplete="off" lay-verify="motherNumber" placeholder="请输入母亲联系电话" class="layui-input">
			    </div>
			  </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">家庭地址</label>
			    <div class="layui-input-block">
			      <input type="text" value="${user.jtdz }" name="jtdz" id="jtdz" style="width: 574px;" lay-verify="required" placeholder="请输入家庭地址" autocomplete="off" class="layui-input">
			    </div>
			  </div>
	    </div>
	  </div>
	  <div class="layui-colla-item">
	    <h2 class="layui-colla-title">个人信息 （如果为空，请填写无）</h2>
	    <div class="layui-colla-content layui-show">
	    	<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">身高</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="sg" value="${user.sg }" id="sg" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">体重</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="tz" value="${user.tz }" id="tz" lay-verify="required|number" placeholder="单位:kg" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">胸围</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="xw" value="${user.xw }" id="xw" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			</div>
			<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">腰围</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="yw" value="${user.yw }" id="yw" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">臀围</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <input type="text" name="tw" id="tw" value="${user.tw }" lay-verify="required|number" placeholder="单位:cm" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-inline">
			    <label class="layui-form-label">鞋号</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="xh" id="xh" lay-verify="required" lay-search="">
			        <option value="">直接选择或搜索选择</option>
			      	<option value="S">35号---22.5cm</option>
			      	<option value="M">36号---23cm</option>
			      	<option value="L">37号---23.5cm</option>
			      	<option value="XL">38号---24cm</option>
			      	<option value="XXL">39号---24.5cm</option>
			      	<option value="XXXL">40号---25cm</option>
			      	<option value="XXXL">41号---25.5cm</option>
			      	<option value="XXXL">42号---26cm</option>
			      </select>
			    </div>
			  </div>
			</div>
			<div class="layui-form-item">
			  <div class="layui-inline">
			    <label class="layui-form-label">衣服尺寸</label>
			    <div class="layui-input-inline" style="width: 200px;">
			      <select name="yfcc" id="yfcc"  lay-verify="required" lay-search="">
			        <option value="">直接选择或搜索选择</option>
			      	<option value="S">S</option>
			      	<option value="M">M</option>
			      	<option value="L">L</option>
			      	<option value="XL">XL</option>
			      	<option value="XXL">XXL</option>
			      	<option value="XXXL">XXXL</option>
			      </select>
			    </div>
			  </div>
			</div>
			
	    </div>
	  </div>
	</div>
	<div class="layui-form-item" style="margin:0 auto">
    <div class="layui-input-block" id="status_submitBtn">
      <input type="button" class="layui-btn" lay-submit lay-filter="formDemo" value="立即提交">
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
    </form>
    
    <div style="display: none;">
      <!-- 0-已初次录入，10-已发初次审核，11-已发送短信，1二次录入完成，2-审批完毕  -->
      <input hidden="hidden" id="stuStatus" value="${user.sta }"/>
      <!-- 不显示的字段 -->
      <input hidden="hidden" id="stuProNotShow" value="${stuProNotShows }"/>
    </div>
</body>
<script src="<%=basePath%>static/js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>larrycms-master/layui/layui.js"></script> 
<script type="text/javascript">



layui.use(['form','element','upload','laydate'] ,function(){
  var form = layui.form;
  var element = layui.element;
  var laydate = layui.laydate;
  var upload = layui.upload;
  
	//录入状态
	var stuStatus = $("#stuStatus").val();
	//不显示的字段
	var stuProNotShow = $("#stuProNotShow").val();
	//隐藏
	if(stuProNotShow&& stuStatus != "11") {
		var arrNotShow = stuProNotShow.split(",");
		for(var i = 0; i < arrNotShow.length; i++) {
			$("input[name='"+arrNotShow[i]+"']").parent().parent().hide();
			$("select[name='"+arrNotShow[i]+"']").parent().parent().hide();
		}
		$(".layui-form-item").hide();
		$(".layui-form-item").each(function(){
			$(this).find(".layui-inline").each(function(){
				if($(this).css('display') != "none") {
					$(this).parent().show();
				}
			});
		});
		$(".layui-colla-content").parent().hide();
		$(".layui-colla-content").each(function(){
			$(this).find(".layui-form-item").each(function(){
				if($(this).css('display') != "none") {
					$(this).parent().parent().show();
				}
			});
		});
	}
	upload.render({
		  elem: '#scbmb',
		  url:'<%=basePath%>xsxx/fileUpload4json',
		  accept:'file',
		  exts:'doc|docx'
		  ,done: function(res, index, upload){
		    if(res.code==0){
			    	var item = this.item;
				    	layer.closeAll('loading');
				    	layer.msg(res.msg);
				    	$("#bmb").val(res.filename);
				    	$("#wa").text("文件上传成功！");
			    	}else if(res.code!=0){
			    		layer.closeAll('loading');
			    		layer.msg(res.msg);
			    	}
		  }
		});
	//只读
	if(stuStatus != "11" ) {
		$("input").attr("readOnly",true);
		$("select").attr("disabled",true);
		$("#rxnf").attr("disabled",true);
		$("#qdydr").attr("disabled",true);
		$("#zhfrsj").attr("disabled",true);
		$("#hzyxq").attr("disabled",true);
		// 隐藏按钮
		$("#status_submitBtn").hide();
		$("#scbmb").hide();
	}
  
  
  
  
  
    //年选择器
  laydate.render({
    elem: '#rxnf'
    ,type: 'year',
    format: 'yyyy年' //可任意组合
  });
  //常规用法
  laydate.render({
    elem: '#qdydr',
    format: 'yyyy年MM月dd日' //可任意组合
  });
   //常规用法
   //日期时间范围选择
laydate.render({ 
  elem: '#zhfrsj'
  ,range: true //或 range: '~' 来自定义分割字符
});
     //常规用法
  laydate.render({
    elem:'#hzyxq',
    format: 'yyyy年MM月dd日' //可任意组合
  });
  
//是否为研究生
  $("#sfyjs").val('${user.sfyjs}');
  function sfyjsSelect(data) {
	  var njVal = $("#nj").val();
	  if(data.value == "研究生") {
		  $("#nj").html("<option value=\"\">直接选择或搜索选择</option><option value=\"1\">大学院1年生</option><option value=\"2\">大学院2年生</option><option value=\"3\">大学院3年生</option><option value=\"4\">大学院4年生</option>");
	  } else {
		  $("#nj").html("<option value=\"\">直接选择或搜索选择</option><option value=\"1\">1年生</option><option value=\"2\">2年生</option><option value=\"3\">3年生</option><option value=\"4\">4年生</option>");
	  }
	  if(njVal) {
	      $("#nj").val(njVal);
	  }
	  form.render();
  }
  sfyjsSelect({value:$("#sfyjs").val()});
 //是否为研究生
 form.on('select(sfyjs)', sfyjsSelect);
  
  $("#xx").val('${user.xx}');
  $("#sfyhz").val('${user.sfyhz}');
  $("#hyqk").val('${user.hyqk}');
  $("#sfqgrb").val('${user.sfqgrb}');
  $("#xz").val('${user.xz}');
  $("#nj").val('${user.nj}');
  $("#jd").val('${user.jd}');
  $("#sxsj").val('${user.sxsj}');
  $("#xh").val('${user.xh}');
  $("#yfcc").val('${user.yfcc}');
 
  
  if('${user.sfyhz}'=='是'){
  		$("#cyhz").css("display","block");
  		$("#myhz").css("display","none");
  	}else if('${user.sfyhz}'=='否'){
  		$("#cyhz").css("display","none");
  		$("#myhz").css("display","");
  	}else if('${user.sfyhz}'==''){
  		$("#cyhz").css("display","none");
  		$("#myhz").css("display","none");
  	}
  if('${user.sfqgrb}'=='是'){
  		$("#cs").css("display","");
  		$("#zhsj").css("display","");
  	}else if('${user.sfqgrb}'=='否'){
  		$("#cs").css("display","none");
  		$("#zhsj").css("display","none");
  	}else if('${user.sfqgrb}'==''){
  		$("#cs").css("display","none");
  		$("#zhsj").css("display","none");
  	}
  
	form.verify({
	    fatherNumber : function(value, item){
	    	if("无" == $("#fqxm").val()) {
	    		item.value = "";
	    	} else {
		    	if(!value){
		    		return "请填写父亲电话号码";
		    	}
		        if(!(/^1[34578][0-9]\d{8}$/.test(value))) {
		        	return "请填写正确的电话号码";
		        }
	    	}
	    },
	    motherNumber : function(value, item){
	    	if("无" == $("#mqxm").val()) {
	    		item.value = "";
	    	} else {
		    	if(!value){
		    		return "请填写母亲电话号码";
		    	}
		        if(!(/^1[34578][0-9]\d{8}$/.test(value))) {
		        	return "请填写正确的电话号码";
		        }
	    	}
	    }
	});
  	form.render();
  form.on('select(sfyhz)', function(data){
  	if(data.value=='是'){
  		$("#cyhz").css("display","block");
  		$("#myhz").css("display","none");
  	}else if(data.value=='否'){
  		$("#cyhz").css("display","none");
  		$("#myhz").css("display","");
  	}else if(data.value==''){
  		$("#cyhz").css("display","none");
  		$("#myhz").css("display","none");
  	}
  });
  //
    form.on('select(sfqgrb)', function(data){
  	if(data.value=='是'){
  		$("#cs").css("display","");
  		$("#zhsj").css("display","");
  	}else if(data.value=='否'){
  		$("#cs").css("display","none");
  		$("#zhsj").css("display","none");
  	}else if(data.value==''){
  		$("#cs").css("display","none");
  		$("#zhsj").css("display","none");
  	}
  });
  
  
  //监听提交
  form.on('submit(formDemo)', function(data){
  
  	if($("#bmb").val()==''){
  		layer.alert("请上传报名表");
  		return false;
  	}
    $("#form1").submit();
    return false;
  });
  
});
</script>
</html>