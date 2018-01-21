package com.yc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yc.Entity.OutletsInfo;
import com.yc.Tool.ISqlDao;
import com.yc.Tool.Pager;


public interface OutletsInfoMapper extends ISqlDao<OutletsInfo> {
	
    int deleteByPrimaryKey(Long id);

    int insert(OutletsInfo record);

    int insertSelective(OutletsInfo record);

    OutletsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutletsInfo record);

    int updateByPrimaryKey(OutletsInfo record);
    
    /**
     * 查询用户网点信息
     * @param user
     * @param outletsInfo
     * @param page
     * @return
     */
    /**
     * 平台公司店铺 网点信息
     * @return
     */
    List<OutletsInfo> selectoutletsInfo(@Param("outletsInfo")OutletsInfo outletsInfo,@Param("page")Pager<?> page);
    /**
     * 我要发货 所有网点
     * @param outletsInfo
     * @return
     */
    List<OutletsInfo> selectOutlets(@Param("companyId")Long companyId,@Param("name")String name);
    /**
     * 平台公司店铺 网点总数
     * @param outletsInfo
     * @return
     */
    int countoutletsInfo(@Param("outletsInfo")OutletsInfo outletsInfo);
    /**
     * 获取用户网点记录总数
     * @param outletsInfo
     * @param user
     * @return
     */
    
    /**
     * 批量删除网点
     * @param list
     * @return
     */
    int deleteBatch(List<Long> idList);
    
    /**
     * 通过企业id获取最近一次添加的网点信息
     * @param companyId
     * @return
     */
    String selectSerNumByCompanyId(long companyId);
    
    /**
     * 获取起始网点信息
     * 
     * @return
     */
    
    /**
     * 获取到站网点信息
     * @return
     */
    /**
     * 通过id获取网点信息
     * @param outletsId
     * @return
     */
    OutletsInfo selectById(long outletsId);
    /**
     * 通过id获取网点信息
     * @param outletsId
     * @return
     */
	OutletsInfo selectOutletsById(long outletsId);
	/**
	 * 通过企业id获取网点信息
	 * @param companyId
	 * @return
	 */
	List<OutletsInfo> selectByCompanyId(Long companyId);
	
	OutletsInfo selectOutletsAndCompanyCodeByLineId(Long lineId);
	
	OutletsInfo selectOutletsAndCompanyCodeById(Long id);
	
	OutletsInfo selectOutletsDetailById(Long id);

	/**
	 * 获取所有网点信息
	 * @return
	 */
	List<OutletsInfo> selectAll();
	
	/**
	 * 更改网点状态
	 * @param outletsId
	 * @param status
	 * @return
	 */
	int updateStatus(@Param("outletsId")Long outletsId,@Param("status")Integer status);

	/**通过公司信息查询网点的总数量**/
	
	/**通过公司信息查询网点*/
	
	/** 运营平台：查询网点 */
	
	/** 运营平台：查询总记录数 ***/
	
	/**
	 * 
	 * 方法描述：获取未绑定用户的网点
	 * @return
	 * @author dub
	 * @version 2016年5月14日 下午4:10:04
	 */
	List<OutletsInfo> selectOutletsNotBind(Long companyId);
}