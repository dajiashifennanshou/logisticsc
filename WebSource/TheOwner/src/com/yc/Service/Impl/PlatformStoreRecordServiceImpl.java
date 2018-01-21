package com.yc.Service.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Canstant.Constant;
import com.yc.Canstant.DictionaryType;
import com.yc.Dao.AdditionalServerConfMapper;
import com.yc.Dao.PlatformDictionaryMapper;
import com.yc.Dao.PlatformOrderEvaluationDao;
import com.yc.Dao.PlatformStoreRecordDao;
import com.yc.Dao.TmsLineInfoDao;
import com.yc.Entity.AdditionalServerConf;
import com.yc.Entity.PlatformStoreRecord;
import com.yc.Entity.TmsLineInfo;
import com.yc.Service.PlatformStoreRecordService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.Pager;
@Service
public class PlatformStoreRecordServiceImpl implements PlatformStoreRecordService {

	@Autowired
	private PlatformStoreRecordDao storeRecordDao;
	@Autowired
	private TmsLineInfoDao lineDao;
	@Autowired
	private PlatformOrderEvaluationDao orderEvaluationDao;
	@Autowired
	private PlatformDictionaryMapper dictionaryDao;//类型
	@Autowired
	private AdditionalServerConfMapper addServerDao;//增值服务配置

	@Override
	public Pager<PlatformStoreRecord> getPagerStoreRecord(Pager<PlatformStoreRecord> pager, PlatformStoreRecord sr) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(sr);
		List<PlatformStoreRecord> list = storeRecordDao.getListPagerInfo(map);
		for(int i=0;i<list.size();i++){
			TmsLineInfo line = new TmsLineInfo();
			line.setId(list.get(i).getLine_id());
			line = lineDao.getLineInfo(line);
			line.setTransport_mode(dictionaryDao.selectByPrimaryId(new Long(line.getTransport_mode()), DictionaryType.TRANSPORT_MODE).getName());
			line.setServer_type(dictionaryDao.selectByPrimaryId(new Long(line.getServer_type()), DictionaryType.SERVER_TYPE).getName());
			//验证增值服务
			AdditionalServerConf conf = addServerDao.selectByOutletsId(line.getOutlets_id().longValue());
			if(conf!=null){
				line.setIsAddServer(Constant.ADDITIONALSERVER_STATE_0);
				line.setConf(conf);
			}else{
				line.setIsAddServer(Constant.ADDITIONALSERVER_STATE_1);
			}
			list.get(i).setLine(line);
			//评价条数
			list.get(i).setCountOrderEvaluation(orderEvaluationDao.getCount(list.get(i).getLine_id()));
		}
		pager.setObjectList(list);
		return pager;
	}

	@Override
	public Integer addStoreRecord(PlatformStoreRecord sr) {
		// TODO Auto-generated method stub
		sr.setState(Constant.PLATFORMUSER_STORE_RECORD_STATE_1);
		sr.setCollection_time(DateUtil.getDateTimeFormatString());
		return storeRecordDao.addSingleInfo(sr);
	}

	@Override
	public Integer delStoreRecord(PlatformStoreRecord sr) {
		// TODO Auto-generated method stub
		return storeRecordDao.delSingleInfo(sr);
	}

	@Override
	public PlatformStoreRecord getStoreRecord(PlatformStoreRecord sr) {
		// TODO Auto-generated method stub
		return storeRecordDao.getSingleInfo(sr);
	}

	@Override
	public Integer multiDelCollect(String ids) {
		// TODO Auto-generated method stub
		String [] str = ids.split(",");
		List<BigInteger> list = new ArrayList<BigInteger>();
		for(String s : str){
			list.add(new BigInteger(s));
		}
		return storeRecordDao.multiDelCollect(list);
	}
}
