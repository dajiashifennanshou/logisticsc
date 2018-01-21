package com.brightsoft.controller.system.tms.waybill;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.OrderSearchParams;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.tms.platform.OutletsService;

/**
 * 专线营运系统 订单管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/sys/order")
public class SystemOrderManagerController extends BaseController{

	@Autowired
	private PlatformOrderServiceImpl platformOrderService;
	@Autowired
	private OutletsService outletsService;
	/**
	 * 跳转到 订单管理页面
	 * @return
	 */
	@RequestMapping("/toordermanagerpage")
	public String toOrderManagerPage(){
		return "/system/tms/waybill/order-manager";
	}
	
	/**
	 * 查询 订单列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object search(OrderSearchParams params,HttpSession session){
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
			if(outs == null || outs.size() == 0){
				params.setOutletsIds("no");
			}else{
				params.setOutletsIds(ids);
			}
		}else{
			params.setOutletsIds(user.getOutletsId()+"");
		}
		return platformOrderService.selectByParamsOfPlatform(params);
	}
}
