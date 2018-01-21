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

	String customernumber		  = formatStr(responseDataMap.get("customernumber"));
	String orderrequestid		  = formatStr(responseDataMap.get("orderrequestid"));
	String code 				  = formatStr(responseDataMap.get("code"));
	String externalid			  = formatStr(responseDataMap.get("externalid"));
	String refundinfo			  = formatStr(responseDataMap.get("refundinfo"));
	String hmac					  = formatStr(responseDataMap.get("hmac"));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.13 订单退款查询接口</title>
</head>

<body>	
	<br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="word-break:break-all; border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.13 订单退款查询接口
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
			<td width="40%" align="right">易宝流水号[externalid]：</td>
			<td width="60%" align="left"> <%=externalid%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">退款详情[refundinfo]：</td>
			<td width="60%" align="left"> <%=refundinfo%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">hmac：</td>
			<td width="60%" align="left"> <%=hmac%> </td>
		</tr>
	</table>
</body>
</html>
