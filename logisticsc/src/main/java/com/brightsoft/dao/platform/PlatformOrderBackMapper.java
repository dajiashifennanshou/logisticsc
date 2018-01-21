package com.brightsoft.dao.platform;


import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformOrderBack;

public interface PlatformOrderBackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformOrderBack record);

    int insertSelective(PlatformOrderBack record);

    PlatformOrderBack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformOrderBack record);

    int updateByPrimaryKey(PlatformOrderBack record);
    

    
    /**
     * 
     * 方法描述：更新退款 记录状态
     * @param orderNumber
     * @param state
     * @return
     * @author dub
     * @version 2016年5月12日 下午4:25:24
     */
    int updateOrderBackState(@Param("orderNumber")String orderNumber,@Param("state")Integer state);
    
    /**
     * 
     * 方法描述：根据订单号获取退款记录
     * @param orderNumber
     * @return
     * @author dub
     * @version 2016年5月12日 下午5:03:50
     */
    PlatformOrderBack selectByOrderNumber(@Param("orderNumber")String orderNumber);
}