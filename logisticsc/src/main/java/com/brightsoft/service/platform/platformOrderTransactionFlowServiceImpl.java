package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brightsoft.dao.platform.platformOrderTransactionFlowMapper;
import com.brightsoft.model.platformMineOrderTransactionFlow;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service
public class platformOrderTransactionFlowServiceImpl {
	
	@Autowired
	private platformOrderTransactionFlowMapper flowMapper;
	
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	/**
	 * 资金流水
	 * @param orderTransactionFlow
	 * @param page
	 * @return
	 */
	public Page<?> selectCompanCondition(platformMineOrderTransactionFlow orderTransactionFlow,Page<?> page){
		Map<String, Object> map = new HashMap<String, Object>();
		int results = flowMapper.countRows(orderTransactionFlow);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<platformMineOrderTransactionFlow> mineOrderTransactionFlows= flowMapper.selectCompanCondition(orderTransactionFlow, page);
		for (int i = 0; i < mineOrderTransactionFlows.size(); i++) {
			mineOrderTransactionFlows.get(i).setStartProvince(xzqhServiceImpl.selectValueById(mineOrderTransactionFlows.get(i).getStartProvince()).getName());
			mineOrderTransactionFlows.get(i).setStartCity(xzqhServiceImpl.selectValueById(mineOrderTransactionFlows.get(i).getStartCity()).getName());
			mineOrderTransactionFlows.get(i).setStartCounty(xzqhServiceImpl.selectValueById(mineOrderTransactionFlows.get(i).getStartCounty()).getName());
			mineOrderTransactionFlows.get(i).setEndProvince(xzqhServiceImpl.selectValueById(mineOrderTransactionFlows.get(i).getEndProvince()).getName());
			mineOrderTransactionFlows.get(i).setEndCity(xzqhServiceImpl.selectValueById(mineOrderTransactionFlows.get(i).getEndCity()).getName());
			mineOrderTransactionFlows.get(i).setEndCounty(xzqhServiceImpl.selectValueById(mineOrderTransactionFlows.get(i).getEndCounty()).getName());
		}
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, mineOrderTransactionFlows);
		page.setParams(map);
		return page;
	}
}