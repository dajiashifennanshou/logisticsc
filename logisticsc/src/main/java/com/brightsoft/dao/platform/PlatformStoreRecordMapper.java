package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformCollectionLine;
import com.brightsoft.model.PlatformStoreRecord;
import com.brightsoft.utils.Page;

public interface PlatformStoreRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformStoreRecord record);

    int insertSelective(PlatformStoreRecord record);

    PlatformStoreRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformStoreRecord record);

    int updateByPrimaryKey(PlatformStoreRecord record);
	
	/**
	 * 查询筛选条件我的收藏
	 * @param commonDriver
	 * @param page
	 * @return
	 */
	public List<PlatformCollectionLine> selectBySelectedCondition(@Param("collectionLine") PlatformCollectionLine collectionLine,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param commonDriver
	 * @return
	 */
    public int countRows(@Param("collectionLine") PlatformCollectionLine collectionLine);
    
    /**
     * 添加线路收藏表
     * @param platformStoreRecord
     * @return
     */
    public int inserCollectionLine(PlatformStoreRecord platformStoreRecord);
    /**
     * 当前用户是否已收藏当前线路
     * @param platformStoreRecord
     * @return
     */
    public int selectCollecionLineId(PlatformStoreRecord platformStoreRecord);
    /**
     * 逻辑删除我的收藏
     * @param collectionLineId
     * @param state
     * @return
     */
    public int updateCollectionLine(@Param("collectionLineId")List<Long> collectionLineId,@Param("state") int state);
}