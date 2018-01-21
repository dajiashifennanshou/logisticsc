package com.brightsoft.service.system.platform;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.AdvertisementInfo;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 货运交易系统--广告管理
 * 2016年3月23日 上午11:06:07
 * @author zhouna
 */
public interface OperAdvertManageService {

	/***通过id删除*/
	Result advertManagementDelId(String idss);

	/***添加广告信息*/
	boolean advertManagementsave(AdvertisementInfo advertisementInfo);

	/**查询货运交易系统类型的广告信息**/
	Result advertManagementlistdata(SearchParams searchParams, Page<?> page);

	/**修改货运交易系统广告信息*/
	int advertManagementupdate(AdvertisementInfo advertisementInfo);

}
