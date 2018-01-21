package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserLineConsumeRecordMapper;
import com.brightsoft.model.PlatformLineConsumeRecord;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service("platformUserLineConsumeRecord")
public class PlatformUserLineConsumeRecordServiceImpl implements PlatformUserLineConsumeRecordService{
	@Autowired
	public PlatformUserLineConsumeRecordMapper consumeRecordMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	/**
	 * 预充值消费记录
	 */
	public Page<?> selectBySelectedCondition(
			PlatformLineConsumeRecord consumeRecord, Page<?> page) {
		int results = consumeRecordMapper.countRows(consumeRecord);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformLineConsumeRecord> consumeRecords = consumeRecordMapper.selectBySelectedCondition(consumeRecord, page);
		for (int i = 0; i < consumeRecords.size(); i++) {
			consumeRecords.get(i).setStartProvince(xzqhServiceImpl.selectValueById(consumeRecords.get(i).getStartProvince()).getName());
			consumeRecords.get(i).setStartCity(xzqhServiceImpl.selectValueById(consumeRecords.get(i).getStartCity()).getName());
			consumeRecords.get(i).setEndProvince(xzqhServiceImpl.selectValueById(consumeRecords.get(i).getEndProvince()).getName());
			consumeRecords.get(i).setEndCity(xzqhServiceImpl.selectValueById(consumeRecords.get(i).getEndCity()).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, consumeRecords);
		page.setParams(map);
		return page;
	}
	

}
