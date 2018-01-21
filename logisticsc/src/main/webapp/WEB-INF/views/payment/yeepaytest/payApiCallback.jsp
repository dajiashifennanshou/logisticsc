<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map"%>
<%! String formatStr(String text) {
		return (text == null) ? "" : text.trim();
	}
%>
<%
	request.setCharacterEncoding("UTF-8");

	Map<String, String> dataMap	= (Map<String, String>)request.getAttribute("dataMap");
	
	String customernumber      = formatStr(dataMap.get("customernumber"));
	String requestid           = formatStr(dataMap.get("requestid"));
	String code                = formatStr(dataMap.get("code"));
	String notifytype          = formatStr(dataMap.get("notifytype"));
	String externalid          = formatStr(dataMap.get("externalid"));
	String amount              = formatStr(dataMap.get("amount"));
	String cardno              = formatStr(dataMap.get("cardno"));
	String bankcode            = formatStr(dataMap.get("bankcode"));
	String cardtype            = formatStr(dataMap.get("cardtype"));
	String msg	               = formatStr(dataMap.get("msg"));
	String hmac                = formatStr(dataMap.get("hmac"));
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单支付结果通知</title>
</head>
<body>	
	<br> <br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
				订单支付结果通知
			</th>
	  	</tr>

		<tr>
			<td width="30%" align="right">商户编号[customernumber]：</td>
			<td width="70%" align="left"> <%=customernumber%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">返回码[code]：</td>
			<td width="70%" align="left"> <%=code%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">通知类型[notifytype]：</td>
			<td width="70%" align="left"> <%=notifytype%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">商户订单号[requestid]：</td>
			<td width="70%" align="left"> <%=requestid%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">交易流水号[externalid]：</td>
			<td width="70%" align="left"> <%=externalid%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">订单金额[amount]：</td>
			<td width="70%" align="left"> <%=amount%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">银行卡号[cardno]：</td>
			<td width="70%" align="left"> <%=cardno%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">银行编码[bankcode]：</td>
			<td width="70%" align="left"> <%=bankcode%> </td>
		</tr>

		<tr>
			<td width="30%" align="right">银行卡类型[cardtype]：</td>
			<td width="70%" align="left"> <%=cardtype%> </td>
		</tr>
	</table>
</body>
</html>
