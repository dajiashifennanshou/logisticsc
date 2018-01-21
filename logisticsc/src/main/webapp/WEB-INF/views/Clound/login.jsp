<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>云仓用户登录</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="/logisticsc/Clound/css/bootstrap.min.css" />
<link rel="stylesheet" href="/logisticsc/Clound/css/camera.css" />
<link rel="stylesheet" href="/logisticsc/Clound/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/logisticsc/Clound/css/matrix-login.css" />
<link href="/logisticsc/Clound/css/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/logisticsc/Clound/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
	function login(form){
		var user=$('#username').val();
		var pwd=$('#password').val();
		
		var Admininfo = {
    			"username" : user,
    			"password" : pwd
    	};
		if(valited()){
			$.ajax({
				type:"post",
				url:"ycLogin.yc",
				dataType:'json',
				async: false,
				contentType : "application/json;charset=UTF-8",
				data:JSON.stringify(Admininfo),
				success:function(data){
					if(data.code==0){
						window.location="yc-index.yc";
					}else{
						alert(data.msg);
					}
				},
				error:function(){
					alert('异常');
				}
			});
		}else{
			alert("用户名或密码不能为空..");
		}
	}
	function valited(){
		var user=$('#username').val();
		var pwd=$('#password').val();
		if(user && pwd){
			return true;
		}
		return false;
	}
</script>

</head>
<body>

	<div style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<div id="loginbox">
			<form action="" method="post" id="loginForm" name="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="/logisticsc/Clound/img/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i><img height="37" src="/logisticsc/Clound/img/user.png" /></i>
							</span><input type="text" group="val"  valited="required" name="username" id="username" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i><img height="37" src="/logisticsc/Clound/img/suo.png" /></i>
							</span><input type="password" group="val"  valited="required" val name="password" id="password" placeholder="请输入密码" value="" />
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						<span style="line-height: 30px;font-size: 14px; float: left;">
				 		<a href="main.jsp">回到首页</a>
				 		</span>
						<span
							class="pull-right"><a onclick="login('loginForm');"
							class="flip-link btn btn-info" id="to-recover">登录</a>
						</span>

					</div>
				</div>

			</form>


			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">© 2013-2017 中工储运 版权所有 粤ICP备15048227号-1</span></font>
				</div>
			</div>
		</div>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<div data-src="/logisticsc/Clound/img/banner_slide_01.jpg"></div>
			<div data-src="/logisticsc/Clound/img/banner_slide_02.jpg"></div>
			<div data-src="/logisticsc/Clound/img/banner_slide_03.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>
	<script src="/logisticsc/Clound/js/login/jquery-1.7.2.js"></script>
	<script src="/logisticsc/Clound/js/login/jquery.easing.1.3.js"></script>
	<script src="/logisticsc/Clound/js/login/camera.min.js"></script>
	<script src="/logisticsc/Clound/js/login/templatemo_script.js"></script>
</body>

</html>