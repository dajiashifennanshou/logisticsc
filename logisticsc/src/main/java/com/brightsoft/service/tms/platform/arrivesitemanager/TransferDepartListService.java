package com.brightsoft.service.tms.platform.arrivesitemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.CostSubjectEnum;
import com.brightsoft.common.enums.OrderNumberTypeEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.ReceivablePayBillMapper;
import com.brightsoft.dao.tms.TransferDepartListMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.dao.tms.WayBillTransferRecordMapper;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.ReceivablePayBill;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.TransferDepartList;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillTransferRecord;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 中转出库 service
 * @author yangshoubao
 *
 */
@Service
public class TransferDepartListService {

	private static final Logger logger = Logger.getLogger(TransferDepartListService.class);
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private TransferDepartListMapper transferDepartListMapper;
	
	@Autowired
	private WayBillTransferRecordMapper wayBillTransferRecordMapper;
	
	@Autowired
	private ReceivablePayBillMapper receivablePayBillMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	/**
	 * 保存 中转发车单信息
	 * @param transferDepartListJson
	 * @param wayBillTransferRecordJson
	 * @return
	 */
	public int saveTransferDepartList(String transferDepartListJson, String wayBillTransferRecordJson, User user){
		TransferDepartList transferDepartList = JSONObject.parseObject(transferDepartListJson, TransferDepartList.class);
		List<WayBillTransferRecord> wayBillTransferRecords = JSONArray.parseArray(wayBillTransferRecordJson, WayBillTransferRecord.class);
		
		String transferDepartNumber = transferDepartList.getTransferDepartNumber();
		if(StringUtils.isEmpty(transferDepartNumber)){
			// 生成 中转发车单号
			transferDepartNumber = generateTransferNumber(user.getOutletsId());
		}
		
		String wayBillNumbers = ""; // 获取需中转的运单号
		for(int i = 0; i < wayBillTransferRecords.size(); i++){
			WayBillTransferRecord wayBillTransferRecord = wayBillTransferRecords.get(i);
			wayBillTransferRecord.setId(null);
			wayBillTransferRecord.setTransferDepartNumber(transferDepartNumber);
			if(i == wayBillTransferRecords.size() - 1){
				wayBillNumbers += wayBillTransferRecord.getWayBillNumber();
			}else{
				wayBillNumbers += wayBillTransferRecord.getWayBillNumber() + ",";
			}
		}
		
		// 保存中转发车单信息  设置相应数据
		transferDepartList.setTransferDepartNumber(transferDepartNumber);
		
		transferDepartList.setStartOutlets(user.getOutletsId());
		transferDepartList.setStatus(0); // TODO 设置中转发车单状态
		transferDepartList.setWayBillNumbers(wayBillNumbers);
		
		int rows = 0;
		if(transferDepartList.getId() == null){ // 添加
			rows = transferDepartListMapper.insertSelective(transferDepartList);
			Double transferCost = transferDepartList.getTransferCost();
			if(transferCost != null && transferCost != 0){
				ReceivablePayBill receivablePayBill = new ReceivablePayBill();
				receivablePayBill.setCostSubject(CostSubjectEnum.TRANSFER_COST.getName());
				receivablePayBill.setIsCompleted(0);
				receivablePayBill.setTransferDepartNumber(transferDepartNumber);
				receivablePayBill.setTotal(transferCost);
				receivablePayBill.setCurrentPay(transferDepartList.getCurrentPay());
				receivablePayBill.setBackPay(transferDepartList.getBackPay());
				receivablePayBill.setCreateTime(new Date());
				receivablePayBill.setOutletsId(transferDepartList.getStartOutlets());
				receivablePayBillMapper.insert(receivablePayBill);
			}
			// 发送短信
			sendMessage(wayBillNumbers, SmsTemplateEnum.TRANSFER_OUT_STORAGE);
		}else{ // 修改
			// 修改中转发车单信息
			rows = transferDepartListMapper.updateByPrimaryKeySelective(transferDepartList);
			wayBillTransferRecordMapper.deleteByTransferDepartNumber(transferDepartNumber);
		}
		wayBillTransferRecordMapper.batchInsert(wayBillTransferRecords);
		return rows;
	}
	
	/**
	 * 生成 中转发车单号
	 * @param outletsId
	 * @return
	 */
	private String generateTransferNumber(Long outletsId){
		// 获取公司组织代码    网点编号
		String orderNumber = null;
	 	OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsAndCompanyCodeById(outletsId);
	 	String companyCode = outletsInfo.getCompanyCode();
		String outletsNumber = outletsInfo.getOutletsNumber();
		String dateStr = DateTools.getCurrentDateStr("yyMM");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numberType", OrderNumberTypeEnum.TRANSFER_DEPART_ORDER_NUMBER.getValue());
		params.put("companyCode", companyCode);
		params.put("outletsNumber", outletsNumber);
		
		OrderSerialNumber serialNumber = new OrderSerialNumber();
		serialNumber.setNumberType(OrderNumberTypeEnum.TRANSFER_DEPART_ORDER_NUMBER.getValue());
		serialNumber.setCompanyCode(companyCode);
		serialNumber.setOutletsNumber(outletsNumber);
		serialNumber.setDateStr(dateStr);
		
		OrderSerialNumber orderSerialNumber = orderSerialNumberMapper.selectByParams(params);
		if(orderSerialNumber == null){
			String newNumber = "001";
			orderNumber = SystemConstant.TRANSFER_ORDER_NUMBER_PREFIX + companyCode + outletsNumber + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			orderSerialNumberMapper.insertSelective(serialNumber);
		}else{
			String number = orderSerialNumber.getSerialNumber();
			String newNumber = (Integer.parseInt(number) + 1) + "";
			if(newNumber.length() == 1){
				newNumber = "00" + newNumber;
			}else if(newNumber.length() == 2){
				newNumber = "0" + newNumber;
			}
			orderNumber = SystemConstant.TRANSFER_ORDER_NUMBER_PREFIX + companyCode + outletsNumber + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			serialNumber.setId(orderSerialNumber.getId());
			orderSerialNumberMapper.updateByPrimaryKeySelective(serialNumber);
		}
		return orderNumber;
	}
	
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(String wayBillNumbers, SmsTemplateEnum smsTemplateEnum){
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		if(StringUtils.isEmpty(wayBillNumbers)){
			return;
		}
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		for (String wayBillNumber : wayBillNumberArr) {
			WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
			if(wayBillOrder == null){
				return;
			}
			SmsParams smsParams = new SmsParams();
			smsParams.setOrderNumber(wayBillOrder.getOrderNumber());
			smsParams.setWaybillNumber(wayBillNumber);
			smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(wayBillOrder.getTargetOutlets()).getCompanyName());
			smsParams.setOutletsName(outletsInfoMapper.selectById(wayBillOrder.getTargetOutlets()).getName());
			smsParams.setOrderStatus("已外包出库");
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
	
	/**
	 * 查询 中转发车单列表
	 * @param params
	 * @return
	 */
	public Result selectByParams(BaseSearchParams params){
		List<TransferDepartList> transferDepartLists = transferDepartListMapper.selectByParams(params);
		int count = transferDepartListMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setRows(transferDepartLists);
		result.setResults(count);
		return result;
	}

	/**
	 * 根据 中转发车单号 中转发车单信息
	 * @param transferDepartNumber
	 * @return
	 */
	public TransferDepartList selectByTransferDepartNumber(String transferDepartNumber){
		TransferDepartList transferDepartList = transferDepartListMapper.selectByTransferDepartNumber(transferDepartNumber);
		if(transferDepartList == null){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		transferDepartList.setTransferTimeStr(format.format(transferDepartList.getTransferTime()));
		List<String> list = new ArrayList<String>();
		String wayBillNumbers = transferDepartList.getWayBillNumbers();
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		for (String wayBillNumber : wayBillNumberArr) {
			list.add(wayBillNumber);
		}
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(list);
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			WayBillTransferRecord wayBillTransferRecord = wayBillTransferRecordMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			wayBillOrder.setTransferCost(wayBillTransferRecord.getTransferCost());
			wayBillOrder.setTransferDepartNumber(wayBillTransferRecord.getTransferDepartNumber());
			wayBillOrder.setTransferWayBillNumber(wayBillTransferRecord.getTransferWayBillNumber());
			wayBillOrder.setCurrentPay(wayBillTransferRecord.getCurrentPay());
			wayBillOrder.setOutBackPay(wayBillTransferRecord.getOutBackPay());
		}
		transferDepartList.setWayBillOrders(wayBillOrders);
		return transferDepartList;
	}
}
