package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.dao.tms.ShortMessageMapper;
import com.brightsoft.model.ShortMessage;
import com.brightsoft.utils.Result;

/**
 * 短信设置 service
 * @author yangshoubao
 *
 */
@Service
public class ShortMessageService {

	@Autowired
	private ShortMessageMapper shortMessageMapper;
	
	/**
	 * 查询 短息列表
	 * @param params
	 * @return
	 */
	public Result selectByParams(BaseSearchParams params){
		List<ShortMessage> shortMessages = shortMessageMapper.selectByParams(params);
		int count = shortMessageMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(shortMessages);
		return result;
	}
	
	/**
	 * 保存 短信设置
	 * @param shortMessage
	 * @return
	 */
	public int saveShortMessage(ShortMessage shortMessage){
		if(shortMessage.getId() == null){
			return shortMessageMapper.insert(shortMessage);
		}else{
			return shortMessageMapper.updateByPrimaryKey(shortMessage);
		}
	}
	
	/**
	 * 删除 短信设置
	 * @param ids
	 * @return
	 */
	public int deleteShortMessage(String ids){
		int rows = 0;
		List<String> idArr = JSONArray.parseArray(ids, String.class);
		for (String id : idArr) {
			rows += shortMessageMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		return rows;
	}
	
	/**
	 * 查询 短信列表 (货运交易系统)
	 * @param params
	 * @return
	 */
	public Result selectByParamsOfPlatform(PlatformBaseSearchParams params){
		List<ShortMessage> shortMessages = shortMessageMapper.selectByParamsOfPlatform(params);
		int count = shortMessageMapper.selectByParamsCountOfPlatform(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(shortMessages);
		return result;
	}
}
