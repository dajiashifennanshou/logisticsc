package com.brightsoft.controller.tms.platform.customerServiceCenter;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformOrderComlainHandle;
import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.OrderComplainService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/complain")
public class ComplaintManageController {

	@Autowired
	private OrderComplainService orderComplainService;
	@Autowired
	private OutletsService outletsService;
	/**
	 * 跳转到投诉显示页面
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView toList(){
		ModelAndView mav = new ModelAndView("/tms/platform/customerservicecenter/complaitmanage/listcomplaininfo");
		return mav;
	}
	
	/**
	 * 查询投诉信息
	 * @param platformOrderComplain
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result searchComplain(PlatformOrderComplain platformOrderComplain,Page<?> page,
			HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId() == null||user.getOutletsId().equals("")){
			List<OutletsInfo> outs =outletsService.selectOutlesNotBind(user.getCompanyId());
			String ids = "";
			for(int i =0;i<outs.size();i++){
				if(i == 0){
					ids += outs.get(i).getId();
				}else{
					ids += ","+outs.get(i).getId();
				}
			}
			platformOrderComplain.setOutletsIds(ids);
		}else{
			platformOrderComplain.setOutletsIds(user.getOutletsId()+"");
		}
		result = orderComplainService.selectByCondition(page, platformOrderComplain, user);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 投诉处理
	 * @param platformOrderComlainHandle
	 * @param session
	 * @return
	 */
	@RequestMapping("/handle")
	@ResponseBody
	public Result handleComplain(PlatformOrderComlainHandle platformOrderComlainHandle,
			HttpSession session){
		Result result = new Result();
		User user =(User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			platformOrderComlainHandle.setHandlePeople(user.getLoginName());
			platformOrderComlainHandle.setHandleTime(DateTools.getYMDHMS());
			
			PlatformOrderComplain platformOrderComplain = new PlatformOrderComplain();
			platformOrderComplain.setId(platformOrderComlainHandle.getComplaintId());
			platformOrderComplain.setState(Const.PLATFORMUSER_ORDER_COMPLAIN_STATE_REPLIED);
			if(orderComplainService.insert(platformOrderComplain, platformOrderComlainHandle)>0){
				result.setResult(true);
			}
		}
		return result;
	}
}
