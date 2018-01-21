package com.brightsoft.service.tms.platform.arrivesitemanager;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.dao.tms.WayBillOutStoreRecordMapper;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillOutStoreRecord;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 运单出库记录 service
 * @author yangshoubao
 *
 */
@Service
public class WayBillOutStoreRecordService {

	private static final Logger logger = Logger.getLogger(WayBillOutStoreRecordService.class);
	
	@Autowired
	private WayBillOutStoreRecordMapper wayBillOutStoreRecordMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	public int save(WayBillOutStoreRecord wayBillOutStoreRecord, User user){
		String trueName = user.getTrueName();
		if(StringUtils.isEmpty(trueName)){
			trueName = user.getLoginName();
		}
		wayBillOutStoreRecord.setOperatePerson(trueName);
		wayBillOutStoreRecord.setOperateTime(new Date());
		int rows = wayBillOutStoreRecordMapper.insert(wayBillOutStoreRecord);
		// 发送短信
		sendMessage(wayBillOutStoreRecord.getWayBillOrderId(), SmsTemplateEnum.DELIVERY_OUT_STORAGE, wayBillOutStoreRecord.getVehicleNumber(), wayBillOutStoreRecord.getDriverPhone());
		return rows;
	}
	
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(Long wayBillOrderId, SmsTemplateEnum smsTemplateEnum, String vehicleNumber, String driverPhone){
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		WayBillOrder wayBillOrder = wayBillOrderMapper.selectByPrimaryKey(wayBillOrderId);
		SmsParams smsParams = new SmsParams();
		smsParams.setOrderNumber(wayBillOrder.getOrderNumber());
		smsParams.setWaybillNumber(wayBillOrder.getWayBillNumber());
		smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(wayBillOrder.getTargetOutlets()).getCompanyName());
		smsParams.setOutletsName(outletsInfoMapper.selectById(wayBillOrder.getTargetOutlets()).getName());
		smsParams.setCarNumber(vehicleNumber);
		smsParams.setLinkPhone(driverPhone);
		smsParams.setOrderStatus("已配送出库");
		
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
}
