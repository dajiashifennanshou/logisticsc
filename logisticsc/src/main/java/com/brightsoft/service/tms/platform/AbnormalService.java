package com.brightsoft.service.tms.platform;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.AbnormalNodeEnum;
import com.brightsoft.common.enums.AbnormalTypeEnum;
import com.brightsoft.common.enums.ExceptionStatusEnum;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.AbnormalMapper;
import com.brightsoft.dao.tms.LadingOrderMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.model.Abnormal;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

@Service
public class AbnormalService {
	
	private static final Logger logger = Logger.getLogger(AbnormalService.class);
	
	@Autowired
	private AbnormalMapper abnormalMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private LadingOrderMapper ladingOrderMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	public Abnormal selectByPrimaryKey(Long id){
		Abnormal abnormal = abnormalMapper.selectByPrimaryKey(id);
		// 设置异常环节名称
		AbnormalNodeEnum[] abnormalNodeEnums = AbnormalNodeEnum.values();
		for (AbnormalNodeEnum abnormalNodeEnum : abnormalNodeEnums) {
			if(Integer.parseInt(abnormal.getAbnormalNode()) == abnormalNodeEnum.getValue()){
				abnormal.setAbnormalNodeName(abnormalNodeEnum.getName());
				continue;
			}
		}
		// 设置异常类型名称
		AbnormalTypeEnum[] abnormalTypeEnums = AbnormalTypeEnum.values();
		for (AbnormalTypeEnum abnormalTypeEnum : abnormalTypeEnums) {
			if(Integer.parseInt(abnormal.getAbnormalType()) == abnormalTypeEnum.getValue()){
				abnormal.setAbnormalTypeName(abnormalTypeEnum.getName());
				continue;
			}
		}
		// 设置责任站点名称
		abnormal.setDutySiteName(outletsInfoMapper.selectById(Long.parseLong(abnormal.getDutySite())).getName());
		return abnormal;
	}
	
	/**
	 * 查询异常信息
	 * @param page
	 * @param user
	 * @param abnormal
	 * @return
	 */
	public Result selectByCondition(Page<?> page,User user,SearchParams searchParams){
		Result result = new Result();
		int results = abnormalMapper.countAbnormal(user, searchParams);
		List<Abnormal> list = abnormalMapper.selectByCondition(page, user, searchParams);
		for (Abnormal abnormal : list) {
			// 设置异常类型名称
			AbnormalTypeEnum[] abnormalTypeEnums = AbnormalTypeEnum.values();
			for (AbnormalTypeEnum abnormalTypeEnum : abnormalTypeEnums) {
				if(Integer.parseInt(abnormal.getAbnormalType()) == abnormalTypeEnum.getValue()){
					abnormal.setAbnormalTypeName(abnormalTypeEnum.getName());
					continue;
				}
			}
			// 设置异常环节名称
			AbnormalNodeEnum[] abnormalNodeEnums = AbnormalNodeEnum.values();
			for (AbnormalNodeEnum abnormalNodeEnum : abnormalNodeEnums) {
				if(Integer.parseInt(abnormal.getAbnormalNode()) == abnormalNodeEnum.getValue()){
					abnormal.setAbnormalNodeName(abnormalNodeEnum.getName());
					continue;
				}
			}
			// 设置 付款方式名称
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(abnormal.getPayMethod() == payModeEnum.getValue()){
					abnormal.setPayMethodName(payModeEnum.getName());
					continue;
				}
			}
		}
		
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	
	/**
	 * 添加异常信息
	 * @param abnormal
	 * @return
	 */
	public Boolean insertAbnormal(Abnormal abnormal, User user){
		Boolean flag = false;
		abnormal.setAbnormalStatus(Const.TMS_ABNORMAL_STATUS_NOT_HANDLED);
		if(abnormalMapper.insert(abnormal)>0){
			flag = true;
		}
		// 修改 对应的运单 或 提货单的异常状态
		WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(abnormal.getWayBillNumber());
		if(wayBillOrder == null){ // 未生成运单 则修改提货单的异常状态
			LadingOrder ladingOrder = new LadingOrder();
			ladingOrder.setWayBillNumber(abnormal.getWayBillNumber());
			ladingOrder.setExceptionStatus(ExceptionStatusEnum.REGISTER.getValue());
			ladingOrderMapper.updateByWayBillNumberSelective(ladingOrder);
		}else{ // 已生成运单 则修改运单的异常状态
			WayBillOrder wayBillOrderTemp = new WayBillOrder();
			wayBillOrderTemp.setWayBillNumber(wayBillOrder.getWayBillNumber());
			wayBillOrderTemp.setExceptionStatus(ExceptionStatusEnum.REGISTER.getValue());
			wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrderTemp);
		}
		sendMessage(wayBillOrder, SmsTemplateEnum.ABNORMAL_CHECK_IN, user.getOutletsId());
		return flag;
	}
	
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(WayBillOrder wayBillOrder, SmsTemplateEnum smsTemplateEnum, Long outletsId){
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		if(wayBillOrder == null){
			return;
		}
		SmsParams smsParams = new SmsParams();
		smsParams.setOrderNumber(wayBillOrder.getOrderNumber());
		smsParams.setWaybillNumber(wayBillOrder.getWayBillNumber());
		smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(outletsId).getCompanyName());
		smsParams.setOutletsName(outletsInfoMapper.selectById(outletsId).getName());
		smsParams.setOrderStatus("已登记异常");
		
		if(smsTemplate.getSendConsignor() != null && smsTemplate.getSendConsignor() == 1){
			// 发送短信给发货人
			boolean flag = false;
			try {
				flag = smsManagerService.sendSms(wayBillOrder.getConsignorMobile(), smsParams, smsTemplateEnum);
			} catch (Exception e) {
				logger.error("【发送短信给发货人失败】", e);
				return;
			}
			if(flag){
				logger.info("【发送短信给发货人成功】");
			}
		}
		if(smsTemplate.getSendConsignee() != null && smsTemplate.getSendConsignee() == 1){
			// 发送短信给收货人
			boolean flag = false;
			try {
				flag = smsManagerService.sendSms(wayBillOrder.getConsigneeMobile(), smsParams, smsTemplateEnum);
			} catch (Exception e) {
				logger.error("【发送短信给收货人失败】", e);
				return;
			}
			if(flag){
				logger.info("【发送短信给收货人成功】");
			}
		}
	}
	
	/**
	 * 关闭 异常
	 * @param wayBillNumbers
	 * @return
	 */
	public int saveCloseException(String[] wayBillNumbers){
		// 修改 对应的运单 或 提货单的异常状态
		if(wayBillNumbers == null || wayBillNumbers.length == 0){
			return 0;
		}
		int rows = 0;
		for (String wayBillNumber : wayBillNumbers) {
			WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
			if(wayBillOrder == null){ // 未生成运单 则修改提货单的异常状态
				LadingOrder ladingOrder = new LadingOrder();
				ladingOrder.setWayBillNumber(wayBillNumber);
				ladingOrder.setExceptionStatus(ExceptionStatusEnum.NO.getValue());
				ladingOrderMapper.updateByWayBillNumberSelective(ladingOrder);
			}else{ // 已生成运单 则修改运单的异常状态
				WayBillOrder wayBillOrderTemp = new WayBillOrder();
				wayBillOrderTemp.setWayBillNumber(wayBillOrder.getWayBillNumber());
				wayBillOrderTemp.setExceptionStatus(ExceptionStatusEnum.NO.getValue());
				wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrderTemp);
			}
			// 修改 异常记录的状态为 关闭
			Abnormal abnormal = new Abnormal();
			abnormal.setWayBillNumber(wayBillNumber);
			abnormal.setAbnormalStatus(Const.TMS_ABNORMAL_STATUS_CLOSED);
			rows += abnormalMapper.updateByWayBillNumberSelective(abnormal);
		}
		return rows;
	}
}
