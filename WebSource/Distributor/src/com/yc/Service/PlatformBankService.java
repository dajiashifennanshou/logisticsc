package com.yc.Service; 
import java.math.BigInteger;

import com.yc.Entity.PlatformBank; 
/** 
* LcPlatformBank服务接口层 
* Auther:FENG 
*/ 
public interface PlatformBankService{  
	/**
	 * 保存绑定银行卡信息
	 * @Author:luojing
	 * @2016年7月23日 下午1:08:46
	 */
	public Integer addSingleInfo(PlatformBank bank);
	
	/**
	 * 修改绑定银行卡信息
	 * @Author:luojing
	 * @2016年7月23日 下午1:08:46
	 */
	public Integer updateSingleInfo(PlatformBank bank);
	
	/**
	 * 查询绑定银行卡信息
	 * @Author:luojing
	 * @2016年8月11日 上午11:37:47
	 */
	public PlatformBank getPlatformBank(BigInteger userId);
}
