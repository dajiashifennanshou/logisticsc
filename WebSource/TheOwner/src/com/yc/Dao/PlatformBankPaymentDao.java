package com.yc.Dao; 
import com.yc.Entity.PlatformBankPayment; 
import com.yc.Tool.ISqlDao; 
/** 
* lc_platform_bank_payment数据层 
* Auther:FENG 
*/ 
public interface PlatformBankPaymentDao extends ISqlDao<PlatformBankPayment> { 
	
	/**
	 * 查询支付状态
	 * @Author:luojing
	 * @2016年8月24日 下午5:19:35
	 */
	PlatformBankPayment getPayState(PlatformBankPayment bankPayment);

}
