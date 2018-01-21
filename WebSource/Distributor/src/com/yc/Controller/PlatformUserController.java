package com.yc.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcPlatformUser;
import com.yc.Entity.YcJoinInfo;
import com.yc.Service.IYcJoinInfoService;
import com.yc.Service.PlatformUserService;
import com.yc.Tool.DES;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;

/**
 * 经销商登录
 * @author ThinkPad
 */
@Controller
public class PlatformUserController {
	
	@Autowired
	private PlatformUserService lpos;
	
	@Autowired 
	private IYcJoinInfoService iYcJoinInfoService;
	/**
	 * 经销商登录
	 * @Author:luojing
	 * @2016年7月14日 下午3:33:55
	 */
	@RequestMapping("app/dealerLogin")
	public void dealerLogin(HttpServletRequest request,HttpServletResponse response){
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(), new LcPlatformUser());
			if(user==null){
				FengUtil.RuntimeExceptionFeng("未获取到登录信息");
			}
			LcPlatformUser users = lpos.getSingleInfo(user);
			if(users!=null){
				/*if(users.getUser_type()!=12){
					FengUtil.RuntimeExceptionFeng("只有经销商才能登陆");
				}else{*/
					String uuid = FengUtil.getUUID();
					//查询云仓信息
					YcJoinInfo join = iYcJoinInfoService.getJoinInfo(new YcJoinInfo(users.getId()));
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("user", users);
					if(join!=null){
						map.put("join", join);
					}else{
						map.put("join", new YcJoinInfo());
					}
					map.put("token", uuid);
					//保存user到session中
					request.getSession().setAttribute(uuid, users);
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, map, response);
					map.put("join", new PlatformUserController());
				//}
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, map, response);
			}else{
				FengUtil.RuntimeExceptionFeng("用户名或密码错误");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getLocalizedMessage(), response);
		}
		
	}
	/**
	 * 获取经销商详情
	 * Author:FENG
	 * 2016年7月14日
	 * @param request
	 * @param user
	 * @param response
	 */
	@RequestMapping("app/getYcDealerSingle")
	public void getDetailInfo(HttpServletRequest request,HttpServletResponse response){
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(),new LcPlatformUser());
			LcPlatformUser users = lpos.getSingleInfo(user);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, users, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, "暂无结果", response);
		}
	}
	
	/**
	 * 经销商注册
	 * @Author:luojing
	 * @2016年7月14日 下午3:46:17
	 */
	@RequestMapping("app/registerDealer")
	public void registerDealer(HttpServletRequest request,HttpServletResponse response){
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(),new LcPlatformUser());
			String validCode = DES.decrypt(request.getParameter("validCode"));
			if(validCode.equals(FengUtil.getCookie(request, user.getLogin_name()))){
				user.setCreate_time(DateUtil.getDateTimeFormatString());
				user.setMobile(user.getLogin_name());
				user.setUser_type(12);
				user.setStatus(1);
				Integer i = lpos.addUserInfo(user);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS,user, response);
				}else{
					FengUtil.RuntimeExceptionFeng("该账号已经注册");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("验证码不正确,请重新输入");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(), response);
		}
	}
	
	/** 
	* 修改方法   即修改密码
	* Author:luojing 
	*/ 
	@RequestMapping("app/modYcDealer") 
	public void modYcDealer(HttpServletRequest request,HttpServletResponse response) {  
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(),new LcPlatformUser());
			String validCode = DES.decrypt(request.getParameter("validCode"));
			if(FengUtil.getCookie(request, user.getMobile()).equals(validCode)){
				//需要新密码和经销商id
				Integer i =  lpos.updatePassword(user);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("密码修改失败,请稍后重试");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("验证码错误");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(), response);
		}
	}
	
	/**
	 * 忘记密码
	 * @Author:luojing
	 * @2016年7月19日 下午9:12:03
	 */
	@RequestMapping("app/forgetPassword") 
	public void forgetPassword(HttpServletRequest request,HttpServletResponse response) {  
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(), new LcPlatformUser());
			String validCode = DES.decrypt(request.getParameter("validCode"));
			//短信验证
			if(FengUtil.getCookie(request, user.getMobile()).equals(validCode)){
				Integer i = lpos.updatePassword(user);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("密码修改失败,请稍后重试");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("验证码错误");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(), response);
		}
	} 
}
