package com.brightsoft.controller.system.lineCompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.SystemLineInfo;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.system.systemline.SystemLineService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@RequestMapping("/system/lineCmp/")
@Controller
public class SystemLineCompManageController {

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SystemLineService systemLineService;
	
	/**
	 * 跳转到线路信息发布页面
	 * @return
	 */
	@RequestMapping("toLineCompMan")
	public String toInfoLineCompanyPublish(ModelMap map){
		List<PlatformDictionary> transportModeList = dictionaryService.selectDictDataByType(DictionaryType.TRANSPORT_MODE);
		map.addAttribute("transportModes", transportModeList);
		return "/system/lineCompany/info_line_company_manage";
	}
	
	/**
	 * 跳转到线路信息字典页面
	 * @return
	 */
	@RequestMapping("toLineCompDic")
	public String toInfoLineCompanyDic(){
		return "";
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Result searchLine(SearchParams searchParams,Page<?> page){
		Result result = systemLineService.selectByCondition4Page(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public Result addSystemLine(SystemLineInfo systemLineInfo){
		Result result = new Result();
		if(systemLineService.insert(systemLineInfo)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Result deleteSystemLine(@RequestParam("lineIds[]")String[] lineIds){
		Result result = new Result();
		if(lineIds!=null && lineIds.length>0){
			ArrayList<Long> list = new ArrayList<Long>();
			for (String lineId : lineIds) {
				list.add(Long.parseLong(lineId));
			}
			if(systemLineService.delete(list)){
				result.setResult(true);
			}
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result updateSystemLine(SystemLineInfo systemLineInfo){
		Result result = new Result();
		if(systemLineService.update(systemLineInfo)){
			result.setResult(true);
		}
		return result;
	}
}
