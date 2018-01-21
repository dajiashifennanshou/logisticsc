package com.brightsoft.controller.platform;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.Constant.Constant;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.utils.CodeImageUtil;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;
import com.brightsoft.utils.yc.FengUtil;
/**
 * 用户
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/user")
public class PlatformUserController extends BaseController{

	@Autowired
	private PlatformUserService platformUserService;
	
	@Autowired
	private PlatformUserCompanyService companyService;
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	/**
	 * 跳转货主注册
	 * @return
	 */
	@RequestMapping("/jumpreGister")
	public ModelAndView jumpreGister(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/personal_register");
		return mv;
	}
	/**
	 * 跳转到企业货主注册
	 * @return
	 */
	@RequestMapping("/jumpreEnterprise_register")
	public ModelAndView jumpreEnterprise_register(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/enterprise_register");
		return mv;
	}
	/**
	 * 专线注册
	 * @return
	 */
	@RequestMapping("/jumpreDedicatedLine_register")
	public ModelAndView jumpreDedicatedLine_register(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/dedicatedLine_register");
		return mv;
	}
	/**
	 * 跳转到忘记密码
	 * @return
	 */
	@RequestMapping("/jumpreForgetPassword")
	public ModelAndView jumpreForgetPassword(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/forget_password");
		return mv;
	}
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/outUser")
	public ModelAndView outUser(HttpSession session){
		ModelAndView mv = new ModelAndView();
		session.removeAttribute(SystemConstant.USER_SESSION);
		session.removeAttribute(SystemConstant.TMS_USER_SESSION);
		mv.setViewName("../../../main");
		return mv;
	}
	
	/**
     * 
     * 
     * @Title: getVerCode
     * @Description:生成验证码并缓存
     * @param userName
     *            用户名
     * @param response
     * @throws IOException
     *             void
     */
    @RequestMapping(value = "/getVerCode")
    @ResponseBody
    public void getVerCode(HttpServletResponse response,HttpServletRequest request) throws IOException{
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        // 指定生成的响应是图片
        response.setContentType("image/jpeg");
        // 生成验证码
        String codeNumber = CodeImageUtil.createCodeNumber();
        System.out.println("【验证码：】" +codeNumber);
        // 生成验证码图片   
        BufferedImage image = CodeImageUtil.createCodeImg(codeNumber);
        // 保存验证码至缓存中
        HttpSession session = request.getSession();
        session.setAttribute("code",codeNumber.toString());
        image.getGraphics().dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
    /**
     * 根据id获取用户信息
     * Author:FENG
     * 2016年7月20日
     * @param response
     * @param request
     * @param pu
     */
    @RequestMapping("/getPlatUserById")
    public void getSingleInfo(HttpServletResponse response,HttpServletRequest request,PlatformUser pu){
    	try{
    		pu=platformUserService.selectUser(pu.getId());
    		FengUtil.OUTAPPSUCCESS(Constant.RESULT_SUCCESS_CODE, pu, response);
    	}catch(Exception e){
    		e.printStackTrace();
    		FengUtil.OUTADDERROR(e.getLocalizedMessage(), Constant.RESULT_ERROR_CODE, response);
    	}
    }
	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Result register(PlatformUser user,HttpSession session,String code) {
		Result ret = new Result();
		if(null == user.getLoginName() || "".equals(user.getLoginName())
				&& null == user.getPassword() || "".equals(user.getPassword())){
			ret.setResult(false);
			ret.setMsg("必填项！");;
		}else if(code.equals(session.getAttribute(user.getLoginName()))){
			user = platformUserService.register(user);
			if(null != user){
				session.removeAttribute(user.getLoginName());
				session.setAttribute(SystemConstant.USER_SESSION, user);
				ret.setMsg("注册成功!");
				ret.setResult(true);
			}else{
				ret.setMsg("注册失败!");
				ret.setResult(false);
			}
		}else{
			ret.setMsg("注册失败,验证码错误！");
			ret.setResult(false);
		}
		return ret;
	}
	
	/**
	 * 验证 验证码是否正确
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping("/validcode")
	@ResponseBody
	public boolean validCode(String code, HttpSession session){
		if(code.equals(session.getAttribute("code"))){
			return true;
		}
		return false;
	}
	
	/**
	 * 短信验证
	 * @param loginName
	 * @return
	 */
	@RequestMapping("/verificationMessage")
	@ResponseBody
	public Result verificationMessage(String  loginName,String validCode,HttpSession session){
		Result ret = new Result();
		if(validCode==null||validCode.equals(session.getAttribute("code"))){
			String code = platformUserService.verificationMessage(loginName);
			session.setAttribute(loginName, code);
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 验证忘记密码验证码
	 * @param loginName
	 * @param code
	 * @return
	 */
	@RequestMapping("/verificationForgetPasswordCode")
	@ResponseBody
	public Result verificationForgetPasswordCode(String loginName,String code,HttpSession session){
		Result  ret = new Result();
		if(code.equals(session.getAttribute(loginName).toString())){
			ret.setResult(true);
		}else{
			ret.setMsg("验证码不匹配，请输入正确验证吗！");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 初始密码
	 * @param mobile
	 * @param email
	 * @param optionsRadiosinline
	 * @param session
	 * @return
	 */
	@RequestMapping("/verificationForgetPassword")
	@ResponseBody
	public Result verificationForgetPassword(String loginName,String password,HttpSession session){
		Result  ret = new Result();
		if(platformUserService.verificationMobile(loginName,password)){
			session.removeAttribute("loginNamePwd");
			ret.setMsg("初始密码设置成功！");
			ret.setResult(true);
		}else{
			ret.setMsg("初始密码设置失败！");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping("/loginUser")
	@ResponseBody
	public Result loginUser(String  loginName,String password, String reCode,HttpSession session) {
		Result ret = new Result();
		if(null ==loginName || "".equals(loginName)
				|| null == password || "".equals(password)
				|| "".equals(reCode) || null == reCode){
			ret.setResult(false);
			ret.setMsg("用户名，密码，验证码不能为空！");
		}else if(platformUserService.selectLoginName(loginName)){
			if(reCode.equals(session.getAttribute("code").toString())){
				PlatformUser user = platformUserService.loginUser(loginName,password);
				if(user != null){
					if(user.getStatus() == 0){
						ret.setResult(false);
						ret.setMsg("该账号已被停用，请联系管理员！");
					}else{
						System.out.println(user.getUserType()+"--------------------------------------------------------------");
						session.setAttribute(SystemConstant.USER_SESSION, user);
						ret.setResult(true);
						ret.setMsg("登录成功!");
					}
				}else{
					ret.setResult(false);
					ret.setMsg("登录失败!");
				}
			}else{
				ret.setResult(false);
				ret.setMsg("验证码不正确!");
			}
		}else{
			ret.setResult(false);
			ret.setMsg("用户未注册!");
		}
		return ret;
	}
	/**
	 * 专线注册
	 * @param user
	 * @param company
	 * @return
	 */
	@RequestMapping("/doinsertDedicatedLineRegister")
	@ResponseBody
	public Result doinsertDedicatedLineRegister(PlatformUser user,
			PlatformUserCompany company,HttpServletRequest request,HttpSession session,String code)
					throws IllegalStateException,IOException{
		Result ret = new Result();
		if(code.equals(session.getAttribute(user.getLoginName()))){
		if(request instanceof MultipartHttpServletRequest){
			for (int i = 0; i < 5; i++) {
				 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
	             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
	             if(null == multipartFile || multipartFile.isEmpty()){
	                 ret.setMsg("图片不能空!");
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
			if(platformUserService.insertDedicatedLineRegister(user, company)){
				session.removeAttribute(user.getLoginName());
				ret.setMsg("专线注册成功!");
				ret.setResult(true);
			}else{
				ret.setMsg("专线注册失败!");
				ret.setResult(false);
			}
		}
		}else{
			ret.setMsg("手机验证码不正确");
			ret.setResult(false);
		}
		return ret;
	}
	@RequestMapping("/getCode")
	@ResponseBody
	public Result getCode(PlatformUser user,String code,HttpSession session){
		Result ret = new Result();
		if(code.equals(session.getAttribute(user.getLoginName()))){
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 企业货主注册
	 * @param user
	 * @param company
	 * @return
	 */
	@RequestMapping("/doinsertEnterprise")
	@ResponseBody
	public Result doinsertEnterprise(PlatformUser user,
			PlatformUserTemporaryCompany company,HttpServletRequest request,HttpSession session,String code)
					throws IllegalStateException,IOException{
		Result ret = new Result();
		if(code.equals(session.getAttribute(user.getLoginName()))){
			if(request instanceof MultipartHttpServletRequest){
				for (int i = 0; i < 5; i++) {
					 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
		             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
		             if(null == multipartFile || multipartFile.isEmpty()){
		                 ret.setMsg("图片不能空!");
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
				if(platformUserService.insertEnterprise(user, company)){
					session.removeAttribute(user.getLoginName());
					ret.setMsg("企业货主注册成功!");
					ret.setResult(true);
				}else{
					ret.setMsg("企业货主注册失败!");
					ret.setResult(false);
				}
			}
		}else{
			ret.setMsg("手机验证码不正确!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 用户是否存在
	 * @param loginName
	 * @return
	 */
	@RequestMapping("getUser")
	@ResponseBody
	public Result getUser(String loginName){
		Result  ret = new Result();
		if(platformUserService.selectLoginName(loginName)){
			ret.setResult(false);
		}else{
			ret.setResult(true);
		}
		return ret;
	}
	/**
	 * 验证用户修改密码是否存在
	 * @param password
	 * @return
	 */
	@RequestMapping("getUserPassword")
	@ResponseBody
	public Result getUserPassword(String password,HttpSession session){
		Result  ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(platformUserService.selectUserPassword(user.getLoginName(), password)){
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		return ret;
	}
}
