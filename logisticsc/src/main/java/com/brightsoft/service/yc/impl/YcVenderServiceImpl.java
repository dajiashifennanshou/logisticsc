package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcVenderDao;
import com.brightsoft.entity.YcVender;
import com.brightsoft.service.yc.IYcVenderService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcVender服务层 
* Auther:FENG 
*/ 
@Service 
public class YcVenderServiceImpl implements IYcVenderService { 

	@Autowired
	private IYcVenderDao iYcVenderDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcVender getSingleInfo(YcVender ycvender) {
		//TODO Auto-generated method stub
		return iYcVenderDao.getSingleInfo(ycvender);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcVender> getListPagerInfo(Pager<YcVender> pager,YcVender ycvender) {
		//TODO Auto-generated method stub
		Integer sum=iYcVenderDao.getSumCount(ycvender);
		Map<String,Object> map=pager.getElestMap(ycvender);
		pager.setObjectList(iYcVenderDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcVender ycvender) {
		//TODO Auto-generated method stub
		return iYcVenderDao.delSingleInfo(ycvender);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcVender ycvender) {
		//TODO Auto-generated method stub
		return iYcVenderDao.getSumCount(ycvender);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcVender ycvender) {
		//TODO Auto-generated method stub
		return iYcVenderDao.modSingleInfo(ycvender);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcVender ycvender) {
		//TODO Auto-generated method stub
		return iYcVenderDao.addSingleInfo(ycvender);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
