package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcStorageZoneDao;
import com.brightsoft.entity.YcStorageZone;
import com.brightsoft.service.yc.IYcStorageZoneService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcStorageZone服务层 
* Auther:FENG 
*/ 
@Service 
public class YcStorageZoneServiceImpl implements IYcStorageZoneService { 

	@Autowired
	private IYcStorageZoneDao iYcStorageZoneDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcStorageZone getSingleInfo(YcStorageZone ycstoragezone) {
		//TODO Auto-generated method stub
		return iYcStorageZoneDao.getSingleInfo(ycstoragezone);
	}
	
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcStorageZone> getListPagerInfo(Pager<YcStorageZone> pager,YcStorageZone ycstoragezone) {
		//TODO Auto-generated method stub
		Integer sum=iYcStorageZoneDao.getSumCount(ycstoragezone);
		Map<String,Object> map=pager.getElestMap(ycstoragezone);
		pager.setObjectList(iYcStorageZoneDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcStorageZone ycstoragezone) {
		//TODO Auto-generated method stub
		return iYcStorageZoneDao.delSingleInfo(ycstoragezone);
	}
	
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcStorageZone ycstoragezone) {
		//TODO Auto-generated method stub
		return iYcStorageZoneDao.getSumCount(ycstoragezone);
	}
	
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcStorageZone ycstoragezone) {
		//TODO Auto-generated method stub
		return iYcStorageZoneDao.modSingleInfo(ycstoragezone);
	}
	
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcStorageZone ycstoragezone) {
		//TODO Auto-generated method stub
		return iYcStorageZoneDao.addSingleInfo(ycstoragezone);
	}
	
	/**
	 * 多条删除
	 * Author:luojing
	 * 2016年6月15日 下午2:48:32
	 */
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcStorageZoneDao.delSelect(list);
	}
	
	/**
	 * 条件集合查询
	 * Author:luojing
	 * 2016年6月15日 下午2:48:38
	 */
	
	public List<YcStorageZone> getStorageZone(YcStorageZone sz) {
		// TODO Auto-generated method stub
		try{
			Map<String,Object> map = FengUtil.getMap(sz);
			List<YcStorageZone> list = iYcStorageZoneDao.getStorageZone(map);
			if(!list.isEmpty()){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
