package com.brightsoft.service.platform;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.ApplyStateEnum;
import com.brightsoft.common.enums.ApplyTypeEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformUserApplyMapper;
import com.brightsoft.dao.platform.PlatformUserCompaninfoMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.platform.PlatformUserTemporaryCompanyMapper;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserApply;
import com.brightsoft.model.PlatformUserCompaninfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.User;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

@Service("platformUserCompanyService")
public class PlatformUserCompanyServiceImpl implements PlatformUserCompanyService{
	
	@Autowired
	public PlatformUserCompanyMapper companyMapper;
	
	@Autowired
	public PlatformUserMapper platformUserMapper;
	
	@Autowired
	public PlatformUserApplyMapper applyMapper;
	
	@Autowired
	public PlatformUserTemporaryCompanyMapper platformUserTemporaryCompanyMapper;
	
	@Autowired
	private PlatformUserCompaninfoMapper platformUserCompanyinfoMapper;
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	/**
	 * 获取公司信息
	 */
	public PlatformUserCompany selectCompanyInfo(Long id) {
		PlatformUserCompany userCompany =null;
		userCompany= companyMapper.selectCompanyInfo(id);
		if(userCompany != null){
			userCompany.setLogo(bundle.getString("baseUrl")
	                +userCompany.getLogo().replace("\"", "/"));
		userCompany.setBusinessLicense(bundle.getString("baseUrl")
                +userCompany.getBusinessLicense().replace("\"", "/"));
		userCompany.setCompanyPhoto(bundle.getString("baseUrl")
                +userCompany.getCompanyPhoto().replace("\\", "/"));
		userCompany.setLegalPhoto(bundle.getString("baseUrl")
                +userCompany.getLegalPhoto().replace("\\", "/"));
		userCompany.setCardPhoto(bundle.getString("baseUrl")
                +userCompany.getCardPhoto().replace("\\", "/"));
		}
		return userCompany;
	}
	/**
	 * 修改公司信息
	 */
	public boolean updateCompanyInfo(PlatformUserCompany companyInfo,PlatformUser user,PlatformUserTemporaryCompany platformUserTemporaryCompany) {
		if(user.getUserType()==Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR){
			PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(companyInfo.getId());
			if(companyInfo.getLogo() == null){
				companyInfo.setLogo(userCompany.getLogo());
			}
			if(companyInfo.getBusinessLicense() == null){
				companyInfo.setBusinessLicense(userCompany.getBusinessLicense());
			}
			if(companyInfo.getCompanyPhoto() == null){
				companyInfo.setCompanyPhoto(userCompany.getCompanyPhoto());
			}
			if(companyInfo.getLegalPhoto() == null){
				companyInfo.setLegalPhoto(userCompany.getLegalPhoto());
			}
			if(companyInfo.getCardPhoto() == null ){
				companyInfo.setCardPhoto(userCompany.getCardPhoto());
			}
			if(companyMapper.updateCompanyInfo(companyInfo) > 0){
				return true;
			}
		}else if(user.getUserType()==Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR){
			PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(companyInfo.getId());
			if(companyInfo.getLogo() == null){
				platformUserTemporaryCompany.setLogo(userCompany.getLogo());
			}else{
				platformUserTemporaryCompany.setLogo(companyInfo.getLogo());
			}
			if(companyInfo.getBusinessLicense() == null){
				platformUserTemporaryCompany.setBusinessLicense(userCompany.getBusinessLicense());
			}else{
				platformUserTemporaryCompany.setBusinessLicense(companyInfo.getBusinessLicense());
			}
			if(companyInfo.getCompanyPhoto() == null){
				platformUserTemporaryCompany.setCompanyPhoto(userCompany.getCompanyPhoto());
			}else{
				platformUserTemporaryCompany.setCompanyPhoto(companyInfo.getCompanyPhoto());
			}
			if(companyInfo.getLegalPhoto() == null){
				platformUserTemporaryCompany.setLegalPhoto(userCompany.getLegalPhoto());
			}else{
				platformUserTemporaryCompany.setLegalPhoto(companyInfo.getLegalPhoto());
			}
			if(companyInfo.getCardPhoto() == null ){
				platformUserTemporaryCompany.setCardPhoto(userCompany.getCardPhoto());
			}else{
				platformUserTemporaryCompany.setCardPhoto(companyInfo.getCardPhoto());
			}
			platformUserTemporaryCompany.setId(user.getTemporaryCompanyId());
			if(platformUserTemporaryCompanyMapper.updateTemporaryCompany(platformUserTemporaryCompany) > 0){
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApplyName(ApplyTypeEnum.MODIFY.getName());
				apply.setApplyType(ApplyTypeEnum.MODIFY.getValue());
				apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
				apply.setUserId(user.getId());
				apply.setTime(new Date());
				if(applyMapper.inserPlatformUserApply(apply)>0){
					Long id = apply.getId();
					apply.setVersion(id);
					applyMapper.updateByPrimaryKeySelective(apply);
					return true;
				}
			}
		}else if(user.getUserType()==Const.PLATFORM_USER_TYPE_LINE_COMPANY){
			PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(companyInfo.getId());
			if(companyInfo.getLogo() == null){
				platformUserTemporaryCompany.setLogo(userCompany.getLogo());
			}else{
				platformUserTemporaryCompany.setLogo(companyInfo.getLogo());
			}
			if(companyInfo.getBusinessLicense() == null){
				platformUserTemporaryCompany.setBusinessLicense(userCompany.getBusinessLicense());
			}else{
				platformUserTemporaryCompany.setBusinessLicense(companyInfo.getBusinessLicense());
			}
			if(companyInfo.getCompanyPhoto() == null){
				platformUserTemporaryCompany.setCompanyPhoto(userCompany.getCompanyPhoto());
			}else{
				platformUserTemporaryCompany.setCompanyPhoto(companyInfo.getCompanyPhoto());
			}
			if(companyInfo.getLegalPhoto() == null){
				platformUserTemporaryCompany.setLegalPhoto(userCompany.getLegalPhoto());
			}else{
				platformUserTemporaryCompany.setLegalPhoto(companyInfo.getLegalPhoto());
			}
			if(companyInfo.getCardPhoto() == null ){
				platformUserTemporaryCompany.setCardPhoto(userCompany.getCardPhoto());
			}else{
				platformUserTemporaryCompany.setCardPhoto(companyInfo.getCardPhoto());
			}
			platformUserTemporaryCompany.setId(user.getTemporaryCompanyId());
			if(platformUserTemporaryCompanyMapper.updateTemporaryCompany(platformUserTemporaryCompany) > 0){
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApplyName(ApplyTypeEnum.MODIFY.getName());
				apply.setApplyType(ApplyTypeEnum.MODIFY.getValue());
				apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
				apply.setUserId(user.getId());
				apply.setTime(new Date());
				if(applyMapper.inserPlatformUserApply(apply)>0){
					Long id = apply.getId();
					apply.setVersion(id);
					applyMapper.updateByPrimaryKeySelective(apply);
					return true;
				}
			}
		}else if(user.getUserType()==Const.PLATFORM_USER_TYPE_LINE_PROVIDER){
			PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(companyInfo.getId());
			if(companyInfo.getLogo() == null){
				platformUserTemporaryCompany.setLogo(userCompany.getLogo());
			}else{
				platformUserTemporaryCompany.setLogo(companyInfo.getLogo());
			}
			if(companyInfo.getBusinessLicense() == null){
				platformUserTemporaryCompany.setBusinessLicense(userCompany.getBusinessLicense());
			}else{
				platformUserTemporaryCompany.setBusinessLicense(companyInfo.getBusinessLicense());
			}
			if(companyInfo.getCompanyPhoto() == null){
				platformUserTemporaryCompany.setCompanyPhoto(userCompany.getCompanyPhoto());
			}else{
				platformUserTemporaryCompany.setCompanyPhoto(companyInfo.getCompanyPhoto());
			}
			if(companyInfo.getLegalPhoto() == null){
				platformUserTemporaryCompany.setLegalPhoto(userCompany.getLegalPhoto());
			}else{
				platformUserTemporaryCompany.setLegalPhoto(companyInfo.getLegalPhoto());
			}
			if(companyInfo.getCardPhoto() == null ){
				platformUserTemporaryCompany.setCardPhoto(userCompany.getCardPhoto());
			}else{
				platformUserTemporaryCompany.setCardPhoto(companyInfo.getCardPhoto());
			}
			platformUserTemporaryCompany.setId(user.getTemporaryCompanyId());
			if(platformUserTemporaryCompanyMapper.updateTemporaryCompany(platformUserTemporaryCompany) > 0){
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApplyName(ApplyTypeEnum.MODIFY.getName());
				apply.setApplyType(ApplyTypeEnum.MODIFY.getValue());
				apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
				apply.setUserId(user.getId());
				apply.setTime(new Date());
				if(applyMapper.inserPlatformUserApply(apply)>0){
					Long id = apply.getId();
					apply.setVersion(id);
					applyMapper.updateByPrimaryKeySelective(apply);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 增加公司信息
	 */
	public boolean insertCompanyInfo(PlatformUserCompany companyInfo,Long userId) {
		Random random = new Random();
		int code = random.nextInt(899999);
		String codeStr=String.valueOf(code+100000);
		companyInfo.setCompanyCode(codeStr);
		if(companyMapper.insertCompanyInfo(companyInfo) > 0){
			Long companyId = companyMapper.selectCompanyMax();
			if(platformUserMapper.updatecompanyId(companyId,userId) > 0){
				return true;
			}
		}else{
			return false;
		}
		return true;
	}
	/**
	 * 获取企业信息
	 */
	public Result selectByCondition(Page<?> page,PlatformUserCompany platformUserCompany){
		Result result = new Result();
		int results = companyMapper.countRows(platformUserCompany);
		List<PlatformUserCompany> rows = companyMapper.selectByCondition(page, platformUserCompany);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 上传公司图片
	 */
	public boolean uploadCompanInfoImg(String dirName, File file, Long id,
			Integer imgType) {
		Map<String, String> map = UploadFileUtil.uploadImage(dirName,file);
		PlatformUserCompany company = new PlatformUserCompany();
		if (!map.isEmpty()) {
			company.setId(id);
			//获取图片路径
			String originalUrl = map.get(UploadFileUtil.ORIGINALURL);
			if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_0){
				company.setLogo(originalUrl);
			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_1){
				company.setBusinessLicense(originalUrl);
			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_2){
				company.setCompanyPhoto(originalUrl);
			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_3){
				company.setLegalPhoto(originalUrl);
			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_4){
				company.setCardPhoto(originalUrl);
			}
		}else{
			return false;
		}
		if(companyMapper.updateCompanyInfoImg(company)>0){
			return true;
		}
		return false;
	}
	/**
	 * 获取公司图片信息
	 */
	public PlatformUserCompany selectCompanInfoImg(Long id, Integer imgType) {
		PlatformUserCompany company = new PlatformUserCompany();
		if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_0){
			company.setLogo(bundle.getString("baseUrl")
                    +companyMapper.selectCompanyInfoLogo(id).replace("\"", "/"));
		}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_1){
			company.setBusinessLicense(bundle.getString("baseUrl")
                    +companyMapper.selectCompanyInfoBusinessLicense(id).replace("\"", "/"));
		}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_2){
			company.setCompanyPhoto(bundle.getString("baseUrl")
                    +companyMapper.selectCompanyInfoCompanyPhoto(id).replace("\\", "/"));
		}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_3){
			company.setLegalPhoto(bundle.getString("baseUrl")
                    +companyMapper.selectCompanyInfoLegalPhoto(id).replace("\\", "/"));
		}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_4){
			company.setCardPhoto(bundle.getString("baseUrl")
                    +companyMapper.selectCompanyInfoCardPhoto(id).replace("\\", "/"));
		}
		return company;
	}
	/**
	 * 申请企业货主
	 */
	public boolean applyEnterprise(Long id, Long UserId,PlatformUserCompany company) {
		return false;
	}
	/**
	 * 验证用户是否存在公司信息审核中
	 * true 没有查到当前用户需要审核修改信息
	 */
	public boolean verifUserCompany(PlatformUser user) {
		if(user.getUserType()==Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR){
			return true;
		}else{
			if(applyMapper.selectMacState(user.getId()) == 0){
				return false;
			}else{
				return true;
			}
		}
	}
	/**
	 * tms:
	 * 更新商铺信息
	 */
	public Result updateCompanyInfoOnly(User user,PlatformUserCompany platformUserCompany,PlatformUserCompaninfo platformUserCompanyinfo) {
		Result result = new Result();
		if(user != null && user.getOutletsId() == null){
			platformUserCompany.setId(platformUserCompany.getCompanyId());
			if(companyMapper.updateByPrimaryKeySelective(platformUserCompany)>0){
				platformUserCompanyinfo.setId(platformUserCompanyinfo.getCompanyInfoId());
				Double annuMoney = platformUserCompanyinfo.getAnnualMoney();
				if(annuMoney != null){
					platformUserCompanyinfo.setAnnualMoney((double)Math.round(annuMoney*100)/100);
				}
				if(platformUserCompanyinfo.getId()!=null){
					if(platformUserCompanyinfoMapper.updateByPrimaryKeySelective(platformUserCompanyinfo)>0){
						result.setResult(true);
					}
				}else{
					platformUserCompanyinfo.setCompanyId(platformUserCompany.getId());
					if(platformUserCompanyinfoMapper.insert(platformUserCompanyinfo)>0){
						result.setResult(true);
					}
				}
			}
		}else{
			result.setMsg("你没有权限执行当前操作！");
		}
		
		return result;
	}
	/**
	 * 货运交易系统；
	 * 获取会员信息
	 * @param userType
	 * @param page
	 * @return
	 */
	public Result selectCompany(Integer userType,SearchParams searchParams,Page<?> page) {
		Result result = new Result();
		int results = companyMapper.countRowsByUserType(userType,searchParams);
		List<PlatformUserCompany> rows = companyMapper.selectCompanyByUserType(userType,searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 推荐物流商
	 */
	public List<PlatformUserCompany> selectCompany() {
		List<PlatformUserCompany> platformUserCompanies = companyMapper.selectLsitCompany();
		return platformUserCompanies;
	}
	public List<PlatformUserCompany> selectAllCompany() {
		return companyMapper.selectAllCompany();
	}
}
