package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankAccounts;
import com.brightsoft.model.platformVoSplitPos;
import com.brightsoft.utils.Page;

public interface platformBankAccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankAccounts record);

    int insertSelective(platformBankAccounts record);

    platformBankAccounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankAccounts record);

    int updateByPrimaryKey(platformBankAccounts record);
    
    /**
     * pos机转账
     * @param splitPos
     * @param page
     * @return
     */
    public List<platformVoSplitPos> selectBankPos(@Param("splitPos") platformVoSplitPos splitPos,@Param("page")Page<?> page);
    
    public int countBankPos(@Param("splitPos") platformVoSplitPos splitPos);
    
    /**
     * pos机转账记录
     * @param splitPos
     * @param page
     * @return
     */
    public List<platformVoSplitPos> selectBankPosRecord(@Param("splitPos") platformVoSplitPos splitPos,@Param("page")Page<?> page);
    
    public int countBankPosRecord(@Param("splitPos") platformVoSplitPos splitPos);
}