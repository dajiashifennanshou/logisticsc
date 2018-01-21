package com.brightsoft.controller.system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.platformBank;
import com.brightsoft.model.platformBankAccount;
import com.brightsoft.model.sysBank;
import com.brightsoft.model.sysBankAccout;
import com.brightsoft.service.platform.PlatformBankConfigureServiceImpl;
import com.brightsoft.service.system.sysBankAccoutServiceImpl;
import com.brightsoft.service.system.sysBankServiceImpl;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;
import com.brightsoft.utils.yeepay.UploadImageManager;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;

@Controller
@RequestMapping("/sys/account")
public class SysBankAccountController {
	
	@Autowired
	private PlatformBankConfigureServiceImpl platformBankConfigure;
	
	@Autowired
	private sysBankAccoutServiceImpl sysBankAccoutServiceImpl;
	
	@Autowired
	private sysBankServiceImpl sysBankServiceImpl;
	
	
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	/**
	 * 跳转到绑定银行卡
	 * @return
	 */
	@RequestMapping("/toBangdingBank")
	public String toSysBangDingBank(){
		return "/system/myWallet/my_wallet";
	}

	@RequestMapping("/toAddBank")
	public String toAddBank(){
		return "/system/myWallet/my_wallet_addBank";
	}
	
	@RequestMapping("/toAddCredit")
	public String toAddQualification(){
		return "/system/myWallet/my_wallet_addQualification";
	}
	/**
	 * 上传资质
	 * @param session
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/dozhizhi")
	@ResponseBody 
	public Result dozhizhi(HttpSession session,HttpServletRequest request)throws IllegalStateException,IOException{
		Result srt = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		sysBankAccout accout = sysBankAccoutServiceImpl.selectSysUserId(user.getId());
		if(null == accout){
			srt.setMsg("请绑定银行卡");
			srt.setResult(false);
		}else{
			sysBank bank = sysBankServiceImpl.selectBankSysUserId(user.getId());
			List<Map<String, Object>> dataMapList = new ArrayList<Map<String,Object>>();
			if(request instanceof MultipartHttpServletRequest){
				for (int i = 0; i < 10; i++) {
					 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
		             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
		             if(null == multipartFile || multipartFile.isEmpty()){
		                 continue;
		             }else if(multipartFile.getSize() > 512000){
			            	srt.setMsg("上传图片必须小于512KB!");
			            	srt.setResult(false);
			     			return srt;
			           }else{
		            	 // 获得图片名称
		                 String fileName = multipartFile.getOriginalFilename();
		                 // 获取图片后缀名称
		                 String suffixName = UploadFileUtil.getFileSuffixName(fileName);
		                 //获取正确的图片格式
		                 String reg = bundle.getString("reg");
		                 if(suffixName.matches(reg)){
		                	 if(("fileName"+i).equals("fileName0")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "ID_CARD_FRONT", file);
			                     dataMapList.add(map);
			                     bank.setIdCardFront(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName1")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "ID_CARD_BACK", file);
			                     dataMapList.add(map);
			                    bank.setIdCcardBack(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName2")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "PERSON_PHOTO", file);
			                     dataMapList.add(map);
		                		 bank.setPersouPhoto(originalUrl); 
		                	 }else if(("fileName"+i).equals("fileName3")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "BANK_CARD_FRONT", file);
			                     dataMapList.add(map);
		                		 bank.setBankCardFront(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName4")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "BUSSINESS_LICENSE", file);
			                     dataMapList.add(map);
		                		 bank.setBusinesslicence(originalUrl); 
		                	 }else if(("fileName"+i).equals("fileName5")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "ID_CARD_FRONT", file);
			                     dataMapList.add(map);
			                     bank.setIdCardFront(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName6")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "ID_CARD_BACK", file);
			                     dataMapList.add(map);
			                     bank.setIdCcardBack(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName7")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "BANK_ACCOUNT_LICENCE", file);
			                     dataMapList.add(map);
		                		 bank.setBankAccountLicence(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName8")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "ORGANIZATION_CODE", file);
			                     dataMapList.add(map);
		                		 bank.setOrganizationCode(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName9")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(accout.getLedgerno(), "TAX_REGISTRATION", file);
			                     dataMapList.add(map);
		                		 bank.setTaxRegistron(originalUrl);
		                	 }
		                 }
		             }
				}
				try {
					List<Future<Map<String, String>>> futureList =	UploadImageManager.pushMessage(dataMapList);
					sysBank sysBank = new sysBank();
					sysBank.setId(bank.getId());
					for (int p = 0; p < futureList.size(); p++) {
						Future<Map<String, String>> future = futureList.get(p);
						Map<String, String> map = future.get();
						if(map.get("code").equals("1")){
							if(map.get("filetype").equals("ID_CARD_FRONT")){
								sysBank.setIdCardFront(bank.getIdCardFront());
							}else if(map.get("filetype").equals("ID_CARD_BACK")){
								sysBank.setIdCcardBack(bank.getIdCcardBack());
							}else if(map.get("filetype").equals("PERSON_PHOTO")){
								sysBank.setPersouPhoto(bank.getPersouPhoto());
							}else if(map.get("filetype").equals("BANK_CARD_FRONT")){
								sysBank.setBankCardFront(bank.getBankCardFront());
							}else if(map.get("filetype").equals("BUSSINESS_LICENSE")){
								sysBank.setBussinessLicense(bank.getBussinessLicense());
							}else if(map.get("filetype").equals("BANK_ACCOUNT_LICENCE")){
								sysBank.setBankAccountLicence(bank.getBankAccountLicence());
							}else if(map.get("filetype").equals("ORGANIZATION_CODE")){
								sysBank.setOrganizationCode(bank.getOrganizationCode());
							}else if(map.get("filetype").equals("TAX_REGISTRATION")){
								sysBank.setTaxRegistron(bank.getTaxRegistron());
							}
						}else{
							sysBank.setIsQualifications(Const.PLATFORM_ORDER_BANK_QS_1);
						}
					}
					if(sysBankServiceImpl.bangding(sysBank)){
						for (int j = 0; j < futureList.size(); j++) {
							Future<Map<String, String>> future = futureList.get(j);
							Map<String, String> map = future.get();
							if(!map.get("code").equals("1")){
								srt.setMsg(map.get("msg"));
								srt.setResult(false);
								return srt;
							}
						}
						sysBank.setIsQualifications(Const.PLATFORM_ORDER_BANK_QS_0);
						if(sysBankServiceImpl.bangding(sysBank)){
							srt.setMsg("上传资质成功，请等待审核！");
							srt.setResult(true);
						}else{
							srt.setMsg("上传资质失败，请联系系统客服！");
							srt.setResult(true);
						}
					}else{
						srt.setMsg("上传资质失败，请联系系统客服！");
						srt.setResult(true);
					}
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return srt;
	}
	/**
	 * 绑定银行卡信息
	 * @param session
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/doBank")
	@ResponseBody
	public Result doBank(sysBank bank,HttpSession session,HttpServletRequest request)throws IllegalStateException,IOException{
		Result srt = new Result();
		com.brightsoft.model.platformBankConfigure bankConfigure = platformBankConfigure.selectBankConfigure();
		StringBuffer sb = new StringBuffer();
		sb.append("BANK");
		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
		bank.setRequestid(sb.toString());
		bank.setSignedname(bank.getLinkman());
		bank.setMinsettleamount(bankConfigure.getMinsettleamount());
		bank.setRiskreserveday(bankConfigure.getRiskreserveday());
		bank.setManualsettle(bankConfigure.getManualsettle());
		bank.setState(Const.PLATFORM_ORDER_BANK_STATE_0);
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
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
			request.setAttribute("businesslicence",bank.getBankAccountLicence());
			request.setAttribute("legalperson",bank.getLegalperson());
		}
		//子账户注册
		String data = ZGTDataAttribute.buildRegisterData(request);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REGISTERAPI_NAME);
		Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.REGISTERAPI_RESPONSE_HMAC_ORDER);
		Map<String, String> responseMap = (Map<String, String>) result.getData();
		//第三步 判断请求是否成功，
		if(!responseMap.get("code").equals("1")) {
			srt.setMsg(responseMap.get("msg"));
			srt.setResult(false);
			return srt ;
		}else{
			sysBankAccout bankAccount = null;
			bankAccount = sysBankAccoutServiceImpl.selectSysUserId(user.getId());
			sysBankAccout accout = new sysBankAccout();
			accout.setCustomernumber(responseMap.get("customernumber"));
			accout.setHmac(responseMap.get("hmac"));
			accout.setLedgerno(responseMap.get("ledgerno"));
			accout.setRequestid(bank.getRequestid());
			accout.setTime(new Date());
			accout.setSysUserId(user.getId());
			if(null == bankAccount){
				if(sysBankAccoutServiceImpl.inserBankAccount(accout)){
					bank.setSysUserId(user.getId());
					if(sysBankServiceImpl.bangding(bank)){
						srt.setMsg("绑定银行卡成功");
						srt.setResult(true);
					}else{
						srt.setMsg("绑定银行卡失败");
						srt.setResult(true);
					}
				}
			}else{
				accout.setId(bankAccount.getId());
				if(sysBankAccoutServiceImpl.updateBankAccount(accout)){
					sysBank sysBank = sysBankServiceImpl.selectBankSysUserId(user.getId());
					if(!bankAccount.getLedgerno().equals(accout.getLedgerno())){
						bank.setIsQualifications(Const.PLATFORM_ORDER_BANK_QS_1);
						bank.setIdCardFront(bank.getIdCardFront());
						bank.setIdCcardBack(bank.getIdCcardBack());
						bank.setPersouPhoto(bank.getPersouPhoto());
						bank.setBankCardFront(bank.getBankCardFront());
						bank.setBussinessLicense(bank.getBussinessLicense());
						bank.setBankAccountLicence(bank.getBankAccountLicence());
						bank.setOrganizationCode(bank.getOrganizationCode());
						bank.setTaxRegistron(bank.getTaxRegistron());
						bank.setIdCardFront("");
						bank.setIdCcardBack("");
						bank.setPersouPhoto("");
						bank.setBankCardFront("");
						bank.setBussinessLicense("");
						bank.setBussinessLicense("");
						bank.setOrganizationCode("");
						bank.setTaxRegistron("");
					}
					bank.setId(sysBank.getId());
					if(sysBankServiceImpl.bangding(bank)){
						srt.setMsg("绑定银行卡成功");
						srt.setResult(true);
					}else{
						srt.setMsg("绑定银行卡失败");
						srt.setResult(true);
					}
				}
			}
		}
		return srt;
	}
	/**
	 * 余额 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/getJine")
	@ResponseBody
	public Result getJine(HttpSession session,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Result srt = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		//查询用户子账户
		sysBankAccout account = null;
		account = sysBankAccoutServiceImpl.selectSysUserId(user.getId());
		if(null == account){
			srt.setResult(false);
			return srt ;
		}else{
			request.setAttribute("ledgerno",account.getLedgerno());
			String data = ZGTDataAttribute.buildQueryBalanceData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYBALANCEAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYBALANCEAPI_RESPONSE_HMAC_ORDER);
			Map<String, String> responseMap = (Map<String, String>) result.getData();
			//第三步 判断请求是否成功，
			if(!responseMap.get("code").equals("1")) {
				srt.setResult(false);
				return srt ;
			}else{
				srt.setResult(true);
				srt.setData(responseMap.get("ledgerbalance"));
				return srt ;
			}
		}
	}
	/**
	 * 银行信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getBankUserId")
	@ResponseBody
	public Result getBankUserId(HttpSession session){
		Result ret = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		sysBank bank =null; 
		bank= sysBankServiceImpl.selectBankSysUserId(user.getId());
		if(null == bank){
			ret.setResult(false);
		}else{
			ret.setResult(true);
			ret.setData(bank);
		}
		return ret;
	}
}
