package com.brightsoft.controller.tms.system.MemberManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.model.OutletsInfo;
import com.brightsoft.service.tms.system.Outlets4SysService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/sys/outlets")
public class OutletsManage {

	@Autowired
	private Outlets4SysService outlets4SysService;
	

	@RequestMapping("/list")
	public String toListOutlets(){
		return "/tms/system/membermanage/outletsmanage";
	}
	
	/**
	 * 查询所有网点信息
	 * @param page
	 * @param outletsInfo
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result search(Page<?> page,OutletsInfo outletsInfo){
		Result result = new Result();
		result = outlets4SysService.selectByCondition(outletsInfo, page);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("/detail/list")
	public ModelAndView toListDetail(Long outletsId){
		ModelAndView mav = new ModelAndView("/tms/system/membermanage/listoutletsDetails");
		mav.addObject("outletsId", outletsId);
		mav.addObject("outlets", outlets4SysService.selectOutletsDetailById(outletsId));
		return mav;
	}
	
	@RequestMapping("/detail/gtlnnf")
	@ResponseBody
	public Result getLineInfo(Page<?> page,Long outletsId){
		Result result = outlets4SysService.selectLineInfoByOutletsId(page, outletsId);
		result.setResult(true);
		return result;
	}
	
//	@RequestMapping("/detail/gtvhclnf")
//	@ResponseBody
//	public Result getVehicleInfo(Page<?> page,Long outletsId){
//		Result result = outlets4SysService.selectVehicleInfoByOutletsId(page, outletsId);
//		result.setResult(true);
//		return result;
//	}
	
	@RequestMapping("/detail/gtdrvrnf")
	@ResponseBody
	public Result getDriverInfo(Page<?> page,Long outletsId){
		Result result = outlets4SysService.selectDriverInfoByOutlets(page, outletsId);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("/detail/gtcstmrnf")
	@ResponseBody
	public Result getCustomerInfo(Page<?> page,Long outletsId){
		Result result = outlets4SysService.selectCustomerByOutletsId(page, outletsId);
		result.setResult(true);
		return result;
	}
}
