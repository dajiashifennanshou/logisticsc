package com.brightsoft.controller.yc; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcPost;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcPostService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.LogUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcPost控制器 
* Author:luojing 
*/ 
@Controller 
public class YcPostController {  
	@Autowired 
	private IYcPostService iYcPostService; 
	
	/**
	 * 前往职位管理页
	 * Author:luojing
	 * 2016年6月16日 下午3:39:30
	 */
	@RequestMapping("toPost")
	public String toPost(){
		return "/Clound/post/post";
	}
	
	/**
	 * 前往职位添加页
	 * Author:luojing
	 * 2016年6月16日 下午3:39:30
	 */
	@RequestMapping("toAddPost")
	public String toAddPost(){
		return "/Clound/post/add_post";
	}
	
	/**
	 * 前往职位修改页
	 * Author:luojing
	 * 2016年6月16日 下午3:39:30
	 */
	@RequestMapping("toModPost")
	public String toModPost(YcPost post,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", post.getId());
		return "/Clound/post/mod_post";
	}


	/** 
	* 获取单条记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcPostSingle") 
	public void getYcPostSingle(YcPost post,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcPost p = iYcPostService.getSingleInfo(post);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, p, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 获取分页记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcPostList") 
	public void getYcPostList(YcPost post,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcPost> pager = new Pager<YcPost>(page, rows);
			pager = iYcPostService.getListPagerInfo(pager, post);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	} 
	/** 
	* 添加方法 
	* Author:luojing 
	*/ 
	@RequestMapping("addYcPost") 
	public void addYcPost(YcPost post,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("请登录..");
			}
			post.setCreateTime(DateUtil.getDateTimeFormatString());
			post.setCreateUser(su.getRealname());
			Integer i = iYcPostService.addSingleInfo(post);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_post", "post_lst", response);
			}else{
				FengUtil.RuntimeExceptionFeng("该职位已存在");
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing 
	*/ 
	@RequestMapping("modYcPost") 
	public void modYcPost(YcPost post,HttpServletRequest request,HttpServletResponse response) {  
		try{
			post.setUpdateTime(DateUtil.getDateTimeFormatString());
			post.setUpdateUser("张学友");
			Integer i = iYcPostService.modSingleInfo(post);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_post", "post_lst", response);
			}else{
				FengUtil.RuntimeExceptionFeng("修改失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	*/ 
	@RequestMapping("delYcPost") 
	public void delYcPost(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i = 0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcPostService.delSelect(list);
			if(i>0){
				FengUtil.OUTADDERROR("删除成功", Constant.RESULT_SUCCESS_CODE, response);
			}else{
				FengUtil.RuntimeExceptionFeng("该职位有员工任职，请先更改员工职位");
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	
	/**
	 * 职位集合查询
	 * Author:luojing
	 * 2016年6月16日 下午4:16:25
	 */
	@RequestMapping("getPost") 
	public void getPost(YcPost post,HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcPost> list = iYcPostService.getPost(post);
			if(!list.isEmpty()){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, list, response);
			}else{
				FengUtil.RuntimeExceptionFeng("获取数据失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
}
