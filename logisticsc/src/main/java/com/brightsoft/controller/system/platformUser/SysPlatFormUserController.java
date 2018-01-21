package com.brightsoft.controller.system.platformUser;

import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.sysVoUser;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.SendMsg;

@Controller
@RequestMapping("/sysPlatformUser")
public class SysPlatFormUserController {

	@Autowired
	private PlatformUserService platformUserService;
	
	@Autowired
	private PlatformUserCompanyService companyService;
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	/**
	 * 获取待审核
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getVerifyUserList",method= RequestMethod.POST)
	@ResponseBody
	public Result getVerifyUserList(PlatformUser user,Page<?> page){
		Result result= platformUserService.getVerifyUserList(user,page);
		result.setResult(true);
		return result;
	}
	/**
	 * 查看申请信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getVerifyUser")
	public ModelAndView getVerifyUser(Long id){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> verifyUser =platformUserService.doGetVerifyUser(id);
		if(verifyUser != null){
			
		verifyUser.put("legal_photo",(bundle.getString("baseUrl")
                +verifyUser.get("legal_photo").toString().replace("\\", "/")));
		
		verifyUser.put("company_photo",(bundle.getString("baseUrl")
				+verifyUser.get("company_photo").toString().replace("\\", "/")));
		
		verifyUser.put("business_license",(bundle.getString("baseUrl")
				+verifyUser.get("business_license").toString().replace("\\", "/")));
		
		verifyUser.put("card_photo",(bundle.getString("baseUrl")
				+verifyUser.get("card_photo").toString().replace("\\", "/")));
		
		verifyUser.put("logo",(bundle.getString("baseUrl")
				+verifyUser.get("logo").toString().replace("\\", "/")));
		}
		mv.addObject("data", verifyUser);
		if(verifyUser.get("user_type") != null){
			int userType =(Integer) verifyUser.get("user_type");
			if(userType ==4 || userType==3){
				mv.setViewName("/system/platformUser/select_user_provider");
			}else if(userType ==2 || userType ==1){
				mv.setViewName("/system/platformUser/select_user_provider");
			}else{
				mv.setViewName("");
			}
		}
		return mv;
	}
	
	
	/**
	 * 查看企业公司信息--- 企业
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getUserCompanyEnterprise")
	public ModelAndView getUserCompanyEnterprise(Long id){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> verifyUser =platformUserService.UserCompanyEnterprise(id);
		if(verifyUser != null){
		verifyUser.put("legal_photo",(bundle.getString("baseUrl")
                +verifyUser.get("legal_photo").toString().replace("\\", "/")));
		
		verifyUser.put("company_photo",(bundle.getString("baseUrl")
				+verifyUser.get("company_photo").toString().replace("\\", "/")));
		
		verifyUser.put("business_license",(bundle.getString("baseUrl")
				+verifyUser.get("business_license").toString().replace("\\", "/")));
		
		verifyUser.put("card_photo",(bundle.getString("baseUrl")
				+verifyUser.get("card_photo").toString().replace("\\", "/")));
		
		verifyUser.put("logo",(bundle.getString("baseUrl")
				+verifyUser.get("logo").toString().replace("\\", "/")));
		}
		mv.addObject("data", verifyUser);
		mv.setViewName("/system/platformUser/select_user_enterprise");
		return mv;
	}
	
	/**
	 * 查看企业公司信息--- 专线
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getUserCompanyLine")
	public ModelAndView getUserCompanyLine(Long id){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> verifyUser =platformUserService.UserCompanyEnterprise(id);
		if(verifyUser != null){
		verifyUser.put("legal_photo",(bundle.getString("baseUrl")
                +verifyUser.get("legal_photo").toString().replace("\\", "/")));
		
		verifyUser.put("company_photo",(bundle.getString("baseUrl")
				+verifyUser.get("company_photo").toString().replace("\\", "/")));
		
		verifyUser.put("business_license",(bundle.getString("baseUrl")
				+verifyUser.get("business_license").toString().replace("\\", "/")));
		
		verifyUser.put("card_photo",(bundle.getString("baseUrl")
				+verifyUser.get("card_photo").toString().replace("\\", "/")));
		
		verifyUser.put("logo",(bundle.getString("baseUrl")
				+verifyUser.get("logo").toString().replace("\\", "/")));
		}
		mv.addObject("data", verifyUser);
		mv.setViewName("/system/platformUser/select_user_lien");
		return mv;
	}
	/**
	 * 查看企业公司信息--物流商
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getUserCompanyProvider")
	public ModelAndView getUserCompanyProvider(Long id){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> verifyUser =platformUserService.UserCompanyEnterprise(id);
		if(verifyUser != null){
		verifyUser.put("legal_photo",(bundle.getString("baseUrl")
                +verifyUser.get("legal_photo").toString().replace("\\", "/")));
		
		verifyUser.put("company_photo",(bundle.getString("baseUrl")
				+verifyUser.get("company_photo").toString().replace("\\", "/")));
		
		verifyUser.put("business_license",(bundle.getString("baseUrl")
				+verifyUser.get("business_license").toString().replace("\\", "/")));
		
		verifyUser.put("card_photo",(bundle.getString("baseUrl")
				+verifyUser.get("card_photo").toString().replace("\\", "/")));
		
		verifyUser.put("logo",(bundle.getString("baseUrl")
				+verifyUser.get("logo").toString().replace("\\", "/")));
		}
		mv.addObject("data", verifyUser);
		mv.setViewName("/system/platformUser/select_user_providers");
		return mv;
	}
	
	/**
	 * 修改评论状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/doGetVerifyUser")
	public ModelAndView doGetVerifyUser(Long id){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> verifyUser =platformUserService.doGetVerifyUser(id);
		if(verifyUser != null){
			
		verifyUser.put("legal_photo",(bundle.getString("baseUrl")
                +verifyUser.get("legal_photo").toString().replace("\\", "/")));
		
		verifyUser.put("company_photo",(bundle.getString("baseUrl")
				+verifyUser.get("company_photo").toString().replace("\\", "/")));
		
		verifyUser.put("business_license",(bundle.getString("baseUrl")
				+verifyUser.get("business_license").toString().replace("\\", "/")));
		
		verifyUser.put("card_photo",(bundle.getString("baseUrl")
				+verifyUser.get("card_photo").toString().replace("\\", "/")));
		
		verifyUser.put("logo",(bundle.getString("baseUrl")
				+verifyUser.get("logo").toString().replace("\\", "/")));
		}
		mv.addObject("data", verifyUser);
		if(verifyUser.get("user_type") != null){
			int userType =(Integer) verifyUser.get("user_type");
			if(userType ==4 || userType==3){
				mv.setViewName("/system/platformUser/verify_provider");
			}else if(userType ==2 || userType ==1){
				mv.setViewName("/system/platformUser/verify_enterprise_owner");
			}else{
				mv.setViewName("");
			}
		}
		return mv;
	}
	
	/**
	 * 获取待审核
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateVerifyUser",method= RequestMethod.POST)
	@ResponseBody
	public Result updateVerifyUser(Long userId ,Long temCompanyId,boolean pass){
		
		Result result = new Result();
		PlatformUserTemporaryCompany plaUserTemporaryCompany =  platformUserService.selectPlatformUserTemporaryCompany(temCompanyId);
		PlatformUser platformUser = platformUserService.selectUser(userId);
		boolean userResult = platformUserService.updateVerifyUser(userId,platformUser, plaUserTemporaryCompany,pass);
		if(userResult){
			if(pass){
				SendMsg.sendVerification(platformUser.getMobile(),"已通过",Const.PLATFORM_SENDTYPE_2);
			}else{
				SendMsg.sendVerification(platformUser.getMobile(),"未通过",Const.PLATFORM_SENDTYPE_2);
			}
		}
		//List<Map<String,Object>> list = platformUserService.getVerifyUserList();
		result.setResult(userResult);
		return result;
	}
	
	/**
	 * 获取个人货主
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getPersonalPlatformUser",method= RequestMethod.POST)
	@ResponseBody
	public Result getPersonalPlatformUser(sysVoUser sysVoUser,Page<?> page){
		sysVoUser.setUserType(Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR);
		Result result = platformUserService.getPlatformUserByUserType(sysVoUser, page);
		result.setResult(true);
		return result;
	}
	
	
	
	/**
	 * 获取企业货主
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getEnterprisePlatformUser",method= RequestMethod.POST)
	@ResponseBody
	public Result getEnterprisePlatformUser(sysVoUser sysVoUser,Page<?> page){
		sysVoUser.setUserType(Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR);
		Result result = platformUserService.getPlatformUserByUserType(sysVoUser, page);
		result.setResult(true);
		return result;
	}
	
	
	/**
	 * 修改用户状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updatePlatformUserStatus",method= RequestMethod.POST)
	@ResponseBody
	public Result updatePlatformUserStatus(Long userId){
		
		Result result = new Result();
		
		PlatformUser platformUser = platformUserService.selectUser(userId);
		if(platformUser.getStatus()==Const.STATE_YES){
			platformUser.setStatus(Const.STATE_NO);
		}else if(platformUser.getStatus()==Const.STATE_NO){
			platformUser.setStatus(Const.STATE_YES);
		}
		int i = platformUserService.updateByPrimaryKeySelective(platformUser);
		if(i==1){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 获取专线
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getLinePlatformUser",method= RequestMethod.POST)
	@ResponseBody
	public Result getLinePlatformUser(sysVoUser sysVoUser,Page<?> page){
		sysVoUser.setUserType(Const.PLATFORM_USER_TYPE_LINE_COMPANY);
		Result result = platformUserService.getPlatformUserByUserType(sysVoUser, page);
		result.setResult(true);
		return result;
	}
	
	
	/**
	 * 获取物流商
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getProviderPlatformUser",method= RequestMethod.POST)
	@ResponseBody
	public Result getProviderPlatformUser(sysVoUser sysVoUser,Page<?> page){
		sysVoUser.setUserType(Const.PLATFORM_USER_TYPE_LINE_PROVIDER);
		Result result = platformUserService.getPlatformUserByUserType(sysVoUser, page);
		result.setResult(true);
		return result;
	}
}
