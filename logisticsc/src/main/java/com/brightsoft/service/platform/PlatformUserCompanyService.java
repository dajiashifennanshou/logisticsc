package com.brightsoft.service.platform;

import java.io.File;
import java.util.List;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCompaninfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * @author ThinkPad
 *
 */
public interface PlatformUserCompanyService {

	/**
	 * 获取公司信息
	 * @param id
	 * @return
	 */
	public PlatformUserCompany selectCompanyInfo(Long id);
	
	/**
	 * 修改公司信息
	 * @param companyInfo
	 * @return
	 */
	public boolean updateCompanyInfo(PlatformUserCompany companyInfo,PlatformUser user,PlatformUserTemporaryCompany platformUserTemporaryCompany);
	/**
	 * 增加公司信息
	 * @param companyInfo
	 * @return
	 */
	public boolean insertCompanyInfo(PlatformUserCompany companyInfo,Long userId);
	/**
	 * 查询企业信息
	 * @param page
	 * @param platformUserCompany
	 * @return
	 */
	public Result selectByCondition(Page<?> page,PlatformUserCompany platformUserCompany);
	/**
	 * 上传图片公司
	 * @param dirName
	 * @param file
	 * @param id
	 * @param imgType
	 * @return
	 */
	public boolean uploadCompanInfoImg(String dirName, File file, Long id,Integer imgType);
	/**
	 * 获取公司图片信息
	 * @param id
	 * @param imgType
	 * @return
	 */
	public PlatformUserCompany selectCompanInfoImg(Long id,Integer imgType);
	/**
	 * 申请企业货主
	 * @param id
	 * @param company
	 * @return
	 */
	public boolean applyEnterprise(Long id,Long UserId,PlatformUserCompany company);
	/**
	 * 验证用户是否存在修改公司信息审核中
	 * @param user
	 * @return
	 */
	public boolean verifUserCompany(PlatformUser user);
	/**
	 * tms:
	 * 修改商铺信息
	 * @param platformUserCompany
	 * @return
	 */
	public Result updateCompanyInfoOnly(User user,PlatformUserCompany platformUserCompany,PlatformUserCompaninfo platformUserCompanyinfo);
	/**
	 * 货运交易系统：
	 * 获取会员
	 * @return
	 */
	public Result selectCompany(Integer userType,SearchParams searchParams,Page<?> page);
	
	public List<PlatformUserCompany> selectCompany();
	
	public List<PlatformUserCompany> selectAllCompany();
}
