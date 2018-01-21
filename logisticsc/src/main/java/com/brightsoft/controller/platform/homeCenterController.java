package com.brightsoft.controller.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PlatformHomeCount;
import com.brightsoft.model.PlatformHomeOrderCity;
import com.brightsoft.model.SysNews;
import com.brightsoft.model.SysPartner;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.system.platform.NewsServiceImpl;
import com.brightsoft.service.system.platform.SysPartnerServiceImpl;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 首页数据
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/homeCenter")
public class homeCenterController {
	
	@Autowired
	private PlatformOrderServiceImpl orderServiceImpl;
	
	@Autowired
	private PlatformUserCompanyService userCompanyServiceImpl;
	
	@Autowired
	private PlatformUserService userService;
	
	@Autowired
	private SysPartnerServiceImpl sysPartner;
	
	@Autowired
	private NewsServiceImpl newsService;
	/**
	 * 跳转公司新闻首页
	 * @return
	 */
	@RequestMapping("/toNewsCompanyIndex")
	public String toNewsCompanyIndex(){
		return "/platform/news/news_company_index";
	}
	
	/**
	 * 跳转新闻资讯首页
	 * @return
	 */
	@RequestMapping("/toNewsinfoIndex")
	public String toNewsinfoIndex(){
		return "/platform/news/news_information_index";
	}
	@RequestMapping("/topInsurance")
	public String topInsurance(){
		return "/platform/platform-Insurance";
	}
	
	//獲取合作夥伴
	@RequestMapping("/getPartner")
	@ResponseBody
	public Result getPartner(){
		Result ret = new Result();
		List<SysPartner> list = sysPartner.selectListParner();
		ret.setData(list);
		ret.setResult(true);
		return ret;
	}
	/**
	 * 热门专线
	 * @return
	 */
	@RequestMapping("/getHotLine")
	@ResponseBody
	public Result getHotLine(){
		Result ret = new Result();
		ret.setRows(orderServiceImpl.selectHotLogst());
		ret.setResult(true);
		return ret;
	}
	
	/**
	 * 获取最近下单记录
	 * @return
	 */
	@RequestMapping("/getRecentlyOrd")
	@ResponseBody
	public Result getRecentlyOrder(){
		Result result = new Result();
		result.setRows(orderServiceImpl.selectOrderRecently());
		result.setResult(true);
		return result;
	}
	
	/**
	 * 推荐物流商
	 * @return
	 */
	@RequestMapping("/getUserCompany")
	@ResponseBody
	public Result getUserCompany(){
		Result result = new Result();
		result.setRows(userCompanyServiceImpl.selectCompany());
		result.setResult(true);
		return result;
	}
	/**
	 * 获取首页总数
	 * @return
	 */
	@RequestMapping("/getUserCount")
	@ResponseBody
	public Result getUserCount(){
		Result result = new Result();
		PlatformHomeCount homeCount = new PlatformHomeCount();
		homeCount.setFinalPriceCount(orderServiceImpl.selectOrderfinalPrice());
		homeCount.setOrderCount(orderServiceImpl.selectOrderCount());
		homeCount.setUserCompanyCount(userService.selectUserTypeCompany());
		homeCount.setUserProviderCount(userService.selectUserTypeProviders());
		result.setData(homeCount);
		result.setResult(true);
		return result;
	}
	/**
	 * 热门城市
	 * @return
	 */
	@RequestMapping("/getHomeOrderCity")
	@ResponseBody
	public Result getHomeOrderCity(){
		Result result = new Result();
		List<PlatformHomeOrderCity> orderCities = orderServiceImpl.selectHomeOrderCity();
		result.setData(orderCities);
		result.setResult(true);
		return result;
	}
	/**
	 * 公司新闻
	 * @param news
	 * @return
	 */
	@RequestMapping("/getNews")
	@ResponseBody
	public Result getNews(SysNews news){
		Result ret = new Result();
		List<SysNews> sysNews = newsService.selectNewsList(news);
		ret.setData(sysNews);
		ret.setResult(true);
		return ret;
	}
	
	@RequestMapping("/getNewsInfo")
	public ModelAndView getNewsInfo(Long id){
		ModelAndView mv = new ModelAndView();
		SysNews news = null;
		news = newsService.selectNewsId(id);
		if(news == null){
			mv.setViewName("../../../platform-index");
		}else{
			mv.addObject("news",news);
			mv.setViewName("/platform/news/news_info");
		}
		return mv;
		
	}
	/**
	 * 获取新闻
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("/getNewsList")
	@ResponseBody
	public Result getNewsList(SearchParams searchParams, Page<?> page){
		Result srt = new Result();
		page = newsService.selectBySelectedCondition(searchParams, page);
		srt.setData(page);
		srt.setResult(true);
		srt.setMsg("获取新闻成功!");
		return srt;
	}
}
