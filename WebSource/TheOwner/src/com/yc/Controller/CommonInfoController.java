package com.yc.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformCompanyCollect;
import com.yc.Entity.PlatformStoreRecord;
import com.yc.Entity.PlatformUserCommonContact;
import com.yc.Entity.PlatformUserCommonDriver;
import com.yc.Service.PlatformCompanyCollectService;
import com.yc.Service.PlatformStoreRecordService;
import com.yc.Service.PlatformUserCommonContactService;
import com.yc.Service.PlatformUserCommonDriverService;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;

/**
 * 常用联系人、司机、货物、收藏
 * @Author:luojing
 * @2016年7月27日 下午2:19:33
 */
@Controller
public class CommonInfoController {
	
	//联系人
	@Autowired
	private PlatformUserCommonContactService commonContactService;
	//司机
	@Autowired
	private PlatformUserCommonDriverService commonDriverService;
	//收藏线路
	@Autowired
	private PlatformStoreRecordService storeRecordService;
	//物流商收藏
	@Autowired
	private PlatformCompanyCollectService companyCollectService;
	
	/**
	 * 常用联系人（常用发货人、常用收货人）
	 * Author:luojing
	 * 2016年6月28日 下午2:30:51
	 */
	@RequestMapping("app/getCommonContact")
	public void getCommonContact(PlatformUserCommonContact cc,Integer isa,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformUserCommonContact> pager = new Pager<PlatformUserCommonContact>(page, rows);
			pager = commonContactService.getPageCommonContact(pager, cc);
			//0代表获取地址代码，1代表获取汉字
			/*if(isa==1){
				List<PlatformUserCommonContact> s=new ArrayList<>();
				for(PlatformUserCommonContact pucc:pager.getObjectList()){
					pucc.setProvince(zxqhDao.getXZQHIDBYID(pucc.getProvince()));
					pucc.setCounty(zxqhDao.getXZQHIDBYID(pucc.getCounty()));
					pucc.setCity(zxqhDao.getXZQHIDBYID(pucc.getCity()));
					s.add(pucc);
				}
				pager.setObjectList(s);
			}*/
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 添加常用联系人（常用发货人、常用收货人）
	 * Author:luojing
	 * 2016年6月28日 下午2:37:19
	 */
	@RequestMapping("app/addCommonContact")
	public void addCommonContact(PlatformUserCommonContact cc,HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformUserCommonContact c = commonContactService.getCommonContact(cc);
			if(c==null){
				Integer i = commonContactService.addCommonContact(cc);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("添加失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("该联系人已经存在");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 删除常用联系人
	 * Author:luojing
	 * 2016年6月29日 下午3:48:10
	 */
	@RequestMapping("app/delCommonContact")
	public void delCommonContact(PlatformUserCommonContact cc,HttpServletRequest request,HttpServletResponse response){
		try{
			Integer i = commonContactService.delCommonContact(cc);
			if(i>0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.RuntimeExceptionFeng("操作失败");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 查询常用司机
	 * Author:luojing
	 * 2016年6月28日 下午2:34:40
	 */
	@RequestMapping("app/getCommonDriver")
	public void getCommonDriver(PlatformUserCommonDriver cd,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformUserCommonDriver> pager = new Pager<PlatformUserCommonDriver>(page, rows);
			pager = commonDriverService.getPagerCommonDriver(pager, cd);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 添加常用司机
	 * Author:luojing
	 * 2016年6月28日 下午2:37:19
	 */
	@RequestMapping("app/addCommonDriver")
	public void addCommonDriver(PlatformUserCommonDriver driver,HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformUserCommonDriver cd = commonDriverService.getCommonDriver(driver);
			if(cd==null){
				Integer i = commonDriverService.addCommonDriver(driver);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("添加失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("该司机已经存在");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 删除常用司机
	 * Author:luojing
	 * 2016年6月29日 下午3:48:10
	 */
	@RequestMapping("app/delCommonDriver")
	public void delCommonDriver(PlatformUserCommonDriver driver,HttpServletRequest request,HttpServletResponse response){
		try{
			Integer i = commonDriverService.delCommonDriver(driver);
			if(i>0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.RuntimeExceptionFeng("操作失败");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 查询收藏的物流商
	 * Author:luojing
	 * 2016年6月29日 下午3:48:10
	 */
	@RequestMapping("app/getCompanyCollect")
	public void getCompanyCollect(PlatformCompanyCollect cc,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformCompanyCollect> pager = new Pager<PlatformCompanyCollect>(page, rows);
			pager = companyCollectService.getPageCompanyCollect(pager, cc);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 收藏物流商
	 * Author:luojing
	 * 2016年6月29日 下午3:48:10
	 */
	@RequestMapping("app/addCompanyCollect")
	public void addCompanyCollect(PlatformCompanyCollect cc,HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformCompanyCollect c = companyCollectService.getCompanyCollect(cc);
			if(c==null){
				Integer i=companyCollectService.addCompanyCollect(cc);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS,response);
				}else{
					FengUtil.RuntimeExceptionFeng("收藏失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("该物流商已经收藏");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 物流商删除
	 * Author:luojing
	 * 2016年6月29日 下午3:48:10
	 */
	@RequestMapping("app/delCompanyCollect")
	public void delCompanyCollect(PlatformCompanyCollect cc,HttpServletRequest request,HttpServletResponse response){
		try{
			Integer i = companyCollectService.delCompanyCollect(cc);
			if(i>0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.RuntimeExceptionFeng("操作失败");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 批量删除物流商
	 * @Author:luojing
	 * @2016年8月15日 下午5:02:17
	 */
	@RequestMapping("app/multiDelCompanyCollect")
	public void multiDelCompanyCollect(HttpServletRequest request,HttpServletResponse response){
		try{
			String ids = request.getParameter("ids");
			if(!"".equals(ids)){
				Integer i = companyCollectService.multiDelCompanyCollect(ids);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS,response);
				}else{
					FengUtil.RuntimeExceptionFeng("操作失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("获取参数失败");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
	
	/**
	 * 查询收藏(线路)
	 * Author:luojing
	 * 2016年6月28日 下午2:34:40
	 */
	@RequestMapping("app/getCollectInfo")
	public void getCollectInfo(PlatformStoreRecord sr,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformStoreRecord> pager = new Pager<PlatformStoreRecord>(page, rows);
			pager = storeRecordService.getPagerStoreRecord(pager, sr);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 添加收藏(线路)
	 * Author:luojing
	 * 2016年6月28日 下午2:34:40
	 */
	@RequestMapping("app/addCollect")
	public void addCollect(PlatformStoreRecord sr,HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformStoreRecord s = storeRecordService.getStoreRecord(sr);
			if(s==null){
				Integer i = storeRecordService.addStoreRecord(sr);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("收藏失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("该线路已经收藏");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 删除收藏(线路)信息
	 * Author:luojing
	 * 2016年6月29日 下午3:48:10
	 */
	@RequestMapping("app/delCollect")
	public void delCollect(PlatformStoreRecord sr,HttpServletRequest request,HttpServletResponse response){
		try{
			Integer i = storeRecordService.delStoreRecord(sr);
			if(i>0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.RuntimeExceptionFeng("操作失败");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMessage(), response);
		}
	}
	
	/**
	 * 批量删除收藏线路
	 * @Author:luojing
	 * @2016年8月15日 下午5:22:20
	 */
	@RequestMapping("app/multiDelCollect")
	public void multiDelCollect(HttpServletRequest request,HttpServletResponse response){
		try{
			String ids = request.getParameter("ids");
			if(!"".equals(ids)){
				Integer i = storeRecordService.multiDelCollect(ids);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("操作失败");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("获取参数失败");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
}
