package com.brightsoft.service.platform;

import com.brightsoft.model.PlatformCollectionPayment;
import com.brightsoft.model.PlatformOrderAgencyFund;
import com.brightsoft.utils.Page;

public interface PlatformOrderAgencyFundService {
	
	/**
	 *分页获取代收货款
	 */
	public Page<?> selectBySelectedCondition(PlatformCollectionPayment collectionPayment, Page<?> page);
	/**
	 * 增加代收货款
	 * @param agencyFund
	 * @return
	 */
	public boolean inserOrderAgencyFund(PlatformOrderAgencyFund agencyFund);
}
