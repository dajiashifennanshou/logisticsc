package com.brightsoft.service.platform;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.ConsumeRecordSearchParams;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

public interface PlatformUserConsumeRecordService {

	/**
	 * 分页获我的消费记录
	 */
	public Page<?> selectBySelectedCondition(Long userId,String consumeType, Page<?> page);
	

	/**
	 * 获取保单消费记录
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectInsByParams(SearchParams searchParams,Page<?> page);
	
	public Result selectByParams(ConsumeRecordSearchParams params);
	/**
	 * 充值
	 * @param money
	 * @param id
	 * @param user
	 * @return
	 */
	public PlatformUser addUserRecharge(Double money,Long id,PlatformUser user);
	/**
	 * 提现
	 */
	public PlatformUser addUserWithdrawals(Double money,Long id,PlatformUser user);
	/**
	 * 转账
	 * @param money
	 * @param loginName
	 * @return
	 */
	public PlatformUser adduserTransferAccounts(Double money,String loginName,PlatformUser user);
}
