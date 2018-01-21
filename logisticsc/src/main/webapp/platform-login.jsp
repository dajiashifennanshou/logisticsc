<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" /> 
<title>货运交易系统</title>
<link rel="stylesheet" href="resources/platform/css/login/wuliu-login-qiantai.css"/>
<link rel="stylesheet" href="resources/platform/css/login/font-awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet" href="resources/platform/css/login/font-awesome-3.2.1/css/font-awesome-ie7.min.css" />
<script type="text/javascript" src="resources/platform/jquery/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="resources/platform/jquery/placeholderfriend.js" ></script>
</head>
<script type="text/javascript">
	//请求验证码
	function myReload(){  
	    document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src + "?nocache="+new Date().getTime();  
	}
	//页面加载
    $(function(){
    	/*获取cookie值*/
		if (getNamePassword("userName") != "") {
			$("#username").val(getNamePassword("userName"));
			$("#password").val(getNamePassword("pwd"));
		}
    	 // 表单回车事件
        $('#theForm').keydown(function(e){
            if( e.keyCode == 13 ){
            	login();
            }
        });
    });
	//登陆
	function login() {
		//登录成功
		var checkbox = document.getElementById('jizhuwo');//
		if(checkbox.checked){
    		var userName = $("#username").val();
			var pwd = $("#password").val();
			NamePassword("userName", "pwd", userName, pwd);
		 }

		var username=$("#username").val();
        var password=$("#password").val();
        var reCode=$("#checkCode").val();
		if(null == username ||"" == username){
			$("#promptInfo").text("用户名不能为空！");
			myReload();
			return;
		}else if(null == password ||"" == password){
			$("#promptInfo").text("密码不能为空！");
			myReload();
			return;
		}else if(null == reCode ||"" == reCode){
			$("#promptInfo").text("验证码不能为空！");
			myReload();
			return;
		}
        $.ajax({
            url : "user/loginUser.shtml",
            type : 'POST',
            data : {"loginName":username,"password":password,"reCode":reCode},
            dataType : 'json',
            success:function(data){
                if (data.result) {
                    window.location = "deliverGoods/deliverGoods.shtml?chooseid='fahuo'";
                } else {
                   $("#promptInfo").text(data.msg);
               		myReload();
                }
            }
        });
	}
	//记住密码
	function NamePassword(name, passord, nameValue, pwdValue) {
		var Days = 30; //此 cookie 将被保存 30 天
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(nameValue) + ";expires=" + exp.toGMTString();
		document.cookie = passord + "=" + escape(pwdValue) + ";expires=" + exp.toGMTString();
	}
	//找到cookie
	function getNamePassword(name) {
		var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
		if (arr != null) return unescape(arr[2]);
		return '';
	}
</script>
<body>
	<div class="top">
			<div class="top-content">
				<!-- <div class="logo f">
                    <img src="/logisticsc/resources/platform/img/logo-1.png" />
                </div>-->
                <!--
                <div class="dh f">
                    <span>
                        <i class="icon-phone zhuce-dh"></i>
                        18000510004
                    </span>
                </div>
                -->
				 <div class="denglu">
				 	<span>
				 		<i class="icon-double-angle-left"></i>
				 		<a href="main.jsp">回到首页</a>
				 	</span>
				 </div>
			</div>
		</div>
		<div class="content" id="theForm">
			<!--<div class="bg">
				<img alt="" src="/logisticsc/resources/platform/img/3.png">
			</div> -->
			<div class="container">
				<div style="text-align: center;margin-top: 15px;"><img src="/logisticsc/resources/platform/img/logo-1.png" alt=""></div>
				<div class="tips-notice">
					<i class="icon-volume-up tubiao1 f"></i>
					<div class="tips-content f">
						<span id="promptInfo">请输入正确的用户名和密码。</span>
					</div>
				</div>
				<div>
					<input type="text"  id="username" class="username" placeholder="请输入用户名"/>
				</div>
				<div>
			 <input type="password" id="password" class="username" placeholder="请输入密码"/> 
				</div>
				<input type="text" id="checkCode" class="username yzm f" placeholder="请输入验证码"/>
				<img id="createCheckCode" class="f" src="user/getVerCode.shtml" onclick="myReload()" width="90" height="37"
					style="margin-top: 15px; margin-left: 27px;">
				<div class="bottom1">
					<input type="checkbox" id="jizhuwo" class="f fwbk" />
					<label class="f jz">记住我</label>
					<a href="user/jumpreGister.shtml" class="f">注册账号</a>
					<a href="user/jumpreForgetPassword.shtml" class="f">忘记密码?</a>
					<div class="btn1" style="float: right;" onclick="login()">
						登陆
					</div>
				</div>
			</div>
		</div>
		<div class="bottom2">
			 © Copyright 2013-2017    粤ICP备15048227号-1    中工储运    版权所有
		</div>
</body>
</html>