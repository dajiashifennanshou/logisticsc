package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformMineOrderTransactionFlow;
import com.brightsoft.model.platformOrderTransactionFlow;
import com.brightsoft.utils.Page;

public interface platformOrderTransactionFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformOrderTransactionFlow record);

    int insertSelective(platformOrderTransactionFlow record);

    platformOrderTransactionFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformOrderTransactionFlow record);

    int updateByPrimaryKey(platformOrderTransactionFlow record);
    /**
     * 资金流水
     * @param mineOrderTransactionFlow
     * @return
     */
    public List<platformMineOrderTransactionFlow> selectCompanCondition(@Param("mineOrderTransactionFlow")platformMineOrderTransactionFlow mineOrderTransactionFlow,@Param("page")Page<?> page);
    
    public int countRows(@Param("mineOrderTransactionFlow")platformMineOrderTransactionFlow mineOrderTransactionFlow);
}