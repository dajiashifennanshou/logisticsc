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

	String customernumber		= formatStr(responseDataMap.get("customernumber"));
	String cashrequestid	 	= formatStr(responseDataMap.get("cashrequestid"));
	String code             	= formatStr(responseDataMap.get("code"));
	String ledgerno         	= formatStr(responseDataMap.get("ledgerno"));
	String amount           	= formatStr(responseDataMap.get("amount"));
	String status           	= formatStr(responseDataMap.get("status"));
	String lastno           	= formatStr(responseDataMap.get("lastno"));
	String feetype 				= formatStr(responseDataMap.get("feetype"));
	String fee 				  	= formatStr(responseDataMap.get("fee"));
	String desc 	          	= formatStr(responseDataMap.get("desc"));
	String hmac					= formatStr(responseDataMap.get("hmac"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.17 提现查询接口</title>
</head>

<body>	
	<br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="word-break:break-all; border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.17 提现查询接口
			</th>
	  	</tr>

		<tr >
			<td width="40%" align="right">商户编号[customernumber]：</td>
			<td width="60%"  align="left"> <%=customernumber%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">提现请求号[cashrequestid]：</td>
			<td width="60%" align="left"> <%=cashrequestid%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">返回码[code]：</td>
			<td width="60%" align="left"> <%=code%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">子账户商户编号[ledgerno]：</td>
			<td width="60%" align="left"> <%=ledgerno%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">提现金额[amount]：</td>
			<td width="60%" align="left"> <%=amount%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">提现状态[status]：</td>
			<td width="60%" align="left"> <%=status%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">银行卡后四位[lastno]：</td>
			<td width="60%" align="left"> <%=lastno%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">手续费扣费类型[feetype]：</td>
			<td width="60%" align="left"> <%=feetype%> </td>
		</tr>
		
		<tr>
			<td width="40%" align="right">手续费金额[fee]：</td>
			<td width="60%" align="left"> <%=fee%> </td>
		</tr>
		
		<tr>
			<td width="40%" align="right">描述[desc]：</td>
			<td width="60%" align="left"> <%=desc%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">hmac：</td>
			<td width="60%" align="left"> <%=hmac%> </td>
		</tr>
	</table>
</body>
</html>
