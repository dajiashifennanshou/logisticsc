package com.yc.Service.Impl;

import java.math.BigInteger;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Canstant.Constant;
import com.yc.Canstant.DictionaryType;
import com.yc.Dao.AdditionalServerConfMapper;
import com.yc.Dao.PlatformCompanyCollectDao;
import com.yc.Dao.PlatformDictionaryMapper;
import com.yc.Dao.PlatformOrderEvaluationDao;
import com.yc.Dao.PlatformStoreRecordDao;
import com.yc.Dao.PlatformUserCompanyDao;
import com.yc.Dao.TmsLineInfoDao;
import com.yc.Dao.XZQHInfoDao;
import com.yc.Entity.AdditionalServerConf;
import com.yc.Entity.LogisticsInfo;
import com.yc.Entity.PlatformCompanyCollect;
import com.yc.Entity.PlatformDeliverGoods;
import com.yc.Entity.PlatformStoreRecord;
import com.yc.Entity.PlatformUserCompany;
import com.yc.Entity.TmsLineInfo;
import com.yc.Entity.XZQHInfo;
import com.yc.Service.PlatformUserCompanyService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.MapUtil;
import com.yc.Tool.Pager;
import com.yc.Tool.StrUtil;
/**
 * 物流商信息
 * @Author:luojing
 * @2016年7月27日 下午5:19:30
 */
@Service
public class PlatformUserCompanyServiceImpl implements PlatformUserCompanyService {

	@Autowired
	private PlatformUserCompanyDao companyDao;
	@Autowired
	private TmsLineInfoDao lineInfoDao;
	@Autowired
	private XZQHInfoDao XZQHInfoDao;
	@Autowired
	private PlatformDictionaryMapper dictionaryDao;
	@Autowired
	private PlatformOrderEvaluationDao orderEvaluationDao;
	//收藏线路
	@Autowired
	private PlatformStoreRecordDao storeRecordDao;
	//收藏物流商
	@Autowired
	private PlatformCompanyCollectDao companyCollectDao;
	@Autowired
	private AdditionalServerConfMapper addServerDao;//增值服务配置

	@Override
	public Pager<LogisticsInfo> getPageLogisticsInfo(Pager<LogisticsInfo> pager, LogisticsInfo log) {
		// TODO Auto-generated method stub
		//查询将地址换为代码
		if(log.getProvince()==null || log.getCity()==null || log.getCounty()==null){
			FengUtil.RuntimeExceptionFeng("省市县不能空...");
		}
		XZQHInfo zxqh= new XZQHInfo();
		zxqh.setProvince(log.getProvince());
		zxqh.setCity(log.getCity());
		zxqh.setCounty(log.getCounty());
		zxqh = XZQHInfoDao.getXZQHID(zxqh);
		log.setProvince(zxqh.getProvince());
		log.setCity(zxqh.getCity());
		log.setCounty(zxqh.getCounty());
		//赋值
		Map<String,Object> map = pager.getElestMap(log);
		//根据省市区县
		List<LogisticsInfo> list = companyDao.getListLogisticsInfo(map);//查询
		if(list.size()==0){
			//根据省市查询
			log.setCounty(null);
			map = pager.getElestMap(log);
			list = companyDao.getListLogisticsInfo(map);//查询
		}
		for(int i=0;i<list.size();i++){
			//计算距离
			Map<String,Double> m = MapUtil.getLngAndLat(list.get(i).getProvince()+list.get(i).getCity()+list.get(i).getCounty()+list.get(i).getAddress());
			if(m!=null){
				if(log.getLongitude()!=null && log.getLatitude()!=null && StrUtil.VString(m.get("lng").toString()) && StrUtil.VString(m.get("lat").toString())){
					list.get(i).setDistance((int)MapUtil.Distance(log.getLongitude(), log.getLatitude(),Double.parseDouble(m.get("lng").toString()),Double.parseDouble(m.get("lat").toString())));
				}
			}
			List<TmsLineInfo> line = lineInfoDao.getListLineInfo(new TmsLineInfo(list.get(i).getOutletsId()));
			list.get(i).setList(line);
		}
		//排序
		list.sort(new Comparator<LogisticsInfo>() {
			@Override
			public int compare(LogisticsInfo log1, LogisticsInfo log2) {
				// TODO Auto-generated method stub
				return log1.getDistance().compareTo(log2.getDistance());
			}
		});
		pager.setObjectList(list);
		return pager;
	}

	@Override
	public Pager<PlatformDeliverGoods> getPageLineInfo(Pager<PlatformDeliverGoods> pager, PlatformDeliverGoods pdg) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(pdg);
		List<PlatformDeliverGoods> list = new ArrayList<PlatformDeliverGoods>();
		for(PlatformDeliverGoods temp:companyDao.getPageLineInfo(map)){
			//设置运输方式和服务类型
			temp.setTransport_mode(dictionaryDao.selectByPrimaryId(new Long(temp.getTransport_mode()),DictionaryType.TRANSPORT_MODE).getName());
			temp.setServer_type(dictionaryDao.selectByPrimaryId(new Long(temp.getServer_type()), DictionaryType.SERVER_TYPE).getName());
			temp.setCountOrderEvaluation((orderEvaluationDao.getCount(temp.getId())));
			//计算距离
			Map<String,Double> m = MapUtil.getLngAndLat(temp.getStartProvince()+temp.getStartCity()+temp.getStartCounty()+temp.getStartAddress());
			if(m!=null){
				if(pdg.getLongitude()!=null && pdg.getLatitude()!=null && StrUtil.VString(m.get("lng").toString()) && StrUtil.VString(m.get("lat").toString())){
					temp.setDistance((int)MapUtil.Distance(pdg.getLongitude(), pdg.getLatitude(),Double.parseDouble(m.get("lng").toString()),Double.parseDouble(m.get("lat").toString())));
				}
			}
			//验证增值服务
			AdditionalServerConf conf = addServerDao.selectByOutletsId(temp.getOutlets_id().longValue());
			if(conf!=null){
				temp.setIsAddServer(Constant.ADDITIONALSERVER_STATE_0);
				temp.setConf(conf);
			}else{
				temp.setIsAddServer(Constant.ADDITIONALSERVER_STATE_1);
			}
			if(pdg.getUser_id()!=null){
				//查看物流商是否收藏
				PlatformCompanyCollect cc = new PlatformCompanyCollect();
				cc.setUserId(pdg.getUser_id());
				cc.setComId(temp.getCompanyId());
				PlatformCompanyCollect collect = companyCollectDao.getSingleInfo(cc);
				if(collect!=null){//收藏
					temp.setIsCollect(Constant.LINE_IS_COLLECT_0);
				}else{//未收藏
					temp.setIsCollect(Constant.LINE_IS_COLLECT_1);
				}
			}
			list.add(temp);
		}
		//排序
		list.sort(new Comparator<PlatformDeliverGoods>() {
			@Override
			public int compare(PlatformDeliverGoods pfd1, PlatformDeliverGoods pfd2) {
				// TODO Auto-generated method stub
				return pfd1.getDistance().compareTo(pfd2.getDistance());
			}
		});
		pager.setObjectList(list);
		return pager ;
	}

	@Override
	public Pager<PlatformUserCompany> getPagerCompanyInfo(Pager<PlatformUserCompany> pager,PlatformUserCompany com) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(com);
		List<PlatformUserCompany> list = new ArrayList<PlatformUserCompany>(); 
		for(PlatformUserCompany c:companyDao.getPagerCompanyInfo(map)){
			List<TmsLineInfo> lineList = lineInfoDao.getListLineInfoByComId1(c.getId(),com.getProvince(),com.getCity());
			if(lineList.size()>0){
				List<Integer> dis = new ArrayList<Integer>();
				for(int j=0;j<lineList.size();j++){
					//计算距离
					Map<String,Double> m = MapUtil.getLngAndLat(lineList.get(j).getProvince()+lineList.get(j).getCity()+lineList.get(j).getCounty()+lineList.get(j).getAddress());
					if(m==null){
						dis.add(0);
					}else{
						if(com.getLongitude()!=null && com.getLatitude()!=null && StrUtil.VString(m.get("lng").toString()) && StrUtil.VString(m.get("lat").toString())){
							dis.add((int)MapUtil.Distance(com.getLongitude(), com.getLatitude(),Double.parseDouble(m.get("lng").toString()),Double.parseDouble(m.get("lat").toString())));
						}else{
							dis.add(0);
						}
					}
				}
				//排序获取最近距离
				Collections.sort(dis);
				c.setDistance(dis.get(0));
				c.setList(lineList);
				list.add(c);
			}
		}
		pager.setObjectList(list);
		return pager;
	}
	
	@Override
	public PlatformUserCompany getPlatformUserCompany(PlatformUserCompany com,BigInteger userId) {
		// TODO Auto-generated method stub
		com = companyDao.getPlatformUserCompany(com);
		List<PlatformDeliverGoods> list =new ArrayList<PlatformDeliverGoods>();
		for(PlatformDeliverGoods temp:lineInfoDao.getListLineInfoByComId3(com.getId())){
			temp.setTransport_mode(dictionaryDao.selectByPrimaryId(new Long(temp.getTransport_mode()),DictionaryType.TRANSPORT_MODE).getName());
			temp.setServer_type(dictionaryDao.selectByPrimaryId(new Long(temp.getServer_type()), DictionaryType.SERVER_TYPE).getName());
			AdditionalServerConf conf = addServerDao.selectByOutletsId(temp.getOutlets_id().longValue());//验证增值服务
			if(conf!=null){
				temp.setIsAddServer(Constant.ADDITIONALSERVER_STATE_0);
				temp.setConf(conf);
			}else{
				temp.setIsAddServer(Constant.ADDITIONALSERVER_STATE_1);
			}
			list.add(temp);
		}
		//查看物流商是否收藏
		PlatformCompanyCollect cc = new PlatformCompanyCollect();
		cc.setUserId(userId);
		cc.setComId(com.getId());
		PlatformCompanyCollect collect = companyCollectDao.getSingleInfo(cc);
		if(collect!=null){//收藏
			com.setIsCollect(Constant.LINE_IS_COLLECT_0);
		}else{//未收藏
			com.setIsCollect(Constant.LINE_IS_COLLECT_1);
		}
		//验证线路是否收藏
		for(int i=0;i<list.size();i++){
			PlatformStoreRecord s = new PlatformStoreRecord();
			s.setLine_id(list.get(i).getId());
			s.setUser_id(userId);
			PlatformStoreRecord sr = storeRecordDao.getSingleInfo(s);
			if(sr!=null){
				list.get(i).setIsCollect(Constant.LINE_IS_COLLECT_0);
			}else{
				list.get(i).setIsCollect(Constant.LINE_IS_COLLECT_1);
			}
		}
		com.setPdglist(list);
		return com;
	}

	@Override
	public Pager<PlatformUserCompany> getCommonCompany(Pager<PlatformUserCompany> pager,BigInteger userId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		map.put("userId", userId);
		List<PlatformUserCompany> list = companyDao.getCommonCompany(map);
		for(int i=0;i<list.size();i++){
			List<TmsLineInfo> line = lineInfoDao.getListLineInfoByComId2(list.get(i).getId());
			list.get(i).setList(line);
		}
		pager.setObjectList(list);
		return pager;
	}

	@Override
	public PlatformUserCompany getUserCompany(PlatformUserCompany com) {
		// TODO Auto-generated method stub
		return companyDao.getSingleInfo(com);
	}
}
