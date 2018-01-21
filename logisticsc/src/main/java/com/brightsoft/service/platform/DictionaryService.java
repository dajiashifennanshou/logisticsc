package com.brightsoft.service.platform;

import java.util.List;

import com.brightsoft.model.BuiDictionary;
import com.brightsoft.model.PlatformDictionary;

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
	/**
	 * 查询字典数据/bui
	 * @param type
	 * @return
	 */
	public List<BuiDictionary> selectDictDataByType4Bui(String type);
}
