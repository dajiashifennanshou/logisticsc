package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.ConsumeTypeEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.ConsumeRecordSearchParams;
import com.brightsoft.dao.platform.PlatformUserBankCardMapper;
import com.brightsoft.dao.platform.PlatformUserConsumeRecordMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserBankCard;
import com.brightsoft.model.PlatformUserConsumeRecord;
import com.brightsoft.utils.CodeImageUtil;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service("platformUserConsumeRecord")
public class PlatformUserConsumeRecordServiceImpl implements PlatformUserConsumeRecordService{

	@Autowired
	public PlatformUserConsumeRecordMapper consumeRecordMapper;
	
	@Autowired
	public PlatformUserBankCardMapper bankCardMapper;
	
	@Autowired
	public PlatformUserMapper platformUserMapper;
	
	/**
	 * 转账
	 */
	public PlatformUser adduserTransferAccounts(Double money, String loginName,PlatformUser user) {
		PlatformUser platformUser = null;
		PlatformUserConsumeRecord consumeRecord = new PlatformUserConsumeRecord();
		platformUser = platformUserMapper.selectUserloginName(loginName);
		if(null == platformUser){
			return null;
		}else{
			if(platformUser.getId() == user.getId()){
				return user;
			}else{
					if(platformUserMapper.updateUserBalance(user.getBalance()-money,user.getId()) > 0){
						if(platformUserMapper.updateUserBalance(platformUser.getBalance()+money,platformUser.getId()) > 0){
							consumeRecord.setConsumeTime(new Date());
							consumeRecord.setMoney(money);
							consumeRecord.setConsumeCard("余额支付");
							consumeRecord.setConsumeType(ConsumeTypeEnum.TRANSFER_ACCOUNT.getValue()+"");
							consumeRecord.setChargeCard("余额到账");
							consumeRecord.setConsumeUser(user.getLoginName());
							String codeNumber = CodeImageUtil.createCodeNumber();
							consumeRecord.setOrderNumber(codeNumber);
							consumeRecord.setChargeUser(platformUser.getLoginName());
							consumeRecord.setUserId(user.getId());
							if(consumeRecordMapper.insertConsumeRecord(consumeRecord) > 0){
								user = platformUserMapper.selectUserId(user.getId());
								if(user != null ){
									return user;
								}else {
									return null;
								}
							}	
						}
					}
				}
		}
		return null;
	}
	/**
	 * 增加我的消费记录---充值
	 */
	public PlatformUser addUserRecharge(Double money, Long id, PlatformUser user) {
		PlatformUserConsumeRecord consumeRecord = new PlatformUserConsumeRecord();
		PlatformUser platUser = null;
		PlatformUserBankCard bankCard = null;
		bankCard= bankCardMapper.selectUserBanCard(id,user.getId());
		if(null != bankCard){
			if(platformUserMapper.updateUserBalance(money+user.getBalance(),user.getId()) > 0){
				consumeRecord.setConsumeTime(new Date());
				consumeRecord.setMoney(money);
				consumeRecord.setConsumeCard(bankCard.getCardNo());
				consumeRecord.setConsumeType(ConsumeTypeEnum.RECHARGE.getValue()+"");
				consumeRecord.setChargeCard("占时没定");
				consumeRecord.setConsumeUser(bankCard.getName());
				String codeNumber = CodeImageUtil.createCodeNumber();
				consumeRecord.setOrderNumber(codeNumber);
				consumeRecord.setChargeUser("货运交易系统");
				consumeRecord.setUserId(user.getId());
				if(consumeRecordMapper.insertConsumeRecord(consumeRecord) > 0){
					platUser = platformUserMapper.selectUserId(user.getId());
					if(platUser != null ){
						return platUser;
					}else {
						return null;
					}
				}
			}
		}
		return null;
	}
	/**
	 * 增加我的消费记录--提现
	 */
	public PlatformUser addUserWithdrawals(Double money, Long id,
			PlatformUser user) {
		PlatformUserConsumeRecord consumeRecord = new PlatformUserConsumeRecord();
		PlatformUser platUser = null;
		PlatformUserBankCard bankCard = null;
		bankCard= bankCardMapper.selectUserBanCard(id,user.getId());
		if(null != bankCard){
			if(platformUserMapper.updateUserBalance(user.getBalance()-money,user.getId()) > 0){
				consumeRecord.setConsumeTime(new Date());
				consumeRecord.setMoney(money);
				consumeRecord.setConsumeCard("货运交易系统");
				consumeRecord.setConsumeUser(user.getLoginName());
				consumeRecord.setConsumeType(ConsumeTypeEnum.TAKE_CASH.getValue()+"");
				consumeRecord.setChargeCard(bankCard.getCardNo());
				String codeNumber = CodeImageUtil.createCodeNumber();
				consumeRecord.setOrderNumber(codeNumber);
				consumeRecord.setChargeUser("货运交易系统");
				consumeRecord.setUserId(user.getId());
				if(consumeRecordMapper.insertConsumeRecord(consumeRecord) > 0){
					platUser = platformUserMapper.selectUserId(user.getId());
					if(platUser != null ){
						return platUser;
					}else {
						return null;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 我的消费记录
	 */
	public Page<?> selectBySelectedCondition(Long userId, String consumeType,
			Page<?> page) {
		int results = consumeRecordMapper.countRows(userId,consumeType);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformUserConsumeRecord> consumeRecords = consumeRecordMapper.selectBySelectedCondition(page, userId, consumeType);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, consumeRecords);
		page.setParams(map);
		return page;
	}
	
	/**
	 * 获取保险消费记录
	 */
	public Result selectInsByParams(SearchParams searchParams, Page<?> page) {
		Result result = new Result();
		int results = consumeRecordMapper.countRows4InsByParams(searchParams,ConsumeTypeEnum.INSURE.getValue());
		List<PlatformUserConsumeRecord> rows = consumeRecordMapper.selectInsRecByParams(searchParams,ConsumeTypeEnum.INSURE.getValue(), page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}

	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public Result selectByParams(ConsumeRecordSearchParams params){
		List<PlatformUserConsumeRecord> consumeRecords = consumeRecordMapper.selectByParams(params);
		int count = consumeRecordMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(consumeRecords);
		return result;
	}


}
