package com.brightsoft.service.tms.platform.financialmanager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.ReceivablePayBillSearchParams;
import com.brightsoft.dao.tms.DepartListMapper;
import com.brightsoft.dao.tms.OutDepartListMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.ReceivablePayBillMapper;
import com.brightsoft.dao.tms.ReceivablePayBillRecordMapper;
import com.brightsoft.dao.tms.TransferDepartListMapper;
import com.brightsoft.model.DepartList;
import com.brightsoft.model.OutDepartList;
import com.brightsoft.model.ReceivablePayBill;
import com.brightsoft.model.ReceivablePayBillRecord;
import com.brightsoft.model.TransferDepartList;
import com.brightsoft.utils.Result;

/**
 * 应收款账单 service
 * @author yangshoubao
 *
 */
@Service
public class ReceivablePayBillService {

	@Autowired
	private ReceivablePayBillMapper receivablePayBillMapper;
	
	@Autowired
	private ReceivablePayBillRecordMapper receivablePayBillRecordMapper;
	
	@Autowired
	private DepartListMapper departListMapper;
	
	@Autowired
	private OutDepartListMapper outDepartListMapper;
	
	@Autowired
	private TransferDepartListMapper transferDepartListMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	public int insert(ReceivablePayBill receivablePayBill){
		return receivablePayBillMapper.insert(receivablePayBill);
	}
	
	/**
	 * 查询 应付款账单
	 * @return
	 */
	public Result selectByParams(ReceivablePayBillSearchParams params){
		List<ReceivablePayBill> receivablePayBills = receivablePayBillMapper.selectByParams(params);
		if(receivablePayBills != null && receivablePayBills.size() > 0){
			for (ReceivablePayBill receivablePayBill : receivablePayBills) {
				String departNumber = receivablePayBill.getDepartNumber();
				if(StringUtils.isNotEmpty(departNumber)){
					DepartList departList = departListMapper.selectByDepartNumber(departNumber);
					receivablePayBill.setStartOutlets(departList.getStartOutlets());
					receivablePayBill.setStartOutletsName(departList.getStartOutletsName());
					receivablePayBill.setTargetOutlets(departList.getTargetOutlets());
					receivablePayBill.setTargetOutletsName(departList.getTargetOutletsName());
					receivablePayBill.setVehicleNumber(departList.getVehicleNumber());
					receivablePayBill.setDriverName(departList.getDriverName());
					receivablePayBill.setDriverPhone(departList.getDriverPhone());
				}
				String outDepartNumber = receivablePayBill.getOutDepartNumber();
				if(StringUtils.isNotEmpty(outDepartNumber)){
					OutDepartList outDepartList = outDepartListMapper.selectByOutDepartNumber(outDepartNumber);
					receivablePayBill.setStartOutlets(outDepartList.getStartOutlets());
					receivablePayBill.setStartOutletsName(outletsInfoMapper.selectById(outDepartList.getStartOutlets()).getName());
					receivablePayBill.setDestination(outDepartList.getDestination());
					receivablePayBill.setCarriageCompany(outDepartList.getCarriageCompany());
				}
				String transferDepartNumber = receivablePayBill.getTransferDepartNumber();
				if(StringUtils.isNotEmpty(transferDepartNumber)){
					TransferDepartList transferDepartList = transferDepartListMapper.selectByTransferDepartNumber(transferDepartNumber);
					receivablePayBill.setStartOutlets(transferDepartList.getStartOutlets());
					receivablePayBill.setStartOutletsName(outletsInfoMapper.selectById(transferDepartList.getStartOutlets()).getName());
				}
				// 查询 付款记录中的 收款人  收款电话
				List<ReceivablePayBillRecord> receivablePayBillRecords = receivablePayBillRecordMapper.selectByBillId(receivablePayBill.getId());
				if(receivablePayBillRecords != null && receivablePayBillRecords.size() > 0){
					receivablePayBill.setReceivePerson(receivablePayBillRecords.get(0).getReceivePerson());
					receivablePayBill.setReceivePersonPhone(receivablePayBillRecords.get(0).getReceivePersonPhone());
				}
			}
		}
		
		int count = receivablePayBillMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(receivablePayBills);
		// 查看应付款合计
		result.setData(receivablePayBillMapper.selectByParamsTotal(params));
		return result;
	}
}
