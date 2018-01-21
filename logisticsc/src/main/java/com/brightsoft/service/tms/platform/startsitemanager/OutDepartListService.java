package com.brightsoft.service.tms.platform.startsitemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.brightsoft.common.enums.OutDepartListStatusEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.common.enums.WayBillOrderStatusEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutDepartListMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderFollowMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.dao.tms.WayBillOutSourceRecordMapper;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutDepartList;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.ReceivablePayBill;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillOrderFollow;
import com.brightsoft.model.WayBillOutSourceRecord;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.service.tms.platform.financialmanager.ReceivablePayBillService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 外包发车单 service
 * @author yangshoubao
 *
 */
@Service
public class OutDepartListService {

	private Logger logger = Logger.getLogger(OutDepartListService.class);
	
	@Autowired
	private OutDepartListMapper outDepartListMapper;
	
	@Autowired
	private WayBillOutSourceRecordMapper wayBillOutSourceRecordMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private ReceivablePayBillService receivablePayBillService;
	
	@Autowired
	private WayBillOrderFollowMapper wayBillOrderFollowMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	 
	 @SuppressWarnings("unchecked")
	public List selectExportByParams(SearchParams params) {
	      List outDepartLists = this.outDepartListMapper.selectExportByParams(params);
	      ArrayList result = new ArrayList();
	      HashMap sheetName = new HashMap();
	      sheetName.put("sheetName", "外包发车单列表");
	      result.add(sheetName);
	      if(outDepartLists != null && outDepartLists.size() != 0) {
	         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Iterator var7 = outDepartLists.iterator();

	         while(var7.hasNext()) {
	            OutDepartList outDepartList = (OutDepartList)var7.next();
	            HashMap map = new HashMap();
	            map.put("outSourceTime", dateFormat.format(outDepartList.getOutSourceTime()));
	            map.put("outDepartNumber", outDepartList.getOutDepartNumber());
	            int status = outDepartList.getStatus().intValue();
	            if(status == 0) {
	               map.put("status", "配载中");
	            } else if(status == 1) {
	               map.put("status", "已外包");
	            } else if(status == 2) {
	               map.put("status", "已作废");
	            }

	            map.put("startOutletsName", outDepartList.getStartOutletsName());
	            map.put("carriageCompany", outDepartList.getCarriageCompany());
	            map.put("startSitePerson", outDepartList.getStartSitePerson());
	            map.put("startSitePhone", outDepartList.getStartSitePhone());
	            map.put("endSitePerson", outDepartList.getEndSitePerson());
	            map.put("endSitePhone", outDepartList.getEndSitePhone());
	            map.put("outSourceCost", outDepartList.getOutSourceCost());
	            map.put("currentPay", outDepartList.getCurrentPay());
	            map.put("backPay", outDepartList.getBackPay());
	            result.add(map);
	         }

	         return result;
	      } else {
	         return result;
	      }
	   }

	
	/**
	 * 保存 外包发车单信息
	 * @param outDepartListJson
	 * @param wayBillOutSourceRecordJson
	 * @return
	 */
	public int saveOutDepartList(String outDepartListJson, String wayBillOutSourceRecordJson, User user){
		OutDepartList outDepartList = JSONObject.parseObject(outDepartListJson, OutDepartList.class);
		List<WayBillOutSourceRecord> wayBillOutSourceRecords = JSONArray.parseArray(wayBillOutSourceRecordJson, WayBillOutSourceRecord.class);
		
		String outDepartNumber = outDepartList.getOutDepartNumber();
		if(StringUtils.isEmpty(outDepartNumber)){
			// 生成 外包发车单号
			outDepartNumber = generateDepartureNumber(user.getOutletsId());
		}
		
		String wayBillNumbers = ""; // 获取需外包的运单号
		for(int i = 0; i < wayBillOutSourceRecords.size(); i++){
			WayBillOutSourceRecord wayBillOutSourceRecord = wayBillOutSourceRecords.get(i);
			wayBillOutSourceRecord.setId(null);
			wayBillOutSourceRecord.setOutDepartNumber(outDepartNumber);
			if(i == wayBillOutSourceRecords.size() - 1){
				wayBillNumbers += wayBillOutSourceRecord.getWayBillNumber();
			}else{
				wayBillNumbers += wayBillOutSourceRecord.getWayBillNumber() + ",";
			}
		}
		
		// 保存外包发车单信息  设置相应数据
		outDepartList.setOutDepartNumber(outDepartNumber);
		
		outDepartList.setStartOutlets(user.getOutletsId());
		outDepartList.setStatus(OutDepartListStatusEnum.LADING.getValue());
		outDepartList.setWayBillNumbers(wayBillNumbers);
		outDepartList.setOperatePerson(user.getId());
		String userName = user.getTrueName();
		if(StringUtils.isEmpty(userName)){
			userName = user.getLoginName();
		}
		outDepartList.setOperatePersonName(userName);
		
		int rows = 0;
		if(outDepartList.getId() == null){ // 添加
			rows = outDepartListMapper.insertSelective(outDepartList);
			Double outSourceCost = outDepartList.getOutSourceCost();
			if(outSourceCost != null && outSourceCost != 0){
				ReceivablePayBill receivablePayBill = new ReceivablePayBill();
				receivablePayBill.setCostSubject(CostSubjectEnum.OUT_SOURCE_COST.getName());
				receivablePayBill.setIsCompleted(0);
				receivablePayBill.setOutDepartNumber(outDepartNumber);
				receivablePayBill.setTotal(outSourceCost);
				receivablePayBill.setCurrentPay(outDepartList.getCurrentPay());
				receivablePayBill.setBackPay(outDepartList.getBackPay());
				receivablePayBill.setCreateTime(new Date());
				receivablePayBill.setOutletsId(outDepartList.getStartOutlets());
				receivablePayBillService.insert(receivablePayBill);
			}
		}else{ // 修改
			// 修改外包发车单信息
			rows = outDepartListMapper.updateByPrimaryKeySelective(outDepartList);
			wayBillOutSourceRecordMapper.deleteByOutDepartNumber(outDepartNumber);
		}
		wayBillOutSourceRecordMapper.batchInsert(wayBillOutSourceRecords);
		return rows;
	}
	
	/**
	 * 生成 外包发车单号
	 * @param outletsId
	 * @return
	 */
	private String generateDepartureNumber(Long outletsId){
		// 获取公司组织代码    网点编号
		String orderNumber = null;
	 	OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsAndCompanyCodeById(outletsId);
	 	String companyCode = outletsInfo.getCompanyCode();
		String outletsNumber = outletsInfo.getOutletsNumber();
		String dateStr = DateTools.getCurrentDateStr("yyMM");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numberType", OrderNumberTypeEnum.OUT_SOURCE_DEPART_ORDER_NUMBER.getValue());
		params.put("companyCode", companyCode);
		params.put("outletsNumber", outletsNumber);
		
		OrderSerialNumber serialNumber = new OrderSerialNumber();
		serialNumber.setNumberType(OrderNumberTypeEnum.OUT_SOURCE_DEPART_ORDER_NUMBER.getValue());
		serialNumber.setCompanyCode(companyCode);
		serialNumber.setOutletsNumber(outletsNumber);
		serialNumber.setDateStr(dateStr);
		
		OrderSerialNumber orderSerialNumber = orderSerialNumberMapper.selectByParams(params);
		if(orderSerialNumber == null){
			String newNumber = "001";
			orderNumber = SystemConstant.DEPARTURE_ORDER_NUMBER_PREFIX + companyCode + outletsNumber + dateStr + newNumber;
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
			orderNumber = SystemConstant.DEPARTURE_ORDER_NUMBER_PREFIX + companyCode + outletsNumber + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			serialNumber.setId(orderSerialNumber.getId());
			orderSerialNumberMapper.updateByPrimaryKeySelective(serialNumber);
		}
		return orderNumber;
	}
	
	/**
	 * 搜索外包发车单
	 * @param params
	 * @return
	 */
	public Result selectByParams(SearchParams params){
		List<OutDepartList> outDepartLists = outDepartListMapper.selectByParams(params);
		int count = outDepartListMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setRows(outDepartLists);
		result.setResults(count);
		return result;
	}
	
	/**
	 * 根据外包发车单号 查询发车单信息
	 * @param outDepartNumber
	 * @return
	 */
	public OutDepartList selectByOutDepartNumber(String outDepartNumber){
		OutDepartList outDepartList = outDepartListMapper.selectByOutDepartNumber(outDepartNumber);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		outDepartList.setOutSourceTimeStr(format.format(outDepartList.getOutSourceTime()));
		return outDepartList;
	}
	
	/**
	 * 外包出库
	 * @param outDepartLists
	 * @return
	 */
	public int saveOutSource(String outDepartLists, User user){
		int rows = 0;
		List<OutDepartList> list = JSONArray.parseArray(outDepartLists, OutDepartList.class);
		for (OutDepartList outDepartList : list) {
			// 修改外包发车单状态为 已外包
			rows += outDepartListMapper.updateStatusByOutDepartNumber(outDepartList.getOutDepartNumber(), OutDepartListStatusEnum.ALREADY_OUT_SOURCE.getValue());
			String[] wayBillNumberArr = outDepartList.getWayBillNumbers().split(",");
			List<String> wayBillNumbers = new ArrayList<String>();
			for (String wayBillNumber : wayBillNumberArr) {
				wayBillNumbers.add(wayBillNumber);
			}
			// 修改运单状态为 已外包
			wayBillOrderMapper.updateStatusByWayBillNumbers(wayBillNumbers, WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
			for(int i = 0; i < wayBillNumbers.size(); i++){
				// 添加运单跟踪记录
				WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
				wayBillOrderFollow.setWayBillNumber(wayBillNumbers.get(i));
				wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
				wayBillOrderFollow.setHandleTime(new Date());
				wayBillOrderFollow.setHandleInfo("运单外包");
				wayBillOrderFollow.setOperatePerson(user.getId());
				String operatePersonName = user.getTrueName();
				if(StringUtils.isEmpty(operatePersonName)){
					operatePersonName = user.getLoginName();
				}
				wayBillOrderFollow.setOperatePersonName(operatePersonName);
				wayBillOrderFollowMapper.insert(wayBillOrderFollow);
			}
		}
		
		// 发送短信
		List<String> wayBillNumbers = new ArrayList<String>();
		for (OutDepartList outDepartList : list) {
			String[] wayBillNumberArr = outDepartList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				wayBillNumbers.add(wayBillNumber);
			}
		}
		sendMessage(wayBillNumbers, SmsTemplateEnum.EPIBOLY_OUT_STORAGE);
		return rows;
	}

	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(List<String> wayBillNumbers, SmsTemplateEnum smsTemplateEnum){
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		if(wayBillNumbers == null || wayBillNumbers.size() == 0){
			return;
		}
		for (String wayBillNumber : wayBillNumbers) {
			WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
			SmsParams smsParams = new SmsParams();
			smsParams.setOrderNumber(wayBillOrder.getOrderNumber());
			smsParams.setWaybillNumber(wayBillNumber);
			smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(wayBillOrder.getStartOutlets()).getCompanyName());
			smsParams.setOutletsName(outletsInfoMapper.selectById(wayBillOrder.getStartOutlets()).getName());
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
	 * 作废
	 * @param outDepartNumbers
	 * @return
	 */
	public int updateDiscard(String outDepartNumbers) {
		List<String> outDepartNumberArr = JSONArray.parseArray(outDepartNumbers, String.class);
		int rows = 0;
		for (String outDepartNumber : outDepartNumberArr) {
			rows += outDepartListMapper.updateStatusByOutDepartNumber(outDepartNumber, OutDepartListStatusEnum.DISCARDED.getValue());
		}
		return rows;
	}
}
