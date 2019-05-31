<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

	String name="";  
    String psw="";  
    Cookie[] cookies=request.getCookies();  
    if(cookies!=null&&cookies.length>0){   
        //遍历Cookie  
        for(int i=0;i<cookies.length;i++){  
            Cookie cookie=cookies[i];  
            //此处类似与Map有name和value两个字段,name相等才赋值,并处理编码问题   
            if("name".equals(cookie.getName())){  
                name=java.net.URLDecoder.decode(cookie.getValue(),"utf-8");  

            }  
            if("psw".equals(cookie.getName())){  
                psw=cookie.getValue();  
            }  
        }  
    }  


 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link href="<%=basePath %>static/css/login.css" rel="stylesheet" type="text/css" />
</head>
	<script src="<%=basePath%>static/js/jquery-1.8.3.js" type="text/javascript"></script> 
	<script src="<%=basePath%>static/js/jquery.form.min.js" type="text/javascript"></script> 
	<script type="text/javascript">
	gologin();
	var name="<%=name%>";
	var psw="<%=psw%>";
	function KeyDown()
	{
	  if (event.keyCode == 13)
	  {
	    event.returnValue=false;
	    event.cancel = true;
	    sub();
	  }
	}
	
		function sub(){
			if(name!=$("#loginName").val()){
				$("#iscookie").val("0");
			}
			$.ajax({
                cache: true,
                type: "POST",
                url:"<%=basePath%>login/login",
                data:$("#form1").serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                   	if(data.length==0){
			    		window.location.href="<%=basePath%>larrycms-master/admin/index.jsp";
			    	}else{
				        alert(data);
				        $("#yzm").val('请输入验证码');
				        createcode();
			    	}
                }
            });
		}
	</script>
  <body onkeydown="KeyDown()">
<img src="<%=basePath %>static/img/login_bg.jpg" alt="" width="100%" height="100%" class="bg-img">
<form id="form1" action="">
 <div class="login">
 <%-- <div class="login_logo"><img src="<%=basePath %>static/img/login_logo.png" /></div> --%>
 <input type="hidden" id = "iscookie" value="0" name="iscookie">
 <div class="user" style="padding-top: 10%;"><i class="login_icon"><img src="<%=basePath %>static/img/login_icon01.png" /></i><input name="loginName" id="loginName" type="text" class="user_input" placeholder="请输入用户名" /></div>
 <div class="user"><i class="login_icon"><img src="<%=basePath %>static/img/login_icon02.png" /></i><input name="loginPwd" id="loginPwd" type="password" class="user_input" placeholder ="请输入密码"/></div>
 <div class="user"><i class="login_icon"><img src="<%=basePath %>static/img/login_icon03.png" /></i><input name="yzm" id="yzm" type="text" class="user_yz" placeholder ="请输入验证码" /><span class="yz"><img onclick="createcode()" id="validatecode" src="<%=basePath %>static/img/yanzheng.jpg" /></span></div>
 <div class="remember" style="padding-left: 30px;padding-top: 10px;"><!-- <input class="login_np" type="checkbox" id = "savepwd" name="savepwd" value="1">记住密码 --></div>
 <div class="login_but" onclick="sub()" >登录</div>
 <div class="remember" style="margin-left:42%;padding-bottom: 6%;margin-top: 5%;"><a style="color:#c1f1fd" href = "<%=basePath%>">用户名admin密码admin</a></div>
 </div>
 </form>
 
 <div class="login_bottom" style="display:none">
  <div class="login_bot_a">
   <div class="login_bot_l">24小时客服热线：<p>400-400-8181</p>地址：北京市海淀区信息路</div>
   <div class="login_bot_r">
     <ul>
       <li>官方微信<img src="<%=basePath %>static/img/weixin01.jpg" />扫描关注微信</li>
       <li>交易平台APP<img src="<%=basePath %>static/img/weixin01.jpg" />扫描下载APP</li>
     </ul>
   </div>
 </div>
 </div>
  </body>
  <script type="text/javascript">
	function createcode() {
		$("#validatecode").attr("src","<%=basePath%>dsna.images?"+new Date().getTime());
	}
	createcode();
	
	if(name.length!=0&&psw.length!=0){
		document.getElementById("loginName").value=name;
		document.getElementById("loginPwd").value=psw;
		document.getElementById("iscookie").value="1";
		document.getElementById("savepwd").checked = true;
	}
  
  </script>
</html>
