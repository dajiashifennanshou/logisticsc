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
	String requestid			= formatStr(responseDataMap.get("requestid")); 
	String code 				= formatStr(responseDataMap.get("code"));
	String externalid			= formatStr(responseDataMap.get("externalid")); 
	String amount				= formatStr(responseDataMap.get("amount")); 
	String productname			= formatStr(responseDataMap.get("productname")); 
	String productcat			= formatStr(responseDataMap.get("productcat")); 
	String productdesc			= formatStr(responseDataMap.get("productdesc")); 
	String status				= formatStr(responseDataMap.get("status")); 
	String ordertype			= formatStr(responseDataMap.get("ordertype")); 
	String busitype				= formatStr(responseDataMap.get("busitype")); 
	String orderdate			= formatStr(responseDataMap.get("orderdate")); 
	String createdate			= formatStr(responseDataMap.get("createdate")); 
	String bankid				= formatStr(responseDataMap.get("bankid")); 
	String bankcode				= formatStr(responseDataMap.get("bankcode")); 
	String userno				= formatStr(responseDataMap.get("userno")); 
	//String memberno				= formatStr(responseDataMap.get("memberno")); 
	String fee					= formatStr(responseDataMap.get("fee"));
	String name					= formatStr(responseDataMap.get("name")); 
	String lastno				= formatStr(responseDataMap.get("lastno")); 
	String phone				= formatStr(responseDataMap.get("phone")); 
	String cardtype				= formatStr(responseDataMap.get("cardtype")); 
	String payproducttype		= formatStr(responseDataMap.get("payproducttype")); 
	String ledgerno				= formatStr(responseDataMap.get("ledgerno")); 
	String errorcode			= formatStr(responseDataMap.get("errorcode")); 
	String errormsg				= formatStr(responseDataMap.get("errormsg")); 
	String hmac					= formatStr(responseDataMap.get("hmac")); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>4.7 订单查询接口</title>
</head>

<body>	
	<br>
	<table width="70%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.7 订单查询接口
			</th>
	  	</tr>

		<tr >
			<td width="40%" align="right">商户编号[customernumber]：</td>
			<td width="60%"  align="left"> <%=customernumber%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">商户订单号[requestid]：</td>
			<td width="60%" align="left"> <%=requestid%> </td>
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
			<td width="40%" align="right">订单金额[amount]：</td>
			<td width="60%" align="left"> <%=amount%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">商品名称[productname]：</td>
			<td width="60%" align="left"> <%=productname%> </td>
		</tr> 

		<tr>
			<td width="40%" align="right">商品类别[productcat]：</td>
			<td width="60%" align="left"> <%=productcat%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">商品描述[productdesc]：</td>
			<td width="60%" align="left"> <%=productdesc%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">订单状态[status]：</td>
			<td width="60%" align="left"> <%=status%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">订单类型[ordertype]：</td>
			<td width="60%" align="left"> <%=ordertype%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">业务类型[busitype]：</td>
			<td width="60%" align="left"> <%=busitype%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">订单创建时间[createdate]：</td>
			<td width="60%" align="left"> <%=createdate%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">下单时间[orderdate]：</td>
			<td width="60%" align="left"> <%=orderdate%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">易宝通道编码[bankid]：</td>
			<td width="60%" align="left"> <%=bankid%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">银行编码[bankcode]：</td>
			<td width="60%" align="left"> <%=bankcode%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">用户标识[userno]：</td>
			<td width="60%" align="left"> <%=userno%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">商户手续费[fee]：</td>
			<td width="60%" align="left"> <%=fee%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">姓名[name]：</td>
			<td width="60%" align="left"> <%=name%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">手机号[phone]：</td>
			<td width="60%" align="left"> <%=phone%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">卡号后4位[lastno]：</td>
			<td width="60%" align="left"> <%=lastno%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">银行卡类型[cardtype]：</td>
			<td width="60%" align="left"> <%=cardtype%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">支付产品类型[payproducttype]：</td>
			<td width="60%" align="left"> <%=payproducttype%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">子账户商户编号[ledgerno]：</td>
			<td width="60%" align="left"> <%=ledgerno%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">订单错误码[errorcode]：</td>
			<td width="60%" align="left"> <%=errorcode%> </td>
		</tr>

		<tr>
			<td width="40%" align="right">订单错描述[errormsg]：</td>
			<td width="60%" align="left"> <%=errormsg%> </td>
		</tr>

	</table>
</body>
</html>
