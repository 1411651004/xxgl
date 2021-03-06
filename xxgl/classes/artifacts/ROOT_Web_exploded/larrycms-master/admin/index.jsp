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
    <title>学生信息采集系统</title>
	<meta name="keywords" content="学生信息采集系统" />
    <meta name="description" content="学生信息采集系统" />
	<meta name="Author" content="larry" />
	<meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="Shortcut Icon" href="/favicon.ico" />
	<!-- load css -->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/common/css/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/font.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>larrycms-master/admin/css/admin.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
	<!-- 顶部区域 -->
	<div class="layui-header header-menu">
		<div class="logo posb" id="log"><%-- <img src="<%=basePath %>larrycms-master/common/images/logo.png"> --%></div>
		<div class="layui-main posb">
		  	<!-- 左侧导航收缩开关 -->
		  	<div class="side-menu-switch posb" id="toggle"><span class="switch"  ara-hidden="true"></span></div>
            <!-- 顶级菜单 -->
            <div class="larry-top-menu posb">
            	<ul class="layui-nav clearfix" id="menu">
            	</ul>
            </div>
            <!-- 右侧常用菜单导航 -->
            <div class="larry-right-menu posb">
                <!--<button class="layui-btn layui-btn-small" id="dianzhan">-->
                	<!--<i class="larry-icon larry-dianzan"></i>-->
                	<!--打赏作者-->
                <!--</button>-->
                <ul class="layui-nav clearfix">
                    <li class="layui-nav-item">
                        <a class="onFullScreen" id="FullScreen"><i class="larry-icon larry-quanping"></i>全屏</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="lock"><i class="larry-icon larry-diannao5"></i>锁屏</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="clearCached"><i class="larry-icon larry-qingchuhuancun"></i>清除缓存</a>
                    </li>
                    <li class="layui-nav-item">
                        <a id="larryTheme"><i class="larry-icon larry-theme1"></i>设置季度</a>
                    </li>
                    
                    <li class="layui-nav-item exit">
                        <a  id="logout"><i class="larry-icon larry-exit"></i><cite>退出</cite></a>
                    </li>
                </ul>
            </div>
		</div>
	</div>
	<!-- 左侧导航 -->
	<div class="layui-side larrycms-left" id="larry-side">
		 <div class="layui-side-scroll" >
              <div class="sys-menu-box" >
                   <ul class="layui-nav layui-nav-tree" id="larrySideNav" lay-filter="side" >
<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
<li class="layui-nav-item layui-this">
  </li>
                   </ul>
              </div>
         </div>
	</div>
    <!-- 右侧主题内容 -->
    <div class="layui-body" id="larry-body">
        <div class="layui-tab" id="larry-tab" lay-filter="larryTab">
             <div class="larry-title-box">
                 <div class="go-left key-press pressKey" id="titleLeft" title="滚动至最右侧"><i class="larry-icon larry-weibiaoti6-copy"></i> </div>
                 <ul class="layui-tab-title" lay-allowClose="true" id="layui-tab-title" lay-filter="subadd">
                    <li class="layui-this" id="admin-home"  lay-id="0" fresh=1>
                        <i class="larry-icon larry-houtaishouye"></i><em>后台首页</em>
                    </li>
                 </ul>
                 <div class="title-right" id="titleRbox">
                    <div class="go-right key-press pressKey" id="titleRight" title="滚动至最左侧"><i class="larry-icon larry-right"></i></div> 
                    <div class="refresh key-press" id="refresh_iframe"><i class="larry-icon larry-shuaxin2"></i><cite>刷新</cite></div>
                    <div class="often key-press" lay-filter='larryOperate' id="buttonRCtrl">
                        <ul class="layui-nav posr">
                            <li class="layui-nav-item posb">
                              <a class="top"><i class="larry-icon larry-caozuo"></i><cite>常用操作</cite></a>
                              <dl class="layui-nav-child">
                                  <dd>
                                      <a  data-eName="closeCurrent"><i class="larry-icon larry-guanbidangqianye"></i>关闭当前选项卡</a>
                                  </dd>
                                  <dd>
                                      <a  data-eName="closeOther"><i class="larry-icon larry-guanbiqita"></i>关闭其他选项卡</a>
                                  </dd>
                                  <dd>
                                      <a  data-eName="closeAll"><i class="larry-icon larry-guanbiquanbufenzu"></i>关闭全部选项卡</a>
                                  </dd>
                                  <dd>
                                      <a  data-eName="refreshAdmin"><i class="larry-icon larry-kuangjia_daohang_shuaxin"></i>刷新最外层框架</a>
                                  </dd>
                                </dl>
                            </li>
                        </ul>
                    </div>
                    
                 </div>
             </div>
             <div class="layui-tab-content">
                  <div class="layui-tab-item layui-show">
                      <iframe class="larry-iframe" data-id='0' name="ifr_0" id='ifr0' src="<%=basePath %>login/main"></iframe>
                  </div>
             </div>
        </div>
    </div>
    <!-- footer -->
    <div class="layui-footer layui-larry-foot psob" id="larry-footer">
    	   <div class="layui-main">
               
         </div>
    </div>
    <!-- footer end -->
    <!-- layui-layout-admin end -->
</div>

<!-- 主题配置 -->
<div class="larryThemeContent" id="LarryThemeSet" style="display:none;">
    <input id="jdId" hidden="hidden" value="<%=session.getAttribute("jdId") %>" />
    <div class="larry-theme-form">
        <h3>季度设置</h3>
        <form class="layui-form" style="position: fixed;margin-top:25px;width: 450px;">
            <div class="layui-form-item select-theme">
                <label class="layui-form-label">当前季度</label>
                <div class="layui-input-block">
                    <select lay-filter="jd" id="jd" lay-search>
                      <option value="larry">第一季度</option>
                      <option value="A">第一季度</option>
                      <option value="B">第一季度</option>
                      <option value="larry_">第一季度</option>
                    </select>     
                </div>
            </div>
            <!-- 
             <div class="layui-form-item fullscreen">
                <label class="layui-form-label">是否全屏</label>
                <div class="layui-input-block">
                    <input type="checkbox" lay-filter="fullscreen" lay-skin="switch"  value="1">
                </div>
            </div> 
             -->
            <div class="layui-form-item submit-form">
                 <button id="LarryThemeSetSubmit" lay-submit lay-filter="submitlocal1" class="layui-btn larry-button">确定</button>
                 <button id="LarryThemeSetCancel" lay-submit lay-filter="submitlocal1" class="layui-btn">取消</button>
            </div>
        </form>
    </div>
</div>
<!-- layui-body常用菜单定义 -->
<div class="rightMenu" id="rightMenu" style="display: none;">
   <ul>
        <li data-target="refreshCur">
            <i class="larry-icon " ></i>刷新当前页面
        </li>
        <li data-target="refreshKj">
            <i class="larry-icon " ></i>刷新外层框架
        </li>
        <li data-target="closeCurrent">
            <i class="larry-icon " ></i>关闭当前选项卡
        </li>
        <li data-target="closeOther">
            <i class="larry-icon " ></i>关闭其他选项卡
        </li>
        <li data-target="closeAll">
            <i class="larry-icon " ></i>全部关闭选项卡
        </li>
   </ul>
</div>
<!-- 屏幕锁屏 -->
<div class="lock-screen" style="display: none;">
   <div class="lock-wrapper" id="lock-screen">
        <div id="time"></div>
        <div class="lock-box">
             <img src="images/user.jpg" alt="">
             <h1>admin</h1>
             <form action="" class="layui-form lock-form">
                  <div class="layui-form-item">
                       <input type="password" name="lock_password" lay-verify="pass" placeholder="锁屏状态，请输入密码解锁" autocomplete="off" class="layui-input"  autofocus="">
                  </div>
                  <div class="layui-form-item">
                       <button class="layui-btn larry-btn" id="unlock">立即解锁</button>
                  </div>
             </form>
        </div>
   </div>
</div>



<!-- 加载js文件-->
<script type="text/javascript" src="<%=basePath %>larrycms-master/common/layui/layui.js"></script> 
<script type="text/javascript" src="<%=basePath %>larrycms-master/admin/js/larrycms.js"></script>
</body>
</html>
