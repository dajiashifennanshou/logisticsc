<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安全设置</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="${configProps['resources']}/platform/js/securitySettings.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/grzx-anquanshezhi.css" />
<body>
<div id="container">
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-self">
			<jsp:include page="peronal_center_left.jsp"></jsp:include>
		<div class="demo-content platformStyle">
			<div class="nav-right f">
				<div class="nav-top1 f">
					<div class="nav-top1fk f"></div>
					<p>安全设置</p>
				</div>	
				<div class="aqsz-top2 f">
					<div class="aqsz1 f">
						<div class="aqsz1-left f">
							<c:if test="${user_session.password == ''}">
				        		<span>未设置</span>
				        	</c:if>
				        	<c:if test="${user_session.password != ''}">
				        		<span>已设置</span>
				        	</c:if> 
						</div>
						<div class="aqsz1-middlel f">
							登陆密码
						</div>
						<div class="aqsz1-middle2 f">
							安全性高的密码可以使账号更安全。建议您定期更换密码，<br />
							且设置一个包含数字和字母，并长度超过5-20位的密码
						</div>
						<div class="aqsz1-right aqsz1-right1 f">
							<button type="button" class='btn btn-warning' onclick="displayPassword()">立即修改</button>
						</div>
					</div>
					<%-- <div class="aqsz2 f">
						<div class="aqsz1-left f">
							<c:if test="${user_session.email == null}">
				        		<span>未验证</span>
				        	</c:if>
				        	<c:if test="${user_session.email != null}">
				        		<span>已验证</span>
				        	</c:if>
						</div>
						<div class="aqsz1-middlel f">
							邮箱验证
						</div>
						<div class="aqsz1-middle2 f">
							验证后，可享受XXX邮箱服务，如找回密码等
						</div>
						<div class="aqsz1-right aqsz2-right f">
							<button type="button" class='btn btn-warning' onclick="displayEmail()">立即绑定</button>
						</div>
					</div> --%>
				</div>
			</div>
	  </div>	
	</div>
	</div>
	<div style="clear: both;"></div>
	<div id="#footer">
		<jsp:include page="../bottom.jsp"></jsp:include>
	</div>
	<div id="updatePassword" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal form-bordered" id="update_password">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">修改密码</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 14px;">
							<label class="control-label col-sm-4">旧密码</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="passwordMimaMsg"></label>
								<input type="password" style="display:inline-block;width:250px" id="passwordMima" name="password" class="form-control required" placeholder="请输入旧密码"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">新密码</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="newPasswordMimaMsg"></label>
								<input type="password" class="form-control" style="display:inline-block;width:250px" id="newPasswordMima" name="newPassword" class="form-control required" placeholder="请输入新密码"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">确认密码：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="confirmPasswordMimaMsg"></label>
								<input type="password" style="display:inline-block;width:250px" id="confirmPasswordMima" name="confirmPassword" class="form-control required" placeholder="请输入确认密码"/>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="xiugaimima">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="updateEmail" class="modal  fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal form-bordered" id="update_">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">绑定邮箱</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" id="jiuyouxiang" style="margin-top: 14px;">
							<label class="control-label col-sm-4">旧邮箱</label>
							<div class="col-sm-8">
								<input type="text" readonly="readonly" style="display:inline-block;width:250px" id="userEmail" class="form-control required" value="${user_session.email}"/>
							</div>
						</div>
						<div class="form-group"  style="margin-top: 28px;">
							<label class="control-label col-sm-4">邮箱地址</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="emailMsg"></label>
								<input type="text" style="display:inline-block;width:250px" id="email" name="email" class="form-control required" placeholder="请输入邮箱地址"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">验证码</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="verCodeMsg"></label>
								<input type="text" style="display:inline-block;width: 116px;" class="form-control required" id="verCode" name="verCode" placeholder="请输入验证码"/>
								<div class="btn btn-default" style="margin-left: 5px;" id="email1">免费获取验证码</div>	
							</div>
						</div>
					</div> 
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="baocunyouxiang">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="youxiangtiaozhuan()">×</span></a>
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
    	<div id="successModals" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="tiaozhuan()">×</span></a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgss"></span></h5>
			</div>
		</div>
	</div>
</div>
</body>
</html>