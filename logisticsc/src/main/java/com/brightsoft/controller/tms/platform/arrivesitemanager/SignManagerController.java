package com.brightsoft.controller.tms.platform.arrivesitemanager;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.SignRecord;
import com.brightsoft.model.User;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.tms.platform.arrivesitemanager.SignRecordService;

/**
 * 签收 管理 Controller
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/sign")
public class SignManagerController extends BaseController{

	@Autowired
	private SignRecordService signRecordService;
	
	@RequestMapping("/tosignmanagerlist")
	public String toSignManagerList(){
		return "/tms/platform/arrivesitemanager/sign-manager";
	}
	
	/**
	 * 运单签收
	 * @param record
	 * @return
	 */
	@RequestMapping("/signwaybill")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public Object signWayBill(SignRecord record, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = signRecordService.saveSignRecord(record, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取签收记录
	 * @param wayBillNumber
	 * @return
	 */
	@RequestMapping("/getsignrecord")
	@ResponseBody
	public Object getSignRecord(String wayBillNumber){
		return signRecordService.selectByWayBillNumber(wayBillNumber);
	}
}
