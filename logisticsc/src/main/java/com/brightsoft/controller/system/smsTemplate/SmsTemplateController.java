package com.brightsoft.controller.system.smsTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.model.SmsTemplate;
import com.brightsoft.service.platform.SmsTemplateServiceImpl;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
public class SmsTemplateController {

	@Autowired
	private SmsTemplateServiceImpl smsTemplateServiceImpl;
	
	/**
	 * 方法描述：跳转到短信模板页面
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午5:59:10
	 */
	@RequestMapping("/sys/template/toSmsTemplate")
	public String toSmsTemplate(){
		return "/system/template/smsTemplateList";
	}
	
	/**
	 * 方法描述：获取短信模板
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午5:55:19
	 */
	@RequestMapping("/sys/template/getSmsTemplateList")
	@ResponseBody
	public Result getSmsTemplateList(Page<?> page){
		Result result= new Result();
		List<SmsTemplate> templateList = smsTemplateServiceImpl.selectSmsTemplate(page);
		int totalCount = smsTemplateServiceImpl.selectTemplateListCount();
		result.setResults(totalCount);
		result.setRows(templateList);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 方法描述：更新短息模板
	 * @param smsTemplate
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午5:58:39
	 */
	@RequestMapping("/sys/template/updateSmsTemplate")
	@ResponseBody
	public Result updateSmsTemplate(SmsTemplate smsTemplate){
		Result result = new Result();
		if(smsTemplateServiceImpl.updateSmsTemplate(smsTemplate)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 方法描述：禁用/启用模板
	 * @param templateId
	 * @param isEnabled
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午6:59:04
	 */
	@RequestMapping("/sys/template/updateEnabled")
	@ResponseBody
	public Result updateEnabled(Long templateId, Integer isEnabled){
		Result result = new Result();
		if(templateId != null && isEnabled != null){
			Boolean flag = isEnabled==0?false:true;
			if(smsTemplateServiceImpl.updateEnabled(templateId, flag)){
				result.setResult(true);
			}
		}
		return result;
	}
}

