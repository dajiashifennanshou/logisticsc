package com.yc.Dao; 
import java.math.BigInteger;

import com.yc.Entity.PlatformOrder; 
import com.yc.Tool.ISqlDao; 
/** 
* LcPlatformOrder数据层 
* Auther:FENG 
*/ 
public interface PlatformOrderDao extends ISqlDao<PlatformOrder> {  

	Integer getNextId();
}
