package com.yc.Dao; 
import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.YcEmployee;
import com.yc.Tool.ISqlDao; 
/** 
* YcEmployee数据层 
* Author:luojing 
*/ 
public interface IYcEmployeeDao extends ISqlDao<YcEmployee> {  

	/**
	 * 员工信息集合查询
	 * Author:luojing
	 * 2016年6月14日 下午4:46:41
	 */
	public List<YcEmployee> getEmployee(YcEmployee ye);
	
	/**
	 * 多ID查询 
	 * Author:luojing
	 * 2016年6月14日 上午10:28:13
	 */
	public List<YcEmployee> getYcEmployeeInId(List<BigInteger> list);
	/**
	 * 获取司机信息
	 * Author:FENG
	 * 2016年6月20日
	 * @param deliveryNo
	 * @return
	 */
	public List<YcEmployee> getDriverInfoByDNo(String deliveryNo);
	/**
	 * 获取安装工信息
	 * Author:FENG
	 * 2016年6月20日
	 * @param deliveryNo
	 * @return
	 */
	public List<YcEmployee> getInstallerInfoByDNo(String deliveryNo);
	
	/**
	 * 根据配送单编号去配载单配送单关联表里面查询出安装工id并修改此员工的状态
	 * Author:FENG
	 * 2016年6月24日
	 * @param status
	 * @param deliveryNo
	 * @return
	 */
	public Integer modUseStatusByOrderNo(Integer status,String deliveryNo);
	
	/**
	 * 根据车牌号获取司机信息
	 * Author:FENG
	 * 2016年6月24日
	 * @return
	 */
	public List<YcEmployee> getEmployeeByCarNo(String carNo);
}
