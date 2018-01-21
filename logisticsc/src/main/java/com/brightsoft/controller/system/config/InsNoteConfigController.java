package com.brightsoft.controller.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.model.SysConfig;
import com.brightsoft.service.platform.SysConfigServiceImpl;
import com.brightsoft.utils.Result;
import com.sun.tools.internal.ws.processor.model.Model;

@Controller
public class InsNoteConfigController {

	@Autowired
	private SysConfigServiceImpl sysConfigServiceImpl;
	
	@RequestMapping("/sys/config/toInsNote")
	public String toSysConfigList(){
		return "/system/sysConfig/ins_note_config";
	}
	
	@RequestMapping("/sys/config/toEditInsNote")
	public String toEditInsNote(String itemAbbr,ModelMap model){
		SysConfig sysConfig = sysConfigServiceImpl.selectByItmeAbbr(itemAbbr);
		model.addAttribute("insNote", sysConfig);
		return "/system/sysConfig/ins_note_edit";
	}
	
	/**
	 * 方法描述：获取保险注意事项配置
	 * @param insComTag
	 * @return
	 * @author dub
	 * @version 2016年5月20日 下午3:39:11
	 */
	@RequestMapping("/sys/config/getInsNote")
	@ResponseBody
	public Result getInsNoteByItemAbbr(String itemAbbr){
		Result result = new Result();
		SysConfig insNote = sysConfigServiceImpl.selectByItmeAbbr(itemAbbr);
		result.setData(insNote);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("/sys/config/updateInsNote")
	@ResponseBody
	public Result updateInsNote(SysConfig sysConfig){
		Result result = new Result();
		if(sysConfigServiceImpl.updateInsNote(sysConfig)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("/sys/config/searchInsNote")
	@ResponseBody
	public Result searchInsNote(){
		Result result = sysConfigServiceImpl.selectInsNote();
		result.setResult(true);
		return  result;
	}
	
}

