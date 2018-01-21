<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<title>4.21 对账文件下载接口</title>
</head>
<body>
	<table width="80%" border="0" align="center" cellpadding="10" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="3" bgcolor="#6BBE18">
				4.21 对账文件下载接口	
			</th>
	  	</tr> 
		<form method="post" action="${configProps['project']}/yeePay/downloadOrderDocument.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="35%" align="right">&nbsp;对账日期 : </td>
				<td width="65%" align="left"> 
					<input type="date" name="checkDate" placeholder="格式：yyyy-MM-dd">
				</td>
			</tr>

			<tr >
				<td width="35%" align="right">&nbsp;对账类别 : </td>
				<td width="65%" align="left"> 
					<input type="radio" name="orderType" id="pay" value="pay" checked />
					<label for="pay">交易对账单</label>
					<input type="radio" name="orderType" id="refund" value="refund"/>
					<label for="refund">退款对账单</label>
					<input type="radio" name="orderType" id="divide" value="divide" />
					<label for="pay">分账对账单</label>
					<input type="radio" name="orderType" id="transfer" value="transfer"/>
					<label for="refund">转账对账单</label>
					<input type="radio" name="orderType" id="cash" value="cash" />
					<label for="pay">提现对账单</label>
				</td>
			</tr>

			<tr >
				<td width="35%" align="right">&nbsp;对账文件 : </td>
				<td width="65%" align="left"> 
					<input type="radio" name="fileType" id="txt" value="txt" checked />
					<label for="txt">txt</label>
					<input type="radio" name="fileType" id="csv" value="csv"/>
					<label for="csv">csv</label>
				</td>
			</tr>

			<tr >
				<td width="35%" align="right">&nbsp;</td>
				<td width="65%" align="left"> 
					<input type="submit" value="submit" />
				</td>
			</tr>
		</form>
	</table>
</body>
</html>
