package com.brightsoft.controller.system.tms.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Menu;
import com.brightsoft.model.Role;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.service.tms.platform.CustomerService;
import com.brightsoft.service.tms.platform.DriverInfoService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.MenuService;
import com.brightsoft.service.tms.platform.OutletsConfService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.TmsRoleService;
import com.brightsoft.service.tms.platform.TmsUserService;
import com.brightsoft.service.tms.platform.VehicleInfoService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/sys/tms/member/")
public class MemberManagerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DriverInfoService driverInfoService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	@Autowired
	private OutletsConfService outletsConfService;
	
	@Autowired
	private PlatformUserCompanyService companyService;
	
	@Autowired
	private TmsUserService tmsUserService;
	
	@Autowired
	private TmsRoleService tmsRoleService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	@Autowired
	private OutletsService outletsService;

	/**
	 * 跳转到客户信息页面
	 * @return
	 */
	@RequestMapping("getCustomer")
	public String toCustomer(){
		return "/system/tms/member/customer_items";
	}
	/**
	 * 跳转到车辆信息页面
	 * @return
	 */
	@RequestMapping("getVehicle")
	public String toVehicle(){
		return "/system/tms/member/vehicle_items";
	}
	/**
	 * 跳转到提送货管理页面
	 * @return
	 */
	@RequestMapping("getDeliverPickup")
	public String toDeliverPickup(){
		return "/system/tms/member/deliver_pickup_items";
	}
	/**
	 * 跳转到司机管理页面
	 * @return
	 */
	@RequestMapping("getDriver")
	public String toDriver(){
		return "/system/tms/member/driver_items";
	}
	/**
	 * 跳转到运单费用自定义信息页面
	 * @return
	 */
	@RequestMapping("getWayBillFee")
	public String toWayBillFee(){
		return "/system/tms/member/way_bill_fee_items";
	}
	/**
	 * 跳转到短信设置页面
	 * @return
	 */
	@RequestMapping("getMessage")
	public String toMessage(){
		return "/system/tms/member/message_items";
	}
	/**
	 * 挑转到基础数据信息展示页面
	 * @return
	 */
	@RequestMapping("getGeneralData")
	public String toGeneralData(){
		return "/system/tms/member/general_data_items";
	}
	/**
	 * 跳转到数据字典展示页面
	 * @return
	 */
	@RequestMapping("getDictionary")
	public String toDictionary(){
		return "/system/tms/member/dictionary_items";
	}
	/**
	 * 跳转到线路信息展示页面
	 * @return
	 */
	@RequestMapping("getLine")
	public String toLine(){
		return "/system/tms/member/line_items";
	}
	
	/**
	 * 跳转到普通专线会员管理
	 * @return
	 */
	@RequestMapping("getSpecialLine")
	public String toSpecialLine(){
		return "/system/tms/member/special_line_items";
	}
	
	/**
	 * 跳转到物流提供商会员管理界面
	 * @return
	 */
	@RequestMapping("getLogistics")
	public String toLogistics(){
		return "/system/tms/member/logistics_items";
	}
	
	@RequestMapping("getOutlets")
	public String toOutlets(){
		return "/system/tms/member/outlets_manage";
	}
	
	@RequestMapping("getOutletsUser")
	public String toOutletsUser(){
		return "/system/tms/member/company_user_items";
	}
	
	@RequestMapping("getPermission")
	public String toPermission(){
		return "/system/tms/member/role_menu";
	}
	
	/**
	 * 查询客户信息
	 * @return
	 */
	@RequestMapping("searchOutlets")
	@ResponseBody
	public Result searchOutlets(SearchParams searchParams,Page<?> page){
		Result result = outletsService.selectAllOutlets(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查询客户信息
	 * @return
	 */
	@RequestMapping("searchCustomer")
	@ResponseBody
	public Result searchCustomer(SearchParams searchParams,Page<?> page){
		Result result = customerService.selectCustomerItems(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取司机信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchDriver")
	@ResponseBody
	public Result searchDriver(SearchParams searchParams,Page<?> page){
		Result result = driverInfoService.selectDriverItems(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取线路信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchLine")
	@ResponseBody
	public Result searchLine(SearchParams searchParams,Page<?> page){
		Result result = lineInfoService.selectLineItems(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取车辆信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchVehicle")
	@ResponseBody
	public Result searchVehicle(SearchParams searchParams,Page<?> page){
		Result result = vehicleInfoService.selectVehicleItems(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取提送货信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchDeliverPickup")
	@ResponseBody
	public Result searchDeliverPickup(SearchParams searchParams,Page<?> page){
		Result result = outletsConfService.selectOutletsConfItems(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取专线信息
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("searchLineCompany")
	@ResponseBody
	public Result searchLineCompany(SearchParams searchParams,Page<?> page,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user != null){
			result = companyService.selectCompany(Const.TMS_USER_TYPE_ENTERPRISE,searchParams, page);
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 获取物流提供商信息
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("searchLogistics")
	@ResponseBody
	public Result searchLogisticsCompany(SearchParams searchParams,Page<?> page,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user != null){
			result = companyService.selectCompany(Const.TMS_USER_TYPE_LOGISTICSC,searchParams, page);
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("searchUser")
	@ResponseBody
	public Result searchOutletsUser(SearchParams searchParams,Page<?> page){
		Result result = tmsUserService.selectUser(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取角色
	 * @return
	 */
	@RequestMapping("searchRole")
	@ResponseBody
	public Result searchLogstRole(){
		Result result = new Result();
		List<Role> roleList = tmsRoleService.selectSysRole4Platform();
		result.setRows(roleList);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取菜单
	 * @param roleId
	 * @return
	 */
	@RequestMapping("searchMenu")
	@ResponseBody
	public List<Menu> searchLogstMenu(Long roleId){
		Menu menu =  menuService.selectByRoleId(0l,roleId);
		return menu.getSubMenus();
	}
	
	@RequestMapping(value = "/updateRoleMenu")
	@ResponseBody
	public Result updateRoleMenu( @RequestParam(value = "menus[]")Long[] menus,Long roleId) { 
		Result reusResult = new Result();
		try {
			tmsRoleService.updateRoleMenu(menus, roleId);
			reusResult.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			reusResult.setResult(false);
		}
		return reusResult;
	}
	
	@RequestMapping("/disable")
	@ResponseBody
	public Result disableUser(String loginName){
		Result result = new Result();
		if(tmsUserService.updateUserStatus(loginName, Const.PLATFORMUSER_USERSTATUS_FORBID)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("/enable")
	@ResponseBody
	public Result enableUser(String loginName){
		Result result = new Result();
		if(tmsUserService.updateUserStatus(loginName, Const.PLATFORMUSER_USERSTATUS_ENABLE)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("/addPlatRole")
	@ResponseBody
	public Result addRole(Role role,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		role.setCreatePersonId(user.getId());
		role.setPlatformType(Const.TMS_ROLE_PLATFORM_TYPE_SYSTEM);
		role.setOwnerType(Const.TMS_ROLE_OWNER_SYS_CUSTOMER);
		if(tmsRoleService.insertRole(role)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("/rmvRole")
	@ResponseBody
	public Result removeRole(Long roleId){
		Result result = new Result();
		List<Long> lst = new ArrayList<Long>();
		lst.add(roleId);
		Map<String, Object> map = tmsRoleService.deleteBatch4Sys(lst);
		result.setResult((Boolean)map.get("success"));
		result.setMsg((String)map.get("msg"));
		return result;
	}
	
	@RequestMapping("/toUserManage")
	public String toUserManage(){
		return "/system/tms/member/user_manage";
	}
	
	@RequestMapping("/getAllUserList")
	@ResponseBody
	public Result getAllUser(SearchParams searchParams, Page<?> page){
		Result result = new Result();
		result.setRows(tmsUserService.selectAllUser(searchParams, page));
		result.setResults(tmsUserService.selectAllResultTotal(searchParams));
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("/getUserDetail")
	@ResponseBody
	public Result getUserDetail(String loginName){
		Result result = new Result();
		result.setData(tmsUserService.selectUserDetail(loginName));
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("/toRoleManage")
	public String toRoleManage(){
		return "/system/tms/member/role_manage";
	}
	
	@RequestMapping("/getRoleList")
	@ResponseBody
	public Result getRoleList(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		result.setRows(tmsUserService.selectEnterpriseUserList(searchParams, page));
		result.setResults(tmsUserService.selectEnterPriseUserTotal(searchParams));
		return result;
	}
	
	/**
	 * 根据角色id获取菜单信息
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/searchRoleMenu")
	@ResponseBody
	public List<Menu> searchRoleMenu(Long roleId,HttpSession session){
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		List<Menu> menuList = null;
		if(user != null){
			Menu menu =  menuService.selectByRoleId(user.getId(),roleId);
			if(menu!=null){
				menuList = menu.getSubMenus();
			}
		}
		
		return menuList;
	}
}
