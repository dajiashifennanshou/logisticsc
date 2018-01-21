package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.VehicleInfo;
import com.brightsoft.utils.Page;

public interface VehicleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VehicleInfo record);

    int insertSelective(VehicleInfo record);

    VehicleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleInfo record);

    int updateByPrimaryKey(VehicleInfo record);
    
    VehicleInfo selectByVehicleNumberAndOutletsId(@Param("vehicleNumber") String vehicleNumber, @Param("outletsId") Long outletsId);
    
    /**
     * 网点获取车辆信息
     * @param vehicleInfo
     * @param page
     * @param outletsId
     * @return
     */
    List<VehicleInfo> selectByOutletsIdAndCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("outletsId")Long outletsId);
    /**
     * 网点获取车辆记录数
     * @param vehicleInfo
     * @param outletsId
     * @return
     */
    int countRowsByOutletsId(@Param("searchParams")SearchParams searchParams,@Param("outletsId")Long outletsId);
    /**
     * 专线获取线路信息
     * @param vehicleInfo
     * @param outletsId
     * @return
     */
    List<VehicleInfo> selectByCompanyIdAndCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("companyId")Long companyId);
    /**
     * 专线获取车辆记录数
     * @param vehicleInfo
     * @param outletsId
     * @return
     */
    int countRowsByCompanyId(@Param("searchParams")SearchParams searchParams,@Param("companyId")Long companyId);
    /**
     * 查询车辆信息
     * @param vehicleInfo
     * @param page
     * @param user
     * @return
     */
//    List<VehicleInfo> selectBySelectedCondition(@Param("vehicleInfo")VehicleInfo vehicleInfo,@Param("page")Page<?> page,@Param("user")User user);
    
    /**
     * 获取结果集总数
     * @param vehicleInfo
     * @param user
     * @return
     */
//    int countRows(@Param("vehicleInfo")VehicleInfo vehicleInfo, @Param("user")User user);
    
    List<VehicleInfo> selectByOutletsId(long outletsId);
    
    VehicleInfo selectVehicleById(long vehicleId);
    
    int deleteVehicleById(List<Long> vehicleId);
    
//    List<VehicleInfo> selectByOutletsId4Page(@Param("page")Page<?> page,@Param("outletsId")long outletsId);
    
    //int countRowsByOutletsId(long outletsId);
    
    /**
     * 货运交易系统
     * 获取车辆信息
     * @param searchParams
     * @param page
     * @return
     */
    List<VehicleInfo> selectVehicleItems(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    
    /**
     * 货运交易系统
     * 获取车辆记录数
     * @param searchParams
     * @return
     */
    int countRows4VehicleItems(@Param("searchParams")SearchParams searchParams);
    
    VehicleInfo selectByOutletsIdAndVehicleNumber(@Param("outletsId") Long outletsId, @Param("vehicleNumber") String vehicleNumber);
}