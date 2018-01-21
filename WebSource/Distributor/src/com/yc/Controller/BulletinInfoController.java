package com.yc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcBulletinInfo;
import com.yc.Service.BulletinInfoService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;

@Controller
public class BulletinInfoController {

	@Autowired
	private BulletinInfoService bulletinInfoService;
	
	/**
	 * 查询咨询
	 * @Author:luojing
	 * @2016年7月23日 下午3:28:14
	 */
	@RequestMapping("app/getBulletinInfo")
	public void getBulletinInfo(HttpServletRequest request,HttpServletResponse response){
		try{
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			Pager<LcBulletinInfo> pager = new Pager<LcBulletinInfo>(page, rows);
			pager = bulletinInfoService.getListPagerInfo(pager);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, "暂无结果", response);
		}
	}
}
