package com.brightsoft.dao.platform;

import com.brightsoft.model.PlatformUserTemporaryCompany;

public interface PlatformUserTemporaryCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUserTemporaryCompany record);

    int insertSelective(PlatformUserTemporaryCompany record);

    PlatformUserTemporaryCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUserTemporaryCompany record);

    int updateByPrimaryKey(PlatformUserTemporaryCompany record);
    
    /**
     * 增加公司信息
     * @param record
     * @return
     */
    public int insertPlatformUserTemporaryCompany(PlatformUserTemporaryCompany record);
    /**
	 *  获取最大ID
	 * @return
	 */
	public Long selectCompanyMax();
	/**
	 * 修改临时表公司信息
	 * @param companyInfo
	 * @return
	 */
	public int updateTemporaryCompany(PlatformUserTemporaryCompany platformUserTemporaryCompany);
}