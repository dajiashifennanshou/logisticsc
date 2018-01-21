package com.brightsoft.controller.tms.platform.syssetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.TmsRoleService;
import com.brightsoft.service.tms.platform.TmsUserService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.SendMsg;

@Controller
@RequestMapping("/tms/user")
public class TmsUserController {
	
	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	@Autowired
	private TmsUserService tmsUserService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private TmsRoleService tmsRoleService;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	/**
	 * 跳转到用户信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresAuthentication
	@RequiresPermissions("/tms/user/list.shtml")
	public ModelAndView toListUser(HttpServletRequest request,HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/tmsuser/listuserinfo");
		if(user!=null){
			List<OutletsInfo> outletsList = new ArrayList<OutletsInfo>();
			if(user.getOutletsId()!=null){
				outletsList.add(outletsService.selectByPrimaryKey(user.getOutletsId()));
			}else{
				outletsList.addAll(outletsService.selectOutlesNotBind(user.getCompanyId()));
			}
			PlatformUserCompany company = platformUserCompanyService.selectCompanyInfo(user.getCompanyId());
			mav.addObject("outletsInfoList",outletsList);
			mav.addObject("company", company);
		}
		return mav;
	}
	/**
	 * 查询用户信息
	 * @param user
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result listUser(SearchParams searchParams,Page<?> page,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			if(user.getOutletsId()!=null){
				result = tmsUserService.selectByOutletsIdCondition(searchParams, page, user);
			}else{
				result = tmsUserService.selectByCompanyIdCondition(searchParams, page, user);
			}
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 添加用户
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addUser(User user,String checkCode,String roleIds,Boolean isAutoRegister,HttpSession session){
		Result result = new Result();
		User usr = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(usr!=null){
			boolean flag = checkCode(session, checkCode);
			if(flag){
				if(StringUtils.isNotBlank(roleIds)){
					user.setCompanyId(usr.getCompanyId());
					if(!isAutoRegister)
						user.setPassword(MD5.getMD5Code(user.getPassword()));
					List<Long> list = new ArrayList<Long>();
					String[] res = roleIds.split(",");
					
					for (String roleIdStr : res) {
						Long roleId = Long.parseLong(roleIdStr);
						list.add(roleId);
					}
					if(tmsUserService.insert(user,list,isAutoRegister)>0){
						result.setResult(true);
					}
				}
			}else{
				result.setMsg("验证码错误");
			}
		}
		return result;
	}
	
	private boolean checkCode(HttpSession session,String checkCode){
		String code = (String)session.getAttribute("tms_user_code");
		if(StringUtils.isNotBlank(checkCode)){
			if(checkCode.equals(code)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 禁用用户
	 * @return
	 */
	@RequestMapping("/forbid")
	@ResponseBody
	public Result forbidUser(String loginName,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(StringUtils.isNotBlank(loginName)&&!loginName.equals(user.getLoginName())){
			if(tmsUserService.updateUserStatus(loginName, Const.TMSUSER_USERSTATUS_FORBID)){
				result.setResult(true);
			}
		}
		return result;
	}
	
	/**
	 * 启用用户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/enable")
	@ResponseBody
	public Result enableUser(String loginName){
		Result result = new Result();
		if(tmsUserService.updateUserStatus(loginName, Const.TMSUSER_USERSTATUS_ENABLE)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteUser(@RequestParam("loginNames[]")String loginNames[]){
		Result result = new Result();
		if(loginNames!=null&&loginNames.length>0){
			Map<String, Object> map = tmsUserService.deleteBatch(loginNames);
			result.setResult((Boolean)map.get("success"));
			result.setMsg((String)map.get("message"));
		}
		return result;
	}
	
	/**
	 * 验证用户是否存在
	 * @param loginName
	 * @return
	 */
	@RequestMapping("/checkUserExist")
	@ResponseBody
	public Result verifyUserExist(String loginName){
		Result result = new Result();
		Map<String, Object> map = tmsUserService.userExist(loginName);
		if((Boolean)map.get("exist")){
			result.setMsg((String)map.get("message"));
			result.setResult(true);
		}else{
			result.setResult(false);
			PlatformUser platformUser = platformUserService.selectUserloginName(loginName);
			result.setData(platformUser);
		}
		return result;
	}
	
	/**
	 * 发送验证码
	 * @param phoneNum
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMsgCode")
	@ResponseBody
	public Result getSMSCode(String phoneNum,HttpSession session){
		Result result = new Result();
		String codeStr = verificationMessage(phoneNum);
//		String codeStr = "111111";
		session.setAttribute("tms_user_code", codeStr);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 生成手机验证码 并发送短信
	 */
	private String verificationMessage(String loginName) {
		Random random = new Random();
		int code = random.nextInt(899999);
		String codeStr=String.valueOf(code+100000);
		SendMsg.sendVerification(loginName,codeStr,Const.PLATFORM_SENDTYPE_0);
		return codeStr;
	}
	
	/**
	 * 更新用户密码
	 * @param phone
	 * @param oldpassword
	 * @param newpassword
	 * @param repassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Result updatePwd(String phone,String oldpassword,String newpassword,String repassword,HttpSession session){
		Result result = new Result();
		result = tmsUserService.updatePwd(phone, newpassword, repassword, oldpassword);
		return result;
	}
	
	@RequestMapping("/updateUserRole")
	@ResponseBody
	public Result updateUserRole(Long userId,String roleIds){
		Result result = new Result();
		tmsRoleService.updateUserRole(userId, roleIds);
		result.setResult(true);
		return result;
	}
	
}
