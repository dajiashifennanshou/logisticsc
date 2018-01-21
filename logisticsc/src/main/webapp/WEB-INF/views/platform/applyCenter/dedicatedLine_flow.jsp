<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专线流程</title>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
</head>
<script type="text/javascript">
	function tiaohzuan() {
		window.location.href="<%=request.getContextPath()%>/applyCenter/todedicatedLine.shtml";
	}
</script>
<body>
<jsp:include page="../top.jsp"></jsp:include>
<div id="#container">
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="apply_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle" style="position:relative;">
			<img src="/logisticsc/resources/platform/img/zhuanxian-apply-flow.png" />
		</div>
		<button type="button" style="margin: 0px auto 0px -52px;position: absolute;left: 50%;top: 300px;padding: 12px 52px;background:#0055bf;border: medium none;font-weight: bold;font-size: 16px;" class="btn btn-success apply-btn" onclick="tiaohzuan()">申请专线</button>
	</div>
</div>
<div class="footer">
	<div style="clear: both;"></div>
	<jsp:include page="../bottom.jsp"></jsp:include>
</div>
</body>
</html>