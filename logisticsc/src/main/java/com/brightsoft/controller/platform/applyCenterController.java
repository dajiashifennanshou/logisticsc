package com.brightsoft.controller.platform;

import java.io.File;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.platformBondConfigure;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.platform.PlatformUserApplyServiceImpl;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.platform.platformBankPaymentServiceImpl;
import com.brightsoft.service.platform.platformBondConfigureServiceImpl;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

/**
 * 申请中心
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/applyCenter")
public class applyCenterController {
	
	@Autowired
	private PlatformUserService platformUserService;
	
	@Autowired
	private PlatformUserCompanyService companyService;
	
	@Autowired
	private PlatformUserApplyServiceImpl applyServiceImpl;
	
	@Autowired 
	private platformBankPaymentServiceImpl platformBankPaymentServiceImpl; 
	
	@Autowired
	private platformBondConfigureServiceImpl platformBondConfigureServiceImpl;
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	/**
	 * 跳转到申请企业货主
	 * @return
	 */
	@RequestMapping("/toorderEnterpriseOwner")
	@RepeatSubmission(needSaveToken = true)
	public String toorderEnterpriseOwner(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(applyServiceImpl.selectMaxSatet(user.getId())){
			return "/platform/applyCenter/apply";
		}else{
			return "/platform/applyCenter/enterprise_owner";
		}
	}
	/**
	 * 跳转到申请企业货主流程图
	 * @return
	 */
	@RequestMapping("/toorderEnterpriseFlow")
	public String toorderEnterpriseFlow(String chooseid,ModelMap model){
		model.addAttribute("chooseid",chooseid);
		return "/platform/applyCenter/enterprise_flow";
	}
	/**
	 * 跳转到申请物流商
	 * @return
	 */
	@RequestMapping("/toorderProvider")
	@RepeatSubmission(needSaveToken = true)
	public String toorderProvider(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(applyServiceImpl.selectMaxSatet(user.getId())){
			return "/platform/applyCenter/apply";
		}else{
			return "/platform/applyCenter/provider";
		}
	}
	/**
	 * 跳转到申请物流商流程图
	 * @return
	 */
	@RequestMapping("/toorderProviderFlow")
	public String toorderProviderFlow(String chooseid,ModelMap model){
		model.addAttribute("chooseid",chooseid);
		return "/platform/applyCenter/provider_flow";
	}
	/**
	 * 跳转到专线流程图
	 * @return
	 */
	@RequestMapping("/todedicatedLineFlow")
	public String todedicatedLineFlow(){
		return "/platform/applyCenter/dedicatedLine_flow";
	}
	/**
	 * 跳转到申请专线
	 * @return
	 */
	@RequestMapping("/todedicatedLine")
	@RepeatSubmission(needSaveToken = true)
	public String todedicatedLine(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(applyServiceImpl.selectMaxSatet(user.getId())){
			return "/platform/applyCenter/apply";
		}else{
			return "/platform/applyCenter/dedicated_line";
		}
		
	}
	/**
	 * 跳转到验证页面
	 * @return
	 */
	@RequestMapping("/toorderApply")
	public String toorderApply(){
		return "/platform/applyCenter/apply";
	}
	/**
	 * 申请专线
	 * @param company
	 * @param session
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/applydedicatedLine")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public Result applydedicatedLine(PlatformUserTemporaryCompany company,HttpSession session,HttpServletRequest request)
			throws IllegalStateException,IOException{
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(request instanceof MultipartHttpServletRequest){
			for (int i = 0; i < 5; i++) {
				 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
	             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
	             if(null == multipartFile || multipartFile.isEmpty()){
	                 continue;
	             }else if(multipartFile.getSize() > 512000){
		            	ret.setMsg("上传图片不能大于512KB!");
		     			ret.setResult(false);
		     			return ret;
		             }else{
	            	 // 获得图片名称
	                 String fileName = multipartFile.getOriginalFilename();
	                 // 获取图片后缀名称
	                 String suffixName = UploadFileUtil.getFileSuffixName(fileName);
	                 //获取正确的图片格式
	                 String reg = bundle.getString("reg");
	                 if(suffixName.matches(reg)){
	                	 if(("fileName"+i).equals("fileName0")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadBusinessLicense"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setBusinessLicense(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName2")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadLegalPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setLegalPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName1")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadCompanyPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setCompanyPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName3")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadCardPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setCardPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName4")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadLogin"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setLogo(originalUrl);
	                	 }
	                 }
	             }
			}
		}
		if(platformUserService.applydedicatedLine(user.getId(), company)){
			ret.setMsg("申请专线成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("申请专线失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 申请企业货主
	 * @return
	 */
	@RequestMapping("/applyEnterprise")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public Result applyEnterprise(PlatformUserTemporaryCompany company,HttpSession session,HttpServletRequest request)
			throws IllegalStateException,IOException{
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(request instanceof MultipartHttpServletRequest){
			for (int i = 0; i < 5; i++) {
				 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
	             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
	             if(null == multipartFile || multipartFile.isEmpty()){
	                 continue;
	             }else if(multipartFile.getSize() > 512000){
	            	ret.setMsg("上传图片不能大于512KB!");
	     			ret.setResult(false);
	     			return ret;
	             }else{
	            	 // 获得图片名称
	                 String fileName = multipartFile.getOriginalFilename();
	                 // 获取图片后缀名称
	                 String suffixName = UploadFileUtil.getFileSuffixName(fileName);
	                 //获取正确的图片格式
	                 String reg = bundle.getString("reg");
	                 if(suffixName.matches(reg)){
	                	 if(("fileName"+i).equals("fileName0")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadBusinessLicense"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setBusinessLicense(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName2")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadLegalPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setLegalPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName1")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadCompanyPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setCompanyPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName3")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadCardPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setCardPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName4")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadLogin"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setLogo(originalUrl);
	                	 }
	                 }
	             }
			}
		}
		if(platformUserService.applyEnterprise(user.getId(), company)){
			ret.setMsg("申请企业货主成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("申请企业货主失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 申请物流商
	 * @param company
	 * @param session
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/applyProvider")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public Result applyProvider(PlatformUserTemporaryCompany company,HttpSession session,HttpServletRequest request)
			throws IllegalStateException,IOException{
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(request instanceof MultipartHttpServletRequest){
			for (int i = 0; i < 5; i++) {
				 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
	             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
	             if(null == multipartFile || multipartFile.isEmpty()){
	                 continue;
	             }else if(multipartFile.getSize() > 512000){
		            	ret.setMsg("上传图片不能大于512KB!");
		     			ret.setResult(false);
		     			return ret;
		             }else{
	            	 // 获得图片名称
	                 String fileName = multipartFile.getOriginalFilename();
	                 // 获取图片后缀名称
	                 String suffixName = UploadFileUtil.getFileSuffixName(fileName);
	                 //获取正确的图片格式
	                 String reg = bundle.getString("reg");
	                 if(suffixName.matches(reg)){
	                	 if(("fileName"+i).equals("fileName0")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadBusinessLicense"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setBusinessLicense(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName2")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadLegalPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setLegalPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName1")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadCompanyPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setCompanyPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName3")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadCardPhoto"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setCardPhoto(originalUrl);
	                	 }else if(("fileName"+i).equals("fileName4")){
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
		                                + bundle.getString("uploadLogin"), fileName);
		                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
		                     multipartFile.transferTo(file);
		                     String originalUrl =platformUserService.uploadFile(dirName, file);
		                     company.setLogo(originalUrl);
	                	 }
	                 }
	             }
			}
		}
		if(platformUserService.applyProvider(user.getId(),company)){
			ret.setMsg("申请物流提供商成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("申请物流提供商失败!");
			ret.setResult(false);
		}
		return ret;
	}
//	/**
//	 * 获取当前用户是否存在申请
//	 * @param userId
//	 * @return
//	 */
//	@RequestMapping("/getApplyState")
//	@ResponseBody
//	public Result getApplyState(Long userId){
//		Result ret = new Result();
//		int  applyType = ApplyTypeEnum.ENTERPRISEOWNER.getValue();
//		if(applyServiceImpl.selectMaxSatet(userId,applyType)){
//			ret.setResult(true);
//		}else{
//			ret.setResult(false);
//		}
//		return ret;
//	}
	/**
	 * 验证是否交纳保证金
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserBond")
	@ResponseBody
	public Result getUserBond(HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(platformBankPaymentServiceImpl.selectBankPaymentByUserId(user.getId())){
			ret.setMsg("已交保证金!");
			ret.setResult(true);
		}else{ 
			ret.setMsg("未交保证金!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 验证是否绑定银行卡
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserCard")
	@ResponseBody
	public Result getUserCard(HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(applyServiceImpl.selectCard(user.getId())){
			ret.setMsg("已绑定银行卡!");
			ret.setResult(true);
		}else{
			ret.setMsg("未绑定银行卡!");
			ret.setResult(false);
		}
		return ret;
	}
//	/**
//	 * 支付保证金
//	 * @param session
//	 * @param bond
//	 * @return
//	 */
//	@RequestMapping("/doinsertUserBond")
//	@ResponseBody
//	public Result doinsertUserBond(HttpSession session,PlatformUserBond bond){
//		Result ret = new Result();
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		bond.setUserId(user.getId());
//		user =applyServiceImpl.insertUserBond(bond);
//		if(null != user){
//			session.setAttribute(SystemConstant.USER_SESSION, user);
//			ret.setMsg("支付保证金成功!");
//			ret.setResult(true);
//		}else{
//			ret.setMsg("支付保证金失败!");
//			ret.setResult(false);
//		}
//		return ret;
//	}
	@RequestMapping("/getBond")
	@ResponseBody
	public Result getBond(){
		Result ret = new Result();
		platformBondConfigure bondConfigure =null;
		bondConfigure= platformBondConfigureServiceImpl.selectBond();
		if(null == bondConfigure){
			ret.setResult(false);
		}else{
			ret.setData(bondConfigure);
			ret.setResult(true);
		}
		return ret;
	}
}
