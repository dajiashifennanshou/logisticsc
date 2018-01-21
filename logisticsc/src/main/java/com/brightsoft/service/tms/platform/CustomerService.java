package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.CustomerMapper;
import com.brightsoft.model.Customer;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	/**
	 * 网点获取客户信息
	 * @param customer
	 * @param page
	 * @param outletsId
	 * @return
	 */
	public Result selectByOutletsIdAndCondition(Customer customer,Page<?> page,Long outletsId){
		Result result = new Result();
		int results = customerMapper.countRowsByOutletsId(customer, outletsId);
		List<Customer> rows = customerMapper.selectByOutletsIdAndCondition(customer, page, outletsId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 专线获取客户信息
	 * @param customer
	 * @param page
	 * @param companyId
	 * @return
	 */
	public Result selectByCompanyIdAndCondition(Customer customer,Page<?> page,Long companyId){
		Result result = new Result();
		int results = customerMapper.countRowsByCompanyId(customer, companyId);
		List<Customer> rows = customerMapper.selectByCompanyIdCondition(customer, page, companyId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 网点获取客户信息
	 */
	public Result selectPageByCondition(Page<?> page,Customer customer,User user){
		Result result = new Result();
		int results = customerMapper.countRows(customer,user);
		List<Customer> customers = customerMapper.selectBySelectedCondition(customer, page,user);
		result.setResults(results);
		result.setRows(customers);;
		return result;
	}
	
	/**
	 * 添加客户
	 * @param customer
	 * @return
	 */
	public int insert(Customer customer){
		return customerMapper.insert(customer);
	}
	
	/**
	 * 更新客户信息
	 * @param customer
	 * @return
	 */
	public int updateById(Customer customer){
		return customerMapper.updateByPrimaryKeySelective(customer);
	}
	
	/**
	 * 通过id获取客户信息
	 * @param customerId
	 * @return
	 */
	public Customer selectById(long customerId){
		return customerMapper.selectByPrimaryKey(customerId);
	}
	/**
	 * 删除信息
	 * @param customerIds
	 * @return
	 */
	public int deleteCustomerById(List<Long> customerIds){
		return customerMapper.deleteCustomerById(customerIds);
	}
	
	/**
	 * 查询全部客户详细信息
	 * @return
	 */
	public Result selectCustomerItems(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = customerMapper.countRows4CustomerItems(searchParams);
		List<Customer> rows = customerMapper.selectCustomerItems(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
}
