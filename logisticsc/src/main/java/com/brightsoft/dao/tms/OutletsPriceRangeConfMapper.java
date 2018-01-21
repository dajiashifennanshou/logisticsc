package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.OutletsPriceRangeConf;
import com.brightsoft.utils.Page;

public interface OutletsPriceRangeConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutletsPriceRangeConf outletsPriceRangeConf);

    int insertSelective(OutletsPriceRangeConf record);

    OutletsPriceRangeConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutletsPriceRangeConf outletsPriceRangeConf);

    int updateByPrimaryKey(OutletsPriceRangeConf record);

    int countRows(long outletsId);
    
    List<OutletsPriceRangeConf> selectByOutletsId(@Param("page")Page<?> page,@Param("outletsId")long outletsId);
    
    List<OutletsPriceRangeConf> selectNewByOutletsId(Long outletsId);
    
    int deleteBatch(List<Long> ids);
    
    /**
     * 网点获取网点价格范围
     * @param searchParams
     * @param page
     * @param outletsId
     * @return
     */
    List<OutletsPriceRangeConf> selectByOutletsIdAndCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("outletsId")Long outletsId);
    /**
     * 网点获取价格范围总记录数
     * @param searchParams
     * @param outletsId
     * @return
     */
    int countRowsByOutletsId(@Param("searchParams")SearchParams searchParams,@Param("outletsId")Long outletsId);
    /**
     * 专线获取网点价格范围
     * @param searchParams
     * @param page
     * @param companyId
     * @return
     */
    List<OutletsPriceRangeConf> selectByCompanyIdAndCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("companyId")Long companyId);
    /**
     * 专线获取网点价格范围记录总数
     * @param searchParams
     * @param companyId
     * @return
     */
    int countRowsByCompanyId(@Param("searchParams")SearchParams searchParams,@Param("companyId")Long companyId);
    
    /**
     * 货运交易系统
     * 获取提送货信息
     * @param searchParams
     * @param page
     * @return
     */
    List<OutletsPriceRangeConf> selectOutletsConfItems(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    
    /**
     * 运营平添
     * 获取提送货信息记录数
     * @param searchParams
     * @return
     */
    int countRows4OutletsConfItems(SearchParams searchParams);
    
    OutletsPriceRangeConf selectByOutletsIdAndCounty(@Param("outletsId") Long outletsId, @Param("county") String county);
}