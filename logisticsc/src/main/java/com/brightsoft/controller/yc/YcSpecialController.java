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
import com.brightsoft.entity.YcSpecial;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcSpecialService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/**
 * 专线控制器 
 * Author:luojing
 * 2016年6月23日 下午1:42:40
 */
@Controller 
public class YcSpecialController {  
	@Autowired 
	private IYcSpecialService iYcSpecialService; 
	
	/**
	 * 前往专线管理页
	 * Author:luojing
	 * 2016年6月23日 下午1:44:29
	 */
	@RequestMapping("toSpecial")
	public String toSpecial(){
		return "/Clound/special/special";
	}
	
	/**
	 * 前往添加专线页
	 * Author:luojing
	 * 2016年6月23日 下午1:45:35
	 */
	@RequestMapping("toAddSpecial")
	public String toAddSpecial(){
		return "/Clound/special/add_special";
	}
	
	/**
	 * 前往修改专线页
	 * Author:luojing
	 * 2016年6月23日 下午1:45:35
	 */
	@RequestMapping("toModSpecial")
	public String toModSpecial(YcSpecial s,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", s.getId());
		return "/Clound/special/mod_special";
	}


	/**
	 * 获取单条记录 
	 * Author:luojing
	 * 2016年6月23日 下午1:42:49
	 */
	@RequestMapping("getYcSpecialSingle") 
	public void getYcSpecialSingle(YcSpecial s,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcSpecial spe = iYcSpecialService.getSingleInfo(s);
			if(spe!=null){
				FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, spe, response);
			}else{
				FengUtil.OUTADDERROR("", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 获取分页记录  
	 * Author:luojing
	 * 2016年6月23日 下午1:42:54
	 */
	@RequestMapping("getYcSpecialList") 
	public void getYcSpecialList(YcSpecial s,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcSpecial> pager = new Pager<YcSpecial>(page, rows);
			pager = iYcSpecialService.getListPagerInfo(pager, s);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 添加方法 
	 * Author:luojing
	 * 2016年6月23日 下午1:43:01
	 */
	@RequestMapping("addYcSpecial") 
	public void addYcSpecial(YcSpecial s,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, Constant.YCLOGINUSER);
			s.setCreateTime(DateUtil.getDateTimeFormatString());
			s.setCreateUser(su.getRealname());
			s.setStatus(0);// 审核状态默认0待审核
			Integer i = iYcSpecialService.addSingleInfo(s);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_special", "special_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败",  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTROLLBACK(e.getMsg(), Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK("专线重复添加", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	
	/**
	 * 修改方法  
	 * Author:luojing
	 * 2016年6月23日 下午1:43:08
	 */
	@RequestMapping("modYcSpecial") 
	public void modYcSpecial(YcSpecial s,HttpServletRequest request,HttpServletResponse response) {  
		try{
			s.setUpdateTime(DateUtil.getDateTimeFormatString());
			s.setUpdateUser("普京");
			Integer i = iYcSpecialService.modSingleInfo(s);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_special", "special_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败",  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除方法 
	 * Author:luojing
	 * 2016年6月23日 下午1:43:15
	 */
	@RequestMapping("delYcSpecial") 
	public void delYcSpecial(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(String str : ids.split(",")){
				list.add(new BigInteger(str));
			}
			Integer i = iYcSpecialService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除 "+i+" 条数据成功", Constant.RESULT_SUCCESS_CODE,"del",response);
			}else{
				FengUtil.OUTADDERROR(Constant.DEL_MSG_002, Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 查询没有注册加盟商的专线用户
	 * Author:luojing
	 * 2016年6月23日 下午4:53:33
	 */
	@RequestMapping("getUnRegisterSpecial")
	public void getUnRegisterSpecial(HttpServletRequest request,HttpServletResponse response) {
		try{
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, iYcSpecialService.getUnRegisterSpecial(), response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			System.out.println(1);
			throw new FengRuntimeException("测试异常");
		} catch (FengRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			
		}
	}
}
