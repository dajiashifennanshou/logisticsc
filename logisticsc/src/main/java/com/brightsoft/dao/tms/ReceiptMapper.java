package com.brightsoft.dao.tms;

import com.brightsoft.model.Receipt;

public interface ReceiptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Receipt record);

    int insertSelective(Receipt record);

    Receipt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Receipt record);

    int updateByPrimaryKey(Receipt record);
}