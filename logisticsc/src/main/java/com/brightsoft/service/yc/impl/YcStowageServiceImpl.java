package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightsoft.Constant.Constant;
import com.brightsoft.dao.yc.IJpushUserInfoDao;
import com.brightsoft.dao.yc.IYcCarManageDao;
import com.brightsoft.dao.yc.IYcDeliveryChargeDao;
import com.brightsoft.dao.yc.IYcDeliveryOrderDao;
import com.brightsoft.dao.yc.IYcEmployeeDao;
import com.brightsoft.dao.yc.IYcStowageDao;
import com.brightsoft.entity.JpushUserInfo;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcCarManage;
import com.brightsoft.entity.YcDeliveryCharge;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.entity.YcStowage;
import com.brightsoft.entity.YcStowageDelivery;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcEmployeeService;
import com.brightsoft.service.yc.IYcStowageDeliveryService;
import com.brightsoft.service.yc.IYcStowageService;
import com.brightsoft.utils.jpush.JpushUtil;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;
/** 
* YcStowage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcStowageServiceImpl implements IYcStowageService { 

	@Autowired
	private IYcStowageDao iYcStowageDao;
	@Autowired
	private IYcCarManageDao iYcCarManageDao ;
	@Autowired
	private IYcEmployeeDao iYcEmployeeDao;
	@Autowired
	private IJpushUserInfoDao iJpushUserInfoDao;
	@Autowired
	private IYcStowageDeliveryService iYcStowageDeliveryService;
	@Autowired
	private IYcDeliveryOrderDao iYcDeliveryOrderDao;
	@Autowired
	private IYcDeliveryChargeDao iYcDeliveryChargeDao;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcStowage getSingleInfo(YcStowage ycstowage) {
		//TODO Auto-generated method stub
		return iYcStowageDao.getSingleInfo(ycstowage);
	}
	/**
	 * 配载单完成
	 * Author:FENG
	 * 2016年7月25日
	 * @return
	 */
	@Transactional
	public ResultEntity stowageOver(YcStowage ys){
		ResultEntity re=new ResultEntity();
		try{
			//前提：包含的所有配送单都已完成
			//步骤：1、将车辆修改为空闲状态
			//步骤：2、将状态修改为已完成
			//1确认订单都已完成
			if(iYcDeliveryOrderDao.selectOrderIsOver(ys.getStowageNo())<1){
				//表示都已完成,修改车辆状态
				if(iYcCarManageDao.modCarStatusByStowage(0, ys.getStowageNo())<1){
					re.setCode(1);
					re.setMsg("修改车辆状态为已完成时,未成功...");
				}
				YcStowage temp=new YcStowage();
				temp.setStowageNo(ys.getStowageNo());
				temp.setStowageStatus(3);
				if(iYcStowageDao.modStatusByNo(temp)<1){
					re.setCode(1);
					re.setMsg("修改配载单状态为已完成时,未成功...");
				}
			}else{
				re.setCode(1);
				re.setMsg("还存在未完成的订单...");
			}
		}catch(Exception e){
			e.printStackTrace();
			re.setCode(1);
			re.setMsg("异常");
			FengUtil.TRANEOLLBACK();
		}
		return re;
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcStowage> getListPagerInfo(Pager<YcStowage> pager,YcStowage ycstowage) {
		//TODO Auto-generated method stub
		Integer sum=iYcStowageDao.getSumCount(ycstowage);
		Map<String,Object> map=pager.getElestMap(ycstowage);
		List<YcStowage> lst=new ArrayList<YcStowage>();
		for(YcStowage ys:iYcStowageDao.getListPagerInfo(map)){
			 List<YcEmployee> lste= iYcEmployeeDao.getEmployeeByCarNo(ys.getCarNo(), ycstowage.getBranchNo());
			 String val="";
			 for(YcEmployee ye:lste){
				 val+=ye.getEmployeeName()+"-"+ye.getPhone()+"</br>";
			 }
			 ys.setDriverInfo(val);
			 lst.add(ys);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	public Integer delSingleInfo(YcStowage ycstowage) {
		//TODO Auto-generated method stub
		Integer result=iYcStowageDao.delSingleInfo(ycstowage);
		if(result<=0){
			FengUtil.RuntimeExceptionFeng("删除异常");
		}
		return result;
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcStowage ycstowage) {
		//TODO Auto-generated method stub
		return iYcStowageDao.getSumCount(ycstowage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcStowage ycstowage) {
		//TODO Auto-generated method stub
		ycstowage.setUpdateTime(DateUtil.getDateTimeFormatString());
		ycstowage.setUpdateUser(Constant.SYSTEM_OPERSTION_PERSON);
		Integer result=iYcStowageDao.modSingleInfo(ycstowage);
		if(result<=0){
			FengUtil.RuntimeExceptionFeng("修改异常..");
		}
		return result;
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	 * @throws FengRuntimeException 
	*/ 
	@Transactional
	public ResultEntity addStowageInfo(HttpServletRequest request,YcStowage ys) throws FengRuntimeException {
		ResultEntity re=new ResultEntity();
		try{
			SysUser su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				throw new FengRuntimeException("你还未登录..");
			}
			ys.setCreateUser(su.getRealname());
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			ys.setBranchNo(branchNo);
			ys.setCreateUser(su.getRealname());
			//生成配载单号
			String StowageNo=FengUtil.PZ_Number();
			//根据车牌号和当前云仓编号获取配送费
			YcDeliveryCharge ydc=new YcDeliveryCharge();
			ydc=iYcDeliveryChargeDao.getDeliveryCostByCarNo(ys.getCarNo(),Constant.NOW_BRANCHNO);
			//获取配送单id
			String deliverNos=StrUtil.getString(request.getParameter("deliveryNo"), "");
			String[] deliverNo=deliverNos.split(",");
			for(String de:deliverNo){
				String[] des=de.split("_");
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(des[0]);
				//修改配送费
				if(ydc!=null){
					ydo.setDeliveryCost(ydc.getDeliveryCost()==null?0:ydc.getDeliveryCost());
				}else{
					throw new FengRuntimeException("你未对此车型添加配送费用...");
				}
				ydo.setOrderStatus(1);
				//修改订单状态
				if(iYcDeliveryOrderDao.modOrderByNo(ydo)<1){
					throw new FengRuntimeException("修改订单状态时失败...");
				}
				//根据配送id获取安装工人id
				String installerId=request.getParameter("installer_"+des[0]);
				YcStowageDelivery ysd=new YcStowageDelivery();
				ysd.setDeliverNo(des[0]);
				ysd.setDealerId(new BigInteger(des[1]));
				ysd.setStowageNo(StowageNo);
				//设置安装工人信息
				ysd.setEmployeeId(installerId);
				//添加配载单配送单关联表信息,在添加的时候同事修改员工状态为工作中
				if(iYcStowageDeliveryService.addSingleInfo(ysd)<1){
					throw new FengRuntimeException("添加配载单时失败...");
				}
			}
			ys.setStowageNo(StowageNo);
			//TODO Auto-generated method stub
			ys.setStowageStatus(0);
			ys.setCreateUser(su.getRealname());
			ys.setCreateTime(DateUtil.getDateTimeFormatString());
			//修改车辆状态为正在使用
			YcCarManage ycm=new YcCarManage();
			ycm.setCarNo(ys.getCarNo());
			ycm.setCarStatus(1);
			Integer i=iYcCarManageDao.modCarByNo(ycm);
			if(i<1){
				throw new FengRuntimeException("修改车辆状态异常!");
			}
			Integer result=iYcStowageDao.addSingleInfo(ys);
			if(result<=0){
				throw new FengRuntimeException("添加配载单时出现异常!");
			}
			re.setMsg("成功..");
		}catch(FengRuntimeException e){
			re.setCode(1);
			re.setMsg(e.getMsg());
		}catch(Exception e){
			re.setCode(1);
			re.setMsg("异常..");
		}
		return re;
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		Integer i=iYcStowageDao.delSelect(list);
		if(i<=0){
			FengUtil.RuntimeExceptionFeng("删除失败..");
		}
		return i;
	}
	
	public List<YcStowage> getAllStowageList(YcStowage ys) {
		// TODO Auto-generated method stub
		return iYcStowageDao.getAllStowageList(ys);
	}
	
	public Integer modStatusByNo(YcStowage ys) {
		// TODO Auto-generated method stub
		Integer result=iYcStowageDao.modStatusByNo(ys);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("修改配载单状态时失败!!");
		}else{
			//推送
			YcStowageDelivery sd = new YcStowageDelivery();
			sd.setStowageNo(ys.getStowageNo());
			List<YcStowageDelivery> list =  iYcStowageDeliveryService.getYcDeliveryOrderAllList(sd);
			if(!list.isEmpty()){
				String [] str = new String[list.size()];
				for(int i=0;i<list.size();i++){
					//得到注册ID
					JpushUserInfo juser = iJpushUserInfoDao.getSingleInfo(new JpushUserInfo(list.get(i).getDealerId()));
					if(StrUtil.VString(juser.getRegisId())){
						str[i]=juser.getRegisId();
					}
				}
				JpushUtil.sendMessage(str, "您的订单有更新");
			}
		}
		return result;
	}
	public Integer addSingleInfo(YcStowage t) {
		// TODO Auto-generated method stub
		return iYcStowageDao.addSingleInfo(t);
	}
	
}
