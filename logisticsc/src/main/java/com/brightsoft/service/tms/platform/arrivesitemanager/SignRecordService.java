package com.brightsoft.service.tms.platform.arrivesitemanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.common.enums.WayBillOrderStatusEnum;
import com.brightsoft.common.enums.WayBillSIgnStatusEnum;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.DepartListMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.SignRecordMapper;
import com.brightsoft.dao.tms.WayBillOrderFollowMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.model.DepartList;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.SignRecord;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillOrderFollow;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 运单签收记录 service
 * @author yangshoubao
 *
 */
@Service
public class SignRecordService {

	private Logger logger = Logger.getLogger(SignRecordService.class);
	
	@Autowired
	private SignRecordMapper signRecordMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private PlatformOrderMapper platformOrderMapper;
	
	@Autowired
	private PlatformOrderFollowMapper platformOrderFollowMapper;
	
	@Autowired
	private WayBillOrderFollowMapper wayBillOrderFollowMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	@Autowired
	private DepartListMapper departListMapper;
	
	/**
	 * 保存 运单签收记录
	 * @param record
	 * @return
	 */
	public int saveSignRecord(SignRecord record, User user){
		String signTimeStr = record.getSignTimeStr();
		if(StringUtils.isNotEmpty(signTimeStr)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date signTime = null;
			try {
				signTime = format.parse(signTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			record.setSignTime(signTime);
		}
		if(record.getIsAdvanceMoney() == null){
			record.setIsAdvanceMoney(0);
		}
		if(record.getIsCompleted() == null){
			record.setIsCompleted(0);
		}
		int rows = signRecordMapper.insertSelective(record);
		// 修改运单状态为 已签收
		WayBillOrder wayBillOrder = new WayBillOrder();
		wayBillOrder.setWayBillNumber(record.getWayBillNumber());
		wayBillOrder.setSignStatus(WayBillSIgnStatusEnum.ALREADY_SIGN.getValue());
		wayBillOrder.setStatus(WayBillOrderStatusEnum.RECEIVED.getValue());
		wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrder);
		
		// 添加运单跟踪记录
		WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
		wayBillOrderFollow.setWayBillNumber(record.getWayBillNumber());
		wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.RECEIVED.getValue());
		wayBillOrderFollow.setHandleTime(new Date());
		wayBillOrderFollow.setHandleInfo("运单签收");
		wayBillOrderFollow.setOperatePerson(user.getId());
		String operatePersonName = user.getTrueName();
		if(StringUtils.isEmpty(operatePersonName)){
			operatePersonName = user.getLoginName();
		}
		wayBillOrderFollow.setOperatePersonName(operatePersonName);
		wayBillOrderFollowMapper.insert(wayBillOrderFollow);
		
		WayBillOrder wayBillOrderTemp = wayBillOrderMapper.selectByWayBillNumber(record.getWayBillNumber());
		// 如果 该运单为货运交易系统下单，则修改货运交易系统订单状态为【已签收】
		String orderNumber = wayBillOrderTemp.getOrderNumber();
		if(StringUtils.isNotEmpty(orderNumber)){
			PlatformOrder order = new PlatformOrder();
			order.setOrderNumber(orderNumber);
			order.setState(OrderStatusEnum.RECEIVED.getValue());
			platformOrderMapper.updateByOrderNumberSelective(order);
			// 添加订单跟踪记录
			PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
			platformOrderFollow.setOrderNumber(orderNumber);
			platformOrderFollow.setWayBillNumber(wayBillOrder.getWayBillNumber());
			platformOrderFollow.setStatus(OrderStatusEnum.RECEIVED.getValue());
			platformOrderFollow.setHandleTime(new Date());
			platformOrderFollow.setHandleInfo("运单签收");
			platformOrderFollow.setOperatePerson(user.getTrueName());
			platformOrderFollowMapper.insert(platformOrderFollow);
		}
		// 发送短信 
		sendMessage(wayBillOrderTemp, SmsTemplateEnum.SIGN_IN, record.getSignPerson(), record.getPhone());
		return rows;
	}
	 public Result saveSignRecordAllVehicle(String departNumber, User user) {
	      Result result = new Result();
	      if(StringUtils.isEmpty(departNumber)) {
	         result.setResult(false);
	         result.setMsg("签收失败");
	         return result;
	      } else if(user != null && user.getOutletsId() != null) {
	         DepartList departList = this.departListMapper.selectByDepartNumber(departNumber);
	         String wayBillNumbers = departList.getWayBillNumbers();
	         String[] wayBillNumberArr = wayBillNumbers.split(",");
	         ArrayList wayBillNumberList = new ArrayList();
	         String[] wayBillOrder = wayBillNumberArr;
	         int outletsInfo = wayBillNumberArr.length;

	         for(int wayBillOrders = 0; wayBillOrders < outletsInfo; ++wayBillOrders) {
	            String rows = wayBillOrder[wayBillOrders];
	            wayBillNumberList.add(rows);
	         }

	         int var20 = 0;
	         List var21 = this.wayBillOrderMapper.selectByWayBillNumbers(wayBillNumberList);
	         if(this.validWayBillOrderIsSign(var21)) {
	            result.setResult(false);
	            result.setMsg("该发车单的所有运单已全部签收");
	            return result;
	         } else {
	            OutletsInfo var22 = this.outletsInfoMapper.selectById(user.getOutletsId().longValue());
	            Iterator var12 = var21.iterator();

	            while(var12.hasNext()) {
	               WayBillOrder var23 = (WayBillOrder)var12.next();
	               if(var23.getSignStatus().intValue() == 0 && var23.getTargetCity().equals(var22.getCity())) {
	                  SignRecord record = new SignRecord();
	                  record.setWayBillNumber(var23.getWayBillNumber());
	                  record.setSignTime(new Date());
	                  record.setSignPerson(var23.getConsignee());
	                  record.setIdCard("");
	                  record.setPhone(var23.getConsigneeMobile());
	                  record.setAddress(var23.getConsigneeAddress());
	                  record.setIsAdvanceMoney(Integer.valueOf(0));
	                  record.setIsCompleted(Integer.valueOf(0));
	                  var20 += this.signRecordMapper.insertSelective(record);
	                  WayBillOrder wayBillOrderTemp = new WayBillOrder();
	                  wayBillOrderTemp.setWayBillNumber(var23.getWayBillNumber());
	                  wayBillOrderTemp.setSignStatus(Integer.valueOf(WayBillSIgnStatusEnum.ALREADY_SIGN.getValue()));
	                  wayBillOrderTemp.setStatus(Integer.valueOf(WayBillOrderStatusEnum.RECEIVED.getValue()));
	                  this.wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrderTemp);
	                  WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
	                  wayBillOrderFollow.setWayBillNumber(var23.getWayBillNumber());
	                  wayBillOrderFollow.setStatus(Integer.valueOf(WayBillOrderStatusEnum.RECEIVED.getValue()));
	                  wayBillOrderFollow.setHandleTime(new Date());
	                  wayBillOrderFollow.setHandleInfo("运单签收");
	                  wayBillOrderFollow.setOperatePerson(user.getId());
	                  String operatePersonName = user.getTrueName();
	                  if(StringUtils.isEmpty(operatePersonName)) {
	                     operatePersonName = user.getLoginName();
	                  }

	                  wayBillOrderFollow.setOperatePersonName(operatePersonName);
	                  this.wayBillOrderFollowMapper.insert(wayBillOrderFollow);
	                  String orderNumber = var23.getOrderNumber();
	                  if(StringUtils.isNotEmpty(orderNumber)) {
	                     PlatformOrder order = new PlatformOrder();
	                     order.setOrderNumber(orderNumber);
	                     order.setState(Integer.valueOf(OrderStatusEnum.RECEIVED.getValue()));
	                     this.platformOrderMapper.updateByOrderNumberSelective(order);
	                     PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
	                     platformOrderFollow.setOrderNumber(orderNumber);
	                     platformOrderFollow.setWayBillNumber(var23.getWayBillNumber());
	                     platformOrderFollow.setStatus(Integer.valueOf(OrderStatusEnum.RECEIVED.getValue()));
	                     platformOrderFollow.setHandleTime(new Date());
	                     platformOrderFollow.setHandleInfo("运单签收");
	                     platformOrderFollow.setOperatePerson(user.getTrueName());
	                     this.platformOrderFollowMapper.insert(platformOrderFollow);
	                  }

	                  this.sendMessage(var23, SmsTemplateEnum.SIGN_IN, record.getSignPerson(), record.getPhone());
	               }
	            }

	            if(var20 > 0) {
	               result.setResult(true);
	               result.setMsg("签收成功");
	               return result;
	            } else {
	               result.setResult(false);
	               result.setMsg("签收失败");
	               return result;
	            }
	         }
	      } else {
	         result.setResult(false);
	         result.setMsg("签收失败");
	         return result;
	      }
	   }
	 private boolean validWayBillOrderIsSign(List wayBillOrders) {
	      if(wayBillOrders != null && wayBillOrders.size() != 0) {
	         Iterator var3 = wayBillOrders.iterator();

	         while(var3.hasNext()) {
	            WayBillOrder wayBillOrder = (WayBillOrder)var3.next();
	            if(wayBillOrder.getSignStatus().intValue() == 0) {
	               return false;
	            }
	         }

	         return true;
	      } else {
	         return true;
	      }
	   }
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(WayBillOrder wayBillOrder, SmsTemplateEnum smsTemplateEnum, String signPerson, String phone){
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
		smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(wayBillOrder.getTargetOutlets()).getCompanyName());
		smsParams.setOutletsName(outletsInfoMapper.selectById(wayBillOrder.getTargetOutlets()).getName());
		smsParams.setOrderStatus("已签收");
		smsParams.setSignMan(signPerson);
		smsParams.setLinkPhone(phone);
		
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
	 * 查询签收记录
	 * @param wayBillNumber
	 * @return
	 */
	public SignRecord selectByWayBillNumber(String wayBillNumber){
		return signRecordMapper.selectByWayBillNumber(wayBillNumber);
	}
}
