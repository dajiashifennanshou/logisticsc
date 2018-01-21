package com.yc.Controller; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.YcInstallCharge;
import com.yc.Service.IYcInstallChargeService;
import com.yc.Tool.DES;
import com.yc.Tool.FengUtil;
import com.yc.Tool.StrUtil;



/** 
* YcInstallCharge控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcInstallChargeController {  
	@Autowired 
	private IYcInstallChargeService iYcInstallChargeService; 


	/** 
	 * 根据货物类型获取货物安装费用
	 * Auther:FENG 
	 */ 
	@RequestMapping("app/getYcInstallCostBy") 
	public void getYcInstallCostBy(HttpServletRequest request,HttpServletResponse response) {  
		try{
			String type=StrUtil.getString(request.getParameter("type"), "");
			String branchNo=StrUtil.getString(request.getParameter("branchNo"), "");
			type = DES.decrypt(type);
			branchNo = DES.decrypt(branchNo);
			List<YcInstallCharge> lst=iYcInstallChargeService.getYcInstallCostBy(type,branchNo);
			//计算费用
			float sum=0;
			float del=0;
			for(YcInstallCharge yic:lst){
				sum+=yic.getInstallCharge();
				del+=yic.getDeliverCharge();
			}
			Map<String,Float> map=new HashMap<String,Float>();
			map.put("installCost", sum);
			map.put("deliveryCost", del);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, map, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, "暂无结果", response);
		}
	}

	
	
}
