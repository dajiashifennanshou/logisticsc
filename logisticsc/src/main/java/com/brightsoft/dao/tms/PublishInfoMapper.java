package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PublishInfo;
import com.brightsoft.utils.Page;

public interface PublishInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PublishInfo record);

    int insertSelective(PublishInfo record);

    PublishInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublishInfo record);

    int updateByPrimaryKey(PublishInfo record);
    
    /**
     * 查询所有的发布信息
     * @param publishInfo
     * @return
     */
    List<PublishInfo> selectAll(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("publishType")Integer publishType);
    
    /**
     * 查询所有的发布信息总记录数
     * @param publishInfo
     * @return
     */
    int countRows(@Param("searchParams")SearchParams searchParams,@Param("publishType")Integer publishType);
    /**
     * 通过类型获取信息
     * @param searchParams
     * @param page
     * @param type
     * @return
     */
    List<PublishInfo> selectByTypeAndCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("type")Integer type);
    /**
     * 通过类型获取信息记录数
     * @param searchParams
     * @param page
     * @param type
     * @return
     */
    int countRowsByType(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("type")Integer type);
    /**
     * 删除信息
     * @param list
     * @return
     */
    int updateBatch4Delete(List<Long> list);

    /**
     * 查询货运交易系统类型的所有信息
     * 2016年3月22日 下午2:37:35
     * @param publishInfo
     * @param page
     * @return 
     * @author zhouna
     */
	List<PublishInfo> findAllByType(@Param("publishInfo")PublishInfo publishInfo, @Param("page")Page<?> page);

	/**
	 * 查询货运交易系统类型的信息数量
	 * 2016年3月22日 下午2:42:02
	 * @param publishInfo
	 * @return 
	 * @author zhouna
	 */
	int counttypeRows();
	
	List<PublishInfo> selectByType(Integer type);

}