package com.brightsoft.controller.system.platform;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.service.system.platform.OperSogoManageService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 货运交易系统--商户管理
 * 2016年3月21日 上午11:51:43
 * @author zhouna
 */

@Controller
@RequestMapping("/system/yypt/sogo")
public class OperSogoManageController {
	
	@Autowired
	private OperSogoManageService operSogoManageService;
	
	/**货运交易系统的商户管理*/
	@RequestMapping("/list")
	public String OperSogoList() {
		return "/system/platform/sogomanagement/sogoment";
	}
	
	/**查询保险信息*/
	@RequestMapping("/listdata")
	@ResponseBody
	public Object OperSogoListfindall(HttpSession session ,Page<?> page,PlatformInsurance platformInsurance) {
		Result result = new Result();
		result = operSogoManageService.OperSogoListfindall(session,page,platformInsurance);
		result.setResult(true);
		return result.getRows();
	}
	
	/**通过id查看保险信息*/
	@RequestMapping("/sogobyid")
	@ResponseBody
	public Object OperSogoById(long id) {
		PlatformInsurance platformInsurance = operSogoManageService.OperSogoById(id);
		return platformInsurance;
	}

}
