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
	<table border="1" width="80%" bordercolor="#cccccc">
		<tr >
			<td style="width: 80px;text-align: center;"  class="td_colour td_style">评价信息</td>
			<td style="margin-left: 10px">
				<div style="margin-left: 5px; line-height: 40px;">
					<div class="col-sm-6">
						<span class="td_colour">订单号:</span><span class="td_colour" id="orderNumbers">${data.evaluation.orderNumber}</span>
					</div>
					<div class="col-sm-6">
						<span class="td_colour">运单号:</span><span class="td_colour" id="wayBillNumber">${data.evaluation.wayBillNumber}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价星级:</span><span class="td_colour" id="evaluateLevel">${data.evaluation.evaluateLevel}级</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价时间:</span><span class="td_colour" id="evaluateTime">${data.evaluation.evaluateTimeStr}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价内容:</span><span class="td_colour" id="evaluateContent">${data.evaluation.evaluateContent}</span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;" class="td_colour td_style">评价回复</td>
			<td>
				<div style="margin-left: 5px; line-height: 40px;">
					<div class="col-sm-12">
						<span class="td_colour">评价回复时间:</span><span class="td_colour"  id="replyTime">${data.evaluationReply.replyTimeStr}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价回复账号:</span><span class="td_colour"  id="replyPeople">${data.evaluationReply.replyContent}</span>
					</div>
					<div class="col-sm-12">
						<span class="td_colour">评价回复内容:</span><span class="td_colour"  id="replyContent">${data.evaluationReply.replyPeople}</span>
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
		window.location="${configProps['project']}/system/pageJump.shtml?url=/system/order/order_evaluate_list";
	}
</script>	
</body>
</html>