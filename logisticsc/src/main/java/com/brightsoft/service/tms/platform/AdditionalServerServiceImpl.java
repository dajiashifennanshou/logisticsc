package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.dao.tms.AdditionalServerConfMapper;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.utils.Result;

/**
 * 
 * 系统设置 - 增值服务管理service
 */
@Service
public class AdditionalServerServiceImpl {

	@Autowired
	private AdditionalServerConfMapper additionalServerConfMapper;
	
	/**
	 * 根据公司ID查询增值服务配置信息
	 * @param companyId
	 * @return
	 */
	/*public AdditionalServerConf selectByCompanyId(Long companyId){
		return additionalServerConfMapper.selectByCompanyId(companyId);
	}*/
	
	/**
	 * 根据网点ID查询增值服务配置信息
	 * @param outletsId
	 * @return
	 */
	public AdditionalServerConf selectByOutletsId(Long outletsId){
		return additionalServerConfMapper.selectByOutletsId(outletsId);
	}
	
	/**
	 * 根据线路ID查询增值服务配置信息
	 * @param companyId
	 * @return
	 */
	public AdditionalServerConf selectByLineId(Long lineId){
		return additionalServerConfMapper.selectByLineId(lineId);
	}
	
	/**
	 * 保存增值服务信息
	 */
	public int save(AdditionalServerConf additionalServer){
		Long id = additionalServer.getId();
		if(id == null){
			return additionalServerConfMapper.insert(additionalServer);
		}else{
			return additionalServerConfMapper.updateByPrimaryKey(additionalServer);
		}
	}
	
	/**
	 * 获取增值服务信息列表 (货运交易系统)
	 * @param params
	 * @return
	 */
	public Result selectByParamsOfPlatform(BaseSearchParams params){
		List<AdditionalServerConf> additionalServerConfs = additionalServerConfMapper.selectByParamsOfPlatform(params);
		int count = additionalServerConfMapper.selectByParamsOfPlatformCount(params);
		Result result = new Result();
		result.setRows(additionalServerConfs);
		result.setResults(count);
		return result;
	}
}
