package com.yc.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Canstant.DictionaryType;
import com.yc.Entity.PlatformDictionary;
import com.yc.Service.PlatformDictionaryService;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
/**
 * 类型字段
 * @Author:luojing
 * @2016年8月18日 下午1:42:43
 */
@Controller
public class PlatformDictionaryController {

	@Autowired
	private PlatformDictionaryService dictionaryService;
	
	/**
	 * 查询车辆类型
	 * @Author:luojing
	 * @2016年8月18日 下午1:45:47
	 */
	@RequestMapping("app/getDictionaryType")
	public void getDictionaryType(HttpServletRequest request,HttpServletResponse response){
		try{
			List<PlatformDictionary> list = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
			if(!list.isEmpty()){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, list, response);
			}else{
				FengUtil.RuntimeExceptionFeng("暂无结果");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMsg(), response);
		}catch (Exception e) {
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
}
