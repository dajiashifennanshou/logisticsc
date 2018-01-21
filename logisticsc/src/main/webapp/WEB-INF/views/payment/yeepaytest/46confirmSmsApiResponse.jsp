<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! String formatStr(String text){
		return text==null ? "" : text.trim();
	}
%>
<%
	request.setCharacterEncoding("UTF-8");

	Map<String, String> responseDataMap	= (Map<String, String>)request.getAttribute("responseDataMap");

	String customernumber	 	= formatStr(responseDataMap.get("customernumber"));
	String orderrequestid		= formatStr(responseDataMap.get("orderrequestid"));
	String code 				= formatStr(responseDataMap.get("code"));
	String amount 				= formatStr(responseDataMap.get("amount"));
	String bindid 				= formatStr(responseDataMap.get("bindid"));
	String bankcode 			= formatStr(responseDataMap.get("bankcode"));
	String cardno 				= formatStr(responseDataMap.get("cardno"));
	String cardtype 			= formatStr(responseDataMap.get("cardtype"));
	String hmac					= formatStr(responseDataMap.get("hmac"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.6 无卡直连短信验证码确认接口</title>
</head>

<body>	
	<br> <br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
				4.6 无卡直连短信验证码确认接口
			</th>
	  	</tr>

		<tr >
			<td width="40%" align="right">商户编号[customernumber]：</td>
			<td width="60%"  align="left"> <%=customernumber%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">商户订单号[orderrequestid]：</td>
			<td width="60%" align="left"> <%=orderrequestid%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">返回码[code]：</td>
			<td width="60%" align="left"> <%=code%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">订单金额[amount]：</td>
			<td width="60%" align="left"> <%=amount%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">绑卡ID[bindid]：</td>
			<td width="60%" align="left"> <%=bindid%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">银行编码[bankcode]：</td>
			<td width="60%" align="left"> <%=bankcode%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">卡号后四位[cardno]：</td>
			<td width="60%" align="left"> <%=cardno%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">银行卡类型[cardtype]：</td>
			<td width="60%" align="left"> <%=cardtype%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">hmac：</td>
			<td width="60%" align="left"> <%=hmac%> </td>
		</tr>
	</table>
</body>
</html>
