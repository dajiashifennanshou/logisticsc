package com.brightsoft.controller.system.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.PosBind;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.system.platform.PosManagerService;
import com.brightsoft.service.tms.platform.OutletsService;

/**
 * POS机管理 Controller
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/system/pos/")
public class PosManagerController extends BaseController{

	@Autowired
	private PosManagerService posManagerService;
	
	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	@Autowired
	private OutletsService outletsService;
	
	/**
	 * 跳转到 POS机管理 页面
	 * @return
	 */
	@RequestMapping("/toposmanagerpage")
	public String toPosManagerPage(){
		return "/system/pos/pos_manager";
	}

	/**
	 * 获取POS机绑定列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getposbindlist")
	@ResponseBody
	public Object getPosBindList(BaseSearchParams params){
		return posManagerService.selectPosBindList(params);
	}
	
	/**
	 * 绑定POS机
	 * @param posBind
	 * @return
	 */
	@RequestMapping("/bindpos")
	@ResponseBody
	public String bindPos(PosBind posBind){
		int rows = posManagerService.saveBindPos(posBind);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 解绑POS机
	 * @param posBindId
	 * @return
	 */
	@RequestMapping("/unbindpos")
	@ResponseBody
	public String unbindPos(Long posBindId){
		int rows = posManagerService.saveUnbindPos(posBindId);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取公司信息
	 * @return
	 */
	@RequestMapping("/getcompanyinfolist")
	@ResponseBody
	public Object getCompanyInfoList(){
		return platformUserCompanyService.selectAllCompany();
	}
	
	/**
	 * 获取网点信息
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/getoutletsinfolist")
	@ResponseBody
	public Object getOutletsInfoList(Long companyId){
		return outletsService.selectByCompanyId(companyId);
	}
	
	/**
	 * 验证 网点是否绑定银行卡
	 * @param outletsId
	 * @return
	 */
	@RequestMapping("/validoutletsisbindcard")
	@ResponseBody
	public boolean validOutletsIsBindCard(Long outletsId){
		return posManagerService.validOutletsIsBindCard(outletsId);
	}
	
	/**
	 * 验证POS机是否 已绑定
	 * @param posSn
	 * @return
	 */
	@RequestMapping("/validposisbind")
	@ResponseBody
	public boolean validPosIsBind(String posSn){
		return posManagerService.validPosIsBind(posSn);
	}
}
