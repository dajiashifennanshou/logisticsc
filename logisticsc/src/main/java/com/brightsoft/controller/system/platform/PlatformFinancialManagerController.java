package com.brightsoft.controller.system.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.enums.ConsumeTypeEnum;
import com.brightsoft.common.enums.DepositRatioStatusEnum;
import com.brightsoft.common.enums.TakeCashStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.ConsumeRecordSearchParams;
import com.brightsoft.controller.vo.PlatformUserSearchParams;
import com.brightsoft.model.TakeCashApply;
import com.brightsoft.service.platform.PlatformUserConsumeRecordService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.system.platform.TakeCashApplyService;
import com.brightsoft.service.tms.platform.DepositRatioService;

/**
 * 财务管理Controller (货运交易系统)
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/system/financial")
public class PlatformFinancialManagerController extends BaseController{

	@Autowired
	private TakeCashApplyService takeCashApplyService;
	
	@Autowired
	private PlatformUserConsumeRecordService consumeRecordService;
	
	@Autowired
	private DepositRatioService depositRatioService;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	/**
	 * 跳转到提现审批页面
	 * @return
	 */
	@RequestMapping("/totakecashauditpage")
	public String toTakeCashAuditPage(HttpServletRequest request){
		request.setAttribute("takeCashStatusList", getTakeCashStatus());
		return "/system/platform/financialmanager/take-cash-audit";
	}
	
	private List<Map<String, Object>> getTakeCashStatus(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		TakeCashStatusEnum[] cashStatusEnums = TakeCashStatusEnum.values();
		for (TakeCashStatusEnum takeCashStatusEnum : cashStatusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", takeCashStatusEnum.getValue());
			map.put("name", takeCashStatusEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取 提现申请列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/gettakecashapplylist")
	@ResponseBody
	public Object getTakeCashApplyList(BaseSearchParams params){
		return takeCashApplyService.selectByParams(params);
	}
	
	/**
	 * 通过 提现审核
	 * @param id
	 * @return
	 */
	@RequestMapping("/takecashauditpass")
	@ResponseBody
	public String takeCashAuditPass(String id){
		TakeCashApply takeCashApply = new TakeCashApply();
		takeCashApply.setId(Long.parseLong(id));
		takeCashApply.setStatus(TakeCashStatusEnum.AUDIT_PASS.getValue());
		int rows = takeCashApplyService.saveTakeCashAudit(takeCashApply);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 不通过 提现审核
	 * @param id
	 * @return
	 */
	@RequestMapping("/takecashauditnotpass")
	@ResponseBody
	public String takeCashAuditNotPass(String id){
		TakeCashApply takeCashApply = new TakeCashApply();
		takeCashApply.setId(Long.parseLong(id));
		takeCashApply.setStatus(TakeCashStatusEnum.AUDIT_NOT_PASS.getValue());
		int rows = takeCashApplyService.saveTakeCashAudit(takeCashApply);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转会员提现记录页面
	 * @return
	 */
	@RequestMapping("/totakecashrecordpage")
	public String toTakeCashRecordPage(HttpServletRequest request){
		request.setAttribute("takeCashStatusList", getTakeCashStatus());
		return "/system/platform/financialmanager/take-cash-record";
	}
	
	/**
	 * 跳转会员充值记录页面
	 * @return
	 */
	@RequestMapping("/torechargerecordpage")
	public String toRechargeRecordPage(){
		return "/system/platform/financialmanager/recharge-record";
	}
	
	/**
	 * 获取会员 充值记录
	 * @param params
	 * @return
	 */
	@RequestMapping("/getrechargerecord")
	@ResponseBody
	public Object getRechargeRecord(ConsumeRecordSearchParams params){
		params.setConsumeType(ConsumeTypeEnum.RECHARGE.getValue() + "");
		return consumeRecordService.selectByParams(params);
	}
	
	/**
	 * 跳转会员转账记录页面
	 * @return
	 */
	@RequestMapping("/totransferaccountrecordpage")
	public String toTransferAccountRecordPage(){
		return "/system/platform/financialmanager/transfer-account-record";
	}
	
	/**
	 * 获取会员 转账记录
	 * @param params
	 * @return
	 */
	@RequestMapping("/gettransferaccountrecord")
	@ResponseBody
	public Object getTransferAccountRecord(ConsumeRecordSearchParams params){
		params.setConsumeType(ConsumeTypeEnum.TRANSFER_ACCOUNT.getValue() + "");
		return consumeRecordService.selectByParams(params);
	}
	
	/**
	 * 跳转线路预存比例页面
	 * @return
	 */
	@RequestMapping("/tolinedepositratiopage")
	public String toLineDepositRatioPage(HttpServletRequest request){
		request.setAttribute("depositRatioStatusList", getDepositRatioStatus());
		return "/system/platform/financialmanager/line-deposit-ratio";
	}
	
	// 获取线路预存运费 状态
	private List<Map<String, Object>> getDepositRatioStatus(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		DepositRatioStatusEnum[] depositRatioStatusEnums = DepositRatioStatusEnum.values();
		for (DepositRatioStatusEnum ratioStatusEnum : depositRatioStatusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ratioStatusEnum.getValue());
			map.put("name", ratioStatusEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 查询 线路预存育肥比例设置(货运交易系统)
	 * @param params
	 * @return
	 */
	@RequestMapping("/getdepositratiolist")
	@ResponseBody
	public Object getDepositRatioList(BaseSearchParams params){
		return depositRatioService.selectByParams(params);
	}
	/**
	 * 跳转到会员账号资金页面
	 * @return
	 */
	@RequestMapping("/tomemberaccountfundpage")
	public String toMemberAccountFundPage(){
		return "/system/platform/financialmanager/member-account-fund";
	}
	
	/**
	 * 获取会员账号资金列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getmemberaccountfundlist")
	@ResponseBody
	public Object getMemberAccountFundList(PlatformUserSearchParams params){
		return platformUserService.selectByParams(params);
	}
}
