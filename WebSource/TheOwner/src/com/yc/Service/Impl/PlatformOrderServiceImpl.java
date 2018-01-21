package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.yc.Canstant.Constant;
import com.yc.Dao.AdditionalServerConfMapper;
import com.yc.Dao.ILcPlatformOrderAdditionalServerDao;
import com.yc.Dao.ILcPlatformOrderAgencyFundDao;
import com.yc.Dao.ILcPlatformOrderBillDao;
import com.yc.Dao.ILcPlatformOrderFollowDao;
import com.yc.Dao.ILcPlatformUserCommonContactDao;
import com.yc.Dao.LineInfoMapper;
import com.yc.Dao.OutletsInfoMapper;
import com.yc.Dao.OutletsPriceRangeConfMapper;
import com.yc.Dao.PlatformBankPaymentDao;
import com.yc.Dao.PlatformDictionaryMapper;
import com.yc.Dao.PlatformOrderCargoDao;
import com.yc.Dao.PlatformOrderDao;
import com.yc.Dao.PlatformUserCommonDriverDao;
import com.yc.Dao.PlatformUserCompanyDao;
import com.yc.Dao.XZQHInfoDao;
import com.yc.Entity.AdditionalServerConf;
import com.yc.Entity.LcPlatformOrderAdditionalServer;
import com.yc.Entity.LcPlatformOrderAgencyFund;
import com.yc.Entity.LcPlatformOrderBill;
import com.yc.Entity.LcPlatformOrderFollow;
import com.yc.Entity.LcPlatformUserCommonContact;
import com.yc.Entity.LineInfo;
import com.yc.Entity.OutletsInfo;
import com.yc.Entity.OutletsPriceRangeConf;
import com.yc.Entity.PlatformBankPayment;
import com.yc.Entity.PlatformOrder;
import com.yc.Entity.PlatformOrderCargo;
import com.yc.Entity.PlatformUserCommonDriver;
import com.yc.Entity.ResultEntity;
import com.yc.Entity.XZQHInfo;
import com.yc.Service.PlatformOrderService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager; 
/** 
* LcPlatformOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformOrderServiceImpl implements PlatformOrderService { 

	@Autowired
	private PlatformOrderDao orderDao;
	@Autowired
	private PlatformOrderCargoDao orderCargoDao;
	@Autowired
	private PlatformUserCompanyDao companyDao;
	@Autowired
	private XZQHInfoDao zxqhDao;
	@Autowired
	private ILcPlatformOrderFollowDao platformOrderFollowMapper;
	@Autowired
	private ILcPlatformOrderAgencyFundDao agencyFundMapper;
	@Autowired
	private PlatformDictionaryMapper platformDictionaryMapper;
	@Autowired
	private LineInfoMapper lineInfoMapper;
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	@Autowired
	private AdditionalServerConfMapper additionalServerConfMapper;
	@Autowired
	private OutletsPriceRangeConfMapper outletsPriceRangeConfMapper;
	@Autowired
	private ILcPlatformOrderBillDao orderBillMapper;
	@Autowired
	private ILcPlatformOrderAdditionalServerDao platformOrderAdditionalServer;
	@Autowired 
	ILcPlatformUserCommonContactDao platformUserCommonContactDao;
	@Autowired
	private PlatformUserCommonDriverDao platformUserCommonDriverDao;
	@Autowired
	private PlatformBankPaymentDao bankPaymentDao;
	
	
	@Override
	public Pager<PlatformOrder> getPageOrder(Pager<PlatformOrder> pager,PlatformOrder order) {
		// TODO Auto-generated method stub
		Map<String, Object> map = pager.getElestMap(order);
		List<PlatformOrder> list = orderDao.getListPagerInfo(map);
		for(int i=0;i<list.size();i++){
			list.get(i).setCom(companyDao.getCompanyByLineId(list.get(i).getTms_line_id()));//公司信息
			//发货人
			list.get(i).setConsignor_province(zxqhDao.getSingleInfo(new XZQHInfo(list.get(i).getConsignor_province())).getName());
			list.get(i).setConsignor_city(zxqhDao.getSingleInfo(new XZQHInfo(list.get(i).getConsignor_city())).getName());
			list.get(i).setConsignor_county(zxqhDao.getSingleInfo(new XZQHInfo(list.get(i).getConsignor_county())).getName());
			//收货人
			list.get(i).setConsignee_province(zxqhDao.getSingleInfo(new XZQHInfo(list.get(i).getConsignee_province())).getName());
			list.get(i).setConsignee_city(zxqhDao.getSingleInfo(new XZQHInfo(list.get(i).getConsignee_city())).getName());
			list.get(i).setConsignee_county(zxqhDao.getSingleInfo(new XZQHInfo(list.get(i).getConsignee_county())).getName());
			//查询订单是否支付
			PlatformBankPayment payment = new PlatformBankPayment();
			payment.setOrder_number(list.get(i).getOrder_number());
			payment.setOrder_type(Constant.PLATFORM_ORDER_TYPE_2);//运费
			list.get(i).setPayment(bankPaymentDao.getPayState(payment));
		}
		pager.setObjectList(list);
		return pager;
	}
	private static Boolean isSuccess=true;
	@Transactional
	public ResultEntity addPlatFormOrder(HttpServletRequest request,PlatformOrder order,String orderAddServer,String cargos){
		ResultEntity re=new ResultEntity("添加成功..",Constant.APP_SUCCESS);
		PlatformOrder resultOrder=new PlatformOrder();
		try{
			order.setVehicle_type(new BigInteger(7+""));
			if(order.getUser_id()==null){
				FengUtil.RuntimeExceptionFeng("未获取到用户信息..");
			}
			String orderNumber =new Date().getTime()+"";
			order.setOrder_number(orderNumber);
			//System.out.println(cargos+"----");
			Integer orderIdt=orderDao.getNextId();
			if(orderIdt==null){
				orderIdt=1;
			}
			BigInteger orderId=new BigInteger(orderIdt.toString());
			List<PlatformOrderCargo> orderCargos = JSONArray.parseArray(cargos, PlatformOrderCargo.class);
			
			//总返回条数
			Integer cargoResult=0;
			if(orderCargos==null){
				FengUtil.RuntimeExceptionFeng("至少有一条货物...");
			}
			LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(new Long(order.getTms_line_id().toString()));
			if(order.getSend_cargo_type().equals(new BigInteger("1"))){
				if(outletsPriceRangeConfMapper.selectByOutletsIdAndCounty(lineInfo.getStartOutlets(), order.getConsignor_county())==null){
					FengUtil.RuntimeExceptionFeng("不在上门取货区域范围内");
				}
			}
			Double orderSumWeight=0D;
			Double orderSumVolume=0D;
			Double orderSumPrice=0D;
			for(PlatformOrderCargo orderCargo:orderCargos){
				Double weightPrice = 0D;
				//获取件数数量，如果件数没有就获取套数
				Integer number = orderCargo.getNumber();
				if(number == null){
					number = orderCargo.getSet_number();
				}
				Double totalWeight = orderCargo.getSingle_weight();
				//Double totalWeight = orderCargo.getSingle_weight() * number;
				if(totalWeight <= 1){
					weightPrice = totalWeight * lineInfo.getHeavyCargoPriceLow();
				}else if(totalWeight > 1 && totalWeight <= 3){
					weightPrice = totalWeight * lineInfo.getHeavyCargoPriceMid();
				}else{
					weightPrice = totalWeight * lineInfo.getHeavyCargoPriceHigh();
				}
				Double volumePrice = 0D;
				Double totalVolume = orderCargo.getSingle_volume() ;
				if(totalWeight <= 1){
					volumePrice = totalVolume * lineInfo.getBulkyCargoPriceLow();
				}else if(totalWeight > 1 && totalWeight <= 3){
					volumePrice = totalVolume * lineInfo.getBulkyCargoPriceMid();
				}else{
					volumePrice = totalVolume * lineInfo.getBulkyCargoPriceHigh();
				}
				if(weightPrice > volumePrice){
					orderCargo.setCount_cost_mode(0);
				}else{
					orderCargo.setCount_cost_mode(1);
				}
				orderSumPrice+=(weightPrice+volumePrice);
				orderSumWeight+=totalWeight;
				orderSumVolume+=totalVolume;
				orderCargo.setOrder_id(orderId);
				cargoResult+=orderCargoDao.addSingleInfo(orderCargo);
			}
			
			//增值服务
			LcPlatformOrderAdditionalServer additionalServer =JSONArray.parseObject(orderAddServer, LcPlatformOrderAdditionalServer.class);
			Integer isCollectionDelivery = additionalServer.getIs_collection_delivery();
			if(isCollectionDelivery != null && isCollectionDelivery == 1){
				// 用户选择 代收货款 则生成代收货款单
				addAgencyFund(orderNumber, additionalServer.getCollection_delivery_money());
			}
			// 构建账单对象
			LcPlatformOrderBill platformOrderBill=null;
			try{
				platformOrderBill = buildPaltformOrderBill(additionalServer, order);
				//设置费用/
				order.setEstimate_freight(platformOrderBill.getEstimate_freight()); // 预估运费
				order.setTake_cargo_cost(platformOrderBill.getEstimate_take_cargo_cost()); // 预约提货费
				order.setInsurance_money(platformOrderBill.getInsurance()); // 保险费
				//order.setTotal_cost(platformOrderBill.getTotal_cost());
			}catch(FengRuntimeException e){
				e.printErrorInfo();
				FengUtil.RuntimeExceptionFeng(e.getMsg());
			}catch(Exception e){
				e.printStackTrace();
			}
			if(platformOrderBill==null){
				FengUtil.RuntimeExceptionFeng("此线路还未配置增值服务及收费标准...");
			}
			platformOrderBill.setOrder_number(orderNumber);
			platformOrderBill.setIs_payment(1);
			platformOrderBill.setIs_confirm(1);
			//添加账单对象
			orderBillMapper.addSingleInfo(platformOrderBill);
			order.setState(0); // 设置订单状态
			order.setOrder_time(DateUtil.getDateTimeFormatString());;
			order.setEstimate_total(platformOrderBill.getEstimate_total_cost());
			if(order.getSend_cargo_type().equals(1) && order.getPay_type() == 0){
				Double takeCargoCost = platformOrderBill.getEstimate_take_cargo_cost();
				order.setTake_cargo_cost(takeCargoCost); // 设置 提货费
				order.setIs_draft(0); 
			}
			if(platformOrderAdditionalServer.addSingleInfo(additionalServer)<1){
					FengUtil.RuntimeExceptionFeng("添加增值服务时失败");
			}
			
			// 添加 订单跟踪记录
			LcPlatformOrderFollow platformOrderFollow = new LcPlatformOrderFollow();
			platformOrderFollow.setOrder_number(orderNumber);
			platformOrderFollow.setStatus(0);
			platformOrderFollow.setHandle_time(DateUtil.getDateTimeFormatString());
			platformOrderFollow.setHandle_info("预约订单");
			platformOrderFollow.setOperate_person("");
			
			if(platformOrderFollowMapper.addSingleInfo(platformOrderFollow)<1){
				FengUtil.RuntimeExceptionFeng("添加订单跟踪记录时失败..");
			}
			
			if(cargoResult!=orderCargos.size()){
				FengUtil.RuntimeExceptionFeng("添加货物时失败..");
			}
			
			//常用发货人
			
			LcPlatformUserCommonContact commonContact = new LcPlatformUserCommonContact();
			commonContact.setPhone_number(order.getConsignor_phone_number());
			commonContact.setUser_id(order.getUser_id());
			if(platformUserCommonContactDao.getSumCount(commonContact)<1){
				commonContact.setContacts_name(order.getConsignor_name());
				commonContact.setTelephone(order.getConsignor_telephone());
				commonContact.setAddress(order.getConsignor_address());
//				commonContact.setProvince(order.getConsignor_province());
//				commonContact.setCity(order.getConsignor_city());
//				commonContact.setCounty(order.getConsignor_county());
				commonContact.setProvince(zxqhDao.getXZQHIDBYID(order.getConsignor_province()));
				commonContact.setCity(zxqhDao.getXZQHIDBYID(order.getConsignor_city()));
				commonContact.setCounty(zxqhDao.getXZQHIDBYID(order.getConsignor_county()));
				commonContact.setContacts_type(0);
				commonContact.setState(1);
				commonContact.setCreate_time(DateUtil.getDateTimeFormatString());
				commonContact.setUser_id(order.getUser_id());
				if(platformUserCommonContactDao.addSingleInfo(commonContact)<1){
					FengUtil.RuntimeExceptionFeng("添加常用发货人时失败");
				}
			}
			//常用收货人
			LcPlatformUserCommonContact scommonContact = new LcPlatformUserCommonContact();
			scommonContact.setPhone_number(order.getConsignee_phone_number());
			commonContact.setUser_id(order.getUser_id());
			if(platformUserCommonContactDao.getSumCount(scommonContact)<1){
				scommonContact.setContacts_name(order.getConsignee_name());
				scommonContact.setTelephone(order.getConsignee_telephone());
				scommonContact.setAddress(order.getConsignee_address());
//				scommonContact.setProvince(order.getConsignee_province());
//				scommonContact.setCity(order.getConsignee_city());
//				scommonContact.setCounty(order.getConsignee_county());
				scommonContact.setProvince(zxqhDao.getXZQHIDBYID(order.getConsignee_province()));
				scommonContact.setCity(zxqhDao.getXZQHIDBYID(order.getConsignee_city()));
				scommonContact.setCounty(zxqhDao.getXZQHIDBYID(order.getConsignee_county()));
				scommonContact.setContacts_type(1);
				scommonContact.setState(1);
				scommonContact.setCreate_time(DateUtil.getDateTimeFormatString());
				scommonContact.setUser_id(order.getUser_id());;
				if(platformUserCommonContactDao.addSingleInfo(scommonContact)<1){
					FengUtil.RuntimeExceptionFeng("添加常用收货人时失败");
				}
			}
			
			//添加常用司机
			PlatformUserCommonDriver commonDriver = new PlatformUserCommonDriver();
			String driverName = order.getDriver_name();
			String driverPhone = order.getDriver_phone();
			String vehicleNumber = order.getVehicle_number();
			commonDriver.setPhone_number(driverPhone);
			commonDriver.setUser_id(order.getUser_id());
			if(platformUserCommonDriverDao.getSumCount(commonDriver)<1){
				if(StringUtils.isNotEmpty(driverName) && StringUtils.isNotEmpty(driverPhone) && StringUtils.isNotEmpty(vehicleNumber)){
					commonDriver.setDriver_name(driverName);
					commonDriver.setLicense_number(vehicleNumber);
					commonDriver.setVehicle_type(order.getVehicle_type() + "");
					commonDriver.setUser_id(order.getUser_id());
					if(platformUserCommonDriverDao.addSingleInfo(commonDriver)<1){
						FengUtil.RuntimeExceptionFeng("添加常用司机时失败..");
					}
				}
			}
			//order.setIs_delete(0);
			order.setIs_draft(0);
			order.setIs_payment(1);
			order.setTotal_volume(orderSumVolume);
			order.setTotal_weight(orderSumWeight);
			order.setTotal_worth(convertDouble2Decimal(orderSumPrice));
			order.setOrder_time(DateUtil.getDateTimeFormatString());
			order.setState(0); // 设置订单状态
			resultOrder.setOrder_number(order.getOrder_number());
			resultOrder.setTake_cargo_cost(order.getTake_cargo_cost());
			re.setData(resultOrder);
			//order.setTake_cargo_cost(0.00);
			if(orderDao.addSingleInfo(order)<1){
				FengUtil.RuntimeExceptionFeng("添加订单信息时失败..");
			}
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			re.setCode(Constant.APP_ERROR);
			re.setMsg(e.getMsg());
			FengUtil.TRANEOLLBACK();
		}catch(Exception e){
			FengUtil.TRANEOLLBACK();
			re.setCode(Constant.APP_ERROR);
			re.setMsg("异常");
			e.printStackTrace();
		}
		return re;
	}
	
	
	
	/**
	 * 构建账单对象
	 * @param additionalServer 增值服务对象
	 * @param platformOrder 订单对象
	 * @return
	 */
	private LcPlatformOrderBill buildPaltformOrderBill(LcPlatformOrderAdditionalServer additionalServer, PlatformOrder platformOrder){
		LcPlatformOrderBill platformOrderBill = new LcPlatformOrderBill();
		
			// 计算运费
			Double freight = 0.0;
	
			LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(new Long(platformOrder.getTms_line_id().toString()));
			
			Double weight_freight = 0D;
			
			double totalWeight = platformOrder.getEstimate_weight();
			if(totalWeight <= 1){
				weight_freight = lineInfo.getHeavyCargoPriceLow() * totalWeight;
			}else if(totalWeight > 1 && totalWeight <= 3){
				weight_freight = lineInfo.getHeavyCargoPriceMid() * totalWeight;
			}else{
				weight_freight = lineInfo.getHeavyCargoPriceHigh() * totalWeight;
			}
			Double volume_freight = 0D;
			double totalVolume = platformOrder.getEstimate_volume();
			if(totalVolume <= 1){
				volume_freight = lineInfo.getBulkyCargoPriceLow() * totalVolume;
			}else if(totalVolume > 1 && totalVolume <= 3){
				volume_freight = lineInfo.getBulkyCargoPriceMid() * totalVolume;
			}else{
				volume_freight = lineInfo.getBulkyCargoPriceHigh() * totalVolume;
			}
	
			if(weight_freight > volume_freight){
				freight = weight_freight;
			}else{
				freight = volume_freight;
			}
			
			OutletsInfo outletsInfo = outletsInfoMapper.selectByPrimaryKey(lineInfo.getOutletsId());
			
			AdditionalServerConf additionalServerConf = additionalServerConfMapper.selectByOutletsId(outletsInfo.getId());
			if(additionalServerConf==null){
				FengUtil.RuntimeExceptionFeng("未对此线路做费用配置...");
			}
			// 装货费用 
			Double loadCargoPrice = 0.0;
			Integer isLoadCargo = additionalServer.getIs_load_cargo();
			if(isLoadCargo != null && isLoadCargo == 1){ // 选择装货
				// 计算不上楼费用
				Double weigthCargoPrice = additionalServerConf.getHeavyCargo() * totalWeight;
				Double volumeCargoPrice = additionalServerConf.getBulkyCargo() * totalVolume;
				Double lowestPrice = additionalServerConf.getNoUpstairsLowPrice(); // 获取TMS增值服务配置表的最低价格
				Double[] price = {weigthCargoPrice,volumeCargoPrice,lowestPrice};
				loadCargoPrice = getDoubleMax(price);
				// 如果上楼 计算上楼收费
				Integer loadCargoFloor = additionalServer.getLoad_cargo_floor();
				if(loadCargoFloor > 0){
					// 判断是否有电梯
					Integer loadCargoIsElevator = additionalServer.getLoad_cargo_is_elevator();
					if(loadCargoIsElevator != null && loadCargoIsElevator == 1){ // 有电梯
						loadCargoPrice += loadCargoPrice * additionalServerConf.getElevatorAdditional() / 100;
					}else{ // 无电梯
						loadCargoPrice += loadCargoPrice * additionalServerConf.getNoElevatorAdditional() * loadCargoFloor / 100;
					}
					double lowestPriceUpstairs = additionalServerConf.getGoUpstairsLowPrice();
					if(lowestPriceUpstairs > loadCargoPrice){
						loadCargoPrice = lowestPriceUpstairs;
					}
				}
			}
			// 卸货费用 
			Double unloadCargoPrice = 0.0;
			Integer isUnLoadCargo = additionalServer.getIs_load_cargo();
			if(isUnLoadCargo != null && isUnLoadCargo == 1){ // 选择卸货
				// 计算不上楼费用
				Double weigthCargoPrice = additionalServerConf.getHeavyCargo() * totalWeight;
				Double volumeCargoPrice = additionalServerConf.getBulkyCargo() * totalVolume;
				Double lowestPrice = additionalServerConf.getNoUpstairsLowPrice(); // 获取TMS增值服务配置表的最低价格
				Double[] price = {weigthCargoPrice,volumeCargoPrice,lowestPrice};
				unloadCargoPrice = getDoubleMax(price);
				// 如果上楼 计算上楼收费
				Integer unloadCargoFloor = additionalServer.getUnload_cargo_floor();
				if(unloadCargoFloor > 0){
					// 判断是否有电梯
					Integer unloadCargoIsElevator = additionalServer.getUnload_cargo_is_elevator();
					if(unloadCargoIsElevator != null && unloadCargoIsElevator == 1){ // 有电梯
						unloadCargoPrice += unloadCargoPrice * additionalServerConf.getElevatorAdditional() / 100;
					}else{ // 无电梯
						unloadCargoPrice += unloadCargoPrice * additionalServerConf.getNoElevatorAdditional() * unloadCargoFloor / 100;
					}
					double lowestPriceUpstairs = additionalServerConf.getGoUpstairsLowPrice();
					if(lowestPriceUpstairs > unloadCargoPrice){
						unloadCargoPrice = lowestPriceUpstairs;
					}
				}
			}
			// 保险费
			Double insurance = 0D;
			//是否专线投保 
			if(platformOrder.getIs_insurance()==0){
				Double insuranceMoney = platformOrder.getInsurance_money();
				if(insuranceMoney != null){
					insurance = insuranceMoney * additionalServerConf.getLineInsuranceRatio();
					insurance = Math.ceil(insurance / 100);
				}
			}
			// 代收货款手续费
			Double agencyFundPoundage = 0D;
			Integer isCollectionDelivery = additionalServer.getIs_collection_delivery();
			if(isCollectionDelivery != null && isCollectionDelivery == 1){
				Double collectionDeliveryMoney = additionalServer.getCollection_delivery_money();
				if(collectionDeliveryMoney != null){
					agencyFundPoundage = collectionDeliveryMoney * additionalServerConf.getCollectionDeliveryRatio() / 100;
				}
			}
			// 提货费
			Double takeCargoCost = 0D;
			if(platformOrder.getSend_cargo_type().equals(new BigInteger("1"))){ // 选择上门取货  计算提货费
				List<OutletsPriceRangeConf> outletsPriceRangeConfs = outletsPriceRangeConfMapper.selectByOutletsId(lineInfo.getStartOutlets());
				if(outletsPriceRangeConfs != null && outletsPriceRangeConfs.size() > 0){
					for (OutletsPriceRangeConf outletsPriceRangeConf : outletsPriceRangeConfs) {
						if(platformOrder.getConsignor_county().equals(outletsPriceRangeConf.getCounty())){
							double weightCostTemp = totalWeight * outletsPriceRangeConf.getWeight();
							double volumeCostTmep = totalVolume * outletsPriceRangeConf.getVolume();
							if(weightCostTemp > volumeCostTmep){
								takeCargoCost = weightCostTemp;
							}else{
								takeCargoCost = volumeCostTmep;
							}
						}
					}
				}
			}
			// 送货费
			Double sendCargoCost = 0D;
			List<OutletsPriceRangeConf> outletsPriceRangeConfs2 = outletsPriceRangeConfMapper.selectByOutletsId(lineInfo.getEndOutlets());
			if(outletsPriceRangeConfs2 != null && outletsPriceRangeConfs2.size() > 0){
				for (OutletsPriceRangeConf outletsPriceRangeConf : outletsPriceRangeConfs2) {
					if(platformOrder.getConsignee_county().equals(outletsPriceRangeConf.getCounty())){
						double weightCostTemp = totalWeight * outletsPriceRangeConf.getWeight();
						double volumeCostTmep = totalVolume * outletsPriceRangeConf.getVolume();
						if(weightCostTemp > volumeCostTmep){
							sendCargoCost = weightCostTemp;
						}else{
							sendCargoCost = volumeCostTmep;
						}
					}
				}
			}
			
			freight = convertDouble2Decimal(freight);
			takeCargoCost = convertDouble2Decimal(takeCargoCost);
			sendCargoCost = convertDouble2Decimal(sendCargoCost);
			loadCargoPrice = convertDouble2Decimal(loadCargoPrice);
			unloadCargoPrice = convertDouble2Decimal(unloadCargoPrice);
			insurance = convertDouble2Decimal(insurance);
			agencyFundPoundage = convertDouble2Decimal(agencyFundPoundage);
	
			Double estimateTotal = freight + takeCargoCost + sendCargoCost + loadCargoPrice + unloadCargoPrice + insurance + agencyFundPoundage;
			
			
			platformOrderBill.setOrder_number(platformOrder.getOrder_number());
			platformOrderBill.setEstimate_freight(freight); // 预估运费
			platformOrderBill.setEstimate_take_cargo_cost(takeCargoCost); // 预约提货费
			platformOrderBill.setEstimate_send_cargo_cost(sendCargoCost); // 送货费
			platformOrderBill.setEstimate_load_cargo_cost(loadCargoPrice); // 装货费
			platformOrderBill.setEstimate_unload_cargo_cost(unloadCargoPrice); // 卸货费
			platformOrderBill.setInsurance(insurance); // 保险费
			platformOrderBill.setAgency_fund_poundage(agencyFundPoundage); // 代收货款 手续费
			platformOrderBill.setEstimate_total_cost(convertDouble2Decimal(estimateTotal));
			platformOrderBill.setIs_confirm(0);
			
		
		return platformOrderBill;
	}
	
	private double getDoubleMax(Double[] d){
		for(int i = 0; i < d.length - 1; i++){
			for (int j = 0; j < d.length - i - 1; j++) {
				if(d[j] < d[j + 1]){
					double temp = d[j];
					d[j] = d[j + 1];
					d[j + 1] = temp;
				}
			}
		}
		return d[0];
	}
	/**
	 * 四舍五入 保留两位小数
	 * @param value
	 * @return
	 */
	private double convertDouble2Decimal(Double value){
		if(value == null){
			return 0D;
		}
		return Double.parseDouble(new java.text.DecimalFormat("#.00").format(value));
	}
	/**
	 * 添加 代收货款信息
	 * @param orderNumber
	 * @param agencyFund
	 */
	private void addAgencyFund(String orderNumber, Double agencyFund){
		if(agencyFund == null){
			return;
		}
		LcPlatformOrderAgencyFund orderAgencyFund = new LcPlatformOrderAgencyFund();
		orderAgencyFund.setOrder_number(orderNumber);
		orderAgencyFund.setAgency_fund(agencyFund);
		orderAgencyFund.setReceived_fund(0D);
		orderAgencyFund.setUncollected_fund(agencyFund);
		orderAgencyFund.setOperate_time(DateUtil.getDateTimeFormatString());
		orderAgencyFund.setState(0);
		agencyFundMapper.addSingleInfo(orderAgencyFund);
	}
	
	@Override
	public PlatformOrder getOrder(PlatformOrder order) {
		// TODO Auto-generated method stub
		PlatformOrder re=new PlatformOrder();
		re=orderDao.getSingleInfo(order);
		re.setConsignor_province(zxqhDao.getSingleInfo(new XZQHInfo(re.getConsignor_province())).getName());
		re.setConsignor_city(zxqhDao.getSingleInfo(new XZQHInfo(re.getConsignor_city())).getName());
		re.setConsignor_county(zxqhDao.getSingleInfo(new XZQHInfo(re.getConsignor_county())).getName());
		//收货人
		re.setConsignee_province(zxqhDao.getSingleInfo(new XZQHInfo(re.getConsignee_province())).getName());
		re.setConsignee_city(zxqhDao.getSingleInfo(new XZQHInfo(re.getConsignee_city())).getName());
		re.setConsignee_county(zxqhDao.getSingleInfo(new XZQHInfo(re.getConsignee_county())).getName());
		
		return re;
	}

	@Override
	public ResultEntity updateOrder(PlatformOrder order) {
		// TODO Auto-generated method stub
		ResultEntity result = new ResultEntity();
		if(orderDao.modSingleInfo(order)>0){
			result.setCode(Constant.APP_SUCCESS);
		}else{
			FengUtil.RuntimeExceptionFeng("订单状态改变失败");
		}
		return result;
	}

	@Override
	public PlatformOrder getSingleInfo(PlatformOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSumCount(PlatformOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<PlatformOrder> getListPagerInfo(Pager<PlatformOrder> pager, PlatformOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delSingleInfo(PlatformOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modSingleInfo(PlatformOrder t) {
		// TODO Auto-generated method stub
		return orderDao.modSingleInfo(t);
	}

	@Override
	public Integer addSingleInfo(PlatformOrder t) {
		// TODO Auto-generated method stub
		return null;
	}

}
