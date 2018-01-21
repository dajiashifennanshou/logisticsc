package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface LineInfoMapper {
	
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
    List<LineInfo> selectByOutletsIdCondition(@Param("lineInfo")LineInfo lineInfo, @Param("page")Page<?> page, @Param("outletsId")Long outletsId);
    
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
    List<LineInfo> selectByCompanyIdCondition(@Param("lineInfo")LineInfo lineInfo, @Param("page")Page<?> page, @Param("companyId")Long companyId);
    /**
     * 专线获取线路信息记录总数
     * @param lineInfo
     * @param companyId
     * @return
     */
    int countRowsByCompanyId(@Param("lineInfo")LineInfo lineInfo, @Param("companyId")Long companyId);
    
    /**
     * 查询线路信息
     * @param lineInfo
     * @param page
     * @return
     */
    List<LineInfo> selectByCondition(@Param("lineInfo")LineInfo lineInfo,@Param("page")Page<?> page,@Param("user")User user);
    
    /**
     * 获取线路记录总数
     * @param lineInfo
     * @param user
     * @return
     */
    int countRows(@Param("lineInfo")LineInfo lineInfo,@Param("user")User user);
    
    /**
     * 批量删除线路
     * @param idList
     * @return
     */
    int deleteBatch(List<Long> idList);
    
    List<LineInfo> selectByCreatePersonId(long createPersonId);
    
    List<LineInfo> selectByOutletsId(long outletsId);
    
    List<LineInfo> selectByOutletsId4Page(@Param("page")Page<?> page,@Param("outletsId")long outletsId);
    
    int countRowsByOutletsId(long outletsId);
    
    /**
     * 货运交易系统
     * 获取线路信息
     * @param searchParams
     * @param page
     * @return
     */
    List<LineInfo> selectLineItems(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
     
    /**
     * 货运交易系统
     * 获取线路记录数
     * @param searchParams
     * @return
     */
    int countRows4LineItems(@Param("searchParams")SearchParams searchParams);
    
    public List<LineInfo> selectByParams(BaseSearchParams params);
    
    public int selectByParamsCount(BaseSearchParams params);
    
} 