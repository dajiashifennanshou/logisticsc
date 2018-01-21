package com.yc.Service;

import com.yc.Entity.PlatformBankPayment;

/** 
* lc_platform_bank_payment服务接口层 
* Auther:FENG 
*/ 
public interface PlatformBankPaymentService {  

	/**
	 * 添加支付记录
	 * @Author:luojing
	 * @2016年8月9日 下午5:52:13
	 */
	public Integer addBankPayment(PlatformBankPayment bp);
	
	/**
	 * 查询支付记录
	 * @Author:luojing
	 * @2016年8月10日 上午10:59:29
	 */
	public PlatformBankPayment getBankPayment(PlatformBankPayment bp);
	
	/**
	 * 修改支付记录状态
	 * @Author:luojing
	 * @2016年8月10日 上午11:12:38
	 */
	public Integer updateBankPayment(PlatformBankPayment bp);
}
