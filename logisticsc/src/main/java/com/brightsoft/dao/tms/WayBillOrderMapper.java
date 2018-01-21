package com.brightsoft.dao.tms;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.ReceiptBillSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.model.WayBillOrder;

public interface WayBillOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillOrder record);

    int insertSelective(WayBillOrder record);

    WayBillOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillOrder record);

    int updateByPrimaryKey(WayBillOrder record);
    
    /*List<WayBillOrder> selectByParams(Map<String, Object> parmas);
    
    int selectByParamsCount(Map<String, Object> parmas);*/
    
    List<WayBillOrder> selectByWayBillNumbers(List<String> list);
    
    int updateStatusByWayBillNumber(Map<String, Object> params);
    
    int updateStatusByWayBillNumbers(@Param("list") List<String> wayBillNumbers, @Param("status") Integer status);
    
    List<WayBillOrder> selectStowageWayBill(@Param("list") Set<String> wayBillNumbers, @Param("status") Integer status, @Param("startOutlets") Long startOutlets, @Param("targetOutlets") Long targetOutlets);
    
    int updateReceiveTypeByWayBillNumbers(@Param("list") List<String> wayBillNumbers, @Param("receiveType") Integer receiveType);
    
    WayBillOrder selectByWayBillNumber(String wayBillNumber);
    
    int update2close(List<WayBillOrder> wayBillOrders);
    
    int updateByWayBillNumberSelective(WayBillOrder record);
    
    List<WayBillOrder> selectByBaseParmas(SearchParams params);
    
    int selectByBaseParamsCount(SearchParams params);
    
    /**
     * 查询自提/送货上门运单
     * @param params
     * @return
     */
    public List<WayBillOrder> selectByReceiveTypeAndCondition(WayBillSearchParams params);
    
    public int selectByReceiveTypeAndConditionCount(WayBillSearchParams params);
    
    // -------------------------------------------------------------------
    
    public List<WayBillOrder> selectNewByParams(WayBillSearchParams params);
    public List<WayBillOrder> selectNewByParamsEnd(WayBillSearchParams params);
    public int selectNewByParamsCount(WayBillSearchParams params);
    public int selectNewByParamsCountEnd(WayBillSearchParams params);
    public List<WayBillOrder> selectNewExportByParams(WayBillSearchParams params);
    
    public List<WayBillOrder> selectExportReceiptByParams(ReceiptBillSearchParams params);
    
    public double selectNewByParamsTotal(WayBillSearchParams params);
    public double selectNewByParamsTotalEnd(WayBillSearchParams params);
    public List<WayBillOrder> selectTransferWayBillByParams(WayBillSearchParams params);
    
    public int selectTransferWayBillByParamsCount(WayBillSearchParams params);
    
    public List<WayBillOrder> selectWayBillFreightList(WayBillSearchParams params);
    
    public int selectWayBillFreightListCount(WayBillSearchParams params);
    
    public List<WayBillOrder> selectSignWayBillList(WayBillSearchParams params);
    
    public int selectSignWayBillListCount(WayBillSearchParams params);
}