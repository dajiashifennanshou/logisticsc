package com.brightsoft.controller.tms.platform.syssetting;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.model.ShortMessage;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.ShortMessageService;

/**
 * 短信设置Controller
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/shortmessage")
public class ShortMessageController extends BaseController{

	@Autowired
	private ShortMessageService shortMessageService;
	
	/**
	 * 跳转到 短信设置 页面
	 * @return
	 */
	@RequestMapping("/toshortmessagepage")
	public String toShortMessagePage(){
		return "/tms/platform/systemmanager/shortmessage/short-message";
	}
	
	/**
	 * 获取短息 列表
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping("/getshortmessagelist")
	@ResponseBody
	public Object getShortMessageList(BaseSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		params.setOutletsId(user.getOutletsId());
		return shortMessageService.selectByParams(params);
	}
	
	/**
	 * 保存 短信 设置
	 * @param shortMessage
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String saveShortMessage(ShortMessage shortMessage, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		shortMessage.setOutletsId(user.getOutletsId());
		int rows = shortMessageService.saveShortMessage(shortMessage);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 删除短信设置
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteShortMessage(String ids){
		int rows = shortMessageService.deleteShortMessage(ids);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取短息 列表(货运交易系统)
	 * @param params
	 * @return
	 */
	@RequestMapping("/getshortmessageofplatform")
	@ResponseBody
	public Object getShortMessageOfPlatform(PlatformBaseSearchParams params){
		return shortMessageService.selectByParamsOfPlatform(params);
	}
}
