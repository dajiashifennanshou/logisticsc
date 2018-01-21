<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	SimpleDateFormat dateFormat		= new SimpleDateFormat("yyMMdd_HHmmssSSS");
	String requestid				= "ZGTPAY" + dateFormat.format(new Date());
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>4.4 订单支付接口</title>

	<style type="text/css">
		tr.onekeyClose {
			display: none;
		}

		tr.directClose {
			display: none;
		}

		tr.commonClose {
			display: none;
		}

		tr.usernoClose {
			display: none;
		}
		
		tr.mccClose {
			display: none;
		}

		tr.bindidClose {
			display: none;
		}

		tr.ledgernoClose {
			display: none;
		}
	</style>

	<script type="text/javascript">

		function closeOnekey() {
			document.getElementById('onekey01').className='onekeyClose';
		}

		function openOnekey() {
			document.getElementById('onekey01').className='';
		}

		function closeDirect() {
			document.getElementById('direct01').className='directClose';
			document.getElementById('direct02').className='directClose';
			document.getElementById('direct03').className='directClose';
			document.getElementById('direct04').className='directClose';
		}

		function openDirect() {
			document.getElementById('direct01').className='';
			document.getElementById('direct02').className='';
			document.getElementById('direct03').className='';
			document.getElementById('direct04').className='';
		}

		function closeCommon() {
			document.getElementById('common01').className='commonClose';
			document.getElementById('common02').className='commonClose';
			document.getElementById('common03').className='commonClose';
		}

		function openCommon() {
			document.getElementById('common01').className='';
			document.getElementById('common02').className='';
			document.getElementById('common03').className='';
		}

		function closeBindid() {
			document.getElementById('bindidTr').className='bindidClose';
		}

		function openBindid() {
			document.getElementById('bindidTr').className='';
		}

		function closeUserno() {
			document.getElementById('usernoTr').className='usernoClose';
		}

		function openUserno() {
			document.getElementById('usernoTr').className='';
		}

		function closeMcc() {
			document.getElementById('mccTr').className='mccClose';
		}

		function openMcc() {
			document.getElementById('mccTr').className='';
		}

		function closeLedgerno() {
			document.getElementById('ledgernoTr').className='ledgernoClose';
		}

		function openLedgerno() {
			document.getElementById('ledgernoTr').className='';
		}

		function clean() {
			document.getElementById('userno').value='';
			document.getElementById('ip').value='';
			document.getElementById('isbind').value='';
			document.getElementById('bindid').value='';
			document.getElementById('cardname').value='';
			document.getElementById('idcard').value='';
			document.getElementById('bankcardnum').value='';
			document.getElementById('mobilephone').value='';
			document.getElementById('cvv2').value='';
			document.getElementById('expiredate').value='';
			document.getElementById('mcc').value='7993';
			document.getElementById('ledgerno').value='';
		}

	</script>
</head>
<body>
	<br>
	<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" style="border:solid 1px #107929">
		<tr>
	  		<th align="center" height="30" colspan="2" bgcolor="#6BBE18">
				4.4 订单支付接口
			</th>
	  	</tr> 

		<form method="post" action="${configProps['project']}/yeePay/pay.pay" target="_blank" accept-charset="UTF-8">
			<tr >
				<td width="30%" align="right">商户订单号[requestid]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="requestid" value="<%=requestid%>"/>
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">支付金额[amount]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="amount" value="0.01" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">是否担保[assure]：</td>
				<td width="70%" align="left"> 
					<input type="radio" name="assure" id="assure_0" value="0" checked />
					<label for="assure_0">非担保交易</label>
					<input type="radio" name="assure" id="assure_1" value="1"/>
					<label for="assure_1">担保交易</label>
				</td>
			</tr> 

			<tr >
				<td width="30%" align="right">商品名称[productname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="productname" value="productname哈哈测试喵喵喵" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">商品种类[productcat]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="productcat" value="productcat" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">商品描述[productdesc]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="productdesc" value="productdesc" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">分账详情[divideinfo]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="divideinfo" value="" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">后台通知地址[callbackurl]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="callbackurl" value="http://localhost:8080/zgt-java/PayApiCallback.do" />
					<span style="color:#FF0000;font-weight:100;">*</span>
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">页面通知地址[webcallbackurl]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="webcallbackurl" value="http://localhost:8080/zgt-java/PayApiCallback.do" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">银行编码[bankid]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankid" value="" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">担保有效期时间[period]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="period" placeholder="担保交易时必填，最大值30。"value="" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">订单备注[memo]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="memo" value="" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">支付类型[payproducttype]：</td>
				<td width="70%" align="left"> 
					<input type="radio" name="payproducttype" id="sales_payproducttype" value="SALES" 
						   onclick= "clean(); closeOnekey(); closeDirect(); closeCommon(); closeUserno(); closeBindid(); closeMcc(); closeLedgerno();" checked />
					<label for="SALES">网银支付</label>

					<input type="radio" name="payproducttype" id="onekey_payproducttype" value="ONEKEY"
						   onclick="clean(); openOnekey(); openCommon(); closeDirect(); openUserno(); closeBindid(); closeMcc(); closeLedgerno();"/>
					<label for="ONEKEY">手机一键</label>

					<input type="radio" name="payproducttype" id="direct_payproducttype01" value="DIRECT"
						   onclick="clean(); openDirect(); openCommon(); closeOnekey(); openUserno(); closeBindid(); openMcc(); closeLedgerno();"/>
					<label for="DIRECT">无卡直连-首次支付</label>

					<input type="radio" name="payproducttype" id="direct_payproducttype02" value="DIRECT"
						   onclick="clean(); closeDirect(); openUserno(); openBindid(); closeCommon(); closeOnekey(); openMcc(); closeLedgerno();"/>
					<label for="DIRECT">无卡直连-绑卡支付</label>

					<input type="radio" name="payproducttype" id="ledger_payproducttype" value="LEDGER"
						   onclick= "clean(); closeOnekey(); closeDirect(); closeCommon(); closeUserno(); closeBindid(); closeMcc(); openLedgerno();"/>
					<label for="LEDGER">账户支付</label>
				</td>
			</tr> 

			<tr class="usernoClose" id="usernoTr">
				<td width="30%" align="right">用户标识[userno]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="userno" id="userno" placeholder="无卡直连-绑卡支付时必填！" />
				</td>
			</tr>

			<tr class="bindidClose" id="bindidTr">
				<td width="30%" align="right">绑卡ID[bindid]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bindid" id="bindid" placeholder="无卡直连-绑卡支付时必填！" />
				</td>
			</tr>

			<tr class="directClose" id="direct01">
				<td width="30%" align="right">是否绑卡[isbind]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="isbind" id="isbind" placeholder="N-不绑卡；Y-绑卡；空-不绑卡。">
				</td>
			</tr>

			<tr class="onekeyClose" id="onekey01">
				<td width="30%" align="right">用户IP[ip]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="ip" id="ip" value="" />
				</td>
			</tr>
			
			<tr class="commonClose" id="common01">
				<td width="30%" align="right">持卡人姓名[cardname]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="cardname" id="cardname" placeholder="无卡直连-首次支付时，必填！" value="" />
				</td>
			</tr>

			<tr class="commonClose" id="common02">
				<td width="30%" align="right">身份证号[idcard]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="idcard" id="idcard" placeholder="无卡直连-首次支付时，必填！" value="" />
				</td>
			</tr>

			<tr class="commonClose" id="common03">
				<td width="30%" align="right">银行卡号[bankcardnum]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="bankcardnum" id="bankcardnum" placeholder="无卡直连-首次支付时，必填！" value="" />
				</td>
			</tr>

			<tr class="directClose" id="direct02">
				<td width="30%" align="right">预留手机号[mobilephone]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="mobilephone" id="mobilephone" placeholder="无卡直连-首次支付时，必填！" value="" />
				</td>
			</tr>

			<tr class="directClose" id="direct03">
				<td width="30%" align="right">cvv2：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="cvv2" id="cvv2" placeholder="信用卡支付时必填" value="" />
				</td>
			</tr>

			<tr class="directClose" id="direct04">
				<td width="30%" align="right">信用卡有效期[expiredate]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="expiredate" id="expiredate" placeholder="信用卡支付时必填" value="" />
				</td>
			</tr>

			<tr class="mccClose" id="mccTr">
				<td width="30%" align="right">行业代码[mcc]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="mcc" id="mcc" placeholder="无卡直连时必填；测试商编mcc为7993" />
				</td>
			</tr>

			<tr class="ledgernoClose" id="ledgernoTr">
				<td width="30%" align="right">子账户商户编号[ledgerno]：</td>
				<td width="70%" align="left"> 
					<input size="70" type="text" name="ledgerno" id="ledgerno" placeholder="账户支付时，必填" />
				</td>
			</tr>

			<tr >
				<td width="30%" align="right">&nbsp;</td>
				<td width="70%" align="left"> 
					<input type="submit" value="提交订单" />
				</td>
			</tr>

		</form>
	</table>
</body>
</html>
