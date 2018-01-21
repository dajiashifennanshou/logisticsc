package com.brightsoft.service.tms.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.tms.AdditionalServerConfMapper;
import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class LineInfoService{

	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private AdditionalServerConfMapper additionalServerConfMapper;
	
	@Autowired
	private PlatformOrderMapper platformOrderMapper;
	
	/**
	 * 网点获取线路信息
	 */
	public Result selectByCondition(User user,LineInfo lineInfo, Page<?> page) {
		Result result = new Result();
		int results = lineInfoMapper.countRows(lineInfo, user);
		List<LineInfo> lineInfos = lineInfoMapper.selectByCondition(lineInfo, page, user);
		result.setRows(lineInfos);
		result.setResults(results);
		return result;
	}
	
	/**
	 * 网点获取线路信息
	 */
	public Result selectByOutletsIdCondition(Long outletsId,LineInfo lineInfo, Page<?> page) {

		Result result = new Result();
		int results = lineInfoMapper.countRowsByOutletsId(lineInfo, outletsId);
		List<LineInfo> lineInfos = lineInfoMapper.selectByOutletsIdCondition(lineInfo, page, outletsId);
		result.setRows(lineInfos);
		result.setResults(results);
		return result;
	}
	
	/**
	 * 专线获取线路信息
	 */
	public Result selectByCompanyIdCondition(Long companyId,LineInfo lineInfo, Page<?> page) {

		Result result = new Result();
		int results = lineInfoMapper.countRowsByCompanyId(lineInfo, companyId);
		List<LineInfo> lineInfos = lineInfoMapper.selectByCompanyIdCondition(lineInfo, page,companyId);
		result.setRows(lineInfos);
		result.setResults(results);
		return result;
	}

	/**
	 * 添加线路 信息
	 */
	public Result insertLineInfo(User user,LineInfo lineInfo) {	
		Result result = new Result();
		if(user.getOutletsId() == null){
			lineInfo.setRecommended(1);
			if(lineInfoMapper.insert(lineInfo)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("当前角色没有添加线路的权限！");
		}
		// 判断 发站网点是否设置 增值服务数据，如果没有设置，则设置默认的增值服务数据
		AdditionalServerConf additionalServerConf = additionalServerConfMapper.selectByOutletsId(lineInfo.getStartOutlets());
		if(additionalServerConf == null){
			additionalServerConf = new AdditionalServerConf();
			additionalServerConf.setCompanyId(user.getCompanyId());
			additionalServerConf.setOutletsId(lineInfo.getStartOutlets());
			additionalServerConf.setIsReceipt(1);
			additionalServerConf.setIsArrivePay(1);
			additionalServerConf.setIsNoReceipt(1);
			additionalServerConfMapper.insertSelective(additionalServerConf);
		}
		return result;
	}

	/**
	 * 更新线路信息
	 */
	public Result updateLineInfo(User user,LineInfo lineInfo) {
		Result result = new Result();
		if(user.getOutletsId() == null){
			if(lineInfoMapper.updateByPrimaryKeySelective(lineInfo)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("当前角色没有添加线路的权限！");
		}
		return result;
	}

	/**
	 * 删除线路
	 */
	public Map<String, Object> deleteLine(User user,Long lineId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(user != null && user.getOutletsId() == null){
			int countOrders = platformOrderMapper.countRowsByLine(lineId);
			if(countOrders == 0){
				if(lineInfoMapper.deleteByPrimaryKey(lineId)>0){
					map.put("success", true);
				};
			}else{
				map.put("success", false);
				map.put("msg", "当前线路不能删除！");
			}
		}else{
			map.put("success", false);
			map.put("msg","当前角色没有删除线路的权限！");
		}
		return map;
	}

	public LineInfo selectLineInfoById(long lineId){
		return lineInfoMapper.selectByPrimaryKey(lineId);
	}
	
	/**
	 * 通过线路创建人获取 线路信息
	 * @param createPersonId
	 * @return
	 */
	public List<LineInfo> selectByCreatePersonId(long createPersonId){
		return lineInfoMapper.selectByCreatePersonId(createPersonId);
	}

	/**
	 * 通过网点id获取线路信息
	 * @param outletsId
	 * @return
	 */
	public List<LineInfo> selectByOutletsId(long outletsId){
		
		OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsById(outletsId);
		List<LineInfo> lineInfos = lineInfoMapper.selectByOutletsId(outletsId);
		for (LineInfo lineInfo : lineInfos) {
			lineInfo.setContactPerson(outletsInfo.getContactPerson());
			lineInfo.setMobile(outletsInfo.getMobile());
			lineInfo.setPhone(outletsInfo.getPhone());
			lineInfo.setEmail(outletsInfo.getEmail());
			lineInfo.setLineName(lineInfo.getStartOutletsName() + "-" + lineInfo.getEndOutletsName());
		}
		return lineInfos;
	}
	
	/**
	 * 查询 线路列表
	 * @param params
	 * @return
	 */
	public Result selectByParams(BaseSearchParams params){
		OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsById(params.getOutletsId());
		List<LineInfo> lineInfos = lineInfoMapper.selectByParams(params);
		for (LineInfo lineInfo : lineInfos) {
			lineInfo.setContactPerson(outletsInfo.getContactPerson());
			lineInfo.setMobile(outletsInfo.getMobile());
			lineInfo.setPhone(outletsInfo.getPhone());
			lineInfo.setEmail(outletsInfo.getEmail());
			lineInfo.setLineName(lineInfo.getStartOutletsName() + "-" + lineInfo.getEndOutletsName());
		}
		int count = lineInfoMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(lineInfos);
		return result;
	}
	
	/**
	 * 通过主键ID 查询线路信息
	 * @param id
	 * @return
	 */
	public LineInfo selectByPrimaryKey(long id){
		return lineInfoMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 货运交易系统
	 * 获取线路信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectLineItems(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = lineInfoMapper.countRows4LineItems(searchParams);
		List<LineInfo> rows = lineInfoMapper.selectLineItems(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}

}
