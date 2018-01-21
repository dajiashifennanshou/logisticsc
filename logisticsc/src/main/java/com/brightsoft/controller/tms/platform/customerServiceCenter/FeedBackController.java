package com.brightsoft.controller.tms.platform.customerServiceCenter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Feedback;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.FeedbackService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/feedback")
public class FeedBackController {

	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 跳转到反馈 显示页面
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView toListFeedBack(){
		ModelAndView mav = new ModelAndView("/tms/platform/customerservicecenter/feedback/listfeedbackinfo");
		return mav;
	}
	
	/**
	 * 查询反馈信息
	 * @param page
	 * @param feedback
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result search(SearchParams searchParams,Page<?> page,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			if(user.getOutletsId()!=null){
				result = feedbackService.selectByOutletsIdAndCondition(page, searchParams, user.getOutletsId());
			}else{
				result = feedbackService.selectByCompanyIdAndCondition(page, searchParams, user.getCompanyId());
			}
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 添加反馈信息
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addFeedback(Feedback feedback,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			if(user.getOutletsId()!=null){
				feedback.setCreateTime(DateTools.getYMDHMS());
				feedback.setOutletsId(user.getOutletsId());
				feedback.setStatus(Const.TMS_FEEDBACK_STATUS_VALID);
				feedback.setCreatePersonId(user.getId());
				if(feedbackService.insert(feedback)>0){
					result.setResult(true);
				}
			}else{
				result.setMsg("请选择网点");
			}
			
		}
		return result;
	}
}
