<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SimpleDateFormat dateFormat		= new SimpleDateFormat("yyMMdd_HHmmssSSS");
	String requestid				= "ZGTMODIFYREQUEST" + dateFormat.format(new Date());
%>
<html>
<head>
	<meta charset="UTF-8" />
	<title>4.2 账户信息修改接口</title>
</head>
<body>
	<br><br>
	<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
				4.2 账户信息修改接口
			</th>
	  	</tr> 

		<form method="POST" action="${configProps['project']}/yeePay/modify.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="30%" align="right">修改请求号[requestid]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="requestid" value="<%=requestid%>"/>
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">子账户商编[ledgerno]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="ledgerno" placeholder="若不填写，则修改主商编的账户信息。" value=""/>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡号[bankaccountnumber]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankaccountnumber" placeholder="当银行卡号有变动时，在易宝会自动审核。" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">开户行[bankname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankname" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">开户名[accountname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="accountname" placeholder="开户名不允许修改，如果写错只能重新注册。" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡类型[bankaccounttype]：</td>
				<td width="70%" align="left"> 
					<input type="radio" name="bankaccounttype" id="PrivateCash" value="PrivateCash" checked />
					<label for="PrivateCash">对私</label>
					<input type="radio" name="bankaccounttype" id="PublicCash" value="PublicCash"/>
					<label for="PublicCash">对公</label>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡开户省[bankprovince]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankprovince" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡开户市[bankcity]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankcity" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">起结金额[minsettleamount]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="minsettleamount" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">结算周期[riskreserveday]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="riskreserveday" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>
			
			<tr >
				<td width="30%" align="right">是否自助结算[manualsettle]：</td>
				<td width="70%" align="left"> 
					<input type="radio" name="manualsettle" id="N" value="N" checked />
					<label for="N">否</label>
					<input type="radio" name="manualsettle" id="Y" value="Y"/>
					<label for="Y">是</label>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">后台回调地址[callbackurl]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="callbackurl" value="http://localhost:8080/zgt-java/ModifyRequestApiCallback.do" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">绑定手机号[bindmobild]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bindmobile" value="" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">&nbsp;</td>
				<td width="70%" align="left"> 
					<input type="submit" value="submit" />
				</td>
			</tr>
		</form>
	</table>
</body>
</html>
