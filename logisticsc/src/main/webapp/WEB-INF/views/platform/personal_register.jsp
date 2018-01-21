<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" /> 
<title>个人货主注册</title>
<link rel="stylesheet" href="${configProps['resources']}/platform/bootstrap-css/bootstrap.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/bootstrap-css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/register/grhz-zhuce.css" />
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js" ></script>
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/platform/js/placeholderfriend.js" ></script>	
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome-ie7.min.css" />	
<script type="text/javascript" src="${configProps['resources']}/platform/js/personalRegister.js" ></script>
</head>
<body style="min-width:1200px;">
	<div class="top">
			<div class="top-content">
				 <div class="logo f">
				 	<img src="/logisticsc/resources/platform/img/logo-1.png" />
				 </div>
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
				 		<a href="${configProps['project']}/main.jsp">回到首页</a>
				 	</span>
				 	<span>
				 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-desktop"></i>
				 		<a href="/logisticsc/platform-login.jsp">已有账号？马上登陆</a>
				 	</span>
				 </div>
			</div>
		</div>
		<div class="content">
			<div class="container">
					<div class="row">
						<p class="col-sm-12">注册</p>
						<i class=" icon-ambulance icon-large"></i>
	 				</div>
					<form id ="theForm" method="post">
						<div class="form-horizontal con1 " style="position:relative;">
							<div class="form-group">
								<div class="col-sm-2"></div>
								<label class="col-sm-2 control-label">手机号：</label>
								<div class="col-sm-5">
									<label class="login-sjh" id="loginNameMsg"></label>
									<input type="text" class="form-control" id="loginName" name="loginName"  placeholder="请输入手机号" />
								</div>
							</div>
						</div>
						<div class="form-horizontal con1 con2 ">
							<div class="form-group">
								<div class="col-sm-2"></div>
								<label class="col-sm-2 control-label">密码:</label>
								<div class="col-sm-5">
									<label class="login-sjh" id="passwordMsg"></label>
									<input type="password" class="form-control user" id="password" name="password" placeholder="请输入密码" />
								</div>
							</div>
						</div>
						<div class="form-horizontal con1 con2 ">
							<div class="form-group">
								<div class="col-sm-2"></div>
								<label class="col-sm-2 control-label">确认密码:</label>
								<div class="col-sm-5">
									<label class="login-sjh" id="repasswordMsg"></label>
									<input type="password" class="form-control user" id="repassword" name="repassword" placeholder="请输入确认密码" />
								</div>
							</div>
						</div>
						<div class="form-horizontal con1 con2">
							<div class="form-group">
								<div class="col-sm-2"></div>
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-3">
									<label class="login-sjh" id="validCodeMsg"></label>
									<input type="text" class="form-control" id="validCode" name="validCode" placeholder="请输入验证码" />
								</div>
								<div class="col-sm-2">
									<img id="createCheckCode" class="f" src="getVerCode.shtml" onclick="myReload()" width="90" height="37">
								</div>
							</div>
						</div>
						<div class="form-horizontal con1 con2">
							<div class="form-group">
								<div class="col-sm-2"></div>
								<label class="col-sm-2 control-label">动态码：</label>
								<div class="col-sm-3">
									<label class="login-sjh" id="codeMsg"></label>
									<input type="text" class="form-control" id="code" name="code" placeholder="请输入动态码" />
								</div>
								<div class="col-sm-2">
									<div class="btn btn-default" id="sendcode" onclick="enterpriseSms()">获取动态码</div>	
								</div>
							</div>
						</div>
						<div role="form" class="form-horizontal con1 con2">  
							<label class="radio-inline col-sm-offset-4">
								<label class="login-sjh" id="agreeMsg"></label>
							  	<input type="checkbox" id="agree" name="agree"/> 我已看过并同意<a href="../platform-provision.html" target="_blank">《货运交易系统注册协议》</a>
							</label>
						</div>
						<div class="form-horizontal con1 con2 con3" style="margin: 30px auto 20px auto;">
							<div id="registerbtn" class="btn btn-info wc-btn col-lg-offset-3">同意注册</div>
						</div>
					</form>
				</div>
		</div>
		<div class="bottom2">
			 © Copyright 2013-2017    粤ICP备15048227号-1    中工储运    版权所有
		</div>
<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="dianzhuan()">×</span></a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgs"></span></h5>
			</div>
		</div>
	</div>
</div>
<div id="errorModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: red !important;">操作失败！</h4>
                    <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: red !important;font-weight:bold">错误提示:<span id="errorModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
  	//请求验证码
  	myReload();
	function myReload(){  
	    document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src + "?nocache="+new Date().getTime();  
	}
    </script>
</body>
</html>