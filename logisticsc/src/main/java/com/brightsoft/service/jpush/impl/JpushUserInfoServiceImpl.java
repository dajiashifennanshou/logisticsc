package com.brightsoft.service.jpush.impl; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IJpushUserInfoDao;
import com.brightsoft.entity.JpushUserInfo;
import com.brightsoft.service.jpush.IJpushUserInfoService; 
/** 
* JpushUserInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class JpushUserInfoServiceImpl implements IJpushUserInfoService { 

	@Autowired
	private IJpushUserInfoDao iJpushUserInfoDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	public JpushUserInfo getSingleInfo(JpushUserInfo jpushuserinfo) {
		//TODO Auto-generated method stub
		return iJpushUserInfoDao.getSingleInfo(jpushuserinfo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	public Integer modSingleInfo(JpushUserInfo jpushuserinfo) {
		//TODO Auto-generated method stub
		return iJpushUserInfoDao.modSingleInfo(jpushuserinfo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	public Integer addSingleInfo(JpushUserInfo jpushuserinfo) {
		//TODO Auto-generated method stub
		return iJpushUserInfoDao.addSingleInfo(jpushuserinfo);
	}
}
