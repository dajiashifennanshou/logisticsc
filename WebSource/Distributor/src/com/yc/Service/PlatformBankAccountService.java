package com.yc.Service;

import com.yc.Entity.PlatformBankAccount;

/** 
* lc_platform_bank_account服务接口层 
* Auther:FENG 
*/ 
public interface PlatformBankAccountService {  

	/**
	 * 银行子账户注册 
	 * @Author:luojing
	 * @2016年8月11日 上午10:10:16
	 */
	public Integer addBankAccount(PlatformBankAccount ba);
	
	/**
	 * 银行子账户修改
	 * @Author:luojing
	 * @2016年8月11日 上午10:11:30
	 */
	public Integer updateBankAccount(PlatformBankAccount ba);
	
	/**
	 * 银行子账户查询
	 * @Author:luojing
	 * @2016年8月11日 上午10:11:54
	 */
	public PlatformBankAccount getBankAccount(PlatformBankAccount ba);
}
