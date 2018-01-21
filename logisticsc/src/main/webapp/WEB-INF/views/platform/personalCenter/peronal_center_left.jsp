<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心左边</title>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<link rel="stylesheet" href="${configProps['resources']}/platform/css/top.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/grzx-zuobu.css" />
</head>
<body>
	<div class="nav-left f">
		<ul>
			<li class="li1">
				<div class="gr-left1"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toorderPersonal.shtml" >个人中心</a>
			</li>
			<li>
				<div class="gr-left2"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toorderAccountInfo.shtml">账号信息</a>
			</li>
			<li>
				<div class="gr-left3"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toorderSecuritySettings.shtml">安全设置</a>
			</li>
			<c:if test="${user_session.userType == 4 || user_session.userType == 5}">
			<li>
				<div class="gr-left4"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toorderMyWallet.shtml">我的钱包</a>
			</li>
			
			</c:if>
			<c:if test="${user_session.userType == 12}">
				<li>
					<div class="gr-left4"></div>
					
					<a href="<%=request.getContextPath()%>/personalCenter/toMyCloud.shtml">我的云仓</a>
				</li>
			
			</c:if>
			 <li>
				<div class="gr-left5"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toorderRecordsConsumption.shtml">我的消费记录</a>
			</li>
			<%-- <li>
				<div class="gr-left7"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toMessage.shtml">消息订阅</a>
			</li> --%>
			<li>
				<div class="gr-left6"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/toInsurance.shtml">我的保险</a>
			</li>
			<li>
				<div class="gr-left8"></div>
				<a href="<%=request.getContextPath()%>/personalCenter/tosettlement.shtml">保险理赔</a>
			</li>
		</ul>
	</div>
</body>
</html>