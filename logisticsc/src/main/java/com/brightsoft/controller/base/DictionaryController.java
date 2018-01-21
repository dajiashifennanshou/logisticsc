package com.brightsoft.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.yc.IYcGoodsTypeService;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/dic")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryServiceImpl;
	
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private IYcGoodsTypeService iycgoodstype;
	
	/**
	 * 获取网点类型
	 * @param type
	 * @return
	 */
	@RequestMapping("/tlttyp")
	@ResponseBody
	public Result getDic4OutletsType(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.OUTLETS_TYPE));
		result.setResult(true);
		return result;
	}
	/**
	 * 获取网点性质
	 * @param type
	 * @return
	 */
	@RequestMapping("/tltntr")
	@ResponseBody
	public Result getDic4OutletsNature(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.OUTLETS_NATURE));
		result.setResult(true);
		return result;
	}
	/**
	 * 获取运输方式
	 * @param type
	 * @return
	 */
	@RequestMapping("/transMode")
	@ResponseBody
	public Result getDic4TransportMode(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.TRANSPORT_MODE));
		result.setResult(true);
		return result;
	}
	/**
	 * 获取驾驶证类型
	 * @param type
	 * @return
	 */
	@RequestMapping("/licensetype")
	@ResponseBody
	public Result getDic4LicenseType(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.DRIVERLICENSETYPE));
		result.setResult(true);
		return result;
	}
	/**
	 * 获取驾驶证类型
	 * @param type
	 * @return
	 */
	@RequestMapping("/publishtype")
	@ResponseBody
	public Result getDic4PublishType(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.PUBLISH_TYPE));
		result.setResult(true);
		return result;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/ad_position")
	@ResponseBody
	public Result getDic4AdPosition(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.AD_POSITION));
		result.setResult(true);
		return result;
	}

	
	/**
	 * 获取车辆类型
	 * @return
	 */
	@RequestMapping("/getvehicletype")
	@ResponseBody
	public Result getVehicleType(){
		Result result = new Result();
		result.setRows(dictionaryServiceImpl.selectDictDataByType(DictionaryType.VEHICLE_TYPE));
		result.setResult(true);
		return result;
	}

	/**
	 * 获取包装类型
	 * @return
	 */
	@RequestMapping("/getpackagetype")
	@ResponseBody
	public Result getPackageType(){
		Result result = new Result();
		result.setRows(dictionaryService.selectDictDataByType(DictionaryType.CARGO_PACKAGE_TYPE));
		result.setResult(true);
		return result;
	}
	/**
	 * 商品类型
	 * @return
	 */
	@RequestMapping("/getGoodstype")
	@ResponseBody
	public Result getGoodstypes(){
		YcGoodsType ygt=new YcGoodsType();
		ygt.setParentSoft(0);
		List<YcGoodsType> lstGt=iycgoodstype.getGoodsTypeBy(ygt);
		Result result = new Result();
		result.setRows(lstGt);
		result.setResult(true);
		return result;
	}
}
