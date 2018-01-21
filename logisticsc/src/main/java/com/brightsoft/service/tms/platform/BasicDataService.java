package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.dao.tms.BasicDataMapper;
import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.model.BasicData;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.utils.Result;

/**
 * TMS 系统设置 - 基础数据service
 * @author Tasty
 *
 */
@Service
public class BasicDataService {

	@Autowired
	private BasicDataMapper basicDataMapper;
	
	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	/**
	 * 保存 基础数据
	 * @param basicData
	 * @return
	 */
	public int saveBasicData(BasicData basicData){
		if(basicData.getId() == null){
			return basicDataMapper.insertSelective(basicData);
		}else{
			return basicDataMapper.updateByPrimaryKey(basicData);
		}
	}
	
	/**
	 * 查询 基础数据 信息
	 * @param companyId
	 * @return
	 */
	public BasicData selectByCompanyId(Long companyId){
		return basicDataMapper.selectByCompanyId(companyId);
	}
	
	/**
	 * 查询 基础数据列表 (货运交易系统)
	 * @param params
	 * @return
	 */
	public Result selectByParamsOfPlatform(PlatformBaseSearchParams params){
		List<BasicData> basicDatas = basicDataMapper.selectByParamsOfPlatform(params);
		int count = basicDataMapper.selectByParamsCountOfPlatform(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(basicDatas);
		return result;
	}
	
	/**
	 * 根据线路 查询物流商基础数据配置
	 * @param lineId
	 * @return
	 */
	public BasicData selectByLineId(Long lineId){
		LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(lineId);
		OutletsInfo outletsInfo = outletsInfoMapper.selectByPrimaryKey(lineInfo.getStartOutlets());
		return basicDataMapper.selectByCompanyId(outletsInfo.getCompanyId());
	}
}
