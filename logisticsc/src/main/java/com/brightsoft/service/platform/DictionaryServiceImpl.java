package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformDictionaryMapper;
import com.brightsoft.model.BuiDictionary;
import com.brightsoft.model.PlatformDictionary;

/**
 * 
 * 字典数据 业务实现类
 */
@Service
public class DictionaryServiceImpl implements DictionaryService{

	/** 字典数据 mapper */
	@Autowired
	private PlatformDictionaryMapper dictionaryMapper;
	
	public List<PlatformDictionary> selectDictDataByType(String type) {
		return dictionaryMapper.selectDictDataByType(type);
	}

	public List<BuiDictionary> selectDictDataByType4Bui(String type) {
		return dictionaryMapper.selectDictDataByType4Bui(type);
	}
	
	public PlatformDictionary selectByPrimaryId(Long id, String type) {
		return dictionaryMapper.selectByPrimaryId(id,type);
	}

	public PlatformDictionary selectByPrimary(Long id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

}
