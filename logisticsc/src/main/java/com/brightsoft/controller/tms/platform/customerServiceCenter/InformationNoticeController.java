package com.brightsoft.controller.tms.platform.customerServiceCenter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.NoticeInfo;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.NoticeService;
import com.brightsoft.service.tms.system.PublishInfoService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/notice")
public class InformationNoticeController {

	@Autowired
	private PublishInfoService publishInfoService;
	
	@RequestMapping("/list")
	public ModelAndView toListNotice(){
		ModelAndView mav = new ModelAndView("/tms/platform/customerservicecenter/informationnotice/listnoticeinfo");
		return mav;
	}
	
	/**
	 * 查询通知消息
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result listNoticeInfo(Page<?> page,SearchParams searchParams,HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			result = publishInfoService.selectByTypeAndCondition(searchParams, page,0);
			result.setResult(true);
		}
		return result;
	}
}
