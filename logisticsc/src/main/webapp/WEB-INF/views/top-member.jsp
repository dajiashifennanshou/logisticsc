<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>没有导航的top</title>
</head>
<body>
	<div class="shortcut-nav">
			<div class="container">
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-nav1">
					<!--  
						<li class="n-cell">客服电话<span class="blue">18000510004</span></li>-->
						<li class="line">|</li>
						<li class="n-qq">
						 <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${qq['provalue'] }&site=qq&menu=yes"><img
							src="${configProps['resources']}/img/qq-icon.png"
							style="vertical-align: middle;" /> <span
							style="vertical-align: middle;">在线客服</span>
						 </a>
						</li>
					</ul>
					<ul class="nav navbar-nav  navbar-nav1 navbar-right">
					<c:choose>
						<c:when test="${loginUser !=null }">
							<li class="login-word"><a href="${configProps['project']}/member/member.shtml">${loginUser.username }</a></li>
							<li class="register-word"><a href="${configProps['project']}/logout.shtml">注销</a></li>
						</c:when>
						<c:otherwise>
						<li class="login-word"><a href="${configProps['project']}/tologin.shtml">立即登录</a></li>
						<li class="register-word"><a href="${configProps['project']}/register.shtml">快速注册</a></li>
						</c:otherwise>				
					</c:choose>
						<li><a href="${configProps['project']}/notice/noticeCategory.shtml?id=3">帮助中心</a></li>
					</ul>
				</div>
			</div>
		</div>
		
</body>
</html>