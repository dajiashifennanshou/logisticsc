package com.yc.Filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformUser;
import com.yc.Tool.DES;
import com.yc.Tool.FengUtil;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//String sign = request.getParameter("sign");
		try {
			//System.out.println(DES.decrypt(sign)+"=="+Constant.SIGN);
			chain.doFilter(req, res);//放行
			/*//验证签名
			if(DES.decrypt(sign).equals(Constant.SIGN)){
				String url = request.getRequestURI();//获取请求路径，过滤一些请求
				url = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
				//放行的接口
				if("dealerLogin".equals(url) //登录 
						|| "orderPayCallback".equals(url)//支付 回调
						|| "getBulletinInfo".equals(url)//获取公告
						|| "validCode".equals(url)//获取短信验证码
						|| "getCode".equals(url)//获取短信验证码
						|| "registerDealer".equals(url)//经销商注册
						|| "forgetPassword".equals(url)//忘记密码
				){
					chain.doFilter(req, res);//放行
				}else{
					LcPlatformUser user = (LcPlatformUser) request.getSession().getAttribute(request.getParameter("token"));
					//验证token是否失效
					if(user!=null){
						chain.doFilter(req, res);
					}else{
						FengUtil.OUTAPPSUCCESS(Constant.APP_Permission, response);
					}
				}
			}else{
				FengUtil.RuntimeExceptionFeng("没有访问权限");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
