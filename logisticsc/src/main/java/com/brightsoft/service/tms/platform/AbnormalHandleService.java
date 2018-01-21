package com.brightsoft.service.tms.platform;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.ExceptionStatusEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.AbnormalHandleMapper;
import com.brightsoft.dao.tms.AbnormalMapper;
import com.brightsoft.dao.tms.LadingOrderMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.model.Abnormal;
import com.brightsoft.model.AbnormalHandle;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.sms.SmsParams;

@Service
public class AbnormalHandleService {

	private static final Logger logger = Logger.getLogger(AbnormalHandleService.class);
	
	@Autowired
	private AbnormalHandleMapper abnormalHandleMapper;
	
	@Autowired
	private AbnormalMapper abnormalMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private LadingOrderMapper ladingOrderMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	/**
	 * 异常处理
	 * @param abnormalHandle
	 * @return
	 */
	public Boolean insert(AbnormalHandle abnormalHandle, User user){
		Boolean flag = false;
		abnormalHandle.setHandleDate(DateTools.string2Date(abnormalHandle.getHandleDateStr()));
		
		// 修改 提货单 或 运单 异常状态
		Abnormal abnormalTemp = abnormalMapper.selectByPrimaryKey(abnormalHandle.getAbnormalId());
		
		WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(abnormalTemp.getWayBillNumber());
		if(wayBillOrder == null){ // 未生成运单 则 修改提货单的异常状态
			LadingOrder ladingOrder = new LadingOrder();
			ladingOrder.setWayBillNumber(abnormalTemp.getWayBillNumber());
			ladingOrder.setExceptionStatus(ExceptionStatusEnum.HANDLER.getValue());
			ladingOrderMapper.updateByWayBillNumberSelective(ladingOrder);
		}else{ // 已生成运单 则 修改运单的异常状态
			WayBillOrder wayBillOrderTemp = new WayBillOrder();
			wayBillOrderTemp.setWayBillNumber(abnormalTemp.getWayBillNumber());
			wayBillOrderTemp.setExceptionStatus(ExceptionStatusEnum.HANDLER.getValue());
			wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrderTemp);
		}
		
		if(abnormalHandleMapper.insert(abnormalHandle)>0){
			Abnormal abnormal = new Abnormal();
			abnormal.setId(abnormalHandle.getAbnormalId());
			abnormal.setAbnormalStatus(Const.TMS_ABNORMAL_STATUS_HANDLED);
			if(abnormalMapper.updateByPrimaryKeySelective(abnormal)>0){
				flag = true;
			}
		}
		sendMessage(wayBillOrder, SmsTemplateEnum.ABNORMAL_HANDLER, user.getOutletsId());
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
		smsParams.setOrderStatus("异常已处理");
		
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
	 * 根据 异常ID 查询异常处理信息
	 * @param abnormalId
	 * @return
	 */
	public AbnormalHandle selectByAbnormalId(Long abnormalId){
		return abnormalHandleMapper.selectByAbnormalId(abnormalId);
	}
}
