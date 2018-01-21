package com.brightsoft.controller.tms.platform.customerServiceCenter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.PlatformOrderEvaluation;
import com.brightsoft.model.PlatformOrderEvaluationReply;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.OrderEvaluationService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/evaluation")
public class EvaluationManangeController {

	@Autowired
	private OrderEvaluationService orderEvaluationService;
	
	/**
	 * 跳转到评论显示列表
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView toListEvaluation(){
		ModelAndView mav = new ModelAndView("/tms/platform/customerservicecenter/evaluationmanage/listevaluationinfo");
		return mav;
	}
	
	/**
	 * 查询评论信息
	 * @param page
	 * @param session
	 * @param platformOrderEvaluation
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result searchEvaluation(Page<?> page,HttpSession session,
			PlatformOrderEvaluation platformOrderEvaluation){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			result = orderEvaluationService.selectByCondition(page, platformOrderEvaluation, user);
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 评论回复
	 * @param platformOrderEvaluation
	 * @param platformOrderEvaluationReply
	 * @return
	 */
	@RequestMapping("/reply")
	@ResponseBody
	public Result reply(PlatformOrderEvaluationReply platformOrderEvaluationReply,
			HttpSession session){
		Result result = new Result();
		
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			platformOrderEvaluationReply.setReplyPeople(user.getLoginName());
			platformOrderEvaluationReply.setReplyTime(DateTools.getYMDHMS());
			platformOrderEvaluationReply.setState(Const.PLATFORMUSER_ORDER_EVALUATION_REPLY_STATE_VALID);
			
			PlatformOrderEvaluation platformOrderEvaluation = new PlatformOrderEvaluation();
			platformOrderEvaluation.setId(platformOrderEvaluationReply.getEvaluateId());
			platformOrderEvaluation.setState(Const.PLATFORMUSER_ORDER_EVALUATION_STATE_REPLIED);
			
			if(orderEvaluationService.update2reply(platformOrderEvaluation, platformOrderEvaluationReply)>0){
				result.setResult(true);
			}
		}
		return result;
	}
}
