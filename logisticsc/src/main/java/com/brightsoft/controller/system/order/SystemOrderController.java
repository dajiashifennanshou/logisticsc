package com.brightsoft.controller.system.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.PlatformMineOrder;
import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.PlatformOrderComplainInfo;
import com.brightsoft.model.PlatformOrderEvaluationInfo;
import com.brightsoft.model.PlatformOrderMineComplain;
import com.brightsoft.model.PlatformOrderMineEvaluation;
import com.brightsoft.service.platform.PlatformOrderComplainHandleServiceImpl;
import com.brightsoft.service.platform.PlatformOrderComplainServiceImpl;
import com.brightsoft.service.platform.PlatformOrderEvaluationReplyServiceImpl;
import com.brightsoft.service.platform.PlatformOrderEvaluationServiceImpl;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Controller
@RequestMapping("/systemOrder")
public class SystemOrderController extends BaseController {
	
	@Autowired
	private PlatformOrderServiceImpl orderServiceImpl;
	
	@Autowired
	private PlatformOrderEvaluationServiceImpl orderEvaluationService;
	
	@Autowired
	private PlatformOrderComplainServiceImpl orderComplainService;
	
	@Autowired
	private PlatformOrderEvaluationReplyServiceImpl orderEvaluationReplyService;

	@Autowired
	private PlatformOrderComplainHandleServiceImpl orderComplainHandleService;
	
	@RequestMapping("/getOrderList")
	@ResponseBody
	public Result getOrderList(Page<?> page,
			PlatformMineOrder mineOrder){
		Result result = new Result();
		
		page = orderServiceImpl.selectBySelectedOrder(mineOrder, page);
		result.setRows((List<?>) page.getParams().get(WebConstant.ROWS));
		//result.setResults(sysUserService.getSysUsersCount(page));
		return result;
	}
	
	/**
	 * 货运交易系统 评价管理
	 * @param page
	 * @param session
	 * @param platformOrderEvaluation
	 * @return
	 */
	@RequestMapping(value= "/getEvaluation",method= RequestMethod.POST)
	@ResponseBody
	public Result getEvaluation(Page<?> page,HttpSession session,PlatformOrderMineEvaluation platformOrderEvaluation){
		Result result = orderEvaluationService.selectByConditionPlatfromList(platformOrderEvaluation, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查看评价回复
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/doGetEvaluationReply")
	public ModelAndView doGetEvaluationReply(Long id){
		ModelAndView mv = new ModelAndView();
		PlatformOrderEvaluationInfo evaluationInfo =orderEvaluationReplyService.selectEvaluationReplySys(id);
		mv.addObject("data", evaluationInfo);
		mv.setViewName("/system/order/order_evaluate_detail");
		return mv;
	}
	
	/**
	 * 修改评论状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateEvaluation",method= RequestMethod.POST)
	@ResponseBody
	public Result updateEvaluation(Long id){
		Result result = new Result();
		if(orderEvaluationService.updatesys(id)){
			result.setResult(true);
		}else{
			result.setResult(false);
		}
		return result;
	}
	
	@RequestMapping(value= "/getComplainList",method= RequestMethod.POST)
	@ResponseBody
	public Result getEvaluation(Page<?> page,HttpSession session,
			PlatformOrderMineComplain orderMineComplain){
		Result result = orderComplainService.selectByConditionPlatfromList(orderMineComplain, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查看投诉回复
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/doGetComplainHandle")
	public ModelAndView doGetComplainHandle(Long id){
		ModelAndView mv = new ModelAndView();
		PlatformOrderComplainInfo  complainInfo = orderComplainHandleService.selectComlainHandle(id);
		mv.addObject("data", complainInfo);
		mv.setViewName("/system/order/order_complain_detail");
		return mv;
	}
	
	/**
	 * 修改投书状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateComplainHandle",method= RequestMethod.POST)
	@ResponseBody
	public Result updateComplainHandle(Long id){
		Result result = new Result();
		boolean res = false;
		try {
			PlatformOrderComplain platformOrderComplain = orderComplainService.selectByPrimaryKey(id);
			if(platformOrderComplain.getStatus() == null || platformOrderComplain.getStatus()==Const.STATE_YES){
				platformOrderComplain.setStatus(Const.STATE_NO);
			}else if(platformOrderComplain.getStatus()==Const.STATE_NO){
				platformOrderComplain.setStatus(Const.STATE_YES);
			}
			orderComplainService.update2handle(platformOrderComplain);
			res =true;
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		result.setResult(res);
		
		return result;
	}
	
	
	/**
	 * 用户订单数量排名
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getOrderUserRanking",method= RequestMethod.POST)
	@ResponseBody
	public Result getOrderUserRanking(String loginName,Page<?> page){
		Result result  = orderServiceImpl.getOrderUserRanking(loginName, page);
		result.setResult(true);
		return result;
	}
	
	
	/**
	 * 接收订单排名
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getOrderCompanyRanking",method= RequestMethod.POST)
	@ResponseBody
	public Result getOrderCompanyRanking(String companyName,String companyCode,String loginName,Page<?> page){
		Result result  = orderServiceImpl.getOrderCompanyRanking(companyName, companyCode, loginName, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 异常订单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getExceptionOrderList",method= RequestMethod.POST)
	@ResponseBody
	public Result getExceptionOrderList(Page<?> page,PlatformMineOrder mineOrder){
		Result result = new Result();		
		page = orderServiceImpl.selectBySelectedOrder(mineOrder, page);
		result.setRows((List<?>) page.getParams().get(WebConstant.ROWS));

		return result;
	}
	@RequestMapping(value="/getSysOrderCount",method= RequestMethod.POST)
	@ResponseBody
	public Result getSysOrderCount(){
		Result result = new Result();
		Map<Object, Object> map = new HashMap<Object, Object>();
		int[] y = orderServiceImpl.syscountOrder();
		map.put("y",y);
		result.setData(map);
		return result;
	}
}
