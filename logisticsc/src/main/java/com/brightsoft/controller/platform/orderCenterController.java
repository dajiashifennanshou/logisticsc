package com.brightsoft.controller.platform;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.BankOrderType;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformCollectionLine;
import com.brightsoft.model.PlatformCollectionPayment;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.PlatformMineBillInfo;
import com.brightsoft.model.PlatformMineOrder;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderAdditionalServer;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.model.PlatformOrderCargo;
import com.brightsoft.model.PlatformOrderCargoTemp;
import com.brightsoft.model.PlatformOrderComlainHandle;
import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.PlatformOrderComplainInfo;
import com.brightsoft.model.PlatformOrderEvaluation;
import com.brightsoft.model.PlatformOrderEvaluationInfo;
import com.brightsoft.model.PlatformOrderMineComplain;
import com.brightsoft.model.PlatformOrderMineEvaluation;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCommonCargo;
import com.brightsoft.model.PlatformUserCommonContact;
import com.brightsoft.model.PlatformUserCommonDriver;
import com.brightsoft.model.PlatformUserLineMoney;
import com.brightsoft.model.XzqhInfo;
import com.brightsoft.model.platformBankPayment;
import com.brightsoft.model.platformBankRefund;
import com.brightsoft.model.platformMineOrderTransactionFlow;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.PlatformInsuranceServiceImpl;
import com.brightsoft.service.platform.PlatformOrderAdditionalServerService;
import com.brightsoft.service.platform.PlatformOrderAgencyFundService;
import com.brightsoft.service.platform.PlatformOrderBackServiceImpl;
import com.brightsoft.service.platform.PlatformOrderBillService;
import com.brightsoft.service.platform.PlatformOrderCargoService;
import com.brightsoft.service.platform.PlatformOrderCargoTempService;
import com.brightsoft.service.platform.PlatformOrderComplainHandleServiceImpl;
import com.brightsoft.service.platform.PlatformOrderComplainServiceImpl;
import com.brightsoft.service.platform.PlatformOrderEvaluationReplyServiceImpl;
import com.brightsoft.service.platform.PlatformOrderEvaluationServiceImpl;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.platform.PlatformStoreRecordService;
import com.brightsoft.service.platform.PlatformUserCommonCargoService;
import com.brightsoft.service.platform.PlatformUserCommonContactService;
import com.brightsoft.service.platform.PlatformUserCommonDriverService;
import com.brightsoft.service.platform.PlatformUserLineMoneyService;
import com.brightsoft.service.platform.XzqhServiceImpl;
import com.brightsoft.service.platform.platformBankPaymentServiceImpl;
import com.brightsoft.service.platform.platformBankRefundServiceImpl;
import com.brightsoft.service.platform.platformOrderTransactionFlowServiceImpl;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.ExportExcel;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;

/**
 * 订单中心
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/orderCenter")
public class orderCenterController extends BaseController{
	
	@Autowired
	private PlatformOrderBackServiceImpl platformOrderBack;
	
	@Autowired
	private PlatformOrderBillService billService;
	
	@Autowired
	private PlatformUserCommonContactService commonContactService; 
	
	@Autowired
	private PlatformUserCommonDriverService commonDriverService;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired 
	private DictionaryService dictionaryService;
	
	@Autowired
	private PlatformUserCommonCargoService commonCargoService;
	
	@Autowired
	private PlatformStoreRecordService storeRecordService;
	
	@Autowired
	private PlatformOrderAgencyFundService orderAgencyFundService;
	
	@Autowired
	private PlatformOrderEvaluationServiceImpl orderEvaluationService;
	
	@Autowired
	private PlatformOrderEvaluationReplyServiceImpl orderEvaluationReplyService;
	
	@Autowired
	private PlatformOrderComplainServiceImpl orderComplainService;
	
	@Autowired
	private PlatformOrderComplainHandleServiceImpl orderComplainHandleService;
	
	@Autowired
	private PlatformOrderServiceImpl orderServiceImpl;
	
	@Autowired
	private PlatformOrderCargoService platformOrderCargoService;
	
	@Autowired
	private PlatformOrderCargoTempService platformOrderCargoTempService;
	
	@Autowired
	private PlatformOrderAdditionalServerService platformOrderAdditionalServerService;
	
	@Autowired
	private PlatformUserLineMoneyService userLineMoneyService;
	
	@Autowired
	private platformOrderTransactionFlowServiceImpl orderTransactionFlowServiceImpl;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private platformBankPaymentServiceImpl platformBankPayment;
	
	@Autowired
	private PlatformInsuranceServiceImpl platformInsuranceServiceImpl;
	
	@Autowired
	private platformBankRefundServiceImpl platformBankRefundServiceImpl;
	
	@Autowired
	private PlatformOrderBillService platformOrderBillService;
	/**
	 * 跳转到我的订单列表页面
	 * @return
	 */
	@RequestMapping("/toorderlistpage")
	public String toOrderListPage(String chooseid,ModelMap model){
		model.addAttribute("chooseid",chooseid);
		return "/platform/orderCenter/mineOrder/order_list";
	}
	/**
	 * 跳转到我的账单
	 * @return
	 */
	@RequestMapping("/toMybill")
	public String toMybill(){
		return "/platform/orderCenter/mineBill/bill_list";
	}
	/**
	 * 跳转到常用联系人初始 页面
	 * @return
	 */
	@RequestMapping("/toCommonContact")
	public String toCommonContact(){
		return "/platform/orderCenter/commonContact/common_contact_list";
	}
	/**
	 * 跳转增加常用联系人
	 * @return
	 */
	@RequestMapping("/jumprecommonContact")
	public ModelAndView jumprecommonContact(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/orderCenter/inserCommonContact");
		return mv;
	}
	/**
	 * 跳转到我的常发货物页面
	 * @return
	 */
	@RequestMapping("/toorderRegularCargo")
	public String toorderRegularCargo(){
		return "/platform/orderCenter/regularCargo/regularCargo_list";
	}
	/**
	 * 跳转到我的收藏
	 * @return
	 */
	@RequestMapping("/toorderCollectionLine")
	public String toorderCollectionLine(){
		return "/platform/orderCenter/mineCollection/mine_collection_list";
	}
	/**
	 * 跳转到资金流水
	 * @return
	 */
	@RequestMapping("/toorderCapitalFlow")
	public String toorderCapitalFlow(){
		return "/platform/orderCenter/capitalFlow/capital_flow";
	}
	/**
	 * 跳转到代收货款
	 * @return
	 */
	@RequestMapping("/toorderCollectionPayment")
	public String toorderCollectionPayment(){
		return "/platform/orderCenter/collectionPayment/collection_payment";
	}
	/**
	 * 跳转到我的评价
	 * @return
	 */
	@RequestMapping("/toorderMineEvaluation")
	public String toorderMineEvaluation(){
		return "/platform/orderCenter/mineEvaluate/evaluate_list";
	}
	/**
	 * 跳转到我的投诉
	 * @return
	 */
	@RequestMapping("/toorderMineComplain")
	public String toorderMineComplain(){
		return "/platform/orderCenter/mineComplaint/complain_list";
	}
	/**
	 * 获取车辆类型
	 * @return
	 */
	@RequestMapping("/getDictionary")
	@ResponseBody
	public Result getDictionary(){
		Result srt = new Result();
		List<PlatformDictionary> dictionaries = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
		if(null == dictionaries || dictionaries.size() <= 0){
			srt.setMsg("获取车辆类型失败!");
			srt.setResult(false);
		}else{
			srt.setMsg("获取车辆类型成功!");
			srt.setData(dictionaries);
			srt.setResult(true);
		}
		return srt;
	}
	/**
	 * 获取订单状态   -- 占时无用
	 * @return
	 */
//	@RequestMapping("/getOrderState")
//	@ResponseBody
//	public Result getOrderState(){
//		Result srt = new Result();
//		List<PlatformDictionary> dictionaries = dictionaryService.selectDictDataByType(DictionaryType.ORDER_STATE);
//		if(null == dictionaries || dictionaries.size() <= 0){
//			srt.setMsg("获取订单状态失败");
//			srt.setResult(false);
//		}else{
//			srt.setMsg("获取订单状态成功");
//			srt.setData(dictionaries);
//			srt.setResult(true);
//		}
//		return srt;
//	}
	/**
	 * 获取城市 市县区
	 * @return
	 */
	@RequestMapping("/getXzqhInfo")
	@ResponseBody
	public Result getXzqhInfo(){
		Result srt = new Result();
		List<XzqhInfo> xzqhInfos = xzqhServiceImpl.selectByPid(SystemConstant.PROVINCE_PID);
		if(null == xzqhInfos || xzqhInfos.size() <= 0){
			srt.setMsg("获取城市失败!");
			srt.setResult(false);
		}else{
			srt.setMsg("获取城市成功!");
			srt.setData(xzqhInfos);
			srt.setResult(true);
		}
		return srt;
	}
	
	/**
	 * 账单信息显示
	 * @param request
	 * @param response
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getListorderBill")
	@ResponseBody
	public Result getListorderBill(PlatformOrderBill orderBill,Page<?> page,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		Result srt = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		orderBill.setUserId(user.getId());
		page = billService.selectBySelectedCondition(orderBill, page);
		srt.setData(page);
		srt.setResult(true);
		srt.setMsg("获取我的账单成功!");
		return srt;
	}
	/**
	 * 获取我的常用司机信息
	 * @param commonDriver
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getListCommonDriver")
	@ResponseBody
	public Result getListCommonDriver(PlatformUserCommonDriver commonDriver,Page<?> page,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		Result srt = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		commonDriver.setUserId(user.getId());
		page = commonDriverService.selectBySelectedCondition(commonDriver, page);
		srt.setData(page);
		srt.setResult(true);
		srt.setMsg("获取常用司机成功!");
		return srt;
	}
	/**
	 * 获取常用联系人
	 * @param commonContact
	 * @param page
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/getListCommonContact")
	@ResponseBody
	public Result getListCommonContact(PlatformUserCommonContact commonContact,Page<?> page,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		Result srt = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		commonContact.setUserId(user.getId());
		page = commonContactService.selectBySelectedCondition(commonContact, page);
		srt.setData(page);
		srt.setResult(true);
		srt.setMsg("获取联系人成功!");
		return srt;
	}
	/**
	 * 增加常用发货人
	 * @return
	 */
	@RequestMapping("/doContacts")
	@ResponseBody
	public Result doContacts(PlatformUserCommonContact commonContact,HttpSession session){
		Result srt = new Result();		
		if(null == commonContact.getContactsType()
				|| StringUtils.isBlank(commonContact.getAddress())
				|| StringUtils.isBlank(commonContact.getCity())
				||StringUtils.isBlank(commonContact.getContactsName())
				||StringUtils.isBlank(commonContact.getCounty())
				||StringUtils.isBlank(commonContact.getPhoneNumber())
				||StringUtils.isBlank(commonContact.getProvince())
				||StringUtils.isBlank(commonContact.getTelephone())){
			srt.setResult(false); 
			srt.setMsg("必填项不能为空!");
		}else{
			PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
			commonContact.setUserId(user.getId());
			if(commonContactService.inserPlatformUserCommonContact(commonContact)){
				if(commonContact.getContactsType() == Const.PLATFORMUSER_COMMONCONTACT_CONTACTSTYPE_0){
					srt.setMsg("增加发货人成功!");
				}else if(commonContact.getContactsType() == Const.PLATFORMUSER_COMMONCONTACT_CONTACTSTYPE_1){
					srt.setMsg("增加收货人成功!");
				}
				srt.setResult(true);
			}else{
				if(commonContact.getContactsType() == Const.PLATFORMUSER_COMMONCONTACT_CONTACTSTYPE_0){
					srt.setMsg("增加发货人失败!");
				}else if(commonContact.getContactsType() == Const.PLATFORMUSER_COMMONCONTACT_CONTACTSTYPE_1){
					srt.setMsg("增加收货人失败!");
				}
				srt.setResult(false);
				
			}
		}
		return srt;
	}
	/**
	 * 当前用户是否已存在联系人手机号
	 * @param commonContact
	 * @param session
	 * @return
	 */
	@RequestMapping("/getPlatCommonContactId")
	@ResponseBody
	public Result getPlatCommonContactId(PlatformUserCommonContact commonContact,HttpSession session){
		Result srt = new Result();	
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		commonContact.setUserId(user.getId());
		if(commonContactService.selectPlatCommonContactId(commonContact)){
			srt.setResult(true);
		}else{
			srt.setResult(false);
		}
		return srt;
	}
	/**
	 * 逻辑删除我的常用联系人
	 * @param commonContact
	 * @return
	 */
	@RequestMapping("/doUpdateContacts")
	@ResponseBody
	public Result doUpdateContacts(String commonContact){
		Result srt = new Result();
		List<Long> list = JSONArray.parseArray(commonContact, Long.class);
		if(commonContactService.updateCommonContact(list)){
			srt.setMsg("删除我的常用联系人成功!");
			srt.setResult(true);
		}else{
			srt.setMsg("删除我的常用联系人失败!");
			srt.setResult(false);
		}
		return srt;
	}
	/**
	 * 删除我的常用司机
	 * @param commonDriver
	 * @return
	 */
	@RequestMapping("/deleteCommonDriver")
	@ResponseBody
	public Result deleteCommonDriver(String commonDriver){
		Result srt = new Result();
		List<Long> list = JSONArray.parseArray(commonDriver, Long.class);
		if(commonDriverService.deleteCommonDriver(list)){
			srt.setMsg("删除我的常用司机成功!");
			srt.setResult(true);
		}else{
			srt.setMsg("删除我的常用司机失败!");
			srt.setResult(false);
		}
		return srt;
	}
	/**
	 * 增加常用司机
	 * @return
	 */
	@RequestMapping("/doCommonDriver")
	@ResponseBody
	public Result doCommonDriver(PlatformUserCommonDriver commonDriver){
		Result srt = new Result();
		if(StringUtils.isBlank(commonDriver.getDriverName())
				||StringUtils.isBlank(commonDriver.getLicenseNumber())
				||StringUtils.isBlank(commonDriver.getPhoneNumber())
				||StringUtils.isBlank(commonDriver.getVehicleType())
				||commonDriver.getUserId() <=0 
				||null == commonDriver.getUserId()){
			srt.setResult(false);
			srt.setMsg("必填项不能为空!");
		}else{
			if(commonDriverService.insertPlatformUserCommonDriver(commonDriver)){
				srt.setResult(true);
				srt.setMsg("增加司机成功!");
			}else{
				srt.setMsg("增加司机失败!");
				srt.setResult(false);
			}
		}
		return srt;
	}
	@RequestMapping("/getCommonDriverId")
	@ResponseBody
	public Result getCommonDriverId(PlatformUserCommonDriver commonDriver){
		Result srt = new Result();
		if(commonDriverService.selectPlatformCommonDriverId(commonDriver)){
			srt.setResult(true);
		}else{
			srt.setResult(false);
		}
		return srt;
	}
	@RequestMapping("/selectPlatformCommonDriverPhone")
	@ResponseBody
	public Result selectPlatformCommonDriverPhone(PlatformUserCommonDriver commonDriver){
		Result srt = new Result();
		if(commonDriverService.selectPlatformCommonDriverPhone(commonDriver)){
			srt.setResult(true);
		}else{
			srt.setResult(false);
		}
		return srt;
	}
	/**
	 * 我的常发货物信息
	 * @param cargo
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/getCommonCargo")
	@ResponseBody
	public Result getCommonCargo(PlatformUserCommonCargo cargo,HttpSession session,Page<?> page){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <=0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			cargo.setUserId(user.getId());
			page= commonCargoService.selectBySelectedCondition(cargo, page);
			ret.setResult(true);
			ret.setMsg("获取我的常发货物信息成功!");
			ret.setData(page);
		}
		return ret;
	}
	/**
	 * 增加我的常发货物信息
	 * @param cargo
	 * @param session
	 * @return
	 */
	@RequestMapping("doCommonCargo")
	@ResponseBody
	public Result doCommonCargo(PlatformUserCommonCargo cargo,HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <=0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			cargo.setUserId(user.getId());
			cargo.setState(Const.PLATFORMUSER_USER_CONSUME_COMMON_CARGO_STATE_1);
			if(commonCargoService.insertCommonCargo(cargo)){
				ret.setResult(true);
				ret.setMsg("增加我的常发货物信息成功!");
			}else{
				ret.setResult(false);
				ret.setMsg("增加我的常发货物信息失败!");
			}
		}
		return ret;
	}
	@RequestMapping("/getCommonCargoName")
	@ResponseBody
	public Result getCommonCargoName(PlatformUserCommonCargo cargo,HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		cargo.setUserId(user.getId());
		if(commonCargoService.selectCommonCargoName(cargo)){
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 逻辑删除我的常发货物
	 * @param commonCargoId
	 * @return
	 */
	@RequestMapping("doUpdateCommonCargo")
	@ResponseBody
	public Result doUpdateCommonCargo(String commonCargoIds){
		Result ret = new Result();
		List<Long> list = JSONArray.parseArray(commonCargoIds, Long.class);
		if(commonCargoService.updateCommonCargo(list)){
			ret.setMsg("删除我的常发货物成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("删除我的常发货物失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 逻辑删除我的收藏
	 * @return
	 */
	@RequestMapping("doUpdateCollectionLine")
	@ResponseBody
	public Result doUpdateCollectionLine(String collectionLineId){
		Result ret = new Result();
		List<Long> list = JSONArray.parseArray(collectionLineId, Long.class);
		if(storeRecordService.updateCollectionLine(list)){
			ret.setMsg("删除我的收藏成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("删除我的收藏失败!");
			ret.setResult(false);
		}
		return ret;
	}
	/**
	 * 我的收藏
	 * @param collectionLine
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("getCollectionLine")
	@ResponseBody
	public Result getCollectionLine(PlatformCollectionLine collectionLine,HttpSession session,Page<?> page){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <=0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			collectionLine.setUserId(user.getId());
			page= storeRecordService.selectBySelectedCondition(collectionLine, page);
			ret.setResult(true);
			ret.setMsg("获取我的收藏线路!");
			ret.setData(page);
		}
		return ret;
	}
	/**
	 * 代收货款
	 * @param collectionLine
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("getCollectionPayment")
	@ResponseBody
	public Result getCollectionPayment(PlatformCollectionPayment collectionLine,HttpSession session,Page<?> page){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <=0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			collectionLine.setUserId(user.getId());
			page= orderAgencyFundService.selectBySelectedCondition(collectionLine, page);
			ret.setResult(true);
			ret.setMsg("获取代收货款!");
			ret.setData(page);
		}
		return ret;
	}
	/**
	 * 查询评论信息
	 * @param page
	 * @param session
	 * @param PlatformOrderMineEvaluation
	 * @return
	 */
	@RequestMapping("/getEvaluation")
	@ResponseBody
	public Result getEvaluation(Page<?> page,HttpSession session,
			PlatformOrderMineEvaluation platformOrderEvaluation){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			platformOrderEvaluation.setUserId(user.getId());
			page = orderEvaluationService.selectByConditionPlatfrom(platformOrderEvaluation, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 查询评论信息
	 * @param page
	 * @param session
	 * @param PlatformOrderMineComplain
	 * @return
	 */
	@RequestMapping("/getComplain")
	@ResponseBody
	public Result getComplain(Page<?> page,HttpSession session,
			PlatformOrderMineComplain orderMineComplain){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			orderMineComplain.setUserId(user.getId());
			page = orderComplainService.selectByConditionPlatfrom(orderMineComplain, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 查询订单信息
	 * @param page
	 * @param session
	 * @param PlatformMineOrder
	 * @return
	 */
	@RequestMapping("/getMineOrder")
	@ResponseBody
	public Result getMineOrder(Page<?> page,HttpSession session,
			PlatformMineOrder mineOrder){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			mineOrder.setUserId(user.getId());
			page = orderServiceImpl.selectBySelectedOrder(mineOrder, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 增加订单评价
	 * @param evaluation
	 * @param session
	 * @return
	 */
	@RequestMapping("doEvaluation")
	@ResponseBody
	public Result doEvaluation(PlatformOrderEvaluation evaluation,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		evaluation.setUserId(user.getId());
		if(orderEvaluationService.inserEvaluation(evaluation)){
			result.setMsg("增加评论成功!");
			result.setResult(true);
		}else{
			result.setMsg("增加评论失败!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 增加投诉
	 * @param evaluation
	 * @param session
	 * @return
	 */
	@RequestMapping("doComplain")
	@ResponseBody
	public Result doComplain(PlatformOrderComplain complain,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		complain.setUserId(user.getId());
		if(orderComplainService.insertComplain(complain)){
			result.setMsg("投诉成功!");
			result.setResult(true);
		}else{
			result.setMsg("投诉失败!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 查看账单详情 
	 * @param orderNumber
	 * @param session
	 * @return
	 */
	@RequestMapping("doMineBillInfo")
	@ResponseBody
	public Result doMineBillInfo(String orderNumber,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		PlatformMineBillInfo billInfo = billService.selectMineBillInfo(user.getId(), orderNumber);
		if(null !=billInfo){
			result.setMsg("查询成功!");
			result.setData(billInfo);
			result.setResult(true);
		}else{
			result.setMsg("查询失败!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 导出我的 
	 * @param request
	 * @param response
	 * @param orderNumber
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportMineOrder")
	public String exportMineOrder(HttpServletRequest request,HttpServletResponse response,
			PlatformMineOrder mineOrder,HttpSession session) throws IOException{
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		mineOrder.setUserId(user.getId());
		String fileName = "我的订单";
		String keys[] ={"orderNumber",
				"fahuoxinxi","consignorName","consignorAddress","consignorPhoneNumber","consignorTelephone",
				"chengpeixinxi","driverName","driverPhone","vehicleNumber","vehicleType",
				"shouhuoxinxi","consigneeName","consigneeAddress","consigneePhoneNumber",
				"consigneeTelephone",
				"huowuxinxi","name","brand","model","number","setNumber",
				"singleWeight","singleVolume","packageTypeName",
				"peisongfangshi","sendCargoType","deliver","receiveCargoType",
				"zengzhifuwu","isReceipt","isImExStore","isLoadCargo","loadCargoFloor",
				"loadCargoIsEleVator","isCollectionDelivery","collectionDeliveryMoney",
				"zonghexinxifuwu","isUnloadCargo","unloadCargoFloor","unloadCargoIsEleVator",
				"huowubaoxian","isInsurance","insuranceMoney",
				"fapiaoxinxi","receiptType","receiptTitle","receiptCompanyName",
				"zhifufangshi","payType"
				};
		String[] columnNames = new String[]
				{"订单号","发货信息","发货人","发货地址","手机号","固定电话",
				"司机信息","姓名","手机号","车牌号","车型",
				"收货信息","收货人","收货地址","手机号","固定电话",
				"货物信息","货物名称","货物品牌","货物型号","件数","套数","每件重量(t)","每件体积(m³)","包装信息",
				"配送方式","发货方式","自送网点-网点地址/上门取货-预约时间","收货方式",
				"增值服务","回单","进出仓","装货","装货楼层","装货电梯",
				"代收货款","贷款金额","综合信息服务","卸货","卸货楼层","卸货电梯",
				"货物保险","专线投保","投保金额",
				"发票信息","发票类型","个人-单位","单位名称",
				"支付方式","支付类型"};
		 List<Map<String, Object>> listmap = orderServiceImpl.exportMineOrder(mineOrder);
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	            ExportExcel.createWorkBook(listmap,keys,columnNames).write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        //设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	        }catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
		return null;
	}
	/**
	 * 导出账单
	 * @return
	 */
	@RequestMapping("/exportMineBill")
	public String exportMineBill(HttpServletRequest request,HttpServletResponse response,
			PlatformOrderBill orderBill,HttpSession session)  throws IOException{
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		orderBill.setUserId(user.getId());
		String fileName = "我的账单";
		String keys[] ={"orderNumber","wayBillNumber","origin","destination","consignorName",
						"consignorPhoneNumber","consigneeName","consigneePhoneNumber","freight",
						"takeCargoCost","sendCargoCost","loadCargoCost","unloadCargoCost",
						"insurance","otherCost","totalCost","prepaidCost","unpaid","payDate",
						"loginName"};
		String[] columnNames = new String[]{"订单号","账单号","起始地","到达地","发货人",
											"发货人手机号","收货人","收货人手机号","运费",
											"提货费","送货费","装货费","卸货费",
											"保险金额","其他费用","总费用","已付费用","未付金额","付款日期",
											"用户账号"};
        List<Map<String, Object>> listmap = billService.exportMineBillInfo(orderBill);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExportExcel.createWorkBook(listmap,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        //设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        }catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
		return null;
	}
	
	/**
	 * 查看评价回复
	 * @param id
	 * @return
	 */
	@RequestMapping("doGetEvaluationReply")
	@ResponseBody
	public Result doGetEvaluationReply(Long id){
		Result result = new Result();
		PlatformOrderEvaluationInfo evaluationInfo =orderEvaluationReplyService.selectEvaluationReply(id);
		if(null !=evaluationInfo){
			result.setMsg("查询评价回复成功!");
			result.setData(evaluationInfo);
			result.setResult(true);
		}else{
			result.setMsg("查询评价回复失败!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 查看投诉回复信息
	 * @param complaintId
	 * @return
	 */
	@RequestMapping("doGetComplainHandle")
	@ResponseBody
	public Result doGetComplainHandle(Long complaintId){
		Result result = new Result();
		PlatformOrderComplainInfo  complainInfo = orderComplainHandleService.selectComlainHandle(complaintId);
		if(null !=complainInfo){
			result.setMsg("查询投诉回复成功!");
			result.setData(complainInfo);
			result.setResult(true);
		}else{
			result.setMsg("查询评投诉回复失败!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 修改投诉回复的满意度
	 * @param comlainHandle
	 * @return
	 */
	@RequestMapping("doUpdateComplainHandle")
	@ResponseBody
	public Result doUpdateComplainHandle(PlatformOrderComlainHandle comlainHandle){
		Result result = new Result();
		if(orderComplainHandleService.updateComlainHandle(comlainHandle)){
			result.setMsg("投诉回复成功!");
			result.setResult(true);
		}else{
			result.setMsg("投诉回复失败!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 总数 草稿
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderisDraft")
	@ResponseBody
	public Result getOrderisDraft(HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		int number = orderServiceImpl.selectOrderisDraft(user.getId());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 总数 订单状态 议价中
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderOrderStateWait")
	@ResponseBody
	public Result getOrderOrderStateWait(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result result = new Result();
		int number = orderServiceImpl.selectOrderState(user.getId(), OrderStatusEnum.CONFIRM_CARGO_INFO.getValue());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 已取消
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderOrderStateCancel")
	@ResponseBody
	public Result getOrderOrderStateCancel(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result result = new Result();
		int number = orderServiceImpl.selectOrderState(user.getId(), OrderStatusEnum.CANCEL.getValue());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 已作废
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderOrderStateVoid")
	@ResponseBody
	public Result getOrderOrderStateVoid(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result result = new Result();
		int number = orderServiceImpl.selectOrderState(user.getId(), OrderStatusEnum.DISCARDED.getValue());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 总数 订单状态  已签收
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderOrderStateAlerady")
	@ResponseBody
	public Result getOrderOrderStateAlerady(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result result = new Result();
		int number = orderServiceImpl.selectOrderState(user.getId(), OrderStatusEnum.RECEIVED.getValue());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 总数 订单状态 已拒绝
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderOrderStateRefused")
	@ResponseBody
	public Result getOrderOrderStateRefused(HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result result = new Result();
		int number = orderServiceImpl.selectOrderState(user.getId(), OrderStatusEnum.REFUSED.getValue());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 总数 确认价格
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderFinalPrice")
	@ResponseBody
	public Result getOrderFinalPrice(HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		int number = orderServiceImpl.selectOrderFinalPrice(user.getId());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 总数 是否投保
	 * @param session
	 * @return
	 */
	@RequestMapping("getOrderIsInsurance")
	@ResponseBody
	public Result getOrderIsInsurance(HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		int number = orderServiceImpl.selectOrderIsInsurance(user.getId());
		result.setData(number);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	/**
	 * 获取当前公司 地区所有网点信息
	 * @param companyId
	 * @param name
	 * @return
	 */
	@RequestMapping("getOultesInfo")
	@ResponseBody
	public Result getOultesInfo(Long companyId,String name){
		Result result = new Result();
		List<OutletsInfo> outletsInfos = orderServiceImpl.selectOutle(companyId, name);
		result.setData(outletsInfos);
		result.setMsg("获取成功!");
		result.setResult(true);
		return result;
	}
	
	/**
	 * 跳转到 订单详情页面
	 * @return
	 */
	@RequestMapping("/toorderdetailpage")
	public String toOrderDetailPage(HttpServletRequest request){
		String orderNumber = request.getParameter("orderNumber");
		if(StringUtils.isNotEmpty(orderNumber)){
			PlatformOrder platformOrder = orderServiceImpl.selectByOrderNumber(orderNumber);
			List<PlatformOrderCargo> platformOrderCargos = platformOrderCargoService.selectByOrderNumber(orderNumber);
			List<PlatformOrderCargoTemp> platformOrderCargoTemps = platformOrderCargoTempService.selectByOrderNumber(orderNumber);
			PlatformOrderAdditionalServer platformOrderAdditionalServer = platformOrderAdditionalServerService.selectByOrderNumber(orderNumber);
			PlatformOrderBill platformOrderBill = platformOrderBillService.selectByOrderNumber(orderNumber);
			request.setAttribute("platformOrder", platformOrder);
			request.setAttribute("platformOrderCargos", platformOrderCargos);
			request.setAttribute("platformOrderCargoTemps", platformOrderCargoTemps);
			request.setAttribute("platformOrderAdditionalServer", platformOrderAdditionalServer);
			request.setAttribute("platformOrderBill", platformOrderBill);
			
			OutletsInfo startOutlets = outletsService.selectById(lineInfoService.selectLineInfoById(platformOrder.getTmsLineId()).getStartOutlets());
			request.setAttribute("startOutlets", startOutlets);
		}
		return "/platform/orderCenter/mineOrder/order-detail";
	}
	/**
	 * 线路充值
	 * @param lineMoney
	 * @param session
	 * @return
	 */
	@RequestMapping("/doUserLineMoney")
	@ResponseBody
	public Result doUserLineMoney(PlatformUserLineMoney lineMoney,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		user = userLineMoneyService.insertUserLineMoney(lineMoney, user);
		if(null != user){
			session.setAttribute(SystemConstant.USER_SESSION, user);
			result.setMsg("充值成功!");
			result.setResult(true);
		}else{
			result.setMsg("充值失败!");
			result.setResult(false);
		}
		return result;
	}
	
	/**
	 * 验证查看当前用户是否存在线路充值
	 * @param lineId
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserLineId")
	@ResponseBody
	public Result getUserLineId(Long lineId,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		PlatformUserLineMoney lineMoney = userLineMoneyService.selectLineId(lineId, user.getId());
		if(null != lineMoney){
			result.setData(lineMoney);
			result.setMsg("查询线路是否存在充值记录成功!");
			result.setResult(true);
		}else{
			result.setMsg("查询线路是否存在充值记录错误!");
			result.setResult(false);
		}
		return result;
	}
	/**
	 * 资金流水
	 * @param page
	 * @param orderTransactionFlow
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMineTransactionFlow")
	@ResponseBody
	public Result getMineTransactionFlow(Page<?> page,
			platformMineOrderTransactionFlow orderTransactionFlow,HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			orderTransactionFlow.setChargeUser(user.getLoginName());
			page = orderTransactionFlowServiceImpl.selectCompanCondition(orderTransactionFlow, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
	/**
	 * 申请退款
	 * @param back
	 * @param session
	 * @return
	 */
	@RequestMapping("/doBack")
	@ResponseBody
	public Result doBack(String orderNumber,HttpSession session,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Result srt = new Result();
		PlatformMineOrder order = orderServiceImpl.selectBankOrder(orderNumber);
		//支付预约费
		if(order.getIsPayment() == 0 || order.getBillPayment() == 0){
			//查询消费记录
			platformBankPayment bankPayment = null;
			if(Integer.parseInt(order.getState()) < OrderStatusEnum.TAkING_CARGO.getValue()){
				bankPayment = platformBankPayment.selectBankPaymentByOrderNumbe(order.getOrderNumber(),Const.PLATFORM_BANK_ORDER_TYPE_1);
			}else if(Integer.parseInt(order.getState()) == OrderStatusEnum.CONFIRM_CARGO_INFO.getValue()){
				bankPayment = platformBankPayment.selectBankPaymentByOrderNumbe(order.getOrderNumber(),Const.PLATFORM_BANK_ORDER_TYPE_2);
			}
			if(null ==bankPayment){
				srt.setMsg("取消订单失败");
				srt.setResult(false);
			}else{
				StringBuffer sb = new StringBuffer();
				sb.append("RD");
				sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
				platformBankRefund bankRefund = new platformBankRefund();
				bankRefund.setCustomernumber("10013368774");
				bankRefund.setRequestid(sb.toString());
				bankRefund.setOrderrequestid(bankPayment.getRequestid());
				bankRefund.setAmount(bankPayment.getAmount());
				bankRefund.setOrderNumber(order.getOrderNumber());
				bankRefund.setTime(new Date());
				bankRefund.setState(Const.PLATFORM_ORDER_BANK_REFUND_0);
				bankRefund.setUsername(user.getLoginName());
				bankRefund.setOrderType(bankPayment.getOrderType());
				bankRefund.setRefundType(Const.PLATFORM_ORDER_BANK_REFUND_REFUND_TYPE_0);
				request.setAttribute("requestid",sb.toString());
				request.setAttribute("orderrequestid",bankRefund.getOrderrequestid());
				request.setAttribute("amount",bankRefund.getAmount());
				request.setAttribute("confirm","1");
				String data = ZGTDataAttribute.buildRefundData(request);
				//第二步 发起请求
				String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REFUNDAPI_NAME);
				Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.REFUNDAPI_RESPONSE_HMAC_ORDER);
				//第七步 进行业务处理
				Map<String, String> responseMap = (Map<String, String>) result.getData();
				if(!responseMap.get("code").equals("1")) {
					srt.setMsg("银行取消订单失败，占时无法取消订单！");
					srt.setResult(false);
					return srt ;
				}else{
					if(platformBankRefundServiceImpl.insertRefund(bankRefund)){
						if(platformOrderBack.doBack(order.getOrderNumber(),user)){
							srt.setMsg("取消订单成功");
							srt.setResult(true);
						}else{
							srt.setMsg("取消订单失败");
							srt.setResult(false);
						}
					}else{
						srt.setMsg("退款失败");
						srt.setResult(false);
					}
				}
			}
		}else{
			if(platformOrderBack.doBack(order.getOrderNumber(),user)){
				srt.setMsg("取消订单成功");
				srt.setResult(true);
			}else{
				srt.setMsg("取消订单失败");
				srt.setResult(false);
			}
		}
		return srt;	
	}
	/**
	 * 订单回调接口
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/payCallBack")
	protected void payCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out	= response.getWriter();
		//第一步 获取回调密文data
		String data	= request.getParameter("data");
		//因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
				if(data == null) {
					return;
				}
				//第二步 解密密文data，获取明文参数
				Map<String, String> dataMap	= ZGTUtils.decryptData(data);
				
				//第三步 hmac签名验证
				if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
					return;
				}
				//此处 处理业务
				String requestid = dataMap.get("requestid");
				//查询订单支付记录表
				platformBankPayment bankPayment = platformBankPayment.selectBankPayment(requestid);
				bankPayment.setState(Const.PLATFORM_BANK_PAYMENT_STATE_0);
				if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_0){
					if(platformBankPayment.update(bankPayment)){
						platformInsuranceServiceImpl.insert4Buy(bankPayment.getOrderNumber());
					}
					//保险
				}else if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_1){
					if(platformBankPayment.update(bankPayment)){
						//修改订单
						if(orderServiceImpl.savePayTakeCargoCost(bankPayment.getOrderNumber()) > 0){
							if(orderServiceImpl.updateOrderPayment(bankPayment.getOrderNumber())){
								if(billService.updatePrepaidOst(Double.parseDouble(bankPayment.getAmount()),bankPayment.getOrderNumber())){
									
								}
							}
						}
					}
					//预约费
				}else if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_2){
					if(platformBankPayment.update(bankPayment)){
						if(billService.updateIsPayment(bankPayment.getOrderNumber())){
							//查询订单已付运费 
							PlatformOrderBill orderBill = billService.selectByOrderNumber(bankPayment.getOrderNumber());
							//修改账单已付运费
							if(null == orderBill){}else{
								Double sum= orderBill.getPrepaidCost()+Double.parseDouble(bankPayment.getAmount());
								if(billService.updatePrepaidOst(sum,bankPayment.getOrderNumber())){
									
								}
							}
							
						}
					}
					//运费
				}else if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_3){
					if(platformBankPayment.update(bankPayment)){
					}
					//保证金
				}
				//第四步 判断通知方式，如果是后台通知则需要回写SUCCESS 
				String notifytype	= dataMap.get("notifytype");
				if("SERVER".equals(notifytype)) {
					out.println("SUCCESS");
					out.flush();
					out.close();
				} else {
					request.setAttribute("dataMap", dataMap);
					RequestDispatcher view	= request.getRequestDispatcher("jsp/payApiCallback.jsp");
					view.forward(request, response);
				}
	}
	/**
	 * 云仓订单回调接口
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/payYcCallBack")
	protected void payYcCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out	= response.getWriter();
		//第一步 获取回调密文data
		String data	= request.getParameter("data");
		//因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
		if(data == null) {
			return;
		}
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		System.out.println("-=--------------------------------"+dataMap.toString());
		
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
			return;
		}
		//此处 处理业务
		String requestid = dataMap.get("requestid");
		//查询订单支付记录表
		platformBankPayment bankPayment = platformBankPayment.selectBankPayment(requestid);
		bankPayment.setState(Const.PLATFORM_BANK_PAYMENT_STATE_0);
		if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_1){
			if(platformBankPayment.update(bankPayment)){
				if(billService.updateIsPayment(bankPayment.getOrderNumber())){
					//查询订单已付运费 
					PlatformOrderBill orderBill = billService.selectByOrderNumber(bankPayment.getOrderNumber());
					//修改账单已付运费
					if(null == orderBill){}else{
						Double sum= orderBill.getPrepaidCost()+Double.parseDouble(bankPayment.getAmount());
						if(billService.updatePrepaidOst(sum,bankPayment.getOrderNumber())){
							
						}
					}
					
				}
			}
		}
		//第四步 判断通知方式，如果是后台通知则需要回写SUCCESS 
		String notifytype	= dataMap.get("notifytype");
		if("SERVER".equals(notifytype)) {
			out.println("SUCCESS");
			out.flush();
			out.close();
		} else {
			request.setAttribute("dataMap", dataMap);
			RequestDispatcher view	= request.getRequestDispatcher("jsp/payApiCallback.jsp");
			view.forward(request, response);
		}
	}
	
	/**
	 * 云仓订单支付
	 * @param bill
	 * @param session
	 * @return
	 */
	@RequestMapping("/doYcOrderMoney")
	@ResponseBody
	public Result doYcOrderMoney(platformBankPayment bankPayment,HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException{
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user==null){
			FengUtil.RuntimeExceptionFeng("你没有登录..");
		}
		String payproducttype = "SALES";
		bankPayment.setCustomernumber("10013368774");
		StringBuffer sb = new StringBuffer();
		sb.append("MO");
		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
		bankPayment.setRequestid(sb.toString());
		//
		bankPayment.setCallbackurl("http://www.xslwl56.com:8080/logisticsc/orderCenter/payYcCallBack.pay");
		bankPayment.setWebcallbackurl("http://www.xslwl56.com:8080/logisticsc/orderCenter/toorderlistpage.pay");
		bankPayment.setPayproducttype(payproducttype);
		bankPayment.setTime(new Date());
		bankPayment.setState(Const.PLATFORM_BANK_PAYMENT_STATE_1); // o 已支付 1 未支付
		bankPayment.setUserId(user.getId());
		if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_1){
			//预约费
			bankPayment.setProductname(BankOrderType.APPOINTMENT.getName());
			bankPayment.setProductcat(BankOrderType.APPOINTMENT.getName());
			bankPayment.setProductdesc(BankOrderType.APPOINTMENT.getName());
			bankPayment.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_1);
		}
		request.setAttribute("requestid",bankPayment.getRequestid());
		request.setAttribute("productname",bankPayment.getProductname());
		request.setAttribute("amount",bankPayment.getAmount());
		request.setAttribute("assure",bankPayment.getAssure());
		request.setAttribute("productname",bankPayment.getProductname());
		request.setAttribute("productcat",bankPayment.getProductcat());
		request.setAttribute("productdesc",bankPayment.getProductdesc());
		request.setAttribute("productdesc",bankPayment.getProductdesc());
		request.setAttribute("callbackurl",bankPayment.getCallbackurl());
		request.setAttribute("webcallbackurl",bankPayment.getWebcallbackurl());
		//第一步 生成密文data
		String data			= ZGTDataAttribute.buildPayData(request);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.PAYAPI_NAME);
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		
		//第三步 判断请求是否成功， 
		if(responseMap.containsKey("code")) {
			result.setMsg("请求银行接口失败");
			return result ;
		}
		//第四步 解密同步响应密文data，获取明文参数
		String responseData	= responseMap.get("data");
		Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
		
		result.setData(responseDataMap);
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			result.setMsg("请求银行接口失败");
			return result ;
		}
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.PAYAPI_RESPONSE_HMAC_ORDER)) {
			result.setMsg("验证签名失败");
			return result ;
		}
		//第七步 进行业务处理
		//payapi支付接口，当支付方式为SALES-网银、或ONEKEY-手机一键时，需要跳转到易宝的收银台页面完成支付
		if(payproducttype.equals("SALES") || payproducttype.equals("ONEKEY")) {
				if(platformBankPayment.insertBankPayment(bankPayment)){
					String payurl	= responseDataMap.get("payurl");
					response.sendRedirect(payurl);
					return result ;
				}else{
					result.setMsg("系统数据异常");
					return result ;
				}
		}
		return result ;
	}
	/**
	 * 订单支付
	 * @param bill
	 * @param session
	 * @return
	 */
	@RequestMapping("/doOrderMoney")
	@ResponseBody
	public Result doOrderMoney(platformBankPayment bankPayment,HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException{
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user==null){
			FengUtil.RuntimeExceptionFeng("你没有登录..");
		}
		String payproducttype = "SALES";
		bankPayment.setCustomernumber("10013368774");
		StringBuffer sb = new StringBuffer();
		sb.append("MO");
		sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
		bankPayment.setRequestid(sb.toString());
		//
		bankPayment.setCallbackurl("http://127.0.0.1:8080/logisticsc/orderCenter/payCallBack.pay");
		bankPayment.setWebcallbackurl("http://127.0.0.1:8080/logisticsc/orderCenter/toorderlistpage.pay");
		bankPayment.setPayproducttype(payproducttype);
		bankPayment.setTime(new Date());
		bankPayment.setState(Const.PLATFORM_BANK_PAYMENT_STATE_1); // o 已支付 1 未支付
		bankPayment.setUserId(user.getId());
		if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_0){
			//保险
			bankPayment.setProductname(BankOrderType.INSURANCE.getName());
			bankPayment.setProductcat(BankOrderType.INSURANCE.getName());
			bankPayment.setProductdesc(BankOrderType.INSURANCE.getName());
			bankPayment.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_0);
			
		}else if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_1){
			//预约费
			bankPayment.setProductname(BankOrderType.APPOINTMENT.getName());
			bankPayment.setProductcat(BankOrderType.APPOINTMENT.getName());
			bankPayment.setProductdesc(BankOrderType.APPOINTMENT.getName());
			bankPayment.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_1);
		}else if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_2){
			//运费
			bankPayment.setProductname(BankOrderType.FREIGHT.getName());
			bankPayment.setProductcat(BankOrderType.FREIGHT.getName());
			bankPayment.setProductdesc(BankOrderType.FREIGHT.getName());
			bankPayment.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_2);
		}else if(bankPayment.getOrderType()== Const.PLATFORM_BANK_ORDER_TYPE_3){
			//保证金
			sb = new StringBuffer();
			sb.append("BOND");
			sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
			bankPayment.setOrderNumber(sb.toString());
			bankPayment.setProductname(BankOrderType.BOND.getName());
			bankPayment.setProductcat(BankOrderType.BOND.getName());
			bankPayment.setProductdesc(BankOrderType.BOND.getName());
			bankPayment.setOrderType(Const.PLATFORM_BANK_ORDER_TYPE_3);
		}
		request.setAttribute("requestid",bankPayment.getRequestid());
		request.setAttribute("productname",bankPayment.getProductname());
		request.setAttribute("amount",bankPayment.getAmount());
		request.setAttribute("assure",bankPayment.getAssure());
		request.setAttribute("productname",bankPayment.getProductname());
		request.setAttribute("productcat",bankPayment.getProductcat());
		request.setAttribute("productdesc",bankPayment.getProductdesc());
		request.setAttribute("productdesc",bankPayment.getProductdesc());
		request.setAttribute("callbackurl",bankPayment.getCallbackurl());
		request.setAttribute("webcallbackurl",bankPayment.getWebcallbackurl());
		//第一步 生成密文data
		String data			= ZGTDataAttribute.buildPayData(request);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.PAYAPI_NAME);
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		
		//第三步 判断请求是否成功， 
		if(responseMap.containsKey("code")) {
			result.setMsg("请求银行接口失败");
			return result ;
		}
		//第四步 解密同步响应密文data，获取明文参数
		String responseData	= responseMap.get("data");
		Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
		
		result.setData(responseDataMap);
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			result.setMsg("请求银行接口失败");
			return result ;
		}
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.PAYAPI_RESPONSE_HMAC_ORDER)) {
			result.setMsg("验证签名失败");
			return result ;
		}
		//第七步 进行业务处理
		//payapi支付接口，当支付方式为SALES-网银、或ONEKEY-手机一键时，需要跳转到易宝的收银台页面完成支付
		if(payproducttype.equals("SALES") || payproducttype.equals("ONEKEY")) {
				if(platformBankPayment.insertBankPayment(bankPayment)){
					String payurl	= responseDataMap.get("payurl");
					response.sendRedirect(payurl);
					return result ;
				}else{
					result.setMsg("系统数据异常");
					return result ;
				}
		}

		/*else {
			//DIRECT无卡直连，当为发送短信验证码模式时，必须调用4.5短信验证码发送接口、4.6短信验证码确认接口才能完成支付。
			//DIRECT无卡直连，当为不发送短信验证码模式时，返回code=1，则表示扣款成功。
			//LEDGER账户支付，返回code=1，则表示扣款成功。
			request.setAttribute("responseDataMap", responseDataMap);
			RequestDispatcher view	= request.getRequestDispatcher("order_list.jsp");
			view.forward(request, response);
			return result ;
		}*/
		
		/*if(orderServiceImpl.doOrderMoney(bill)){
			result.setMsg("支付成功!");
			result.setResult(true);
		}else{
			result.setMsg("支付失败!");
			result.setResult(false);
		}*/
		return result ;
	}
	/**
	 * 资金流水
	 * @param page
	 * @param session
	 * @param bankPayment
	 * @return
	 */
	@RequestMapping("/getBySelectedForm")
	@ResponseBody
	public Result getBySelectedForm(Page<?> page,HttpSession session,platformBankPayment bankPayment){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user!=null){
			bankPayment.setUserId(user.getId());
			page = platformBankPayment.selectBySelectedPaymentFrom(bankPayment, page);
			result.setData(page);
			result.setResult(true);
		}
		return result;
	}
}
