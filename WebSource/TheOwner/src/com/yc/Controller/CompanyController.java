package com.yc.Controller;


import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.LogisticsInfo;
import com.yc.Entity.PlatformDeliverGoods;
import com.yc.Entity.PlatformUserCompany;
import com.yc.Entity.TmsLineInfo;
import com.yc.Service.PlatformUserCompanyService;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;

/**
 * 物流商信息
 * Author:luojing
 * 2016年6月27日 下午5:32:27
 */
@Controller
public class CompanyController {
	
	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	
	/**
	 * 集合查询附近的物流商信息
	 * Author:luojing
	 * @param longitude, latitude
	 * 2016年6月27日 下午5:33:59
	 */
	@RequestMapping("app/getNearbyLineInfo")
	public void getNearbyLineInfo(Integer page,LogisticsInfo log,Integer rows,TmsLineInfo line,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<LogisticsInfo> pager = new Pager<LogisticsInfo>(page, rows);
			pager = platformUserCompanyService.getPageLogisticsInfo(pager, log);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 集合查询物流商线路信息
	 * Author:luojing
	 * 2016年6月28日 下午1:36:59
	 */
	@RequestMapping("app/getLineInfo")
	public void getLineInfo(PlatformDeliverGoods pdg,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformDeliverGoods> pager = new Pager<PlatformDeliverGoods>(page, rows);
			pager = platformUserCompanyService.getPageLineInfo(pager, pdg);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 集合查询物流商(公司)信息（根据物流商名称模糊查询）
	 * Author:luojing
	 * 2016年6月28日 下午2:03:13
	 */
	@RequestMapping("app/getCompanyInfo")
	public void getCompanyInfo(PlatformUserCompany com,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformUserCompany> pager = new Pager<PlatformUserCompany>(page, rows);
			pager = platformUserCompanyService.getPagerCompanyInfo(pager, com);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}

	/**
	 * （单个查询）查询物流商详细信息
	 * Author:luojing
	 * 2016年6月28日 下午2:03:13
	 */
	@RequestMapping("app/getCompanyDetails")
	public void getCompany(PlatformUserCompany com,BigInteger userId,HttpServletRequest request,HttpServletResponse response){
		try{
			com = platformUserCompanyService.getPlatformUserCompany(com,userId);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, com, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 查询用户公司信息
	 * @Author:luojing
	 * @2016年8月18日 上午10:50:25
	 */
	@RequestMapping("app/getUserCompany")
	public void getUserCompany(PlatformUserCompany com,HttpServletRequest request,HttpServletResponse response){
		try{
			if(com.getId()!=null){
				PlatformUserCompany c = platformUserCompanyService.getUserCompany(com);
				if(c!=null){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS,c, response);
				}else{
					FengUtil.RuntimeExceptionFeng("未查询到公司信息");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("未获取到公司编号,无法查询");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
}
