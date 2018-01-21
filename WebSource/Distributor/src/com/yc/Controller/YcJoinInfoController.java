package com.yc.Controller; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcPlatformUser;
import com.yc.Entity.YcJoinInfo;
import com.yc.Service.IYcJoinInfoService;
import com.yc.Tool.DES;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;

/**
 * 加盟商控制器
 * Author:luojing
 * 2016年6月15日 上午9:25:57
 */
@Controller 
public class YcJoinInfoController {  
	@Autowired 
	private IYcJoinInfoService iYcJoinInfoService; 
	
	/**
	 * 申请加盟
	 * @Author:luojing
	 * @2016年7月5日 下午2:41:58
	 */
	@RequestMapping("app/applyJoin")
	public void addYcJoinInfo(HttpServletRequest request,HttpServletResponse response){ 
		try{
			YcJoinInfo join = (YcJoinInfo) FengUtil.getObject(request.getParameterMap(),new YcJoinInfo());
			YcJoinInfo jo = iYcJoinInfoService.getSingleInfo(join);
			if(jo==null){
				LcPlatformUser user = (LcPlatformUser) request.getSession().getAttribute(request.getParameter("token"));
				join.setCreateTime(DateUtil.getDateTimeFormatString());
				join.setCreateUser(user.getTrue_name());
				join.setJoinType(0);
				join.setApplyStatus(0);//申请状态 0待审核
				Integer i = iYcJoinInfoService.addSingleInfo(join);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS,response);
				}else{
					FengUtil.RuntimeExceptionFeng("申请失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("重复申请");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(),response);
		}
	} 
	
}
