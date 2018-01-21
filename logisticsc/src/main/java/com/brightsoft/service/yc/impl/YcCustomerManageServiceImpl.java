package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcCustomerManageDao;
import com.brightsoft.entity.YcCustomerManage;
import com.brightsoft.service.yc.IYcCustomerManageService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcCustomerManage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcCustomerManageServiceImpl implements IYcCustomerManageService { 

	@Autowired
	private IYcCustomerManageDao iYcCustomerManageDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	public YcCustomerManage getSingleInfo(YcCustomerManage yccustomermanage) {
		//TODO Auto-generated method stub
		return iYcCustomerManageDao.getSingleInfo(yccustomermanage);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	public Pager<YcCustomerManage> getListPagerInfo(Pager<YcCustomerManage> pager,YcCustomerManage yccustomermanage) {
		//TODO Auto-generated method stub
		Integer sum=iYcCustomerManageDao.getSumCount(yccustomermanage);
		Map<String,Object> map=pager.getElestMap(yccustomermanage);
		pager.setObjectList(iYcCustomerManageDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	public Integer delSingleInfo(YcCustomerManage yccustomermanage) {
		//TODO Auto-generated method stub
		return iYcCustomerManageDao.delSingleInfo(yccustomermanage);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	public Integer getSumCount(YcCustomerManage yccustomermanage) {
		//TODO Auto-generated method stub
		return iYcCustomerManageDao.getSumCount(yccustomermanage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	public Integer modSingleInfo(YcCustomerManage yccustomermanage) {
		//TODO Auto-generated method stub
		return iYcCustomerManageDao.modSingleInfo(yccustomermanage);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	public Integer addSingleInfo(YcCustomerManage yccustomermanage) {
		//TODO Auto-generated method stub
		return iYcCustomerManageDao.addSingleInfo(yccustomermanage);
	}
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
