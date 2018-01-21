package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.XZQHInfoDao;
import com.yc.Entity.XZQHInfo;
import com.yc.Service.XZQHInfoService;
import com.yc.Tool.Pager; 
/** 
* lc_xzqh_info服务层 
* Auther:FENG 
*/ 
@Service 
public class XZQHInfoServiceImpl implements XZQHInfoService {
	
	@Autowired
	private XZQHInfoDao xzqhInfoDao;

	@Override
	public List<XZQHInfo> getXZQH() {
		// TODO Auto-generated method stub
		List<XZQHInfo> province = xzqhInfoDao.getXZQH("100000");
		for(int i=0;i<province.size();i++){
			List<XZQHInfo> city = xzqhInfoDao.getXZQH(province.get(i).getId());
			for(int j=0;j<city.size();j++){
				List<XZQHInfo> county = xzqhInfoDao.getXZQH(city.get(j).getId());
				city.get(j).setList(county);
			}
			province.get(i).setList(city);
		}
		return province;
	}

	@Override
	public XZQHInfo getSingleInfo(XZQHInfo t) {
		// TODO Auto-generated method stub
		return xzqhInfoDao.getSingleInfo(t)
				;
	}

	@Override
	public Integer getSumCount(XZQHInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<XZQHInfo> getListPagerInfo(Pager<XZQHInfo> pager, XZQHInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delSingleInfo(XZQHInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modSingleInfo(XZQHInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addSingleInfo(XZQHInfo t) {
		// TODO Auto-generated method stub
		return null;
	}
}
