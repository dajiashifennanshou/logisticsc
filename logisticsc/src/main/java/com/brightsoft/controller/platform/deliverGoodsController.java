package com.brightsoft.controller.platform;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.OutletsPriceRangeConf;
import com.brightsoft.model.PlatformDeliverGoods;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.PlatformStoreRecord;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.platform.PlatformStoreRecordService;
import com.brightsoft.service.platform.XzqhServiceImpl;
import com.brightsoft.service.tms.platform.AdditionalServerServiceImpl;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsPriceRangeConfService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.system.PublishInfoService;
import com.brightsoft.service.yc.IYcGoodsTypeService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 我要发货
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/deliverGoods")
public class deliverGoodsController extends BaseController{
	
	@Autowired
	private PlatformOrderServiceImpl orderServiceImpl;
	
	@Autowired
	private PlatformStoreRecordService storeRecordService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private AdditionalServerServiceImpl additionalServerService;
	
	/*@Autowired
	private BasicDataService basicDataService;*/
	
	@Autowired
	private PublishInfoService publishInfoService;
	
	@Autowired
	private XzqhServiceImpl xzqhService;
	
	@Autowired
	private OutletsPriceRangeConfService outletsPriceRangeConfService;
	
	@Autowired
	private IYcGoodsTypeService iycgoodstype;
	
	/**
	 * 跳转到 我要发货 页面
	 * @return
	 */
	@RequestMapping("/deliverGoods")
	public String toDeliverGoodsPage(PlatformDeliverGoods platformDeliverGoods,ModelMap model,HttpServletRequest request){
		model.addAttribute("paramForm", JSON.toJSONString(platformDeliverGoods));
		model.addAttribute("xqzh", JSON.toJSONString(platformDeliverGoods));
		String userName = request.getParameter("companyNames");
//		if(chooseid != null&&!chooseid.equals("")){
//			
//		}
		String chooseid = request.getParameter("chooseid");
		model.addAttribute("chooseid",chooseid);
		if(null != userName){
			/*userName =  new  String(userName.getBytes("ISO-8859-1"),"UTF-8");*/
			model.addAttribute("companyName", userName);
			
		}
		return "/platform/deliverGoods/deliver_goods";
	}
	/**
	 * 跳转到下单页面
	 * @return
	 */
	@RequestMapping("/placeOrder")
	@RepeatSubmission(needSaveToken = true)
	public String toCreateOderPage(HttpServletRequest request){
		String lineId = request.getParameter("lineId");
		String startProvince = request.getParameter("startProvince");
		String startCity = request.getParameter("startCity");
		String startCounty = request.getParameter("startCounty");
		String endProvince = request.getParameter("endProvince");
		String endCity = request.getParameter("endCity");
		String endCounty = request.getParameter("endCounty");
		
		OutletsInfo startOutletsAdd = new OutletsInfo();
		startOutletsAdd.setProvince(startProvince);
		//startOutletsAdd.setProvinceValue(xzqhService.selectValueById(startProvince).getName());
		startOutletsAdd.setCity(startCity);
		//startOutletsAdd.setCityValue(xzqhService.selectValueById(startCity).getName());
		startOutletsAdd.setCounty(startCounty);
		//startOutletsAdd.setCountyValue(xzqhService.selectValueById(startCounty).getName());
		request.setAttribute("startOutletsAdd", startOutletsAdd);
		OutletsInfo endOutletsAdd = new OutletsInfo();
		endOutletsAdd.setProvince(endProvince);
		//endOutletsAdd.setProvinceValue(xzqhService.selectValueById(endProvince).getName());
		endOutletsAdd.setCity(endCity);
		//endOutletsAdd.setCityValue(xzqhService.selectValueById(endCity).getName());
		endOutletsAdd.setCounty(endCounty);
		//endOutletsAdd.setCountyValue(xzqhService.selectValueById(endCounty).getName());
		request.setAttribute("endOutletsAdd", endOutletsAdd);
		
		LineInfo lineInfo = lineInfoService.selectByPrimaryKey(Long.parseLong(lineId));
		OutletsInfo startOutlets = outletsService.selectOutletsInfoById(lineInfo.getStartOutlets());
		OutletsInfo endOutlets = outletsService.selectOutletsInfoById(lineInfo.getEndOutlets());
		// 获取车辆类型
		List<PlatformDictionary> vehicleTypes = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
		// 获取包装类型信息
		List<PlatformDictionary> packageTypes = dictionaryService.selectDictDataByType(DictionaryType.CARGO_PACKAGE_TYPE);
		// 获取线路增值服务配置
		AdditionalServerConf additionalServerConf = additionalServerService.selectByLineId(Long.parseLong(lineId));
		// 起始网点 提送货配置数据
		List<OutletsPriceRangeConf> outletsPriceRangeConfs = outletsPriceRangeConfService.selectByOutletsId(lineInfo.getStartOutlets());
		// 获取 物流商 基础数据配置
		//BasicData basicData = basicDataService.selectByLineId(Long.parseLong(lineId));
		//获取货物类型数据
		YcGoodsType ygt=new YcGoodsType();
		ygt.setParentSoft(0);
		List<YcGoodsType> lstGt=iycgoodstype.getGoodsTypeBy(ygt);
		request.setAttribute("lineId", lineId);
		request.setAttribute("goodsTypes", lstGt);
		request.setAttribute("startOutlets", startOutlets);
		request.setAttribute("endOutlets", endOutlets);
		request.setAttribute("vehicleTypes", vehicleTypes);
		request.setAttribute("packageTypes", packageTypes);
		request.setAttribute("additionalServerConf", additionalServerConf);
		request.setAttribute("outletsPriceRangeConfs", JSONArray.toJSONString(outletsPriceRangeConfs));
		//request.setAttribute("basicData", basicData);
		request.setAttribute("lineInfo", lineInfo);
		return "/platform/deliverGoods/place_order";
	}
	
	/**
	 * 下单
	 * @return
	 */
	@RequestMapping("/placeorder")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public Object placeOrder(String order, String orderAddServer, String cargoInfos, HttpSession session){
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		return orderServiceImpl.saveOrder(order, orderAddServer, cargoInfos, user);
	}
	/**
	 * 我要发货
	 * @param deliverGoods
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getListorderDeliver")
	@ResponseBody
	public Result getListorderDeliver(PlatformDeliverGoods deliverGoods,Page<?> page){
		Result ret = new Result();
		page = orderServiceImpl.selectBySelectedDeliver(deliverGoods, page);
		ret.setData(page);
		ret.setMsg("获取发货成功!");
		ret.setResult(true);
		return ret;
	}
	/**
	 * 增加线路收藏
	 * @return
	 */
	@RequestMapping("/doCollectionLine")
	@ResponseBody
	public Result doCollectionLine(PlatformStoreRecord platformStoreRecord,HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(null == user.getId() || user.getId() <= 0){
			ret.setMsg("用户ID为空!");
			ret.setResult(false);
		}else{
			platformStoreRecord.setUserId(user.getId());
			if(storeRecordService.insertCollectionLine(platformStoreRecord)){
				ret.setMsg("收藏线路成功!");
				ret.setResult(true);
			}else{
				ret.setMsg("收藏线路失败!");
				ret.setResult(false);
			}
		}
		return ret;
	}
	/**
	 * 验证线路是否已收藏
	 * @param platformStoreRecord
	 * @param session
	 * @return
	 */
	@RequestMapping("/getCollectionLineId")
	@ResponseBody
	public Result getCollectionLineId(PlatformStoreRecord platformStoreRecord,HttpSession session){
		Result ret = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		platformStoreRecord.setUserId(user.getId());
		if(storeRecordService.selectCollentionLineId(platformStoreRecord)){
			ret.setMsg("线路收藏已存在");
			ret.setResult(true);
		}else{
			ret.setMsg("收藏不存在");
			ret.setResult(false);
		}
		return ret;
	}
	
	@RequestMapping("/getPlatInfo")
	@ResponseBody
	public Result getPlatInfo(){
		Result result = new Result();
		result.setData(publishInfoService.select4Platform());
		result.setResult(true);
		return result;
	}
	
	/**
	 * 支付提货费
	 * @param orderNumber
	 * @param money
	 * @return
	 */
	@RequestMapping("/paytakecargocost")
	@ResponseBody
	public String payTakeCargoCost(String orderNumber, String money){
		int rows = orderServiceImpl.savePayTakeCargoCost(orderNumber);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
}
