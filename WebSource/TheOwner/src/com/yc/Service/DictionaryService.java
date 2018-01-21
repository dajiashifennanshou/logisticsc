package com.yc.Service;

import java.util.List;

import com.yc.Entity.PlatformDictionary;


/**
 * 
 * 字典数据 业务接口
 */
public interface DictionaryService {

	public PlatformDictionary selectByPrimary(Long id);
	
	/**
	 * 查询字典数据
	 * @param dictType 字典类型
	 * @return
	 */
	List<PlatformDictionary> selectDictDataByType(String type);
	/***
	 * 获取运输方式
	 * @param id
	 * @param type
	 * @return
	 */
	public PlatformDictionary selectByPrimaryId(Long id, String type);
}
