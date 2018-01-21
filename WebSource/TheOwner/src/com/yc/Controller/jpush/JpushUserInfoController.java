package com.yc.Controller.jpush; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.JpushUserInfo;
import com.yc.Service.JpushUserInfoService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;   

/**
 * 极光推送
 * @Author:luojing
 * @2016年7月15日 下午2:32:44
 */
@Controller 
public class JpushUserInfoController {  
	@Autowired 
	private JpushUserInfoService iJpushUserInfoService; 
	
	/**
	 * 添加极光推送注册ID
	 * @Author:luojing
	 * @2016年7月15日 下午2:45:41
	 */
	@RequestMapping("app/editJpushUserInfo")
	public void editJpushUserInfo(JpushUserInfo user,HttpServletRequest request,HttpServletResponse response){
		try{
			//JpushUserInfo user = null;//(JpushUserInfo) FengUtil.getObject(request.getParameterMap(), new JpushUserInfo());
			user.setType(1);//0云仓，1发货人
			JpushUserInfo us = iJpushUserInfoService.getSingleInfo(user);
			Integer i = 0;
			if(us!=null){
				if(us.getRegisId().equals(user.getRegisId())){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					user.setId(us.getId());
					i = iJpushUserInfoService.modSingleInfo(user);
				}
			}else{
				i = iJpushUserInfoService.addSingleInfo(user);
			}
			if(i>0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.OUTAPPSUCCESS(Constant.APP_ERROR, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
			FengUtil.OUTAPPSUCCESS(Constant.APP_ERROR, response);
		}
	}
}
