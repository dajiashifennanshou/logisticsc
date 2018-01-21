<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SimpleDateFormat dateFormat		= new SimpleDateFormat("yyMMdd_HHmmssSSS");
	String requestid				= "ZGTREFUND" + dateFormat.format(new Date());
%>
<html>
<head>
	<meta charset="UTF-8" />
	<title>4.12 订单退款接口</title>
</head>

<body>
	<br><br>
	<table width="80%" border="0" align="center" cellpadding="9" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.12 订单退款接口
			</th>
	  	</tr> 

		<form method="post" action="${configProps['project']}/yeePay/refund.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="30%" align="right">退款请求号[requestid]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="requestid" value="<%=requestid%>">
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">商户订单号[orderrequestid]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="orderrequestid" value="">
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">退款金额[amount]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="amount" value="">
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">子账户退款详情[divideinfo]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="divideinfo" value="">
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">确认退款[confirm]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="confirm" value="1" readonly="readonly">
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">退款备注[memo]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="memo" value="">
				</td>
			</tr>

			<tr >
				<td width="30%" align="left">&nbsp;</td>
				<td width="70%" align="left"> 
					<input type="submit" value="submit" />
				</td>
			</tr>
		</form>
	</table>
</body>
</html>
