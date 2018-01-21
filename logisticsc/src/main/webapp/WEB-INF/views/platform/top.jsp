<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<link rel="stylesheet" href="${configProps['resources']}/platform/css/top.css" />
<title>货运交易系统头部</title>
<!-- bootstrap-->
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome-ie7.min.css" />
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
</head>
<script type="text/javascript">
$(function(){
	var chooseid = ${chooseid}+"";
	if(chooseid != ""){
		$("#"+chooseid).css("color","#0055bf");
	}
	$("#dingdanzhongxin").click(function(){
		if($("#userName").val() == null || $("#userName").val() == ""){
			qingkomg();
			$("#login").modal({show : true});
		}else{
			window.location="<%=request.getContextPath()%>/orderCenter/toorderlistpage.shtml?chooseid='dingdanzhongxin'";
		}
	});
	$("#baoxianyewu").click(function(){
		if($("#userName").val() == null || $("#userName").val() == ""){
			qingkomg();
			$("#login").modal({show : true});
		}else{
			window.location="<%=request.getContextPath()%>/insurance/toInsurance.shtml?chooseid='baoxianyewu'";
		}
	});
	$("#gerenzhongxin").click(function(){
		if($("#userName").val() == null || $("#userName").val() == ""){
			qingkomg();
			$("#login").modal({show : true});
		}else{
			window.location="<%=request.getContextPath()%>/personalCenter/toorderPersonal.shtml?chooseid='gerenzhongxin'";
		}
	});
});
//请求验证码
function myReload(){  
    document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src + "?nocache="+new Date().getTime();  
}
//页面加载
$(function(){
	 // 表单回车事件
    $('#theForm').keydown(function(e){
        if( e.keyCode == 13 ){
        	login();
        }
    });
});
function qingkomg() {
	$("#username").val("");
	$("#password").val("");
	$("#checkCode").val("");
}
//登陆
function login() {
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
        url : "/logisticsc/user/loginUser.shtml",
        type : 'POST',
        data : {"loginName":username,"password":password,"reCode":reCode},
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	$("#login").modal("hide");
            	window.location = "../deliverGoods/deliverGoods.shtml?chooseid='fahuo'";
            } else {
               $("#promptInfo").text(data.msg);
           		myReload();
            }
        }
    });
}
</script>
<body>
	<div class="top" >
		<div class="top-con">
			<div class="top-btn1">
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</div>
			<input type="hidden" id="userName" value="${user_session.loginName}"/>
			<!--  
			<div class="top-btn" id="go_logisticsc">
				<a href="javascript:void(0)" style="color: rgb(255, 102, 0);display:block;text-decoration: none;">专线管理</a>
				
			</div>
			<div class="top-btn">
				<a href="/logisticsc/yc-login.yc" style="color: rgb(255, 102, 0);display:block;text-decoration: none;" target="_blank">云仓登录</a>
			</div>
				-->
			<div class="top-tu">
				<ul>
					<li class="li4">
						<!--
						<div class="top-denglu"></div>
						
						<c:if test="${user_session.loginName == null }">
							<a href="/logisticsc/platform-login.jsp">请登录</a>
						</c:if>
						  -->
						<c:if test="${user_session.loginName != null }">
							<a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" class="f">${user_session.loginName}</a>
						</c:if>
					</li>
					<li class="li5">
					<!--  
					<c:if test="${user_session.loginName == null }">
							<div class="top-zhuce"></div>
						<a href="${configProps['project']}/user/jumpreGister.shtml?">免费注册</a>
					</c:if>
					-->
					<c:if test="${user_session.loginName != null }">
							<div class="top-zhuce"></div>
						<a href="${configProps['project']}/user/outUser.shtml?">退出</a>
					</c:if>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="dd-nav">
		<div class="nav-con">
			<div class="logo-top f">
				<img src="/logisticsc/resources/platform/img/logo-10.png" />
			</div>
			<div class="nav-con2 f">
				<ul>
					<li>
						<a class="li" href="<%=request.getContextPath()%>/platform-index.jsp">首页</a>
					</li>
					<li >                                                          
						<a class="li" id = "fahuo" href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml?chooseid='fahuo'" >我要发货</a>
					</li>
					 <li>
						<a class="li" href="javascript:void(0)"   id="baoxianyewu">保险业务</a>
						 
					</li> 
					<li>
						<a class="li" id = "genzhong" href="<%=request.getContextPath()%>/orderTracking/toorderorderTracking.shtml?chooseid='genzhong'">订单跟踪</a>
					</li>
				    <li>
				    	<a class="li" href="javascript:void(0)" id="dingdanzhongxin">订单中心</a>
				    </li>	           
					<li>
						<a  class="li" href="javascript:void(0)" id="gerenzhongxin">个人中心</a>
					</li>
					<c:if test="${user_session.userType == 1 || user_session.userType == 3}">
					<li>
						<c:if test="${user_session.userType == 1}">
							<a class="li" id = "applay" href="<%=request.getContextPath()%>/applyCenter/toorderEnterpriseFlow.shtml?chooseid='applay'">申请中心</a>
						</c:if>
						<c:if test="${user_session.userType == 3}">
							<a class="li" id = "applay1" href="<%=request.getContextPath()%>/applyCenter/toorderProviderFlow.shtml?chooseid='applay1'">申请中心</a>
						</c:if>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#go_logisticsc").click(function(){
			$.ajax({
				url:'../tms/login.shtml',
				type:'post',
				success:function(data){
					if(data.result){
						window.location.href="../tms/index.shtml";
					}else{
						$("#promptModal").modal('show');
						$("#promptModalMsgs").html(data.msg);
					}
				},
			})
		})
	</script>
	<div id="login" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" id="theForm">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" >
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">登录</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group">
							<div class="col-sm-4"></div>
							<div class="tips-notice col-sm-8" style="width: 250px; margin-left: 15px;">
								<i class="icon-volume-up tubiao1 f"></i>
								<div class="tips-content f" style="line-height: 30px; margin-left: 9px;">
									<span id="promptInfo">请输入正确的用户名和密码。</span>
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">用户名</label>
							<div class="col-sm-8">
								<!-- <label class="login-sjh" id="promptInfo"></label> -->
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="username" class="username" placeholder="请输入用户名"/>
							</div>
						</div> 
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">密码</label>
							<div class="col-sm-8">
								<input type="password" style="display:inline-block;width:250px" class="form-control required"  id="password" class="username user" placeholder="请输入密码"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">验证码</label>
							<div class="col-sm-8">
								<input type="text" id="checkCode"  style="display:inline-block; float:left;width: 116px;" class="form-control required" placeholder="请输入验证码"/>
								<div><img id="createCheckCode" class="f" src="/logisticsc/user/getVerCode.shtml" onclick="myReload()" width="90" height="37" style="margin-top: 0px; margin-left: 27px;"/></div>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-top: 28px;">
						<button type="button" class="btn btn-success btn-save" onclick="login()">登录</button>
					</div> 
			</form>
		</div>
	</div>
</div>
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #666 !important;">温馨提示！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #666!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
	</body>
</html>