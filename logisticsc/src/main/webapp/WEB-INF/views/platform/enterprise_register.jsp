<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业货主注册</title>
<link rel="stylesheet" href="${configProps['resources']}/platform/bootstrap-css/bootstrap.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/bootstrap-css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/register/qiyehuozhu-zhuce.css" />
<script type="text/javascript" src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js" ></script>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<script type="text/javascript" src="${configProps['resources']}/platform/js/placeholderfriend.js" ></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/login/font-awesome-3.2.1/css/font-awesome-ie7.min.css" />	
<script type="text/javascript" src="${configProps['resources']}/platform/js/enterpriseRegister.js" ></script>
</head>
<body>
	<div class="top">
			<div class="top-content">
				 <div class="logo f">
				 	<img src="/logisticsc/resources/platform/img/wuliucompany_logo.png" />
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
				 		<i class="icon-desktop"></i>
				 		<a href="/logisticsc/platform-login.jsp">已有账号？马上登陆</a>
				 	</span>
				 </div>
			</div>
		</div>
		<div class="content">
			<form id="enter_prise" action="" enctype="multipart/form-data" method="post">
				<div class="container">
					<div class="row">
							<p class="col-sm-12">企业货主注册</p>
							<a href="${configProps['project']}/user/jumpreGister.shtml?" class="grhz">
								个人货主注册
							</a>
							<a href="${configProps['project']}/user/jumpreDedicatedLine_register.shtml?" class="zxhz">
								专线注册
							</a>
							<i class=" icon-ambulance icon-large"></i>
					</div>
					<div class="denglu1">
							<div class="denglu1-top">
							</div>
								<div class="form-horizontal con1">
									<div class="form-group">
										<div class="col-sm-2"></div>
										<label class="col-sm-2 control-label">手机号：</label>
										<div class="col-sm-5">
											<label class="login-sjh" id="loginNameMsg"></label>
											<input type="text" class="form-control" id="loginName" name="loginName" placeholder="请输入手机号" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2 ">
									<div class="form-group">
										<div class="col-sm-2"></div>
										<label class="col-sm-2 control-label">密码:</label>
										<div class="col-sm-5">
											<label class="login-sjh" id="passwordMsg"></label>
											<input type="password" class="form-control user" name="password" id="password" placeholder="请输入密码" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2 ">
									<div class="form-group">
										<div class="col-sm-2"></div>
										<label class="col-sm-2 control-label">确认密码:</label>
										<div class="col-sm-5">
											<label class="login-sjh" id="repasswordMsg"></label>
											<input type="password" class="form-control user" name="repassword" id="repassword" placeholder="请输入确认密码" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<div class="col-sm-2"></div>
										<label class="col-sm-2 control-label">验证码：</label>
										<div class="col-sm-3">
											<label class="login-sjh" id="codeMsg"></label>
											<input type="text" class="form-control" id="code" name="code" placeholder="请输入验证码" />
										</div>
										<div class="col-sm-2">
											<div class="btn btn-default" id="sendcode" onclick="enterpriseSms()">获取验证码</div>	
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2 con3">
									<div class="btn btn-info wc-btn col-lg-offset-3" id="xyb1">下一步</div>
								</div>
						</div>
						<div class="denglu2">
							<div class="denglu2-top">
							</div>
								<div class="form-horizontal con1 ">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司名称:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="companyNameMsg"></label>
											<input type="text" class="form-control" name="companyName" id="companyName" placeholder="请输入公司名称" />
										</div>
										<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">邮政编码:</label>			
										<div class="col-sm-4">
											<label class="login-sjh" id="postCodeMsg"></label>
											<input type="text" class="form-control" name="postCode" id="postCode" placeholder="请输入邮政编码" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司地址:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="companyAddressMsg"></label>
											<input type="text" class="form-control" name="companyAddress" id="companyAddress" placeholder="请输入公司地址" />
										</div>
										<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司电话:</label>			
										<div class="col-sm-4">
											<label class="login-sjh" id="companyPhoneMsg"></label>
											<input type="text" class="form-control" name="companyPhone" id="companyPhone" placeholder="请输入公司电话" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">法定代表人:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="legalPersonMsg"></label>
											<input type="text" class="form-control" name="legalPerson" id="legalPerson" placeholder="请输入法定代表人" />
										</div>
										<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司传真:</label>			
										<div class="col-sm-4">
											<label class="login-sjh" id="companyFaxMsg"></label>
											<input type="text" class="form-control" name="companyFax" id="companyFax" placeholder="请输入公司传真" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">法定人电话:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="legalMobileMsg"></label>
											<input type="text" class="form-control" name="legalMobile" id="legalMobile" placeholder="请输入法定人电话" />
										</div>
										<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">税务登记号:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="companyTaxNoMsg"></label>
											<input type="text" class="form-control" name="companyTaxNo" id="companyTaxNo" placeholder="请输入税务登记号" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">联系人:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="contactsMsg"></label>
											<input type="text" class="form-control" name="contacts" id="contacts" placeholder="请输入联系人姓名" />
										</div>
										<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">财务邮箱:</label>			
										<div class="col-sm-4">
											<label class="login-sjh" id="financeEmailMsg"></label>
											<input type="text" class="form-control" name="financeEmail" id="financeEmail" placeholder="请输入财务邮箱" />
										</div>
									</div>
								</div>
								<div  class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">联系人电话:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="contactsMobileMsg"></label>
											<input type="text" class="form-control" name="contactsMobile" id="contactsMobile" placeholder="请输入联系人电话" />
										</div>
										<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司介绍:</label>			
										<div class="col-sm-4">
											<label class="login-sjh" id="companyInfoMsg"></label>
											<input type="text" class="form-control" name="companyInfo" id="companyInfo" placeholder="请输入公司介绍" />
										</div>
									</div>
								</div>
								<div  class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司Log:</label>
										<div class="col-sm-2">
											<label class="login-sjh" id="logMsg"></label>
											<input type="file" name="fileName4" id="log"/>
										</div>
										<div class="col-sm-1" style="margin-left: 145px;"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">QQ:</label>
										<div class="col-sm-4">
											<label class="login-sjh" id="QQMsg"></label>
											<input type="text" class="form-control" name="QQ" id="QQ" placeholder="请输入QQ" />
										</div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">营业执照:</label>
										<div class="col-sm-2">
											<label class="login-sjh" id="businessLicensesMsg"></label>
											<input type="file" name="fileName0" id="businessLicenses"/>
										</div>
										<div class="col-sm-2">
										</div>
										<div class="col-sm-1"></div>
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司照片:</label>
										<div class="col-sm-2">
											<label class="login-sjh" id="companyPhotosMsg"></label>
											<input type="file" name="fileName1" id="companyPhotos"/>
										</div>
										<div class="col-sm-2">
										</div>
										<div class="col-sm-1"></div>
									</div>
								</div>
								<div class="form-horizontal con1 con2">
									<div class="form-group">
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">法人身份证照:</label>
										<div class="col-sm-2">
											<label class="login-sjh" id="legalPhotosMsg"></label>
											<input type="file" name="fileName2" id="legalPhotos"/>
										</div>
										<div class="col-sm-3"></div>
										<i class="icon-asterisk col-sm-1 "></i>
										<label class="text-left control-label pull-left juli" style="text-align: left;">名片照片:</label>
										<div class="col-sm-2">
											<label class="login-sjh" id="cardphotosMsg"></label>
											<input type="file" name="fileName3" id="cardphotos"/>
										</div>
										<div class="col-sm-2">
										</div>
									</div>
								</div>
								<div role="form" class="form-horizontal con1 con2">  
									<label class="radio-inline col-sm-offset-4">
									<label class="login-sjh" id="agreeMsg"></label>
									  	<input type="checkbox" id="agree" name="agree"/>  我已看过并同意《企业货主服务条款》
									</label>
								</div>
								<div class="form-horizontal con1 con2 con3">
									<div class="btn btn-info wc-btn col-lg-offset-3" id="querenzhuce">确认注册</div>
								</div>
						</div>
					<div class="denglu3">
						<div class="denglu3-top">
						</div>
						<div class="zhu3">	</div>
						<div class="fanhui">
							<a href="../platform-index.jsp">回首页</a>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="bottom2">
			 © Copyright 2013-2017    粤ICP备15048227号-1    中工储运    版权所有
		</div>
		<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
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
</body>
</html>