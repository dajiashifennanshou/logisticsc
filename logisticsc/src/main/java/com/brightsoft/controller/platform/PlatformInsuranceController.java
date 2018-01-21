package com.brightsoft.controller.platform;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.InsuranceBackData;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.InsuranceCompany;
import com.brightsoft.model.InsuranceTsType;
import com.brightsoft.model.InsuranceType;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.platformBankPayment;
import com.brightsoft.model.platformBankRefund;
import com.brightsoft.model.platformBankSplit;
import com.brightsoft.model.sysBankAccout;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.InsuranceManageService;
import com.brightsoft.service.platform.InsuranceRateService;
import com.brightsoft.service.platform.PlatformInsuranceServiceImpl;
import com.brightsoft.service.platform.platformBankPaymentServiceImpl;
import com.brightsoft.service.platform.platformBankRefundServiceImpl;
import com.brightsoft.service.platform.platformBankSplitServiceImpl;
import com.brightsoft.service.system.sysBankAccoutServiceImpl;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
@RequestMapping("/insurance")
public class PlatformInsuranceController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private PlatformInsuranceServiceImpl platformInsuranceServiceImpl;
	
	@Autowired
	private DictionaryService dicitonaryService;
	
	@Autowired
	private InsuranceRateService insuranceRateService;
	
	@Autowired
	private InsuranceManageService InsuranceManageService;
	
	@Autowired
	private platformBankPaymentServiceImpl platformBankPayment;
	
	@Autowired
	private platformBankRefundServiceImpl platformBankRefundServiceImpl;
	
	@Autowired
	private sysBankAccoutServiceImpl sysBankAccoutServiceImpl;
	
	@Autowired
	private platformBankPaymentServiceImpl platformBankPaymentServiceImpl;
	
	@Autowired 
	private platformBankSplitServiceImpl platformBankSplitServiceImpl;
	/**
	 * 跳转到我的我要投保
	 * @return
	 */
	@RequestMapping("/toInsurance")
	public String toInsurance(Long insId,String chooseid,ModelMap model){
		List<InsuranceCompany> insCompanyList = InsuranceManageService.selectInsCompany();
//		ConfigResult configResult = insuranceHttpService.getComConfig();
//		List<PlatformDictionary> insRateList = dicitonaryService.selectDictDataByType(DictionaryType.INSURANCE_RATE);
		List<PlatformDictionary> insBaoZhuangList = dicitonaryService.selectDictDataByType(DictionaryType.INS_BAO_ZHUANG);
		List<PlatformDictionary> insGoodsTypeList = dicitonaryService.selectDictDataByType(DictionaryType.INS_GOODS_TYPE);
		List<PlatformDictionary> insLoadTypeList = dicitonaryService.selectDictDataByType(DictionaryType.INS_LOAD_TYPE);
		List<PlatformDictionary> insTransTypeList = dicitonaryService.selectDictDataByType(DictionaryType.INS_TRANS_TYPE);
		if(insId != null){
			model.addAttribute("insId", insId);
		}
//		model.addAttribute("insurance", configResult);
//		model.addAttribute("insRateList", insRateList);
		model.addAttribute("chooseid", chooseid);
		model.addAttribute("insCompanyList", insCompanyList);
		model.addAttribute("insBaoZhuangList", insBaoZhuangList);
		model.addAttribute("insGoodsTypeList", insGoodsTypeList);
		model.addAttribute("insLoadTypeList", insLoadTypeList);
		model.addAttribute("insTransTypeList", insTransTypeList);
		return "/platform/insurance/add_insurance";
	}
	
	/**
	 * 查询投保信息
	 * @param page
	 * @param searchParams
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result searchInsurance(Page<?> page,SearchParams searchParams,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			page = platformInsuranceServiceImpl.selectByCondition(user.getId(), searchParams, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 保存保单
	 * @param platformInsurance
	 * @return
	 */
	@RequestMapping("/addInsurance")
	@ResponseBody
	public Result addInsuranceInfo(HttpSession session,PlatformInsurance platformInsurance){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			platformInsurance.setUserId(user.getId());
			platformInsurance.setInsOrderNum(generalInsOrderNum());
			if(platformInsuranceServiceImpl.insertInsurance(platformInsurance)){
				result.setData(platformInsurance.getInsOrderNum());
				result.setResult(true);
			}
		}
		return result;
	}
	/**
	 * 订单号生成
	 * @return
	 */
	private String generalInsOrderNum(){
		StringBuffer sb = new StringBuffer();
		sb.append("XSL");
		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
		return sb.toString();
	}
	
	/**
	 * 
	 * 方法描述：银行回调 购买保险
	 * @author dub
	 * @version 2016年5月11日 下午8:28:27
	 */
	@RequestMapping("/getBankBackData")
	public void purchaseIns(){
		
	}
	
	@RequestMapping("/insDetails")
	public String toInsDetails(Long insId,ModelMap model){
		if(insId != null){
			PlatformInsurance platformInsurance = platformInsuranceServiceImpl.selectById(insId);
			model.addAttribute("platIns", platformInsurance);
		}
		return "/platform/personalCenter/my_insurance_details";
	}
	
	@RequestMapping("/getInsInfo")
	@ResponseBody
	public Result getInsInfo(Long insId){
		Result result = new Result();
		if(insId != null){
			result.setData(platformInsuranceServiceImpl.selectById(insId));
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 保单回掉接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/getInsBackData")
	public void getInsBackData(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		String SUCCESS = "success";
		InputStream in = null;
		try {
			in = request.getInputStream();
			String result = IOUtils.toString(in);
			// -------------
			System.out.println("【保险返回数据】");
			System.out.println(result);
			System.out.println("【保险返回数据】");
			
			// -------------
			InsuranceBackData insuranceBackData = xmlStreamToObj(result);
			if(StringUtils.isNotBlank(result)){
				platformInsuranceServiceImpl.insertInsBackData(insuranceBackData);
				response.getOutputStream().write(SUCCESS.getBytes("utf-8"));
				if(Split(insuranceBackData.getInsOrderNum(), session, request, response)){
				}
			}else{
				response.getOutputStream().write("error".getBytes("utf-8"));
				if(refund(insuranceBackData.getInsOrderNum(), session, request, response)){
				}
			}
		}catch (UnsupportedEncodingException e) {
			logger.error("不支持的编码格式", e);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean Split(String orderNumber,HttpSession session,HttpServletRequest request, HttpServletResponse response){
		//查询平台保险子账户
		sysBankAccout accout = sysBankAccoutServiceImpl.selectSysBank();
		//根据订单查询保险支付信息
		platformBankPayment bankPayment = platformBankPaymentServiceImpl.selectBankPaymentByOrderNumbe(orderNumber, Const.PLATFORM_BANK_ORDER_TYPE_0);
		String subAccount = accout.getLedgerno();
		subAccount+=":1";
		StringBuffer sb = new StringBuffer();
		sb.append("SL");
		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
		request.setAttribute("requestid",sb.toString());
		request.setAttribute("orderrequestid",bankPayment.getRequestid());
		request.setAttribute("divideinfo",subAccount);
		request.setAttribute("callbackurl","http://www.xslwl56.com/logisticsc/sys/bank/divideCallBack.shtml");
		String data = ZGTDataAttribute.buildDivideData(request);
		//第二步 发起请求
		String requestUrl = ZGTUtils.getRequestUrl(ZGTUtils.DIVIDEAPI_NAME);
		Result result= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.DIVIDEAPI_RESPONSE_HMAC_ORDER);
		Map<String, String> responseMap = (Map<String, String>) result.getData();
		//第三步 判断请求是否成功，
		if(!responseMap.get("code").equals("1")) {
			return false;
		}else{
			platformBankSplit bankSplit = new platformBankSplit();
			bankSplit.setCustomernumber("10013368774");
			bankSplit.setRequestid(sb.toString());
			bankSplit.setOrderrequestid(bankPayment.getRequestid());
			bankSplit.setDivideinfo(subAccount);
			bankSplit.setOrderNumber(orderNumber);
			bankSplit.setTime(new Date());
			bankSplit.setState(Const.PLATFORM_ORDER_BANK_SPLIT_STATE_2);
			bankSplit.setSuername("系统");
			bankSplit.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_0);
			bankSplit.setCallbackurl("http://www.xslwl56.com/logisticsc/sys/bank/divideCallBack.shtml");
			//增加用户信息
			if(platformBankSplitServiceImpl.insertBankSplit(bankSplit)){
				return true;
			}else{
				return false;
			}
		}
	}
	/**
	 * 保险退款
	 * @param orderNumber
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean refund(String orderNumber,HttpSession session,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		platformBankPayment bankPayment = null;
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		bankPayment = platformBankPayment.selectBankPaymentByOrderNumbe(orderNumber,Const.PLATFORM_BANK_ORDER_TYPE_0);
		if(null == bankPayment){
			return false;
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append("RD");
			sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
			platformBankRefund bankRefund = new platformBankRefund();
			bankRefund.setCustomernumber("10013368774");
			bankRefund.setRequestid(sb.toString());
			bankRefund.setOrderrequestid(bankPayment.getRequestid());
			bankRefund.setAmount(bankPayment.getAmount());
			bankRefund.setOrderNumber(orderNumber);
			bankRefund.setTime(new Date());
			bankRefund.setUsername(user.getLoginName());
			bankRefund.setOrderType(bankPayment.getOrderType());
			bankRefund.setRefundType(Const.PLATFORM_ORDER_BANK_REFUND_REFUND_TYPE_2);
			request.setAttribute("requestid",sb.toString());
			request.setAttribute("orderrequestid",bankRefund.getOrderrequestid());
			request.setAttribute("amount",bankRefund.getAmount());
			request.setAttribute("confirm","1");
			String data = ZGTDataAttribute.buildRefundData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REFUNDAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.REFUNDAPI_RESPONSE_HMAC_ORDER);
			//第七步 进行业务处理
			Map<String, String> responseMap = (Map<String, String>) result.getData();
			if(!responseMap.get("code").equals("1")) {
				bankRefund.setState(Const.PLATFORM_ORDER_BANK_REFUND_1);
				if(platformBankRefundServiceImpl.insertRefund(bankRefund)){
					return false;
				}
			}else{
				bankRefund.setState(Const.PLATFORM_ORDER_BANK_REFUND_0);
				if(platformBankRefundServiceImpl.insertRefund(bankRefund)){
					return true;
				}
			}
		}
		return false;
	}
	
	@RequestMapping("/getTsAndT")
	@ResponseBody
	public Result getInsTypeAndTs(String companyTag){
		Result result = new Result();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<InsuranceType> typeList = InsuranceManageService.selectTypeByCompanyTag(companyTag);
		List<InsuranceTsType> tsTypeList = InsuranceManageService.selectTsTypeByCompanyTag(companyTag);
		map.put("typeList", typeList);
		map.put("tsTypeList", tsTypeList);
		result.setData(map);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("/getInsRate")
	@ResponseBody
	public Result getInsRate(String insCompanyTag,String insTypeTag,String insTsTypeTag){
		Result result = new Result();
		if(StringUtils.isNotBlank(insCompanyTag)&&StringUtils.isNotBlank(insTypeTag)){
			result.setData(insuranceRateService.selectByComTyTsTy(insCompanyTag, insTypeTag, insTsTypeTag));
			result.setResult(true);
		}
		return result;
	}
	
	private InsuranceBackData xmlStreamToObj(String in){
		XStream stream = new XStream(new DomDriver());
		stream.alias("document", InsuranceBackData.class);
		InsuranceBackData insuranceBackData = (InsuranceBackData)stream.fromXML(in);
		return insuranceBackData;
	}
}
