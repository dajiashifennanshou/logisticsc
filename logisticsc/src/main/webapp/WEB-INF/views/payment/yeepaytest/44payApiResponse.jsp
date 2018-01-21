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
	//out.println(responseDataMap);
	
	String customernumber	= formatStr(responseDataMap.get("customernumber"));
	String requestid        = formatStr(responseDataMap.get("requestid"));
	String code             = formatStr(responseDataMap.get("code"));
	String externalid       = formatStr(responseDataMap.get("externalid"));
	String amount           = formatStr(responseDataMap.get("amount"));
	String payurl           = formatStr(responseDataMap.get("payurl"));
	String bindid           = formatStr(responseDataMap.get("bindid"));
	String bankcode         = formatStr(responseDataMap.get("bankcode"));
	String cardno           = formatStr(responseDataMap.get("cardno"));
	String cardtype         = formatStr(responseDataMap.get("cardtype"));
	String hmac             = formatStr(responseDataMap.get("hmac"));
	
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>掌柜通-支付接口返回参数</title>
</head>
<body>
	<br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				掌柜通-支付接口返回参数
			</th>
	  	</tr> 

		<tr >
			<td width="30%" align="right">商户编号[customernumber]：</td>
			<td width="70%" align="left"> <%=customernumber%></td>
		</tr>

		<tr >
			<td width="30%" align="right">商户订单号[requestid]：</td>
			<td width="70%" align="left"> <%=requestid%></td>
		</tr>

		<tr >
			<td width="30%" align="right">返回码[code]：</td>
			<td width="70%" align="left"> <%=code%></td>
		</tr>

		<tr >
			<td width="30%" align="right">易宝流水号[externalid]：</td>
			<td width="70%" align="left"> <%=externalid%></td>
		</tr>

		<tr >
			<td width="30%" align="right">订单金额[amount]：</td>
			<td width="70%" align="left"> <%=amount%></td>
		</tr>

		<tr >
			<td width="30%" align="right">支付链接[payurl]：</td>
			<td width="70%" align="left"> <%=payurl%></td>
		</tr>

		<tr >
			<td width="30%" align="right">绑卡ID[bindid]：</td>
			<td width="70%" align="left"> <%=bindid%></td>
		</tr>

		<tr >
			<td width="30%" align="right">银行编码[bankcode]：</td>
			<td width="70%" align="left"> <%=bankcode%></td>
		</tr>

		<tr >
			<td width="30%" align="right">卡号后四位[cardno]：</td>
			<td width="70%" align="left"> <%=cardno%></td>
		</tr>

		<tr >
			<td width="30%" align="right">银行卡类型[cardtype]：</td>
			<td width="70%" align="left"> <%=cardtype%></td>
		</tr>

		<tr >
			<td width="30%" align="right">hmac：</td>
			<td width="70%" align="left"> <%=hmac%></td>
		</tr>

	</table>
</body>
</html>
