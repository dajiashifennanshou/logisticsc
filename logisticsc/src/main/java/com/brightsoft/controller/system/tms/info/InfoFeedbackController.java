package com.brightsoft.controller.system.tms.info;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Feedback;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.FeedbackService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/system/feedback/")
public class InfoFeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("listFeedback")
	public String toListFeedback(){
		return "/system/tms/feedback/list_feedback_info";
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
		Result result = feedbackService.selectByCondition(page, searchParams);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result updateFeedback(Feedback feedback,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user!=null){
			feedback.setReplyPersonId(user.getId());
			feedback.setReplyTime(DateTools.getYMDHMS());
			if(feedbackService.update2Reply(feedback)){
				result.setResult(true);
			}
		}
		return result;
	}
}
