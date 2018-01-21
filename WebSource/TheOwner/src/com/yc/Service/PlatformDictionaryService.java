package com.yc.Service;

import java.util.List;

import com.yc.Entity.PlatformDictionary;

/**
 * 类型字段
 * @Author:luojing
 * @2016年8月18日 下午1:42:43
 */
public interface PlatformDictionaryService {
	List<PlatformDictionary> selectDictDataByType(String type);
}
