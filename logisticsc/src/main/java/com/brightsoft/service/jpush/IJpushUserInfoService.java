package com.brightsoft.service.jpush;

import com.brightsoft.entity.JpushUserInfo;

/**
 * 接口推送业务接口层
 * @Author:luojing
 * @2016年7月15日 下午2:35:04
 */
public interface IJpushUserInfoService {  

	/** 
	* 获取单页记录 
	* @Author:luojing 
	*/ 
	public JpushUserInfo getSingleInfo(JpushUserInfo jpushuserinfo);
	/** 
	* 修改信息 
	* @Author:luojing 
	*/ 
	public Integer modSingleInfo(JpushUserInfo jpushuserinfo);
	/** 
	* 添加信息 
	* @Author:luojing 
	*/ 
	public Integer addSingleInfo(JpushUserInfo jpushuserinfo);
}
