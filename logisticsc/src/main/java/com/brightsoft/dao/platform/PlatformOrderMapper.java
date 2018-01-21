package com.brightsoft.dao.platform;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.OrderSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.StatisticsHelper;
import com.brightsoft.model.PlatformDeliverGoods;
import com.brightsoft.model.PlatformHomeOrderCity;
import com.brightsoft.model.PlatformMineOrder;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.sysVoOrderCount;
import com.brightsoft.utils.Page;

public interface PlatformOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformOrder record);

    int insertSelective(PlatformOrder record);

    PlatformOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformOrder record);

    int updateByPrimaryKey(PlatformOrder record);
    
    List<PlatformOrder> selectByParams(SearchParams params);
    
    int selectByParamsCount(SearchParams params);
    
    PlatformOrder selectByOrderNumber(String orderNumber);
    
    int updateOrderStatusByOrderNumber(Map<String, Object> map);
    
    int updateOrderStatusByWayBillNumber(Map<String, Object> map);
    
    int updateWayBillOrderByOrderNumber(Map<String, Object> map);
    
    int updateByOrderNumberSelective(PlatformOrder platformOrder);
    
    PlatformOrder selectByWayBillNumber(String wayBillNumber);
    
    public PlatformMineOrder selectBankOrder(@Param("orderNumber") String orderNumber);
    
    /**
	 *我要发货
	 * @param deliverGoods
	 * @param page
	 * @return
	 */
	public List<PlatformDeliverGoods> selectBySelectedCondition(@Param("deliverGoods") PlatformDeliverGoods deliverGoods,@Param("page")Page<?> page);
	/**
	 * 物流商店铺线路信息
	 * @param deliverGoods
	 * @param page
	 * @return
	 */
	public List<PlatformDeliverGoods> selectCompanCondition(@Param("deliverGoods") PlatformDeliverGoods deliverGoods,@Param("page")Page<?> page);
	/**
	 * 物流商店铺线路总数
	 * @param deliverGoods
	 * @return
	 */
	public int companCountRows(@Param("deliverGoods") PlatformDeliverGoods deliverGoods);
	/**
	 * 获取总数
	 * @param deliverGoods
	 * @return
	 */
    public int countRows(@Param("deliverGoods") PlatformDeliverGoods deliverGoods);
    
    /**
	 *我的订单
	 * @param deliverGoods
	 * @param page
	 * @return
	 */
	public List<PlatformMineOrder> selectBySelectedConditionOrder(@Param("mineOrder") PlatformMineOrder mineOrder,@Param("page")Page<?> page);
	/**
	 * 获取总数订单
	 * @param deliverGoods
	 * @return
	 */
    public int countRowsOrder(@Param("mineOrder") PlatformMineOrder mineOrder);
    
    /**
     * 获取当前线路订单总数
     * @param id
     * @return
     */
    public int countOrderLine(@Param("id") Long id);
    
    /**
     * 获取订单详情
     * @param id
     * @return
     */
    public PlatformOrder selectById(long id);
    
    /**
     * 订单数量排名
     * @param id
     * @return
     */
    public List<Map<String, Object>> getOrderUserRanking(@Param("loginName")String loginName,@Param("page")Page<?> page);
    /**
     * 订单数量排名
     * @param id
     * @return
     */
    public int getCountOrderUserRanking(@Param("loginName")String loginName);
    
    
    /**
     * 公司接单数量排名
     * @param id
     * @return
     */
    public int getCountOrderCompanyRanking(@Param("companyName")String companyName,@Param("companyCode")String companyCode,@Param("loginName") String loginName);
    /**
     * 公司接单数量排名
     * @param id
     * @return
     */
    public List<Map<String, Object>> getOrderCompanyRanking(@Param("companyName")String companyName,@Param("companyCode")String companyCode,@Param("loginName") String loginName,@Param("page")Page<?> page);

    
    /**
     * 获取热门专线
     * @return
     */
    public List<PlatformOrder> selectHotLogs();
    
    /**
     * 获取最近下单
     * @return
     */
    public List<PlatformOrder> selectOrderRecently();
    
    /**
     * 获取我的订单草稿总数
     * @param id
     * @return
     */
    public int selectOrderisDraft(@Param("userId") Long userId);
    /**
     * 获取 待处理 已完成 已取消 总数
     * @param userId
     * @param state
     * @return
     */
    public int selectOrderState(@Param("userId") Long userId,@Param("state") Integer state);
    /**
     * 获取 确认费用 总数
     * @param userId
     * @return
     */
    public int selectOrderFinalPrice(@Param("userId") Long userId);
    /**
     * 获取 没有投保总数
     * @param userId
     * @return
     */
    public int selectOrderIsInsurance(@Param("userId") Long userId);
    
    public List<PlatformOrder> selectByParamsOfPlatform(OrderSearchParams params);
    
    public int selectByParamsCountOfPlatform(OrderSearchParams params);
    
    /**
     * 统计
     * @return
     */
    public int countOrder();

    /**
     * 运营货运交易系统获取订单按月统计
     * @return
     */
    public List<StatisticsHelper> selectOrderStatistics();
    
    /**
     * 货运交易系统 获取货运交易系统总交易金额
     * @return
     */
    public int selectPlatformOrderfinalPrice();
    /**
     * 货运交易系统 获取订单总数
     * @return
     */
    public int selectOrderCount();
    /**
     * 货运交易系统热门城市
     * @return
     */
    public List<PlatformHomeOrderCity> selectHomeOrderCity();
    /**
     * 根据订单号 获取线路
     * @param orderNumber
     * @return
     */
    public Long selectTmsLineId(@Param("orderNumber") String orderNumber);
    /**
     * 查询需要导出的订单ID
     * @param mineOrder
     * @return
     */
    public List<String> selectOrder(@Param("mineOrder") PlatformMineOrder mineOrder);
    
    public int updateOrderPayment(@Param("orderNumber") String orderNumber);
    
    public int countRowsByLine(@Param("lineId")Long lineId);
    
//    /**
//     * 
//     * 方法描述：更具订单状态获取分账信息
//     * @param searchParams
//     * @param page
//     * @param state
//     * @return
//     * @author dub
//     * @version 2016年5月13日 下午4:25:32
//     */
//    public List<PlatformOrder> selectSubAccountByOrderState(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("state")Integer state);
//
//    /**
//     * 
//     * 方法描述：更具订单状态获取分账信息记录总数
//     * @param searchParams
//     * @param state
//     * @return
//     * @author dub
//     * @version 2016年5月13日 下午5:06:25
//     */
//    public int countSubRowsByOrderState(@Param("searchParams")SearchParams searchParams,@Param("state")Integer state);
      /**
       * 系统订单统计
       * @return
       */
      public List<sysVoOrderCount> syscountOrder();
}