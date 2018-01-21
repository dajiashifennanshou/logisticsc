package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankSplit;
import com.brightsoft.model.platformVoSplitInsurance;
import com.brightsoft.model.platformVoSplitOrder;
import com.brightsoft.model.platformVoSplitPos;
import com.brightsoft.utils.Page;

public interface platformBankSplitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankSplit record);

    int insertSelective(platformBankSplit record);

    platformBankSplit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankSplit record);

    int updateByPrimaryKey(platformBankSplit record);
    
    public platformBankSplit selectBankSplit(@Param("requestid") String requestid);
    
    /**
     * 分账 根据订单状态进行分账
     * @return
     */
    public List<platformVoSplitOrder> selectSplitOrderState(@Param("platformVoSplitOrder") platformVoSplitOrder platformVoSplitOrder,@Param("page")Page<?> page,@Param("statusList") List<Integer> statusList);
    /**
     * 总数
     * @param platformVoSplitOrder
     * @return
     */
    public int countSplitOrderState(@Param("platformVoSplitOrder") platformVoSplitOrder platformVoSplitOrder,@Param("statusList") List<Integer> statusList);
    /**
     * 分账成功数据
     * @param platformVoSplitOrder
     * @param page
     * @param statusList
     * @return
     */
    public List<platformVoSplitOrder> selectBankSplitList(@Param("platformVoSplitOrder") platformVoSplitOrder platformVoSplitOrder,@Param("page")Page<?> page,@Param("statusList") List<Integer> statusList);
    /**
     * 分账成功总数
     * @param platformVoSplitOrder
     * @param statusList
     * @return
     */
    public int countBankSplit(@Param("platformVoSplitOrder") platformVoSplitOrder platformVoSplitOrder,@Param("statusList") List<Integer> statusList);
    /**
     * 保险分账记录
     * @param insurance
     * @param page
     * @return
     */
    public List<platformVoSplitInsurance> selectBankInsurance(@Param("insurance") platformVoSplitInsurance insurance,@Param("page")Page<?> page);
    
    public int countBankInsurance(@Param("insurance") platformVoSplitInsurance insurance);
    
    /**
     * pos机分账
     * @param splitPos
     * @param page
     * @return
     */
    public List<platformVoSplitPos> selectBankPos(@Param("splitPos") platformVoSplitPos splitPos,@Param("page")Page<?> page);
    public int countBankPos(@Param("splitPos") platformVoSplitPos splitPos);
}