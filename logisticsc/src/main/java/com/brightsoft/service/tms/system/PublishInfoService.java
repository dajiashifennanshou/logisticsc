package com.brightsoft.service.tms.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.PublishInfoMapper;
import com.brightsoft.model.PublishInfo;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class PublishInfoService {

	@Autowired
	private PublishInfoMapper publishInfoMapper;
	
	/**
	 * 查询所有发布信息
	 * 
	 * @param page
	 * @param publishInfo
	 * @return
	 */
	public Result selectByCondition(SearchParams searchParams,Page<?> page,Integer publishType){
		Result result = new Result();
		int Results = publishInfoMapper.countRows(searchParams,publishType);
		List<PublishInfo> rows = publishInfoMapper.selectAll(searchParams, page, publishType);
		result.setResults(Results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 通过类型查询发布信息
	 * @param searchParams
	 * @param page
	 * @param type
	 * @return
	 */
	public Result selectByTypeAndCondition(SearchParams searchParams,Page<?> page,Integer type){
		Result result = new Result();
		int Results = publishInfoMapper.countRowsByType(searchParams, page, type);
		List<PublishInfo> rows = publishInfoMapper.selectByTypeAndCondition(searchParams, page, type);
		result.setResults(Results);
		result.setRows(rows);
		return result;
	}
	
	public List<PublishInfo> select4Platform(){
		return publishInfoMapper.selectByType(Const.INFO_PUBLISH_TYPE_PLATFORM);
	}
	
	/**
	 * 新增信息
	 * @return
	 */
	public int insert(PublishInfo publishInfo){
		return publishInfoMapper.insert(publishInfo);
	}
	
	/**
	 * 删除信息
	 * @param publishInfo
	 * @return
	 */
	public int update2Delete(List<Long> list){
		return publishInfoMapper.updateBatch4Delete(list);
	}

	/**
	 * 查询货运交易系统类型的所有信息
	 * 2016年3月22日 下午2:35:30
	 * @param page
	 * @param publishInfo
	 * @return 
	 * @author zhouna
	 */
	public Result findAllByType(Page<?> page,PublishInfo publishInfo) {
		Result result = new Result();
		try {
			int Results = publishInfoMapper.counttypeRows();
			List<PublishInfo> rows = publishInfoMapper.findAllByType(publishInfo, page);
			result.setResults(Results);
			result.setRows(rows);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}

}
