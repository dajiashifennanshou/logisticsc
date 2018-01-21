package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.ReceivablePayBillSearchParams;
import com.brightsoft.model.ReceivablePayBill;

public interface ReceivablePayBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceivablePayBill record);

    int insertSelective(ReceivablePayBill record);

    ReceivablePayBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceivablePayBill record);

    int updateByPrimaryKey(ReceivablePayBill record);
    
    List<ReceivablePayBill> selectByParams(ReceivablePayBillSearchParams params);
    
    int selectByParamsCount(ReceivablePayBillSearchParams params);
    
    double selectByParamsTotal(ReceivablePayBillSearchParams params);
}