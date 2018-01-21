<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<link rel="stylesheet" href="${configProps['resources']}/platform/css/top.css" />
<link rel="stylesheet" href="${configProps['resources']}/platform/css/dingdanzuobu.css" />
<title>订单中心-导航</title>
</head>
<body>
	<div class="nav-left f">
			<ul>
				<li class="li1">
					<div class="dd-left1"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderlistpage.shtml">我的订单</a>
				</li>
				<%-- <li>
					<div class="dd-left2"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toMybill.shtml" >我的账单</a>
				</li> --%>
				<li>
					<div class="dd-left3"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderCollectionPayment.shtml">代收款</a>
				</li>
				<%-- <li>
					<div class="dd-left4"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderCapitalFlow.shtml">资金流水</a>
				</li> --%>
				<li>
					<div class="dd-left5"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderMineEvaluation.shtml">我的评价</a>
				</li>
				<li>
					<div class="dd-left6"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderMineComplain.shtml">我的投诉</a>
				</li>
				<li>
					<div class="dd-left7"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderCollectionLine.shtml">我的收藏</a>
				</li>
				<li>
					<div class="dd-left8"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toCommonContact.shtml">常用联系人</a>
				</li>
				<li>
					<div class="dd-left9"></div>
					<a href="<%=request.getContextPath()%>/orderCenter/toorderRegularCargo.shtml">常发货物</a>
				</li>
			</ul>
		</div>
	</body>
</html>