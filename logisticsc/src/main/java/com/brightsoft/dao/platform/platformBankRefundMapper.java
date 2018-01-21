package com.brightsoft.dao.platform;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankRefund;
import com.brightsoft.utils.Page;

public interface platformBankRefundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankRefund record);

    int insertSelective(platformBankRefund record);

    platformBankRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankRefund record);

    int updateByPrimaryKey(platformBankRefund record);
    
    public List<platformBankRefund> selectBySelectedConditionBankRefund(@Param("bankRefund") platformBankRefund bankRefund,@Param("page")Page<?> page);
    
    public int countBankRefund(@Param("bankRefund") platformBankRefund bankRefund);
    
    
    public List<platformBankRefund> selectBankRefundList(@Param("bankRefund") platformBankRefund bankRefund,@Param("page")Page<?> page);
    
    public int countBankRefundList(@Param("bankRefund") platformBankRefund bankRefund);
}