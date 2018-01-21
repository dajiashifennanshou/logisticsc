package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.SysConfigMapper;
import com.brightsoft.model.SysConfig;
import com.brightsoft.utils.Result;

@Service
public class SysConfigServiceImpl {

	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	public SysConfig selectByItmeAbbr(String itemAbbr){
		return sysConfigMapper.getSysConfigByAbbr(itemAbbr);
	}
	
	public Result selectInsNote(){
		Result result = new Result();
		int results = sysConfigMapper.getSysConfigCount();
		List<SysConfig> rows = sysConfigMapper.getSysConfig();
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	public boolean updateInsNote(SysConfig sysConfig){
		boolean flag = false;
		if(sysConfigMapper.updateByPrimaryKeySelective(sysConfig)>0){
			flag = true;
		}
		return flag;
	}
}

