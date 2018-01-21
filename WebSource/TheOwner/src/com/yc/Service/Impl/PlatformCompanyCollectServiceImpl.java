package com.yc.Service.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Canstant.Constant;
import com.yc.Dao.AdditionalServerConfMapper;
import com.yc.Dao.PlatformCompanyCollectDao;
import com.yc.Dao.PlatformUserCompanyDao;
import com.yc.Dao.TmsLineInfoDao;
import com.yc.Entity.AdditionalServerConf;
import com.yc.Entity.PlatformCompanyCollect;
import com.yc.Entity.PlatformUserCompany;
import com.yc.Entity.TmsLineInfo;
import com.yc.Service.PlatformCompanyCollectService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.Pager;
@Service
public class PlatformCompanyCollectServiceImpl implements PlatformCompanyCollectService {

	@Autowired
	private PlatformCompanyCollectDao companyCollectDao;
	@Autowired
	private PlatformUserCompanyDao companyDao;
	@Autowired
	private TmsLineInfoDao lineDao;
	
	@Override
	public Pager<PlatformCompanyCollect> getPageCompanyCollect(Pager<PlatformCompanyCollect> pager,
			PlatformCompanyCollect cc) {
		// TODO Auto-generated method stub
		Map<String, Object> map = pager.getElestMap(cc);
		List<PlatformCompanyCollect> list = companyCollectDao.getListPagerInfo(map);
		for(int i=0;i<list.size();i++){
			PlatformUserCompany com = new PlatformUserCompany();
			com.setId(list.get(i).getComId());
			com = companyDao.getPlatformUserCompany(com);
			List<TmsLineInfo> line = lineDao.getListLineInfoByComId2(com.getId());
			com.setList(line);
			list.get(i).setCom(com);
		}
		pager.setObjectList(list);
		return pager;
	}

	@Override
	public PlatformCompanyCollect getCompanyCollect(PlatformCompanyCollect cc) {
		// TODO Auto-generated method stub
		return companyCollectDao.getSingleInfo(cc);
	}

	@Override
	public Integer addCompanyCollect(PlatformCompanyCollect cc) {
		// TODO Auto-generated method stub
		cc.setStatus(Constant.PLATFORMUSER_COM_COLLECT_1);
		cc.setCreateTime(DateUtil.getDateTimeFormatString());
		return companyCollectDao.addSingleInfo(cc);
	}

	@Override
	public Integer delCompanyCollect(PlatformCompanyCollect cc) {
		// TODO Auto-generated method stub
		return companyCollectDao.delSingleInfo(cc);
	}
	@Override
	public Integer multiDelCompanyCollect(String ids) {
		// TODO Auto-generated method stub
		String[] str = ids.split(",");
		List<BigInteger> list = new ArrayList<BigInteger>();
		for(String s : str){
			list.add(new BigInteger(s));
		}
		return companyCollectDao.multiDelCompanyCollect(list);
	}

}
