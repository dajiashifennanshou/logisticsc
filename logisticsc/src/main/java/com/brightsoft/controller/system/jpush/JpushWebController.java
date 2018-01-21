package com.brightsoft.controller.system.jpush;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.JpushUserInfo;
import com.brightsoft.utils.jpush.JpushUtil;
import com.brightsoft.utils.yc.FengUtil;
/**
 * 极光推送
 * @Author:luojing
 * @2016年7月21日 下午2:25:45
 */
@Controller
@RequestMapping("/system/jpush/web/")
public class JpushWebController {
	/**
	 * 前往极光推送页
	 * @Author:luojing
	 * @2016年7月14日 下午6:06:36
	 */
	@RequestMapping("toJpush")
	public String toJpush(HttpServletRequest request,HttpServletResponse response){
		return "/system/jpush/sendMessage";
	} 
	
	/**
	 * 推送消息
	 * @Author:luojing
	 * @2016年7月18日 下午2:50:33
	 */
	@RequestMapping("sendMessage")
	public void sendMessage(JpushUserInfo jpush,HttpServletRequest request,HttpServletResponse response){
		try{
			Integer i = JpushUtil.sendMessage(jpush);
			if(i==200){
				FengUtil.OUTAPPERROR(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.OUTAPPERROR(Constant.APP_ERROR_01, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
