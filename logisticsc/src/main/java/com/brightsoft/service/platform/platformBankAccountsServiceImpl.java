package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankAccountsMapper;
import com.brightsoft.model.platformBankAccounts;
import com.brightsoft.model.platformVoSplitPos;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class platformBankAccountsServiceImpl {
	
	@Autowired
	private platformBankAccountsMapper accountsMapper;
	
	public Result selectBankPos(platformVoSplitPos splitPos,Page<?> page){
		Result result = new Result();
		List<platformVoSplitPos> platformVoSplitPos = accountsMapper.selectBankPos(splitPos, page);
		int results = accountsMapper.countBankPos(splitPos);
		result.setResults(results);
		result.setRows(platformVoSplitPos);
		return result;
	}
	/**
	 * 转账记录
	 * @param splitPos
	 * @param page
	 * @return
	 */
	public Result selectBankPosRecord(platformVoSplitPos splitPos,Page<?> page){
		Result result = new Result();
		List<platformVoSplitPos> platformVoSplitPos = accountsMapper.selectBankPosRecord(splitPos, page);
		int results = accountsMapper.countBankPosRecord(splitPos);
		result.setResults(results);
		result.setRows(platformVoSplitPos);
		return result;
	}
	
	public boolean insertBankPos(platformBankAccounts accounts){
		if(accountsMapper.insertSelective(accounts)>0){
			return true;
		}
		return false;
	}
}
