package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SmsTemplate;
import com.brightsoft.utils.Page;

public interface SmsTemplateMapper {
    int insert(SmsTemplate record);

    int insertSelective(SmsTemplate record);
    
    int updateSelective(SmsTemplate record);
    
    List<SmsTemplate> getSmsTemplateList(@Param("page")Page<?> page);
    
    int getSmsTemplateListCount();
    
    SmsTemplate getSmsTemplateByAbbr(@Param("templateAbbr")String templateAbbr);
}