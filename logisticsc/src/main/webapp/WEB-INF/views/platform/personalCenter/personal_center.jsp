<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<%-- <link rel="stylesheet" href="${configProps['resources']}/platform/css/grzx.css" /> --%>
<script src="${configProps['resources']}/platform/js/personalCenter.js"></script>
<title>个人中心</title>
</head>
<style type="text/css">
	*,li{
		margin: 0px;
		padding: 0px;
		color: #333;
		font-family: 'arial';
	}
	.f {
		float: left;
	}
	.my_center {
		width: 1038px;
		float: left;
		margin-bottom: 10px;
	}
	.center_top {
		width: 100%;
		height: 106px;
		background: url(/logisticsc/resources/platform/img/grzx_bg22.png) no-repeat;
	}
	.top_touxiang img {
		margin-left: 20px;
		margin-top: 14px;
		float: left;
	}
	.top_touxiang p {
		color: white;
		font-size: 20px;
		display: block;
		padding-top: 24px;
	}
	.top_touxiang .zh_p2 {
		font-size: 18px;
		padding-top: 3px;
	}
	.top_touxiang .zh_p2 span {
		color: #f2ca76;
	}
	.center_bottom {
		width: 100%;
		height: 134px;
		margin-top: 20px;
	}
	.center_bottom ul li {
		width: 330px;
		height: 134px;
		background-color: #f9f9f9;
		margin-left: 23px;
		float: left;
		border: 1px solid #f6f6f6;
		list-style: none;
		position: relative;
	}
	.center_bottom ul li.center_li1 {
		margin-left: 0px;
	}
	.center_li1 img {
		position: absolute;
		top: 22px;
		left: 30px;
	}
	.li1_fon {
		position: absolute;
		left: 138px;
		top: 30px;
	}
	.li1_fon span {
		font-size: 16px;
	}
	.center_bottom ul li .fon_price {
		color: #f88312;
		font-size: 32px;
	}
	.center_bottom ul li .font_span2 {
		font-size: 12px;
		color: #666;
	}
	.center_li2 img,.center_li3 img {
		position: absolute;
		left: 46px;
		top: 34px;
	}
	.li2_bind {
		width: 108px;
		height: 34px;
		background: #f8b814;
		color: white;
		line-height: 34px;
		text-align: center;
		position: absolute;
		left: 165px;
		top: 45px;
		text-decoration: none;
		display: block;
		cursor: pointer;
	}
	.center_li3 img  {
		top: 26px;
	}
	.li3_fon {
		position: absolute;
		left: 150px;
		top: 40px;
	}
	.li3_fon .font_span3 {
		color: #F88312;
	}
</style>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="peronal_center_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
			<div class="my_center">
			<div class="center_top f">
				<div class="top_touxiang">
					<img src="/logisticsc/resources/platform/img/grzx_touxiang.png" />
					<c:if test="${user_session.userType == 1}">
						<p>
						您好，会员
						</p>
					</c:if>
					<c:if test="${user_session.userType == 2}">
						<p>
						您好，企业会员
						</p>
					</c:if>
					<c:if test="${user_session.userType == 3}">
						<p>
						您好，专线会员
						</p>
					</c:if>
					<c:if test="${user_session.userType == 4}">
						<p>
						您好，物流商会员
						</p>
					</c:if>
					<c:if test="${user_session.userType == 5}">
						<p>
						您好，网点会员
						</p>
					</c:if>
					<c:if test="${user_session.userType == 12}">
						<p>
						您好，经销商
						</p>
					</c:if>
					<p class="zh_p2">
						帐号：
						<span>${user_session.loginName}</span>
					</p>
				</div>
			</div>
			<div class="center_bottom f">
				<ul>
				<c:if test="${user_session.userType == 4 || user_session.userType == 5}">
					<li class="center_li1">
						<img src="/logisticsc/resources/platform/img/my_wallet1.png" />
						<div class="li1_fon">
							<span>当前账户余额</span><br />
							<span class="fon_price" id="jine"></span>
							<input type="text" id="userType" value="${user_session.userType}" style="display: none;">
							<span>元</span><br />
							<!-- <span class="font_span2">低于5000：余额不足，请充值</span> -->
						</div>
						
					</li>
				</c:if>	
					<%-- <li class="center_li2">
						<img src="/logisticsc/resources/platform/img/grzx_myemail.png" />
						 <c:if test="${user_session.email == ''}">
							<a href="<%=request.getContextPath()%>/personalCenter/toorderSecuritySettings.shtml" class="li2_bind">
								绑定邮箱
							</a>
						</c:if> 
						<c:if test="${user_session.email != ''}">
							<div class="li3_fon">
								<span>当前邮箱</span><br/>
								<span class="font_span3">${user_session.email}</span>
							</div>
			        	</c:if>
					</li> --%>
					<li class="center_li3">
						<img src="/logisticsc/resources/platform/img/grzx_phone.png" />
						<div class="li3_fon">
							<span>当前手机号</span><br />
							<span class="font_span3">${user_session.loginName}</span>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
</div>
 <div style="clear: both;"></div>
 <div class="footer">	
	<jsp:include page="../bottom.jsp"></jsp:include>
	</div>
</body>
</html>