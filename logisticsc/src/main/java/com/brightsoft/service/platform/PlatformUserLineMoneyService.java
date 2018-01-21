package com.brightsoft.service.platform;
import com.brightsoft.model.PlatformLineStorage;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserLineMoney;
import com.brightsoft.utils.Page;

public interface PlatformUserLineMoneyService {
	
	/**
	 * 分页获取用户路线预充值
	 */
	public Page<?> selectBySelectedCondition(PlatformLineStorage lineStorage, Page<?> page);
	/**
	 * 线路预充值
	 * @param consumeRecord
	 * @return
	 */
	public PlatformUser doUserLineConsumeRecord(Long companyId,Long lineId,Long moneyId,PlatformUser user,double money);
	/**
	 * 线路充值 --新线路
	 * @param lineMoney
	 * @param user
	 * @return
	 */
	public PlatformUser insertUserLineMoney(PlatformUserLineMoney lineMoney,PlatformUser user);
	
	/**
	 * 获取当前用户是否存在线路充值信息
	 * @param lineId
	 * @param userId
	 * @return
	 */
	public PlatformUserLineMoney selectLineId(Long lineId,Long userId);
}
