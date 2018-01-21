package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.brightsoft.Constant.Constant;
import com.brightsoft.controller.yc.ValitedLogin;
import com.brightsoft.dao.yc.IYcDeliveryOrderDao;
import com.brightsoft.dao.yc.IYcGoodsDao;
import com.brightsoft.dao.yc.IYcOutGoodsDao;
import com.brightsoft.dao.yc.IYcOutStorageDao;
import com.brightsoft.dao.yc.IYcStowageDao;
import com.brightsoft.entity.JpushUserInfo;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcGoods;
import com.brightsoft.entity.YcOutGoods;
import com.brightsoft.entity.YcOutStorage;
import com.brightsoft.entity.YcStowage;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.jpush.IJpushUserInfoService;
import com.brightsoft.service.yc.IYcOutStorageService;
import com.brightsoft.utils.jpush.JpushUtil;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.LogUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil; 
/** 
* YcOutStorage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcOutStorageServiceImpl implements IYcOutStorageService { 

	@Autowired
	private IYcOutStorageDao iYcOutStorageDao;
	@Autowired
	private IYcGoodsDao iYcgoodsDao;
	@Autowired
	private IYcOutGoodsDao iYcOutGoodsDao ;
	@Autowired
	private IYcStowageDao iYcStowageDao;
	@Autowired
	private IYcDeliveryOrderDao iYcDeliveryOrderDao;
	/**
	 * 推送消息
	 */
	@Autowired
	private IJpushUserInfoService iJpushUserInfoService;	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcOutStorage getSingleInfo(YcOutStorage ycoutstorage) {
		//TODO Auto-generated method stub
		return iYcOutStorageDao.getSingleInfo(ycoutstorage);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcOutStorage> getListPagerInfo(Pager<YcOutStorage> pager,YcOutStorage ycoutstorage) {
		//TODO Auto-generated method stub
		Integer sum=iYcOutStorageDao.getSumCount(ycoutstorage);
		Map<String,Object> map=pager.getElestMap(ycoutstorage);
		pager.setObjectList(iYcOutStorageDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/**
	 * 配载出库
	 * Author:FENG
	 * 2016年7月22日
	 * @param request
	 * @return
	 */
	public ResultEntity outStowage(HttpServletRequest request,YcOutStorage os){
		ResultEntity re=new ResultEntity();
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				re.setCode(1);
				re.setMsg("你还未登录..");
				return re;
			}
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			//出库步骤：修改出库配载单状态为已出库
			//所有关系都靠配载单来关联
			String stowageNo=StrUtil.getString(request.getParameter("stowage"), "");
			YcStowage ys=new YcStowage();
			ys.setStowageNo(stowageNo);
			ys.setStowageStatus(1);
			//修改配载单状态根据配载单号
			iYcStowageDao.modStatusByNo(ys);
			YcDeliveryOrder ydo=new YcDeliveryOrder();
			ydo.setOrderStatus(2);
			ydo.setStowageNo(stowageNo);
			//根据配载单号修改配送单状态
			iYcDeliveryOrderDao.modOrderByStowageNo(ydo);
			os.setBranchNo(branchNo);
			os.setCreateTime(DateUtil.getDateTimeFormatString());
			os.setCreateUser(pu.getRealname());
			os.setStowageNo(stowageNo);
			Integer result=iYcOutStorageDao.addSingleInfo(os);
			//添加出库货物信息
			List<YcGoods> lst= new ArrayList<YcGoods>();
			lst=iYcgoodsDao.getGoodsInfoByStowageNo(os.getStowageNo());
			Integer ayres=0;
			for(YcGoods yg:lst){
				YcOutGoods yog=new YcOutGoods();
				yog.setGoodsCount(yg.getGoodsNum());
				yog.setStowageNo(os.getStowageNo());
				yog.setCreateTime(DateUtil.getDateTimeFormatString());
				yog.setGoodsBrand(yg.getGoodsBrand());
				yog.setGoodsName(yg.getGoodsName());
				yog.setGoodsType(yg.getGoodsType());
				yog.setVolume(yg.getVolume());
				yog.setVender(yg.getVender());
				yog.setWeight(yg.getWeight());
				yog.setPark(yg.getPark());
				yog.setDealerId(yg.getDealerId());
				yog.setWailBillNo(yg.getWayBillNo());
				yog.setCreateTime(DateUtil.getDateTimeFormatString());
				yog.setCreateUser(Constant.SYSTEM_OPERSTION_PERSON);
				ayres+=iYcOutGoodsDao.addSingleInfo(yog);
				try{
					//及时推送出现异常，也不能影响配载
					JpushUserInfo juser = iJpushUserInfoService.getSingleInfo(new JpushUserInfo(yg.getDealerId()));
					JpushUtil.sendMessage(juser.getRegisId(), "您的货物已出库，请前往出库记录查看..");
				}catch(Exception e){
					LogUtil.LogError.error(e);
				}
			}
			if(result<=0){
				FengUtil.ReturnError("添加出库时失败...");
			}
			if(ayres<lst.size()){
				FengUtil.ReturnError("添加出库货物信息时失败!!");
			}
			return re;
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			FengUtil.TRANEOLLBACK();
			return FengUtil.ReturnError("异常");
		}
		
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcOutStorage ycoutstorage) {
		//TODO Auto-generated method stub
		return iYcOutStorageDao.delSingleInfo(ycoutstorage);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcOutStorage ycoutstorage) {
		//TODO Auto-generated method stub
		return iYcOutStorageDao.getSumCount(ycoutstorage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcOutStorage ycoutstorage) {
		//TODO Auto-generated method stub
		return iYcOutStorageDao.modSingleInfo(ycoutstorage);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcOutStorage ycoutstorage) {
		Integer result=iYcOutStorageDao.addSingleInfo(ycoutstorage);
		//添加出库货物信息
		List<YcGoods> lst= new ArrayList<YcGoods>();
		lst=iYcgoodsDao.getGoodsInfoByStowageNo(ycoutstorage.getStowageNo());
		Integer ayres=0;
		for(YcGoods yg:lst){
			YcOutGoods yog=new YcOutGoods();
			yog.setGoodsCount(yg.getGoodsNum());
			yog.setCreateTime(DateUtil.getDateTimeFormatString());
			yog.setGoodsBrand(yg.getGoodsBrand());
			yog.setGoodsName(yg.getGoodsName());
			yog.setGoodsType(yg.getGoodsType());
			yog.setVolume(yg.getVolume());
			yog.setVender(yg.getVender());
			yog.setWeight(yg.getWeight());
			yog.setPark(yg.getPark());
			yog.setDealerId(ycoutstorage.getDealerId());
			yog.setWailBillNo(yg.getWayBillNo());
			yog.setCreateTime(DateUtil.getDateTimeFormatString());
			yog.setCreateUser(Constant.SYSTEM_OPERSTION_PERSON);
			ayres+=iYcOutGoodsDao.addSingleInfo(yog);
		}
		if(result<=0){
			FengUtil.ReturnError("添加出库时失败...");
		}
		if(ayres<lst.size()){
			FengUtil.ReturnError("添加出库货物信息时失败!!");
		}
		//TODO Auto-generated method stub
		return result;
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcOutStorageDao.delSelect(list);
	}
}
