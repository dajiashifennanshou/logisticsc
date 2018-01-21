package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcCarTypeDao;
import com.brightsoft.entity.YcCarType;
import com.brightsoft.service.yc.IYcCarTypeService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcCarType服务层 
* Auther:FENG 
*/ 
@Service 
public class YcCarTypeServiceImpl implements IYcCarTypeService { 

	@Autowired
	private IYcCarTypeDao iYcCarTypeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcCarType getSingleInfo(YcCarType yccartype) {
		//TODO Auto-generated method stub
		return iYcCarTypeDao.getSingleInfo(yccartype);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcCarType> getListPagerInfo(Pager<YcCarType> pager,YcCarType yccartype) {
		//TODO Auto-generated method stub
		Integer sum=iYcCarTypeDao.getSumCount(yccartype);
		Map<String,Object> map=pager.getElestMap(yccartype);
		pager.setObjectList(iYcCarTypeDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcCarType yccartype) {
		//TODO Auto-generated method stub
		return iYcCarTypeDao.delSingleInfo(yccartype);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcCarType yccartype) {
		//TODO Auto-generated method stub
		return iYcCarTypeDao.getSumCount(yccartype);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcCarType yccartype) {
		//TODO Auto-generated method stub
		return iYcCarTypeDao.modSingleInfo(yccartype);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcCarType yccartype) {
		//TODO Auto-generated method stub
		return iYcCarTypeDao.addSingleInfo(yccartype);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<YcCarType> getYcCarTypeListBy(YcCarType yc) {
		// TODO Auto-generated method stub
		return iYcCarTypeDao.getYcCarTypeListBy(yc);
	}
}
