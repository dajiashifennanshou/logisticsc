package com.yc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yc.Entity.OutletsPriceRangeConf;
import com.yc.Tool.ISqlDao;


public interface OutletsPriceRangeConfMapper extends ISqlDao<OutletsPriceRangeConf> {
    int deleteByPrimaryKey(Long id);

    int insert(OutletsPriceRangeConf outletsPriceRangeConf);

    int insertSelective(OutletsPriceRangeConf record);

    OutletsPriceRangeConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutletsPriceRangeConf outletsPriceRangeConf);

    int updateByPrimaryKey(OutletsPriceRangeConf record);

    int countRows(long outletsId);
    
    List<OutletsPriceRangeConf> selectByOutletsId(@Param("outletsId")long outletsId);
    
    List<OutletsPriceRangeConf> selectNewByOutletsId(Long outletsId);
    
    int deleteBatch(List<Long> ids);
    
    /**
     * 网点获取网点价格范围
     * @param searchParams
     * @param page
     * @param outletsId
     * @return
     */
    /**
     * 网点获取价格范围总记录数
     * @param searchParams
     * @param outletsId
     * @return
     */
    /**
     * 专线获取网点价格范围
     * @param searchParams
     * @param page
     * @param companyId
     * @return
     */
    /**
     * 专线获取网点价格范围记录总数
     * @param searchParams
     * @param companyId
     * @return
     */
    
    /**
     * 运营平台
     * 获取提送货信息
     * @param searchParams
     * @param page
     * @return
     */
    
    /**
     * 运营平添
     * 获取提送货信息记录数
     * @param searchParams
     * @return
     */
    
    OutletsPriceRangeConf selectByOutletsIdAndCounty(@Param("outletsId") Long outletsId, @Param("county") String county);
}