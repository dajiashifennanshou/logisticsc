package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.TmsWayBillOrderCargoInfo;
import com.brightsoft.service.yc.ITmsWayBillOrderCargoInfoService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* Tms运单货物详细控制器 
* Author:luojing 
*/ 
@Controller 
public class TmsWayBillOrderCargoInfoController {  
	@Autowired 
	private ITmsWayBillOrderCargoInfoService iTmsWayBillOrderCargoInfoService; 
	
	/**
	 * 运单货物信息查询
	 * Author:luojing
	 * 2016年6月24日 下午5:59:22
	 */
	@RequestMapping("getWayBillOrderCargoInfoList")
	public void getWayBillOrderCargoInfoList(TmsWayBillOrderCargoInfo wbc,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<TmsWayBillOrderCargoInfo> pager = new Pager<TmsWayBillOrderCargoInfo>(1,5);
			pager = iTmsWayBillOrderCargoInfoService.getWayBillOrderCargoInfoPager(pager,wbc);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
}
