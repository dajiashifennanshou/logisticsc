package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankPayment;
import com.brightsoft.utils.Page;

public interface platformBankPaymentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankPayment record);

    int insertSelective(platformBankPayment record);

    platformBankPayment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankPayment record);

    int updateByPrimaryKey(platformBankPayment record);
    
    public platformBankPayment selectBankPayment(@Param("requestid")String requestid);
    
    platformBankPayment selectBankPaymentByOrderNumber(@Param("orderNumber")String orderNumber,@Param("orderType") Integer orderType);
    
    public List<platformBankPayment> selectBySelectedConditionPayment(@Param("bankPayment") platformBankPayment bankPayment,@Param("page")Page<?> page);
    
    public int countPayment(@Param("bankPayment") platformBankPayment bankPayment);
    
    
    
    public int selectBankPaymentByUserId(@Param("userId") Long userId);
    
    
    public List<platformBankPayment> selectPaymentList(@Param("bankPayment") platformBankPayment bankPayment,@Param("page")Page<?> page);
    
    public int countPaymentList(@Param("bankPayment") platformBankPayment bankPayment);
    
   
    public List<platformBankPayment> selectBySelectedConditionPaymentForm(@Param("bankPayment") platformBankPayment bankPayment,@Param("page")Page<?> page);
    
    public int countPaymentForm(@Param("bankPayment") platformBankPayment bankPayment);
    
    public int getTotalNotSplit(String loginName);
    
    /**
     * 获取
     * @param bankPayment
     * @return
     */
    public int selectPaymentAmount(@Param("bankPayment") platformBankPayment bankPayment);
    
}