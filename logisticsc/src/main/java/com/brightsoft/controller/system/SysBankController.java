package com.brightsoft.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.PlatfoemBankSplitConfigure;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.platformBankAccounts;
import com.brightsoft.model.platformBankConfigure;
import com.brightsoft.model.platformBankPayment;
import com.brightsoft.model.platformBankRefund;
import com.brightsoft.model.platformBankSplit;
import com.brightsoft.model.platformBondConfigure;
import com.brightsoft.model.platformVoSplitInsurance;
import com.brightsoft.model.platformVoSplitOrder;
import com.brightsoft.model.platformVoSplitPos;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.platform.PlatfoemBankSplitConfigureServiceImpl;
import com.brightsoft.service.platform.PlatformBankConfigureServiceImpl;
import com.brightsoft.service.platform.PlatformOrderBankServiceImpl;
import com.brightsoft.service.platform.PlatformOrderRefundServiceImpl;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.platform.platformBankAccountsServiceImpl;
import com.brightsoft.service.platform.platformBankPaymentServiceImpl;
import com.brightsoft.service.platform.platformBankRefundServiceImpl;
import com.brightsoft.service.platform.platformBankSplitServiceImpl;
import com.brightsoft.service.platform.platformBondConfigureServiceImpl;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.yeepay.ZGTData;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;


@Controller
@RequestMapping("/sys/bank")
public class SysBankController extends BaseController{
	
	@Autowired
	private PlatformBankConfigureServiceImpl platformBankConfigureServiceImpl;
	
	@Autowired
	private PlatformOrderBankServiceImpl platformOrderBankServiceImpl;
	
	@Autowired
	private platformBankRefundServiceImpl platformBankRefundServiceImpl;
	
	@Autowired 
	private platformBankSplitServiceImpl platformBankSplitServiceImpl;
	
	@Autowired
	private platformBondConfigureServiceImpl platformBondConfigureServiceImpl;
	
	@Autowired
	private platformBankPaymentServiceImpl platformBankPaymentServiceImpl;
	
	@Autowired
	private platformBankAccountsServiceImpl platformBankAccountsServiceImpl;
	
	@Autowired
	private PlatfoemBankSplitConfigureServiceImpl PlatfoemBankSplitConfigureServiceImpl;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	private static final String SLIPT_PHONE="18190944891";  //分账和转帐设置唯一发送验证码短信手机号
	
	/**
	 * 发送验证并保存验证信息
	 * @param loginName
	 * @param session
	 * @return
	 */
	@RequestMapping("/verificationMessage")
	@ResponseBody
	public Result verificationMessage(HttpSession session){
		Result ret = new Result();
		String code = platformUserService.verificationMessage(SLIPT_PHONE);
		System.out.println(code);
		session.setAttribute(SLIPT_PHONE, code);
		ret.setResult(true);
		return ret;
	}
	@RequestMapping("/eliminateMessage")
	@ResponseBody
	public Result eliminateMessage(HttpSession session){
		Result ret = new Result();
		session.removeAttribute(SLIPT_PHONE);
		ret.setResult(true);
		return ret;
	}
	/**
	 * pos机转账
	 * @return
	 */
	@RequestMapping("/toSysAccounts")
	@RepeatSubmission(needSaveToken = true)
	public String toSysAccounts(){
		return "/system/sys_bank_pos_accounts";
	}
	/**
	 * POS机转账记录
	 * @return
	 */
	@RequestMapping("/toSysPosPosRecord")
	public String toSysPosPosRecord(){
		return "/system/sys_bank_pos_record";
	}
	/**
	 * 保险分账记录
	 * @return
	 */
	@RequestMapping("/toSysPosRecord")
	public String toSysPosRecord(){
		return "/system/sys_bank_pos_appoint";
	}
	/**
	 * 保险分账记录
	 * @return
	 */
	@RequestMapping("/toSysInsuranceRecord")
	public String toSysInsuranceRecord(){
		return "/system/sys_bank_Insurance_record";
	}
	
	/**
	 * 退款记录
	 * @return
	 */
	@RequestMapping("/toSysRefundRecord")
	public String toSysRefundRecord(){
		return "/system/sys_bank_make_refund";
	}
	/**
	 * 支付记录
	 * @return
	 */
	@RequestMapping("/toSysPaymentRecord")
	public String toSysPaymentRecord(){
		return "/system/sys_bank_make_payment";
	}
	/**
	 * 预约分账记录
	 * @return
	 */
	@RequestMapping("/toSysMakeRecord")
	public String toSys(){
		return "/system/sys_bank_make_record";
	}
	/**
	 * 运费分账记录
	 * @return
	 */
	@RequestMapping("/toSysFreightRecord")
	public String toSysFreightRecord(){
		return "/system/sys_bank_freight_record";
	}
	/**
	 * 分账配合
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getBankSplitConfigure")
	public ModelAndView getBankSplitConfigure(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		PlatfoemBankSplitConfigure bankSplitConfigure = new PlatfoemBankSplitConfigure();
		bankSplitConfigure.setType(Const.PLATFORM_BANK_SPLIT_TYPE_0);
		PlatfoemBankSplitConfigure configure =PlatfoemBankSplitConfigureServiceImpl.selectPlatfoemBankSplitConfigure(bankSplitConfigure);
		mv.addObject("configure", configure);
		mv.setViewName("/system/sys_bank_split_configure");
		return mv;
	}
	@RequestMapping("/getBankPosConfigure")
	public ModelAndView getBankPosConfigure(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		PlatfoemBankSplitConfigure bankSplitConfigure = new PlatfoemBankSplitConfigure();
		bankSplitConfigure.setType(Const.PLATFORM_BANK_SPLIT_TYPE_1);
		PlatfoemBankSplitConfigure configure =PlatfoemBankSplitConfigureServiceImpl.selectPlatfoemBankSplitConfigure(bankSplitConfigure);
		mv.addObject("configure", configure);
		mv.setViewName("/system/sys_bank_pos_configure");
		return mv;
	}
	@RequestMapping("/getBondConfigure")
	public ModelAndView getBondConfigure(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		platformBondConfigure bondConfigure = platformBondConfigureServiceImpl.selectBond();
		mv.addObject("bondConfigure", bondConfigure);
		mv.setViewName("/system/sys_bond_configure");
		return mv;
	}
	@RequestMapping("/updateBondConfigure")
	@ResponseBody
	public Result updateBondConfigure(platformBondConfigure configure,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		configure.setUsername(user.getUsername());
		if(platformBondConfigureServiceImpl.updateBond(configure)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 修改分账配置
	 * @param bankSplitConfigure
	 * @return
	 */
	@RequestMapping("/updateBankSplitConfigure")
	@ResponseBody
	public Result updateBankSplitConfigure(PlatfoemBankSplitConfigure bankSplitConfigure,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		bankSplitConfigure.setUsername(user.getUsername());
		if(PlatfoemBankSplitConfigureServiceImpl.updateSplitConfigur(bankSplitConfigure)){
			result.setResult(true);
		}else{
			result.setResult(false);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：获取银行配置
	 * @param request
	 * @param response
	 * @return
	 * @author dub
	 * @version 2016年5月11日 下午7:15:38
	 */
	@RequestMapping("/getBankConfigure")
	public ModelAndView register(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		platformBankConfigure bankConfigureInfo = platformBankConfigureServiceImpl.selectBankConfigure();
		mv.addObject("bankConfigure", bankConfigureInfo);
		mv.setViewName("/system/sys_bank_configure");
			return mv;
	}
	
	/**
	 * 
	 * 方法描述：更新银行配置信息
	 * @param platformBankConfigure
	 * @return
	 * @author dub
	 * @version 2016年5月11日 下午7:16:12
	 */
	@RequestMapping("/updateBankConfig")
	@ResponseBody
	public Result updateBankConfig(platformBankConfigure platformBankConfigure){
		Result result = new Result();
		if(platformBankConfigureServiceImpl.updateBankConfig(platformBankConfigure)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到预费分账页面
	 * @return
	 * @author dub
	 * @version 2016年5月11日 下午7:10:23
	 */
	@RequestMapping("/toSysRoutingApp")
	public String toSysRoutingApp(){
		return "/system/sys_bank_routing_appoint";
	}
	
	/**
	 * 
	 * 方法描述：跳转到运费分账页面
	 * @return
	 * @author dub
	 * @version 2016年5月11日 下午7:10:23
	 */
	@RequestMapping("/toSysRoutingFreight")
	public String toSysRoutingFreight(){
		return "/system/sys_bank_routing_freight";
	}
	
//	/**
//	 * 异常预约退款
//	 * @return
//	 */
//	@RequestMapping("/toSysAbnormalAppoint")
//	public String toSysAbnormalAppoint(){
//		return "/system/sys_bank_abnormal_routing_appoint";
//	}
	
	@RequestMapping("/getBankPos")
	@ResponseBody
	public Result getBankPos(platformVoSplitPos splitPos,Page<?> page){
		splitPos.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_4);
		Result result = platformBankSplitServiceImpl.selectBankPos(splitPos, page);
		result.setResult(true);
		return result;
	}
	/**
	 * pos机转账
	 * @param splitPos
	 * @param page
	 * @return
	 */
	@RequestMapping("/getBankPosAccounts")
	@ResponseBody
	public Result getBankPosAccounts(platformVoSplitPos splitPos,Page<?> page){
		splitPos.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_4);
		Result result = platformBankAccountsServiceImpl.selectBankPos(splitPos, page);
		result.setResult(true);
		return result;
	}
	/**
	 * pos机转账记录
	 * @param splitPos
	 * @param page
	 * @return
	 */
	@RequestMapping("/getBankPosRecord")
	@ResponseBody
	public Result getBankPosRecord(platformVoSplitPos splitPos,Page<?> page){
		splitPos.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_4);
		Result result = platformBankAccountsServiceImpl.selectBankPosRecord(splitPos, page);
		result.setResult(true);
		return result;
	}
	/**
	 * 
	 * 方法描述：查询预付分账记录
	 * @param searchParams
	 * @param page
	 * @return
	 * @author dub
	 * @version 2016年5月13日 下午5:15:13
	 */
	@RequestMapping("/searchSubAccount4App")
	@ResponseBody
	public Result searchSubAccount4App(platformVoSplitOrder platformVoSplitOrder,Page<?> page){
		platformVoSplitOrder.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_1);
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatusEnum.TAkING_CARGO.getValue());
		statusList.add(OrderStatusEnum.CONFIRM_CARGO_INFO.getValue());
		statusList.add(OrderStatusEnum.CARGO_STORED.getValue());
		statusList.add(OrderStatusEnum.TRANSPORTING.getValue());
		statusList.add(OrderStatusEnum.ARRIVED.getValue());
		statusList.add(OrderStatusEnum.DELIVERING.getValue());
		statusList.add(OrderStatusEnum.RECEIVED.getValue());
		statusList.add(OrderStatusEnum.CANCEL.getValue());
		platformVoSplitOrder.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_1);
		Result result = platformBankSplitServiceImpl.selectSplitOrderState(platformVoSplitOrder, page,statusList);
		result.setResult(true);
		return result;
	}
	/**
	 * 运费分账
	 * @param platformVoSplitOrder
	 * @param page
	 * @return
	 */
	@RequestMapping("/searchSubAccount5App")
	@ResponseBody
	public Result searchSubAccount5App(platformVoSplitOrder platformVoSplitOrder,Page<?> page){
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatusEnum.CARGO_STORED.getValue());
		statusList.add(OrderStatusEnum.TRANSPORTING.getValue());
		statusList.add(OrderStatusEnum.ARRIVED.getValue());
		statusList.add(OrderStatusEnum.DELIVERING.getValue());
		statusList.add(OrderStatusEnum.RECEIVED.getValue());
		platformVoSplitOrder.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_2);
		Result result = platformBankSplitServiceImpl.selectSplitOrderState(platformVoSplitOrder, page,statusList);
		result.setResult(true);
		return result;
	}
	/**
	 * 预约分账记录
	 * @return
	 */
	@RequestMapping("/getMakeRecordSplit")
	@ResponseBody
	public Result getMakeRecordSplit(platformVoSplitOrder platformVoSplitOrder,Page<?> page){
		platformVoSplitOrder.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_1);
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatusEnum.TAkING_CARGO.getValue());
		statusList.add(OrderStatusEnum.CONFIRM_CARGO_INFO.getValue());
		statusList.add(OrderStatusEnum.CARGO_STORED.getValue());
		statusList.add(OrderStatusEnum.TRANSPORTING.getValue());
		statusList.add(OrderStatusEnum.ARRIVED.getValue());
		statusList.add(OrderStatusEnum.DELIVERING.getValue());
		statusList.add(OrderStatusEnum.RECEIVED.getValue());
		statusList.add(OrderStatusEnum.CANCEL.getValue());
		platformVoSplitOrder.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_1);
		Result result = platformBankSplitServiceImpl.selectBankSplitList(platformVoSplitOrder, page,statusList);
		result.setResult(true);
		return result;
	}
	/**
	 * 运费分账记录
	 * @return
	 */
	@RequestMapping("/getFreightRecordSplit")
	@ResponseBody
	public Result getFreightRecordSplit(platformVoSplitOrder platformVoSplitOrder,Page<?> page){
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderStatusEnum.CARGO_STORED.getValue());
		statusList.add(OrderStatusEnum.TRANSPORTING.getValue());
		statusList.add(OrderStatusEnum.ARRIVED.getValue());
		statusList.add(OrderStatusEnum.DELIVERING.getValue());
		statusList.add(OrderStatusEnum.RECEIVED.getValue());
		platformVoSplitOrder.setPaymentOrderType(Const.PLATFORM_BANK_ORDER_TYPE_2);
		Result result = platformBankSplitServiceImpl.selectBankSplitList(platformVoSplitOrder, page,statusList);
		result.setResult(true);
		return result;
	}
	/**
	 * 保险分账
	 * @param insurance
	 * @param page
	 * @return
	 */
	@RequestMapping("/getSplitInsurance")
	@ResponseBody
	public Result getSplitInsurance(platformVoSplitInsurance insurance,Page<?> page){
		Result result = platformBankSplitServiceImpl.selectBankInsurance(insurance, page);
		return result;
	}
	/**
	 * 订单支付记录
	 * @param bankPayment
	 * @param page
	 * @return
	 */
	@RequestMapping("/getSelectPaymentList")
	@ResponseBody
	public Result getSelectPaymentList(platformBankPayment bankPayment, Page<?> page){
		Result result =platformBankPaymentServiceImpl.selectPaymentList(bankPayment, page);
		result.setResult(true);
		return result;
	}
	/**
	 * 退款记录
	 * @param bankRefund
	 * @param page
	 * @return
	 */
	@RequestMapping("/getSelectRefund")
	@ResponseBody
	public Result getSelectRefund(platformBankRefund bankRefund,Page<?> page){
		Result result =platformBankRefundServiceImpl.selectPaymentList(bankRefund, page);
		result.setResult(true);
		return result;
	}
	/**
	 * 
	 * 方法描述：分账
	 * @param orderNumber
	 * @return
	 * @author dub
	 * @version 2016年5月13日 下午6:09:04
	 */
	@RequestMapping("/orderBillRouting")
	@ResponseBody
	public Result orderBillRouting(double paymentAmount,String code,Long tmsUserId,String requestid,String subAccount,String orderNumber,Integer orderType,HttpSession session,HttpServletRequest request, HttpServletResponse response)throws NullPointerException{
		Result srt = new Result();
		try{
			if(null == session.getAttribute(SLIPT_PHONE).toString()){
					srt.setData("请输入正确的验证码！");
					srt.setResult(false);
					return srt;
				}
			}catch(NullPointerException e)
			{
				srt.setData("请输入正确的验证码！");
				srt.setResult(false);
				return srt;
			}
		if(session.getAttribute(SLIPT_PHONE).toString().equals(code)){
			session.removeAttribute(SLIPT_PHONE);
			PlatfoemBankSplitConfigure bankSplitConfigure = new PlatfoemBankSplitConfigure();
			bankSplitConfigure.setType(Const.PLATFORM_BANK_SPLIT_TYPE_0);
			PlatfoemBankSplitConfigure configure =PlatfoemBankSplitConfigureServiceImpl.selectPlatfoemBankSplitConfigure(bankSplitConfigure);
			if(null == configure){
				srt.setData("请在配置管理，配置运费分账比例！（如无运费分账比例，无法进行分账操作）");
				srt.setResult(false);
				return srt;
			}else{
				double amount= PlatfoemBankSplitConfigureServiceImpl.splitCounterFee(configure, paymentAmount);
				SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
				subAccount+=":AMOUNT"+amount;
				StringBuffer sb = new StringBuffer();
				sb.append("SL");
				sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
				request.setAttribute("requestid",sb.toString());
				request.setAttribute("orderrequestid",requestid);
				request.setAttribute("divideinfo",subAccount);
				request.setAttribute("callbackurl","http://www.xslwl56.com/logisticsc/sys/bank/divideCallBack.shtml");
			
				String data = ZGTDataAttribute.buildDivideData(request);
				//第二步 发起请求
				String requestUrl = ZGTUtils.getRequestUrl(ZGTUtils.DIVIDEAPI_NAME);
				Result result= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.DIVIDEAPI_RESPONSE_HMAC_ORDER);
				Map<String, String> responseMap = (Map<String, String>) result.getData();
				//第三步 判断请求是否成功，
				if(!responseMap.get("code").equals("1")) {
					srt.setData(responseMap.get("msg"));
					srt.setResult(false);
					return srt ;
				}else{
					platformBankSplit bankSplit = new platformBankSplit();
					bankSplit.setTmsUserId(tmsUserId);
					bankSplit.setCustomernumber("10013368774");
					bankSplit.setRequestid(sb.toString());
					bankSplit.setOrderrequestid(requestid);
					bankSplit.setDivideinfo(subAccount);
					bankSplit.setOrderNumber(orderNumber);
					bankSplit.setTime(new Date());
					bankSplit.setState(Const.PLATFORM_ORDER_BANK_SPLIT_STATE_2);
					bankSplit.setSuername(user.getUsername());
					bankSplit.setOrderType(orderType);
					bankSplit.setAmount(String.valueOf(amount));
					bankSplit.setCallbackurl("http://www.xslwl56.com/logisticsc/sys/bank/divideCallBack.shtml");
					//增加用户信息
					if(platformBankSplitServiceImpl.insertBankSplit(bankSplit)){
						srt.setResult(true);
						srt.setData("分账已成功");
					}else{
						srt.setData("分账失败");
						srt.setResult(false);
					}
					return srt ;
				}
			}
		}else{
			srt.setData("请输入正确的验证码！");
			srt.setResult(false);
		}
		return srt ;
	}
	/**
	 * 分账回调
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/divideCallBack")
	protected void divideCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out	= response.getWriter();
		
		//第一步 获取回调密文data
		String data	= request.getParameter("data");
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.DIVIDEAPICALLBACK_HMAC_ORDER)) {
			return;
		}
		String code =dataMap.get("code");
		String requestid = dataMap.get("requestid");
		//修改分账信息表状态
		if(code.equals("1")){
			//根据订单请求号查询
			platformBankSplit bankSplit = platformBankSplitServiceImpl.selectBankSplit(requestid);
			bankSplit.setState(Const.PLATFORM_ORDER_BANK_SPLIT_STATE_0);
			if(platformBankSplitServiceImpl.updateBankSplit(bankSplit)){
			}
			//修改订单状态
		}else{
			platformBankSplit bankSplit = platformBankSplitServiceImpl.selectBankSplit(requestid);
			bankSplit.setState(Const.PLATFORM_ORDER_BANK_SPLIT_STATE_1);
			if(platformBankSplitServiceImpl.updateBankSplit(bankSplit)){
			}
		}
		//第四步 回写SUCCESS 
		out.println("SUCCESS");
		out.flush();
		out.close();
	}
	/**
	 * pos转账
	 * @param accounts
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/posAccounts")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public Result posAccounts(String code,platformBankAccounts accounts,HttpServletRequest request,HttpSession session, HttpServletResponse response)throws ServletException, IOException {
		Result srt = new Result();
		try{
			if(null == session.getAttribute(SLIPT_PHONE).toString()){
					srt.setData("请输入正确的验证码！");
					srt.setResult(false);
					return srt;
				}
			}catch(NullPointerException e)
			{
				srt.setData("请输入正确的验证码！");
				srt.setResult(false);
				return srt;
			}
		if(session.getAttribute(SLIPT_PHONE).toString().equals(code)){
			session.removeAttribute(SLIPT_PHONE);
			PlatfoemBankSplitConfigure bankSplitConfigure = new PlatfoemBankSplitConfigure();
			bankSplitConfigure.setType(Const.PLATFORM_BANK_SPLIT_TYPE_1);
			PlatfoemBankSplitConfigure configure =PlatfoemBankSplitConfigureServiceImpl.selectPlatfoemBankSplitConfigure(bankSplitConfigure);
			if(null == configure){
				srt.setData("请在配置管理，配置运费分账比例！（如无POS机转账比例，无法进行转账操作）");
				srt.setResult(false);
				return srt;
			}else{
				double amounts=Double.valueOf(accounts.getAmount()).doubleValue();
				double amount= PlatfoemBankSplitConfigureServiceImpl.splitCounterFee(configure,amounts);
				SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
				StringBuffer sb = new StringBuffer();
				sb.append("POS");
				sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
				accounts.setCustomernumber("10013368774");
				accounts.setRequestid(sb.toString());
				accounts.setTime(new Date());
				accounts.setState(Const.PLATFORM_POS_BANK_STATE_2);
				accounts.setSuername(user.getUsername());
				accounts.setOrderType(Const.PLATFORM_POS_BANK_ORDERTYPE_0);
				accounts.setAmount(String.valueOf(amount));
				request.setAttribute("requestid",sb.toString());
				request.setAttribute("ledgerno",accounts.getLedgerno());
				request.setAttribute("amount",amount);
				String data = ZGTDataAttribute.buildTransferData(request);
				//第二步 发起请求
				String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.TRANSFERAPI_NAME);
				Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.TRANSFERAPI_RESPONSE_HMAC_ORDER);
				Map<String, String> responseMap = (Map<String, String>) result.getData();
				if(!responseMap.get("code").equals("1")) {
					srt.setMsg("分账失败");
					srt.setResult(false);
					return srt ;
				}else{
					accounts.setState(Const.PLATFORM_POS_BANK_STATE_0);
					if(platformBankAccountsServiceImpl.insertBankPos(accounts)){
						srt.setMsg("分账成功");
						srt.setResult(true);
					}else{
						srt.setMsg("分账失败");
						srt.setResult(false);
					}
				}
				return srt ;
			}
		}else{
			srt.setData("请输入正确的验证码！");
			srt.setResult(false);
			return srt;
		}
	}
//	/**
//	 * 异常退款
//	 * @param requestid
//	 * @param money
//	 * @return
//	 */
//	@RequestMapping("/subAccountDrawback")
//	@ResponseBody
//	public Result subAccountDrawback(String orderNumber,String requestid,String money,HttpSession session,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		Result srt = new Result();
//		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
//		StringBuffer sb = new StringBuffer();
//		sb.append("RD");
//		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
//		
//		platformBankRefund bankRefund = new platformBankRefund();
//		bankRefund.setCustomernumber("10013368774");
//		bankRefund.setRequestid(sb.toString());
//		bankRefund.setOrderrequestid(requestid);
//		bankRefund.setAmount(money);
//		bankRefund.setOrderNumber(orderNumber);
//		bankRefund.setTime(new Date());
//		bankRefund.setState(Const.PLATFORM_ORDER_BANK_REFUND_0);
//		bankRefund.setUsername(user.getUsername());
//		
//		request.setAttribute("requestid",sb.toString());
//		request.setAttribute("orderrequestid",bankRefund.getOrderrequestid());
//		request.setAttribute("amount",bankRefund.getAmount());
//		request.setAttribute("confirm","1");
//		String data = ZGTDataAttribute.buildRefundData(request);
//		//第二步 发起请求
//		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REFUNDAPI_NAME);
//		Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.REFUNDAPI_RESPONSE_HMAC_ORDER);
//		//第七步 进行业务处理
//		Map<String, String> responseMap = (Map<String, String>) result.getData();
//		if(!responseMap.get("code").equals("1")) {
//			srt.setMsg("退款失败");
//			srt.setResult(false);
//			return srt ;
//		}else{
//			if(platformBankRefundServiceImpl.insertRefund(bankRefund)){
//				//orderNumber
//				srt.setMsg("退款成功");
//				srt.setResult(true);
//			}else{
//				srt.setMsg("退款失败");
//				srt.setResult(false);
//			}
//		}
//		return srt;
//	}
}
