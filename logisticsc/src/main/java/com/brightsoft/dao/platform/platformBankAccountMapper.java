package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankAccount;

public interface platformBankAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankAccount record);

    int insertSelective(platformBankAccount record);

    platformBankAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankAccount record);

    int updateByPrimaryKey(platformBankAccount record);
    
    /**
     * 
     * 方法描述：根据用户id获取用户子账户
     * @param userId
     * @return
     * @author dub
     * @version 2016年5月13日 下午7:38:42
     */
    platformBankAccount selectByUserId(Long userId);
    
    public platformBankAccount selectBankAccountUserId(@Param("userId") Long userId);
}