package com.brightsoft.dao.tms;

import java.util.List;
import java.util.Map;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.LadingOrder;

public interface LadingOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LadingOrder record);

    int insertSelective(LadingOrder record);

    LadingOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LadingOrder record);

    int updateByPrimaryKey(LadingOrder record);
    
    List<LadingOrder> selectByParams(SearchParams params);
    
    int selectByParamsCount(SearchParams params);
    
    LadingOrder selectByWayBillNumber(String wayBillNumber);
    
    int updateStatusByWayBillNumber(Map<String, Object> params);
    
    int updateByWayBillNumberSelective(LadingOrder ladingOrder);
}