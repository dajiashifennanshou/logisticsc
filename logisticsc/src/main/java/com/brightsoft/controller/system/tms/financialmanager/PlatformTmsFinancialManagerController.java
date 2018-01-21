package com.brightsoft.controller.system.tms.financialmanager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.PlatformUserSearchParams;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.model.platformBankAccount;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.platform.platformBankAccountServiceImpl;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;

/**
 * 财务管理Controller (货运交易系统TMS)
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/system/tms/financial")
public class PlatformTmsFinancialManagerController extends BaseController{

	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	@Autowired
	private platformBankAccountServiceImpl platformBankAccount;
	
	/**
	 * 跳转到代收货款页面
	 * @return
	 */
	@RequestMapping("/toinsteadcollectmoneypage")
	public String toInsteadCollectMoneyPage(HttpServletRequest request){
		
		return "/system/tms/financialmanager/instead-collect-money";
	}
	
	/*private List<Map<String, Object>> getTakeCashStatus(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		TakeCashStatusEnum[] cashStatusEnums = TakeCashStatusEnum.values();
		for (TakeCashStatusEnum takeCashStatusEnum : cashStatusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", takeCashStatusEnum.getValue());
			map.put("name", takeCashStatusEnum.getName());
			result.add(map);
		}
		return result;
	}*/
	
	/**
	 * 获取 代收货款列表(货运交易系统TMS)
	 * @param params
	 * @return
	 */
	@RequestMapping("/getinsteadcollectmoneylist")
	@ResponseBody
	public Object getInsteadCollectMoneyList(WayBillSearchParams params){
		return wayBillOrderService.selectByParams(params);
	}
	
	/**
	 * 跳转专线钱包页面
	 * @return
	 */
	@RequestMapping("/tolinewalletpage")
	public String toLineWalletPage(){
		return "/system/tms/financialmanager/line-wallet";
	}
	
	/**
	 * 获取 专线/物流商用户
	 * @param params
	 * @return
	 */
	@RequestMapping("/getplatformlineuser")
	@ResponseBody
	public Object getPlatformLineUser(PlatformUserSearchParams params){
		/*List<String> userTypes = new ArrayList<String>();
		userTypes.add(Const.PLATFORM_USER_TYPE_LINE_COMPANY + "");
		userTypes.add(Const.PLATFORM_USER_TYPE_LINE_PROVIDER + "");*/
		return platformUserService.selectBankAccountUser(params);
	}
	
	/**
	 * 查询 余额
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/findblance")
	@ResponseBody
	public Object findBlance(Long id, HttpServletRequest request){
		Result srt = new Result();
		//查询用户子账户
		platformBankAccount account =  platformBankAccount.selectBankAccountUserId(id);
		if(null == account){
			srt.setResult(false);
			return srt ;
		}else{
			request.setAttribute("ledgerno",account.getLedgerno());
			String data = ZGTDataAttribute.buildQueryBalanceData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYBALANCEAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYBALANCEAPI_RESPONSE_HMAC_ORDER);
			Map<String, String> responseMap =  (Map<String, String>) result.getData();
			//第三步 判断请求是否成功，
			if(!responseMap.get("code").equals("1")) {
				srt.setResult(false);
				return srt ;
			}else{
				srt.setResult(true);
				srt.setData(responseMap.get("ledgerbalance"));
				return srt ;
			}
		}
	}
}
