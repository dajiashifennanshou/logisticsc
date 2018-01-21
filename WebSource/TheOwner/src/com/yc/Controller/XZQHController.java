package com.yc.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.XZQHInfo;
import com.yc.Service.XZQHInfoService;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;

/**
 * 获取省市县
 * @Author:luojing
 * @2016年7月27日 下午3:50:30
 */
@Controller
public class XZQHController {
	
	@Autowired
	private XZQHInfoService zxqhInfoService;

	/**
	 * 查询地址
	 * @Author:luojing
	 * @2016年7月27日 下午3:51:43
	 */
	@RequestMapping("app/getXZQH")
	public void getXZQH(HttpServletRequest request,HttpServletResponse response){
		try{
			List<XZQHInfo> list = zxqhInfoService.getXZQH();
			if(list.isEmpty()){
				FengUtil.RuntimeExceptionFeng("网络异常,请重试");
			}
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, list, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
		
	}
}
