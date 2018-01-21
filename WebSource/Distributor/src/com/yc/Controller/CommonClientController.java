package com.yc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.YcCommonClient;
import com.yc.Service.IYcCommonClientService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.Pager;
/**
 * 常用客户
 * @Author:luojing
 * @2016年7月5日 上午9:14:40
 */
@Controller
public class CommonClientController {
	@Autowired 
	private IYcCommonClientService iYcCommonClientService; 
	
	/**
	 * 获取常用客户列表，分页查询
	 * @Author:luojing
	 * @2016年7月5日 上午9:19:55
	 */
	@RequestMapping("app/getCommonClient")
	public void getCommonClient(HttpServletRequest request,HttpServletResponse response){
		try{
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			YcCommonClient cc = (YcCommonClient) FengUtil.getObject(request.getParameterMap(),new YcCommonClient());
			Pager<YcCommonClient> pager = new Pager<YcCommonClient>(page, rows);
			pager = iYcCommonClientService.getListPagerInfo(pager, cc);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
}
