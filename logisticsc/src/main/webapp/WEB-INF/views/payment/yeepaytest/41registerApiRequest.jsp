<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SimpleDateFormat dateFormat		= new SimpleDateFormat("yyMMdd_HHmmssSSS");
	String requestid				= "ZGTREGISTER" + dateFormat.format(new Date());
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>4.1 子账户注册接口</title>
</head>
<body>
	<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="5" bgcolor="#6BBE18">
				4.1 子账户注册接口
			</th>
	  	</tr> 

		<form method="POST" action="${configProps['project']}/yeePay/register.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="30%" align="right">注册请求号[requestid]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="requestid" value="<%=requestid%>"/>
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">绑定手机号[bindmobile]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bindmobile" value="13712348888" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">注册类型[customertype]：</td>
				<td width="70%" align="left"> 
					<input type="radio" name="customertype" id="PERSON" value="PERSON" checked />
					<label for="PERSON">个人</label>
					<input type="radio" name="customertype" id="ENTERPRISE" value="ENTERPRISE"/>
					<label for="ENTERPRISE">企业</label>
				</td>
			</tr> 


			<tr >
				<td width="30%" align="right">签约名[signedname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="signedname" value="掌柜通测试" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">联系人姓名[linkman]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="linkman" value="王英杰" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">个人身份证号[idcard]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="idcard" value="123456789012345678" placeholder="「注册类型」为「个人」时，该参数必填！"/>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">企业营业执照号[businesslicence]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="businesslicence" value="" placeholder="「注册类型」为「企业」时，该参数必填！"/>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">法人姓名[legalperson]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="legalperson" value="" placeholder="「注册类型」为「企业」时，该参数必填！"/>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">起结金额[minsettleamount]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="minsettleamount" value="1" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">结算周期[riskreserveday]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="riskreserveday" value="1" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡号[bankaccountnumber]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankaccountnumber" value="6214830108344317" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡开户行[bankname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankname" value="招商银行股份有限公司北京京广桥支行" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡开户名[accountname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="accountname" value="王英杰" />
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
					<input size="70" type="text" name="bankprovince" value="北京" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行卡开户市[bankcity]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankcity" value="北京" />
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
				<td width="30%" align="right">保证金[deposit]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="deposit" value="" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">邮箱[email]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="email" value="" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">&nbsp;</td>
				<td width="70%" align="left"> 
					<input type="submit" value="提交注册信息" />
				</td>
			</tr>

		</form>
	</table>
</body>
</html>
