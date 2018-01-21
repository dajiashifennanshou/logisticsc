package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PlatformCompanyinfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.utils.Page;

public interface PlatformUserCompanyMapper {
	/**
	 * 根据ID查询公司信息
	 * @param id
	 * @return
	 */
	public PlatformUserCompany selectCompanyInfo(@Param("id")Long id);
	
	/**
	 * 修改用户公司信息 - 没有图片
	 * @param companyInfo
	 * @return
	 */
	public int updateCompanyInfo(PlatformUserCompany companyInfo);
	/**
	 * 增加公司信息
	 * @param companyInfo
	 * @return
	 */
	public int insertCompanyInfo(PlatformUserCompany companyInfo);
	
	/**
	 * 根据网点ID 查询公司信息
	 * @param outletsId 网点ID
	 * @return
	 */
	public PlatformUserCompany selectByOutletsId(Long outletsId);
	/**
	 *  获取最大ID
	 * @return
	 */
	public Long selectCompanyMax();
	/**
	 * 查询企业信息
	 * @param page
	 * @param platformUserCompany
	 * @return
	 */
	public List<PlatformUserCompany> selectByCondition(@Param("page")Page<?>page,@Param("platformUserCompany")PlatformUserCompany platformUserCompany);
	/**
	 * 查询企业总记录数
	 * @param platformUserCompany
	 * @return
	 */
	public int countRows(@Param("platformUserCompany")PlatformUserCompany platformUserCompany);
	/**
	 * 修改公司上传图片
	 * @param companyInfo
	 * @return
	 */
	public int updateCompanyInfoImg(PlatformUserCompany companyInfo);
	/**
	 * 获取login图片
	 * @param id
	 * @return
	 */
	public String selectCompanyInfoLogo(@Param("id") Long id); 
	/**
	 * 公司照片
	 * @param id
	 * @return
	 */
	public String selectCompanyInfoCompanyPhoto(@Param("id") Long id); 
	/**
	 * 法定人身份证照片
	 * @param id
	 * @return
	 */
	public String selectCompanyInfoLegalPhoto(@Param("id") Long id); 
	/**
	 * 名片照片
	 * @param id
	 * @return
	 */
	public String selectCompanyInfoCardPhoto(@Param("id") Long id); 
	/**
	 * 营业执照图片
	 * @param id
	 * @return
	 */
	public String selectCompanyInfoBusinessLicense(@Param("id") Long id); 
	/**
	 * 获取物流提供商公司信息
	 * @param companyId
	 * @return
	 */
	public PlatformCompanyinfo selectLogisticsCompanyinfo(@Param("companyId") Long companyId);
	/**
	 * 分页查询商铺信息
	 * 2016年3月17日 下午1:27:02
	 * @param page
	 * @return 
	 * @author zhouna
	 */
	public List<PlatformCompanyinfo> getAllPlatformCompanyinfo(@Param("page")Page<?> page, @Param("platformUserCompany")PlatformUserCompany platformUserCompany);
	/***
	 * 通过recommended修改
	 *  启用  0  
	 *  停用  1
	 * 2016年3月17日 下午9:59:21
	 * @param platformUserCompany
	 * @return 
	 * @author zhouna
	 */
	public int updateCompanyinfochangeStatus(@Param("platformUserCompany")PlatformUserCompany platformUserCompany);
	/***
	 * 通过id查看详细信息
	 * 2016年3月17日 下午9:59:08
	 * @param companyId
	 * @return 
	 * @author zhouna
	 */
	public PlatformUserCompany platformCompanyById(@Param("id")Long id);

	/***
	 *专线商铺的总数
	 * 2016年3月17日 下午9:58:27
	 * @param platformUserCompany
	 * @return 
	 * @author zhouna
	 */
	public int countCompanyinfoRows(@Param("platformUserCompany")PlatformUserCompany platformUserCompany);

	
	/**
	 * 更新公司信息
	 * @param platformUserCompany
	 * @return
	 */
	public int updateByPrimaryKeySelective(PlatformUserCompany platformUserCompany);
	/**
	 * 货运交易系统：
	 * 根据用户类型获取公司信息
	 * @param userType
	 * @return
	 */
	public List<PlatformUserCompany> selectCompanyByUserType(@Param("userType")Integer userType,@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
	/**
	 * 货运交易系统：
	 * 根据用户类型获取公司信息记录数
	 * @param userType
	 * @return
	 */
	public int countRowsByUserType(@Param("userType")Integer userType,@Param("searchParams")SearchParams searchParams);
	
	/**
	 * 推荐物流商
	 * @return
	 */
	public List<PlatformUserCompany> selectLsitCompany();

	public List<PlatformUserCompany> selectAllCompany();
	
}