package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformOrderEvaluationDao;
import com.yc.Dao.PlatformUserDao;
import com.yc.Entity.PlatformOrderEvaluation;
import com.yc.Service.PlatformOrderEvaluationService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.Pager; 
/** 
* LcPlatformOrderEvaluation服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformOrderEvaluationServiceImpl implements PlatformOrderEvaluationService { 

	@Autowired
	private PlatformOrderEvaluationDao platformOrderEvaluationDao;
	@Autowired
	private PlatformUserDao iLcPlatformUserDao;
	@Override
	public List<PlatformOrderEvaluation> getEvaluateInfo(BigInteger linId, BigInteger userId) {
		// TODO Auto-generated method stub
		List<PlatformOrderEvaluation> lst=new ArrayList<PlatformOrderEvaluation>();
		for(PlatformOrderEvaluation pe:platformOrderEvaluationDao.getEvaluateInfo(linId, userId)){
			pe.setEvaluate_time(DateUtil.getTime2Long(pe.getEvaluate_time())+"");
			lst.add(pe);
		}
		return lst;
	}
	@Override
	public Integer addSingleInfo(PlatformOrderEvaluation t) {
		// TODO Auto-generated method stub
		return platformOrderEvaluationDao.addSingleInfo(t);
	}
	@Override
	public PlatformOrderEvaluation getSingleInfo(PlatformOrderEvaluation t) {
		// TODO Auto-generated method stub
		return platformOrderEvaluationDao.getSingleInfo(t);
	}
	@Override
	public Integer getSumCount(PlatformOrderEvaluation t) {
		// TODO Auto-generated method stub
		return platformOrderEvaluationDao.getSumCount(t);
	}
	@Override
	public Pager<PlatformOrderEvaluation> getPageEvaluateInfo(Pager<PlatformOrderEvaluation> pager, BigInteger linId,
			BigInteger userId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		map.put("linId", linId);
		map.put("userId", userId);
		List<PlatformOrderEvaluation> list = platformOrderEvaluationDao.getListPagerInfo(map);
		for(int i=0;i<list.size();i++){
			list.get(i).setEvaluate_time(DateUtil.getTime2Long(list.get(i).getEvaluate_time())+"");
			list.get(i).setUser(iLcPlatformUserDao.getEleUserInfo(userId));
		}
		pager.setObjectList(list);
		return pager;
	}
}
