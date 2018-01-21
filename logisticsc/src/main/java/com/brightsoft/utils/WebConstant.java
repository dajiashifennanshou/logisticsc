package com.brightsoft.utils;

public interface WebConstant {

	//专线营运系统session key
	String TMS_USER_SESSION_KEY = "tms_user";
	
	//错误信息标志符
	String ERRORMSG = "errormsg";
	
	//分页查询返回结果集标志符
	String PAGE = "page";
	
	//分页查询总页数
	String TOTALPAGE = "totalPage";
	
	//结果集
	String ROWS = "rows";
	
	//总记录数
	String RESULTS = "results";
	
	/***** 不对外发布*****/
	int RELEASE_STATE_PUBLISH = 0;
	
	/***** 对外发布*****/
	int RELEASE_STATE_NOT_PUBLISH = 1;
}
