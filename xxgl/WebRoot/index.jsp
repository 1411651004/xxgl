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
	<title>交易网后台管理</title>
	<link rel="stylesheet" href="<%=basePath %>static/css/layui.css">
</head>
<script src="<%=basePath %>static/layui.js"></script>
<body>
<div class="layui-layout layui-layout-admin" style="">
  <div class="layui-header" style="background-color:#23262E;border-bottom:5px solid #1AA094;">
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <div style="display:inline">
    	<img alt="" src="<%=basePath %>static/img/logo(3).png" style="width:200px;height:59px">
    </div>
    <div style="float:right;display:inline">
    	<ul class="layui-nav" style="background-color:#23262E" lay-filter="">
  <li class="layui-nav-item"><a href=""><i class="iconfont" style="font-size:16px;">&#xe60d;</i>网页全屏</a></li>
  <li class="layui-nav-item layui-this"><a href=""><i class="iconfont" style="font-size:16px;">&#xe652;</i>锁屏</a></li>
  <li class="layui-nav-item"><a href=""><i class="layui-icon" style="font-size:22px;">&#xe640;</i>清除缓存</a></li>
  <li class="layui-nav-item"><a href=""><i class="iconfont" style="font-size:16px;">&#xe888;</i>我的消息</a>
  </li>
  <li class="layui-nav-item"><a href="#"><i class="iconfont" style="font-size:16px;">&#xe6ed;</i>退出</a></li>
</ul>
 
<script>

//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element();
  
  //…
});
</script>
    </div>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <div class="user-info">
                   <div class="photo">
                       <img src="static/img/user.jpg" alt="">
                   </div>
                   <p>admin您好！欢迎登录</p>
      </div>
      <div>
      	<ul class="layui-nav layui-nav-tree" lay-filter="test">
<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
  <li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;" style="background-color:#009688;"><i class="iconfont" style="vertical-align:initial;font-size:20px;">&#xe639;</i>后台首页</a>
  <c:forEach items="${authorutyList }" var= "tmp">
  	<c:if test = "${tmp.parentId=='0' }">
  	</dl>
  		</li>
  		<li class="layui-nav-item">
  			<a href="javascript:;">${tmp.auName }</a>
  				<dl class="layui-nav-child">
  	</c:if>
  	<c:if test="${tmp.parentId!='0' }">
  		<dd><a href="#" data-url="${tmp.url }"><i class="iconfont icon-yonghu1" data-icon='icon-yonghu1'></i><span>${tmp.auName }</span></a></dd>
  	</c:if>
  </c:forEach>
    </dl>
  </li>
</ul>
      </div>
    </div>
  </div>
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="layui-tab" id="larry-tab" lay-filter="larryTab">
             <div class="layui-title-box">
                 <div class="go-left key-press pressKey" id="titleLeft" title="滚动至最右侧"><i class="layui-icon">&#xe603;</i> </div>
                 <ul class="layui-tab-title-body" lay-allowclose="true" id="layui-tab-title" lay-filter="subadd" style="margin-left: 0px;">
                    <li class="layui-this" id="admin-home" lay-id="0" fresh="1">
                        <i class="larry-icon larry-houtaishouye"></i><em style="font-style: normal;">后台首页</em>
                    </li>
                 </ul>
                 <div class="title-right" id="titleRbox">
                    <div class="go-right key-press pressKey" id="titleRight" title="滚动至最左侧"><i class="layui-icon" style="padding-left: 10px;">&#xe602;</i></div> 
                    <div class="refresh key-press" id="refresh_iframe"><i class="iconfont" style="font-size:16px;vertical-align:top">&#xe69a;</i><cite style="font-style: normal;">刷新</cite></div>
                    <div class="often key-press" lay-filter="larryOperate" id="buttonRCtrl">
                        <ul class="layui-nav posr">
                            <li class="layui-nav-item posb">
                              <a class="top"><i class="larry-icon larry-caozuo"></i><cite>常用操作</cite><span class="layui-nav-more"></span></a>
                              <dl class="layui-nav-child">
                                  <dd>
                                      <a data-ename="closeCurrent"><i class="larry-icon larry-guanbidangqianye"></i>关闭当前选项卡</a>
                                  </dd>
                                  <dd>
                                      <a data-ename="closeOther"><i class="larry-icon larry-guanbiqita"></i>关闭其他选项卡</a>
                                  </dd>
                                  <dd>
                                      <a data-ename="closeAll"><i class="larry-icon larry-guanbiquanbufenzu"></i>关闭全部选项卡</a>
                                  </dd>
                                  <dd>
                                      <a data-ename="refreshAdmin"><i class="larry-icon larry-kuangjia_daohang_shuaxin"></i>刷新最外层框架</a>
                                  </dd>
                                </dl>
                            </li>
                        <span class="layui-nav-bar"></span></ul>
                    </div>
                    
                 </div>
             </div>
             <div class="layui-tab-content" style="height: 458px;">
                  <div class="layui-tab-item layui-show">
                      <iframe class="larry-iframe" data-id="0" name="ifr_0" id="ifr0" src="html/main.php" style="height: 458px;"></iframe>
                  </div>
             <div class="layui-tab-item"><iframe src="html/personInfo.html" data-id="1" name="ifr_1" id="ifr1" class="larry-iframe" style="height: 458px;"></iframe></div></div>
        </div>
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
  </div>
</div>
<script>
//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element();
  
  //…
});
</script>
</body>
</html>
