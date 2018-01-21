package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcInstorageDao;
import com.yc.Entity.YcInstorage;
import com.yc.Service.IYcInstorageService;
import com.yc.Tool.Pager; 
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
	@Override
	public YcInstorage getSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.getSingleInfo(ycinstorage);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
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
	@Override
	public Integer delSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.delSingleInfo(ycinstorage);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.getSumCount(ycinstorage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.modSingleInfo(ycinstorage);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcInstorage ycinstorage) {
		//TODO Auto-generated method stub
		return iYcInstorageDao.addSingleInfo(ycinstorage);
	}
	
	@Override
	public Integer delInstorage(String ids) {
		// TODO Auto-generated method stub
		return iYcInstorageDao.delInstorage(ids);
	}
	
	@Override
	public Integer addInstorage(YcInstorage is, String cId) {
		// TODO Auto-generated method stub
		return iYcInstorageDao.addInstorage(is, cId);
	}
}
