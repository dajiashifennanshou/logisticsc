package com.brightsoft.service.system.platform;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.TakeCashStatusEnum;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.dao.tms.TakeCashApplyMapper;
import com.brightsoft.model.TakeCashApply;
import com.brightsoft.utils.Result;

/**
 * 提现申请 service
 * @author yangshoubao
 *
 */
@Service
public class TakeCashApplyService {

	@Autowired
	private TakeCashApplyMapper takeCashApplyMapper;
	
	/**
	 * 保存 提现申请
	 * @param takeCashApply
	 * @return
	 */
	public int save(TakeCashApply takeCashApply){
		takeCashApply.setApplyTime(new Date());
		takeCashApply.setStatus(TakeCashStatusEnum.AUDITING.getValue());
		return takeCashApplyMapper.insert(takeCashApply);
	}
	
	/**
	 * 查询 提现申请列表
	 * @param params
	 * @return
	 */
	public Result selectByParams(BaseSearchParams params){
		List<TakeCashApply> takeCashApplies = takeCashApplyMapper.selectByParams(params);
		if(takeCashApplies != null && takeCashApplies.size() > 0){
			for (TakeCashApply takeCashApply : takeCashApplies) {
				TakeCashStatusEnum[] takeCashStatusEnums = TakeCashStatusEnum.values();
				for (TakeCashStatusEnum takeCashStatusEnum : takeCashStatusEnums) {
					if(takeCashApply.getStatus() == takeCashStatusEnum.getValue()){
						takeCashApply.setStatusName(takeCashStatusEnum.getName());
						continue;
					}
				}
			}
		}
		int count = takeCashApplyMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(takeCashApplies);
		return result;
	}
	
	/**
	 * 保存 提现审核
	 * @param takeCashApply
	 */
	public int saveTakeCashAudit(TakeCashApply takeCashApply){
		return takeCashApplyMapper.updateByPrimaryKeySelective(takeCashApply);
	}
}
