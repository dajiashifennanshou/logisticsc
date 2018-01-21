<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SimpleDateFormat dateFormat		= new SimpleDateFormat("yyMMdd_HHmmssSSS");
	String requestid				= "ZGTCASH" + dateFormat.format(new Date());
%>
<html>
<head>
	<meta charset="UTF-8" />
	<title>4.16 提现接口</title>
</head>

<body>
	<br><br>
	<table width="80%" border="0" align="center" cellpadding="9" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.16 提现接口
			</th>
	  	</tr> 

		<form method="post" action="${configProps['project']}/yeePay/cashTransfer.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="30%" align="right">提现请求号[requestid]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="requestid" value="<%=requestid%>" required>
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">子账户商户编号[ledgerno]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="ledgerno" value="" placeholder="若为空，则为主账号商户提现，否则，为该子账户提现">
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">提现金额[amount]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="amount" value="" required>
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">后台回调地址[callbackurl]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="callbackurl" value="http://localhost:8080/zgt-java/CashTransferApiCallback.do" placeholder="为空时，无后台回调">
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">手续费扣费类型[feetype]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="feetype" value="SOURCE">
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
