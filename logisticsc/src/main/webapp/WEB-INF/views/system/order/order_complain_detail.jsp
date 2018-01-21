<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>
<div class="demo-content" >
	<input id="complainId" type="hidden" value="${data.complain.complainId}" />
	<table border="1" width="80%" bordercolor="#cccccc">
		<tr >
			<td style="width: 80px;text-align: center;"  class="td_colour td_style">评价信息</td>
			<td style="margin-left: 10px">
				<div style="margin-left: 5px; line-height: 40px;">
					<div class="col-sm-6">
						<span class="td_colour">订单号:</span><span class="td_colour" id="orderNumbers">${data.complain.orderNumber}</span>
					</div>
					<div class="col-sm-6">
						<span class="td_colour">运单号:</span><span class="td_colour" id="wayBillNumber">${data.complain.wayBillNumber}</span>

					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价时间:</span><span class="td_colour" id="evaluateTime">${data.complain.complaintTimeStr}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价内容:</span><span class="td_colour" id="evaluateContent">${data.complain.complaintContent}</span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;" class="td_colour td_style">投诉回复</td>
			<td>
				<div style="margin-left: 5px; line-height: 40px;">
					<div class="col-sm-12">
						<span class="td_colour">投诉处理时间:</span><span class="td_colour"  id="replyTime">${data.comlainHandle.handleTimeStr}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">投诉处理账号:</span><span class="td_colour"  id="replyPeople">${data.comlainHandle.handlePeople}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">投书处理内容:</span><span class="td_colour"  id="replyContent">${data.comlainHandle.handleContent}</span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;" class="td_colour td_style">投诉反馈</td>
			<td>
				<div style="margin-left: 5px; line-height: 40px;">
					<div class="col-sm-12">
						<c:if test="${data.comlainHandle.handleSatisfiedDegree == 1}">
							<span class="td_colour">投诉反馈:</span><span class="td_colour"  id="handleContent">非常满意</span>
						</c:if>
						<c:if test="${data.comlainHandle.handleSatisfiedDegree == 2}">
							<span class="td_colour">投诉反馈:</span><span class="td_colour"  id="handleContent">满意</span>
						</c:if>
						<c:if test="${data.comlainHandle.handleSatisfiedDegree == 3}">
							<span class="td_colour">投诉反馈:</span><span class="td_colour"  id="handleContent">一般</span>
						</c:if>
						<c:if test="${data.comlainHandle.handleSatisfiedDegree == 4}">
							<span class="td_colour">投诉反馈:</span><span class="td_colour"  id="handleContent">不满意</span>
						</c:if>
						<c:if test="${data.comlainHandle.handleSatisfiedDegree == 5}">
							<span class="td_colour">投诉反馈:</span><span class="td_colour"  id="handleContent">非常不满意</span>
						</c:if>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">投诉反馈时间:</span><span class="td_colour"  id="handleTimeStr">${data.comlainHandle.handleTimeStr}</span>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div class="mt20">
		<div class="form-actions new-con-btn">
			<button type="submit" onclick="verifyUser()" class="button button-primary">返回</button>
		</div>
	</div>
</div>	
<script type="text/javascript">
	function verifyUser(){
		window.location="${configProps['project']}/system/pageJump.shtml?url=/system/order/order_complain_list";
	}
</script>
</body>
</html>