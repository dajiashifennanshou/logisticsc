package com.brightsoft.service.tms.platform;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.DepositRatioStatusEnum;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.dao.tms.DepositRatioMapper;
import com.brightsoft.model.DepositRatio;
import com.brightsoft.model.User;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class DepositRatioService {

	@Autowired
	private DepositRatioMapper depositRatioMapper;
	
	/**
	 * 网点获取线路费率信息
	 * @param page
	 * @param depositRatio
	 * @param outletsId
	 * @return
	 */
	public Result selectByOutletsIdAndCondition(Page<?> page,DepositRatio depositRatio,Long outletsId){
		Result result = new Result();
		int results = depositRatioMapper.countRowsByOutletsId(depositRatio, outletsId);
		List<DepositRatio> list = depositRatioMapper.selectByOutletsIdAndCondition(depositRatio, page, outletsId);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	/**
	 * 专线获取线路费率信息
	 * @param page
	 * @param depositRatio
	 * @param companyId
	 * @return
	 */
	public Result selectByCompanyIdAndCondition(Page<?> page,DepositRatio depositRatio,Long companyId){
		Result result = new Result();
		int results = depositRatioMapper.countRowsByCompanyId(depositRatio, companyId);
		List<DepositRatio> list = depositRatioMapper.selectByCompanyIdAndCondition(depositRatio, page, companyId);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	/**
	 * 查询预存费信息
	 * @param page
	 * @param depositRatio
	 * @param user
	 * @return
	 */
	public Result selectByCondition(Page<?> page,DepositRatio depositRatio,User user){
		Result result = new Result();
		int results = depositRatioMapper.countRows(depositRatio,user);
		List<DepositRatio> list = depositRatioMapper.selectByCondition(page, depositRatio, user);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	
	/**
	 * 添加预存费信息
	 * @param depositRatio
	 * @return
	 */
	public int insert(DepositRatio depositRatio){
		return depositRatioMapper.insert(depositRatio);
	}
	/**
	 * 更新线路费率信息
	 * @param depositRatio
	 * @return
	 */
	public boolean update(DepositRatio depositRatio){
		depositRatio.setCreateTime(DateTools.getYMDHMS());
		if(depositRatioMapper.updateByPrimaryKeySelective(depositRatio)>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 批量删除
	 * @param depositIds
	 * @return
	 */
	public Boolean deleteBatch(String[] depositIds){
		boolean flag = false;
		if(depositIds!=null
				&&depositIds.length>0){
			List<Long> ids = new ArrayList<Long>();
			for (String depositId : depositIds) {
				if(StringUtils.isNotBlank(depositId)){
					ids.add(Long.parseLong(depositId));
				}
			}
			if(depositRatioMapper.deleteBatch(ids)>0){
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 信息发布
	 * @param depositIds
	 * @return
	 */
	public Boolean update2Publish(String[] depositIds){
		boolean flag = false;
		if(depositIds!=null
				&&depositIds.length>0){
			List<DepositRatio> list = new ArrayList<DepositRatio>();
			for (String depositId : depositIds) {
				if(StringUtils.isNotBlank(depositId)){
					DepositRatio depositRatio = new DepositRatio();
					depositRatio.setId(Long.parseLong(depositId));
					depositRatio.setStatus(DepositRatioStatusEnum.AUDITING.getValue());
					list.add(depositRatio);
				}
			}
			if(depositRatioMapper.audit(list)>0){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 查询 线路预存运费比例设置(货运交易系统)
	 * @param params
	 * @return
	 */
	public Result selectByParams(BaseSearchParams params){
		List<DepositRatio> depositRatios = depositRatioMapper.selectByParams(params);
		int count = depositRatioMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(depositRatios);
		return result;
	}
	
	/**
	 * 查询当前正在进行的活动
	 * @return
	 */
	public DepositRatio selectCurrentDeposit(){
		return depositRatioMapper.selectCurrentDeposit();
	}
}
