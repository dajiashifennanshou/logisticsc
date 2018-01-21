package com.yc.Controller; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.YcStorageBranch;
import com.yc.Service.IYcStorageBranchService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;

/** 
* YcStorageBranch控制器 
* Author:luojing 
*/ 
@Controller 
public class YcStorageBranchController {  
	@Autowired 
	private IYcStorageBranchService iYcStorageBranchService; 

	/**
	 * 集合查询云仓编号
	 * Author:luojing
	 * 2016年6月14日 下午1:35:27
	 */
	@RequestMapping("app/getStorageBranch")
	public void getBranchNoList(HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcStorageBranch> list = iYcStorageBranchService.getBranchNoList();
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, list, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果",response);
		}
	}
}
