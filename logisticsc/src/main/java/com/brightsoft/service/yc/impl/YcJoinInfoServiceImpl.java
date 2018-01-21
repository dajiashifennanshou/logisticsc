package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.yc.IYcJoinInfoDao;
import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.service.yc.IYcJoinInfoService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcJoinInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class YcJoinInfoServiceImpl implements IYcJoinInfoService { 

	@Autowired
	private IYcJoinInfoDao iYcJoinInfoDao;
	@Autowired
	private PlatformUserMapper pfum;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcJoinInfo getSingleInfo(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.getSingleInfo(ycjoininfo);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcJoinInfo> getListPagerInfo(Pager<YcJoinInfo> pager,YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		Integer sum=iYcJoinInfoDao.getSumCount(ycjoininfo);
		Map<String,Object> map=pager.getElestMap(ycjoininfo);
		List<YcJoinInfo> lst=new ArrayList<YcJoinInfo>();
		for(YcJoinInfo yji: iYcJoinInfoDao.getListPagerInfo(map)){
			PlatformUser pu= pfum.selectUserId(new Long(yji.getJoinerId().toString()));
			if(pu!=null){
				yji.setJoiner(pu.getTrueName());
				yji.setJoinPhone(pu.getLoginName());
			}
			lst.add(yji);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.delSingleInfo(ycjoininfo);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.getSumCount(ycjoininfo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcJoinInfo ycjoininfo) {
		//TODO Auto-generated method stub
		return iYcJoinInfoDao.modSingleInfo(ycjoininfo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcJoinInfo ycjoininfo) {
		Integer i=iYcJoinInfoDao.addSingleInfo(ycjoininfo);
		if(i<=0){
			FengUtil.RuntimeExceptionFeng("添加异常..");
		}
		return i;
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcJoinInfoDao.delSelect(list);
	}
	
	public List<YcJoinInfo> getYcJoin(YcJoinInfo join) {
		// TODO Auto-generated method stub
		return iYcJoinInfoDao.getYcJoin(join);
	}
}
