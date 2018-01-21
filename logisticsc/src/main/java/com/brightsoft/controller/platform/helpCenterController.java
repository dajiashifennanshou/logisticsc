package com.brightsoft.controller.platform;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.model.SysHelp;
import com.brightsoft.model.SysHelpContent;
import com.brightsoft.service.system.platform.HelpContentServiceImpl;
import com.brightsoft.service.system.platform.HelpServiceImpl;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/helpCenter")
public class helpCenterController {
	
	@Autowired
	private HelpServiceImpl helpService;
	
	@Autowired
	private HelpContentServiceImpl helpContentService;
	
	
	/**
	 * 跳转到帮助中心首页
	 * @return
	 */
	@RequestMapping("/toHelpCenter")
	public String toHelpCenter(String name,ModelMap model){
		String names = getHelpMenuValue(name);
		SysHelp help = helpService.getHelp();
		help.setHelpName("帮助中心");
		for (int i = 0; i < help.getHelps().size(); i++) {
			if(names.equals(help.getHelps().get(i).getHelpName())){
				if(help.getHelps().get(i).getHelps().size() > 0){
					for (int j = 0; j < help.getHelps().get(i).getHelps().size(); j++) {
						if(help.getHelps().get(i).getHelps().get(j).getHelps().size() > 0){
							for (int j2 = 0; j2 <help.getHelps().get(i).getHelps().get(j).getHelps().size(); j2++) {
								String id =help.getHelps().get(i).getHelps().get(j).getHelps().get(j2).getId().toString();
								model.addAttribute("helpId", id);
								break;
							}
						}else{
							String id =help.getHelps().get(i).getHelps().get(j).getId().toString();
							model.addAttribute("helpId", id);
							break;
						}
					}
				}else{
					String id = help.getHelps().get(i).getId().toString();
					model.addAttribute("helpId", id);
					break;
				}
			}
		}
		return "/platform/helpCenter/help_center";
	}
	
	private String getHelpMenuValue(String name){
		if(name.equals("manual")){
			return "专线营运系统使用手册";
		}else if(name.equals("law")){
			return "法律咨询";
		}else if(name.equals("aboutUs")){
			return "关于中工";
		}else if(name.equals("contactUs")){
			return "加入我们";
		}
		return "";
	}
	
	@RequestMapping("getHelp")
	@ResponseBody
	public Result getHelp(){
		Result ret = new Result();
		SysHelp help = helpService.getHelp();
		help.setHelpName("帮助中心");
		ret.setResult(true);
		ret.setData(help.getHelps());
		return ret;
		
	}
	
	@RequestMapping("getHelpContent")
	@ResponseBody
	public Result getHelpContent(Long id,HttpSession session){
		Result ret = new Result();
		SysHelpContent content = null;
		content = helpContentService.selectContent(id);
		if(content == null){
			ret.setResult(false);
		}else{
			ret.setResult(true);
			ret.setData(content);
		}
		return ret;
	}
	
}
