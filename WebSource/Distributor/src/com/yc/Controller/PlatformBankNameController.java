package com.yc.Controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformBankName;
import com.yc.Service.PlatformBankNameService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
/**
 * 查询开户行
 * @Author:luojing
 * @2016年7月19日 下午7:41:29
 */
@Controller
public class PlatformBankNameController {
	
	@Autowired
	private PlatformBankNameService platformBankNameService;
	
	@RequestMapping("app/getProvinceName")
	public void getProvinceName(HttpServletRequest request,HttpServletResponse response){
		try{
			List<PlatformBankName> province = platformBankNameService.selectProvinceName();
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, province, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
	
	@RequestMapping("app/getCityName")
	public void getCityName(HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformBankName pfb = (PlatformBankName) FengUtil.getObject(request.getParameterMap(),new PlatformBankName());
			List<PlatformBankName> city = platformBankNameService.selectCityName(pfb);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, city, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
	
	@RequestMapping("app/getHeadName")
	public void getHeadName(HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformBankName pfb = (PlatformBankName) FengUtil.getObject(request.getParameterMap(), new PlatformBankName());
			List<PlatformBankName> head = platformBankNameService.selectHeadName(pfb);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, head, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}

	@RequestMapping("app/getBranchName")
	public void getBranchName(HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformBankName pfb = (PlatformBankName) FengUtil.getObject(request.getParameterMap(), new PlatformBankName());
			List<PlatformBankName> branch = platformBankNameService.selectBranchName(pfb);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, branch, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
	

}
