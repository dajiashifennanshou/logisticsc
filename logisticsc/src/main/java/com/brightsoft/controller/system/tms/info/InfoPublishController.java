package com.brightsoft.controller.system.tms.info;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PublishInfo;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.base.EnumParserService;
import com.brightsoft.service.tms.system.PublishInfoService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/system/tms/publish")
public class InfoPublishController {

	@Autowired
	private PublishInfoService publishInfoService;
	
	@Autowired
	private EnumParserService enumParserService;
	
	@RequestMapping("/list")
	public String toListPublishInfo(ModelMap model){
//		model.addAttribute("publishTypes", enumParserService.getPublishType());
		return "/system/tms/publish/listpublishinfo";
	}
	
	/**
	 * 查询发布的信息
	 * @param page`
	 * @param publishInfo
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result search(SearchParams searchParams,Page<?> page){
		Result result = publishInfoService.selectByCondition(searchParams, page, Const.INFO_PUBLISH_TYPE_TMS);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 发布信息
	 * @param publishInfo
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result insert(PublishInfo publishInfo,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user != null){
			publishInfo.setCreatePersonId(user.getId());
			publishInfo.setCreateTime(DateTools.getYMDHMS());
			publishInfo.setStatus(1);
			publishInfo.setType(Const.INFO_PUBLISH_TYPE_TMS);
			if(publishInfoService.insert(publishInfo)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("请登录");
		}
		return result;
	}
	
	/**
	 * 删除信息
	 * @param publishId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("publishIds[]")String publishIds[]){
		Result result = new Result();
		if(publishIds!=null&&publishIds.length>0){
			List<Long> list = new ArrayList<Long>();
			for (String publishId : publishIds) {
				list.add(Long.parseLong(publishId));
			}
			if(publishInfoService.update2Delete(list)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("请登录");
		}
		return result;
	}
}
