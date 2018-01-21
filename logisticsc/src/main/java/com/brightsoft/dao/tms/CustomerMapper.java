package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Customer;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    Customer selectByPhoneAndOutletsId(@Param("phone") String phone, @Param("outletsId") Long outletsId);
    
    /**
     * 网点获取线路信息
     * @param customer
     * @param page
     * @param outletsId
     * @return
     */
    List<Customer> selectByOutletsIdAndCondition(@Param("customer")Customer customer, @Param("page")Page<?> page,@Param("outletsId")Long outletsId);
    /**
     * 网点获取线路信息记录数
     * @param customer
     * @param outletsId
     * @return
     */
    int countRowsByOutletsId(@Param("customer")Customer customer, @Param("outletsId")Long outletsId);
    /**
     * 专线获取线路信息
     * @param customer
     * @param page
     * @param CompanyId
     * @return
     */
    List<Customer> selectByCompanyIdCondition(@Param("customer")Customer customer, @Param("page")Page<?> page,@Param("companyId")Long companyId);
    /**
     * 专线获取线路总记录数
     * @param customer
     * @param companyId
     * @return
     */
    int countRowsByCompanyId(@Param("customer")Customer customer, @Param("companyId")Long companyId);
    /**
     * 获取记录总数
     * @param customer
     * @param user
     * @return
     */
	int countRows(@Param("customer")Customer customer, @Param("user")User user);

	/**
	 * 查询客户信息
	 * @param customer
	 * @param page
	 * @param user 
	 * @return
	 */
	List<Customer> selectBySelectedCondition(@Param("customer")Customer customer, @Param("page")Page<?> page, @Param("user")User user);
	
	int deleteCustomerById(List<Long> customerIds);
	/**
	 * 通过outletsId获取客户信息
	 * @param page
	 * @param outletsId
	 * @return
	 */
	List<Customer> selectByOutletsId(@Param("page")Page<?> page,@Param("outletsId")long outletsId);
	/**
	 * 通过outletsId获取记录总数
	 * @param outletsId
	 * @return
	 */
//	int countRowsByOutletsId(long outletsId);
	/**
	 * 查询所有客户详细信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	List<Customer> selectCustomerItems(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
	/**
	 * 获取客户总的记录数
	 * @param searchParams
	 * @return
	 */
	int countRows4CustomerItems(@Param("searchParams")SearchParams searchParams);
}