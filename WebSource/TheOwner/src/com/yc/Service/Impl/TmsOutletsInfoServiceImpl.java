package com.yc.Service.Impl; 
import com.yc.Entity.TmsOutletsInfo; 
import com.yc.Tool.Pager; 
import com.yc.Dao.ITmsOutletsInfoDao; 
import com.yc.Service.ITmsOutletsInfoService; 
import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;
import java.util.Map; 
/** 
* TmsOutletsInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class TmsOutletsInfoServiceImpl implements ITmsOutletsInfoService { 

	@Autowired
	private ITmsOutletsInfoDao iTmsOutletsInfoDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public TmsOutletsInfo getSingleInfo(TmsOutletsInfo tmsoutletsinfo) {
		//TODO Auto-generated method stub
		return iTmsOutletsInfoDao.getSingleInfo(tmsoutletsinfo);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<TmsOutletsInfo> getListPagerInfo(Pager<TmsOutletsInfo> pager,TmsOutletsInfo tmsoutletsinfo) {
		//TODO Auto-generated method stub
		Integer sum=iTmsOutletsInfoDao.getSumCount(tmsoutletsinfo);
		Map<String,Object> map=pager.getElestMap(tmsoutletsinfo);
		pager.setObjectList(iTmsOutletsInfoDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(TmsOutletsInfo tmsoutletsinfo) {
		//TODO Auto-generated method stub
		return iTmsOutletsInfoDao.delSingleInfo(tmsoutletsinfo);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(TmsOutletsInfo tmsoutletsinfo) {
		//TODO Auto-generated method stub
		return iTmsOutletsInfoDao.getSumCount(tmsoutletsinfo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(TmsOutletsInfo tmsoutletsinfo) {
		//TODO Auto-generated method stub
		return iTmsOutletsInfoDao.modSingleInfo(tmsoutletsinfo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(TmsOutletsInfo tmsoutletsinfo) {
		//TODO Auto-generated method stub
		return iTmsOutletsInfoDao.addSingleInfo(tmsoutletsinfo);
	}
	@Override
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
