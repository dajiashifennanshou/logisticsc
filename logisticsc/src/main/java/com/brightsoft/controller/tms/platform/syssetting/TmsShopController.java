package com.brightsoft.controller.tms.platform.syssetting;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUserCompaninfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformCompanyInfoServiceImpl;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/shop/")
public class TmsShopController {

	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private PlatformCompanyInfoServiceImpl platformCompanyInfoServiceImpl;
	
	/**
	 *跳转到商铺显示页面
	 * @param session
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView toListShop(HttpSession session){
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/listshopinfo");
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!= null){
			PlatformUserCompany platformUserCompany = platformUserCompanyService.selectCompanyInfo(user.getCompanyId());
			PlatformUserCompaninfo platformUserCompanyinfo = platformCompanyInfoServiceImpl.selectByCompanyId(user.getCompanyId());
			mav.addObject("platformUserCompanyinfo",platformUserCompanyinfo);
			mav.addObject("platformUserCompany",platformUserCompany);
			mav.addObject("outletsId", user.getOutletsId());
		}
		return mav;
	}
	
	/**
	 * 获取商铺网点信息
	 * @param page
	 * @param outletsInfo
	 * @param session
	 * @return
	 */
	@RequestMapping("/getoutlets")
	@ResponseBody
	public Result getOutlets(Page<?> page,OutletsInfo outletsInfo,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			result = outletsService.selectBySelectedCondition(user, outletsInfo, page);
		}
		return result;
		
	}
	
	/**
	 * 获取商铺线路信息
	 * @param page
	 * @param lineInfo
	 * @param session
	 * @return
	 */
	@RequestMapping("/getlines")
	@ResponseBody
	public Result getLines(Page<?> page,LineInfo lineInfo,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			result = lineInfoService.selectByCondition(user, lineInfo, page);
		}
		return result;
		
  	}
	
	/**
	 * 更新商铺基本信息
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateComInfo(PlatformUserCompaninfo platformUserCompanyinfo,
			PlatformUserCompany platformUserCompany,HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		return platformUserCompanyService.updateCompanyInfoOnly(user,platformUserCompany,platformUserCompanyinfo);
	}
}
