package com.brightsoft.controller.yc; 
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.Constant.ErrorCode;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.entity.YcGoods;
import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.entity.YcOrderGoods;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.yc.IYcDeliveryOrderService;
import com.brightsoft.service.yc.IYcEmployeeService;
import com.brightsoft.service.yc.IYcGoodsService;
import com.brightsoft.service.yc.IYcJoinInfoService;
import com.brightsoft.service.yc.IYcOrderGoodsService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcDeliveryOrder控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcDeliveryOrderController {  
	@Autowired 
	private IYcDeliveryOrderService iYcDeliveryOrderService; 
	@Autowired 
	private IYcOrderGoodsService iYcOrderGoodsService; 
	@Autowired 
	private IYcGoodsService iYcGoodsService ; 
	@Autowired
	private IYcEmployeeService iYcEmp;
	@Autowired
	private PlatformUserService pus;
	@Autowired
	private IYcJoinInfoService iycjis;
	@Autowired
	private PlatformUserService plus;
	
	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcDeliveryOrderSingle") 
	public void getYcDeliveryOrderSingle(HttpServletRequest request,HttpServletResponse response,YcDeliveryOrder ycdo) {  
		try{
			ycdo=iYcDeliveryOrderService.getSingleInfo(ycdo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, ycdo, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}

	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcDeliveryOrderList") 
	public void getYcDeliveryOrderList(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		ResultEntity re=new ResultEntity();
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				re.setMsg(ErrorCode.NOTLOGIN.getName());
				re.setCode(ErrorCode.NOTLOGIN.getValue());
				FengUtil.RuntimeExceptionFeng();
			}
			ycdo.setBranchNo(pu.getBranchNo());
			Pager<YcDeliveryOrder> pager=new Pager<YcDeliveryOrder>(page, rows);
			pager=iYcDeliveryOrderService.getListPagerInfo(pager, ycdo);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.ERRORDATA(re, response);
		}
	} 
	/** 
	 * 获取tms分页记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getTmsDeliveryOrderList") 
	public void getTmsDeliveryOrderList(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			PlatformUser pu=new PlatformUser();
			pu=(PlatformUser) FengUtil.GETDATABYSESSION(request, SystemConstant.USER_SESSION);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			YcJoinInfo yji=new YcJoinInfo();
			yji.setJoinerId(new BigInteger(pu.getId().toString()));
			yji=iycjis.getSingleInfo(yji);
			if(yji.getApplyStatus()==0){
				FengUtil.RuntimeExceptionFeng("你的审核还未通过..");
			}
			ycdo.setBranchNo(yji.getBranchNo());
			ycdo.setDealerId(new BigInteger(pu.getId().toString()));
			Pager<YcDeliveryOrder> pager=new Pager<YcDeliveryOrder>(page, rows);
			pager=iYcDeliveryOrderService.getListPagerInfo(pager, ycdo);
			FengUtil.OUTAPPPAGETMS(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取sys分页记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getSysDeliveryOrderList") 
	public void getSyssDeliveryOrderList(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response,Integer pageIndex,Integer limit) {  
		try{
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(StrUtil.VObject(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			Pager<YcDeliveryOrder> pager=new Pager<YcDeliveryOrder>(pageIndex+1, limit);
			pager=iYcDeliveryOrderService.getListPagerInfo(pager, ycdo);
			List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
			for(YcDeliveryOrder ydo:pager.getObjectList()){
				List<YcEmployee> ld= iYcEmp.getDriverInfoByDNo(ydo.getDeliveryNo());
				for(YcEmployee ye:ld){
					if(ye!=null){
						ydo.setDeliveryInfo(ye.getEmployeeName()+"-"+ye.getPhone()+"<br/>");
					}
				}
				System.out.println(ydo.getDeliveryInfo()+"--");
				List<YcEmployee> li=iYcEmp.getInstallerInfoByDNo(ydo.getDeliveryNo(),branchNo);
				for(YcEmployee ye:li){
					if(ye!=null){
						ydo.setInstallInfo(ye.getEmployeeName()+"-"+ye.getPhone()+"<br/>");
					}
				}
				lst.add(ydo);
			}
			pager.setObjectList(lst);
			FengUtil.OUTSYSPAGETMS(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取安装工人安装费用列表
	 * Auther:FENG 
	 */ 
	@RequestMapping("getInstallerCharge") 
	public void getInstallerCharge(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			Pager<YcDeliveryOrder> pager=new Pager<YcDeliveryOrder>(page, rows);
			ycdo.setOrderStatus(5);
			pager=iYcDeliveryOrderService.getListPagerInfo(pager, ycdo);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取所有信息
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcDeliveryOrderAllList") 
	public void getYcDeliveryOrderAllList(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			ycdo.setBranchNo(pu.getBranchNo());
			List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
			lst=iYcDeliveryOrderService.getYcDeliveryOrderAllList(ycdo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 获取所有信息
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcDeliveryOrderAllListByStatus") 
	public void getYcDeliveryOrderAllListByStatus(String status,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			Map<String,String> map=new HashMap<String, String>();
			map.put("branchNo", pu.getBranchNo());
			map.put("orderStatus", status);
			List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
			lst=iYcDeliveryOrderService.getYcDeliveryOrderAllListByStatus(map);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 根据配载单编号获取所有信息
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcDeliveryOrderListBystNo") 
	public void getYcDeliveryOrderListBystNo(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			Pager<YcDeliveryOrder> pager=new Pager<YcDeliveryOrder>(page, rows);
			String stowage=request.getParameter("stowageNo");
			List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
			
			for(YcDeliveryOrder yo:iYcDeliveryOrderService.getYcDeliveryOrderByStowage(stowage)){
				PlatformUser pu=plus.selectUser(new Long(yo.getDealerId().toString()));
				yo.setDealerName(pu.getTrueName()+"-"+pu.getLoginName());
				lst.add(yo);
			}
			pager.setObjectList(lst);
			pager.setRecordCount(1000);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 获取未配载的订单
	 * Auther:FENG 
	 */ 
	@RequestMapping("getNoStowageOrder") 
	public void getNoStowageOrder(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
			//lst=iYcDeliveryOrderService.getYcDeliveryOrderAllList();
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcDeliveryOrder") 
	@Transactional
	public void addYcDeliveryOrder(HttpServletRequest request,HttpServletResponse response,YcDeliveryOrder ycdo) {  
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			String deliveryNo=FengUtil.PS_Number();
			//获取货物id
			String goodsIds=StrUtil.getString(request.getParameter("goodsIds"), "");
			String[] goodsId=goodsIds.split(",");
			for(String id:goodsId){
				YcOrderGoods yog=new YcOrderGoods();
				yog.setDeliveryNo(deliveryNo);
				//返货ids集合，下标0为货物的id，1为货物所属的运单号
				String[] ids=id.split("_");
				BigInteger bid=new BigInteger(ids[0]);
				String wayNo=ids[1];
				yog.setZoneGoodsId(bid);
				yog.setWayBillNo(wayNo);
				//添加订单货物信息
				iYcOrderGoodsService.addSingleInfo(yog);
				YcGoods yg=new YcGoods();
				yg.setId(bid);
				yg.setOutStorageStatus(1);
//				YcZoneGoods yzg=new YcZoneGoods();
//				yzg.setWayBillNo(ids[1]);
//				yzg.setWayBillStatus(1);
//				//根据运单号修改库储货状态为已出库,这样这条储货记录就不会再次被配送
//				iYcZoneGoodsService.modWayBillStatusByNo(ids[1]);
				//根据id修改货物出库状态为为已出库，即已出库的货物无法再次出库
				iYcGoodsService.modSingleInfo(yg);
				//这里开始修改运单的状态为在途
			}
			//配送订单单号
			ycdo.setDeliveryNo(deliveryNo);
			ycdo.setDealerId(ycdo.getDealerId());
			
			ycdo.setBranchNo(branchNo);
			ycdo.setAgentPaidCost(new Float(0));
			//设置初始已支付金额
			ycdo.setSumCost(ycdo.getInstallCost());
			ycdo.setCreateUser(pu.getRealname());
			//0
			//ycdo.setDeliveryCost(new Float(0));
			//添加配送单信息
			iYcDeliveryOrderService.addSingleInfo(ycdo);
			FengUtil.OUTADDSUCCESS("", Constant.RESULT_SUCCESS_CODE, "add_delivery_order", "delivery_order_lst", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK("添加失败!",Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * Tms下单添加方法 
	 * Auther:FENG 
	 */ 
	@RequestMapping("addTmsDeliveryOrder") 
	@Transactional
	public void addTmsDeliveryOrder(HttpServletRequest request,HttpServletResponse response,YcDeliveryOrder ycdo) {  
		try{
			ValitedLogin<PlatformUser> v=new ValitedLogin<PlatformUser>();
			PlatformUser pu= v.VALITEDLOGIN(request, SystemConstant.USER_SESSION);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			YcJoinInfo yji=new YcJoinInfo();
			yji.setJoinerId(new BigInteger(pu.getId().toString()));
			yji=iycjis.getSingleInfo(yji);
			String branchNo=yji.getBranchNo();
			String deliveryNo=FengUtil.PS_Number();
			//获取货物id
			String goodsIds=StrUtil.getString(request.getParameter("goodsIds"), "");
			String[] goodsId=goodsIds.split(",");
			for(String id:goodsId){
				YcOrderGoods yog=new YcOrderGoods();
				yog.setDeliveryNo(deliveryNo);
				//返货ids集合，下标0为货物的id，1为货物所属的运单号
				String[] ids=id.split("_");
				BigInteger bid=new BigInteger(ids[0]);
				String wayNo=ids[1];
				yog.setZoneGoodsId(bid);
				yog.setWayBillNo(wayNo);
				//添加订单货物信息
				iYcOrderGoodsService.addSingleInfo(yog);
				YcGoods yg=new YcGoods();
				yg.setId(bid);
				yg.setOutStorageStatus(1);
//				YcZoneGoods yzg=new YcZoneGoods();
//				yzg.setWayBillNo(ids[1]);
//				yzg.setWayBillStatus(1);
//				//根据运单号修改库储货状态为已出库,这样这条储货记录就不会再次被配送
//				iYcZoneGoodsService.modWayBillStatusByNo(ids[1]);
				//根据id修改货物出库状态为为已出库，即已出库的货物无法再次出库
				iYcGoodsService.modSingleInfo(yg);
				//这里开始修改运单的状态为在途
			}
			//配送订单单号
			ycdo.setDeliveryNo(deliveryNo);
			ycdo.setDealerId(new BigInteger(pu.getId().toString()));
			
			ycdo.setBranchNo(branchNo);
			ycdo.setAgentPaidCost(new Float(0));
			//设置初始已支付金额
			ycdo.setSumCost(ycdo.getInstallCost());
			ycdo.setCreateUser(pu.getTrueName());
			//0
			ycdo.setDeliveryCost(new Float(0));
			//添加配送单信息
			iYcDeliveryOrderService.addSingleInfo(ycdo);
			FengUtil.OUTADDSUCCESS("", Constant.RESULT_SUCCESS_CODE, "add_delivery_order", "delivery_order_lst", response);
		}catch(RuntimeException e){
			FengUtil.OUTROLLBACK(e.getLocalizedMessage(),Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK("异常",Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 修改方法 
	 * Auther:FENG 
	 */ 
	@RequestMapping("modYcDeliveryOrder") 
	@Transactional
	public void modYcDeliveryOrder(HttpServletRequest request,HttpServletResponse response,YcDeliveryOrder ycdo) {  
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser su=v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(su==null){
				throw new FengRuntimeException("你还未登录..");
			}
			//ycdo.setPaidCost(f);
			ycdo.setUpdateTime(DateUtil.getDateTimeFormatString());
			ycdo.setUpdateUser(su.getRealname());
			iYcDeliveryOrderService.modSingleInfo(ycdo);
			FengUtil.OUTADDSUCCESS("", Constant.RESULT_SUCCESS_CODE, "mod_delivery_order", "delivery_order_lst", response);
		}catch(FengRuntimeException e){
			FengUtil.OUTROLLBACK(e.getMsg(),Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK("异常",Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法  temp
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcDeliveryOrder1") 
	@Transactional
	public void modYcDeliveryOrder1(HttpServletRequest request,HttpServletResponse response,YcDeliveryOrder ycdo) {  
		try{
			//经销商
			String deliveryNo=ycdo.getDeliveryNo();
			//获取货物id
			String goodsIds=StrUtil.getString(request.getParameter("goodsId"), "");
			//将取消配送的货物出库状态改为未出库：0若并未对货物信息作出改动，则无需对货物数据作出改动
			//要被取消配送的货物id，
			String aGoodsIds=StrUtil.getString(request.getParameter("afterGoodsId"), "");
			if(!StrUtil.equalsString(goodsIds,aGoodsIds)){
				for(String aid :aGoodsIds.split(",")){
					YcGoods yg=new YcGoods();
					yg.setId(new BigInteger(aid));
					yg.setOutStorageStatus(0);
					//修改状态为未出库
					iYcGoodsService.modSingleInfo(yg);
					//将已经取消的货物从订单货物表里清除
					YcOrderGoods yog=new YcOrderGoods();
					yog.setDeliveryNo(deliveryNo);
					yog.setZoneGoodsId(new BigInteger(aid));
					iYcOrderGoodsService.delSingleInfo(yog);
				}
				//
				for(String id:goodsIds.split(",")){
					YcOrderGoods yog=new YcOrderGoods();
					yog.setDeliveryNo(deliveryNo);
					//返货ids集合，下标0为货物的id，1为货物所属的运单号
					String[] ids=id.split("_");
					BigInteger bid=new BigInteger(ids[0]);
					String wayNo=ids[1];
					yog.setZoneGoodsId(bid);
					yog.setWayBillNo(wayNo);
					//添加订单货物信息
					iYcOrderGoodsService.addSingleInfo(yog);
					YcGoods yg=new YcGoods();
					yg.setId(bid);
					yg.setOutStorageStatus(1);
					iYcGoodsService.modSingleInfo(yg);
					//这里开始修改运单的状态为在途
				}
			}
			//添加配送单信息
			ycdo.setSumCost(ycdo.getInstallCost());
			//ycdo.setPaidCost(f);
			iYcDeliveryOrderService.modSingleInfo(ycdo);
			FengUtil.OUTADDSUCCESS("", Constant.RESULT_SUCCESS_CODE, "mod_delivery_order", "delivery_order_lst", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK("修改失败!",Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcDeliveryOrder") 
	public void delYcDeliveryOrder(HttpServletRequest request,HttpServletResponse response) {  
		
	} 
	/** 
	 * 到添加页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toAddYcDeliveryOrderPage") 
	public String toAddYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		SysUser su=new SysUser();
		su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
		if(su==null){
			FengUtil.RuntimeExceptionFeng("你还未登录..");
			//return "redirect:/login";
		}
		PlatformUser pu=new PlatformUser();
		pu.setUserType(12);
		pu.setBranchNo(su.getBranchNo());
		String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
		pu.setBranchNo(branchNo);
		List<PlatformUser> lst=pus.getAllUser(pu);
		request.setAttribute("userList", lst);
		return "/Clound/DeliveryOrder/add_delivery_order";
	} 
	/** 
	 * 到修改页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toModYcDeliveryOrderPage") 
	public String toModYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/DeliveryOrder/mod_delivery_order";
	} 
	/** 
	 * 到详情页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toDetYcDeliveryOrderPage") 
	public String toDetYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/DeliveryOrder/det_order";
	} 
	/** 
	 * 到列表页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toListYcDeliveryOrderPage") 
	public String toListYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/DeliveryOrder/DeliveryOrder";
	} 
	
	
	
	/** 
	* 修改评价方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcDeliveryOrderEvaluate") 
	public void modYcDeliveryOrderEvaluate(HttpServletRequest request,HttpServletResponse response,YcDeliveryOrder ycdo,String type) {  
		try{
			
			//修改评价
			if("mod".equals(type)){
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(ycdo.getDeliveryNo());
				ydo.setEvaluateStatus(0);
				ydo = iYcDeliveryOrderService.getSingleInfo(ydo);
				if(ydo==null){
					FengUtil.RuntimeExceptionFeng("没有未评价的订单..");
				}
				ycdo.setEvaluateStatus(1);//已评价
				ycdo.setDeliveryNo(ycdo.getDeliveryNo());
				ycdo.setEvaluateTime(DateUtil.getDateTimeFormatString());
				Integer i = iYcDeliveryOrderService.modSingleInfo(ycdo);
				if(i>0){
					FengUtil.OUTADDSUCCESS("我们已收到您的评价...", Constant.RESULT_SUCCESS_CODE, "mod_InstallEvaluate", "install_evaluate_lst", response);
				}else{
					FengUtil.OUTADDERROR("评价系统出现异常...", Constant.RESULT_ERROR_CODE, response);
				}
			}
			//删除评价（修改评价状态）
			if("del".equals(type)){
				String ids = request.getParameter("ids");
				String[] str = ids.split(",");
				List<BigInteger> list = new ArrayList<BigInteger>();
				for(int i=0;i<str.length;i++){
					list.add(new BigInteger(str[i]));
				}
				Integer i = iYcDeliveryOrderService.modEvaluateStatus(list);
				if(i>0){
					FengUtil.OUTADDERROR("删除成功", Constant.RESULT_SUCCESS_CODE, response);
				}else{
					FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage().length()>20?"异常":e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/**
	 * 前往评价页
	 * Author:luojing
	 * 2016年6月16日 上午9:23:59
	 */
	@RequestMapping("toInstallEvaluate")
	public String toInstallEvaluate(){
		return "/Clound/InstallEvaluate/install_evaluate";
	}
	/**
	 * 前往评价修改
	 * Author:luojing
	 * 2016年6月16日 上午9:23:59
	 */
	@RequestMapping("toModInstallEvaluate")
	public String toModInstallEvaluate(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", ycdo.getId());
		return "/Clound/InstallEvaluate/mod_install_evaluate";
	}
	/**
	 * 前往报表页面
	 * Author:luojing
	 * 2016年6月16日 上午9:23:59
	 */
	@RequestMapping("toChargePage")
	public String toChargePage(YcDeliveryOrder ycdo,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", ycdo.getId());
		return "/Clound/Chart/Chart";
	}
	
	
}
