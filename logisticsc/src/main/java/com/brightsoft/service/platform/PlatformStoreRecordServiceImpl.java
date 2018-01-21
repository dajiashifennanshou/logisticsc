package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.dao.platform.PlatformStoreRecordMapper;
import com.brightsoft.model.PlatformCollectionLine;
import com.brightsoft.model.PlatformStoreRecord;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;
@Service("platformStoreRecord")
public class PlatformStoreRecordServiceImpl implements PlatformStoreRecordService{
	
	@Autowired
	private PlatformStoreRecordMapper recordMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired
	private DictionaryService dictionaryService;
	/**
	 * 我的收藏
	 */
	public Page<?> selectBySelectedCondition(
			PlatformCollectionLine collectionLine, Page<?> page) {
		int results = recordMapper.countRows(collectionLine);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformCollectionLine> collectionLines = recordMapper.selectBySelectedCondition(collectionLine,page);
		for (int i = 0; i < collectionLines.size(); i++) {
			collectionLines.get(i).setStartProvinceValue(xzqhServiceImpl.selectValueById(collectionLines.get(i).getStartProvince()).getName());
			collectionLines.get(i).setStartCityValue(xzqhServiceImpl.selectValueById(collectionLines.get(i).getStartCity()).getName());
			collectionLines.get(i).setStartCountyValue(xzqhServiceImpl.selectValueById(collectionLines.get(i).getStartCounty()).getName());
			collectionLines.get(i).setEndProvinceValue(xzqhServiceImpl.selectValueById(collectionLines.get(i).getEndProvince()).getName());
			collectionLines.get(i).setEndCityValue(xzqhServiceImpl.selectValueById(collectionLines.get(i).getEndCity()).getName());
			collectionLines.get(i).setEndCountyValue(xzqhServiceImpl.selectValueById(collectionLines.get(i).getEndCounty()).getName());
			collectionLines.get(i).setServerType(dictionaryService.selectByPrimaryId(Long.parseLong(collectionLines.get(i).getServerType()), DictionaryType.SERVER_TYPE).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, collectionLines);
		page.setParams(map);
		return page;
	}
	/**
	 * 线路收藏
	 */
	public boolean insertCollectionLine(PlatformStoreRecord platformStoreRecord) {
		platformStoreRecord.setState(Const.PLATFORMUSER_STORE_RECORD_STATE_1);
		platformStoreRecord.setCollectionTime(new Date());
		if(recordMapper.inserCollectionLine(platformStoreRecord)> 0){
			return true;
		}
		return false;
	}
	/**
	 *  逻辑删除我的收藏
	 */
	public boolean updateCollectionLine(List<Long> collectionLineId) {
		if(recordMapper.updateCollectionLine(collectionLineId, Const.PLATFORMUSER_STORE_RECORD_STATE_0) >0){
			return true;
		}
		return false;
	}
	public boolean selectCollentionLineId(PlatformStoreRecord platformStoreRecord){
		if(recordMapper.selectCollecionLineId(platformStoreRecord) > 0){
			return true;
		}
		return false;
	}
}
