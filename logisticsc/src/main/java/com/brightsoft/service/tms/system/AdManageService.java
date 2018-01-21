package com.brightsoft.service.tms.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.AdvertisementInfoMapper;
import com.brightsoft.model.AdvertisementInfo;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class AdManageService {

	@Autowired
	private AdvertisementInfoMapper advertisementInfoMapper;
	/**
	 * 获取广告信息
	 * @param page
	 * @return
	 */ 
	public Result selectByCondition(Page<?> page,SearchParams searchParams){
		Result result = new  Result();
		int results = advertisementInfoMapper.countRowsByType(searchParams,Const.AD_PUBLISH_TYPE_TMS);
		List<AdvertisementInfo> list = advertisementInfoMapper.selectByTypeAndCondition(page,searchParams,Const.AD_PUBLISH_TYPE_TMS);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	/**
	 * 添加广告
	 * @param advertisementInfo
	 * @return
	 */
	public boolean insertAd(AdvertisementInfo advertisementInfo){
		boolean flag = false;
//		advertisementInfo.setStartTime(DateTools.string2Date(advertisementInfo.getStartTimeStr()));
//		advertisementInfo.setEndTime(DateTools.string2Date(advertisementInfo.getEndTimeStr()));
		advertisementInfo.setState(Const.TMS_AD_STATUS_VALID);
		if(advertisementInfoMapper.insert(advertisementInfo)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 验证日期是否可用
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Result select2VerifyDate(String startTime,String endTime,Integer adPosition){
		Result result = new Result();
		long currentTimeM = DateTools.getYMD().getTime();
		long startTimeM = DateTools.string2Date(startTime).getTime();
		if(startTimeM<currentTimeM){
			result.setMsg("起始时间不能小于当前时间值");
		}else{
			if(advertisementInfoMapper.verifyDate(startTime, endTime, adPosition)==0){
				result.setResult(true);
			}
		}
		return result;
	}
}
