package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.TransferDepartList;

public interface TransferDepartListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransferDepartList record);

    int insertSelective(TransferDepartList record);

    TransferDepartList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransferDepartList record);

    int updateByPrimaryKey(TransferDepartList record);
    
    List<TransferDepartList> selectByParams(BaseSearchParams params);
    
    int selectByParamsCount(BaseSearchParams params);
    
    TransferDepartList selectByTransferDepartNumber(String transferDepartNumber);
    
    TransferDepartList selectByWayBillNumber(String wayBillNumber);
}