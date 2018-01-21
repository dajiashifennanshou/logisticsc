<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发车配载</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<script type="text/javascript">
var userType = ${tms_user_session.userType};
if(userType == 0 || userType == 1){
	alert('请使用网点账号登录');
	history.go(-1);
}
</script>
</head>
<body>
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
	<input type="hidden" id="departNumber" value="${departNumber}">
	<div class="doc-content">
		<div class="panel">
			<div class="panel-header">
				<h3>发车配载</h3>
			</div>
			<div class="panel-body">
				<div style="padding-bottom: 5px;">
	    			<label>目的网点：</label>
	    			<select onchange="searchLeft(this.value)">
	    				<option value="">全部</option>
	    				<c:forEach items="${outletsInfos}" var="outletsInfo">
	    					<option value="${outletsInfo.id}">${outletsInfo.name}</option>
	    				</c:forEach>
	    			</select>
	    		</div>
				<div class="row-fluid">
			    	<div class="span11">
			      		<div id="gridLeft"></div>
			      	</div>
			      	<div class="span2">
			      		<div class="row-fluid text-center">
			      			<div class="span24" style="padding-top: 100px;">
			      				<button class="button button-primary" onclick="moveRight()">&gt;</button>
			      			</div>
			      			<div class="span24" style="padding-top: 20px;">
			      				<button class="button button-primary" onclick="moveLeft()">&lt;</button>
			      			</div>
			      		</div>
			      	</div>
			      	<div class="span11">
			      		<div id="gridRight"></div>
			      	</div>
			    </div>
			    <div class="row-fluid" style="margin-top: 20px;">
			    	<div class="span24 text-center">
			    		<form id="toDepartListForm" action="<%=request.getContextPath()%>/tms/depart/todepartlistpage.shtml"
			    		 method="post" onsubmit="return generateDepartList()">
			    			<input type="hidden" name="wayBillData" id="wayBillData">
			    			<input type="hidden" name="departNumber" value="${departNumber}">
			    			<button class="button button-info" type="submit">生成发车清单</button>
			    		</form>
			    	</div>
			    </div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tms/startsitemanager/stowage-workbench.js"></script>
</body>
</html>