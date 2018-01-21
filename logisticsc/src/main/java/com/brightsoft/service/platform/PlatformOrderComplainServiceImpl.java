package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderComplainMapper;
import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.PlatformOrderMineComplain;
import com.brightsoft.model.User;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service
public class PlatformOrderComplainServiceImpl {

	@Autowired
	private PlatformOrderComplainMapper platformOrderComplainMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	/**
	 * 查询投诉投诉信息
	 * @param user
	 * @param platformOrderComplain
	 * @param page
	 * @return
	 */
	public Result selectByCondition(User user,PlatformOrderComplain platformOrderComplain, Page<?> page) {
		Result result = new Result();
		int results = platformOrderComplainMapper.countRows(platformOrderComplain,user);
		List<PlatformOrderComplain> list = platformOrderComplainMapper.selectByCondition(page,platformOrderComplain,user);
		result.setRows(list);
		result.setResults(results);
		return result;
	}
	
	/**
	 * 投诉处理
	 * @return
	 */
	public int update2handle(PlatformOrderComplain platformOrderComplain){
		return platformOrderComplainMapper.updateByPrimaryKeySelective(platformOrderComplain);
	}
	
	/**
	 * 查询投诉投诉信息
	 * @param user
	 * @param platformOrderComplain
	 * @param page
	 * @return
	 */
	public Page<?> selectByConditionPlatfrom(PlatformOrderMineComplain platformOrderComplain , Page<?> page) {
		int results = platformOrderComplainMapper.countRowsPlatfrom(platformOrderComplain);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformOrderMineComplain> list = platformOrderComplainMapper.selectByConditionPlatfrom(platformOrderComplain, page);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setStartProvince(xzqhServiceImpl.selectValueById(list.get(i).getStartProvince()).getName());
			list.get(i).setStartCity(xzqhServiceImpl.selectValueById(list.get(i).getStartCity()).getName());
			list.get(i).setStartCounty(xzqhServiceImpl.selectValueById(list.get(i).getStartCounty()).getName());
			list.get(i).setEndProvince(xzqhServiceImpl.selectValueById(list.get(i).getEndProvince()).getName());
			list.get(i).setEndCity(xzqhServiceImpl.selectValueById(list.get(i).getEndCity()).getName());
			list.get(i).setEndCounty(xzqhServiceImpl.selectValueById(list.get(i).getEndCounty()).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, list);
		page.setParams(map);
		return page;
	}
	
	/**
	 * 增加投诉
	 * @return
	 */
	public boolean insertComplain(PlatformOrderComplain complain){
		complain.setComplaintTime(new Date());
		complain.setState(Const.PLATFORMUSER_ORDER_COMPLAIN_STATE_NOT_REPLIED);
		if(platformOrderComplainMapper.insertComplain(complain) > 0){
			return true;
		}
		return false;
	}
	
	public PlatformOrderComplain selectByPrimaryKey(Long id){
		return platformOrderComplainMapper.selectByPrimaryKey(id);
	}
	
	
	
	public Result selectByConditionPlatfromList(PlatformOrderMineComplain platformOrderComplain , Page<?> page) {
		Result result = new Result();
		List<PlatformOrderMineComplain> list = platformOrderComplainMapper.selectByConditionPlatfromList(platformOrderComplain, page);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setStartProvince(xzqhServiceImpl.selectValueById(list.get(i).getStartProvince()).getName());
			list.get(i).setStartCity(xzqhServiceImpl.selectValueById(list.get(i).getStartCity()).getName());
			list.get(i).setStartCounty(xzqhServiceImpl.selectValueById(list.get(i).getStartCounty()).getName());
			list.get(i).setEndProvince(xzqhServiceImpl.selectValueById(list.get(i).getEndProvince()).getName());
			list.get(i).setEndCity(xzqhServiceImpl.selectValueById(list.get(i).getEndCity()).getName());
			list.get(i).setEndCounty(xzqhServiceImpl.selectValueById(list.get(i).getEndCounty()).getName());
		}
		int results = platformOrderComplainMapper.countRowsPlatfromList(platformOrderComplain);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
}
