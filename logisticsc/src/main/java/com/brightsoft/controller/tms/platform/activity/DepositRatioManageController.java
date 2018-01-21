package com.brightsoft.controller.tms.platform.activity;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.DepositRatioStatusEnum;
import com.brightsoft.model.DepositRatio;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.DepositRatioService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/dpstrt")
public class DepositRatioManageController {

	@Autowired
	private DepositRatioService depositRatioService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	/**
	 * 跳转到预存费显示页面
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView toListDepositRatio(HttpSession session){
		ModelAndView mav = new ModelAndView("/tms/platform/activitymanage/listdepositratioinfo");
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId()!=null){
			List<LineInfo> lineInfos = lineInfoService.selectByOutletsId(user.getOutletsId());
			mav.addObject("lineInfos", lineInfos);
		}
		return mav;
	}
	
	/**
	 * 查询预存费比例
	 * @param page
	 * @param depositRatio
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result searchRatio(Page<?> page,DepositRatio depositRatio,
			HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			if(user.getOutletsId()!=null){
				result = depositRatioService.selectByOutletsIdAndCondition(page, depositRatio, user.getOutletsId());
			}else{
				result = depositRatioService.selectByCompanyIdAndCondition(page, depositRatio, user.getCompanyId());
			}
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 添加预存费信息
	 * @param depositRatio
	 * @param session
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addDepositRatio(DepositRatio depositRt,String saveType,HttpSession session){
		Result result = new Result();
		User user  =  (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			if(user.getOutletsId()!=null){
				depositRt.setCreatePersonId(user.getId());
				depositRt.setCreateTime(DateTools.getYMDHMS());
				depositRt.setStatus(DepositRatioStatusEnum.NO_PUBLISH.getValue());
				if(depositRatioService.insert(depositRt)>0){
					result.setResult(true);
				}
			}
		}
		return result;
	}
	
	/**
	 * 更新线路费率信息
	 * @param depositRatio
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result editDeposit(DepositRatio depositRatio){
		Result result = new Result();
		if(depositRatioService.update(depositRatio)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 预存费信息删除
	 * @param depositIds
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteDeposit(@RequestParam("depositIds[]")String[] depositIds){
		Result result = new Result();
		if(depositIds!=null
				&&depositIds.length>0){
			if(depositRatioService.deleteBatch(depositIds)){
				result.setResult(true);
			}
		}
		return result;
	}

	/**
	 * 发布预存费信息
	 * @param depositIds
	 * @return
	 */
	@RequestMapping("/publish")
	@ResponseBody
	public Result publishDeposit(@RequestParam("depositIds[]")String[] depositIds){
		Result result = new Result();
		if(depositIds!=null
				&&depositIds.length>0){
			if(depositRatioService.update2Publish(depositIds)){
				result.setResult(true);
			}
		}
		return result;
	}
}
