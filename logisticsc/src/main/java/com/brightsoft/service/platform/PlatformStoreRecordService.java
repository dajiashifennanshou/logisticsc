package com.brightsoft.service.platform;

import java.util.List;

import com.brightsoft.model.PlatformCollectionLine;
import com.brightsoft.model.PlatformStoreRecord;
import com.brightsoft.utils.Page;

public interface PlatformStoreRecordService {
	
	/**
	 * 分页获取线路收藏
	 */
	public Page<?> selectBySelectedCondition(PlatformCollectionLine collectionLine, Page<?> page);
	/**
	 * 增加线路收藏
	 * @param platformStoreRecord
	 * @return
	 */
	public boolean insertCollectionLine(PlatformStoreRecord platformStoreRecord);
	/**
	 * 逻辑删除我的收藏
	 * @param collectionLineId
	 * @return
	 */
	public boolean updateCollectionLine(List<Long> collectionLineId);
	
	public boolean selectCollentionLineId(PlatformStoreRecord platformStoreRecord);
}
