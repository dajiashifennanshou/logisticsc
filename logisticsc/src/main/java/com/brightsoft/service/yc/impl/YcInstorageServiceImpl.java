package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcInstorageDao;
import com.brightsoft.entity.YcInstorage;
import com.brightsoft.service.yc.IYcInstorageService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcInstorage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcInstorageServiceImpl implements IYcInstorageService { 

	@Autowired
	private IYcInstorageDao iYcInstorageDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcInstorage getSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.getSingleInfo(ycinstorage);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcInstorage> getListPagerInfo(Pager<YcInstorage> pager,YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		Integer sum=iYcInstorageDao.getSumCount(ycinstorage);
		Map<String,Object> map=pager.getElestMap(ycinstorage);
		pager.setObjectList(iYcInstorageDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.delSingleInfo(ycinstorage);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.getSumCount(ycinstorage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.modSingleInfo(ycinstorage);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.addSingleInfo(ycinstorage);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcInstorageDao.delSelect(list);
	}
	
	
	public Integer delInstorage(String ids) {
		// TODO Auto-generated method stub
		return iYcInstorageDao.delInstorage(ids);
	}
	
	
	public Integer addInstorage(YcInstorage is, String cId) {
		// TODO Auto-generated method stub
		return iYcInstorageDao.addInstorage(is, cId);
	}
}
