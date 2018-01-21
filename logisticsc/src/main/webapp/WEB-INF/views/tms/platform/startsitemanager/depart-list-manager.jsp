<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发车管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.form-inline input{width: 100px;}
.form-inline select{width: 120px;}
</style>
</head>
<body>
	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
	<input type="hidden" id="userType" value="${tms_user_session.userType}">
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>发车管理</h3>
			</div>
			<div class="panel-body">
				
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
					        <c:if test="${not empty outletsInfos}">
					        	<div style="display: inline-block;">
						       		<label class="control-label">出发网点</label>
							        <select class="input-normal" id="startOutlets">
							        	<option value="">全部</option>
							        	<c:forEach items="${outletsInfos}" var="outletsInfo">
							        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
							        	</c:forEach>
							        </select>
							        <label class="control-label">途径网点</label>
							        <select class="input-normal" id="wayOutlets">
							        	<option value="">全部</option>
							        	<c:forEach items="${outletsInfos}" var="outletsInfo">
							        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
							        	</c:forEach>
							        </select>
							        <label class="control-label">到达网点</label>
							        <select class="input-normal" id="targetOutlets">
							        	<option value="">全部</option>
							        	<c:forEach items="${outletsInfos}" var="outletsInfo">
							        		<option value="${outletsInfo.id}">${outletsInfo.name}</option>
							        	</c:forEach>
							        </select>
						        </div>
					        </c:if>
					        <label class="control-label">发车单状态</label>
					        <select class="input-normal" id="departStatus"></select>
				        	
				        	<label class="control-label">开始时间</label>
					        <input type="text" class="calendar" id="startTime">
					        
					        <label class="control-label">结束时间</label>
					        <input type="text" class="calendar" id="endTime">
					        
				            <input type="text" class="input-normal" id="condition" placeholder="发车单号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="wayBillOrderList"></div>
						<div id="wayBillOrderListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/tms/startsitemanager/depart-list-manager.js"></script>
	<script type="text/javascript">
	// 查询
	function search(){
		var params = {
			'startOutlets' : $('#startOutlets').val(),
			'wayOutlets' : $('#wayOutlets').val(),
			'targetOutlets' : $('#targetOutlets').val(),
			'status' : $('#departStatus').val(),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	</script>
</body>
</html>