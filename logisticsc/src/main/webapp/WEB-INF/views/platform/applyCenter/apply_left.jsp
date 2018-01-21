<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请中心左部</title>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/sqzx-left.css" />
</head>
<body>
	<div class="nav-left f">
		<ul>
			 <c:if test="${user_session.userType == 1}"> 
				<li class="li1">
					<div class="gr-left1"></div>
					<a href="<%=request.getContextPath()%>/applyCenter/toorderEnterpriseFlow.shtml">申请企业货主</a>
				</li>
				<li class="li1">
					<div class="gr-left2"></div>
					<a href="<%=request.getContextPath()%>/applyCenter/todedicatedLineFlow.shtml">申请专线</a>
				</li>
			</c:if>	
			<c:if test="${user_session.userType == 3 || user_session.userType == 1}">
				<li class="li1">
					<div class="gr-left3"></div>
					<a href="<%=request.getContextPath()%>/applyCenter/toorderProviderFlow.shtml">申请物流提供商</a>
				</li>
			</c:if>
			<%-- <li class="li1">
				<div class="gr-left2"></div>
				<a href="<%=request.getContextPath()%>/applyCenter/toDriverFlow.shtml">司机流程</a>
			</li>
			<li class="li1">
				<div class="gr-left2"></div>
				<a href="<%=request.getContextPath()%>/applyCenter/toDriver.shtml">申请司机</a>
			</li> --%>
		</ul>
	</div>
</body>
</html>