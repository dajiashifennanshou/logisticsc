package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformLineStorage;
import com.brightsoft.model.PlatformUserLineMoney;
import com.brightsoft.utils.Page;

public interface PlatformUserLineMoneyMapper {
	
 /**
	 * 查询筛选条件我的线路余额
	 * @param commonDriver
	 * @param page
	 * @return
	 */
	public List<PlatformLineStorage> selectBySelectedCondition(@Param("lineStorage") PlatformLineStorage lineStorage,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param commonDriver
	 * @return
	 */
    public int countRows(@Param("lineStorage") PlatformLineStorage lineStorage);
    /**
     * 修改我的预充值金额
     * @return
     */
    public int updateUserLineMoney(@Param("id") Long id,@Param("money") Double money);
    
    public PlatformUserLineMoney selectUserLineMoney(@Param("id") Long id);
    
    /**
     * 增加线路充值
     * @param lineMoney
     * @return
     */
    public int insertUserLineMoney(PlatformUserLineMoney lineMoney);
    
    /**
     * 根据线路ID 获取当前用户是否存在线路信息
     * @param lineId
     * @return
     */
    public PlatformUserLineMoney selectlineId(@Param("lineId") Long lineId,@Param("userId") Long userId);
    
    
}