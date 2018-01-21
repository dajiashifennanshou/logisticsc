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
import com.brightsoft.entity.YcRuleInfo;
import com.brightsoft.entity.YcStorageBranch;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcRuleInfoService;
import com.brightsoft.service.yc.IYcStorageBranchService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcStorageBranch控制器 
* Author:luojing 
*/ 
@Controller 
public class YcStorageBranchController {  
	@Autowired 
	private IYcStorageBranchService iYcStorageBranchService; 
	@Autowired
	private IYcRuleInfoService iYcRuleInfoService;

	/**
	 * 前往云仓网点管理页
	 * Author:luojing
	 * 2016年6月8日 下午5:01:34
	 */
	@RequestMapping("toStorageBranch")
	public String storageBranch(){
		return "/system/branch_manage";
	}
	
	/**
	 * 前往添加云仓网点页
	 * Author:luojing
	 * 2016年6月8日 下午5:01:49
	 */
	@RequestMapping("toAddStorageBranch")
	public String addStorageBranch(){
		return "/system/add_storage_branch";
	}
	
	/**
	 * 前往编辑云仓网点页
	 * Author:luojing
	 * 2016年6月8日 下午5:01:49
	 */
	@RequestMapping("toModStorageBranch")
	public String editStorageBranch(YcStorageBranch yc_sb,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", yc_sb.getId());
		return "/system/mod_storage_branch";
	}
	

	/**
	 * 获取单条记录  
	 * Author:luojing
	 * 2016年6月12日 下午4:32:59
	 */
	@RequestMapping("getYcStorageBranchSingle") 
	public void getYcStorageBranchSingle(YcStorageBranch yc_sb,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcStorageBranch sb = iYcStorageBranchService.getSingleInfo(yc_sb);
			if(sb!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, sb, response);
			}else{
				FengUtil.OUTPRINTRESULT("获取数据失败", Constant.RESULT_ERROR_CODE, null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
		
	} 
	/** 
	* 获取分页记录 
	* Author:luojing 
	* 2016年6月12日 下午4:32:59
	*/ 
	@RequestMapping("getYcStorageBranchList") 
	public void getYcStorageBranchList(YcStorageBranch sb,Integer pageIndex,Integer limit,Integer start,HttpServletRequest request,HttpServletResponse response) {
		try{
			Pager<YcStorageBranch> pager=new Pager<YcStorageBranch>(start,limit,pageIndex);
			pager=iYcStorageBranchService.getListPagerInfo(pager,sb);
			FengUtil.OUTSYSPAGETMS(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 添加方法 
	* Author:luojing 
	* 2016年6月12日 下午4:32:59
	*/ 
	@RequestMapping("addYcStorageBranch") 
	public void addYcStorageBranch(YcStorageBranch yc_sb,HttpServletRequest request,HttpServletResponse response) {
		try{
			SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, "user");
			yc_sb.setBranchNo(YC_Branch_Number());//编号
			yc_sb.setCreateTime(DateUtil.getDateTimeFormatString());
			yc_sb.setCreateUser(su.getRealname());
			Integer i = iYcStorageBranchService.addSingleInfo(yc_sb);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_storage_branch", "branch_lst", response);
			}else if(i==-1){
				FengUtil.OUTADDERROR("网点重复添加", Constant.RESULT_ERROR_CODE, response);
			}else{
				FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing 
	* 2016年6月12日 下午4:32:59
	*/ 
	@RequestMapping("modYcStorageBranch") 
	public void modYcStorageBranch(YcStorageBranch yc_sb,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, "user");
			
			yc_sb.setUpdateTime(DateUtil.getDateTimeFormatString());
			yc_sb.setUpdateUser(su.getRealname());
			Integer i = iYcStorageBranchService.modSingleInfo(yc_sb);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_storage_branch", "branch_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	* 2016年6月12日 下午4:32:59
	*/ 
	@RequestMapping("delYcStorageBranch") 
	public void delYcStorageBranch(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcStorageBranchService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除"+i+"条数据成功", Constant.RESULT_SUCCESS_CODE, null, response);
			}else{
				FengUtil.OUTADDERROR("删除失败,存在库区关联,无法删除", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	 /**
	  * 生成云仓编号
	  * Author:luojing
	  * 2016年6月12日 下午4:45:37
	  */
	 public  String YC_Branch_Number(){
		 String str = "YC-";
		 YcRuleInfo yri=new YcRuleInfo();
		 yri.setRtype(0);
		 yri=iYcRuleInfoService.getSingleInfo(yri);
		 iYcRuleInfoService.modSingleInfo(yri);
		 return str+yri.getRvalue();
	 }
	/**
	 * 集合查询云仓编号
	 * Author:luojing
	 * 2016年6月14日 下午1:35:27
	 */
	@RequestMapping("getYcStorageBranch")
	public void getBranchNoList(HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcStorageBranch> list = iYcStorageBranchService.getBranchNoList();
			if(!list.isEmpty()){
				FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, list, response);
			}else{
				FengUtil.OUTADDERROR("获取网点编号失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
}
