package com.yc.Service.Impl; 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcGoodsTypeDao;
import com.yc.Entity.YcGoodsType;
import com.yc.Service.IYcGoodsTypeService;
import com.yc.Tool.Pager; 
/** 
* YcGoodsType服务层 
* Auther:FENG 
*/ 
@Service 
public class YcGoodsTypeServiceImpl implements IYcGoodsTypeService { 

	@Autowired
	private IYcGoodsTypeDao iYcGoodsTypeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcGoodsType getSingleInfo(YcGoodsType ycgoodstype) {
		//TODO Auto-generated method stub
		return iYcGoodsTypeDao.getSingleInfo(ycgoodstype);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcGoodsType> getListPagerInfo(Pager<YcGoodsType> pager,YcGoodsType ycgoodstype) {
		//TODO Auto-generated method stub
		Integer sum=iYcGoodsTypeDao.getSumCount(ycgoodstype);
		Map<String,Object> map=pager.getElestMap(ycgoodstype);
		pager.setObjectList(iYcGoodsTypeDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcGoodsType ycgoodstype) {
		//TODO Auto-generated method stub
		return iYcGoodsTypeDao.delSingleInfo(ycgoodstype);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcGoodsType ycgoodstype) {
		//TODO Auto-generated method stub
		return iYcGoodsTypeDao.getSumCount(ycgoodstype);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcGoodsType ycgoodstype) {
		//TODO Auto-generated method stub
		return iYcGoodsTypeDao.modSingleInfo(ycgoodstype);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcGoodsType ycgoodstype) {
		//TODO Auto-generated method stub
		return iYcGoodsTypeDao.addSingleInfo(ycgoodstype);
	}
	@Override
	public List<YcGoodsType> getGoodsTypeBy(YcGoodsType ygt) {
		// TODO Auto-generated method stub
		return iYcGoodsTypeDao.getGoodsTypeBy(ygt);
	}
}
