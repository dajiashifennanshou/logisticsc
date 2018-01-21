package com.yc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformDictionaryMapper;
import com.yc.Entity.PlatformDictionary;
import com.yc.Service.PlatformDictionaryService;
@Service
public class PlatformDictionaryServiceImpl implements PlatformDictionaryService {
	@Autowired
	private PlatformDictionaryMapper dictionaryDao;

	@Override
	public List<PlatformDictionary> selectDictDataByType(String type) {
		// TODO Auto-generated method stub
		return dictionaryDao.selectDictDataByType(type);
	}

}
