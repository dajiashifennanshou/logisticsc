package com.brightsoft.service.platform;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderBillMapper;
import com.brightsoft.model.PlatformMineBillInfo;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service("platformOrderBill")
public class PlatformOrderBillServiceImpl implements PlatformOrderBillService{
	
	@Autowired
	public PlatformOrderBillMapper billMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	/**
	 * 分页获取账单信息
	 */
	public Page<?> selectBySelectedCondition(PlatformOrderBill orderBill, Page<?> page) {
		int results = billMapper.countRows(orderBill);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformOrderBill> orderBills = billMapper.selectBySelectedCondition(orderBill, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, orderBills);
		page.setParams(map);
		return page;
	}

	/**
	 * 根据订单编号 查询账单信息
	 */
	public PlatformOrderBill selectByOrderNumber(String orderNumber) {
		PlatformOrderBill orderBill = null;
		orderBill =billMapper.selectByOrderNumber(orderNumber);
		if(null == orderBill){
			return null;
		}
		return orderBill;
	}
	/**
	 * 查看账单详情
	 */
	public PlatformMineBillInfo selectMineBillInfo(Long userId,
			String orderNumber) {
		PlatformMineBillInfo billInfo = billMapper.selectMineBillInfo(userId, orderNumber);
		billInfo.setConsigneeProvince(xzqhServiceImpl.selectValueById(billInfo.getConsigneeProvince()).getName());
		billInfo.setConsigneeCity(xzqhServiceImpl.selectValueById(billInfo.getConsigneeCity()).getName());
		billInfo.setConsigneeCounty(xzqhServiceImpl.selectValueById(billInfo.getConsigneeCounty()).getName());
		billInfo.setConsignorProvince(xzqhServiceImpl.selectValueById(billInfo.getConsignorProvince()).getName());
		billInfo.setConsignorCity(xzqhServiceImpl.selectValueById(billInfo.getConsignorCity()).getName());
		billInfo.setConsignorCounty(xzqhServiceImpl.selectValueById(billInfo.getConsignorCounty()).getName());
		return billInfo;
	}
	/**
	 * 导出我的订单
	 */
	public List<Map<String, Object>> exportMineBillInfo(PlatformOrderBill orderBill) {
		//查询当前账单ID
		List<Long> id = billMapper.selectBill(orderBill);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		if(id.size() >0){
			List<PlatformMineBillInfo> billInfos = billMapper.selectMineBill(orderBill.getUserId(), id);
			 Map<String, Object> map = new HashMap<String, Object>();
		        map.put("sheetName", "mineBill");
		        listmap.add(map);
		        PlatformMineBillInfo mineBillInfo = null;
		        for (int j = 0; j < billInfos.size(); j++) {
		        	billInfos.get(j).setConsigneeProvince(xzqhServiceImpl.selectValueById(billInfos.get(j).getConsigneeProvince()).getName());
		        	billInfos.get(j).setConsigneeCity(xzqhServiceImpl.selectValueById(billInfos.get(j).getConsigneeCity()).getName());
		        	billInfos.get(j).setConsigneeCounty(xzqhServiceImpl.selectValueById(billInfos.get(j).getConsigneeCounty()).getName());
		        	billInfos.get(j).setConsignorProvince(xzqhServiceImpl.selectValueById(billInfos.get(j).getConsignorProvince()).getName());
		        	billInfos.get(j).setConsignorCity(xzqhServiceImpl.selectValueById(billInfos.get(j).getConsignorCity()).getName());
		        	billInfos.get(j).setConsignorCounty(xzqhServiceImpl.selectValueById(billInfos.get(j).getConsignorCounty()).getName());
		        	mineBillInfo=billInfos.get(j);
		            Map<String, Object> mapValue = new HashMap<String, Object>();
		            mapValue.put("orderNumber", mineBillInfo.getOrderNumber());
		            mapValue.put("wayBillNumber", mineBillInfo.getWayBillNumber());
		            mapValue.put("origin", mineBillInfo.getConsignorProvince()+"-"+mineBillInfo.getConsignorCity()+"-"+mineBillInfo.getConsignorCounty());
		            mapValue.put("destination", mineBillInfo.getConsigneeProvince()+"-"+mineBillInfo.getConsigneeCity()+"-"+mineBillInfo.getConsigneeCounty());
		            mapValue.put("consignorName", mineBillInfo.getConsignorName());
		            mapValue.put("consignorPhoneNumber", mineBillInfo.getConsignorPhoneNumber());
		            mapValue.put("consigneeName", mineBillInfo.getConsigneeName());
		            mapValue.put("consigneePhoneNumber", mineBillInfo.getConsigneePhoneNumber());
		            mapValue.put("freight", mineBillInfo.getFreight());
		            mapValue.put("takeCargoCost", mineBillInfo.getTakeCargoCost());
		            mapValue.put("sendCargoCost", mineBillInfo.getSendCargoCost());
		            mapValue.put("loadCargoCost", mineBillInfo.getLoadCargoCost());
		            mapValue.put("unloadCargoCost", mineBillInfo.getUnloadCargoCost());
		            mapValue.put("insurance", mineBillInfo.getInsurance());
		            mapValue.put("otherCost", mineBillInfo.getOtherCost());
		            mapValue.put("totalCost", mineBillInfo.getTotalCost());
		            mapValue.put("prepaidCost", mineBillInfo.getPrepaidCost());
		            mapValue.put("unpaid",mineBillInfo.getTotalCost()-mineBillInfo.getFreight());
		            SimpleDateFormat myFmt=new SimpleDateFormat("yy/MM/dd HH:mm");        
		            String date = myFmt.format(mineBillInfo.getCreateTime());
		            mapValue.put("payDate",date);
		            mapValue.put("loginName", mineBillInfo.getLoginName());
		            listmap.add(mapValue);
		        }
		}else{
			 Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sheetName", "mineBill");
	        listmap.add(map);
		}
		return listmap;
	}

	public boolean updateIsPayment(String orderNumber) {
		if(billMapper.updateIsPayment(orderNumber)>0){
			return true;
		}
		return false;
	}

	public boolean updatePrepaidOst(Double prepaidCost,String orderNumber) {
		if(billMapper.updatePrepaidOst(prepaidCost,orderNumber)>0){
			return true;
		}
		return false;
	}
}
