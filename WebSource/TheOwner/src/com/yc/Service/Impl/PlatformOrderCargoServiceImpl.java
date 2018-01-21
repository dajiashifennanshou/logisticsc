package com.yc.Service.Impl; 
import com.yc.Entity.PlatformDictionary;
import com.yc.Entity.PlatformOrderCargo; 
import com.yc.Tool.Pager;
import com.yc.Dao.PlatformDictionaryMapper;
import com.yc.Dao.PlatformOrderCargoDao; 
import com.yc.Service.PlatformOrderCargoService; 
import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map; 
/** 
* LcPlatformOrderCargo服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformOrderCargoServiceImpl implements PlatformOrderCargoService { 

	@Autowired
	private PlatformOrderCargoDao iLcPlatformOrderCargoDao;
	@Autowired
	private PlatformDictionaryMapper platformDictionaryMapper;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public PlatformOrderCargo getSingleInfo(PlatformOrderCargo lcplatformordercargo) {
		//TODO Auto-generated method stub
		return iLcPlatformOrderCargoDao.getSingleInfo(lcplatformordercargo);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<PlatformOrderCargo> getListPagerInfo(Pager<PlatformOrderCargo> pager,PlatformOrderCargo lcplatformordercargo) {
		//TODO Auto-generated method stub
		Integer sum=iLcPlatformOrderCargoDao.getSumCount(lcplatformordercargo);
		Map<String,Object> map=pager.getElestMap(lcplatformordercargo);
		pager.setObjectList(iLcPlatformOrderCargoDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(PlatformOrderCargo lcplatformordercargo) {
		//TODO Auto-generated method stub
		return iLcPlatformOrderCargoDao.delSingleInfo(lcplatformordercargo);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(PlatformOrderCargo lcplatformordercargo) {
		//TODO Auto-generated method stub
		return iLcPlatformOrderCargoDao.getSumCount(lcplatformordercargo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(PlatformOrderCargo lcplatformordercargo) {
		//TODO Auto-generated method stub
		return iLcPlatformOrderCargoDao.modSingleInfo(lcplatformordercargo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(PlatformOrderCargo lcplatformordercargo) {
		//TODO Auto-generated method stub
		return iLcPlatformOrderCargoDao.addSingleInfo(lcplatformordercargo);
	}
	/** 
	* 多条删除 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSelect(List<BigInteger> list) {
		//TODO Auto-generated method stub
		return iLcPlatformOrderCargoDao.delSelect(list);
	}
	@Override
	public List<PlatformOrderCargo> getListInfoBy(PlatformOrderCargo cargo) {
		// TODO Auto-generated method stub
		List<PlatformOrderCargo> lst=new ArrayList<PlatformOrderCargo>();
		for(PlatformOrderCargo poc:iLcPlatformOrderCargoDao.getListInfoBy(cargo)){
			poc.setPackage_name(platformDictionaryMapper.selectByPrimaryKey(new Long(poc.getPackage_type().toString())).getName());
			lst.add(poc);
		}
		return lst;
	}
}
