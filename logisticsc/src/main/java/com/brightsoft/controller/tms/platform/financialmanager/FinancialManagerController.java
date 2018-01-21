package com.brightsoft.controller.tms.platform.financialmanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.ReceivablePayBillSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.model.DepositRatio;
import com.brightsoft.model.InsteadCollectMoney;
import com.brightsoft.model.MoneyDiaryRecord;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.ReceivablePayBillRecord;
import com.brightsoft.model.TakeCashApply;
import com.brightsoft.model.User;
import com.brightsoft.model.lineAdvanceMoneyRecord;
import com.brightsoft.model.receiveMoneyRecord;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.system.platform.TakeCashApplyService;
import com.brightsoft.service.tms.platform.DepositRatioService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.financialmanager.InsteadCollectMoneyService;
import com.brightsoft.service.tms.platform.financialmanager.LineAdvanceMoneyRecordService;
import com.brightsoft.service.tms.platform.financialmanager.MoneyDiaryRecordService;
import com.brightsoft.service.tms.platform.financialmanager.ReceivablePayBillRecordService;
import com.brightsoft.service.tms.platform.financialmanager.ReceivablePayBillService;
import com.brightsoft.service.tms.platform.financialmanager.ReceiveMoneyRecordService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;

/**
 * 财务管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/financial")
public class FinancialManagerController extends BaseController{

	@Autowired
	private ReceiveMoneyRecordService receiveMoneyRecordService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private LineAdvanceMoneyRecordService advanceMoneyRecordService;
	
	@Autowired
	private MoneyDiaryRecordService moneyDiaryRecordService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private InsteadCollectMoneyService insteadCollectMoneyService;
	
	@Autowired
	private ReceivablePayBillService receivablePayBillService;
	
	@Autowired
	private ReceivablePayBillRecordService receivablePayBillRecordService;
	
	@Autowired
	private TakeCashApplyService takeCashApplyService;
	
	@Autowired
	private DepositRatioService depositRatioService;
	
	/**
	 * 跳转到 财务管理-应收款页面
	 * @return
	 */
	@RequestMapping("/toreceivableaccount")
	public String toReceivableAccountPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			OutletsInfo outletsInfo = outletsService.selectById(outletsId);
			request.setAttribute("outletsName", outletsInfo.getName());
		}
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/financialmanager/receivable-account";
	}

	/**
	 * 添加收款记录
	 * @param receiveMoneyRecord
	 * @return
	 */
	@RequestMapping("/addreceivemoneyrecord")
	@ResponseBody
	public String addReceiveMoneyRecord(receiveMoneyRecord receiveMoneyRecord, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		receiveMoneyRecord.setOutletsId(user.getOutletsId());
		receiveMoneyRecord.setOperatePerson(user.getId());
		int rows = receiveMoneyRecordService.insertReceiveMoneyRecord(receiveMoneyRecord);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取收款记录
	 * @param departNumber
	 * @return
	 */
	@RequestMapping("/getreceivemoneyrecordbydepartnumber")
	@ResponseBody
	public Object getReceiveMoneyRecordByDepartNumber(String departNumber){
		return receiveMoneyRecordService.selectByDepartNumber(departNumber);
	}
	
	/**
	 * 跳转到 财务管理-预付款页面
	 * @return
	 */
	@RequestMapping("/toadvancemoneypage")
	public String toAdvanceMoneyPage(HttpServletRequest request){
		DepositRatio depositRatio = depositRatioService.selectCurrentDeposit();
		request.setAttribute("depositRatio", depositRatio);
		return "/tms/platform/financialmanager/advance-money";
	}
	
	/**
	 * 获取线路信息
	 * @return
	 */
	@RequestMapping("/getlineinfos")
	@ResponseBody
	public Object getLineInfos(HttpSession session, BaseSearchParams params){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}
		params.setOutletsId(outletsId);
		return lineInfoService.selectByParams(params);
	}
	
	/**
	 * 添加 线路充值 记录
	 * @param lineAdvanceMoneyRecord
	 * @return
	 */
	@RequestMapping("/addlineadvancemoneyrecord")
	@ResponseBody
	public String addLineAdvanceMoneyRecord(lineAdvanceMoneyRecord lineAdvanceMoneyRecord){
		int rows = advanceMoneyRecordService.insert(lineAdvanceMoneyRecord);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取线路充值记录
	 * @param lineId
	 * @return
	 */
	@RequestMapping("/getlineadvancemoneyrecord")
	@ResponseBody
	public Object getLineAdvanceMoneyRecord(String lineId){
		return advanceMoneyRecordService.selectByLineId(lineId);
	}
	
	/**
	 * 跳转到 财务管理-现金日记页面
	 * @return
	 */
	@RequestMapping("/tomoneydiarypage")
	public String toMoneyDiaryPage(HttpServletRequest request){
		List<PlatformDictionary> dictionaries = dictionaryService.selectDictDataByType(DictionaryType.COST_SUBJECT);
		request.setAttribute("dictionaries", dictionaries);
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			request.setAttribute("outletsInfo", outletsService.selectById(outletsId));
		}
		return "/tms/platform/financialmanager/money-diary";
	}
	
	/**
	 * 获取 现金日记 列表
	 * @param searchParams
	 * @return
	 */
	@RequestMapping("/getmoneydiarylist")
	@ResponseBody
	public Object getMoneyDiaryList(SearchParams searchParams, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}
		searchParams.setOutletsId(user.getOutletsId());
		return moneyDiaryRecordService.selectByParams(searchParams);
	}
	
	/**
	 * 添加现金日记 记录
	 * @param moneyDiaryRecord
	 * @return
	 */
	@RequestMapping("/addmoneydiaryrecord")
	@ResponseBody
	public String addMoneyDiaryRecord(MoneyDiaryRecord moneyDiaryRecord, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			moneyDiaryRecord.setOutletsId(user.getOutletsId());
			moneyDiaryRecord.setOperatePerson(user.getId());
			rows = moneyDiaryRecordService.insert(moneyDiaryRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到网点对账 页面
	 * @return
	 */
	@RequestMapping("/tooutletscheckaccountpage")
	public String toOutletsCheckAccountPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			OutletsInfo outletsInfo = outletsService.selectById(outletsId);
			request.setAttribute("outletsInfo", outletsInfo);
		}
		return "/tms/platform/financialmanager/outlets-check-account";
	}
	
	/**
	 * 跳转到代收货款 页面
	 * @return
	 */
	@RequestMapping("/toinsteadcollectmoneypage")
	public String toInsteadCollectMoneyPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			OutletsInfo outletsInfo = outletsService.selectById(user.getOutletsId());
			request.setAttribute("outletsInfo", outletsInfo);
		}
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/financialmanager/instead-collect-money";
	}
	
	/**
	 * 查询代收货款运单列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/getinsteadcollectmoneywaybilllist")
	@ResponseBody
	public Object getInsteadCollectMoneyWayBillList(WayBillSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}
		params.setTargetOutlets(outletsId);
		params.setIsAgencyFund("1");
		return wayBillOrderService.selectByParams(params);
	}
	
	/**
	 * 添加 代收款 记录
	 * @param insteadCollectMoney
	 * @return
	 */
	@RequestMapping("/addinsteadcollectmoney")
	@ResponseBody
	public String addInsteadCollectMoney(InsteadCollectMoney insteadCollectMoney, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		insteadCollectMoney.setOperatePerson(user.getId());
		int rows = 0;
		try {
			rows = insteadCollectMoneyService.insert(insteadCollectMoney);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取代收货款列表
	 * @param wayBillNumber 运单号
	 * @return
	 */
	@RequestMapping("/getinsteadcollectmoneylist")
	@ResponseBody
	public Object getInsteadCollectMoneyList(String wayBillNumber){
		return insteadCollectMoneyService.selectByWayBillNumber(wayBillNumber);
	}
	
	/**
	 * 跳转到 专线钱包页面
	 * @return
	 */
	@RequestMapping("/tolinewalletpage")
	public String toLineWalletPage(){
		return "/tms/platform/financialmanager/line-wallet";
	}
	
	/**
	 * 跳转到 应付款订单表页面
	 * @return
	 */
	@RequestMapping("/toreceivablepayrecord")
	@RepeatSubmission(needSaveToken = true)
	public String toReceivablePayRecordPage(HttpServletRequest request){
		List<PlatformDictionary> dictionaries = dictionaryService.selectDictDataByType(DictionaryType.COST_SUBJECT);
		request.setAttribute("dictionaries", dictionaries);
		
		return "/tms/platform/financialmanager/receivable-payrecord";
	}
	
	/**
	 * 跳转到 应付款页面
	 * @return
	 */
	@RequestMapping("/toreceivablepay")
	@RepeatSubmission(needSaveToken = true)
	public String toReceivablePayPage(HttpServletRequest request){
		
		List<PlatformDictionary> dictionaries = dictionaryService.selectDictDataByType(DictionaryType.COST_SUBJECT);
		request.setAttribute("dictionaries", dictionaries);
		
		return "/tms/platform/financialmanager/receivable-pay";
	}
	
	/**
	 * 获取 应付款 列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/getreceivablepaybill")
	@ResponseBody
	public Object getReceivablePayBill(HttpSession session, ReceivablePayBillSearchParams params){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId() == null||user.getOutletsId().equals("")){
			List<OutletsInfo> outs =outletsService.selectOutlesNotBind(user.getCompanyId());
			String ids = "";
			for(int i =0;i<outs.size();i++){
				if(i == 0){
					ids += outs.get(i).getId();
				}else{
					ids += ","+outs.get(i).getId();
				}
			}
			if(outs == null || outs.size() == 0){
				params.setOutletsIds("no");
			}else{
				params.setOutletsIds(ids);
			}
		}else{
			params.setOutletsIds(user.getOutletsId()+"");
		}
		return receivablePayBillService.selectByParams(params);
	}
	
	/**
	 * 保存 应收款 账单 收款记录
	 * @param receivablePayBillRecord
	 * @return
	 */
	@RequestMapping("/savereceivablepaybillrecord")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public String saveReceivablePayBillRecrod(ReceivablePayBillRecord receivablePayBillRecord, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		receivablePayBillRecord.setOperatePerson(user.getId());
		int rows = receivablePayBillRecordService.insert(receivablePayBillRecord, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 查询 应收款 账单 收款记录
	 * @param receivablePayBillRecord
	 * @return
	 */
	@RequestMapping("/getreceivablepaybillrecord")
	@ResponseBody
	public Object getReceivablePayBillRecrod(String billId){
		return receivablePayBillRecordService.selectByBillId(billId);
	}
	
	/**
	 * 跳转到订单退款页面
	 * @return
	 */
	@RequestMapping("/toorderrefundpage")
	public String toOrderRefundPage(){
		return "/tms/platform/financialmanager/order-refund";
	}
	
	/**
	 * 跳转到运单统计页面
	 * @return
	 */
	@RequestMapping("/towaybillanalysepage")
	public String toWayBillAnalysePage(){
		return "/tms/platform/financialmanager/way-bill-analyse";
	}
	
	/**
	 * 保存 提现申请
	 * @param takeCashApply
	 * @return
	 */
	@RequestMapping("/savetakecashapply")
	@ResponseBody
	public String saveTakeCashApply(TakeCashApply takeCashApply, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		takeCashApply.setUserId(user.getId());
		int rows = takeCashApplyService.save(takeCashApply);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
}
