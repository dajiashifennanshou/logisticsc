package com.brightsoft.service.tms.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.tms.RoleMapper;
import com.brightsoft.dao.tms.RoleMenuMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.dao.tms.UserRoleMapper;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.Role;
import com.brightsoft.model.RoleMenu;
import com.brightsoft.model.User;
import com.brightsoft.model.UserRole;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class TmsRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private TmsRoleMenuService tmsRoleMenuService;
	
	@Autowired
	private TmsUserRoleService tmsUserRoleService;
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PlatformUserMapper platformUserMapper;
	
	/**
	 * 添加角色，权限
	 * @param role
	 * @param menuIds
	 * @return
	 */
	public int insert(Role role,List<RoleMenu> list){
		int flag = 0;
		role.setRoleStatus(Const.TMS_ROLE_STATUS_ENABLE);
		if(roleMapper.insert(role)>0){
			for(int i=0;i<list.size();i++){
				list.get(i).setRoleId(role.getId());
			}
			flag = tmsRoleMenuService.insert(list);
		}
		return flag;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public boolean insertRole(Role role){
		boolean flag = false;
		role.setCreateTime(DateTools.getYMDHMS());
		role.setRoleStatus(Const.TMS_ROLE_STATUS_ENABLE);
		if(roleMapper.insert(role)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 更新角色权限
	 * @param role
	 * @param menus
	 * @return
	 */
	public Result updateRoleAndPerm(Role role,String[] menus){
		Result result = new Result();
		//判断当前用户对角色是否可以修改
		Role roleIsCan = roleMapper.selectByPrimaryKey(role.getId());
		if(roleIsCan != null && roleIsCan.getOwnerType() != Const.TMS_ROLE_OWNER_CUSTOMER){
			result.setMsg("你没有权限修改该角色！"); 
			return result;
		}
		//修改角色
		roleMapper.updateByPrimaryKey(role);
		roleMenuMapper.deleteByRoleId(role.getId());
		List<RoleMenu> list = new ArrayList<RoleMenu>();
		for (String menuId : menus) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(Long.parseLong(menuId));
			roleMenu.setRoleId(role.getId());
			list.add(roleMenu);
		}
		roleMenuMapper.insertBatch(list);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 */
	public boolean updateRole(Role role){
		boolean flag = false;
		if(roleMapper.updateByPrimaryKeySelective(role)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取权限信息
	 * @return
	 */
	public Result selectByCondition(Page<?> page,User user,String condition){
		Result result = new Result();
		ArrayList<Role> roleList = new ArrayList<Role>();
		int results = roleMapper.countRows(user.getId(),condition);
		List<Role> roles = roleMapper.selectByCondition(page,user.getId(),condition);
		if(user.getOutletsId()==null){
			List<Role> roleSysOutlets = null;
			if(user.getUserType() == Const.TMS_USER_TYPE_LOGISTICSC){
				roleSysOutlets = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS);
			}else if(user.getUserType() == Const.TMS_USER_TYPE_ENTERPRISE){
				roleSysOutlets = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS);
			}
			List<Role> roleSysKD = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD);
			List<Role> roleSysFinance = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE);
			List<Role> roleSysCustomer = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_CUSTOMER);
			roleList.addAll(roleSysOutlets);
			roleList.addAll(roleSysKD);
			roleList.addAll(roleSysFinance);
			roleList.addAll(roleSysCustomer);
			results += roleSysOutlets.size()+roleSysKD.size()+roleSysFinance.size();
		}else{
			List<Role> roleSysKD = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD);
			List<Role> roleSysFinance = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE);
			List<Role> roleSysCustomer = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_CUSTOMER);
			roleList.addAll(roleSysKD);
			roleList.addAll(roleSysFinance);
			roleList.addAll(roleSysCustomer);
			results += roleSysKD.size()+roleSysFinance.size();
		}
		roleList.addAll(roles);
		result.setResults(results);
		result.setRows(roleList);
		return result;
	}
	/**
	 * tms:删除角色
	 * @param ids
	 * @return
	 */
	public Map<String, Object> deleteBatch(List<Long> ids){
		Map<String, Object> map = new HashMap<String, Object>();
		//判断当前角色是否允许删除
		if(userRoleMapper.countUserByRoleId(ids)>0){
			map.put("success", false);
			map.put("msg", "当前角色不能删除！");
			return map;
		}
		//判断当前用户是可以操作该权限
		for (Long id : ids) {
			Role role = roleMapper.selectByPrimaryKey(id);
			if(role != null 
					&& role.getPlatformType() != Const.TMS_ROLE_PLATFORM_TYPE_CUSTOMER){
				map.put("success", false);
				map.put("msg", "你没有权限删除该角色！");
				return map;
			}
		}
		//删除角色
		tmsRoleMenuService.deleteByRoleId(ids);
		roleMapper.deleteBatch(ids);
		map.put("success", true);
		return map;
	}
	
	/**
	 * 货运交易系统：删除角色
	 * @param ids
	 * @return
	 */
	public Map<String, Object> deleteBatch4Sys(List<Long> ids){
		Map<String, Object> map = new HashMap<String, Object>();
		//判断当前角色是否允许删除
		if(userRoleMapper.countUserByRoleId(ids)>0){
			map.put("success", false);
			map.put("msg", "当前角色不能删除！");
			return map;
		}
		//判断当前用户是可以操作该权限
		for (Long id : ids) {
			Role role = roleMapper.selectByPrimaryKey(id);
			if(role != null){
				if(role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_FINANCE
					|| role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_KD
					|| role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS
					|| role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LOGISTICSC
					|| role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS
					|| role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_SPECIAL_LINE){
					map.put("success", false);
					map.put("msg", "当前角色不能删除！");
					return map;
				}
			}
		}
		//删除角色
		tmsRoleMenuService.deleteByRoleId(ids);
		roleMapper.deleteBatch(ids);
		map.put("success", true);
		return map;
	}
	
	public List<Role> getRole(long userId,Integer userType, Long outletsId){
		ArrayList<Role> roleList = new ArrayList<Role>();
		User user = userMapper.selectManagerUserByOutletsId(outletsId);
		if(userType != null){
			if(Const.TMS_USER_TYPE_LOGISTICSC==userType){// 物流商
				if(user == null){
					roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS));
				}
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_CUSTOMER));
			}else if(Const.TMS_USER_TYPE_ENTERPRISE == userType){ // 专线
				if(user == null){
					roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS));
				}
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_CUSTOMER));
			}else{ // 网点管理员
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
				roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_CUSTOMER));
			}
		}
		roleList.addAll(roleMapper.selectByCreatePersonId(userId));
		return roleList;
	}
	/**
	 * 通过用户名查询角色，权限信息
	 * @param loginName
	 * @return
	 */
	public List<Role> selectByLoginName(String loginName){
		return roleMapper.selectByLoginName(loginName);
	}

	/**
	 * 更新角色状态
	 * @param roleId
	 * @param roleStatus
	 * @return
	 */
	public Result updateRoleStatusById(Long roleId,Integer roleStatus){
		Result result = new Result();
		//判断当前用户对是否可以执行该操作
		Role rlIsCan = roleMapper.selectByPrimaryKey(roleId); 
		if(rlIsCan.getOwnerType() != 5){
			result.setMsg("你没有权限操作该角色！");
			return result;
		}
		//禁用，启用角色
		Role role = new Role();
		role.setId(roleId);
		role.setRoleStatus(roleStatus);
		roleMapper.updateByPrimaryKeySelective(role);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 判断角色是否可以删除
	 * @param idList
	 * @return
	 */
	/*public boolean select4isCanDelRole(List<Long> idList){
		boolean flag = false;
		if(userRoleMapper.countUserByRoleId(idList) == 0){
			flag = true;
		}
		return flag;
	}*/
	
	/**
	 * 改：通过创建人获取角色信息
	 * @param createPersonId
	 * @return
	 */
	public List<Role> selectByCreatePersonId(Long createPersonId,Integer userType){
		ArrayList<Role> roleList = new ArrayList<Role>();
		if(Const.TMS_USER_TYPE_LOGISTICSC==userType){
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS));
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
		}else if(Const.TMS_USER_TYPE_ENTERPRISE == userType){
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS));
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
		}else{
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
			roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
		}
		roleList.addAll(roleMapper.selectByCreatePersonId(createPersonId));
		return roleList;
	}
	
	/**
	 * 货运交易系统获取系统角色
	 * @return
	 */
	public List<Role> selectSysRole4Platform(){
//		ArrayList<Role> roleList = new ArrayList<Role>();
//		roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC));
//		roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_SPECIAL_LINE));
//		roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS));
//		roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_KD));
//		roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_FINANCE));
//		roleList.addAll(roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS));
		List<Role> roleList = roleMapper.selectByPlatformType(Const.TMS_ROLE_PLATFORM_TYPE_SYSTEM);
		return roleList;
	}
	
	/**
	 * 获取系统物流商角色
	 * @return
	 */
	public Role selectSysLogisticsc(){
		List<Role> roleList = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC);
		return roleList!=null&&!roleList.isEmpty()?roleList.get(0):null;
	}
	
	/**
	 * 获取系统专线角色
	 * @return
	 */
	public Role selectSysSpecialLine(){
		List<Role> roleList = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_SPECIAL_LINE);
		return roleList!=null&&!roleList.isEmpty()?roleList.get(0):null;
	}
	
	public boolean updateRoleMenu(Long[] menus,Long roleId){
			roleMenuMapper.deleteByRoleId(roleId);
			List<RoleMenu> list = new ArrayList<RoleMenu>();
			for (long menuId : menus) {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setMenuId(menuId);
				roleMenu.setRoleId(roleId);
				list.add(roleMenu);
			}
			if(roleMenuMapper.insertBatch(list)>0){
				return true;
			}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：获取用户角色
	 * @param userId
	 * @return
	 * @author dub
	 * @version 2016年5月14日 下午3:17:40
	 */
	public List<Role> selectUserRoleByUserId(Long userId){
		return roleMapper.selectByUserId(userId);
	}
	
	/**
	 * 
	 * 方法描述：更具id获取角色
	 * @return
	 * @author dub
	 * @version 2016年6月3日 下午4:33:01
	 */
	public Role selectById(Long roleId){
		return roleMapper.selectByPrimaryKey(roleId);
	}
	
	public void updateUserRole(Long userId,String roleIds){
		userRoleMapper.deleteByUserId(userId);
		if(StringUtils.isNotBlank(roleIds)){
			String[] res = roleIds.split(",");
			for (int i = 0; i < res.length; i++) {
				long roleId = Long.parseLong(res[i]);
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(userId);
				userRoleMapper.insert(userRole);
				 
				Role role = roleMapper.selectByPrimaryKey(roleId);
				if(role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_CUSTOMER
						|| role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS){
					PlatformUser platformUser = new PlatformUser();
					platformUser.setUserType(Const.PLATFORM_USER_TYPE_DOT);
					User user = userMapper.selectByPrimaryKey(userId);
					platformUser.setLoginName(user.getLoginName());
					platformUserMapper.updateUserByLoginName(platformUser);
				}
				
			}
		}
		
	}
	
}
