package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface DriverInfoMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 添加司机信息
     * @param record
     * @return
     */
    int insert(DriverInfo record);

    int insertSelective(DriverInfo record);

    DriverInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DriverInfo record);

    int updateByPrimaryKey(DriverInfo record);
    
    /**
     * 网点获取司机信息
     * @param driverInfo
     * @param page
     * @param outletsId
     * @return
     */
    List<DriverInfo> selectByOutletsIdAndCondition(@Param("driverInfo")DriverInfo driverInfo,@Param("page")Page<?> page,@Param("outletsId")Long outletsId);
    /**
     * 网点获取司机信息记录总数
     * @param driverInfo
     * @param outletsId
     * @return
     */
    int countRowsByOutletsId(@Param("driverInfo")DriverInfo driverInfo,@Param("outletsId")Long outletsId);
    /**
     * 专线获取司机信息
     * @param driverInfo
     * @param page
     * @param companyId
     * @return
     */
    List<DriverInfo> selectByCompanyIdAndCondition(@Param("driverInfo")DriverInfo driverInfo,@Param("page")Page<?> page,@Param("companyId")Long companyId);
    /**
     * 专线获取司机信息记录数
     * @param driverInfo
     * @param companyId
     * @return
     */
    int countRowsByCompanyId(@Param("driverInfo")DriverInfo driverInfo,@Param("companyId")Long companyId);
    /**
     * 查询司机信息
     * @param driverInfo
     * @param page
     * @param user
     * @return
     */
    List<DriverInfo> selectBySelectedCondition(@Param("driverInfo")DriverInfo driverInfo,@Param("page")Page<?> page,@Param("user")User user);
    
    /**
     * 获取结果集总数
     * @param driverInfo
     * @param user
     * @return
     */
    int countRows(@Param("driverInfo")DriverInfo driverInfo, @Param("user")User user);
    
    List<DriverInfo> selectByCreatePersonId(long userId);
    
    int deleteDriverById(List<Long> driverId);
    
    List<DriverInfo> selectByOutletsId(Long outletsId);
    
    List<DriverInfo> selectByCompanyId(Long companyId);
    /**
     * 通过网点Id分页获取司机信息
     * @param page
     * @param outletsId
     * @return
     */
    List<DriverInfo> selectByOutletsId4Page(@Param("page")Page<?> page,@Param("outletsId")Long outletsId);
    /**
     * 通过outletId获取总记录数
     * @param outletsId
     * @return
     */
//    int countRowsByOutletsId(long outletsId);
    /**
     * 货运交易系统
     * 获取司机信息
     * @param searchParams
     * @param page
     * @return
     */
    List<DriverInfo> selectDriverItems(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    /**
     * 货运交易系统
     * 获取司机总记录数
     * @param searchParams
     * @return
     */
    int countRows4DriverItems(@Param("searchParams")SearchParams searchParams);
}