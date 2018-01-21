package com.brightsoft.controller.payment.yeepay;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.common.enums.POSOrderStatusEnum;
import com.brightsoft.common.enums.POSResponseEnum;
import com.brightsoft.common.enums.ReceiveMoneyOrderStatusEnum;
import com.brightsoft.common.enums.ReceiveMoneyTypeEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.tms.PosBindMapper;
import com.brightsoft.dao.tms.PosOrderRecordMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRecordMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PosBind;
import com.brightsoft.model.PosOrderRecord;
import com.brightsoft.model.ReceiveMoneyOrder;
import com.brightsoft.model.ReceiveMoneyOrderRecord;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.platformBankAccountServiceImpl;
import com.brightsoft.service.system.YeePayPOSTService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.XmlParser;
import com.brightsoft.utils.yeepay.JaxbUtil;
import com.brightsoft.utils.yeepay.JaxbUtil.CollectionWrapper;
import com.brightsoft.utils.yeepay.POSCODMS;
import com.brightsoft.utils.yeepay.POSCODMSCancelOrderRequest;
import com.brightsoft.utils.yeepay.POSCODMSLoginRequest;
import com.brightsoft.utils.yeepay.POSCODMSOrderRequest;
import com.brightsoft.utils.yeepay.POSCODMSPayRequest;
import com.brightsoft.utils.yeepay.POSCODMSReimburseRequest;
import com.brightsoft.utils.yeepay.POSCODMSRouting;
import com.brightsoft.utils.yeepay.POSCODMSSignRequest;
import com.brightsoft.utils.yeepay.POSCancelOrderResponseBody;
import com.brightsoft.utils.yeepay.POSExtendAtt;
import com.brightsoft.utils.yeepay.POSHealer;
import com.brightsoft.utils.yeepay.POSLoginResponseBody;
import com.brightsoft.utils.yeepay.POSOrderResponseBody;
import com.brightsoft.utils.yeepay.POSPayResponseBody;
import com.brightsoft.utils.yeepay.POSReimburseResponseBody;
import com.brightsoft.utils.yeepay.POSResponseHealer;
import com.brightsoft.utils.yeepay.POSSignResponseBody;
import com.brightsoft.utils.yeepay.ZGTUtils;


@Controller
@RequestMapping("/yeePayPost")
public class YeePayPOSTController extends BaseController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private ReceiveMoneyOrderMapper receiveMoneyOrderMapper;
	
	@Autowired
	private ReceiveMoneyOrderRecordMapper receiveMoneyOrderRecordMapper;
	
	@Autowired
	private PosOrderRecordMapper posOrderRecordMapper;
	
	@Autowired
	private PosBindMapper posBindMapper;
	
	@Autowired
	private platformBankAccountServiceImpl platformBankAccountService;
	
	@Autowired
	private YeePayPOSTService yeePayPOSTService;
	
	@RequestMapping(value="/getData")
	protected void getData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		InputStream in = request.getInputStream();
		String xml = XmlParser.InputStreamToString(in);
		
		String keyForHmac = ZGTUtils.getKeyForHmac();
		
		POSCODMSRouting hotelObj = null;
		POSCODMSRouting hmacHotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSRouting.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml); 
	        String hmcrXml = putMD5(xml, keyForHmac);
	        hmacHotelObj = resultBinder.fromXml(hmcrXml); 
		}
		
		String serviceCode = hotelObj.getRequestHead().getServiceCode();
   
        String retXml = "";

        //验证对面过来的  验签, 可以测试使用
		if(!hotelObj.getRequestHead().getHmac().equals(hmacHotelObj.getRequestHead().getHmac())){
			POSCODMS poscodms = new POSCODMS();
	 		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
	 		poscodms.setResponseHead(posResponseHealer);
	 		posResponseHealer.setServiceCode(serviceCode);
	 		posResponseHealer.setResultCode(POSResponseEnum.FAIL_XML.getResult_code());
	 		posResponseHealer.setResultMsg(POSResponseEnum.FAIL_XML.getResult_msg());
	 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
	 		retXml = requestBinder.toXml(poscodms, "utf-8");
	 		PrintWriter p = response.getWriter();
	 		p.write(retXml);
	 		p.flush();
	 		p.close();
		}else{

	        if(null != serviceCode){
	        	if(serviceCode.equals(Const.POS_SERVICE_CODE_LOGIN)){
	        		retXml= this.postLogin(xml);
	            }else if(serviceCode.equals(Const.POS_SERVICE_CODE_ORDER)){
	            	retXml=this.getPostOrder(xml);
	            }else if(serviceCode.equals(Const.POS_SERVICE_CODE_PAY)){
	            	retXml=this.addPostPay(xml);
	            }else if(serviceCode.equals(Const.POS_SERVICE_CODE_SIGN)){
	            	retXml=this.getPostSign(xml);
	            }else if(serviceCode.equals(Const.POS_SERVICE_CODE_REIMBURSE)){
	            	retXml=addPostReimburse(xml);
	            }else if(serviceCode.equals(Const.POS_SERVICE_CODE_CANCEL_ORDER)){
	            	retXml=cacelOrder(xml);
	            }
	        }
	        
	        String hmcretXml = putMD5(retXml, keyForHmac);
			response.getWriter().write(hmcretXml);
		}
	}

	/**
	 * POS登录 接口
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String postLogin(String xml) throws IOException, ServletException {

		System.out.println("【调用登录接口】");
		
		System.out.println("【XML数据  = 】" + xml);
		
		POSCODMSLoginRequest hotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSLoginRequest.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml); 
		}
		
		//处理业务

		String employeeId = hotelObj.getPosLoginRequestBody().getEmployee_ID();
		String password = hotelObj.getPosLoginRequestBody().getPassword();
		
		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
		POSLoginResponseBody posResponseBody = new POSLoginResponseBody();//报文体
		//posResponseHealer.setVersion("1");

 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_LOGIN);
 		//posResponseHealer.setTransactionID("2");
 		//posResponseHealer.setDstSysID("1");
 		//posResponseHealer.setReqTime("1");
 		//posResponseHealer.setHmac("");
 		POSExtendAtt attHeader = new POSExtendAtt();
 		attHeader.setEmployee_ID(employeeId);
 		posResponseHealer.setPosExtendAtt(attHeader);
 		
 		PosBind posBind = posBindMapper.selectByLoginAccount(employeeId);
 		if(posBind == null){
 			posResponseHealer.setResultCode(POSResponseEnum.NO_USER.getResult_code());
	 		posResponseHealer.setResultMsg(POSResponseEnum.NO_USER.getResult_msg());
 		}else{
 			if(!posBind.getLoginPwd().equals(password)){
 				posResponseHealer.setResultCode(POSResponseEnum.ERROR_LOGIN_PASSWORD.getResult_code());
			 	posResponseHealer.setResultMsg(POSResponseEnum.ERROR_LOGIN_PASSWORD.getResult_msg());
 			}else{
 				User user = userMapper.selectLogisManagerUserByOutletsId(posBind.getOutletsId());
 	 			//User user = userMapper.selectByLoginName(employeeId);
 	 			if(user == null){
 	 				posResponseHealer.setResultCode(POSResponseEnum.NO_USER.getResult_code());
 	 		 		posResponseHealer.setResultMsg(POSResponseEnum.NO_USER.getResult_msg());
 	 			}else{
 	 				posResponseHealer.setResultCode(POSResponseEnum.SUCCESS.getResult_code());
 			 		posResponseHealer.setResultMsg(POSResponseEnum.SUCCESS.getResult_msg());
 			 		
 			 		PlatformUserCompany company = platformUserCompanyMapper.selectCompanyInfo(user.getCompanyId());
 			 		
 			 		posResponseBody.setEmployee_ID(employeeId);//必填
 			 		posResponseBody.setEmployee_Name(user.getTrueName());
 			 		posResponseBody.setCompany_Code(company.getCompanyCode());//必填
 			 		posResponseBody.setCompany_Name(company.getCompanyName());
 			 		//posResponseBody.setCompany_Tel("123123123123");
 			 		//posResponseBody.setCompany_Addr("天府大道");
 	 			}
 			}
 		}
 		
 		POSCODMS poscodms = new POSCODMS();
 		poscodms.setResponseHead(posResponseHealer);
 		POSExtendAtt attBody = new POSExtendAtt();
 		attBody.setPosLoginResponseBody(posResponseBody);
 		poscodms.setPosExtendAtt(attBody);
 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
 		String retXml = requestBinder.toXml(poscodms, "utf-8");
        System.out.println("【返回数据】" + retXml);
        
		return retXml;
	}


	/**
	 * POS查询 订单 接口
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String getPostOrder(String xml) throws IOException, ServletException {
		
		System.out.println("【调用订单查询接口】");
		System.out.println("【XML = 】" + xml);
		POSCODMSOrderRequest hotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSOrderRequest.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml); 
		}

		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
		POSOrderResponseBody posResponseBody = new POSOrderResponseBody();//报文体
		
		//posResponseHealer.setVersion("1");
 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_ORDER);
 		//posResponseHealer.setTransactionID("2");
 		//posResponseHealer.setDstSysID("1");
 		//posResponseHealer.setReqTime("1");
 		//posResponseHealer.setHmac("");
 		//POSExtendAtt attHeader = new POSExtendAtt();
 		//attHeader.setEmployee_ID("345345");
 		//posResponseHealer.setPosExtendAtt(attHeader);
 		posResponseHealer.setResultCode(POSResponseEnum.SUCCESS.getResult_code());
 		posResponseHealer.setResultMsg(POSResponseEnum.SUCCESS.getResult_msg());
 		
		//处理业务
		String orderNo = hotelObj.getPosOrderRequestBody().getOrderNo();
		if(StringUtils.isEmpty(orderNo)){
			posResponseBody.setOrderStatus(POSOrderStatusEnum.NO_ORDER.getOrderStatus());
		}else{
			PosBind posBind = posBindMapper.selectByPosSn(hotelObj.getPosOrderRequestBody().getPosSn());
			ReceiveMoneyOrder receiveMoneyOrder = receiveMoneyOrderMapper.selectByOrderNumberAndOutletsId(orderNo, posBind.getOutletsId());
			if(receiveMoneyOrder == null){
				posResponseBody.setOrderStatus(POSOrderStatusEnum.NO_ORDER.getOrderStatus());
			}else{
				// 获取 已收金额
				Double receivedMoney = 0D;
				List<ReceiveMoneyOrderRecord> receiveMoneyOrderRecords = receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder.getId());
				for (ReceiveMoneyOrderRecord receiveMoneyOrderRecord : receiveMoneyOrderRecords) {
					receivedMoney += receiveMoneyOrderRecord.getReceiveMoney();
				}
				// 获取POS应收金额
				Double posReceiveMoney = convertDouble2Decimal(receiveMoneyOrder.getMoney() - receivedMoney);
				if(posReceiveMoney == 0){
					posResponseBody.setOrderStatus(POSOrderStatusEnum.NO_ORDER.getOrderStatus());
				}else{
					posResponseBody.setEmployeeID(hotelObj.getPosOrderRequestBody().getEmployeeID());
					posResponseBody.setOrderNo(receiveMoneyOrder.getOrderNumber());
					posResponseBody.setReceiverOrderNo(receiveMoneyOrder.getOrderNumber());
					posResponseBody.setReceiverName(receiveMoneyOrder.getPayPerson());
					posResponseBody.setRceiverTel(receiveMoneyOrder.getPayPersonMobile());
					posResponseBody.setAmount(posReceiveMoney + "");
					// 查询 该网点 对应的子账号
					/*platformBankAccount platformBankAccount = platformBankAccountService.selectByOutlets(posBind.getOutletsId());
					if(platformBankAccount != null){
						posResponseBody.setPaDetails(platformBankAccount.getLedgerno() + ":" + receiveMoneyOrder.getPosMoney());
						posResponseBody.setPcAutoSplit("1");
					}*/
					if(receiveMoneyOrder.getStatus() == ReceiveMoneyOrderStatusEnum.FINISHED.getValue()){ //已完结
						posResponseBody.setOrderStatus(POSOrderStatusEnum.PAYED_UNDIGN.getOrderStatus());
					}else{ // 未完结
						posResponseBody.setOrderStatus(POSOrderStatusEnum.UNPAYED_UNDIGN.getOrderStatus());
				 		/*posResponseBody.setCompanyAddr("阿萨德");
				 		posResponseBody.setCompanyCode("123123123123");
				 		posResponseBody.setCompanyTel("12312123123");
				 		posResponseBody.setCustomerNo("aasdasdasd");
				 		posResponseBody.setPosSn("78968njyh");		 		
				 		posResponseBody.setReceiverName("力王");*/
					}
				}
			}
		}
 		POSExtendAtt attBody = new POSExtendAtt();
 		attBody.setPosOrderResponseBody(posResponseBody);
 		POSCODMS poscodms = new POSCODMS();
 		poscodms.setResponseHead(posResponseHealer);
 		poscodms.setPosExtendAtt(attBody);
 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);
        String retXml = requestBinder.toXml(poscodms, "utf-8");
        System.out.println("【返回数据】" + retXml);
        
		return retXml;
	}
	
	/**
	 * 四舍五入 保留两位小数
	 * @param value
	 * @return
	 */
	private double convertDouble2Decimal(Double value){
		if(value == null){
			return 0D;
		}
		return Double.parseDouble(new java.text.DecimalFormat("#.00").format(value));
	}
	
	/**
	 * POS订单 支付 回调接口
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String addPostPay(String xml) throws IOException, ServletException {
		
		System.out.println("【调用 订单支付回调接口】");
		System.out.println("【XML数据 = 】" + xml);
		
		POSCODMSPayRequest hotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSPayRequest.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml);
		}
		
		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
		POSPayResponseBody posResponseBody = new POSPayResponseBody();//报文体
		
		//posResponseHealer.setVersion("1");
 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_PAY);
 		//posResponseHealer.setTransactionID("2");
 		//posResponseHealer.setDstSysID("1");
 		//posResponseHealer.setReqTime("1");
 		//posResponseHealer.setHmac("");
 		//POSExtendAtt attHeader = new POSExtendAtt();
 		//attHeader.setEmployee_ID("345345");
 		//posResponseHealer.setPosExtendAtt(attHeader);
		if(hotelObj == null){
			posResponseHealer.setResultCode(POSResponseEnum.RECEIVE_FAIL.getResult_code());
	 		posResponseHealer.setResultMsg(POSResponseEnum.RECEIVE_FAIL.getResult_msg());
		}else{
			posResponseHealer.setResultCode(POSResponseEnum.SUCCESS.getResult_code());
	 		posResponseHealer.setResultMsg(POSResponseEnum.SUCCESS.getResult_msg());
			// 添加 POS机订单收款记录
	 		PosOrderRecord posOrderRecord = new PosOrderRecord();
			posOrderRecord.setEmployeeId(hotelObj.getPosPayRequestBody().getEmployeeID());
			posOrderRecord.setPosSn(hotelObj.getPosPayRequestBody().getPosSn());
			posOrderRecord.setOrderNumber(hotelObj.getPosPayRequestBody().getOrderNo());
			posOrderRecord.setAmount(Double.parseDouble(hotelObj.getPosPayRequestBody().getAmount()));
			posOrderRecord.setPosRequestId(hotelObj.getPosPayRequestBody().getPosRequestID());
			posOrderRecord.setReferNo(hotelObj.getPosPayRequestBody().getReferNo());
			posOrderRecord.setPayTypeCode(hotelObj.getPosPayRequestBody().getPayTypeCode());
			posOrderRecord.setPayMethod(hotelObj.getPosPayRequestBody().getPayMethod());
			posOrderRecord.setChequeNo(hotelObj.getPosPayRequestBody().getChequeNo());
			posOrderRecord.setBankCardNo(hotelObj.getPosPayRequestBody().getBankCardNo());
			posOrderRecord.setBankCardName(hotelObj.getPosPayRequestBody().getBankCardName());
			posOrderRecord.setBankCardType(hotelObj.getPosPayRequestBody().getBankCardType());
			posOrderRecord.setBankOrderNo(hotelObj.getPosPayRequestBody().getBankOrderNo());
			posOrderRecord.setYeepayOrderNo(hotelObj.getPosPayRequestBody().getYeepayOrderNo());
			posOrderRecord.setCustomerNo(hotelObj.getPosPayRequestBody().getCustomerNo());
			posOrderRecord.setPosMenue(hotelObj.getPosPayRequestBody().getPosMenue());
			posOrderRecordMapper.insertSelective(posOrderRecord);
			
			ReceiveMoneyOrder receiveMoneyOrder = receiveMoneyOrderMapper.selectByOrderNumber(hotelObj.getPosPayRequestBody().getOrderNo());
			/*User user = userMapper.selectByLoginName(hotelObj.getPosPayRequestBody().getEmployeeID());
			String userName = user.getTrueName();
			if(StringUtils.isEmpty(userName)){
				userName = user.getLoginName();
			}*/
			if(receiveMoneyOrder != null){
				// 收款订单应收费用
				double money = receiveMoneyOrder.getMoney();
				// 收款订单已收费用
				double cashMoneyTotal = 0; // 现金收款总额
				double posMoneyTotal = 0; //POS机收款总额
				List<ReceiveMoneyOrderRecord> receiveMoneyOrderRecords = receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder.getId());
				if(receiveMoneyOrderRecords != null && receiveMoneyOrderRecords.size() > 0){
					for (ReceiveMoneyOrderRecord receiveMoneyOrderRecordTemp : receiveMoneyOrderRecords) {
						if(receiveMoneyOrderRecordTemp.getReceiveMoneyType() == ReceiveMoneyTypeEnum.CASH_RECEIVE_MONEY.getValue()){
							cashMoneyTotal += receiveMoneyOrderRecordTemp.getReceiveMoney();
						}
					}
				}
				List<PosOrderRecord> posOrderRecords = posOrderRecordMapper.selectByOrderNumber(receiveMoneyOrder.getOrderNumber());
				for (PosOrderRecord posOrderRecordTemp : posOrderRecords) {
					posMoneyTotal += posOrderRecordTemp.getAmount();
				}
				// 修改收款订单状态  是否完结
				// 如果 应收费用 = 已收现金费用 + POS机已收费用 //修改收款订单状态为已完结
				if(money == (cashMoneyTotal + posMoneyTotal)){
					ReceiveMoneyOrder receiveMoneyOrderTemp = new ReceiveMoneyOrder();
					receiveMoneyOrderTemp.setId(receiveMoneyOrder.getId());
					receiveMoneyOrderTemp.setStatus(ReceiveMoneyOrderStatusEnum.FINISHED.getValue());
					receiveMoneyOrderMapper.updateByPrimaryKeySelective(receiveMoneyOrderTemp);
				}
				
				// 添加 收款订单POS机收款记录
				ReceiveMoneyOrderRecord receiveMoneyOrderRecord = new ReceiveMoneyOrderRecord();
				receiveMoneyOrderRecord.setReceiveMoneyOrderId(receiveMoneyOrder.getId());
				receiveMoneyOrderRecord.setReceiveMoney(Double.parseDouble(hotelObj.getPosPayRequestBody().getAmount()));
				receiveMoneyOrderRecord.setReceiveMoneyType(ReceiveMoneyTypeEnum.POS_RECEIVE_MONEY.getValue());
				receiveMoneyOrderRecord.setOperatePerson(hotelObj.getPosPayRequestBody().getEmployeeID());
				receiveMoneyOrderRecord.setOperateTime(new Date());
				receiveMoneyOrderRecordMapper.insertSelective(receiveMoneyOrderRecord);
			}
			posResponseBody.setOrderNo(hotelObj.getPosPayRequestBody().getOrderNo());
	 		posResponseBody.setReferNo(hotelObj.getPosPayRequestBody().getReferNo());//必填 交易参考号
		}
		
		POSCODMS poscodms = new POSCODMS();
 		poscodms.setResponseHead(posResponseHealer);
 		poscodms.setPosPayResponseBody(posResponseBody);
 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
        String retXml = requestBinder.toXml(poscodms, "utf-8");  
        System.out.println("【返回数据】" + retXml);   
		return retXml;
	}
	
	
	/**
	 * POS签收 交易接口
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String getPostSign(String xml) throws IOException, ServletException {
		POSCODMSSignRequest hotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSSignRequest.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml); 
		} 
		
		POSCODMS poscodms = new POSCODMS();
 		
 		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
 		//posResponseHealer.setVersion("1");
 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_SIGN);
 		//posResponseHealer.setTransactionID("2");
 		//posResponseHealer.setDstSysID("1");
 		//posResponseHealer.setReqTime("1");
 		//posResponseHealer.setHmac("");
 		//POSExtendAtt attHeader = new POSExtendAtt();
 		//attHeader.setEmployee_ID("345345");
 		//posResponseHealer.setPosExtendAtt(attHeader);
 		posResponseHealer.setResultCode(POSResponseEnum.RECEIVE_FAIL.getResult_code());
 		posResponseHealer.setResultMsg(POSResponseEnum.RECEIVE_FAIL.getResult_msg());
 		
 		poscodms.setResponseHead(posResponseHealer);

 		POSSignResponseBody posResponseBody = new POSSignResponseBody();
 		poscodms.setPosSignResponseBody(posResponseBody);
 		//posResponseBody.setEmployee_ID("1");
 		posResponseBody.setOrderNo("asdasdasade@SFSAD");
 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
        String retXml = requestBinder.toXml(poscodms, "utf-8");  
        System.out.println(retXml);  
		return retXml;
	}
	
	/**
	 * 撤销交易接口
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String cacelOrder(String xml) throws IOException, ServletException {
		System.out.println("【调用 撤销交易】");
		System.out.println("【XML数据 = 】" + xml);
		
		POSCODMSCancelOrderRequest hotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSCancelOrderRequest.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml);
		}
		
		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
		posResponseHealer.setTransactionID(UUID.randomUUID().toString());
		posResponseHealer.setResultCode(POSResponseEnum.SUCCESS.getResult_code());
 		posResponseHealer.setResultMsg(POSResponseEnum.SUCCESS.getResult_msg());
		
		POSCancelOrderResponseBody posResponseBody = new POSCancelOrderResponseBody();//报文体
		posResponseBody.setReferNo(hotelObj.getPosCancelOrderRequestBody().getReferNo());
		posResponseBody.setOrderNo(hotelObj.getPosCancelOrderRequestBody().getPosSn());
		
		try {
			yeePayPOSTService.saveCancelOrder(hotelObj);
		} catch (Exception e) {
			System.out.println("【POS订单撤销，业务处理失败】");
			e.printStackTrace();
		}
		
		POSCODMS poscodms = new POSCODMS();
 		poscodms.setResponseHead(posResponseHealer);
 		poscodms.setPosCancelOrderResponseBody(posResponseBody);
 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
        String retXml = requestBinder.toXml(poscodms, "utf-8");  
        System.out.println("【返回数据】" + retXml);   
		return retXml;
	}
	
	/**
	 * POS订单 退款 回调接口
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String addPostReimburse(String xml) throws IOException, ServletException {
		
		System.out.println("【调用 订单退款回调接口】");
		System.out.println("【XML数据 = 】" + xml);
		
		POSCODMSReimburseRequest hotelObj = null;
		if(xml!=null && !xml.equals("")){
			JaxbUtil resultBinder = new JaxbUtil(POSCODMSReimburseRequest.class,CollectionWrapper.class);  
	        hotelObj = resultBinder.fromXml(xml);
		}
		
		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
		posResponseHealer.setTransactionID("@#FER^%%&&&*33245");
		posResponseHealer.setResultCode(POSResponseEnum.SUCCESS.getResult_code());
 		posResponseHealer.setResultMsg(POSResponseEnum.SUCCESS.getResult_msg());
		
		POSReimburseResponseBody posResponseBody = new POSReimburseResponseBody();//报文体
		posResponseBody.setReferNo("1548784");
		//处理业务
 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_REIMBURSE);

		
		
		POSCODMS poscodms = new POSCODMS();
 		poscodms.setResponseHead(posResponseHealer);
 		poscodms.setPosReimburseResponseBody(posResponseBody);
 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
        String retXml = requestBinder.toXml(poscodms, "utf-8");  
        System.out.println("【返回数据】" + retXml);   
		return retXml;
	}
	
	
	/**
	* @param srcXml 原xml
	* @param secKey 加密key
	* @return 生成新的带hamc 节点的xml
	*/
	public String putMD5(String srcXml, String secKey) {
		// xml 文档开始与结束部分，此部分不进行加密处理，在拼接时使用
		String xmlNodeStartString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><COD-MS>";
		String xmlNodeEndString = "</COD-MS>";
		// 需要加密的字符串
		String md5String = "";
		// 本次传送xml 的md5
		String md5token = "";
		md5String = srcXml.substring(srcXml.indexOf("<COD-MS>") + 8,
		srcXml.indexOf("</COD-MS>"));
		// 把hmac 节点过滤点
		String hmac = srcXml.substring(srcXml.indexOf("<HMAC>"),
		srcXml.indexOf("</HMAC>") + 7);
		System.out.println("old hmac=" + hmac);
		md5String = md5String.replace(hmac, "");
		md5String = filter(md5String);
		// 生成md5
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);
		md5token = md5.encodePassword(md5String + secKey, null);
		System.out.println("发送时计算的MD5：" + md5String + secKey);
		System.out.println("new hmac=" + md5token);
		// hmac 节点前部分
		String startStr = md5String.split("</SessionHead>")[0];
		// hmac 节点后部分
		String endStr="";
		if (md5String.split("</SessionHead>").length > 1) {
		// hmac 节点后部分
		endStr = md5String.split("</SessionHead>")[1];
		}
		return xmlNodeStartString + startStr + "<HMAC>" + md5token + "</HMAC>" +
		"</SessionHead>" + endStr + xmlNodeEndString;
	}
	
	/**
	* 过滤制表符
	*
	* @param content
	* @return String
	*/
	public String filter(String content) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(content);
		return m.replaceAll("");
	}
	
}
