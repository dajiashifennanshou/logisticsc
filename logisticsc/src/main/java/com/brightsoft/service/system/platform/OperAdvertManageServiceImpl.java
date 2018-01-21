package com.brightsoft.service.system.platform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.AdvertisementInfoMapper;
import com.brightsoft.model.AdvertisementInfo;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 2016年3月23日 上午11:07:07
 * 货运交易系统--广告管理
 * @author zhouna
 */
@Service
public class OperAdvertManageServiceImpl implements OperAdvertManageService {

	@Autowired
	private AdvertisementInfoMapper advertisementInfoMapper;

	/*** 通过id删除 */
	public Result advertManagementDelId(String idss) {
		Result result = new Result();
		try {
			ArrayList<Long> idslist = new ArrayList<Long>();
			if (idss != null) {
				idss = idss.substring(1, idss.length() - 1);
				String[] idArr = idss.split(",");
				for (int i = 0; i < idArr.length; i++) {
					if (idArr[i] == null && "".equals(idArr[i])) {
						break;
					}
					long ids = Long.parseLong(idArr[i]);
					idslist.add(ids);
					if (advertisementInfoMapper.advertManagementDelId(idslist) > 0) {
						result.setResult(true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}

	/***添加广告信息*/
	public boolean advertManagementsave(AdvertisementInfo advertisementInfo) {
		try {
			int count = advertisementInfoMapper.advertManagementsave(advertisementInfo);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public Result advertManagementlistdata(SearchParams searchParams,Page<?> page) {
		Result result = new  Result();
		try {
			int results = advertisementInfoMapper.countRowsByType(searchParams, Const.INFO_PUBLISH_TYPE_PLATFORM);
			List<AdvertisementInfo> list = advertisementInfoMapper.selectByTypeAndCondition(page, searchParams, Const.INFO_PUBLISH_TYPE_PLATFORM);
			result.setResults(results);
			result.setRows(list);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}

	public int advertManagementupdate(AdvertisementInfo advertisementInfo) {
		try {
			return advertisementInfoMapper.updateByPrimaryKey(advertisementInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
