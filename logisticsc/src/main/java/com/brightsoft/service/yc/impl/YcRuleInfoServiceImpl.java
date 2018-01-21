package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcRuleInfoDao;
import com.brightsoft.entity.YcRuleInfo;
import com.brightsoft.service.yc.IYcRuleInfoService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcRuleInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class YcRuleInfoServiceImpl implements IYcRuleInfoService { 

	@Autowired
	private IYcRuleInfoDao iYcRuleInfoDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcRuleInfo getSingleInfo(YcRuleInfo ycruleinfo) {
		//TODO Auto-generated method stub
		return iYcRuleInfoDao.getSingleInfo(ycruleinfo);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcRuleInfo> getListPagerInfo(Pager<YcRuleInfo> pager,YcRuleInfo ycruleinfo) {
		//TODO Auto-generated method stub
		Integer sum=iYcRuleInfoDao.getSumCount(ycruleinfo);
		Map<String,Object> map=pager.getElestMap(ycruleinfo);
		pager.setObjectList(iYcRuleInfoDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcRuleInfo ycruleinfo) {
		//TODO Auto-generated method stub
		return iYcRuleInfoDao.delSingleInfo(ycruleinfo);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcRuleInfo ycruleinfo) {
		//TODO Auto-generated method stub
		return iYcRuleInfoDao.getSumCount(ycruleinfo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcRuleInfo ycruleinfo) {
		//TODO Auto-generated method stub
		return iYcRuleInfoDao.modSingleInfo(ycruleinfo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcRuleInfo ycruleinfo) {
		//TODO Auto-generated method stub
		return iYcRuleInfoDao.addSingleInfo(ycruleinfo);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcRuleInfoDao.delSelect(list);
	}

}
