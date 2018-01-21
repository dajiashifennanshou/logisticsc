<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8" />
	<title>4.15 余额查询接口</title>
</head>

<body>
	<br><br>
	<table width="80%" border="0" align="center" cellpadding="9" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.15 余额查询接口
			</th>
	  	</tr> 

		<form method="post" action="${configProps['project']}/yeePay/queryBalance.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="30%" align="right">子账户商户编号[ledgerno]:</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="ledgerno" value="" placeholder="当ledgerno为空时，查询主账户的余额">
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
