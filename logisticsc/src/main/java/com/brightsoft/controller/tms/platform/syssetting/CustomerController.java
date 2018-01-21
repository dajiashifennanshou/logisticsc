package com.brightsoft.controller.tms.platform.syssetting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.Customer;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.CustomerService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	/**
	 * 跳转到客户信息页面
	 * @return
	 */
	@RequestMapping("/list")
	public String toListCustomer(){
		return "/tms/platform/systemmanager/customerinfo/listcustomerinfo";
	}
	/**
	 * 查询客户信息
	 * @param page
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result searchCustomerInfo(Page<?> page,Customer customer,
			HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			if(user.getOutletsId()!=null){
				result = customerService.selectByOutletsIdAndCondition(customer, page, user.getOutletsId());
			}else{
				result = customerService.selectByCompanyIdAndCondition(customer, page, user.getCompanyId());
			}
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 添加客户
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping("/insert.shtml")
	@ResponseBody
	public Result insertCustomer(Customer customer,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		customer.setCompanyId(user.getCompanyId());
		customer.setOutletsId(user.getOutletsId());
		customer.setCreateTime(DateTools.getYMDHMS());
		customer.setStatus(1);
		if(customerService.insert(customer)>0){
			result.setResult(true);
		}
		return result;
	}
	
	
	/**
	 * 更新客户信息
	 * @param customer
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateCustomer(Customer customer){
		Result result = new Result();
		if(customerService.updateById(customer)>0){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 删除客户信息
	 * @param customerIds
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public  Result deleteCustomer(@RequestParam("customerIds[]")String customerIds[]){
		Result result = new Result();
		if(customerIds!=null&&customerIds.length>0){
			List<Long> desList = new ArrayList<Long>();
			for(int i=0,j=customerIds.length;i<j;i++){
				desList.add(Long.parseLong(customerIds[i]));
			}
			if(customerService.deleteCustomerById(desList)>0){
				result.setResult(true);
			}
		}
		return result;
	}
}
