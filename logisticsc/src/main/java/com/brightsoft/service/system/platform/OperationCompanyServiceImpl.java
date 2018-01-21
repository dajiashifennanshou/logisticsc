package com.brightsoft.service.system.platform;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformCompanyinfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 2016年3月17日 下午12:25:47
 *  货运交易系统--专线商铺管理
 * @author zhouna
 */
@Service
public class OperationCompanyServiceImpl implements OperationCompanyService {
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	/***
	 * 分页查询专线商铺的信息
	 */
	public Result getAllPlatformCompanyinfo(Page<?> page , PlatformUserCompany platformUserCompany) {
		Result result = new Result();
		int results = platformUserCompanyMapper.countCompanyinfoRows(platformUserCompany);
		List<PlatformCompanyinfo> list = platformUserCompanyMapper.getAllPlatformCompanyinfo(page,platformUserCompany);
		result.setResults(results);
		result.setRows(list);
		result.setResult(true);
		return result;
	}

	/**
	 * 通过id查看详细信息
	 * */
	public PlatformUserCompany platformCompanyById(Long id) {
		if (id != null) {
			return platformUserCompanyMapper.platformCompanyById(id);
		}
		return null;
	}

	
	/**通过recommended修改
	 *  启用  0  
	 *  停用  1 
	 *  */
	public Integer CompanyinfochangeStatus(PlatformUserCompany platformUserCompany) {
		Integer count = null ;
		try {
			if (platformUserCompany.getId()!= null && (platformUserCompany.getRecommended()==0)
					|| platformUserCompany.getRecommended()==1) {
				count = platformUserCompanyMapper.updateCompanyinfochangeStatus(platformUserCompany);
				return count;
			} 
		} catch (Exception e) {
			count = null;
			e.printStackTrace();
		}
		return count;
	}

	/**当前公司的信息*/
	public Result getlineshopCompang(User user, OutletsInfo outletsInfo,Page<?> page) {
		return outletsService.getlineshopCompang(user, outletsInfo, page);
	}
	
	/**获取当前线路*/
	public Result getlineshoplines(User user, LineInfo lineInfo, Page<?> page) {
		return lineInfoService.selectByCondition(user, lineInfo, page);
	}
}
