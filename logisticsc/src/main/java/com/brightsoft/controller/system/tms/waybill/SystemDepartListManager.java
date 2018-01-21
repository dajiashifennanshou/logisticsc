package com.brightsoft.controller.system.tms.waybill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.DepartListSearchParams;
import com.brightsoft.service.tms.platform.startsitemanager.DepartListService;

/**
 * 专线营运系统 发车单管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/sys/depart")
public class SystemDepartListManager extends BaseController{

	@Autowired
	private DepartListService departListService;
	
	/**
	 * 跳转到 发车管理页面
	 * @return
	 */
	@RequestMapping("/todepartlistmanager")
	public String toDepartListManagerPage(){
		return "/system/tms/waybill/depart-list-manager";
	}
	
	/**
	 * 查询发车单信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object search(DepartListSearchParams params){
		return departListService.selectByParams(params);
	}
}
