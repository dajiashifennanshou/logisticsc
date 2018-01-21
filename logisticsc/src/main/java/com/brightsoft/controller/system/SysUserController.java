package com.brightsoft.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.entity.YcStorageBranch;
import com.brightsoft.model.SysRole;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysUserService;
import com.brightsoft.service.yc.IYcStorageBranchService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.QueryObject;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/system")
public class SysUserController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private IYcStorageBranchService iycBranch;

	/**
	 * 系统人员管理
	 * @return
	 */
	@RequestMapping("/sys_user_list")
	@ResponseBody
	public Result loginUser(Page<SysUser> page) {
		Result ret = new Result();
		
		List<SysUser> list = sysUserService.selectByPage(page);
		ret.setRows(list);
		ret.setResults(sysUserService.getSysUsersCount(page));
		ret.setResult(true);
			return ret;
	}
	
	
	/**
	 * 根据id查询用户
	 * @return
	 */
	@RequestMapping("/selectUserById/{id}")
	public ModelAndView selectUserById(@PathVariable("id")Long id) {
		ModelAndView mv = new ModelAndView();
		
		SysUser sysUser = sysUserService.selectByPrimaryKey(id);
		
		mv.addObject("sysUser", sysUser);
		mv.setViewName("/system/sys_user_detail");
		
		//request.getSession().setAttribute("user", user);
		//Integer.parseInt("asd");
		//System.out.println("cccc");

			return mv;
	}
	
	
	
	/**
	 * 根据id查询用户
	 * @return
	 */
	@RequestMapping(value="/updateUser",method= RequestMethod.POST)
	@ResponseBody
	public Result updateUser(SysUser sysUser) {
		Result ret = new Result();
		if(sysUser.getPassword()==null || sysUser.getPassword().equals("")){
			sysUser.setPassword(null);
		}else{
			sysUser.setPassword(MD5.getMD5Code(sysUser.getPassword()));
		}
		boolean result = sysUserService.updateByPrimaryKey(sysUser);
		if(result){
			ret.setMsg("修改成功");
		}else{
			ret.setMsg("保存失败");
		}
		
		ret.setResult(result);
		return ret;
	}
	
	
	@RequestMapping(value="/addUser",method= RequestMethod.POST)
	@ResponseBody
	public Result addUser(SysUser sysUser) {
		Result ret = new Result();
		//System.out.println(MD5.getMD5Code(sysRole.getRolename()));
		try {
			SysUser sysUser2 = sysUserService.selectByName(sysUser.getUsername());
			if(sysUser2==null){
				sysUser.setPassword(MD5.getMD5Code(sysUser.getPassword()));
				sysUser.setCreattime(new Date());
				sysUser.setAdminstatus((long) Const.STATE_YES);
				sysUserService.insertUserAndRole(sysUser);
				//sysUserService.insert(sysUser);
				ret.setMsg("添加成功");
				ret.setResult(true);
			}else{
				ret.setMsg("用户已经存在");
				ret.setResult(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ret.setResult(false);
		}
			return ret;
	}
	
	
	/**
	 * 前往添加页面
	 * @return
	 */
	@RequestMapping(value="/toAddUserView")
	public String toAddUserView(HttpServletRequest request) {
		List<YcStorageBranch> lst=iycBranch.getBranchNoList();
		request.setAttribute("branchNos", lst);
		return "/system/sys_user_add";
	}
}
