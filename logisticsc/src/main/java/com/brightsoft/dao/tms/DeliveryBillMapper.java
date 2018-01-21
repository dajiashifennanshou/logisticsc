package com.brightsoft.dao.tms;

import com.brightsoft.model.DeliveryBill;

public interface DeliveryBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryBill record);

    int insertSelective(DeliveryBill record);

    DeliveryBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryBill record);

    int updateByPrimaryKey(DeliveryBill record);
}