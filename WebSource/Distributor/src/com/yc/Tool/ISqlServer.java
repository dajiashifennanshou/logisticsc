package com.yc.Tool;

/**
 * 公共的sql方法接口，继承了此类后，可以实现常用的方法无需自己填写
 * 缺点是每个serviceIMPl类都必须实现这里的所有方法
 * Author:FENG
 * 2016年5月11日
 */
public abstract interface ISqlServer<T> {
	
	/**
	 * 根据条件获取单行数据
	 * Author:FENG
	 * 2016年5月11日
	 * @return
	 */
	public T getSingleInfo(T t);
	/**
	 * 获取总行数
	 * Author:FENG
	 * 2016年5月13日
	 * @return
	 */
	public Integer getSumCount(T t);

	/**
	 * 根据条件获取分页数据
	 * Author:FENG
	 * 2016年5月11日
	 * @param pager
	 * @param id
	 * @return
	 */
	public Pager<T> getListPagerInfo(Pager<T> pager,T t);

	/**
	 * 根据条件删除信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param id
	 * @return
	 */
	public Integer delSingleInfo(T t);
	
	
	/**
	 * 根据条件修改信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param t
	 * @return
	 */
	public Integer modSingleInfo(T t);
	/**
	 * 添加对应信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param t
	 * @return
	 */
	public Integer addSingleInfo(T t);
}
