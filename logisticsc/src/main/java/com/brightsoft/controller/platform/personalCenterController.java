package com.brightsoft.controller.platform;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.InsuranceStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.entity.YcStorageBranch;
import com.brightsoft.model.InsuranceCompany;
import com.brightsoft.model.MessageSubscription;
import com.brightsoft.model.PlatformLineConsumeRecord;
import com.brightsoft.model.PlatformLineStorage;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserBankCard;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PlatformUserMailbox;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.User;
import com.brightsoft.model.platformBank;
import com.brightsoft.model.platformBankAccount;
import com.brightsoft.model.platformBankPayment;
import com.brightsoft.model.platformBankRefund;
import com.brightsoft.model.platformVoSplitPos;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.InsuranceManageService;
import com.brightsoft.service.platform.PlatformBankCityServiceImpl;
import com.brightsoft.service.platform.PlatformBankConfigureServiceImpl;
import com.brightsoft.service.platform.PlatformInsuranceServiceImpl;
import com.brightsoft.service.platform.PlatformUserBankCardServerImpl;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserConsumeRecordService;
import com.brightsoft.service.platform.PlatformUserLineConsumeRecordService;
import com.brightsoft.service.platform.PlatformUserLineMoneyService;
import com.brightsoft.service.platform.PlatformUserMessageSubscriptionServiceImpl;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.platform.platformBankAccountServiceImpl;
import com.brightsoft.service.platform.platformBankMapperServiceImpl;
import com.brightsoft.service.platform.platformBankNameServiceImpl;
import com.brightsoft.service.platform.platformBankPaymentServiceImpl;
import com.brightsoft.service.platform.platformBankRefundServiceImpl;
import com.brightsoft.service.platform.platformBankSplitServiceImpl;
import com.brightsoft.service.yc.IYcJoinChargeService;
import com.brightsoft.service.yc.IYcJoinInfoService;
import com.brightsoft.service.yc.IYcStorageBranchService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;
import com.brightsoft.utils.insurance.InsType;
import com.brightsoft.utils.yc.StrUtil;
import com.brightsoft.utils.yeepay.UploadImageManager;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;
/**
 * 个人中心
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/personalCenter")
public class personalCenterController extends BaseController{
	
	@Autowired
	private platformBankPaymentServiceImpl platformBankPayment;
	
	@Autowired
	private platformBankAccountServiceImpl platformBankAccount;
	
	@Autowired
	private PlatformBankConfigureServiceImpl platformBankConfigure;
	
	@Autowired
	private platformBankMapperServiceImpl platformBankMapper;
	@Autowired
	private platformBankNameServiceImpl platformBankName;
	
	@Autowired
	private PlatformBankCityServiceImpl platformBankCity;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	@Autowired
	private PlatformUserCompanyService companyService;
	
	@Autowired
	private PlatformUserConsumeRecordService consumeRecordService;
	
	@Autowired
	private PlatformUserLineMoneyService lineMoneyService;
	
	@Autowired
	private PlatformUserLineConsumeRecordService lineConsumeRecordService;
	
	@Autowired
	private PlatformUserBankCardServerImpl bankCardServerImpl;
	
	@Autowired
	private PlatformUserMessageSubscriptionServiceImpl messageSubscriptionServiceImpl;
	
	@Autowired
	private PlatformInsuranceServiceImpl platformInsuranceServiceImpl;
	
	@Autowired
	private InsuranceManageService insuranceManageService;
	
	@Autowired
	private platformBankRefundServiceImpl bankRefundServiceImpl;
	
	@Autowired
	private platformBankSplitServiceImpl platformBankSplitServiceImpl;
	
	@Autowired
	private IYcJoinInfoService iYcJoinInfoService;
	
	@Autowired
	private IYcStorageBranchService iYcStorageBranchService;
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	/**
	 * 跳转到个人中心——个人中心 页面
	 * @return
	 */
	@RequestMapping("/toorderPersonal")
	public String toorderPersonalCenter(String chooseid,ModelMap model){
		model.addAttribute("chooseid",chooseid);
		return "/platform/personalCenter/personal_center";
	}
	/**
	 * 跳转到我的消息订阅
	 * @return
	 */
	@RequestMapping("/toMessage")
	public String toMessage(){
		return "/platform/personalCenter/message_subscription";
	}
	/**
	 * 跳转到安全设置
	 * @return
	 */
	@RequestMapping("/toorderSecuritySettings")
	public String toorderSecuritySettings(){
		return "/platform/personalCenter/security_settings";
	}
	/**
	 * 跳转到账号信息
	 * @return
	 */
	@RequestMapping("/toorderAccountInfo")
	public String toorderAccountInfo(){
		return "/platform/personalCenter/account_information";
	}
	/**
	 * 跳转我的钱包
	 * @return
	 */
	@RequestMapping("/toorderMyWallet")
	public String toorderMyWallet(){
		return "/platform/personalCenter/my_wallet";
	}
	/**
	 * 跳转我的云仓
	 * @return
	 */
	@RequestMapping("/toMyCloud")
	public String tomy_cloud(HttpServletRequest request){
		PlatformUser user=(PlatformUser) request.getSession().getAttribute(SystemConstant.USER_SESSION);
		YcJoinInfo t=new YcJoinInfo();
		if(user==null){
			return "redirect:/platform-login.jsp";
		}
		t.setJoinerId(new BigInteger(user.getId().toString()));
		t=iYcJoinInfoService.getSingleInfo(t);
		if(t==null){
			//未加盟
			List<YcStorageBranch> lst= iYcStorageBranchService.getBranchNoList();
			request.setAttribute("branchs", lst);
			return "/platform/personalCenter/my_join";
		}else{
			if(t.getApplyStatus()==0){
				//让页面提示等待审核
				request.setAttribute("msg_", 0);
				return "/platform/personalCenter/my_join";
			}
			return "/platform/personalCenter/my_cloud";
		}
	}
	/**
	 * 加盟云仓页面
	 * @return
	 */
	@RequestMapping("/toMyJoin")
	public String tomy_join(HttpServletRequest request){
		String id=StrUtil.getString(request.getParameter("userId"), "0");
		YcJoinInfo t=new YcJoinInfo();
		t.setJoinerId(new BigInteger(id));
		t=iYcJoinInfoService.getSingleInfo(t);
		if(t==null){
			//未加盟
			return "";
		}else{
			return "/platform/personalCenter/my_cloud";
		}
	}
	
	/**
	 * 保险理赔
	 * @return
	 */
	@RequestMapping("/tosettlement")
	public String tosettlement(){
		return "/platform/personalCenter/settlement";
	}
	/**
	 * 添加银行卡
	 * @return
	 */
	@RequestMapping("/toAddBank")
	public String toAddBank(){
		return "/platform/personalCenter/my_wallet_addBank";
	}
	@RequestMapping("/toUpdateBank")
	public String toUpdateBank(){
		return "/platform/personalCenter/my_wallet_updateBank";
	}
	@RequestMapping("/toAddQualification")
	public String toAddQualification(){
		return "/platform/personalCenter/my_wallet_addQualification";
	}
	/**
	 * 跳转都我的消费记录
	 * @return
	 */
	@RequestMapping("/toorderRecordsConsumption")
	public String toorderRecordsConsumption(){
		return "/platform/personalCenter/records_consumption";
	}
	/**
	 * 跳转到保单页面
	 * @return
	 */
	@RequestMapping("/toInsurance")
	public String toListInsurance(String chooseid,ModelMap model){
		List<InsuranceCompany> insCompanyList = insuranceManageService.selectInsCompany();
		List<InsType> insTypeList = insuranceManageService.selectAllType();
		//ConfigResult configResult = insuranceHttpService.getComConfig();
		HashMap<Integer, String> map = new HashMap<Integer,String>();
		map.put(InsuranceStatusEnum.saved.getValue(), InsuranceStatusEnum.saved.getName());
		map.put(InsuranceStatusEnum.checking.getValue(), InsuranceStatusEnum.checking.getName());
		map.put(InsuranceStatusEnum.takeEffect.getValue(), InsuranceStatusEnum.takeEffect.getName());
		map.put(InsuranceStatusEnum.cancellationInsurance.getValue(), InsuranceStatusEnum.cancellationInsurance.getName());
		model.addAttribute("insuranceStatus",map);
		model.addAttribute("chooseid",chooseid);
		model.addAttribute("insTypeList",insTypeList);
		model.addAttribute("insurance", insCompanyList);
		return "/platform/personalCenter/my_insurance";
	}
//	/**
//	 * 获取用户信息
//	 * @return
//	 */
//	@RequestMapping("/getUser")
//	@ResponseBody
//	public ModelAndView getUser(HttpSession session) {
//		ModelAndView mv = new ModelAndView("/platform/personalCenter/basic_Info");
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		if(null == user.getId()  || user.getId()  <=0){
//			mv.addObject("用户ID为空");
//		}else{
//			user = platformUserService.selectUser(user.getId());
//			if(null == user){
//				mv.addObject("用户为空");
//			}else{
//				mv.addObject("user",user);
//				mv.addObject("查看用户成功");
//			}
//		}
//		return mv;
//	}
	/**
	 * 修改用户基本信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/doUpdateUser")
	@ResponseBody
	public Result doUpdateUser(PlatformUser user,HttpSession session){
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		user.setId(sessionUser.getId());
		Result ret = new Result();
		if(null == user.getId()||user.getId() <= 0){
			ret.setResult(false);
			ret.setMsg("用户ID为空!");
		}else{
			user = platformUserService.updateUser(user);
			if(null != user){
				ret.setResult(true);
				session.setAttribute(SystemConstant.USER_SESSION,user);
				ret.setMsg("修改用户信息成功!");
			}else{
				ret.setResult(false);
				ret.setMsg("修改用户信息失败!");
			}
		}
		return ret;
	}
	/**
	 * 获取公司信息
	 * @return
	 */
	@RequestMapping("getCompanyInfo")
	@ResponseBody
	public Result getCompanyInfo(HttpSession session){
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result srt = new Result();
		if(null == sessionUser.getId() || sessionUser.getId() <= 0){
			srt.setMsg("用户信息为空!");
			srt.setResult(false);
		}else{
			PlatformUser user = platformUserService.selectUser(sessionUser.getId());
				PlatformUserCompany companyInfo = new PlatformUserCompany();
				companyInfo=companyService.selectCompanyInfo(user.getCompanyId());
				if(null == companyInfo){
					srt.setMsg("获取公司信息失败!");
					srt.setResult(false);
				}else{
					srt.setMsg("获取用户公司信息成功!");
					srt.setData(companyInfo);
					srt.setResult(true);
				}
		}
		return srt;
	}
	/**
	 * 修改公司信息
	 * @param companyInfo
	 * @return
	 */
	@RequestMapping("doUpdateCompanInfo")
	@ResponseBody
	public Result doUpdateCompanInfo(PlatformUserCompany companyInfo,HttpSession session,HttpServletRequest request,PlatformUserTemporaryCompany platformUserTemporaryCompany)throws IllegalStateException,IOException{
		Result ret = new Result();
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		//ID 空 新增公司信息
		if(null == companyInfo.getId() || companyInfo.getId() <= 0){
			if(request instanceof MultipartHttpServletRequest){
				for (int i = 0; i < 5; i++) {
					 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
		             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
		             if(null == multipartFile || multipartFile.isEmpty()){
		                 break;
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
			                     companyInfo.setBusinessLicense(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName2")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadLegalPhoto"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setLegalPhoto(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName1")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadCompanyPhoto"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setCompanyPhoto(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName3")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadCardPhoto"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setCardPhoto(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName4")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadLogin"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setLogo(originalUrl);
		                	 }
		                 }
		             }
				}
			}
			if(companyService.insertCompanyInfo(companyInfo, sessionUser.getId())){
				ret.setMsg("增加公司信息成功!");
				ret.setResult(true);
			}else{
				ret.setMsg("增加公司信息失败!");
				ret.setResult(false);
			}
		}else{
			if(request instanceof MultipartHttpServletRequest){
				for (int i = 0; i < 5; i++) {
					 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
		             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
		             if(null == multipartFile || multipartFile.isEmpty()){
		                 continue;
		             }else if(multipartFile.getSize() > 5197877){
			            	ret.setMsg("上传图片不能大于5M!");
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
			                     companyInfo.setBusinessLicense(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName2")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadLegalPhoto"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setLegalPhoto(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName1")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadCompanyPhoto"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setCompanyPhoto(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName3")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadCardPhoto"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setCardPhoto(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName4")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("uploadLogin"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =platformUserService.uploadFile(dirName, file);
			                     companyInfo.setLogo(originalUrl);
		                	 }
		                 }
		             }
				}
			}
			if(companyService.updateCompanyInfo(companyInfo,sessionUser,platformUserTemporaryCompany)){
				if(sessionUser.getUserType() == Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR){
					ret.setMsg("修改公司信息成功!");
				}else if(sessionUser.getUserType() == Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR){
					ret.setMsg("修改公司信息成功,请耐心等待管理员审核修改资料!");
				}else if(sessionUser.getUserType() == Const.PLATFORM_USER_TYPE_LINE_COMPANY){
					ret.setMsg("修改公司信息成功,请耐心等待管理员审核修改资料!!");
				}else if(sessionUser.getUserType() == Const.PLATFORM_USER_TYPE_LINE_PROVIDER){
					ret.setMsg("修改公司信息成功,请耐心等待管理员审核修改资料!");
				}
				ret.setResult(true);
			}else{
				ret.setMsg("修改公司信息失败!");
				ret.setResult(false);
			}
		}
		return ret;
	}
	/**
	 * 验证当前用户是否存在资质
	 * @param session
	 * @return
	 */
	@RequestMapping("verifUserCompany")
	@ResponseBody
	public Result verifUserCompany(HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(companyService.verifUserCompany(user)){
			ret.setMsg("没有审核中的资质信息!");
			ret.setResult(true);
		}else{
			ret.setMsg("修改信息正在审核中,请耐心等待！");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 修改密码
	 * @param password
	 * @param id
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Result updatePwd(String password,String newPassword,HttpSession session) {
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null ==user.getId()
			||user.getId() <= 0 
			|| StringUtils.isBlank(newPassword) 
			|| StringUtils.isBlank(password)){
			ret.setMsg("必填项不能为空!");
			ret.setResult(false);
		}else{
			user = platformUserService.updatePwd(password, newPassword, user.getId());
			if(null != user){
				ret.setMsg("修改成功!");
				ret.setResult(true);
				user.setPassword(newPassword);
				session.setAttribute(SystemConstant.USER_SESSION,user);
			}else{
				ret.setMsg("修改失败!");
				ret.setResult(false);
			}
		}
		return ret;
	}
	/**
	 * 绑定邮箱
	 * @param email
	 * @param id
	 * @return
	 */
	@RequestMapping("/bindingEmail")
	@ResponseBody
	public Result bindingEmail(String verCode,HttpSession session) {
		Result ret = new Result();
		if(StringUtils.isBlank(verCode)){
			ret.setMsg("验证码不能为空!");
			ret.setResult(false);
		}else{
			PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
			PlatformUserMailbox userMailbox = (PlatformUserMailbox)session.getAttribute(user.getLoginName());
			if(null != userMailbox){
				user = platformUserService.bindingEmail(verCode, userMailbox,user.getId());
				if(null != user){
					ret.setMsg("绑定邮箱成功!");
					user.setEmail(userMailbox.getEmail());
					session.setAttribute(SystemConstant.USER_SESSION,user);
					ret.setResult(true);
				}else{
					ret.setMsg("绑定邮箱失败!");
					ret.setResult(false);
				}
			}else{
				ret.setMsg("验证码已过期!");
				ret.setResult(false);
			}
		}
		return ret;
	}
	/**
	 * 绑定手机号
	 * @param mobile
	 * @param id
	 * @return
	 */
	@RequestMapping("/bindingMobile")
	@ResponseBody
	public Result bindingMobile(String mobile,Long id) {
		Result ret = new Result();
		if(id <= 0 && null == mobile || "".equals(mobile)){
			ret.setMsg("手机号不能为空!");
			ret.setResult(false);
		}else{
			if(platformUserService.updateMobile(mobile, id)){
				ret.setMsg("绑定手机号成功!");
				ret.setResult(true);
			}else{
				ret.setMsg("绑定手机号失败!");
				ret.setResult(false);
			}
		}
		return ret;
	}
	/**
	 * 用户账号是否可用
	 * @param loginName
	 * @return
	 */
	@RequestMapping("/getUserId")
	@ResponseBody
	public Result getUserId(String loginName){
		Result ret = new Result();
		if(null ==loginName || "".equals(loginName)){
			ret.setMsg("用户账号为空!");
			ret.setResult(false);
		}else{
			if(platformUserService.selectLoginName(loginName)){
				ret.setMsg("用户账号已注册!");
				ret.setResult(false);
			}else{
				ret.setMsg("用户账号可用!");
				ret.setResult(true);
			}
		}
		return ret;
	}
	
	
	/**
	 * 获取消费记录
	 * @param consumeType
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getConsumeRecord")
	@ResponseBody
	public Result getConsumeRecord(String consumeType,Long userId,HttpSession session,Page<?> page){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == consumeType || "".equals(consumeType) 
				||null == user.getId() || user.getId() <=0){
			ret.setMsg("消费类型和用户ID为空!");
			ret.setResult(false);
		}else{
			page = consumeRecordService.selectBySelectedCondition(user.getId(),consumeType,page);
			ret.setResult(true);
			ret.setMsg("获取消费记录成功!");
			ret.setData(page);
		}
		return ret;
	}
	
	/**
	 * 获取线路充值信息
	 * @return
	 */
	@RequestMapping("/getLineMoney")
	@ResponseBody
	public Result getLineMoney(PlatformLineStorage moneys,Page<?> page,HttpSession session) {
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <=0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			moneys.setId(user.getId());
			page = lineMoneyService.selectBySelectedCondition(moneys, page);
			ret.setResult(true);
			ret.setMsg("获取线路充值信息成功!");
			ret.setData(page);
		}
		return ret;
	}
	/**
	 * 发送验证码
	 * @param email
	 * @param session
	 * @return
	 */
	@RequestMapping("/sendMail")
	@ResponseBody
	public Result sendMail(String email,HttpSession session){
		Result ret = new Result();
		if(StringUtils.isBlank(email)){
			ret.setMsg("邮箱不能为空!");
			ret.setResult(false);
		}else{
			PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
			PlatformUserMailbox userMailbox = platformUserService.sendResetPwdVerCode(user.getLoginName(),email);
			if(null != userMailbox){
				session.setAttribute(user.getLoginName(),userMailbox);
				ret.setMsg("发送邮箱验证码成功!");
				ret.setResult(true);
			}else{
				ret.setMsg("发送邮箱验证码失败!");
				ret.setResult(false);
			}
		}
		return ret;
	}
	/**
	 * 获取线路预充值消费记录
	 * @param moneys
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLineConsumeRecord")
	@ResponseBody
	public Result getLineConsumeRecord(PlatformLineConsumeRecord moneys,Page<?> page,HttpSession session) {
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <=0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			moneys.setUserId(user.getId());
			page = lineConsumeRecordService.selectBySelectedCondition(moneys, page);
			ret.setResult(true);
			ret.setMsg("获取线路预充值消费记录成功!");
			ret.setData(page);
		}
		return ret;
	}
	/**
	 * 获取公司图片
	 * @param imgType
	 * @param id
	 * @return
	 */
	@RequestMapping("/doGetCompanInfoImg")
	@ResponseBody
	public Result doGetCompanInfoImg(Integer imgType,long id){
		Result ret = new Result();
		PlatformUserCompany company = companyService.selectCompanInfoImg(id, imgType);
		if(null != company){
			ret.setData(company);
			ret.setMsg("查询公司成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("查询公司失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 上传公司图片
	 * @param request
	 * @param imgType
	 * @param id
	 * @return
	 */
	@RequestMapping("/addCompanInfoImg")
	@ResponseBody
	public Result addCompanInfoImg(HttpServletRequest request,Integer imgType,long id)
			throws IllegalStateException,IOException{
		Result ret = new Result();
		if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
            MultipartFile multipartFile = multipartRequset.getFile(multipartRequset.getFileNames().next());
            if(null == multipartFile || multipartFile.isEmpty()){
                ret.setMsg("图片不能为空!");
                ret.setResult(false);
            }else{
            	 // 获得图片名称
                String fileName = multipartFile.getOriginalFilename();
                // 获取图片后缀名称
                String suffixName = UploadFileUtil.getFileSuffixName(fileName);
                //获取正确的图片格式
                String reg = bundle.getString("reg");
                //验证图片格式
                if(suffixName.matches(reg)){
                	String dirName ="";
                	if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_0){
                		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
                                + bundle.getString("uploadLogin"), fileName);
        			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_1){
        				 dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
                                + bundle.getString("uploadBusinessLicense"), fileName);
        			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_2){
        				 dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
                                + bundle.getString("uploadCompanyPhoto"), fileName);
        			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_3){
        				 dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
                                + bundle.getString("uploadLegalPhoto"), fileName);
        			}else if(imgType == Const.PLATFORM_USER_COMPANY_IMG_TYPE_4){
        				 dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
                                + bundle.getString("uploadCardPhoto"), fileName);
        			}
                    /* 根据图片名生成唯一文件路径 */
                    File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
                    // 写入文件到实际路径
                    multipartFile.transferTo(file);
                    if(companyService.uploadCompanInfoImg(dirName, file, id, imgType)){
                    	ret.setMsg("上传图片成功!");
                    	ret.setResult(true);
                    }else{
                    	ret.setMsg("上传图片失败!");
                    	ret.setResult(false);
                    }
                }
            }
        }
		return ret;
	}
	/**
	 * 添加用户银行卡
	 * @param bankCard
	 * @param session
	 * @return
	 */
	@RequestMapping("/doUserBankCard")
	@ResponseBody
	public Result doUserBankCard(PlatformUserBankCard bankCard,HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		bankCard.setUserId(user.getId());
		Result ret = new Result();
		if(bankCardServerImpl.insertUserBankCard(bankCard)){
			ret.setMsg("添加银行卡成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("添加银行卡失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 查询当前用户绑定银行卡
	 * @param session
	 * @return
	 */
//	@RequestMapping("/getUserBankCard")
//	@ResponseBody
//	public Result getUserBankCard(HttpSession session){
//		Result ret = new Result();
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		List<PlatformUserBankCard> bankCards = bankCardServerImpl.selectBankCard(user.getId());
//		if(bankCards.size() > 0){
//			ret.setMsg("查询银行卡成功!");
//			ret.setData(bankCards);
//			ret.setResult(true);
//		}else{
//			ret.setMsg("查询银行卡失败!");
//			ret.setResult(false);
//		}
//		return ret;
//	}
	/**
	 * 获取当个银行卡信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getBanKCardId")
	@ResponseBody
	public Result getBanKCardId(Long id){
		Result ret = new Result();
		PlatformUserBankCard bankCard = bankCardServerImpl.selectBankCardId(id);
		if(null != bankCard){
			ret.setMsg("获取银行卡成功!");
			ret.setData(bankCard);
			ret.setResult(true);
		}else{
			ret.setMsg("获取银行卡失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 修改银行卡信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/doUpdateUserBankCard")
	@ResponseBody
	public Result doUpdateUserBankCard(PlatformUserBankCard bankCard){
		Result ret = new Result();
		if(bankCardServerImpl.updateUserBankCard(bankCard)){
			ret.setMsg("修改银行卡成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("修改银行卡失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 获取消息订阅成功
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMessageSubscription")
	@ResponseBody
	public Result getMessageSubscription(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result ret = new Result();
		List<MessageSubscription> messageSubscriptions = messageSubscriptionServiceImpl.selectMessageSubscription(user.getId());
		ret.setData(messageSubscriptions);
		ret.setMsg("获取消息订阅成功!");
		ret.setResult(true);
		return ret;
	}
	/**
	 * 保存用户订阅
	 * @param session
	 * @param xuanzhong
	 * @return
	 */
	@RequestMapping("/doMessageSubscription")
	@ResponseBody
	public Result doMessageSubscription(HttpSession session,String xuanzhong){
		List<Long> list = JSONArray.parseArray(xuanzhong, Long.class);
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result ret = new Result();
		if(list.size() <= 0){
			if(messageSubscriptionServiceImpl.deleteMessageSubscriptionConf(user.getId())){
				ret.setMsg("保存订阅成功!");
				ret.setResult(true);
				return ret;
			}
		}else{
			if(messageSubscriptionServiceImpl.deleteMessageSubscriptionConf(user.getId())){
				if(messageSubscriptionServiceImpl.insertMessageSubscriptionConf(list,user.getId())){
					ret.setMsg("保存订阅成功!");
					ret.setResult(true);
					return ret;
				}
			}
		}
		ret.setMsg("保存订阅失败!");
		ret.setResult(false);
		return ret;
	}
	/**
	 * 查询投保信息
	 * @param searchParams
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/getInsuranceInfo")
	@ResponseBody
	public Result getInsuranceInfo(SearchParams searchParams,Page<?> page,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			result.setData(platformInsuranceServiceImpl.selectByCondition(user.getId(), searchParams, page));
			result.setResult(true);
		}
		return result;
	}
//	/**
//	 * 获取银行卡信息
//	 * @return
//	 */
//	@RequestMapping("/getDictionary")
//	@ResponseBody
//	public Result getDictionary(){
//		Result srt = new Result();
//		List<PlatformDictionary> dictionaries = dictionaryService.selectDictDataByType(DictionaryType.CRAD_TYPE);
//		if(null == dictionaries || dictionaries.size() <= 0){
//			srt.setMsg("获取银行卡失败!");
//			srt.setResult(false);
//		}else{
//			srt.setMsg("获取银行卡成功!");
//			srt.setData(dictionaries);
//			srt.setResult(true);
//		}
//		return srt;
//	}
//	/**
//	 * 充值
//	 * @param money
//	 * @param id
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/doUserRecharge")
//	@ResponseBody
//	public Result doUserRecharge(Double money,Long id,HttpSession session){
//		Result srt = new Result();
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		user = consumeRecordService.addUserRecharge(money, id, user);
//		if(null != user){
//			session.setAttribute(SystemConstant.USER_SESSION, user);
//			srt.setMsg("充值成功!");
//			srt.setResult(true);
//		}else {
//			srt.setMsg("充值失败!");
//			srt.setResult(false);
//		}
//		return srt;
//	}
//	/**
//	 * 提现
//	 * @param money
//	 * @param id
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/doUserWithdrawals")
//	@ResponseBody
//	public Result doUserWithdrawals(Double money,Long id,HttpSession session){
//		Result srt = new Result();
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		user = consumeRecordService.addUserWithdrawals(money,id, user);
//		if(null != user){
//			session.setAttribute(SystemConstant.USER_SESSION, user);
//			srt.setMsg("提现成功!");
//			srt.setResult(true);
//		}else {
//			srt.setMsg("提现失败!");
//			srt.setResult(false);
//		}
//		return srt;
//	}
//	/**
//	 * 线路预充值
//	 * @param companyId
//	 * @param lineId
//	 * @param moneyId
//	 * @param money
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/doUserLineConsumeRecord")
//	@ResponseBody
//	public Result doUserLineConsumeRecord(Long companyId,Long lineId,Long moneyId,double money,HttpSession session){
//		Result srt = new Result();
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		user =lineMoneyService.doUserLineConsumeRecord(companyId, lineId, moneyId, user, money);
//		if(null != user){
//			session.setAttribute(SystemConstant.USER_SESSION, user);
//			srt.setMsg("线路预充值成功!");
//			srt.setResult(true);
//		}else {
//			srt.setMsg("线路预充值失败!");
//			srt.setResult(false);
//		}
//		return srt;
//	}
//	/**
//	 * 转账
//	 * @param money
//	 * @param loginName
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/doUserTransferAccounts")
//	@ResponseBody
//	public Result doUserTransferAccounts(double money,String loginName,HttpSession session){
//		Result srt = new Result();
//		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
//		user = consumeRecordService.adduserTransferAccounts(money, loginName, user);
//		if(null != user){
//			session.setAttribute(SystemConstant.USER_SESSION, user);
//			srt.setMsg("转账成功!");
//			srt.setResult(true);
//		}else {
//			srt.setMsg("转账用户不存在!");
//			srt.setResult(false);
//		}
//		return srt;
//	}
	
	@RequestMapping("/getBankProvince")
	@ResponseBody
	public Result getBankProvince(){
		Result srt = new Result();
		List<com.brightsoft.model.platformBankCity> list = platformBankCity.selectBankProvince();
		srt.setData(list);
		srt.setResult(true);
		return srt;
	}
	
	@RequestMapping("/getBankCity")
	@ResponseBody
	public Result getgetBankCity(String province){
		Result srt = new Result();
		List<com.brightsoft.model.platformBankCity> list = platformBankCity.selectBankCity(province);
		srt.setData(list);
		srt.setResult(true);
		return srt;
	}
	
	@RequestMapping("/getProvinceName")
	@ResponseBody
	public Result getProvinceName(){
		Result srt = new Result();
		List<com.brightsoft.model.platformBankName> list=platformBankName.selectProvinceName();
		srt.setData(list);
		srt.setResult(true);
		return srt;
	}
	
	@RequestMapping("/getCityName")
	@ResponseBody
	public Result getCityName(String provinceName){
		Result srt = new Result();
		List<com.brightsoft.model.platformBankName> list=platformBankName.selectCityName(provinceName);
		srt.setData(list);
		srt.setResult(true);
		return srt;
	}
	
	@RequestMapping("/getHeadName")
	@ResponseBody
	public Result getHeadName(String provinceName,String cityName){
		Result srt = new Result();
		List<com.brightsoft.model.platformBankName> list=platformBankName.selectHeadName(provinceName, cityName);
		srt.setData(list);
		srt.setResult(true);
		return srt;
	}

	@RequestMapping("/getBranchName")
	@ResponseBody
	public Result getBranchName(String headName,String provinceName,String cityName){
		Result srt = new Result();
		List<com.brightsoft.model.platformBankName> list=platformBankName.selectBranchName(headName,provinceName,cityName);
		srt.setData(list);
		srt.setResult(true);
		return srt;
	}
	/**
	 * 银行卡修改
	 * @param bank
	 * @param session
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/doUpdateBank")
	@ResponseBody
	public Result doUpdateBank(platformBank bank,HttpSession session,HttpServletRequest request)throws ServletException, IOException {
		Result srt = new Result();
		com.brightsoft.model.platformBankConfigure bankConfigure = platformBankConfigure.selectBankConfigure();
		return srt;
	}
	@RequestMapping("/doBank")
	@ResponseBody
	public Result doBank(platformBank bank,HttpSession session,HttpServletRequest request)throws IllegalStateException,IOException{
		Result srt = new Result();
		com.brightsoft.model.platformBankConfigure bankConfigure = platformBankConfigure.selectBankConfigure();
		StringBuffer sb = new StringBuffer();
		sb.append("BANK");
		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
		bank.setRequestid(sb.toString());
		bank.setSignedname(bank.getLinkman());
		bank.setMinsettleamount(bankConfigure.getMinsettleamount());
		bank.setRiskreserveday(bankConfigure.getRiskreserveday());
		bank.setManualsettle(bankConfigure.getManualsettle());
		bank.setState(Const.PLATFORM_ORDER_BANK_STATE_0);
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		request.setAttribute("requestid",bank.getRequestid());
		request.setAttribute("bindmobile",bank.getBindmobile());
		request.setAttribute("customertype",bank.getCustomertype());
		request.setAttribute("signedname",bank.getLinkman());
		request.setAttribute("linkman",bank.getLinkman());
		request.setAttribute("minsettleamount",bank.getMinsettleamount());
		request.setAttribute("riskreserveday",bank.getRiskreserveday());  
		request.setAttribute("bankaccountnumber",bank.getBankaccountnumber());
		request.setAttribute("bankname",bank.getBankname());
		request.setAttribute("accountname",bank.getAccountname());
		request.setAttribute("bankaccounttype",bank.getBankaccounttype());
		request.setAttribute("bankprovince",bank.getBankprovince());
		request.setAttribute("bankcity",bank.getBankcity());
		request.setAttribute("manualsettle",bank.getManualsettle());
		if(bank.getCustomertype().equals("PERSON")){
			request.setAttribute("idcard",bank.getIdcard());
		}else if(bank.getCustomertype().equals("ENTERPRISE")){
			request.setAttribute("businesslicence",bank.getBankAccountLicence());
			request.setAttribute("legalperson",bank.getLegalperson());
		}
		//子账户注册
		String data = ZGTDataAttribute.buildRegisterData(request);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REGISTERAPI_NAME);
		Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.REGISTERAPI_RESPONSE_HMAC_ORDER);
		Map<String, String> responseMap = (Map<String, String>) result.getData();
		//第三步 判断请求是否成功，
		if(!responseMap.get("code").equals("1")) {
			srt.setMsg(responseMap.get("msg"));
			srt.setResult(false);
			return srt ;
		}else{
			//根据当前用户查询是否存在子账户
			platformBankAccount bankAccount =null;
			bankAccount=  platformBankAccount.selectBankAccountUserId(sessionUser.getId());
			platformBankAccount account = new platformBankAccount();
			account.setCustomernumber(responseMap.get("customernumber"));
			account.setHmac(responseMap.get("hmac"));
			account.setLedgerno(responseMap.get("ledgerno"));
			account.setRequestid(bank.getRequestid());
			account.setTime(new Date());
			account.setUserId(sessionUser.getId());
			if(null == bankAccount){
				if(platformBankAccount.insertBankAccount(account)){
					//第七步 进行业务处理
					bank.setUserId(sessionUser.getId());
					if(platformBankMapper.binding(bank)){
						srt.setMsg("绑定银行卡成功");
						srt.setResult(true);
					}else{
						srt.setMsg("绑定银行卡失败");
						srt.setResult(true);
					}
				}
			}else{
				account.setId(bankAccount.getId());
				//修改子账户
				if(platformBankAccount.updateBankAccount(account)){
					platformBank platformBank = platformBankMapper.selectBankUserId(sessionUser.getId());
					if(!bankAccount.getLedgerno().equals(account.getLedgerno())){
						bank.setIsQualifications(Const.PLATFORM_ORDER_BANK_QS_1);
						bank.setIdCardFront(bank.getIdCardFront());
						bank.setIdCcardBack(bank.getIdCcardBack());
						bank.setPersouPhoto(bank.getPersouPhoto());
						bank.setBankCardFront(bank.getBankCardFront());
						bank.setBussinessLicense(bank.getBusinesslicence());
						bank.setBankAccountLicence(bank.getBankAccountLicence());
						bank.setOrganizationCode(bank.getOrganizationCode());
						bank.setTaxRegistron(bank.getTaxRegistron());
						bank.setIdCardFront("");
						bank.setIdCcardBack("");
						bank.setPersouPhoto("");
						bank.setBankCardFront("");
						bank.setBussinessLicense("");
						bank.setBussinessLicense("");
						bank.setOrganizationCode("");
						bank.setTaxRegistron("");
						
					}
					bank.setId(platformBank.getId());
					if(platformBankMapper.binding(bank)){
						srt.setMsg("绑定银行卡成功");
						srt.setResult(true);
					}else{
						srt.setMsg("绑定银行卡失败");
						srt.setResult(true);
					}
				}
				//修改银行卡
			}
		}
		return srt;
	}
	
	@RequestMapping("/getBankUserId")
	@ResponseBody
	public Result getBankUserId(HttpSession session){
		Result ret = new Result();
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		platformBank bank =null; 
		bank= platformBankMapper.selectBankUserId(sessionUser.getId());
		if(null == bank){
			ret.setResult(false);
		}else{
			ret.setResult(true);
			ret.setData(bank);
		}
		return ret;
	}
	@RequestMapping("/dozhizhi")
	@ResponseBody 
	public Result dozhizhi(HttpSession session,HttpServletRequest request)throws IllegalStateException,IOException{
		Result srt = new Result();
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		//查询用户子账户
		platformBankAccount account =  platformBankAccount.selectBankAccountUserId(sessionUser.getId());
		if(null == account){
			srt.setMsg("请绑定银行卡");
			srt.setResult(false);
		}else{
			//查询银行卡信息
			platformBank bank= platformBankMapper.selectBankUserId(sessionUser.getId());
			List<Map<String, Object>> dataMapList = new ArrayList<Map<String,Object>>();
			if(request instanceof MultipartHttpServletRequest){
				for (int i = 0; i < 10; i++) {
					 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
		             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
		             if(null == multipartFile || multipartFile.isEmpty()){
		                 continue;
		             }else if(multipartFile.getSize() > 512000){
			            	srt.setMsg("上传图片必须小于512KB!");
			            	srt.setResult(false);
			     			return srt;
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
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "ID_CARD_FRONT", file);
			                     dataMapList.add(map);
			                     bank.setIdCardFront(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName1")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "ID_CARD_BACK", file);
			                     dataMapList.add(map);
			                    bank.setIdCcardBack(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName2")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "PERSON_PHOTO", file);
			                     dataMapList.add(map);
		                		 bank.setPersouPhoto(originalUrl); 
		                	 }else if(("fileName"+i).equals("fileName3")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "BANK_CARD_FRONT", file);
			                     dataMapList.add(map);
		                		 bank.setBankCardFront(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName4")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "BUSSINESS_LICENSE", file);
			                     dataMapList.add(map);
		                		 bank.setBussinessLicense(originalUrl); 
		                	 }else if(("fileName"+i).equals("fileName5")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "ID_CARD_FRONT", file);
			                     dataMapList.add(map);
			                     bank.setIdCardFront(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName6")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "ID_CARD_BACK", file);
			                     dataMapList.add(map);
			                     bank.setIdCcardBack(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName7")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "BANK_ACCOUNT_LICENCE", file);
			                     dataMapList.add(map);
		                		 bank.setBankAccountLicence(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName8")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "ORGANIZATION_CODE", file);
			                     dataMapList.add(map);
		                		 bank.setOrganizationCode(originalUrl);
		                	 }else if(("fileName"+i).equals("fileName9")){
		                		 String dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
			                                + bundle.getString("bankImg"), fileName);
			                	 File file = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
			                     multipartFile.transferTo(file);
			                     String originalUrl =dirName+file.getName();
			                     Map<String, Object> map = ZGTDataAttribute.buildUploadData(account.getLedgerno(), "TAX_REGISTRATION", file);
			                     dataMapList.add(map);
		                		 bank.setTaxRegistron(originalUrl);
		                	 }
		                 }
		             }
				}
				try {
					List<Future<Map<String, String>>> futureList =	UploadImageManager.pushMessage(dataMapList);
					platformBank banPlatformBank = new platformBank();
					banPlatformBank.setId(bank.getId());
					for (int p = 0; p < futureList.size(); p++) {
						Future<Map<String, String>> future = futureList.get(p);
						Map<String, String> map = future.get();
						if(map.get("code").equals("1")){
							if(map.get("filetype").equals("ID_CARD_FRONT")){
								banPlatformBank.setIdCardFront(bank.getIdCardFront());
							}else if(map.get("filetype").equals("ID_CARD_BACK")){
								banPlatformBank.setIdCcardBack(bank.getIdCcardBack());
							}else if(map.get("filetype").equals("PERSON_PHOTO")){
								banPlatformBank.setPersouPhoto(bank.getPersouPhoto());
							}else if(map.get("filetype").equals("BANK_CARD_FRONT")){
								banPlatformBank.setBankCardFront(bank.getBankCardFront());
							}else if(map.get("filetype").equals("BUSSINESS_LICENSE")){
								banPlatformBank.setBussinessLicense(bank.getBussinessLicense());
							}else if(map.get("filetype").equals("BANK_ACCOUNT_LICENCE")){
								banPlatformBank.setBussinessLicense(bank.getBussinessLicense());
							}else if(map.get("filetype").equals("ORGANIZATION_CODE")){
								banPlatformBank.setOrganizationCode(bank.getOrganizationCode());
							}else if(map.get("filetype").equals("TAX_REGISTRATION")){
								banPlatformBank.setTaxRegistron(bank.getTaxRegistron());
							}
						}else{
							banPlatformBank.setIsQualifications(Const.PLATFORM_ORDER_BANK_QS_1);
						}
					}
					if(platformBankMapper.binding(banPlatformBank)){
						for (int j = 0; j < futureList.size(); j++) {
							Future<Map<String, String>> future = futureList.get(j);
							Map<String, String> map = future.get();
							if(!map.get("code").equals("1")){
								srt.setMsg(map.get("msg"));
								srt.setResult(false);
								return srt;
							}else if(map.get("active").equals("true")){
								banPlatformBank.setIsQualifications(Const.PLATFORM_ORDER_BANK_QS_0);
							}
						}
						if(platformBankMapper.binding(banPlatformBank)){
							srt.setMsg("上传资质成功，请等待审核！");
							srt.setResult(true);
						}else{
							srt.setMsg("上传资质失败，请联系平台客户！");
							srt.setResult(true);
						}
					}else{
						srt.setMsg("上传资质失败，请联系平台客户！");
						srt.setResult(true);
					}
					//修改用户状态
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return srt;
	}
	
	@RequestMapping("/getJine")
	@ResponseBody
	public Result getJine(HttpSession session,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Result srt = new Result();
		PlatformUser sessionUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		//查询用户子账户
		platformBankAccount account =  platformBankAccount.selectBankAccountUserId(sessionUser.getId());
		if(null == account){
			srt.setResult(false);
			return srt ;
		}else{
			request.setAttribute("ledgerno",account.getLedgerno());
			String data = ZGTDataAttribute.buildQueryBalanceData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYBALANCEAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYBALANCEAPI_RESPONSE_HMAC_ORDER);
			Map<String, String> responseMap = (Map<String, String>) result.getData();
			//第三步 判断请求是否成功，
			if(!responseMap.get("code").equals("1")) {
				srt.setResult(false);
				return srt ;
			}else{
				srt.setResult(true);
				srt.setData(responseMap.get("ledgerbalance"));
				return srt ;
			}
		}
	}
	/**
	 * 消费记录
	 * @param page
	 * @param session
	 * @param bankPayment
	 * @return
	 */
	@RequestMapping("/getBySelectedPayment")
	@ResponseBody
	public Result getBySelectedPayment(Page<?> page,HttpSession session,platformBankPayment bankPayment){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			bankPayment.setUserId(user.getId());
			page = platformBankPayment.selectBySelectedPayment(bankPayment, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 退款记录
	 * @param page
	 * @param session
	 * @param bankRefund
	 * @return
	 */
	@RequestMapping("/getSelectRefund")
	@ResponseBody
	public Result getSelectRefund(Page<?> page,HttpSession session,platformBankRefund bankRefund){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user != null){
			bankRefund.setUserId(user.getId());
			page = bankRefundServiceImpl.selectBySelectedBankRefund(bankRefund, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	//POS机收款记录
	@RequestMapping("/getSelectPos")
	@ResponseBody
	public Result getSelectPos(platformVoSplitPos splitPos,Page<?> page,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user != null){
			splitPos.setUserId(user.getId());
			page = platformBankSplitServiceImpl.selectBySelectedPayment(splitPos, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	@RequestMapping("getBankPayment")
	@ResponseBody
	public Result getBankPayment(HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		platformBankPayment bankPayment = new platformBankPayment();
		bankPayment.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_3);
		bankPayment.setUserId(user.getId());
		Integer amount = platformBankPayment.selectPaymentAmount(bankPayment);
		result.setData(amount);
		result.setResult(true);
		return result;
	}
}
