package com.yc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yc.Entity.LineInfo;
import com.yc.Tool.ISqlDao;
import com.yc.Tool.Pager;


public interface LineInfoMapper extends ISqlDao<LineInfo> {
	
	int deleteByPrimaryKey(Long id);

    int insert(LineInfo record);

    int insertSelective(LineInfo record);

    LineInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LineInfo record);

    int updateByPrimaryKey(LineInfo record);
    
    /**
     * 网点获取线路信息
     * @param lineInfo
     * @param page
     * @param outletsId
     * @return
     */
    List<LineInfo> selectByOutletsIdCondition(@Param("lineInfo")LineInfo lineInfo, @Param("page")Pager<?> page, @Param("outletsId")Long outletsId);
    
    /**
     * 网点获取线路信息总数
     * @param lineInfo
     * @param outletsId
     * @return
     */
    int countRowsByOutletsId(@Param("lineInfo")LineInfo lineInfo, @Param("outletsId")Long outletsId);
    /**
     * 专线获取线路信息
     * @param lineInfo
     * @param page
     * @param outletsId
     * @return
     */
    List<LineInfo> selectByCompanyIdCondition(@Param("lineInfo")LineInfo lineInfo, @Param("page")Pager<?> page, @Param("companyId")Long companyId);
    /**
     * 专线获取线路信息记录总数
     * @param lineInfo
     * @param companyId
     * @return
     */
    int countRowsByCompanyId(@Param("lineInfo")LineInfo lineInfo, @Param("companyId")Long companyId);
    

    
    /**
     * 批量删除线路
     * @param idList
     * @return
     */
    int deleteBatch(List<Long> idList);
    
    List<LineInfo> selectByCreatePersonId(long createPersonId);
    
    List<LineInfo> selectByOutletsId(long outletsId);
    
    List<LineInfo> selectByOutletsId4Pager(@Param("page")Pager<?> page,@Param("outletsId")long outletsId);
    
    int countRowsByOutletsId(long outletsId);
    
     
    /**
     * 运营平台
     * 获取线路记录数
     * @param searchParams
     * @return
     */
    
} 