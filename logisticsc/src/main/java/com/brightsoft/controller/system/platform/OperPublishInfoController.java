package com.brightsoft.controller.system.platform;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PublishInfo;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.system.PublishInfoService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 货运交易系统--货运交易系统发布信息
 * 2016年3月17日 下午10:50:29
 * @author zhouna
 */
@Controller
@RequestMapping("/system/yypt/platinfo")
public class OperPublishInfoController {
	
	@Autowired
	private PublishInfoService publishInfoService;
	
	/**
	 * 跳转到货运交易系统发布的页面
	 * 2016年3月17日 下午10:55:09
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView publishplatfrominfo() {
		ModelAndView mView = new ModelAndView("/system/platform/PlatformInformation/platforminfopublish");
		return mView;
	}
	
	
	/**
	 * 货运交易系统--货运交易系统类型 的信息查询
	 * 2016年3月17日 下午10:55:46
	 * @param publishInfo
	 * @param session
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/listdata")
	@ResponseBody
	public Object findAllByType(Page<?> page,SearchParams searchParams) {
		Result result = new Result();
		result = publishInfoService.selectByCondition(searchParams, page, Const.INFO_PUBLISH_TYPE_PLATFORM);
		result.setResult(true);
		return result.getRows();
	}
	
	/**
	 * 货运交易系统--货运交易系统类型 的信息发布
	 * 2016年3月17日 下午10:55:46
	 * @param publishInfo
	 * @param session
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/platinsert")
	@ResponseBody
	public Result platinsert(PublishInfo publishInfo,HttpSession session){
		Result result = new Result();
		try {
			User user = new User();
			user.setId(1l);
			if (user != null) {
				publishInfo.setCreatePersonId(user.getId());
				publishInfo.setCreateTime(DateTools.getYMDHMS());
				publishInfo.setType(Const.INFO_PUBLISH_TYPE_PLATFORM);
				publishInfo.setStatus(1);
				if (publishInfoService.insert(publishInfo) > 0) {
					result.setResult(true);
					result.setMsg("系统信息发布成功！！！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
			result.setMsg("系统信息发布失败！！！");
		}
		return result;
	}
}
