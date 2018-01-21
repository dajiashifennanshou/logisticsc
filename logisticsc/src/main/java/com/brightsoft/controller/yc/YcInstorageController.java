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
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.entity.YcGoods;
import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.entity.YcInGoods;
import com.brightsoft.entity.YcInstorage;
import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.entity.YcZoneGoods;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.yc.IYcGoodsService;
import com.brightsoft.service.yc.IYcGoodsTypeService;
import com.brightsoft.service.yc.IYcInGoodsService;
import com.brightsoft.service.yc.IYcInstorageService;
import com.brightsoft.service.yc.IYcJoinInfoService;
import com.brightsoft.service.yc.IYcZoneGoodsService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.GsonUtil;
import com.brightsoft.utils.yc.Pager;
import com.google.gson.reflect.TypeToken;   

/** 
* YcInstorage控制器 
* Author:luojing 
* 2016年6月17日 下午3:40:28
*/ 
@Controller 
public class YcInstorageController {  
	@Autowired 
	private IYcInstorageService iYcInstorageService; 
	@Autowired
	private IYcGoodsTypeService iycgoodstype;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private PlatformUserService pus;
	@Autowired
	private IYcGoodsService iycgoods;
	@Autowired
	private IYcInGoodsService iycingoods;
	@Autowired
	private IYcZoneGoodsService iyczonegoods;
	@Autowired
	private IYcJoinInfoService iycjoin;
	/**
	 * 前往入库记录管理页
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toInStorage")
	public String toInStorage(HttpServletRequest request){
		
		return "/Clound/in_storage/in_storage";
	}
	/**
	 * 前往选库区页面
	 */
	@RequestMapping("toChooseStorage")
	public String toChooseStorage(HttpServletRequest request){
		
		return "/Clound/choose_storage/choose_storage";
	}
	
	/**
	 * 前往入库记录添加页
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toAddInStorage")
	public String toAddInStorage(HttpServletRequest request){
		SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, Constant.YCLOGINUSER);
		PlatformUser pu=new PlatformUser();
		pu.setUserType(12);
		pu.setBranchNo(su.getBranchNo());
		String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
		pu.setBranchNo(branchNo);
		List<PlatformUser> lst=pus.getAllUser(pu);
		request.setAttribute("userList", lst);
		request.setAttribute("branchNo", su.getBranchNo());
		return "/Clound/in_storage/add_in_storage";
	}
	/**
	 * 前往手动入库记录添加页
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toAddInStorageSelf")
	public String toAddInStorageSelf(HttpServletRequest request){
		SysUser su=new SysUser();
		su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
		if(su==null){
			FengUtil.RuntimeExceptionFeng("你还未登录..");
			//return "/Clound/login";
		}
		//获取货物类型数据
		YcGoodsType ygt=new YcGoodsType();
		ygt.setBranchNo(su.getBranchNo());
		List<YcGoodsType> lstGt=iycgoodstype.getGoodsTypeBy(ygt);
		// 获取包装类型信息
		List<PlatformDictionary> packageTypes = dictionaryService.selectDictDataByType(DictionaryType.CARGO_PACKAGE_TYPE);
		PlatformUser pu=new PlatformUser();
		pu.setUserType(12);
		pu.setBranchNo(su.getBranchNo());
		List<PlatformUser> lst=pus.getAllUser(pu);
		request.setAttribute("goodsTypes", lstGt);
		request.setAttribute("userList", lst);
		request.setAttribute("packageTypes", packageTypes);
		return "/Clound/in_storage/add_in_storage_self";
	}
	
	
	/**
	 * 前往入库记录修改页
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toModInStorage")
	public String toModInStorage(YcInstorage is,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", is.getId());
		SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, Constant.YCLOGINUSER);
		request.setAttribute("branchNo", su.getBranchNo());
		return "/Clound/in_storage/mod_in_storage";
	}
	
	

	/** 
	* 获取单条记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcInstorageSingle") 
	public void getYcInstorageSingle(YcInstorage is,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcInstorage ins = iYcInstorageService.getSingleInfo(is);
			if(ins!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, ins, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 获取分页记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcInstorageList") 
	public void getYcInstorageList(YcInstorage is,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("未登录..");
			}
			is.setBranchNo(su.getBranchNo());
			Pager<YcInstorage> pager = new Pager<YcInstorage>(page, rows);
			pager = iYcInstorageService.getListPagerInfo(pager, is);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR("", Constant.RESULT_SUCCESS_CODE, response);
			e.printStackTrace();
		}
	} 
	
	/** 
	* 添加方法 
	* Author:luojing 
	*/ 
	@RequestMapping("addYcInstorage") 
	public void addYcInstorage(YcInstorage is,String cId,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, Constant.YCLOGINUSER);
			is.setCreateTime(DateUtil.getDateTimeFormatString());
			is.setCreateUser(su.getRealname());
			is.setBranchNo(su.getBranchNo());
			is.setWaybillSource(0);
			Integer i = iYcInstorageService.addInstorage(is, cId);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_in_storage", "in_storage_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTADDERROR(e.getMsg(), Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	 * 手动添加方法 
	 * Author:luojing 
	 */ 
	@RequestMapping("addYcInstorageSelf") 
	@Transactional
	public void addYcInstorageSelf(YcInstorage is,HttpServletRequest request,HttpServletResponse response) {  
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			String wb=FengUtil.WB_Number();
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			String goodsInfo=request.getParameter("goodsInfo");
			String dealerId=request.getParameter("dealerId");
			YcJoinInfo yji=new YcJoinInfo();
			yji.setJoinerId(new BigInteger(dealerId));
			yji=iycjoin.getSingleInfo(yji);
			is.setZoneNo(yji.getZoneNo());
			is.setInType(1);
			is.setWaybillSource(1);
			is.setWaybillNo(wb);
			is.setDealerId(new BigInteger(dealerId));
			is.setBranchNo(branchNo);
			is.setCreateTime(DateUtil.getDateTimeFormatString());
			is.setCreateUser(pu.getRealname());
			List<YcGoods> goods= GsonUtil.fromJsonList(goodsInfo, new TypeToken<List<YcGoods>>(){}.getType());
			List<YcInGoods> ingoods=GsonUtil.fromJsonList(goodsInfo, new TypeToken<List<YcInGoods>>(){}.getType());
			for(YcGoods yg:goods){
				yg.setWayBillNo(wb);
				yg.setDealerId(new BigInteger(dealerId));
				yg.setCreateTime(DateUtil.getDateTimeFormatString());
				yg.setCreateUser(pu.getRealname());
				yg.setInStorageStatus(0);
				yg.setOutStorageStatus(0);
				iycgoods.addSingleInfo(yg);
			}
			for(YcInGoods yig:ingoods){
				yig.setDealerId(new BigInteger(dealerId));
				yig.setCreateTime(DateUtil.getDateTimeFormatString());
				yig.setCreateUser(pu.getRealname());
				yig.setWayBillNo(wb);
				iycingoods.addSingleInfo(yig);
			}
			//库区储货表
			YcZoneGoods yzg=new YcZoneGoods();
			yzg.setWayBillNo(wb);
			yzg.setDealerId(new BigInteger(dealerId));
			yzg.setBranchNo(branchNo);
			yzg.setZoneNo(yji.getZoneNo());
			yzg.setInZoneStatus(0);
			yzg.setCreateTime(DateUtil.getDateTimeFormatString());
			yzg.setCreateUser(pu.getRealname());
			iyczonegoods.addSingleInfo(yzg);
			Integer i = iYcInstorageService.addSingleInfo(is);
			FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_in_storage_self", "in_storage_lst", response);
			
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
			FengUtil.OUTROLLBACK(e.getLocalizedMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing 
	*/ 
	@RequestMapping("modYcInstorage") 
	public void modYcInstorage(YcInstorage is,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new ValitedLogin<SysUser>().VALITEDLOGIN(request, Constant.YCLOGINUSER);
			is.setUpdateTime(DateUtil.getDateTimeFormatString());
			is.setUpdateUser(su.getRealname());
			Integer i = iYcInstorageService.modSingleInfo(is);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_in_storage", "in_storage_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	*/ 
	@RequestMapping("delYcInstorage") 
	public void delYcInstorage(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			/*List<BigInteger> list = new ArrayList<BigInteger>();
			for(String str : ids.split(",")){
				list.add(new BigInteger(str));
			}*/
			Integer i = iYcInstorageService.delInstorage(ids);
			if(i>0){
				FengUtil.OUTADDERROR("删除成功", Constant.RESULT_SUCCESS_CODE, response);
			}else{
				FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}
