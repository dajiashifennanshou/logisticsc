<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title>易宝支付-掌柜通-接口示例</title>
	<style type="text/css">
		tr {
			border:		none;
		}
		td {
			border:		none;
		}
		tr.tableCaption {
			padding-bottom:	5px;
		}
    </style>
</head>

<body>
<table border="0" align="center">
	<tr class="tableCaption">
		<td align="left">
			<h3>易宝支付-掌柜通-接口示例</h3>
		</td>
	</tr>
</table>
<hr>
<table border="0" align="center" cellpadding="10">
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/41registerApiRequest" 
			target="_blank" style="text-decoration:none;">4.1 子账户注册接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/42modifyRequestApiRequest" 
			target="_blank" style="text-decoration:none;">4.2 账户信息修改接口</a>
		</td>
	</tr>
	
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/43queryModifyRequestApiRequest" 
			target="_blank" style="text-decoration:none;">4.3 账户信息修改查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/44payApiRequest" 
			target="_blank" style="text-decoration:none;">4.4 订单支付接口</a>
		</td>
	</tr>
	
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/45sendSmsApiRequest" 
			target="_blank" style="text-decoration:none;">4.5 无卡直连短信验证码发送接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/46confirmSmsApiRequest" 
			target="_blank" style="text-decoration:none;">4.6 无卡直连短信验证码确认接口</a>
		</td>
	</tr>


	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/47queryOrderApiRequest" 
			target="_blank" style="text-decoration:none;">4.7 订单查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/48transferApiRequest" 
			target="_blank" style="text-decoration:none;">4.8 转账接口</a>
		</td>
	</tr>

	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/49transferQueryApiRequest" 
			target="_blank" style="text-decoration:none;">4.9 转账查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/410divideApiRequest" 
			target="_blank" style="text-decoration:none;">4.10 分账接口</a>
		</td>
	</tr>
	
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/411queryDivideApiRequest" 
			target="_blank" style="text-decoration:none;">4.11 分账查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/412refundApiRequest" 
			target="_blank" style="text-decoration:none;">4.12 订单退款接口</a>
		</td>
	</tr>
	
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/413queryRefundApiRequest" 
			target="_blank" style="text-decoration:none;">4.13 订单退款查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/414settleConfirmApiRequest" 
			target="_blank" style="text-decoration:none;">4.14 担保确认接口</a>
		</td>
	</tr>

	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/415queryBalanceApiRequest" 
			target="_blank" style="text-decoration:none;">4.15 余额查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/416cashTransferApiRequest" 
			target="_blank" style="text-decoration:none;">4.16 提现接口</a>
		</td>
	</tr>
	
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/417queryCashTransferApiRequest" 
			target="_blank" style="text-decoration:none;">4.17 提现查询接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/418querySettlementApiRequest" 
			target="_blank" style="text-decoration:none;">4.18 结算结果查询接口</a>
		</td>
	</tr>

	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/419queryBindCardsApiRequest" 
			target="_blank" style="text-decoration:none;">4.19 查询绑卡列表接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/420unbindCardApiRequest" 
			target="_blank" style="text-decoration:none;">4.20 解绑接口</a>
		</td>	
	</tr>
	
	<tr>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/421downloadOrderDocumentApiRequest" 
			target="_blank" style="text-decoration:none;">4.21 对账文件下载接口</a>
		</td>
		<td align="left">
			<a href="${configProps['project']}/system/pageJump.shtml?url=/payment/yeepaytest/upload" 
			target="_blank" style="text-decoration:none;">分账方资质上传接口</a>
		</td>
	</tr>
	
</table>
<hr>
</body>
</html>