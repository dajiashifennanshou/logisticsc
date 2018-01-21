package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcSpecialDao;
import com.yc.Entity.YcSpecial;
import com.yc.Service.IYcSpecialService;
import com.yc.Tool.Pager; 
/** 
* YcSpecial服务层 
* Auther:FENG 
*/ 
@Service 
public class YcSpecialServiceImpl implements IYcSpecialService { 

	@Autowired
	private IYcSpecialDao iYcSpecialDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcSpecial getSingleInfo(YcSpecial ycspecial) {
		//TODO Auto-generated method stub
		return iYcSpecialDao.getSingleInfo(ycspecial);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcSpecial> getListPagerInfo(Pager<YcSpecial> pager,YcSpecial ycspecial) {
		//TODO Auto-generated method stub
		Integer sum=iYcSpecialDao.getSumCount(ycspecial);
		Map<String,Object> map=pager.getElestMap(ycspecial);
		pager.setObjectList(iYcSpecialDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcSpecial ycspecial) {
		//TODO Auto-generated method stub
		return iYcSpecialDao.delSingleInfo(ycspecial);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcSpecial ycspecial) {
		//TODO Auto-generated method stub
		return iYcSpecialDao.getSumCount(ycspecial);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcSpecial ycspecial) {
		//TODO Auto-generated method stub
		return iYcSpecialDao.modSingleInfo(ycspecial);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcSpecial ycspecial) {
		//TODO Auto-generated method stub
		return iYcSpecialDao.addSingleInfo(ycspecial);
	}
	@Override
	public List<YcSpecial> getUnRegisterSpecial() {
		// TODO Auto-generated method stub
		return iYcSpecialDao.getUnRegisterSpecial();
	}
}
