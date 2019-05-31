<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <link rel="stylesheet" href="<%=basePath %>static/css/stylesheet.css">
</head>
<body onkeydown="keyLogin()">
    
    <div class="header">
        <div class="container">
            <!-- logo -->
            <div class="logo">
                <img src="<%=basePath %>static/images/logo.png" alt="">
                <div class="title">
                    <h1>中国人民对外友好协会</h1>
                    <h2>中国对外友好合作服务中心</h2>
                </div>
            </div>

        </div>
    </div>

    <div class="main">
        <div class="container">

            <!-- wenzi -->
            <div class="content">
                <ul>
                    <li>
                        <em>1、</em>  登录系统的用户名为本人身份号，密码为身份证号码后6位，身份证号 最后一位如果是x，请使用小写x，不要使用大写X； 
                    </li>
                    <li>
                        <em>2、</em> 请务必仔细阅读系统提示和各项操作说明； 
                    </li>
                    <li>
                        <em>3、</em> 系统采集的所有信息涉及到学生派遣等事宜，因此请同学们在指定期限内完成信息的录入。未完成信息采集的学生，无法如期完成其他工作。
                    </li>
                    <li>
                        <em>4、</em> 如有疑问，可咨询本站负责人联系。 
                    </li>
                    <li>
                        <em>5、</em> 由于学生本人不认真阅读系统提示和每一项操作说明导致信息采集失败或错误，进而造成未达到如期派遣，由学生本人自行承担责任。
                    </li>
                </ul>
            </div>
<form id="form1">
            <!-- login -->
            <div class="login-box">
                <h3></h3>
                <div class="form-group">
                    <label for="">用户名：</label>
                    <input type="text" name="sfz" id="username" placeholder="此处输入身份证号">
                </div>
                <div class="form-group">
                    <label for="">密&emsp;码：</label>
                    <input type="password" name="byzd1" id="password" placeholder="此处输入身份证号后六位">
                </div>
                <div class="form-group">
                    <label for="">校验码：</label>
                    <input type="text" id="jym" name="yzm" class="small">
                    <span>
                        <img onclick="createcode()" id="validatecode" src="<%=basePath %>static/img/yanzheng.jpg" />
                    </span>
                </div>
                <div class="form-button">
                    <input type="button" onclick="login()" value="登录">
                    <input type="button" value="重置">
                </div>
            </div>
</form>
        </div>
    </div>

    <div class="footer">
        <div class="container">
            <div class="menu">
                <a href="">关于我们</a>
                <a href="">网站地图</a>
                <a href="">法律声明</a>
            </div>
            <p class="copyright">
                主办：中国人民对外友好合作服务中心
                <span>版权所有</span>
            </p>
            <p class="icpb">
                <span>京公安网备：110105000722</span>
                <span>京ICP备案：05060933号</span>
            </p>
        </div>
    </div>

</body>
<script src="<%=basePath%>static/js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript">
function keyLogin(){
 if (event.keyCode==13)  //回车键的键值为13
   login();
}
function login(){
	if($("#username")&&$("#password")&&$("#jym").val()){
		$.ajax({
                cache: true,
                type: "POST",
                url:"<%=basePath%>xsxx/yz",
                data:$("#form1").serialize(),// 你的formid
                dataType:"json",
                async: false,
                error: function(request) {
                    alert("Connection error");
                    createcode();
                },
                success: function(data) {
                   	if(data.code=='0'){
			    		window.location.href="<%=basePath%>xsxx/eclr";
			    	}else{
				        alert(data.msg);
				        createcode();
			    	}
                }
            });
	}else{
		alert("登录信息填写错误");
		return false;
	}
}
	function createcode() {
		$("#validatecode").attr("src","<%=basePath%>dsna.images?"+new Date().getTime());
	}
	createcode();
</script>
</html>