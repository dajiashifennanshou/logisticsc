package com.brightsoft.service.base;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.PublishTypeEnum;

@Service
public class EnumParserService {
	
	/**
	 * 获取发布信息的类型
	 * @return
	 */
	public HashMap<Object, Object> getPublishType(){
		PublishTypeEnum[] publishType = PublishTypeEnum.values();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		for (PublishTypeEnum publishTypeEnum : publishType) {
			map.put(publishTypeEnum.getValue(), publishTypeEnum.getName());
		}
		return map;
	}
	
}
