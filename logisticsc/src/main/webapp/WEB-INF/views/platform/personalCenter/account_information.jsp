<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账号信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="${configProps['resources']}/platform/js/accountInformation.js"></script>
<script type="text/javascript" src="${configProps['resources']}/platform/js/placeholderfriend.js" ></script>
<link href="${configProps['resources']}/platform/css/register1.css" rel="stylesheet">
</head>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="peronal_center_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle">
			<ul id="myTab" class="nav nav-tabs">
			   <li class="active"><a href="#basicInform" data-toggle="tab">基本信息</a></li>
			   <li><a href="#companyInform" data-toggle="tab">公司信息</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="basicInform">
	    			 <form id="update_userInfo" class="form-horizontal form-bordered">
	    			 	<div class="modal-body add-role-body">
	    			 		 <div class="form-group" style="margin-top: 14px;">
		                        <label class="control-label col-sm-4">我的账号：</label>
		                        <div class="col-sm-2">
		                            <input type="text" style="position:relative;display:inline-block;width:250px" readonly="readonly" name="loginName" class="form-control required" value="${user_session.loginName}"/>
		                        </div>
		                    </div>
		                    <div class="form-group" style="margin-top: 28px;">
		                        <label class="control-label col-sm-4">真实姓名：</label>
		                        <div class="col-sm-2">
		                        	<label class="login-sjh" id="trueNameMsg"></label>
		                            <input type="text" style="position:relative;display:inline-block;width:250px" id="trueName" name="trueName" class="form-control required" value="${user_session.trueName}"/>
		                        </div>
		                    </div>
		                    <div class="form-group" style="margin-top: 28px;">
		                        <label class="control-label col-sm-4">手机号码：</label>
		                        <div class="col-sm-2">
		                            <input type="text" style="position:relative;display:inline-block;width:250px" readonly="readonly" name="mobile" class="form-control required" value="${user_session.loginName}"/>
		                        </div>
		                    </div>
		                    <div class="form-group" style="margin-top: 28px;">
		                        <label class="control-label col-sm-4">邮箱号码：</label>
		                        <div class="col-sm-2">
		                            <input type="text" style="position:relative;display:inline-block;width:250px" readonly="readonly" name="email" class="form-control required" value="${user_session.email}"/><span style="color: #008800">安全设置，绑定邮箱</span>
		                        </div>
		                    </div>
		                    <div class="form-group" style="margin-top: 28px;">
		                        <label class="control-label col-sm-4">详细地址：</label>
		                        <div class="col-sm-2">
		                        	<label class="login-sjh" id="addressjibenMsg"></label>
		                       		<textarea class="form-control" rows="3" style="position:relative;display:inline-block;width:250px" id="addressjiben" name="address">${user_session.address}</textarea>
		                        </div>
		                    </div>
	    			 	</div>
	    			 	<div class="modal-footer">
		                    <button class="btn btn-success btn-save" type="button" id="xiugaijiben">修改</button>
               			</div>
	    			 </form>
			    </div>	
			    <div class="tab-pane fade" id="companyInform">
			    	<form id="aaaaa"  class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
						<input type="text" id="id" name="id" style="display:none;"/>
						<div class="denglu2" style="margin-top: 30px;margin-left: 30px;">
							<div class="form-horizontal con1 ">
								<div class="form-group">
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
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
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
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
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
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
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
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
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
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
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
									<label class="text-left control-label pull-left juli" style="text-align: left;">联系人电话:</label>
									<div class="col-sm-4">
										<label class="login-sjh" id="contactsMobileMsg"></label>
										<input type="text" class="form-control" name="contactsMobile" id="contactsMobile" placeholder="请输入联系人电话" />
									</div>
									<div class="col-sm-1"></div>
									<label class="text-left control-label pull-left juli" style="text-align: left;">QQ:</label>
									<div class="col-sm-4">
											<label class="login-sjh" id="QQMsg"></label>
											<input type="text" class="form-control" name="QQ" id="QQ" placeholder="请输入QQ" />
									</div>
								</div>
							</div>
							<div  class="form-horizontal con1 con2">
								<div class="form-group">
									<i class="icon-asterisk col-sm-1 " id="ico_xx"></i>
									<label class="text-left control-label pull-left juli" style="text-align: left;">组织代码:</label>			
									<div class="col-sm-4">
										<label class="login-sjh" id="companyCodeMsg"></label>
										<input type="text" class="form-control" name="companyCode" readonly="readonly" id="companyCode" placeholder="请输入组织代码" />
									</div>
									<div class="col-sm-1"></div>
										<label class="text-left control-label pull-left juli" style="text-align: left;">公司介绍:</label>			
										<div class="col-sm-4">
											<label class="login-sjh" id="companyInfoMsg"></label>
											<!-- <input type="text" class="form-control" name="companyInfo" id="companyInfo" placeholder="请输入公司介绍" /> -->
											<textarea class="form-control" rows="3" name="companyInfo" id="companyInfo" placeholder="请输入公司介绍"></textarea>
										</div>
								</div>
							</div>
							<div class="form-horizontal con1 con2" >
								<div class="biaoqian1">
									<span id="tupianMsg">请上传正规的图片格式</span>
								</div>
								<div class="uppic f-cb fl" style="margin-top:10px;margin-left: 1px;">
									<div class="upbtn fl" title="上传图片">
										<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'0')" id="preview0"/>
										<span>营业执照</span>
										<input type="file" name="fileName0" id="fileName0" onchange="javascript:fileNmae(0);"/>
									</div>
									<div class="upbtn fl" title="上传图片">
										<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'1')" id="preview1"/>
										<span>公司照片</span>
										<input type="file" name="fileName1" id="fileName1" onchange="javascript:fileNmae(1);"/>
									</div>
									<div class="upbtn fl" title="上传图片">
										<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'2')" id="preview2"/>
										<span>法人身份证照</span>
										<input type="file" name="fileName2" id="fileName2" onchange="javascript:fileNmae(2);"/>	
									</div>
									<div class="upbtn fl" title="上传图片">
										<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'3')" id="preview3"/>
										<span>名片照片</span>
										<input type="file" name="fileName3" id="fileName3" onchange="javascript:fileNmae(3);"/>
									</div>
									<div class="upbtn fl" title="上传图片">
										<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'4')" id="preview4"/>
										<span>公司logo</span>
										<input type="file" name="fileName4" id="fileName4" onchange="javascript:fileNmae(4);"/>
									</div>
								</div>
							</div>
							<div role="form" class="form-horizontal con1 con2" style="margin-top: 180px;">  
										<label class="radio-inline col-sm-offset-4">
									</div>
									<div class="form-horizontal con1 con2 con3">
									<c:if test="${user_session.userType == 1 || user_session.userType == 2 || user_session.userType == 3 || user_session.userType == 4}">
										<div class="btn btn-info wc-btn col-lg-offset-3" id="xiugaigongsi">修改</div>
									</c:if>
							</div>
						</div>
					</form>	
			    </div>
			 </div>  
	    </div>
   </div>
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
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
                    <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #cccccc!important;font-weight:bold">温馨提示:<span id="promptModalMsgsDIV"></span></h5>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
     <div style="clear: both;"></div>
     <div class="footer">	
		<jsp:include page="../bottom.jsp"></jsp:include>
	 </div>
</body>
</html>