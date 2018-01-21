package com.brightsoft.controller.yc; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.JpushUserInfo;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.service.jpush.IJpushUserInfoService;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcJoinInfoService;
import com.brightsoft.utils.jpush.JpushUtil;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;

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
	 * 前往加盟商管理页
	 * Author:luojing
	 * 2016年6月15日 上午9:27:02
	 */
	@RequestMapping("toJoinInfo")
	public String toJoinInfo(){
		return "/system/join_info";
	}
	
	/**
	 * 前往加盟商添加页
	 * Author:luojing
	 * 2016年6月15日 上午9:27:02
	 */
	@RequestMapping("toAddJoin")
	public String toAddJoin(){
		return "/system/add_join";
	}
	
	/**
	 * 前往加盟商修改页
	 * Author:luojing
	 * 2016年6月15日 上午9:27:02
	 */
	@RequestMapping("toModJoin")
	public String toModJoin(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", join.getId());
		return "/system/mod_join";
	}
	

	/**
	 * 来自于tms方的经销商申请加盟
	 * @Author:luojing
	 * @2016年7月5日 下午2:41:58
	 */
	@RequestMapping("addTmsJoin")
	@Transactional
	public void addTmsJoinInfo(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response) {  
		try{
			PlatformUser user=(PlatformUser) request.getSession().getAttribute(SystemConstant.USER_SESSION);
			if(user==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			//join.setJoinType(0);
			join.setJoinerId(new BigInteger(user.getId().toString()));
			join.setCreateTime(DateUtil.getDateTimeFormatString());
			join.setCreateUser(user.getTrueName());
			join.setApplyStatus(0);//申请状态 0待审核
			Integer i = iYcJoinInfoService.addSingleInfo(join);
			FengUtil.OUTAPPSUCCESS(Constant.RESULT_SUCCESS_CODE, null, response);
		}catch(NullPointerException e){
			FengUtil.OUTROLLBACK("字段不能为空...", Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK(e.getLocalizedMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}

	/**
	 * 获取单条记录 
	 * Author:luojing
	 * 2016年6月15日 上午9:26:05
	 */
	@RequestMapping("getYcJoinInfoSingle") 
	public void getYcJoinInfoSingle(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcJoinInfo j = iYcJoinInfoService.getSingleInfo(join);
			if(j!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, j, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	* 获取分页记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcJoinInfoList") 
	public void getYcJoinInfoList(YcJoinInfo join,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcJoinInfo> pager = new Pager<YcJoinInfo>(page, rows);
			pager = iYcJoinInfoService.getListPagerInfo(pager, join);
			FengUtil.OUTPRINTObject("获取数据成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
		}
		
	} 
	/** 
	 * 获取Tms分页记录 
	 * Author:luojing 
	 */ 
	@RequestMapping("getTmsJoinInfoList") 
	public void getTmsJoinInfoList(YcJoinInfo join,Integer start,Integer limit,Integer pageIndex,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcJoinInfo> pager = new Pager<YcJoinInfo>(start, limit,pageIndex);
			pager = iYcJoinInfoService.getListPagerInfo(pager, join);
			FengUtil.OUTSYSPAGETMS(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
		}
		
	} 
	/** 
	* 添加方法 
	* Author:luojing 
	*/ 
	@RequestMapping("addYcJoinInfo")
	@Transactional
	public void addYcJoinInfo(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response) {  
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, "user");
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			//String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			YcJoinInfo jo = iYcJoinInfoService.getSingleInfo(join);
			if(jo==null){
				join.setCreateTime(DateUtil.getDateTimeFormatString());
				join.setCreateUser(pu.getRealname());
				join.setApplyStatus(0);//申请状态 0待审核
				Integer i = iYcJoinInfoService.addSingleInfo(join);
				if(i>0){
					FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_join", "joininfo_lst", response);
				}else{
					FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
				}
			}else{
				FengUtil.OUTADDERROR("重复申请", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	
	/**
	 * 推送消息
	 */
	@Autowired
	private IJpushUserInfoService iJpushUserInfoService;
	
	/**
	 * 加盟商审核
	 * @Author:luojing
	 * @2016年7月19日 上午11:44:14
	 */
	@RequestMapping("modYcJoinInfo") 
	public void modYcJoinInfo(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=(SysUser) FengUtil.GETDATABYSESSION(request, "user");
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录...");
			}
			//join.setApplyStatus(1);
			join.setStartUseTime(DateUtil.getTimeFormat());
			join.setEndUseTime(DateUtil.MonthAfterDate(join.getDays()));
			join.setUpdateTime(DateUtil.getDateTimeFormatString());
			join.setUpdateUser(su.getRealname());
			Integer i = iYcJoinInfoService.modSingleInfo(join);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_join", "joininfo_lst", response);
				if(join.getJoinType()==0){//经销商时推送
					try{
						JpushUserInfo juser = iJpushUserInfoService.getSingleInfo(new JpushUserInfo(join.getJoinerId()));
						if(join.getApplyStatus()==1){
							JpushUtil.sendMessage(juser.getRegisId(), "您的加盟云仓申请已经通过审核");
						}else{
							JpushUtil.sendMessage(juser.getRegisId(), "很抱歉您的加盟云仓没有通过审核");
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 修改方法  审核通过 也可作为普通更改
	 * Author:luojing 
	 */ 
	@RequestMapping("modStatusJoinInfo") 
	public void modStatusJoinInfo(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=(SysUser) FengUtil.GETDATABYSESSION(request, "user");
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录...");
			}
			join.setUpdateTime(DateUtil.getDateTimeFormatString());
			join.setUpdateUser(su.getRealname());
			Integer i = iYcJoinInfoService.modSingleInfo(join);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_join", "joininfo_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	*/ 
	@RequestMapping("delYcJoinInfo") 
	public void delYcJoinInfo(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcJoinInfoService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除成功", Constant.RESULT_SUCCESS_CODE, "del", response);
			}else{
				FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	
	/**
	 * 集合查询加盟商信息
	 * Author:luojing
	 * 2016年6月20日 上午10:32:13
	 */
	@RequestMapping("getYcJoin")
	public void getYcJoin(YcJoinInfo join,HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcJoinInfo> list = iYcJoinInfoService.getYcJoin(join);
			if(!list.isEmpty()){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, list, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
