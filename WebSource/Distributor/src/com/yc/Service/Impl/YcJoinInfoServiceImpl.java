package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcJoinInfoDao;
import com.yc.Entity.YcJoinInfo;
import com.yc.Service.IYcJoinInfoService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager; 
/** 
* YcJoinInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class YcJoinInfoServiceImpl implements IYcJoinInfoService { 

	@Autowired
	private IYcJoinInfoDao iYcJoinInfoDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcJoinInfo getSingleInfo(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.getSingleInfo(ycjoininfo);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcJoinInfo> getListPagerInfo(Pager<YcJoinInfo> pager,YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		Integer sum=iYcJoinInfoDao.getSumCount(ycjoininfo);
		Map<String,Object> map=pager.getElestMap(ycjoininfo);
		pager.setObjectList(iYcJoinInfoDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.delSingleInfo(ycjoininfo);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.getSumCount(ycjoininfo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.modSingleInfo(ycjoininfo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcJoinInfo ycjoininfo) {
		Integer i=iYcJoinInfoDao.addSingleInfo(ycjoininfo);
		if(i<=0){
			FengUtil.RuntimeExceptionFeng("");
		}
		return i;
	}
	@Override
	public List<YcJoinInfo> getYcJoinInfoList(YcJoinInfo join) {
		// TODO Auto-generated method stub
		return iYcJoinInfoDao.getYcJoinInfoList(join);
	}
	@Override
	public YcJoinInfo getJoinInfo(YcJoinInfo join) {
		// TODO Auto-generated method stub
		return iYcJoinInfoDao.getJoinInfo(join);
	}
}
