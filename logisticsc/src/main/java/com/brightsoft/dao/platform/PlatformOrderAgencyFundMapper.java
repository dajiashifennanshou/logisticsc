package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformCollectionPayment;
import com.brightsoft.model.PlatformOrderAgencyFund;
import com.brightsoft.utils.Page;

public interface PlatformOrderAgencyFundMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformOrderAgencyFund record);
//
//    int insertSelective(PlatformOrderAgencyFund record);
//
//    PlatformOrderAgencyFund selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformOrderAgencyFund record);
//
//    int updateByPrimaryKey(PlatformOrderAgencyFund record);
	
	/**
	 * 代收货款
	 * @param deliverGoods
	 * @param page
	 * @return
	 */
	public List<PlatformCollectionPayment> selectBySelectedCondition(@Param("collectionPayment") PlatformCollectionPayment collectionPayment ,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param deliverGoods
	 * @return
	 */
    public int countRows(@Param("collectionPayment") PlatformCollectionPayment collectionPayment);
    /**
     * 增加代收货款信息
     * @param agencyFund
     * @return
     */
    public int inserOrderAgencyFund(PlatformOrderAgencyFund agencyFund);
}