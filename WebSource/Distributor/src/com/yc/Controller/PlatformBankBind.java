package com.yc.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformBank;
import com.yc.Entity.PlatformBankAccount;
import com.yc.Entity.PlatformBankConfigure;
import com.yc.Service.PlatformBankAccountService;
import com.yc.Service.PlatformBankConfigureService;
import com.yc.Service.PlatformBankService;
import com.yc.Tool.DES;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.FileUtil;
import com.yc.Tool.StrUtil;
import com.yc.Tool.yeepay.ZGTDATA;
import com.yc.Tool.yeepay.ZGTUtils;

/**
 * 绑定银行卡
 * @Author:luojing
 * @2016年7月22日 下午5:30:13
 */
@Controller
public class PlatformBankBind {
	
	@Autowired
	private PlatformBankService bankService;
	@Autowired
	private PlatformBankConfigureService bankConfigureService;
	@Autowired
	private PlatformBankAccountService bankAccountService;

	/**
	 * 银行卡绑定
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @Author:luojing
	 * @2016年7月22日 下午5:31:05
	 */
	@RequestMapping("app/bankBinding")
	public void bankBinding(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		try{
			PlatformBank bank = (PlatformBank) FengUtil.getObject(request.getParameterMap(),new PlatformBank());
			//注册请求号
			StringBuffer sb = new StringBuffer();
			sb.append("BANK");
			sb.append(DateUtil.getDateTime());
			//得到银行卡配配置信息
			PlatformBankConfigure conf = bankConfigureService.getPlatformBankConfigure();
			bank.setRequestid(sb.toString());
			bank.setSignedname(bank.getLinkman());
			bank.setMinsettleamount(conf.getMinsettleamount());
			bank.setRiskreserveday(conf.getRiskreserveday());
			bank.setManualsettle(conf.getManualsettle());
			bank.setBank_time(DateUtil.getDateTimeFormatString());
			bank.setState(Constant.PLATFORM_ORDER_BANK_STATE_0);
			bank.setIs_qualifications(Constant.PLATFORM_ORDER_BANK_QS_0);
			
			request.setAttribute("requestid",bank.getRequestid());
			request.setAttribute("bindmobile",bank.getBindmobile());
			request.setAttribute("customertype",bank.getCustomertype());
			request.setAttribute("signedname",bank.getLinkman());
			request.setAttribute("linkman",bank.getLinkman());
			request.setAttribute("minsettleamount",bank.getMinsettleamount());
			request.setAttribute("riskreserveday",bank.getRiskreserveday());  
			request.setAttribute("bankaccountnumber",bank.getBankaccountnumber());
			request.setAttribute("bankname",bank.getBankname());
			request.setAttribute("accountname",bank.getAccountname());
			request.setAttribute("bankaccounttype",bank.getBankaccounttype());
			request.setAttribute("bankprovince",bank.getBankprovince());
			request.setAttribute("bankcity",bank.getBankcity());
			request.setAttribute("manualsettle",bank.getManualsettle());
			if(bank.getCustomertype().equals("PERSON")){
				request.setAttribute("idcard",bank.getIdcard());
			}else if(bank.getCustomertype().equals("ENTERPRISE")){
				request.setAttribute("businesslicence",bank.getBank_account_licence());
				request.setAttribute("legalperson",bank.getLegalperson());
			}
			
			//注册子账号
			// 生成密文data
			String data = ZGTDATA.getRegister(request);
			// 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REGISTERAPI_NAME);
			Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
			// 解密同步响应密文data，获取明文参数
			String responseData	= responseMap.get("data");
			Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
			//hmac签名验证
			if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.REGISTERAPI_RESPONSE_HMAC_ORDER)) {
				FengUtil.RuntimeExceptionFeng("签名失败");
			}
			//验证求生是否成功
			if(!"1".equals(responseDataMap.get("code"))) {
				FengUtil.RuntimeExceptionFeng("请求失败");
			}else{
				PlatformBankAccount bankAccount = bankAccountService.getBankAccount(new PlatformBankAccount(bank.getUser_id()));
				PlatformBankAccount account = new PlatformBankAccount();
				account.setCustomernumber(responseDataMap.get("customernumber"));
				account.setHmac(responseDataMap.get("hmac"));
				account.setLedgerno(responseDataMap.get("ledgerno"));
				account.setRequestid(responseDataMap.get("requestid"));
				account.setTime(DateUtil.getDateTimeFormatString());
				account.setUser_id(bank.getUser_id());
				if(bankAccount==null){
					//添加
					if(bankAccountService.addBankAccount(account)>0){
						if(bankService.addSingleInfo(bank)>0){
							FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
						}else{
							FengUtil.RuntimeExceptionFeng("绑定银行卡失败");
						}
					}
				}else{
					//存在修改
					account.setId(bankAccount.getId());
					if(bankAccountService.updateBankAccount(account)>0){
						PlatformBank platformBank = bankService.getPlatformBank(bank.getUser_id());
						if(!bankAccount.getLedgerno().equals(account.getLedgerno())){
							bank.setIs_qualifications(Constant.PLATFORM_ORDER_BANK_QS_1);
							bank.setId_card_front("");
							bank.setId_ccard_back("");
							bank.setPersou_photo("");
							bank.setBank_card_front("");
							bank.setBusinesslicence("");
							bank.setOrganization_code("");
							bank.setTax_registron("");
						}
						bank.setId(platformBank.getId());
						if(bankService.updateSingleInfo(bank)>0){
							FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
						}else{
							FengUtil.RuntimeExceptionFeng("绑定银行卡失败");
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
	
	/**
	 * 上传银行卡资质
	 * @Author:luojing
	 * @2016年7月23日 下午12:19:51
	 */
	@RequestMapping("app/uploadBankImg")
	public void uploadBankImg(HttpServletRequest request,HttpServletResponse response){
		try{
			String userId = DES.decrypt(request.getParameter("userId"));//解密
			String txt = FileUtil.uploadBankImg(request, userId);
			if(StrUtil.VString(txt)){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, txt, response);
			}else{
				FengUtil.RuntimeExceptionFeng("文件上传失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
}
