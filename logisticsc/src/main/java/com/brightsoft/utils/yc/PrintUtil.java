package com.brightsoft.utils.yc;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: PrintUtil 
* @Description: TODO 页面输出帮助类
* @author FENG 
* @date 2015-3-24 下午05:14:01 
*
 */
public class PrintUtil {
	/**
	 * 
	* @Title: outprint 
	* @Description: TODO (输出到页面)
	* @param @param jstr
	* @param @param response  
	* @return void 
	* @throws
	 */
	public static void  outprint(String jstr,HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.print(jstr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				out.close();
			}
		}
	}
	
    /**
     * @Title: buildResult
     * @Description: TODO (构造返回结果json字符串)
     * @param code  返回结果码，必需
     * @param msg   返回消息，必需
     * @param data  返回数据，非必需，没有是传null
     * @param total 分页查询总记录数，非必需，没有是传null
     * @return void
     * @throws
     */
    public static String buildResult(Integer code, String msg, Object data, Integer total) {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code", code.intValue());
        result.put("msg", msg);
        if (null != data) {
            result.put("data", data);
        }
        if (null != total) {
            result.put("total", total.intValue());
        }
//        return GsonLIB.toJson(result, Map.class);
        return GsonUtil.toJson(result,Map.class);
    }
}
